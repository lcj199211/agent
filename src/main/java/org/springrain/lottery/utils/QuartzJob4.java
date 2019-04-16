package org.springrain.lottery.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetReportform;
import org.springrain.lottery.service.IBetReportformService;
import org.springrain.lottery.service.ISlaveBetMemberService;
import org.springrain.system.service.IDicDataService;


public class QuartzJob4 {
	@Resource
	private ISlaveBetMemberService  slaveBetMemberService;
	@Resource
	private IBetReportformService betReportformService;
	@Resource
	private IDicDataService dicDataService;
	//代理报表
    public void work() throws Exception{ 
    		try {
    			System.out.println("QuartzJob4开始工作===");
    			//21-2点 高峰不跑
    			Integer hour = Integer.valueOf(new SimpleDateFormat("HH").format(new Date()));
    			Integer start = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:code").setParam("code", "reportfrom_job4start"), Integer.class);
    			Integer end = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:code").setParam("code", "reportfrom_job4end"), Integer.class);
    			if(start!=end){
    				if(hour>=start||hour<=end){
        				return;
        			}
    			} 
    			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    			System.out.println("job4开始"+outputFormat.format(new Date()));
				dicDataService.update(new Finder("update t_dic_data set remark = :remark where code=:code").setParam("code", "agentjob4").setParam("remark", outputFormat.format(new Date())));
    			List<BetAgent> agentidlist = slaveBetMemberService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where agentid!=:agentid and active=1 ").setParam("agentid", "A101"), BetAgent.class);
//				List<BetAgent> agentidlist = slaveBetMemberService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where agentid!=:agentid and active=1 and qq=0 ").setParam("agentid", "A101"), BetAgent.class);
				if(agentidlist!=null){
    				Calendar cal = Calendar.getInstance();
    				cal.add(Calendar.DATE, 1);
	    			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    			Calendar calendar = new GregorianCalendar();
	    			for(int i = 1 ; i<15 ; i++){
	    				Date date2 =DateUtils.convertString2Date(yesterday);
	    				if(date2!=null){
	    					calendar.setTime(date2); 
	    					calendar.add(Calendar.DATE,-1);
	    					Date date3=calendar.getTime();
	    					yesterday = DateUtils.convertDate2String(date3);
	    				}
//	    				System.out.println("job4"+yesterday);
	    				for (BetAgent betAgent : agentidlist) {
	    					String agentid=betAgent.getAgentid();
	    					
	    					Calendar cal2 = Calendar.getInstance();
    		    			String today = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());
    		    			
    		    			Date datetoday =DateUtils.convertString2Date(today);
    	    				String today1 = "";
    	    				if(datetoday!=null){
    	    					calendar.setTime(datetoday); 
    	    					calendar.add(Calendar.DATE,-1);
    	    					Date date3=calendar.getTime();
    	    					today1 = DateUtils.convertDate2String(date3);
    	    				}
    		    			
    	    				Double sumScore = null;
    	    				Double sumScoree = null;
    	    				Double sumgamescore = null;
    	    				Double sumbankscore = null; 
    	    				Double sumfreezingscore = null;
    	    				Double sumnoissuescore = null;
    	    				Double sumnoissuegamescore = null;
    	    				Double sumnoissuebankscore = null;
    	    				Double sumnoissuefreezingscore = null;
    	    				Integer registNum = null; 
    	    				Double sumBetGoldMoney = null;
    	    				Double sumBetWithdrawcashMoney = null;
//    	    				Double sumBetScorerecordMoney = null;
    	    				Integer sumReliefScore = null;
    	    				Double sumDaywinorfailRebate = null;
    	    				Double sumRankRebate = null;
    	    				Double sumRedRecord = null;
    	    				Double sumSubordinaterebate = null; 
    	    				Double firstrebate = null;
    	    				Double sumGameWin = null;
    	    				Double settletimebettingscore = null;
    	    				Double settletimebrokeragemoney = null;
    	    				Double sumBettingMoney = null;
    	    				Double betsumty = null;
    	    				Double payrebate = null;
    	    				Double todayrebate = null;
    	    				Double registersend = null;
    	    				Double result = null;
    	    				Double sumWeekwinorfailrebate = null;
    	    				Double sumtransferaccount = null;
    	    				Double sumagentwithdrawcash = null;
    	    				Double ttzzttyy = null;
    	    				Double allwelfare = null;
    	    				Double activitywelfare = null;
    	    				Double agentproxies = null;
    	    				
    	    				Double sumtransferaccountagent = null;
    	    				Double sumtransferaccountagent1 = null;
    	    				Double sumbrokerage = null;
    		    			if(today.equals(yesterday)){
    		    				//前天的日期
    	    	    			String before_yesterday = null;
    	    	    			Date date1 =DateUtils.convertString2Date(yesterday);
    		    				if(date1!=null){
    		    					calendar.setTime(date1); 
    		    					calendar.add(Calendar.DATE,-1);
    		    					Date date3=calendar.getTime();
    		    					before_yesterday = DateUtils.convertDate2String(date3);
    		    				}
    	    	    			
    	    	    			//注册人数
    	    	    			registNum = slaveBetMemberService.queryForObject(new Finder("select count(*) from bet_member where isinternal=0 and substring(signdate,1,10)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Integer.class);
    	    	    			if(registNum==null) {
    	    	    				registNum=0;
    	    	    			}
    	    	    			//充值统计
    	    	    			sumBetGoldMoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.money) from bet_gold a where  (a.agentid=:agentid or a.agentparentids like :aid) AND a.state=2 and substring(a.rechargetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(sumBetGoldMoney==null){
    	    	    				sumBetGoldMoney=0.;
    	    	    			}
    	    	    			//提现统计
    	    	    			sumBetWithdrawcashMoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.money) from bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=2 and substring(a.audittime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(sumBetWithdrawcashMoney==null){
    	    	    				sumBetWithdrawcashMoney=0.;
    	    	    			}
    	    	    			
    	    	    			//商户提现
    	    	    			agentproxies = slaveBetMemberService.queryForObject(new Finder("select sum(money) from bet_agentproxies where state=2 and (agentid=:agentid or agentparentids like :aid) and substring(applicationtime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(agentproxies==null){
    	    	    				agentproxies=0.;
    	    	    			}
//    	    	    			
    	    	    			//游戏输赢
//    	    	    			Double sumGameWin = betBettingService.queryForObject(new Finder("select sum(a.bettingscore-a.bettingmoney) from bet_betting a left join bet_member b on a.memberid=b.id where b.isinternal=0 and substring(a.settlementtime,1,10)=:date ").setParam("date", yesterday), Double.class);
    	    	    			sumGameWin = slaveBetMemberService.queryForObject(new Finder("select sum(a.bettingscore-a.bettingmoney) from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(sumGameWin==null){
    	    	    				sumGameWin=0.;
    	    	    			}
    	    	    			
    	    	    			//当天结算的派彩金额
    	    	    			settletimebettingscore = slaveBetMemberService.queryForObject(new Finder("select sum(a.bettingscore) from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(settletimebettingscore==null){
    	    	    				settletimebettingscore=0.;
    	    	    			}
    	    	    			
    	    	    			//当天结算的大神佣金
    	    	    			Double soccerbrokeragemoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokeragemoney) from soccer_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.buytype=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			Double basketballbrokeragemoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokeragemoney) from basketball_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.buytype=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			Double bjdcbrokeragemoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokeragemoney) from bjdc_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.buytype=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			
    	    	    			if(soccerbrokeragemoney==null){
    	    	    				soccerbrokeragemoney=0.;
    	    	    			}
    	    	    			if(basketballbrokeragemoney==null){
    	    	    				basketballbrokeragemoney=0.;
    	    	    			}
    	    	    			if(bjdcbrokeragemoney==null){
    	    	    				bjdcbrokeragemoney=0.;
    	    	    			}
    	    	    			settletimebrokeragemoney = soccerbrokeragemoney + basketballbrokeragemoney + bjdcbrokeragemoney;
    	    	    			
    	    	    			//投注额
//    	    	    			Double sumBettingMoney = betBettingService.queryForObject(new Finder("select sum(a.bettingmoney) from bet_betting a left join bet_member b on a.memberid=b.id where b.isinternal=0 and state!=2 and substring(a.bettingtime,1,10)=:date ").setParam("date", yesterday), Double.class);
    	    	    			sumBettingMoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state!=2 and substring(a.bettingtime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(sumBettingMoney==null){
    	    	    				sumBettingMoney=0.;
    	    	    			}
    	    	    			
    	    	    			//首冲返利
    	    	    			firstrebate = slaveBetMemberService.queryForObject(new Finder("select sum(rebate) from bet_firstrechargerebate a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date and state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(firstrebate==null){
    	    	    				firstrebate=0.;
    	    	    			}
    	    	    			
    	    	    			//单笔充值返利
    	    	    			payrebate = slaveBetMemberService.queryForObject(new Finder("select sum(a.rebate) from bet_singlerecharge a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=1 and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(payrebate == null){
    	    	    				payrebate = 0.;
    	    	    			}
    	    	    			
    	    	    			//注册送
    	    	    			registersend = slaveBetMemberService.queryForObject(new Finder("select sum(a.reward) from bet_register_reward a  left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.receivetime,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(registersend == null){
    	    	    				registersend = 0.;
    	    	    			}
    	    	    			
    	    	    			//活动福利
    	    	    			activitywelfare = registersend+firstrebate+payrebate;
    	    	    			
    	    	    			//昨日留存
    	    	    			result = slaveBetMemberService.queryForObject(new Finder("select score from bet_agentreportform where agentid=:agentid and date=:date limit 1 ").setParam("agentid", agentid).setParam("date", before_yesterday), Double.class);
    	    	    			if(result == null){
    	    	    				result = 0.;
    	    	    			}
    	    	    			
    	    	    			//转账
    	    	    			sumtransferaccount = slaveBetMemberService.queryForObject(new Finder("select sum(a.transferaccountsscore) from bet_transfer_accounts a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.time,1,10)=:date ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(sumtransferaccount==null){
    	    	    				sumtransferaccount=0.;
    	    	    			}
    	    	    			
    	    	    			//代理提现
    	    	    			sumagentwithdrawcash = slaveBetMemberService.queryForObject(new Finder("select sum(money) from bet_agentwithdraw where  (agentid=:agentid or agentparentids like :aid) and substring(audittime,1,10)=:date and state=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(sumagentwithdrawcash==null){
    	    	    				sumagentwithdrawcash=0.;
    	    	    			}
    	    	    			//总福利
    	    	    			
    	    	    			//投注退佣
    	    	    			ttzzttyy=slaveBetMemberService.queryForObject(new Finder("select sum(commission) as commission from bet_commission  where substring(settlementtime,1,10)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    	    			if(ttzzttyy==null){
    	    	    				ttzzttyy=0.;
    	    	    			}
    		    			
    	    	    			
    	    	    			
    	    	    			//代理转账（代理转账给代理）
//    	    	    			if(betAgent.getParentid().equals("A101")){
    	    	    				sumtransferaccountagent1 = slaveBetMemberService.queryForObject(new Finder("select sum(a.transferaccountsscore) from bet_transferagent_accounts a left join bet_agent b on a.transfermanagentid=b.agentid where b.active=1 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.time,1,10)=:date ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%").setParam("date", yesterday), Double.class);
//    	    	    			}
    	    	    			if(sumtransferaccountagent1==null){
    	    	    				sumtransferaccountagent1=0.;
    	    	    			}
    	    	    			
    	    	    			//代理会员转账（代理转账给会员）
    	    	    			sumtransferaccountagent = slaveBetMemberService.queryForObject(new Finder("select sum(a.transferaccountsscore) from bet_transfer_accounts a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=1 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.time,1,10)=:date ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%").setParam("date", yesterday), Double.class);
    	    	    			if(sumtransferaccountagent==null){
    	    	    				sumtransferaccountagent=0.;
    	    	    			}
    	    	    			
    	    	    			//领取佣金
    	    	    			sumbrokerage = slaveBetMemberService.queryForObject(new Finder("select sum(income) from bet_subordinaterebate_detail a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.state=1 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.subtime,1,10)=:date ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%").setParam("date", yesterday), Double.class);
    	    	    			if(sumbrokerage==null){
    	    	    				sumbrokerage=0.;
    	    	    			}
    		    			}
    	    	    			
	    					
	    					
	    	    			BetReportform betReportform = new BetReportform();
	    	    			betReportform.setDate(DateUtils.convertString2Date(yesterday));
	    	    			betReportform.setRegisternum(registNum);
	    	    			betReportform.setRegistersend(registersend);
	    	    			betReportform.setRecharge(sumBetGoldMoney);
	    	    			betReportform.setWithdrawcash(sumBetWithdrawcashMoney);
	    	    			//betReportform.setCardrecycle(sumBetRechargecard);
	    	    			betReportform.setCardrecycle(0d);
	    	    			betReportform.setRelief(sumReliefScore);
	    	    			betReportform.setDaywinorfailrebate(sumDaywinorfailRebate);
	    	    			betReportform.setWeekwinorfailrebate(sumWeekwinorfailrebate);
//	    	    			betReportform.setDaywinorfailrebate(0d);
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
	    	    			betReportform.setNoissuescore(sumnoissuescore);
	    	    			betReportform.setNoissuegamescore(sumnoissuegamescore);
	    	    			betReportform.setNoissuebankscore(sumnoissuebankscore);
	    	    			betReportform.setNoissuefreezingscore(sumnoissuefreezingscore);
	    	    			betReportform.setScoree(sumScoree);
	    	    			betReportform.setGamescore(sumgamescore);
	    	    			betReportform.setBankscore(sumbankscore);
	    	    			betReportform.setFreezingscore(sumfreezingscore);
	    	    			betReportform.setAgentwithdrawcash(sumagentwithdrawcash);
//	    	    			betReportform.setMks(mks);
//	    	    			betReportform.setGks(gks);
	    	    			betReportform.setSettletimebettingscore(settletimebettingscore);
	    	    			betReportform.setSettletimebrokeragemoney(settletimebrokeragemoney);
	    	    			betReportform.setActivitywelfare(activitywelfare);
	    	    			betReportform.setAgentproxies(agentproxies);
	    	    			
	    	    			betReportform.setTransferaccountagent(sumtransferaccountagent);
	    	    			betReportform.setTransferaccountagent1(sumtransferaccountagent1);
	    	    			betReportform.setBrokerage(sumbrokerage);
	    	    			
	    	    			Double transferaccount = betReportform.getTransferaccount();
	    					Double allwelfarex = betReportform.getAllwelfare();
	    					Double recharge = betReportform.getRecharge();
	    					Double withdrawcash = betReportform.getWithdrawcash();
	    					Double redpackage = betReportform.getRedpackage();
	    					Double ty = betReportform.getTy();
	    					Double winorloss = betReportform.getWinorloss();
	    					Double resultx = betReportform.getResult();
	    					
	    					Double transferaccountagent = betReportform.getTransferaccountagent();
	    					Double transferaccountagent1 = betReportform.getTransferaccountagent1();
	    					Double brokerage = betReportform.getBrokerage();
	    					if(transferaccountagent==null){
	    						transferaccountagent=0.;
	    					}
	    					if(transferaccountagent1==null){
	    						transferaccountagent1=0.;
	    					}
	    					if(brokerage==null){
	    						brokerage=0.;
	    					}
	    					
	    					if(transferaccount==null){
	    						transferaccount=0.;
	    					}
	    					if(allwelfarex==null){
	    						allwelfarex=0.;
	    					}
	    					if(recharge==null){
	    						recharge=0.;
	    					}
	    					if(withdrawcash==null){
	    						withdrawcash=0.;
	    					}
	    					if(redpackage==null){
	    						redpackage=0.;
	    					}
	    					if(ty==null){
	    						ty=0.;
	    					}
	    					if(winorloss==null){
	    						winorloss=0.;
	    					}
	    					if(resultx==null){
	    						resultx=0.;
	    					}
	    					
	    					betReportform.setDayscore(transferaccount+allwelfarex+recharge*1000-withdrawcash*1000+redpackage+ty+winorloss+resultx);
	    	    			
	    					try {
	    	    				Map<String, Object> untreatedbetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.bettingmoney) as bettingmoney FROM soccer_allbetting a  where  (a.agentid=:agentid or a.agentparentids like :aid) and a.state=0  and  substring(a.bettingtime,1,10)=:date ").setParam("date",yesterday ).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
	    	    				if(untreatedbetting!=null&&untreatedbetting.get("bettingmoney")!=null){
	    	    					Double bettingmoney = ((BigDecimal)untreatedbetting.get("bettingmoney")).doubleValue();
	    	    					betReportform.setUntreatedbettingmoney(bettingmoney);
	    	    				}else{
	    	    					betReportform.setUntreatedbettingmoney(0.);
	    	    				}
	    	    			} catch (Exception e) {
	    	    				e.printStackTrace();
	    	    			}
	    					
	    					
	    					//按投注时间统计已结算投注额、游戏输赢
	    	    			try {
	    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.bettingmoney) as bettingmoney,SUM(a.bettingscore) as bettingscore,SUM(a.bettingscore-a.bettingmoney) as bettingwin FROM soccer_allbetting a where  (a.agentid=:agentid or a.agentparentids like :aid) and a.state=1 and substring(a.bettingtime,1,10)=:date").setParam("date",yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
	    	    				if(bettingtimebetting!=null){
	    	    					if(bettingtimebetting.get("bettingmoney")!=null){
	    	    						double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
	    	    						betReportform.setBettingtimebettingmoney(bettingmoney1);
	    	    					}else{
	    	    						betReportform.setBettingtimebettingmoney(0.);
	    	    					}
		    						if(bettingtimebetting.get("bettingscore")!=null){
		    							double bettingscore1 = ((BigDecimal)bettingtimebetting.get("bettingscore")).doubleValue();
		    							betReportform.setBettingtimebettingscore(bettingscore1);
		    						}else{
		    							betReportform.setBettingtimebettingscore(0.);
		    						}
		    						if(bettingtimebetting.get("bettingwin")!=null){
		    							double bettingwin1 = ((BigDecimal)bettingtimebetting.get("bettingwin")).doubleValue();
			    						betReportform.setBettingtimebettingwin(bettingwin1);
		    						}else{
		    							betReportform.setBettingtimebettingwin(0.);
		    						}
		    						
	    	    				}
	    	    			} catch (Exception e) {
	    	    				e.printStackTrace();
	    	    			}
	    					
	    					
	    	    			//投注佣金
	    	    			try {
	    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("select sum(commission) as bettingtimecommission from bet_commission where (agentid=:id or agentparentids like :aid) and  substring(bettingtime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("id", agentid));
	    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingtimecommission")!=null){
	    	    					double bettingtimecommission = ((BigDecimal)bettingtimebetting.get("bettingtimecommission")).doubleValue();
	    	    					betReportform.setBettingtimecommission(bettingtimecommission);
	    	    				}else{
	    	    					betReportform.setBettingtimecommission(0.);
	    	    				}
	    	    			} catch (Exception e) {
	    	    				e.printStackTrace();
	    	    			}
	    	    			
	    	    			if(today.equals(yesterday)){
	    	    				//不出票用户转账
		    	    			try {
		    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.transferaccountsscore) as bettingmoney FROM bet_transfer_accounts a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid)  and  substring(a.time,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
		    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
		    	    					double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
		    	    					betReportform.setNoissuetransferscore(bettingmoney1);
		    	    				}else{
		    	    					betReportform.setNoissuetransferscore(0.);
		    	    				}
		    	    			} catch (Exception e) {
		    	    				e.printStackTrace();
		    	    			}
		    	    			
		    	    			//不出票用户充值
		    	    			try {
		    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.money) as bettingmoney FROM bet_gold a left join bet_member b on a.memberid=b.id where b.isinternal=0 and b.isissue=0 and a.state=2 and (a.agentid=:agentid or a.agentparentids like :aid)  and  substring(a.rechargetime,1,10)=:date ").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
		    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
		    	    					double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
		    	    					betReportform.setNoissuebetgold(bettingmoney1);
		    	    				}else{
		    	    					betReportform.setNoissuebetgold(0.);
		    	    				}
		    	    			} catch (Exception e) {
		    	    				e.printStackTrace();
		    	    			}
		    	    			
		    	    			//不出票用户提现
		    	    			try {
		    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT  SUM(a.money) as bettingmoney FROM bet_withdrawcash a left join bet_member b on a.memberid=b.id where b.isinternal=0 and b.isissue=0 and a.state=2 and (a.agentid=:agentid or a.agentparentids like :aid)  and  substring(a.audittime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
		    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
		    	    					double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
		    	    					betReportform.setNoissuebetwithdrawcash(bettingmoney1);
		    	    				}else{
		    	    					betReportform.setNoissuebetwithdrawcash(0.);
		    	    				}
		    	    			} catch (Exception e) {
		    	    				e.printStackTrace();
		    	    			}
		    	    			
		    	    			//不出票用户投注额
		    	    			try {
		    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.bettingmoney) as bettingmoney FROM soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state!=2  and  substring(a.bettingtime,1,10)=:date ").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
		    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
		    	    					double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
		    	    					betReportform.setNoissuebettingtimetotalbettingmoney(bettingmoney1);
		    	    				}else{
		    	    					betReportform.setNoissuebettingtimetotalbettingmoney(0.);
		    	    				}
		    	    			} catch (Exception e) {
		    	    				e.printStackTrace();
		    	    			}
		    	    			
		    	    			//当天结算不出票用户的派彩金额
		    	    			Double noissuesettletimebettingscore = slaveBetMemberService.queryForObject(new Finder("select sum(a.bettingscore) from soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.state=1 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
		    	    			if(noissuesettletimebettingscore==null){
		    	    				noissuesettletimebettingscore=0.;
		    	    			}
		    	    			betReportform.setNoissuesettletimebettingscore(noissuesettletimebettingscore);
		    	    			
		    	    			//当天结算的不出票用户的大神佣金
		    	    			Double noissuesoccerbrokeragemoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokeragemoney) from soccer_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.buytype=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
		    	    			Double noissuebasketballbrokeragemoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokeragemoney) from basketball_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.buytype=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
		    	    			Double noissuebjdcbrokeragemoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokeragemoney) from bjdc_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and substring(a.settlementtime,1,10)=:date and a.buytype=2 ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
		    	    			
		    	    			if(noissuesoccerbrokeragemoney==null){
		    	    				noissuesoccerbrokeragemoney=0.;
		    	    			}
		    	    			if(noissuebasketballbrokeragemoney==null){
		    	    				noissuebasketballbrokeragemoney=0.;
		    	    			}
		    	    			if(noissuebjdcbrokeragemoney==null){
		    	    				noissuebjdcbrokeragemoney=0.;
		    	    			}
		    	    			Double noissuesettletimebrokeragemoney = noissuesoccerbrokeragemoney + noissuebasketballbrokeragemoney + noissuebjdcbrokeragemoney;
		    	    			betReportform.setNoissuesettletimebrokeragemoney(noissuesettletimebrokeragemoney);
	    	    			}
	    	    			
	    	    			
	    	    			
	    	    			//不出票用户未结算
	    	    			try {
	    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.bettingmoney) as bettingmoney FROM soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=0 and  substring(a.bettingtime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
	    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingmoney")!=null){
	    	    					double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
	    	    					betReportform.setNoissuebettingtimeunsettlebettingmoney(bettingmoney1);
	    	    				}else{
	    	    					betReportform.setNoissuebettingtimeunsettlebettingmoney(0.);
	    	    				}
	    	    			} catch (Exception e) {
	    	    				e.printStackTrace();
	    	    			}
	    	    			
	    	    			//不出票用户投注佣金
	    	    			try {
	    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("select sum(a.commission) as bettingtimecommission from bet_commission a  left join bet_member mm on a.memberid2=mm.id2  where (a.agentid=:id or a.agentparentids like :aid) and mm.isissue=0 and  substring(a.bettingtime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("id", agentid));
	    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingtimecommission")!=null){
	    	    					double bettingtimecommission = ((BigDecimal)bettingtimebetting.get("bettingtimecommission")).doubleValue();
	    	    					betReportform.setNoissuebettingtimecommission(bettingtimecommission);
	    	    				}else{
	    	    					betReportform.setNoissuebettingtimecommission(0.);
	    	    				}
	    	    			} catch (Exception e) {
	    	    				e.printStackTrace();
	    	    			}
	    	    			
	    	    			//不出票用户已结算
	    	    			try {
	    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("SELECT SUM(a.bettingmoney) as bettingmoney,SUM(a.bettingscore) as bettingscore,SUM(a.bettingscore-a.bettingmoney) as bettingwin FROM soccer_allbetting a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and (a.agentid=:agentid or a.agentparentids like :aid) and a.state=1 and substring(a.bettingtime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid));
	    	    				if(bettingtimebetting!=null){
	    	    					if(bettingtimebetting.get("bettingmoney")!=null){
	    	    						double bettingmoney1 = ((BigDecimal)bettingtimebetting.get("bettingmoney")).doubleValue();
	    	    						betReportform.setNoissuebettingtimebettingmoney(bettingmoney1);
	    	    					}else{
	    	    						betReportform.setNoissuebettingtimebettingmoney(0.);
	    	    					}
		    						
	    	    					if(bettingtimebetting.get("bettingscore")!=null){
	    	    						double bettingscore1 = ((BigDecimal)bettingtimebetting.get("bettingscore")).doubleValue();
	    	    						betReportform.setNoissuebettingtimebettingscore(bettingscore1);
	    	    					}else{
	    	    						betReportform.setNoissuebettingtimebettingscore(0.);
	    	    					}
		    						
	    	    					if(bettingtimebetting.get("bettingwin")!=null){
	    	    						double bettingwin1 = ((BigDecimal)bettingtimebetting.get("bettingwin")).doubleValue();
			    						betReportform.setNoissuebettingtimebettingwin(bettingwin1);
	    	    					}else{
	    	    						betReportform.setNoissuebettingtimebettingwin(0.);
	    	    					}
		    						
	    	    				}
	    	    			} catch (Exception e) {
	    	    				e.printStackTrace();
	    	    			}
	    	    			
	    	    			
	    	    			if(today.equals(yesterday)){
    	    					//用户留存
    	    					Double todaysumScore = slaveBetMemberService.queryForObject(new Finder("select sum(score) from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid), Double.class);
    	    					if(todaysumScore==null){
    	    						todaysumScore=0.;
    	    					}
    	    					//出票冻结分
    	    	    			Double todaysumfreezingscore = slaveBetMemberService.queryForObject(new Finder("select sum(freezingscore) from bet_member where isinternal=0 and isissue=1 and (agentid=:agentid or agentparentids like :aid) ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%"), Double.class);
    	    	    			if(todaysumfreezingscore==null){
    	    	    				todaysumfreezingscore=0.;
    	    	    			}
    	    					//出票用户游戏分库存
    	    					Double todaysumgamescore = slaveBetMemberService.queryForObject(new Finder("select sum(gamescore) from bet_member where isinternal=0 and isissue=1 and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid), Double.class);
    	    					if(todaysumgamescore==null){
    	    						todaysumgamescore=0.;
    	    					}
    	    					//出票用户银行分库存
    	    					Double todaysumbankscore = slaveBetMemberService.queryForObject(new Finder("select sum(bankscore) from bet_member where isinternal=0 and isissue=1 and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid), Double.class);
    	    					if(todaysumbankscore==null){
    	    						todaysumbankscore=0.;
    	    					}
    	    					
    	    					betReportform.setScore(todaysumScore);
	    	    				betReportform.setGamescore(todaysumgamescore);
	    	    				betReportform.setBankscore(todaysumbankscore);
	    	    				betReportform.setFreezingscore(todaysumfreezingscore);
    	    				}
	    	    			
	    	    			BetReportform betform = betReportformService.queryForObject(new Finder("select * from bet_agentreportform where date = :date and agentid = :agentid").setParam("date", yesterday).setParam("agentid", agentid), BetReportform.class);
	    	    			if(betform!=null){
	    	    				betReportform.setId(betform.getId());
	    	    				
	    		    			
	    		    			if(!today.equals(yesterday)&&!today1.equals(yesterday)){
	    	    					betReportform.setScore(null);
		    	    				betReportform.setScoree(null);
		    	    				betReportform.setGamescore(null);
		    	    				betReportform.setBankscore(null);
		    	    				betReportform.setFreezingscore(null);
		    	    				betReportform.setNoissuescore(null);
		    	    				betReportform.setNoissuegamescore(null);
		    	    				betReportform.setNoissuebankscore(null);
		    	    				betReportform.setNoissuefreezingscore(null);
	    	    				}
	    	    				
	    	    				betReportformService.update(betReportform, true);
	    	    			}else{
	    	    				betReportformService.save(betReportform);
	    	    			}
    	    			
						}
	    			}
    				
    			}
    			
//    			System.out.println("job4结束"+outputFormat.format(new Date()));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code=:code").setParam("code", "agentjob4").setParam("value", outputFormat.format(new Date())));
    			
        } catch (Exception e) {
//        		System.out.println(e);
//    			e.printStackTrace();
        	System.out.println("QuartzJob4有异常");
        }
    }
}
