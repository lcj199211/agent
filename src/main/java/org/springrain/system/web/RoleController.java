package  org.springrain.system.web;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
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
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.system.entity.Menu;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.Role;
import org.springrain.system.service.IMenuService;
import org.springrain.system.service.IOrgService;
import org.springrain.system.service.IRoleService;
import org.springrain.system.service.IUserRoleMenuService;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author weicms.net<Auto generate>
 * @version  2013-07-29 11:36:46
 * @see org.springrain.springrain.web.Role
 */
@Controller
@RequestMapping(value="/system/role")
public class RoleController  extends BaseController {
	@Resource
	private IRoleService roleService;
	@Resource
	private IUserRoleMenuService userRoleMenuService;
	@Resource
	private IOrgService orgService;
	@Resource
	private IMenuService menuService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	//角色管理页面  普通用户使用
	private String listurl="/system/role/roleList";
	//角色管理页面  管理员使用
	private String listurlAdmin="/system/role/roleListAdmin";
	

	/**
	 * 查询角色带权限  非管理员使用
	 * 
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, Role role) throws Exception {
		ReturnDatas returnObject = listjson(request, model, role);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 查询角色带权限  管理员使用
	 * 
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/all")
	public String listall(HttpServletRequest request, Model model, Role role) throws Exception {
		ReturnDatas returnObject = listjson(request, model, role);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurlAdmin;
	}

	/**
	 * 查询角色 带权限  非管理员使用
	 * 
	 * @param request
	 * @param model
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model, Role role) throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		if(betagent==null){
			returnObject.setStatus("error");
			return returnObject;
		}
		Page page = newPage(request);
		role.setAgentid(agentId);
		List<Role> datas =roleService.findListDataByFinder(null, page, Role.class, role);
		if(!CollectionUtils.isEmpty(datas)){
			for(Role r:datas){
				StringBuffer menunames=new StringBuffer();
				//查询部门名称
				if(StringUtils.isNotBlank(r.getOrgId())){
					Org org=orgService.findById(r.getOrgId(), Org.class);
					if(org!=null){
						r.setOrgname(org.getName()); 
					}
				}
				//查询角色对应菜单
				List<Menu> lsmenu=userRoleMenuService.findMenuByRoleId(r.getId());
				if(!CollectionUtils.isEmpty(lsmenu)){
					for(Menu m:lsmenu){
						menunames.append(m.getName()).append(",");
					}
				}
				r.setMenunames(menunames.toString());
			}
		}
		returnObject.setQueryBean(role);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	

	

	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/role/roleLook";
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Role role = userRoleMenuService.findRoleAndMenu(id);
			if(role!=null&&StringUtils.isNotBlank(role.getOrgId())){
				Org org=orgService.findById(role.getOrgId(),Org.class); 
				role.setOrgname(org.getName());    
			}
			returnObject.setData(role);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}

		return returnObject;

	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody ReturnDatas saveorupdate(Role role, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			String id=role.getId();
			if(StringUtils.isBlank(id)){
				role.setId(null);
			}
			
			String str_menuIds=request.getParameter("menuIds");
			if(StringUtils.isBlank(str_menuIds)){
				role.setMenus(null);
			}else{
				String[] menuIds=str_menuIds.split(",");
				if(menuIds!=null&&menuIds.length>0){
					List<Menu> menus=new ArrayList<Menu>();
					for(String s:menuIds){
						if(StringUtils.isBlank(s)){
							continue;
						}
						Menu m=new Menu();
						m.setId(s);
						menus.add(m);
					}
					
					role.setMenus(menus);
					
				}
				
				
			}
			String id2 = role.getId();
			role.setAgentid(agentid);
			role.setAgentparentid(betagent.getParentid());
			role.setAgentparentids(betagent.getParentids());
			roleService.saveorupdateRole(role);
			if(id2==null){
				 String details = "";
			     details = "新增"+role.getName()+"的角色";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());

			}else{
				 String details = "";
			     details = "更新"+role.getName()+"的角色";
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
	 * 角色添加修改页面   普通人员使用
	 */
	@RequestMapping(value = "/update/pre")
	public String edit(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/role/roleCru";
	}
	/**
	 * 角色添加修改页面  普通人员使用
	 */
	@RequestMapping(value = "/update/admin/pre")
	public String editadmin(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/role/roleCruAdmin";
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
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				Role role = roleService.queryForObject(new Finder("select*from t_role where id=:id ").setParam("id", id), Role.class);
				roleService.deleteRoleById(id);
				
				 String details = "";
			     details = "删除"+role.getName()+"的角色";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());

				return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	@RequestMapping(value = "/ajax/select2")
	public @ResponseBody List<Role> ajaxUser(HttpServletRequest request) throws Exception {
		String key=request.getParameter("q");
		Page page=new Page();
		page.setPageIndex(1);
		
		Finder finder=Finder.getSelectFinder(Role.class, "id,name").append(" WHERE roleType=1 and name like :name order by name asc ");
		finder.setParam("name", key+"%");
		return roleService.queryForList(finder,Role.class, page);
		
	}
	

}
