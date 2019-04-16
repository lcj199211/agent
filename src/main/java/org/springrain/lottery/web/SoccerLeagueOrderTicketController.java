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
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.lottery.entity.SoccerLeagueResult;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.ISoccerAllotTicketService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerLeagueOrderContentService;
import org.springrain.lottery.service.ISoccerLeagueOrderService;
import org.springrain.lottery.service.ISoccerLeagueResultService;
import org.springrain.lottery.service.ISoccerSchemeMatchService;
import org.springrain.lottery.service.ISoccerSchemeService;
import org.springrain.lottery.utils.WeekOfDate;
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
 * TODO 足球出票
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.web.SoccerLeagueOrder
 */
@Controller
@RequestMapping(value="/soccerleagueorderticket")
public class SoccerLeagueOrderTicketController  extends BaseController {
	@Resource
	private ISoccerLeagueOrderService soccerLeagueOrderService;
	@Resource
	private ISoccerLeagueOrderContentService soccerLeagueOrderContentService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	@Resource
	private ISoccerSchemeMatchService soccerSchemeMatchService;
	@Resource
	private ISoccerLeagueResultService soccerLeagueResultService;
	@Resource
	private ISoccerSchemeService soccerSchemeService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private ISoccerAllotTicketService allotTicketService;
	private String listurl="/lottery/soccerleagueorderticket/soccerleagueorderList";
	
	
	   
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
	public String list(HttpServletRequest request, Model model,SoccerLeagueOrder soccerLeagueOrder) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("k"))){
			//订单详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String orderid = request.getParameter("orderid");
			//List<SoccerLeagueOrderContent> datas=soccerLeagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where  orderid = :orderid" ).setParam("orderid", orderid), SoccerLeagueOrderContent.class);
			List<SoccerLeagueOrderContent> datas=soccerLeagueOrderContentService.queryForList(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.num,b.endtime,c.oddsrealname from soccer_league_order_content a left join soccer_league_arrange b on a.mid = b.mid left join soccer_league_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid ").setParam("orderid", orderid),SoccerLeagueOrderContent.class);
			if(datas!=null){
				for(SoccerLeagueOrderContent orderContent : datas){
					orderContent.setNum(WeekOfDate.getWeekOfDate(orderContent.getEndtime())+orderContent.getNum());
					if("left_odds".equals(orderContent.getOddsname())){
						String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
						orderContent.setOddsrealname(oddsrealname);
				    }else if("right_odds".equals(orderContent.getOddsname())){
				    	String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
				    	orderContent.setOddsrealname(oddsrealname);
				    }
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerLeagueOrderContent());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerleagueorderticket/soccerleagueordercontent";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
//			String result = request.getParameter("result");
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
			List<SoccerLeagueOrder> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where (:memberid2 is null or a.memberid2 like :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :agentids) order by a.id desc").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//				 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Integer.class);
//				 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
//				 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
			}else{
			     datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :agentids) order by a.id desc").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("issuestate", issuestate).setParam("memberid2", memberid2),SoccerLeagueOrder.class,page);
//				 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Integer.class);
//				 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
//				 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				for(SoccerLeagueOrder soccerOrder : datas){
					List<SoccerLeagueOrderContent> contentDatas=soccerLeagueOrderContentService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,b.leftteamname,b.rightteamname,c.oddsrealname from soccer_league_order_content a left join soccer_league_arrange b on a.mid = b.mid left join soccer_league_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", soccerOrder.getOrderid()),SoccerLeagueOrderContent.class);
					if(contentDatas!=null){
						for(SoccerLeagueOrderContent orderContent : contentDatas){
							if("left_odds".equals(orderContent.getOddsname())){
								String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
								orderContent.setOddsrealname(oddsrealname);
						    }else if("right_odds".equals(orderContent.getOddsname())){
						    	String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
						    	orderContent.setOddsrealname(oddsrealname);
						    }
						}
					}
					soccerOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setQueryBean(soccerLeagueOrder);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
//			model.addAttribute("contentTotal", contentTotal);
//			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
//			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
		
	}
//	@RequestMapping("/refresh")
//	@ResponseBody      
//	public ReturnDatas orderrefresh(Model model,SoccerLeagueOrder soccerLeagueOrder,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
//		String account = SessionUser.getShiroUser().getAccount();
//		String agentId = SessionUser.getAgentId();
//		String company = "";
//		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
//		if(",A101,".equals(agent.getParentids())){
//			company = agentId;
//		}else{
//			company = agent.getParentids().split(",")[2];
//		}
//		List<String> orderid = soccerLeagueOrderService.queryForList(new Finder("select a.orderid from soccer_league_order a left join soccer_scheme b on a.schemeid=b.schemeid left join bet_member c on a.memberid2=c.id2 where c.isissue=1 and c.isinternal=0 and a.allot=0 and a.handle=0 and b.endtime>:endtime AND a.systemissue IS NULL order by a.bettingmoney desc limit 10  "), String.class);
//		
//		return returnObject;
//	
//	}
	@RequestMapping("/handlesoccerlist")
	public String handlesoccerlist(HttpServletRequest request, Model model,SoccerScheme soccerScheme) throws Exception {
		//user表中的account
		String account = SessionUser.getShiroUser().getAccount();
		String agentId = SessionUser.getAgentId();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		page.setPageSize(10);
		List<SoccerLeagueOrder> datas = null;
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		String company = "";
		if(",A101,".equals(agent.getParentids())){
			company = agentId;
		}else{
			company = agent.getParentids().split(",")[2];
		}
		//分配的出票订单
		datas= allotTicketService.queryForList(new Finder("select a.orderid from soccer_league_order a left join soccer_allot_ticket b on a.orderid=b.orderid where b.state=2 and b.company=:company and operater=:account ").setParam("company", company).setParam("account", account),SoccerLeagueOrder.class,page);
		if(datas!=null){
			//投注内容
			for(SoccerLeagueOrder soccerOrder : datas){
				List<SoccerLeagueOrderContent> contents = soccerLeagueOrderContentService.queryForList(new Finder("select a.mid,a.odds,a.oddsname,a.result,a.resultname,b.starttime,b.num,b.leftteamname,b.rightteamname,c.betname,c.oddsrealname from soccer_league_order_content a left join soccer_league_arrange b on a.mid=b.mid left join soccer_league_playmethod_oddsname c on a.oddsname=c.oddsname where a.orderid=:orderid order by a.id ").setParam("orderid", soccerOrder.getOrderid()), SoccerLeagueOrderContent.class);
				for (SoccerLeagueOrderContent content : contents) {
					content.setNum(WeekOfDate.getWeekOfDate(content.getStarttime())+content.getNum());
					if("left_odds".equals(content.getOddsname())){
				    	try{
				    		String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",content.getMid()),String.class);
				    		content.setOddsrealname(oddsrealname);
				    	}catch (Exception e) {
							e.printStackTrace();
						}
				    	
				    }else if("right_odds".equals(content.getOddsname())){
				    	try{
				    		String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",content.getMid()),String.class);
				    		content.setOddsrealname(oddsrealname);
				    	}catch (Exception e) {
							e.printStackTrace();
						}
				    }
				    if("rqwin".equals(content.getOddsname())||"rqflat".equals(content.getOddsname())||"rqlose".equals(content.getOddsname())){
				    	try{
				    		String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", content.getMid()), String.class);
					    	content.setBetname(content.getBetname()+"("+ letpoints+")");
				    	}catch (Exception e) {
							e.printStackTrace();
						}
				    }
				}
				soccerOrder.setContent(contents);
			}
		}
		
		returnObject.setQueryBean(soccerScheme);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		
		return "/lottery/soccerleagueorderticket/handlesoccerlist";
	}
	
	
	
	@RequestMapping("/schemelist")
	public String schemelist(HttpServletRequest request, Model model,SoccerScheme soccerScheme) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<SoccerLeagueOrder> datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),SoccerLeagueOrder.class,page);
			if(datas!=null){
				for(SoccerLeagueOrder soccerOrder : datas){
					List<SoccerLeagueOrderContent> contentDatas=soccerLeagueOrderContentService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,b.leftteamname,b.rightteamname,c.oddsrealname from soccer_league_order_content a left join soccer_league_arrange b on a.mid = b.mid left join soccer_league_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", soccerOrder.getOrderid()),SoccerLeagueOrderContent.class);
					if(contentDatas!=null){
						for(SoccerLeagueOrderContent orderContent : contentDatas){
							if("left_odds".equals(orderContent.getOddsname())){
								String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
								orderContent.setOddsrealname(oddsrealname);
						    }else if("right_odds".equals(orderContent.getOddsname())){
						    	String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
						    	orderContent.setOddsrealname(oddsrealname);
						    }
						}
					}
					soccerOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new SoccerLeagueOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerleagueorderticket/soccerschemeorderList";
		}else if("2".equals(request.getParameter("k"))){
			//查询会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerleagueorder/soccermemberList";
		}else if("3".equals(request.getParameter("k"))){
			//投注内容
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String schemeid = request.getParameter("schemeid");
			List<SoccerSchemeMatch> datas= soccerSchemeMatchService.queryForList(new Finder("select a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where a.schemeid = :schemeid order by a.id").setParam("schemeid", schemeid), SoccerSchemeMatch.class);
			if(datas!=null){
				for(SoccerSchemeMatch schemeMatch : datas){
					String oddsrealname = soccerSchemeMatchService.queryForObject(new Finder("select GROUP_CONCAT(distinct b.oddsrealname) as odds from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname where a.mid=:mid group by a.mid").setParam("mid", schemeMatch.getMid()), String.class);
					SoccerLeagueResult socceResult = soccerLeagueResultService.queryForObject(new Finder("select halfscore,allscore from soccer_league_result where mid = :mid").setParam("mid", schemeMatch.getMid()), SoccerLeagueResult.class);
					schemeMatch.setOddsrealname(oddsrealname);
					if(socceResult!=null){
						schemeMatch.setHalfscore(socceResult.getHalfscore());
						schemeMatch.setAllscore(socceResult.getAllscore());
					}
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerSchemeMatch());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerscheme/soccerschemematch";
		}if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String schemeid2 = request.getParameter("schemeid2");
			List<SoccerScheme> datas = soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where  a.schemeid2 = :schemeid2").setParam("schemeid2", schemeid2),SoccerScheme.class,page);
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
			returnObject.setData(datas);
			returnObject.setQueryBean(soccerScheme);
			model.addAttribute("schemeid2", schemeid2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerscheme/soccerschemeListgod";
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
//			List<SoccerScheme> datas = null;
			List<SoccerLeagueOrder> datas = null;
			//足球出票倍数
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			String company = "";
			if(",A101,".equals(agent.getParentids())){
				company = agentId;
			}else{
				company = agent.getParentids().split(",")[2];
			}
			Double value = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:id and remark=:company ").setParam("id", "issuebetmulriple").setParam("company", company), Double.class);
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 //今日
					datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on a.memberid2=c.id2 left join soccer_scheme b on a.schemeid=b.schemeid where c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
				 }else if("2".equals(time)){
					 //昨日
//					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,d.systemissue from soccer_scheme a left join bet_member c on c.id2=a.memberid2 left join soccer_league_order d on a.schemeid=d.schemeid where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),SoccerScheme.class,page);
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on a.memberid2=c.id2 left join soccer_scheme b on a.schemeid=b.schemeid where c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
				 }else if("3".equals(time)){
					 //本周
//					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,d.systemissue from soccer_scheme a left join bet_member c on c.id2=a.memberid2 left join soccer_league_order d on a.schemeid=d.schemeid where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),SoccerScheme.class,page);
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on a.memberid2=c.id2 left join soccer_scheme b on a.schemeid=b.schemeid  where c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
				 }else if("4".equals(time)){
					 //上周
//					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,d.systemissue from soccer_scheme a left join bet_member c on c.id2=a.memberid2 left join soccer_league_order d on a.schemeid=d.schemeid where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),SoccerScheme.class,page);
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on a.memberid2=c.id2 left join soccer_scheme b on a.schemeid=b.schemeid where c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
				 }else if("5".equals(time)){
					 //本月
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on c.id2=a.memberid2 left join soccer_scheme b on a.schemeid=b.schemeid where c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
				 }else{
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on c.id2=a.memberid2  left join soccer_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and  (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
				 }
				
			}else{
				datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from soccer_league_order a left join bet_member c on c.id2=a.memberid2 left join soccer_scheme b on a.schemeid=b.schemeid where  c.isinternal=0 and c.isissue=1 and b.bettingtime >=:starttime and b.bettingtime <=:endtime and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("issuestate", issuestate),SoccerLeagueOrder.class,page);
//				bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where a.bettingtime>=:starttime and a.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (:playmethodid is null or a.playmethodid = :playmethodid) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
//				bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where a.bettingtime>=:starttime and a.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (:buytype is null or a.buytype = :buytype) and (:playmethodid is null or a.playmethodid = :playmethodid) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("issuestate", issuestate).setParam("playmethodid", playmethodid).setParam("buytype", buytype),Double.class);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			if(datas!=null){
				//投注内容
				for(SoccerLeagueOrder soccerOrder : datas){
					List<SoccerLeagueOrderContent> contents = soccerLeagueOrderContentService.queryForList(new Finder("select a.mid,a.odds,a.oddsname,a.result,a.resultname,b.starttime,b.num,b.leftteamname,b.rightteamname,c.betname,c.oddsrealname,d.halfscore,d.allscore from soccer_league_order_content a left join soccer_league_arrange b on a.mid=b.mid left join soccer_league_playmethod_oddsname c on a.oddsname=c.oddsname left join soccer_league_result d on a.mid = d.mid where a.orderid=:orderid order by a.id ").setParam("orderid", soccerOrder.getOrderid()), SoccerLeagueOrderContent.class);
					for (SoccerLeagueOrderContent content : contents) {
						content.setNum(WeekOfDate.getWeekOfDate(content.getStarttime())+content.getNum());
						if("left_odds".equals(content.getOddsname())){
					    	try{
					    		String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",content.getMid()),String.class);
					    		content.setOddsrealname(oddsrealname);
					    	}catch (Exception e) {
								e.printStackTrace();
							}
					    	
					    }else if("right_odds".equals(content.getOddsname())){
					    	try{
					    		String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",content.getMid()),String.class);
					    		content.setOddsrealname(oddsrealname);
					    	}catch (Exception e) {
								e.printStackTrace();
							}
					    }
					    if("rqwin".equals(content.getOddsname())||"rqflat".equals(content.getOddsname())||"rqlose".equals(content.getOddsname())){
					    	try{
					    		String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", content.getMid()), String.class);
						    	content.setBetname(content.getBetname()+"("+ letpoints+")");
					    	}catch (Exception e) {
								e.printStackTrace();
							}
					    }
					}
					soccerOrder.setContent(contents);
				}
			}

			
			
			returnObject.setQueryBean(soccerScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("value", value);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/soccerleagueorderticket/soccerschemeList";
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
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueOrder soccerLeagueOrder) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeagueOrder> datas=soccerLeagueOrderService.findListDataByFinder(null,page,SoccerLeagueOrder.class,soccerLeagueOrder);
			returnObject.setQueryBean(soccerLeagueOrder);
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
		return "/lottery/soccerleagueorder/soccerleagueorderLook";
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
		  SoccerLeagueOrder soccerLeagueOrder = soccerLeagueOrderService.findSoccerLeagueOrderById(id);
		   returnObject.setData(soccerLeagueOrder);
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
	public ReturnDatas updateissuestate(Model model,SoccerScheme soccerScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			SoccerLeagueOrder order = soccerLeagueOrderService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orderid), SoccerLeagueOrder.class);
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
					soccerSchemeService.update(new Finder("update soccer_league_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", orderid));
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<SoccerLeagueOrder> order2 = soccerLeagueOrderService.queryForList(new Finder("select * from soccer_league_order where schemeid=:schemeid ").setParam("schemeid", schemeid), SoccerLeagueOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(SoccerLeagueOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				soccerSchemeService.update(new Finder("update soccer_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", soccerScheme.getSchemeid()));
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
	public ReturnDatas schemeupdateissuestate(Model model,SoccerScheme soccerScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
//			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			List<SoccerLeagueOrder> order = soccerLeagueOrderService.queryForList(new Finder("select * from soccer_league_order where schemeid=:schemeid ").setParam("schemeid", schemeid), SoccerLeagueOrder.class);
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
					soccerSchemeService.update(new Finder("update soccer_league_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid",  order.get(i).getOrderid()));
				}
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<SoccerLeagueOrder> order2 = soccerLeagueOrderService.queryForList(new Finder("select * from soccer_league_order where schemeid=:schemeid ").setParam("schemeid", schemeid), SoccerLeagueOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(SoccerLeagueOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				soccerSchemeService.update(new Finder("update soccer_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", soccerScheme.getSchemeid()));
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
	public ReturnDatas systemissue(Model model,SoccerScheme soccerScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
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
					SoccerLeagueOrder order = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orderid), SoccerLeagueOrder.class);
					if(Integer.valueOf(pass) > order.getBetmulriple()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数超越投注倍数");
					}else{
						if(order.getIssuestate()==4){
							soccerLeagueOddsService.update(new Finder("update soccer_league_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", orderid));
						}
						soccerLeagueOddsService.update(new Finder("update soccer_league_order set systemissue=1,issuebetmulriple=:issuebetmulriple where orderid=:orderid ").setParam("orderid", orderid).setParam("issuebetmulriple", pass));
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("倍数不正确");
				}
			}else{
				SoccerLeagueOrder order = soccerLeagueOrderService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orderid), SoccerLeagueOrder.class);
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
				List<SoccerLeagueOrder> order2 = soccerLeagueOrderService.queryForList(new Finder("select * from soccer_league_order where schemeid=:schemeid ").setParam("schemeid", schemeid), SoccerLeagueOrder.class);
				List<Integer>issuestate=new ArrayList<Integer>();
				for(SoccerLeagueOrder orders : order2){
					issuestate.add(orders.getIssuestate());
				}
				int ii=0;
				for(int i=0;i<issuestate.size();i++){
					if(issuestate.get(i)==3){
						ii++;
					}
				}
				if(ii==issuestate.size()){
					soccerSchemeService.update(new Finder("update soccer_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", soccerScheme.getSchemeid()));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	//方案系统出票接口
	@RequestMapping("/schemeSystemissue")
	@ResponseBody      
	public ReturnDatas schemeSystemissue(Model model,SoccerScheme soccerScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//系统出票
//			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			List<SoccerLeagueOrder> orders = soccerLeagueOddsService.queryForList(new Finder("select * from soccer_league_order where schemeid = :schemeid").setParam("schemeid", schemeid),SoccerLeagueOrder.class);
			if("1".equals(request.getParameter("k"))){
				String pass = request.getParameter("pass");
				boolean matches = pass.matches("^[1-9][0-9]*$");
				if(matches == true){
					for(int i=0;i<orders.size();i++){
//						SoccerLeagueOrder order = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orders.get(i).getOrderid()), SoccerLeagueOrder.class);
						if(Integer.valueOf(pass) > orders.get(i).getBetmulriple()){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("倍数超越投注倍数");
						}else{
							if(orders.get(i).getIssuestate()==4){
								soccerLeagueOddsService.update(new Finder("update soccer_league_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", orders.get(i).getOrderid()));
							}
							soccerLeagueOddsService.update(new Finder("update soccer_league_order set systemissue=1,issuebetmulriple=:issuebetmulriple where orderid=:orderid ").setParam("orderid", orders.get(i).getOrderid()).setParam("issuebetmulriple", pass));
						}
					} 
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("倍数不正确");
				}
			}else{
				for(int i=0;i<orders.size();i++){
//				SoccerLeagueOrder order = soccerLeagueOrderService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orderid), SoccerLeagueOrder.class);
				  if(orders!=null){	
					  if(orders.get(i).getSystemissue() != null){
						  if(orders.get(i).getSystemissue()==3){
							  returnObject.setStatus(ReturnDatas.ERROR);
							  returnObject.setMessage("该订单已手动出票");
						  }else if(orders.get(i).getSystemissue()==1){
							  returnObject.setStatus(ReturnDatas.ERROR);
						   	  returnObject.setMessage("该订单已系统出票");
						  }
					  }
			      }else{
			      	returnObject.setStatus(ReturnDatas.ERROR);
				    returnObject.setMessage("该订单不存在");
			      }
				}
//				List<SoccerLeagueOrder> order2 = soccerLeagueOrderService.queryForList(new Finder("select * from soccer_league_order where schemeid=:schemeid ").setParam("schemeid", schemeid), SoccerLeagueOrder.class);
				List<Integer>issuestate=new ArrayList<Integer>();
				for(SoccerLeagueOrder order2 : orders){
					issuestate.add(order2.getIssuestate());
				}
				int ii=0;
				for(int i=0;i<issuestate.size();i++){
					if(issuestate.get(i)==3){
						ii++;
					}
				}
				if(ii==issuestate.size()){
					soccerSchemeService.update(new Finder("update soccer_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", soccerScheme.getSchemeid()));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	
	
	
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody      
	public ReturnDatas saveorupdate(Model model,SoccerLeagueOrder soccerLeagueOrder,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
//		try {
//			if("1".equals(request.getParameter("cancel"))){
//				SoccerLeagueOrder soccerOrder = soccerLeagueOrderService.findSoccerLeagueOrderById(soccerLeagueOrder.getId());
//				if(soccerOrder!=null){
//					if(soccerOrder.getResult()!=null&&soccerOrder.getResult()==0){
//						Integer memberid2 = soccerOrder.getMemberid2();
//						BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2),BetMember.class);
//						if(member!=null){
//							SoccerLeagueOrder s = new SoccerLeagueOrder();
//							s.setId(soccerLeagueOrder.getId());
//							s.setResult(2);
//							soccerLeagueOrderService.update(s,true);
//							BetMember member2=new BetMember();
//							member2.setId(member.getId());
//							member2.setId2(member.getId2());
//							BigDecimal freezingScore = new BigDecimal(Double.toString(member.getFreezingscore()));
//							BigDecimal gameScore = new BigDecimal(Double.toString(member.getGamescore()));
//					        BigDecimal bettingMoney = new BigDecimal(Double.toString(soccerOrder.getBettingmoney()));
//							member2.setFreezingscore(freezingScore.subtract(bettingMoney).doubleValue());
//							member2.setGamescore(gameScore.add(bettingMoney).doubleValue());
//							betMemberService.update(member2,true);
//							//更新缓存
//							member.setFreezingscore(member2.getFreezingscore());
//							member.setGamescore(member2.getGamescore());
//									String ticket = member.getTicket();
//									if(ticket!=null){
//										try{
////											cached.deleteCached(("TICKET_"+ticket).getBytes());
//											ObjectMapper mapper=new ObjectMapper();
//											byte[] json = mapper.writeValueAsBytes(member);
//											
//											cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
//										}catch(Exception e){
//											//String errorMessage = e.getLocalizedMessage();
//											e.printStackTrace();
//										}
//										
//									}
//									 //金币记录
//								     BetScorerecord betScorerecord=new BetScorerecord();
//								     String content="";
//								     content="用户"+soccerOrder.getMemberid2()+"撤销在足球的订单号为"+soccerOrder.getOrderid()+"的订单，投注额为"+soccerOrder.getBettingmoney();
//								     betScorerecord.setMemberid2(member.getId2());
//								     betScorerecord.setTime(new Date());
//								     betScorerecord.setContent(content);
//								     betScorerecord.setOriginalscore(member.getScore());
//								     betScorerecord.setChangescore(0.);
//								     betScorerecord.setBalance(member.getScore());
//								     betScorerecord.setState(1);
//								     betScorerecord.setType(7);
//								     betScorerecordService.saveBetScorerecord(betScorerecord);
//								     //操作日志
//								   //操作日志
//									 String details = "";
//								     details = "撤销ID："+member.getId2()+"的用户在"+"足球-"+soccerOrder.getBettingmoney()+"分的投注";
//								     String ip = IPUtils.getClientAddress(request);
//								     String tool = AgentToolUtil.getAgentTool(request);
//								     betOptLogService.saveoptLog(tool,ip,details);;
//						}else{
//							returnObject.setStatus(ReturnDatas.ERROR);
//							returnObject.setMessage("无此用户");
//						}
//					}else{
//						returnObject.setStatus(ReturnDatas.ERROR);
//						returnObject.setMessage("已结算投注无法撤消");
//					}
//				}else{
//					returnObject.setStatus(ReturnDatas.ERROR);
//					returnObject.setMessage("无此投注ID");
//				}
//			}else{
//				//soccerLeagueOrderService.saveorupdate(soccerLeagueOrder);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(),e);
//			returnObject.setStatus(ReturnDatas.ERROR);
//			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
//		}
		return returnObject;
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
//	@RequestMapping(value = "/update/pre")
//	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
//		ReturnDatas returnObject = lookjson(model, request, response);
//		model.addAttribute(GlobalStatic.returnDatas, returnObject);
//		return "/lottery/soccerleagueorder/soccerleagueorderCru";
//	}
	
	/**
	 * 删除操作
	 */
//	@RequestMapping(value="/delete")
//	@ResponseBody      
//	public  ReturnDatas delete(HttpServletRequest request) throws Exception {
//
//			// 执行删除
//		try {
//		  String  strId=request.getParameter("id");
//		  java.lang.Integer id=null;
//		  if(StringUtils.isNotBlank(strId)){
//			 id= java.lang.Integer.valueOf(strId.trim());
//				soccerLeagueOrderService.deleteById(id,SoccerLeagueOrder.class);
//				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
//			} else {
//				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
//	}
	
	/**
	 * 删除多条记录
	 * 
	 */
//	@RequestMapping("/delete/more")
//	@ResponseBody      
//	public ReturnDatas deleteMore(HttpServletRequest request, Model model) {
//		String records = request.getParameter("records");
//		if(StringUtils.isBlank(records)){
//			 return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
//		}
//		String[] rs = records.split(",");
//		if (rs == null || rs.length < 1) {
//			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_FAIL);
//		}
//		try {
//			List<String> ids = Arrays.asList(rs);
//			soccerLeagueOrderService.deleteByIds(ids,SoccerLeagueOrder.class);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
//		}
//		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
//		
//		
//	}
	
	/*
	public String soccerlist(HttpServletRequest request, Model model,SoccerLeagueOrder soccerLeagueOrder) 
			throws Exception {
		
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String time = request.getParameter("time");
			String playmethodid = request.getParameter("playmethodid");
			String result = request.getParameter("result");
			if("100".equals(playmethodid)){
				playmethodid = null;
			}
			if("100".equals(result)){
				result = null;
			}
			if(StringUtils.isBlank(time)){
				time="0";
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<SoccerLeagueOrder> datas = null;
			Integer contentTotal = 0;
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				 if("1".equals(time)){
					 //今日
					 datas=soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
					 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
					 bettingmoneyTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
					 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 }else if("2".equals(time)){
					 //昨日
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
					 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
					 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
					 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m-%d").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 }else if("3".equals(time)){
					 //本周
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
					 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
					 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
					 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 }else if("4".equals(time)){
					 //上周
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
					 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
					 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
					 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%u").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 }else if("5".equals(time)){
					 //本月
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m").setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
					 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m").setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
					 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
					 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where date_format(b.bettingtime,:x)=date_format(now(),:x) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("x","%Y-%m").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 }else{
					 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
					 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
					 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
					 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 }
				
			}else{
				datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result),SoccerLeagueOrder.class,page);
				 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result),Integer.class);
				 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
				 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result),Double.class);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				for(SoccerLeagueOrder soccerOrder : datas){
					List<SoccerLeagueOrderContent> contentDatas=soccerLeagueOrderContentService.queryForList(new Finder("select a.oddsname,a.mid,b.leftteamname,b.rightteamname,c.oddsrealname from soccer_league_order_content a left join soccer_league_arrange b on a.mid = b.mid left join soccer_league_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", soccerOrder.getOrderid()),SoccerLeagueOrderContent.class);
					if(contentDatas!=null){
						for(SoccerLeagueOrderContent orderContent : contentDatas){
							if("left_odds".equals(orderContent.getOddsname())){
								String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
								orderContent.setOddsrealname(oddsrealname);
						    }else if("right_odds".equals(orderContent.getOddsname())){
						    	String oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
						    	orderContent.setOddsrealname(oddsrealname);
						    }
						}
					}
					soccerOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setQueryBean(soccerLeagueOrder);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("contentTotal", contentTotal);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportform/soccerreportList";
		}
		*/

}
