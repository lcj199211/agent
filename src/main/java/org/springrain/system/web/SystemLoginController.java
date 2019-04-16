package org.springrain.system.web;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.shiro.FrameAuthenticationToken;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentannouncement;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetOptLog;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAgentannouncementService;
import org.springrain.lottery.service.IBetDaywinorfailrebateService;
import org.springrain.lottery.service.IBetFirstrechargerebateService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetRankMemberService;
import org.springrain.lottery.service.IBetRedenvelopeRecordService;
import org.springrain.lottery.service.IBetSubordinaterebateDetailService;
import org.springrain.lottery.service.IBetWeekwinorfailrebateService;
import org.springrain.lottery.service.IBetWithdrawcashService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.system.entity.Fwlog;
import org.springrain.system.entity.User;
import org.springrain.system.service.IFwlogService;
import org.springrain.system.service.IUserService;

@Controller
@RequestMapping(value="/system")
public class SystemLoginController extends BaseController   {
	
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private ICached cached;
	@Resource
	private IUserService iUserService;
	@Resource
	private IFwlogService fwlogService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetRedenvelopeRecordService betRedenvelopeRecordService;
	@Resource
	private IBetDaywinorfailrebateService betDaywinorfailrebateService;
	@Resource
	private IBetSubordinaterebateDetailService betSubordinaterebateDetailService;
	@Resource
	private IBetWeekwinorfailrebateService betWeekwinorfailrebateService;
	@Resource
	private IBetFirstrechargerebateService betFirstrechargerebateService;
	@Resource
	private IBetRankMemberService betRankMemberService; 
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentannouncementService betAgentannouncementService;
	/**
	 * 首页的映射
	 * @param model
	 * @return
	 * @throws Exception
	 */
		@RequestMapping(value = "/")
		public String index() throws Exception {
			return super.redirect+"/system/index";
		}

	
	/**
	 * 首页的映射
	 * @param model
	 * @return
	 * @throws Exception
	 */
		@RequestMapping(value = "/index")
		public String index(Model model,HttpSession session,HttpServletRequest request) throws Exception {
			String siteId = request.getParameter("systemSiteId");
			String id = SessionUser.getShiroUser().getAgentid();
			String account = SessionUser.getShiroUser().getAccount();
			try {
				BetAgent agent3 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid",id), BetAgent.class);
				User user3 = iUserService.queryForObject(new Finder("select * from t_user where account=:account ").setParam("account",account), User.class);
				if(user3.getCpuandmac()==null){
					user3.setCpuandmac("1");
				}
				if((agent3.getAgentid().equals("klc")&&user3.getCpuandmac().equals("subsidiary"))||agent3.getParentids().indexOf("klc")!=-1){
					if(StringUtils.isNotBlank(siteId))
						model.addAttribute("systemSiteId", siteId);
					Page page=new Page();
					page.setOrder("startDate");
					page.setPageSize(2);
					page.setSort("desc");
					List<Fwlog> fwlogList = fwlogService.findListDataByFinder(null, page, Fwlog.class, new Fwlog());
					Fwlog fwlog=new Fwlog();
					if(fwlog!=null){
						fwlog=fwlogList.get(1);
					}
					String ip = fwlog.getIp();
//					String address=AddressUtils.getAddresses("ip="+ip, "utf-8");
					//在线人数
					Integer concurrentUsers=0;
					Set<String> keys2 = cached.getKeys("OnTICKET_*".getBytes());
					if(keys2!=null){
						for (String strKey2 : keys2) {
							if(strKey2!=null){
								BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id=:id and (agentid=:agentid or agentparentids like :aid)").setParam("id", strKey2).setParam("agentid", id).setParam("aid", "%,"+id+",%"), BetMember.class);
								if (member!=null) {
									concurrentUsers++;
								}
							}
						}
						model.addAttribute("concurrentUsers", concurrentUsers);
					}
					
					//管理员操作日志
					Page page2=new Page();
					page2.setOrder("time");
					page2.setSort("desc");
					page2.setPageSize(20);
					List<BetOptLog> betOptLogList = betOptLogService.queryForList(new Finder("select*from bet_opt_log where (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), BetOptLog.class, page2);
					
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					returnObject.setData(betOptLogList);
					
					
					BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid",id), BetAgent.class);
				
					//代理公告
					Page page3=new Page();
					page3.setOrder("time");
					page3.setSort("desc");
					page3.setPageSize(5);
					List<BetAgentannouncement> betAgentannouncement = betAgentannouncementService.queryForList(new Finder("select * from bet_agentannouncement where (agentid=:A102 or agentid=:A101 or agentid=:id) and (agentparentids like :agentparentids or agentparentids = :A1012)").setParam("A1012",",A101,").setParam("A102","A102").setParam("A101","A101").setParam("id", agent.getParentid()).setParam("agentparentids", "%,"+agent.getParentid()+",%"), BetAgentannouncement.class, page3);
					
					ReturnDatas returnObject3 = ReturnDatas.getSuccessReturnDatas();
					returnObject3.setData(betAgentannouncement);
				
					//除一级账户以为其余代理看不到管理日志
//					if(agent.getActive()==1&&agent.getParentid().equals("A101")){
//						User user = betAgentService.queryForObject(new Finder("select * from t_user where agentid=:agentid and account=:account ").setParam("agentid",id).setParam("account", account), User.class);
//						if(user.getCpuandmac()==null&&user.getAgentid().equals(id)){
//							model.addAttribute(GlobalStatic.returnDatas, returnObject);
//						}
//					}//其余代理只能看到代理公告
//					else{
						model.addAttribute(GlobalStatic.returnDatas2, returnObject3);
//					}		
//					//用户留存
//					Double memberRemain = betMemberService.queryForObject(new Finder("select sum(score) from bet_member where (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//					model.addAttribute("memberRemain", memberRemain);
//					//福利(红包、救济、签到、返利等福利)
//					Double sumredenvelope = betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//					Double sumdaywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where state=1 and (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"),Double.class);
//					Double sumsubordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//					Double sumweekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//					Double sumfirstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//					Double sumrankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//					if(sumredenvelope==null){
//						sumredenvelope=0.;
//					}
//					if(sumdaywinorfailrebate==null){
//						sumdaywinorfailrebate=0.;
//					}
//					if(sumsubordinaterebatedetail==null){
//						sumsubordinaterebatedetail=0.;
//					}
//					if(sumweekwinorfailrebate==null){
//						sumweekwinorfailrebate=0.;
//					}
//					if(sumfirstrechargerebate==null){
//						sumfirstrechargerebate=0.;
//					}
//					if(sumrankmember==null){
//						sumrankmember=0.;
//					}
//					Double sumwelfare = sumredenvelope+sumdaywinorfailrebate+sumsubordinaterebatedetail+sumweekwinorfailrebate+sumfirstrechargerebate+sumrankmember;
//					model.addAttribute("sumwelfare", sumwelfare);
//					Double todayredenvelope= betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where (agentid=:agentid or agentparentids like :id) and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double todaydaywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"),Double.class);
//					Double todaysubordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double todayweekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where (agentid=:agentid or agentparentids like :id)  and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double todayfirstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double todayrankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(date) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					if(todayredenvelope==null){
//						todayredenvelope=0.;
//					}
//					if(todaydaywinorfailrebate==null){
//						todaydaywinorfailrebate=0.;
//					}
//					if(todaysubordinaterebatedetail==null){
//						todaysubordinaterebatedetail=0.;
//					}
//					if(todayweekwinorfailrebate==null){
//						todayweekwinorfailrebate=0.;
//					}
//					if(todayfirstrechargerebate==null){
//						todayfirstrechargerebate=0.;
//					}
//					if(todayrankmember==null){
//						todayrankmember=0.;
//					}
//					Double todaywelfare = todayredenvelope+todaydaywinorfailrebate+todaysubordinaterebatedetail+todayweekwinorfailrebate+todayfirstrechargerebate+todayrankmember;
//					model.addAttribute("todaywelfare", todaywelfare);
//					
//					
//					//充值，提现
//					Double sumRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where (agentid=:agentid or agentparentids like :id) and state=:state ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double sumWithdrawcash=betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where (agentid=:agentid or agentparentids like :id) and state=:state ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double todayRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where (agentid=:agentid or agentparentids like :id) and state=:state and to_days(submittime) = to_days(now()) ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"),  Double.class);
//					Double todayWithdrawcash=betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where (agentid=:agentid or agentparentids like :id) and state=:state and to_days(applicationtime) = to_days(now()) ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Double todayTotalWinOrLoss=betMemberService.queryForObject(new Finder("select sum(dayscore) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					Integer todayEnrollment=betMemberService.queryForObject(new Finder("select count(*) from bet_member where (agentid=:agentid or agentparentids like :id) and to_days(signdate) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Integer.class);
//					Integer totalEnrollment=betMemberService.queryForObject(new Finder("select count(*) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Integer.class);
//					Double totalWinOrLoss=betMemberService.queryForObject(new Finder("select sum(winorfail) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//					model.addAttribute("totalEnrollment", totalEnrollment);
//					model.addAttribute("totalWinOrLoss",totalWinOrLoss);
//					model.addAttribute("todayEnrollment", todayEnrollment);
//					model.addAttribute("todayTotalWinOrLoss", todayTotalWinOrLoss);
//					model.addAttribute("todayRecharge", todayRecharge);
//					model.addAttribute("todayWithdrawcash", todayWithdrawcash);
//					model.addAttribute("sumWithdrawcash", sumWithdrawcash);
//					model.addAttribute("sumRecharge", sumRecharge);
////					model.addAttribute("lastip", ip+" "+address);
//					model.addAttribute("lastip", ip);
//					model.addAttribute("lasttime",fwlog.getStartDate());
//					model.addAttribute("account", SessionUser.getShiroUser().getAccount());
//					//结果
//					if(sumRecharge==null){
//						sumRecharge=0.;
//					}
//					if(sumWithdrawcash==null){
//						sumWithdrawcash=0.;
//					}
//					if(totalWinOrLoss==null){
//						totalWinOrLoss=0.;
//					}
//					if(todayRecharge==null){
//						todayRecharge=0.;
//					}
//					if(todayWithdrawcash==null){
//						todayWithdrawcash=0.;
//					}
//					if(todayTotalWinOrLoss==null){
//						todayTotalWinOrLoss=0.;
//					}
//					
//					model.addAttribute("sumresult", sumRecharge-sumWithdrawcash-totalWinOrLoss-sumwelfare);
//					model.addAttribute("todayresult", todayRecharge-todayWithdrawcash-todayTotalWinOrLoss-todaywelfare);
					return "/system/index5";
				}else{
					if(StringUtils.isNotBlank(siteId))
						model.addAttribute("systemSiteId", siteId);
					Page page=new Page();
					page.setOrder("startDate");
					page.setPageSize(2);
					page.setSort("desc");
					List<Fwlog> fwlogList = fwlogService.findListDataByFinder(null, page, Fwlog.class, new Fwlog());
					Fwlog fwlog=new Fwlog();
					if(fwlog!=null){
						fwlog=fwlogList.get(1);
					}
					String ip = fwlog.getIp();
//					String address=AddressUtils.getAddresses("ip="+ip, "utf-8");
					//在线人数
					Integer concurrentUsers=0;
					Set<String> keys2 = cached.getKeys("OnTICKET_*".getBytes());
					if(keys2!=null){
						for (String strKey2 : keys2) {
							if(strKey2!=null){
								BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id=:id and (agentid=:agentid or agentparentids like :aid)").setParam("id", strKey2).setParam("agentid", id).setParam("aid", "%,"+id+",%"), BetMember.class);
								if (member!=null) {
									concurrentUsers++;
								}
							}
						}
						model.addAttribute("concurrentUsers", concurrentUsers);
					}
					
					//管理员操作日志
					Page page2=new Page();
					page2.setOrder("time");
					page2.setSort("desc");
					page2.setPageSize(20);
					List<BetOptLog> betOptLogList = betOptLogService.queryForList(new Finder("select*from bet_opt_log where (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), BetOptLog.class, page2);
					
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					returnObject.setData(betOptLogList);
					
					
					BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid",id), BetAgent.class);
				
					//代理公告
					Page page3=new Page();
					page3.setOrder("time");
					page3.setSort("desc");
					page3.setPageSize(5);
					List<BetAgentannouncement> betAgentannouncement = betAgentannouncementService.queryForList(new Finder("select * from bet_agentannouncement where (agentid=:A102 or agentid=:A101 or agentid=:id) and (agentparentids like :agentparentids or agentparentids = :A1012)").setParam("A1012",",A101,").setParam("A102","A102").setParam("A101","A101").setParam("id", agent.getParentid()).setParam("agentparentids", "%,"+agent.getParentid()+",%"), BetAgentannouncement.class, page3);
					
					ReturnDatas returnObject3 = ReturnDatas.getSuccessReturnDatas();
					returnObject3.setData(betAgentannouncement);
				
					//除一级账户以为其余代理看不到管理日志
					if(agent.getActive()==1&&agent.getParentid().equals("A101")){
						User user = betAgentService.queryForObject(new Finder("select * from t_user where agentid=:agentid and account=:account ").setParam("agentid",id).setParam("account", account), User.class);
						if(user.getCpuandmac()==null&&user.getAgentid().equals(id)){
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
						}
					}//其余代理只能看到代理公告
					else{
						model.addAttribute(GlobalStatic.returnDatas2, returnObject3);
					}		
					//用户留存
					Double memberRemain = betMemberService.queryForObject(new Finder("select sum(score) from bet_member where (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
					model.addAttribute("memberRemain", memberRemain);
					//福利(红包、救济、签到、返利等福利)
					Double sumredenvelope = betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
					Double sumdaywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where state=1 and (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"),Double.class);
					Double sumsubordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
					Double sumweekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
					Double sumfirstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
					Double sumrankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
					if(sumredenvelope==null){
						sumredenvelope=0.;
					}
					if(sumdaywinorfailrebate==null){
						sumdaywinorfailrebate=0.;
					}
					if(sumsubordinaterebatedetail==null){
						sumsubordinaterebatedetail=0.;
					}
					if(sumweekwinorfailrebate==null){
						sumweekwinorfailrebate=0.;
					}
					if(sumfirstrechargerebate==null){
						sumfirstrechargerebate=0.;
					}
					if(sumrankmember==null){
						sumrankmember=0.;
					}
					Double sumwelfare = sumredenvelope+sumdaywinorfailrebate+sumsubordinaterebatedetail+sumweekwinorfailrebate+sumfirstrechargerebate+sumrankmember;
					model.addAttribute("sumwelfare", sumwelfare);
					Double todayredenvelope= betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where (agentid=:agentid or agentparentids like :id) and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double todaydaywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"),Double.class);
					Double todaysubordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double todayweekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where (agentid=:agentid or agentparentids like :id)  and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double todayfirstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double todayrankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(date) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					if(todayredenvelope==null){
						todayredenvelope=0.;
					}
					if(todaydaywinorfailrebate==null){
						todaydaywinorfailrebate=0.;
					}
					if(todaysubordinaterebatedetail==null){
						todaysubordinaterebatedetail=0.;
					}
					if(todayweekwinorfailrebate==null){
						todayweekwinorfailrebate=0.;
					}
					if(todayfirstrechargerebate==null){
						todayfirstrechargerebate=0.;
					}
					if(todayrankmember==null){
						todayrankmember=0.;
					}
					Double todaywelfare = todayredenvelope+todaydaywinorfailrebate+todaysubordinaterebatedetail+todayweekwinorfailrebate+todayfirstrechargerebate+todayrankmember;
					model.addAttribute("todaywelfare", todaywelfare);
					
					
					//充值，提现
					Double sumRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where (agentid=:agentid or agentparentids like :id) and state=:state ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double sumWithdrawcash=betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where (agentid=:agentid or agentparentids like :id) and state=:state ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double todayRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where (agentid=:agentid or agentparentids like :id) and state=:state and to_days(submittime) = to_days(now()) ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"),  Double.class);
					Double todayWithdrawcash=betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where (agentid=:agentid or agentparentids like :id) and state=:state and to_days(applicationtime) = to_days(now()) ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Double todayTotalWinOrLoss=betMemberService.queryForObject(new Finder("select sum(dayscore) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					Integer todayEnrollment=betMemberService.queryForObject(new Finder("select count(*) from bet_member where (agentid=:agentid or agentparentids like :id) and to_days(signdate) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Integer.class);
					Integer totalEnrollment=betMemberService.queryForObject(new Finder("select count(*) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Integer.class);
					Double totalWinOrLoss=betMemberService.queryForObject(new Finder("select sum(winorfail) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
					model.addAttribute("totalEnrollment", totalEnrollment);
					model.addAttribute("totalWinOrLoss",totalWinOrLoss);
					model.addAttribute("todayEnrollment", todayEnrollment);
					model.addAttribute("todayTotalWinOrLoss", todayTotalWinOrLoss);
					model.addAttribute("todayRecharge", todayRecharge);
					model.addAttribute("todayWithdrawcash", todayWithdrawcash);
					model.addAttribute("sumWithdrawcash", sumWithdrawcash);
					model.addAttribute("sumRecharge", sumRecharge);
//					model.addAttribute("lastip", ip+" "+address);
					model.addAttribute("lastip", ip);
					model.addAttribute("lasttime",fwlog.getStartDate());
					model.addAttribute("account", SessionUser.getShiroUser().getAccount());
					//结果
					if(sumRecharge==null){
						sumRecharge=0.;
					}
					if(sumWithdrawcash==null){
						sumWithdrawcash=0.;
					}
					if(totalWinOrLoss==null){
						totalWinOrLoss=0.;
					}
					if(todayRecharge==null){
						todayRecharge=0.;
					}
					if(todayWithdrawcash==null){
						todayWithdrawcash=0.;
					}
					if(todayTotalWinOrLoss==null){
						todayTotalWinOrLoss=0.;
					}
					
					model.addAttribute("sumresult", sumRecharge-sumWithdrawcash-totalWinOrLoss-sumwelfare);
					model.addAttribute("todayresult", todayRecharge-todayWithdrawcash-todayTotalWinOrLoss-todaywelfare);
					model.addAttribute("agent", agent);
					return "/system/index";
				}
			}catch (Exception e) {
				// TODO: handle exception
				return e.toString();
			}

		}
		
		
		/**
		 * 渲染登录/login的界面展示,如果已经登录,跳转到首页,如果没有登录,就渲染login.html
		 * @param modelwei
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/login",method=RequestMethod.GET)
		public String login(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(AgentToolUtil.isMobileDevice(request.getHeader("user-agent"))){
				 String jsonStatusCode="relogin";
			     String jsonMessage="登录超时,请重新登录!";
				response.setCharacterEncoding(GlobalStatic.defaultCharset);
				response.setContentType("application/json;charset="+GlobalStatic.defaultCharset);
			     PrintWriter out = response.getWriter();
			     ReturnDatas returnDatas=ReturnDatas.getErrorReturnDatas();
			     returnDatas.setStatusCode(jsonStatusCode);
			     returnDatas.setMessage(jsonMessage);
			     out.println(JsonUtils.writeValueAsString(returnDatas));
			     out.flush();
			     out.close();
			}
			return getLoginUrl(model,request,response,null);
		}
		
		private String getLoginUrl(Model model,HttpServletRequest request, HttpServletResponse response,String siteId){
			//判断用户是否登录
			if(SecurityUtils.getSubject().isAuthenticated()){
//				String agentid= SessionUser.getShiroUser().getAgentid();
//				String account = SessionUser.getShiroUser().getAccount();
//				try {
//					BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid",agentid), BetAgent.class);
//					User user3 = iUserService.queryForObject(new Finder("select * from t_user where account=:account ").setParam("account",account), User.class);
//					if(user3.getCpuandmac()==null){
//						user3.setCpuandmac("1");
//					}
//					if((agent.getAgentid().equals("fhwc")&&user3.getCpuandmac().equals("subsidiary"))||agent.getParentids().indexOf("fhwc")!=-1){
//						return redirect+"/system/index2";
//					}else{
						return redirect+"/system/index";
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			//默认赋值message,避免freemarker尝试从session取值,造成异常
			model.addAttribute("message", "");
			  String url=request.getParameter("gotourl");
			  if(StringUtils.isNotBlank(url)){
			     model.addAttribute("gotourl", url);
			  }
			return "/system/login";
		}
		
		/**
		 * 处理登录提交的方法
		 * @param currUser 自动封装当前登录人的 账号,密码,验证码
		 * @param session
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		 */
		
		@RequestMapping(value = "/login",method=RequestMethod.POST)
		public String loginPost(User currUser,HttpSession session,Model model,HttpServletRequest request) throws Exception {
			Subject user = SecurityUtils.getSubject();
			//系统产生的验证码
//			  String code = (String) session.getAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			  
			  session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			  
			  
			  String systemSiteId=request.getParameter("systemSiteId");
			  if(StringUtils.isNotBlank(systemSiteId)){
					model.addAttribute("systemSiteId", systemSiteId);
				}
			  
//			  if(StringUtils.isNotBlank(code)){
//				  code=code.toLowerCase().toString();
//			  }
			  //用户产生的验证码
//			 String submitCode = WebUtils.getCleanParam(request, GlobalStatic.DEFAULT_CAPTCHA_PARAM);
//			  if(StringUtils.isNotBlank(submitCode)){
//				  submitCode=submitCode.toLowerCase().toString();
//			  }
			  String gotourl=request.getParameter("gotourl");
			  //如果验证码不匹配,跳转到登录
//			if (StringUtils.isBlank(submitCode) ||StringUtils.isBlank(code)||!code.equals(submitCode)) {
//				model.addAttribute("message", "验证码错误!");
//				return "/system/login";
//	        }
			//通过账号和密码  agentid获取 UsernamePasswordToken token
			  User user2 = iUserService.queryForObject(new Finder("select * from t_user where account=:account").setParam("account", currUser.getAccount()), User.class);
//			 Integer isagent = iUserService.queryForObject(new Finder("select isagent from t_user where account=:account").setParam("account", currUser.getAccount()), Integer.class);
			  Integer active = iUserService.queryForObject(new Finder("select active from bet_agent where account=:account ").setParam("account", currUser.getAccount()), Integer.class);
			  if(active!=null){
			  if(active==0){
				  model.addAttribute("message", "代理用戶不存在");
				  return "/system/login";
			  }
			  }
			  BetAgent betAgent = iUserService.queryForObject(new Finder("select * from bet_agent where account=:account").setParam("account", currUser.getAccount()), BetAgent.class);
			 if(betAgent!=null){
			  if(!"A101".equals(betAgent.getParentid())){
				  String[] split = betAgent.getParentids().split(",");
				  if(split.length>=3){
					  Integer live = iUserService.queryForObject(new Finder("select active from bet_agent where agentid=:agentid ").setParam("agentid", split[2]), Integer.class);
					  if(live==0){
						  model.addAttribute("message", "上級代理用戶不存在");
						  return "/system/login";
					  }
				  }
			  }
			 }
			 if(user2!=null&&user2.getIsagent()==1&&user2.getAgentid()!="A101"){
				 
				 FrameAuthenticationToken token = new FrameAuthenticationToken(currUser.getAccount(),currUser.getPassword());
			 
			
			String rememberme=request.getParameter("rememberme");
			if(StringUtils.isNotBlank(rememberme)){
				token.setRememberMe(true);
			}else{
				token.setRememberMe(false);
			}
			
			try {
				//会调用 shiroDbRealm 的认证方法 org.springrain.frame.shiro.ShiroDbRealm.doGetAuthenticationInfo(AuthenticationToken)
				user.login(token);
			} catch (UnknownAccountException uae) {
				model.addAttribute("message", "账号不存在!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("message", "密码错误!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			} catch (LockedAccountException lae) {
				model.addAttribute("message", "账号被锁定!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "未知错误,请联系管理员.");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			}
			
			
			/*//验证cpu序列号和mac地址
			String cpuandmac = user2.getCpuandmac();
			String cpuandmac2 = currUser.getCpuandmac();
			if(StringUtils.isNoneBlank(cpuandmac2)){
				if(cpuandmac==null){
					iUserService.update(new Finder("update t_user set cpuandmac=:cpuandmac where account=:account ").setParam("cpuandmac", cpuandmac2).setParam("account", currUser.getAccount()));
				}else{
					if(!cpuandmac.equals(cpuandmac2)){
						model.addAttribute("message", "机器码不一致，请与管理员联系.");
						 if(StringUtils.isNotBlank(gotourl)){
						     model.addAttribute("gotourl", gotourl);
						  }
						return "/system/login";
					}
				}
			}else{
				model.addAttribute("message", "无法获取机器码，请联系管理员.");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			}*/
			
			if(StringUtils.isBlank(gotourl)){
//				gotourl="/system/index";
//				String agentid= SessionUser.getShiroUser().getAgentid();
//				String account = SessionUser.getShiroUser().getAccount();
//				try {
//					BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid",agentid), BetAgent.class);
//					User user3 = iUserService.queryForObject(new Finder("select * from t_user where account=:account ").setParam("account",account), User.class);
//					if(user3.getCpuandmac()==null){
//						user3.setCpuandmac("1");
//					}
//					if((agent.getAgentid().equals("fhwc")&&user3.getCpuandmac().equals("subsidiary"))||agent.getParentids().indexOf("fhwc")!=-1){
//						gotourl="/system/index2";
//					}else{
						gotourl="/system/index";
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}	
			//设置tokenkey
			String springraintoken="system_"+SecUtils.getUUID();
			session.setAttribute(GlobalStatic.tokenKey, springraintoken);
			model.addAttribute(GlobalStatic.tokenKey,springraintoken);
			//更新管理员登录ip和登录时间
			/*
			String ip=IPUtils.getClientAddress(request);
			String account=currUser.getAccount();
			Date date=new Date();
			iUserService.updateUserLogin(ip,date, account);
			betAgentService.update(new Finder("update bet_agent set login=login+1 where account=:account").setParam("account", account));
			//更新访问日志
			HttpServletRequest req = request;
			
			String uri = req.getRequestURI();
			String requestURL = req.getRequestURL().toString();
			String contextPath = req.getContextPath();
			if(uri.endsWith("/pre")){// 去掉pre
				uri=uri.substring(0,uri.length()-4);
			}
			if(uri.endsWith("/json")){// 去掉json
				uri=uri.substring(0,uri.length()-5);
			}
			if(uri.endsWith("/more")){// 去掉more
				uri=uri.substring(0,uri.length()-5);
			}
			int i=uri.indexOf(contextPath);
			if(i>-1){
				uri=uri.substring(i+contextPath.length());
			}
			if(StringUtils.isBlank(uri)){
				uri="/";
			}
			
			 boolean permitted = false;
			 if("/".equals(uri)){
				 permitted=true;
			 }else{
			 permitted= SecurityUtils.getSubject().isPermitted(uri);
			 }
			 String isqx="否";
			 if(permitted){
				 isqx="是";
			 }
			 String ip2 = IPUtils.getClientAddress(req);
			
			 Fwlog fwlog=new Fwlog();
			 fwlog.setFwUrl(requestURL);
			 fwlog.setIsqx(isqx);
			 
//			 fwlog.setIp(ip);
		
			fwlog.setUserCode(SessionUser.getUserCode());
			fwlog.setUserName(SessionUser.getUserName());
			Date startDate=date;
			fwlog.setStartDate(startDate);
			fwlog.setStrDate(DateUtils.convertDate2String("yyyy-MM-dd HH:mm:ss", startDate));
			HttpSession httpSession = req.getSession(false);
			if(httpSession!=null){
				fwlog.setSessionId(httpSession.getId());
			}
			try {
//				String menuName = menuService.getNameByPageurl(uri);
				//req.setAttribute(GlobalStatic.pageurlName, menuName);
//				fwlog.setMenuName(menuName);
				String userId = SessionUser.getUserId();
				fwlogService.saveFwlog1(userId,ip2, fwlog);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			*/
			return redirect+gotourl;
			 }else{
				 model.addAttribute("message", "账号不存在!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			 }
		}
		
		
		/**
		 * 手机处理登录提交的方法
		 * @param currUser 自动封装当前登录人的 账号,密码,验证码
		 * @param session
		 * @param model
		 * @param request
		 * @return
		 * @throws Exception
		 */
		
		@RequestMapping(value = "/loginmobile",method=RequestMethod.POST)
		@ResponseBody
		public ReturnDatas loginmobile(User currUser,HttpSession session,Model model,HttpServletRequest request) throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage("登录成功");
			Subject user = SecurityUtils.getSubject();
			  session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			  String systemSiteId=request.getParameter("systemSiteId");
			  if(StringUtils.isNotBlank(systemSiteId)){
					model.addAttribute("systemSiteId", systemSiteId);
				}
			  String gotourl=request.getParameter("gotourl");
			  User user2 = iUserService.queryForObject(new Finder("select * from t_user where account=:account").setParam("account", currUser.getAccount()), User.class);
			  
			 if(user2!=null&&user2.getIsagent()==1&&user2.getAgentid()!="A101"){
				 String parentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where account=:account ").setParam("account", user2.getAccount()), String.class);
				 if(StringUtils.isNoneBlank(parentids)){
				 }else{
					 returnObject.setStatus(ReturnDatas.ERROR);
					 returnObject.setMessage("无此代理账号！");
					 return returnObject;
				 }
				 FrameAuthenticationToken token = new FrameAuthenticationToken(currUser.getAccount(),currUser.getPassword());
			String rememberme=request.getParameter("rememberme");
			if(StringUtils.isNotBlank(rememberme)){
				token.setRememberMe(true);
			}else{
				token.setRememberMe(false);
			}
			try {
				//会调用 shiroDbRealm 的认证方法 org.springrain.frame.shiro.ShiroDbRealm.doGetAuthenticationInfo(AuthenticationToken)
				user.login(token);
			} catch (UnknownAccountException uae) {
				model.addAttribute("message", "账号不存在!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				 returnObject.setStatus(ReturnDatas.ERROR);
				 returnObject.setMessage("账号不存在!");
				 return returnObject;
			} catch (IncorrectCredentialsException ice) {
				model.addAttribute("message", "密码错误!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				 returnObject.setStatus(ReturnDatas.ERROR);
				 returnObject.setMessage("密码错误!");
				 return returnObject;
			} catch (LockedAccountException lae) {
				model.addAttribute("message", "账号被锁定!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				 returnObject.setStatus(ReturnDatas.ERROR);
				 returnObject.setMessage("账号被锁定!");
				 return returnObject;
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "未知错误,请联系管理员.");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				 returnObject.setStatus(ReturnDatas.ERROR);
				 returnObject.setMessage("未知错误,请联系管理员.");
				 return returnObject;
			}
			
			
			/*//验证cpu序列号和mac地址
			String cpuandmac = user2.getCpuandmac();
			String cpuandmac2 = currUser.getCpuandmac();
			if(StringUtils.isNoneBlank(cpuandmac2)){
				if(cpuandmac==null){
					iUserService.update(new Finder("update t_user set cpuandmac=:cpuandmac where account=:account ").setParam("cpuandmac", cpuandmac2).setParam("account", currUser.getAccount()));
				}else{
					if(!cpuandmac.equals(cpuandmac2)){
						model.addAttribute("message", "机器码不一致，请与管理员联系.");
						 if(StringUtils.isNotBlank(gotourl)){
						     model.addAttribute("gotourl", gotourl);
						  }
						return "/system/login";
					}
				}
			}else{
				model.addAttribute("message", "无法获取机器码，请联系管理员.");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				return "/system/login";
			}*/
			
			if(StringUtils.isBlank(gotourl)){
				gotourl="/system/index";
			}
			//设置tokenkey
			String springraintoken="system_"+SecUtils.getUUID();
			session.setAttribute(GlobalStatic.tokenKey, springraintoken);
//			model.addAttribute(GlobalStatic.tokenKey,springraintoken);
		
			//更新管理员登录ip和登录时间
			String ip=IPUtils.getClientAddress(request);
			String account=currUser.getAccount();
			Date date=new Date();
			iUserService.updateUserLogin(ip,date, account);
			betAgentService.update(new Finder("update bet_agent set login=login+1 where account=:account").setParam("account", account));
			//更新访问日志
			HttpServletRequest req = request;
			
			String uri = req.getRequestURI();
			String requestURL = req.getRequestURL().toString();
			String contextPath = req.getContextPath();
			if(uri.endsWith("/pre")){// 去掉pre
				uri=uri.substring(0,uri.length()-4);
			}
			if(uri.endsWith("/json")){// 去掉json
				uri=uri.substring(0,uri.length()-5);
			}
			if(uri.endsWith("/more")){// 去掉more
				uri=uri.substring(0,uri.length()-5);
			}
			int i=uri.indexOf(contextPath);
			if(i>-1){
				uri=uri.substring(i+contextPath.length());
			}
			if(StringUtils.isBlank(uri)){
				uri="/";
			}
			
			 boolean permitted = false;
			 if("/".equals(uri)){
				 permitted=true;
			 }else{
			 permitted= SecurityUtils.getSubject().isPermitted(uri);
			 }
			 String isqx="否";
			 if(permitted){
				 isqx="是";
			 }
			 String ip2 = IPUtils.getClientAddress(req);
			
			 Fwlog fwlog=new Fwlog();
			 fwlog.setFwUrl(requestURL);
			 fwlog.setIsqx(isqx);
			 
//			 fwlog.setIp(ip);
		
			fwlog.setUserCode(SessionUser.getUserCode());
			fwlog.setUserName(SessionUser.getUserName());
			Date startDate=date;
			fwlog.setStartDate(startDate);
			fwlog.setStrDate(DateUtils.convertDate2String("yyyy-MM-dd HH:mm:ss", startDate));
			HttpSession httpSession = req.getSession(false);
			if(httpSession!=null){
				fwlog.setSessionId(httpSession.getId());
			}
			try {
//				String menuName = menuService.getNameByPageurl(uri);
				//req.setAttribute(GlobalStatic.pageurlName, menuName);
//				fwlog.setMenuName(menuName);
				String userId = SessionUser.getUserId();
				fwlogService.saveFwlog1(userId,ip2, fwlog);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			Map<String, Object> mpppp=new HashMap<String, Object>();
			mpppp.put("springraintoken", springraintoken);
			String id = session.getId();
			mpppp.put("SHAREJSESSIONID", id);
			returnObject.setData(mpppp);
			
			
			
			 return returnObject;
			 }else{
				 model.addAttribute("message", "账号不存在!");
				 if(StringUtils.isNotBlank(gotourl)){
				     model.addAttribute("gotourl", gotourl);
				  }
				 returnObject.setStatus(ReturnDatas.ERROR);
				 returnObject.setMessage("账号不存在!");
				 return returnObject;
			 }
		}


		/**
		 * 退出
		 * @param request
		 */
		@RequestMapping(value="/logout")
	    public String logout(HttpServletRequest request){
	        Subject subject = SecurityUtils.getSubject();
	        if (subject != null) {           
	            subject.logout();
	        }
	        return super.redirect+"/system/login";
	    }
		
		/**
		 * 手机退出
		 * @param request
		 */
		@RequestMapping(value="/logoutmobile")
		@ResponseBody
	    public ReturnDatas logoutmobile(HttpServletRequest request){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage("登出成功");
			try {
				Subject subject = SecurityUtils.getSubject();
		        if (subject != null) {           
		            subject.logout();
		        }
		        return returnObject;
			} catch (Exception e) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("登出失败");
				return returnObject;
			}
	        
	    }
		
		
//		/**
//		 * 麒麟首页的映射
//		 * @param model
//		 * @return
//		 * @throws Exception
//		 */
//			@RequestMapping(value = "/index2")
//			public String index2(Model model,HttpSession session,HttpServletRequest request) throws Exception {
//				String siteId = request.getParameter("systemSiteId");
//				
//				if(StringUtils.isNotBlank(siteId))
//					model.addAttribute("systemSiteId", siteId);
//				String id = SessionUser.getShiroUser().getAgentid();
//				String account = SessionUser.getShiroUser().getAccount();
//				Page page=new Page();
//				page.setOrder("startDate");
//				page.setPageSize(2);
//				page.setSort("desc");
//				List<Fwlog> fwlogList = fwlogService.findListDataByFinder(null, page, Fwlog.class, new Fwlog());
//				Fwlog fwlog=new Fwlog();
//				if(fwlog!=null){
//					fwlog=fwlogList.get(1);
//				}
//				String ip = fwlog.getIp();
////				String address=AddressUtils.getAddresses("ip="+ip, "utf-8");
//				//在线人数
//				Integer concurrentUsers=0;
//				Set<String> keys2 = cached.getKeys("OnTICKET_*".getBytes());
//				if(keys2!=null){
//					for (String strKey2 : keys2) {
//						if(strKey2!=null){
//							BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id=:id and (agentid=:agentid or agentparentids like :aid)").setParam("id", strKey2).setParam("agentid", id).setParam("aid", "%,"+id+",%"), BetMember.class);
//							if (member!=null) {
//								concurrentUsers++;
//							}
//						}
//					}
//					model.addAttribute("concurrentUsers", concurrentUsers);
//				}
//				
//				//管理员操作日志
//				Page page2=new Page();
//				page2.setOrder("time");
//				page2.setSort("desc");
//				page2.setPageSize(20);
//				List<BetOptLog> betOptLogList = betOptLogService.queryForList(new Finder("select*from bet_opt_log where (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), BetOptLog.class, page2);
//				
//				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//				returnObject.setData(betOptLogList);
//				
//				
//				BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid",id), BetAgent.class);
//			
//				//代理公告
//				Page page3=new Page();
//				page3.setOrder("time");
//				page3.setSort("desc");
//				page3.setPageSize(5);
//				List<BetAgentannouncement> betAgentannouncement = betAgentannouncementService.queryForList(new Finder("select * from bet_agentannouncement where (agentid=:A102 or agentid=:A101 or agentid=:id) and (agentparentids like :agentparentids or agentparentids = :A1012)").setParam("A1012",",A101,").setParam("A102","A102").setParam("A101","A101").setParam("id", agent.getParentid()).setParam("agentparentids", "%,"+agent.getParentid()+",%"), BetAgentannouncement.class, page3);
//				
//				ReturnDatas returnObject3 = ReturnDatas.getSuccessReturnDatas();
//				returnObject3.setData(betAgentannouncement);
//			
//				//除一级账户以为其余代理看不到管理日志
////				if(agent.getActive()==1&&agent.getParentid().equals("A101")){
////					User user = betAgentService.queryForObject(new Finder("select * from t_user where agentid=:agentid and account=:account ").setParam("agentid",id).setParam("account", account), User.class);
////					if(user.getCpuandmac()==null&&user.getAgentid().equals(id)){
////						model.addAttribute(GlobalStatic.returnDatas, returnObject);
////					}
////				}//其余代理只能看到代理公告
////				else{
//					model.addAttribute(GlobalStatic.returnDatas2, returnObject3);
////				}		
//				//用户留存
//				Double memberRemain = betMemberService.queryForObject(new Finder("select sum(score) from bet_member where (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//				model.addAttribute("memberRemain", memberRemain);
//				//福利(红包、救济、签到、返利等福利)
//				Double sumredenvelope = betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//				Double sumdaywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where state=1 and (agentid=:id or agentparentids like :agentid)").setParam("id", id).setParam("agentid", "%,"+id+",%"),Double.class);
//				Double sumsubordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//				Double sumweekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//				Double sumfirstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//				Double sumrankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where state=1 and (agentid=:id or agentparentids like :agentid) ").setParam("id", id).setParam("agentid", "%,"+id+",%"), Double.class);
//				if(sumredenvelope==null){
//					sumredenvelope=0.;
//				}
//				if(sumdaywinorfailrebate==null){
//					sumdaywinorfailrebate=0.;
//				}
//				if(sumsubordinaterebatedetail==null){
//					sumsubordinaterebatedetail=0.;
//				}
//				if(sumweekwinorfailrebate==null){
//					sumweekwinorfailrebate=0.;
//				}
//				if(sumfirstrechargerebate==null){
//					sumfirstrechargerebate=0.;
//				}
//				if(sumrankmember==null){
//					sumrankmember=0.;
//				}
//				Double sumwelfare = sumredenvelope+sumdaywinorfailrebate+sumsubordinaterebatedetail+sumweekwinorfailrebate+sumfirstrechargerebate+sumrankmember;
//				model.addAttribute("sumwelfare", sumwelfare);
//				Double todayredenvelope= betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where (agentid=:agentid or agentparentids like :id) and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double todaydaywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"),Double.class);
//				Double todaysubordinaterebatedetail = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double todayweekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where (agentid=:agentid or agentparentids like :id)  and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double todayfirstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(receivetime) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double todayrankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where (agentid=:agentid or agentparentids like :id) and state=1 and to_days(date) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				if(todayredenvelope==null){
//					todayredenvelope=0.;
//				}
//				if(todaydaywinorfailrebate==null){
//					todaydaywinorfailrebate=0.;
//				}
//				if(todaysubordinaterebatedetail==null){
//					todaysubordinaterebatedetail=0.;
//				}
//				if(todayweekwinorfailrebate==null){
//					todayweekwinorfailrebate=0.;
//				}
//				if(todayfirstrechargerebate==null){
//					todayfirstrechargerebate=0.;
//				}
//				if(todayrankmember==null){
//					todayrankmember=0.;
//				}
//				Double todaywelfare = todayredenvelope+todaydaywinorfailrebate+todaysubordinaterebatedetail+todayweekwinorfailrebate+todayfirstrechargerebate+todayrankmember;
//				model.addAttribute("todaywelfare", todaywelfare);
//				
//				
//				//充值，提现
//				Double sumRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where (agentid=:agentid or agentparentids like :id) and state=:state ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double sumWithdrawcash=betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where (agentid=:agentid or agentparentids like :id) and state=:state ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double todayRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where (agentid=:agentid or agentparentids like :id) and state=:state and to_days(submittime) = to_days(now()) ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"),  Double.class);
//				Double todayWithdrawcash=betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where (agentid=:agentid or agentparentids like :id) and state=:state and to_days(applicationtime) = to_days(now()) ").setParam("state", 2).setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Double todayTotalWinOrLoss=betMemberService.queryForObject(new Finder("select sum(dayscore) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				Integer todayEnrollment=betMemberService.queryForObject(new Finder("select count(*) from bet_member where (agentid=:agentid or agentparentids like :id) and to_days(signdate) = to_days(now()) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Integer.class);
//				Integer totalEnrollment=betMemberService.queryForObject(new Finder("select count(*) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Integer.class);
//				Double totalWinOrLoss=betMemberService.queryForObject(new Finder("select sum(winorfail) from bet_member where (agentid=:agentid or agentparentids like :id) ").setParam("agentid", id).setParam("id", "%,"+id+",%"), Double.class);
//				model.addAttribute("totalEnrollment", totalEnrollment);
//				model.addAttribute("totalWinOrLoss",totalWinOrLoss);
//				model.addAttribute("todayEnrollment", todayEnrollment);
//				model.addAttribute("todayTotalWinOrLoss", todayTotalWinOrLoss);
//				model.addAttribute("todayRecharge", todayRecharge);
//				model.addAttribute("todayWithdrawcash", todayWithdrawcash);
//				model.addAttribute("sumWithdrawcash", sumWithdrawcash);
//				model.addAttribute("sumRecharge", sumRecharge);
////				model.addAttribute("lastip", ip+" "+address);
//				model.addAttribute("lastip", ip);
//				model.addAttribute("lasttime",fwlog.getStartDate());
//				model.addAttribute("account", SessionUser.getShiroUser().getAccount());
//				//结果
//				if(sumRecharge==null){
//					sumRecharge=0.;
//				}
//				if(sumWithdrawcash==null){
//					sumWithdrawcash=0.;
//				}
//				if(totalWinOrLoss==null){
//					totalWinOrLoss=0.;
//				}
//				if(todayRecharge==null){
//					todayRecharge=0.;
//				}
//				if(todayWithdrawcash==null){
//					todayWithdrawcash=0.;
//				}
//				if(todayTotalWinOrLoss==null){
//					todayTotalWinOrLoss=0.;
//				}
//				
//				model.addAttribute("sumresult", sumRecharge-sumWithdrawcash-totalWinOrLoss-sumwelfare);
//				model.addAttribute("todayresult", todayRecharge-todayWithdrawcash-todayTotalWinOrLoss-todaywelfare);
//				return "/system/index5";
//			}
}
