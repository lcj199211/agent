package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetCentralbankService;
import org.springrain.lottery.service.IBetDaywinorfailrebateService;
import org.springrain.lottery.service.IBetFirstrechargerebateService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetKeyvalueService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetRankMemberService;
import org.springrain.lottery.service.IBetRedenvelopeRecordService;
import org.springrain.lottery.service.IBetRegisterRewardService;
import org.springrain.lottery.service.IBetReliefRecordService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBetSigninRewardService;
import org.springrain.lottery.service.IBetSinglerechargeService;
import org.springrain.lottery.service.IBetSubordinaterebateDetailService;
import org.springrain.lottery.service.IBetTodayrechargerebateService;
import org.springrain.lottery.service.IBetWeekwinorfailrebateService;
import org.springrain.lottery.service.IBetWithdrawcashService;
import org.springrain.lottery.utils.AgentToolUtil;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 09:58:21
 * @see org.springrain.lottery.web.BetWithdrawcash
 */
@Controller
@RequestMapping(value="/betwithdrawcash")
public class BetWithdrawcashController  extends BaseController {
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private ICached cached;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetCentralbankService iBetCentralbankService;
	@Resource
	private IBetDaywinorfailrebateService betDaywinorfailrebateService;
	@Resource
	private IBetFirstrechargerebateService betFirstrechargerebateService;
	@Resource
	private IBetSigninRewardService betSigninRewardService;
	@Resource
	private IBetSubordinaterebateDetailService betSubordinaterebateDetailService ;
	@Resource
	private IBetWeekwinorfailrebateService betWeekwinorfailrebateService;
	@Resource
	private IBetKeyvalueService betKeyvalueService;
	@Resource
	private IBetRegisterRewardService betRegisterRewardService;
	@Resource
	private IBetTodayrechargerebateService betTodayrechargerebateService;
	@Resource
	private IBetRankMemberService betRankMemberService;
	@Resource
	private IBetRedenvelopeRecordService betRedenvelopeRecordService;
	@Resource
	private IBetSinglerechargeService betSinglerechargeService;
	@Resource
	private IBetReliefRecordService betReliefRecordService;
	@Resource
	private IBetAgentService betAgentService;
	
	private String listurl="/lottery/betwithdrawcash/betwithdrawcashList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betWithdrawcash
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetWithdrawcash betWithdrawcash) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		Double rechargecolorlimit = betKeyvalueService.queryForObject(new Finder("select value from bet_keyvalue where id=1 "),Double.class);
		model.addAttribute("rechargecolorlimit", rechargecolorlimit);
		String memberid = betWithdrawcash.getMemberid();
		BetWithdrawcash betWithdrawcash4=new BetWithdrawcash();
		if(memberid!=null){
			betWithdrawcash4.setMemberid(memberid);
		}
