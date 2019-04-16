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

import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.RenjiuMatch;
import org.springrain.lottery.entity.RenjiuResult;
import org.springrain.lottery.entity.RenjiuScheme;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IRenjiuMatchService;
import org.springrain.lottery.service.IRenjiuResultService;
import org.springrain.lottery.service.IRenjiuSchemeService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-07-30 16:29:19
 * @see org.springrain.lottery.web.RenjiuScheme
 */
@Controller
@RequestMapping(value="/renjiuscheme")
public class RenjiuSchemeController  extends BaseController {
	@Resource
	private IRenjiuSchemeService renjiuSchemeService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IRenjiuMatchService renjiuMatchService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private ICached cached;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IRenjiuResultService renjiuResultService;
	
	private String listurl="/lottery/renjiuscheme/renjiuschemeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param renjiuScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,RenjiuScheme renjiuScheme) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<RenjiuScheme> datas= renjiuSchemeService.queryForList(new Finder("select * from renjiu_scheme where schemeid = :schemeid").setParam("schemeid", schemeid),RenjiuScheme.class,page);
			
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new LotteryOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/renjiuscheme/renjiuschemedetail";
		}else {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String memberid2 = request.getParameter("memberid2");
			String situation = request.getParameter("situation");
			String time = request.getParameter("time");
			String periodnum = request.getParameter("periodnum");
			if(StringUtils.isBlank(periodnum)){
				periodnum = null;
			}
			if("100".equals(situation)){
				situation=null;
			}
			if(StringUtils.isBlank(time)){
				time="0";
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
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
					 bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
					 bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				 }else if("2".equals(time)){
					 //昨日
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
					 bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
					 bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				 }else if("3".equals(time)){
					 //本周
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
					 bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
					 bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				 }else if("4".equals(time)){
					 //上周
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
					 bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
					 bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				 }else if("5".equals(time)){
					 //本月
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation)  and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
					 bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
					 bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				 }else{
					 datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2  where  (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
					 bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where  (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
					 bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where  (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				 }
				
			}else{
				datas= renjiuSchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from renjiu_scheme a LEFT JOIN bet_member c on c.id2=a.memberid2 where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (:periodnum is null or periodnum=:periodnum) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation).setParam("periodnum", periodnum),RenjiuScheme.class,page);
				bettingmoneyTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingmoney) from renjiu_scheme a where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
				bettingwinTotal = renjiuSchemeService.queryForObject(new Finder("select sum(a.bettingwin) from renjiu_scheme a where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("situation", situation),Double.class);
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
			model.addAttribute("periodnum",periodnum);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("time", time);
			model.addAttribute("bettingmoneyTotal", bettingmoneyTotal);
			model.addAttribute("bettingwinTotal", bettingwinTotal);
			//ReturnDatas returnObject = listjson(request, model, renjiuScheme);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}		
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param renjiuScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,RenjiuScheme renjiuScheme) throws Exception{
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
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,RenjiuScheme renjiuScheme) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = renjiuSchemeService.findDataExportExcel(null,listurl, page,RenjiuScheme.class,renjiuScheme);
		String fileName="renjiuScheme"+GlobalStatic.excelext;
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
		return "/lottery/renjiuscheme/renjiuschemeLook";
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
		  RenjiuScheme renjiuScheme = renjiuSchemeService.findRenjiuSchemeById(id);
		   returnObject.setData(renjiuScheme);
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
	ReturnDatas saveorupdate(Model model,RenjiuScheme renjiuScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		
		String pagentid = SessionUser.getShiroUser().getAgentid();
		BetAgent pAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", pagentid), BetAgent.class);
		String agentparentids = ","+pagentid+",";
		try {
				//撤销
			if("1".equals(request.getParameter("cancel"))){
				String schemeid = request.getParameter("schemeid");
				RenjiuScheme scheme = null;
				if(schemeid!=null){
					scheme = renjiuSchemeService.queryForObject(new Finder("select * from renjiu_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), RenjiuScheme.class);
				}else{
					scheme = renjiuSchemeService.queryForObject(new Finder("select * from renjiu_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", renjiuScheme.getId()), RenjiuScheme.class);
				}
				if(scheme!=null){
					if(scheme.getIssuestate()!=null&&scheme.getIssuestate()==3){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该方案存在已出票订单，无法撤消");
						return returnObject;
					}
					/**
					 * 比赛开始后就不可以撤销
					 */
					RenjiuMatch matchDatas = renjiuMatchService.queryForObject(new Finder("select * from renjiu_match where periodnum = :periodnum order by starttime limit 1 ").setParam("periodnum", scheme.getPeriodnum()),RenjiuMatch.class);
					if(matchDatas!=null){
						Date time = new Date();
						if((matchDatas.getStarttime()).before(time)){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该方案存在已开始的比赛，无法撤消");
							return returnObject;
						}
					}
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						Integer memberid2 = scheme.getMemberid2();
						BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
						if(member!=null){
							renjiuSchemeService.update(new Finder("update soccer_scheme set situation=2 and result=2 where id=:id").setParam("id", scheme.getId()));
							soccerAllbettingService.update(new Finder("update soccer_allbetting set state=2 where id=:id").setParam("id", scheme.getSchemeid()));
							BetMember member2=new BetMember();
							member2.setId(member.getId());
							member2.setId2(member.getId2());
							BigDecimal gameScore = new BigDecimal(Double.toString(member.getGamescore()));
					        BigDecimal bettingMoney = new BigDecimal(scheme.getBettingmoney().toString());
							member2.setGamescore(gameScore.add(bettingMoney).doubleValue());
							betMemberService.update(member2,true);
							
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
						     content="用户"+scheme.getMemberid2()+"撤销在任九的方案号为"+scheme.getSchemeid()+"的投注方案，投注额为"+scheme.getBettingmoney();
						     betScorerecord.setMemberid2(member.getId2());
						     betScorerecord.setTime(new Date());
						     betScorerecord.setContent(content);
						     betScorerecord.setOriginalscore(member.getScore());
						     betScorerecord.setChangescore(scheme.getBettingmoney().doubleValue());
						     betScorerecord.setBalance(member.getScore());
						     betScorerecord.setState(1);
						     betScorerecord.setType(29);					 
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
						     details = "撤销ID："+member.getId2()+"的用户在"+"任九-"+scheme.getBettingmoney()+"分的投注方案";
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
			}else if("1".equals(request.getParameter("settle"))){
				//结算
				String schemeid = request.getParameter("schemeid");
				RenjiuScheme scheme = null;
				if(schemeid!=null){
					scheme = renjiuSchemeService.queryForObject(new Finder("select * from renjiu_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid), RenjiuScheme.class);
				}else{
					scheme = renjiuSchemeService.findRenjiuSchemeById(renjiuScheme.getId());
				}
				
				if(scheme!=null){
					Integer memberid2 = scheme.getMemberid2();
					BetMember member = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2),BetMember.class);
					if(member==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("无此用户");
						return returnObject;
					}
					if(scheme.getResult()!=null&&scheme.getResult()==0){
						String schemeid1=scheme.getSchemeid();
						RenjiuResult datas=renjiuResultService.queryForObject(new Finder("select * from renjiu_result where periodnum = :periodnum and state = 1").setParam("periodnum", scheme.getPeriodnum()), RenjiuResult.class);						
						
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("已结算方案无法结算");
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此方案");
				}
			}else if("1".equals(request.getParameter("recalculate"))){
				
			}else{
//				renjiuSchemeService.saveorupdate(renjiuScheme);
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
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/renjiuscheme/renjiuschemeCru";
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
				renjiuSchemeService.deleteById(id,RenjiuScheme.class);
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
			renjiuSchemeService.deleteByIds(ids,RenjiuScheme.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
