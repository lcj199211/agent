package org.springrain.lottery.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Resource;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetReportform;
import org.springrain.lottery.service.IBetReportformService;
import org.springrain.lottery.service.ISlaveBetMemberService;


public class QuartzJob100 {
	@Resource
	private ISlaveBetMemberService  slaveBetMemberService;
	@Resource
	private IBetReportformService betReportformService;
	
	//代理报表
    public void work() throws Exception{ 
    		try {
    			
    			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			System.out.println("job100开始"+outputFormat.format(new Date()));
    			List<BetAgent> agentidlist = slaveBetMemberService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where agentid!=:agentid ").setParam("agentid", "A101"), BetAgent.class);
    			if(agentidlist!=null){
    				Calendar cal = Calendar.getInstance();
    				cal.add(Calendar.DATE, -5);
	    			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    			Calendar calendar = new GregorianCalendar();
	    			for(int i = 1 ; i<5 ; i++){
	    				Date date2 =DateUtils.convertString2Date(yesterday);
	    				if(date2!=null){
	    					calendar.setTime(date2); 
	    					calendar.add(Calendar.DATE,-1);
	    					Date date3=calendar.getTime();
	    					yesterday = DateUtils.convertDate2String(date3);
	    				}
	    				System.out.println("job100"+yesterday);
	    				for (BetAgent betAgent : agentidlist) {
	    					String agentid=betAgent.getAgentid();
    	    				
    	    				Double settletimebettingscore = null;
    	    				Double settletimebrokeragemoney = null;
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
	    					
	    	    			BetReportform betReportform = new BetReportform();
	    	    			betReportform.setDate(DateUtils.convertString2Date(yesterday));
	    	    			
	    	    			betReportform.setAgentid(agentid);
	    	    			betReportform.setAgentparentid(betAgent.getParentid());
	    	    			betReportform.setAgentparentids(betAgent.getParentids());
	    	    			
	    	    			betReportform.setSettletimebettingscore(settletimebettingscore);
	    	    			betReportform.setSettletimebrokeragemoney(settletimebrokeragemoney);
	    	    			
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
	    	    			
	    	    			
	    	    			BetReportform betform = betReportformService.queryForObject(new Finder("select * from bet_agentreportform where date = :date and agentid = :agentid").setParam("date", yesterday).setParam("agentid", agentid), BetReportform.class);
	    	    			if(betform!=null){
	    	    				betReportform.setId(betform.getId());
	    	    				betReportformService.update(betReportform, true);
	    	    			}
	    	    			
						}
	    			}
    				
    				
    			}
    			
    			System.out.println("job100结束"+outputFormat.format(new Date()));
    			
        } catch (Exception e) {
//        		System.out.println(e);
//    			e.printStackTrace();
        	System.out.println("QuartzJob100有异常");
        }
    }
}