//		Integer i=0;
		Double yesterdayWithdrawcash=0.0;
		Double todayWithdrawcash=0.0;
		Integer member=0;
		if(memberid==null){
			Double memberRemain = betMemberService.queryForObject(new Finder("select sum(score) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
			model.addAttribute("memberRemain", memberRemain);
		}		
		ReturnDatas returnObject = listjson(request, model, betWithdrawcash);		
		String date = request.getParameter("date");
		model.addAttribute("agentId", agentId);
		if("1".equals(date)){
	//		List<BetWithdrawcash> betWithdrawcashlist=(List<BetWithdrawcash>)returnObject.getData();
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			if(betWithdrawcash.getMemberid()!=null){
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betwithdrawcashList1";
			}
			return listurl;
		}else{
			if(memberid!=null){
				
			}else{
				todayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and to_days(audittime) = to_days(now()) and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
				yesterdayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and TO_DAYS( NOW( ) ) - TO_DAYS(audittime) <= 1 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
				if(todayWithdrawcash==null){
					todayWithdrawcash=0.;
				}
				if(yesterdayWithdrawcash==null){
					yesterdayWithdrawcash=0.;
				}
				model.addAttribute("todayWithdrawcash", todayWithdrawcash);
				model.addAttribute("yesterdayWithdrawcash", yesterdayWithdrawcash);
			}
			
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("member", member);
			if(betWithdrawcash.getMemberid()!=null){
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betwithdrawcashList1";
			}
			return listurl;
		}
		
		
		
		
	}
	
	//获取一级代理名称
	private String getCompany(BetAgent agag, String company) {
		if(agag!=null){
			if("A101".equals(agag.getParentid())){
				company = agag.getAgentid();
			}else{
				String[] spilt = agag.getParentids().split(",");//,A101,agent1,agent2
				company = spilt[2];
			}
		}
		return company;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betWithdrawcash
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetWithdrawcash betWithdrawcash) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent agag = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		String company = "";//一级代理
		company = getCompany(agag, company);
		
		if("1".equals(request.getParameter("untreated"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Integer untreatedWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select count(*) from bet_withdrawcash where state=0 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Integer.class);
			returnObject.setData(untreatedWithdrawcash);
			return returnObject;
		}
		String memberid = betWithdrawcash.getMemberid();
		BetWithdrawcash betWithdrawcash4=new BetWithdrawcash();
		if(memberid!=null){
			betWithdrawcash4.setMemberid(memberid);
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("applicationtime");
		page.setSort("desc");
		String pageSize = request.getParameter("pageSize");
		if(StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}else{
			page.setPageSize(50);
		}
		// ==执行分页查询
		String date = request.getParameter("date");
		Double totolWithdrawcash=0.;
		Double withdrawcashNumber=0.;
		
		String state = request.getParameter("state");
		if(StringUtils.isBlank(state)){
			state="0";
		}else if(state.equals("100")){
			state=null;
		}
		if("1".equals(date)){
			String paymentmethod = request.getParameter("paymentmethod");
			if(paymentmethod==null){
				paymentmethod="0";
			}
			//按时间查询
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
			
			List<BetWithdrawcash> datas=null;
			if(paymentmethod.equals("0")){
				//String paymentmethods=null;
				if(state!=null&&"2".equals(state)){
					datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where (:state is null or a.state=:state) and a.audittime>=:starttime and a.audittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("state", state).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				}else{
					datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where (:state is null or a.state=:state) and a.applicationtime>=:starttime and a.applicationtime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("state", state).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				}
			}else if(paymentmethod.equals("1")){
				String paymentmethods="支付宝";
				if(state!=null&&"2".equals(state)){
					datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where (:state is null or a.state=:state) and a.paymentmethod=:paymentmethod and a.audittime>=:starttime and a.audittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("state", state).setParam("paymentmethod", paymentmethods).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				}else{
					datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where (:state is null or a.state=:state) and a.paymentmethod=:paymentmethod and a.applicationtime>=:starttime and a.applicationtime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("state", state).setParam("paymentmethod", paymentmethods).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				}
			}else if(paymentmethod.equals("3")){
				String paymentmethods="支付宝";
				if(state!=null&&"2".equals(state)){
					datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where (:state is null or a.state=:state) and a.paymentmethod!=:paymentmethod and a.audittime>=:starttime and a.audittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("state", state).setParam("paymentmethod", paymentmethods).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				}else{
					datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where (:state is null or a.state=:state) and a.paymentmethod!=:paymentmethod and a.applicationtime>=:starttime and a.applicationtime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("state", state).setParam("paymentmethod", paymentmethods).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				}
			}
			//List<BetWithdrawcash> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where a.applicationtime>=:starttime and a.applicationtime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
			if(memberid!=null){
				List<BetWithdrawcash> memberwithdrawcashlist = betWithdrawcashService.queryForList(new Finder("select *from bet_withdrawcash where applicationtime>=:starttime and applicationtime<:endtime and memberid=:memberid and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), BetWithdrawcash.class);
				if(memberwithdrawcashlist!=null){
					for (BetWithdrawcash betWithdrawcash2 : memberwithdrawcashlist) {
						if(betWithdrawcash2.getState()==2){
							totolWithdrawcash+=betWithdrawcash2.getMoney();
						}
						withdrawcashNumber++;
					}
				}
			}else{
				if(paymentmethod.equals("0")){
					//String paymentmethod1=null;
					totolWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where audittime>=:starttime and audittime<:endtime and state=2 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
				}else if(paymentmethod.equals("1")){
					String paymentmethod1="支付宝";
					totolWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where paymentmethod=:paymentmethod and audittime>=:starttime and audittime<:endtime and state=2 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("paymentmethod", paymentmethod1).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
				}else if(paymentmethod.equals("3")){
					String paymentmethod1="支付宝";
					totolWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where paymentmethod!=:paymentmethod and audittime>=:starttime and audittime<:endtime and state=2 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("paymentmethod", paymentmethod1).setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
				}	
			}
			model.addAttribute("totolWithdrawcash", totolWithdrawcash);
			model.addAttribute("withdrawcashNumber", withdrawcashNumber);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
			if("0000-00-00".equals(starttime)&&"9999-00-00".equals(endtime)){
				if(memberid==null){
					
					Double todayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and to_days(audittime) = to_days(now()) and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
					Double yesterdayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and TO_DAYS( NOW( ) ) - TO_DAYS(audittime) <= 1 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), Double.class);
					model.addAttribute("todayWithdrawcash", todayWithdrawcash);
					model.addAttribute("yesterdayWithdrawcash", yesterdayWithdrawcash);
				}
			}
			if(memberid!=null){
				String realname = betMemberService.queryForObject(new Finder("select realname from bet_member where id=:id ").setParam("id", memberid), String.class);
				if(!company.equals("zhouyunfei")){
					if(!"A101".equals(agag.getParentid())){//不是一级代理或者子账号，手机号码和身份证号码都要隐藏部分
						//隐藏用户真实姓名
						if(realname!=null){
							if (realname.length() <= 1) {        
								realname="*";
							} else {     
								realname=realname.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1***");
							}
						}
					}
				}
				betWithdrawcash4.setRealname(realname);
			}
			betWithdrawcash4.setPaymentmethod(betWithdrawcash.getPaymentmethod());
			if(state==null){
				betWithdrawcash4.setState(100);
			}else{
				betWithdrawcash4.setState(Integer.parseInt(state));
			}			
			returnObject.setQueryBean(betWithdrawcash4);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else{
			List<BetWithdrawcash> datas=null;
			if(memberid==null){
				datas = betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where to_days(a.applicationtime) = to_days(now()) and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), page, BetWithdrawcash.class, betWithdrawcash4);
				if(state.equals("0")){
					datas = betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where state=0 and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%"), page, BetWithdrawcash.class, betWithdrawcash4);
				}
				Date date2 = new Date();
				String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date2);
				model.addAttribute("endTime", strdate);
				model.addAttribute("startTime", strdate);
			}
			if(memberid!=null){
				datas = betWithdrawcashService.queryForList(new Finder("select *from bet_withdrawcash where  memberid=:memberid and to_days(applicationtime) = to_days(now()) and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("memberid", memberid), BetWithdrawcash.class,page);
				Date date2 = new Date();
				String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date2);
				model.addAttribute("endTime", strdate);
				model.addAttribute("startTime", strdate);
				if(datas!=null){
					for (BetWithdrawcash betWithdrawcash2 : datas) {
						if(betWithdrawcash2.getState()==2){
							totolWithdrawcash+=betWithdrawcash2.getMoney();
						}
						withdrawcashNumber++;
					}
				}
			}
			model.addAttribute("totolWithdrawcash", totolWithdrawcash);
			model.addAttribute("withdrawcashNumber", withdrawcashNumber);
			if(memberid!=null){
				String realname = betMemberService.queryForObject(new Finder("select realname from bet_member where id=:id ").setParam("id", memberid), String.class);
				betWithdrawcash4.setRealname(realname);
			}
			returnObject.setQueryBean(betWithdrawcash4);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;			
		}		
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetWithdrawcash betWithdrawcash) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betWithdrawcashService.findDataExportExcel(null,listurl, page,BetWithdrawcash.class,betWithdrawcash);
		String fileName="betWithdrawcash"+GlobalStatic.excelext;
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
		return "/lottery/betwithdrawcash/betwithdrawcashLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if("1".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			try{
				BetMember betmember = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2), BetMember.class);
				Double membertotalrecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where memberid=:memberid and state=2 ").setParam("memberid", betmember.getId()), Double.class);
				Double membertotalwithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where memberid=:memberid and state=2 ").setParam("memberid", betmember.getId()), Double.class);
				//福利统计
				Double daywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where memberid2=:memberid2 and state=1 ").setParam("memberid2", memberid2), Double.class);
				Double registerreward = betRegisterRewardService.queryForObject(new Finder("select sum(reward) from bet_register_reward where memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				Double signinreward = betSigninRewardService.queryForObject(new Finder("select sum(reward) from bet_signin_reward where memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				Double firstrechargerabate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where state=1 and memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				Double subordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where memberid2=:memberid2 and state=1 ").setParam("memberid2", memberid2), Double.class);
				Double weekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where memberid2=:memberid2 and state=1 ").setParam("memberid2", memberid2), Double.class);
				Double todayrechargerebate = betTodayrechargerebateService.queryForObject(new Finder("select sum(reward) from bet_todayrechargerebate where memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				Double rankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where state=1 and memberid=:memberid ").setParam("memberid", betmember.getId()), Double.class);
				Double redenvelope = betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				Double singlerecharge = betSinglerechargeService.queryForObject(new Finder("select sum(rebate) from bet_singlerecharge where memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				Double reliefscore = betReliefRecordService.queryForObject(new Finder("select sum(reliefscore) from bet_relief_record where memberid2=:memberid2 ").setParam("memberid2", memberid2), Double.class);
				
				
				
				if(daywinorfailrebate==null){
					daywinorfailrebate=0.;
				}
				if(registerreward==null){
					registerreward=0.;
				}
				if(signinreward==null){
					signinreward=0.;
				}
				if(firstrechargerabate==null){
					firstrechargerabate=0.;
				}
				if(subordinaterebatedetail==null){ 
					subordinaterebatedetail=0.;
				}
				if(weekwinorfailrebate==null){
					weekwinorfailrebate=0.;
				}
				if(todayrechargerebate==null){
					todayrechargerebate=0.;
				}
				if(rankmember==null){
					rankmember=0.;
				}
				if(redenvelope==null){
					redenvelope=0.;
				}
				if(singlerecharge==null){
					singlerecharge=0.;
				}
				if(reliefscore==null){
					reliefscore=0.;
				}
				
				
				if(membertotalrecharge==null){
					membertotalrecharge=0.;
				}
				if(membertotalwithdrawcash==null){
					membertotalwithdrawcash=0.;
				}
				Map<String,Object> map=new HashMap<>();
				map.put("welfare",daywinorfailrebate+registerreward+signinreward+firstrechargerabate+subordinaterebatedetail+weekwinorfailrebate+todayrechargerebate+rankmember+redenvelope+singlerecharge+reliefscore);
				map.put("membertotalrecharge", membertotalrecharge);
				map.put("membertotalwithdrawcash", membertotalwithdrawcash);
				map.put("memberwinorfail", betmember.getWinorfail());
				map.put("mobile", betmember.getMobile());
				map.put("qq", betmember.getQq());
				returnObject.setData(map);
				returnObject.setMessage("success");
				return returnObject;
			}catch(Exception e){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("err");
				return returnObject;
			}
			
			
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			java.lang.String id=request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
			  BetWithdrawcash betWithdrawcash = betWithdrawcashService.findBetWithdrawcashById(id);
			   returnObject.setData(betWithdrawcash);
			}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			}
			return returnObject;
		}
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BetWithdrawcash betWithdrawcash,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			BetWithdrawcash betWithdrawcash1 = betWithdrawcashService.findBetWithdrawcashById(betWithdrawcash.getId());
			if(!(agentId.equals(betWithdrawcash1.getAgentid())||(betWithdrawcash1.getAgentparentids().contains(agentId)))){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此用户");
				return returnObject;
			}
			BetMember betMember = betMemberService.findBetMemberById(betWithdrawcash1.getMemberid());
			if(betMember==null||betMember.getIsinternal()==1){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此用户或为内部用户");
				return returnObject;
			}
