package  org.springrain.lottery.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.entity.BasketballOrder;
import org.springrain.lottery.entity.BasketballOrderContent;
import org.springrain.lottery.entity.BasketballScheme;
import org.springrain.lottery.entity.BasketballSchemeMatch;
import org.springrain.lottery.entity.BetBetting;
import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.lottery.entity.BjdcOrderContent;
import org.springrain.lottery.entity.BjdcScheme;
import org.springrain.lottery.entity.BjdcSchemeMatch;
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.LotteryScheme;
import org.springrain.lottery.entity.RenjiuScheme;
import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.lottery.entity.SoccerLeagueResult;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.service.IBasketballOrderContentService;
import org.springrain.lottery.service.IBasketballOrderService;
import org.springrain.lottery.service.IBasketballSchemeMatchService;
import org.springrain.lottery.service.IBasketballSchemeService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBjdcOddsService;
import org.springrain.lottery.service.IBjdcOrderContentService;
import org.springrain.lottery.service.IBjdcOrderService;
import org.springrain.lottery.service.IBjdcSchemeMatchService;
import org.springrain.lottery.service.IBjdcSchemeService;
import org.springrain.lottery.service.ILotteryOrderService;
import org.springrain.lottery.service.ILotterySchemeService;
import org.springrain.lottery.service.IRenjiuSchemeService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerLeagueOrderContentService;
import org.springrain.lottery.service.ISoccerLeagueOrderService;
import org.springrain.lottery.service.ISoccerSchemeMatchService;
import org.springrain.lottery.service.ISoccerSchemeService;
import org.springrain.lottery.utils.WeekOfDate;
import org.springrain.lottery.utils.basketballWeekOfDate;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-14 16:24:25
 * @see org.springrain.lottery.web.SoccerAllbetting
 */
@Controller
@RequestMapping(value="/soccerallbetting")
public class SoccerAllbettingController  extends BaseController {
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private ISoccerSchemeMatchService soccerSchemeMatchService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private IBasketballOrderService basketballOrderService;
	@Resource
	private IBasketballOrderContentService basketballOrderContentService;
	@Resource
	private ISoccerLeagueOrderContentService soccerLeagueOrderContentService;
	@Resource
	private ISoccerLeagueOrderService soccerLeagueOrderService;
	@Resource
	private ICached cached;
	@Resource
	private ISoccerSchemeService soccerSchemeService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBasketballSchemeService basketballSchemeService;
	@Resource
	private IBasketballSchemeMatchService basketballSchemeMatchService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	@Resource
	private IBjdcOddsService bjdcOddsService;
	@Resource
	private IBjdcSchemeService bjdcSchemeService;
	@Resource
	private IBjdcSchemeMatchService bjdcSchemeMatchService;
	@Resource
	private IBjdcOrderService bjdcOrderService;
	@Resource
	private IBjdcOrderContentService bjdcOrderContentService;
	@Resource
	private ILotterySchemeService lotterySchemeService;
	@Resource
	private ILotteryOrderService lotteryOrderService;
	@Resource
	private IRenjiuSchemeService renjiuSchemeService;
	private String listurl="/lottery/soccerallbetting/soccerallbettingList";
	
