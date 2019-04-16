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
import org.springrain.lottery.service.IBetReportformService;
import org.springrain.lottery.service.ISlaveBetMemberService;


public class QuartzJob7 {
	@Resource
	private ISlaveBetMemberService  slaveBetMemberService;
	@Resource
	private IBetReportformService betReportformService;
	
	//每日报表返代理佣金
    public void work() throws Exception{ 
    		try {
    			List<BetAgent> agentidlist = slaveBetMemberService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where parentid=:agentid ").setParam("agentid", "A101"), BetAgent.class);
    			if(agentidlist!=null){
    				Calendar cal = Calendar.getInstance();
    				cal.add(Calendar.DATE, 1);
	    			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	    			Calendar calendar = new GregorianCalendar();
	    			for(int i = 1 ; i<3 ; i++){
	    				Date date2 =DateUtils.convertString2Date(yesterday);
	    				if(date2!=null){
	    					calendar.setTime(date2); 
	    					calendar.add(Calendar.DATE,-1);
	    					Date date3=calendar.getTime();
	    					yesterday = DateUtils.convertDate2String(date3);
	    				}
	    				for (BetAgent betAgent : agentidlist) {
	    					String agentid=betAgent.getAgentid();
    	    				
    	    				//当天结算的返代理佣金
    	        			Double soccerbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from soccer_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	        			Double basketballbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from basketball_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	        			Double bjdcbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from bjdc_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.brokerageagentid=:agentid  and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	        			
    	        			if(soccerbrokerageagentmoney==null){
    	        				soccerbrokerageagentmoney=0.;
    	        			}
    	        			if(basketballbrokerageagentmoney==null){
    	        				basketballbrokerageagentmoney=0.;
    	        			}
    	        			if(bjdcbrokerageagentmoney==null){
    	        				bjdcbrokerageagentmoney=0.;
    	        			}
    	        			Double brokerageagentmoney = -(soccerbrokerageagentmoney + basketballbrokerageagentmoney + bjdcbrokerageagentmoney);
    	    				
    	        			
    	        			//当天结算的返代理佣金
    	        			Double noissuesoccerbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from soccer_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	        			Double noissuebasketballbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from basketball_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	        			Double noissuebjdcbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from bjdc_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and a.brokerageagentid=:agentid  and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	        			
    	        			if(noissuesoccerbrokerageagentmoney==null){
    	        				noissuesoccerbrokerageagentmoney=0.;
    	        			}
    	        			if(noissuebasketballbrokerageagentmoney==null){
    	        				noissuebasketballbrokerageagentmoney=0.;
    	        			}
    	        			if(noissuebjdcbrokerageagentmoney==null){
    	        				noissuebjdcbrokerageagentmoney=0.;
    	        			}
    	        			Double noissuebrokerageagentmoney = -(noissuesoccerbrokerageagentmoney + noissuebasketballbrokerageagentmoney + noissuebjdcbrokerageagentmoney);
	    	    				
	    	    			betReportformService.update(new Finder("update bet_agentreportform set brokerageagentmoney = :brokerageagentmoney,noissuebrokerageagentmoney = :noissuebrokerageagentmoney where date = :date and agentid = :agentid").setParam("agentid", agentid).setParam("date", yesterday).setParam("brokerageagentmoney", brokerageagentmoney).setParam("noissuebrokerageagentmoney", noissuebrokerageagentmoney));
	    	    			
						}
	    			}
    			
    			}
    		
        } catch (Exception e) {
        	System.out.println("QuartzJob7有异常");
        }
    }
}
