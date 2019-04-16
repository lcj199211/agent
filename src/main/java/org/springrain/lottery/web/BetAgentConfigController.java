package  org.springrain.lottery.web;

import java.math.BigDecimal;
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
import org.springrain.lottery.entity.BetChannelcharge;
import org.springrain.lottery.entity.BetLotterychannelaccess;
import org.springrain.lottery.entity.BetOptLog;
import org.springrain.lottery.entity.BetTransferagentAccounts;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetChannelchargeService;
import org.springrain.lottery.service.IBetLotterychannelaccessService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetTransferagentAccountsService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.system.entity.Fwlog;
import org.springrain.system.service.IFwlogService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-11 15:44:16
 * @see org.springrain.lottery.web.BetAgent
 */
@Controller
@RequestMapping(value="/agent/config")
public class BetAgentConfigController  extends BaseController {
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetLotterychannelaccessService betLotterychannelaccessService;
	@Resource
	private IBetChannelchargeService channelchargeService;
	@Resource
	private IBetTransferagentAccountsService betTransferagentAccountsService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IFwlogService fwlogService;
	private String listurl="/lottery/config/channelList";
	private static String[] issue_models={"手动出票","系统出票","店内出票"};
	

	public BetAgent getAgent() throws Exception {
		String agentid = SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		return agent;
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betNotice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetLotterychannelaccess channel) 
			throws Exception {
		BetAgent agent = getAgent();
		channel.setCompany(agent.getAgentid());
		ReturnDatas returnObject = listjson(request, model, channel);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		model.addAttribute("issuemodel", agent.getIssuemodel());//出票模式
		model.addAttribute("issuebalance", agent.getIssuebalance());//系统出票余额
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betNotice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetLotterychannelaccess channel) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(30);
		// ==执行分页查询
		List<BetLotterychannelaccess> datas=betLotterychannelaccessService.findListDataByFinder(null,page,BetLotterychannelaccess.class,channel);
		Double issuebalance2 = 0.0;
		if(datas != null){
			for (BetLotterychannelaccess betLotterychannelaccess : datas) {
				issuebalance2 += betLotterychannelaccess.getMoney()==null?0.0:betLotterychannelaccess.getMoney();
			}
		}
		model.addAttribute("issuebalance2", issuebalance2);//店内出票余额
		returnObject.setQueryBean(channel);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * 渠道充值
	 */
	@RequestMapping("/issuecharge")
	public @ResponseBody
	ReturnDatas chargeChannel(BetChannelcharge channelcharge,Integer issuemodel,Model model,BetAgent betAgent,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		BetAgent agent = getAgent();
		try {
			if(issuemodel == null){//无效模式
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无效操作");
			}
			if(channelcharge.getMoney() == null || channelcharge.getMoney().compareTo(BigDecimal.valueOf(0L)) <= 0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额需大于0");
			}
			if(issuemodel == 1){//系统
				channelcharge.setRemark("系统出票余额充值申请");
			}else if(issuemodel == 2){//店内
				if(channelcharge.getChannelid() == null){//手动出票模式，无需设置
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无效渠道");
				}
				BetLotterychannelaccess channel = betLotterychannelaccessService.findById(channelcharge.getChannelid(), BetLotterychannelaccess.class);
				if(channel == null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无效渠道");
				}
				channelcharge.setChannelname(channel.getName());
				channelcharge.setRemark("店内出票"+channel.getName()+"渠道余额充值申请");
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无效操作");
			}
			channelcharge.setAgentid(agent.getAgentid());
			channelcharge.setAgentaccount(agent.getAccount());
			channelcharge.setAgentnickname(agent.getNickname());
			channelcharge.setAgentparentid(agent.getParentid());
			channelcharge.setAgentparentids(agent.getParentids());
			channelcharge.setSubmittime(new Date());
			channelcharge.setState(0);
			channelchargeService.save(channelcharge);
			
			//操作日志
			String details = "";
		    details = issue_models[issuemodel] + "出票余额充值申请";
		    String ip = IPUtils.getClientAddress(request);
		    String tool = AgentToolUtil.getAgentTool(request);
		    betOptLogService.saveoptLog(tool, ip, details, agent.getAgentid(), agent.getParentid(), agent.getParentids());
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * @param command 指令
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Integer command,Model model,BetAgent betAgent,BetLotterychannelaccess channel,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		BetAgent agent = getAgent();
		try {
			if(command == null){//指令错误
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无效操作");
			}
			
			if(command == 1){//出票设置
				if(betAgent.getIssuemodel() == null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("请设置正确的模式");
				}
				int issuemodel = betAgent.getIssuemodel();
				if(issuemodel == 0){//手动出票模式，无需设置
					
				}else if(issuemodel == 1){//系统出票模式
					
				}else if(issuemodel == 2){//店内出票模式
					//校验 channel
					
					//betLotterychannelaccessService
					//需新增渠道
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("尚在开发");
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("非法模式");
				}
				Integer update = betAgentService.update(
						new Finder("update bet_agent set issuemodel=:issuemodel where agentid=:agentid")
						.setParam("issuemodel", issuemodel).setParam("agentid", agent.getAgentid()));
				if(update == 1){
					//操作日志
					String details = "";
				    details = "修改出票模式为"+issue_models[issuemodel];
				    String ip = IPUtils.getClientAddress(request);
				    String tool = AgentToolUtil.getAgentTool(request);
				    betOptLogService.saveoptLog(tool, ip, details, agent.getAgentid(), agent.getParentid(), agent.getParentids());
				}
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
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		BetAgent agent = getAgent();
		model.addAttribute("agentid", agent.getAgentid());
		model.addAttribute("agent", agent);
		
		return "/lottery/config/issueConfig";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
			String  strId=request.getParameter("id");
			java.lang.Integer id=null;
			if(StringUtils.isNotBlank(strId)){
				 id= java.lang.Integer.valueOf(strId.trim());
				 betLotterychannelaccessService.deleteById(id,BetLotterychannelaccess.class);
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
			betAgentService.deleteByIds(ids,BetAgent.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	public ReturnDatas addc_lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BetLotterychannelaccess betLotterychannelaccess = betLotterychannelaccessService.findBetLotterychannelaccessById(id);
		   returnObject.setData(betLotterychannelaccess);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 出票渠道
	 * 
	 */
	@RequestMapping("/addchannel")
	public @ResponseBody
	ReturnDatas addchannel(Model model,BetLotterychannelaccess betLotterychannelaccess,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			BetAgent agent = getAgent();
			Double rebate = betLotterychannelaccess.getRebate();
			if(rebate==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				return returnObject;
			}
			betLotterychannelaccess.setRebate(rebate/100);
			String id = request.getParameter("id");
			if(StringUtils.isEmpty(id)){//设置渠道归属
				betLotterychannelaccess.setCompany(agent.getAgentid());
				betLotterychannelaccess.setBelonging(2);
				betLotterychannelaccessService.save(betLotterychannelaccess);
			}else{
				betLotterychannelaccessService.update(betLotterychannelaccess,true);
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
	@RequestMapping(value = "/addchannel/pre")
	public String addchannelpre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = addc_lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/config/betlotterychannelaccessCru";
	}
	
	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		String id=request.getParameter("id");
		BetAgent agent = betAgentService.findBetAgentById(id);
		model.addAttribute("id", agent.getId());
		model.addAttribute("agentid", agent.getAgentid());
		model.addAttribute("agentsx", agent.getScore());
		if("1".equals(request.getParameter("k"))){
			BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			Double sx = betAgent.getScore();
			model.addAttribute("sx", sx);
			return "/lottery/betagent/betagenttransferaccount";
		}else if("2".equals(request.getParameter("k"))){
			returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<BetTransferagentAccounts> accountsList = betTransferagentAccountsService.queryForList(new Finder("select * from bet_transferagent_accounts where transfermanagentid=:transfermanagentid").setParam("transfermanagentid", request.getParameter("agentid")), BetTransferagentAccounts.class,page);
			returnObject.setPage(page);
			returnObject.setData(accountsList);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betagenttransferaccountList";
		}else if("3".equals(request.getParameter("k"))){
			//登录日志
			String account = request.getParameter("account");
			returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("strDate");
			page.setSort("desc");
			List<Fwlog> datas = fwlogService.queryForList(new Finder("select * from t_fwlog_history_2017 where userCode=:account").setParam("account", account), Fwlog.class,page);
			returnObject.setQueryBean(new Fwlog());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("account", account);
			model.addAttribute("id", id);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betagentfwlogList";
		}else if("4".equals(request.getParameter("k"))){
			//操作日志
			String account = request.getParameter("account");
			returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("time");
			page.setSort("desc");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BetOptLog> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas = betOptLogService.queryForList(new Finder("select * from bet_opt_log where account=:account").setParam("account", account), BetOptLog.class,page);
			}else{
				datas = betOptLogService.queryForList(new Finder("select * from bet_opt_log where account=:account and substr(time,1,10)>=:starttime and substr(time,1,10)<=:endtime").setParam("account", account).setParam("starttime",startDate).setParam("endtime", endDate), BetOptLog.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			returnObject.setQueryBean(new BetOptLog());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("account", account);
			model.addAttribute("id", id);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betoptlogList";
		}else{
			return "/lottery/betagent/betagentLook";
			
		}
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
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
