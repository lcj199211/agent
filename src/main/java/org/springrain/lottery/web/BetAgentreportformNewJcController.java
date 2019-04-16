package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.entity.BasketballScheme;
import org.springrain.lottery.entity.BasketballSchemeMatch;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentreportformJc;
import org.springrain.lottery.entity.BetAgentreportformNewJc;
import org.springrain.lottery.entity.BetAgentwithdraw;
import org.springrain.lottery.entity.BetBetting;
import org.springrain.lottery.entity.BetDaywinorfailrebate;
import org.springrain.lottery.entity.BetFirstrechargerebate;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetRankMember;
import org.springrain.lottery.entity.BetRechargecard;
import org.springrain.lottery.entity.BetRedenvelopeRecord;
import org.springrain.lottery.entity.BetRegisterReward;
import org.springrain.lottery.entity.BetReliefRecord;
import org.springrain.lottery.entity.BetReportform;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetSinglerecharge;
import org.springrain.lottery.entity.BetSubordinaterebateDetail;
import org.springrain.lottery.entity.BetTodayrechargerebate;
import org.springrain.lottery.entity.BetTransferAccounts;
import org.springrain.lottery.entity.BetWeekwinorfailrebate;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.lottery.entity.BjdcScheme;
import org.springrain.lottery.entity.BjdcSchemeMatch;
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.LotteryScheme;
import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.service.IBasketballSchemeMatchService;
import org.springrain.lottery.service.IBasketballSchemeService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAgentreportformNewJcService;
import org.springrain.lottery.service.IBetAgentwithdrawService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetDaywinorfailrebateService;
import org.springrain.lottery.service.IBetFirstrechargerebateService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetPaymentInterfaceService;
import org.springrain.lottery.service.IBetRankMemberService;
import org.springrain.lottery.service.IBetRechargecardService;
import org.springrain.lottery.service.IBetRedenvelopeRecordService;
import org.springrain.lottery.service.IBetRegisterRewardService;
import org.springrain.lottery.service.IBetReliefRecordService;
import org.springrain.lottery.service.IBetReportformService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBetSigninRewardService;
import org.springrain.lottery.service.IBetSinglerechargeService;
import org.springrain.lottery.service.IBetSubordinaterebateDetailService;
import org.springrain.lottery.service.IBetTodayrechargerebateService;
import org.springrain.lottery.service.IBetTransferAccountsService;
import org.springrain.lottery.service.IBetWeekwinorfailrebateService;
import org.springrain.lottery.service.IBetWithdrawcashService;
import org.springrain.lottery.service.IBjdcOddsService;
import org.springrain.lottery.service.IBjdcSchemeMatchService;
import org.springrain.lottery.service.IBjdcSchemeService;
import org.springrain.lottery.service.ILotteryOrderService;
import org.springrain.lottery.service.ISlaveBetMemberService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerSchemeMatchService;
import org.springrain.lottery.service.ISoccerSchemeService;
import org.springrain.lottery.utils.QuartzJob101;
import org.springrain.lottery.utils.WeekOfDate;
import org.springrain.lottery.utils.basketballWeekOfDate;
import org.springrain.pay.entity.BetAgentProxies;
import org.springrain.pay.service.IBetAgentProxiesService;
import org.springrain.system.service.IDicDataService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-24 10:41:36
 * @see org.springrain.lottery.web.BetAgentreportformJc
 */
@Controller
@RequestMapping(value="/betagentreportformnewjc")
public class BetAgentreportformNewJcController  extends BaseController {
	@Resource
	private IBetAgentreportformNewJcService betAgentreportformNewJcService;
	@Resource
	private IBetReportformService betReportformService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetRedenvelopeRecordService betRedenvelopeRecordService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetDaywinorfailrebateService betDaywinorfailrebateService;
	@Resource
	private IBetRechargecardService betRechargecardService;
	@Resource
	private IBetFirstrechargerebateService betFirstrechargerebateService;
	@Resource
	private IBjdcOddsService bjdcOddsService;
	@Resource
	private IBjdcSchemeMatchService bjdcSchemeMatchService;
	@Resource 
	private IBetSubordinaterebateDetailService betSubordinaterebateDetailService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetRegisterRewardService betRegisterRewardService;
	@Resource
	private IBetSinglerechargeService betSinglerechargeService;
	@Resource
	private IBetTodayrechargerebateService betTodayrechargerebateService;
	@Resource
	private IBetWeekwinorfailrebateService betWeekwinorfailrebateService;
	@Resource
	private IBetTransferAccountsService betTransferAccountsService;
	@Resource
	private IBetPaymentInterfaceService betPaymentInterfaceService;
	@Resource
	private IBetSigninRewardService betSigninRewardService;
	@Resource
	private IBetReliefRecordService betReliefRecordService;
	@Resource
	private IBetRankMemberService betRankMemberService;
	@Resource
	private IBjdcSchemeService bjdcSchemeService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	@Resource
	private ISoccerSchemeService soccerSchemeService;
	@Resource
	private ISoccerSchemeMatchService soccerSchemeMatchService;
	@Resource
	private IBetAgentwithdrawService betAgentwithdrawService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private ICached cached;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBasketballSchemeMatchService basketballSchemeMatchService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	@Resource
	private ILotteryOrderService lotteryOrderService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private ISlaveBetMemberService  slaveBetMemberService;
	@Resource
	private IBetAgentProxiesService betAgentProxiesService;

	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
	
	@Resource
	private IBasketballSchemeService basketballSchemeService;
	private String listurl="/lottery/betreportformnewJc/betreportformListqqv2";
	private String listurlsec="/lottery/betreportformnewJc/betreportformListqqsecv2";
	
	
	
	@RequestMapping("/secagreport/v2")
	public String secagreport(HttpServletRequest request, Model model,BetReportform betReportform) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		if("1".equals(request.getParameter("k"))){
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==执行分页查询
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
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
				List<Map<String, Object>> agentdatas=betAgentService.queryForList(new Finder("select a.agentid,a.parentids,a.nickname from bet_agent a where (a.parentid=:id) and a.active=1").setParam("id", agentid));
				
				if(agentdatas!=null){
					for (Map<String, Object> map : agentdatas) {
						if(map.get("agentid")!=null){
							String ageasdf = (String)map.get("agentid");
							Map<String, Object> ddd = betAgentreportformNewJcService.queryForObject(new Finder("select sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(bettingtimecommission1) as bettingtimecommission1,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 1").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", ageasdf));
							
							if(ddd!=null){
								Double bettingmoney=0.;
								Double untreatedbettingmoney=0.;
								Double treatedbettingmoney=0.;
								Double bettingscore=0.;
								Double bettingwin=0.;
								Double bettingtimecommission=0.;
								Double bettingtimecommission1=0.;
								Double transferaccountsscore=0.;
								Object object = ddd.get("bettingmoney");
								Object object2 = ddd.get("untreatedbettingmoney");
								Object object5 = ddd.get("treatedbettingmoney");
								Object object6 = ddd.get("bettingscore");
								Object object7 = ddd.get("bettingwin");
								Object object8 = ddd.get("bettingtimecommission");
								Object object9 = ddd.get("bettingtimecommission1");
								Object object10 = ddd.get("transferaccountsscore");
								if(object!=null){
									bettingmoney=((BigDecimal)object).doubleValue();
								}
								if(object2!=null){
									untreatedbettingmoney=((BigDecimal)object2).doubleValue();
								}
								if(object5!=null){
									treatedbettingmoney=((BigDecimal)object5).doubleValue();
								}
								if(object6!=null){
									bettingscore=((BigDecimal)object6).doubleValue();
								}
								if(object7!=null){
									bettingwin=((BigDecimal)object7).doubleValue();
								}
								if(object8!=null){
									bettingtimecommission=((BigDecimal)object8).doubleValue();
								}
								if(object9!=null){
									bettingtimecommission1=((BigDecimal)object9).doubleValue();
								}
								if(object10!=null){
									transferaccountsscore=((BigDecimal)object10).doubleValue();
								}
								map.put("bettingmoney", bettingmoney);
								map.put("untreatedbettingmoney", untreatedbettingmoney);
								map.put("treatedbettingmoney", treatedbettingmoney);
								map.put("bettingscore", bettingscore);
								map.put("bettingwin", bettingwin);
								map.put("bettingtimecommission", bettingtimecommission);
								map.put("bettingtimecommission1", bettingtimecommission1);
								map.put("transferaccountsscore", transferaccountsscore);
							}
							String parentids = (String)map.get("parentids");
							if(parentids.startsWith(",")){
								parentids=parentids.substring(1);
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}else{
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}
						}
					}
				}
				
				Map<String, Object> sadfasd = betAgentreportformNewJcService.queryForObject(new Finder("select agentid,agentparentids,agentnickname,sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 0").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid));
			
				
				sadfasd.put("level", "直属");
				sadfasd.put("fgdfgdfgdf", 1);
				if(agentdatas!=null){
					agentdatas.add(sadfasd);
				}else{
					agentdatas=new ArrayList<Map<String, Object>>();
					agentdatas.add(sadfasd);
				}
				model.addAttribute("agentdatas", agentdatas);
				BetAgent betagentt = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
				model.addAttribute("agentaccount", betagentt.getAccount());
				model.addAttribute("agentnickname", betagentt.getNickname());
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
				return "/lottery/betreportformnewJc/betreportformagreportsecv2";
			}else{
				BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", requestagentid), BetAgent.class);
				if(betaaa!=null){
					if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
						
					}else{
						return "/errorpage/error";
					}
				}else{
					return "/errorpage/error";
				}

				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==执行分页查询
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
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
				List<Map<String, Object>> agentdatas=betAgentService.queryForList(new Finder("select a.agentid,a.parentids,a.nickname from bet_agent a where (a.parentid=:id) and a.active=1 ").setParam("id", requestagentid));
				
