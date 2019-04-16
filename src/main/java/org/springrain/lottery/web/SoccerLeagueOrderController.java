package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueOrderContentService;
import org.springrain.lottery.service.ISoccerLeagueOrderService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.WeekOfDate;
import org.springrain.frame.cached.ICached;
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
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.web.SoccerLeagueOrder
 */
@Controller
@RequestMapping(value="/soccerleagueorder")
public class SoccerLeagueOrderController  extends BaseController {
	@Resource
	private ISoccerLeagueOrderService soccerLeagueOrderService;
	@Resource
	private ISoccerLeagueOrderContentService soccerLeagueOrderContentService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	private String listurl="/lottery/soccerleagueorder/soccerleagueorderList";
	
	
	   
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
			return  "/lottery/soccerleagueorder/soccerleagueordercontent";
		}else if("2".equals(request.getParameter("k"))){
			//查询会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerleagueorder/soccermemberList";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String result = request.getParameter("result");
			String memberid2 = request.getParameter("memberid2");
			String playmethodid = request.getParameter("playmethodid");
			if("100".equals(playmethodid)){
				playmethodid = null;
			}
			if("100".equals(result)){
				result = null;
			}
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
			List<SoccerLeagueOrder> datas = null;
			Integer contentTotal = 0;
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				 datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("memberid2", memberid2).setParam("result", result).setParam("playmethodid", playmethodid),SoccerLeagueOrder.class,page);
				 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Integer.class);
				 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
				 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
			}else{
			     datas= soccerLeagueOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid LEFT JOIN soccer_league_playmethod c on b.playmethodid = c.id where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),SoccerLeagueOrder.class,page);
				 contentTotal = soccerLeagueOrderService.queryForObject(new Finder("select count(a.id) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Integer.class);
				 bettingmoneyTotal =soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
				 bettingwinTotal = soccerLeagueOrderService.queryForObject(new Finder("select sum(a.bettingwin) from soccer_league_order a LEFT JOIN soccer_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
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
			model.addAttribute("contentTotal", contentTotal);
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
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueOrder soccerLeagueOrder) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueOrderService.findDataExportExcel(null,listurl, page,SoccerLeagueOrder.class,soccerLeagueOrder);
		String fileName="soccerLeagueOrder"+GlobalStatic.excelext;
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
	@RequestMapping("/update")
	@ResponseBody      
	public ReturnDatas saveorupdate(Model model,SoccerLeagueOrder soccerLeagueOrder,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("cancel"))){
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
//								     betScorerecord.setGamescore(BigDecimal.valueOf(member.getGamescore()));
//								     betScorerecord.setBankscore(BigDecimal.valueOf(member.getBankscore()));
//								     betScorerecord.setFreezescore(BigDecimal.valueOf(member.getFreezingscore()));
//								     betScorerecord.setAgentid(member.getAgentid());
//								     betScorerecord.setAgentparentid(member.getAgentparentid());
//								     betScorerecord.setAgentparentids(member.getAgentparentids());
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
//								     //betOptLogService.saveoptLog(tool,ip,details);;
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
			}else{
				//soccerLeagueOrderService.saveorupdate(soccerLeagueOrder);
			}
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
		return "/lottery/soccerleagueorder/soccerleagueorderCru";
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
				soccerLeagueOrderService.deleteById(id,SoccerLeagueOrder.class);
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
			soccerLeagueOrderService.deleteByIds(ids,SoccerLeagueOrder.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
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