	@RequestMapping("/currentbetting")
	public String currentbetting(HttpServletRequest request, Model model,SoccerAllbetting soccerAllbetting) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//足球方案详情
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
			return  "/lottery/soccerscheme/soccerschemeorderListagent";
		
		}else if("2".equals(request.getParameter("k"))){
			//足球订单详情
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
			return  "/lottery/soccerleagueorder/soccerleagueordercontentagent";
		
		}else if("3".equals(request.getParameter("k"))){
			//篮球方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BasketballOrder> datas= basketballOrderService.queryForList(new Finder("select a.*,c.name as playmethod from basketball_order a LEFT JOIN basketball_scheme b on a.schemeid = b.schemeid LEFT JOIN basketball_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BasketballOrder.class,page);
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
			return  "/lottery/basketballorder/basketballorderListagent";
		
		}else if("4".equals(request.getParameter("k"))){
			//北单方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BjdcOrder> datas= bjdcOrderService.queryForList(new Finder("select a.*,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BjdcOrder.class,page);
			if(datas!=null){
				for(BjdcOrder bjdcOrder : datas){
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", bjdcOrder.getOrderid()),BjdcOrderContent.class);
					bjdcOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BasketballOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorder/bjdcorderListagent";
		}else if("5".equals(request.getParameter("k"))){
			//大乐透方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<LotteryOrder> datas= lotteryOrderService.queryForList(new Finder("select a.*,c.name as playmethod from lottery_order a LEFT JOIN lottery_scheme b on a.schemeid = b.schemeid LEFT JOIN lottery_playmethod c on a.playtype = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),LotteryOrder.class,page);

			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BasketballOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/lotteryorder/lotteryorderListagent";
		}else{
			String agentid = SessionUser.getShiroUser().getAgentid();
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("bettingtime");
			page.setSort("desc");
			page.setPageSize(10);
			String type = request.getParameter("type");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			Date date1 =DateUtils.convertString2Date(endtime);
			Calendar calendar = new GregorianCalendar();
			if(date1!=null){
				calendar.setTime(date1); 
				calendar.add(Calendar.DATE,1);
				Date date3=calendar.getTime();
				endtime = DateUtils.convertDate2String(date3);
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			
			
			if(type==null){
				type = "1";
			}
			if("0".equals(type)){
				List<BetBetting> betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2,c.id2 as memberid2,c.nickname as membernickname,d.nickname as agentnickname from bet_betting a left join bet_gameplay b on a.gameplayid=b.id left join bet_member c on c.id=a.memberid left join bet_agent d on d.agentid=a.agentid where a.state=0 and c.isissue=1 and c.isinternal=0  and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BetBetting.class,page);
				returnObject1.setData(betbettinglist);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/betbettingList4";
			}else if("1".equals(type)){
				List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where a.situation=0 and c.isissue=1 and c.isinternal=0  and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerScheme.class,page);
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
						List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid").setParam("schemeid", schemeids));
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/soccerbettingList4";
			}else if("4".equals(type)){
				List<BjdcScheme> datas= bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where c.isissue=1 and c.isinternal=0 and a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BjdcScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (BjdcScheme soccerScheme2 : datas) {
						String schemeid = soccerScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(schemeid);
						}
					}
					List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
								    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
									    	String betname = m.get("betname").toString();
									    	m.put("betname",betname+"("+ letpoints+")");
								    	}
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
							schemeMatch.setNum(schemeMatch.getNum());
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/bjdcbettingList4";
			}else if("3".equals(type)){
				//篮球
				List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 left join bet_agent d on a.agentid=d.agentid  where c.isissue=1 and c.isinternal=0 and  a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime),BasketballScheme.class,page);
				
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (BasketballScheme basketballScheme2 : datas) {
						String schemeid = basketballScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(basketballScheme2.getSchemeid());
						}
					}
					
					List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
						if(resultMap!=null){
							for (Map<String, Object> m : resultMap){
								String oddsname  = m.get("oddsname").toString();
							    
								if("big".equals(oddsname)||"small".equals(oddsname)){
							    	try{
							    		
							    		String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	/*
										String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	*/
									}
							    }
								
							    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
							    	try{
							    		
							    		String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
										/*
										String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
								    	*/
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
				
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/basketballbettingList4";
			}else if("5".equals(type)){
				//大乐透
				List<LotteryScheme> datas = lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,d.nickname as agentnickname from lottery_scheme a left join bet_member c on c.id2=a.memberid2 left join bet_agent d on a.agentid=d.agentid  where c.isissue=1 and c.isinternal=0 and  a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid)").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime),LotteryScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (LotteryScheme lotteryScheme2 : datas) {
						String schemeid = lotteryScheme2.getSchemeid();
							if(schemeid!=null){
								schemeids.add(lotteryScheme2.getSchemeid());
						}
					}
					List<LotteryOrder> orderDatas=null;
					if(!schemeids.isEmpty()){
						orderDatas= lotteryOrderService.queryForList(new Finder("select a.*,b.name as playmethod from lottery_order a LEFT JOIN lottery_playmethod b on a.playtype = b.id where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), LotteryOrder.class);
					}
					for(LotteryScheme scheme : datas){
						List<LotteryOrder> sss=new ArrayList<LotteryOrder>();
						if(orderDatas!=null){
							for(LotteryOrder schemeContent : orderDatas){
								String schemeid = schemeContent.getSchemeid();
								if(schemeid.equals(scheme.getSchemeid())){
									sss.add(schemeContent);
								}
							}
						}
						scheme.setSchemecontent(sss);
					}
				}
				
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/lotterybettingList4";
			}else if("6".equals(type)){
				//任九
				List<RenjiuScheme> datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,d.nickname as agentnickname from renjiu_scheme a left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where a.situation=0 and c.isissue=1 and c.isinternal=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) ").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime),RenjiuScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (RenjiuScheme renjiuScheme2 : datas) {
						String schemeid = renjiuScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(renjiuScheme2.getSchemeid());
						}
					}
				}
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/renjiubettingList4";	
			}else{
				List<SoccerAllbetting> datas = soccerAllbettingService.queryForList(new Finder("select a.*,b.nickname as membernickname,c.nickname as agentnickname  from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 left join bet_agent c on a.agentid=c.agentid where c.isissue=1 and c.isinternal=0 and  a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (b.agentid=:agentid or b.agentparentids like :aid)  and a.type=:type").setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),SoccerAllbetting.class,page); 
				if("1".equals(type)){
					if(datas!=null){
						List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					List<SoccerSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					}
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
					
					for(SoccerAllbetting allBetting : datas){
						List<SoccerSchemeMatch> sss=new ArrayList<SoccerSchemeMatch>();
						if(matchDatas!=null){
							for(SoccerSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setSchemecontent(sss);
					}
					
				}
			}else if("4".equals(type)){
				
				//北单
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
						for (SoccerAllbetting allBetting : datas) {
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					List<BjdcSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					}
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
								    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
									    	String betname = m.get("betname").toString();
									    	m.put("betname",betname+"("+ letpoints+")");
								    	}
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
							schemeMatch.setNum(schemeMatch.getNum());
						}
					}
					
					for(SoccerAllbetting allBetting : datas){
						List<BjdcSchemeMatch> sss=new ArrayList<BjdcSchemeMatch>();
						if(matchDatas!=null){
							for(BjdcSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setBjdcschemecontent(sss);
					}
				}
			}else if("3".equals(type)){
				//篮球处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
						}
					}
					List<BasketballSchemeMatch> matchDatas=null;
					if(!schemeids.isEmpty()){
						matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					}
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
						if(resultMap!=null){
							for (Map<String, Object> m : resultMap){
								String oddsname  = m.get("oddsname").toString();
								if("big".equals(oddsname)||"small".equals(oddsname)){
							    	try{
							    		String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	
									}
							    }
								
							    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
							    	try{
							    		
							    		String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
								    	/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = m.get("base").toString();
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
					
					for(SoccerAllbetting allBetting : datas){
						List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
						if(matchDatas!=null){
							for(BasketballSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setBasketballschemecontent(sss);
					}
					
				}
			}else if("5".equals(type)){
				//大乐透处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
						}
					}
					List<LotteryOrder> orderDatas=null;
					if(!schemeids.isEmpty()){
						orderDatas= lotteryOrderService.queryForList(new Finder("select a.*,b.name as playmethod from lottery_order LEFT JOIN lottery_playmethod b on a.playtype = b.id where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), LotteryOrder.class);
					}
					for(SoccerAllbetting allBetting : datas){
						List<LotteryOrder> sss=new ArrayList<LotteryOrder>();
						if(orderDatas!=null){
							for(LotteryOrder schemeContent : orderDatas){
								String schemeid = schemeContent .getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeContent);
								}
							}
						}
						allBetting.setLotteryschemecontent(sss);
					}
				} 
			}else if("6".equals(type)){
				//任九处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						String schemeid = allBetting.getId();
						if(schemeid!=null){
							schemeids.add(schemeid);
						}
					}
				}
			}	
				returnObject1.setQueryBean(new SoccerAllbetting());
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/allsoccerbettingList4";
			}
		}
		
	}
	
	
	@RequestMapping("/currentbettingMax")
	public String currentbettingMax(HttpServletRequest request, Model model,SoccerAllbetting soccerAllbetting) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//足球方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<SoccerLeagueOrder> datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,b.theoreticalbonusmax,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),SoccerLeagueOrder.class,page);
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
			return  "/lottery/soccerscheme/soccerschemeorderListagentMax";
		
		}else if("2".equals(request.getParameter("k"))){
			//足球订单详情
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
			return  "/lottery/soccerleagueorder/soccerleagueordercontentagentMax";
		
		}else if("3".equals(request.getParameter("k"))){
			//篮球方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BasketballOrder> datas= basketballOrderService.queryForList(new Finder("select a.*,b.theoreticalbonusmax,c.name as playmethod from basketball_order a LEFT JOIN basketball_scheme b on a.schemeid = b.schemeid LEFT JOIN basketball_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BasketballOrder.class,page);
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
			return  "/lottery/basketballorder/basketballorderListagentMax";
		
		}else if("4".equals(request.getParameter("k"))){
			//北单方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BjdcOrder> datas= bjdcOrderService.queryForList(new Finder("select a.*,b.theoreticalbonusmax,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BjdcOrder.class,page);
			if(datas!=null){
				for(BjdcOrder bjdcOrder : datas){
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", bjdcOrder.getOrderid()),BjdcOrderContent.class);
					bjdcOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BasketballOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorder/bjdcorderListagentMax";
		}else{
			String agentid = SessionUser.getShiroUser().getAgentid();
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("bettingtime");
			page.setSort("desc");
			page.setPageSize(10);
			String type = request.getParameter("type");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String theoreticalbonusmax = request.getParameter("theoreticalbonusmax");
			String maxmultiple = request.getParameter("maxmultiple");
			String issuestate = request.getParameter("issuestate");
			if(theoreticalbonusmax==null||theoreticalbonusmax==""){
				theoreticalbonusmax = "0";
			}
			if(maxmultiple==null||maxmultiple==""){
				maxmultiple = "20";
			}
			if(issuestate==null||issuestate==""){
				issuestate = "0";
			}
			Date date1 =DateUtils.convertString2Date(endtime);
			Calendar calendar = new GregorianCalendar();
			if(date1!=null){
				calendar.setTime(date1); 
				calendar.add(Calendar.DATE,1);
				Date date3=calendar.getTime();
				endtime = DateUtils.convertDate2String(date3);
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			
			
			if(type==null){
				type = "3";
			}
			if("0".equals(type)){
				List<BetBetting> betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2,c.id2 as memberid2,c.nickname as membernickname,d.nickname as agentnickname from bet_betting a left join bet_gameplay b on a.gameplayid=b.id left join bet_member c on c.id=a.memberid left join bet_agent d on d.agentid=a.agentid where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BetBetting.class,page);
				returnObject1.setData(betbettinglist);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/betbettingList4Max";
			}else if("1".equals(type)){
				List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where c.isissue=1 and c.isinternal=0 and a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid)  and  a.theoreticalbonusmax>=:theoreticalbonusmax and a.maxmultiple>=:maxmultiple and a.issuestate=:issuestate").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("theoreticalbonusmax", Double.parseDouble(theoreticalbonusmax)).setParam("maxmultiple", maxmultiple).setParam("issuestate", issuestate),SoccerScheme.class,page);
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("theoreticalbonusmax", theoreticalbonusmax);
				model.addAttribute("maxmultiple", maxmultiple);
				model.addAttribute("issuestate", issuestate);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/soccerbettingList4Max";
			}else if("4".equals(type)){
				List<BjdcScheme> datas= bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where c.isissue=1 and c.isinternal=0 and  a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) and a.theoreticalbonusmax>=:theoreticalbonusmax and a.maxmultiple>=:maxmultiple and a.issuestate=:issuestate").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("theoreticalbonusmax", Double.parseDouble(theoreticalbonusmax)).setParam("maxmultiple", maxmultiple).setParam("issuestate", issuestate),BjdcScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (BjdcScheme soccerScheme2 : datas) {
						String schemeid = soccerScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(schemeid);
						}
					}
					List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
								    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
									    	String betname = m.get("betname").toString();
									    	m.put("betname",betname+"("+ letpoints+")");
								    	}
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
							schemeMatch.setNum(schemeMatch.getNum());
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("theoreticalbonusmax", theoreticalbonusmax);
				model.addAttribute("maxmultiple", maxmultiple);
				model.addAttribute("issuestate", issuestate);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/bjdcbettingList4Max";
			}else if("3".equals(type)){
				//篮球
				List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 left join bet_agent d on a.agentid=d.agentid  where c.isissue=1 and c.isinternal=0 and a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) and theoreticalbonusmax >=:theoreticalbonusmax and maxmultiple>=:maxmultiple and issnestate =:issnestate").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("theoreticalbonusmax", Double.parseDouble(theoreticalbonusmax)).setParam("maxmultiple", maxmultiple).setParam("issnestate", issuestate),BasketballScheme.class,page);
				
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (BasketballScheme basketballScheme2 : datas) {
						String schemeid = basketballScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(basketballScheme2.getSchemeid());
						}
					}
					
					List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid").setParam("schemeid", schemeids));
						if(resultMap!=null){
							for (Map<String, Object> m : resultMap){
								String oddsname  = m.get("oddsname").toString();
							    
								if("big".equals(oddsname)||"small".equals(oddsname)){
							    	try{
							    		
							    		String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	/*
										String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	*/
									}
							    }
								
							    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
							    	try{
							    		
							    		String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
										/*
										String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
								    	*/
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
				
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				model.addAttribute("theoreticalbonusmax", theoreticalbonusmax);
				model.addAttribute("maxmultiple", maxmultiple);
				model.addAttribute("issuestate", issuestate);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/basketballbettingList4Max";
				
			}else{
				Finder finder = new Finder();
				finder.append("select a.*,b.nickname as membernickname,c.nickname as agentnickname ,ifnull(d.theoreticalbonusmax,0) as soccerTheoreticalbonusmax,ifnull(e.theoreticalbonusmax,0) as BjdcTheoreticalbonusmax,ifnull(f.theoreticalbonusmax,0) as BasketTheoreticalbonusmax");
				finder.append(" ,ifnull(d.maxmultiple,0) as soccerMaxmultiple,ifnull(e.maxmultiple,0) as BjdcMaxmultiple,ifnull(f.maxmultiple,0) as BasketMaxmultiple,d.issuestate as soccerIssuestate,e.issuestate as BjdcIssuestate,f.issnestate as BasketIssuestate");
				finder.append(" from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 left join bet_agent c on a.agentid=c.agentid left join soccer_scheme d on a.id = d.schemeid left join bjdc_scheme e on a.id = e.schemeid left join basketball_scheme f on a.id = f.schemeid");
				finder.append(" where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (b.agentid=:agentid or b.agentparentids like :aid)  and (:type=100 or a.type=:type)");
				finder.append(" and( ifnull(d.theoreticalbonusmax,0)>=:theoreticalbonusmax or ifnull(e.theoreticalbonusmax,0)>=:theoreticalbonusmax2 or ifnull(f.theoreticalbonusmax,0)>=:theoreticalbonusmax3)");
				finder.append(" and( ifnull(d.maxmultiple,0)>=:maxmultiple or ifnull(e.maxmultiple,0)>=:maxmultiple or ifnull(f.maxmultiple,0)>=:maxmultiple) and( d.issuestate=:issuestate or e.issuestate=:issuestate or f.issnestate =:issuestate )")
				.setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid)
				.setParam("theoreticalbonusmax",Double.parseDouble(theoreticalbonusmax)).setParam("theoreticalbonusmax2",Double.parseDouble(theoreticalbonusmax)).setParam("theoreticalbonusmax3",Double.parseDouble(theoreticalbonusmax))
				.setParam("maxmultiple",Double.parseDouble(maxmultiple))
				.setParam("issuestate",issuestate);
				List<SoccerAllbetting> datas = soccerAllbettingService.queryForList(finder,SoccerAllbetting.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						allBetting.setIssuestate(issuestate);
						if(1==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<SoccerSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					}
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
					
					for(SoccerAllbetting allBetting : datas){
						List<SoccerSchemeMatch> sss=new ArrayList<SoccerSchemeMatch>();
						if(matchDatas!=null){
							for(SoccerSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setSchemecontent(sss);
					}
					
				}
				
				
				//北单
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(4==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<BjdcSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					}
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
								    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
									    	String betname = m.get("betname").toString();
									    	m.put("betname",betname+"("+ letpoints+")");
								    	}
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
							schemeMatch.setNum(schemeMatch.getNum());
						}
					}
					
					for(SoccerAllbetting allBetting : datas){
						List<BjdcSchemeMatch> sss=new ArrayList<BjdcSchemeMatch>();
						if(matchDatas!=null){
							for(BjdcSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setBjdcschemecontent(sss);
					}
				}
				
				//篮球处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(3==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<BasketballSchemeMatch> matchDatas=null;
					if(!schemeids.isEmpty()){
						matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					}
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
						if(resultMap!=null){
							for (Map<String, Object> m : resultMap){
								String oddsname  = m.get("oddsname").toString();
								if("big".equals(oddsname)||"small".equals(oddsname)){
							    	try{
							    		String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	
									}
							    }
								
							    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
							    	try{
							    		
							    		String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
								    	/*
							    		
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = m.get("base").toString();
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
					
					for(SoccerAllbetting allBetting : datas){
						List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
						if(matchDatas!=null){
							for(BasketballSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setBasketballschemecontent(sss);
					}
					
				}
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/allsoccerbettingList4max";
			}
		}
		
	}
	
	//神單
	@RequestMapping("/currentbettingGod")
	public String currentbettingGod(HttpServletRequest request, Model model,SoccerAllbetting soccerAllbetting) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//足球方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<SoccerLeagueOrder> datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,b.theoreticalbonusmax,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),SoccerLeagueOrder.class,page);
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
			return  "/lottery/soccerscheme/soccerschemeorderListagentGod";
		
		}else if("2".equals(request.getParameter("k"))){
			//足球订单详情
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
			return  "/lottery/soccerleagueorder/soccerleagueordercontentagentGod";
		
		}else if("3".equals(request.getParameter("k"))){
			//篮球方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BasketballOrder> datas= basketballOrderService.queryForList(new Finder("select a.*,b.theoreticalbonusmax,c.name as playmethod from basketball_order a LEFT JOIN basketball_scheme b on a.schemeid = b.schemeid LEFT JOIN basketball_league_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BasketballOrder.class,page);
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
			return  "/lottery/basketballorder/basketballorderListagentGod";
		
		}else if("4".equals(request.getParameter("k"))){
			//北单方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			
			List<BjdcOrder> datas= bjdcOrderService.queryForList(new Finder("select a.*,b.theoreticalbonusmax,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),BjdcOrder.class,page);
			if(datas!=null){
				for(BjdcOrder bjdcOrder : datas){
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", bjdcOrder.getOrderid()),BjdcOrderContent.class);
					bjdcOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BasketballOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorder/bjdcorderListagentGod";
		}else{
			String agentid = SessionUser.getShiroUser().getAgentid();
			ReturnDatas returnObject1 = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("bettingtime");
			page.setSort("desc");
			page.setPageSize(10);
			String type = request.getParameter("type");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String theoreticalbonusmax = request.getParameter("theoreticalbonusmax");
			String maxmultiple = request.getParameter("maxmultiple");
			String issuestate = request.getParameter("issuestate");
			if(theoreticalbonusmax==null||theoreticalbonusmax==""){
				theoreticalbonusmax = "0";
			}
			if(maxmultiple==null||maxmultiple==""){
				maxmultiple = "20";
			}
			if(issuestate==null||issuestate==""){
				issuestate = "0";
			}
			Date date1 =DateUtils.convertString2Date(endtime);
			Calendar calendar = new GregorianCalendar();
			if(date1!=null){
				calendar.setTime(date1); 
				calendar.add(Calendar.DATE,1);
				Date date3=calendar.getTime();
				endtime = DateUtils.convertDate2String(date3);
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			
			
			if(type==null){
				type = "3";
			}
			if("0".equals(type)){
				List<BetBetting> betbettinglist = betBettingService.queryForList(new Finder("select a.*,b.name2,c.id2 as memberid2,c.nickname as membernickname,d.nickname as agentnickname from bet_betting a left join bet_gameplay b on a.gameplayid=b.id left join bet_member c on c.id=a.memberid left join bet_agent d on d.agentid=a.agentid where a.state=0 and c.isissue=1 and c.isinternal=0  and a.bettingtime>=:starttime and a.bettingtime<:endtime and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid),BetBetting.class,page);
				returnObject1.setData(betbettinglist);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/betbettingList4God";
			}else if("1".equals(type)){
				List<SoccerScheme> datas= soccerSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from soccer_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where a.buytype=2 and c.isissue=1 and c.isinternal=0 and a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid)  and  a.theoreticalbonusmax>=:theoreticalbonusmax and a.maxmultiple>=:maxmultiple and a.issuestate=:issuestate").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("theoreticalbonusmax", Double.parseDouble(theoreticalbonusmax)).setParam("maxmultiple", maxmultiple).setParam("issuestate", issuestate),SoccerScheme.class,page);
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("theoreticalbonusmax", theoreticalbonusmax);
				model.addAttribute("maxmultiple", maxmultiple);
				model.addAttribute("issuestate", issuestate);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/soccerbettingList4God";
			}else if("4".equals(type)){
				List<BjdcScheme> datas= bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on a.memberid2 = c.id2 left join bet_agent d on a.agentid=d.agentid where a.buytype=2 and c.isissue=1 and c.isinternal=0 and  a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) and a.theoreticalbonusmax>=:theoreticalbonusmax and a.maxmultiple>=:maxmultiple and a.issuestate=:issuestate").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("theoreticalbonusmax", Double.parseDouble(theoreticalbonusmax)).setParam("maxmultiple", maxmultiple).setParam("issuestate", issuestate),BjdcScheme.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (BjdcScheme soccerScheme2 : datas) {
						String schemeid = soccerScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(schemeid);
						}
					}
					List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
								    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
									    	String betname = m.get("betname").toString();
									    	m.put("betname",betname+"("+ letpoints+")");
								    	}
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = soccerLeagueOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
							schemeMatch.setNum(schemeMatch.getNum());
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
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("theoreticalbonusmax", theoreticalbonusmax);
				model.addAttribute("maxmultiple", maxmultiple);
				model.addAttribute("issuestate", issuestate);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/bjdcbettingList4God";
			}else if("3".equals(type)){
				//篮球
				List<BasketballScheme> datas = basketballSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname,d.nickname as agentnickname from basketball_scheme a LEFT JOIN basketball_league_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 left join bet_agent d on a.agentid=d.agentid where a.buytype=2 and c.isissue=1 and c.isinternal=0 and a.situation=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (c.agentid=:agentid or c.agentparentids like :aid) and theoreticalbonusmax >=:theoreticalbonusmax and maxmultiple>=:maxmultiple and issnestate =:issnestate").setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("theoreticalbonusmax", Double.parseDouble(theoreticalbonusmax)).setParam("maxmultiple", maxmultiple).setParam("issnestate", issuestate),BasketballScheme.class,page);
				
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (BasketballScheme basketballScheme2 : datas) {
						String schemeid = basketballScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(basketballScheme2.getSchemeid());
						}
					}
					
					List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
						if(resultMap!=null){
							for (Map<String, Object> m : resultMap){
								String oddsname  = m.get("oddsname").toString();
							    
								if("big".equals(oddsname)||"small".equals(oddsname)){
							    	try{
							    		
							    		String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	/*
										String dxf = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	*/
									}
							    }
								
							    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
							    	try{
							    		
							    		String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
										/*
										String letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid ").setParam("zid", m.get("zid").toString()), String.class);
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
								    	*/
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
				
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				model.addAttribute("theoreticalbonusmax", theoreticalbonusmax);
				model.addAttribute("maxmultiple", maxmultiple);
				model.addAttribute("issuestate", issuestate);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/basketballbettingList4God";
				
			}else{
				Finder finder = new Finder();
				finder.append("select a.*,b.nickname as membernickname,c.nickname as agentnickname ,ifnull(d.theoreticalbonusmax,0) as soccerTheoreticalbonusmax,ifnull(e.theoreticalbonusmax,0) as BjdcTheoreticalbonusmax,ifnull(f.theoreticalbonusmax,0) as BasketTheoreticalbonusmax");
				finder.append(" ,ifnull(d.maxmultiple,0) as soccerMaxmultiple,ifnull(e.maxmultiple,0) as BjdcMaxmultiple,ifnull(f.maxmultiple,0) as BasketMaxmultiple,d.issuestate as soccerIssuestate,e.issuestate as BjdcIssuestate,f.issnestate as BasketIssuestate");
				finder.append(" from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 left join bet_agent c on a.agentid=c.agentid left join soccer_scheme d on a.id = d.schemeid left join bjdc_scheme e on a.id = e.schemeid left join basketball_scheme f on a.id = f.schemeid");
				finder.append(" where a.state=0 and a.bettingtime>=:starttime and a.bettingtime<:endtime and (b.agentid=:agentid or b.agentparentids like :aid)  and (:type=100 or a.type=:type)");
				finder.append(" and( ifnull(d.theoreticalbonusmax,0)>=:theoreticalbonusmax or ifnull(e.theoreticalbonusmax,0)>=:theoreticalbonusmax2 or ifnull(f.theoreticalbonusmax,0)>=:theoreticalbonusmax3)");
				finder.append(" and( ifnull(d.maxmultiple,0)>=:maxmultiple or ifnull(e.maxmultiple,0)>=:maxmultiple or ifnull(f.maxmultiple,0)>=:maxmultiple) and( d.issuestate=:issuestate or e.issuestate=:issuestate or f.issnestate =:issuestate )")
				.setParam("type", type).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("aid", "%,"+agentid+",%").setParam("agentid", agentid)
				.setParam("theoreticalbonusmax",Double.parseDouble(theoreticalbonusmax)).setParam("theoreticalbonusmax2",Double.parseDouble(theoreticalbonusmax)).setParam("theoreticalbonusmax3",Double.parseDouble(theoreticalbonusmax))
				.setParam("maxmultiple",Double.parseDouble(maxmultiple))
				.setParam("issuestate",issuestate);
				List<SoccerAllbetting> datas = soccerAllbettingService.queryForList(finder,SoccerAllbetting.class,page);
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						allBetting.setIssuestate(issuestate);
						if(1==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<SoccerSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
					}
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
					
					for(SoccerAllbetting allBetting : datas){
						List<SoccerSchemeMatch> sss=new ArrayList<SoccerSchemeMatch>();
						if(matchDatas!=null){
							for(SoccerSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setSchemecontent(sss);
					}
					
				}
				
				
				//北单
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(4==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<BjdcSchemeMatch> matchDatas = null;
					if(!schemeids.isEmpty()){
						matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
					}
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
								    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
									    	String betname = m.get("betname").toString();
									    	m.put("betname",betname+"("+ letpoints+")");
								    	}
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
							schemeMatch.setNum(schemeMatch.getNum());
						}
					}
					
					for(SoccerAllbetting allBetting : datas){
						List<BjdcSchemeMatch> sss=new ArrayList<BjdcSchemeMatch>();
						if(matchDatas!=null){
							for(BjdcSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setBjdcschemecontent(sss);
					}
				}
				
				//篮球处理
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					for (SoccerAllbetting allBetting : datas) {
						if(3==allBetting.getType()){
							String schemeid = allBetting.getId();
							if(schemeid!=null){
								schemeids.add(schemeid);
							}
						}
					}
					List<BasketballSchemeMatch> matchDatas=null;
					if(!schemeids.isEmpty()){
						matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
					}
					if(matchDatas!=null){
						List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.base,a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
						if(resultMap!=null){
							for (Map<String, Object> m : resultMap){
								String oddsname  = m.get("oddsname").toString();
								if("big".equals(oddsname)||"small".equals(oddsname)){
							    	try{
							    		String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
							    		/*
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String dxf = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ dxf+")");
								    	
									}
							    }
								
							    if("rfzs".equals(oddsname)||"rfzf".equals(oddsname)){
							    	try{
							    		
							    		String letpoints = m.get("base").toString();
								    	String betname = m.get("betname").toString();
								    	m.put("betname",betname+"("+ letpoints+")");
								    	/*
							    		
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
								    	*/
							    	}catch (Exception e) {
										e.printStackTrace();
										String letpoints = m.get("base").toString();
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
					
					for(SoccerAllbetting allBetting : datas){
						List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
						if(matchDatas!=null){
							for(BasketballSchemeMatch schemeMatch : matchDatas){
								String schemeid = schemeMatch.getSchemeid();
								if(schemeid.equals(allBetting.getId())){
									sss.add(schemeMatch);
								}
							}
						}
						allBetting.setBasketballschemecontent(sss);
					}
					
				}
				returnObject1.setData(datas);
				returnObject1.setPage(page);
				model.addAttribute(GlobalStatic.returnDatas, returnObject1);
				model.addAttribute("type", type);
				if(!"0000-00-00".equals(starttime)){
					model.addAttribute("startTime", starttime);
				}
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betmember/allsoccerbettingList4god";
			}
		}
		
	}
	
	
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerAllbetting
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerAllbetting soccerAllbetting) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		page.setPageSize(10);
		page.setOrder("bettingtime");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		String memberid2 = request.getParameter("memberid2");
		if(StringUtils.isBlank(memberid2)){
			memberid2=null;
		}else{
			memberid2="%"+memberid2+"%";
		}
		if(StringUtils.isBlank(starttime)){
			starttime="0000-01-01";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-01-01";
		}
		java.sql.Date startDate = java.sql.Date.valueOf(starttime);
		java.sql.Date endDate=java.sql.Date.valueOf(endtime);
		List<SoccerAllbetting> datas = null;
		Double bettingmoneyTotal = 0d;
		Double bettingwinTotal = 0d;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			datas=soccerAllbettingService.findListDataByFinder(new Finder("select a.*,b.nickname as membernickname from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where ( :memberid2 is null or a.memberid2 like :memberid2) and (:state is null or a.state = :state) and (:type is null or a.type = :type)").setParam("memberid2", memberid2).setParam("state", soccerAllbetting.getState()).setParam("type", soccerAllbetting.getType()),page,SoccerAllbetting.class,null);
			bettingmoneyTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where ( :memberid2 is null or memberid2 like :memberid2) and (:state is null or state = :state) and (:type is null or type = :type)").setParam("memberid2", memberid2).setParam("state", soccerAllbetting.getState()).setParam("type", soccerAllbetting.getType()),Double.class);
			bettingwinTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingscore) from soccer_allbetting where ( :memberid2 is null or memberid2 like :memberid2) and (:state is null or state = :state) and (:type is null or type = :type)").setParam("memberid2", memberid2).setParam("state", soccerAllbetting.getState()).setParam("type", soccerAllbetting.getType()),Double.class);
		}else{
			datas=soccerAllbettingService.findListDataByFinder(new Finder("select a.*,b.nickname as membernickname from soccer_allbetting a left join bet_member b on a.memberid2 = b.id2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and ( :memberid2 is null or a.memberid2 like :memberid2) and (:state is null or a.state = :state) and (:type is null or a.type = :type)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("state", soccerAllbetting.getState()).setParam("type", soccerAllbetting.getType()),page,SoccerAllbetting.class,null);
			bettingmoneyTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingmoney) from soccer_allbetting where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and ( :memberid2 is null or memberid2 like :memberid2) and (:state is null or state = :state) and (:type is null or type = :type)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("state", soccerAllbetting.getState()).setParam("type", soccerAllbetting.getType()),Double.class);
			bettingwinTotal = soccerAllbettingService.queryForObject(new Finder("select sum(bettingscore) from soccer_allbetting where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and ( :memberid2 is null or memberid2 like :memberid2) and (:state is null or state = :state) and (:type is null or type = :type)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("state", soccerAllbetting.getState()).setParam("type", soccerAllbetting.getType()),Double.class);
		}
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			for (SoccerAllbetting allBetting : datas) {
				if(1==allBetting.getType()){
					String schemeid = allBetting.getId();
					if(schemeid!=null){
						schemeids.add(schemeid);
					}
				}
			}
			List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), SoccerSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.mid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid,c.schemeid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
			
			for(SoccerAllbetting allBetting : datas){
				List<SoccerSchemeMatch> sss=new ArrayList<SoccerSchemeMatch>();
				if(matchDatas!=null){
					for(SoccerSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(allBetting.getId())){
							sss.add(schemeMatch);
						}
					}
				}
				allBetting.setSchemecontent(sss);
			}
		}
		//北单
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			for (SoccerAllbetting allBetting : datas) {
				if(4==allBetting.getType()){
					String schemeid = allBetting.getId();
					if(schemeid!=null){
						schemeids.add(schemeid);
					}
				}
			}
			List<BjdcSchemeMatch> matchDatas= bjdcSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid left join bjdc_result c on a.fid = c.fid where a.schemeid in (:schemeid) order by a.id").setParam("schemeid", schemeids), BjdcSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = bjdcSchemeMatchService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid in (:schemeid) group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeids));
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
						    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
							    	String betname = m.get("betname").toString();
							    	m.put("betname",betname+"("+ letpoints+")");
						    	}
					    	}catch (Exception e) {
								e.printStackTrace();
								String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", m.get("fid").toString()), String.class);
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
					schemeMatch.setNum(schemeMatch.getNum());
				}
			}
			for(SoccerAllbetting allBetting : datas){
				List<BjdcSchemeMatch> sss=new ArrayList<BjdcSchemeMatch>();
				if(matchDatas!=null){
					for(BjdcSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(allBetting.getId())){
							sss.add(schemeMatch);
						}
					}
				}
				allBetting.setBjdcschemecontent(sss);
			}
		}
		//篮球处理
		if(datas!=null){
			List<String> schemeids=new ArrayList<String>();
			for (SoccerAllbetting allBetting : datas) {
				if(3==allBetting.getType()){
					String schemeid = allBetting.getId();
					if(schemeid!=null){
						schemeids.add(schemeid);
					}
				}
			}
			
			List<BasketballSchemeMatch> matchDatas= basketballSchemeMatchService.queryForList(new Finder("select a.schemeid,a.id,a.zid,a.dan,b.matchname,b.hometeam,b.awayteam,b.starttime,b.endtime,b.num,b.matchdate,c.score from basketball_scheme_match a LEFT JOIN basketball_league_arrange b on a.zid = b.zid left join basketball_league_result c on a.zid = c.zid where a.schemeid in (:schemeid)  order by a.id").setParam("schemeid", schemeids), BasketballSchemeMatch.class);
			if(matchDatas!=null){
				List<Map<String, Object>> resultMap = basketballSchemeMatchService.queryForList(new Finder("select a.oddsname,a.zid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.shortname,c.schemeid from basketball_order_content a LEFT JOIN basketball_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN basketball_order c on a.orderid = c.orderid where c.schemeid in (:schemeid)  group by c.schemeid, a.mid,a.oddsname").setParam("schemeid", schemeids));
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
			
			for(SoccerAllbetting allBetting : datas){
				List<BasketballSchemeMatch> sss=new ArrayList<BasketballSchemeMatch>();
				if(matchDatas!=null){
					for(BasketballSchemeMatch schemeMatch : matchDatas){
						String schemeid = schemeMatch.getSchemeid();
						if(schemeid.equals(allBetting.getId())){
							sss.add(schemeMatch);
						}
					}
				}
				allBetting.setBasketballschemecontent(sss);
			}
			
		}
		
		returnObject.setQueryBean(soccerAllbetting);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("startTime", startDate);
		model.addAttribute("endTime", endDate);
		model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
		model.addAttribute("bettingwinTotal", bettingwinTotal);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerAllbetting
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerAllbetting soccerAllbetting) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerAllbetting> datas=soccerAllbettingService.findListDataByFinder(null,page,SoccerAllbetting.class,soccerAllbetting);
		if(datas!=null){
			for(SoccerAllbetting allBetting : datas){
				if(1==allBetting.getType()){
					List<SoccerSchemeMatch> matchDatas= soccerSchemeMatchService.queryForList(new Finder("select a.id,a.mid,a.dan,b.matchname,b.leftteamname,b.rightteamname,b.starttime,b.endtime,b.num,c.halfscore,c.allscore from soccer_scheme_match a LEFT JOIN soccer_league_arrange b on a.mid = b.mid left join soccer_league_result c on a.mid = c.mid where a.schemeid = :schemeid order by a.id").setParam("schemeid", allBetting.getId()), SoccerSchemeMatch.class);
					if(matchDatas!=null){
						for(SoccerSchemeMatch schemeMatch : matchDatas){
							List<Map<String, Object>> resultMap = soccerSchemeMatchService.queryForList(new Finder("select a.oddsname,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playmethodid from soccer_league_order_content a LEFT JOIN soccer_league_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN soccer_league_order c on a.orderid = c.orderid where a.mid=:mid  and c.schemeid = :schemeid group by a.mid,a.oddsname").setParam("mid", schemeMatch.getMid()).setParam("schemeid", allBetting.getId()));
							for (Map<String, Object> m : resultMap){ 
								String oddsname  = m.get("oddsname").toString();
								String oddsrealname = "";
							    if("left_odds".equals(oddsname)){
							    	oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select left_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",schemeMatch.getMid()),String.class);
							    	 m.put("oddsrealname", oddsrealname);
							    }else if("right_odds".equals(oddsname)){
							    	oddsrealname =soccerLeague2choose1oddsService.queryForObject(new Finder("select right_name from soccer_league_2choose1odds where mid=:mid").setParam("mid",schemeMatch.getMid()),String.class);
							    	 m.put("oddsrealname", oddsrealname);
							    }
							}
							schemeMatch.setResultMap(resultMap);
							schemeMatch.setNum(WeekOfDate.getWeekOfDate(schemeMatch.getEndtime())+schemeMatch.getNum());
							
						}
					}
					allBetting.setSchemecontent(matchDatas);
				}
			}
		}
		returnObject.setQueryBean(soccerAllbetting);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerAllbetting soccerAllbetting) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerAllbettingService.findDataExportExcel(null,listurl, page,SoccerAllbetting.class,soccerAllbetting);
		String fileName="soccerAllbetting"+GlobalStatic.excelext;
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
		return "/lottery/soccerallbetting/soccerallbettingLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  SoccerAllbetting soccerAllbetting = soccerAllbettingService.findSoccerAllbettingById(id);
		   returnObject.setData(soccerAllbetting);
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
	public ReturnDatas saveorupdate(Model model,SoccerAllbetting soccerAllbetting,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =soccerAllbetting.getId();
			if(StringUtils.isBlank(id)){
			  soccerAllbetting.setId(null);
			}
		
			soccerAllbettingService.saveorupdate(soccerAllbetting);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/soccerallbetting/soccerallbettingCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody      
	public  ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				soccerAllbettingService.deleteById(id,SoccerAllbetting.class);
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
			soccerAllbettingService.deleteByIds(ids,SoccerAllbetting.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	

}
