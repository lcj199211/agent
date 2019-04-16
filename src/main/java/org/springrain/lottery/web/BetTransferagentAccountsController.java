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

import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentRechargeRebate;
import org.springrain.lottery.entity.BetTransferagentAccounts;
import org.springrain.lottery.service.IBetAgentRechargeRebateService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetTransferagentAccountsService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;



/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-08 09:55:02
 * @see org.springrain.lottery.web.BetTransferagentAccounts
 */
@Controller
@RequestMapping(value="/bettransferagentaccounts")
public class BetTransferagentAccountsController  extends BaseController {
	@Resource
	private IBetTransferagentAccountsService betTransferagentAccountsService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentRechargeRebateService betAgentRechargeRebateService;
	
	private String listurl="/lottery/bettransferagentaccounts/bettransferagentaccountsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betTransferagentAccounts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetTransferagentAccounts betTransferagentAccounts) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
//		ReturnDatas returnObject = listjson(request, model, betTransferagentAccounts);
//		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		List<BetTransferagentAccounts> datas=null;
		if("1".equals(request.getParameter("a"))){
			datas = betTransferagentAccountsService.findListDataByFinder(new Finder("select * from bet_transferagent_accounts where transfermanagentid=:agentid").setParam("agentid", agentid), page, BetTransferagentAccounts.class, betTransferagentAccounts);
			returnObject.setQueryBean(betTransferagentAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("a",1);
			return "/lottery/betagent/betagenttransferaccountarriveList";
			
		}else if("2".equals(request.getParameter("a"))){
			String parameter = request.getParameter("xxx");
			datas = betTransferagentAccountsService.findListDataByFinder(new Finder("select * from bet_transferagent_accounts where agentid=:agentid").setParam("agentid", parameter), page, BetTransferagentAccounts.class, betTransferagentAccounts);
			returnObject.setQueryBean(betTransferagentAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("a",2);
			model.addAttribute("xxx", parameter);
			return "/lottery/betagent/betagenttransferaccountarriveList";
		}else if("3".equals(request.getParameter("a"))){
			String parameter = request.getParameter("xxx");
			datas = betTransferagentAccountsService.findListDataByFinder(new Finder("select * from bet_transferagent_accounts where transfermanagentid=:agentid and type=1 ").setParam("agentid", parameter), page, BetTransferagentAccounts.class, betTransferagentAccounts);
			returnObject.setQueryBean(betTransferagentAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("a",3);
			model.addAttribute("xxx", parameter);
			return "/lottery/betagent/betagenttransferaccountarriveList1";
		}else if("4".equals(request.getParameter("a"))){
			String parameter = request.getParameter("xxx");
			datas = betTransferagentAccountsService.findListDataByFinder(new Finder("select * from bet_transferagent_accounts where transfermanagentid=:agentid ").setParam("agentid", parameter), page, BetTransferagentAccounts.class, betTransferagentAccounts);
			returnObject.setQueryBean(betTransferagentAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("a",4);
			model.addAttribute("xxx", parameter);
			return "/lottery/betagent/betagenttransferaccountarriveList";
		}else{
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betTransferagentAccounts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetTransferagentAccounts betTransferagentAccounts) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetTransferagentAccounts> datas=betTransferagentAccountsService.findListDataByFinder(null,page,BetTransferagentAccounts.class,betTransferagentAccounts);
			returnObject.setQueryBean(betTransferagentAccounts);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetTransferagentAccounts betTransferagentAccounts) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betTransferagentAccountsService.findDataExportExcel(null,listurl, page,BetTransferagentAccounts.class,betTransferagentAccounts);
		String fileName="betTransferagentAccounts"+GlobalStatic.excelext;
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
		return "/lottery/bettransferagentaccounts/bettransferagentaccountsLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BetTransferagentAccounts betTransferagentAccounts = betTransferagentAccountsService.findBetTransferagentAccountsById(id);
		   returnObject.setData(betTransferagentAccounts);
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
	ReturnDatas saveorupdate(Model model,BetTransferagentAccounts betTransferagentAccounts,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String transfermanagentid = betTransferagentAccounts.getTransfermanagentid();
		String agentid = SessionUser.getShiroUser().getAgentid();
		if("1".equals(request.getParameter("u"))){
			//确保是否为下线
			String parentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", transfermanagentid), String.class);
			String pid[] = parentids.split(",");
			int flag=0;
			for (int i = 1; i < pid.length; i++) {
				if (agentid.equals(pid[i])) {
					flag=1;
				}
			}
			
			Date date=new Date();
			String remark = betTransferagentAccounts.getRemark();
			Double transferaccountsscore = betTransferagentAccounts.getTransferaccountsscore();
			BetAgent agent = betTransferagentAccountsService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			String pwd = agent.getBankpassword();
			String bankpwd = request.getParameter("pwd");
			bankpwd = SecUtils.encoderByMd5With32Bit(bankpwd);
			if(agent.getTransferaccount()==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("您没有转账权限,请联系上级");
				return returnObject;
			}
			if(flag==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("不是你的下线,不能转账");
				return returnObject;
			}
			if(!pwd.equals(bankpwd)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("密码错误");
				return returnObject;
			}
			if(transferaccountsscore>agent.getScore()){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("转账分不能超过金币");
				return returnObject;
			}
			BetAgent betAgent = null;
			if(transfermanagentid!=null){
				betAgent = betAgentService.queryForObject(new Finder("select*from bet_agent where agentid=:agentid ").setParam("agentid", transfermanagentid), BetAgent.class);
				if(betAgent==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此接收ID用户");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("接收ID不能为空");
				return returnObject;
			}
			if(transferaccountsscore==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("转账分不能为零");
				return returnObject;
			}
			if(transferaccountsscore<0){
				if((-transferaccountsscore)>betAgent.getScore()){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("转账分不能超过余额");
					return returnObject;
				}
			}
			String account = SessionUser.getShiroUser().getAccount();
			String name = SessionUser.getShiroUser().getName();
			if(name==null){
				name="";
			}
			BetAgent agent2 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", transfermanagentid), BetAgent.class);
			//转账记录
			BetTransferagentAccounts transferagentAccounts=new BetTransferagentAccounts();
			transferagentAccounts.setTransfermanagentid(transfermanagentid);
			transferagentAccounts.setRemark(remark);
			transferagentAccounts.setTime(date);
			transferagentAccounts.setTransferaccountsscore(transferaccountsscore);
			transferagentAccounts.setTransferman(account+"(代理商"+agentid+")");
			String ip = IPUtils.getClientAddress(request);
			transferagentAccounts.setIp(ip);
			transferagentAccounts.setAgentid(agentid);
			transferagentAccounts.setAgentparentid(agent.getParentid());
			transferagentAccounts.setAgentparentids(agent.getParentids());
			transferagentAccounts.setType(0);
			betTransferagentAccountsService.save(transferagentAccounts);
			BetTransferagentAccounts transferagentAcoounts2=new BetTransferagentAccounts();
			List<BetAgentRechargeRebate> betagentrechargerebatelist = betAgentRechargeRebateService.queryForList(new Finder("select *from bet_agent_recharge_rebate where agentid=:agentid ").setParam("agentid", transfermanagentid), BetAgentRechargeRebate.class);
			Double rechargerebate=0.;
			if(betagentrechargerebatelist!=null){
				for (BetAgentRechargeRebate betAgentRechargeRebate : betagentrechargerebatelist) {
					if(betAgentRechargeRebate.getLowerlimit()!=null&&(betAgentRechargeRebate.getLowerlimit()<=transferaccountsscore)){
						if(betAgentRechargeRebate.getUpperlimit()==null){
							rechargerebate=betAgentRechargeRebate.getRebate();
						}else{
							if(betAgentRechargeRebate.getUpperlimit()>transferaccountsscore){
								rechargerebate=betAgentRechargeRebate.getRebate();
							}
						}
					}
				}
			}
			if(rechargerebate!=null&&rechargerebate!=0.){
				transferagentAcoounts2.setTransfermanagentid(transfermanagentid);
				transferagentAcoounts2.setRemark("充值返利");
				transferagentAcoounts2.setTime(date);
				transferagentAcoounts2.setTransferaccountsscore(transferaccountsscore*rechargerebate);
				transferagentAcoounts2.setTransferman(account+"(代理商"+agentid+")");
				transferagentAcoounts2.setIp(ip);
				transferagentAcoounts2.setAgentid(agentid);
				transferagentAcoounts2.setAgentparentid(agent.getParentid());
				transferagentAcoounts2.setAgentparentids(agent.getParentids());
				transferagentAcoounts2.setType(1);
				betTransferagentAccountsService.save(transferagentAcoounts2);
			}
			if(transferagentAcoounts2.getTransferaccountsscore()!=null){
				agent2.setScore(agent2.getScore()+transferaccountsscore+transferagentAcoounts2.getTransferaccountsscore());
				agent2.setTy(agent2.getTy()+transferagentAcoounts2.getTransferaccountsscore());
			}else{
				agent2.setScore(agent2.getScore()+transferaccountsscore);
			}
			
			betAgentService.update(agent2, true);
			//操作日志
			 String details = "";
			 if(transferagentAcoounts2.getTransferaccountsscore()!=null){
				 details = "给ID为："+agent2.getAgentid()+"的代理商转账："+transferaccountsscore+"元,"+"充值返利："+transferagentAcoounts2.getTransferaccountsscore()+"元";
			 }else{
				 details = "给ID为："+agent2.getAgentid()+"的代理商转账："+transferaccountsscore+"元";
			 }
		     
		     String ip1 = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip1,details,agentid,agent.getParentid(),agent.getParentids());
		     betAgentService.update(new Finder("update bet_agent set score=score-:score where agentid=:agentid").setParam("score", transferaccountsscore).setParam("agentid", agentid));
			returnObject.setMessage("转账成功");
			return returnObject;
		}else{
			
		
		try {
			betTransferagentAccountsService.saveorupdate(betTransferagentAccounts);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
		}
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/bettransferagentaccounts/bettransferagentaccountsCru";
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
				betTransferagentAccountsService.deleteById(id,BetTransferagentAccounts.class);
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
			betTransferagentAccountsService.deleteByIds(ids,BetTransferagentAccounts.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
