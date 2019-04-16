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


public class QuartzJob102 {
	@Resource
	private ISlaveBetMemberService  slaveBetMemberService;
	@Resource
	private IBetReportformService betReportformService;
	
	//增加未跑的每日报表数据
    public void work() throws Exception{ 
		try {
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("job102开始"+outputFormat.format(new Date()));
			List<BetAgent> agentidlist = slaveBetMemberService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where agentid!=:agentid ").setParam("agentid", "A101"), BetAgent.class);
	      //List<BetAgent> agentidlist = slaveBetMemberService.queryForList(new Finder("select agentid,parentid,parentids from bet_agent where parentid=:agentid ").setParam("agentid", "A101"), BetAgent.class);
			if(agentidlist!=null){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, 1);
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
    				System.out.println("job102"+yesterday);
    				for (BetAgent betAgent : agentidlist) {
    					String agentid=betAgent.getAgentid();
    					Double ttzzttyy = null;
    					ttzzttyy=slaveBetMemberService.queryForObject(new Finder("select sum(commission) as commission from bet_commission  where substring(settlementtime,1,10)=:date and (agentid=:agentid or agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
    	    			if(ttzzttyy==null){
    	    				ttzzttyy=0.;
    	    			}
    	    			
    	    			
    	    			//投注佣金
    	    			double bettingtimecommission = 0.;
    	    			try {
    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("select sum(commission) as bettingtimecommission from bet_commission where (agentid=:id or agentparentids like :aid) and  substring(bettingtime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("id", agentid));
    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingtimecommission")!=null){
    	    					bettingtimecommission = ((BigDecimal)bettingtimebetting.get("bettingtimecommission")).doubleValue();
    	    					
    	    				}else{
    	    					bettingtimecommission = 0.;
    	    				}
    	    			} catch (Exception e) {
    	    				e.printStackTrace();
    	    			}
    	    			
    	    			double noissuebettingtimecommission = 0.;
    	    			try {
    	    				Map<String, Object> bettingtimebetting = slaveBetMemberService.queryForObject(new Finder("select sum(a.commission) as bettingtimecommission from bet_commission a  left join bet_member mm on a.memberid2=mm.id2  where (a.agentid=:id or a.agentparentids like :aid) and mm.isissue=0 and  substring(a.bettingtime,1,10)=:date").setParam("date", yesterday).setParam("aid", "%,"+agentid+",%").setParam("id", agentid));
    	    				if(bettingtimebetting!=null&&bettingtimebetting.get("bettingtimecommission")!=null){
    	    					noissuebettingtimecommission = ((BigDecimal)bettingtimebetting.get("bettingtimecommission")).doubleValue();
    	    				}else{
    	    					noissuebettingtimecommission = 0.;
    	    				}
    	    			} catch (Exception e) {
    	    				e.printStackTrace();
    	    			}
    	    			
    	    			betReportformService.update(new Finder("update bet_agentreportform set tzty = :tzty,noissuebettingtimecommission = :noissuebettingtimecommission,bettingtimecommission = :bettingtimecommission where date = :date and agentid = :agentid").setParam("agentid", agentid).setParam("date", yesterday).setParam("bettingtimecommission", bettingtimecommission).setParam("noissuebettingtimecommission", noissuebettingtimecommission).setParam("tzty", ttzzttyy));
    					
//	    				//当天结算的返代理佣金
//	        			Double soccerbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from soccer_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	        			Double basketballbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from basketball_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	        			Double bjdcbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from bjdc_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and a.brokerageagentid=:agentid  and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	        			
//	        			if(soccerbrokerageagentmoney==null){
//	        				soccerbrokerageagentmoney=0.;
//	        			}
//	        			if(basketballbrokerageagentmoney==null){
//	        				basketballbrokerageagentmoney=0.;
//	        			}
//	        			if(bjdcbrokerageagentmoney==null){
//	        				bjdcbrokerageagentmoney=0.;
//	        			}
//	        			Double brokerageagentmoney = -(soccerbrokerageagentmoney + basketballbrokerageagentmoney + bjdcbrokerageagentmoney);
//	    				
//	        			
//	        			//当天结算的返代理佣金
//	        			Double noissuesoccerbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from soccer_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	        			Double noissuebasketballbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from basketball_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and a.brokerageagentid=:agentid and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	        			Double noissuebjdcbrokerageagentmoney = slaveBetMemberService.queryForObject(new Finder("select sum(a.brokerageagentmoney) from bjdc_scheme a left join bet_member b on a.memberid2=b.id2 where b.isinternal=0 and b.isissue=0 and a.brokerageagentid=:agentid  and substring(a.settlementtime,1,10)=:date and a.buytype=1 ").setParam("agentid", agentid).setParam("date", yesterday), Double.class);
//	        			
//	        			if(noissuesoccerbrokerageagentmoney==null){
//	        				noissuesoccerbrokerageagentmoney=0.;
//	        			}
//	        			if(noissuebasketballbrokerageagentmoney==null){
//	        				noissuebasketballbrokerageagentmoney=0.;
//	        			}
//	        			if(noissuebjdcbrokerageagentmoney==null){
//	        				noissuebjdcbrokerageagentmoney=0.;
//	        			}
//	        			Double noissuebrokerageagentmoney = -(noissuesoccerbrokerageagentmoney + noissuebasketballbrokerageagentmoney + noissuebjdcbrokerageagentmoney);
    	    				
    	    			
    	    			
					}
    			}
			
			}
			System.out.println("job102结束"+outputFormat.format(new Date()));
		
    } catch (Exception e) {
//    		System.out.println(e);
//			e.printStackTrace();
    	System.out.println("QuartzJob102有异常");
    }
}
}
