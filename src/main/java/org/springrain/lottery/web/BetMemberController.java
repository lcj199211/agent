package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.shiro.ShiroUser;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.entity.BasketballSchemeMatch;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetBetting;
import org.springrain.lottery.entity.BetFirstrechargerebate;
import org.springrain.lottery.entity.BetGameclass;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.entity.BetLimitusername;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetMemberOplog;
import org.springrain.lottery.entity.BetMemberbankcard;
import org.springrain.lottery.entity.BetMemberloginlog;
import org.springrain.lottery.entity.BetRankMember;
import org.springrain.lottery.entity.BetRedenvelope;
import org.springrain.lottery.entity.BetRedenvelopeRecord;
import org.springrain.lottery.entity.BetRegisterReward;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetSinglerecharge;
import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.lottery.entity.BetSubordinaterebateDetail;
import org.springrain.lottery.entity.BetTodayrechargerebate;
import org.springrain.lottery.entity.BetTransferAccounts;
import org.springrain.lottery.entity.BetVip;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.lottery.entity.BjdcSchemeMatch;
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.entity.SubordinateDto;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.service.IBasketballSchemeMatchService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetDaywinorfailrebateService;
import org.springrain.lottery.service.IBetFirstrechargerebateService;
import org.springrain.lottery.service.IBetGameclassService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetKeyvalueService;
import org.springrain.lottery.service.IBetLimitusernameService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetMemberbankcardService;
import org.springrain.lottery.service.IBetMemberloginlogService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetPaymentInterfaceService;
import org.springrain.lottery.service.IBetRankMemberService;
import org.springrain.lottery.service.IBetRedenvelopeRecordService;
import org.springrain.lottery.service.IBetRedenvelopeService;
import org.springrain.lottery.service.IBetRegisterRewardService;
import org.springrain.lottery.service.IBetReliefRecordService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBetSigninRewardService;
import org.springrain.lottery.service.IBetSinglerechargeService;
import org.springrain.lottery.service.IBetSubordinateRebateService;
import org.springrain.lottery.service.IBetSubordinaterebateDetailService;
import org.springrain.lottery.service.IBetTodayrechargerebateService;
import org.springrain.lottery.service.IBetTransferAccountsService;
import org.springrain.lottery.service.IBetVipService;
import org.springrain.lottery.service.IBetWeekwinorfailrebateService;
import org.springrain.lottery.service.IBetWithdrawcashService;
import org.springrain.lottery.service.IBjdcOddsService;
import org.springrain.lottery.service.IBjdcSchemeMatchService;
import org.springrain.lottery.service.ILotteryOrderService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerSchemeMatchService;
import org.springrain.lottery.service.ISoccerSchemeService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.ChineseUtill;
import org.springrain.lottery.utils.RandomCharData;
import org.springrain.lottery.utils.SendMsg;
import org.springrain.lottery.utils.WeekOfDate;
import org.springrain.lottery.utils.basketballWeekOfDate;
import org.springrain.system.service.IDicDataService;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-03 16:41:36
 * @see org.springrain.lottery.web.BetMember
 */
@Controller
@RequestMapping(value="/betmember")
public class BetMemberController  extends BaseController {
	@Resource
	private IBjdcSchemeMatchService bjdcSchemeMatchService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetRankMemberService betRankMemberService;
	@Resource
	private IBetMemberloginlogService betMemberloginlogService;
	@Resource
	private IBetVipService betVipService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetGameclassService betGameclassService;
	@Resource
	private IBetMemberbankcardService betMemberbankcardService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private ICached cached;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetLimitusernameService betLimitusernameService;
	@Resource
	private IBetTransferAccountsService betTransferAccountsService;
	@Resource
	private IBetRedenvelopeRecordService betRedenvelopeRecordService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetSubordinaterebateDetailService betSubordinaterebateDetailService;
	@Resource
	private IBetSigninRewardService betSigninRewardService;
	@Resource
	private IBetFirstrechargerebateService betFirstrechargerebateService;
	@Resource
	private IBetReliefRecordService betReliefRecordService;
	@Resource
	private IBetRegisterRewardService betRegisterRewardService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private ISoccerSchemeService soccerSchemeService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	@Resource
	private ISoccerSchemeMatchService soccerSchemeMatchService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private IBetDaywinorfailrebateService betDaywinorfailrebateService;
	@Resource
	private IBetWeekwinorfailrebateService betWeekwinorfailrebateService;
	@Resource
	private IBetTodayrechargerebateService betTodayrechargerebateService;
	@Resource
	private IBetSinglerechargeService betSinglerechargeService;
	@Resource
	private IBetPaymentInterfaceService betPaymentInterfaceService;
	@Resource
	private IBetKeyvalueService betKeyvalueService;
	@Resource
	private IBetSubordinateRebateService betSubordinateRebateService;
	@Resource
	private IBasketballSchemeMatchService basketballSchemeMatchService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	@Resource
	private IBetRedenvelopeService betRedenvelopeService;
	@Resource
	private IBjdcOddsService bjdcOddsService;
	@Resource
	private ILotteryOrderService lotteryOrderService;
	@Resource
	private IDicDataService dicDataService;
	private String listurl="/lottery/betmember/betmemberList";
	
