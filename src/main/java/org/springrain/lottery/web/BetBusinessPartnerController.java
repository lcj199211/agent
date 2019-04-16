package org.springrain.lottery.web;

import java.util.ArrayList;
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
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;

@Controller
@RequestMapping(value="/businesspartner")
public class BetBusinessPartnerController extends BaseController{
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetOptLogService betOptLogService;
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception {
			String agentid = SessionUser.getShiroUser().getAgentid();
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			String id = request.getParameter("agentid");
			List<BetAgent> datas = null;
			if(id == null){
				id = agentid;
			}
			datas = betAgentService.queryForList(new Finder("select * from bet_agent where parentid =:id").setParam("id", id), BetAgent.class, page);
			String parentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", id), String.class); 
			String agent[] = parentids.split(",");
			List<String> list = new ArrayList<String>();
			for (int i = 1; i < agent.length; i++) {
				list.add(agent[i]);
			}
			list.add(id);
			String currentparentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class);
			String cuagent[] = currentparentids.split(",");
			List<String> list2 = new ArrayList<String>();
			for (int i = 1; i < cuagent.length; i++) {
				list2.add(cuagent[i]);
			}
			list.removeAll(list2);
			returnObject.setQueryBean(betAgent);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("agentid", agentid);
			model.addAttribute("List", list);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/businessPartnerList";
	}
	
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BetAgent betAgent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid = SessionUser.getShiroUser().getAgentid();
		try {
			if("1".equals(request.getParameter("c"))){
				String strbankpwd = betAgentService.queryForObject(new Finder("select bankpassword from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class);
				String bankpassword = betAgent.getBankpassword();
				if(strbankpwd.equals(bankpassword)){
					betAgentService.update(new Finder("update bet_agent set nickname=:nickname,mobile=:mobile,qq=:qq,weixin=:weixin where agentid=:agentid").setParam("nickname", betAgent.getNickname()).setParam("mobile", betAgent.getMobile()).setParam("qq", betAgent.getQq()).setParam("weixin", betAgent.getWeixin()).setParam("agentid", agentid));
				}else{
					bankpassword = SecUtils.encoderByMd5With32Bit(bankpassword);
					betAgentService.update(new Finder("update bet_agent set nickname=:nickname,bankpassword=:bankpassword,mobile=:mobile,qq=:qq,weixin=:weixin where agentid=:agentid").setParam("nickname", betAgent.getNickname()).setParam("bankpassword", bankpassword).setParam("mobile", betAgent.getMobile()).setParam("qq", betAgent.getQq()).setParam("weixin", betAgent.getWeixin()).setParam("agentid", agentid));
				}
				betOptLogService.saveoptLog(AgentToolUtil.getAgentTool(request),IPUtils.getClientAddress(request),"代理商id为:"+agentid+" 修改个人信息",agentid,betAgent.getParentid(),betAgent.getParentids());
			}else{
			String strbankpwd = betAgentService.queryForObject(new Finder("select bankpassword from bet_agent where agentid=:agentid").setParam("agentid", request.getParameter("agentid")), String.class);
			String bankpassword = betAgent.getBankpassword();
			if(strbankpwd.equals(bankpassword)){
				betAgentService.update(new Finder("update bet_agent set nickname=:nickname,mobile=:mobile,qq=:qq,weixin=:weixin where agentid=:agentid").setParam("nickname", betAgent.getNickname()).setParam("mobile", betAgent.getMobile()).setParam("qq", betAgent.getQq()).setParam("weixin", betAgent.getWeixin()).setParam("agentid", request.getParameter("agentid")));
			}else{
				bankpassword = SecUtils.encoderByMd5With32Bit(bankpassword);
				betAgentService.update(new Finder("update bet_agent set nickname=:nickname,bankpassword=:bankpassword,mobile=:mobile,qq=:qq,weixin=:weixin where agentid=:agentid").setParam("nickname", betAgent.getNickname()).setParam("bankpassword", bankpassword).setParam("mobile", betAgent.getMobile()).setParam("qq", betAgent.getQq()).setParam("weixin", betAgent.getWeixin()).setParam("agentid", request.getParameter("agentid")));
			}
			betOptLogService.saveoptLog(AgentToolUtil.getAgentTool(request),IPUtils.getClientAddress(request),"代理商id为:"+agentid+" 修改id为"+request.getParameter("agentid")+"的代理商信息",agentid,betAgent.getParentid(),betAgent.getParentids());
			}
		} catch (Exception e) {
			System.out.println(e);
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", request.getParameter("agentid")), BetAgent.class);
		model.addAttribute("agent", agent);
		return "/lottery/betagent/betagentManage";
	}
	
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String parameter = request.getParameter("agentid");
		if(StringUtils.isNotBlank(parameter)){
			BetAgent queryForObject = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", parameter), BetAgent.class);
			returnObject.setData(queryForObject);
			return returnObject;
		}
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  BetAgent betAgent = betAgentService.findBetAgentById(id);
		   returnObject.setData(betAgent);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
}
