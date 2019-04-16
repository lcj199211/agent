package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

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
import org.springrain.frame.service.BaseServiceImpl;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentgold;
import org.springrain.lottery.entity.BetAgentwithdraw;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAgentwithdrawService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.system.entity.BetAgentData;
import org.springrain.system.service.IBetAgentDataService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-25 15:08:35
 * @see org.springrain.lottery.web.BetAgentwithdraw
 */
@Controller
@RequestMapping(value="/betagentwithdraw")
public class BetAgentwithdrawController  extends BaseController {
	@Resource
	private IBetAgentwithdrawService betAgentwithdrawService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentDataService betAgentDataService;
	
	private String listurl="/lottery/betagentwithdraw/betagentwithdrawList";
	
	
	/**
	 * 代理提现记录
	 * @param request
	 * @param model
	 * @param betAgentwithdraw
	 * @return
	 * @throws Exception
	 * /betagentwithdraw/withdrawlist
	 */
	@RequestMapping("/withdrawlist/json")
	public @ResponseBody
	ReturnDatas withdrawjson(HttpServletRequest request, Model model,BetAgentwithdraw betAgentwithdraw) throws Exception{
		String agentid = SessionUser.getAgentId();
		String pageIndex = request.getParameter("pageIndex");
		if(StringUtils.isEmpty(pageIndex)){
			pageIndex="1";
		}
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(20);
		page.setPageIndex(Integer.valueOf(pageIndex));
		List<BetAgentwithdraw> datas = betAgentwithdrawService.queryForList(new Finder("select applicationtime,audittime,bwcs,awcs,money,realname,state,remark from bet_agentwithdraw where agentid=:agentid order by applicationtime desc").setParam("agentid", agentid), BetAgentwithdraw.class,page);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		// ==执行分页查询
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	@RequestMapping("/withdrawlist")
	@ResponseBody
	public ReturnDatas withdrawlist(HttpServletRequest request, Model model,BetAgentwithdraw betAgentwithdraw) 
			throws Exception {
		ReturnDatas returnObject = withdrawjson(request, model, betAgentwithdraw);
		return returnObject;
	}
	/**
	 * 手机提现
	 * betagentwithdraw/withdraw
	 */
	@RequestMapping("/withdraw")
	public @ResponseBody
	ReturnDatas withdraw(Model model,BetAgentwithdraw betAgentwithdraw,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String money = request.getParameter("money");
		String remark = new String(request.getParameter("remark").getBytes("ISO-8859-1"), "UTF-8");
		String realname =new String(request.getParameter("realname").getBytes("ISO-8859-1"), "UTF-8");
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		try {
			if(Double.valueOf(money)>(betagent.getScore().doubleValue())){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可大于余额！");
				return returnObject;
			}
			betAgentwithdraw.setId(System.currentTimeMillis()+new Random().nextInt(100)+"");
			betAgentwithdraw.setAgentid(agentid);
			betAgentwithdraw.setAgentaccount(betagent.getAccount());
			betAgentwithdraw.setAgentnickname(betagent.getNickname());
			betAgentwithdraw.setApplicationtime(new Date());
			betAgentwithdraw.setState(0);
			betAgentwithdraw.setRealname(realname);
			betAgentwithdraw.setAgentparentid(betagent.getParentid());
			betAgentwithdraw.setAgentparentids(betagent.getParentids());
			betAgentwithdraw.setAwcs(new BigDecimal((betagent.getScore()-Double.valueOf(money))));
			betAgentwithdraw.setBwcs(new BigDecimal(betagent.getScore()));
			betAgentwithdraw.setRemark(remark);
			
			//操作日志
			 String details = "";
		     details = "提交订单号为："+betAgentwithdraw.getId()+"的代理提现订单";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     
		     betAgentwithdrawService.save(betAgentwithdraw);
		     betAgentService.update(new Finder("update bet_agent set score=score-:score where agentid=:agentid ").setParam("agentid", betagent.getAgentid()).setParam("score", betAgentwithdraw.getMoney().doubleValue()));
		     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
		     returnObject.setStatus(ReturnDatas.SUCCESS);
		     returnObject.setMessage("操作成功");
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	@RequestMapping("/list1")
	public String list1(HttpServletRequest request, Model model,BetAgentwithdraw betAgentwithdraw) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		Page page = newPage(request);
		page.setOrder("applicationtime");
		page.setSort("desc");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		String starttime1 = request.getParameter("startTime1");
		String endtime1 = request.getParameter("endTime1");
		Date date1 =DateUtils.convertString2Date(endtime);
		Calendar calendar = new GregorianCalendar();
		if(date1!=null){
			calendar.setTime(date1); 
			calendar.add(Calendar.DATE,1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime)){
			starttime="0000-00-00";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-00-00";
		}
		Date date2 =DateUtils.convertString2Date(endtime1);
		Calendar calendar2 = new GregorianCalendar();
		if(date2!=null){
			calendar2.setTime(date2); 
			calendar2.add(Calendar.DATE,1);
			Date date3=calendar2.getTime();
			endtime1 = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime1)){
			starttime1="0000-00-00";
		}
		if(StringUtils.isBlank(endtime1)){
			endtime1="9999-00-00";
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==执行分页查询
		betAgentwithdraw.setAgentid(agentId);
		List<BetAgentwithdraw> datas=new ArrayList<>();
		if("0000-00-00".equals(starttime1)&&"9999-00-00".equals(endtime1)){
			datas=betAgentwithdrawService.queryForList(new Finder("select * from bet_agentwithdraw where (agentid=:agentid or agentparentids like :aid) and applicationtime>=:starttime and applicationtime<:endtime ").setParam("aid", "%,"+agentId+",%").setParam("agentid", agentId).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentwithdraw.class, page);
		}else{
			datas=betAgentwithdrawService.queryForList(new Finder("select * from bet_agentwithdraw where (agentid=:agentid or agentparentids like :aid) and applicationtime>=:starttime and applicationtime<:endtime and audittime>=:starttime1 and audittime<:endtime1 ").setParam("aid", "%,"+agentId+",%").setParam("agentid", agentId).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("starttime1",starttime1 ).setParam("endtime1", endtime1), BetAgentwithdraw.class, page);
		}
		if(!"0000-00-00".equals(starttime)){
			model.addAttribute("startTime", starttime);
		}
		if(!"9999-00-00".equals(endtime)){
			Date date4 =DateUtils.convertString2Date(endtime);
			calendar.setTime(date4); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime", endtime);
		}
		if(!"0000-00-00".equals(starttime1)){
			model.addAttribute("startTime1", starttime1);
		}
		if(!"9999-00-00".equals(endtime1)){
			Date date4 =DateUtils.convertString2Date(endtime1);
			calendar.setTime(date4); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime1 = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime1", endtime1);
		}

		//昨日提现，今日提现，总提现
		Double yesterdayWithdrawcash=0.0;
		Double todayWithdrawcash=0.0;
		Double allWithdrawcash=0.0;
		todayWithdrawcash = betAgentwithdrawService.queryForObject(new Finder("select sum(money) from bet_agentwithdraw where state=2 and to_days(audittime) = to_days(now()) and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
		yesterdayWithdrawcash = betAgentwithdrawService.queryForObject(new Finder("select sum(money) from bet_agentwithdraw where state=2 and TO_DAYS( NOW( ) ) - TO_DAYS(audittime) = 1 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
		allWithdrawcash = betAgentwithdrawService.queryForObject(new Finder("select sum(money) from bet_agentwithdraw where state=2 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);

		if(todayWithdrawcash==null){
			todayWithdrawcash=0.;
		}
		if(yesterdayWithdrawcash==null){
			yesterdayWithdrawcash=0.;
		}

		model.addAttribute("allWithdrawcash", allWithdrawcash);
		model.addAttribute("todayWithdrawcash", todayWithdrawcash);
		model.addAttribute("yesterdayWithdrawcash", yesterdayWithdrawcash);

		returnObject.setQueryBean(betAgentwithdraw);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betagentwithdraw/firstbetagentwithdrawList";
	}
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAgentwithdraw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetAgentwithdraw betAgentwithdraw) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		Page page = newPage(request);
		page.setOrder("applicationtime");
		page.setSort("desc");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		Date date1 =DateUtils.convertString2Date(endtime);
		Calendar calendar = new GregorianCalendar();
		if(date1!=null){
			calendar.setTime(date1); 
			calendar.add(Calendar.DATE,1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime)){
			starttime="0000-00-00";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-00-00";
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==执行分页查询
		betAgentwithdraw.setAgentid(agentId);
		List<BetAgentwithdraw> datas=betAgentwithdrawService.queryForList(new Finder("select * from bet_agentwithdraw where agentid=:agentid and applicationtime>=:starttime and applicationtime<:endtime ").setParam("agentid", agentId).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentwithdraw.class, page);



		if(!"0000-00-00".equals(starttime)){
			model.addAttribute("startTime", starttime);
		}
		if(!"9999-00-00".equals(endtime)){
			Date date2 =DateUtils.convertString2Date(endtime);
			calendar.setTime(date2); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime", endtime);
		}
		returnObject.setQueryBean(betAgentwithdraw);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 一级代理
	 * 
	 * @param request
	 * @param model
	 * @param betAgentwithdraw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list1/json")
	public @ResponseBody
	ReturnDatas listjson1(HttpServletRequest request, Model model,BetAgentwithdraw betAgentwithdraw) throws Exception{
		
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("untreated"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Integer untreated = betAgentwithdrawService.queryForObject(new Finder("select count(*) from bet_agentwithdraw where state=0 and (agentid=:agentid or agentparentids like :aid ) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%"), Integer.class);
			returnObject.setData(untreated);
			return returnObject;
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetAgentgold> datas=null;
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
		
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetAgentwithdraw betAgentwithdraw) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betAgentwithdrawService.findDataExportExcel(null,listurl, page,BetAgentwithdraw.class,betAgentwithdraw);
		String fileName="betAgentwithdraw"+GlobalStatic.excelext;
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
		return "/lottery/betagentwithdraw/betagentwithdrawLook";
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
		  BetAgentwithdraw betAgentwithdraw = betAgentwithdrawService.findBetAgentwithdrawById(id);
		   returnObject.setData(betAgentwithdraw);
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
	ReturnDatas saveorupdate(Model model,BetAgentwithdraw betAgentwithdraw,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			BigDecimal money = betAgentwithdraw.getMoney();
			if(money==null){
				returnObject.setStatus(ReturnDatas.ERROR);                                                                                                                                                                                                                                         
				returnObject.setMessage("金额不可为空！");
				return returnObject;
			}
			double money1=money.doubleValue();
			if(money1<=0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可小于或等于0！");
				return returnObject;
			}
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			if(money1>(betagent.getScore().doubleValue())){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可大于余额！");
				return returnObject;
			}
			
			String parentids = betagent.getParentids();
			String[] par_split = parentids.split(",");
			String company="";
			Double minNum = 20.00;
			if(par_split.length>3||par_split.length==3){
				company=par_split[2];
			}else{
				company=agentid;
			}
			BetAgentData betAgentData=betAgentDataService.queryForObject(new Finder("select *from bet_agent_data where company=:company and code=:code ").setParam("company", company).setParam("code", "agentdraw"), BetAgentData.class);
			if(betAgentData!=null){
				minNum=Double.valueOf(betAgentData.getValue());
			}
			if(money1<minNum){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可小于"+minNum+"！");
				return returnObject;
			}
			
			if(betagent.getIswithdraw()==1){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该代理不可提现！");
				return returnObject;
			}
			
			
			betAgentwithdraw.setId(System.currentTimeMillis()+new Random().nextInt(100)+"");
			betAgentwithdraw.setAgentid(agentid);
			betAgentwithdraw.setAgentaccount(betagent.getAccount());
			betAgentwithdraw.setAgentnickname(betagent.getNickname());
			betAgentwithdraw.setApplicationtime(new Date());
			betAgentwithdraw.setState(0);
			betAgentwithdraw.setAgentparentid(betagent.getParentid());
			betAgentwithdraw.setAgentparentids(betagent.getParentids());
			betAgentwithdraw.setAwcs(new BigDecimal((betagent.getScore()-money1)));
			betAgentwithdraw.setBwcs(new BigDecimal(betagent.getScore()));
			betAgentwithdrawService.save(betAgentwithdraw);
			betAgentService.update(new Finder("update bet_agent set score=score-:score where agentid=:agentid ").setParam("agentid", betagent.getAgentid()).setParam("score", betAgentwithdraw.getMoney().doubleValue()));
			//操作日志
			 String details = "";
		     details = "提交订单号为："+betAgentwithdraw.getId()+"的代理提现订单";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 一级代理
	 * 
	 */
	@RequestMapping("/update1")
	public @ResponseBody
	ReturnDatas saveorupdate1(Model model,BetAgentwithdraw betAgentwithdraw,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentId = SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		try {
			if(betAgentwithdraw.getId()!=null&&betAgentwithdraw.getState()!=null){

				BetAgentwithdraw betAgentwithdraw1 = betAgentwithdrawService.findBetAgentwithdrawById(betAgentwithdraw.getId());
				if(betAgentwithdraw1==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此代理提现订单！");
					return returnObject;
				}
				String agentid2 = betAgentwithdraw1.getAgentid();
				String agentparentids = betAgentwithdraw1.getAgentparentids();
				if(!(agentid2.equals(agentId)||agentparentids.indexOf(agentId)!=-1)){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("garbage,rubbish,trash,litter");
					return returnObject;
				}
				BetAgent betagent=betAgentService.queryForObject(new Finder("select*from bet_agent where agentid=:id ").setParam("id", betAgentwithdraw1.getAgentid()),BetAgent.class);
				if(betagent==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此代理！");
					return returnObject;
				}
				Integer state = betAgentwithdraw1.getState();
				Integer state1=betAgentwithdraw.getState();
				if(0==state){
					if(1==state1){
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betAgentwithdraw.setOperator(account+"("+name+")");
						betAgentwithdraw.setAwcs(null);
						int sdsd = betAgentwithdrawService.update(new Finder("update bet_agentwithdraw set operator=:operator,awcs=:ars,state=1 where id=:id ").setParam("operator", betAgentwithdraw.getOperator()).setParam("ars", betAgentwithdraw1.getBwcs()).setParam("id", betAgentwithdraw.getId()));
						if(sdsd==1){
							betAgentService.update(new Finder("update bet_agent set score=score+:score where agentid=:agentid ").setParam("agentid", betagent.getAgentid()).setParam("score", betAgentwithdraw1.getMoney().doubleValue()));
						}
						//操作日志
						 String details = "";
					     details = "取消订单号为："+betAgentwithdraw.getId()+"的代理充值订单";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agent.getAgentid(),agent.getParentid(),agent.getParentids());
					}else if(2==state1){
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						Date date=new Date();
						betAgentwithdraw.setOperator(account+"("+name+")");
						betAgentwithdraw.setAudittime(date);
						if(betagent.getScore()==null){
							betagent.setScore(0.);
						}
						betAgentwithdrawService.update(new Finder("update bet_agentwithdraw set operator=:operator,state=2,audittime=:rechargetime where id=:id ").setParam("rechargetime", betAgentwithdraw.getAudittime()).setParam("operator", betAgentwithdraw.getOperator()).setParam("ars", betAgentwithdraw.getAwcs()).setParam("brs", betAgentwithdraw.getBwcs()).setParam("id", betAgentwithdraw.getId()));
						//操作日志
						 String details = "";
					     details = "确定订单号为："+betAgentwithdraw.getId()+"的代理提现订单";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agent.getAgentid(),agent.getParentid(),agent.getParentids());
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("提现状态只能修改一次");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
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
		return "/lottery/betagentwithdraw/betagentwithdrawCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				betAgentwithdrawService.deleteById(id,BetAgentwithdraw.class);
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
			betAgentwithdrawService.deleteByIds(ids,BetAgentwithdraw.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
