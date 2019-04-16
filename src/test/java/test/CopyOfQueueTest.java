package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.frame.queue.SendMessage;
import org.springrain.frame.queue.RedisMessageDelegateListener;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetReportform;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetDaywinorfailrebateService;
import org.springrain.lottery.service.IBetFirstrechargerebateService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetRankMemberService;
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
import org.springrain.lottery.service.ISoccerAllbettingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CopyOfQueueTest  {
	
	
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
//	@Resource
//	private IBetRechargecardService betRechargecardService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetReliefRecordService betReliefRecordService;
	@Resource
	private IBetDaywinorfailrebateService betDaywinorfailrebateService;
	@Resource
	private IBetFirstrechargerebateService betFirstrechargerebateService;
	@Resource
	private IBetRedenvelopeRecordService betRedenvelopeRecordService;
	@Resource
	private IBetSubordinaterebateDetailService betSubordinaterebateDetailService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource 
	private IBetRankMemberService betRankMemberService;
	@Resource
	private IBetReportformService betReportformService;
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
	private IBetSigninRewardService betSigninRewardService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private IBetAgentService betAgentService;
	
	@Test
	public void sendMessage() throws Exception{ 
		try {
			List<BetAgent> agentidlist = betAgentService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where agentid!=:agentid ").setParam("agentid", "A101"), BetAgent.class);
			if(agentidlist!=null){
				
					
				for (BetAgent betAgent : agentidlist) {
					String agentid=betAgent.getAgentid();
					//用户留存
	    			Double sumScore = betRankMemberService.queryForObject(new Finder("select sum(score1) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :aid) ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%"), Double.class);
	    			if(sumScore==null){
	    				sumScore=0.;
	    			}
	    			//昨天的日期
	    			Calendar cal = Calendar.getInstance();
	    			cal.add(Calendar.DATE, -1);
	    			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    			//前天的日期
	    			Calendar cal1 = Calendar.getInstance();
	    			cal1.add(Calendar.DATE, -2);
	    			String before_yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
	    			//注册人数
	    			Integer registNum = betMemberService.queryForObject(new Finder("select count(*) from bet_member where isinternal=0 and substring(signdate,1,10)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Integer.class);
	    			if(registNum==null) {
	    				registNum=0;
	    			}
	    			//充值统计
	    			Double sumBetGoldMoney = betGoldService.queryForObject(new Finder("select sum(a.money) from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where b.isinternal=0 AND (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=2 and substring(a.rechargetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumBetGoldMoney==null){
	    				sumBetGoldMoney=0.;
	    			}
	    			//提现统计
	    			Double sumBetWithdrawcashMoney = betWithdrawcashService.queryForObject(new Finder("select sum(a.money) from bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=2 and substring(a.audittime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumBetWithdrawcashMoney==null){
	    				sumBetWithdrawcashMoney=0.;
	    			}
	    			//卡充
//	    			//卡收
//	    			List<BetRechargecard> betRechargecardList = betRechargecardService.queryForList(new Finder("select a.money from bet_rechargecard a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.state=2 and substring(a.rechargetime,1,10)=:date order by a.id asc ").setParam("date", yesterday), BetRechargecard.class);
//	    			double sumBetRechargecard = 0d;//卡收总额
//	    			if(!betRechargecardList.isEmpty()){
//	    				for (BetRechargecard betRechargecard : betRechargecardList) {
//	    					sumBetRechargecard+=betRechargecard.getMoney();
//	    				}
//	    			}
//	    			sumBetRechargecard = Math.round(sumBetRechargecard*100)*0.01d;
//	    			//签到 金额
//	    			Integer sumBetScorerecordMoney = betScorerecordService.queryForObject(new Finder("select sum(a.changescore) from bet_scorerecord a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.type=3 and substring(a.time,1,10)=:date ").setParam("date", yesterday), Integer.class);
	    			Double sumBetScorerecordMoney =betSigninRewardService.queryForObject(new Finder("select sum(reward) from bet_signin_reward a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumBetScorerecordMoney==null){
	    				sumBetScorerecordMoney = 0.;
	    			}
	    			
	    			//总救济金
	    			Integer sumReliefScore = betReliefRecordService.queryForObject(new Finder("select sum(a.reliefscore) from bet_relief_record a left join bet_member b on a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.date,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Integer.class);
	    			if(sumReliefScore==null){
	    				sumReliefScore=0;
	    			}
	    			//当日输赢返利
	    			Double sumDaywinorfailRebate = betDaywinorfailrebateService.queryForObject(new Finder("select sum(a.rebate) from bet_daywinorfailrebate a left join bet_member b on a.memberid2=b.id2 where a.state=1 and b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumDaywinorfailRebate==null){
	    				sumDaywinorfailRebate=0.;
	    			}
	    			//总排行返利
//	    			Double sumRankRebate = betScorerecordService.queryForObject(new Finder("select sum(a.changescore) from bet_scorerecord a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.type=9 and substring(a.time,1,10)=:date ").setParam("date", yesterday), Double.class);
	    			Double sumRankRebate = betRankMemberService.queryForObject(new Finder("select sum(award) from bet_rank_member a left join bet_member b on a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and  a.state =1 and substring(a.receivetime,1,10)=:date  ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumRankRebate==null){
	    				sumRankRebate=0.;
	    			}
	    			//红包
	    			Double sumRedRecord =  betRedenvelopeRecordService.queryForObject(new Finder("select sum(a.receivescore) from bet_redenvelope_record a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumRedRecord==null){
	    				sumRedRecord=0.;
	    			}
	    			//下线(推广返利)
	    			Double sumSubordinaterebate = betSubordinaterebateDetailService.queryForObject(new Finder("select sum(a.income) from bet_subordinaterebate_detail a left join bet_member b on a.memberid2=b.id2 where a.state=1 and b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumSubordinaterebate==null){
	    				sumSubordinaterebate=0.;
	    			}
	    			//首冲返利
	    			Double firstrebate = betFirstrechargerebateService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date and state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(firstrebate==null){
	    				firstrebate=0.;
	    			}
	    			//游戏输赢
//	    			Double sumGameWin = betBettingService.queryForObject(new Finder("select sum(a.bettingscore-a.bettingmoney) from bet_betting a left join bet_member b on a.memberid=b.id where b.isinternal=0 and substring(a.settlementtime,1,10)=:date ").setParam("date", yesterday), Double.class);
	    			Double sumGameWin = soccerAllbettingService.queryForObject(new Finder("select sum(a.bettingscore-a.bettingmoney) from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumGameWin==null){
	    				sumGameWin=0.;
	    			}
	    			//投注额
//	    			Double sumBettingMoney = betBettingService.queryForObject(new Finder("select sum(a.bettingmoney) from bet_betting a left join bet_member b on a.memberid=b.id where b.isinternal=0 and state!=2 and substring(a.bettingtime,1,10)=:date ").setParam("date", yesterday), Double.class);
	    			Double sumBettingMoney = soccerAllbettingService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state!=2 and substring(a.bettingtime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumBettingMoney==null){
	    				sumBettingMoney=0.;
	    			}
	    			//退佣
	    			Double betsumty = betBettingService.queryForObject(new Finder("select sum(memberty) from bet_betting where membertystate=1 and (agentid=:agentid or agentparentids like :aid) and substring(membertytime,1,10)=:membertytime ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("membertytime", yesterday), Double.class);
	    			if(betsumty == null){
	    				betsumty =0.;
	    			}
	    			//单笔充值返利
	    			Double payrebate = betSinglerechargeService.queryForObject(new Finder("select sum(a.rebate) from bet_singlerecharge a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=1 and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(payrebate == null){
	    				payrebate = 0.;
	    			}
	    			//当日充值返
	    			Double todayrebate = betTodayrechargerebateService.queryForObject(new Finder("select sum(a.reward) from bet_todayrechargerebate a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=1 and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday),Double.class);
	    			if(todayrebate == null){
	    				todayrebate = 0.;
	    			}
	    			//注册送
	    			Double registersend = betRegisterRewardService.queryForObject(new Finder("select sum(a.reward) from bet_register_reward a  left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(registersend == null){
	    				registersend = 0.;
	    			}
	    			//昨日留存
	    			Double result = betReportformService.queryForObject(new Finder("select score from bet_agentreportform where agentid=:agentid and date=:date limit 1 ").setParam("agentid", agentid).setParam("date", before_yesterday), Double.class);
	    			if(result == null){
	    				result = 0.;
	    			}
	    			//周返利
	    			Double sumWeekwinorfailrebate = betWeekwinorfailrebateService.queryForObject(new Finder("select sum(a.rebate) from bet_weekwinorfailrebate a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date and a.state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumWeekwinorfailrebate==null){
	    				sumWeekwinorfailrebate=0.;
	    			}
	    			//转账
	    			Double sumtransferaccount = betTransferAccountsService.queryForObject(new Finder("select sum(a.transferaccountsscore) from bet_transfer_accounts a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.time,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(sumtransferaccount==null){
	    				sumtransferaccount=0.;
	    			}
	    			//总福利
	    			Double allwelfare = sumDaywinorfailRebate+sumWeekwinorfailrebate+sumRankRebate+sumReliefScore+sumBetScorerecordMoney+todayrebate+firstrebate+sumSubordinaterebate+registersend+payrebate;
	    			//用户。游戏扣税
//	    			Double gks = betBettingService.queryForObject(new Finder("select sum(gks) from bet_betting a left join bet_member b on a.memberid=b.id where b.isinternal=0 and b.agentid=:agentid and substring(a.settlementtime,1,10)=:date ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	    			if(gks==null){
//	    				gks=0.;
//	    			}
//	    			Double mks = betBettingService.queryForObject(new Finder("select sum(mks) from bet_betting a left join bet_member b on a.memberid=b.id where b.isinternal=0 and b.agentid=:agentid and substring(a.settlementtime,1,10)=:date ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	    			if(mks==null){
//	    				mks=0.;
//	    			}
	    			//投注退佣
	    			Double ttzzttyy=betReportformService.queryForObject(new Finder("select sum(a.commission) as commission from bet_commission a left join bet_agent b on a.agentid=b.agentid where substring(a.settlementtime,1,10)=:date and (b.agentid=:agentid or b.parentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
	    			if(ttzzttyy==null){
	    				ttzzttyy=0.;
	    			}
	    			BetReportform betReportform = new BetReportform();
	    			betReportform.setDate(cal.getTime());
	    			betReportform.setRegisternum(registNum);
	    			betReportform.setRegistersend(registersend);
	    			betReportform.setRecharge(sumBetGoldMoney);
	    			betReportform.setWithdrawcash(sumBetWithdrawcashMoney);
	    			//betReportform.setCardrecycle(sumBetRechargecard);
	    			betReportform.setCardrecycle(0d);
	    			betReportform.setSignin(sumBetScorerecordMoney.intValue());
	    			betReportform.setRelief(sumReliefScore);
	    			betReportform.setDaywinorfailrebate(sumDaywinorfailRebate);
	    			betReportform.setWeekwinorfailrebate(sumWeekwinorfailrebate);
//	    			betReportform.setDaywinorfailrebate(0d);
	    			betReportform.setFirstrecharge(firstrebate);
	    			betReportform.setRank(sumRankRebate);
	    			betReportform.setRedpackage(sumRedRecord);
	    			betReportform.setSubordinaterebate(sumSubordinaterebate);
	    			betReportform.setWinorloss(sumGameWin);
	    			betReportform.setScore(sumScore);
	    			betReportform.setBettingmoney(sumBettingMoney);
	    			betReportform.setTy(betsumty);
	    			betReportform.setResult(result);
	    			betReportform.setAllwelfare(allwelfare);
	    			betReportform.setPayrebate(payrebate);
	    			betReportform.setTodayrechargerebate(todayrebate);
	    			betReportform.setTransferaccount(sumtransferaccount);
	    			betReportform.setAgentid(agentid);
	    			betReportform.setAgentparentid(betAgent.getParentid());
	    			betReportform.setAgentparentids(betAgent.getParentids());
	    			betReportform.setTzty(ttzzttyy);
//	    			betReportform.setMks(mks);
//	    			betReportform.setGks(gks);
	    			betReportformService.save(betReportform);
				}
			}
			
    } catch (Exception e) {
    		System.out.println(e);
			e.printStackTrace();
	}
}
	
	
}
