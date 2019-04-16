package org.springrain.lottery.web;

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
import org.springrain.lottery.entity.RenjiuScheme;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.lottery.entity.SoccerLeagueResult;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IRenjiuSchemeService;
import org.springrain.lottery.utils.WeekOfDate;
import org.springrain.system.service.IDicDataService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * TODO 足球出票
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.web.SoccerLeagueOrder
 */
@Controller
@RequestMapping(value="/renjiuschemeticket")
public class RenjiuSchemeTicketController  extends BaseController {
	
	@Resource
	private IRenjiuSchemeService renjiuSchemeService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IDicDataService dicDataService;
	
	private String listurl="/lottery/renjiuschemeticket/renjiuschemeList";
	
	
	   
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
	public String list(HttpServletRequest request, Model model,RenjiuScheme renjiuscheme) 
			throws Exception {
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
		List<RenjiuScheme> datas = null;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			 datas= renjiuSchemeService.queryForList(new Finder("select * from renjiu_scheme where (:memberid2 is null or memberid2 like :memberid2) and (:issuestate is null or issuestate = :issuestate) order by id desc").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
		}else{
		     datas= renjiuSchemeService.queryForList(new Finder("select * from renjiu_scheme where substr(bettingtime,1,10)>=:starttime and substr(bettingtime,1,10)<=:endtime and (:memberid2 is null or memberid2 like :memberid2) and (:issuestate is null or issuestate = :issuestate) order by id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("issuestate", issuestate).setParam("memberid2", memberid2),RenjiuScheme.class,page);
		}
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}

		returnObject.setQueryBean(renjiuscheme);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("startTime", startDate);
		model.addAttribute("endTime", endDate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;		
	}
	
	@RequestMapping("/schemelist")
	public String schemelist(HttpServletRequest request, Model model,RenjiuScheme renjiuScheme) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		if("2".equals(request.getParameter("k"))){
			//查询会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String memberid2 = request.getParameter("memberid2");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 = :id2 ").setParam("id2", memberid2),BetMember.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/renjiuscheme/renjiumemberList";
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
			List<RenjiuScheme> datas = null;
			//任九出票倍数
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			String company = "";
			if(",A101,".equals(agent.getParentids())){
				company = agentId;
			}else{
				company = agent.getParentids().split(",")[2];
			}
			Double value = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:id and remark=:company ").setParam("id", "issuebetmulriple_rj").setParam("company", company), Double.class);
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 //今日
					datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=1 and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
				}else if("2".equals(time)){
					 //昨日
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=1 and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
				}else if("3".equals(time)){
					 //本周
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=1 and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
				}else if("4".equals(time)){
					 //上周
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on a.memberid2=c.id2 where c.isinternal=0 and c.isissue=1 and date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%u").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
				}else if("5".equals(time)){
					 //本月
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on c.id2=a.memberid2 where c.isinternal=0 and c.isissue=1 and date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
				}else{
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on c.id2=a.memberid2 where c.isinternal=0 and c.isissue=1 and  (:memberid2 is null or a.memberid2 = :memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
				}
				
			}else{
				datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname from renjiu_scheme a left join bet_member c on c.id2=a.memberid2 where c.isinternal=0 and c.isissue=1 and b.bettingtime >=:starttime and a.bettingtime <=:endtime and (:memberid2 is null or a.memberid2 =:memberid2) and (:issuestate is null or a.issuestate = :issuestate) and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("issuestate", issuestate),RenjiuScheme.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			returnObject.setQueryBean(renjiuScheme);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("value", value);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/renjiuschemeticket/renjiuschemeList1";
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
	public  ReturnDatas listjson(HttpServletRequest request, Model model,RenjiuScheme renjiuScheme) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<RenjiuScheme> datas=renjiuSchemeService.findListDataByFinder(null,page,RenjiuScheme.class,renjiuScheme);
			returnObject.setQueryBean(renjiuScheme);
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
		return "/lottery/renjiuscheme/renjiuschemeLook";
	}

	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/updateissuestate")
	@ResponseBody      
	public ReturnDatas updateissuestate(Model model,RenjiuScheme renjiuScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//手动出票
			String schemeid = request.getParameter("schemeid");
			RenjiuScheme scheme = renjiuSchemeService.queryForObject(new Finder("select * from renjiu_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid), RenjiuScheme.class);
			if(scheme!=null){
				if(scheme.getSystemissue() != null&&scheme.getIssuestate()==3){
					if(scheme.getSystemissue()==3){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该订单已手动出票");
					}else if(scheme.getSystemissue()==1){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该订单已系统出票");
					}
				}else{
					renjiuSchemeService.update(new Finder("update renjiu_scheme set issuestate=3,systemissue=3 where schemeid=:schemeid ").setParam("schemeid", schemeid));
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该订单不存在");
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
			String schemeid = request.getParameter("schemeid");
			if("1".equals(request.getParameter("k"))){
				String pass = request.getParameter("pass");
				boolean matches = pass.matches("^[1-9][0-9]*$");
				if(matches == true){
					RenjiuScheme scheme = renjiuSchemeService.queryForObject(new Finder("select * from renjiu_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid), RenjiuScheme.class);
					if(Integer.valueOf(pass) > scheme.getBetmulriple()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("倍数超越投注倍数");
					}else{
						if(scheme.getIssuestate()==4){
							renjiuSchemeService.update(new Finder("update renjiu_scheme set bettingretrytime=0,channelid=null,issuestate=0 where schemeid=:schemeid").setParam("schemeid", schemeid));
						}
						renjiuSchemeService.update(new Finder("update renjiu_scheme set systemissue=1,issuebetmulriple=:issuebetmulriple where schemeid=:schemeid ").setParam("schemeid", schemeid).setParam("issuebetmulriple", pass));
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("倍数不正确");
				}
			}else{
				RenjiuScheme scheme = renjiuSchemeService.queryForObject(new Finder("select * from renjiu_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid), RenjiuScheme.class);
				if(scheme!=null){
					if(scheme.getSystemissue() != null){
						if(scheme.getSystemissue()==3){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该订单已手动出票");
						}else if(scheme.getSystemissue()==1){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该订单已系统出票");
						}
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该订单不存在");
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
			RenjiuScheme renjiuScheme = renjiuSchemeService.findRenjiuSchemeById(id);
			returnObject.setData(renjiuScheme);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
	}
	
	
	

}
