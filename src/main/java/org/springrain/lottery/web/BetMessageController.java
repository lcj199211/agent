package  org.springrain.lottery.web;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetMessage;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetMessageService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 11:32:52
 * @see org.springrain.lottery.web.BetMessage
 */
@Controller
@RequestMapping(value="/betmessage")
public class BetMessageController  extends BaseController {
	@Resource
	private IBetMessageService betMessageService;
	
	@Resource
	private IBetMemberService betMemberService;
	
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betmessage/betmessageList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betMessage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetMessage betMessage) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betMessage);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betMessage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetMessage betMessage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("time");
		page.setSort("desc");
		// ==执行分页查询
		List<BetMessage> datas=betMessageService.findListDataByFinder(null,page,BetMessage.class,betMessage);
			returnObject.setQueryBean(betMessage);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetMessage betMessage) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betMessageService.findDataExportExcel(null,listurl, page,BetMessage.class,betMessage);
		String fileName="betMessage"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betmessage/betmessageLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  BetMessage betMessage = betMessageService.findBetMessageById(id);
		   returnObject.setData(betMessage);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BetMessage betMessage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid"),BetAgent.class);

		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =betMessage.getId();
			if(StringUtils.isBlank(id)){
			  betMessage.setId(null);
			}
			//指定用户
			String zd = request.getParameter("zd");
			if(zd != null && !"".equals(zd)){
				betMessage.setReceiver(zd);
			}
			betMessage.setSender(SessionUser.getUserCode());
			betMessage.setTime(new Date());
			betMessageService.saveorupdate(betMessage);
			//日志
			String details = SessionUser.getUserCode()+"给";
			if("all".equals(betMessage.getReceiver().toLowerCase())){
				details += "所有用户"; 
			}else if("vip".equals(betMessage.getReceiver().toLowerCase())){
				details += "vip用户"; 
			}else{
				details += betMessage.getReceiver(); 
			}
			details+="发送站内消息:"+betMessage.getTitle();
			String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
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
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betmessage/betmessageCru";
	}
	
	/**
	 * 进入修改页面,Ajax提交，获取会员列表
	 */
	@RequestMapping(value = "/update/json")
	@ResponseBody
	public List<BetMember> getMembers(Model model,HttpServletRequest request,HttpServletResponse response,BetMember betMember)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		List<BetMember> betMembers = betMemberService.findListDataByFinder(null, null, BetMember.class, betMember);
		returnObject.setData(betMembers);
		return betMembers;
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid"), BetAgent.class);
			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			BetMessage betMessage = betMessageService.queryForObject(new Finder("select *from bet_message where id=:id ").setParam("id", id), BetMessage.class);
				betMessageService.deleteById(id,BetMessage.class);
				
				//日志
				String details = SessionUser.getUserCode()+"删除站内消息:"+betMessage.getTitle();
				String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	public @ResponseBody
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String agentid = SessionUser.getShiroUser().getAgentid();
		try {
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid"), BetAgent.class);
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
		
			List<String> ids = Arrays.asList(rs);
			betMessageService.deleteByIds(ids,BetMessage.class);
			//日志
			String details = SessionUser.getUserCode()+"删除了多条站内消息";
			String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
