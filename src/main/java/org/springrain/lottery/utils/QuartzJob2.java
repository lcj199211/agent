package org.springrain.lottery.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetReportformsAgent;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetReportformsAgentService;
import org.springrain.lottery.service.IBetTransferagentAccountsService;
import org.springrain.lottery.service.IBetWithdrawcashService;



public class QuartzJob2 {
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetReportformsAgentService betReportformsAgentService;
	@Resource
	private IBetTransferagentAccountsService betTransferagentAccountsService;
	
	public void work() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
			List<BetAgent> list = betAgentService.queryForList(new Finder("select * from bet_agent "),BetAgent.class);	
			if((list!=null)&&(!list.isEmpty())){	
				for (BetAgent betAgent : list) {
					//下线
					String xx = betMemberService.queryForObject(new Finder("select count(*) from bet_member where agentid=:agentid ").setParam("agentid", betAgent.getAgentid()), String.class); 
					if(xx==null){
						xx="0";
					}
					//充值
					String cz = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where substring(rechargetime,1,10)=:rechargetime and agentid=:agentid ").setParam("rechargetime", yesterday).setParam("agentid", betAgent.getAgentid()), String.class);
					if(cz==null){
						cz="0";
					}
					//充值退佣
					String czty = betTransferagentAccountsService.queryForObject(new Finder("select sum(transferaccountsscore) from bet_transferagent_accounts where type=1 and agentid=:agentid and substring(time,1,10)=:date ").setParam("agentid", betAgent.getAgentid()).setParam("date", yesterday), String.class);
					if(czty==null){
						czty="0";
					}
					//提现
					String tx = betWithdrawcashService.queryForObject(new Finder("select sum(money) from bet_withdrawcash where agentid=:agentid and substring(audittime,1,10)=:audittime ").setParam("agentid", betAgent.getAgentid()).setParam("audittime", yesterday), String.class);
					if(tx==null){
						tx="0";
					}
					//投注额
					String tze = betBettingService.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where state=1 and agentid=:agentid and substring(bettingtime,1,10)=:bettingtime ").setParam("agentid", betAgent.getAgentid()).setParam("bettingtime", yesterday), String.class);
					if(tze==null){
						tze="0";
					}
					//投注退佣
					String tzty = betBettingService.queryForObject(new Finder("select sum(ty) from bet_betting where state=1 and agentid=:agentid and substring(settlementtime,1,10)=:settlementtime").setParam("agentid", betAgent.getAgentid()).setParam("settlementtime", yesterday), String.class);
					if(tzty==null){
						tzty="0";
					}
					//游戏输赢
					String yxsy = betBettingService.queryForObject(new Finder("select sum(bettingmoney-bettingscore) from bet_betting where state=1 and agentid=:agentid and substring(settlementtime,1,10)=:settlementtime ").setParam("agentid", betAgent.getAgentid()).setParam("settlementtime", yesterday),String.class);
					if(yxsy==null){
						yxsy="0";
					}
					//输赢返利
					String syyj = "0";
//					if(syyj==null){
//						syyj="0";
//					}
					
					BetReportformsAgent betReportformsAgent = new BetReportformsAgent();
					betReportformsAgent.setDate(new SimpleDateFormat( "yyyy-MM-dd ").parse(yesterday));
					betReportformsAgent.setMembernum(Integer.valueOf(xx));
					betReportformsAgent.setRecharge(Double.valueOf(cz));
					betReportformsAgent.setRechargeyj(Double.valueOf(czty));
					betReportformsAgent.setWithdraw(Double.valueOf(tx));
					betReportformsAgent.setBetmoney(Double.valueOf(tze));
					betReportformsAgent.setBetyj(Double.valueOf(tzty));
					betReportformsAgent.setGamewinorlose(Double.valueOf(yxsy));
					betReportformsAgent.setWinorloseyj(Double.valueOf(syyj));
					betReportformsAgent.setAgentid(betAgent.getAgentid());
					betReportformsAgent.setParentid(betAgent.getParentid());
					betReportformsAgent.setParentids(betAgent.getParentids());
					
					betReportformsAgentService.save(betReportformsAgent);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("QuartzJob2有异常");
		}
	}
}