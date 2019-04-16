package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.lottery.entity.BetCommission;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.lottery.entity.SoccerLeaguePlaymethodOddsname;
import org.springrain.lottery.entity.SoccerLeagueResult;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.service.IBetAgentBrokerageService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetCommissionService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerLeagueOrderContentService;
import org.springrain.lottery.service.ISoccerLeagueOrderService;
import org.springrain.lottery.service.ISoccerLeaguePlaymethodOddsnameService;
import org.springrain.lottery.service.ISoccerLeagueResultService;
import org.springrain.lottery.service.ISoccerSchemeMatchService;
import org.springrain.lottery.service.ISoccerSchemeService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.WeekOfDate;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-18 09:08:21
 * @see org.springrain.lottery.web.SoccerScheme
 */
@Controller
@RequestMapping(value="/soccerscheme")
public class SoccerSchemeController  extends BaseController {
	@Resource
	private ISoccerSchemeService soccerSchemeService;
	@Resource
	private ISoccerLeagueOrderService soccerLeagueOrderService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ISoccerLeagueOrderContentService soccerLeagueOrderContentService;
	@Resource
	private ISoccerSchemeMatchService soccerSchemeMatchService;
	@Resource
	private ISoccerLeagueResultService soccerLeagueResultService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	@Resource
	private ICached cached;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private ISoccerLeagueOrderService leagueOrderService;
	@Resource
	private ISoccerLeagueOrderContentService leagueOrderContentService;
	@Resource
	private IBetCommissionService betCommissionService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentBrokerageService betAgentBrokerageService;
	@Resource
	private ISoccerLeaguePlaymethodOddsnameService soccerLeaguePlaymethodOddsnameService;
	