	@Value("${serverapi}")
	private String serverapi;
	
	
	@RequestMapping("/updateisissue")
	public @ResponseBody
	ReturnDatas updateisissue(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagetn = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			Integer isissue = betMember2.getIsissue();
			if(isissue==1){
				BetMember betMember3=new BetMember();
				betMember3.setId(betMember2.getId());
				betMember3.setIsissue(0);
				betMemberService.update(betMember3, true);
				//操作日志
				 String details = "";
			     details = "取消ID为"+id2+"的用户的出票权限";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details, agentid, betagetn.getParentid(), betagetn.getParentids());
			}else{
				BetMember betMember3=new BetMember();
				betMember3.setId(betMember2.getId());
				betMember3.setIsissue(1);
				betMemberService.update(betMember3, true);
				//操作日志
				 String details = "";
			     details = "添加ID为"+id2+"的用户的出票权限";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details, agentid, betagetn.getParentid(), betagetn.getParentids());
			}
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	
	
	@RequestMapping("/updateisavatar")
	public @ResponseBody
	ReturnDatas updateisavatar(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagetn = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			Integer isavatarupdate = betMember2.getIsavatarupdate();
			if(isavatarupdate==1){
				BetMember betMember3=new BetMember();
				betMember3.setId(betMember2.getId());
				betMember3.setIsavatarupdate(0);
				betMemberService.update(betMember3, true);
				//操作日志
				 String details = "";
			     details = "取消ID为"+id2+"的用户的修改头像权限";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details, agentid, betagetn.getParentid(), betagetn.getParentids());
			}else{
				BetMember betMember3=new BetMember();
				betMember3.setId(betMember2.getId());
				betMember3.setIsavatarupdate(1);
				betMemberService.update(betMember3, true);
				//操作日志
				 String details = "";
			     details = "添加ID为"+id2+"的用户的修改头像权限";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details, agentid, betagetn.getParentid(), betagetn.getParentids());
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
	 * 投注用户
	 */
	@RequestMapping("/bettinglist")
	public String bettinglist(HttpServletRequest request, Model model,BetMember betMember) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Double yesterdaywinorloss=0.;
		Double todaywinorloss=0.;
		yesterdaywinorloss=betMemberService.queryForObject(new Finder("select sum(dayscore1) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :company)").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"), Double.class);
		model.addAttribute("yesterdaywinorloss", yesterdaywinorloss);
		todaywinorloss=betMemberService.queryForObject(new Finder("select sum(dayscore) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :company)").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"), Double.class);
		model.addAttribute("todaywinorloss", todaywinorloss);
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(50);
		List<BetMember>datas=new ArrayList<BetMember>();
		
		Integer it = soccerAllbettingService.queryForObject(new Finder("select count(*) from (SELECT * from soccer_allbetting where state=0 and (agentid=:agentid or agentparentids like :company) GROUP BY memberid2 )a").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),Integer.class);
		model.addAttribute("concurrentUsers", (it==null)?0:it);
		String parameter = request.getParameter("id2");
		String parameter2 = request.getParameter("account");
		String parameter3 = request.getParameter("mobile");
		String parameter4 = request.getParameter("realname");
		String parameter5 = request.getParameter("loginip");
		String parameter6 = request.getParameter("signip");
		
		if(StringUtils.isNoneBlank(parameter)){
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where a.memberid2 like :id2 and a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("id2", "%"+parameter+"%").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}else if(StringUtils.isNoneBlank(parameter2)){
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.account like :account and a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("account", "%"+parameter2+"%").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}else if(StringUtils.isNoneBlank(parameter3)){
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.mobile like :mobile and a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("mobile", "%"+parameter3+"%").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}else if(StringUtils.isNoneBlank(parameter4)){
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.realname like :realname and a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("realname", "%"+parameter4+"%").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}else if(StringUtils.isNoneBlank(parameter5)){
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.loginip like :loginip and a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("loginip", "%"+parameter5+"%").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}else if(StringUtils.isNoneBlank(parameter6)){
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.signip like :signip and a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("signip", "%"+parameter6+"%").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}else{
			datas = soccerAllbettingService.queryForList(new Finder("SELECT GROUP_CONCAT(distinct gcname) as operate,a.memberid2,sum(a.bettingmoney) as currentBettingMoney,b.* from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where a.state=0 and (a.agentid=:agentid or a.agentparentids like :company) and b.id2 is not null GROUP BY a.memberid2 order by sum(a.bettingmoney) desc ").setParam("signip", parameter6).setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),BetMember.class,page);
		}
		
		if(datas!=null){
			BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
			String company = "";//一级代理
			company = getCompany(agag, company);
			//隐藏用户List私密信息
			changeHideNameList(agag, company, datas);
		}
		
		returnObject.setQueryBean(betMember);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betmember/betmemberBettingList";

	}
	
	
	/**
	 * 内部用户
	 */
	@RequestMapping("/internallist")
	public String internallist(HttpServletRequest request, Model model,BetMember betMember) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setQueryBean(betMember);
		Page page = newPage(request);
		page.setPageSize(50);
		List<BetMember> datas = betMemberService.queryForList(new Finder("select*from bet_member where (agentid=:id or agentparentids like :agentid) and isinternal=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"), BetMember.class, page);
		if(datas!=null){
			BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
			String company = "";//一级代理
			company = getCompany(agag, company);
			
			for (BetMember betMember2 : datas) {
				String id = betMember2.getId();
				Double totalBettingMoney = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
				betMember2.setTotalBettingMoney(totalBettingMoney);
				//隐藏用户私密信息
				changeHideName(agag, company, betMember2);
			}
		}
		returnObject.setPage(page);
		returnObject.setData(datas);
			
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betmember/betinternalList";
	
	}
	/**
	 * 输赢用户
	 */
	@RequestMapping("/winorlosslist")
	public String winorlosslist(HttpServletRequest request, Model model,BetMember betMember) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		if("1".equals(request.getParameter("daywinorlossdetail"))){
			//投注记录明细
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String pageSize = request.getParameter("pageSize");
			if(StringUtils.isNotBlank(pageSize)){
				page.setPageSize(Integer.valueOf(pageSize));
			}else{
				page.setPageSize(50);
			}
			page.setOrder("bettingtime");
			page.setSort("desc");
			String id = betMember.getId();
			BetBetting betBetting=new BetBetting();
			betBetting.setMemberid(id);
			
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
			List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select *from bet_betting where (agentid=:id or agentparentids like :agentid) and bettingtime>=:starttime and bettingtime<:endtime ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetBetting.class,betBetting);
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			String hs="";
			if(datas!=null){
				for (BetBetting betBetting2 : datas) {
					String qibie = betBetting2.getQibie();
					Integer gameclassid = betBetting2.getGameclassid();
					hs = betBettingService.queryForObject(new Finder("select hs from bet_period where (agentid=:id or agentparentids like :agentid) and gameclassid=:gameclassid and qibie=:qibie ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("gameclassid", gameclassid).setParam("qibie", qibie), String.class);
					betBetting2.setHs(hs);
				}
			}
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
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betbettingList1";
		
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Double yesterdaywinorloss=0.;
			Double todaywinorloss=0.;
			yesterdaywinorloss=betMemberService.queryForObject(new Finder("select sum(dayscore1) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :company)").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"), Double.class);
			model.addAttribute("yesterdaywinorloss", yesterdaywinorloss);
			todaywinorloss=betMemberService.queryForObject(new Finder("select sum(dayscore) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :company)").setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"), Double.class);
			model.addAttribute("todaywinorloss", todaywinorloss);
			
			// ==构造分页请求
			Page page = newPage(request,"dayscore","desc");
			page.setPageSize(50);
			
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
			
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select a.*,b.xxxx as currentBettingMoney,c.ffff as todayRecharge,d.wwww as todaywithdrawcash from bet_member a left join (select memberid2,sum(bettingmoney) as xxxx from soccer_allbetting where state=0 group by memberid2) b on a.id2=b.memberid2 left join (select memberid,sum(money) as ffff from bet_gold where to_days(submittime) = to_days(now()) and state=2 group by memberid) c on a.id=c.memberid left join (select memberid,sum(money) as wwww from bet_withdrawcash where to_days(applicationtime) = to_days(now()) and state=2 group by memberid) d on a.id=d.memberid where a.signdate>=:starttime and a.dayscore!=0 and a.signdate<:endtime and (a.agentid=:agentid or a.agentparentids like :company)").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid).setParam("company", "%,"+agentid+",%"),page,BetMember.class,betMember);
			
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
			
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
				
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betmemberWinorlossList";
		}
	}
	/**
	 * 在线用户
	 */
	@RequestMapping("/onlinelist")
	public String onlinelist(HttpServletRequest request, Model model,BetMember betMember) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Integer concurrentUsers=0;
		Double yesterdaywinorloss=0.;
		Double todaywinorloss=0.;
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
		String company = "";//一级代理
		company = getCompany(agag, company);
		
		yesterdaywinorloss=betRankMemberService.queryForObject(new Finder("select sum(dayscore) from bet_rank_member where (agentid=:id or agentparentids like :agentid) and TO_DAYS( NOW( ) ) - TO_DAYS(date) = 1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"), Double.class);
		model.addAttribute("yesterdaywinorloss", yesterdaywinorloss);
		todaywinorloss=betMemberService.queryForObject(new Finder("select sum(dayscore) from bet_member where agentid=:id or agentparentids like :agentid ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"), Double.class);
		model.addAttribute("todaywinorloss", todaywinorloss);
		// ==构造分页请求
		Page page = newPage(request,"signdate","desc");
		page.setPageSize(50);
		List<BetMember>datas=new ArrayList<BetMember>();
		try{
			Set<String> keys=new HashSet<String>();
			
			@SuppressWarnings("unchecked")
			Set<String> keys2 = cached.getKeys("OnTICKET_*".getBytes());
			if(keys2!=null){
				for (String strKey2 : keys2) {
					if(strKey2!=null){
						BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id=:id and (agentid=:aid or agentparentids=:agentid)").setParam("id", strKey2).setParam("aid", agentid).setParam("agentid", "%"+agentid+"%"), BetMember.class);
						if (member!=null) {
							concurrentUsers++;
						}
					}
				}
				model.addAttribute("concurrentUsers", concurrentUsers);
			}
			for (String string : keys) {
				BetMember betMember2 = betMemberService.queryForObject(new Finder("select *from bet_member where (agentid=:aid or agentparentids like :agentid) and id=:id ").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id", string), BetMember.class);
				String id = betMember2.getId();
				Double currentBettingMoney=0.;
				String operate="";
				List<BetBetting> betBettingList = betBettingService.findListDataByFinder(new Finder("select * from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and state=0 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), null, BetBetting.class, null);
				for (BetBetting betBetting : betBettingList) {
					currentBettingMoney+=betBetting.getBettingmoney();
					if("".equals(operate)){
						operate+=betBetting.getGcname();
					}else{
						if(operate.indexOf(betBetting.getGcname())==-1){
							operate+="|"+betBetting.getGcname();
						}
					}
				}
				betMember2.setOperate(operate);
				betMember2.setCurrentBettingMoney(currentBettingMoney);
				//隐藏用户私密信息
				changeHideName(agag, company, betMember2);
				datas.add(betMember2);
			}
			
			if(betMember.getRealname()!=null){
				if(ChineseUtill.isMessyCode(betMember.getRealname())){
					String chinese = ChineseUtill.toChinese(betMember.getRealname());
					List<BetMember>datas2=new ArrayList<BetMember>();
					for (BetMember betMember2 : datas) {
						if(chinese.equals(betMember2.getRealname())){
							datas2.add(betMember2);
						}
					}
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas2);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/betmemberOnlineList";
				}else{
					String realname=betMember.getRealname();
					List<BetMember>datas2=new ArrayList<BetMember>();
					for (BetMember betMember2 : datas) {
						if(realname.equals(betMember2.getRealname())){
							datas2.add(betMember2);
						}
					}
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas2);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/betmemberOnlineList";
				}
			}else if(betMember.getId2()!=null){
				Integer id2 = betMember.getId2();
				List<BetMember>datas2=new ArrayList<BetMember>();
				for (BetMember betMember2 : datas) {
					if(id2.equals(betMember2.getId2())){
						datas2.add(betMember2);
					}
				}
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betmemberOnlineList";
			}else if(betMember.getAccount()!=null){
				String account = betMember.getAccount();
				List<BetMember>datas2=new ArrayList<BetMember>();
				for (BetMember betMember2 : datas) {
					if(betMember2.getAccount().indexOf(account)!=-1){
						datas2.add(betMember2);
					}
				}
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betmemberOnlineList";
			}else if(betMember.getMobile()!=null){
				String mobile = betMember.getMobile();
				List<BetMember>datas2=new ArrayList<BetMember>();
				for (BetMember betMember2 : datas) {
					if(betMember2.getMobile().indexOf(mobile)!=-1){
						datas2.add(betMember2);
					}
				}
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betmemberOnlineList";
			}else if(betMember.getLoginip()!=null){
				String loginip = betMember.getLoginip();
				List<BetMember>datas2=new ArrayList<BetMember>();
				for (BetMember betMember2 : datas) {
					if(betMember2.getLoginip().indexOf(loginip)!=-1){
						datas2.add(betMember2);
					}
				}
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betmemberOnlineList";
			}else if(betMember.getSignip()!=null){
				String signip = betMember.getSignip();
				List<BetMember>datas2=new ArrayList<BetMember>();
				for (BetMember betMember2 : datas) {
					if(betMember2.getSignip().indexOf(signip)!=-1){
						datas2.add(betMember2);
					}
				}
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betmemberOnlineList";
			}
			
			page.setTotalCount(datas.size());
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betmemberOnlineList";
		}catch (Exception e) {
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(null);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betmemberOnlineList";
		}
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betMember
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetMember betMember) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		if(betagent!=null){
			model.addAttribute("agentparentid", betagent.getParentid());
		}
		
		String company = "";//一级代理
		company = getCompany(betagent, company);
		//隐藏用户私密信息
		changeHideName(betagent, company, betMember);
		
		Double totalwinorloss=0.;
		Double totalbettingmoney=0.;
		if("1".equals(request.getParameter("k"))){
			//每日输赢
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request,"date","desc");
			page.setPageSize(50);
			String id = betMember.getId();
			BetMember betMember1 = betMemberService.findBetMemberById(id);
			changeHideName(betagent, company, betMember1);
			totalwinorloss=soccerAllbettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from soccer_allbetting where memberid2=:memberid2 and state=1 ").setParam("memberid2", betMember1.getId2()), Double.class);
			totalbettingmoney=soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where memberid2=:memberid2 ").setParam("memberid2", betMember1.getId2()), Double.class);
			BetRankMember betRankMember=new BetRankMember();
			betRankMember.setMemberid(id);
			List<BetRankMember> datas = betRankMemberService.findListDataByFinder(null, page,BetRankMember.class, betRankMember);
			
			BetRankMember betRankMember3=new BetRankMember();
			betRankMember3.setMemberid(id);
			betRankMember3.setDate(new java.sql.Date(new java.util.Date().getTime()));
			betRankMember3.setDayscore(betMember1.getDayscore());
			Double todayBettingmoney=soccerAllbettingService.queryForObject(new  Finder("select sum(bettingmoney) from soccer_allbetting where memberid2=:memberid2 and date_format(bettingtime,:parsedate) =:date ").setParam("memberid2", betMember1.getId2()).setParam("parsedate", "%Y-%m-%d").setParam("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date())), Double.class);
			if(todayBettingmoney==null){
				todayBettingmoney=0.;
			}
			String operation=soccerAllbettingService.queryForObject(new  Finder("select GROUP_CONCAT(distinct gcname) from soccer_allbetting where memberid2=:memberid2 and date_format(bettingtime,:parsedate) =:date ").setParam("memberid2", betMember1.getId2()).setParam("parsedate", "%Y-%m-%d").setParam("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
			if(operation==null){
				operation="";
			}
			betRankMember3.setBettingmoney(todayBettingmoney);
			betRankMember3.setBankmoney(betMember1.getBankscore());
			betRankMember3.setGamemoney(betMember1.getGamescore());
			if(betMember1.getLogintime()!=null){
				if(org.apache.commons.lang3.time.DateUtils.isSameDay(new java.util.Date(), betMember1.getLogintime())){
					betRankMember3.setLoginip(betMember1.getLoginip());
				}
			}
			
			betRankMember3.setOperation(operation);
			if(page.getPageIndex()==1){
				if(datas!=null){
					datas.add(0, betRankMember3);
				}else{
					datas=new ArrayList<BetRankMember>();
					datas.add(betRankMember3);
				}
			}
			
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("totalwinorloss",totalwinorloss);
			model.addAttribute("totalbettingmoney",totalbettingmoney);
			model.addAttribute("betMember",betMember1);
			model.addAttribute("k", 1);
			model.addAttribute("showtoday11", 1);
			return "/lottery/betmember/betrankmemberList1";
		}else if("2".equals(request.getParameter("k"))){
			//每日输赢本周
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request,"date","desc");
			page.setPageSize(50);
			BetMember betMember1 = betMemberService.findBetMemberById(betMember.getId());
			changeHideName(betagent, company, betMember1);
			BetRankMember betRankMember=new BetRankMember();
			betRankMember.setMemberid(betMember.getId());
			Finder finder =new Finder("select*from bet_rank_member where (agentid=:id or agentparentids like :agentid) and month(date) = month(curdate()) and week(date) = week(curdate()) ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%");
			List<BetRankMember> datas = betRankMemberService.findListDataByFinder(finder, page,BetRankMember.class, betRankMember);
			if(datas!=null){
				for (BetRankMember betRankMember2 : datas) {
					totalbettingmoney+=betRankMember2.getBettingmoney();
					totalwinorloss+=betRankMember2.getDayscore();
				}
			}
			model.addAttribute("totalbettingmoney", totalbettingmoney);
			model.addAttribute("totalwinorloss", totalwinorloss);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("betMember",betMember1);
			model.addAttribute("k", 2);
			return "/lottery/betmember/betrankmemberList1";
		}else if("3".equals(request.getParameter("k"))){
			//每日输赢本月
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request,"date","desc");
			page.setPageSize(50);
			BetMember betMember1 = betMemberService.findBetMemberById(betMember.getId());
			changeHideName(betagent, company, betMember1);
			BetRankMember betRankMember=new BetRankMember();
			betRankMember.setMemberid(betMember.getId());
			Finder finder =new Finder("select*from bet_rank_member where (agentid =:id or agentparentids like :agentid) and month(date) = month(curdate()) and year(date) = year(curdate()) ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%");
			List<BetRankMember> datas = betRankMemberService.findListDataByFinder(finder, page,BetRankMember.class, betRankMember);
			if(datas!=null){
				for (BetRankMember betRankMember2 : datas) {
					totalbettingmoney+=betRankMember2.getBettingmoney();
					totalwinorloss+=betRankMember2.getDayscore();
				}
			}
			model.addAttribute("totalbettingmoney", totalbettingmoney);
			model.addAttribute("totalwinorloss", totalwinorloss);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("betMember",betMember1);
			model.addAttribute("k", 3);
			return "/lottery/betmember/betrankmemberList1";
		}else if("4".equals(request.getParameter("k"))){
			//每日输赢上月
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request,"date","desc");
			page.setPageSize(50);
			BetMember betMember1 = betMemberService.findBetMemberById(betMember.getId());
			changeHideName(betagent, company, betMember1);
			BetRankMember betRankMember=new BetRankMember();
			betRankMember.setMemberid(betMember.getId());
			Finder finder =new Finder("select*from bet_rank_member WHERE (agentid=:id or agentparentids like :agentid) and PERIOD_DIFF( date_format(now( ),:x),date_format(date,:x))=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("x","%Y%m");
			List<BetRankMember> datas = betRankMemberService.findListDataByFinder(finder, page,BetRankMember.class, betRankMember);
			if(datas!=null){
				for (BetRankMember betRankMember2 : datas) {
					totalbettingmoney+=betRankMember2.getBettingmoney();
					totalwinorloss+=betRankMember2.getDayscore();
				}
			}
			model.addAttribute("totalbettingmoney", totalbettingmoney);
			model.addAttribute("totalwinorloss", totalwinorloss);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("betMember",betMember1);
			model.addAttribute("k", 4);
			return "/lottery/betmember/betrankmemberList1";
		}else if("5".equals(request.getParameter("k"))){
			//每日输赢上周
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request,"date","desc");
			page.setPageSize(50);
			BetMember betMember1 = betMemberService.findBetMemberById(betMember.getId());
			changeHideName(betagent, company, betMember1);
			BetRankMember betRankMember=new BetRankMember();
			betRankMember.setMemberid(betMember.getId());
			Finder finder =new Finder("select*from bet_rank_member WHERE (agentid=:id or agentparentids like :agentid) and YEARWEEK(date_format(date,:x),1) = YEARWEEK(now(),1)-1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("x","%Y-%m-%d");
			List<BetRankMember> datas = betRankMemberService.findListDataByFinder(finder, page,BetRankMember.class, betRankMember);
			if(datas!=null){
				for (BetRankMember betRankMember2 : datas) {
					totalbettingmoney+=betRankMember2.getBettingmoney();
					totalwinorloss+=betRankMember2.getDayscore();
				}
			}
			model.addAttribute("totalbettingmoney", totalbettingmoney);
			model.addAttribute("totalwinorloss", totalwinorloss);
			returnObject.setQueryBean(betRankMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("betMember",betMember1);
			model.addAttribute("k", 5);
			return "/lottery/betmember/betrankmemberList1";
		}else if("6".equals(request.getParameter("k"))){
			//用户操作日志
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String pageSize = request.getParameter("pageSize");
			if(StringUtils.isNotBlank(pageSize)){
				page.setPageSize(Integer.valueOf(pageSize));
			}else{
				page.setPageSize(50);
			}
			BetMemberOplog betMemberOplog=new BetMemberOplog();
			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select *from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
			changeHideName(betagent, company, betMember2);
			betMemberOplog.setMemberid2(id2);
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
			List<BetMemberOplog> datas=betMemberService.findListDataByFinder(new Finder("select *from bet_member_oplog where (agentid=:id or agentparentids like :agentid) and time>=:starttime and time<:endtime ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetMemberOplog.class,betMemberOplog);
			
			returnObject.setQueryBean(betMember2);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("betMember",betMember2);
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
			return "/lottery/betmember/betmemberoplogList1";
		}else if("7".equals(request.getParameter("k"))){
			if("1".equals(request.getParameter("p"))){
				//在线投注
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				String pageSize = request.getParameter("pageSize");
				if(StringUtils.isNotBlank(pageSize)){
					page.setPageSize(Integer.valueOf(pageSize));
				}else{
					page.setPageSize(10);
				}
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
				String type = request.getParameter("type");
				if(type==null){
					type="100";
				}
				String id2= request.getParameter("id2");
				if("1".equals(type)){
					//足球
					List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id  left join bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 and a.situation = 0 and (a.agentid=:id or a.agentparentids like :agentid)").setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2).setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),SoccerScheme.class,page);
					Double bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 and a.situation = 0 and (a.agentid=:id or a.agentparentids like :agentid)").setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2).setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),Double.class);
					///////////////////////////////////////////////////////////////////撤销的不算
					Double bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 and a.situation = 0 and (a.agentid=:id or a.agentparentids like :agentid)").setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2).setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),Double.class);
					
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerScheme soccerScheme2 : datas) {
							String schemeid = soccerScheme2.getSchemeid();
							if(schemeid!=null){
								schemeids.add(soccerScheme2.getSchemeid());
							}
						}
						List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("type", type);
					model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
					model.addAttribute("bettingwinTotal", bettingwinTotal);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/soccerbettingList3";
				}else if("0".equals(type)){
					//彩票
					page.setOrder("bettingtime");
					page.setSort("desc");
					String id = betMember.getId();
					BetBetting betBetting=new BetBetting();
					betBetting.setMemberid(id);
					String strgameclass2 = request.getParameter("gameclassid");
					List<BetBetting> datas=null;
					if(StringUtils.isNoneBlank(strgameclass2)){
						Integer gameclass2 = Integer.valueOf(strgameclass2);
						datas=betBettingService.findListDataByFinder(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.gameclassid=:gameclassid and a.state=0 and a.memberid=:memberid and (a.agentid=:agentid or a.agentparentids like :agentparentids)").setParam("gameclassid", gameclass2).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid).setParam("memberid", id).setParam("agentparentids", "%,"+agentid+",%"),page,BetBetting.class,betBetting);
						String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid  ").setParam("gameclassid", gameclass2), String.class);
						model.addAttribute("gcname", gcname);
						model.addAttribute("gameclassid",gameclass2);
					}else{
						datas=betBettingService.findListDataByFinder(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.state=0 and a.memberid=:memberid and (a.agentid=:agentid or a.agentparentids like :agentparentids)").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid).setParam("memberid", id).setParam("agentparentids", "%,"+agentid+",%"),page,BetBetting.class,betBetting);
					}
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas);
					String hs="";
					if(datas!=null){
						for (BetBetting betBetting2 : datas) {
							String qibie = betBetting2.getQibie();
							Integer gameclassid = betBetting2.getGameclassid();
							hs = betBettingService.queryForObject(new Finder("select hs from bet_period where gameclassid=:gameclassid and qibie=:qibie").setParam("gameclassid", gameclassid).setParam("qibie", qibie), String.class);
							betBetting2.setHs(hs);
						}
					}
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
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					model.addAttribute("type", type);
					return "/lottery/betmember/betbettingListuntreated";
				}else{
					//所有游戏
					List<SoccerAllbetting> datas =soccerAllbettingService.findListDataByFinder(new Finder("select a.*,b.nickname as membernickname from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 and a.state = 0 and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),page,SoccerAllbetting.class,null);
					Double bettingmoneyTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and  memberid2 = :memberid2 and state = 0 and (:type=100 or type=:type)").setParam("type", type).setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),Double.class);
					Double bettingwinTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingscore) from soccer_allbetting where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and  memberid2 = :memberid2 and state = 0 and (:type=100 or type=:type)").setParam("type", type).setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),Double.class);
					
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							if(1==allBetting.getType()){
								String schemeid = allBetting.getId();
								if(schemeid!=null){
									schemeids.add(schemeid);
								}
							}
						}
						if(!schemeids.isEmpty()){
						List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in(:schemeid)  order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
					
					//篮球处理
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							if(3==allBetting.getType()){
								String schemeid = allBetting.getId();
								if(schemeid!=null){
									schemeids.add(schemeid);
								}
							}
						}
						if(!schemeids.isEmpty()){
						List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in(:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("type", type);
					model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
					model.addAttribute("bettingwinTotal", bettingwinTotal);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/allsoccerbettingList3";
				}

				
			
			}else{
				//在线投注
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				String pageSize = request.getParameter("pageSize");
				if(StringUtils.isNotBlank(pageSize)){
					page.setPageSize(Integer.valueOf(pageSize));
				}else{
					page.setPageSize(10);
				}
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
				String type = request.getParameter("type");
				if(type==null){
					type="100";
				}
				String id2= request.getParameter("id2");
				if("1".equals(type)){
					//足球
					List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 ").setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),SoccerScheme.class,page);
					Double bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 ").setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),Double.class);
					///////////////////////////////////////////////////////////////////撤销的不算
					Double bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 ").setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),Double.class);
					
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerScheme soccerScheme2 : datas) {
							String schemeid = soccerScheme2.getSchemeid();
							if(schemeid!=null){
								schemeids.add(soccerScheme2.getSchemeid());
							}
						}
						List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("type", type);
					model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
					model.addAttribute("bettingwinTotal", bettingwinTotal);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/soccerbettingList";
				}else if("0".equals(type)){
					//彩票
					page.setOrder("bettingtime");
					page.setSort("desc");
					String id = betMember.getId();
					BetBetting betBetting=new BetBetting();
					betBetting.setMemberid(id);
					String strgameclass2 = request.getParameter("gameclassid");
					List<BetBetting> datas=null;
					if(StringUtils.isNoneBlank(strgameclass2)){
						Integer gameclass2 = Integer.valueOf(strgameclass2);
						datas=betBettingService.findListDataByFinder(new Finder("select a.* ,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.gameclassid=:gameclassid and a.memberid=:memberid and (a.agentid=:agentid or a.agentparentids like :agentparentids)").setParam("gameclassid", gameclass2).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid).setParam("memberid", id).setParam("agentparentids", "%,"+agentid+",%"),page,BetBetting.class,betBetting);
						String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", gameclass2), String.class);
						model.addAttribute("gcname", gcname);
						model.addAttribute("gameclassid",gameclass2);
					}else{
						datas=betBettingService.findListDataByFinder(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.bettingtime>=:starttime and a.bettingtime<:endtime and a.memberid=:memberid and (a.agentid=:agentid or a.agentparentids like :agentparentids)").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("agentid", agentid).setParam("memberid", id).setParam("agentparentids", "%,"+agentid+",%"),page,BetBetting.class,betBetting);
					}
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas);
					String hs="";
					if(datas!=null){
						for (BetBetting betBetting2 : datas) {
							String qibie = betBetting2.getQibie();
							Integer gameclassid = betBetting2.getGameclassid();
							hs = betBettingService.queryForObject(new Finder("select hs from bet_period where gameclassid=:gameclassid and qibie=:qibie").setParam("gameclassid", gameclassid).setParam("qibie", qibie), String.class);
							betBetting2.setHs(hs);
						}
					}
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
					model.addAttribute("type", type);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/betbettingList1";
				}else{
					List<SoccerAllbetting> datas =soccerAllbettingService.findListDataByFinder(new Finder("select a.*,b.nickname as membernickname from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and  a.memberid2 = :memberid2 and (:type=100 or a.type=:type)").setParam("type", type).setParam("starttime",starttime).setParam("endtime", endtime).setParam("memberid2", id2),page,SoccerAllbetting.class,null);
					Double bettingmoneyTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and  memberid2 = :memberid2 and (:type=100 or type=:type)").setParam("starttime",starttime).setParam("type", type).setParam("endtime", endtime).setParam("memberid2", id2),Double.class);
					Double bettingwinTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingscore) from soccer_allbetting where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and  memberid2 = :memberid2 and (:type=100 or type=:type)").setParam("starttime",starttime).setParam("type", type).setParam("endtime", endtime).setParam("memberid2", id2),Double.class);
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							if(1==allBetting.getType()){
								String schemeid = allBetting.getId();
								if(schemeid!=null){
									schemeids.add(schemeid);
								}
							}
						}
						List<SoccerSchemeMatch> matchDatas = null;
						if(!schemeids.isEmpty()){
							matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
						}
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
					//北单
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							if(4==allBetting.getType()){
								String schemeid = allBetting.getId();
								if(schemeid!=null){
									schemeids.add(schemeid);
								}
							}
						}
						List<BjdcSchemeMatch> matchDatas = null;
						if(!schemeids.isEmpty()){
							matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
						}
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
									    		String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
										    	String betname = m.get("betname").toString();
										    	m.put("betname",betname+"("+ letpoints+")");
									    	}
								    	}catch (Exception e) {
											e.printStackTrace();
											String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
					
					
					
					//篮球处理
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							if(3==allBetting.getType()){
								String schemeid = allBetting.getId();
								if(schemeid!=null){
									schemeids.add(schemeid);
								}
							}
						}
						List<BasketballSchemeMatch> matchDatas = null;
						if(!schemeids.isEmpty()){
							matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
						}
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
					returnObject.setQueryBean(betMember);
					returnObject.setPage(page);
					returnObject.setData(datas);
					model.addAttribute("type", type);
					model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
					model.addAttribute("bettingwinTotal", bettingwinTotal);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return "/lottery/betmember/allsoccerbettingList";
				}
				
			}
			
		}else if("8".equals(request.getParameter("k"))){
			//金币记录
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String pageSize = request.getParameter("pageSize");
			if(StringUtils.isNotBlank(pageSize)){
				page.setPageSize(Integer.valueOf(pageSize));
			}else{
				page.setPageSize(50);
			}
			page.setOrder("time");
			page.setSort("desc");
			Integer id2 = betMember.getId2();
			BetScorerecord betScorerecord=new BetScorerecord();
			betScorerecord.setMemberid2(id2);
			
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
			List<BetScorerecord> datas=betScorerecordService.findListDataByFinder(new Finder("select *from bet_scorerecord where (agentid=:id or agentparentids like :agentid) and time>=:starttime and time<:endtime ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetScorerecord.class,betScorerecord);
			returnObject.setQueryBean(betMember);
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
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betscorerecordList1";
		}else if("9".equals(request.getParameter("k"))){
			Map<String, Object> agent = betAgentService.queryForObject(new Finder("select active,score from bet_agent where agentid=:agent ").setParam("agent", agentid));
			if(agent!=null){
				Integer i=(Integer)agent.get("active");
				if(i!=null&1==i){
					//转账
					Object score = agent.get("score");
					model.addAttribute("score", score);
					String id2 = request.getParameter("id2");
					model.addAttribute("id2", id2);
					return "/lottery/betmember/bettransferaccounts1";
				}else{
					model.addAttribute("exception", "此代理账户被冻结！");
					return "/errorpage/error";
				}
			}else{
				return "/errorpage/error";
			}
		}else if("10".equals(request.getParameter("k"))){
			//转账记录
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("time");
			page.setSort("desc");
			BetTransferAccounts betTransferAccounts=new BetTransferAccounts();
			
			betTransferAccounts.setMemberid2(betMember.getId2());
			model.addAttribute("id2", betMember.getId2());
			// ==执行分页查询
			List<BetTransferAccounts> datas=betTransferAccountsService.findListDataByFinder(null,page,BetTransferAccounts.class,betTransferAccounts);
				returnObject.setQueryBean(betTransferAccounts);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/bettransferaccountsList1";
		}else if("11".equals(request.getParameter("k"))){
			//足球方案
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String memberid2 = request.getParameter("memberid2");
			String playmethodid = request.getParameter("playmethodid");
			String situation = request.getParameter("situation");
			String time = request.getParameter("time");
			SoccerScheme soccerscheme = new SoccerScheme();
			soccerscheme.setMemberid2(Integer.valueOf(memberid2));
			soccerscheme.setPlaymethodid(Integer.valueOf(playmethodid));
			soccerscheme.setSituation(Integer.valueOf(situation));  
			if("100".equals(situation)){
				situation=null;
			}
			if("100".equals(playmethodid)){
				playmethodid = null;
			}
			if(StringUtils.isBlank(time)){
				time="0";
			}
			if(StringUtils.isBlank(memberid2)){
				memberid2=null;
			}else{
				memberid2="%"+memberid2+"%";
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<SoccerScheme> datas = null;
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 //今日
					datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				 }else if("2".equals(time)){
					 //昨日
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				 }else if("3".equals(time)){
					 //本周
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				 }else if("4".equals(time)){
					 //上周
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				 }else if("5".equals(time)){
					 //本月
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				 }else{
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2  where  (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) ").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				 }
				
			}else{
				datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:situation is null or a.situation = :situation)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),SoccerScheme.class,page);
				bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:situation is null or a.situation = :situation)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
				bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:situation is null or a.situation = :situation)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid),Double.class);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			if(datas!=null){
				List<String> schemeids=new ArrayList<String>();
				
				for (SoccerScheme soccerScheme2 : datas) {
					String schemeid = soccerScheme2.getSchemeid();
					if(schemeid!=null){
						schemeids.add(soccerScheme2.getSchemeid());
					}
				}
				List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
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
			
			returnObject.setQueryBean(soccerscheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betsoccerschemeList";
			
		}else if("12".equals(request.getParameter("k"))){
			//充值记录
			List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface  "));
			String memberid = request.getParameter("memberid");
			BetGold betgold = new BetGold();
			betgold.setMemberid(memberid);
			Double yesterdayRecharge=0.0;
			Double todayRecharge=0.0;
			Integer chargeNumber=0;
			Integer member=0;
			Double totolRecharge=0.0;
			String date = request.getParameter("date");
			if("1".equals(date)){
				//按时间查询
				Page page = newPage(request);
				page.setOrder("submittime");
				page.setSort("desc");
				page.setPageSize(50);
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
				List<BetGold>datas=null;
				if(memberid!=null){                                          
					datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.memberid=:memberid and a.submittime>=:starttime and a.submittime<:endtime ").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
					List<BetGold> betgoldlist = betGoldService.queryForList(new Finder("select*from bet_gold where memberid=:memberid and submittime>=:starttime and submittime<:endtime  ").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), BetGold.class);
					if(betgoldlist!=null){
						for (BetGold betGold2 : betgoldlist) {
							if(2==betGold2.getState()){
								totolRecharge+=betGold2.getMoney();
							}
							chargeNumber++;
						}
							
					}
				}
				if(datas!=null){
					for (BetGold betGold2 : datas) {
						String source = betGold2.getSource();
						if(idandbanktypelist!=null){
							for (Map<String, Object> map : idandbanktypelist) {
								if(source.equals(map.get("id"))){
									betGold2.setSource((String)map.get("banktype"));
									break;
								}else{
									betGold2.setSource(null);
								}
							}
						}
					}
				}
				
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				
				returnObject.setQueryBean(betgold);
				returnObject.setPage(page);
				returnObject.setData(datas);
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
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				model.addAttribute("chargeNumber", chargeNumber);
				model.addAttribute("member", member);
				model.addAttribute("totolRecharge", totolRecharge);
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betgoldList1";
			
			}else{
				if(memberid!=null){
					totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid ").setParam("memberid", memberid), Double.class);
				}
				todayRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and to_days(rechargetime) = to_days(now())"), Double.class);
				yesterdayRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and TO_DAYS( NOW( ) ) - TO_DAYS(submittime) = 1 "), Double.class);
				if(todayRecharge==null){
					todayRecharge=0.;
				}
				if(yesterdayRecharge==null){
					yesterdayRecharge=0.;
				}
			}
			
			
			List<Map<String, Object>> idandbanktypelist2 = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface  "));
			BetGold betGold4=new BetGold();
			if(memberid!=null){
				betGold4.setMemberid(memberid);
			}
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("submittime");
			page.setSort("desc");
			page.setPageSize(50);
			List<BetGold> datas=null;
			if(!("1".equals(request.getParameter("bdjl")))){
				if(memberid!=null){
					datas = betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id  where  to_days(a.submittime) = to_days(now()) "), page, BetGold.class , betGold4);
					Date date1=new Date();
					String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date1);
					model.addAttribute("startTime", strdate);
					model.addAttribute("endTime", strdate);
				}else{
					datas = betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id  where  to_days(a.submittime) = to_days(now()) "), page, BetGold.class, betGold4);
					Date date1=new Date();
					String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date1);
					model.addAttribute("startTime", strdate);
					model.addAttribute("endTime", strdate);
				}
				
				if(datas!=null){
					for (BetGold betGold2 : datas) {
						String source = betGold2.getSource();
						if(idandbanktypelist2!=null){
							for (Map<String, Object> map : idandbanktypelist2) {
								if(source.equals(map.get("id"))){
									betGold2.setSource((String)map.get("banktype"));
									break;
								}else{
									betGold2.setSource(null);
								}
							}
						}
					}
				}
				List<String> memberList=new ArrayList<String>();
				if(memberid!=null){
					List<BetGold> betgoldlist = betGoldService.queryForList(new Finder("select*from bet_gold where memberid=:memberid ").setParam("memberid", memberid), BetGold.class);
					if(betgoldlist!=null){
						chargeNumber+=betgoldlist.size();
					}
				}else{
					if(datas!=null){
						for (BetGold betGold2 : datas) {
							chargeNumber++;
							if(!memberList.contains(betGold2.getMemberid())){
								member++;
								memberList.add(betGold2.getMemberid());
							}
						}
					}
				}
				
				model.addAttribute("chargeNumber", chargeNumber);
				model.addAttribute("member", member);
			}
			
			returnObject.setQueryBean(betgold);
			returnObject.setPage(page);
			returnObject.setData(datas);
			String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
			model.addAttribute("id2", id2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("todayRecharge", todayRecharge);
			model.addAttribute("yesterdayRecharge", yesterdayRecharge);
			model.addAttribute("totolRecharge", totolRecharge);
			return "/lottery/betmember/betgoldList1";
		}else if("13".equals(request.getParameter("k"))){
			//提现记录
			Double rechargecolorlimit = betKeyvalueService.queryForObject(new Finder("select value from bet_keyvalue where id=1 "),Double.class);
			model.addAttribute("rechargecolorlimit", rechargecolorlimit);
			String memberid = request.getParameter("memberid");
			Double yesterdayWithdrawcash=0.0;
			Double todayWithdrawcash=0.0;
			Integer member=0;
			if(memberid==null){
				Double memberRemain = betMemberService.queryForObject(new Finder("select sum(score) from bet_member where isinternal=0  "), Double.class);
				model.addAttribute("memberRemain", memberRemain);
			}
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
			if("1".equals(date)){
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
				List<BetWithdrawcash> datas=betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where a.applicationtime>=:starttime and a.applicationtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetWithdrawcash.class,betWithdrawcash4);
				if(memberid!=null){
					List<BetWithdrawcash> memberwithdrawcashlist = betWithdrawcashService.queryForList(new Finder("select *from bet_withdrawcash where applicationtime>=:starttime and applicationtime<:endtime and memberid=:memberid  ").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), BetWithdrawcash.class);
					if(memberwithdrawcashlist!=null){
						for (BetWithdrawcash betWithdrawcash2 : memberwithdrawcashlist) {
							if(betWithdrawcash2.getState()==2){
								totolWithdrawcash+=betWithdrawcash2.getMoney();
							}
							withdrawcashNumber++;
						}
					}
				}else{
					totolWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where audittime>=:starttime and audittime<:endtime and state=2 ").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
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
						
						todayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and to_days(audittime) = to_days(now()) "), Double.class);
						yesterdayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and TO_DAYS( NOW( ) ) - TO_DAYS(audittime) <= 1  "), Double.class);
						model.addAttribute("todayWithdrawcash", todayWithdrawcash);
						model.addAttribute("yesterdayWithdrawcash", yesterdayWithdrawcash);
					}
				}
				if(memberid!=null){
					String realname = betMemberService.queryForObject(new Finder("select realname from bet_member where id=:id ").setParam("id", memberid), String.class);
					betWithdrawcash4.setRealname(realname);
				}
				returnObject.setQueryBean(betWithdrawcash4);
				returnObject.setPage(page);
				returnObject.setData(datas);
			
			}else{
				List<BetWithdrawcash> datas=null;
				if(memberid==null){
					datas = betWithdrawcashService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2,b.realname as realname from bet_withdrawcash a LEFT JOIN bet_member b ON a.memberid=b.id  where to_days(a.applicationtime) = to_days(now())  "), page, BetWithdrawcash.class, betWithdrawcash4);
					Date date2 = new Date();
					String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date2);
					model.addAttribute("endTime", strdate);
					model.addAttribute("startTime", strdate);
				}
				
				if(memberid!=null){
					datas = betWithdrawcashService.queryForList(new Finder("select *from bet_withdrawcash where  memberid=:memberid and to_days(applicationtime) = to_days(now()) ").setParam("memberid", memberid), BetWithdrawcash.class,page);
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
				
			}
			
			if("1".equals(date)){
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betwithdrawcashList1";
			}else{
				if(memberid!=null){
					
				}else{
					todayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and to_days(audittime) = to_days(now()) "), Double.class);
					yesterdayWithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and TO_DAYS( NOW( ) ) - TO_DAYS(audittime) <= 1  "), Double.class);
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
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betwithdrawcashList1";
			}
			
		}else if("14".equals(request.getParameter("k"))){
			//每日输赢中的投注记录
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String id = request.getParameter("memberid");
			BetBetting betBetting = new BetBetting();
			betBetting.setMemberid(id);
			String daybettingdate = request.getParameter("date1");
			String type = request.getParameter("type");
			if(type==null){
				type = "100";
			}
			if("0".equals(type)){
				String state = request.getParameter("state");
				
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				String date1 = request.getParameter("date1");
				Date parseDate = DateUtils.parseDate(date1);
				if(StringUtils.isNoneBlank(date1)){
					Integer id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:id ").setParam("id", id), Integer.class);
					List<BetBetting> datas=betBettingService.findListDataByFinder(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where to_days(:date1) = to_days(a.bettingtime)").setParam("date1", parseDate),page,BetBetting.class,betBetting);
					String hs="";
					if(datas!=null){
						for (BetBetting betBetting2 : datas) {
							String qibie = betBetting2.getQibie();
							Integer gameclassid = betBetting2.getGameclassid();
							hs = betBettingService.queryForObject(new Finder("select hs from bet_period where gameclassid=:gameclassid and qibie=:qibie ").setParam("gameclassid", gameclassid).setParam("qibie", qibie), String.class);
							betBetting2.setHs(hs);
						}
					}
					model.addAttribute("id2", id2);
					model.addAttribute("id", betBetting.getMemberid());
					
					model.addAttribute("dataad", date1);
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
				}else if(StringUtils.isNoneBlank(betBetting.getQibie())&&betBetting.getGameclassid()!=null){
					List<BetBetting> datas=betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.qibie=:qibie and a.gameclassid=:gameclassid ").setParam("qibie", betBetting.getQibie()).setParam("gameclassid", betBetting.getGameclassid()), BetBetting.class, page);
					String hs="";
					if(datas!=null){
						for (BetBetting betBetting2 : datas) {
							String memberid = betBetting2.getMemberid();
							Integer id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:id ").setParam("id", memberid), Integer.class);
							hs = betBettingService.queryForObject(new Finder("select hs from bet_period where gameclassid=:gameclassid and qibie=:qibie ").setParam("gameclassid", betBetting.getGameclassid()).setParam("qibie", betBetting.getQibie()), String.class);
							betBetting2.setHs(hs);
							betBetting2.setMemberid2(id2);
						}
					}
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
					
					
				}else{
					List<BetBetting> datas=betBettingService.findListDataByFinder(null,page,BetBetting.class,betBetting);
					if(datas!=null){
						for (BetBetting betBetting2 : datas) {
							String memberid = betBetting2.getMemberid();
							Integer id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:id ").setParam("id", memberid), Integer.class);
							betBetting2.setMemberid2(id2);
						}
					}
					returnObject.setQueryBean(betBetting);
					returnObject.setPage(page);
					returnObject.setData(datas);
				}
				
				
				
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				if(StringUtils.isNoneBlank(request.getParameter("date1"))){
					model.addAttribute("type", type);
					return "/lottery/betmember/betdaybettingList";
				}
				if(StringUtils.isNoneBlank(request.getParameter("qibie"))&&StringUtils.isNoneBlank(request.getParameter("gameclassid"))){
					return "/lottery/betbetting/betqibiebettingList";
				}
				if(StringUtils.isNoneBlank(request.getParameter("gameplayid"))&&state!=null&&"0".equals(state)){
					return "/lottery/betbetting/betuntreatedbettingList";
				}
				return "/lottery/betbetting/betbettingList";
				
			}else if("1".equals(type)){
				List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where c.id=:id and substr(a.bettingtime,1,10)=:daybettingdate ").setParam("daybettingdate", daybettingdate).setParam("id", id),SoccerScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerScheme soccerScheme2 : datas) {
						String schemeid = soccerScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(soccerScheme2.getSchemeid());
						}
					}
					List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("id", id);
				model.addAttribute("type", type);
				model.addAttribute("dataad", daybettingdate);
				return "/lottery/betbetting/soccerbettingList";
			}else{
				List<SoccerAllbetting> datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where b.id=:id and substr(a.bettingtime,1,10)=:daybettingdate and (:type=100 or a.type=:type)").setParam("type", type).setParam("id", id).setParam("daybettingdate", daybettingdate),SoccerAllbetting.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(1==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					if(!schemeids.isEmpty()){
						
					List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
				
				//篮球处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(3==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					if(!schemeids.isEmpty()){
						List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
							
						if(matchDatas!=null){
							List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
				
				//北单
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(4==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<BjdcSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					}
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
				
				//大乐透处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
						}
					}
					List<LotteryOrder> orderDatas=null;
					if(!schemeids.isEmpty()){
						orderDatas= lotteryOrderService.queryForList(new Finder("select a.*,b.name as playmethod from lottery_order a LEFT JOIN lottery_playmethod b on a.playtype = b.id where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), LotteryOrder.class);
					}
					for(SoccerAllbetting allBetting : datas){
						List<LotteryOrder> sss=new ArrayList<LotteryOrder>();
						if(orderDatas!=null){
							for(LotteryOrder schemeContent : orderDatas){
								String schemeid = schemeContent.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeContent);
								}
								System.out.println(sss+"===============");
							}
						}
						allBetting.setLotteryschemecontent(sss);
					}					
				} 
				
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("id", id);
				model.addAttribute("type", type);
				model.addAttribute("dataad", daybettingdate);
				return "/lottery/betbetting/allsoccerbettingList";
			}
			
		}else if("15".equals(request.getParameter("k"))){
			//红包记录
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("receivetime");
			page.setSort("desc");
			if(request.getParameter("memberid2")!=null){
				String parameter = request.getParameter("memberid2");
				List<BetRedenvelopeRecord> datas=	betRedenvelopeRecordService.queryForList(new Finder("select * from bet_redenvelope_record where memberid2=:memberid2 and state=0 ").setParam("memberid2", parameter), BetRedenvelopeRecord.class,page);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				
				return "/lottery/betmember/betredenveloperecordList1";
			}else{
				return "/errorpage/error";
			}
			
		}else if("16".equals(request.getParameter("k"))){
			//注册返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("receivetime");
			page.setSort("desc");
			if(request.getParameter("memberid2")!=null){
				String parameter = request.getParameter("memberid2");
				List<BetRegisterReward> datas=	betRegisterRewardService.queryForList(new Finder("select * from bet_register_reward where memberid2=:memberid2  ").setParam("memberid2", parameter), BetRegisterReward.class,page);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				
				return "/lottery/betmember/betregisterrewardList1";
			}else{
				return "/errorpage/error";
			}
		}else if("17".equals(request.getParameter("k"))){
			//推广返利
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("subtime");
			page.setSort("desc");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			String id2 = request.getParameter("memberid2");
			if(id2!=null){
				List<Map<String, Object>> datas = betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime>=:starttime and subtime<=:endtime and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("parentmemberid2", id2).setParam("starttime",starttime ).setParam("endtime", endtime),page);
				BetSubordinaterebateDetail betSubordinaterebateDetail = new BetSubordinaterebateDetail();
				betSubordinaterebateDetail.setMemberid2(Integer.valueOf(id2));
				returnObject.setQueryBean(betSubordinaterebateDetail);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("id2", id2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				if("0000-00-00".equals(starttime)){
					starttime="";
				}
				if("9999-00-00".equals(endtime)){
					endtime="";
				}
				model.addAttribute("startTime", starttime);
				model.addAttribute("endTime", endtime);
				return "/lottery/betmember/betsubordinaterebatedetailList2";			
			}else{
				return "/errorpage/error";
			}
		}else if("18".equals(request.getParameter("k"))){
			//首冲
			String id2 = request.getParameter("memberid2");
			BetFirstrechargerebate betFirstrechargerebate = new BetFirstrechargerebate();
			betFirstrechargerebate.setMemberid2(Integer.valueOf(id2));
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<BetFirstrechargerebate> datas=betFirstrechargerebateService.findListDataByFinder(new Finder("select * from bet_firstrechargerebate where memberid2=:id2 order by receivetime desc").setParam("id2", id2),page,BetFirstrechargerebate.class,betFirstrechargerebate);
			returnObject.setQueryBean(betFirstrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("id2", id2);
			return "/lottery/betmember/betfirstrechargerebateList2";
		}else if("19".equals(request.getParameter("k"))){
			//当日充值
			String id2 = request.getParameter("memberid2");
			BetTodayrechargerebate betTodayrechargerebate = new BetTodayrechargerebate();
			betTodayrechargerebate.setMemberid2(Integer.valueOf(id2));
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setSort("desc");
			page.setOrder("receivetime");
			// ==执行分页查询
			List<BetTodayrechargerebate> datas=betTodayrechargerebateService.findListDataByFinder(new Finder("select * from bet_todayrechargerebate where memberid2=:id2 ").setParam("id2", id2),page,BetTodayrechargerebate.class,betTodayrechargerebate);
			returnObject.setQueryBean(betTodayrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("id2", id2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/bettodayrechargerebateList2";
		}else if("20".equals(request.getParameter("k"))){
			//单笔充值
			String id2 = request.getParameter("memberid2");
			BetSinglerecharge  betSinglerecharge = new BetSinglerecharge();
			betSinglerecharge.setMemberid2(Integer.valueOf(id2));
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setSort("desc");
			page.setOrder("receivetime");
			// ==执行分页查询
			List<BetSinglerecharge> datas=betSinglerechargeService.findListDataByFinder(new Finder("select * from bet_singlerecharge where memberid2=:id2 ").setParam("id2", id2),page,BetSinglerecharge.class,null);
			returnObject.setQueryBean(betSinglerecharge);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("id2", id2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betsinglerechargeList2";
		}else if("21".equals(request.getParameter("k"))){
			//推荐收益
			
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String dateDetail = request.getParameter("date");
			List<Map<String, Object>> datas2=new ArrayList<>();
			String starttime = "";
			String endtime = "";
			String memberid2 = request.getParameter("memberid2");
			BetSubordinaterebateDetail betSubordinaterebateDetail = new BetSubordinaterebateDetail();
			if(memberid2!=null){
				try{
					betSubordinaterebateDetail.setMemberid2(Integer.valueOf(memberid2));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(StringUtils.isNotEmpty(dateDetail)){
				if("today".equals(dateDetail)){

				}else if("week".equals(dateDetail)){
					datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE YEARWEEK(SUBTIME,1)=YEARWEEK(NOW(),1) and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("parentmemberid2", memberid2), page);
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					int d = 0;
					if(cal.get(Calendar.DAY_OF_WEEK)==1){
						d = -6;
					}else{
						d = 2-cal.get(Calendar.DAY_OF_WEEK);
					}
					cal.add(Calendar.DAY_OF_WEEK, d);
					//所在周开始日期
					starttime =DateUtils.convertDate2String("yyyy-MM-dd", cal.getTime());
					cal.add(Calendar.DAY_OF_WEEK, 6);
					//所在周结束日期
					endtime =DateUtils.convertDate2String("yyyy-MM-dd", cal.getTime());
					
				}else if("month".equals(dateDetail)){
					datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE DATE_FORMAT(SUBTIME,:format)=DATE_FORMAT(NOW(),:format) and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("format", "%Y-%m").setParam("parentmemberid2", memberid2), page);
					Calendar c = Calendar.getInstance();    
			        c.add(Calendar.MONTH, 0);
			        c.set(Calendar.DAY_OF_MONTH,1);
			        starttime =DateUtils.convertDate2String("yyyy-MM-dd", c.getTime());
			        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
			        endtime =DateUtils.convertDate2String("yyyy-MM-dd", c.getTime());
			        

				}else if("lmonth".equals(dateDetail)){
					datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE DATE_FORMAT(SUBTIME,:format)=DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 1 MONTH),:format) and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("format", "%Y-%m").setParam("parentmemberid2", memberid2), page);
					 Calendar calendar1 = Calendar.getInstance();
				        calendar1.add(Calendar.MONTH, -1);
				        calendar1.set(Calendar.DAY_OF_MONTH,1);
				        starttime =DateUtils.convertDate2String("yyyy-MM-dd", calendar1.getTime());
				        calendar1.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
				        endtime =DateUtils.convertDate2String("yyyy-MM-dd", calendar1.getTime());
				}
				
			}else if("1".equals(request.getParameter("p"))){
				starttime = request.getParameter("startTime");
				endtime = request.getParameter("endTime");
				if(StringUtils.isBlank(starttime)){
					starttime="0000-00-00";
				}
				if(StringUtils.isBlank(endtime)){
					endtime="9999-00-00";
				}
				if(betSubordinaterebateDetail.getMemberid2()!=null){
					datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime>=:starttime and subtime<=:endtime and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("parentmemberid2", betSubordinaterebateDetail.getMemberid2()).setParam("starttime",starttime ).setParam("endtime", endtime),page);
								
				}else{
					datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime>=:starttime and subtime<=:endtime group by parentmemberid2 ,subtime ").setParam("starttime",starttime ).setParam("endtime", endtime),page);
				}
				

			}else{
				datas2 = betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime=DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),:format) and parentmemberid2=:parentmemberid2 group by parentmemberid2 ").setParam("format", "%Y-%m-%d").setParam("parentmemberid2", memberid2), page);
