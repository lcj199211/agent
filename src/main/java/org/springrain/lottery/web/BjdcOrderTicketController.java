package  org.springrain.lottery.web;

import java.util.ArrayList;
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

import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.lottery.entity.BjdcOrderContent;
import org.springrain.lottery.entity.BjdcResult;
import org.springrain.lottery.entity.BjdcScheme;
import org.springrain.lottery.entity.BjdcSchemeMatch;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBjdcOddsService;
import org.springrain.lottery.service.IBjdcOrderContentService;
import org.springrain.lottery.service.IBjdcOrderService;
import org.springrain.lottery.service.IBjdcResultService;
import org.springrain.lottery.service.IBjdcSchemeMatchService;
import org.springrain.lottery.service.IBjdcSchemeService;
import org.springrain.system.service.IDicDataService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 北单出票
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.web.SoccerLeagueOrder
 */
@Controller
@RequestMapping(value="/bjdcorderticket")
public class BjdcOrderTicketController  extends BaseController {
	@Resource
	private IBjdcOrderService bjdcOrderService;
	@Resource
	private IBjdcOrderContentService bjdcOrderContentService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBjdcSchemeMatchService bjdcSchemeMatchService;
	@Resource
	private IBjdcResultService bjdcResultService;
	@Resource
	private IBjdcSchemeService bjdcSchemeService;
	@Resource
	private IBjdcOddsService bjdcOddsService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/bjdcorderticket/bjdcorderList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BjdcOrder bjdcOrder) 			{
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("k"))){
			try {
				//订单详情
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				String orderid = request.getParameter("orderid");
				List<BjdcOrderContent> datas=bjdcOrderContentService.queryForList(new Finder("select a.*,b.matchname,b.hometeam,b.guestteam,b.starttime,b.num,b.endtime,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid ").setParam("orderid", orderid),BjdcOrderContent.class);
				returnObject.setData(datas);
				returnObject.setQueryBean(new BjdcOrderContent());
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
			} catch (Exception e) {
				System.out.println(e);
			}
			return  "/lottery/bjdcorderticket/bjdcordercontent";
		}else{
			try {
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				String starttime = request.getParameter("startTime");
				String endtime = request.getParameter("endTime");
				String issuestate = request.getParameter("issuestate");
				String memberid2 = request.getParameter("memberid2");
				if("100".equals(issuestate)){
					issuestate = null;
				}
				if(StringUtils.isBlank(memberid2)){
					memberid2=null;
				}
				if(StringUtils.isBlank(starttime)){
					starttime="0000-01-01";
				}
				if(StringUtils.isBlank(endtime)){
					endtime="9999-01-01";
				}
				java.sql.Date startDate = java.sql.Date.valueOf(starttime);
				java.sql.Date endDate=java.sql.Date.valueOf(endtime);
				List<BjdcOrder> datas = null;
				if(starttime=="0000-01-01" && endtime=="9999-01-01"){
					 datas= bjdcOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :agentids) order by a.id desc").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				}else{
				     datas= bjdcOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where b.bettingtime>=:starttime and b.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :agentids) order by a.id desc").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("issuestate", issuestate).setParam("memberid2", memberid2),BjdcOrder.class,page);
				}
				if(starttime=="0000-01-01"){
					startDate=null;
				}
				if(endtime=="9999-01-01"){
					endDate=null;
				}
				if(datas!=null){
					for(BjdcOrder bdOrder : datas){
						List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", bdOrder.getOrderid()),BjdcOrderContent.class);
						bdOrder.setOrdercontent(contentDatas);
					}
				}
				returnObject.setQueryBean(bjdcOrder);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("startTime", startDate);
				model.addAttribute("endTime", endDate);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
			} catch (Exception e) {
				System.out.println(e);
			}
			return listurl;
		}
		
	}
	
	
	@RequestMapping("/schemelist")
	public String schemelist(HttpServletRequest request, Model model, BjdcScheme bjdcScheme) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<BjdcOrder> datas= bjdcOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BjdcOrder.class,page);
			if(datas!=null){
				for(BjdcOrder bdOrder : datas){
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", bdOrder.getOrderid()),BjdcOrderContent.class);
					bdOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new SoccerLeagueOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorderticket/bjdcschemeorderList";
		}else if("2".equals(request.getParameter("k"))){
			//查询会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorder/bjdcmemberList";
		}else if("3".equals(request.getParameter("k"))){
			//投注内容
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String schemeid = request.getParameter("schemeid");
			List<BjdcSchemeMatch> datas= bjdcSchemeMatchService.queryForList(new Finder("select a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid where a.schemeid = :schemeid order by a.id").setParam("schemeid", schemeid), BjdcSchemeMatch.class);
			if(datas!=null){
				for(BjdcSchemeMatch bjdcMatch : datas){
					String oddsrealname = bjdcSchemeMatchService.queryForObject(new Finder("select GROUP_CONCAT(distinct b.oddsrealname) as odds from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname where a.fid=:fid group by a.fid").setParam("fid", bjdcMatch.getFid()), String.class);
					BjdcResult bjdcResult = bjdcResultService.queryForObject(new Finder("select halfscore,allscore from bjdc_result where fid = :fid").setParam("fid", bjdcMatch.getFid()), BjdcResult.class);
					bjdcMatch.setOddsrealname(oddsrealname);
					if(bjdcResult!=null){
						bjdcMatch.setHalfscore(bjdcResult.getHalfscore());
						bjdcMatch.setAllscore(bjdcResult.getAllscore());
					}
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerSchemeMatch());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcscheme/bjdcschemematch";
		}if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String schemeid2 = request.getParameter("schemeid2");
			List<BjdcScheme> datas = bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where  a.schemeid2 = :schemeid2").setParam("schemeid2", schemeid2),BjdcScheme.class,page);
			if(datas!=null){
				List<String> schemeids=new ArrayList<String>();
				for (BjdcScheme bjdcScheme2 : datas) {
					String schemeid = bjdcScheme2.getSchemeid();
					if(schemeid!=null){
						schemeids.add(bjdcScheme2.getSchemeid());
					}
				}
				List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
				if(matchDatas!=null){
					List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
							    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid").setParam("fid", m.get("fid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
							    	}
						    	}catch (Exception e) {
									e.printStackTrace();
									String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid").setParam("fid", m.get("fid").toString()), String.class);
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
			returnObject.setData(datas);
			returnObject.setQueryBean(bjdcScheme);
			model.addAttribute("schemeid2", schemeid2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcscheme/bjdcschemeListgod";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String memberid2 = request.getParameter("memberid2");
			String playmethodid = request.getParameter("playmethodid");
			String issuestate = request.getParameter("issuestate");
			String time = request.getParameter("time");
			String buytype = request.getParameter("buytype");
			if("100".equals(buytype)){
				buytype = null;
			}
			if("100".equals(issuestate)){
				issuestate=null;
			}
			if("100".equals(playmethodid)){
				playmethodid = null;
			}
			if(StringUtils.isBlank(time)){
				time="1";
			}
			if(StringUtils.isBlank(memberid2)){
				memberid2=null;
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BjdcOrder> datas = null;
			//北单出票倍数
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			String company = "";
			if(",A101,".equals(agent.getParentids())){
				company = agentId;
			}else{
				company = agent.getParentids().split(",")[2];
			}
			Double value = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:id and remark=:company ").setParam("id", "issuebetmulriple_bjdc").setParam("company", company), Double.class);
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 //今日
					datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2 left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				 }else if("2".equals(time)){
					 //昨日
					 datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2 left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				 }else if("3".equals(time)){
					 //本周
					 datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2  left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				 }else if("4".equals(time)){
					 //上周
					 datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2  left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				 }else if("5".equals(time)){
					 //本月
					 datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2  left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				 }else{
					 datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2 left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
				 }
			}else{
				datas= bjdcOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from bjdc_order a left join bet_member c on c.id2=a.memberid2 left join bjdc_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and b.bettingtime>=:starttime and b.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("issuestate", issuestate),BjdcOrder.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			if(datas!=null){
				//投注内容
				for(BjdcOrder bjdcOrder : datas){
					List<BjdcOrderContent> contents = bjdcOrderContentService.queryForList(new Finder("select a.fid,a.odds,a.oddsname,a.result,a.resultname,b.starttime,b.num,b.hometeam,b.guestteam,c.betname,c.oddsrealname,d.halfscore,d.allscore from bjdc_order_content a left join bjdc_arrange b on a.fid=b.fid left join bjdc_playmethod_oddsname c on a.oddsname=c.oddsname left join bjdc_result d on a.fid = d.fid where a.orderid=:orderid order by a.id ").setParam("orderid", bjdcOrder.getOrderid()), BjdcOrderContent.class);
					for (BjdcOrderContent content : contents) {
					    if("rqwin".equals(content.getOddsname())||"rqflat".equals(content.getOddsname())||"rqlose".equals(content.getOddsname())){
					    	try{
					    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid").setParam("mid", content.getFid()), String.class);
						    	content.setBetname(content.getBetname()+"("+ letpoints+")");
					    	}catch (Exception e) {
								e.printStackTrace();
							}
					    }
					}
					bjdcOrder.setContent(contents);
				}
			
			}
			returnObject.setQueryBean(bjdcScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("value", value);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/bjdcorderticket/bjdcschemeList";
		}
	}
	
	
	
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,BjdcOrder bjdcOrder) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BjdcOrder> datas=bjdcOrderService.findListDataByFinder(null,page,BjdcOrder.class,bjdcOrder);
			returnObject.setQueryBean(bjdcOrder);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/bjdcorder/bjdcorderLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BjdcOrder bjdcOrder = bjdcOrderService.findBjdcOrderById(id);
		   returnObject.setData(bjdcOrder);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/updateissuestate")
	@ResponseBody      
	public ReturnDatas updateissuestate(Model model,BjdcScheme bjdcScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			BjdcOrder order = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_order where orderid=:orderid ").setParam("orderid", orderid), BjdcOrder.class);
			if(order!=null){
				if(order.getSystemissue() != null&&order.getIssuestate()==3){
					if(order.getSystemissue()==3){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该订单已手动出票");
					}else if(order.getSystemissue()==1){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该订单已系统出票");
					}
				}else{
					bjdcSchemeService.update(new Finder("update bjdc_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", orderid));
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<BjdcOrder> order2 = bjdcSchemeService.queryForList(new Finder("select * from bjdc_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BjdcOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(BjdcOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				bjdcSchemeService.update(new Finder("update bjdc_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", bjdcScheme.getSchemeid()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	/**
	 * 方案手动出票
	 * 
	 */
	@RequestMapping("/schemeUpdateissuestate")
	@ResponseBody      
	public ReturnDatas schemeUpdateissuestate(Model model,BjdcScheme bjdcScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
//			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			List<BjdcOrder> order = bjdcSchemeService.queryForList(new Finder("select * from bjdc_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BjdcOrder.class);
			if(order!=null){
				for(int i=0;i<order.size();i++){
				if(order.get(i).getSystemissue() != null&&order.get(i).getIssuestate()==3){
					if(order.get(i).getSystemissue()==3){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该订单已手动出票");
					}else if(order.get(i).getSystemissue()==1){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该订单已系统出票");
					}
				}else{
					bjdcSchemeService.update(new Finder("update bjdc_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", order.get(i).getOrderid()));
				}
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<BjdcOrder> order2 = bjdcSchemeService.queryForList(new Finder("select * from bjdc_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BjdcOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(BjdcOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				bjdcSchemeService.update(new Finder("update bjdc_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", bjdcScheme.getSchemeid()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	//方案系统出票
	@RequestMapping("/schemeSystemissue")
	@ResponseBody      
	public ReturnDatas schemeSystemissue(Model model,BjdcScheme bjdcScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
				//系统出票
//				String orderid = request.getParameter("orderid");
				String schemeid = request.getParameter("schemeid");
				List<BjdcOrder> order = bjdcOrderService.queryForList(new Finder("select * from bjdc_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BjdcOrder.class);
				if("1".equals(request.getParameter("k"))){
					String pass = request.getParameter("pass");
					boolean matches = pass.matches("^[1-9][0-9]*$");
					if(matches == true){
						for(int i=0;i<order.size();i++){
						if(Integer.valueOf(pass) > order.get(i).getBetmulriple()){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("倍数超越投注倍数");
						}else{
							if(order.get(i).getIssuestate()==4){
								bjdcOrderService.update(new Finder("update bjdc_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", order.get(i).getOrderid()));
							}
							bjdcOrderService.update(new Finder("update bjdc_order set systemissue=1,issuebetmulriple=:issuebetmulriple where orderid=:orderid ").setParam("orderid", order.get(i).getOrderid()).setParam("issuebetmulriple", pass));
						}
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数不正确");
					}
				}else{
//					BjdcOrder order = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_order where orderid=:orderid ").setParam("orderid", orderid), BjdcOrder.class);
					for(int i=0;i<order.size();i++){
					if(order!=null){
						if(order.get(i).getSystemissue() != null){
							if(order.get(i).getSystemissue()==3){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该订单已手动出票");
							}else if(order.get(i).getSystemissue()==1){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该订单已系统出票");
							}
						}
				    }else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该订单不存在");
				}
					}
					List<BjdcOrder> order2 = bjdcSchemeService.queryForList(new Finder("select * from bjdc_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BjdcOrder.class);
					List<Integer>issuestate=new ArrayList<Integer>();
					for(BjdcOrder orders : order2){
						issuestate.add(orders.getIssuestate());
					}
					int ii=0;
					for(int i=0;i<issuestate.size();i++){
						if(issuestate.get(i)==3){
							ii++;
						}
					}
					if(ii==issuestate.size()){
						bjdcSchemeService.update(new Finder("update bjdc_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", bjdcScheme.getSchemeid()));
					}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	@RequestMapping("/systemissue")
	@ResponseBody      
	public ReturnDatas systemissue(Model model,BjdcScheme bjdcScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
				//系统出票
				String orderid = request.getParameter("orderid");
				String schemeid = request.getParameter("schemeid");
				if("1".equals(request.getParameter("k"))){
					String pass = request.getParameter("pass");
					boolean matches = pass.matches("^[1-9][0-9]*$");
					if(matches == true){
						BjdcOrder order = bjdcOrderService.queryForObject(new Finder("select * from bjdc_order where orderid=:orderid ").setParam("orderid", orderid), BjdcOrder.class);
						if(Integer.valueOf(pass) > order.getBetmulriple()){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("倍数超越投注倍数");
						}else{
							if(order.getIssuestate()==4){
								bjdcOrderService.update(new Finder("update bjdc_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", orderid));
							}
							bjdcOrderService.update(new Finder("update bjdc_order set systemissue=1,issuebetmulriple=:issuebetmulriple where orderid=:orderid ").setParam("orderid", orderid).setParam("issuebetmulriple", pass));
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数不正确");
					}
				}else{
					BjdcOrder order = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_order where orderid=:orderid ").setParam("orderid", orderid), BjdcOrder.class);
					if(order!=null){
						if(order.getSystemissue() != null){
							if(order.getSystemissue()==3){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该订单已手动出票");
							}else if(order.getSystemissue()==1){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该订单已系统出票");
							}
						}
				    }else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该订单不存在");
				}
					List<BjdcOrder> order2 = bjdcSchemeService.queryForList(new Finder("select * from bjdc_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BjdcOrder.class);
					List<Integer>issuestate=new ArrayList<Integer>();
					for(BjdcOrder orders : order2){
						issuestate.add(orders.getIssuestate());
					}
					int ii=0;
					for(int i=0;i<issuestate.size();i++){
						if(issuestate.get(i)==3){
							ii++;
						}
					}
					if(ii==issuestate.size()){
						bjdcSchemeService.update(new Finder("update bjdc_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", bjdcScheme.getSchemeid()));
					}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
}