	private String listurl="/lottery/soccerscheme/soccerschemeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerScheme soccerScheme) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<SoccerLeagueOrder> datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid and (b.agentid = :agentid or b.agentparentids like :agentparentids)  order by b.id").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid),SoccerLeagueOrder.class,page);
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
			return  "/lottery/soccerscheme/soccerschemeorderList";
			
		}else if("2".equals(request.getParameter("k"))){
			//查询会员
			/*
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			*/
			return  "/lottery/soccerleagueorder/soccermemberList";
		}else if("3".equals(request.getParameter("k"))){
			//投注内容
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String schemeid = request.getParameter("schemeid");
			List<SoccerSchemeMatch> datas= soccerSchemeMatchService.queryForList(new Finder("select a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where a.schemeid = :schemeid and (a.agentid = :agentid or a.agentparentids like :agentparentids) order by a.id").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), SoccerSchemeMatch.class);
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
		}else if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String schemeid2 = request.getParameter("schemeid2");
			List<SoccerScheme> datas = soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where  a.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) order by a.bettingtime").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", schemeid2),SoccerScheme.class,page);
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
			returnObject.setPage(page);
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
			String situation = request.getParameter("situation");
			String time = request.getParameter("time");
			String buytype = request.getParameter("buytype");
			String mid2 = request.getParameter("mid");
			if(StringUtils.isBlank(mid2)){
				mid2=null;
			}
			if("100".equals(buytype)){
				buytype = null;
			}
			if(StringUtils.isBlank(situation)||situation==null){
				situation="1";
				soccerScheme.setSituation(1);
			}
			if(StringUtils.isBlank(time)){
				time="1";
			}
			if(StringUtils.isBlank(memberid2)){
				memberid2=null;
			}else{
				//memberid2="%"+memberid2+"%";
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
					datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("buytype", buytype),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("buytype", buytype),Double.class);
				 }else if("2".equals(time)){
					 //昨日
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid) and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("situation", situation).setParam("buytype", buytype),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("mid", mid2).setParam("agentparentids", "%"+agentparentids+"%").setParam("buytype", buytype),Double.class);
				 }else if("3".equals(time)){ 
					 //本周
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid) and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("mid", mid2).setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation).setParam("mid", mid2).setParam("buytype", buytype),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("mid", mid2).setParam("situation", situation).setParam("buytype", buytype),Double.class);
				 }else if("4".equals(time)){
					 //上周
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid) and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("memberid2", memberid2).setParam("situation", situation).setParam("buytype", buytype),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("memberid2", memberid2).setParam("situation", situation).setParam("buytype", buytype),Double.class);
				 }else if("5".equals(time)){
					 //本月
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid) and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("mid", mid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("mid", mid2).setParam("agentparentids", "%"+agentparentids+"%").setParam("buytype", buytype),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("mid", mid2).setParam("agentparentids", "%"+agentparentids+"%").setParam("buytype", buytype),Double.class);
				 }else{
					 datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and (:memberid2 is null or a.memberid2 = :memberid2)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("mid", mid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation),SoccerScheme.class,page);
					 bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(bettingmoney) from soccer_scheme where schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and (:memberid2 is null or memberid2 = :memberid2) and (:situation is null or situation = :situation) and (:buytype is null or buytype = :buytype) and (agentid = :agentid or agentparentids like :agentparentids)").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid2).setParam("buytype", buytype),Double.class);
					 bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(bettingwin) from soccer_scheme where schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and (:memberid2 is null or memberid2 = :memberid2) and (:situation is null or situation = :situation) and (:buytype is null or buytype = :buytype) and (agentid = :agentid or agentparentids like :agentparentids)").setParam("memberid2", memberid2).setParam("situation", situation).setParam("mid", mid2).setParam("buytype", buytype).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
				 }
			}else{
				datas= soccerSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from soccer_scheme a left join bet_member c on c.id2=a.memberid2 where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid) and a.bettingtime>=:starttime and a.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("mid", mid2).setParam("starttime",startDate).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation),SoccerScheme.class,page);
				bettingmoneyTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("mid", mid2).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("buytype", buytype),Double.class);
				bettingwinTotal = soccerSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_scheme a where a.schemeid in (select schemeid from soccer_league_order_content where (:mid is null or mid = :mid) GROUP BY schemeid)  and substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("mid", mid2).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("buytype", buytype),Double.class);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				List<String> soccerSchemeids = new ArrayList<String>();
				for (SoccerScheme soccerScheme2 : datas) {
					String schemeid = soccerScheme2.getSchemeid();
					if(schemeid!=null){
						soccerSchemeids.add(soccerScheme2.getSchemeid());
					}
				}
				List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in(:schemeid) order by a.id").setParam("schemeid", soccerSchemeids), SoccerSchemeMatch.class);
				if(matchDatas!=null){
					List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder
							("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from" +
									" soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid" +
									" where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", soccerSchemeids));
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
						    	
						    }
						    if("right_odds".equals(oddsname)){
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
			returnObject.setQueryBean(soccerScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("mid2", mid2);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerScheme soccerScheme) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerScheme> datas=soccerSchemeService.findListDataByFinder(null,page,SoccerScheme.class,soccerScheme);
			returnObject.setQueryBean(soccerScheme);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerScheme soccerScheme) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerSchemeService.findDataExportExcel(null,listurl, page,SoccerScheme.class,soccerScheme);
		String fileName="soccerScheme"+GlobalStatic.excelext;
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
		return "/lottery/soccerscheme/soccerschemeLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 SoccerScheme soccerScheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", id), SoccerScheme.class);
		  	 returnObject.setData(soccerScheme);
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
	@ResponseBody      
	public ReturnDatas saveorupdate(Model model,SoccerScheme soccerScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);

		//就这里不一样
		 String pagentid = SessionUser.getShiroUser().getAgentid();
		 BetAgent pAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", pagentid), BetAgent.class);
		 String agentparentids = ","+pagentid+",";
		try {
			if("1".equals(request.getParameter("cancel"))){
				String schemeid = request.getParameter("schemeid");
				SoccerScheme scheme = null;
				if(schemeid!=null){
					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), SoccerScheme.class);
				}else{
					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", soccerScheme.getId()), SoccerScheme.class);
				}
				if(scheme!=null){
					List<SoccerLeagueOrder> suestateList = soccerLeagueOrderService.queryForList(new Finder("select orderid from soccer_league_order where schemeid = :schemeid and issuestate = 3").setParam("schemeid", scheme.getSchemeid()),SoccerLeagueOrder.class);
					if(suestateList!=null&&!suestateList.isEmpty()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该方案存在已出票订单，无法撤消");
						return returnObject;
					}
					/**
					 * 比赛开始后就不可以撤销
					 */
					List<SoccerLeagueOrderContent> contentDatas=soccerLeagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()),SoccerLeagueOrderContent.class);
					if(contentDatas!=null){
						for(SoccerLeagueOrderContent orderContent : contentDatas){
							SoccerLeagueArrange matchDatas = soccerLeagueArrangeService.queryForObject(new Finder("select * from soccer_league_arrange where mid = :mid").setParam("mid", orderContent.getMid()),SoccerLeagueArrange.class);
							if(matchDatas!=null){
								Date time = new Date();
								if((matchDatas.getEndtime()).before(time)){
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage("该方案存在已开始的比赛，无法撤消");
									return returnObject;
								}
							}
						}
					}
					
					
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						
						Integer memberid2 = scheme.getMemberid2();
						BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
						if(member!=null){
							soccerSchemeService.update(new Finder("update soccer_scheme set buytype=1,situation=:situation where id=:id").setParam("situation", 2).setParam("id", scheme.getId()));
							soccerLeagueOrderService.update(new Finder("update soccer_league_order set result=2 where schemeid=:id").setParam("id", scheme.getSchemeid()));
							soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", scheme.getSchemeid()));
							BetMember member2=new BetMember();
							member2.setId(member.getId());
							member2.setId2(member.getId2());
							BigDecimal gameScore = new BigDecimal(Double.toString(member.getGamescore()));
					        BigDecimal bettingMoney = new BigDecimal(scheme.getBettingmoney().toString());
							member2.setGamescore(gameScore.add(bettingMoney).doubleValue());
							betMemberService.update(member2,true);
							if(scheme.getBuytype()==1){
								//跟单
								SoccerScheme sdsoccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", scheme.getSchemeid2()), SoccerScheme.class);
								soccerSchemeService.update(new Finder("update soccer_scheme set bettingalready=bettingalready-:bettingalready,bettingnum=bettingnum-:bettingnum where schemeid=:schemeid").setParam("bettingalready", scheme.getBettingmoney()).setParam("bettingnum", 1).setParam("schemeid", sdsoccersheme.getSchemeid()));
							}

							if(scheme.getBuytype()==2){
								//神单
								List<SoccerScheme> datas = soccerSchemeService.queryForList(new Finder("select * from soccer_scheme where buytype = 1 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid2", scheme.getSchemeid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), SoccerScheme.class);
								for(SoccerScheme gdScheme : datas){
									if(gdScheme.getSituation()!=null&&gdScheme.getSituation()==0){
										Integer gdmemberid2 = gdScheme.getMemberid2();
										BetMember gdmember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id2", gdmemberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),BetMember.class);
										if(gdmember!=null){
											soccerSchemeService.update(new Finder("update soccer_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", gdScheme.getId()));
											soccerLeagueOrderService.update(new Finder("update soccer_league_order set result=2 where schemeid=:id").setParam("id", gdScheme.getSchemeid()));
											soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", gdScheme.getSchemeid()));
											BetMember gdmember2=new BetMember();
											gdmember2.setId(gdmember.getId());
											gdmember2.setId2(gdmember.getId2());
											BigDecimal gdgameScore = new BigDecimal(Double.toString(gdmember.getGamescore()));
									        BigDecimal gdbettingMoney = new BigDecimal(gdScheme.getBettingmoney().toString());
									        gdmember2.setGamescore(gdgameScore.add(gdbettingMoney).doubleValue());
											betMemberService.update(gdmember2,true);
											SoccerScheme sdsoccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid2", gdScheme.getSchemeid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), SoccerScheme.class);
											soccerSchemeService.update(new Finder("update soccer_scheme set bettingalready=bettingalready-:bettingalready,bettingnum=bettingnum-:bettingnum where schemeid=:schemeid").setParam("bettingalready", gdScheme.getBettingmoney()).setParam("bettingnum", 1).setParam("schemeid", sdsoccersheme.getSchemeid()));
											
											gdmember.setGamescore(gdmember2.getGamescore());
													String ticket = gdmember.getTicket();
													if(ticket!=null){
														try{
															ObjectMapper mapper=new ObjectMapper();
															byte[] json = mapper.writeValueAsBytes(gdmember);
															cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
														}catch(Exception e){
															e.printStackTrace();
														}
														
													}
													 //金币记录
												     BetScorerecord betScorerecord=new BetScorerecord();
												     String content="";
												     content="用户"+gdScheme.getMemberid2()+"撤销在足球的方案号为"+gdScheme.getSchemeid()+"的投注方案，投注额为"+gdScheme.getBettingmoney();
												     betScorerecord.setMemberid2(gdmember.getId2());
												     betScorerecord.setTime(new Date());
												     betScorerecord.setContent(content);
												     betScorerecord.setOriginalscore(gdmember.getScore());
												     betScorerecord.setChangescore(gdScheme.getBettingmoney().doubleValue());
												     betScorerecord.setBalance(gdmember.getScore());
												     betScorerecord.setState(1);
												     betScorerecord.setType(18);
												     betScorerecord.setOgamescore(new BigDecimal(gdmember.getGamescore() - gdbettingMoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
												     betScorerecord.setObankscore(new BigDecimal(gdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
												     betScorerecord.setOfreezescore(new BigDecimal(gdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
												     betScorerecord.setGamescore(new BigDecimal(gdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
												     betScorerecord.setBankscore(new BigDecimal(gdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
												     betScorerecord.setFreezescore(new BigDecimal(gdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
												     betScorerecord.setAgentid(gdmember.getAgentid());
												     betScorerecord.setAgentparentid(gdmember.getAgentparentid());
												     betScorerecord.setAgentparentids(gdmember.getAgentparentids());
												     betScorerecord.setOrderid(gdScheme.getSchemeid());
												     betScorerecordService.saveBetScorerecord(betScorerecord);
												     //操作日志
												   //操作日志
													 String details = "";
												     details = "撤销ID："+gdmember.getId2()+"的用户在"+"足球-"+gdScheme.getBettingmoney()+"分的投注方案";
												     String ip = IPUtils.getClientAddress(request);
												     String tool = AgentToolUtil.getAgentTool(request);
												     betOptLogService.saveoptLog(tool,ip,details,pAgent.getAgentid(),pAgent.getParentid(),pAgent.getParentids());
										}else{
											returnObject.setStatus(ReturnDatas.ERROR);
											returnObject.setMessage("无此用户");
										}
									}
								}
							}
							//更新缓存
							member.setGamescore(member2.getGamescore());
									String ticket = member.getTicket();
									if(ticket!=null){
										try{
											ObjectMapper mapper=new ObjectMapper();
											byte[] json = mapper.writeValueAsBytes(member);
											cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
										}catch(Exception e){
											e.printStackTrace();
										}
										
									}
									 //金币记录
								     BetScorerecord betScorerecord=new BetScorerecord();
								     String content="";
								     content="用户"+scheme.getMemberid2()+"撤销在足球的方案号为"+scheme.getSchemeid()+"的投注方案，投注额为"+scheme.getBettingmoney();
								     betScorerecord.setMemberid2(member.getId2());
								     betScorerecord.setTime(new Date());
								     betScorerecord.setContent(content);
								     betScorerecord.setOriginalscore(member.getScore());
								     betScorerecord.setChangescore(scheme.getBettingmoney().doubleValue());
								     betScorerecord.setBalance(member.getScore());
								     betScorerecord.setState(1);
								     betScorerecord.setType(18);					 
								     betScorerecord.setOgamescore(new BigDecimal(member.getGamescore() - bettingMoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
								     betScorerecord.setObankscore(new BigDecimal(member.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
								     betScorerecord.setOfreezescore(new BigDecimal(member.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
								     betScorerecord.setGamescore(new BigDecimal(member.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
								     betScorerecord.setBankscore(new BigDecimal(member.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
								     betScorerecord.setFreezescore(new BigDecimal(member.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
								     betScorerecord.setAgentid(member.getAgentid());
								     betScorerecord.setAgentparentid(member.getAgentparentid());
								     betScorerecord.setAgentparentids(member.getAgentparentids());
								     betScorerecord.setOrderid(scheme.getSchemeid());
								     betScorerecordService.saveBetScorerecord(betScorerecord);
								     //操作日志
								   //操作日志
									 String details = "";
								     details = "撤销ID："+member.getId2()+"的用户在"+"足球-"+scheme.getBettingmoney()+"分的投注方案";
								     String ip = IPUtils.getClientAddress(request);
								     String tool = AgentToolUtil.getAgentTool(request);
								     betOptLogService.saveoptLog(tool,ip,details,pAgent.getAgentid(),pAgent.getParentid(),pAgent.getParentids());
						}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("无此用户");
						}
					}else if(scheme.getSituation()!=null&&scheme.getSituation()==1){
						//已开奖撤销
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("已开奖无法撤销");
						return returnObject;
						
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此方案");
				}
			}else if("1".equals(request.getParameter("clear"))){
				String id = request.getParameter("id");
				soccerSchemeService.update(new Finder("update soccer_scheme set statement=:statement where id=:id ").setParam("statement", "").setParam("id", id));
			}else if("1".equals(request.getParameter("settle"))){
				String schemeid = request.getParameter("schemeid");
				SoccerScheme scheme = null;
				if(schemeid!=null){
					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), SoccerScheme.class);
				}else{
					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", soccerScheme.getId()), SoccerScheme.class);
				}
				
				if(scheme!=null){
					Integer memberid2 = scheme.getMemberid2();
					BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
					if(member==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("无此用户");
						return returnObject;
					}
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						String schemeid1=scheme.getSchemeid();
						List<SoccerLeagueOrder> orderList234 = leagueOrderService.queryForList(new Finder("select * from soccer_league_order where result=0 and state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), SoccerLeagueOrder.class);
						if(orderList234!=null){
							for (SoccerLeagueOrder soccerLeagueOrder : orderList234) {
								List<SoccerLeagueOrderContent>  contentList = leagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where orderid=:orderid ").setParam("orderid", soccerLeagueOrder.getOrderid()), SoccerLeagueOrderContent.class);
								int count = 0 ;
								Boolean flag=false;
								if(contentList!=null){
									for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
										if(soccerLeagueOrderContent.getResult()==1){
											count++;
										}
										if(soccerLeagueOrderContent.getResult()==3){
											flag=true;
										}
										
									}
									if(contentList.size() == count){
										
									}else{
										if(flag==true){
											
										}else{
											returnObject.setStatus(ReturnDatas.ERROR);
											returnObject.setMessage("已结赛比赛数量不足");
											return returnObject;
										}
									}
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage("未结算，有订单无内容");
									return returnObject;
								}
							}
							
							Double schemereward=0.;
							for (SoccerLeagueOrder soccerLeagueOrder : orderList234) {

								List<SoccerLeagueOrderContent>  contentList = leagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where orderid=:orderid ").setParam("orderid", soccerLeagueOrder.getOrderid()), SoccerLeagueOrderContent.class);
								int count = 0 ;
								if((contentList!=null)&&(!contentList.isEmpty())){
									for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
										if(soccerLeagueOrderContent.getResult()==1){
											count++;
										}
									}
										
									if(contentList.size() == count){
										//中奖了 返奖
										Double odds = 1.;
										for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
											odds*=soccerLeagueOrderContent.getOdds();
										}
										Double award = odds*soccerLeagueOrder.getBettingmoney();
										Double posttaxprice=award;
										schemereward+=posttaxprice;
										
										Date dddd=new Date();
										leagueOrderService.update(new Finder("update soccer_league_order set result=1,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid and result=0 ").setParam("posttaxprize", award).setParam("orderid", soccerLeagueOrder.getOrderid()).setParam("settletime", dddd).setParam("award", posttaxprice));
										
										
									}else{
										Date ddddd=new Date();
										leagueOrderService.update(new Finder("update soccer_league_order set result=3,settletime=:settletime,bettingwin=0,posttaxprize=0 where orderid=:orderid and result=0 ").setParam("orderid", soccerLeagueOrder.getOrderid()).setParam("settletime", ddddd));
											
									}
								}
							}
							SoccerScheme soccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), SoccerScheme.class);
							BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id2", soccersheme.getMemberid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BetMember.class);
							
							if(soccersheme.getBuytype()==1){
								//跟单
								SoccerScheme sdsoccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid2", soccersheme.getSchemeid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), SoccerScheme.class);
								if(sdsoccersheme.getPubstate()!=null&&sdsoccersheme.getPubstate()==2){
									BigDecimal guarantee = sdsoccersheme.getGuarantee();
									BigDecimal guaranteeMoney = null;
									if(guarantee!=null){
										guaranteeMoney = guarantee.multiply(soccersheme.getBettingmoney());
									}
									if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
										BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join soccer_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) limit 1").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2",soccersheme.getSchemeid2() ), BetMember.class);
										
										BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
										if(!"A101".equals(betAgent.getParentid())){
											betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
										}
										
										BigDecimal brokeragemember = sdsoccersheme.getBrokeragemember(); // 大神佣金比例
										BigDecimal brokerageagent = sdsoccersheme.getBrokerageagent(); // 一级代理佣金比例
										
										if (brokeragemember == null || brokerageagent == null
												|| brokeragemember.compareTo(BigDecimal.ZERO) <= 0
												|| brokerageagent.compareTo(new BigDecimal(0)) <= 0) {
											BetAgentBrokerage agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
											
											if(agentBrokerage==null){
												BetAgentBrokerage brokerage = new BetAgentBrokerage();
												brokerage.setAgentid(betAgent.getAgentid());
												brokerage.setAgentparentid(betAgent.getParentid());
												brokerage.setAgentparentids(betAgent.getParentids());
												brokerage.setBrokerageagent(new BigDecimal(0.02));
												brokerage.setBrokeragemember(new BigDecimal(0.08));
												betAgentBrokerageService.save(brokerage);
												agentBrokerage = brokerage;
											}
											brokeragemember = agentBrokerage.getBrokeragemember();
											brokerageagent = agentBrokerage.getBrokerageagent();
										}
										BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
										//跟单方案大神佣金
										BigDecimal brokeragemoney = bettingwin.multiply(brokeragemember);
										soccersheme.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
										//跟单方案代理佣金
										BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
										soccersheme.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
										soccersheme.setBrokerageagentid(betAgent.getAgentid());
										
										//返给代理佣金
										Integer agentupdatenum=null;
										for(int i=0;i<10;i++){
											if(agentupdatenum==null){
												agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
											}else if(agentupdatenum==0){
												agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
												
											}else if(agentupdatenum==1){
												break;
											}
											
										}
										
										//跟单方案减总佣金
										BigDecimal sumbrokeragemoney = brokeragemoney.add(brokerageagentmoney);
										schemereward = bettingwin.subtract(sumbrokeragemoney).doubleValue();
										soccersheme.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
										
										if(sdsoccersheme.getBrokeragemoney()!=null){
											sdsoccersheme.setBrokeragemoney(sdsoccersheme.getBrokeragemoney().add(brokeragemoney));
										}else{
											sdsoccersheme.setBrokeragemoney(brokeragemoney);
										}
										sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
										sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
										sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
										sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
										Integer updatenum=null;
										for(int i=0;i<10;i++){
											if(updatenum==null){
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("gamescore", sdmember.getGamescore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
											}else if(updatenum==0){
												sdmember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", sdmember.getId()), BetMember.class);
												sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
												sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
												sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
												sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("gamescore", sdmember.getGamescore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
												
											}else if(updatenum==1){
												break;
											}
											
										}
										if(updatenum==1){
											
											//更新缓存
											String ticket = sdmember.getTicket();
											if(ticket!=null){
												try{
													ObjectMapper mapper=new ObjectMapper();
													byte[] json = mapper.writeValueAsBytes(sdmember);
													cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
												}catch(Exception e){
													e.printStackTrace();
												}
												
											}
											Date dd=new Date();
											BetScorerecord scorerecord = new BetScorerecord();
											scorerecord.setId(null);
											scorerecord.setMemberid2(sdsoccersheme.getMemberid2());
											scorerecord.setTime(dd);
											double f1 = new BigDecimal(schemereward).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
											double f2 = soccersheme.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
											scorerecord.setContent("用户"+betMember.getId2()+"投注的足彩竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
											scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
											scorerecord.setChangescore(brokeragemoney.doubleValue());
											scorerecord.setBalance(sdmember.getScore());
											scorerecord.setState(1);
											scorerecord.setType(15);
											scorerecord.setOgamescore(new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
											scorerecord.setObankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
											scorerecord.setOfreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
											scorerecord.setGamescore(new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
											scorerecord.setBankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
											scorerecord.setFreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
											scorerecord.setAgentid(sdmember.getAgentid());
											scorerecord.setAgentparentid(sdmember.getAgentparentid());
											scorerecord.setAgentparentids(sdmember.getAgentparentids());
											scorerecord.setOrderid(schemeid1);
											double gsq = new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
										    double gsh = new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
										    double bs = new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
										    double dsq = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
										    double dsh = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
										    scorerecord.setRemark("gq:"+gsq+"gh"+gsh+",b:"+bs+",dq:"+dsq+",dh:"+dsh);
											betScorerecordService.save(scorerecord);
											
											//投注方案
											soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", soccersheme.getBrokeragemoney()).setParam("brokerageagentmoney", soccersheme.getBrokerageagentmoney()).setParam("brokeragemembermoney", soccersheme.getBrokeragemembermoney()).setParam("brokerageagentid", soccersheme.getBrokerageagentid()));
											soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdsoccersheme.getSchemeid()).setParam("brokeragemoney", sdsoccersheme.getBrokeragemoney()));
											
										}
									}
								}
								
								
							}
							String firstagentid=null;
							Double bbb=null;
							try {
								String parentids = betMember.getAgentparentids();
								String parentid=betMember.getAgentparentid();
								if("A101".equals(parentid)){
									bbb = soccerSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:zuqiu limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", betMember.getAgentid()),Double.class);
								}else{
									String[] split = parentids.split(",");
									int i=0;
									for (String string : split) {
										if(i==0){
											if("A101".equals(string)){
												i=1;
											}
										}else{
											firstagentid=string;
											break;
										}
									}
									bbb = soccerSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:zuqiu limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", firstagentid),Double.class);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							Double plusawards=0.;
							if(bbb!=null){
								plusawards=bbb*schemereward;
							}
							
							betMember.setBankscore(betMember.getBankscore()+schemereward);
							betMember.setGamescore(betMember.getGamescore()+plusawards);
							betMember.setScore(betMember.getScore() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
							betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
							betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
							Integer updatenum=null;
							for(int i=0;i<10;i++){
								if(updatenum==null){
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
								}else if(updatenum==0){
									betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
									betMember.setBankscore(betMember.getBankscore()+schemereward);
									betMember.setGamescore(betMember.getGamescore()+plusawards);
									betMember.setScore(betMember.getScore() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
									betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
									betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
//									betMember.setFreezingscore(betMember.getFreezingscore()-soccersheme.getBettingmoney().doubleValue());
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
									
								}else if(updatenum==1){
									break;
								}
								
							}
								
							if(updatenum==1){
								
								//更新缓存
								String ticket = betMember.getTicket();
								if(ticket!=null){
									try{
										ObjectMapper mapper=new ObjectMapper();
										byte[] json = mapper.writeValueAsBytes(betMember);
										cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
									}catch(Exception e){
										//String errorMessage = e.getLocalizedMessage();
										e.printStackTrace();
									}
									
								}
								
								Date dd=new Date();
								try {
									BetScorerecord scorerecord = new BetScorerecord();
									scorerecord.setId(null);
									scorerecord.setMemberid2(soccersheme.getMemberid2());
									scorerecord.setTime(dd);
									double f1 = new BigDecimal(schemereward+plusawards).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									double f2 = soccersheme.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									scorerecord.setContent("足彩竞猜方案号为"+schemeid1+"的投注方案押"+f2+"返奖"+f1+"元");
									scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + soccersheme.getBettingmoney().doubleValue());
									scorerecord.setBalance(betMember.getScore());
									scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore()-plusawards));
									scorerecord.setObankscore(new BigDecimal(betMember.getBankscore()-schemereward));
									scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()));
									scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()));
									scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()));
									scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()));
									scorerecord.setOrderid(schemeid1);
									scorerecord.setChangescore(schemereward+plusawards);
									scorerecord.setState(1);
									scorerecord.setType(14);
									scorerecord.setAgentid(betMember.getAgentid());
									scorerecord.setAgentparentid(betMember.getAgentparentid());
									scorerecord.setAgentparentids(betMember.getAgentparentids());
									betScorerecordService.save(scorerecord);
								} catch (Exception e) {
									e.printStackTrace();
								}
								//投注方案
								soccerSchemeService.update(new Finder("update soccer_scheme set plusawards=:plusawards,bettingwin=:bettingwin,situation=1,settlementtime=:settlementtime,pretaxamount=:pretaxamount WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("pretaxamount", schemereward).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("bettingwin", schemereward+plusawards));
								
								//汇总投注
								soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and state=0 and type=1 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
//										
								//代理退佣
								try {
									String agentid = betMember.getAgentid();
									double bettingmoney = (soccersheme.getBettingmoney()).doubleValue();
									BetAgent betaggg = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
									if(bettingmoney>=0.){
										bettingmoneyagentrebate(betaggg,bettingmoney,betMember,schemeid1,dd,0.);
									}
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
						}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("未结算，此方案无订单");
							return returnObject;
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("已结算方案无法结算");
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此方案");
				}
			}else if("1".equals(request.getParameter("k"))){
				//无效比赛处理
				String mid = request.getParameter("mid");
				SoccerLeagueArrange arrange = soccerLeagueArrangeService.queryForObject(new Finder("select * from soccer_league_arrange where  mid = :mid").setParam("mid", mid), SoccerLeagueArrange.class);
				if(arrange!=null){
					if(arrange.getStarttime().after(new Date())){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次未开赛");
					}else{
						SoccerLeagueResult result = soccerLeagueResultService.queryForObject(new Finder("select * from soccer_league_result where  mid = :mid").setParam("mid", mid), SoccerLeagueResult.class);
						if(result!=null){
//							returnObject.setStatus(ReturnDatas.ERROR);
//							returnObject.setMessage("该场次已有结果");
							
							//单个平台无效比赛
							//soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set odds = 1,settletime=now(),result=1,resultname=:resultname where mid = :mid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("mid", mid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("resultname", "无效比赛"));
							
							//同一环境所有平台都设置为无效比赛
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set odds = 1,settletime=now(),result=1,resultname=:resultname where mid = :mid").setParam("mid", mid).setParam("resultname", "无效比赛"));
							soccerLeagueResultService.update(new Finder("update soccer_league_result set halfscore=:halfscore,allscore=:allscore,state=:state,issettle=:issettle where mid=:mid").setParam("halfscore", "--").setParam("allscore", "--").setParam("state", "2").setParam("issettle", "1").setParam("mid", mid));
						}else{
							//单个平台无效比赛
							//soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set odds = 1,settletime=now(),result=1,resultname=:resultname where mid = :mid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("mid", mid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("resultname", "无效比赛"));
							
							//同一环境所有平台都设置为无效比赛
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set odds = 1,settletime=now(),result=1,resultname=:resultname where mid = :mid").setParam("mid", mid).setParam("resultname", "无效比赛"));
							SoccerLeagueResult soccerResult = new SoccerLeagueResult();
							soccerResult.setNum(WeekOfDate.getWeekOfDate(arrange.getEndtime())+arrange.getNum());
							soccerResult.setMid(arrange.getMid());
							soccerResult.setZid(arrange.getZid());
							soccerResult.setArrangeid2(arrange.getMatchid2());
							soccerResult.setMatchtime(arrange.getStarttime());
							soccerResult.setLeft_team(arrange.getLeftteamname());
							soccerResult.setLeft_team_id2(arrange.getLeftteamid2());
							soccerResult.setRight_team(arrange.getRightteamname());
							soccerResult.setRight_team_id2(arrange.getRightteamid2());
							soccerResult.setHalfscore("--");
							soccerResult.setAllscore("--");
							soccerResult.setState(2);//状态 1:已确认3:未确认；2、无效比赛；
							soccerResult.setIssettle(1);//结算1:已结算 3:未结算
							soccerResult.setCreattime(new Date());
							soccerLeagueResultService.save(soccerResult);
						}
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次不存在");
				}
			}else if("1".equals(request.getParameter("recalculate"))){
				//重新结算
				String schemeid = request.getParameter("schemeid");
				SoccerScheme scheme = null;
				if(schemeid!=null){
					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), SoccerScheme.class);
				}else{
					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", soccerScheme.getId()), SoccerScheme.class);
				}
				
				if(scheme!=null&&scheme.getSituation()==1){
					BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", scheme.getMemberid2()), BetMember.class);
					betMember.setBankscore(betMember.getBankscore()-scheme.getBettingwin().doubleValue()+scheme.getPlusawards().doubleValue());
					betMember.setGamescore(betMember.getGamescore()-scheme.getPlusawards().doubleValue());
					betMember.setScore(betMember.getScore() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
					
					if(scheme.getBuytype()==2){
						if(scheme.getBrokeragemoney()!=null){
							betMember.setBankscore(betMember.getBankscore()-scheme.getBrokeragemoney().doubleValue());
							betMember.setScore(betMember.getScore()-scheme.getBrokeragemoney().doubleValue());
						}
						soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = 0 where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()));
					}
					
					betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score where id2=:id2 ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id2", betMember.getId2()).setParam("score", betMember.getScore()));
					
					Date dd=new Date();
					BetScorerecord scorerecord = new BetScorerecord();
					scorerecord.setId(null);
					scorerecord.setMemberid2(scheme.getMemberid2());
					scorerecord.setTime(dd);
					//scorerecord.setContent("用户"+betMember.getId2()+"投注的足彩竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
					scorerecord.setContent("减去原有奖金");
					
					scorerecord.setOriginalscore(betMember.getScore() + scheme.getBettingwin().doubleValue() - scheme.getBettingmoney().doubleValue());
					scorerecord.setChangescore(-scheme.getBettingwin().doubleValue());
					scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore() +scheme.getPlusawards().doubleValue()));
					scorerecord.setObankscore(new BigDecimal(betMember.getBankscore() + scheme.getBettingwin().doubleValue() - scheme.getPlusawards().doubleValue()));
					scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					
					if(scheme.getBuytype()==2){
						if(scheme.getBrokeragemoney()!=null){
							scorerecord.setChangescore(-scheme.getBettingwin().doubleValue()-scheme.getBrokeragemoney().doubleValue());
							scorerecord.setOriginalscore(scorerecord.getOriginalscore()+scheme.getBrokeragemoney().doubleValue());
							scorerecord.setObankscore(new BigDecimal(scorerecord.getObankscore().doubleValue()+scheme.getBrokeragemoney().doubleValue()));
						}
						
					}
					
					scorerecord.setBalance(betMember.getScore());
					scorerecord.setState(1);
					scorerecord.setType(24);			
					scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setAgentid(betMember.getAgentid());
					scorerecord.setAgentparentid(betMember.getAgentparentid());
					scorerecord.setAgentparentids(betMember.getAgentparentids());
					scorerecord.setOrderid(scheme.getSchemeid());
					betScorerecordService.save(scorerecord);
					
					if(scheme.getBuytype()==1){
						BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
						if("A101".equals(betAgent.getParentid())){
							
						}else{
							betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
						}
						
						if(scheme.getBrokerageagentmoney()!=null){
							betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)-:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)-:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", scheme.getBrokerageagentmoney()).setParam("agentid", betAgent.getAgentid()));
						}
						
					}
					
					List<String> mids = soccerSchemeMatchService.queryForList(new Finder("select mid from soccer_scheme_match where schemeid = :schemeid group by schemeid").setParam("schemeid", scheme.getSchemeid()), String.class);
					List<SoccerLeagueResult> datas=soccerLeagueResultService.queryForList(new Finder("select * from soccer_league_result  where  state = 1 and mid in (:mid)").setParam("mid", mids), SoccerLeagueResult.class);
					
					if(datas!=null&&!datas.isEmpty()){
						for(SoccerLeagueResult soccerResult : datas){
							String allscore[] = soccerResult.getAllscore().split(":");
							int allball = Integer.valueOf(allscore[0])+Integer.valueOf(allscore[1]);
							if(allball<7){
								soccerResult.setZjq("ball"+allball);
							}else{
								soccerResult.setZjq("ball7");
							}
							
							if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
								soccerResult.setSpf("win");
							}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])){
								soccerResult.setSpf("lose");
							}else{
								soccerResult.setSpf("flat");
							}
							
							SoccerLeagueOdds soccerOdds = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", soccerResult.getMid()), SoccerLeagueOdds.class);
							Double letpoints = 0.0d;
							if(soccerOdds!=null){
								letpoints = Double.valueOf(soccerOdds.getLetpoints());
							}
							if(Integer.valueOf(allscore[0])+letpoints>Integer.valueOf(allscore[1])){
								soccerResult.setRqspf("rqwin");
							}else if(Integer.valueOf(allscore[0])+letpoints<Integer.valueOf(allscore[1])){
								soccerResult.setRqspf("rqlose");
							}else{
								soccerResult.setRqspf("rqflat");
							}
							
							String[] half = soccerResult.getHalfscore().split(":");
							String bqc = "sp";
							if(Integer.valueOf(half[0])>Integer.valueOf(half[1])){
								bqc+="3";
							}else if(Integer.valueOf(half[0])<Integer.valueOf(half[1])){
								bqc+="0";
							}else{
								bqc+="1";
							}
							if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
								bqc+="3";
							}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])){
								bqc+="0";
							}else{
								bqc+="1";
							}
							soccerResult.setBqc(bqc);
							
							SoccerLeaguePlaymethodOddsname soccerOddsname = soccerLeaguePlaymethodOddsnameService.queryForObject(new Finder("select * from soccer_league_playmethod_oddsname where oddsrealname = :oddsrealname and playmethodid = 3").setParam("oddsrealname", soccerResult.getAllscore()), SoccerLeaguePlaymethodOddsname.class);
							if(soccerOddsname!=null){
								soccerResult.setBf(soccerResult.getSpf()+allscore[0]+allscore[1]);
							}else{
								if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
									soccerResult.setBf("win3A");
								}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])){
									soccerResult.setBf("lose0A");
								}else{
									soccerResult.setBf("flat1A");
								}
							}
							
							SoccerLeague2choose1odds soccer2choose1odds =  soccerLeague2choose1oddsService.queryForObject(new Finder("select * from soccer_league_2choose1odds where mid = :mid").setParam("mid", soccerResult.getMid()), SoccerLeague2choose1odds.class);
							if(soccer2choose1odds!=null){
								if("主胜".equals(soccer2choose1odds.getLeft_name())&&(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1]))){
										soccerResult.setChoose("left_odds");
								} 
								if("主不败".equals(soccer2choose1odds.getLeft_name())&&(Integer.valueOf(allscore[0])>=Integer.valueOf(allscore[1]))){
										soccerResult.setChoose("left_odds");
								} 
								if("客胜".equals(soccer2choose1odds.getRight_name())&&(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1]))){
										soccerResult.setChoose("right_odds");
								}
								if("客不败".equals(soccer2choose1odds.getRight_name())&&(Integer.valueOf(allscore[0])<=Integer.valueOf(allscore[1]))){
										soccerResult.setChoose("right_odds");			
								}
							}
							
							String spf = soccerResult.getSpf();
							String spfresult=null;
							if("win".equals(spf)){
								spfresult="主胜";
							}else if("flat".equals(spf)){
								spfresult="平";
							}else if("lose".equals(spf)){
								spfresult="客胜";
							}
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=3,settletime=:date,resultname=:spfresult where mid=:mid and oddsname in(:w,:f,:l) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "lose").setParam("f", "flat").setParam("w", "win").setParam("spfresult", spfresult).setParam("resultname", soccerResult.getSpf()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=1,settletime=:date,resultname=:spfresult where mid=:mid and oddsname in(:w,:f,:l) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "lose").setParam("f", "flat").setParam("w", "win").setParam("spfresult", spfresult).setParam("resultname", soccerResult.getSpf()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							
							String rqspf = soccerResult.getRqspf();
							String rqspfresult=null;
							if("rqwin".equals(rqspf)){
								rqspfresult="让球主胜";
							}else if("rqflat".equals(rqspf)){
								rqspfresult="让球平";
							}else if("rqlose".equals(rqspf)){
								rqspfresult="让球客胜";
							}
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=3,settletime=:date,resultname=:rqspfresult where mid=:mid and oddsname in(:w,:f,:l) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "rqlose").setParam("f", "rqflat").setParam("w", "rqwin").setParam("rqspfresult",rqspfresult).setParam("resultname", soccerResult.getRqspf()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=1,settletime=:date,resultname=:rqspfresult where mid=:mid and oddsname in(:w,:f,:l) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "rqlose").setParam("f", "rqflat").setParam("w", "rqwin").setParam("rqspfresult", rqspfresult).setParam("resultname", soccerResult.getRqspf()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							
							String zjq = soccerResult.getZjq();
							String zjqresult=null;
							if("ball0".equals(zjq)){
								zjqresult="0球";
							}else if("ball1".equals(zjq)){
								zjqresult="1球";
							}else if("ball2".equals(zjq)){
								zjqresult="2球";
							}else if("ball3".equals(zjq)){
								zjqresult="3球";
							}else if("ball4".equals(zjq)){
								zjqresult="4球";
							}else if("ball5".equals(zjq)){
								zjqresult="5球";
							}else if("ball6".equals(zjq)){
								zjqresult="6球";
							}else if("ball7".equals(zjq)){
								zjqresult="7+球";
							}
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=3,settletime=:date,resultname=:zjqresult where mid=:mid and left(oddsname, 4)=:b and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("b", "ball").setParam("zjqresult", zjqresult).setParam("resultname", soccerResult.getZjq()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=1,settletime=:date , resultname=:zjqresult where mid=:mid and left(oddsname, 4)=:b and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("b", "ball").setParam("zjqresult", zjqresult).setParam("resultname", soccerResult.getZjq()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							
							String bqc2 = soccerResult.getBqc();
							String bqcresult=null;
							if("sp00".equals(bqc2)){
								bqcresult="负负";
							}else if("sp01".equals(bqc2)){
								bqcresult="负平";
							}else if("sp03".equals(bqc2)){
								bqcresult="负胜";
							}else if("sp10".equals(bqc2)){
								bqcresult="平负";
							}else if("sp11".equals(bqc2)){
								bqcresult="平平";
							}else if("sp13".equals(bqc2)){
								bqcresult="平胜";
							}else if("sp30".equals(bqc2)){
								bqcresult="胜负";
							}else if("sp31".equals(bqc2)){
								bqcresult="胜平";
							}else if("sp33".equals(bqc2)){
								bqcresult="胜胜";
							}
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=3, settletime=:date , resultname=:bqcresult where mid=:mid and left(oddsname, 2)=:s and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("s", "sp").setParam("bqcresult", bqcresult).setParam("resultname", soccerResult.getBqc()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=1, settletime=:date, resultname=:bqcresult where mid=:mid and left(oddsname, 2)=:s and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("s", "sp").setParam("bqcresult", bqcresult).setParam("resultname", soccerResult.getBqc()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							
							String bf = soccerResult.getBf();
							String bfresult=null;
							if("win3A".equals(bf)){
								bfresult="胜其它";
							}else if ("win10".equals(bf)){
								bfresult="1:0";
							}else if ("win20".equals(bf)){
								bfresult="2:0";
							}else if ("win21".equals(bf)){
								bfresult="2:1";
							}else if ("win30".equals(bf)){
								bfresult="3:0";
							}else if ("win31".equals(bf)){
								bfresult="3:1";
							}else if ("win32".equals(bf)){
								bfresult="3:2";
							}else if ("win40".equals(bf)){
								bfresult="4:0";
							}else if ("win41".equals(bf)){
								bfresult="4:1";
							}else if ("win42".equals(bf)){
								bfresult="4:2";
							}else if ("win50".equals(bf)){
								bfresult="5:0";
							}else if ("win51".equals(bf)){
								bfresult="5:1";
							}else if ("win52".equals(bf)){
								bfresult="5:2";
							}else if ("flat1A".equals(bf)){
								bfresult="平其它";
							}else if ("flat00".equals(bf)){
								bfresult="0:0";
							}else if ("flat11".equals(bf)){
								bfresult="1:1";
							}else if ("flat22".equals(bf)){
								bfresult="2:2";
							}else if ("flat33".equals(bf)){
								bfresult="3:3";
							}else if("lose0A".equals(bf)){
								bfresult="负其它";
							}else if("lose01".equals(bf)){
								bfresult="0:1";
							}else if("lose02".equals(bf)){
								bfresult="0:2";
							}else if("lose12".equals(bf)){
								bfresult="1:2";
							}else if("lose03".equals(bf)){
								bfresult="0:3";
							}else if("lose13".equals(bf)){
								bfresult="1:3";
							}else if("lose23".equals(bf)){
								bfresult="2:3";
							}else if("lose04".equals(bf)){
								bfresult="0:4";
							}else if("lose14".equals(bf)){
								bfresult="1:4";
							}else if("lose24".equals(bf)){
								bfresult="2:4";
							}else if("lose05".equals(bf)){
								bfresult="0:5";
							}else if("lose15".equals(bf)){
								bfresult="1:5";
							}else if("lose25".equals(bf)){
								bfresult="2:5";
							}
							
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=3 , settletime=:date , resultname=:bfresult where mid=:mid and LENGTH(oddsname)!=3 and LENGTH(oddsname)!=4 and left(oddsname, 3) in(:w,:f,:l) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "los").setParam("f", "fla").setParam("w", "win").setParam("bfresult", bfresult).setParam("resultname", soccerResult.getBf()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=1 , settletime=:date , resultname=:bfresult where mid=:mid and LENGTH(oddsname)!=3 and LENGTH(oddsname)!=4 and left(oddsname, 3) in(:w,:f,:l) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "los").setParam("f", "fla").setParam("w", "win").setParam("bfresult", bfresult).setParam("resultname", soccerResult.getBf()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							
							String choose = soccerResult.getChoose();
							String mid = soccerResult.getMid();
							String chooseresult=null;
							if("left_odds".equals(choose)){
								chooseresult=soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid limit 1 ").setParam("mid",mid),String.class);
							}else if("right_odds".equals(choose)){
								chooseresult=soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid limit 1 ").setParam("mid",mid),String.class);
							}
							
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=3 , settletime=:date , resultname=:chooseresult where mid=:mid and oddsname in(:l,:r) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("l", "left_odds").setParam("r", "right_odds").setParam("chooseresult", chooseresult).setParam("resultname", soccerResult.getChoose()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueOrderContentService.update(new Finder("update soccer_league_order_content set result=1 , settletime=:date , resultname=:chooseresult where mid=:mid and oddsname in(:l,:r) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("chooseresult", chooseresult).setParam("l", "left_odds").setParam("r", "right_odds").setParam("resultname", soccerResult.getChoose()).setParam("date", new Date()).setParam("mid", soccerResult.getMid()));
							soccerLeagueResultService.update(new Finder("update soccer_league_result set issettle=1 where mid = :mid").setParam("mid", soccerResult.getMid()));
						}
					}
					
					List<String> schemelist = soccerSchemeService.queryForList(new Finder("select schemeid from soccer_scheme where schemeid = :schemeid ").setParam("schemeid", scheme.getSchemeid()), String.class);
					if(schemelist!=null){
						for (String schemeid1 : schemelist) {
							Integer untreatedcontent = leagueOrderService.queryForObject(new Finder("select count(*) from soccer_league_order a right join soccer_league_order_content b on b.orderid=a.orderid where a.schemeid=:schemeid and b.result=0 ").setParam("schemeid", schemeid1), Integer.class);
							if(untreatedcontent==0){
								try{
									
									List<SoccerLeagueOrder> orderList = leagueOrderService.queryForList(new Finder("select * from soccer_league_order where state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), SoccerLeagueOrder.class);
									
									if((orderList !=null)&&(!orderList.isEmpty())){
										Double schemereward=0.;
										for (SoccerLeagueOrder soccerLeagueOrder : orderList) {
											List<SoccerLeagueOrderContent>  contentList = leagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where orderid=:orderid ").setParam("orderid", soccerLeagueOrder.getOrderid()), SoccerLeagueOrderContent.class);
											int count = 0 ;
											if((contentList!=null)&&(!contentList.isEmpty())){
												for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
													if(soccerLeagueOrderContent.getResult()==1){
														count++;
													}
												}
													
												if(contentList.size() == count){
													//中奖了 返奖
													Double odds = 1.;
													for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
														odds*=soccerLeagueOrderContent.getOdds();
													}
													Double award = odds*soccerLeagueOrder.getBettingmoney();
													Double posttaxprice=award;
													schemereward+=posttaxprice;
													Date dddd=new Date();
													leagueOrderService.update(new Finder("update soccer_league_order set result=1,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid  ").setParam("posttaxprize", award).setParam("orderid", soccerLeagueOrder.getOrderid()).setParam("settletime", dddd).setParam("award", posttaxprice));
												}else{
													Date ddddd=new Date();
													leagueOrderService.update(new Finder("update soccer_league_order set result=3,settletime=:settletime,bettingwin=0,posttaxprize=0 where orderid=:orderid  ").setParam("orderid", soccerLeagueOrder.getOrderid()).setParam("settletime", ddddd));
												}
											}
										}
										
										SoccerScheme soccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), SoccerScheme.class);
										betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id2", soccersheme.getMemberid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BetMember.class);
										
										
										try {
											if(soccersheme.getBuytype()==1){
												//跟单
												SoccerScheme sdsoccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", soccersheme.getSchemeid2()), SoccerScheme.class);
												if(sdsoccersheme.getPubstate()!=null&&sdsoccersheme.getPubstate()==2){
													BigDecimal guarantee = sdsoccersheme.getGuarantee();
													BigDecimal guaranteeMoney = null;
													if(guarantee!=null){
														guaranteeMoney = guarantee.multiply(soccersheme.getBettingmoney());
													}
													if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
														BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join soccer_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) limit 1").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2",soccersheme.getSchemeid2() ), BetMember.class);
														
														BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
														if("A101".equals(betAgent.getParentid())){
															
														}else{
															betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
														}
														
														BigDecimal brokeragemember = sdsoccersheme.getBrokeragemember(); // 大神佣金比例
														BigDecimal brokerageagent = sdsoccersheme.getBrokerageagent(); // 一级代理佣金比例
														
														if (brokeragemember == null || brokerageagent == null
																|| brokeragemember.compareTo(BigDecimal.ZERO) <= 0
																|| brokerageagent.compareTo(new BigDecimal(0)) <= 0) {
															BetAgentBrokerage agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
															
															if(agentBrokerage==null){
																BetAgentBrokerage brokerage = new BetAgentBrokerage();
																brokerage.setAgentid(betAgent.getAgentid());
																brokerage.setAgentparentid(betAgent.getParentid());
																brokerage.setAgentparentids(betAgent.getParentids());
																brokerage.setBrokerageagent(new BigDecimal(0.02));
																brokerage.setBrokeragemember(new BigDecimal(0.08));
																betAgentBrokerageService.save(brokerage);
																agentBrokerage = brokerage;
															}
															brokeragemember = agentBrokerage.getBrokeragemember();
															brokerageagent = agentBrokerage.getBrokerageagent();
														}
														BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
														//跟单方案大神佣金
														BigDecimal brokeragemoney = bettingwin.multiply(brokeragemember);
														soccersheme.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
														//跟单方案代理佣金
														BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
														soccersheme.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
														soccersheme.setBrokerageagentid(betAgent.getAgentid());
														
														//返给代理佣金
														Integer agentupdatenum=null;
														for(int i=0;i<10;i++){
															if(agentupdatenum==null){
																agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
															}else if(agentupdatenum==0){
																agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
																
															}else if(agentupdatenum==1){
																break;
															}
															
														}
														
														//跟单方案减总佣金
														BigDecimal sumbrokeragemoney = brokeragemoney.add(brokerageagentmoney);
														schemereward = bettingwin.subtract(sumbrokeragemoney).doubleValue();
														soccersheme.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
														
														if(sdsoccersheme.getBrokeragemoney()!=null){
															sdsoccersheme.setBrokeragemoney(sdsoccersheme.getBrokeragemoney().add(brokeragemoney));
														}else{
															sdsoccersheme.setBrokeragemoney(brokeragemoney);
														}
														sdmember.setBankscore(sdmember.getBankscore()+ brokeragemoney.doubleValue());
														sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
														sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
														sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
														Integer updatenum=null;
														for(int i=0;i<10;i++){
															if(updatenum==null){
																updatenum = betMemberService.update(new Finder("update bet_member set bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", sdmember.getBankscore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
															}else if(updatenum==0){
																sdmember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", sdmember.getId()), BetMember.class);
																sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
																sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
																sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
																sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
																updatenum = betMemberService.update(new Finder("update bet_member set bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", sdmember.getBankscore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
																
															}else if(updatenum==1){
																break;
															}
															
														}
														if(updatenum==1){
															
															//更新缓存
															String ticket = sdmember.getTicket();
															if(ticket!=null){
																try{
																	ObjectMapper mapper=new ObjectMapper();
																	byte[] json = mapper.writeValueAsBytes(sdmember);
																	cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
																}catch(Exception e){
																	System.out.println(e);
																}
																
															}
															dd=new Date();
															scorerecord = new BetScorerecord();
															scorerecord.setId(null);
															scorerecord.setMemberid2(sdsoccersheme.getMemberid2());
															scorerecord.setTime(dd);
															scorerecord.setContent("重新结算足彩佣金");
															scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
															scorerecord.setChangescore(brokeragemoney.doubleValue());
															scorerecord.setBalance(sdmember.getScore());
															scorerecord.setState(1);
															scorerecord.setType(15);
															scorerecord.setOgamescore(new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setObankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setOfreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setGamescore(new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setBankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setFreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setAgentid(sdmember.getAgentid());
															scorerecord.setAgentparentid(sdmember.getAgentparentid());
															scorerecord.setAgentparentids(sdmember.getAgentparentids());
															scorerecord.setOrderid(schemeid1);
															double gsq = new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														    double gsh = new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														    double bs = new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														    double dsq = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														    double dsh = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														    scorerecord.setRemark("gq:"+gsq+"gh"+gsh+",b:"+bs+",dq:"+dsq+",dh:"+dsh);
															betScorerecordService.save(scorerecord);
															
															//投注方案
															soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", soccersheme.getBrokeragemoney()).setParam("brokerageagentmoney", soccersheme.getBrokerageagentmoney()).setParam("brokeragemembermoney", soccersheme.getBrokeragemembermoney()).setParam("brokerageagentid", soccersheme.getBrokerageagentid()));
															soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdsoccersheme.getSchemeid()).setParam("brokeragemoney", sdsoccersheme.getBrokeragemoney()));
															
															
														}
													}
												}
												
											}
										} catch (Exception e) {
											System.out.println(e);
										}
										
										String firstagentid=null;
										Double bbb=null;
										try {
											String parentids = betMember.getAgentparentids();
											String parentid=betMember.getAgentparentid();
											if("A101".equals(parentid)){
												bbb = soccerSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:zuqiu limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", betMember.getAgentid()),Double.class);
											}else{
												String[] split = parentids.split(",");
												int i=0;
												for (String string : split) {
													if(i==0){
														if("A101".equals(string)){
															i=1;
														}
													}else{
														firstagentid=string;
														break;
													}
												}
												bbb = soccerSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:zuqiu limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", firstagentid),Double.class);
											}
										} catch (Exception e) {
											System.out.println("足球重新结算有异常");
										}
										
										Double plusawards=0.;
										if(bbb!=null){
											plusawards=bbb*schemereward;
										}
										betMember.setBankscore(betMember.getBankscore()+schemereward);
										betMember.setGamescore(betMember.getGamescore()+plusawards);
										betMember.setScore(betMember.getScore() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
										betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
										betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
										
										Integer updatenum=null;
										for(int i=0;i<10;i++){
											if(updatenum==null){
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
											}else if(updatenum==0){
												betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
												betMember.setBankscore(betMember.getBankscore()+schemereward);
												betMember.setGamescore(betMember.getGamescore()+plusawards);
												betMember.setScore(betMember.getScore() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
												betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
												betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
											}else if(updatenum==1){
												break;
											}
										}	
										if(updatenum==1){
											//更新缓存
											String ticket = betMember.getTicket();
											if(ticket!=null){
												try{
													ObjectMapper mapper=new ObjectMapper();
													byte[] json = mapper.writeValueAsBytes(betMember);
													cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
												}catch(Exception e){
													System.out.println(e);
												}
											}
											dd=new Date();
											try {
												scorerecord = new BetScorerecord();
												scorerecord.setId(null);
												scorerecord.setMemberid2(soccersheme.getMemberid2());
												scorerecord.setTime(dd);
												scorerecord.setContent("重新结算足彩返奖");
												scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + soccersheme.getBettingmoney().doubleValue());
												scorerecord.setBalance(betMember.getScore());
												scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore()-plusawards));
												scorerecord.setObankscore(new BigDecimal(betMember.getBankscore()-schemereward));
												scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()));
												scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()));
												scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()));
												scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()));
												scorerecord.setOrderid(schemeid1);
												scorerecord.setChangescore(schemereward+plusawards);
												scorerecord.setState(1);
												scorerecord.setType(14);
												scorerecord.setAgentid(betMember.getAgentid());
												scorerecord.setAgentparentid(betMember.getAgentparentid());
												scorerecord.setAgentparentids(betMember.getAgentparentids());
												betScorerecordService.save(scorerecord);
											} catch (Exception e) {
												System.out.println(e);
											}
											//投注方案
											soccerSchemeService.update(new Finder("update soccer_scheme set plusawards=:plusawards,bettingwin=:xxxxk, situation=1,settlementtime=:settlementtime,pretaxamount=:xxxx WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("xxxxk", schemereward+plusawards).setParam("xxxx", schemereward));
											//汇总投注
											soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id  and type=1 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
											//连红========================================
											if(soccersheme.getBuytype()==2){
												List<Double> hong = soccerAllbettingService.queryForList(new Finder("select bettingscore from soccer_allbetting" +
														" where memberid2=:id2 and state=1 and buytype=2 order by bettingtime desc limit 30 ").setParam("id2", soccersheme.getMemberid2()), Double.class);
												Integer lianhong = 0;
												if(hong.size()>0){
													for (Double score : hong) {
														if(score<=0){
															break;
														}else{
															lianhong++;
														}
													}
												}
												betMemberService.update(new Finder("update bet_member set lianhong =:lianhong where id2=:id2 ").setParam("lianhong", lianhong).setParam("id2", soccersheme.getMemberid2()));
	 										}
											//连红结束======================================
										}
									}
								} catch (Exception e) {
									System.out.println(e);
								}
							}else{
								continue;
							}
						}
					}	
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("此方案信息有误");
				}
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
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
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			String  mid = request.getParameter("mid");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			SoccerLeagueResult result = soccerLeagueArrangeService.queryForObject(new Finder("select b.halfscore,b.allscore,a.endtime,a.num,a.mid,a.zid,a.matchid2 as arrangeid2,a.starttime as matchtime,a.leftteamname as left_team,a.leftteamid2 as left_team_id2,a.rightteamname as right_team,a.rightteamid2 as right_team_id2 from soccer_league_arrange a left join soccer_league_result b on a.mid = b.mid where a.mid = :mid").setParam("mid", mid), SoccerLeagueResult.class);
			String halfscore1 = "";
			String halfscore2 = "";
			String allscore1 = "";
			String allscore2 = "";
			String halfscore = result.getHalfscore();
			String allscore = result.getAllscore();
			if(halfscore!=null){
				String[] halfscores = halfscore.split(":");
				if(halfscores.length>=2){
					halfscore1 = halfscores[0];
					halfscore2 = halfscores[1];
				}
			}
			if(allscore!=null){
				String[] allscores = allscore.split(":");
				if(allscores.length>=2){
					allscore1 = allscores[0];
					allscore2 = allscores[1];
				}
			}
			
			result.setNum(WeekOfDate.getWeekOfDate(result.getEndtime())+result.getNum());
			result.setState(1);
			result.setIssettle(3);
			returnObject.setData(result);
			model.addAttribute("halfscore1", halfscore1);
			model.addAttribute("halfscore2", halfscore2);
			model.addAttribute("allscore1", allscore1);
			model.addAttribute("allscore2", allscore2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/soccerscheme/soccerleagueresultAdd";
		}else if("2".equals(request.getParameter("k"))){
			String schemeid = request.getParameter("schemeid");
			SoccerScheme scheme = null;
			if(schemeid!=null){
				scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), SoccerScheme.class);
			}
			if(scheme==null){
				model.addAttribute("exception", "无此方案");
				return "/errorpage/error";
			}
			
			String mid = request.getParameter("mid");
			String oddsname = request.getParameter("oddsname");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			scheme= soccerSchemeService.queryForObject(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where a.schemeid = :schemeid").setParam("schemeid", schemeid),SoccerScheme.class);
			SoccerSchemeMatch match= soccerSchemeMatchService.queryForObject(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid  where a.schemeid=:schemeid and b.mid = :mid  order by a.id").setParam("schemeid", schemeid).setParam("mid", mid), SoccerSchemeMatch.class);
			SoccerLeagueOrderContent orderContent = soccerSchemeMatchService.queryForObject(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid=:schemeid and a.oddsname=:oddsname and a.mid = :mid group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeid).setParam("oddsname", oddsname).setParam("mid", mid), SoccerLeagueOrderContent.class);
				
			if(orderContent!=null){
					String oddsrealname = "";
				    if("left_odds".equals(oddsname)){
				    	try{
				    		String cached2 = (String)cached.getCached(("2x1_"+orderContent.getMid().toString()).getBytes());
					    	if(cached2!=null){
					    		ObjectMapper mmmm=new ObjectMapper();
								SoccerLeague2choose1odds readValue = mmmm.readValue(cached2, SoccerLeague2choose1odds.class);
								oddsrealname =readValue.getLeft_name();
								orderContent.setOddsrealname(oddsrealname);
					    	}else{
					    		oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
					    		orderContent.setOddsrealname(oddsrealname);
					    	}
				    	}catch (Exception e) {
							e.printStackTrace();
							oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
							orderContent.setOddsrealname(oddsrealname);
						}
				    	
				    }else if("right_odds".equals(oddsname)){
				    	try{
				    		String cached2 = (String)cached.getCached(("2x1_"+orderContent.getMid().toString()).getBytes());
					    	if(cached2!=null){
					    		ObjectMapper mmmm=new ObjectMapper();
								SoccerLeague2choose1odds readValue = mmmm.readValue(cached2, SoccerLeague2choose1odds.class);
								oddsrealname =readValue.getRight_name();
								orderContent.setOddsrealname(oddsrealname);
					    	}else{
					    		oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
					    		orderContent.setOddsrealname(oddsrealname);
					    	}
				    	}catch (Exception e) {
							e.printStackTrace();
							oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",orderContent.getMid()),String.class);
							orderContent.setOddsrealname(oddsrealname);
						}
				    }
				    if("rqwin".equals(oddsname)||"rqflat".equals(oddsname)||"rqlose".equals(oddsname)){
				    	try{
				    		String cached2 = (String)cached.getCached(("rqsfp_"+orderContent.getMid().toString()).getBytes());
					    	if(cached2!=null){
					    		ObjectMapper mmmm=new ObjectMapper();
					    		SoccerLeagueOdds readValue = mmmm.readValue(cached2, SoccerLeagueOdds.class);
					    		String letpoints = readValue.getLetpoints();
					    		String betname = orderContent.getBetname().toString();
					    		orderContent.setBetname(betname+"("+ letpoints+")");
					    	}else{
					    		String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", orderContent.getMid()), String.class);
						    	String betname = orderContent.getBetname().toString();
						    	orderContent.setBetname(betname+"("+ letpoints+")");
					    	}
				    	}catch (Exception e) {
							e.printStackTrace();
							String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from soccer_league_odds where mid = :mid and type = 1 ").setParam("mid", orderContent.getMid()), String.class);
					    	String betname = orderContent.getBetname().toString();
					    	orderContent.setBetname(betname+"("+ letpoints+")");
					    	
						}
				    }
			}
			match.setNum(WeekOfDate.getWeekOfDate(match.getEndtime())+match.getNum());
			returnObject.setData(scheme);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("match", match);
			model.addAttribute("orderContent", orderContent);
			return "/lottery/soccerscheme/soccerschemeoddmodify";
		}else{
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/soccerscheme/soccerschemeCru";
		}
		
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody      
	public  ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				soccerSchemeService.deleteById(id,SoccerScheme.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
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
	@ResponseBody      
	public ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			soccerSchemeService.deleteByIds(ids,SoccerScheme.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	void bettingmoneyagentrebate(BetAgent betaggg,double bettingmoney,BetMember betmember11,String orderid,Date settlementtime,Double bettingrebate1){
		try {
			if("A101".equals(betaggg.getAgentid())){
				return;
			}else{
				if(betaggg!=null){
					if(betaggg.getActive()==1){
						Double bettingrebate =betaggg.getBettingrebate()-bettingrebate1;
						if((bettingrebate!=null)&&(bettingrebate<=1)&&(bettingrebate>=0)){
							BetCommission betcommission =new BetCommission();
							
							SoccerAllbetting allbet = soccerAllbettingService.queryForObject(new Finder("select bettingtime from soccer_allbetting where id = :id").setParam("id", orderid), SoccerAllbetting.class);
							if(allbet!=null){
								betcommission.setBettingtime(allbet.getBettingtime());
							}
							betcommission.setAgentparentid(betaggg.getParentid());
							betcommission.setAgentparentids(betaggg.getParentids());
							if(betmember11!=null){
								betcommission.setMemberagentid(betmember11.getAgentid());
							}
							
							betcommission.setAgentid(betaggg.getAgentid());
							betcommission.setCommission(bettingmoney*bettingrebate);
							betcommission.setMemberid2(betmember11.getId2());
							betcommission.setOrderid(orderid);
							betcommission.setSettlementtime(settlementtime);
							if(betcommission.getCommission()!=0.){
								betCommissionService.save(betcommission);
								betAgentService.update(new Finder("update bet_agent set score=IFNULL(score,0)+:score,bettingty=IFNULL(bettingty,0)+:score where agentid=:agentid ").setParam("agentid", betaggg.getAgentid()).setParam("score", betcommission.getCommission()));
							}
							BetAgent betaggg1 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", betaggg.getParentid()), BetAgent.class);
							
							bettingmoneyagentrebate(betaggg1,bettingmoney,betmember11,orderid,settlementtime,betaggg.getBettingrebate());
						}
					
					}else{
						BetAgent betaggg1 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", betaggg.getParentid()), BetAgent.class);
						
						bettingmoneyagentrebate(betaggg1,bettingmoney,betmember11,orderid,settlementtime,bettingrebate1);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("足球手动结算有异常");
		}
		
	}
	
	
	
	

}
