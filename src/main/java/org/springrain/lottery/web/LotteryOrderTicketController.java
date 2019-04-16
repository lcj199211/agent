package org.springrain.lottery.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.LotteryScheme;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.ILotteryOrderService;
import org.springrain.lottery.service.ILotterySchemeService;
import org.springrain.system.service.IDicDataService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;



/**
 * TODO 大乐透出票
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.web.SoccerLeagueOrder
 */
@Controller
@RequestMapping(value="/lotteryorderticket")
public class LotteryOrderTicketController extends BaseController {
	@Resource
	private ILotteryOrderService lotteryOrderService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ILotterySchemeService lotterySchemeService;
	@Resource
	private ICached cached;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IBetAgentService betAgentService;
	
	private String listurl="/lottery/lotteryorderticket/lotteryorderList";
	
	
	   
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
	public String list(HttpServletRequest request, Model model,LotteryOrder lotteryOrder) 			{
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("k"))){
			try {
				//订单详情
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				String orderid = request.getParameter("orderid");
				List<LotteryOrder> datas=lotteryOrderService.queryForList(new Finder("select * from lottery_order where orderid = :orderid ").setParam("orderid", orderid),LotteryOrder.class);
				returnObject.setData(datas);
				returnObject.setQueryBean(new LotteryOrder());
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
			} catch (Exception e) {
				System.out.println(e);
			}
			return  "/lottery/lotteryorderticket/lotteryordercontent";
		}else{
			try {
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				Page page = newPage(request);
				String starttime = request.getParameter("startTime");
				String endtime = request.getParameter("endTime");
				String issuestate = request.getParameter("issuestate");
				String memberid2 = request.getParameter("memberid2");
				String phaseno = request.getParameter("phaseno");
				if("100".equals(issuestate)){
					issuestate = null;
				}
				if(StringUtils.isBlank(memberid2)){
					memberid2=null;
				}
				if(StringUtils.isBlank(phaseno)){
					phaseno=null;
				}
				if(StringUtils.isBlank(starttime)){
					starttime="0000-01-01";
				}
				if(StringUtils.isBlank(endtime)){
					endtime="9999-01-01";
				}
				java.sql.Date startDate = java.sql.Date.valueOf(starttime);
				java.sql.Date endDate=java.sql.Date.valueOf(endtime);
				List<LotteryOrder> datas = null;
				if(starttime=="0000-01-01" && endtime=="9999-01-01"){
					 datas= lotteryOrderService.queryForList(new Finder("select a.*,b.bettingtime from lottery_order a LEFT JOIN lottery_scheme b on a.schemeid = b.schemeid where (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :agentids) order by a.id desc").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("phaseno", phaseno).setParam("issuestate", issuestate),LotteryOrder.class,page);
				}else{
				     datas= lotteryOrderService.queryForList(new Finder("select a.*,b.bettingtime from lottery_order a LEFT JOIN lottery_scheme b on a.schemeid = b.schemeid where b.bettingtime>=:starttime and b.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :agentids) order by a.id desc").setParam("agentid", agentId).setParam("agentids", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("phaseno", phaseno).setParam("issuestate", issuestate).setParam("memberid2", memberid2),LotteryOrder.class,page);
				}
				if(starttime=="0000-01-01"){
					startDate=null;
				}
				if(endtime=="9999-01-01"){
					endDate=null;
				}

				returnObject.setQueryBean(lotteryOrder);
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
	public String schemelist(HttpServletRequest request, Model model, LotteryScheme lotteryScheme) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<LotteryOrder> datas= lotteryOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.bettingip,c.name as playmethod from lottery_order a LEFT JOIN lottery_scheme b on a.schemeid = b.schemeid LEFT JOIN lottery_playmethod c on a.playtype = c.id where a.schemeid = :schemeid order by b.id").setParam("schemeid", schemeid),LotteryOrder.class,page);
			
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new LotteryOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/lotteryorderticket/lotteryschemeorderList";
		}else if("2".equals(request.getParameter("k"))){
			//查询会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member where id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/lotteryorder/lotterymemberList";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String memberid2 = request.getParameter("memberid2");
			String phaseno = request.getParameter("phaseno");
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
			if(StringUtils.isBlank(phaseno)){
				phaseno=null;
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<LotteryOrder> datas = null;
			//大乐透出票倍数
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			String company = "";
			if(",A101,".equals(agent.getParentids())){
				company = agentId;
			}else{
				company = agent.getParentids().split(",")[2];
			}
			Double value = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:id and remark=:company ").setParam("id", "issuebetmultiple_dlt").setParam("company", company), Double.class);
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 //今日
					datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2 left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("phaseno", phaseno).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
				 }else if("2".equals(time)){
					 //昨日
					 datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2 left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("phaseno", phaseno).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
				 }else if("3".equals(time)){
					 //本周
					 datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2  left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("phaseno", phaseno).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
				 }else if("4".equals(time)){
					 //上周
					 datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2  left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("phaseno", phaseno).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
				 }else if("5".equals(time)){
					 //本月
					 datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2  left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and date_format(b.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("phaseno", phaseno).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
				 }else{
					 datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2 left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and (:memberid2 is null or a.memberid2 =:memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("phaseno", phaseno).setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
				 }
			}else{
				datas= lotteryOrderService.queryForList(new Finder("select a.*,d.name as playmethod,c.nickname as membernickname,b.bettingtime from lottery_order a left join bet_member c on c.id2=a.memberid2 left join lottery_scheme b on a.schemeid=b.schemeid left join lottery_playmethod d on a.playtype=d.id where  c.isinternal=0 and c.isissue=1 and b.bettingtime>=:starttime and b.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:phaseno is null or a.phaseno =:phaseno) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("phaseno", phaseno).setParam("aid", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("issuestate", issuestate),LotteryOrder.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			returnObject.setQueryBean(lotteryScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("value", value);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/lotteryorderticket/lotteryschemeList";
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
	public  ReturnDatas listjson(HttpServletRequest request, Model model,LotteryOrder lotteryOrder) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<LotteryOrder> datas=lotteryOrderService.findListDataByFinder(null,page,LotteryOrder.class,lotteryOrder);
			returnObject.setQueryBean(lotteryOrder);
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
		return "/lottery/lotteryorder/lotteryorderLook";
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
			 LotteryOrder lotteryOrder = lotteryOrderService.findLotteryOrderById(id);
			 returnObject.setData(lotteryOrder);
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
	public ReturnDatas updateissuestate(Model model,LotteryScheme lotteryScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
			String orderid = request.getParameter("orderid");
			String schemeid = request.getParameter("schemeid");
			LotteryOrder order = lotterySchemeService.queryForObject(new Finder("select * from lottery_order where orderid=:orderid ").setParam("orderid", orderid), LotteryOrder.class);
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
					lotterySchemeService.update(new Finder("update lottery_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", orderid));
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<LotteryOrder> order2 = lotterySchemeService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid ").setParam("schemeid", schemeid), LotteryOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(LotteryOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				lotterySchemeService.update(new Finder("update lottery_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", lotteryScheme.getSchemeid()));
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
	public ReturnDatas schemeUpdateissuestate(Model model,LotteryScheme lotteryScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
			String schemeid = request.getParameter("schemeid");
			List<LotteryOrder> order = lotterySchemeService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid ").setParam("schemeid", schemeid), LotteryOrder.class);
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
						lotterySchemeService.update(new Finder("update lottery_order set issuestate=3,systemissue=3 where orderid=:orderid ").setParam("orderid", order.get(i).getOrderid()));
					}
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
			}
			List<LotteryOrder> order2 = lotterySchemeService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid ").setParam("schemeid", schemeid), LotteryOrder.class);
			List<Integer>issuestate=new ArrayList<Integer>();
			for(LotteryOrder orders : order2){
				issuestate.add(orders.getIssuestate());
			}
			int ii=0;
			for(int i=0;i<issuestate.size();i++){
				if(issuestate.get(i)==3){
					ii++;
				}
			}
			if(ii==issuestate.size()){
				lotterySchemeService.update(new Finder("update lottery_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", lotteryScheme.getSchemeid()));
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
	public ReturnDatas schemeSystemissue(Model model,LotteryScheme lotteryScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
				//系统出票
				String schemeid = request.getParameter("schemeid");
				List<LotteryOrder> order = lotteryOrderService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid ").setParam("schemeid", schemeid), LotteryOrder.class);
				if("1".equals(request.getParameter("k"))){
					String pass = request.getParameter("pass");
					boolean matches = pass.matches("^[1-9][0-9]*$");
					if(matches == true){
						for(int i=0;i<order.size();i++){
						if(Integer.valueOf(pass) > order.get(i).getBetmultiple()){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("倍数超越投注倍数");
						}else{
							if(order.get(i).getIssuestate()==4){
								lotteryOrderService.update(new Finder("update lottery_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", order.get(i).getOrderid()));
							}
							lotteryOrderService.update(new Finder("update lottery_order set systemissue=1,issuebetmultiple=:issuebetmultiple where orderid=:orderid ").setParam("orderid", order.get(i).getOrderid()).setParam("issuebetmultiple", pass));
						}
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数不正确");
					}
				}else{
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
					List<LotteryOrder> order2 = lotterySchemeService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid ").setParam("schemeid", schemeid), LotteryOrder.class);
					List<Integer>issuestate=new ArrayList<Integer>();
					for(LotteryOrder orders : order2){
						issuestate.add(orders.getIssuestate());
					}
					int ii=0;
					for(int i=0;i<issuestate.size();i++){
						if(issuestate.get(i)==3){
							ii++;
						}
					}
					if(ii==issuestate.size()){
						lotterySchemeService.update(new Finder("update lottery_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", lotteryScheme.getSchemeid()));
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
	public ReturnDatas systemissue(Model model,LotteryScheme lotteryScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
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
						LotteryOrder order = lotteryOrderService.queryForObject(new Finder("select * from lottery_order where orderid=:orderid ").setParam("orderid", orderid), LotteryOrder.class);
						if(Integer.valueOf(pass) > order.getBetmultiple()){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("倍数超越投注倍数");
						}else{
							if(order.getIssuestate()==4){
								lotteryOrderService.update(new Finder("update lottery_order set bettingretrytime=0,channelid=null,issuestate=0 where orderid=:orderid").setParam("orderid", orderid));
							}
							lotteryOrderService.update(new Finder("update lottery_order set systemissue=1,issuebetmultiple=:issuebetmultiple where orderid=:orderid ").setParam("orderid", orderid).setParam("issuebetmultiple", pass));
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数不正确");
					}
				}else{
					LotteryOrder order = lotterySchemeService.queryForObject(new Finder("select * from lottery_order where orderid=:orderid ").setParam("orderid", orderid), LotteryOrder.class);
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
					List<LotteryOrder> order2 = lotterySchemeService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid ").setParam("schemeid", schemeid), LotteryOrder.class);
					List<Integer>issuestate=new ArrayList<Integer>();
					for(LotteryOrder orders : order2){
						issuestate.add(orders.getIssuestate());
					}
					int ii=0;
					for(int i=0;i<issuestate.size();i++){
						if(issuestate.get(i)==3){
							ii++;
						}
					}
					if(ii==issuestate.size()){
						
						lotterySchemeService.update(new Finder("update lottery_scheme set issuestate=2 where schemeid=:schemeid ").setParam("schemeid", lotteryScheme.getSchemeid()));
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