//			if(betMember!=null){
//				betWithdrawcash.setRealname(betMember.getRealname());
//			}
			java.lang.String id =betWithdrawcash.getId();
			if(StringUtils.isBlank(id)){
			  betWithdrawcash.setId(null);
			}
			if(betWithdrawcash.getId()!=null&&betWithdrawcash.getState()!=null&&betWithdrawcash.getMemberid()==null){
				
				Integer state = betWithdrawcash1.getState();
				Integer state1=betWithdrawcash.getState();
				if(0==state){
					if(1==state1){
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						Date date =new Date();
						String failreason = new String(betWithdrawcash.getFailreason().getBytes("ISO-8859-1"),"utf-8");
						betWithdrawcash.setFailreason(failreason);						
						betWithdrawcash.setBwcs(betMember.getScore());
						betWithdrawcash.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
						betWithdrawcash.setAwcs(betMember.getScore());
						betWithdrawcash.setOperator(account+"("+name+")");
						betWithdrawcash.setAudittime(date);
						betMember.setBankscore(betMember.getBankscore()+betWithdrawcash1.getMoney());
						betMember.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
						betMemberService.update(betMember);
						betWithdrawcashService.update(betWithdrawcash, true);
						//操作日志
						 String details = "";
					     details = "取消ID为"+betMember.getId2()+"的用户提现"+betWithdrawcash1.getMoney()+"元的申请";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
					   //金币记录
					     BetScorerecord betScorerecord=new BetScorerecord();
					     String content="";
					     content="提现失败";
					     betScorerecord.setMemberid2(betMember.getId2());
					     betScorerecord.setTime(date);
					     betScorerecord.setContent(content);
					     betScorerecord.setOriginalscore(betWithdrawcash.getBwcs());
					     betScorerecord.setChangescore(betWithdrawcash1.getMoney());
					     betScorerecord.setGamescore(BigDecimal.valueOf(betMember.getGamescore()));
					     betScorerecord.setBankscore(BigDecimal.valueOf(betMember.getBankscore()));
					     betScorerecord.setFreezescore(BigDecimal.valueOf(betMember.getFreezingscore()));
					     betScorerecord.setAgentid(betMember.getAgentid());
					     betScorerecord.setAgentparentid(betMember.getAgentparentid());
					     betScorerecord.setAgentparentids(betMember.getAgentparentids());
					     betScorerecord.setOrderid(betWithdrawcash.getId());
					     betScorerecord.setBalance(betWithdrawcash.getAwcs());
					     betScorerecord.setState(1);
					     betScorerecord.setType(2);
					     betScorerecordService.saveBetScorerecord(betScorerecord);
					   //清除缓存
							String ticket = betMember.getTicket();
							if(ticket!=null){
								try{
//									cached.deleteCached(("TICKET_"+ticket).getBytes());
									ObjectMapper mapper=new ObjectMapper();
									byte[] json = mapper.writeValueAsBytes(betMember);
									
									cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
								}catch(Exception e){
									String errorMessage = e.getLocalizedMessage();
									logger.error(errorMessage,e);
								}
								
							}
					}else if(2==state1){
						if(betWithdrawcash1.getMoney()<=0.){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("提现金额不能小于或等于零");
							return returnObject;
						}
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						Date date=new Date();
						betWithdrawcash.setBwcs(betMember.getScore());
						betWithdrawcash.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
						betWithdrawcash.setAwcs(betMember.getScore()-betWithdrawcash1.getMoney());
						betWithdrawcash.setOperator(account+"("+name+")");
						betWithdrawcash.setAudittime(date);
						betMember.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
						betMember.setScore(betMember.getScore()-betWithdrawcash1.getMoney());
						betMemberService.update(betMember);
						betWithdrawcashService.update(betWithdrawcash, true);
						//中央银行
					     iBetCentralbankService.update(new Finder("update bet_centralbank set withdrawcash = withdrawcash+:withdrawcash ").setParam("withdrawcash", betWithdrawcash1.getMoney()));
						//操作日志
						 String details = "";
						 String memberid = betWithdrawcash.getMemberid();
						 BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id=:id ").setParam("id", memberid), BetMember.class);
					     details = "确定ID为"+betMember.getId2()+"的用户提现"+betWithdrawcash1.getMoney()+"元的申请";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
					   //金币记录
					     BetScorerecord betScorerecord=new BetScorerecord();
					     String content="";
					     content = betWithdrawcash1.getMoney() + "提现成功";
					     betScorerecord.setMemberid2(betMember.getId2());
					     betScorerecord.setTime(date);
					     betScorerecord.setContent(content);
					     betScorerecord.setOriginalscore(betWithdrawcash.getBwcs());
					     betScorerecord.setChangescore(0.0);
					     betScorerecord.setGamescore(BigDecimal.valueOf(betMember.getGamescore()));
					     betScorerecord.setBankscore(BigDecimal.valueOf(betMember.getBankscore()));
					     betScorerecord.setFreezescore(BigDecimal.valueOf(betMember.getFreezingscore()));
					     betScorerecord.setAgentid(betMember.getAgentid());
					     betScorerecord.setAgentparentid(betMember.getAgentparentid());
					     betScorerecord.setAgentparentids(betMember.getAgentparentids());
					     betScorerecord.setOrderid(betWithdrawcash.getId());
					     betScorerecord.setBalance(betWithdrawcash.getAwcs());
					     betScorerecord.setState(1);
					     betScorerecord.setType(2);
					     betScorerecordService.saveBetScorerecord(betScorerecord);
						//更新缓存
						String ticket = betMember.getTicket();
						if(ticket!=null){
							try{
//								cached.deleteCached(("TICKET_"+ticket).getBytes());
								ObjectMapper mapper=new ObjectMapper();
								byte[] json = mapper.writeValueAsBytes(betMember);
								
								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
							}catch(Exception e){
								String errorMessage = e.getLocalizedMessage();
								logger.error(errorMessage,e);
							}
							
						}
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("提现状态只能修改一次");
					return returnObject;
				}
				betWithdrawcashService.update(betWithdrawcash, true);
			}else{
//				betWithdrawcashService.saveorupdate(betWithdrawcash);
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
		if("1".equals(request.getParameter("state"))){
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			
			
			
			
			
			return "/lottery/betwithdrawcash/betwithdrawcashConfirm";
		}else{
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betwithdrawcash/betwithdrawcashCru";
		}
	}
	
	/**
	 * 删除操作
	 */
	/*@RequestMapping(value="/delete")*/
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
//		try {
//		java.lang.String id=request.getParameter("id");
//		if(StringUtils.isNotBlank(id)){
//				betWithdrawcashService.deleteById(id,BetWithdrawcash.class);
//				return new ReturnDatas(ReturnDatas.SUCCESS,
//						MessageUtils.DELETE_SUCCESS);
//			} else {
//				return new ReturnDatas(ReturnDatas.WARNING,
//						MessageUtils.DELETE_WARNING);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
/*	@RequestMapping("/delete/more")*/
	public @ResponseBody
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
//		String records = request.getParameter("records");
//		if(StringUtils.isBlank(records)){
//			 return new ReturnDatas(ReturnDatas.ERROR,
//					MessageUtils.DELETE_ALL_FAIL);
//		}
//		String[] rs = records.split(",");
//		if (rs == null || rs.length < 1) {
//			return new ReturnDatas(ReturnDatas.ERROR,
//					MessageUtils.DELETE_NULL_FAIL);
//		}
//		try {
//			List<String> ids = Arrays.asList(rs);
//			betWithdrawcashService.deleteByIds(ids,BetWithdrawcash.class);
//		} catch (Exception e) {
//			return new ReturnDatas(ReturnDatas.ERROR,
//					MessageUtils.DELETE_ALL_FAIL);
//		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