				if(agentdatas!=null){
					for (Map<String, Object> map : agentdatas) {
						if(map.get("agentid")!=null){
							String ageasdf = (String)map.get("agentid");
							
							Map<String, Object> ddd = betAgentreportformNewJcService.queryForObject(new Finder("select sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(bettingtimecommission1) as bettingtimecommission1,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 1").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", ageasdf));
							
							
							if(ddd!=null){
								Double bettingmoney=0.;
								Double untreatedbettingmoney=0.;
								Double treatedbettingmoney=0.;
								Double bettingscore=0.;
								Double bettingwin=0.;
								Double bettingtimecommission=0.;
								Double bettingtimecommission1=0.;
								Double transferaccountsscore=0.;
								Object object = ddd.get("bettingmoney");
								Object object2 = ddd.get("untreatedbettingmoney");
								Object object5 = ddd.get("treatedbettingmoney");
								Object object6 = ddd.get("bettingscore");
								Object object7 = ddd.get("bettingwin");
								Object object8 = ddd.get("bettingtimecommission");
								Object object9 = ddd.get("bettingtimecommission1");
								Object object10 = ddd.get("transferaccountsscore");
								if(object!=null){
									bettingmoney=((BigDecimal)object).doubleValue();
								}
								if(object2!=null){
									untreatedbettingmoney=((BigDecimal)object2).doubleValue();
								}
								if(object5!=null){
									treatedbettingmoney=((BigDecimal)object5).doubleValue();
								}
								if(object6!=null){
									bettingscore=((BigDecimal)object6).doubleValue();
								}
								if(object7!=null){
									bettingwin=((BigDecimal)object7).doubleValue();
								}
								if(object8!=null){
									bettingtimecommission=((BigDecimal)object8).doubleValue();
								}
								if(object9!=null){
									bettingtimecommission1=((BigDecimal)object9).doubleValue();
								}
								if(object10!=null){
									transferaccountsscore=((BigDecimal)object10).doubleValue();
								}
								map.put("bettingmoney", bettingmoney);
								map.put("untreatedbettingmoney", untreatedbettingmoney);
								map.put("treatedbettingmoney", treatedbettingmoney);
								map.put("bettingscore", bettingscore);
								map.put("bettingwin", bettingwin);
								map.put("bettingtimecommission", bettingtimecommission);
								map.put("bettingtimecommission1", bettingtimecommission1);
								map.put("transferaccountsscore", transferaccountsscore);
							}
							String parentids = (String)map.get("parentids");
							if(parentids.startsWith(",")){
								parentids=parentids.substring(1);
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}else{
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}
						}
					}
				}
				
				Map<String, Object> sadfasd = betAgentreportformNewJcService.queryForObject(new Finder("select agentid,agentparentids,agentnickname,sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 0").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", requestagentid));
				
				
				sadfasd.put("level", "直属");
				sadfasd.put("fgdfgdfgdf", 1);
				if(agentdatas!=null){
					agentdatas.add(sadfasd);
				}else{
					agentdatas=new ArrayList<Map<String, Object>>();
					agentdatas.add(sadfasd);
				}
				model.addAttribute("agentdatas", agentdatas);
				model.addAttribute("agentaccount", betaaa.getAccount());
				model.addAttribute("agentnickname", betaaa.getNickname());
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
				model.addAttribute("agentid", requestagentid);
				return "/lottery/betreportformnewJc/betreportformagreportsecv2";
			}
		}else if("2".equals(request.getParameter("k"))){
			//投注额
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page=newPage(request);
			String memberid2 = request.getParameter("id2");
			if(memberid2!=null&&StringUtils.isNoneBlank(memberid2)){
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				String gcname = request.getParameter("gcname");
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
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");

					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.memberid2=:memberid2 and c.isinternal=0 and (c.agentid=:agentid or c.agentparentids like :aid) and a.situation!=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime),SoccerScheme.class,page);
						soccer(datas);
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
						returnObject.setQueryBean(betReportform);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingList111sec";
					}else{
						List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.bettingtime>=:starttime and a.bettingtime<:endtime and a.gcname=:gcname and b.id2=:memberid2 and a.state!=2 ) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("starttime",starttime ).setParam("endtime", endtime),page,BetBetting.class,new BetBetting());
						
						returnObject.setQueryBean(new BetBetting());
						returnObject.setPage(page);
						returnObject.setData(datas);
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
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingList111sec";
					}
				
				}else{
					
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.id2=:id2 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.bettingtime>=:starttime and a.bettingtime<:endtime and a.state!=2 group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("id2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime), page);
					
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
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
					model.addAttribute("memberid2", memberid2);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbetting111sec";
				
				}
			}else{
				return "errorpage/error";
			}
		}else if("4".equals(request.getParameter("k"))){
			//未结算
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page=newPage(request);
			String memberid2 = request.getParameter("id2");
			if(memberid2!=null&&StringUtils.isNoneBlank(memberid2)){
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				String gcname = request.getParameter("gcname");
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
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");

					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.situation=0 and a.memberid2=:memberid2 and c.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime),SoccerScheme.class,page);
						
						soccer(datas);
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
						returnObject.setQueryBean(betReportform);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingList333sec";
					}else{
						List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.bettingtime>=:starttime and a.bettingtime<:endtime and a.state=0 and a.gcname=:gcname and b.id2=:memberid2 ) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("starttime",starttime ).setParam("endtime", endtime),page,BetBetting.class,new BetBetting());
						
						returnObject.setQueryBean(new BetBetting());
						returnObject.setPage(page);
						returnObject.setData(datas);
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
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingList333sec";
					}
				}else{
					
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.id2=:id2 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.bettingtime>=:starttime and a.bettingtime<:endtime and a.state=0 group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("id2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime), page);
					
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
//					model.addAttribute("bettingtime", date);
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
					model.addAttribute("memberid2", memberid2);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbetting333sec";
				}
			}else{
				return "errorpage/error";
			}
		}else if("6".equals(request.getParameter("k"))){
			//会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				return "/errorpage/error";
				}else{
					BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", requestagentid), BetAgent.class);
					if(betaaa!=null){
						if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
							
						}else{
							return "/errorpage/error";
						}
					}else{
						return "/errorpage/error";
					}

					// ==构造分页请求
					Page page = newPage(request,"b.bettingmoney","desc");
					// ==执行分页查询
					String starttime = request.getParameter("starttime");
					String endtime = request.getParameter("endtime");
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
					List<Map<String, Object>> datas = betMemberService.queryForList(new Finder("select a.id2,a.nickname,b.bettingmoney,c.untreatedbettingmoney,f.treatedbettingmoney,f.bettingscore,f.bettingwin,g.bettingtimecommission from bet_member a " +
							"left join (select memberid2,sum(bettingmoney) as bettingmoney from soccer_allbetting where state!=2 and bettingtime>=:starttime and bettingtime<:endtime group by memberid2) b on a.id2=b.memberid2 " +
							"left join (select memberid2,sum(bettingmoney) as untreatedbettingmoney from soccer_allbetting where bettingtime>=:starttime and bettingtime<:endtime and state=0 group by memberid2)c on c.memberid2=a.id2 " +
							"left join (select memberid2,sum(bettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingscore-bettingmoney) as bettingwin from soccer_allbetting where bettingtime>=:starttime and bettingtime<:endtime and state=1 group by memberid2)f on f.memberid2=a.id2 " +
							"left join (select a.memberid2,sum(a.commission) as bettingtimecommission from bet_commission a left join soccer_allbetting b on a.orderid=b.id where b.bettingtime>=:starttime and b.bettingtime<:endtime and (a.agentid=:id) group by a.memberid2)g on g.memberid2=a.id2 "+
							" where (a.agentid=:id) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("id", requestagentid),page);
					returnObject.setQueryBean(betReportform);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("agentaccount", betaaa.getAccount());
					model.addAttribute("agentnickname", betaaa.getNickname());
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
					model.addAttribute("agentid", requestagentid);
					return "/lottery/betreportformnewJc/betreportformagmemberreportsec";
				}
		
		}else if("7".equals(request.getParameter("k"))){
			//未结算
			String directlyunder = request.getParameter("directlyunder");
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				return "/errorpage/error";
			}else{
				BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", requestagentid), BetAgent.class);
				if(betaaa!=null){
					if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
						
					}else{
						return "/errorpage/error";
					}
				}else{
					return "/errorpage/error";
				}
				
				agentid = requestagentid;
				ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("bettingtime");
				page.setSort("desc");
				String type = request.getParameter("type");
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
				
				
				if(type==null){
					type = "100";
				}
				if("0".equals(type)){
					List<BetBetting> betbettinglist=null;
					if("1".equals(directlyunder)){
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),BetBetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BetBetting.class,page);
					}
					
					returnObject1.setData(betbettinglist);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/betbettingList5sec";
				}else if("1".equals(type)){
					List<SoccerScheme> datas=null;
					if("1".equals(directlyunder)){
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid ) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerScheme.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerScheme.class,page);
					}
					
					soccer(datas);
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/soccerbettingList5sec";
				}else{
					List<SoccerAllbetting> datas =null;
					if("1".equals(directlyunder)){
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid) and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerAllbetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerAllbetting.class,page);
					}
					 
					soccerAll(datas);
					bjdc(datas);
					//篮球处理
					basketballAll(datas);
					
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid",agentid);
					return "/lottery/betmember/allsoccerbettingList5sec";
				}
			}
		}else if("8".equals(request.getParameter("k"))){
			//投注额
			String directlyunder = request.getParameter("directlyunder");
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				return "/errorpage/error";
			}else{
				BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid and active=1").setParam("agentid", requestagentid), BetAgent.class);
				if(betaaa!=null){
					if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
						
					}else{
						return "/errorpage/error";
					}
				}else{
					return "/errorpage/error";
				}
				
				agentid = requestagentid;
				ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("bettingtime");
				page.setSort("desc");
				String type = request.getParameter("type");
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
				
				
				if(type==null){
					type = "100";
				}
				if("0".equals(type)){
					List<BetBetting> betbettinglist=null;
					if("1".equals(directlyunder)){
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid) and a.state!=2 ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),BetBetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) and a.state!=2 ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BetBetting.class,page);
					}
					
					returnObject1.setData(betbettinglist);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/betbettingList6sec";
				}else if("1".equals(type)){
					List<SoccerScheme> datas=null;
					if("1".equals(directlyunder)){
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid ) and a.situation!=2 ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerScheme.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) and a.situation!=2").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerScheme.class,page);
					}
					
					soccer(datas);
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/soccerbettingList6sec";
				}else{
					List<SoccerAllbetting> datas =null;
					if("1".equals(directlyunder)){
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid) and a.state!=2 and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerAllbetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) and a.state!=2 and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerAllbetting.class,page);
					}
					 
					soccerAll(datas);
					bjdc(datas);
					//篮球处理
					basketballAll(datas);
					
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid",agentid);
					return "/lottery/betmember/allsoccerbettingList6sec";
				}
			}
		}else if("9".equals(request.getParameter("k"))){
			//已结算
			String directlyunder = request.getParameter("directlyunder");
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				return "/errorpage/error";
			}else{
				BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", requestagentid), BetAgent.class);
				if(betaaa!=null){
					if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
						
					}else{
						return "/errorpage/error";
					}
				}else{
					return "/errorpage/error";
				}
				
				agentid = requestagentid;
				ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("bettingtime");
				page.setSort("desc");
				String type = request.getParameter("type");
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
				
				
				if(type==null){
					type = "100";
				}
				if("0".equals(type)){
					List<BetBetting> betbettinglist=null;
					if("1".equals(directlyunder)){
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.state=1 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),BetBetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.state=1 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BetBetting.class,page);
					}
					
					returnObject1.setData(betbettinglist);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/betbettingList7sec";
				}else if("1".equals(type)){
					List<SoccerScheme> datas=null;
					if("1".equals(directlyunder)){
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.situation=1 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid ) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerScheme.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.situation=1 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerScheme.class,page);
					}
					
					soccer(datas);
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/soccerbettingList7sec";
				}else{
					List<SoccerAllbetting> datas =null;
					if("1".equals(directlyunder)){
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where a.state=1 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid) and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerAllbetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where a.state=1 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerAllbetting.class,page);
					}
					 
					soccerAll(datas);
					bjdc(datas);
					//篮球处理
					basketballAll(datas);
					
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid",agentid);
					return "/lottery/betmember/allsoccerbettingList7sec";
				}
			}
		}else if("12".equals(request.getParameter("k"))){
			//投注退佣
			String directlyunder = request.getParameter("directlyunder");
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				return "/errorpage/error";
			}else{
				BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid and actice=1 ").setParam("agentid", requestagentid), BetAgent.class);
				if(betaaa!=null){
					if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
						
					}else{
						return "/errorpage/error";
					}
				}else{
					return "/errorpage/error";
				}
				
				agentid = requestagentid;
				ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("bettingtime");
				page.setSort("desc");
				String type = request.getParameter("type");
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
				if(type==null){
					type = "100";
				}
				if("0".equals(type)){
					List<BetBetting> betbettinglist=null;
					if("1".equals(directlyunder)){
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2,bet_commission.commission from bet_commission left join bet_betting a on bet_commission.orderid=a.id left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and (bet_commission.agentid=:agentid) and a.agentid=:agentid ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),BetBetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2,bet_commission.commission from bet_commission left join bet_betting a on bet_commission.orderid=a.id left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and (bet_commission.agentid=:agentid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%"+agentid+"%").setParam("agentid", agentid),BetBetting.class,page);
					}
					returnObject1.setData(betbettinglist);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/betbettingList10sec";
				}else if("1".equals(type)){
					List<SoccerScheme> datas=null;
					if("1".equals(directlyunder)){
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,bet_commission.commission from bet_commission left join soccer_scheme a on a.schemeid=bet_commission.orderid LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (bet_commission.agentid=:agentid ) and a.agentid=:agentid ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerScheme.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,bet_commission.commission from bet_commission left join soccer_scheme a on a.schemeid=bet_commission.orderid LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (bet_commission.agentid=:agentid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%"+agentid+"%").setParam("agentid", agentid),SoccerScheme.class,page);
					}
					soccer(datas);
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid", agentid);
					return "/lottery/betmember/soccerbettingList10sec";
				}else{
					List<SoccerAllbetting> datas =null;
					if("1".equals(directlyunder)){
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname,bet_commission.commission from bet_commission left join soccer_allbetting a on a.id=bet_commission.orderid left join bet_member b on a.memberid2 = b.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (bet_commission.agentid=:agentid) and (:type=100 or a.type=:type) and a.agentid=:agentid ").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid),SoccerAllbetting.class,page);
						model.addAttribute("directlyunder", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname,bet_commission.commission from bet_commission left join soccer_allbetting a on a.id=bet_commission.orderid left join bet_member b on a.memberid2 = b.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and (bet_commission.agentid=:agentid) and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%"+agentid+"%").setParam("agentid", agentid),SoccerAllbetting.class,page);
					}
					 
					soccerAll(datas);
					bjdc(datas);
					//篮球处理
					basketballAll(datas);
					
					returnObject1.setData(datas);
					returnObject1.setPage(page);
					model.addAttribute(GlobalStatic.returnDatas, returnObject1);
					model.addAttribute("type", type);
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
					model.addAttribute("agentid",agentid);
					return "/lottery/betmember/allsoccerbettingList10sec";
				}
			}
		}else if("13".equals(request.getParameter("k"))){
			//已结算
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page=newPage(request);
			String memberid2 = request.getParameter("id2");
			if(memberid2!=null&&StringUtils.isNoneBlank(memberid2)){
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				String gcname = request.getParameter("gcname");
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
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");

					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.situation=1 and a.memberid2=:memberid2 and c.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime),SoccerScheme.class,page);
						
						soccer(datas);
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
						returnObject.setQueryBean(betReportform);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingList555sec";
					}else{
						List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.bettingtime>=:starttime and a.bettingtime<:endtime and a.state=1 and a.gcname=:gcname and b.id2=:memberid2 ) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("starttime",starttime ).setParam("endtime", endtime),page,BetBetting.class,new BetBetting());
						
						returnObject.setQueryBean(new BetBetting());
						returnObject.setPage(page);
						returnObject.setData(datas);
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
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingList555sec";
					}
				}else{
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney, sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.id2=:id2 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.bettingtime>=:starttime and a.state=1 and a.bettingtime<:endtime group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("id2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime), page);
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
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
					model.addAttribute("memberid2", memberid2);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbetting555sec";
				}
			}else{
				return "errorpage/error";
			}
		}else if("14".equals(request.getParameter("k"))){
			//会员投注佣金
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page=newPage(request);
			String memberid2 = request.getParameter("id2");
			if(memberid2!=null&&StringUtils.isNoneBlank(memberid2)){
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				String gcname = request.getParameter("gcname");
				agentid=betMemberService.queryForObject(new Finder("select agentid from bet_member where id2=:id2 ").setParam("id2", memberid2), String.class);
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
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.commission,d.* from bet_commission a inner join (select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 ) d on a.orderid=d.schemeid where a.memberid2=:memberid2 and a.agentid=:agentid and d.bettingtime>=:starttime and d.bettingtime<:endtime and d.situation=1 ").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime),SoccerScheme.class,page);
						  
						soccer(datas);
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
						returnObject.setQueryBean(betReportform);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingList666sec";
					}else{
						List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select a.memberid2,a.commission,e.* from bet_commission a left join (select c.*,d.name2 from (select * from bet_betting where gcname=:gcname ) c left join bet_gameplay d on c.gameplayid =d.id ) e on a.orderid=e.id where a.memberid2=:memberid2 and a.agentid=:agentid and e.bettingtime>=:starttime and e.bettingtime<:endtime and e.state=1 ").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("starttime",starttime ).setParam("endtime", endtime),page,BetBetting.class,new BetBetting());
						returnObject.setQueryBean(new BetBetting());
						returnObject.setPage(page);
						returnObject.setData(datas);
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
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingList666sec";
					}
				}else{
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select b.gcname,sum(a.commission) as commission,sum(b.bettingmoney) as totallybettingmoney,sum(b.bettingscore-b.bettingmoney) as result from bet_commission a left join soccer_allbetting b on a.orderid=b.id where a.memberid2=:id2 and a.agentid=:agentid and b.bettingtime>=:starttime and b.bettingtime<:endtime and b.state=1 group by b.gcname   ").setParam("agentid", agentid).setParam("id2", memberid2).setParam("starttime",starttime ).setParam("endtime", endtime), page);
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
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
					model.addAttribute("memberid2", memberid2);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbetting666sec";
				}
			}else{
				return "errorpage/error";
			}
		}else if("15".equals(request.getParameter("k"))){
			//转账
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String requestagentid = request.getParameter("agentid");
			// ==构造分页请求
			Page page = newPage(request,"time","desc");
			// ==执行分页查询
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
			if("1".equals(request.getParameter("todayflag"))){
				Date dateeee=new Date();
				 starttime =new SimpleDateFormat("yyyy-MM-dd").format(dateeee);
				 endtime = new SimpleDateFormat("yyyy-MM-dd").format(dateeee);
				Date date2 =DateUtils.convertString2Date(endtime);
				Calendar calendar1 = new GregorianCalendar();
				if(date2!=null){
					calendar1.setTime(date2); 
					calendar1.add(Calendar.DATE,1);
					Date date3=calendar1.getTime();
					endtime = DateUtils.convertDate2String(date3);
				}
			}
			List<BetTransferAccounts> datas = new ArrayList<>();
			
			if("1".equals(request.getParameter("directlyunder"))){
				datas = betTransferAccountsService.queryForList(new Finder("select * from bet_transfer_accounts where (agentid=:agentid ) and time>=:starttime and time<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", requestagentid), BetTransferAccounts.class, page);
				model.addAttribute("directlyunder", 1);
			}else{
				datas = betTransferAccountsService.queryForList(new Finder("select * from bet_transfer_accounts where (agentid=:agentid or agentparentids like :aid ) and time>=:starttime and time<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", requestagentid).setParam("aid", "%,"+requestagentid+",%"), BetTransferAccounts.class, page);
			}
			
			returnObject.setQueryBean(new BetBetting());
			returnObject.setPage(page);
			returnObject.setData(datas);
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
			
			model.addAttribute("agentid", requestagentid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbetTransferAccountsList2sec";
		}else{
			//今日
			model.addAttribute("todayflag", 1);
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String requestagentid = request.getParameter("agentid");
			if(StringUtils.isBlank(requestagentid)){
				// ==执行分页查询
				//所在周开始日期
				Date dateeee=new Date();
				String starttime =new SimpleDateFormat("yyyy-MM-dd").format(dateeee);
				//所在周结束日期
				String endtime = new SimpleDateFormat("yyyy-MM-dd").format(dateeee);
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
				List<Map<String, Object>> agentdatas=betAgentService.queryForList(new Finder("select a.agentid,a.parentids,a.nickname from bet_agent a where (a.parentid=:id) and a.active=1").setParam("id", agentid));
				if(agentdatas!=null){
					for (Map<String, Object> map : agentdatas) {
						if(map.get("agentid")!=null){
							String ageasdf = (String)map.get("agentid");
							Map<String, Object> ddd = betAgentreportformNewJcService.queryForObject(new Finder("select sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(bettingtimecommission1) as bettingtimecommission1,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 1").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", ageasdf));
							if(ddd!=null){
								Double bettingmoney=0.;
								Double untreatedbettingmoney=0.;
								Double treatedbettingmoney=0.;
								Double bettingscore=0.;
								Double bettingwin=0.;
								Double bettingtimecommission=0.;
								Double bettingtimecommission1=0.;
								Double transferaccountsscore=0.;
								Object object = ddd.get("bettingmoney");
								Object object2 = ddd.get("untreatedbettingmoney");
								Object object5 = ddd.get("treatedbettingmoney");
								Object object6 = ddd.get("bettingscore");
								Object object7 = ddd.get("bettingwin");
								Object object8 = ddd.get("bettingtimecommission");
								Object object9 = ddd.get("bettingtimecommission1");
								Object object10 = ddd.get("transferaccountsscore");
								if(object!=null){
									bettingmoney=((BigDecimal)object).doubleValue();
								}
								if(object2!=null){
									untreatedbettingmoney=((BigDecimal)object2).doubleValue();
								}
								if(object5!=null){
									treatedbettingmoney=((BigDecimal)object5).doubleValue();
								}
								if(object6!=null){
									bettingscore=((BigDecimal)object6).doubleValue();
								}
								if(object7!=null){
									bettingwin=((BigDecimal)object7).doubleValue();
								}
								if(object8!=null){
									bettingtimecommission=((BigDecimal)object8).doubleValue();
								}
								if(object9!=null){
									bettingtimecommission1=((BigDecimal)object9).doubleValue();
								}
								if(object10!=null){
									transferaccountsscore=((BigDecimal)object10).doubleValue();
								}
								map.put("bettingmoney", bettingmoney);
								map.put("untreatedbettingmoney", untreatedbettingmoney);
								map.put("treatedbettingmoney", treatedbettingmoney);
								map.put("bettingscore", bettingscore);
								map.put("bettingwin", bettingwin);
								map.put("bettingtimecommission", bettingtimecommission);
								map.put("bettingtimecommission1", bettingtimecommission1);
								map.put("transferaccountsscore", transferaccountsscore);
							}
							String parentids = (String)map.get("parentids");
							if(parentids.startsWith(",")){
								parentids=parentids.substring(1);
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}else{
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}
						}
					}
				}
				
				Map<String, Object> sadfasd = betAgentreportformNewJcService.queryForObject(new Finder("select agentid,agentparentids,agentnickname,sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 0").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid));
				
				
				sadfasd.put("level", "直属");
				sadfasd.put("fgdfgdfgdf", 1);
				if(agentdatas!=null){
					agentdatas.add(sadfasd);
				}else{
					agentdatas=new ArrayList<Map<String, Object>>();
					agentdatas.add(sadfasd);
				}
				model.addAttribute("agentdatas", agentdatas);
				BetAgent betagentt = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
				model.addAttribute("agentaccount", betagentt.getAccount());	
				model.addAttribute("agentnickname", betagentt.getNickname());
				
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
				return "/lottery/betreportformnewJc/betreportformagreportsecv2";
		
			}else{
				BetAgent betaaa = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", requestagentid), BetAgent.class);
				if(betaaa!=null){
					if(betaaa.getAgentid().equals(agentid)||betaaa.getParentids().contains(agentid)){
						
					}else{
						return "/errorpage/error";
					}
				}else{
					return "/errorpage/error";
				}
				
				//所在周开始日期
				Date dateee=new Date();
				String starttime =new SimpleDateFormat("yyyy-MM-dd").format(dateee);
				//所在周结束日期
				String endtime = new SimpleDateFormat("yyyy-MM-dd").format(dateee);
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
				List<Map<String, Object>> agentdatas=betAgentService.queryForList(new Finder("select a.agentid,a.parentids,a.nickname from bet_agent a where (a.parentid=:id) ").setParam("id", requestagentid));
				
				if(agentdatas!=null){
					for (Map<String, Object> map : agentdatas) {
						if(map.get("agentid")!=null){
							String ageasdf = (String)map.get("agentid");
							
							Map<String, Object> ddd = betAgentreportformNewJcService.queryForObject(new Finder("select sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(bettingtimecommission1) as bettingtimecommission1,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 1").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", ageasdf));
							
							
							if(ddd!=null){
								Double bettingmoney=0.;
								Double untreatedbettingmoney=0.;
								Double treatedbettingmoney=0.;
								Double bettingscore=0.;
								Double bettingwin=0.;
								Double bettingtimecommission=0.;
								Double bettingtimecommission1=0.;
								Double transferaccountsscore=0.;
								Object object = ddd.get("bettingmoney");
								Object object2 = ddd.get("untreatedbettingmoney");
								Object object5 = ddd.get("treatedbettingmoney");
								Object object6 = ddd.get("bettingscore");
								Object object7 = ddd.get("bettingwin");
								Object object8 = ddd.get("bettingtimecommission");
								Object object9 = ddd.get("bettingtimecommission1");
								Object object10 = ddd.get("transferaccountsscore");
								if(object!=null){
									bettingmoney=((BigDecimal)object).doubleValue();
								}
								if(object2!=null){
									untreatedbettingmoney=((BigDecimal)object2).doubleValue();
								}
								if(object5!=null){
									treatedbettingmoney=((BigDecimal)object5).doubleValue();
								}
								if(object6!=null){
									bettingscore=((BigDecimal)object6).doubleValue();
								}
								if(object7!=null){
									bettingwin=((BigDecimal)object7).doubleValue();
								}
								if(object8!=null){
									bettingtimecommission=((BigDecimal)object8).doubleValue();
								}
								if(object9!=null){
									bettingtimecommission1=((BigDecimal)object9).doubleValue();
								}
								if(object10!=null){
									transferaccountsscore=((BigDecimal)object10).doubleValue();
								}
								map.put("bettingmoney", bettingmoney);
								map.put("untreatedbettingmoney", untreatedbettingmoney);
								map.put("treatedbettingmoney", treatedbettingmoney);
								map.put("bettingscore", bettingscore);
								map.put("bettingwin", bettingwin);
								map.put("bettingtimecommission", bettingtimecommission);
								map.put("bettingtimecommission1", bettingtimecommission1);
								map.put("transferaccountsscore", transferaccountsscore);
							}
							String parentids = (String)map.get("parentids");
							if(parentids.startsWith(",")){
								parentids=parentids.substring(1);
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}else{
								String[] split = parentids.split(",");
								map.put("level", "登"+split.length);
							}
						}
					}
				}
				
				Map<String, Object> sadfasd = betAgentreportformNewJcService.queryForObject(new Finder("select agentid,agentparentids,agentnickname,sum(bettingmoney) as bettingmoney,sum(untreatedbettingmoney) as untreatedbettingmoney,sum(treatedbettingmoney) as treatedbettingmoney,sum(bettingscore) as bettingscore,sum(bettingwin) as bettingwin,sum(bettingtimecommission) as bettingtimecommission,sum(transferaccountsscore) as transferaccountsscore from bet_agentreportform_new_jc where date>=:starttime and date<:endtime and agentid = :agentid and isagent = 0").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", requestagentid));
				
				
				sadfasd.put("level", "直属");
				sadfasd.put("fgdfgdfgdf", 1);
				if(agentdatas!=null){
					agentdatas.add(sadfasd);
				}else{
					agentdatas=new ArrayList<Map<String, Object>>();
					agentdatas.add(sadfasd);
				}
				
				model.addAttribute("agentdatas", agentdatas);
				model.addAttribute("agentaccount", betaaa.getAccount());
				model.addAttribute("agentnickname", betaaa.getNickname());
				
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
				model.addAttribute("agentid", requestagentid);
				return "/lottery/betreportformnewJc/betreportformagreportsecv2";
			}
		}
	}
	
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betReportform
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/seclist/v2")
	public String seclist(HttpServletRequest request, Model model,BetReportform betReportform) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		if("1".equals(request.getParameter("k"))){
			//上月 &搜索
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="3000-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate = java.sql.Date.valueOf(endtime);
			List<BetReportform> datas = null;
			if(starttime=="0000-01-01" && endtime=="3000-01-01"){
				datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid  ").setParam("agentid", agentid),page,BetReportform.class,betReportform);
				model.addAttribute("show", 2);
				model.addAttribute("addtotalcount", 1);
				
				
			}else{
				datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid and  date>=:starttime and date<=:endtime ").setParam("agentid", agentid).setParam("starttime",startDate).setParam("endtime", endDate),page,BetReportform.class,betReportform);
				if("-1".equals(request.getParameter("show"))){
					model.addAttribute("show", -1);
				}else{
					model.addAttribute("show", 2);
				}
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
				Date parse2 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
				String early = DateFormatUtils.format(date, "yyyy-MM-dd 00:00:00");
				date = sdf.parse(early);
				if((date.equals(parse) || date.after(parse)) && date.equals(parse2) || date.before(parse2)){
					model.addAttribute("addtotalcount", 1);
				}
				
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, 1);
			
			returnObject.setQueryBean(betReportform);
			returnObject.setPage(page);
			returnObject.setData(datas);
			Double gamewin = 0d;
			Double welfare = 0d;
			if(starttime=="0000-01-01" && endtime=="3000-01-01"){
				gamewin = betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid =:agentid ").setParam("agentid", agentid), Double.class);
				welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid =:agentid ").setParam("agentid", agentid), Double.class);
			}else{
				if(!starttime.equals(endtime)){
					gamewin = betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid =:agentid and (date between :starttime and :endtime) ").setParam("agentid", agentid).setParam("starttime", starttime).setParam("endtime", endtime), Double.class);
					welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid =:agentid and (date between :starttime and :endtime) ").setParam("agentid", agentid).setParam("starttime", starttime).setParam("endtime", endtime), Double.class);
				}else{
					gamewin = betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid =:agentid and date=:date").setParam("agentid", agentid).setParam("date", starttime), Double.class);
					welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid =:agentid and date=:date ").setParam("agentid", agentid).setParam("date", starttime), Double.class);
				}
			}
			if(gamewin==null){
				gamewin=0.;
			}
			if(welfare==null){
				welfare=0.;
			}
			model.addAttribute("gamewin", gamewin);
			model.addAttribute("welfare", welfare);
			model.addAttribute("xs", 1);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			if(starttime=="0000-01-01"){
				starttime=null;
			}
			if(endtime=="3000-01-01"){
				endtime=null;
			}
			model.addAttribute("startDate", starttime);
			model.addAttribute("endDate", endtime);
			return listurlsec;
		}else if("2".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="3000-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BetReportform> datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid=:agentid and date>=:starttime and date<=:endtime ").setParam("agentid", agentid).setParam("starttime",startDate).setParam("endtime", endDate),page,BetReportform.class,betReportform);
			returnObject.setQueryBean(betReportform);
			returnObject.setPage(page);
			returnObject.setData(datas);
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="3000-01-01"){
				endDate=null;
			}
			Double allresult = 0d;
			allresult= betReportformService.queryForObject(new Finder("select sum(result) from bet_agentreportform where  agentid=:agentid and date>=:starttime and date<=:endtime").setParam("agentid", agentid).setParam("starttime",startDate).setParam("endtime", endDate), Double.class);
			model.addAttribute("allresult", allresult);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurlsec;
		}else if("3".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			String date = request.getParameter("date");
			BetMember betMember = new BetMember();
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select * from bet_member where (agentid=:agentid or agentparentids like :aid) and isinternal=0 and date_format(signdate,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetMember.class,betMember );
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute("signdate", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformregisterListsec";
		}else if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface  "));
			String date = request.getParameter("date");
			BetGold betGold = new BetGold();
			List<BetGold> datas=new ArrayList<>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betGoldService.findListDataByFinder(new Finder("select  a.*,b.id2 as memberid2  from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.rechargetime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetGold.class,betGold);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betGoldService.findListDataByFinder(new Finder("select  a.*,b.id2 as memberid2  from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.rechargetime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetGold.class,betGold);
			}
			if(datas!=null){
				for (BetGold betGold2 : datas) {
					if(idandbanktypelist!=null){
						for (Map<String, Object> map : idandbanktypelist) {
							if(map.get("id").equals(betGold2.getSource())){
								betGold2.setSource((String)map.get("banktype"));
								break;
							}else{
								betGold2.setSource(null);
							}
						}
					}
				}
			}
			
			returnObject.setQueryBean(betGold);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("rechargetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformrechargeListsec";
		}else if("5".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetWithdrawcash betWithdrawcash = new BetWithdrawcash();
			List<BetWithdrawcash> datas=new ArrayList<BetWithdrawcash>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.audittime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetWithdrawcash.class,betWithdrawcash);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.audittime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetWithdrawcash.class,betWithdrawcash);
			}
			returnObject.setQueryBean(betWithdrawcash);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("audittime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformwithdrawcashListsec";
		}else if("7".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetRedenvelopeRecord betRedenvelopeRecord = new BetRedenvelopeRecord();
			List<BetRedenvelopeRecord> datas=betRedenvelopeRecordService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.redenvelopecode,a.receivescore,a.receivetime,a.source,a.state,a.redenvelopeid,a.agentid,a.agentparentid,a.agentparentids from bet_redenvelope_record a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRedenvelopeRecord.class,betRedenvelopeRecord);
			returnObject.setQueryBean(betRedenvelopeRecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("receivetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformredenveloperecordListsec";
		}else  if("8".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetSubordinaterebateDetail betSubordinaterebateDetail = new BetSubordinaterebateDetail();
			List<BetSubordinaterebateDetail> datas=betSubordinaterebateDetailService.findListDataByFinder(new Finder("select  a.id,a.memberid2,a.nickname,a.recommendnum,a.sb,a.subordinatebet,a.sc,a.subordinaterecharge,a.sl,a.subordinatelose,a.income,a.subtime,a.receivetime,a.receiveip,a.state,a.agentid,a.agentparentid,a.agentparentids from bet_subordinaterebate_detail a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.subtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
			returnObject.setQueryBean(betSubordinaterebateDetail);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("subtime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformsubordinaterebateListsec";
		}else if("9".equals(request.getParameter("k"))){
			String gcname = request.getParameter("gcname");
			String memberid2 = request.getParameter("memberid2");
			if(StringUtils.isNoneEmpty(gcname)){
				gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request,"bettingtime","desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				if(StringUtils.isNoneEmpty(memberid2)){
					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas=new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
						}
						
						
						soccer(datas);
						
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingListsec";
					}else if("北京单场".equals(gcname)){
						List<BjdcScheme> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
						}
						corebjdc(datas);
						return "/lottery/betreportformnewJc/betreportformbjdcbettingListsec";
					}else if("竞彩篮球".equals(gcname)){
						List<BasketballScheme> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
						}
						
						
						basketball(datas);
						
						
						
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbasketballschemeListsec";
						
						
					}else{
						List<BetBetting> datas=new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
							model.addAttribute("virtualmember", 1);
						}else{
							datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
						}
						
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingListsec";
					}
				}else{
					page.setSort("desc");
					page.setOrder("bettingmoney");
					List<Map<String, Object>> datas =new ArrayList<>();
					if("1".equals(request.getParameter("virtualmember"))){
						datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,b.nickname,b.agentid,sum(a.bettingmoney) as bettingmoney from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state!=2 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
						model.addAttribute("virtualmember", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,b.nickname,b.agentid,sum(a.bettingmoney) as bettingmoney from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state!=2 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
					}
					
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute("gcname", gcname);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformmemberbettingListsec";
				}
			}else{
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("totallybettingmoney");
				page.setPageSize(50);
				page.setSort("desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				List<Map<String, Object>> datas =new ArrayList<>();
				if("1".equals(request.getParameter("virtualmember"))){
					datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state!=2 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
					model.addAttribute("virtualmember", 1);
				}else{
					datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state!=2 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
				}
				
				returnObject.setQueryBean(betBetting);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("bettingtime", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betreportformnewJc/betreportformgameclassbettingListsec";
			}
			
		}else if("10".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("a."+page.getOrder());
			String date = request.getParameter("date");
			BetRankMember betRankMember = new BetRankMember();
			List<BetRankMember> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.memberid,b.id2,a.nickname,a.score,a.gamemoney,a.bankmoney,a.freezingscore from bet_rank_member a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.date,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRankMember.class,betRankMember);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformmemberListsec";
		}else if("11".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			if(page.getOrder()=="id"){
				page.setOrder("a.id");
			}
			String date = request.getParameter("date");
			BetBetting betBetting = new BetBetting();
			List<BetBetting> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.bettingtime,a.gcname,a.name1,sum(a.bettingmoney) AS bettingmoney from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date GROUP BY a.name1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
			returnObject.setQueryBean(betBetting);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("bettingtime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbettingmoneyListsec";
		}else if("12".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetDaywinorfailrebate betdaywinorfailrebate = new BetDaywinorfailrebate();
			List<BetDaywinorfailrebate> datas=betDaywinorfailrebateService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.nickname,a.dayscore,a.daybettingmoney,a.rebate,a.receivetime,a.receiveip,a.gamescore,a.bankscore,a.state,a.date,a.agentid,a.agentparentid,a.agentparentids from bet_daywinorfailrebate a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetDaywinorfailrebate.class,betdaywinorfailrebate);
			returnObject.setQueryBean(betdaywinorfailrebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("receivetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformdaywinorfailListsec";
		}else if("13".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetReliefRecord betReliefRecord = new BetReliefRecord();
			List<BetReliefRecord> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.id,a.memberid,a.memberid2,a.reliefscore,a.date,a.agentid,a.agentparentid,a.agentparentids from bet_relief_record a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.date,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetReliefRecord.class,betReliefRecord);
			returnObject.setQueryBean(betReliefRecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformreliefListsec";
		}else if("14".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetScorerecord betScorerecord = new BetScorerecord();
			List<BetScorerecord> datas=betScorerecordService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.time,a.content,a.originalscore,a.changescore,a.balance,a.state,a.remark,a.type,a.agentid,a.agentparentid,a.agentparentids from bet_scorerecord a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.time,:format)=:date and type=:state ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("state", 9),page,BetScorerecord.class,betScorerecord);
			returnObject.setQueryBean(betScorerecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("state", 9);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformrankListsec";
		}else if("15".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetScorerecord betScorerecord = new BetScorerecord();
			List<BetScorerecord> datas=betScorerecordService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.time,a.content,a.originalscore,a.changescore,a.balance,a.state,a.remark,a.type,a.agentid,a.agentparentid,a.agentparentids from bet_scorerecord a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.time,:format)=:date and type=:state ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("state", 3),page,BetScorerecord.class,betScorerecord);
			returnObject.setQueryBean(betScorerecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("state", 3);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformsigninListsec";
		}else if("16".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetFirstrechargerebate betFirstrechargerebate = new BetFirstrechargerebate();
			List<BetFirstrechargerebate> datas=betFirstrechargerebateService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.nickname,a.recharge,a.bettingmoney,a.rebate,a.receivetime,a.receiveip,a.gamescore,a.bankscore,a.state,a.date,a.agentid,a.agentparentid,a.agentparentids from bet_firstrechargerebate a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetFirstrechargerebate.class,betFirstrechargerebate);
			returnObject.setQueryBean(betFirstrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("receivetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformfirstrechargeListsec";
		}else if("17".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetRechargecard betRechargecard = new BetRechargecard();
			List<BetRechargecard> datas=betRechargecardService.findListDataByFinder(new Finder("select a.id,a.password,a.money,a.state,a.ip,a.time,a.memberid2,a.operator,a.validity,a.rechargetime,a.agentid,a.agentparentid,a.agentparentids,a.exchangeid2 from bet_rechargecard a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.time,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRechargecard.class,betRechargecard);
			returnObject.setQueryBean(betRechargecard);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformrechargecardListsec";
		}else if("18".equals(request.getParameter("k"))){
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="3000-01-01";
			}
			Date date =  new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			//
			Double gamewin= betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid=:agentid and date_format(date,:format)=:date ").setParam("agentid", agentid).setParam("format", "%Y-%m").setParam("date", sdf.format(date)), Double.class);
			model.addAttribute("gamewin", gamewin);
			Double welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid=:agentid and date_format(date,:format)=:date ").setParam("agentid", agentid).setParam("format", "%Y-%m").setParam("date", sdf.format(date)), Double.class);
			model.addAttribute("welfare", welfare);
			
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<BetReportform> datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid and date between :starttime and :endtime ").setParam("agentid", agentid).setParam("starttime",starttime).setParam("endtime",endtime),page,BetReportform.class,betReportform);
				returnObject.setQueryBean(betReportform);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			if(starttime=="0000-01-01"){
				starttime=null;
			}
			if(endtime=="3000-01-01"){
				endtime=null;
			}
			model.addAttribute("startDate", starttime);
			model.addAttribute("endDate", endtime);
			model.addAttribute("p", "1");
			return listurlsec;
		}else if("19".equals(request.getParameter("k"))){
			//退佣
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			List<BetBetting> datas = betBettingService.queryForList(new Finder("select a.bettingmoney,a.memberty,a.membertytime,b.id2 as memberid2 from bet_betting a left join bet_member b on a.memberid=b.id  where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.membertystate=1 and date_format(a.membertytime,:format)=:date order by b.id2").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), BetBetting.class);
			returnObject.setQueryBean(new BetBetting());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformtymemberListsec";
		}else if("20".equals(request.getParameter("k"))){
			//总福利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String date = request.getParameter("date");
			List<BetReportform> data =new ArrayList<BetReportform>();
//			BetReportform data = null;
			if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(date)){
				Double registersend = betRegisterRewardService.queryForObject(new Finder("select sum(reward) from bet_register_reward where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(registersend == null){
					registersend = 0.;
				}
				Double signinreward = betSigninRewardService.queryForObject(new Finder("select sum(reward) from bet_signin_reward where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid)  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(signinreward==null){
					signinreward=0.;
				}
				Double firstrebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where date=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", date), Double.class);
				if(firstrebate==null){
					firstrebate=0.;
				}
				Double sumSubordinaterebate = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(a.income) from bet_subordinaterebate_detail a left join bet_member b on a.memberid2=b.id2 where a.state=1 and b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.subtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(sumSubordinaterebate==null){
					sumSubordinaterebate=0.;
				}
				Double payrebate = betSinglerechargeService.queryForObject(new Finder("select sum(rebate) from bet_singlerecharge where state=1 and date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(payrebate == null){
					payrebate = 0.;
				}
				Double todayrebate = betTodayrechargerebateService.queryForObject(new Finder("select sum(reward) from bet_todayrechargerebate where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("date", date),Double.class);
				if(todayrebate == null){
					todayrebate = 0.;
				}
				Double relief = betReliefRecordService.queryForObject(new Finder("select sum(reliefscore) from bet_relief_record where date_format(date,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(relief==null){
					relief=0.;
				}
				Double rankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where state=1 and date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date),Double.class);
				if(rankmember==null){
					rankmember=0.;
				}
				Double weekwinorfail = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(weekwinorfail==null){
					weekwinorfail=0.;
				}
				Double daywinorfail = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate  where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(daywinorfail==null){
					daywinorfail=0.;
				}
				BetReportform reportform = new BetReportform();
				reportform.setRegistersend(registersend);
				reportform.setFirstrecharge(firstrebate);
				reportform.setSubordinaterebate(sumSubordinaterebate);
				reportform.setPayrebate(payrebate);
				reportform.setTodayrechargerebate(todayrebate);
				reportform.setSignin(signinreward.intValue());
				reportform.setRelief(relief.intValue());
				reportform.setRank(rankmember);
				reportform.setWeekwinorfailrebate(weekwinorfail);
				reportform.setDaywinorfailrebate(daywinorfail);
				data.add(reportform);
			}else{
				data = betReportformService.queryForList(new Finder("select * from bet_agentreportform where date=:date and agentid=:agentid  ").setParam("agentid", agentid).setParam("date", date), BetReportform.class);
			}
				returnObject.setData(data);
				model.addAttribute("time", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
			
			return "/lottery/betreportformnewJc/betreportformallwelfareListsec";
		}else if("21".equals(request.getParameter("k"))){
			//注册 送
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetRegisterReward betRegisterReward = new BetRegisterReward();
			List<BetRegisterReward> datas=betRegisterRewardService.findListDataByFinder(new Finder("select a.* from bet_register_reward a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRegisterReward.class,betRegisterReward);
			returnObject.setQueryBean(betRegisterReward);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformRegisterSendListsec";
		}else if("22".equals(request.getParameter("k"))){
			//单笔充值返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetSinglerecharge betSinglerecharge = new BetSinglerecharge();
			List<BetSinglerecharge> datas=betSinglerechargeService.findListDataByFinder(new Finder("select a.* from bet_singlerecharge a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=1 and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetSinglerecharge.class,betSinglerecharge);
			returnObject.setQueryBean(betSinglerecharge);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformSinglereChargeListsec";
		}else if("23".equals(request.getParameter("k"))){
			//当日充值返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetTodayrechargerebate betTodayrechargerebate = new BetTodayrechargerebate();
			List<BetTodayrechargerebate> datas=betTodayrechargerebateService.findListDataByFinder(new Finder("select a.* from bet_todayrechargerebate a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=1 and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetTodayrechargerebate.class,betTodayrechargerebate);
			returnObject.setQueryBean(betTodayrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformTodayrechargereListsec";
		}else if("24".equals(request.getParameter("k"))){
			//周输赢返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetWeekwinorfailrebate betWeekwinorfailrebate = new BetWeekwinorfailrebate();
			List<BetWeekwinorfailrebate> datas=betWeekwinorfailrebateService.findListDataByFinder(new Finder("select a.* from bet_weekwinorfailrebate a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=1 and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
			returnObject.setQueryBean(betWeekwinorfailrebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformWeekwinorfailrebateListsec";
		}else if("25".equals(request.getParameter("k"))){
			//转账分
			
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetTransferAccounts betTransferAccounts = new BetTransferAccounts();
			List<BetTransferAccounts> datas=new ArrayList<>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betTransferAccountsService.findListDataByFinder(new Finder("select a.* from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.time,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betTransferAccountsService.findListDataByFinder(new Finder("select a.* from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.time,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
			}
			returnObject.setQueryBean(betTransferAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportform/betreportformbetTransferAccountsListsec";
		}else if("26".equals(request.getParameter("k"))){
			String gcname = request.getParameter("gcname");
			String memberid2 = request.getParameter("memberid2");
			if(StringUtils.isNoneEmpty(gcname)){
				gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request,"bettingtime","desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				if(StringUtils.isNoneEmpty(memberid2)){
					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where (c.agentid=:agentid or c.agentparentids like :aid) and date_format(a.settlementtime,:format)=:date and a.memberid2=:memberid2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
						soccer(datas);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingListxxsec";
					}else if("北京单场".equals(gcname)){
						gcbjdc(model, agentid, gcname, memberid2, returnObject, page, date, betBetting);
						return "/lottery/betreportformnewJc/betreportformbjdcbettingListxxsec";
					}else if("竞彩篮球".equals(gcname)){
						List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  (c.agentid=:agentid or c.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.settlementtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
						basketball(datas);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbasketballschemeListsettlesec";
					}else{
						List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingListsec";
					}
				}else{
					page.setSort("desc");
					page.setOrder("bettingscore");
					List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from(select a.memberid2,b.agentid,b.nickname,sum(a.bettingmoney) as bettingmoney,sum(a.bettingscore-a.bettingmoney) as bettingscore from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.settlementtime,:format)=:date and a.gcname=:gcname and a.state!=2 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute("gcname", gcname);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformmemberbettingListxxsec";
				}
			}else{
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("result");
				page.setPageSize(50);
				page.setSort("desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname,sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND a.state!=2 and date_format(a.settlementtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
				returnObject.setQueryBean(betBetting);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("bettingtime", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betreportformnewJc/betreportformgameclassbettingListxxsec";
			}
		
		}else if("28".equals(request.getParameter("k"))){
			//游戏扣税
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("gks");
			page.setSort("desc");
			String date = request.getParameter("date");
			BetRankMember betRankMember = new BetRankMember();
			List<Map<String, Object>> datas = betBettingService.queryForList(new Finder("select gcname, sum(a.gks) as gks from bet_betting a left join bet_member b on a.memberid=b.id where (b.agentid=:agentid or b.agentparentids like :aid) and settlementtime>=:date group by gcname HAVING sum(a.gks)>0 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", date), page);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformmemberListgkssec";
		}else if("29".equals(request.getParameter("k"))){
				//未结算
				String gcname = request.getParameter("gcname");
				String memberid2 = request.getParameter("memberid2");
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request,"bettingtime","desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					if(StringUtils.isNoneEmpty(memberid2)){
						if("竞彩足球".equals(gcname)){
							List<SoccerScheme> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
							}
							
							soccer(datas);
							
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformsoccerbettingListuntreatedsec";
						}else if("北京单场".equals(gcname)){
							List<BjdcScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
							}
							corebjdc(datas);
							return "/lottery/betreportformnewJc/betreportformbjdcbettingListuntreatedsec";
							
						}else if("竞彩篮球".equals(gcname)){
							List<BasketballScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
							}
							
							basketball(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbasketballschemeListuntreatedsec";
						}else{
							List<BetBetting> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
								model.addAttribute("virtualmember", 1);
							}else{
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
							}
							
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbettingListuntreatedsec";
						}
					}else{
						page.setSort("desc");
						page.setOrder("bettingmoney");
						List<Map<String, Object>> datas = new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=0 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=0 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
						}
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
//						return "/lottery/betreportformnewJc/betreportformgameclassbettingList";
						return "/lottery/betreportformnewJc/betreportformmemberbettingListuntreatedsec";
					}
				}else{
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request);
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas=new ArrayList<>();
					if("1".equals(request.getParameter("virtualmember"))){
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=0 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
						model.addAttribute("virtualmember", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=0 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
					}
					
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbettingListuntreatedsec";
				}
				
			}else if("30".equals(request.getParameter("k"))){
				//已结算
				String gcname = request.getParameter("gcname");
				String memberid2 = request.getParameter("memberid2");
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request,"bettingtime","desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					if(StringUtils.isNoneEmpty(memberid2)){
						if("竞彩足球".equals(gcname)){
							List<SoccerScheme> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
							}
							soccer(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformsoccerbettingListtreatedsec";
						}else if("北京单场".equals(gcname)){
							List<BjdcScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
							}
							corebjdc(datas);
							return "/lottery/betreportformnewJc/betreportformbjdcbettingListtreatedsec";
						}else if("竞彩篮球".equals(gcname)){
							List<BasketballScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
							}
							basketball(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbasketballschemeListtreatedsec";
							
							
						}else{
							List<BetBetting> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
								model.addAttribute("virtualmember", 1);
							}else{
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
							}
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbettingListtreatedsec";
						}
					}else{
						page.setSort("desc");
						page.setOrder("bettingmoney");
						List<Map<String, Object>> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,sum(a.bettingscore) as bettingscore,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=1 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,sum(a.bettingscore) as bettingscore,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=1 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
						}
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformmemberbettingListtreatedsec";
					}
				}else{
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request);
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas =new ArrayList<>();
					if("1".equals(request.getParameter("virtualmember"))){
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=1 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
						model.addAttribute("virtualmember", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=1 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
					}
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbettingListtreatedsec";
				}
			}else if("31".equals(request.getParameter("k"))){

				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				// ==执行分页查询
				String date = request.getParameter("date");
				BetAgentwithdraw betMember = new BetAgentwithdraw();
				List<BetAgentwithdraw> datas=betAgentwithdrawService.findListDataByFinder(new Finder("select * from bet_agentwithdraw where (agentid=:agentid or agentparentids like :aid) and date_format(audittime,:format)=:date and state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetAgentwithdraw.class,betMember );
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas);
				
				model.addAttribute("date", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betreportformnewJc/betreportformagentwithdrawListsec";
			
			}else{

				//本周
				SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal11 = Calendar.getInstance();
				cal11.add(Calendar.DATE, -1);
				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				Date startDate = cal.getTime();
				model.addAttribute("startDate", sdfdate.format(startDate));
				Calendar currentDate = new GregorianCalendar();   
				currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
				currentDate.set(Calendar.HOUR_OF_DAY, 23);  
				currentDate.set(Calendar.MINUTE, 59);  
				currentDate.set(Calendar.SECOND, 59);  
				currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
				model.addAttribute("endDate", sdfdate.format(currentDate.getTime()));
				ReturnDatas returnObject = listjson(request, model, betReportform);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				model.addAttribute("p", "0");
				model.addAttribute("show", 0);
				model.addAttribute("agentparentid", betagent.getParentid());
				return listurlsec;
				
			}
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betReportform
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetReportform betReportform) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(50);
		page.setOrder("date");
		
		// ==执行分页查询
		List<BetReportform> datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid and  YEARWEEK(date_format(date,:ppp),1) = YEARWEEK(:date,1)  ").setParam("agentid", agentid).setParam("ppp", "%Y-%m-%d").setParam("date", new Date()),page,BetReportform.class,betReportform);
		Calendar cal = Calendar.getInstance();
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		if(datas!=null){
			BetReportform betReportform1 = datas.get(0);
			//提现统计
			String date = "";
			if(betReportform1!=null){
				date = new SimpleDateFormat("yyyy-MM-dd").format(betReportform1.getDate());
			}
			if(yesterday.equals(date)){
				Double sumBetWithdrawcashMoney = betWithdrawcashService.queryForObject(new Finder("select sum(a.money) from bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=2 and date_format(a.audittime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", yesterday), Double.class);
				if(sumBetWithdrawcashMoney==null){
					sumBetWithdrawcashMoney=0.;
				}
				
				//代理提现
				Double sumagentwithdrawcash = betAgentwithdrawService.queryForObject(new Finder("select sum(money) from bet_agentwithdraw where  (agentid=:agentid or agentparentids like :aid) and date_format(audittime,:format)=:date and state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", yesterday), Double.class);
				if(sumagentwithdrawcash==null){
					sumagentwithdrawcash=0.;
				}
				
				//不出票用户提现
				try {
					Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT  SUM(a.money) as bettingmoney FROM bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and b.isissue=0 and a.state=2 and (a.agentid=:agentid or a.agentparentids like :aid)  and  date_format(a.audittime,:format)=:date").setParam("format", "%Y-%m-%d").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
					if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
						double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
						betReportform1.setNoissuebetwithdrawcash(bettingmoney1);
					}else{
						betReportform1.setNoissuebetwithdrawcash(0.);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				betReportform1.setWithdrawcash(sumBetWithdrawcashMoney);
				betReportform1.setAgentwithdrawcash(sumagentwithdrawcash);
			}
		}
		
		model.addAttribute("addtotalcount", 1);
		returnObject.setQueryBean(betReportform);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	
	/**
	 * 一级每日报表
	 * 
	 * @param request
	 * @param model
	 * @param betReportform
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/v2")
	public String list(HttpServletRequest request, Model model,BetReportform betReportform) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		String parentids = ","+agentid+",";
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		if("1".equals(request.getParameter("k"))){
			//上月 &搜索
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("date");
			// ==执行分页查询
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="3000-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate = java.sql.Date.valueOf(endtime);
			List<BetReportform> datas = null;
			if(starttime=="0000-01-01" && endtime=="3000-01-01"){
				datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid  ").setParam("agentid", agentid),page,BetReportform.class,betReportform);
				model.addAttribute("show", 2);
				model.addAttribute("addtotalcount", 1);
			}else{
				datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid and  date>=:starttime and date<=:endtime ").setParam("agentid", agentid).setParam("starttime",startDate).setParam("endtime", endDate),page,BetReportform.class,betReportform);
				if("-1".equals(request.getParameter("show"))){
					model.addAttribute("show", -1);
				}else{
					model.addAttribute("show", 2);
				}
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
				Date parse2 = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
				String early = DateFormatUtils.format(date, "yyyy-MM-dd 00:00:00");
				date = sdf.parse(early);
				if((date.equals(parse) || date.after(parse)) && date.equals(parse2) || date.before(parse2)){
					model.addAttribute("addtotalcount", 1);
				}
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, 1);
			
			Calendar cal1 = Calendar.getInstance();
			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
			if(datas!=null){
				BetReportform betReportform1 = datas.get(0);
				//提现统计
				String date = "";
				if(betReportform1!=null){
					date = new SimpleDateFormat("yyyy-MM-dd").format(betReportform1.getDate());
				}
				if(yesterday.equals(date)){
					Double sumBetWithdrawcashMoney = betWithdrawcashService.queryForObject(new Finder("select sum(a.money) from bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=2 and date_format(a.audittime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", yesterday), Double.class);
					if(sumBetWithdrawcashMoney==null){
						sumBetWithdrawcashMoney=0.;
					}
					//代理提现
					Double sumagentwithdrawcash = betAgentwithdrawService.queryForObject(new Finder("select sum(money) from bet_agentwithdraw where  (agentid=:agentid or agentparentids like :aid) and date_format(audittime,:format)=:date and state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", yesterday), Double.class);
					if(sumagentwithdrawcash==null){
						sumagentwithdrawcash=0.;
					}
					//不出票用户提现
					try {
						Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT  SUM(a.money) as bettingmoney FROM bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and b.isissue=0 and a.state=2 and (a.agentid=:agentid or a.agentparentids like :aid)  and  date_format(a.audittime,:format)=:date").setParam("date", yesterday).setParam("format", "%Y-%m-%d").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
						if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
							double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
							betReportform1.setNoissuebetwithdrawcash(bettingmoney1);
						}else{
							betReportform1.setNoissuebetwithdrawcash(0.);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					betReportform1.setWithdrawcash(sumBetWithdrawcashMoney);
					betReportform1.setAgentwithdrawcash(sumagentwithdrawcash);
				}
				
			}
			
			returnObject.setQueryBean(betReportform);
			returnObject.setPage(page);
			returnObject.setData(datas);
			Double gamewin = 0d;
			Double welfare = 0d;
			if(starttime=="0000-01-01" && endtime=="3000-01-01"){
				gamewin = betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid =:agentid ").setParam("agentid", agentid), Double.class);
				welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid =:agentid ").setParam("agentid", agentid), Double.class);
			}else{
				if(!starttime.equals(endtime)){
					gamewin = betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid =:agentid and (date between :starttime and :endtime) ").setParam("agentid", agentid).setParam("starttime", starttime).setParam("endtime", endtime), Double.class);
					welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid =:agentid and (date between :starttime and :endtime) ").setParam("agentid", agentid).setParam("starttime", starttime).setParam("endtime", endtime), Double.class);
				}else{
					gamewin = betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid =:agentid and date=:date").setParam("agentid", agentid).setParam("date", starttime), Double.class);
					welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid =:agentid and date=:date ").setParam("agentid", agentid).setParam("date", starttime), Double.class);
				}
			}
			if(gamewin==null){
				gamewin=0.;
			}
			if(welfare==null){
				welfare=0.;
			}
			model.addAttribute("gamewin", gamewin);
			model.addAttribute("welfare", welfare);
			model.addAttribute("xs", 1);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			if(starttime=="0000-01-01"){
				starttime=null;
			}
			if(endtime=="3000-01-01"){
				endtime=null;
			}
			model.addAttribute("startDate", starttime);
			model.addAttribute("endDate", endtime);
			
			String job4start = dicDataService.queryForObject(new Finder("select remark from  t_dic_data where code=:code").setParam("code", "agentjob4"),String.class);
			String job4end = dicDataService.queryForObject(new Finder("select value from  t_dic_data where code=:code").setParam("code", "agentjob4"),String.class);
			model.addAttribute("job4start", job4start);
			model.addAttribute("job4end", job4end);
			return listurl;
		}else if("2".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="3000-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BetReportform> datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid=:agentid and date>=:starttime and date<=:endtime ").setParam("agentid", agentid).setParam("starttime",startDate).setParam("endtime", endDate),page,BetReportform.class,betReportform);
			returnObject.setQueryBean(betReportform);
			returnObject.setPage(page);
			returnObject.setData(datas);
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="3000-01-01"){
				endDate=null;
			}
			Double allresult = 0d;
			allresult= betReportformService.queryForObject(new Finder("select sum(result) from bet_agentreportform where  agentid=:agentid and date>=:starttime and date<=:endtime").setParam("agentid", agentid).setParam("starttime",startDate).setParam("endtime", endDate), Double.class);
			model.addAttribute("allresult", allresult);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}else if("3".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			String date = request.getParameter("date");
			BetMember betMember = new BetMember();
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select * from bet_member where (agentid=:agentid or agentparentids like :aid) and isinternal=0 and date_format(signdate,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetMember.class,betMember );
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute("signdate", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformregisterList";
		}else if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface  "));
			String date = request.getParameter("date");
			BetGold betGold = new BetGold();
			List<BetGold> datas=new ArrayList<>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betGoldService.findListDataByFinder(new Finder("select  a.*,b.id2 as memberid2  from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.rechargetime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetGold.class,betGold);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betGoldService.findListDataByFinder(new Finder("select  a.*,b.id2 as memberid2  from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.rechargetime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetGold.class,betGold);
			}
			if(datas!=null){
				for (BetGold betGold2 : datas) {
					if(idandbanktypelist!=null){
						for (Map<String, Object> map : idandbanktypelist) {
							if(map.get("id").equals(betGold2.getSource())){
								betGold2.setSource((String)map.get("banktype"));
								break;
							}else{
								betGold2.setSource(null);
							}
						}
					}
				}
			}
			
			returnObject.setQueryBean(betGold);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("rechargetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformrechargeList";
		}else if("5".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetWithdrawcash betWithdrawcash = new BetWithdrawcash();
			List<BetWithdrawcash> datas=new ArrayList<BetWithdrawcash>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.audittime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetWithdrawcash.class,betWithdrawcash);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.audittime,:format)=:date and a.state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetWithdrawcash.class,betWithdrawcash);
			}
			returnObject.setQueryBean(betWithdrawcash);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("audittime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformwithdrawcashList";
		}else if("7".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetRedenvelopeRecord betRedenvelopeRecord = new BetRedenvelopeRecord();
			List<BetRedenvelopeRecord> datas=betRedenvelopeRecordService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.redenvelopecode,a.receivescore,a.receivetime,a.source,a.state,a.redenvelopeid,a.agentid,a.agentparentid,a.agentparentids from bet_redenvelope_record a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRedenvelopeRecord.class,betRedenvelopeRecord);
			returnObject.setQueryBean(betRedenvelopeRecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("receivetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformredenveloperecordList";
		}else  if("8".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetSubordinaterebateDetail betSubordinaterebateDetail = new BetSubordinaterebateDetail();
			List<BetSubordinaterebateDetail> datas=betSubordinaterebateDetailService.findListDataByFinder(new Finder("select  a.id,a.memberid2,a.nickname,a.recommendnum,a.sb,a.subordinatebet,a.sc,a.subordinaterecharge,a.sl,a.subordinatelose,a.income,a.subtime,a.receivetime,a.receiveip,a.state,a.agentid,a.agentparentid,a.agentparentids from bet_subordinaterebate_detail a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.subtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
			returnObject.setQueryBean(betSubordinaterebateDetail);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("subtime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformsubordinaterebateList";
		}else if("9".equals(request.getParameter("k"))){
			String gcname = request.getParameter("gcname");
			String memberid2 = request.getParameter("memberid2");
			if(StringUtils.isNoneEmpty(gcname)){
				gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request,"bettingtime","desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				if(StringUtils.isNoneEmpty(memberid2)){
					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas=new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date",date),SoccerScheme.class,page);
						}
						soccer(datas);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingList";
					}else if("北京单场".equals(gcname)){
						List<BjdcScheme> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
						}
						corebjdc(datas);
						return "/lottery/betreportformnewJc/betreportformbjdcbettingList";
					}else if("大乐透".equals(gcname)){
						List<LotteryScheme> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = basketballSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from lottery_scheme a left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),LotteryScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = basketballSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from lottery_scheme a left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),LotteryScheme.class,page);
						}
						superlotto(datas);
						return "/lottery/betreportformnewJc/betreportformlotterybettingList";
					}else if("竞彩篮球".equals(gcname)){
						List<BasketballScheme> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
						}
						basketball(datas);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbasketballschemeList";
					}else{
						List<BetBetting> datas=new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("gcname", gcname).setParam("date",date),page,BetBetting.class,betBetting);
							model.addAttribute("virtualmember", 1);
						}else{
							datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("gcname", gcname).setParam("date",date),page,BetBetting.class,betBetting);
						}
						
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingList";
					}
				}else{
					page.setSort("desc");
					page.setOrder("bettingmoney");
					List<Map<String, Object>> datas =new ArrayList<>();
					if("1".equals(request.getParameter("virtualmember"))){
						datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,b.nickname,b.agentid,sum(a.bettingmoney) as bettingmoney from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state!=2 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
						model.addAttribute("virtualmember", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,b.nickname,b.agentid,sum(a.bettingmoney) as bettingmoney from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state!=2 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date).setParam("gcname", gcname),  page);
					}
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute("gcname", gcname);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformmemberbettingList";
				}
			}else{
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("totallybettingmoney");
				page.setPageSize(50);
				page.setSort("desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				List<Map<String, Object>> datas =new ArrayList<>();
				if("1".equals(request.getParameter("virtualmember"))){
					datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state!=2 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
					model.addAttribute("virtualmember", 1);
				}else{
					datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state!=2 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
				}
				returnObject.setQueryBean(betBetting);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("bettingtime", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betreportformnewJc/betreportformgameclassbettingList";
			}
			
		}else if("10".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("a."+page.getOrder());
			String date = request.getParameter("date");
			BetRankMember betRankMember = new BetRankMember();
			List<BetRankMember> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.memberid,b.id2,a.nickname,a.score,a.gamemoney,a.bankmoney,a.freezingscore from bet_rank_member a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.date,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRankMember.class,betRankMember);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformmemberList";
		}else if("99".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("a."+page.getOrder());
			String date = request.getParameter("date");
			BetRankMember betRankMember = new BetRankMember();
			List<BetAgentProxies> datas=betAgentProxiesService.queryForList(new Finder("select * from bet_rank_member WHERE state=2 and (agentid=:agentid or agentparentids like :aid) and date_format(date,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),BetAgentProxies.class);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betAgentProxies/firstbetagentproxiesList";
		}else if("11".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			if(page.getOrder()=="id"){
				page.setOrder("a.id");
			}
			String date = request.getParameter("date");
			BetBetting betBetting = new BetBetting();
			List<BetBetting> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.bettingtime,a.gcname,a.name1,sum(a.bettingmoney) AS bettingmoney from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date GROUP BY a.name1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
			returnObject.setQueryBean(betBetting);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("bettingtime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbettingmoneyList";
		}else if("12".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetDaywinorfailrebate betdaywinorfailrebate = new BetDaywinorfailrebate();
			List<BetDaywinorfailrebate> datas=betDaywinorfailrebateService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.nickname,a.dayscore,a.daybettingmoney,a.rebate,a.receivetime,a.receiveip,a.gamescore,a.bankscore,a.state,a.date,a.agentid,a.agentparentid,a.agentparentids from bet_daywinorfailrebate a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetDaywinorfailrebate.class,betdaywinorfailrebate);
			returnObject.setQueryBean(betdaywinorfailrebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("receivetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformdaywinorfailList";
		}else if("13".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetReliefRecord betReliefRecord = new BetReliefRecord();
			List<BetReliefRecord> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.id,a.memberid,a.memberid2,a.reliefscore,a.date,a.agentid,a.agentparentid,a.agentparentids from bet_relief_record a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.date,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetReliefRecord.class,betReliefRecord);
			returnObject.setQueryBean(betReliefRecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformreliefList";
		}else if("14".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetScorerecord betScorerecord = new BetScorerecord();
			List<BetScorerecord> datas=betScorerecordService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.time,a.content,a.originalscore,a.changescore,a.balance,a.state,a.remark,a.type,a.agentid,a.agentparentid,a.agentparentids from bet_scorerecord a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.time,:format)=:date and type=:state ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("state", 9),page,BetScorerecord.class,betScorerecord);
			returnObject.setQueryBean(betScorerecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("state", 9);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformrankList";
		}else if("15".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetScorerecord betScorerecord = new BetScorerecord();
			List<BetScorerecord> datas=betScorerecordService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.time,a.content,a.originalscore,a.changescore,a.balance,a.state,a.remark,a.type,a.agentid,a.agentparentid,a.agentparentids from bet_scorerecord a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.time,:format)=:date and type=:state ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("state", 3),page,BetScorerecord.class,betScorerecord);
			returnObject.setQueryBean(betScorerecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("state", 3);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformsigninList";
		}else if("16".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetFirstrechargerebate betFirstrechargerebate = new BetFirstrechargerebate();
			List<BetFirstrechargerebate> datas=betFirstrechargerebateService.findListDataByFinder(new Finder("select a.id,a.memberid2,a.nickname,a.recharge,a.bettingmoney,a.rebate,a.receivetime,a.receiveip,a.gamescore,a.bankscore,a.state,a.date,a.agentid,a.agentparentid,a.agentparentids from bet_firstrechargerebate a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetFirstrechargerebate.class,betFirstrechargerebate);
			returnObject.setQueryBean(betFirstrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("receivetime", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformfirstrechargeList";
		}else if("17".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetRechargecard betRechargecard = new BetRechargecard();
			List<BetRechargecard> datas=betRechargecardService.findListDataByFinder(new Finder("select a.id,a.password,a.money,a.state,a.ip,a.time,a.memberid2,a.operator,a.validity,a.rechargetime,a.agentid,a.agentparentid,a.agentparentids,a.exchangeid2 from bet_rechargecard a LEFT JOIN bet_member b ON a.memberid2=b.id2 WHERE b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.time,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRechargecard.class,betRechargecard);
			returnObject.setQueryBean(betRechargecard);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformrechargecardList";
		}else if("18".equals(request.getParameter("k"))){
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="3000-01-01";
			}
			Date date =  new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Double gamewin= betReportformService.queryForObject(new Finder("select sum(winorloss) from bet_agentreportform where agentid=:agentid and date_format(date,:format)=:date ").setParam("agentid", agentid).setParam("format", "%Y-%m").setParam("date", sdf.format(date)), Double.class);
			model.addAttribute("gamewin", gamewin);
			Double welfare = betReportformService.queryForObject(new Finder("select SUM(allwelfare) from bet_agentreportform where agentid=:agentid and date_format(date,:format)=:date ").setParam("agentid", agentid).setParam("format", "%Y-%m").setParam("date", sdf.format(date)), Double.class);
			model.addAttribute("welfare", welfare);
			
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<BetReportform> datas=betReportformService.findListDataByFinder(new Finder("select*from bet_agentreportform where agentid =:agentid and date between :starttime and :endtime ").setParam("agentid", agentid).setParam("starttime",starttime).setParam("endtime",endtime),page,BetReportform.class,betReportform);
			returnObject.setQueryBean(betReportform);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			if(starttime=="0000-01-01"){
				starttime=null;
			}
			if(endtime=="3000-01-01"){
				endtime=null;
			}
			model.addAttribute("startDate", starttime);
			model.addAttribute("endDate", endtime);
			model.addAttribute("p", "1");
			return listurl;
		}else if("19".equals(request.getParameter("k"))){
			//退佣
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			List<BetBetting> datas = betBettingService.queryForList(new Finder("select a.bettingmoney,a.memberty,a.membertytime,b.id2 as memberid2 from bet_betting a left join bet_member b on a.memberid=b.id  where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.membertystate=1 and date_format(a.membertytime,:format)=:date order by b.id2").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), BetBetting.class);
			returnObject.setQueryBean(new BetBetting());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformtymemberList";
		}else if("20".equals(request.getParameter("k"))){
			//总福利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String date = request.getParameter("date");
			List<BetReportform> data =new ArrayList<BetReportform>();
			if(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(date)){
				Double registersend = betRegisterRewardService.queryForObject(new Finder("select sum(reward) from bet_register_reward where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(registersend == null){
					registersend = 0.;
				}
				Double signinreward = betSigninRewardService.queryForObject(new Finder("select sum(reward) from bet_signin_reward where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid)  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(signinreward==null){
					signinreward=0.;
				}
				Double firstrebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where date_format(date,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(firstrebate==null){
					firstrebate=0.;
				}
				Double sumSubordinaterebate = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(a.income) from bet_subordinaterebate_detail a left join bet_member b on a.memberid2=b.id2 where a.state=1 and b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.subtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(sumSubordinaterebate==null){
					sumSubordinaterebate=0.;
				}
				Double payrebate = betSinglerechargeService.queryForObject(new Finder("select sum(rebate) from bet_singlerecharge where state=1 and date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(payrebate == null){
					payrebate = 0.;
				}
				Double todayrebate = betTodayrechargerebateService.queryForObject(new Finder("select sum(reward) from bet_todayrechargerebate where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("date", date),Double.class);
				if(todayrebate == null){
					todayrebate = 0.;
				}
				Double relief = betReliefRecordService.queryForObject(new Finder("select sum(reliefscore) from bet_relief_record where date_format(date,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(relief==null){
					relief=0.;
				}
				Double rankmember = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where state=1 and date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date),Double.class);
				if(rankmember==null){
					rankmember=0.;
				}
				Double weekwinorfail = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(weekwinorfail==null){
					weekwinorfail=0.;
				}
				Double daywinorfail = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate  where date_format(receivetime,:format)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date", date), Double.class);
				if(daywinorfail==null){
					daywinorfail=0.;
				}
				BetReportform reportform = new BetReportform();
				reportform.setRegistersend(registersend);
				reportform.setFirstrecharge(firstrebate);
				reportform.setSubordinaterebate(sumSubordinaterebate);
				reportform.setPayrebate(payrebate);
				reportform.setTodayrechargerebate(todayrebate);
				reportform.setSignin(signinreward.intValue());
				reportform.setRelief(relief.intValue());
				reportform.setRank(rankmember);
				reportform.setWeekwinorfailrebate(weekwinorfail);
				reportform.setDaywinorfailrebate(daywinorfail);
				data.add(reportform);
			}else{
				data = betReportformService.queryForList(new Finder("select * from bet_agentreportform where date=:date and agentid=:agentid  ").setParam("agentid", agentid).setParam("date", date), BetReportform.class);
			}
				returnObject.setData(data);
				model.addAttribute("time", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformallwelfareList";
		}else if("21".equals(request.getParameter("k"))){
			//注册 送
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetRegisterReward betRegisterReward = new BetRegisterReward();
			List<BetRegisterReward> datas=betRegisterRewardService.findListDataByFinder(new Finder("select a.* from bet_register_reward a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetRegisterReward.class,betRegisterReward);
			returnObject.setQueryBean(betRegisterReward);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformRegisterSendList";
		}else if("22".equals(request.getParameter("k"))){
			//单笔充值返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetSinglerecharge betSinglerecharge = new BetSinglerecharge();
			List<BetSinglerecharge> datas=betSinglerechargeService.findListDataByFinder(new Finder("select a.* from bet_singlerecharge a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=1 and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetSinglerecharge.class,betSinglerecharge);
			returnObject.setQueryBean(betSinglerecharge);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformSinglereChargeList";
		}else if("23".equals(request.getParameter("k"))){
			//当日充值返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetTodayrechargerebate betTodayrechargerebate = new BetTodayrechargerebate();
			List<BetTodayrechargerebate> datas=betTodayrechargerebateService.findListDataByFinder(new Finder("select a.* from bet_todayrechargerebate a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=1 and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetTodayrechargerebate.class,betTodayrechargerebate);
			returnObject.setQueryBean(betTodayrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformTodayrechargereList";
		}else if("24".equals(request.getParameter("k"))){
			//周输赢返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			BetWeekwinorfailrebate betWeekwinorfailrebate = new BetWeekwinorfailrebate();
			List<BetWeekwinorfailrebate> datas=betWeekwinorfailrebateService.findListDataByFinder(new Finder("select a.* from bet_weekwinorfailrebate a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) and a.state=1 and date_format(a.receivetime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
			returnObject.setQueryBean(betWeekwinorfailrebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("time", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformWeekwinorfailrebateList";
		}else if("25".equals(request.getParameter("k"))){
			//转账分
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("a.id");
			String date = request.getParameter("date");
			BetTransferAccounts betTransferAccounts = new BetTransferAccounts();
			List<BetTransferAccounts> datas=new ArrayList<BetTransferAccounts>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betTransferAccountsService.findListDataByFinder(new Finder("SELECT SUM(a.transferaccountsscore) as transferaccountsscore,a.transferman,a.time,a.transfermanid from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.time,:parsetime)=:date GROUP BY a.transferman ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("parsetime", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betTransferAccountsService.findListDataByFinder(new Finder("SELECT SUM(a.transferaccountsscore) as transferaccountsscore,a.transferman,a.time,a.transfermanid from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.time,:parsetime)=:date GROUP BY a.transferman ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("parsetime", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
			}
			returnObject.setQueryBean(betTransferAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbetTransferAccounts";
		}else if("101".equals(request.getParameter("k"))){
			//代理转账分
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("a.id");
			String date = request.getParameter("date");
			BetTransferAccounts betTransferAccounts = new BetTransferAccounts();
			List<BetTransferAccounts> datas=new ArrayList<BetTransferAccounts>();
			datas=betTransferAccountsService.findListDataByFinder(new Finder("SELECT SUM(a.transferaccountsscore) as transferaccountsscore,a.transferman,a.time,a.transfermanid from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and a.agentid=:agentid and date_format(a.time,:parsetime)=:date GROUP BY a.transferman ").setParam("agentid", agentid).setParam("parsetime", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
			returnObject.setQueryBean(betTransferAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbetTransferAccounts0";
		}else if("102".equals(request.getParameter("k"))){
			//下属代理转账分
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("a.id");
			String date = request.getParameter("date");
			BetTransferAccounts betTransferAccounts = new BetTransferAccounts();
			List<BetTransferAccounts> datas=new ArrayList<BetTransferAccounts>();
			datas=betTransferAccountsService.findListDataByFinder(new Finder("SELECT SUM(a.transferaccountsscore) as transferaccountsscore,a.transferman,a.time,a.transfermanid from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentparentids like :aid) and date_format(a.time,:parsetime)=:date GROUP BY a.transferman ").setParam("aid", "%,"+agentid+",%").setParam("parsetime", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
			returnObject.setQueryBean(betTransferAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbetTransferAccounts";
		}else if("125".equals(request.getParameter("k"))){
			//转账分
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String date = request.getParameter("date");
			String transfermanid = request.getParameter("transfermanid");
			BetTransferAccounts betTransferAccounts = new BetTransferAccounts();
			List<BetTransferAccounts> datas=new ArrayList<BetTransferAccounts>();
			if("1".equals(request.getParameter("virtualmember"))){
				datas=betTransferAccountsService.findListDataByFinder(new Finder("select a.* from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where a.transfermanid=:transfermanid and b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.time,:format)=:date ").setParam("transfermanid", transfermanid).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
				model.addAttribute("virtualmember", 1);
			}else{
				datas=betTransferAccountsService.findListDataByFinder(new Finder("select a.* from bet_transfer_accounts a LEFT JOIN bet_member b on a.memberid2=b.id2 where a.transfermanid=:transfermanid and b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.time,:format)=:date ").setParam("transfermanid", transfermanid).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetTransferAccounts.class,betTransferAccounts);
				model.addAttribute("virtualmember", 0);
			}
			model.addAttribute("transfermanid", transfermanid);
			returnObject.setQueryBean(betTransferAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformbetTransferAccountsList";
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}else if("26".equals(request.getParameter("k"))){
			String gcname = request.getParameter("gcname");
			String memberid2 = request.getParameter("memberid2");
			if(StringUtils.isNoneEmpty(gcname)){
				gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request,"bettingtime","desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				if(StringUtils.isNoneEmpty(memberid2)){
					if("竞彩足球".equals(gcname)){
						List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where (c.agentid=:agentid or c.agentparentids like :aid) and date_format(a.settlementtime,:format)=:date and a.memberid2=:memberid2 ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("date",date),SoccerScheme.class,page);
						soccer(datas);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformsoccerbettingListxx";
					}else if("北京单场".equals(gcname)){
						gcbjdc(model, agentid, gcname, memberid2, returnObject, page, date, betBetting);
						return "/lottery/betreportformnewJc/betreportformbjdcbettingListxx";
					}else if("竞彩篮球".equals(gcname)){
						List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  (c.agentid=:agentid or c.agentparentids like :aid) and a.memberid2 = :memberid2  and date_format(a.settlementtime,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("date", date),BasketballScheme.class,page);
						basketball(datas);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbasketballschemeListsettle";
					}else{
						List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute("memberid2", memberid2);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformbettingList";
					}
				}else{
					page.setSort("desc");
					page.setOrder("bettingscore");
					List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from(select a.memberid2,b.agentid,b.nickname,sum(a.bettingmoney) as bettingmoney,sum(a.bettingscore-a.bettingmoney) as bettingscore from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where (b.agentid=:agentid or b.agentparentids like :aid) and substr(a.settlementtime,1,10)=:date and a.gcname=:gcname and a.state!=2 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", date).setParam("gcname", gcname),  page);
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute("gcname", gcname);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformmemberbettingListxx";
				}
			}else{
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setOrder("result");
				page.setPageSize(50);
				page.setSort("desc");
				String date = request.getParameter("date");
				BetBetting betBetting = new BetBetting();
				List<Map<String, Object>> datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname,sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and (b.agentid=:agentid or b.agentparentids like :aid) AND a.state!=2 and date_format(a.settlementtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("date",date), page);
				returnObject.setQueryBean(betBetting);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("bettingtime", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betreportformnewJc/betreportformgameclassbettingListxx";
			}
		}else if("28".equals(request.getParameter("k"))){
			//游戏扣税
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("gks");
			page.setSort("desc");
			String date = request.getParameter("date");
			BetRankMember betRankMember = new BetRankMember();
			List<Map<String, Object>> datas = betBettingService.queryForList(new Finder("select gcname, sum(a.gks) as gks from bet_betting a left join bet_member b on a.memberid=b.id where (b.agentid=:agentid or b.agentparentids like :aid) and settlementtime>=:date group by gcname HAVING sum(a.gks)>0 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", new SimpleDateFormat("yyyy-MM-dd").format(date)), page);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("date", date);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformnewJc/betreportformmemberListgks";
		}else if("29".equals(request.getParameter("k"))){
				//未结算
				String gcname = request.getParameter("gcname");
				String memberid2 = request.getParameter("memberid2");
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request,"bettingtime","desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					if(StringUtils.isNoneEmpty(memberid2)){
						if("竞彩足球".equals(gcname)){
							List<SoccerScheme> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("date",date),SoccerScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("date",date),SoccerScheme.class,page);
							}
							soccer(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformsoccerbettingListuntreated";
						}else if("北京单场".equals(gcname)){
							List<BjdcScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
							}
							corebjdc(datas);
							return "/lottery/betreportformnewJc/betreportformbjdcbettingListuntreated";
						}else if("竞彩篮球".equals(gcname)){
							List<BasketballScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
							}
							basketball(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbasketballschemeListuntreated";
						}else{
							List<BetBetting> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
								model.addAttribute("virtualmember", 1);
							}else{
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
							}
							
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbettingListuntreated";
						}
					}else{
						page.setSort("desc");
						page.setOrder("bettingmoney");
						List<Map<String, Object>> datas = new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=0 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("date", date).setParam("gcname", gcname),  page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=0 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("date", date).setParam("gcname", gcname),  page);
						}
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformmemberbettingListuntreated";
					}
				}else{
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request);
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas=new ArrayList<>();
					if("1".equals(request.getParameter("virtualmember"))){
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=0 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
						model.addAttribute("virtualmember", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore-a.bettingmoney) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=0 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), page);
					}
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbettingListuntreated";
				}
			}else if("30".equals(request.getParameter("k"))){
				//已结算
				String gcname = request.getParameter("gcname");
				String memberid2 = request.getParameter("memberid2");
				if(StringUtils.isNoneEmpty(gcname)){
					gcname=new String(gcname.getBytes("iso-8859-1"),"utf-8");
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request,"bettingtime","desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					if(StringUtils.isNoneEmpty(memberid2)){
						if("竞彩足球".equals(gcname)){
							List<SoccerScheme> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("memberid2", memberid2).setParam("date",date),SoccerScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2=c.id2 where date_format(a.bettingtime,:format)=:date and a.memberid2=:memberid2 and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("memberid2", memberid2).setParam("date",date),SoccerScheme.class,page);
							}
							soccer(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformsoccerbettingListtreated";
						}else if("北京单场".equals(gcname)){
							List<BjdcScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=0 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BjdcScheme.class,page);
							}
							corebjdc(datas);
							return "/lottery/betreportformnewJc/betreportformbjdcbettingListtreated";
						}else if("竞彩篮球".equals(gcname)){
							List<BasketballScheme> datas =new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
								model.addAttribute("virtualmember", 1);
							}else{
								datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  a.memberid2 = :memberid2  and date_format(a.bettingtime,:format)=:date and a.situation=1 and c.isinternal=0 and c.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("format", "%Y-%m-%d").setParam("date", date),BasketballScheme.class,page);
							}
							basketball(datas);
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbasketballschemeListtreated";
						}else{
							List<BetBetting> datas=new ArrayList<>();
							if("1".equals(request.getParameter("virtualmember"))){
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
								model.addAttribute("virtualmember", 1);
							}else{
								datas=betBettingService.findListDataByFinder(new Finder("select c.*,d.name2 from (select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and b.id2=:memberid2 and a.state=0) c left join bet_gameplay d on c.gameplayid =d.id ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("gcname", gcname).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetBetting.class,betBetting);
							}
							returnObject.setQueryBean(betBetting);
							returnObject.setPage(page);
							returnObject.setData(datas);
							model.addAttribute("bettingtime", date);
							model.addAttribute("gcname", gcname);
							model.addAttribute("memberid2", memberid2);
							model.addAttribute(GlobalStatic.returnDatas, returnObject);
							return "/lottery/betreportformnewJc/betreportformbettingListtreated";
						}
					}else{
						page.setSort("desc");
						page.setOrder("bettingmoney");
						List<Map<String, Object>> datas =new ArrayList<>();
						if("1".equals(request.getParameter("virtualmember"))){
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,sum(a.bettingscore) as bettingscore,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=1 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("format", "%Y-%m-%d").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", date).setParam("gcname", gcname),  page);
							model.addAttribute("virtualmember", 1);
						}else{
							datas = soccerAllbettingService.queryForList(new Finder("select c.*,d.account as agentaccount,d.nickname as agentnickname from (select a.memberid2,sum(a.bettingmoney) as bettingmoney,sum(a.bettingscore) as bettingscore,b.nickname as membernickname,b.agentid from soccer_allbetting a  left join bet_member b on a.memberid2 = b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and date_format(a.bettingtime,:format)=:date and a.gcname=:gcname and a.state=1 group by a.memberid2) c left join bet_agent d on c.agentid=d.agentid  ").setParam("format", "%Y-%m-%d").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", date).setParam("gcname", gcname),  page);
						}
						returnObject.setQueryBean(betBetting);
						returnObject.setPage(page);
						returnObject.setData(datas);
						model.addAttribute("bettingtime", date);
						model.addAttribute("gcname", gcname);
						model.addAttribute(GlobalStatic.returnDatas, returnObject);
						return "/lottery/betreportformnewJc/betreportformmemberbettingListtreated";
					}
				}else{
					ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
					Page page = newPage(request);
					page.setOrder("totallybettingmoney");
					page.setPageSize(50);
					page.setSort("desc");
					String date = request.getParameter("date");
					BetBetting betBetting = new BetBetting();
					List<Map<String, Object>> datas =new ArrayList<>();
					if("1".equals(request.getParameter("virtualmember"))){
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=1 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("date",date), page);
						model.addAttribute("virtualmember", 1);
					}else{
						datas = soccerAllbettingService.queryForList(new Finder("select  a.gcname, sum(a.bettingmoney) as totallybettingmoney,sum(a.bettingscore) as result from soccer_allbetting a LEFT JOIN bet_member b ON a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=1 and date_format(a.bettingtime,:format)=:date group by gcname ").setParam("aid", "%,"+agentid+",%").setParam("format", "%Y-%m-%d").setParam("agentid", agentid).setParam("date",date), page);
					}
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("bettingtime", date);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betreportformnewJc/betreportformgameclassbettingListtreated";
				}
			}else if("31".equals(request.getParameter("k"))){
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				// ==执行分页查询
				String date = request.getParameter("date");
				BetAgentwithdraw betMember = new BetAgentwithdraw();
				List<BetAgentwithdraw> datas=betAgentwithdrawService.findListDataByFinder(new Finder("select * from bet_agentwithdraw where (agentid=:agentid or agentparentids like :aid) and date_format(audittime,:format)=:date and state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date),page,BetAgentwithdraw.class,betMember );
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas);
				
				model.addAttribute("date", date);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betreportformnewJc/betreportformagentwithdrawList";
			
			}else if("32".equals(request.getParameter("k"))){
				//游戏分库存用户
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				String date = request.getParameter("date");
				String order = request.getParameter("order");
				Calendar cal = Calendar.getInstance();
    			String today = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    			List<BetMember> datas = null;
    			if(today.equals(date)){
    				if("1".equals(order)){
    					page.setOrder("gamescore");
    				}else if("2".equals(order)){
    					page.setOrder("bankscore");
    				}else if("3".equals(order)){
    					page.setOrder("freezingscore");
    				}
    				
    				datas=betMemberService.queryForList(new Finder("select * from bet_member  where isinternal=0 and isissue=1 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", agentid).setParam("agentparentids", "%"+parentids+"%"),BetMember.class,page);
    				returnObject.setData(datas);
    				returnObject.setPage(page);
    				returnObject.setQueryBean(new BetMember());
    				model.addAttribute(GlobalStatic.returnDatas, returnObject);
    				model.addAttribute("date", date);
    				model.addAttribute("order", order);
    				return "/lottery/betreportformnewJc/gamescorememberList";
    			}else{
    				if("1".equals(order)){
    					page.setOrder("a.gamemoney");
    				}else if("2".equals(order)){
    					page.setOrder("a.bankmoney");
    				}else if("3".equals(order)){
    					page.setOrder("a.freezingscore");
    				}
    				
    				List<BetRankMember> rankdatas=betWithdrawcashService.queryForList(new Finder("select a.memberid,b.id2,a.nickname,a.score,a.gamemoney,a.bankmoney,a.freezingscore from bet_rank_member a LEFT JOIN bet_member b ON a.memberid=b.id WHERE b.isinternal=0 and b.isissue=1 and (b.agentid=:agentid or b.agentparentids like :aid) and date_format(a.date,:format)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("format", "%Y-%m-%d").setParam("date",date), BetRankMember.class, page);
    				returnObject.setData(rankdatas);
    				returnObject.setPage(page);
    				returnObject.setQueryBean(new BetRankMember());
    				model.addAttribute(GlobalStatic.returnDatas, returnObject);
    				model.addAttribute("date", date);
    				model.addAttribute("order", order);
        			return "/lottery/betreportformnewJc/gamescorememberList2";
    			}
			}else if("33".equals(request.getParameter("k"))){
				SoccerAllbetting allbetting = new SoccerAllbetting();
				String pagentid = SessionUser.getShiroUser().getAgentid();
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				page.setPageSize(10);
				String date = request.getParameter("date");
				String type = request.getParameter("type");
				String isissue = request.getParameter("isissue");
				allbetting.setType(Integer.valueOf(type));
				if("1".equals(type)){
					List<SoccerScheme> datas = null;
					datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where  date_format(a.settlementtime,:format)=:date  and a.brokerageagentid = :agentid  and a.brokerageagentmoney < 0 and c.isissue=:isissue").setParam("isissue", isissue).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("agentid", pagentid),SoccerScheme.class,page);
					returnObject.setData(datas);
				}else if("3".equals(type)){
					List<BasketballScheme> datas = null;
					datas= basketballSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from basketball_scheme a left join bet_member c on c.id2=a.memberid2 where  date_format(a.settlementtime,:format)=:date  and a.brokerageagentid = :agentid  and a.brokerageagentmoney < 0 and c.isissue=:isissue").setParam("isissue", isissue).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("agentid", pagentid),BasketballScheme.class,page);
					returnObject.setData(datas);
				}else if("4".equals(type)){
					List<BjdcScheme> datas = null;
					datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from bjdc_scheme a left join bet_member c on c.id2=a.memberid2 where  date_format(a.settlementtime,:format)=:date  and a.brokerageagentid = :agentid  and a.brokerageagentmoney < 0 and c.isissue=:isissue").setParam("isissue", isissue).setParam("format", "%Y-%m-%d").setParam("date",date).setParam("agentid", pagentid),BjdcScheme.class,page);
					returnObject.setData(datas);
				}
				returnObject.setQueryBean(allbetting);
				returnObject.setPage(page);
				model.addAttribute("date", date);
				model.addAttribute("type", type);
				model.addAttribute("isissue", isissue);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/soccerallbetting/brokerageagentlist";
			}else if("66".equals(request.getParameter("k"))){
				//本周
				SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal11 = Calendar.getInstance();
				cal11.add(Calendar.DATE, -1);
				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				Date startDate = cal.getTime();
				model.addAttribute("startDate", sdfdate.format(startDate));
				Calendar currentDate = new GregorianCalendar();   
				currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
				currentDate.set(Calendar.HOUR_OF_DAY, 23);  
				currentDate.set(Calendar.MINUTE, 59);  
				currentDate.set(Calendar.SECOND, 59);  
				currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
				model.addAttribute("endDate", sdfdate.format(currentDate.getTime()));
				ReturnDatas returnObject = listjson(request, model, betReportform);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				model.addAttribute("p", "0");
				model.addAttribute("show", 0);
				model.addAttribute("agentparentid", betagent.getParentid());
				return "/lottery/betreportformnewJc/betreportformListv66";
			}else{
				//本周
				SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal11 = Calendar.getInstance();
				cal11.add(Calendar.DATE, -1);
				Calendar cal = Calendar.getInstance();
				cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				Date startDate = cal.getTime();
				model.addAttribute("startDate", sdfdate.format(startDate));
				Calendar currentDate = new GregorianCalendar();   
				currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
				currentDate.set(Calendar.HOUR_OF_DAY, 23);  
				currentDate.set(Calendar.MINUTE, 59);  
				currentDate.set(Calendar.SECOND, 59);  
				currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
				model.addAttribute("endDate", sdfdate.format(currentDate.getTime()));
				ReturnDatas returnObject = listjson(request, model, betReportform);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				model.addAttribute("p", "0");
				model.addAttribute("show", 0);
				model.addAttribute("agentparentid", betagent.getParentid());
				String job4start = dicDataService.queryForObject(new Finder("select remark from  t_dic_data where code=:code").setParam("code", "agentjob4"),String.class);
				String job4end = dicDataService.queryForObject(new Finder("select value from  t_dic_data where code=:code").setParam("code", "agentjob4"),String.class);
				model.addAttribute("job4start", job4start);
				model.addAttribute("job4end", job4end);				
				String recalculatestate = dicDataService.queryForObject(new Finder("select value from  t_dic_data  where code = :code").setParam("code", "recalculatestate"),String.class);
				model.addAttribute("recalculatestate", recalculatestate);
				return listurl;
			}
	}
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetAgentreportformNewJc betAgentreportformNewJc) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		File file = betAgentreportformNewJcService.findDataExportExcel(null,listurl, page,BetAgentreportformJc.class,betAgentreportformNewJc);
		String fileName="betAgentreportformNewJc"+GlobalStatic.excelext;
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
		return "/lottery/betagentreportformjc/betagentreportformjcLook";
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
		  BetAgentreportformNewJc betAgentreportformNewJc = betAgentreportformNewJcService.findBetAgentreportformNewJcById(id);
		   returnObject.setData(betAgentreportformNewJc);
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
	ReturnDatas saveorupdate(Model model,BetAgentreportformNewJc betAgentreportformnewJc,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				if(StringUtils.isBlank(starttime)||StringUtils.isBlank(endtime)){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("请选择重算时间");
					return returnObject;
				}
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = outputFormat.parse(starttime);
				Date endDate = outputFormat.parse(endtime);
				Integer day=(int) ((endDate.getTime()-startDate.getTime())/(24*60*60*1000));    
				if(day<0){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("重算时间有误");
					return returnObject;
				}
				dicDataService.update(new Finder("update t_dic_data set remark = :remark,value = :value where code = :code").setParam("remark", endtime).setParam("value", day+2).setParam("code", "recalculatejob"));
				
				 Scheduler sched = schedulerFactoryBean.getScheduler();
	             
				 JobDetail job = JobBuilder.newJob(QuartzJob101.class).withIdentity("job101", Scheduler.DEFAULT_GROUP).build();  
				 
				 Trigger trigger = TriggerBuilder.newTrigger()  
				            .withIdentity("trigger+" +
				            		"", Scheduler.DEFAULT_GROUP) 
				            .startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND)) 
				            .usingJobData("name", "quartz")
				            .build();  
	              
				 if(sched.checkExists(job.getKey())){
					 sched.deleteJob(job.getKey());					
				 }
				 sched.scheduleJob(job,trigger);  
	             if(sched.isShutdown()){
	            	 sched.start();  
	             }
	             sched.resumeJob(job.getKey());
	              
	              
	              returnObject.setMessage("job将在5秒后开始运行");
			}
		
			//betAgentreportformJcService.saveorupdate(betAgentreportformJc);
			
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
		return "/lottery/betagentreportformnewjc/betagentreportformnewjcCru";
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
			 betAgentreportformNewJcService.deleteById(id,BetAgentreportformNewJc.class);
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
			betAgentreportformNewJcService.deleteByIds(ids,BetAgentreportformNewJc.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	private void bjdc(List<SoccerAllbetting> datas) throws Exception {
		//北京单场
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (SoccerAllbetting allBetting : datas) {
				if(4==allBetting.getType()){
					String schemeid = allBetting.getId();
					if(schemeid!=null){
						schemeids.add(allBetting.getId());
					}
				}
			}
			List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
				if(resultMap!=null){
					for (Map<String, Object> m : resultMap){
						String oddsname  = m.get("oddsname").toString();
					    if("rqwin".equals(oddsname)||"rqflat".equals(oddsname)||"rqlose".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("bdsfp_"+m.get("fid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		BjdcOdds readValue = mmmm.readValue(cached2, BjdcOdds.class);
						    		String letpoints = readValue.getLetpoints();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}else{
						    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ letpoints+")");
							}
					    }
					}
				}
				for(BjdcSchemeMatch schemeMatch : matchDatas){
					String schemeid = schemeMatch.getSchemeid();
					String fid = schemeMatch.getFid();
					List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : resultMap){
						if(schemeid.equals((String)m.get("schemeid"))&&fid.equals((String)m.get("fid"))){
							mmm.add(m);
						}
					}
					schemeMatch.setResultMap(mmm);
					schemeMatch.setNum(schemeMatch.getNum());
				}
			}
			
			for(SoccerAllbetting allBetting : datas){
				List<BjdcSchemeMatch> sss=new ArrayList<BjdcSchemeMatch>();
				if(matchDatas!=null){
					for(BjdcSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(allBetting.getId())){
							sss.add(schemeMatch);
						}
					}
				}
				allBetting.setBjdcschemecontent(sss);
			}
		}
	}	
	
	
	private void corebjdc(List<BjdcScheme> datas) throws Exception {
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (BjdcScheme soccerScheme2 : datas) {
				String schemeid = soccerScheme2.getSchemeid();
				if(schemeid!=null){
					schemeids.add(soccerScheme2.getSchemeid());
				}
			}
			List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
				if(resultMap!=null){
					for (Map<String, Object> m : resultMap){
						String oddsname  = m.get("oddsname").toString();
					    if("rqwin".equals(oddsname)||"rqflat".equals(oddsname)||"rqlose".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("bdsfp_"+m.get("fid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		BjdcOdds readValue = mmmm.readValue(cached2, BjdcOdds.class);
						    		String letpoints = readValue.getLetpoints();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}else{
						    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ letpoints+")");
							}
					    }
					}
				}
				for(BjdcSchemeMatch schemeMatch : matchDatas){
					String schemeid = schemeMatch.getSchemeid();
					String fid = schemeMatch.getFid();
					List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : resultMap){
						if(schemeid.equals((String)m.get("schemeid"))&&fid.equals((String)m.get("fid"))){
							mmm.add(m);
						}
					}
					schemeMatch.setResultMap(mmm);
					schemeMatch.setNum(schemeMatch.getNum());
				}
			}
			
			for(BjdcScheme scheme : datas){
				List<BjdcSchemeMatch> sss=new ArrayList<BjdcSchemeMatch>();
				if(matchDatas!=null){
					for(BjdcSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(scheme.getSchemeid())){
							sss.add(schemeMatch);
						}
					}
				}
				scheme.setSchemecontent(sss);
			}
		}
	}

	private void gcbjdc(Model model, String agentid, String gcname,
			String memberid2, ReturnDatas returnObject, Page page, String date,
			BetBetting betBetting) throws Exception {
		List<BjdcScheme> datas= bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod, c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playid = b.id left join bet_member c on a.memberid2=c.id2 where substr(a.bettingtime,1,10)=:date and a.memberid2=:memberid2 and a.situation=0 and c.isinternal=0 and (c.agentid=:agentid or c.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("memberid2", memberid2).setParam("date",date),BjdcScheme.class,page);
		corebjdc(datas);
		
		returnObject.setQueryBean(betBetting);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("bettingtime", date);
		model.addAttribute("gcname", gcname);
		model.addAttribute("memberid2", memberid2);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
	}
	
	private void soccerAll(List<SoccerAllbetting> datas) throws Exception {
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (SoccerAllbetting allBetting : datas) {
				if(1==allBetting.getType()){
					String schemeid = allBetting.getId();
					if(schemeid!=null){
						schemeids.add(allBetting.getId());
					}
				}
			}
			List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
				if(resultMap!=null){
					for (Map<String, Object> m : resultMap){
						String oddsname  = m.get("oddsname").toString();
						String oddsrealname = "";
						
						if("left_odds".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("2x1_"+m.get("mid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
									SoccerLeague2choose1odds readValue = mmmm.readValue(cached2, SoccerLeague2choose1odds.class);
									oddsrealname =readValue.getLeft_name();
										m.put("oddsrealname", oddsrealname);
						    	}else{
						    		oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
							    	 m.put("oddsrealname", oddsrealname);
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
						    	 m.put("oddsrealname", oddsrealname);
							}
					    	
					    }else if("right_odds".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("2x1_"+m.get("mid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
									SoccerLeague2choose1odds readValue = mmmm.readValue(cached2, SoccerLeague2choose1odds.class);
									oddsrealname =readValue.getRight_name();
										m.put("oddsrealname", oddsrealname);
						    	}else{
						    		oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
							    	 m.put("oddsrealname", oddsrealname);
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
						    	 m.put("oddsrealname", oddsrealname);
							}
					    }
					    if("rqwin".equals(oddsname)||"rqflat".equals(oddsname)||"rqlose".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("rqsfp_"+m.get("mid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		SoccerLeagueOdds readValue = mmmm.readValue(cached2, SoccerLeagueOdds.class);
						    		String letpoints = readValue.getLetpoints();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}else{
						    		String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", m.get("mid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", m.get("mid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ letpoints+")");
							}
					    }
					}
				}
				for(SoccerSchemeMatch schemeMatch : matchDatas){
					String schemeid = schemeMatch.getSchemeid();
					String mid = schemeMatch.getMid();
					List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : resultMap){
						if(schemeid.equals((String)m.get("schemeid"))&&mid.equals((String)m.get("mid"))){
							mmm.add(m);
						}
					}
					schemeMatch.setResultMap(mmm);
					schemeMatch.setNum(WeekOfDate.getWeekOfDate(schemeMatch.getEndtime())+schemeMatch.getNum());
				}
			}
			
			for(SoccerAllbetting allBetting : datas){
				List<SoccerSchemeMatch> sss=new ArrayList<SoccerSchemeMatch>();
				if(matchDatas!=null){
					for(SoccerSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(allBetting.getId())){
							sss.add(schemeMatch);
						}
					}
				}
				allBetting.setSchemecontent(sss);
			}
		}
	}
	
	private void soccer(List<SoccerScheme> datas) throws Exception {
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (SoccerScheme soccerScheme2 : datas) {
				String schemeid = soccerScheme2.getSchemeid();
				if(schemeid!=null){
					schemeids.add(soccerScheme2.getSchemeid());
				}
			}
				
			List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where  c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
				if(resultMap!=null){
					for (Map<String, Object> m : resultMap){
						String oddsname  = m.get("oddsname").toString();
						String oddsrealname = "";
					    if("left_odds".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("2x1_"+m.get("mid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
									SoccerLeague2choose1odds readValue = mmmm.readValue(cached2, SoccerLeague2choose1odds.class);
									oddsrealname =readValue.getLeft_name();
										m.put("oddsrealname", oddsrealname);
						    	}else{
						    		oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
							    	 m.put("oddsrealname", oddsrealname);
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
						    	 m.put("oddsrealname", oddsrealname);
							}
					    }else if("right_odds".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("2x1_"+m.get("mid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
									SoccerLeague2choose1odds readValue = mmmm.readValue(cached2, SoccerLeague2choose1odds.class);
									oddsrealname =readValue.getRight_name();
										m.put("oddsrealname", oddsrealname);
						    	}else{
						    		oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
							    	 m.put("oddsrealname", oddsrealname);
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",m.get("mid").toString()),String.class);
						    	 m.put("oddsrealname", oddsrealname);
							}
					    }
					    if("rqwin".equals(oddsname)||"rqflat".equals(oddsname)||"rqlose".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("rqsfp_"+m.get("mid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		SoccerLeagueOdds readValue = mmmm.readValue(cached2, SoccerLeagueOdds.class);
						    		String letpoints = readValue.getLetpoints();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}else{
						    		String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", m.get("mid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", m.get("mid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ letpoints+")");
							}
					    }
					}
				}
				for(SoccerSchemeMatch schemeMatch : matchDatas){
					
					String schemeid = schemeMatch.getSchemeid();
					String mid = schemeMatch.getMid();
					List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : resultMap){
						if(schemeid.equals((String)m.get("schemeid"))&&mid.equals((String)m.get("mid"))){
							mmm.add(m);
						}
					}
					schemeMatch.setResultMap(mmm);
					schemeMatch.setNum(WeekOfDate.getWeekOfDate(schemeMatch.getEndtime())+schemeMatch.getNum());
				}
			}
			for(SoccerScheme scheme : datas){
				List<SoccerSchemeMatch> sss=new ArrayList<SoccerSchemeMatch>();
				if(matchDatas!=null){
					for(SoccerSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(scheme.getSchemeid())){
							sss.add(schemeMatch);
						}
					}
				}
				scheme.setSchemecontent(sss);
			}
		}
	}
	
	
	private void basketballAll(List<SoccerAllbetting> datas) throws Exception {
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (SoccerAllbetting allBetting : datas) {
				if(3==allBetting.getType()){
					String schemeid = allBetting.getId();
					if(schemeid!=null){
						schemeids.add(allBetting.getId());
					}
				}
			}
			
			List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
				if(resultMap!=null){
					for (Map<String, Object> m : resultMap){
						String oddsname  = m.get("oddsname").toString();
					    
						if("big".equals(oddsname)||"small".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("basketballOdds_"+m.get("zid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		BasketballLeagueOdds readValue = mmmm.readValue(cached2, BasketballLeagueOdds.class);
						    		String dxf = readValue.getDxf().toString();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ dxf+")");
						    	}else{
						    		String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ dxf+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ dxf+")");
							}
					    }
						
					    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("basketballOdds_"+m.get("zid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		BasketballLeagueOdds readValue = mmmm.readValue(cached2, BasketballLeagueOdds.class);
						    		String letpoints = readValue.getLetpoints().toString();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}else{
						    		String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ letpoints+")");
							}
					    }
					}
				}
				for(BasketballSchemeMatch basketballMatch : matchDatas){
					String schemeid = basketballMatch.getSchemeid();
					String zid = basketballMatch.getZid();
					List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : resultMap){
						if(schemeid.equals((String)m.get("schemeid"))&&zid.equals((String)m.get("zid"))){
							mmm.add(m);
						}
					}
					basketballMatch.setResultMap(mmm);
					basketballMatch.setNum(basketballWeekOfDate.getWeekOfDate(basketballMatch.getMatchdate())+basketballMatch.getNum());
				}
			}
			
			for(SoccerAllbetting allBetting : datas){
				List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
				if(matchDatas!=null){
					for(BasketballSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(allBetting.getId())){
							sss.add(schemeMatch);
						}
					}
				}
				allBetting.setBasketballschemecontent(sss);
			}
			
		}
	}
	
	
	private void basketball(List<BasketballScheme> datas) throws Exception {
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (BasketballScheme basketballScheme2 : datas) {
				String schemeid = basketballScheme2.getSchemeid();
				if(schemeid!=null){
					schemeids.add(basketballScheme2.getSchemeid());
				}
			}
			List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
				if(resultMap!=null){
					for (Map<String, Object> m : resultMap){
						String oddsname  = m.get("oddsname").toString();
					    
						if("big".equals(oddsname)||"small".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("basketballOdds_"+m.get("zid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		BasketballLeagueOdds readValue = mmmm.readValue(cached2, BasketballLeagueOdds.class);
						    		String dxf = readValue.getDxf().toString();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ dxf+")");
						    	}else{
						    		String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ dxf+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ dxf+")");
							}
					    }
					    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
					    	try{
					    		String cached2 = (String)cached.getCached(("basketballOdds_"+m.get("zid").toString()).getBytes());
						    	if(cached2!=null){
						    		ObjectMapper mmmm=new ObjectMapper();
						    		BasketballLeagueOdds readValue = mmmm.readValue(cached2, BasketballLeagueOdds.class);
						    		String letpoints = readValue.getLetpoints().toString();
						    		String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}else{
						    		String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
						    	String betname = m.get("betname").toString();
						    	m.put("betname",betname+"("+ letpoints+")");
							}
					    }
					}
				}
				for(BasketballSchemeMatch basketballMatch : matchDatas){
					String schemeid = basketballMatch.getSchemeid();
					String zid = basketballMatch.getZid();
					List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
					for (Map<String, Object> m : resultMap){
						if(schemeid.equals((String)m.get("schemeid"))&&zid.equals((String)m.get("zid"))){
							mmm.add(m);
						}
					}
					basketballMatch.setResultMap(mmm);
					basketballMatch.setNum(basketballWeekOfDate.getWeekOfDate(basketballMatch.getMatchdate())+basketballMatch.getNum());
				}
			}
			for(BasketballScheme scheme : datas){
				List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
				if(matchDatas!=null){
					for(BasketballSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(scheme.getSchemeid())){
							sss.add(schemeMatch);
						}
					}
				}
				scheme.setSchemecontent(sss);
			}
		}
	}

	private void superlotto(List<LotteryScheme> datas) throws Exception {
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			schemeids.add("");
			for (LotteryScheme lotteryScheme2 : datas) {
				String schemeid = lotteryScheme2.getSchemeid();
				if(schemeid!=null){
					schemeids.add(lotteryScheme2.getSchemeid());
				}
			}
			List<LotteryOrder> orderDatas=null;
			if(!schemeids.isEmpty()){
				orderDatas= lotteryOrderService.queryForList(new Finder("select a.*,b.name as playmethod from lottery_order a LEFT JOIN lottery_playmethod b on a.playtype = b.id where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), LotteryOrder.class);
			}
			for(LotteryScheme scheme : datas){
				List<LotteryOrder> sss=new ArrayList<LotteryOrder>();
				if(orderDatas!=null){
					for(LotteryOrder schemeMatch : orderDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(scheme.getSchemeid())){
							sss.add(schemeMatch);
						}
					}
				}
				scheme.setSchemecontent(sss);
			}
		}
	}
}
