package org.springrain.system.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.system.entity.Role;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.service.IUserService;

/**
 * 用户管理Controller,PC和手机浏览器用ACE自适应,APP提供JSON格式的数据接口
 * 
 * @copyright {@link weicms.net}
 * @author weicms.net<Auto generate>
 * @version 2014-06-26 11:36:47
 * @see org.springrain.Agent.web.User
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {
	@Resource
	private IUserService userService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl = "/system/user/userList";

	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, User user)
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, user);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model, User user)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		
		Integer active=user.getActive();
		if(active==null){
			user.setActive(1);
		}
		
		
		List<User> datas = userService.findListDataByFinder(null, page,
				User.class, user);
		returnObject.setQueryBean(user);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	
	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/user/userLook";
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			User user = userService.findUserById(id);
			returnObject.setData(user);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
	}

	/**
	 * 修改启用停用状态
	 * 
	 */
	@RequestMapping("/update/s")
	public String update(User user,HttpServletRequest request) throws Exception{
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid limit 1"), BetAgent.class);
			String id =user.getId();
			userService.update(user,true);
			//日志记录
		    String details = "";
		    User user1 = userService.queryForObject(new Finder("select*from t_user where id=:id ").setParam("id", id), User.class);
		    details = "停用"+user1.getAccount()+"的管理员";
		    String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
		}
		return "redirect:/system/user/list?springraintoken="+request.getSession().getAttribute(GlobalStatic.tokenKey);
	
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(User user, HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid"), BetAgent.class);
			String id = user.getId();
			String password = user.getPassword();

			if (StringUtils.isBlank(password)) {
				user.setPassword(null);
			} else {
				user.setPassword(SecUtils.encoderByMd5With32Bit(password));
			}
			String[] roleIds=request.getParameterValues("roleIds");
			List<Role> listRole=null;
			if(roleIds!=null&&roleIds.length>0){
				Set<String> set=new HashSet<String>();
				for(String s:roleIds){
					if(StringUtils.isBlank(s)){
						continue;
					}
					set.add(s);
				}
				listRole=new ArrayList<Role>();
				for(String s2:set){
					Role role=new Role();
					role.setId(s2);
					listRole.add(role);
				}
			}
			user.setUserRoles(listRole);
			
			//处理管理的部门  韩彦阳   开始
			String[]  managerOrgNames= request.getParameterValues("managerOrgNames");
			String[] managerOrgIds = request.getParameterValues("managerOrgIds");
			String[] hasleafs = request.getParameterValues("hasleaf");
			String[] managerTypes = request.getParameterValues("managerType");
			List<UserOrg> managerOrgs=null;
			if(managerOrgNames!=null&&managerOrgIds!=null&&managerOrgIds.length==managerOrgNames.length){
				managerOrgs=new ArrayList<UserOrg>();
				UserOrg managerOrg=null;
				for(int i=0;i<managerOrgIds.length;i++){
					managerOrg=new UserOrg();
					managerOrg.setOrgId(managerOrgIds[i]);
					managerOrg.setUserId(id);//可能为空，service中再补全
					managerOrg.setManagerType(Integer.valueOf(managerTypes[i]));
					managerOrg.setHasleaf(Integer.valueOf(hasleafs[i]));
					if(Integer.valueOf(managerTypes[i])<=10){
						//会员  或 员工
						managerOrg.setHasleaf(0);//没有用
					}
					managerOrgs.add(managerOrg);
				}
			}
			user.setManagerOrgs(managerOrgs);
			//处理管理的部门  韩彦阳  结束
			if (StringUtils.isBlank(id)) {
				user.setId(null);
				userService.saveUser(user);
				String details = "";
			    details = "新增"+user.getAccount()+"的管理员";
			    String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			} else {
				String account = user.getAccount();
				user.setAccount(null);
				userService.updateUser(user);
				String details = "";
			    details = "更新"+account+"的管理员";
			    String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}

	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String edit(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/user/userCru";
	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody
	ReturnDatas destroy(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid"), BetAgent.class);
			java.lang.String id = request.getParameter("id");
			
			if (StringUtils.isBlank(id)) {
				return new ReturnDatas(ReturnDatas.ERROR, "删除失败,用户Id不能为空!"); 
			}
			
		    userService.deleteUserById(id);
		    String details = "";
		    details = "删除"+id+"的管理员";
		    String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR, "删除失败!");
		}
		return  new ReturnDatas(ReturnDatas.SUCCESS, "用户删除成功!"); 
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	public @ResponseBody
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			userService.deleteUserByIds(ids,User.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

	@RequestMapping(value = "/ajax/select2")
	public @ResponseBody List<User> ajaxUser(HttpServletRequest request) throws Exception {
		String key=request.getParameter("q");
		Page page=new Page();
		page.setPageIndex(1);
		
		Finder finder=Finder.getSelectFinder(User.class, "id,name").append(" WHERE account like :account order by account asc ");
		finder.setParam("account", key+"%");
		
		return userService.queryForList(finder,User.class, page);
		
	}
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/modifiypwd/pre")
	public String modifiypwdpre(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = SessionUser.getUserId();
		if(StringUtils.isEmpty(userId)){
			return "/system/user/modifiypwd";
		}
		//获取当前登录人
		User currentUser = userService.findUserById(userId);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setData(currentUser);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/user/modifiypwd";
	}
	@RequestMapping(value="/modifiypwd/ispwd")
	public @ResponseBody Map<String, Object> checkPwd(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String userId = SessionUser.getUserId();
		String pwd=request.getParameter("pwd");
		Map<String, Object> maps=new HashMap<String, Object>();
		User user = userService.findById(userId, User.class);
		if(user==null){
			maps.put("msg", "-1");
			maps.put("msgbox", "数据有问题，正在返回");
			return maps;
		}
		if(user.getPassword().equals(SecUtils.encoderByMd5With32Bit(pwd))){
			maps.put("msg", "1");
			maps.put("msgbox", "正确");
			return maps;
		}else{
			maps.put("msg", "0");
			maps.put("msgbox", "原始密码错误，请修改");
			return maps;
		}
		
	}
	@RequestMapping(value="/modifiypwd/save")
	public @ResponseBody ReturnDatas modifiySave(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		ReturnDatas datas=ReturnDatas.getSuccessReturnDatas();
//		String userId=request.getParameter("id");
		String userId=SessionUser.getUserId();
		
		String pwd=request.getParameter("newpwd");
		String repwd=request.getParameter("renewpwd");
		String oldpassword=request.getParameter("oldpassword");
		User user = userService.findById(userId, User.class);
		if(user==null){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("数据有问题，正在返回");
			return datas;
		}
		if(StringUtils.isEmpty(pwd)||StringUtils.isEmpty(repwd)){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("新密码或重复密码为空，请修改。");
			return datas;
		}
		pwd=pwd.trim();
		repwd=repwd.trim();
		if(!pwd.equals(repwd)){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("两次密码不一致，请修改。");
			return datas;
		}
		if(!user.getPassword().equals(SecUtils.encoderByMd5With32Bit(oldpassword))){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("原始登录密码错误，请修改");
			return datas;
		}
		try{
			user.setPassword(SecUtils.encoderByMd5With32Bit(pwd));
			userService.update(user);
			datas.setMessage("修改成功，请用新密码登录，即将退出。");
			return datas;
		}catch(Exception e){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("系统故障，请稍后再试。");
			return datas;
		}
		
	}
	
	
	
}
