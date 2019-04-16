package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import org.springrain.lottery.entity.BasketballLeagueArrange;
import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.entity.BasketballLeagueResult;
import org.springrain.lottery.entity.BasketballOrder;
import org.springrain.lottery.entity.BasketballOrderContent;
import org.springrain.lottery.entity.BasketballScheme;
import org.springrain.lottery.entity.BasketballSchemeMatch;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.lottery.entity.BetCommission;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.lottery.service.IBasketballLeagueArrangeService;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.service.IBasketballLeagueResultService;
import org.springrain.lottery.service.IBasketballOrderContentService;
import org.springrain.lottery.service.IBasketballOrderService;
import org.springrain.lottery.service.IBasketballSchemeMatchService;
import org.springrain.lottery.service.IBasketballSchemeService;
import org.springrain.lottery.service.IBetAgentBrokerageService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetCommissionService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.basketballWeekOfDate;
import org.springrain.system.service.IDicDataService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 16:06:05
 * @see org.springrain.lottery.web.BasketballScheme
 */
@Controller
@RequestMapping(value="/basketballschemeticket")
public class BasketballSchemeTicketController  extends BaseController {
	@Resource
	private IBasketballSchemeService basketballSchemeService;
	@Resource
	private IBasketballSchemeMatchService basketballSchemeMatchService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	@Resource
	private IBasketballOrderContentService basketballOrderContentService;
	@Resource
	private ICached cached;
	@Resource
	private IBasketballOrderService basketballOrderService;
	@Resource
	private IBasketballLeagueResultService basketballLeagueResultService;
	@Resource
	private IBasketballLeagueArrangeService basketballLeagueArrangeService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetCommissionService betCommissionService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private IBetAgentBrokerageService betAgentBrokerageService;
	@Resource
	private IDicDataService dicDataService;
	private String listurl="/lottery/basketballscheme/basketballschemeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BasketballScheme basketballScheme) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BasketballOrder> datas= basketballOrderService.queryForList(new Finder("select a.*,c.name as playmethod from basketball_order a LEFT JOIN basketball_scheme b on a.schemeid = b.schemeid LEFT JOIN basketball_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid and (b.agentid = :agentid or b.agentparentids like :agentparentids) order by b.id").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid),BasketballOrder.class,page);
			if(datas!=null){
				for(BasketballOrder basketballOrder : datas){
					List<BasketballOrderContent> contentDatas=basketballOrderContentService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,b.hometeam,b.awayteam,c.oddsrealname from basketball_order_content a left join basketball_league_arrange b on a.zid = b.zid left join basketball_league_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", basketballOrder.getOrderid()),BasketballOrderContent.class);
					basketballOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BasketballOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/basketballschemeticket/basketballorderticketList";
		}else if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String schemeid2 = request.getParameter("schemeid2");
			List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where  a.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) order by a.bettingtime").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", schemeid2),BasketballScheme.class,page);
			if(datas!=null){
				List<String> schemeids = new ArrayList<String>();
				
				for (BasketballScheme basketballScheme2 : datas) {
					String schemeid = basketballScheme2.getSchemeid();
					if(schemeid!=null){
						schemeids.add(basketballScheme2.getSchemeid());
					}
				}
				
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
				
				for(BasketballScheme scheme : datas){
					List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
					if(matchDatas!=null){
						for(BasketballSchemeMatch schemeMatch : matchDatas){
							String schemeid = schemeMatch.getSchemeid();
							if(schemeid.equals(scheme.getSchemeid())){
								sss.add(schemeMatch);
							}
						}
					}
					scheme.setSchemecontent(sss);
				}
				
			}
		
			returnObject.setQueryBean(basketballScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("schemeid2", schemeid2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/basketballscheme/basketballschemeListgod";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String memberid2 = request.getParameter("memberid2");
			String issuestate = request.getParameter("issuestate");
			//篮球出票倍数
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", pagentid), BetAgent.class);
			String company = "";
			if(",A101,".equals(agent.getParentids())){
				company = pagentid;
			}else{
				company = agent.getParentids().split(",")[2];
			}
			Double value = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:id and remark=:company ").setParam("id", "issuebetmulriple_lq").setParam("company", company), Double.class);
			if(StringUtils.isBlank(memberid2)){
				memberid2=null;
			}else{
				//memberid2="%"+memberid2+"%";
			}
			if("100".equals(issuestate)){
				issuestate=null;
			}
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = outputFormat.format(new Date());
			
			if(StringUtils.isBlank(starttime)){
				starttime=date;
			}
			if(StringUtils.isBlank(endtime)){
				endtime=date;
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BasketballOrder> datas = null;
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas= basketballSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from basketball_order a LEFT JOIN bet_member c on c.id2=a.memberid2 left join basketball_scheme b on a.schemeid = b.schemeid where  c.isinternal=0 and c.isissue=1 and  (:memberid2 is null or a.memberid2 = :memberid2) and (:issnestate is null or a.issuestate = :issnestate)  and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("issnestate", issuestate),BasketballOrder.class,page);
			}else{
				datas= basketballSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,b.bettingtime from basketball_order a LEFT JOIN bet_member c on c.id2=a.memberid2 left join basketball_scheme b on a.schemeid = b.schemeid where  c.isinternal=0 and c.isissue=1 and  substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:issnestate is null or a.issuestate = :issnestate)  and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("issnestate", issuestate).setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2),BasketballOrder.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				List<String> schemeids=new ArrayList<String>();
				for (BasketballOrder basketballScheme2 : datas) {
					String schemeid = basketballScheme2.getSchemeid();
					if(schemeid!=null){
						schemeids.add(basketballScheme2.getSchemeid());
					}
				}
				List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
				if(matchDatas!=null){
					List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid,c.systemissue from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.zid,a.oddsname").setParam("schemeid", schemeids));
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
				
				for(BasketballOrder scheme : datas){
					List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
					if(matchDatas!=null){
						for(BasketballSchemeMatch schemeMatch : matchDatas){
							String schemeid = schemeMatch.getSchemeid();
							if(schemeid.equals(scheme.getSchemeid())){
								sss.add(schemeMatch);
							}
						}
					}
					scheme.setSchemecontent(sss);
				}
				
			}
		    if(issuestate!=null){
		    	basketballScheme.setIssnestate(Integer.valueOf(issuestate));
		    }
			returnObject.setQueryBean(basketballScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("value", value);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/basketballschemeticket/basketballschemeList";
		}
		
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderlist")
	public String orderlist(HttpServletRequest request, Model model,BasketballOrder basketballOrder2) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		page.setPageSize(10);
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		String memberid2 = request.getParameter("memberid2");
		String issuestate = request.getParameter("issuestate");
		
		if("100".equals(issuestate)){
			issuestate = null;
		}
		
		if(StringUtils.isBlank(memberid2)){
			memberid2=null;
		}else{
			//memberid2="%"+memberid2+"%";
		}
		if("100".equals(issuestate)){
			issuestate=null;
		}
		
		if(StringUtils.isBlank(starttime)){
			starttime="0000-01-01";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-01-01";
		}
		java.sql.Date startDate = java.sql.Date.valueOf(starttime);
		java.sql.Date endDate=java.sql.Date.valueOf(endtime);
		List<BasketballOrder> datas = null;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			datas= basketballOrderService.queryForList(new Finder("select a.*,b.bettingtime from basketball_order a LEFT JOIN basketball_scheme b on a.schemeid = b.schemeid  where  (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (b.agentid = :agentid or b.agentparentids like :agentparentids) order by b.id").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BasketballOrder.class,page);
		}else{
			datas= basketballOrderService.queryForList(new Finder("select a.*,b.bettingtime from basketball_order a LEFT JOIN basketball_scheme b on a.schemeid = b.schemeid  where  substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (b.agentid = :agentid or b.agentparentids like :agentparentids) order by b.id").setParam("starttime",startDate).setParam("endtime", endDate).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),BasketballOrder.class,page);
		}
		
		if(datas!=null){
			for(BasketballOrder basketballOrder : datas){
				List<BasketballOrderContent> contentDatas=basketballOrderContentService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,b.hometeam,b.awayteam,c.oddsrealname from basketball_order_content a left join basketball_league_arrange b on a.zid = b.zid left join basketball_league_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", basketballOrder.getOrderid()),BasketballOrderContent.class);
				basketballOrder.setOrdercontent(contentDatas);
			}
		}
		
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}
		returnObject.setQueryBean(basketballOrder2);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("startTime", startDate);
		model.addAttribute("endTime", endDate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/basketballschemeticket/basketballorderList";
		
		
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param basketballScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BasketballScheme basketballScheme) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BasketballScheme> datas=basketballSchemeService.findListDataByFinder(null,page,BasketballScheme.class,basketballScheme);
			returnObject.setQueryBean(basketballScheme);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BasketballScheme basketballScheme) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = basketballSchemeService.findDataExportExcel(null,listurl, page,BasketballScheme.class,basketballScheme);
		String fileName="basketballScheme"+GlobalStatic.excelext;
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
		return "/lottery/basketballscheme/basketballschemeLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  //BasketballScheme basketballScheme = basketballSchemeService.findBasketballSchemeById(id);
			BasketballScheme basketballScheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id", id).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
		   returnObject.setData(basketballScheme);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	
	@RequestMapping("/updateissuestate")
	@ResponseBody      
	public ReturnDatas updateissuestate(Model model,BasketballScheme basketballScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			BasketballOrder order = basketballOrderService.queryForObject(new Finder("select * from basketball_order where orderid=:orderid ").setParam("orderid", orderid), BasketballOrder.class);			
			if(order!=null){
				if(order.getSystemissue()!=null&&order.getIssuestate()==3){
					if(order.getSystemissue()==3){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该方案已手动出票");
					}else if(order.getSystemissue()==1){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该方案已系统出票");
					}
				}else{
					//basketballSchemeService.update(new Finder("update basketball_scheme set issnestate=2,systemissue=3 where schemeid=:schemeid ").setParam("schemeid", basketballScheme.getSchemeid()));
					basketballSchemeService.update(new Finder("update basketball_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", orderid));
				}
				
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<BasketballOrder> order2 = basketballOrderService.queryForList(new Finder("select * from basketball_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BasketballOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(BasketballOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				basketballSchemeService.update(new Finder("update basketball_scheme set issnestate=2,systemissue=3 where schemeid=:schemeid ").setParam("schemeid", basketballScheme.getSchemeid()));
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
	public ReturnDatas systemissue(Model model,BasketballScheme basketballScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
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
					BasketballOrder order = basketballOrderService.queryForObject(new Finder("select * from basketball_order where orderid=:orderid ").setParam("orderid", orderid), BasketballOrder.class);
					if(Integer.valueOf(pass) > order.getBetmulriple()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数超越投注倍数");
					}else{
						if(order.getIssuestate()==4){
							basketballOrderService.update(new Finder("update basketball_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", orderid));
						}
						basketballOrderService.update(new Finder("update basketball_order set systemissue=1,issuebetmulriple=:issuebetmulriple where orderid=:orderid ").setParam("orderid", orderid).setParam("issuebetmulriple", pass));
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("倍数不正确");
				}
			}else{
				BasketballOrder order = basketballOrderService.queryForObject(new Finder("select * from basketball_order where orderid=:orderid ").setParam("orderid", orderid), BasketballOrder.class);
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
				List<BasketballOrder> order2 = basketballOrderService.queryForList(new Finder("select * from basketball_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BasketballOrder.class);
				List<Integer>issuestate=new ArrayList<Integer>();
				for(BasketballOrder orders : order2){
					issuestate.add(orders.getIssuestate());
				}
				int ii=0;
				for(int i=0;i<issuestate.size();i++){
					if(issuestate.get(i)==3){
						ii++;
					}
				}
				if(ii==issuestate.size()){
					basketballSchemeService.update(new Finder("update basketball_scheme set issnestate=2,systemissue=3 where schemeid=:schemeid ").setParam("schemeid", basketballScheme.getSchemeid()));
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
		public ReturnDatas schemeSystemissue(Model model,BasketballScheme basketballScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				//系统出票
//				String orderid = request.getParameter("orderid");
				String schemeid = request.getParameter("schemeid");
				List<BasketballOrder> orders = basketballSchemeService.queryForList(new Finder("select * from basketball_order where schemeid = :schemeid").setParam("schemeid", schemeid),BasketballOrder.class);
				if("1".equals(request.getParameter("k"))){
					String pass = request.getParameter("pass");
					boolean matches = pass.matches("^[1-9][0-9]*$");
					if(matches == true){
						for(int i=0;i<orders.size();i++){
//							SoccerLeagueOrder order = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orders.get(i).getOrderid()), SoccerLeagueOrder.class);
							if(Integer.valueOf(pass) > orders.get(i).getBetmulriple()){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("倍数超越投注倍数");
							}else{
								if(orders.get(i).getIssuestate()==4){
									basketballSchemeService.update(new Finder("update basketball_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", orders.get(i).getOrderid()));
								}
								basketballSchemeService.update(new Finder("update basketball_order set systemissue=1,issuebetmulriple=:issuebetmulriple where orderid=:orderid ").setParam("orderid", orders.get(i).getOrderid()).setParam("issuebetmulriple", pass));
							}
						} 
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数不正确");
					}
				}else{
					for(int i=0;i<orders.size();i++){
//					SoccerLeagueOrder order = soccerLeagueOrderService.queryForObject(new Finder("select * from soccer_league_order where orderid=:orderid ").setParam("orderid", orderid), SoccerLeagueOrder.class);
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
				      }
					}
					List<Integer>issuestate=new ArrayList<Integer>();
					for(BasketballOrder order2 : orders){
						issuestate.add(order2.getIssuestate());
					}
					int ii=0;
					for(int i=0;i<issuestate.size();i++){
						if(issuestate.get(i)==3){
							ii++;
						}
					}
					if(ii==issuestate.size()){
						basketballSchemeService.update(new Finder("update basketball_scheme set issnestate=2 where schemeid=:schemeid ").setParam("schemeid", basketballScheme.getSchemeid()));
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
			return returnObject;
		
		}
		
		//方案手动出票接口
		@RequestMapping("/schemeUpdateissuestate")
		@ResponseBody      
		public ReturnDatas schemeupdateissuestate(Model model,BasketballScheme basketballScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				//手动出票
//				String orderid = request.getParameter("orderid");
				String schemeid = request.getParameter("schemeid");
				List<BasketballOrder> order = basketballOrderService.queryForList(new Finder("select * from basketball_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BasketballOrder.class);			
				if(order!=null){
					for(int i=0;i<order.size();i++){
						if(order.get(i).getSystemissue()!=null&&order.get(i).getIssuestate()==3){
							if(order.get(i).getSystemissue()==3){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该方案已手动出票");
							}else if(order.get(i).getSystemissue()==1){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该方案已系统出票");
							}
						}else{
							//basketballSchemeService.update(new Finder("update basketball_scheme set issnestate=2,systemissue=3 where schemeid=:schemeid ").setParam("schemeid", basketballScheme.getSchemeid()));
							basketballSchemeService.update(new Finder("update basketball_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", order.get(i).getOrderid()));
						}
					}
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该订单不存在");
				}
				List<BasketballOrder> order2 = basketballOrderService.queryForList(new Finder("select * from basketball_order where schemeid=:schemeid ").setParam("schemeid", schemeid), BasketballOrder.class);
				List<Integer>issuestate=new ArrayList<Integer>();
				for(BasketballOrder orders : order2){
					issuestate.add(orders.getIssuestate());
				}
				int ii=0;
				for(int i=0;i<issuestate.size();i++){
					if(issuestate.get(i)==3){
						ii++;
					}
				}
				if(ii==issuestate.size()){
					basketballSchemeService.update(new Finder("update basketball_scheme set issnestate=2,systemissue=3 where schemeid=:schemeid ").setParam("schemeid", basketballScheme.getSchemeid()));
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
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BasketballScheme basketballScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//就这里不一样
			String pagentid = SessionUser.getShiroUser().getAgentid();
			BetAgent pAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", pagentid), BetAgent.class);
			String agentparentids = ","+pagentid+",";
			
			if("1".equals(request.getParameter("k"))){
				//无效比赛处理
				String zid = request.getParameter("zid");
				BasketballLeagueArrange arrange = basketballLeagueArrangeService.queryForObject(new Finder("select * from basketball_league_arrange where  zid = :zid").setParam("zid", zid), BasketballLeagueArrange.class);
				
				if(arrange!=null){
					if(arrange.getStarttime().after(new Date())){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次未开赛");
					}else{
						BasketballLeagueResult result = basketballLeagueResultService.queryForObject(new Finder("select * from basketball_league_result where  zid = :zid").setParam("zid", zid), BasketballLeagueResult.class);
						if(result!=null){
							if("--:--".equals(result.getScore())){
								basketballLeagueResultService.update(new Finder("delete from basketball_league_result where zid=:zid ").setParam("zid", zid));
								basketballOrderContentService.update(new Finder("update basketball_order_content set odds = 1,settletime=now(),result=1,resultname=:resultname where zid = :zid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("zid", zid).setParam("resultname", "无效比赛"));
								BasketballLeagueResult basketballResult = new BasketballLeagueResult();
								basketballResult.setZid(arrange.getZid());
								basketballResult.setNum(basketballWeekOfDate.getWeekOfDate(arrange.getMatchdate())+arrange.getNum());
								basketballResult.setMatchname(arrange.getMatchname());
								basketballResult.setStarttime(arrange.getStarttime());
								basketballResult.setAwayteam(arrange.getAwayteam());
								basketballResult.setAwayteamid2(arrange.getAwayteamid2());
								basketballResult.setHometeam(arrange.getHometeam());
								basketballResult.setHometeamid2(arrange.getHometeamid2());
								basketballResult.setScore("--:--");
								basketballResult.setCreatedate(new Date());
								basketballResult.setState(2);
								basketballResult.setIssettle(1);
								basketballLeagueResultService.save(basketballResult);
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("该场次已有结果");
							}
						}else{
							basketballOrderContentService.update(new Finder("update basketball_order_content set odds = 1,settletime=now(),result=1,resultname=:resultname where zid = :zid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("zid", zid).setParam("resultname", "无效比赛"));
							BasketballLeagueResult basketballResult = new BasketballLeagueResult();
							basketballResult.setZid(arrange.getZid());
							basketballResult.setNum(basketballWeekOfDate.getWeekOfDate(arrange.getMatchdate())+arrange.getNum());
							basketballResult.setMatchname(arrange.getMatchname());
							basketballResult.setStarttime(arrange.getStarttime());
							basketballResult.setAwayteam(arrange.getAwayteam());
							basketballResult.setAwayteamid2(arrange.getAwayteamid2());
							basketballResult.setHometeam(arrange.getHometeam());
							basketballResult.setHometeamid2(arrange.getHometeamid2());
							basketballResult.setScore("--:--");
							basketballResult.setCreatedate(new Date());
							basketballResult.setState(2);
							basketballResult.setIssettle(1);
							basketballLeagueResultService.save(basketballResult);
							
						}
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次不存在");
				}
			}else if("1".equals(request.getParameter("cancel"))){
				String schemeid = request.getParameter("schemeid");
				BasketballScheme scheme = null;
				if(schemeid!=null){
					scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid", schemeid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
				}else{
					//scheme = basketballSchemeService.findBasketballSchemeById(basketballScheme.getId());
					scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id", basketballScheme.getId()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
				}
				
				if(scheme!=null){
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						//未开奖撤销
						Integer memberid2 = scheme.getMemberid2();
						BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
						if(member!=null){
							basketballSchemeService.update(new Finder("update basketball_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", scheme.getId()));
							basketballOrderService.update(new Finder("update basketball_order set result=2 where schemeid=:schemeid").setParam("schemeid", scheme.getSchemeid()));
							soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", scheme.getSchemeid()));
							BetMember member2=new BetMember();
							member2.setId(member.getId());
							member2.setId2(member.getId2());
							//BigDecimal freezingScore = new BigDecimal(Double.toString(member.getFreezingscore()));
							BigDecimal gameScore = new BigDecimal(Double.toString(member.getGamescore()));
					        BigDecimal bettingMoney = new BigDecimal(scheme.getBettingmoney().toString());
							//member2.setFreezingscore(freezingScore.subtract(bettingMoney).doubleValue());
							member2.setGamescore(gameScore.add(bettingMoney).doubleValue());
							betMemberService.update(member2,true);
							
							
							if(scheme.getBuytype()==1){
								//跟单
								BasketballScheme sdbasketballsheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", scheme.getSchemeid2()), BasketballScheme.class);					
								basketballSchemeService.update(new Finder("update basketball_scheme set bettingalready=bettingalready-:bettingalready,bettingnum=bettingnum-:bettingnum where schemeid=:schemeid").setParam("bettingalready", scheme.getBettingmoney()).setParam("bettingnum", 1).setParam("schemeid", sdbasketballsheme.getSchemeid()));
							}

							if(scheme.getBuytype()==2){
								//神单
								List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select * from basketball_scheme where buytype = 1 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", scheme.getSchemeid2()), BasketballScheme.class);
								
								for(BasketballScheme gdScheme : datas){
									if(gdScheme.getSituation()!=null&&gdScheme.getSituation()==0){
										Integer gdmemberid2 = gdScheme.getMemberid2();
										BetMember gdmember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", gdmemberid2),BetMember.class);
										if(gdmember!=null){
											basketballSchemeService.update(new Finder("update basketball_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", gdScheme.getId()));
											basketballOrderService.update(new Finder("update basketball_order set result=2 where schemeid=:schemeid").setParam("schemeid", gdScheme.getSchemeid()));
											soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", gdScheme.getSchemeid()));
											
											BetMember gdmember2=new BetMember();
											gdmember2.setId(gdmember.getId());
											gdmember2.setId2(gdmember.getId2());
											//BigDecimal gdfreezingScore = new BigDecimal(Double.toString(gdmember.getFreezingscore()));
											BigDecimal gdgameScore = new BigDecimal(Double.toString(gdmember.getGamescore()));
									        BigDecimal gdbettingMoney = new BigDecimal(gdScheme.getBettingmoney().toString());
									        //gdmember2.setFreezingscore(gdfreezingScore.subtract(gdbettingMoney).doubleValue());
									        gdmember2.setGamescore(gdgameScore.add(gdbettingMoney).doubleValue());
											betMemberService.update(gdmember2,true);
											BasketballScheme sdbasketballsheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", gdScheme.getSchemeid2()), BasketballScheme.class);					
											basketballSchemeService.update(new Finder("update basketball_scheme set bettingalready=bettingalready-:bettingalready,bettingnum=bettingnum-:bettingnum where schemeid=:schemeid").setParam("bettingalready", gdScheme.getBettingmoney()).setParam("bettingnum", 1).setParam("schemeid", sdbasketballsheme.getSchemeid()));
											
											//gdmember.setFreezingscore(gdmember2.getFreezingscore());
											gdmember.setGamescore(gdmember2.getGamescore());
													String ticket = gdmember.getTicket();
													if(ticket!=null){
														try{
//															cached.deleteCached(("TICKET_"+ticket).getBytes());
															ObjectMapper mapper=new ObjectMapper();
															byte[] json = mapper.writeValueAsBytes(gdmember);
															cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
														}catch(Exception e){
															//String errorMessage = e.getLocalizedMessage();
															e.printStackTrace();
														}
														
													}
													 //金币记录
												     BetScorerecord betScorerecord=new BetScorerecord();
												     String content="";
												     content="用户"+gdScheme.getMemberid2()+"撤销在篮球的方案号为"+gdScheme.getSchemeid()+"的投注方案，投注额为"+gdScheme.getBettingmoney();
												     betScorerecord.setMemberid2(gdmember.getId2());
												     betScorerecord.setTime(new Date());
												     betScorerecord.setContent(content);
												     betScorerecord.setOriginalscore(gdmember.getScore());
												     betScorerecord.setChangescore(gdbettingMoney.doubleValue());
												     betScorerecord.setBalance(gdmember.getScore());
												     betScorerecord.setState(1);
												     betScorerecord.setType(19);
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
												     details = "撤销ID："+gdmember.getId2()+"的用户在"+"篮球"+gdScheme.getBettingmoney()+"分的投注方案";
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
							//member.setFreezingscore(member2.getFreezingscore());
							member.setGamescore(member2.getGamescore());
									String ticket = member.getTicket();
									if(ticket!=null){
										try{
//											cached.deleteCached(("TICKET_"+ticket).getBytes());
											ObjectMapper mapper=new ObjectMapper();
											byte[] json = mapper.writeValueAsBytes(member);
											cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
										}catch(Exception e){
											//String errorMessage = e.getLocalizedMessage();
											e.printStackTrace();
										}
										
									}
									 //金币记录
								     BetScorerecord betScorerecord=new BetScorerecord();
								     String content="";
								     content="用户"+scheme.getMemberid2()+"撤销在篮球的方案号为"+scheme.getSchemeid()+"的投注方案，投注额为"+scheme.getBettingmoney();
								     betScorerecord.setMemberid2(member.getId2());
								     betScorerecord.setTime(new Date());
								     betScorerecord.setContent(content);
								     betScorerecord.setOriginalscore(member.getScore());
								     betScorerecord.setChangescore(bettingMoney.doubleValue());
								     betScorerecord.setBalance(member.getScore());
								     betScorerecord.setState(1);
								     betScorerecord.setType(19);
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
								     details = "撤销ID："+member.getId2()+"的用户在"+"篮球"+scheme.getBettingmoney()+"分的投注方案";
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
						
						/*
						Date now = new Date();
					    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    String nowDate = dateFormat.format(now);
					    String settleDate = dateFormat.format(scheme.getSettlementtime());
						Integer memberid2 = scheme.getMemberid2();
						BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2),BetMember.class);
						if(member!=null){
							
							member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2),BetMember.class);
							basketballSchemeService.update(new Finder("update basketball_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", scheme.getId()));
							soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", scheme.getSchemeid()));
							
							BetMember member2=new BetMember();
							member2.setId(member.getId());
							member2.setId2(member.getId2());
							//BigDecimal freezingScore = new BigDecimal(Double.toString(member.getFreezingscore()));
							//BigDecimal gameScore = new BigDecimal(Double.toString(member.getGamescore()));
					        //BigDecimal bettingMoney = new BigDecimal(scheme.getBettingmoney().toString());
					        member2.setGamescore(member.getGamescore() - scheme.getBettingwin().doubleValue()+scheme.getBettingmoney().doubleValue());
					        member2.setScore(member.getScore() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
					        //当天输赢(可能结算时间与撤销时间不同)
					       
					        if(nowDate.equals(settleDate)){
					        	member2.setDayscore(member.getDayscore()  - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
					        }else{
					        	//betRankMemberService.update(new Finder("update bet_rank_member set dayscore =dayscore-:dayscore where memberid = :memberid and substr(date,1,10) = :date").setParam("dayscore", (scheme.getBettingwin().doubleValue() - scheme.getBettingmoney().doubleValue())).setParam("memberid", member.getId()).setParam("date", settleDate));
					        	//betAgentService.update(new Finder("update bet_agentreportform set winorloss = winorloss-:winorloss where substr(date,1,10) = :date and agentid = :agentid").setParam("winorloss", (scheme.getBettingwin().doubleValue() - scheme.getBettingmoney().doubleValue())).setParam("date", settleDate).setParam("agentid", member.getAgentid()));
					        	//betReportformService.update(new Finder("update bet_reportform set winorloss = winorloss-:winorloss where substr(date,1,10) = :date").setParam("winorloss", (scheme.getBettingwin().doubleValue() - scheme.getBettingmoney().doubleValue())).setParam("date", settleDate));
					        }
					        member2.setWinorfail(member.getWinorfail() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
							betMemberService.update(member2,true);
							
							//更新缓存
							member.setGamescore(member2.getGamescore());
							member.setScore(member2.getScore());
							if(nowDate.equals(settleDate)){
								member.setDayscore(member2.getDayscore());
							}
							member.setWinorfail(member2.getWinorfail());
							
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
							try {
								String agentid = member.getAgentid();
								double bettingmoney = (scheme.getBettingmoney()).doubleValue();
								if(agentid!=null){
									if("A101".equals(agentid)){
										
									}else{
										BetAgent betaggg = betAgentService.queryForObject(new Finder("select bettingrebate,parentid from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
										if(betaggg!=null){
											Double bettingrebate = betaggg.getBettingrebate();
											if((bettingrebate!=null)&&(bettingrebate<=1)&&(bettingrebate>=0)){
												BetCommission betcommission =new BetCommission();
												betcommission.setAgentid(agentid);
												betcommission.setCommission(bettingmoney*bettingrebate);
												betcommission.setMemberid2(member.getId2());
												betcommission.setOrderid(scheme.getSchemeid().toString());
												if(betcommission.getCommission()!=0.){
													//betCommissionService.save(betcommission);
													betCommissionService.update(new Finder("delete from bet_commission where agentid = :agentid and memberid2 = :memberid2 and substr(settlementtime,1,10) = :settlementtime and orderid =:orderid").setParam("agentid", betcommission.getAgentid()).setParam("memberid2", betcommission.getMemberid2()).setParam("settlementtime", settleDate).setParam("orderid", betcommission.getOrderid()));
													betAgentService.update(new Finder("update bet_agent set score=score-:score,bettingty=bettingty-:score where agentid=:agentid ").setParam("agentid", agentid).setParam("score", betcommission.getCommission()));
												}
												String parentid = betaggg.getParentid();
												if("A101".equals(parentid)){
													
												}else{
													BetAgent betaggg1 = betAgentService.queryForObject(new Finder("select bettingrebate,parentid from bet_agent where agentid=:agentid ").setParam("agentid", parentid), BetAgent.class);
													if(betaggg1!=null){
														Double bettingrebate1=betaggg1.getBettingrebate();
														Double bettingrebate11=new Double(betaggg1.getBettingrebate());
														if((bettingrebate1!=null)&&(bettingrebate1<=1)&&(bettingrebate1>=0)){
															bettingrebate1=bettingrebate1-bettingrebate;
															if((bettingrebate1!=null)&&(bettingrebate1<=1)&&(bettingrebate1>=0)){
																BetCommission betcommission1 =new BetCommission();
																betcommission1.setAgentid(parentid);
																betcommission1.setCommission(bettingmoney*bettingrebate1);
																betcommission1.setMemberid2(member.getId2());
																betcommission1.setOrderid(scheme.getSchemeid().toString());
																if(betcommission1.getCommission()!=0.){
																	//betCommissionService.save(betcommission1);
																	betCommissionService.update(new Finder("delete from bet_commission where agentid = :agentid and memberid2 = :memberid2 and substr(settlementtime,1,10) = :settlementtime and orderid =:orderid").setParam("agentid", betcommission.getAgentid()).setParam("memberid2", betcommission.getMemberid2()).setParam("settlementtime", settleDate).setParam("orderid", betcommission.getOrderid()));
																	betAgentService.update(new Finder("update bet_agent set score=score-:score,bettingty=bettingty-:score where agentid=:agentid ").setParam("agentid", parentid).setParam("score", betcommission1.getCommission()));
																}
																String parentid2 = betaggg1.getParentid();
																if("A101".equals(parentid2)){
																	
																}else{
																	BetAgent betaggg2 = betAgentService.queryForObject(new Finder("select bettingrebate,parentid from bet_agent where agentid=:agentid ").setParam("agentid", parentid2), BetAgent.class);
																	if(betaggg2!=null){
																		Double bettingrebate2=betaggg2.getBettingrebate();
																		if((bettingrebate2!=null)&&(bettingrebate2<=1)&&(bettingrebate2>=0)){
																			bettingrebate2=bettingrebate2-bettingrebate11;
																			if((bettingrebate2!=null)&&(bettingrebate2<=1)&&(bettingrebate2>=0)){
																				BetCommission betcommission2 =new BetCommission();
																				betcommission2.setAgentid(parentid2);
																				betcommission2.setCommission(bettingmoney*bettingrebate2);
																				betcommission2.setMemberid2(member.getId2());
																				betcommission2.setOrderid(scheme.getSchemeid().toString());
																				if(betcommission2.getCommission()!=0.){
																					//betCommissionService.save(betcommission2);
																					betCommissionService.update(new Finder("delete from bet_commission where agentid = :agentid and memberid2 = :memberid2 and substr(settlementtime,1,10) = :settlementtime and orderid =:orderid").setParam("agentid", betcommission.getAgentid()).setParam("memberid2", betcommission.getMemberid2()).setParam("settlementtime", settleDate).setParam("orderid", betcommission.getOrderid()));
																					betAgentService.update(new Finder("update bet_agent set score=score-:score,bettingty=bettingty-:score where agentid=:agentid ").setParam("agentid", parentid2).setParam("score", betcommission2.getCommission()));
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
										
									}
								}
								
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
							
							 //金币记录
						     BetScorerecord betScorerecord=new BetScorerecord();
						     String content="";
						     content="用户"+scheme.getMemberid2()+"撤销在篮球的已结算方案号为"+scheme.getSchemeid()+"的投注方案，投注额为"+scheme.getBettingmoney();
						     betScorerecord.setMemberid2(member.getId2());
						     betScorerecord.setTime(new Date());
						     betScorerecord.setContent(content);
						     betScorerecord.setOriginalscore(member.getScore() + scheme.getBettingwin().doubleValue() - scheme.getBettingmoney().doubleValue());
						     betScorerecord.setChangescore(scheme.getBettingmoney().doubleValue() -  scheme.getBettingwin().doubleValue());
						     betScorerecord.setBalance(member.getScore());
						     betScorerecord.setState(1);
						     betScorerecord.setType(7);
						     betScorerecordService.saveBetScorerecord(betScorerecord);
						     //操作日志
						   //操作日志
							 String details = "";
						     details = "撤销ID："+member.getId2()+"的用户在"+"篮球-"+scheme.getBettingmoney()+"分的投注方案";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details);;
						}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("无此用户");
						}
						*/
						//returnObject.setStatus(ReturnDatas.ERROR);
						//returnObject.setMessage("已结算方案无法撤消");
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此方案");
				}
			}else if("1".equals(request.getParameter("settle"))){
				String schemeid = request.getParameter("schemeid");
				BasketballScheme scheme = null;
				if(schemeid!=null){
					scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid", schemeid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
				}else{
					//scheme = basketballSchemeService.findBasketballSchemeById(basketballScheme.getId());
					scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id", basketballScheme.getId()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
				}
				
				if(scheme!=null){
					Integer memberid2 = scheme.getMemberid2();
					BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
					if(member==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("无此用户");
						return returnObject;
					}
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						String schemeid1=scheme.getSchemeid();
						List<BasketballOrder> orderList234 = basketballOrderService.queryForList(new Finder("select * from basketball_order where result=0 and state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), BasketballOrder.class);
						if(orderList234!=null){
							for (BasketballOrder basketballOrder : orderList234) {
								List<BasketballOrderContent>  contentList = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()), BasketballOrderContent.class);
								int count = 0 ;
								Boolean flag=false;
								if(contentList!=null){
									for (BasketballOrderContent basketballOrderContent : contentList) {
										if(basketballOrderContent.getResult()==1){
											count++;
										}
										if(basketballOrderContent.getResult()==3){
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
							for (BasketballOrder basketballOrder : orderList234) {

								List<BasketballOrderContent>  contentList = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()), BasketballOrderContent.class);
								int count = 0 ;
								if((contentList!=null)&&(!contentList.isEmpty())){
									for (BasketballOrderContent basketballOrderContent : contentList) {
										if(basketballOrderContent.getResult()==1){
											count++;
										}
									}
										
									if(contentList.size() == count){
										//中奖了 返奖
										Double odds = 1.;
										for (BasketballOrderContent basketballOrderContent : contentList) {
											odds*=basketballOrderContent.getOdds();
										}
										Double award = odds*(basketballOrder.getBettingmoney().doubleValue());
										Double posttaxprice=award;
										schemereward+=posttaxprice;
										basketballOrderService.update(new Finder("update basketball_order set result=1,settletime=now(),bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid ").setParam("award", posttaxprice).setParam("posttaxprize", award).setParam("orderid", basketballOrder.getOrderid()));
									}else{
										basketballOrderService.update(new Finder("update basketball_order set result=3,settletime=now(),bettingwin=0,posttaxprize=0 where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()));
									}
								}
							
							}
							
							BasketballScheme basketballScheme222 = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), BasketballScheme.class);
							BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", basketballScheme222.getMemberid2()), BetMember.class);
							try {
								if(basketballScheme222.getBuytype()==1){
									//跟单
									BasketballScheme sdbasketballsheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", basketballScheme222.getSchemeid2()), BasketballScheme.class);
									if(sdbasketballsheme.getPubstate()!=null&&sdbasketballsheme.getPubstate()==2){
										BigDecimal guarantee = sdbasketballsheme.getGuarantee();
										BigDecimal guaranteeMoney = null;
										if(guarantee!=null){
											guaranteeMoney = guarantee.multiply(basketballScheme222.getBettingmoney());
										}
										if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
											BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join basketball_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) limit 1 ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2",basketballScheme222.getSchemeid2() ), BetMember.class);
											
											BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
											if("A101".equals(betAgent.getParentid())){
												
											}else{
												betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
											}
											BigDecimal brokeragemember = sdbasketballsheme.getBrokeragemember(); // 大神佣金比例
											BigDecimal brokerageagent = sdbasketballsheme.getBrokerageagent(); // 一级代理佣金比例
											
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
											//agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
											BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
											//跟单方案大神佣金
											BigDecimal brokeragemoney = bettingwin.multiply(brokeragemember);
											basketballScheme222.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
											//跟单方案代理佣金
											BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
											basketballScheme222.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
											basketballScheme222.setBrokerageagentid(betAgent.getAgentid());
											
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
											basketballScheme222.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
											
											if(sdbasketballsheme.getBrokeragemoney()!=null){
												sdbasketballsheme.setBrokeragemoney(sdbasketballsheme.getBrokeragemoney().add(brokeragemoney));
											}else{
												sdbasketballsheme.setBrokeragemoney(brokeragemoney);
											}
											sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
											sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
											sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
											sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
											//sdmember.setFreezingscore(sdmember.getFreezingscore());
											//betMemberService.update(sdmember, true);
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
//															cached.deleteCached(("TICKET_"+ticket).getBytes());
														ObjectMapper mapper=new ObjectMapper();
														byte[] json = mapper.writeValueAsBytes(sdmember);
														cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
													}catch(Exception e){
														//String errorMessage = e.getLocalizedMessage();
														e.printStackTrace();
													}
													
												}
												Date dd=new Date();
												BetScorerecord scorerecord = new BetScorerecord();
												scorerecord.setId(null);
												scorerecord.setMemberid2(sdbasketballsheme.getMemberid2());
												scorerecord.setTime(dd);
												double f1 = new BigDecimal(schemereward).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
												double f2 = basketballScheme222.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
												scorerecord.setContent("用户"+betMember.getId2()+"投注的篮彩竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
												scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
												scorerecord.setChangescore(brokeragemoney.doubleValue());
												scorerecord.setBalance(sdmember.getScore());
												scorerecord.setState(1);
												scorerecord.setType(17);
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
												basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", basketballScheme222.getBrokeragemoney()).setParam("brokerageagentmoney", basketballScheme222.getBrokerageagentmoney()).setParam("brokeragemembermoney", basketballScheme222.getBrokeragemembermoney()).setParam("brokerageagentid", basketballScheme222.getBrokerageagentid()));
												basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdbasketballsheme.getSchemeid()).setParam("brokeragemoney", sdbasketballsheme.getBrokeragemoney()));
												
												//汇总投注
												//soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and state=0 and type=1 ").setParam("bettingscore", schemereward).setParam("settlementtime", dd).setParam("id", schemeid1));
												
											}
										}
									}
									
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
							String firstagentid=null;
							Double bbb=null;
							try {
								String parentids = betMember.getAgentparentids();
								String parentid=betMember.getAgentparentid();
								if("A101".equals(parentid)){
									bbb = basketballSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lq limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", betMember.getAgentid()),Double.class);
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
									bbb = basketballSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lq limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", firstagentid),Double.class);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							Double plusawards=0.;
							if(bbb!=null){
								plusawards=bbb*schemereward;
							}
							
							betMember.setBankscore(betMember.getBankscore()+schemereward);
							betMember.setGamescore(betMember.getGamescore() + plusawards);
							betMember.setScore(betMember.getScore() + schemereward +plusawards - basketballScheme222.getBettingmoney().doubleValue());
							betMember.setDayscore(betMember.getDayscore()  + schemereward +plusawards - basketballScheme222.getBettingmoney().doubleValue());
							betMember.setWinorfail(betMember.getWinorfail() + schemereward +plusawards - basketballScheme222.getBettingmoney().doubleValue());
							//betMember.setFreezingscore(betMember.getFreezingscore()-basketballScheme222.getBettingmoney().doubleValue());
							Integer updatenum=null;
							for(int i=0;i<10;i++){
								if(updatenum==null){
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("version", betMember.getVersion()));
								}else if(updatenum==0){
									betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
									betMember.setBankscore(betMember.getBankscore()+schemereward);
									betMember.setGamescore(betMember.getGamescore() + plusawards);
									betMember.setScore(betMember.getScore() + schemereward +plusawards - basketballScheme222.getBettingmoney().doubleValue());
									betMember.setDayscore(betMember.getDayscore()  + schemereward +plusawards - basketballScheme222.getBettingmoney().doubleValue());
									betMember.setWinorfail(betMember.getWinorfail() + schemereward +plusawards - basketballScheme222.getBettingmoney().doubleValue());
									//betMember.setFreezingscore(betMember.getFreezingscore()-basketballScheme222.getBettingmoney().doubleValue());
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("version", betMember.getVersion()));
									
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
										String errorMessage = e.getLocalizedMessage();
										System.out.println(errorMessage);
										e.printStackTrace();
									}
									
								}
								Date dd=new Date();
								try {
									BetScorerecord scorerecord = new BetScorerecord();
									scorerecord.setId(null);
									scorerecord.setMemberid2(basketballScheme222.getMemberid2());
									scorerecord.setTime(dd);
									double f1 = new BigDecimal(schemereward+plusawards).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									double f2 = basketballScheme222.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									scorerecord.setContent("篮彩竞猜方案号为"+schemeid1+"的投注方案押"+f2+"返奖"+f1+"元");
									scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + basketballScheme222.getBettingmoney().doubleValue());
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
									scorerecord.setType(16);
									scorerecord.setAgentid(betMember.getAgentid());
									scorerecord.setAgentparentid(betMember.getAgentparentid());
									scorerecord.setAgentparentids(betMember.getAgentparentids());
									betScorerecordService.save(scorerecord);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								//投注方案
								basketballSchemeService.update(new Finder("update basketball_scheme set plusawards=:plusawards,bettingwin=:xxxxk, situation=1,settlementtime=:settlementtime,pretaxamount=:xxxx WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("xxxxk", schemereward+plusawards).setParam("xxxx", schemereward));
									//汇总投注
								soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and state=0 and type=3 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
								//代理退佣
								try {
									String agentid = betMember.getAgentid();
									double bettingmoney = (basketballScheme222.getBettingmoney()).doubleValue();
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
			}else if("1".equals(request.getParameter("recalculate"))){
				//重新结算
				String schemeid = request.getParameter("schemeid");
				BasketballScheme scheme = null;
				if(schemeid!=null){
					scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid", schemeid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
				}else{
					//scheme = basketballSchemeService.findBasketballSchemeById(basketballScheme.getId());
					scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id", basketballScheme.getId()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
				}
				
				if(scheme!=null&&scheme.getSituation()==1){
					BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id2", scheme.getMemberid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BetMember.class);
					betMember.setBankscore(betMember.getBankscore()-scheme.getBettingwin().doubleValue()+scheme.getPlusawards().doubleValue());
					betMember.setGamescore(betMember.getGamescore()-scheme.getPlusawards().doubleValue());
					betMember.setScore(betMember.getScore() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
					
					if(scheme.getBuytype()==2){
						if(scheme.getBrokeragemoney()!=null){
							betMember.setBankscore(betMember.getBankscore()-scheme.getBrokeragemoney().doubleValue());
							betMember.setScore(betMember.getScore()-scheme.getBrokeragemoney().doubleValue());
						}
						basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = 0 where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()));
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
					scorerecord.setType(25);			
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
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("此方案信息有误");
				}	
				//重新结算
				List<String> zids = basketballSchemeMatchService.queryForList(new Finder("select zid from basketball_scheme_match where schemeid = :schemeid group by schemeid").setParam("schemeid", scheme.getSchemeid()), String.class);
				List<BasketballLeagueResult> datas=basketballLeagueResultService.queryForList(new Finder("select * from basketball_league_result  where  state = 1 and zid in (:zid) ").setParam("zid", zids), BasketballLeagueResult.class);
				
				if(datas!=null&&!datas.isEmpty()){
					for(BasketballLeagueResult basketballResult : datas){
						String sfresult = null;
						String allscore[] = basketballResult.getScore().split(":");
						if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
							basketballResult.setSf("sfzf");
							sfresult="主负";
						}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])){
							basketballResult.setSf("sfzs");
							sfresult="主胜";
						}
						
						basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:sfresult where zid = :zid and oddsname in(:s,:f) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfresult", sfresult).setParam("zid", basketballResult.getZid()).setParam("s", "sfzs").setParam("f", "sfzf").setParam("resultname", basketballResult.getSf()));
						basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:sfresult where zid = :zid and oddsname in(:s,:f) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfresult", sfresult).setParam("zid", basketballResult.getZid()).setParam("s", "sfzs").setParam("f", "sfzf").setParam("resultname", basketballResult.getSf()));
						
						
						//让分胜负
						List<BasketballOrderContent> rfdatas = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where zid = :zid and oddsname in(:rs,:rf) and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("zid", basketballResult.getZid()).setParam("rs", "rfzs").setParam("rf", "rfzf"), BasketballOrderContent.class);
						if(rfdatas!=null&&!rfdatas.isEmpty()){
							for(BasketballOrderContent rfOrderContent : rfdatas){
								Double letpoints = null;
								if(rfOrderContent.getBase()!=null){
									letpoints = rfOrderContent.getBase().doubleValue();
								}
								
								if(letpoints==null){
									letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid").setParam("zid", basketballResult.getZid()), Double.class);
									if(letpoints==null){
										try {
											letpoints =  Double.valueOf(basketballResult.getLetpoints());
										} catch (Exception e) {
											System.out.println("job32取篮球让球数异常");
//											e.printStackTrace();
										}
										
									}
								}
								
								String rfsfresult = null;
								if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])+letpoints){
									basketballResult.setRfsf("rfzf");
									rfsfresult = "让分主负";
								}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])+letpoints){
									basketballResult.setRfsf("rfzs");
									rfsfresult = "让分主胜";
								}
								
								if(rfOrderContent.getOddsname().equals(basketballResult.getRfsf())){
									basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:rfsfresult where id = :id and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("rfsfresult", rfsfresult).setParam("id", rfOrderContent.getId()));
								}else{
									basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:rfsfresult where id = :id  and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("rfsfresult", rfsfresult).setParam("id", rfOrderContent.getId()));
								}
								
							}
						}
						
						
						//大小分
						List<BasketballOrderContent> dxfdatas = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where zid = :zid and oddsname in(:b,:s) and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("zid", basketballResult.getZid()).setParam("b", "big").setParam("s", "small"), BasketballOrderContent.class);
						if(dxfdatas!=null&&!dxfdatas.isEmpty()){
							for(BasketballOrderContent dxfOrderContent : dxfdatas){
								String dxfresult = null;
								int allf = Integer.valueOf(allscore[0])+Integer.valueOf(allscore[1]);
								Double dxfys = null;
								if(dxfOrderContent.getBase()!=null){
									dxfys = dxfOrderContent.getBase().doubleValue();
								}
								if(dxfys==null){
									dxfys = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid").setParam("zid", basketballResult.getZid()), Double.class);
								}
								if(dxfys==null){
									try {
										dxfys =  Double.valueOf(basketballResult.getDxf());
									} catch (Exception e) {
										System.out.println("job32取篮球大小分异常");
//										e.printStackTrace();
									}
									
								}
								if(allf>dxfys){
									basketballResult.setDxf("big");
									dxfresult = "大";
								}else{
									basketballResult.setDxf("small");
									dxfresult = "小";
								}
								
								if(dxfOrderContent.getOddsname().equals(basketballResult.getDxf())){
									basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:dxfresult where  id = :id and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("dxfresult", dxfresult).setParam("id", dxfOrderContent.getId()));
								}else{
									basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:dxfresult where  id = :id and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("dxfresult", dxfresult).setParam("id", dxfOrderContent.getId()));
								}
								
								
								
							}
						}
							
						
						String sfcresult = null;
						if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
							int fc = Integer.valueOf(allscore[0]) - Integer.valueOf(allscore[1]);
							if(fc>=1&&fc<=5){
								basketballResult.setSfc("ksfc1t5");
								sfcresult = "客胜1-5";
							}else if(fc>=6&&fc<=10){
								basketballResult.setSfc("ksfc6t10");
								sfcresult = "客胜6-10";
							}else if(fc>=11&&fc<=15){
								basketballResult.setSfc("ksfc11t15");
								sfcresult = "客胜11-15";
							}else if(fc>=16&&fc<=20){
								basketballResult.setSfc("ksfc16t20");
								sfcresult = "客胜16-20";
							}else if(fc>=21&&fc<=25){
								basketballResult.setSfc("ksfc21t25");
								sfcresult = "客胜21-25";
							}else if(fc>=26){
								basketballResult.setSfc("ksfc26");
								sfcresult = "客胜26+";
							}
						}else{
							int fc =  Integer.valueOf(allscore[1]) -  Integer.valueOf(allscore[0]);
							if(fc>=1&&fc<=5){
								basketballResult.setSfc("zsfc1t5");
								sfcresult = "主胜1-5";
							}else if(fc>=6&&fc<=10){
								basketballResult.setSfc("zsfc6t10");
								sfcresult = "主胜6-10";
							}else if(fc>=11&&fc<=15){
								basketballResult.setSfc("zsfc11t15");
								sfcresult = "主胜11-15";
							}else if(fc>=16&&fc<=20){
								basketballResult.setSfc("zsfc16t20");
								sfcresult = "主胜16-20";
							}else if(fc>=21&&fc<=25){
								basketballResult.setSfc("zsfc21t25");
								sfcresult = "主胜21-25";
							}else if(fc>=26){
								basketballResult.setSfc("zsfc26");
								sfcresult = "主胜26+";
							}
						}
						
						basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:sfcresult where zid = :zid and left(oddsname, 4) in(:zsfc,:ksfc) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfcresult", sfcresult).setParam("zid", basketballResult.getZid()).setParam("zsfc", "zsfc").setParam("ksfc", "ksfc").setParam("resultname", basketballResult.getSfc()));
						basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:sfcresult where zid = :zid and left(oddsname, 4) in(:zsfc,:ksfc) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfcresult", sfcresult).setParam("zid", basketballResult.getZid()).setParam("zsfc", "zsfc").setParam("ksfc", "ksfc").setParam("resultname", basketballResult.getSfc()));
						
						
						basketballLeagueResultService.update(new Finder("update basketball_league_result set issettle=1 where zid = :zid").setParam("zid", basketballResult.getZid()));
					}
				}
				
				//篮球重新返奖
				List<String> schemelist = basketballSchemeService.queryForList(new Finder("select schemeid from basketball_scheme where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()), String.class);
				if(schemelist!=null){
					for (String schemeid1 : schemelist) {
						Integer untreatedcontent = basketballOrderService.queryForObject(new Finder("select count(*) from basketball_order a right join basketball_order_content b on b.orderid=a.orderid where a.schemeid=:schemeid and b.result=0 ").setParam("schemeid", schemeid1), Integer.class);
						if(untreatedcontent==0){
							try{
								List<BasketballOrder> orderList = basketballOrderService.queryForList(new Finder("select * from basketball_order where state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), BasketballOrder.class);
								if((orderList !=null)&&(!orderList.isEmpty())){
									Double schemereward=0.;
									for (BasketballOrder basketballOrder : orderList) {
										List<BasketballOrderContent>  contentList = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()), BasketballOrderContent.class);
										int count = 0 ;
										if((contentList!=null)&&(!contentList.isEmpty())){
											for (BasketballOrderContent basketballOrderContent : contentList) {
												if(basketballOrderContent.getResult()==1){
													count++;
												}
											}
												
											if(contentList.size() == count){
												//中奖了 返奖
												Double odds = 1.;
												for (BasketballOrderContent basketballOrderContent : contentList) {
													odds*=basketballOrderContent.getOdds();
												}
												Double award = odds*(basketballOrder.getBettingmoney().doubleValue());
												Double posttaxprice=award;
												schemereward+=posttaxprice;
												basketballOrderService.update(new Finder("update basketball_order set result=1,settletime=now(),bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid ").setParam("award", posttaxprice).setParam("posttaxprize", award).setParam("orderid", basketballOrder.getOrderid()));
												
											}else{
												basketballOrderService.update(new Finder("update basketball_order set result=3,settletime=now(),bettingwin=0,posttaxprize=0 where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()));
											}
										}
									}
									
									BasketballScheme basketballScheme2 = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), BasketballScheme.class);
									BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id2", basketballScheme2.getMemberid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BetMember.class);
									
									
									try {
										if(basketballScheme2.getBuytype()==1){
											//跟单
											BasketballScheme sdbasketballsheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", basketballScheme2.getSchemeid2()), BasketballScheme.class);
											if(sdbasketballsheme.getPubstate()!=null&&sdbasketballsheme.getPubstate()==2){
												BigDecimal guarantee = sdbasketballsheme.getGuarantee();
												BigDecimal guaranteeMoney = null;
												if(guarantee!=null){
													guaranteeMoney = guarantee.multiply(basketballScheme2.getBettingmoney());
												}
												if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
													BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join basketball_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 limit 1").setParam("schemeid2",basketballScheme2.getSchemeid2() ), BetMember.class);
													
													BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
													if("A101".equals(betAgent.getParentid())){
														
													}else{
														betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
													}
													
													BigDecimal brokeragemember = sdbasketballsheme.getBrokeragemember(); // 大神佣金比例
													BigDecimal brokerageagent = sdbasketballsheme.getBrokerageagent(); // 一级代理佣金比例
													
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
													basketballScheme2.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
													//跟单方案代理佣金
													BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
													basketballScheme2.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
													basketballScheme2.setBrokerageagentid(betAgent.getAgentid());
													
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
													basketballScheme2.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
													
													if(sdbasketballsheme.getBrokeragemoney()!=null){
														sdbasketballsheme.setBrokeragemoney(sdbasketballsheme.getBrokeragemoney().add(brokeragemoney));
													}else{
														sdbasketballsheme.setBrokeragemoney(brokeragemoney);
													}
//													sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
													sdmember.setBankscore(sdmember.getBankscore() + brokeragemoney.doubleValue());
													sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
													sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
													sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
													//sdmember.setFreezingscore(sdmember.getFreezingscore());
													//betMemberService.update(sdmember, true);
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
			//														cached.deleteCached(("TICKET_"+ticket).getBytes());
																ObjectMapper mapper=new ObjectMapper();
																byte[] json = mapper.writeValueAsBytes(sdmember);
																cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
															}catch(Exception e){
//																String errorMessage = e.getLocalizedMessage();
//																e.printStackTrace();
																System.out.println("QuartzJob32有异常");
															}
															
														}
														Date dd=new Date();
														BetScorerecord scorerecord = new BetScorerecord();
														scorerecord.setId(null);
														scorerecord.setMemberid2(sdbasketballsheme.getMemberid2());
														scorerecord.setTime(dd);
														//double f1 = new BigDecimal(schemereward).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														//double f2 = basketballScheme2.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
														//scorerecord.setContent("用户"+betMember.getId2()+"投注的篮彩竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
														scorerecord.setContent("重新结算篮彩佣金");
														scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
														scorerecord.setChangescore(brokeragemoney.doubleValue());
														scorerecord.setBalance(sdmember.getScore());
														scorerecord.setState(1);
														scorerecord.setType(17);
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
//														double gsq = new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//													    double gsh = new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//													    double bs = new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//													    double dsq = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//													    double dsh = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//													    scorerecord.setRemark("gq:"+gsq+"gh"+gsh+",b:"+bs+",dq:"+dsq+",dh:"+dsh);
														betScorerecordService.save(scorerecord);
														
														//投注方案
														basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", basketballScheme2.getBrokeragemoney()).setParam("brokerageagentmoney", basketballScheme2.getBrokerageagentmoney()).setParam("brokeragemembermoney", basketballScheme2.getBrokeragemembermoney()).setParam("brokerageagentid", basketballScheme2.getBrokerageagentid()));
														basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdbasketballsheme.getSchemeid()).setParam("brokeragemoney", sdbasketballsheme.getBrokeragemoney()));
														
														//连红========================================
														if(basketballScheme2.getBuytype()==2){
															List<Double> hong = soccerAllbettingService.queryForList(new Finder("select bettingscore from soccer_allbetting" +
																	" where memberid2=:id2 and state=1 and buytype=2 order by bettingtime desc limit 30 ").setParam("id2", basketballScheme2.getMemberid2()), Double.class);
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
															betMemberService.update(new Finder("update bet_member set lianhong =:lianhong where id2=:id2 ").setParam("lianhong", lianhong).setParam("id2", basketballScheme2.getMemberid2()));
				 										}
														//连红结束======================================
													}
												}
											}
											
										}
									} catch (Exception e) {
//										e.printStackTrace();
										System.out.println("QuartzJob32有异常");
									}
									
									String firstagentid=null;
									Double bbb=null;
									try {
										String parentids = betMember.getAgentparentids();
										String parentid=betMember.getAgentparentid();
										if("A101".equals(parentid)){
											bbb = basketballSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lq limit 1").setParam("lq", "lq").setParam("agentid", betMember.getAgentid()),Double.class);
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
											bbb = basketballSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lq limit 1").setParam("lq", "lq").setParam("agentid", firstagentid),Double.class);
										}
									} catch (Exception e) {
//										e.printStackTrace();
										System.out.println("QuartzJob32有异常");
									}
									
									Double plusawards=0.;
									if(bbb!=null){
										plusawards=bbb*schemereward;
									}
									
									betMember.setBankscore(betMember.getBankscore()+schemereward);
									betMember.setGamescore(betMember.getGamescore() + plusawards);
									betMember.setScore(betMember.getScore() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
									betMember.setDayscore(betMember.getDayscore()  + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
									betMember.setWinorfail(betMember.getWinorfail() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
									//betMember.setFreezingscore(betMember.getFreezingscore()-basketballScheme2.getBettingmoney().doubleValue());
									Integer updatenum=null;
									for(int i=0;i<10;i++){
										if(updatenum==null){
											updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("version", betMember.getVersion()));
										}else if(updatenum==0){
											betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
											betMember.setBankscore(betMember.getBankscore()+schemereward);
											betMember.setGamescore(betMember.getGamescore() + plusawards);
											betMember.setScore(betMember.getScore() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
											betMember.setDayscore(betMember.getDayscore()  + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
											betMember.setWinorfail(betMember.getWinorfail() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
											//betMember.setFreezingscore(betMember.getFreezingscore()-basketballScheme2.getBettingmoney().doubleValue());
											updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("version", betMember.getVersion()));
											
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
//												String errorMessage = e.getLocalizedMessage();
//												System.out.println(errorMessage);
//												e.printStackTrace();
												System.out.println("QuartzJob32有异常");
											}
											
										}
										Date dd=new Date();
										try {
											BetScorerecord scorerecord = new BetScorerecord();
											scorerecord.setId(null);
											scorerecord.setMemberid2(basketballScheme2.getMemberid2());
											scorerecord.setTime(dd);
											//double f1 = new BigDecimal(schemereward+plusawards).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
											//double f2 = basketballScheme2.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
											//scorerecord.setContent("篮彩竞猜方案号为"+schemeid1+"的投注方案押"+f2+"返奖"+f1+"元");
											scorerecord.setContent("重新结算篮彩返奖");
											scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + basketballScheme2.getBettingmoney().doubleValue());
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
											scorerecord.setType(16);
											scorerecord.setAgentid(betMember.getAgentid());
											scorerecord.setAgentparentid(betMember.getAgentparentid());
											scorerecord.setAgentparentids(betMember.getAgentparentids());
											betScorerecordService.save(scorerecord);
										} catch (Exception e) {
											System.out.println("QuartzJob32有异常");
										}
										
										//投注方案
										basketballSchemeService.update(new Finder("update basketball_scheme set plusawards=:plusawards,bettingwin=:xxxxk, situation=1,settlementtime=:settlementtime,pretaxamount=:xxxx WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("xxxxk", schemereward+plusawards).setParam("xxxx", schemereward));
											//汇总投注
										soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id  and type=3 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
										
									}
								}
							} catch (Exception e) {
								System.out.println("QuartzJob32有异常");
							}
						}else{
							continue;
						}
					}
				}
				
			}
			else{
				//basketballSchemeService.saveorupdate(basketballScheme);
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
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			String  zid = request.getParameter("zid");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			BasketballLeagueResult result =  basketballLeagueResultService.queryForObject(new Finder("select b.score,a.matchdate,a.num,a.zid,a.matchname,a.starttime,a.hometeam,a.awayteam,a.hometeamid2,a.awayteamid2 from basketball_league_arrange a left join basketball_league_result b on a.zid = b.zid where a.zid = :zid").setParam("zid", zid), BasketballLeagueResult.class);
			
			String allscore1 = "";
			String allscore2 = "";
			String allscore = result.getScore();
			if(allscore!=null){
				String[] allscores = allscore.split(":");
				allscore1 = allscores[0];
				allscore2 = allscores[1];
			}
			
			result.setNum(basketballWeekOfDate.getWeekOfDate(result.getMatchdate())+result.getNum());
			//result.setCreatedate(new Date());
			result.setState(1);
			result.setIssettle(3);
			returnObject.setData(result);
			model.addAttribute("allscore1", allscore1);
			model.addAttribute("allscore2", allscore2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/basketballscheme/basketballresultAdd";
		}else if("2".equals(request.getParameter("k"))){
			String schemeid = request.getParameter("schemeid");
			
			BasketballScheme scheme = null;
			if(schemeid!=null){
				scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid", schemeid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BasketballScheme.class);
			}
			
			if(scheme==null){
				model.addAttribute("exception", "无此方案");
				return "/errorpage/error";
			}
			
			String zid = request.getParameter("zid");
			String oddsname = request.getParameter("oddsname");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			scheme = basketballSchemeService.queryForObject(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where a.schemeid = :schemeid").setParam("schemeid", schemeid),BasketballScheme.class);
			BasketballSchemeMatch match= basketballSchemeMatchService.queryForObject(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid=:schemeid and b.zid = :zid order by a.id").setParam("schemeid", schemeid).setParam("zid", zid), BasketballSchemeMatch.class);
			BasketballOrderContent orderContent = basketballOrderContentService.queryForObject(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid=:schemeid and a.oddsname=:oddsname and a.zid = :zid group by c.schemeid, a.zid,a.oddsname").setParam("schemeid", schemeid).setParam("oddsname", oddsname).setParam("zid", zid),BasketballOrderContent.class);
				
			if(orderContent!=null){
					//String oddsrealname = "";
					if("big".equals(oddsname)||"small".equals(oddsname)){
				    	try{
				    		String cached2 = (String)cached.getCached(("basketballOdds_"+orderContent.getZid().toString()).getBytes());
					    	if(cached2!=null){
					    		ObjectMapper mmmm=new ObjectMapper();
					    		BasketballLeagueOdds readValue = mmmm.readValue(cached2, BasketballLeagueOdds.class);
					    		String dxf = readValue.getDxf().toString();
					    		String betname = orderContent.getBetname().toString();
					    		orderContent.setBetname(betname+"("+ dxf+")");
					    	}else{
					    		String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", orderContent.getZid().toString()), String.class);
						    	String betname = orderContent.getBetname().toString();
						    	orderContent.setBetname(betname+"("+ dxf+")");
					    	}
				    	}catch (Exception e) {
							e.printStackTrace();
							String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", orderContent.getZid().toString()), String.class);
					    	String betname = orderContent.getBetname().toString();
					    	orderContent.setBetname(betname+"("+ dxf+")");
						}
				    }
					
					if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
				    	try{
				    		String cached2 = (String)cached.getCached(("basketballOdds_"+orderContent.getZid().toString()).getBytes());
					    	if(cached2!=null){
					    		ObjectMapper mmmm=new ObjectMapper();
					    		BasketballLeagueOdds readValue = mmmm.readValue(cached2, BasketballLeagueOdds.class);
					    		String letpoints = readValue.getLetpoints().toString();
					    		String betname = orderContent.getBetname().toString();
					    		orderContent.setBetname(betname+"("+ letpoints+")");
					    	}else{
					    		String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", orderContent.getZid().toString()), String.class);
						    	String betname = orderContent.getBetname().toString();
						    	orderContent.setBetname(betname+"("+ letpoints+")");
						
					    	}
				    	}catch (Exception e) {
							e.printStackTrace();
							String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", orderContent.getZid().toString()), String.class);
					    	String betname = orderContent.getBetname().toString();
					    	orderContent.setBetname(betname+"("+ letpoints+")");
					  
						}
				    }
					
				    
			}
			match.setNum(basketballWeekOfDate.getWeekOfDate(match.getMatchdate())+match.getNum());
			returnObject.setData(scheme);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("match", match);
			model.addAttribute("orderContent", orderContent);
			return "/lottery/basketballscheme/basketballschemeoddmodify";
		}else{
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/basketballscheme/basketballschemeCru";
		}
		
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				basketballSchemeService.deleteById(id,BasketballScheme.class);
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
			basketballSchemeService.deleteByIds(ids,BasketballScheme.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	void bettingmoneyagentrebate(BetAgent betaggg,double bettingmoney,BetMember betmember11,String orderid,Date settlementtime,Double bettingrebate1){
		try {
			if("A101".equals(betaggg.getAgentid())){
				return;
			}else{
//				BetAgent betaggg = betAgentService.queryForObject(new Finder("select bettingrebate,parentid from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
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
			e.printStackTrace();
		}
		
	}
	
	

}