//				xxxx
				Calendar now = Calendar.getInstance(); 
				now.add(Calendar.DATE, -1);
				Date date1 = now.getTime();
				String time = DateUtils.convertDate2String(date1);
				starttime = time;
				endtime = time;
			}
			if("0000-00-00".equals(starttime)){
				starttime="";
			}
			if("9999-00-00".equals(endtime)){
				endtime="";
			}
			model.addAttribute("startTime", starttime);
			model.addAttribute("endTime", endtime);
			model.addAttribute("memberid2", memberid2);
			returnObject.setQueryBean(betSubordinaterebateDetail);
			returnObject.setPage(page);
			returnObject.setData(datas2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betsubordinaterebatedetailList";
		}else if("22".equals(request.getParameter("k"))){
			String agentId2 = SessionUser.getAgentId();
			//查登陆地址
			String memberid2 = request.getParameter("memberid2");
			String ip2=request.getParameter("ip2");
			String memberid = request.getParameter("memberid");
			BetMemberloginlog betMemberloginlog  = new BetMemberloginlog();
			if(memberid2!=null){
				try{
					betMemberloginlog.setMemberid2(Integer.valueOf(memberid2));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(ip2!=null){
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page=newPage(request);
				List<BetMember> ipmemberList = betMemberloginlogService.queryForList(new Finder("select * from bet_member a inner join (select memberid2 from bet_memberloginlog where ip like :ip2 GROUP BY memberid2) b on a.id2=b.memberid2 where (a.agentid=:agentid or a.agentparentids like :aid)  ").setParam("agentid", agentId2).setParam("aid", "%,"+agentId2+",%").setParam("ip2", "%"+ip2+"%"), BetMember.class,page);
				returnObject.setData(ipmemberList);
				returnObject.setQueryBean(new BetMember());
				returnObject.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/iploginlogList";
			}else{
				if(memberid!=null){
					BetMember betMember2 = betMemberService.findBetMemberById(memberid);
					betMemberloginlog.setMemberid2(betMember2.getId2());
				}
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request,"time","desc");
				List<BetMemberloginlog> datas=betMemberloginlogService.findListDataByFinder(null,page,BetMemberloginlog.class,betMemberloginlog);
				returnObject.setQueryBean(betMemberloginlog);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betmemberloginlogList";
			}
		}else{
			if(betMember.getRealname()!=null){
				//真实姓名
				if(ChineseUtill.isMessyCode(betMember.getRealname())){
					String chinese = ChineseUtill.toChinese(betMember.getRealname());
					betMember.setRealname(chinese);
					ReturnDatas returnObject = listjson(request, model, betMember);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return listurl;
				}else{
					ReturnDatas returnObject = listjson(request, model, betMember);
					model.addAttribute(GlobalStatic.returnDatas, returnObject);
					return listurl;
				}
			}else if(betMember.getParentid()!=null){
				//下线列表
				ReturnDatas returnObject = listjson(request, model, betMember);
				Integer isparent = betMemberService.queryForObject(new Finder("select isparent from bet_member where id2=:id2 ").setParam("id2", betMember.getParentid()), Integer.class);
				model.addAttribute("isparent",isparent);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
//				return "/lottery/betmember/subordinateList";
				return "/lottery/betmember/subordinateList1";
				
			}else if("1".equals(request.getParameter("limitusername"))){
				//限制用户名
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = new Page();
				page.setOrder("id");
				page.setSort("asc");
				page.setPageSize(50);
				// ==执行分页查询
				List<BetLimitusername> datas=betLimitusernameService.findListDataByFinder(new Finder("select*from bet_limitusername where 1=1 "),page,BetLimitusername.class,null);
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betmember/betlimitusername1";
			}else{
				ReturnDatas returnObject = listjson(request, model, betMember);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return listurl;
			}
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betMember
	 * @return
	 * @throws Exception
	 *                 betmember
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetMember betMember) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
		String company = "";//一级代理
		company = getCompany(agag, company);
			
		if("1".equals(request.getParameter("mo"))){
			//代理
			String pageIndex = request.getParameter("pageIndex");
			if(StringUtils.isEmpty(pageIndex)){
				pageIndex ="1";
			}
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setPageSize(20);
			page.setPageIndex(Integer.valueOf(pageIndex));
			// ==执行分页查询
//			List<SubordinateDto> datas=betMemberService.queryForList(new Finder("select a.id2,a.account,a.nickname,b.bettingrebate,b.bettingrebate2 from bet_member a left join bet_agent b on a.account=b.account where a.agentid=:agentid and b.bettingrebate is NOT NULL").setParam("agentid", agentid),SubordinateDto.class,page);
			List<SubordinateDto> datas=betMemberService.queryForList(new Finder("select agentid,account,nickname,bettingrebate,bettingrebate2 from bet_agent  where parentid=:parentid ").setParam("parentid", agentid),SubordinateDto.class,page);
			if(datas!=null){
				BetAgent betagent = betAgentService.queryForObject(new Finder("select bettingrebate,bettingrebate2 from bet_agent  where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
				for (SubordinateDto subordinateDto : datas) {
					subordinateDto.setId2(subordinateDto.getAgentid());
					subordinateDto.setAgentbettingrebate(betagent.getBettingrebate());
					subordinateDto.setAgentbettingrebate2(betagent.getBettingrebate2());
				}
			}
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else if("2".equals(request.getParameter("mo"))){
			//
			String pageIndex = request.getParameter("pageIndex");
			if(StringUtils.isEmpty(pageIndex)){
				pageIndex ="1";
			}
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setPageSize(20);
			page.setOrder("a.id");
			page.setPageIndex(Integer.valueOf(pageIndex));
			
			//排查自己的代理账号
			String currentparentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class);
			
			// ==执行分页查询
			List<SubordinateDto> datas=betMemberService.queryForList(new Finder("select a.id2,a.account,a.nickname,b.bettingrebate,b.bettingrebate2 from bet_member a left join (select c.account,c.bettingrebate,c.bettingrebate2 from bet_agent c where c.parentids like :currentparentids) b on a.account=b.account where a.agentid=:agentid and b.bettingrebate is NULL").setParam("agentid", agentid).setParam("currentparentids", currentparentids),SubordinateDto.class,page);
			if(datas!=null){
				BetAgent betagent = betAgentService.queryForObject(new Finder("select bettingrebate,bettingrebate2 from bet_agent  where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
				for (SubordinateDto subordinateDto : datas) {
					subordinateDto.setAgentbettingrebate(betagent.getBettingrebate());
					subordinateDto.setAgentbettingrebate2(betagent.getBettingrebate2());
				}
			}
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}
		
		//添加vip等级
		Finder finderx =new Finder("select *from bet_vip where (agentid=:id or agentparentids like :agentid) ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%");
		List<BetVip> betVipList = betVipService.queryForList(finderx, BetVip.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request,"signdate","desc");
		page.setPageSize(50);
		// ==执行分页查询
		if("1".equals(request.getParameter("today"))){
			//今日注册
			java.sql.Date date =new java.sql.Date(new Date().getYear(),new Date().getMonth(),new Date().getDate());
			betMember.setSigndate(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			List<BetMember> datas = betMemberService.findListDataByFinder(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where (bet_member.agentid=:id or bet_member.agentparentids like :agentid) and date_format(bet_member.signdate,:parsetime)=:today ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("parsetime", "%Y-%m-%d").setParam("today", today), page, BetMember.class, betMember);
			//隐藏用户List私密信息
			changeHideNameList(agag, company, datas);
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else if("1".equals(request.getParameter("online"))){
			//在线用户
			try{
				@SuppressWarnings("unchecked")
				Set<String> keys = cached.getKeys("OnTICKET_*".getBytes());
				List<BetMember>datas=new ArrayList<BetMember>();
				if(keys!=null && keys.size()>0){
					for (String string : keys) {
						try {
				            ObjectMapper objectMapper = new ObjectMapper();
				            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
				            BetMember betmember = objectMapper.readValue(string, BetMember.class);
				            BetMember betMember2 = betMemberService.queryForObject(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where (bet_member.agentid=:aid or bet_member.agentparentids like :agentid) and bet_member.id=:id ").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id", betmember.getId()), BetMember.class);
							if(betMember2!=null){
								String id = betMember2.getId();
								Double currentBettingMoney=0.;
								String operate="";
								List<BetBetting> betBettingList = betBettingService.findListDataByFinder(new Finder("select * from bet_betting where (agentid=:aid or agentparentids like :agentid) and memberid=:memberid and state=0 ").setParam("memberid", id).setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%"), null, BetBetting.class, null);
								for (BetBetting betBetting : betBettingList) {
									currentBettingMoney+=betBetting.getBettingmoney();
									if("".equals(operate)){
										operate+=betBetting.getGcname();
									}else{
										if(operate.indexOf(betBetting.getGcname())==-1){
											operate+="|"+betBetting.getGcname();
										}
									}
								}
								betMember2.setOperate(operate);
								betMember2.setCurrentBettingMoney(currentBettingMoney);
								datas.add(betMember2);
							}
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
					}
				}
				
				if(datas!=null){
					for (BetMember betMember2 : datas) {
						Integer exp = betMember2.getExp();
						if(exp!=null){
							for (BetVip betVip : betVipList) {
								if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
									betMember2.setLevel(betVip.getLevel());
								}
							}
						}
						//隐藏用户私密信息
						changeHideName(agag, company, betMember2);
					}
				}
				Page page1=new Page();
				page1.setPageSize(50);
				page1.setTotalCount(datas.size());
				returnObject.setQueryBean(betMember);
				returnObject.setPage(page);
				returnObject.setData(datas);
				return returnObject;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(null);
			return returnObject;
		}else if(betMember.getParentid()!=null){
			
			page.setOrder("id");
			page.setSort("asc");
			page.setPageSize(50);
			List<BetMember> subordinatelist = betMemberService.queryForList(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where bet_member.parentid=:parentid ").setParam("parentid", betMember.getParentid()), BetMember.class,page);
			changeHideNameList(agag, company, subordinatelist);
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(subordinatelist);
			return returnObject;
			
		}else if(betMember.getIsinternal()!=null){
			//内部用户
			List<BetMember> datas=betMemberService.queryForList(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where (bet_member.agentid=:id or bet_member.agentparentids like :agentid)").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),BetMember.class,page);
			if(datas!=null){
				for (BetMember betMember2 : datas) {
					Integer exp = betMember2.getExp();
					if(exp!=null){
						for (BetVip betVip : betVipList) {
							if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
								betMember2.setLevel(betVip.getLevel());
							}
						}
					}
					
					//隐藏用户私密信息
					changeHideName(agag, company, betMember2);
				}
			}
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else if("1".equals(request.getParameter("vip"))){
			//vip用户
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where (bet_member.agentid=:id or bet_member.agentparentids like :agentid) and bet_member.status=1 and bet_member.isinternal=0 and bet_member.winorfail>-3000 and bet_member.exp>10000 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),page,BetMember.class,null);
			if(datas!=null){
				for (BetMember betMember2 : datas) {
					Integer exp = betMember2.getExp();
					if(exp!=null){
						for (BetVip betVip : betVipList) {
							if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
								betMember2.setLevel(betVip.getLevel());
							}
						}
					}
					
					//隐藏用户私密信息
					changeHideName(agag, company, betMember2);
				}
			}
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("vip22", 1);
			return returnObject;
		}else if("1".equals(request.getParameter("risk"))){
			//风险用户
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where (bet_member.agentid=:id or bet_member.agentparentids like :agentid) and bet_member.status=1 and bet_member.isinternal=0 and bet_member.winorfail>0 and bet_member.exp<1000 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),page,BetMember.class,null);
			if(datas!=null){
				for (BetMember betMember2 : datas) {
					Integer exp = betMember2.getExp();
					if(exp!=null){
						for (BetVip betVip : betVipList) {
							if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
								betMember2.setLevel(betVip.getLevel());
							}
						}
					}
					
					//隐藏用户私密信息
					changeHideName(agag, company, betMember2);
				}
			}
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("risk22", 1);
			return returnObject;
		}else if(betMember.getStatus()!=null&&betMember.getStatus()==0){
			//冻结用户
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where bet_member.status=0 and (bet_member.agentid=:id or bet_member.agentparentids like :agentid)").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),page,BetMember.class,null);
			if(datas!=null){
				for (BetMember betMember2 : datas) {
					Integer exp = betMember2.getExp();
					if(exp!=null){
						for (BetVip betVip : betVipList) {
							if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
								betMember2.setLevel(betVip.getLevel());
							}
						}
					}
					
					//隐藏用户私密信息
					changeHideName(agag, company, betMember2);
				}
			}
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("statussss", 0);
			return returnObject;
		}else if(betMember.getStatus()!=null&&betMember.getStatus()==2){
			//作废用户
			List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select bet_member.*,bet_agent.nickname as agentnickname from bet_member left join bet_agent on bet_member.agentid=bet_agent.agentid where bet_member.status=2 and (bet_member.agentid=:id or bet_member.agentparentids like :agentid)").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),page,BetMember.class,null);
			if(datas!=null){
				for (BetMember betMember2 : datas) {
					Integer exp = betMember2.getExp();
					if(exp!=null){
						for (BetVip betVip : betVipList) {
							if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
								betMember2.setLevel(betVip.getLevel());
							}
						}
					}
					
					//隐藏用户私密信息
					changeHideName(agag, company, betMember2);
				}
			}
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("statussss", 2);
			return returnObject;
		}else{
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
			List<BetMember> datas=null;
			datas=betMemberService.findListDataByFinder(new Finder("select * from bet_member a where (agentid=:id or agentparentids like :agentid) and signdate>=:starttime and signdate<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"),page,BetMember.class,betMember);
			if(datas!=null){
				for (BetMember betMember2 : datas) {
					Integer exp = betMember2.getExp();
					if(exp!=null){
						for (BetVip betVip : betVipList) {
							if(betVip.getBottom()<=exp&&betVip.getTop()>=exp){
								betMember2.setLevel(betVip.getLevel());
							}
						}
					}
					betMember2.setAgentnickname(betAgentService.queryForObject(new Finder("select nickname from bet_agent where agentid=:agentid ").setParam("agentid", betMember2.getAgentid()), String.class));
					//隐藏用户私密信息
					changeHideName(agag, company, betMember2);
				}
			}
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
			returnObject.setQueryBean(betMember);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}
	}

	//隐藏用户List私密信息
	private void changeHideNameList(BetAgent agag, String company, List<BetMember> subordinatelist) {
		if(!company.equals("zhouyunfei")){
			if(!"A101".equals(agag.getParentid())){//不是一级代理或者子账号，手机号码和身份证号码都要隐藏部分
				if(subordinatelist!=null){
					for (BetMember betMember2 : subordinatelist) {
						if(!agag.getAgentid().equals(betMember2.getAgentid())){//代理可以看到自己的会员手机号，不能看到其他代理的会员手机号
							//隐藏手机号码
							if(betMember2.getMobile()!=null && betMember2.getMobile().length()>=11){
								betMember2.setMobile(betMember2.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
							}
							//隐藏手机号码
							if(betMember2.getAccount()!=null && betMember2.getAccount().length()>=11){
								betMember2.setAccount(betMember2.getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
							}
						}
						//隐藏身份证号码
						if(betMember2.getIdno()!=null && betMember2.getIdno().length()>=18){
							betMember2.setIdno(betMember2.getIdno().replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2"));
						}
						//隐藏用户真实姓名
						if(betMember2.getRealname()!=null){
							if (betMember2.getRealname().length() <= 1) {        
								betMember2.setRealname("*");
							} else {        
								betMember2.setRealname(betMember2.getRealname().replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1***"));
							}
						}
					}
				}
			}
		}
	}

	//隐藏用户私密信息
	private void changeHideName(BetAgent agag, String company, BetMember betMember2) {
		if(!company.equals("zhouyunfei")){
			if(!"A101".equals(agag.getParentid())){//不是一级代理或者子账号，手机号码和身份证号码都要隐藏部分
				if(!agag.getAgentid().equals(betMember2.getAgentid())){//代理可以看到自己的会员手机号，不能看到其他代理的会员手机号
					//隐藏手机号码
					if(betMember2.getMobile()!=null && betMember2.getMobile().length()>=11){
						betMember2.setMobile(betMember2.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
					}
					//隐藏手机号码
					if(betMember2.getAccount()!=null && betMember2.getAccount().length()>=11){
						betMember2.setAccount(betMember2.getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
					}
				}

				//隐藏身份证号码
				if(betMember2.getIdno()!=null && betMember2.getIdno().length()>=18){
					betMember2.setIdno(betMember2.getIdno().replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2"));
				}
				//隐藏用户真实姓名
				if(betMember2.getRealname()!=null){
					if (betMember2.getRealname().length() <= 1) {        
						betMember2.setRealname("*");
					} else {        
						betMember2.setRealname(betMember2.getRealname().replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1***"));
					}
				}
			}
		}
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetMember betMember) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betMemberService.findDataExportExcel(null,listurl, page,BetMember.class,betMember);
		String fileName="betMember"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		if("1".equals(request.getParameter("k"))){
			//用户权限管理
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			List<BetMemberbankcard> betMemberbankcardList = betMemberbankcardService.queryForList(new Finder("select *from bet_memberbankcard where (agentid=:id or agentparentids like :agentid) and memberid2=:memberid2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid2", request.getParameter("id2")), BetMemberbankcard.class);
			BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
			String company = "";//一级代理
			company = getCompany(agag, company);
			//隐藏用户提现重要信息
			getMemberbankcard(company, agag, betMemberbankcardList);
			model.addAttribute("betMemberbankcardList", betMemberbankcardList);
			return "/lottery/betmember/betmemberauthorization";
		}else if("2".equals(request.getParameter("k"))){
			//每日流水
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(20);
			String id = request.getParameter("id");
			List<Map<String, Object>> maplist = soccerAllbettingService.queryForList(new Finder("select substr(a.bettingtime,1,10) as date,SUM(a.bettingmoney) as money,sum(a.bettingscore) as bettingwin from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where b.id=:id group by substr(a.bettingtime,1,10) ORDER BY substr(a.bettingtime,1,10) desc ").setParam("id", id),page);
			returnObject1.setData(maplist);
			returnObject1.setPage(page);
			model.addAttribute(GlobalStatic.returnDatas, returnObject1);
			model.addAttribute("id", id);
			return "/lottery/betmember/betbettingList2";
		}else if("3".equals(request.getParameter("k"))){
			//每日流水点击进入明细
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("bettingtime");
			page.setSort("desc");
			String id = request.getParameter("id");
			String daybettingdate = request.getParameter("daybettingdate");
			String type = request.getParameter("type");
			if(type==null){
				type = "100";
			}
			if("0".equals(type)){
				List<BetBetting> betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2 from bet_betting a left join bet_gameplay b on a.gameplayid=b.id where a.memberid=:memberid and substr(a.bettingtime,1,10)=:daybettingdate ").setParam("daybettingdate", daybettingdate).setParam("memberid", id),BetBetting.class,page);
				returnObject1.setData(betbettinglist);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("id", id);
				model.addAttribute("type", type);
				model.addAttribute("daybettingdate", daybettingdate);
				return "/lottery/betmember/betbettingList3";
			}else if("1".equals(type)){
				List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 where c.id=:id and substr(a.bettingtime,1,10)=:daybettingdate ").setParam("daybettingdate", daybettingdate).setParam("id", id),SoccerScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerScheme soccerScheme2 : datas) {
						String schemeid = soccerScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(soccerScheme2.getSchemeid());
						}
					}
					List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("id", id);
				model.addAttribute("type", type);
				model.addAttribute("daybettingdate", daybettingdate);
				return "/lottery/betmember/soccerbettingList2";
			}else{
				List<SoccerAllbetting> datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where b.id=:id and substr(a.bettingtime,1,10)=:daybettingdate and (:type=100 or a.type=:type)").setParam("type", type).setParam("id", id).setParam("daybettingdate", daybettingdate),SoccerAllbetting.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(1==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					if(!schemeids.isEmpty()){
					List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
				//篮球处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(3==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					if(!schemeids.isEmpty()){
					List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in(:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
				
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("id", id);
				model.addAttribute("type", type);
				model.addAttribute("daybettingdate", daybettingdate);
				return "/lottery/betmember/allsoccerbettingList2";
			}
			
			/*
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("bettingtime");
			page.setSort("desc");
			String id = request.getParameter("id");
			String daybettingdate = request.getParameter("daybettingdate");
			List<BetBetting> betbettinglist = betBettingService.queryForList(new Finder("select * from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and substr(bettingtime,1,10)=:daybettingdate ").setParam("daybettingdate", daybettingdate).setParam("memberid", id).setParam("id", agentid).setParam("agentid", "%"+agentid+"%"),BetBetting.class,page);
			returnObject1.setData(betbettinglist);
			returnObject1.setPage(page);
			model.addAttribute(GlobalStatic.returnDatas, returnObject1);
			model.addAttribute("id", id);
			model.addAttribute("daybettingdate", daybettingdate);
			return "/lottery/betmember/betbettingList3";
			*/
		}else{
			
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			String id = request.getParameter("id");
			String id2 = request.getParameter("id2");
			if(id==null&&id2!=null){
				BetMember betMember=new BetMember();
				betMember.setId2(Integer.valueOf(id2));
				BetMember betMember2 = betMemberService.queryForObject(betMember);
				id=betMember2.getId();
			}
//			String operation="";
//			List<BetBetting> betBettingList = betBettingService.findListDataByFinder(new Finder("select*from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and DATEDIFF(bettingtime,CURDATE())=0 ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%").setParam("memberid", id), null, BetBetting.class, null);
//			Set<String> gcnameset=new HashSet<String>();
//			//所在游戏
//			for (BetBetting betBetting2 : betBettingList) {
//				String gcname= betBetting2.getGcname();
//				gcnameset.add(gcname);
//			}
//			for (String string : gcnameset) {
//				if("".equals(operation)){
//					operation+=string;
//				}else{
//					operation+="|"+string;
//				}
//			}
			String operation=soccerAllbettingService.queryForObject(new Finder("select GROUP_CONCAT(distinct gcname) from soccer_allbetting where memberid2=:memberid2 and state =0 ").setParam("memberid2", id2), String.class);
			//推荐收益
			Double subordinaterebate = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where (agentid=:id or agentparentids like :agentid) and parentmemberid2=:memberid2  ").setParam("memberid2", Integer.valueOf(id2)).setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"), Double.class);
			model.addAttribute("subordinaterebate", subordinaterebate);
			//投注总流水
			//Double currentbettingmoney = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and state=0 ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%").setParam("memberid", id), Double.class);
			Double currentbettingmoney = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where  memberid2=:memberid2 and state=0 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("currentbettingmoney", currentbettingmoney);
			//Double totalbettingmoney = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%").setParam("memberid", id), Double.class);
			Double totalbettingmoney = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where  memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			//Double todaybettingmoney = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and DATEDIFF(bettingtime,CURDATE())=0 ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%").setParam("memberid", id), Double.class);
			Double todaybettingmoney = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where  memberid2=:memberid2 and DATEDIFF(bettingtime,CURDATE())=0 ").setParam("memberid2", id2), Double.class);
			//Double yesterdaybettingmoney = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and (TO_DAYS(now())-TO_DAYS(bettingtime))=1 ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%").setParam("memberid", id), Double.class);
			Double yesterdaybettingmoney = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where  memberid2=:memberid2 and (TO_DAYS(now())-TO_DAYS(bettingtime))=1 ").setParam("memberid2", id2), Double.class);
			BetMemberbankcard betMemberbankcard=new BetMemberbankcard();
			betMemberbankcard.setMemberid2(Integer.valueOf(id2));
			//List<BetMemberbankcard> betMemberbankcardList = betMemberbankcardService.findListDataByFinder(new Finder("select * from bet_memberbankcard where agentid=:id or agentparentids like :agentid").setParam("id", agentid).setParam("agentid", "%"+agentid+"%"), null, BetMemberbankcard.class, betMemberbankcard);
			List<BetMemberbankcard> betMemberbankcardList = betMemberbankcardService.findListDataByFinder(null, null, BetMemberbankcard.class, betMemberbankcard);
			BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
			String company = "";//一级代理
			company = getCompany(agag, company);
			//隐藏用户提现重要信息
			getMemberbankcard(company, agag, betMemberbankcardList);
			
			//重庆时时彩输赢
			Double cqsscwinorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=7 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			//新疆时时彩输赢
			Double xjsscwinorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=8 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("xjsscwinorloss", xjsscwinorloss);
			//新加坡5D输赢
			Double xjp5dwinorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=9 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("xjp5dwinorloss", xjp5dwinorloss);
			//北京11输赢
			Double bj11winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=10 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("bj11winorloss", bj11winorloss);
			//北京16输赢
			Double bj16winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=11 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("bj16winorloss", bj16winorloss);
			//北京28输赢
			Double bj28winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=5 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("bj28winorloss", bj28winorloss);
			//北京36输赢
			Double bj36winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=12 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("bj36winorloss", bj36winorloss);
			//加拿大11输赢
			Double jnd11winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=16 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("jnd11winorloss", jnd11winorloss);
			//加拿大16输赢
			Double jnd16winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=17 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("jnd16winorloss", jnd16winorloss);
			//加拿大28输赢
			Double jnd28winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=6 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("jnd28winorloss", jnd28winorloss);
			//加拿大36输赢
			Double jnd36winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=18 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("jnd36winorloss", jnd36winorloss);
			//蛋蛋28输赢
			Double dd28winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=19 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("dd28winorloss", dd28winorloss);
			//蛋蛋36输赢
			Double dd36winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=20 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("dd36winorloss", dd36winorloss);
			//新加坡11输赢
			Double xjp11winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=21 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("xjp11winorloss", xjp11winorloss);
			//新加坡16输赢
			Double xjp16winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=22 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("xjp16winorloss", xjp16winorloss);
			//新加坡28输赢
			Double xjp28winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=23 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("xjp28winorloss", xjp28winorloss);
			//新加坡36输赢
			Double xjp36winorloss = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and gameclassid=24 and state=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", id), Double.class);
			model.addAttribute("xjp36winorloss", xjp36winorloss);
			//足球
//			Double soccerwinorloss = soccerLeagueOrderService.queryForObject(new Finder("select sum(bettingwin-bettingmoney) from soccer_league_order where memberid2=:memberid2 and (result=1 or result=3) ").setParam("memberid2", id2),Double.class);
			Double soccerwinorloss = soccerSchemeService.queryForObject(new Finder("select sum(bettingwin-bettingmoney) from soccer_scheme where memberid2=:memberid2 and situation=1  ").setParam("memberid2", id2),Double.class);
			model.addAttribute("soccerwinorloss", soccerwinorloss);
			
			//转账
			Double membertransferaccounts = betTransferAccountsService.queryForObject(new Finder("select sum(transferaccountsscore) from bet_transfer_accounts where memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			//点卡充值
//			Double memberrechargecard = betRechargecardService.queryForObject(new Finder("select sum(money) from bet_rechargecard where state=1 and memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			Double membergold = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid ").setParam("memberid", id), Double.class);
			Double memberwithdrawcash = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where state=2 and memberid=:memberid ").setParam("memberid", id), Double.class);
			Double memberredenvelope = betRedenvelopeRecordService.queryForObject(new Finder("select sum(receivescore) from bet_redenvelope_record where state=0 and memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("memberredenvelope",memberredenvelope);
//			Double memberwinorfail = betMemberService.queryForObject(new Finder("select winorfail from bet_member where id=:memberid ").setParam("memberid", id), Double.class);
//			Double memberwinorfail = betBettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from bet_betting where memberid=:memberid and state=1 ").setParam("memberid", id), Double.class);
			Double memberwinorfail = soccerAllbettingService.queryForObject(new Finder("select sum(bettingscore-bettingmoney) from soccer_allbetting where  memberid2 = :memberid2 and state = 1").setParam("memberid2", id2),Double.class);
			
			//兑换
//			Double exchangerechargecard = betRechargecardService.queryForObject(new Finder("select sum(money) from bet_rechargecard where exchangeid2=:exchangeid2 ").setParam("exchangeid2", id2), Double.class);
//			model.addAttribute("exchangerechargecard", exchangerechargecard);
			//签到
			Double signinreward = betSigninRewardService.queryForObject(new Finder("select sum(reward) from bet_signin_reward where memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("signinreward", signinreward);
			//首充
			Double firstrechargerebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate where memberid2=:memberid2 and state=1 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("firstrechargerebate", firstrechargerebate);
			//推广
			Double subordinatedetailrebate = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail where parentmemberid2=:memberid2 and state=1  ").setParam("memberid2", id2), Double.class);
			model.addAttribute("subordinatedetailrebate", subordinatedetailrebate);
			//救济
			Double reliefrecord = betReliefRecordService.queryForObject(new Finder("select sum(reliefscore) from bet_relief_record where memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("reliefrecord", reliefrecord);
			//排行榜
			Double rankmemberreward = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member where memberid=:memberid and state=1 ").setParam("memberid", id),Double.class);
			model.addAttribute("rankmemberreward",rankmemberreward);
			//注册送
			Double registerreward = betRegisterRewardService.queryForObject(new Finder("select sum(reward) from bet_register_reward where memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("registerreward", registerreward);
			//当日输赢返利
			Double daywinorfailrebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_daywinorfailrebate where state=1 and memberid2=:memberid2 ").setParam("memberid2", id2),Double.class);
			model.addAttribute("daywinorfailrebate", daywinorfailrebate);
			//周输赢返利
			Double weekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(rebate) from bet_weekwinorfailrebate where state=1 and memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("weekwinorfailrebate", weekwinorfailrebate);
			//当日充值
			Double todayrechargerebate = betTodayrechargerebateService.queryForObject(new Finder("select sum(reward) from bet_todayrechargerebate where state=1 and memberid2=:memberid2  ").setParam("memberid2", id2), Double.class);
			model.addAttribute("todayrechargerebate", todayrechargerebate);
			//单笔充值
			Double todaysinglerecharge = betSinglerechargeService.queryForObject(new Finder("select sum(rebate) from bet_singlerecharge  where state=1 and memberid2=:memberid2 ").setParam("memberid2", id2), Double.class);
			model.addAttribute("todaysinglerecharge", todaysinglerecharge);
			
			//投注退佣
			Double summemberty = betBettingService.queryForObject(new Finder("select sum(memberty) from bet_betting  where memberid=:memberid and membertystate=1 ").setParam("memberid", id),Double.class);
			model.addAttribute("summemberty", summemberty);
			if(membertransferaccounts==null){
				membertransferaccounts=0.;
			}
			if(membergold==null){
				membergold=0.;
			}
			if(memberwithdrawcash==null){
				memberwithdrawcash=0.;
			}
			if(memberredenvelope==null){
				memberredenvelope=0.;
			}
			if(registerreward==null){
				registerreward=0.;
			}
			if(daywinorfailrebate==null){
				daywinorfailrebate=0.;
			}
			if(subordinatedetailrebate==null){
				subordinatedetailrebate=0.;
			}
			if(weekwinorfailrebate==null){
				weekwinorfailrebate=0.;
			}
			if(firstrechargerebate==null){
				firstrechargerebate=0.;
			}
			if(todayrechargerebate==null){
				todayrechargerebate=0.;
			}
			if(signinreward==null){
				signinreward=0.;
			}
			if(rankmemberreward==null){
				rankmemberreward=0.;
			}
			if(todaysinglerecharge==null){
				todaysinglerecharge=0.;
			}
			if(reliefrecord==null){
				reliefrecord=0.;
			}
			if(memberwinorfail==null){
				memberwinorfail=0.;
			}
			if(summemberty==null){
				summemberty=0.;
			}
			//Double reresult=membertransferaccounts+membergold*1000-memberwithdrawcash*1000+memberredenvelope+registerreward+daywinorfailrebate+subordinatedetailrebate+weekwinorfailrebate+firstrechargerebate+todayrechargerebate+signinreward+rankmemberreward+todaysinglerecharge+reliefrecord+memberwinorfail+summemberty;
			Double reresult=membertransferaccounts+membergold-memberwithdrawcash+memberredenvelope+registerreward+subordinatedetailrebate+firstrechargerebate+todayrechargerebate+todaysinglerecharge+memberwinorfail+summemberty;
			model.addAttribute("reresult", reresult);
			//最近登陆
			List<BetMemberloginlog> memberloginloglist = betMemberloginlogService.queryForList(new Finder("select *from bet_memberloginlog where memberid2=:memberid2 order by time desc limit 0,10 ").setParam("memberid2", id2), BetMemberloginlog.class);
			List<BetGold> membergoldlist = betGoldService.queryForList(new Finder("select *from bet_gold where memberid=:memberid and state=2 order by submittime desc limit 0,10 ").setParam("memberid", id), BetGold.class);
			
			List<BetWithdrawcash> memberwithdrawcashlist = betWithdrawcashService.queryForList(new Finder("select *from bet_withdrawcash where memberid=:memberid and state=2 order by applicationtime desc limit 0,10 ").setParam("memberid", id), BetWithdrawcash.class);
			String agentdetail = betAgentService.queryForObject(new Finder("select concat(b.account,:xxx,b.nickname,:xxxx) from bet_member a left join bet_agent b on a.agentid=b.agentid where a.id2=:memberid2 ").setParam("xxx", "(").setParam("xxxx", ")").setParam("memberid2", id2),String.class);
			model.addAttribute("agentdetail", agentdetail);
			model.addAttribute("memberwithdrawcashlist",memberwithdrawcashlist);
			model.addAttribute("membergoldlist",membergoldlist);
			model.addAttribute("memberloginloglist",memberloginloglist);
			model.addAttribute("memberwinorfail",memberwinorfail);
			model.addAttribute("memberwithdrawcash",memberwithdrawcash);
			model.addAttribute("membergold",membergold);
			//model.addAttribute("memberrechargecard",memberrechargecard);
			model.addAttribute("membertransferaccounts",membertransferaccounts);
			model.addAttribute("cqsscwinorloss", cqsscwinorloss);
			model.addAttribute("betMemberbankcardList", betMemberbankcardList);
			model.addAttribute("totalbettingmoney", totalbettingmoney);
			model.addAttribute("todaybettingmoney", todaybettingmoney);
			model.addAttribute("yesterdaybettingmoney", yesterdaybettingmoney);
			model.addAttribute("operation", operation);
			
//			BetAgent agent = betAgentService.queryForObject(new Finder("select b.account,b.nickname from bet_member a left join bet_agent b on a.agentid=b.agentid where a.id2=:agentid ").setParam("agentid",id2), BetAgent.class);
//			model.addAttribute("agentdetail", agent);
//			BetAgent parentids = betAgentService.queryForObject(new Finder("select agentparentids from bet_member where id2=:id2 ").setParam("id2",id2), BetAgent.class);
//			model.addAttribute("parentids", parentids);
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_member a left join bet_agent b on a.agentid=b.agentid where a.id2=:agentid ").setParam("agentid",id2), BetAgent.class);
			model.addAttribute("agentdetail1", agent.getAccount());
			model.addAttribute("agentdetail2", agent.getNickname());
			String parentid = betAgentService.queryForObject(new Finder("select parentid from bet_agent where agentid=:agentid ").setParam("agentid",agentid), String.class);
			if(parentid.equals("A101")){
				model.addAttribute("agentdetail3", agent.getParentids());
			}
			model.addAttribute("agentparentid", parentid);
			return "/lottery/betmember/betmemberLook";
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

	 @RequestMapping("/updatenickname")
	  	public @ResponseBody
	  	ReturnDatas updatenickname(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
	  		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
	  		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
	  		try {

	  			Integer id2 = betMember.getId2();
	  			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
	  			betMember2.setNickname(betMember.getNickname());
	  			BetMember betMember3=new BetMember();
	  			betMember3.setId(betMember2.getId());
	  			betMember3.setNickname(betMember.getNickname());
	  			betMemberService.update(betMember3, true);
	  			//清除缓存
	  			try{
	  				if(betMember2.getTicket()!=null){
	  					//更新缓存
	  					String ticket = betMember2.getTicket();
	  					if(ticket!=null){
	  						try{
//	  							cached.deleteCached(("TICKET_"+ticket).getBytes());
	  							ObjectMapper mapper=new ObjectMapper();
	  							byte[] json = mapper.writeValueAsBytes(betMember2);
	  							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
	  						}catch(Exception e){
	  							String errorMessage = e.getLocalizedMessage();
	  							logger.error(errorMessage,e);
	  						}
	  						
	  					}
	  				}
	  			}catch(Exception e){
	  				e.printStackTrace();
	  			}
	  			//操作日志
	  			 String details = "";
	  		     details = "更新ID为"+id2+"的用户的昵称为："+betMember.getNickname();
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
		 * 
		 * 
		 * @Title: updateHeadimg
		 * 
		 * @Description: TODO 修改头像
		 * 
		 * @param model 
		 * @param betMember
		 *  @param request 
		 *  @param response
		 *  @return 
		 *  @throws
		 * Exception
		 * 
		 * ReturnDatas 返回类型
		 * 
		 * @throws
		 */
		@RequestMapping("/updateheadimg")
		public @ResponseBody ReturnDatas updateHeadimg(Model model, BetMember betMember, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		 	String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			String company = "";//一级代理
			company = getCompany(betagent, company);
			  
	  		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
	  		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
	  		try {

	  			Integer id2 = betMember.getId2();
	  			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
	  			betMember2.setNickname(betMember.getNickname());
	  			BetMember betMember3=new BetMember();
	  			betMember3.setId(betMember2.getId());
	  			//String server = betMember2.getAvatar().substring(0, betMember2.getAvatar().indexOf("api")+4);
				betMember3.setAvatar(serverapi+"img/portrait/default_head_"+company+".png");
				betMemberService.update(betMember3, true);
	  			//清除缓存
	  			try{
	  				if(betMember2.getTicket()!=null){
	  					//更新缓存
	  					String ticket = betMember2.getTicket();
	  					if(ticket!=null){
	  						try{
//	  							cached.deleteCached(("TICKET_"+ticket).getBytes());
	  							ObjectMapper mapper=new ObjectMapper();
	  							byte[] json = mapper.writeValueAsBytes(betMember2);
	  							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
	  						}catch(Exception e){
	  							String errorMessage = e.getLocalizedMessage();
	  							logger.error(errorMessage,e);
	  						}
	  						
	  					}
	  				}
	  			}catch(Exception e){
	  				e.printStackTrace();
	  			}
	  			//操作日志
	  			 String details = "";
	  		     details = "更新ID为"+id2+"的用户的昵称为："+betMember.getNickname();
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
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id2 = request.getParameter("id2");
		if(StringUtils.isNotBlank(id2)){
		  BetMember betMember = betMemberService.queryForObject(new Finder("select*from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
		  if(betMember!=null){
			  Integer exp = betMember.getExp();
			  if(exp==null){
				  exp=0;
			  }
			  Finder finder =new Finder("select *from bet_vip where bottom <=:exp and top>=:exp ").setParam("exp", exp);
			  BetVip betVip = betVipService.queryForObject(finder, BetVip.class);
			  betMember.setLevel( betVip.getLevel());
			 //转账检测添加投注中金额
			  if("1".equals(request.getParameter("currentbetting"))){
				  Double currentbettingmoney = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where (agentid=:id or agentparentids like :agentid) and memberid=:memberid and state=0 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("memberid", betMember.getId()), Double.class);
				  if(currentbettingmoney==null){
					  betMember.setCurrentBettingMoney(0.);
				  }else{
					  betMember.setCurrentBettingMoney(currentbettingmoney);
				  }
				  
			  }
			  if(betMember.getGameauthorization()==null){
				  betMember.setGameauthorization("");
			  }
			  if(betMember.getAgentauthorization()==null){
				  betMember.setAgentauthorization("");
			  }
			  if(betMember.getFinanceauthorization()==null){
				  betMember.setFinanceauthorization("");
			  }
			  
			  BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),BetAgent.class);
			  String company = "";//一级代理
			  company = getCompany(agag, company);
			  //隐藏用户私密信息
			  changeHideName(agag, company, betMember);

			  returnObject.setData(betMember);
		  }else{
			  returnObject.setStatus(ReturnDatas.ERROR);
		  }
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
	ReturnDatas saveorupdate(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if((StringUtils.isNoneBlank(betMember.getId())||betMember.getId2()!=null)&&StringUtils.isNoneBlank(betMember.getRealname())){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				return returnObject;
			}
			if("1".equals(request.getParameter("updatemobile"))){
//				Integer id2 = betMember.getId2();
//				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
//				String mobile = betMember.getMobile();
//				betMember2.setMobile(mobile);
//				BetMember betMember3=new BetMember();
//				betMember3.setId(betMember2.getId());
//				betMember3.setMobile(mobile);
//				betMemberService.update(betMember3, true);
//				//清除缓存
//				try{
//					if(betMember2.getTicket()!=null){
//						//更新缓存
//						String ticket = betMember2.getTicket();
//						if(ticket!=null){
//							try{
//								ObjectMapper mapper=new ObjectMapper();
//								byte[] json = mapper.writeValueAsBytes(betMember2);
//								
//								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
//							}catch(Exception e){
//								String errorMessage = e.getLocalizedMessage();
//								logger.error(errorMessage,e);
//							}
//							
//						}
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				//操作日志
//				 String details = "";
//			     details = "更新ID为"+id2+"的用户的手机号";
//			     String ip = IPUtils.getClientAddress(request);
//			     String tool = AgentToolUtil.getAgentTool(request);
//			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else if("1".equals(request.getParameter("updateqq"))){
//				Integer id2 = betMember.getId2();
//				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
//				String qq = betMember.getQq();
//				betMember2.setQq(qq);
//				BetMember betMember3=new BetMember();
//				betMember3.setId(betMember2.getId());
//				betMember3.setQq(qq);
//				betMemberService.update(betMember3, true);
//				//清除缓存
//				try{
//					if(betMember2.getTicket()!=null){
//						//更新缓存
//						String ticket = betMember2.getTicket();
//						if(ticket!=null){
//							try{
////								cached.deleteCached(("TICKET_"+ticket).getBytes());
//								ObjectMapper mapper=new ObjectMapper();
//								byte[] json = mapper.writeValueAsBytes(betMember2);
//								
//								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
//							}catch(Exception e){
//								String errorMessage = e.getLocalizedMessage();
//								logger.error(errorMessage,e);
//							}
//							
//						}
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				//操作日志
//				 String details = "";
//			     details = "更新ID为"+id2+"的用户的qq";
//			     String ip = IPUtils.getClientAddress(request);
//			     String tool = AgentToolUtil.getAgentTool(request);
//			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else if("1".equals(request.getParameter("updatepassword"))){
//				Integer id2 = betMember.getId2();
//				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
//				String md5Hex = DigestUtils.md5Hex(betMember.getPassword());
//				BetMember betMember3=new BetMember();
//				betMember3.setId(betMember2.getId());
//				betMember3.setPassword(md5Hex);
//				betMemberService.update(betMember3, true);
//				//清除缓存
//				try{
//					if(betMember2.getTicket()!=null){
//						cached.deleteCached(betMember2.getTicket().getBytes());
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				//操作日志
//				 String details = "";
//			     details = "更新ID为"+id2+"的用户的登录密码";
//			     String ip = IPUtils.getClientAddress(request);
//			     String tool = AgentToolUtil.getAgentTool(request);
//			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else if("1".equals(request.getParameter("updateexp"))){
				Integer id2 = betMember.getId2();
				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
				betMember2.setExp(betMember.getExp());
				BetMember betMember3=new BetMember();
				betMember3.setId(betMember2.getId());
				betMember3.setExp(betMember.getExp());
				betMemberService.update(betMember3, true);
				//清除缓存
				try{
					if(betMember2.getTicket()!=null){
						//更新缓存
						String ticket = betMember2.getTicket();
						if(ticket!=null){
							try{
//								cached.deleteCached(("TICKET_"+ticket).getBytes());
								ObjectMapper mapper=new ObjectMapper();
								byte[] json = mapper.writeValueAsBytes(betMember2);
								
								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
							}catch(Exception e){
								String errorMessage = e.getLocalizedMessage();
								logger.error(errorMessage,e);
							}
							
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				//操作日志
				 String details = "";
			     details = "更新ID为"+id2+"的用户的经验为："+betMember.getExp();
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else if("1".equals(request.getParameter("updatetx"))){
				Integer id2 = betMember.getId2();
				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id2", id2).setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"), BetMember.class);
				Double tx = betMember.getTx();
				betMember2.setTx(tx/100.);
				BetMember betMember3=new BetMember();
				betMember3.setId(betMember2.getId());
				betMember3.setTx(tx/100.);
				betMemberService.update(betMember3, true);
				//清除缓存
				try{
					if(betMember2.getTicket()!=null){
						//更新缓存
						String ticket = betMember2.getTicket();
						if(ticket!=null){
							try{
								ObjectMapper mapper=new ObjectMapper();
								byte[] json = mapper.writeValueAsBytes(betMember2);
								
								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
							}catch(Exception e){
								String errorMessage = e.getLocalizedMessage();
								logger.error(errorMessage,e);
							}
							
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				//操作日志
				 String details = "";
			     details = "更新ID为"+id2+"的用户的退税";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else if("1".equals(request.getParameter("updatepassword2"))){
//				Integer id2 = betMember.getId2();
//				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
//				BetMember betMember3=new BetMember();
//				betMember3.setId(betMember2.getId());
//				betMember3.setPassword2(DigestUtils.md5Hex(betMember.getPassword2()));
//				betMemberService.update(betMember3, true);
//				//清除缓存
//				try{
//					if(betMember2.getTicket()!=null){
//						cached.deleteCached(betMember2.getTicket().getBytes());
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				//操作日志
//				 String details = "";
//			     details = "更新ID为"+id2+"的用户的银行密码";
//			     String ip = IPUtils.getClientAddress(request);
//			     String tool = AgentToolUtil.getAgentTool(request);
//			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());			
			 }else if("1".equals(request.getParameter("updateauthorization"))){
				
			    String a = request.getParameter("a");
				String b = request.getParameter("b");
				Integer id21 = betMember.getId2();
				BetMember betMember1 = betMemberService.queryForObject(new Finder("select *from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2",id21),BetMember.class);
				String authorization=null;
				String c = request.getParameter("c");
				if("1".equals(c)){
					authorization = betMember1.getGameauthorization();
				}else if("2".equals(c)){
					authorization = betMember1.getAgentauthorization();
				}else if("3".equals(c)){
					authorization = betMember1.getFinanceauthorization();
				}else{
					throw new Exception();
				}
				if(authorization==null){
					authorization="";
				}
				String[] split = authorization.split(",");
				List<String> asList = Arrays.asList(split);
				List<String> list=new LinkedList<String>(asList);
				
				if("true".equals(b)){
					if(!list.contains(a)){
						list.add(a);
						int size = list.size();  
						String[] array = list.toArray(new String[size]);
						String join = StringUtils.join(array, ",");
						if("1".equals(request.getParameter("c"))){
							betMember.setGameauthorization(join);
						}else if("2".equals(request.getParameter("c"))){
							betMember.setAgentauthorization(join);
						}else if("3".equals(request.getParameter("c"))){
							betMember.setFinanceauthorization(join);
						}else{
							throw new Exception();
						}
						
						betMemberService.update(betMember,true);
						//清除缓存
						String ticket = betMember1.getTicket();
						if(ticket!=null){
							try{
//								cached.deleteCached(("TICKET_"+ticket).getBytes());
								ObjectMapper mapper=new ObjectMapper();
								byte[] json = mapper.writeValueAsBytes(betMember1);
								
								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
							}catch(Exception e){
								String errorMessage = e.getLocalizedMessage();
								logger.error(errorMessage,e);
							}
							
						}
						//操作日志
						 String details = "";
						 if("1".equals(c)){
							 details = "更新ID为"+betMember.getId2()+"的用户的游戏权限";
						 }else if("2".equals(c)){
							 details = "更新ID为"+betMember.getId2()+"的用户的代理权限";
						 }else if("3".equals(c)){
							 details = "更新ID为"+betMember.getId2()+"的用户的财务权限";
						 }
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
					}
				}else if("false".equals(b)){
					if(list.contains(a)){
						LinkedList<String> linkedList = new LinkedList<String>();
						linkedList.add(a);
						list.removeAll(linkedList);
						int size=list.size();
						String[] array=list.toArray(new String[size]);
						String join=StringUtils.join(array,",");
						if("1".equals(request.getParameter("c"))){
							betMember.setGameauthorization(join);
						}else if("2".equals(request.getParameter("c"))){
							betMember.setAgentauthorization(join);
						}else if("3".equals(request.getParameter("c"))){
							betMember.setFinanceauthorization(join);
						}else{
							throw new Exception();
						}
						betMemberService.update(betMember,true);
						//清除缓存
						try{
							if(betMember1.getTicket()!=null){
								cached.deleteCached(betMember1.getTicket().getBytes());
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						//操作日志
						 String details = "";
					     details = "更新ID为"+betMember.getId2()+"的用户的权限";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
					}
				}else{
					throw new Exception();
				}
				
			}else if("1".equals(request.getParameter("internalorfreeze"))){
				String a = request.getParameter("a");
				String d = request.getParameter("d");
				String e = request.getParameter("e");
				String[] idList = d.split(",");
				if("internal".equals(a)){
					if(idList!=null){
						for (String id : idList) {
							BetMember betMember3 = new BetMember();
							betMember3.setId(id);
							betMember3.setIsinternal(1);
							betMember3.setInternaladdtime(new java.util.Date());
							ShiroUser shiroUser = SessionUser.getShiroUser();
							String account = shiroUser.getAccount();
							String name = shiroUser.getName();
							if(name==null){
								name="";
							}
							betMember3.setInternaloperator(account+"("+name+")");
							betMemberService.update(betMember3, true);
							BetMember betmember = betMemberService.queryForObject(new Finder("select*from bet_member where (agentid=:aid or agentparentids like :agentid) and id=:id").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id", id),BetMember.class);
							Integer id2 = betmember.getId2();
							//清除缓存
							try{
								if(betmember.getTicket()!=null){
									cached.deleteCached(betmember.getTicket().getBytes());
								}
							}catch(Exception ex){
								ex.printStackTrace();
							}
							//操作日志
							 String details = "";
						     details = "将ID为"+id2+"的用户改为内部用户";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}
						
					}
				}else if("freeze".equals(a)){
					if(idList!=null){
						for (String id : idList) {
							BetMember betMember3 = new BetMember();
							betMember3.setId(id);
							betMember3.setStatus(0);
							betMember3.setFreezeaddtime(new java.util.Date());
							betMember3.setFreezeremark(e);
							ShiroUser shiroUser = SessionUser.getShiroUser();
							String account = shiroUser.getAccount();
							String name = shiroUser.getName();
							if(name==null){
								name="";
							}
							betMember3.setFreezeoperator(account+"("+name+")");
							betMemberService.update(betMember3, true);
							BetMember betmember = betMemberService.queryForObject(new Finder("select*from bet_member where (agentid=:aid or agentparentids like :agentid) and id=:id").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id", id),BetMember.class);
							Integer id2 = betmember.getId2();
							//清除缓存
							try{
								if(betmember.getTicket()!=null){
									cached.deleteCached(("TICKET_"+betmember.getTicket()).getBytes());
								}
							}catch(Exception e1){
								e1.printStackTrace();
							}
							//操作日志
							 String details = "";
							 details = "将ID为"+id2+"的用户改为冻结用户";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}
					}
				}else if("changeagent".equals(a)){
					//转移用户
					String ag = SessionUser.getShiroUser().getAgentid();
					BetAgent agag = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", ag),BetAgent.class);
					if(!"A101".equals(agag.getParentid())){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("只有一级代理可以转移用户");
						return returnObject;
					}
					
					String agentaccount = request.getParameter("agentaccount");
					BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where account=:account ").setParam("account", agentaccount), BetAgent.class);
					if(agent==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("无此代理账号");
						return returnObject;
					}
					if(idList!=null){
						for (String id : idList) {
							BetMember betMember3 = new BetMember();
							betMember3.setId(id);
							betMember3.setAgentid(agent.getAgentid());
							betMember3.setAgentparentid(agent.getParentid());
							betMember3.setAgentparentids(agent.getParentids());
							
							BetMember betmember = betMemberService.queryForObject(new Finder("select*from bet_member where (agentid=:aid or agentparentids like :agentid) and id=:id").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id", id),BetMember.class);
							Integer id2 = betmember.getId2();
							betMemberService.update(betMember3, true);
							//清除缓存
							try{
								if(betmember.getTicket()!=null){
									cached.deleteCached(("TICKET_"+betmember.getTicket()).getBytes());
								}
							}catch(Exception e1){
								e1.printStackTrace();
							}
							//操作日志
							 String details = "";
							 details = "将ID为"+id2+"的用户的代理ID由"+betmember.getAgentid()+"转移至"+agent.getAgentid();
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					return returnObject;
				}
//				String id = betMemberService.queryForObject(new Finder("select id from bet_member where id2=:id2 ").setParam("id2", id2), String.class);
//				betMember.setId(id);
//				betMemberService.update(betMember, true);
			}else if("1".equals(request.getParameter("unfreeze"))){
				BetMember betMember3 = new BetMember();
				betMember3.setId(betMember.getId());
				betMember3.setStatus(1);
				betMemberService.update(betMember3, true);
				returnObject.setMessage("解冻成功！");
				BetMember betmember = betMemberService.queryForObject(new Finder("select*from bet_member where id=:id").setParam("id", betMember.getId()),BetMember.class);
				Integer id2 = betmember.getId2();
				//清除缓存
				try{
					if(betmember.getTicket()!=null){
						cached.deleteCached(("TICKET_"+betmember.getTicket()).getBytes());
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				//操作日志
				 String details = "";
			     details = "解冻ID为"+id2+"的用户";
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else if("1".equals(request.getParameter("adddaysocre"))){
				String id = betMember.getId();
				Double dayscore = betMember.getDayscore();
				if(id!=null&&dayscore!=null){
					BetMember member = betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", id),BetMember.class );
					if(member!=null){
						if(member.getIsinternal()!=null&&1==member.getIsinternal()){
							BetMember betMember3 = new BetMember();
							betMember3.setId(member.getId());
							betMember3.setDayscore(member.getDayscore()+dayscore);
							betMember3.setWinorfail(member.getWinorfail()+dayscore);
							betMember3.setScore(member.getScore()+dayscore);
							betMember3.setBankscore(member.getBankscore()+dayscore);
							betMemberService.update(betMember3, true);
							returnObject.setMessage("增加每日输赢成功！");
							member.setDayscore(betMember3.getDayscore());
							member.setWinorfail(betMember3.getWinorfail());
							member.setScore(betMember3.getScore());
							member.setBankscore(betMember3.getBankscore());
							//清除缓存
							try{
								if(member.getTicket()!=null){
									//更新缓存
									String ticket = member.getTicket();
									if(ticket!=null){
										try{
//											cached.deleteCached(("TICKET_"+ticket).getBytes());
											ObjectMapper mapper=new ObjectMapper();
											byte[] json = mapper.writeValueAsBytes(member);
											
											cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
										}catch(Exception e1){
											String errorMessage = e1.getLocalizedMessage();
											logger.error(errorMessage,e1);
										}
										
									}
								}
							}catch(Exception e1){
								e1.printStackTrace();
							}
							 //金币记录
						     BetScorerecord betScorerecord=new BetScorerecord();
						     String content="";
						     content="系统增加内部用户每日输赢"+dayscore+"元";
						     betScorerecord.setMemberid2(member.getId2());
						     betScorerecord.setTime(new Date());
						     betScorerecord.setContent(content);
						     betScorerecord.setOriginalscore(member.getScore()-dayscore);
						     betScorerecord.setChangescore(dayscore);
						     betScorerecord.setGamescore(BigDecimal.valueOf(member.getGamescore()));
						     betScorerecord.setBankscore(BigDecimal.valueOf(member.getBankscore()));
						     betScorerecord.setFreezescore(BigDecimal.valueOf(member.getFreezingscore()));
						     betScorerecord.setAgentid(member.getAgentid());
						     betScorerecord.setAgentparentid(member.getAgentparentid());
						     betScorerecord.setAgentparentids(member.getAgentparentids());
						     betScorerecord.setBalance(member.getScore());
						     betScorerecord.setState(1);
						     betScorerecord.setType(12);
						     betScorerecordService.saveBetScorerecord(betScorerecord);
							//操作日志
							 String details = "";
						     details = "给ID为："+member.getId2()+"的内部用户增加每日输赢："+dayscore;
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				}
				
			}else if("1".equals(request.getParameter("addinternal"))){
				//添加内部用户
				Integer id2 = betMember.getId2();
				String internalremark = betMember.getInternalremark();
				Double gamescore = betMember.getGamescore();
				String nickname = betMember.getNickname();
				BetMember betMember2 = betMemberService.queryForObject(new Finder("select*from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
				if(betMember2!=null){
					betMember2.setIsinternal(1);
					betMember2.setInternaladdtime(new java.util.Date());
					betMember2.setInternalremark(internalremark);
					betMember2.setGamescore(betMember2.getGamescore()+gamescore);
					betMember2.setScore(betMember2.getScore()+gamescore);
					betMember2.setNickname(nickname);
					ShiroUser shiroUser = SessionUser.getShiroUser();
					String account = shiroUser.getAccount();
					String name = shiroUser.getName();
					if(name==null){
						name="";
					}
					betMember2.setInternaloperator(account+"("+name+")");
					betMemberService.update(betMember2, true);
					//清除缓存
					try{
						if(betMember2.getTicket()!=null){
							cached.deleteCached(betMember2.getTicket().getBytes());
						}
						
					}catch(Exception e){
						e.printStackTrace();
					}
					//操作日志
					 String details = "";
				     details = "将ID为"+id2+"的用户改为内部用户";
				     String ip = IPUtils.getClientAddress(request);
				     String tool = AgentToolUtil.getAgentTool(request);
				     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				}
			}else if("1".equals(request.getParameter("delinternal"))){
				//删除内部用户
				String d = request.getParameter("d");
				if(StringUtils.isNoneBlank(d)){
					String[] split = d.split(",");
					List<String> asList = Arrays.asList(split);
					String ids="";
					 for (String string : asList) {
						BetMember member = betMemberService.queryForObject(new Finder("select *from bet_member where (agentid=:aid or agentparentids like :agentid) and id=:id ").setParam("aid", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id", string),BetMember.class);
						Integer id2 = member.getId2();
						if("".equals(ids)){
							ids+=id2;
						}else{
							ids+=","+id2;
						}
						//清除缓存
						try{
							if(member.getTicket()!=null){
								cached.deleteCached(member.getTicket().getBytes());
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					betMemberService.deleteByIds(asList, BetMember.class);
					
					//操作日志
					 String details = "";
				     details = "删除ID为"+ids+"的内部用户";
				     String ip = IPUtils.getClientAddress(request);
				     String tool = AgentToolUtil.getAgentTool(request);
				     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());	
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				}
			}else{
				java.lang.String id =betMember.getId();
				if(StringUtils.isBlank(id)){
					//新增用户
					String id3 = UUID.randomUUID().toString();
					String nickname = betMember.getNickname();
					String account = betMember.getAccount();
					String realname = betMember.getRealname();
					String password = DigestUtils.md5Hex(betMember.getPassword());
					Integer parentid = betMember.getParentid();
					  //判断一级代理下是否有此用户
					  String company = "";//一级代理
					  company = getCompany(betagent, company);
					  BetMember already = betMemberService.queryForObject(new Finder("select id,id2,account from bet_member where account=:account and (agentid=:agentid or agentparentids like :company)")
					  		.setParam("account", account).setParam("agentid", company).setParam("company", "%"+company+"%"), BetMember.class);
					  if(already != null){
						  returnObject.setStatus(ReturnDatas.ERROR);
						  returnObject.setMessage("此账号已存在");
						  return returnObject;
					  }
					  
					  Date date=new Date();
					  String ip2 = IPUtils.getClientAddress(request);
					  String gameauthorization = "";
					  String agentauthorization = "";
					  String financeauthorization = "";
					  Integer id2= null;
					  List<Map<String, Object>> id2list = betMemberService.queryForList(new Finder("select id2 from bet_member "));
					  Set<Object> set=new HashSet<>();
					  if(id2list!=null){
						  for (Map<String, Object> map : id2list) {
							set.add(map.get("id2"));
						}
					  }
					  for(int i=0;i<500;i++){
						  Integer d =(int)(((Math.random())*9.+1)*100000);
						  
						  if(!set.contains(d)){
							  id2=d;
							  break;
						  }
					  }
					  BetAgent betagg = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
					  if(betagg==null){
						  returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
							return returnObject;
					  }
					  try{
						  //System.out.println("serverapi=="+serverapi);
						  String avatar=serverapi+"img/portrait/default_head_"+company+".png";
						  betMemberService.update(new Finder("INSERT INTO bet_member(mobile,agentauthorization,financeauthorization,password,nickname,gameauthorization,id,password2,account,realname,parentid,signdate,signip,id2,agentid,agentparentid,agentparentids,avatar) values(:mobile,:agentauthorization,:financeauthorization,:password,:nickname,:gameauthorization,:id,:password2,:account,:realname,:parentid,:signdate,:signip,:id2,:agentid,:agentparentid,:agentparentids,:avatar) ").setParam("mobile", account).setParam("id2", id2).setParam("signip", ip2).setParam("signdate", date).setParam("id", id3).setParam("password", password).setParam("password2", password).setParam("gameauthorization", gameauthorization).setParam("agentauthorization", agentauthorization).setParam("financeauthorization", financeauthorization).setParam("nickname", nickname).setParam("account",account).setParam("realname", realname).setParam("parentid", parentid).setParam("agentid", agentid).setParam("agentparentid", betagg.getParentid()).setParam("agentparentids", betagg.getParentids()).setParam("avatar", avatar));
					  }catch(Exception e){
						  returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("此账号已存在");
							return returnObject;
					  }
					  
					  //操作日志
						 String details = "";
					     details = "新增账号为："+account+"的用户";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
					  if(parentid!=null){
						  BetMember betMember2 = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id ").setParam("id", parentid),BetMember.class);
						  betMember2.setSubordinate(betMember2.getSubordinate()+1);
						  betMemberService.update(betMember2);
					  }
				}else{
					betMemberService.saveorupdate(betMember);
					BetMember queryForObject = betMemberService.queryForObject(new Finder("select*from bet_member where id=:id ").setParam("id", id), BetMember.class);
					//清除缓存
					try{
						if(queryForObject.getTicket()!=null){
							cached.deleteCached(("TICKET_"+queryForObject.getTicket()).getBytes());
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					//操作日志
					 String details = "";
				     details = "修改账号为："+betMember.getAccount()+"的用户";
				     String ip = IPUtils.getClientAddress(request);
				     String tool = AgentToolUtil.getAgentTool(request);
				     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
				}
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
	
	
	@RequestMapping("/updaterealname")
	public @ResponseBody
	ReturnDatas updaterealname(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			betMember2.setRealname(null);
			betMemberService.update(new Finder("update bet_member set realname=null where id2=:id2 ").setParam("id2",betMember2.getId2()));
			//清除缓存
			try{
				if(betMember2.getTicket()!=null){
					//更新缓存
					String ticket = betMember2.getTicket();
					if(ticket!=null){
						try{
//							cached.deleteCached(("TICKET_"+ticket).getBytes());
							ObjectMapper mapper=new ObjectMapper();
							byte[] json = mapper.writeValueAsBytes(betMember2);
							
							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
						}
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//操作日志
			 String details = "";
		     details = "解绑ID为"+id2+"的用户的真实姓名";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     //betOptLogService.saveoptLog(tool,ip,details);;
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
		
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
		
		
		
//		
//		String agentid = SessionUser.getShiroUser().getAgentid();
//		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
//		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
//		try {
//				Integer id2 = betMember.getId2();
//				BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where (agentid=:id or agentparentids like :agentid) and id2=:id2 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%").setParam("id2", id2), BetMember.class);
//				String realname = new String(betMember.getRealname().getBytes("ISO8859-1"),"UTF-8");
//				betMember2.setRealname(realname);
//				BetMember betMember3=new BetMember();
//				betMember3.setId(betMember2.getId());
//				betMember3.setRealname(realname);
//				//betMember3.setAgentid(agentid);
//				betMemberService.update(betMember3, true);
//				//清除缓存
//				try{
//					if(betMember2.getTicket()!=null){
//						//更新缓存
//						String ticket = betMember2.getTicket();
//						if(ticket!=null){
//							try{
////								cached.deleteCached(("TICKET_"+ticket).getBytes());
//								ObjectMapper mapper=new ObjectMapper();
//								byte[] json = mapper.writeValueAsBytes(betMember2);
//								
//								cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
//							}catch(Exception e){
//								String errorMessage = e.getLocalizedMessage();
//								logger.error(errorMessage,e);
//							}
//							
//						}
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				//操作日志
//				 String details = "";
//				 details = "修改ID为："+betMember2.getId2()+"的用户的真实姓名为："+realname;
//			     String ip = IPUtils.getClientAddress(request);
//			     String tool = AgentToolUtil.getAgentTool(request);
//			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
//		} catch (Exception e) {
//			String errorMessage = e.getLocalizedMessage();
//			logger.error(errorMessage,e);
//			returnObject.setStatus(ReturnDatas.ERROR);
//			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
//		}
//		return returnObject;
	
	}
	
	@RequestMapping("/updatemobile")
	public @ResponseBody
	ReturnDatas updatemobile(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			String mobile = betMember.getMobile();
			betMember2.setMobile(mobile);
			BetMember betMember3=new BetMember();
			betMember3.setId(betMember2.getId());
			betMember3.setMobile(mobile);
			betMemberService.update(betMember3, true);
			//清除缓存
			try{
				if(betMember2.getTicket()!=null){
					//更新缓存
					String ticket = betMember2.getTicket();
					if(ticket!=null){
						try{
//							cached.deleteCached(("TICKET_"+ticket).getBytes());
							ObjectMapper mapper=new ObjectMapper();
							byte[] json = mapper.writeValueAsBytes(betMember2);
							
							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
						}
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//操作日志
			 String details = "";
		     details = "更新ID为"+id2+"的用户的手机号为："+mobile;
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     //betOptLogService.saveoptLog(tool,ip,details);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	@RequestMapping("/updateqq")
	public @ResponseBody
	ReturnDatas updateqq(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			String qq = betMember.getQq();
			betMember2.setQq(qq);
			BetMember betMember3=new BetMember();
			betMember3.setId(betMember2.getId());
			betMember3.setQq(qq);
			betMemberService.update(betMember3, true);
			//清除缓存
			try{
				if(betMember2.getTicket()!=null){
					//更新缓存
					String ticket = betMember2.getTicket();
					if(ticket!=null){
						try{
//							cached.deleteCached(("TICKET_"+ticket).getBytes());
							ObjectMapper mapper=new ObjectMapper();
							byte[] json = mapper.writeValueAsBytes(betMember2);
							
							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
						}
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//操作日志
			 String details = "";
		     details = "更新ID为"+id2+"的用户的qq";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     //betOptLogService.saveoptLog(tool,ip,details);;
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
		
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	@RequestMapping("/updateidno")
	public @ResponseBody
	ReturnDatas updateidno(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			betMember2.setIdno(null);
			betMemberService.update(new Finder("update bet_member set idno=null where id2=:id2 ").setParam("id2",betMember2.getId2()));
			//清除缓存
			try{
				if(betMember2.getTicket()!=null){
					//更新缓存
					String ticket = betMember2.getTicket();
					if(ticket!=null){
						try{
//							cached.deleteCached(("TICKET_"+ticket).getBytes());
							ObjectMapper mapper=new ObjectMapper();
							byte[] json = mapper.writeValueAsBytes(betMember2);
							
							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
						}
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//操作日志
			 String details = "";
		     details = "解绑ID为"+id2+"的用户的身份证";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     //betOptLogService.saveoptLog(tool,ip,details);;
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
		
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	
	@RequestMapping("/updatepassword")
	public @ResponseBody
	ReturnDatas updatepassword(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			String md5Hex = DigestUtils.md5Hex(betMember.getPassword());
			BetMember betMember3=new BetMember();
			betMember3.setId(betMember2.getId());
			betMember3.setPassword(md5Hex);
			betMemberService.update(betMember3, true);
			//清除缓存
			try{
				if(betMember2.getTicket()!=null){
					cached.deleteCached(("TICKET_"+betMember2.getTicket()).getBytes());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//操作日志
			 String details = "";
		     details = "更新ID为"+id2+"的用户的登录密码";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		    // betOptLogService.saveoptLog(tool,ip,details);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	@RequestMapping("/updatepassword2")
 	public @ResponseBody
 	ReturnDatas updatepassword2(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
 		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
 		try {

 			Integer id2 = betMember.getId2();
 			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
 			BetMember betMember3=new BetMember();
 			betMember3.setId(betMember2.getId());
 			betMember3.setPassword2(DigestUtils.md5Hex(betMember.getPassword2()));
 			betMemberService.update(betMember3, true);
 			//清除缓存
 			try{
 				if(betMember2.getTicket()!=null){
 					cached.deleteCached(("TICKET_"+betMember2.getTicket()).getBytes());
 				}
 			}catch(Exception e){
 				e.printStackTrace();
 			}
 			//操作日志
 			 String details = "";
 		     details = "更新ID为"+id2+"的用户的银行密码";
 		     String ip = IPUtils.getClientAddress(request);
 		     String tool = AgentToolUtil.getAgentTool(request);
 		     //betOptLogService.saveoptLog(tool,ip,details);
 		    betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
 			
 		} catch (Exception e) {
 			String errorMessage = e.getLocalizedMessage();
 			logger.error(errorMessage,e);
 			returnObject.setStatus(ReturnDatas.ERROR);
 			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
 		}
 		return returnObject;
 	
 	}
	
	 @RequestMapping("/updatetx")
	 	public @ResponseBody
	 	ReturnDatas updatetx(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);	
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
	 		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
	 		try {

	 			Integer id2 = betMember.getId2();
	 			BetMember betMember2 = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
	 			Double tx = betMember.getTx();
	 			betMember2.setTx(tx/100.);
	 			BetMember betMember3=new BetMember();
	 			betMember3.setId(betMember2.getId());
	 			betMember3.setTx(tx/100.);
	 			betMemberService.update(betMember3, true);
	 			//清除缓存
	 			try{
	 				if(betMember2.getTicket()!=null){
	 					//更新缓存
	 					String ticket = betMember2.getTicket();
	 					if(ticket!=null){
	 						try{
//	 							cached.deleteCached(("TICKET_"+ticket).getBytes());
	 							ObjectMapper mapper=new ObjectMapper();
	 							byte[] json = mapper.writeValueAsBytes(betMember2);
	 							
	 							cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
	 						}catch(Exception e){
	 							String errorMessage = e.getLocalizedMessage();
	 							logger.error(errorMessage,e);
	 						}
	 						
	 					}
	 				}
	 			}catch(Exception e){
	 				e.printStackTrace();
	 			}
	 			//操作日志
	 			 String details = "";
	 		     details = "更新ID为"+id2+"的用户的退税";
	 		     String ip = IPUtils.getClientAddress(request);
	 		     String tool = AgentToolUtil.getAgentTool(request);
	 		    // betOptLogService.saveoptLog(tool,ip,details);;
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
		if("1".equals(request.getParameter("k"))){
			//新增下线
			model.addAttribute("parentid", request.getParameter("parentid"));
			return "/lottery/betmember/betmemberCru1";
			
		}else if("2".equals(request.getParameter("k"))){
			//推广设置
			Page page = newPage(request);
			page.setOrder("id");
			page.setSort("asc");
			BetSubordinateRebate  betSubordinateRebate= new BetSubordinateRebate();
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			List<BetSubordinateRebate> datas=betSubordinateRebateService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark=:remark").setParam("remark", "t"),page,BetSubordinateRebate.class,betSubordinateRebate);
			if(datas!=null){
				Double maxbetting=0.;
				for (BetSubordinateRebate betSubordinateRebate2 : datas) {
					Double betstream = betSubordinateRebate2.getBetstream();
					if(betstream!=null){
						if(5!=betSubordinateRebate2.getId()){
							if(betstream>maxbetting){
								maxbetting=betstream;
							}
						}
					}
					model.addAttribute("maxbetting",maxbetting);
				}
			}
			returnObject.setQueryBean(betSubordinateRebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betsubordinaterebateCru";
		}else{
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betmember/betmemberCru";
		}
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
				betMemberService.deleteById(id,BetMember.class);
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
			betMemberService.deleteByIds(ids,BetMember.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 未退投注退佣列表
	 */
	@RequestMapping("/bettingtylist")
	public String bettingtylist(HttpServletRequest request, Model model,BetMember betMember) throws Exception {
		String parameter = request.getParameter("gameclassid");
		Integer gameclassid = null;
		try{
			gameclassid = Integer.valueOf(parameter);
		}catch(Exception e){
			return "/errorpage/error";
		}
		
		if(gameclassid==null){
			return "/errorpage/error";
		}
		String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid =:gameclassid ").setParam("gameclassid", gameclassid),String.class);
		if(gcname==null){
			return "/errorpage/error";
		}
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
		model.addAttribute("starttime", starttime);
		if(StringUtils.isBlank(starttime)) {
			starttime="0000-00-00";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-00-00";
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetBetting> datas = betBettingService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where (a.bettingtime >=:starttime and a.bettingtime<:endtime) and a.gameclassid=:gameclassid and a.membertystate=0 and a.memberty>0 and a.state=1  ").setParam("gameclassid", gameclassid).setParam("starttime", starttime).setParam("endtime", endtime), BetBetting.class,page);
		returnObject.setQueryBean(betMember);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject); 	
		model.addAttribute("gcname", gcname);
		if(!"9999-00-00".equals(endtime)){
			Date date2 =DateUtils.convertString2Date(endtime);
			calendar.setTime(date2); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
			model.addAttribute("endtime", endtime);
		}
		return "/lottery/betmember/bettingty";
		
	}
	
	/**
	 * 手动退还用户投注退佣
	 * 
	 */
	@RequestMapping("/manualrefundbettingty")
	public @ResponseBody
	ReturnDatas manualrefundbettingty(Model model,BetMember betMember,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String parameter = request.getParameter("gameclassid");
		Integer gameclassid = null;
		try{
			gameclassid = Integer.valueOf(parameter);
		}catch(Exception e){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
		
		if(gameclassid==null){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
		BetGameclass bgc = betGameclassService.queryForObject(new Finder("select gcname,membertymode from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", gameclassid), BetGameclass.class);
		if(bgc.getMembertymode()!=0){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
		Object cached2=null;
		try{
			cached2 = cached.getCached("updatebettingty2332".getBytes());
		}catch (Exception e) {
			
		}
		if(cached2!=null){
			if("1".equals(cached2)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("");
				return returnObject;
			}
			
		}
		cached.updateCached("updatebettingty2332".getBytes(), "1".getBytes(),10000L);
			
		try{
			List<Map<String, Object>> membertylist = betBettingService.queryForList(new Finder("select memberid,SUM(memberty) as membertys from bet_betting where gameclassid=:gameclassid and membertystate=0 and memberty>0 and state=1  GROUP BY memberid").setParam("gameclassid", gameclassid));
			
			Date date = new Date();
			List<BetRedenvelope> send = new ArrayList<BetRedenvelope>();
			if(membertylist!=null){//以红包形式发放
				for (Map<String, Object> map : membertylist) {
					String memberid =(String)map.get("memberid");
					Double membertys = ((BigDecimal)map.get("membertys")).doubleValue();
					BetMember mmeber = betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", memberid),BetMember.class);
					
					BetRedenvelope b = new BetRedenvelope();
					b.setCaption("洗码费");
					b.setCreatetime(date);
					b.setRedenvelopecode("R"+RandomCharData.createData(8));
					b.setState(2);
					b.setReceivenum(0);
					b.setCountpayscore(0.);
					b.setTotalscore(null);
					b.setSendnum(1);
					b.setMinscore(membertys);
					b.setMaxscore(membertys);
					b.setMemberid2(mmeber.getId2()+"");
					b.setLifetime(100);
					
					Integer update = betBettingService.update(new Finder("update bet_betting set membertystate=1,membertytime=:tytime where gameclassid=:gameclassid and membertystate=0 and memberty>0 and state=1 and memberid=:memberid ").setParam("memberid", memberid).setParam("gameclassid", gameclassid).setParam("tytime", new Date()));
					if(update>=1){
						send.add(b);
					}
				}
			}
			betRedenvelopeService.save(send);
			
			//操作日志
			 String details = "";
			
		     details = "退还"+bgc.getGcname()+"的用户投注退佣";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
			return returnObject;
		}catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}finally{
			cached.deleteCached("updatebettingty2332".getBytes());
		}
		
	}
	
	/**
	 * 按dayscore查询列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betMember
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/listByDayscore")
//	public String listByDayscore(HttpServletRequest request, Model model,BetMember betMember) 
//			throws Exception {
//		ReturnDatas returnObject = listByDayscorejson(request, model, betMember);
//		model.addAttribute(GlobalStatic.returnDatas, returnObject);
//		return "/lottery/betrankmember/betrankmemberList";
//	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betMember
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/listByDayscore/json")
//	public @ResponseBody
//	ReturnDatas listByDayscorejson(HttpServletRequest request, Model model,BetMember betMember) throws Exception{
//		String agentid = SessionUser.getShiroUser().getAgentid();
//		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//		// ==构造分页请求
//		Page page = newPage(request,"dayscore","desc");
//		page.setPageSize(30);
//		// ==执行分页查询
//		List<BetMember> datas=betMemberService.findListDataByFinder(new Finder("select * from bet_member where agentid=:id or agentparentids like :agentid ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%"),page,BetMember.class,betMember);
//			returnObject.setQueryBean(betMember);
//			page.setTotalCount(30);
//		returnObject.setPage(page);
//		Page page1 = newPage(request, "id", "asc");
//		page1.setPageSize(30);
//		List<BetRankConf> betRankConfList=betRankConfService.findListDataByFinder(new Finder("select * from bet_rank_conf where agentid=:id or agentparentids like :agentid ").setParam("id", agentid).setParam("agentid", "%"+agentid+"%"),page1,BetRankConf.class,new BetRankConf());
//		for(int i=0;i<datas.size();i++){
//			datas.get(i).setAward(betRankConfList.get(i).getAward());
//		}
//		returnObject.setData(datas);
//		return returnObject;
//	}
	
	//隐藏用户提现重要信息
	private void getMemberbankcard(String company, BetAgent agag, List<BetMemberbankcard> betMemberbankcardList) {
		if(!company.equals("zhouyunfei")){
			if(!"A101".equals(agag.getParentid())){//不是一级代理或者子账号，提现信息都要隐藏部分
				if(betMemberbankcardList!=null && betMemberbankcardList.size()>0){
					for(BetMemberbankcard memberbankcard:betMemberbankcardList){
						if(memberbankcard.getAccount()!=null){//隐藏账号信息
							memberbankcard.setAccount(namestrReplaceWithStar(memberbankcard.getAccount()));
						}
						if(memberbankcard.getBankofdeposit()!=null){//隐藏开户行
							if (memberbankcard.getBankofdeposit().length() <= 1) {        
								memberbankcard.setBankofdeposit("*");
							} else {        
								memberbankcard.setBankofdeposit(memberbankcard.getBankofdeposit().replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1***"));
							}
						}
					}
				}
			}
		}
	}
	
	/**
     * 根据字符串的不同长度，来进行替换 ，达到保密效果
     *
     * @param namestr namestr
     * @return 替换后的字符串
     */
    public static String namestrReplaceWithStar(String namestr) {
        String namestrAfterReplaced = "";

        if (namestr == null){
        	namestr = "";
        }

        int nameLength = namestr.length();

        if (nameLength <= 1) {
        	namestrAfterReplaced = "*";
        } else if (nameLength == 2) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{0})\\d(?=\\d{1})");
        } else if (nameLength >= 3 && nameLength <= 6) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{1})\\d(?=\\d{1})");
        } else if (nameLength == 7) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{1})\\d(?=\\d{2})");
        } else if (nameLength == 8) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{2})\\d(?=\\d{2})");
        } else if (nameLength == 9) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{2})\\d(?=\\d{3})");
        } else if (nameLength == 10) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{3})\\d(?=\\d{3})");
        } else if (nameLength >= 11) {
        	namestrAfterReplaced = replaceAction(namestr, "(?<=\\d{3})\\d(?=\\d{4})");
        }

        return namestrAfterReplaced;
    }

    /**
     * 实际替换动作
     *
     * @param namestr namestr
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String namestr, String regular) {
        return namestr.replaceAll(regular, "*");
    }
    
    /**
     * 更新栏目版本
     * @param model
     * @param betMember
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping("/update_versioncontrol")
	public @ResponseBody ReturnDatas updateVersiontrol(Model model, BetMember betMember, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(
				new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid),
				BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String versioncontrol = betMember.getVersioncontrol();
			String versioncontrolName = "";
			if (StringUtils.isNotBlank(versioncontrol)) {
				versioncontrol = versioncontrol.toUpperCase();
				if (!"A".equals(versioncontrol) && !"B".equals(versioncontrol)) {
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR + "，版本栏目只能选择“A”或“B”");
					return returnObject;
				}
			}
			switch (versioncontrol) {
			case "A":
				versioncontrolName = "资讯版（A版）";
				break;
			case "B":
				versioncontrolName = "彩票版（B版）";
				break;
				
			default:
				versioncontrolName = "空";
				break;
			}
			Integer id2 = betMember.getId2();
			BetMember betMember2 = betMemberService.queryForObject(
					new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
			betMember2.setVersioncontrol(betMember.getVersioncontrol());
			BetMember betMember3 = new BetMember();
			betMember3.setId(betMember2.getId());
			betMember3.setId2(betMember2.getId2());
			betMember3.setVersioncontrol(versioncontrol);
			betMemberService.update(betMember3, true);
			// 清除缓存
			try {
				if (betMember2.getTicket() != null) {
					// 更新缓存
					String ticket = betMember2.getTicket();
					if (ticket != null) {
						try {
							// cached.deleteCached(("TICKET_"+ticket).getBytes());
							ObjectMapper mapper = new ObjectMapper();
							byte[] json = mapper.writeValueAsBytes(betMember2);
							cached.updateCached(("TICKET_" + ticket).getBytes(), json, 7L * 24 * 60 * 60);
						} catch (Exception e) {
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage, e);
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 操作日志
			String details = "";
			details = "更新ID为" + id2 + "的用户的栏目版本为：" + versioncontrolName;
			String ip = IPUtils.getClientAddress(request);
			String tool = AgentToolUtil.getAgentTool(request);
			betOptLogService.saveoptLog(tool, ip, details, agentid, betagent.getParentid(), betagent.getParentids());

		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage, e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;

	}
	/**
	 * 发送短信根据用户ids
	 * 
	 * @param model
	 * @param msg
	 *            短信内容
	 * @param id
	 *            用户id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/send_msgs", method=RequestMethod.POST)
	public @ResponseBody ReturnDatas sendMsgs(Model model, String msg, String idsStr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage("发送成功");
		// 获取用户列表
		// 获取代理列表
		// 
		try {
			if (StringUtils.isBlank(idsStr) || StringUtils.isBlank(msg)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("发送短信失败，无待发送短信的信息");
				return returnObject;
			}
			String[] ids = idsStr.split(",");
			if (ids.length > 100) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("发送短信失败，一次最多只能发送一百个号码");
				return returnObject;
			}
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagetn = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			String company = "";
			if (betagetn != null) { //
				if ("A101".equals(betagetn.getParentid())) {
					company = betagetn.getAgentid();
				} else {
					String[] spilt = betagetn.getParentids().split(",");// ,A101,agent1,agent2
					company = spilt[2];
				}
			}
			String company2 = dicDataService.queryForObject(
					new Finder("select value from t_dic_data where code=:code ").setParam("code", "company_" + company),
					String.class);
			if (StringUtils.isBlank(company2)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("代理无短信配置，发送短信失败");
				return returnObject;
			}
			String sql = "select member.id, member.id2, member.mobile from bet_member member left join bet_agent agent on member.agentid = agent.agentid where member.id in (";
			for (int i = 0; i < ids.length; i++) {
				sql = sql.concat(":id" + i);
				if (i != ids.length -1) {
					sql = sql.concat(",");
				}
			}
			sql = sql.concat(")");
			Finder finder = new Finder(sql);
			for (int i = 0; i < ids.length; i++) {
				finder.setParam("id" + i, ids[i]);
			}
			List<BetMember> betMembers = betMemberService.queryForList(finder, BetMember.class);
			if (betMembers.isEmpty()) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("发送短信失败，无待发送短信的信息");
				return returnObject;
			}
			String phones = "";
			for (BetMember betMember : betMembers) {
				if (StringUtils.isNotBlank(betMember.getMobile())) {
					if (StringUtils.isNotBlank(phones)) {
						phones = phones.concat(betMember.getMobile()).concat(",");
					} else {
						phones = betMember.getMobile() + ",";
					}
				}
			}
			phones = phones.substring(0, phones.lastIndexOf(","));
			if (StringUtils.isBlank(phones)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("发送短信失败，无待发送短信的信息");
				return returnObject;
			}
			Integer sendNum = sendMsg(msg, phones, company2);
			// 操作日志
			String details = "";
			if (sendNum.compareTo(phones.split(",").length) != 0) {
				details = "发送短信给ID为" + idsStr + "的用户的内容为：" + msg + "发送短信成功" + sendNum +"条，发送短信失败" + (phones.split(",").length - sendNum) + "条";
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("发送短信成功" + sendNum +"条，发送短信失败" + (phones.split(",").length - sendNum) + "条");
			} else {
				details = "发送短信给ID为" + idsStr + "的用户的内容为：" + msg;
			}
			String ip = IPUtils.getClientAddress(request);
			String tool = AgentToolUtil.getAgentTool(request);
			betOptLogService.saveoptLog(tool,ip,details, agentid, betagetn.getParentid(), betagetn.getParentids());
			return returnObject;
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage, e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("发送短信失败");
		}
		return returnObject;

	}
	
	/**
	 * 发送短信根据用户id
	 * 
	 * @param model
	 * @param msg
	 *            短信内容
	 * @param id
	 *            用户id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/send_msg")
	public @ResponseBody ReturnDatas sendMsg(Model model, String msg, String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage("发送成功");
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagetn = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			BetMember betMember2 = betMemberService.queryForObject(
					new Finder("select * from bet_member where id=:id ").setParam("id", id), BetMember.class);
			String company = "";
			if (betagetn != null) { //
				if ("A101".equals(betagetn.getParentid())) {
					company = betagetn.getAgentid();
				} else {
					String[] spilt = betagetn.getParentids().split(",");// ,A101,agent1,agent2
					company = spilt[2];
				}
			}
			String company2 = "";
			String phone = betMember2.getMobile();
			if (StringUtils.isBlank(phone)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("会员无电话号码，发送短信失败");
				return returnObject;
			}
			company2 = dicDataService.queryForObject(
					new Finder("select value from t_dic_data where code=:code ").setParam("code", "company_" + company),
					String.class);
			if (StringUtils.isBlank(company2)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("代理无短信配置，发送短信失败");
				return returnObject;
			}
			Integer sendNum = sendMsg(msg, phone, company2);
			if (sendNum.compareTo(1) != 0) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("发送短信失败");
				return returnObject;
			}
			// 操作日志
			String details = "";
			details = "发送短信给ID为" + id + "的用户的内容为：" + msg;
			String ip = IPUtils.getClientAddress(request);
			String tool = AgentToolUtil.getAgentTool(request);
			betOptLogService.saveoptLog(tool,ip,details, agentid, betagetn.getParentid(), betagetn.getParentids());
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage, e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("发送短信失败");
		}
		return returnObject;

	}
	
	/**
	 * 发送短信
	 * 
	 * @param msg
	 *            短信内容
	 * @param phone
	 *            手机号码
	 * @param company
	 *            短信配置账号 短信配置密码
	 * @return
	 * @throws Exception
	 */
	public Integer sendMsg(String msg, String phones, String company) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {

			// 发送短信
			return SendMsg.send(phones, msg, company);

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
}
