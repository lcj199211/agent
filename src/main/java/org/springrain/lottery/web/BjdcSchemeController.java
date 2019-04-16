package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.lottery.entity.BetCommission;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BjdcArrange;
import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.lottery.entity.BjdcOrderContent;
import org.springrain.lottery.entity.BjdcPlaymethodOddsname;
import org.springrain.lottery.entity.BjdcResult;
import org.springrain.lottery.entity.BjdcScheme;
import org.springrain.lottery.entity.BjdcSchemeMatch;
import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.lottery.service.IBetAgentBrokerageService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetCommissionService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBjdcArrangeService;
import org.springrain.lottery.service.IBjdcOddsService;
import org.springrain.lottery.service.IBjdcOrderContentService;
import org.springrain.lottery.service.IBjdcOrderService;
import org.springrain.lottery.service.IBjdcPlaymethodOddsnameService;
import org.springrain.lottery.service.IBjdcResultService;
import org.springrain.lottery.service.IBjdcSchemeMatchService;
import org.springrain.lottery.service.IBjdcSchemeService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.utils.AgentToolUtil;
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
 * @version  2017-12-12 09:37:24
 * @see org.springrain.lottery.web.BjdcScheme
 */
@Controller
@RequestMapping(value="/bjdcscheme")
public class BjdcSchemeController  extends BaseController {
	@Resource
	private IBjdcSchemeService bjdcSchemeService;
	@Resource
	private IBjdcSchemeMatchService bjdcSchemeMatchService;
	@Resource
	private ICached cached;
	@Resource
	private IBjdcOddsService bjdcOddsService;
	@Resource
	private IBjdcArrangeService bjdcArrangeService;
	@Resource
	private IBjdcOrderContentService bjdcOrderContentService;
	@Resource
	private IBjdcResultService bjdcResultService;
	@Resource
	private IBjdcOrderService bjdcOrderService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentBrokerageService betAgentBrokerageService;
	@Resource
	private IBetCommissionService betCommissionService;
	@Resource
	private IBjdcPlaymethodOddsnameService bjdcPlaymethodOddsnameService;
	private String listurl="/lottery/bjdcscheme/bjdcschemeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param bjdcScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BjdcScheme bjdcScheme) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<BjdcOrder> datas= bjdcOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where  a.schemeid = :schemeid and (b.agentid = :agentid or b.agentparentids like :agentparentids) order by b.id").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid),BjdcOrder.class,page);
			if(datas!=null){
				for(BjdcOrder bjdcOrder : datas){
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", bjdcOrder.getOrderid()),BjdcOrderContent.class);
					bjdcOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BjdcOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcscheme/bjdcschemeorderList";
		}else if("2".equals(request.getParameter("k"))){
			//查询会员
//			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//			String memberid2 = request.getParameter("memberid2");
//			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
//			returnObject.setData(datas);
//			returnObject.setQueryBean(new BetMember());
//			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorder/bjdcmemberList";
		}else if("3".equals(request.getParameter("k"))){
			//投注内容
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String schemeid = request.getParameter("schemeid");
			List<BjdcSchemeMatch> datas= bjdcSchemeMatchService.queryForList(new Finder("select a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid where a.schemeid = :schemeid order by a.id").setParam("schemeid", schemeid), BjdcSchemeMatch.class);
			if(datas!=null){
				for(BjdcSchemeMatch schemeMatch : datas){
					String oddsrealname = bjdcSchemeMatchService.queryForObject(new Finder("select GROUP_CONCAT(distinct b.oddsrealname) as odds from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname where a.fid=:fid group by a.fid").setParam("fid", schemeMatch.getFid()), String.class);
					BjdcResult bjdcResult = bjdcResultService.queryForObject(new Finder("select halfscore,allscore from bjdc_result where fid = :fid").setParam("fid", schemeMatch.getFid()), BjdcResult.class);
					schemeMatch.setOddsrealname(oddsrealname);
					if(bjdcResult!=null){
						schemeMatch.setHalfscore(bjdcResult.getHalfscore());
						schemeMatch.setAllscore(bjdcResult.getAllscore());
					}
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new BjdcSchemeMatch());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcscheme/bjdcschemematch";
		}else if("4".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String schemeid2 = request.getParameter("schemeid2");
			List<BjdcScheme> datas = bjdcSchemeService.queryForList(new Finder("select a.*,b.name as playmethod,c.nickname as membernickname from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id left join bet_member c on c.id2=a.memberid2 where  a.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) order by a.bettingtime").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2", schemeid2),BjdcScheme.class,page);
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
							if(schemeid.equals((String)m.get("schemeid"))&& fid.equals((String)m.get("fid"))){
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
				String situation = request.getParameter("situation");
				String time = request.getParameter("time");
				String buytype = request.getParameter("buytype");
				String fid = request.getParameter("fid");
				if("100".equals(buytype)){
					buytype = null;
				}
				if("100".equals(situation)){
					situation=null;
				}
				if("100".equals(playmethodid)){
					playmethodid = null;
				}
				if(StringUtils.isBlank(time)){
					time="0";
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
				if(StringUtils.isBlank(fid)){
					fid=null;
				}
				java.sql.Date startDate = java.sql.Date.valueOf(starttime);
				java.sql.Date endDate=java.sql.Date.valueOf(endtime);
				List<BjdcScheme> datas = null;
				Double bettingmoneyTotal = 0d;
				Double bettingwinTotal = 0d;
				if(starttime=="0000-01-01" && endtime=="9999-01-01"){
					if("1".equals(time)){
						datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
						 bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
						 bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
					 }else if("2".equals(time)){
						 //昨日
						 datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
						 bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
						 bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
					 }else if("3".equals(time)){
						 //本周
						 datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
						 bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
						 bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
					 }else if("4".equals(time)){
						 //上周
						 datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
						 bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
						 bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
					 }else if("5".equals(time)){
						 //本月
						 datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
						 bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
						 bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
					 }else{
						 datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where  (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("memberid2", memberid2).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
						 bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where  (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("fid", fid).setParam("buytype", buytype),Double.class);
						 bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where  (:memberid2 is null or a.memberid2 = :memberid2) and (:playmethodid is null or a.playmethodid = :playmethodid) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("fid", fid).setParam("buytype", buytype),Double.class);
					 }
					
				}else{
					datas= bjdcSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal  from bjdc_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (:playmethodid is null or a.playmethodid = :playmethodid) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid) and (a.agentid = :agentid or a.agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),BjdcScheme.class,page);
					bettingmoneyTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (:playmethodid is null or a.playmethodid = :playmethodid) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
					bettingwinTotal = bjdcSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_scheme a LEFT JOIN bjdc_playmethod b on a.playmethodid = b.id where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:buytype is null or a.buytype = :buytype) and (:playmethodid is null or a.playmethodid = :playmethodid) and a.schemeid IN (SELECT schemeid FROM bjdc_order_content WHERE fid=(:fid is null or fid=:fid) GROUP BY schemeid ) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation).setParam("playmethodid", playmethodid).setParam("buytype", buytype).setParam("fid", fid),Double.class);
				}
				if(starttime=="0000-01-01"){
					startDate=null;
				}
				if(endtime=="9999-01-01"){
					endDate=null;
				}
				
				if(datas!=null){
					List<String> schemeids=new ArrayList<String>();
					
					for (BjdcScheme soccerScheme2 : datas) {
						String schemeid = soccerScheme2.getSchemeid();
						if(schemeid!=null){
							schemeids.add(soccerScheme2.getSchemeid());
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
							String mid = schemeMatch.getFid();
							List<Map<String, Object>> mmm=new ArrayList<Map<String, Object>>();
							for (Map<String, Object> m : resultMap){
								if(schemeid.equals((String)m.get("schemeid"))&&mid.equals((String)m.get("fid"))){
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
				returnObject.setQueryBean(bjdcScheme);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("startTime", startDate);
				model.addAttribute("endTime", endDate);
				model.addAttribute("time", time);
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
	 * @param bjdcScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BjdcScheme bjdcScheme) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BjdcScheme> datas=bjdcSchemeService.findListDataByFinder(null,page,BjdcScheme.class,bjdcScheme);
			returnObject.setQueryBean(bjdcScheme);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BjdcScheme bjdcScheme) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = bjdcSchemeService.findDataExportExcel(null,listurl, page,BjdcScheme.class,bjdcScheme);
		String fileName="bjdcScheme"+GlobalStatic.excelext;
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
		return "/lottery/bjdcscheme/bjdcschemeLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BjdcScheme bjdcScheme = bjdcSchemeService.findBjdcSchemeById(id);
		   returnObject.setData(bjdcScheme);
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
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BjdcScheme bjdcScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			 String pagentid = SessionUser.getShiroUser().getAgentid();
			 BetAgent pAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", pagentid), BetAgent.class);
			 String agentparentids = ","+pagentid+",";
			if("1".equals(request.getParameter("cancel"))){
				String schemeid = request.getParameter("schemeid");
				BjdcScheme scheme = null;
				if(schemeid!=null){
					scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), BjdcScheme.class);
				}else{
					scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", bjdcScheme.getId()), BjdcScheme.class);
				}
				if(scheme!=null){
					List<BjdcOrder> suestateList = bjdcOrderService.queryForList(new Finder("select orderid from bjdc_order where schemeid = :schemeid and issuestate = 3").setParam("schemeid", scheme.getSchemeid()),BjdcOrder.class);
					if(suestateList!=null&&!suestateList.isEmpty()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该方案存在已出票订单，无法撤消");
						return returnObject;
					}
					/**
					 * 比赛开始后就不可以撤销
					 */
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select * from bjdc_order_content where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()),BjdcOrderContent.class);
					if(contentDatas!=null){
						for(BjdcOrderContent orderContent : contentDatas){
							BjdcArrange matchDatas = bjdcArrangeService.queryForObject(new Finder("select * from bjdc_arrange where fid = :fid").setParam("fid", orderContent.getFid()),BjdcArrange.class);
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
						//未开奖撤销
						Integer memberid2 = scheme.getMemberid2();
						BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2),BetMember.class);
						if(member!=null){
							bjdcSchemeService.update(new Finder("update bjdc_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", scheme.getId()));
							bjdcOrderService.update(new Finder("update bjdc_order set result=2 where schemeid=:id").setParam("id", scheme.getSchemeid()));
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
								BjdcScheme sdsoccersheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", scheme.getSchemeid2()), BjdcScheme.class);
								bjdcSchemeService.update(new Finder("update bjdc_scheme set bettingalready=bettingalready-:bettingalready,bettingnum=bettingnum-:bettingnum where schemeid=:schemeid").setParam("bettingalready", scheme.getBettingmoney()).setParam("bettingnum", 1).setParam("schemeid", sdsoccersheme.getSchemeid()));
							}

							if(scheme.getBuytype()==2){
								//神单
								List<BjdcScheme> datas = bjdcSchemeService.queryForList(new Finder("select * from bjdc_scheme where buytype = 1 and schemeid2 = :schemeid2 ").setParam("schemeid2", scheme.getSchemeid2()), BjdcScheme.class);
								for(BjdcScheme gdScheme : datas){
									if(gdScheme.getSituation()!=null&&gdScheme.getSituation()==0){
										Integer gdmemberid2 = gdScheme.getMemberid2();
										BetMember gdmember = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", gdmemberid2),BetMember.class);
										if(gdmember!=null){
											bjdcSchemeService.update(new Finder("update bjdc_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", gdScheme.getId()));
											bjdcOrderService.update(new Finder("update bjdc_order set result=2 where schemeid=:id").setParam("id", gdScheme.getSchemeid()));
											soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", gdScheme.getSchemeid()));
											BetMember gdmember2=new BetMember();
											gdmember2.setId(gdmember.getId());
											gdmember2.setId2(gdmember.getId2());
											BigDecimal gdgameScore = new BigDecimal(Double.toString(gdmember.getGamescore()));
									        BigDecimal gdbettingMoney = new BigDecimal(gdScheme.getBettingmoney().toString());
									        gdmember2.setGamescore(gdgameScore.add(gdbettingMoney).doubleValue());
											betMemberService.update(gdmember2,true);
											BjdcScheme sdsoccersheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", gdScheme.getSchemeid2()), BjdcScheme.class);
											bjdcSchemeService.update(new Finder("update bjdc_scheme set bettingalready=bettingalready-:bettingalready,bettingnum=bettingnum-:bettingnum where schemeid=:schemeid").setParam("bettingalready", gdScheme.getBettingmoney()).setParam("bettingnum", 1).setParam("schemeid", sdsoccersheme.getSchemeid()));
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
												     content="用户"+gdScheme.getMemberid2()+"撤销在北单的方案号为"+gdScheme.getSchemeid()+"的投注方案，投注额为"+gdScheme.getBettingmoney();
												     betScorerecord.setMemberid2(gdmember.getId2());
												     betScorerecord.setTime(new Date());
												     betScorerecord.setContent(content);
												     betScorerecord.setOriginalscore(gdmember.getScore());
												     betScorerecord.setChangescore(0.);
												     betScorerecord.setBalance(gdmember.getScore());
												     betScorerecord.setState(1);
												     betScorerecord.setType(22);
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
												     details = "撤销ID："+gdmember.getId2()+"的用户在"+"北单-"+gdScheme.getBettingmoney()+"分的投注方案";
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
								     content="用户"+scheme.getMemberid2()+"撤销在北单的方案号为"+scheme.getSchemeid()+"的投注方案，投注额为"+scheme.getBettingmoney();
								     betScorerecord.setMemberid2(member.getId2());
								     betScorerecord.setTime(new Date());
								     betScorerecord.setContent(content);
								     betScorerecord.setOriginalscore(member.getScore());
								     betScorerecord.setChangescore(0.);
								     betScorerecord.setBalance(member.getScore());
								     betScorerecord.setState(1);
								     betScorerecord.setType(22);					 
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
								     details = "撤销ID："+member.getId2()+"的用户在"+"北单-"+scheme.getBettingmoney()+"分的投注方案";
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
				bjdcSchemeService.update(new Finder("update bjdc_scheme set statement=:statement where id=:id ").setParam("statement", "").setParam("id", id));
			}else if("1".equals(request.getParameter("settle"))){
				String schemeid = request.getParameter("schemeid");
				BjdcScheme scheme = null;
				if(schemeid!=null){
					scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), BjdcScheme.class);
				}else{
					scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", bjdcScheme.getId()), BjdcScheme.class);
				}
				
				if(scheme!=null){
					Integer memberid2 = scheme.getMemberid2();
					BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2),BetMember.class);
					if(member==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("无此用户");
						return returnObject;
					}
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						String schemeid1=scheme.getSchemeid();
						List<BjdcOrder> orderList234 = bjdcOrderService.queryForList(new Finder("select * from bjdc_order where result=0 and state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), BjdcOrder.class);
						if(orderList234!=null){
							for (BjdcOrder bjdcOrder : orderList234) {
								List<BjdcOrderContent>  contentList = bjdcOrderContentService.queryForList(new Finder("select * from bjdc_order_content where orderid=:orderid ").setParam("orderid", bjdcOrder.getOrderid()), BjdcOrderContent.class);
								int count = 0 ;
								Boolean flag=false;
								if(contentList!=null){
									for (BjdcOrderContent bjdcOrderContent : contentList) {
										if(bjdcOrderContent.getResult()==1){
											count++;
										}
										if(bjdcOrderContent.getResult()==3){
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
							for (BjdcOrder bjdcOrder : orderList234) {

								List<BjdcOrderContent>  contentList = bjdcOrderContentService.queryForList(new Finder("select * from bjdc_order_content where orderid=:orderid ").setParam("orderid", bjdcOrder.getOrderid()), BjdcOrderContent.class);
								int count = 0 ;
								if((contentList!=null)&&(!contentList.isEmpty())){
									for (BjdcOrderContent bjdcOrderContent : contentList) {
										if(bjdcOrderContent.getResult()==1){
											count++;
										}
									}
										
									if(contentList.size() == count){
										//中奖了 返奖
										Double odds = 1.;
										for (BjdcOrderContent bjdcOrderContent : contentList) {
											odds*=bjdcOrderContent.getOdds();
										}
										Double award = odds*bjdcOrder.getBettingmoney().doubleValue();
										Double posttaxprice=award;
//										if(award>10000.){
//											posttaxprice=0.8*award;
//										}
										schemereward+=posttaxprice;
										
										Date dddd=new Date();
										bjdcOrderService.update(new Finder("update bjdc_order set result=1,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid and result=0 ").setParam("posttaxprize", award).setParam("orderid", bjdcOrder.getOrderid()).setParam("settletime", dddd).setParam("award", posttaxprice));
									}else{
										Date ddddd=new Date();
										bjdcOrderService.update(new Finder("update bjdc_order set result=3,settletime=:settletime,bettingwin=0,posttaxprize=0 where orderid=:orderid and result=0 ").setParam("orderid", bjdcOrder.getOrderid()).setParam("settletime", ddddd));
											
									}
								}
							}
							schemereward = schemereward*0.65;
							BjdcScheme bjdcsheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), BjdcScheme.class);
							BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", bjdcsheme.getMemberid2()), BetMember.class);
							
							if(bjdcsheme.getBuytype()==1){
								//跟单
								BjdcScheme sdsoccersheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", bjdcsheme.getSchemeid2()), BjdcScheme.class);
								if(sdsoccersheme.getPubstate()!=null&&sdsoccersheme.getPubstate()==2){
									BigDecimal guarantee = sdsoccersheme.getGuarantee();
									BigDecimal guaranteeMoney = null;
									if(guarantee!=null){
										guaranteeMoney = guarantee.multiply(bjdcsheme.getBettingmoney());
									}
									if(guarantee==null||schemereward > guaranteeMoney.doubleValue()){
										BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join bjdc_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 limit 1").setParam("schemeid2",bjdcsheme.getSchemeid2() ), BetMember.class);
										
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
										//agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
										BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
										//跟单方案大神佣金
										BigDecimal brokeragemoney = bettingwin.multiply(brokeragemember);
										bjdcsheme.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
										//跟单方案代理佣金
										BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
										bjdcsheme.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
										bjdcsheme.setBrokerageagentid(betAgent.getAgentid());
										
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
										bjdcsheme.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
										
										if(sdsoccersheme.getBrokeragemoney()!=null){
											sdsoccersheme.setBrokeragemoney(sdsoccersheme.getBrokeragemoney().add(brokeragemoney));
										}else{
											sdsoccersheme.setBrokeragemoney(brokeragemoney);
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
//													String errorMessage = e.getLocalizedMessage();
													e.printStackTrace();
												}
												
											}
											Date dd=new Date();
											BetScorerecord scorerecord = new BetScorerecord();
											scorerecord.setId(null);
											scorerecord.setMemberid2(sdsoccersheme.getMemberid2());
											scorerecord.setTime(dd);
											double f1 = new BigDecimal(schemereward).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
											double f2 = bjdcsheme.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
											scorerecord.setContent("用户"+betMember.getId2()+"投注的北单竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
											scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
											scorerecord.setChangescore(brokeragemoney.doubleValue());
											scorerecord.setBalance(sdmember.getScore());
											scorerecord.setState(1);
											scorerecord.setType(21);
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
											bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", bjdcsheme.getBrokeragemoney()).setParam("brokerageagentmoney", bjdcsheme.getBrokerageagentmoney()).setParam("brokeragemembermoney", bjdcsheme.getBrokeragemembermoney()).setParam("brokerageagentid", bjdcsheme.getBrokerageagentid()));
											bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdsoccersheme.getSchemeid()).setParam("brokeragemoney", sdsoccersheme.getBrokeragemoney()));
											
											//汇总投注
											//soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and state=0 and type=1 ").setParam("bettingscore", schemereward).setParam("settlementtime", dd).setParam("id", schemeid1));
											
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
									bbb = bjdcSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:bjdc limit 1").setParam("bjdc", "bjdc").setParam("agentid", betMember.getAgentid()),Double.class);
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
									bbb = bjdcSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:bjdc limit 1").setParam("bjdc", "bjdc").setParam("agentid", firstagentid),Double.class);
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
							betMember.setScore(betMember.getScore() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
							betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
							betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
//							betMember.setFreezingscore(betMember.getFreezingscore()-soccersheme.getBettingmoney().doubleValue());
//										betMemberService.update(betMember, true);
							Integer updatenum=null;
							for(int i=0;i<10;i++){
								if(updatenum==null){
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
								}else if(updatenum==0){
									betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
									betMember.setBankscore(betMember.getBankscore()+schemereward);
									betMember.setGamescore(betMember.getGamescore()+plusawards);
									betMember.setScore(betMember.getScore() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
									betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
									betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
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
//										String errorMessage = e.getLocalizedMessage();
										e.printStackTrace();
									}
									
								}
								
								Date dd=new Date();
								try {
									BetScorerecord scorerecord = new BetScorerecord();
									scorerecord.setId(null);
									scorerecord.setMemberid2(bjdcsheme.getMemberid2());
									scorerecord.setTime(dd);
									double f1 = new BigDecimal(schemereward+plusawards).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									double f2 = bjdcsheme.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
									scorerecord.setContent("北单竞猜方案号为"+schemeid1+"的投注方案押"+f2+"返奖"+f1+"元");
									scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + bjdcsheme.getBettingmoney().doubleValue());
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
									scorerecord.setType(20);
									scorerecord.setAgentid(betMember.getAgentid());
									scorerecord.setAgentparentid(betMember.getAgentparentid());
									scorerecord.setAgentparentids(betMember.getAgentparentids());
									betScorerecordService.save(scorerecord);
								} catch (Exception e) {
									e.printStackTrace();
								}
								//投注方案
								bjdcSchemeService.update(new Finder("update bjdc_scheme set plusawards=:plusawards,bettingwin=:bettingwin,situation=1,settlementtime=:settlementtime,pretaxamount=:pretaxamount WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("pretaxamount", schemereward).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("bettingwin", schemereward+plusawards));
								
								//汇总投注
								soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and state=0 and type=1 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
//										
								//代理退佣
								try {
									String agentid = betMember.getAgentid();
									double bettingmoney = (bjdcsheme.getBettingmoney()).doubleValue();
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
				String fid = request.getParameter("fid");
				
				BjdcResult result = bjdcResultService.queryForObject(new Finder("select * from bjdc_result where fid =:fid").setParam("fid", fid), BjdcResult.class);
				
				if(result!=null){
					if(!":".equals(result.getAllscore())){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次已有结果");
					}else{
						String starttime = bjdcResultService.queryForObject(new Finder("select starttime from bjdc_arrange where fid=:fid ").setParam("fid",result.getFid()),String.class);
						if(new SimpleDateFormat("yyyy-MM-dd").parse(starttime).before(new Date())){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("比賽未開始");
						}
						bjdcResultService.update(new Finder("update bjdc_result set state=2 where fid=:fid ").setParam("fid", fid));
						bjdcOrderContentService.update(new Finder("update bjdc_order_content set odds=1 ,result=1,resultname=:resultname where fid=:fid ").setParam("resultname", "无效比赛").setParam("fid", fid));
						returnObject.setStatus(ReturnDatas.SUCCESS);
						returnObject.setMessage("已设置为无效比赛");
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次不存在");
				}
			}else if("1".equals(request.getParameter("recalculate"))){
				//重新结算
				String schemeid = request.getParameter("schemeid");
				BjdcScheme scheme = null;
				if(schemeid!=null){
					scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), BjdcScheme.class);
				}else{
					scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", bjdcScheme.getId()), BjdcScheme.class);
				}
				
				if(scheme!=null&&scheme.getSituation()==1){
					BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", scheme.getMemberid2()), BetMember.class);
					betMember.setBankscore(betMember.getBankscore()-scheme.getBettingwin().doubleValue()+scheme.getPlusawards().doubleValue());
					betMember.setGamescore(betMember.getGamescore()-scheme.getPlusawards().doubleValue());
					betMember.setScore(betMember.getScore() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
					
					if(scheme.getBuytype()==2){
						if(scheme.getBrokeragemembermoney()!=null){
							betMember.setBankscore(betMember.getBankscore()-scheme.getBrokeragemoney().doubleValue());
							betMember.setScore(betMember.getScore()-scheme.getBrokeragemoney().doubleValue());
						}
						bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = 0 where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()));
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
						if(scheme.getBrokeragemembermoney()!=null){
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
					
					List<String> fids = bjdcSchemeMatchService.queryForList(new Finder("select fid from bjdc_scheme_match where schemeid = :schemeid group by schemeid").setParam("schemeid", scheme.getSchemeid()), String.class);
					List<BjdcResult> datas=bjdcResultService.queryForList(new Finder("select * from bjdc_result  where  state = 1 and fid in (:fid)").setParam("fid", fids), BjdcResult.class);
					Map<String,String> map = new HashMap<String, String>();
					List<BjdcPlaymethodOddsname> bjdcPlaymethodOddsnameList = bjdcPlaymethodOddsnameService.queryForList(new Finder("select oddsname,shortname from bjdc_playmethod_oddsname "), BjdcPlaymethodOddsname.class);
					for (BjdcPlaymethodOddsname bjdcPlaymethodOddsname : bjdcPlaymethodOddsnameList) {
						map.put(bjdcPlaymethodOddsname.getOddsname(), bjdcPlaymethodOddsname.getShortname());
					}
					if(datas!=null&&!datas.isEmpty()){
						for(BjdcResult bjdcResult : datas){
							
							String letpoints = bjdcResult.getLetpoints();
							String[] halfscore = bjdcResult.getHalfscore().split(":");
							String[] allscore = bjdcResult.getAllscore().split(":");
							String lefthalfscore = halfscore[0];
							String righthalfscore = halfscore[1];
							String leftallscore = allscore[0];
							String rightallscore = allscore[1];
							
							//胜平负
							String spf = "";
							if(Integer.valueOf(leftallscore)+Integer.valueOf(letpoints) > Integer.valueOf(rightallscore)){
								spf = "rqwin";
							}else if(Integer.valueOf(leftallscore)+Integer.valueOf(letpoints) == Integer.valueOf(rightallscore)){
								spf = "rqflat";
							}else{
								spf = "rqlose";
							}
							List<BjdcOrderContent> spfList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and SUBSTRING(oddsname,1,2)=:str AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str", "rq"), BjdcOrderContent.class);
							if(!spfList.isEmpty()){
								for (BjdcOrderContent bjdcOrderContent : spfList) {
									if(spf.equals(bjdcOrderContent.getOddsname())){
										//中
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(spf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
									}else{
										//没
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(spf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
									}
								}
							}
							//进球数
							String jqs = "ball";
							Integer alljqs = Integer.valueOf(leftallscore)+Integer.valueOf(rightallscore);
							if(alljqs>=7){
								jqs = "ball7";
							}else{
								jqs += alljqs;
							}
							List<BjdcOrderContent> ballList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and SUBSTRING(oddsname,1,4)=:str AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str", "ball"), BjdcOrderContent.class);
							if(!ballList.isEmpty()){
								for (BjdcOrderContent bjdcOrderContent : ballList) {
									if(jqs.equals(bjdcOrderContent.getOddsname())){
										//中
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(jqs)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
									}else{
										//没
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(jqs)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
									}
								}
							}
							//上下单双
							String sxds = "";
							if(alljqs==0||alljqs==2){
								sxds = "downdouble";
							}else if(alljqs==1){
								sxds = "downsingle";
							}else if(alljqs%2==1){
								sxds = "topsingle";
							}else{
								sxds = "topdouble";
							}
							List<BjdcOrderContent> sxdsList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and (oddsname=:o1 or oddsname=:o2 or oddsname=:o3 or oddsname=:o4 ) AND result=0").setParam("fid", bjdcResult.getFid()).setParam("o1", "downdouble").setParam("o2", "downsingle").setParam("o3", "topsingle").setParam("o4", "topdouble"), BjdcOrderContent.class);
							if(!sxdsList.isEmpty()){
								for (BjdcOrderContent bjdcOrderContent : sxdsList) {
									if(sxds.equals(bjdcOrderContent.getOddsname())){
										//中
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:o1 ").setParam("settletime", new Date()).setParam("resultname", map.get(sxds)).setParam("fid", bjdcOrderContent.getFid()).setParam("o1", bjdcOrderContent.getOddsname()));
									}else{
										//没
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:o1 ").setParam("settletime", new Date()).setParam("resultname", map.get(sxds)).setParam("fid", bjdcOrderContent.getFid()).setParam("o1", bjdcOrderContent.getOddsname()));
									}
								}
							}
							//半全场
							String bqc = "sp";
							if(Integer.valueOf(lefthalfscore) > Integer.valueOf(righthalfscore)){
								bqc += "3";
							}else if(Integer.valueOf(lefthalfscore) == Integer.valueOf(righthalfscore)){
								bqc += "1";
							}else{
								bqc += "0";
							}
							if(Integer.valueOf(leftallscore) > Integer.valueOf(rightallscore)){
								bqc += "3";
							}else if(Integer.valueOf(leftallscore) == Integer.valueOf(rightallscore)){
								bqc += "1";
							}else{
								bqc += "0";
							}
							List<BjdcOrderContent> bqcList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and SUBSTRING(oddsname,1,2)=:str AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str", "sp"), BjdcOrderContent.class);
							if(!bqcList.isEmpty()){
								for (BjdcOrderContent bjdcOrderContent : bqcList) {
									if(bqc.equals(bjdcOrderContent.getOddsname())){
										//中
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(bqc)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
									}else{
										//没
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(bqc)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
									}
								}
							}
							//比分
							String bf = "";
							if(Integer.valueOf(leftallscore) > Integer.valueOf(rightallscore)){
								bf = "win"+leftallscore+rightallscore;
								if(bjdcPlaymethodOddsnameService.queryForObject(new Finder("select count(*) from bjdc_playmethod_oddsname where oddsname=:oddsname ").setParam("oddsname", bf), Integer.class)==0){
									bf = "win3A";
								}
							}else if(Integer.valueOf(leftallscore) == Integer.valueOf(rightallscore)){
								bf = "flat"+leftallscore+rightallscore;
								if(bjdcPlaymethodOddsnameService.queryForObject(new Finder("select count(*) from bjdc_playmethod_oddsname where oddsname=:oddsname ").setParam("oddsname", bf), Integer.class)==0){
									bf = "flat1A";
								}
							}else{
								bf = "lose"+leftallscore+rightallscore;
								if(bjdcPlaymethodOddsnameService.queryForObject(new Finder("select count(*) from bjdc_playmethod_oddsname where oddsname=:oddsname ").setParam("oddsname", bf), Integer.class)==0){
									bf = "lose0A";
								}
							}
							List<BjdcOrderContent> bfList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and (SUBSTRING(oddsname,1,3)=:str1 or SUBSTRING(oddsname,1,4)=:str2 or SUBSTRING(oddsname,1,4)=:str3 ) AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str1", "win").setParam("str2", "flat").setParam("str3", "lose"), BjdcOrderContent.class);
							if(!bfList.isEmpty()){
								for (BjdcOrderContent bjdcOrderContent : bfList) {
									if(bf.equals(bjdcOrderContent.getOddsname())){
										//中
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str1 ").setParam("settletime", new Date()).setParam("resultname", map.get(bf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str1", bjdcOrderContent.getOddsname()));
									}else{
										//没
										bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str1 ").setParam("settletime", new Date()).setParam("resultname", map.get(bf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str1", bjdcOrderContent.getOddsname()));
									}
								}
							}
							
							
						}
					}
					
					List<String> schemelist = bjdcSchemeService.queryForList(new Finder("select schemeid from bjdc_scheme where schemeid = :schemeid ").setParam("schemeid", scheme.getSchemeid()), String.class);
					if(schemelist!=null){
						for (String schemeid1 : schemelist) {
							Integer untreatedcontent = bjdcOrderService.queryForObject(new Finder("select count(*) from bjdc_order a right join bjdc_order_content b on b.orderid=a.orderid where a.schemeid=:schemeid and b.result=0 ").setParam("schemeid", schemeid1), Integer.class);
							if(untreatedcontent==0){
								try{
									
									List<BjdcOrder> orderList = bjdcOrderService.queryForList(new Finder("select * from bjdc_order where state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), BjdcOrder.class);
									
									if((orderList !=null)&&(!orderList.isEmpty())){
										Double schemereward=0.;
										for (BjdcOrder bjdcOrder : orderList) {
											List<BjdcOrderContent>  contentList = bjdcOrderContentService.queryForList(new Finder("select * from bjdc_order_content where orderid=:orderid ").setParam("orderid", bjdcOrder.getOrderid()), BjdcOrderContent.class);
	//										int flag = 0;
											int count = 0 ;
											if((contentList!=null)&&(!contentList.isEmpty())){
												for (BjdcOrderContent bjdcOrderContent : contentList) {
	//												if(soccerLeagueOrderContent.getResult()==0){
	//													flag++;
	//												}
													if(bjdcOrderContent.getResult()==1){
														count++;
													}
												}
													
												if(contentList.size() == count){
													//中奖了 返奖
													Double odds = 1.;
													for (BjdcOrderContent bjdcOrderContent : contentList) {
														odds*=bjdcOrderContent.getOdds();
													}
													Double award = odds*(bjdcOrder.getBettingmoney().doubleValue())*Double.valueOf(0.65);
													Double posttaxprice=award;
//													if(award>10000.){
//														posttaxprice=0.8*award;
//													}
													schemereward+=posttaxprice;
													
													Date dddd=new Date();
													bjdcOrderService.update(new Finder("update bjdc_order set result=1,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid  ").setParam("posttaxprize", award).setParam("orderid", bjdcOrder.getOrderid()).setParam("settletime", dddd).setParam("award", posttaxprice));
												}else{
													Date ddddd=new Date();
													bjdcOrderService.update(new Finder("update bjdc_order set result=3,settletime=:settletime,bettingwin=0,posttaxprize=0 where orderid=:orderid  ").setParam("orderid", bjdcOrder.getOrderid()).setParam("settletime", ddddd));
												}
											}
										}
										
										BjdcScheme bjdcsheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), BjdcScheme.class);
										betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", bjdcsheme.getMemberid2()), BetMember.class);
										
										
										
										
										
										try {
											if(bjdcsheme.getBuytype()==1){
												//跟单
												BjdcScheme sdsoccersheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", bjdcsheme.getSchemeid2()), BjdcScheme.class);
												if(sdsoccersheme.getPubstate()!=null&&sdsoccersheme.getPubstate()==2){
													BigDecimal guarantee = sdsoccersheme.getGuarantee();
													BigDecimal guaranteeMoney = null;
													if(guarantee!=null){
														guaranteeMoney = guarantee.multiply(bjdcsheme.getBettingmoney());
													}
													if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
														BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join soccer_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 limit 1").setParam("schemeid2",bjdcsheme.getSchemeid2() ), BetMember.class);
														
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
														//agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
														BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
														//跟单方案大神佣金
														BigDecimal brokeragemoney = bettingwin.multiply(brokeragemember);
														bjdcsheme.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
														//跟单方案代理佣金
														BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
														bjdcsheme.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
														bjdcsheme.setBrokerageagentid(betAgent.getAgentid());
														
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
														bjdcsheme.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
														
														if(sdsoccersheme.getBrokeragemoney()!=null){
															sdsoccersheme.setBrokeragemoney(sdsoccersheme.getBrokeragemoney().add(brokeragemoney));
														}else{
															sdsoccersheme.setBrokeragemoney(brokeragemoney);
														}
//														sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
														sdmember.setBankscore(sdmember.getBankscore()+ brokeragemoney.doubleValue());
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
//																	String errorMessage = e.getLocalizedMessage();
//																	e.printStackTrace();
																	System.out.println(e);
																}
																
															}
															dd=new Date();
															scorerecord = new BetScorerecord();
															scorerecord.setId(null);
															scorerecord.setMemberid2(sdsoccersheme.getMemberid2());
															scorerecord.setTime(dd);
															scorerecord.setContent("重新结算北单佣金");
															scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
															scorerecord.setChangescore(brokeragemoney.doubleValue());
															scorerecord.setBalance(sdmember.getScore());
															scorerecord.setState(1);
															scorerecord.setType(21);
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
															bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", bjdcsheme.getBrokeragemoney()).setParam("brokerageagentmoney", bjdcsheme.getBrokerageagentmoney()).setParam("brokeragemembermoney", bjdcsheme.getBrokeragemembermoney()).setParam("brokerageagentid", bjdcsheme.getBrokerageagentid()));
															bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdsoccersheme.getSchemeid()).setParam("brokeragemoney", sdsoccersheme.getBrokeragemoney()));
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
												bbb = bjdcSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:bjdc limit 1").setParam("bjdc", "bjdc").setParam("agentid", betMember.getAgentid()),Double.class);
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
												bbb = bjdcSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:bjdc limit 1").setParam("bjdc", "bjdc").setParam("agentid", firstagentid),Double.class);
											}
										} catch (Exception e) {
											System.out.println(e);
										}
										
										Double plusawards=0.;
										if(bbb!=null){
											plusawards=bbb*schemereward;
										}
										betMember.setBankscore(betMember.getBankscore()+schemereward);
										betMember.setGamescore(betMember.getGamescore()+plusawards);
										betMember.setScore(betMember.getScore() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
										betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
										betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
										
										Integer updatenum=null;
										for(int i=0;i<10;i++){
											if(updatenum==null){
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
											}else if(updatenum==0){
												betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
												betMember.setBankscore(betMember.getBankscore()+schemereward);
												betMember.setGamescore(betMember.getGamescore()+plusawards);
												betMember.setScore(betMember.getScore() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
												betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
												betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
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
												scorerecord.setMemberid2(bjdcsheme.getMemberid2());
												scorerecord.setTime(dd);
												scorerecord.setContent("重新结算北单返奖");
												scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + bjdcsheme.getBettingmoney().doubleValue());
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
												scorerecord.setType(20);
												scorerecord.setAgentid(betMember.getAgentid());
												scorerecord.setAgentparentid(betMember.getAgentparentid());
												scorerecord.setAgentparentids(betMember.getAgentparentids());
												betScorerecordService.save(scorerecord);
											} catch (Exception e) {
												System.out.println(e);
											}
											//投注方案
											bjdcSchemeService.update(new Finder("update bjdc_scheme set plusawards=:plusawards,bettingwin=:xxxxk, situation=1,settlementtime=:settlementtime,pretaxamount=:xxxx WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("xxxxk", schemereward+plusawards).setParam("xxxx", schemereward));
											//汇总投注
											soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id  and type=1 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
											//连红========================================
											if(bjdcsheme.getBuytype()==2){
												List<Double> hong = soccerAllbettingService.queryForList(new Finder("select bettingscore from soccer_allbetting" +
														" where memberid2=:id2 and state=1 and buytype=2 order by bettingtime desc limit 30 ").setParam("id2", bjdcsheme.getMemberid2()), Double.class);
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
												betMemberService.update(new Finder("update bet_member set lianhong =:lianhong where id2=:id2 ").setParam("lianhong", lianhong).setParam("id2", bjdcsheme.getMemberid2()));
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
				}
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
			String fid = request.getParameter("fid");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			BjdcResult result = bjdcArrangeService.queryForObject(new Finder("select a.periodnum,b.halfscore,b.allscore,a.num,a.fid,a.matchid2,a.starttime as matchtime,a.hometeam,a.hometeamid2 as left_team_id2,a.guestteam,a.guestteamid2 as right_team_id2 from bjdc_arrange a left join bjdc_result b on a.fid = b.fid where a.fid = :fid").setParam("fid", fid), BjdcResult.class);
			String halfscore1 = "";
			String halfscore2 = "";
			String allscore1 = "";
			String allscore2 = "";
			String halfscore = result.getHalfscore();
			String allscore = result.getAllscore();
			if(":".equals(halfscore)){
				halfscore = null;
			}
			if(":".equals(allscore)){
				allscore = null;
			}
			if(halfscore!=null){
				String[] halfscores = halfscore.split(":");
				halfscore1 = halfscores[0];
				halfscore2 = halfscores[1];
			}
			if(allscore!=null){
				String[] allscores = allscore.split(":");
				allscore1 = allscores[0];
				allscore2 = allscores[1];
			}
			result.setState(1);
			result.setIssettle(3);
			returnObject.setData(result);
			model.addAttribute("halfscore1", halfscore1);
			model.addAttribute("halfscore2", halfscore2);
			model.addAttribute("allscore1", allscore1);
			model.addAttribute("allscore2", allscore2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/bjdcscheme/bjdcresultAdd";
		}else if("2".equals(request.getParameter("k"))){
			String schemeid = request.getParameter("schemeid");
			String fid = request.getParameter("fid");
			String oddsname = request.getParameter("oddsname");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			BjdcScheme scheme = null;
			if(schemeid!=null){
				scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), BjdcScheme.class);
			}
			if(scheme==null){
				model.addAttribute("exception", "无此方案");
				return "/errorpage/error";
			}
			BjdcSchemeMatch match= bjdcSchemeMatchService.queryForObject(new Finder("select a.schemeid,a.id,a.fid,a.dan,b.matchname,b.hometeam,b.guestteam,b.starttime,b.endtime,b.num from bjdc_scheme_match a LEFT JOIN bjdc_arrange b on a.fid = b.fid  where a.schemeid=:schemeid and b.fid = :fid  order by a.id").setParam("schemeid", schemeid).setParam("fid", fid), BjdcSchemeMatch.class);
			BjdcOrderContent orderContent = bjdcSchemeMatchService.queryForObject(new Finder("select a.oddsname,a.fid,a.odds,a.result,a.resultname,b.oddsrealname,b.betname,b.playid,c.schemeid from bjdc_order_content a LEFT JOIN bjdc_playmethod_oddsname b on a.oddsname=b.oddsname LEFT JOIN bjdc_order c on a.orderid = c.orderid where c.schemeid=:schemeid and a.oddsname=:oddsname and a.fid = :fid group by c.schemeid, a.fid,a.oddsname").setParam("schemeid", schemeid).setParam("oddsname", oddsname).setParam("fid", fid), BjdcOrderContent.class);
				
			if(orderContent!=null){
				    if("rqwin".equals(oddsname)||"rqflat".equals(oddsname)||"rqlose".equals(oddsname)){
				    	try{
				    		String cached2 = (String)cached.getCached(("bdsfp_"+orderContent.getFid().toString()).getBytes());
					    	if(cached2!=null){
					    		ObjectMapper mmmm=new ObjectMapper();
					    		BjdcOdds readValue = mmmm.readValue(cached2, BjdcOdds.class);
					    		String letpoints = readValue.getLetpoints();
					    		String betname = orderContent.getBetname().toString();
					    		orderContent.setBetname(betname+"("+ letpoints+")");
					    	}else{
					    		String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", orderContent.getFid()), String.class);
						    	String betname = orderContent.getBetname().toString();
						    	orderContent.setBetname(betname+"("+ letpoints+")");
					    	}
				    	}catch (Exception e) {
							e.printStackTrace();
							String letpoints = bjdcOddsService.queryForObject(new Finder("select letpoints from bjdc_odds where fid = :fid ").setParam("fid", orderContent.getFid()), String.class);
					    	String betname = orderContent.getBetname().toString();
					    	orderContent.setBetname(betname+"("+ letpoints+")");
					    	
						}
				    }
			}
			match.setNum(match.getNum());
			returnObject.setData(scheme);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("match", match);
			model.addAttribute("orderContent", orderContent);
			return "/lottery/bjdcscheme/bjdcschemeoddmodify";
		}
		
		
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/bjdcscheme/bjdcschemeCru";
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
				bjdcSchemeService.deleteById(id,BjdcScheme.class);
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
			bjdcSchemeService.deleteByIds(ids,BjdcScheme.class);
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
			System.out.println("北单手动结算有异常");
		}
	}
}
