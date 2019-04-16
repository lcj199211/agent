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
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.LotteryScheme;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.ILotteryOrderService;
import org.springrain.lottery.service.ILotterySchemeService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.utils.AgentToolUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-18 09:08:21
 * @see org.springrain.lottery.web.LotteryScheme
 */
@Controller
@RequestMapping(value="/lotteryscheme")
public class LotterySchemeController  extends BaseController {
	@Resource
	private ILotterySchemeService lotterySchemeService;
	@Resource
	private ILotteryOrderService lotteryOrderService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	
	private String listurl="/lottery/lotteryscheme/lotteryschemeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param lotteryScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,LotteryScheme lotteryScheme) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			//方案详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String schemeid = request.getParameter("schemeid");
			List<LotteryOrder> datas= lotteryOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.bettingip,c.name as playmethod from lottery_order a LEFT JOIN lottery_scheme b on a.schemeid = b.schemeid LEFT JOIN lottery_playmethod c on a.playtype = c.id where  a.schemeid = :schemeid and (b.agentid = :agentid or b.agentparentids like :agentparentids)  order by b.id").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid),LotteryOrder.class,page);
			
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new LotteryOrder());
			model.addAttribute("schemeid", schemeid);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/lotteryscheme/lotteryorderList";
			
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
			return  "/lottery/lotteryorder/lotterymemberList";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(10);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String memberid2 = request.getParameter("memberid2");
			String phase = request.getParameter("phase");
			String situation = request.getParameter("situation");
			String time = request.getParameter("time");
//			String buytype = request.getParameter("buytype");
//			String mid2 = request.getParameter("mid");
//			if(StringUtils.isBlank(mid2)){
//				mid2=null;
//			}
//			if("100".equals(buytype)){
//				buytype = null;
//			}
			if(StringUtils.isBlank(situation)||situation==null){
				situation="1";
			}
			if(StringUtils.isBlank(time)){
				time="1";
			}
			if(StringUtils.isBlank(memberid2)){
				memberid2=null;
			}else{
				//memberid2="%"+memberid2+"%";
			}
			if(StringUtils.isBlank(phase)){
				phase=null;
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<LotteryScheme> datas = null;
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				if("1".equals(time)){
					 //今日
					datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),LotteryScheme.class,page);
					bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
					bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
				 }else if("2".equals(time)){
					 //昨日
					 datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),LotteryScheme.class,page);
					 bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("phase", phase).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation),Double.class);
					 bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 DAY),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m-%d").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
				 }else if("3".equals(time)){ 
					 //本周
					 datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("memberid2", memberid2).setParam("phase", phase).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("situation", situation),LotteryScheme.class,page);
					 bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),Double.class);
					 bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),Double.class);
				 }else if("4".equals(time)){
					 //上周
					 datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("x","%u").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),LotteryScheme.class,page);
					 bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),Double.class);
					 bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(DATE_SUB(curdate(), INTERVAL 1 WEEK),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%u").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),Double.class);
				 }else if("5".equals(time)){
					 //本月
					 datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase)  and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),LotteryScheme.class,page);
					 bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
					 bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where date_format(a.bettingtime,:x)=date_format(now(),:x) and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("x","%Y-%m").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
				 }else{
					 datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),LotteryScheme.class,page);
					 bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where (:memberid2 is null or memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or situation = :situation) and (agentid = :agentid or agentparentids like :agentparentids)").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
					 bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where (:memberid2 is null or memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or situation = :situation) and (agentid = :agentid or agentparentids like :agentparentids)").setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
				 }
			}else{
				datas= lotterySchemeService.queryForList(new Finder("select a.*,c.nickname as membernickname,c.isinternal as isinternal from lottery_scheme a left join bet_member c on c.id2=a.memberid2 where a.bettingtime>=:starttime and a.bettingtime<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation),LotteryScheme.class,page);
				bettingmoneyTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.amount) from lottery_scheme a where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
				bettingwinTotal = lotterySchemeService.queryForObject(new Finder("select sum(a.award) from lottery_scheme a where substr(a.bettingtime,1,10)>=:starttime and substr(a.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 = :memberid2) and (:phase is null or phase=:phase) and (:situation is null or a.situation = :situation) and (a.agentid = :agentid or a.agentparentids like :agentparentids)").setParam("starttime",startDate).setParam("endtime", endDate).setParam("memberid2", memberid2).setParam("phase", phase).setParam("situation", situation).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"),Double.class);
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
	 * @param lotteryScheme
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,LotteryScheme lotteryScheme) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<LotteryScheme> datas=lotterySchemeService.findListDataByFinder(null,page,LotteryScheme.class,lotteryScheme);
			returnObject.setQueryBean(lotteryScheme);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,LotteryScheme lotteryScheme) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = lotterySchemeService.findDataExportExcel(null,listurl, page,LotteryScheme.class,lotteryScheme);
		String fileName="lotteryScheme"+GlobalStatic.excelext;
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
		return "/lottery/lotteryscheme/lotteryschemeLook";
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
			 LotteryScheme lotteryScheme = lotterySchemeService.queryForObject(new Finder("select * from lottery_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", id), LotteryScheme.class);
		  	 returnObject.setData(lotteryScheme);
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
	public ReturnDatas saveorupdate(Model model,LotteryScheme lotteryScheme,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);

		String pagentid = SessionUser.getShiroUser().getAgentid();
		BetAgent pAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", pagentid), BetAgent.class);
		String agentparentids = ","+pagentid+",";
		try {
			//撤销
			if("1".equals(request.getParameter("cancel"))){
				String schemeid = request.getParameter("schemeid");
				LotteryScheme scheme = null;
				if(schemeid!=null){
					scheme = lotterySchemeService.queryForObject(new Finder("select * from lottery_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), LotteryScheme.class);
				}else{
					scheme = lotterySchemeService.queryForObject(new Finder("select * from lottery_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", lotteryScheme.getId()), LotteryScheme.class);
				}
				if(scheme!=null){
					List<LotteryOrder> suestateList = lotteryOrderService.queryForList(new Finder("select orderid from lottery_order where schemeid = :schemeid and issuestate = 3").setParam("schemeid", scheme.getSchemeid()),LotteryOrder.class);
					if(suestateList!=null&&!suestateList.isEmpty()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该方案存在已出票订单，无法撤消");
						return returnObject;
					}
					
					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
						
						Integer memberid2 = scheme.getMemberid2();
						BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
						if(member!=null){
							lotterySchemeService.update(new Finder("update lottery_scheme set situation=:situation where id=:id").setParam("situation", 2).setParam("id", scheme.getId()));
							lotteryOrderService.update(new Finder("update lottery_order set result=2 where schemeid=:id").setParam("id", scheme.getSchemeid()));
							soccerAllbettingService.update(new Finder("update soccer_allbetting set state=:state where id=:id").setParam("state", 2).setParam("id", scheme.getSchemeid()));
							BetMember member2=new BetMember();
							member2.setId(member.getId());
							member2.setId2(member.getId2());
							BigDecimal gameScore = new BigDecimal(Double.toString(member.getGamescore()));
					        BigDecimal bettingMoney = new BigDecimal(scheme.getAmount().toString());
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
								     content="用户"+scheme.getMemberid2()+"撤销在大乐透的方案号为"+scheme.getSchemeid()+"的投注方案，投注额为"+scheme.getAmount();
								     betScorerecord.setMemberid2(member.getId2());
								     betScorerecord.setTime(new Date());
								     betScorerecord.setContent(content);
								     betScorerecord.setOriginalscore(member.getScore());
								     betScorerecord.setChangescore(scheme.getAmount().doubleValue());
								     betScorerecord.setBalance(member.getScore());
								     betScorerecord.setState(1);
								     betScorerecord.setType(26);					 
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
								     details = "撤销ID："+member.getId2()+"的用户在"+"大乐透-"+scheme.getAmount()+"分的投注方案";
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
//			}else if("1".equals(request.getParameter("settle"))){
//				String schemeid = request.getParameter("schemeid");
//				LotteryScheme scheme = null;
//				if(schemeid!=null){
//					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), SoccerScheme.class);
//				}else{
//					scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", soccerScheme.getId()), SoccerScheme.class);
//				}
//				
//				if(scheme!=null){
//					Integer memberid2 = scheme.getMemberid2();
//					BetMember member = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", memberid2),BetMember.class);
//					if(member==null){
//						returnObject.setStatus(ReturnDatas.ERROR);
//						returnObject.setMessage("无此用户");
//						return returnObject;
//					}
//					if(scheme.getSituation()!=null&&scheme.getSituation()==0){
//						String schemeid1=scheme.getSchemeid();
//						List<SoccerLeagueOrder> orderList234 = leagueOrderService.queryForList(new Finder("select * from soccer_league_order where result=0 and state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), SoccerLeagueOrder.class);
//						if(orderList234!=null){
//							for (SoccerLeagueOrder soccerLeagueOrder : orderList234) {
//								List<SoccerLeagueOrderContent>  contentList = leagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where orderid=:orderid ").setParam("orderid", soccerLeagueOrder.getOrderid()), SoccerLeagueOrderContent.class);
//								int count = 0 ;
//								Boolean flag=false;
//								if(contentList!=null){
//									for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
//										if(soccerLeagueOrderContent.getResult()==1){
//											count++;
//										}
//										if(soccerLeagueOrderContent.getResult()==3){
//											flag=true;
//										}
//										
//									}
//									if(contentList.size() == count){
//										
//									}else{
//										if(flag==true){
//											
//										}else{
//											returnObject.setStatus(ReturnDatas.ERROR);
//											returnObject.setMessage("已结赛比赛数量不足");
//											return returnObject;
//										}
//									}
//								}else{
//									returnObject.setStatus(ReturnDatas.ERROR);
//									returnObject.setMessage("未结算，有订单无内容");
//									return returnObject;
//								}
//							}
//							
//							Double schemereward=0.;
//							for (SoccerLeagueOrder soccerLeagueOrder : orderList234) {
//
//								List<SoccerLeagueOrderContent>  contentList = leagueOrderContentService.queryForList(new Finder("select * from soccer_league_order_content where orderid=:orderid ").setParam("orderid", soccerLeagueOrder.getOrderid()), SoccerLeagueOrderContent.class);
//								int count = 0 ;
//								if((contentList!=null)&&(!contentList.isEmpty())){
//									for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
//										if(soccerLeagueOrderContent.getResult()==1){
//											count++;
//										}
//									}
//										
//									if(contentList.size() == count){
//										//中奖了 返奖
//										Double odds = 1.;
//										for (SoccerLeagueOrderContent soccerLeagueOrderContent : contentList) {
//											odds*=soccerLeagueOrderContent.getOdds();
//										}
//										Double award = odds*soccerLeagueOrder.getBettingmoney();
//										Double posttaxprice=award;
//										schemereward+=posttaxprice;
//										
//										Date dddd=new Date();
//										leagueOrderService.update(new Finder("update soccer_league_order set result=1,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid and result=0 ").setParam("posttaxprize", award).setParam("orderid", soccerLeagueOrder.getOrderid()).setParam("settletime", dddd).setParam("award", posttaxprice));
//										
//										
//									}else{
//										Date ddddd=new Date();
//										leagueOrderService.update(new Finder("update soccer_league_order set result=3,settletime=:settletime,bettingwin=0,posttaxprize=0 where orderid=:orderid and result=0 ").setParam("orderid", soccerLeagueOrder.getOrderid()).setParam("settletime", ddddd));
//											
//									}
//								}
//							}
//							SoccerScheme soccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), SoccerScheme.class);
//							BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("id2", soccersheme.getMemberid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), BetMember.class);
//							
//							if(soccersheme.getBuytype()==1){
//								//跟单
//								SoccerScheme sdsoccersheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where buytype = 2 and schemeid2 = :schemeid2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("schemeid2", soccersheme.getSchemeid2()).setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%"), SoccerScheme.class);
//								if(sdsoccersheme.getPubstate()!=null&&sdsoccersheme.getPubstate()==2){
//									BigDecimal guarantee = sdsoccersheme.getGuarantee();
//									BigDecimal guaranteeMoney = null;
//									if(guarantee!=null){
//										guaranteeMoney = guarantee.multiply(soccersheme.getBettingmoney());
//									}
//									if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
//										BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join soccer_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 and (a.agentid = :agentid or a.agentparentids like :agentparentids) limit 1").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid2",soccersheme.getSchemeid2() ), BetMember.class);
//										
//										BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
//										if(!"A101".equals(betAgent.getParentid())){
//											betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
//										}
//										
//										BetAgentBrokerage agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
//										
//										if(agentBrokerage==null){
//											BetAgentBrokerage brokerage = new BetAgentBrokerage();
//											brokerage.setAgentid(betAgent.getAgentid());
//											brokerage.setAgentparentid(betAgent.getParentid());
//											brokerage.setAgentparentids(betAgent.getParentids());
//											brokerage.setBrokerageagent(new BigDecimal(0.02));
//											brokerage.setBrokeragemember(new BigDecimal(0.08));
//											betAgentBrokerageService.save(brokerage);
//											agentBrokerage = brokerage;
//										}
//										BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
//										//跟单方案大神佣金
//										BigDecimal brokeragemoney = bettingwin.multiply(agentBrokerage.getBrokeragemember());
//										soccersheme.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
//										//跟单方案代理佣金
//										BigDecimal brokerageagentmoney = bettingwin.multiply(agentBrokerage.getBrokerageagent());
//										soccersheme.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
//										soccersheme.setBrokerageagentid(betAgent.getAgentid());
//										
//										//返给代理佣金
//										Integer agentupdatenum=null;
//										for(int i=0;i<10;i++){
//											if(agentupdatenum==null){
//												agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
//											}else if(agentupdatenum==0){
//												agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
//												
//											}else if(agentupdatenum==1){
//												break;
//											}
//											
//										}
//										
//										//跟单方案减总佣金
//										BigDecimal sumbrokeragemoney = brokeragemoney.add(brokerageagentmoney);
//										schemereward = bettingwin.subtract(sumbrokeragemoney).doubleValue();
//										soccersheme.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
//										
//										if(sdsoccersheme.getBrokeragemoney()!=null){
//											sdsoccersheme.setBrokeragemoney(sdsoccersheme.getBrokeragemoney().add(brokeragemoney));
//										}else{
//											sdsoccersheme.setBrokeragemoney(brokeragemoney);
//										}
//										sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
//										sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
//										sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
//										sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
//										Integer updatenum=null;
//										for(int i=0;i<10;i++){
//											if(updatenum==null){
//												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("gamescore", sdmember.getGamescore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
//											}else if(updatenum==0){
//												sdmember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", sdmember.getId()), BetMember.class);
//												sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
//												sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
//												sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
//												sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
//												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("gamescore", sdmember.getGamescore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
//												
//											}else if(updatenum==1){
//												break;
//											}
//											
//										}
//										if(updatenum==1){
//											
//											//更新缓存
//											String ticket = sdmember.getTicket();
//											if(ticket!=null){
//												try{
//													ObjectMapper mapper=new ObjectMapper();
//													byte[] json = mapper.writeValueAsBytes(sdmember);
//													cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
//												}catch(Exception e){
//													e.printStackTrace();
//												}
//												
//											}
//											Date dd=new Date();
//											BetScorerecord scorerecord = new BetScorerecord();
//											scorerecord.setId(null);
//											scorerecord.setMemberid2(sdsoccersheme.getMemberid2());
//											scorerecord.setTime(dd);
//											double f1 = new BigDecimal(schemereward).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//											double f2 = soccersheme.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//											scorerecord.setContent("用户"+betMember.getId2()+"投注的足彩竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
//											scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
//											scorerecord.setChangescore(brokeragemoney.doubleValue());
//											scorerecord.setBalance(sdmember.getScore());
//											scorerecord.setState(1);
//											scorerecord.setType(15);
//											scorerecord.setOgamescore(new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
//											scorerecord.setObankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
//											scorerecord.setOfreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
//											scorerecord.setGamescore(new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
//											scorerecord.setBankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
//											scorerecord.setFreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
//											scorerecord.setAgentid(sdmember.getAgentid());
//											scorerecord.setAgentparentid(sdmember.getAgentparentid());
//											scorerecord.setAgentparentids(sdmember.getAgentparentids());
//											scorerecord.setOrderid(schemeid1);
//											double gsq = new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//										    double gsh = new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//										    double bs = new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//										    double dsq = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//										    double dsh = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//										    scorerecord.setRemark("gq:"+gsq+"gh"+gsh+",b:"+bs+",dq:"+dsq+",dh:"+dsh);
//											betScorerecordService.save(scorerecord);
//											
//											//投注方案
//											soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", soccersheme.getBrokeragemoney()).setParam("brokerageagentmoney", soccersheme.getBrokerageagentmoney()).setParam("brokeragemembermoney", soccersheme.getBrokeragemembermoney()).setParam("brokerageagentid", soccersheme.getBrokerageagentid()));
//											soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdsoccersheme.getSchemeid()).setParam("brokeragemoney", sdsoccersheme.getBrokeragemoney()));
//											
//										}
//									}
//								}
//								
//								
//							}
//							String firstagentid=null;
//							Double bbb=null;
//							try {
//								String parentids = betMember.getAgentparentids();
//								String parentid=betMember.getAgentparentid();
//								if("A101".equals(parentid)){
//									bbb = soccerSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:zuqiu limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", betMember.getAgentid()),Double.class);
//								}else{
//									String[] split = parentids.split(",");
//									int i=0;
//									for (String string : split) {
//										if(i==0){
//											if("A101".equals(string)){
//												i=1;
//											}
//										}else{
//											firstagentid=string;
//											break;
//										}
//									}
//									bbb = soccerSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:zuqiu limit 1").setParam("zuqiu", "zuqiu").setParam("agentid", firstagentid),Double.class);
//								}
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//							
//							Double plusawards=0.;
//							if(bbb!=null){
//								plusawards=bbb*schemereward;
//							}
//							
//							betMember.setBankscore(betMember.getBankscore()+schemereward);
//							betMember.setGamescore(betMember.getGamescore()+plusawards);
//							betMember.setScore(betMember.getScore() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
//							betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
//							betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
//							Integer updatenum=null;
//							for(int i=0;i<10;i++){
//								if(updatenum==null){
//									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
//								}else if(updatenum==0){
//									betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
//									betMember.setBankscore(betMember.getBankscore()+schemereward);
//									betMember.setGamescore(betMember.getGamescore()+plusawards);
//									betMember.setScore(betMember.getScore() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
//									betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
//									betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - soccersheme.getBettingmoney().doubleValue());
////									betMember.setFreezingscore(betMember.getFreezingscore()-soccersheme.getBettingmoney().doubleValue());
//									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
//									
//								}else if(updatenum==1){
//									break;
//								}
//								
//							}
//								
//							if(updatenum==1){
//								
//								//更新缓存
//								String ticket = betMember.getTicket();
//								if(ticket!=null){
//									try{
//										ObjectMapper mapper=new ObjectMapper();
//										byte[] json = mapper.writeValueAsBytes(betMember);
//										cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
//									}catch(Exception e){
//										//String errorMessage = e.getLocalizedMessage();
//										e.printStackTrace();
//									}
//									
//								}
//								
//								Date dd=new Date();
//								try {
//									BetScorerecord scorerecord = new BetScorerecord();
//									scorerecord.setId(null);
//									scorerecord.setMemberid2(soccersheme.getMemberid2());
//									scorerecord.setTime(dd);
//									double f1 = new BigDecimal(schemereward+plusawards).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//									double f2 = soccersheme.getBettingmoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
//									scorerecord.setContent("足彩竞猜方案号为"+schemeid1+"的投注方案押"+f2+"返奖"+f1+"元");
//									scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + soccersheme.getBettingmoney().doubleValue());
//									scorerecord.setBalance(betMember.getScore());
//									scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore()-plusawards));
//									scorerecord.setObankscore(new BigDecimal(betMember.getBankscore()-schemereward));
//									scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()));
//									scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()));
//									scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()));
//									scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()));
//									scorerecord.setOrderid(schemeid1);
//									scorerecord.setChangescore(schemereward+plusawards);
//									scorerecord.setState(1);
//									scorerecord.setType(14);
//									scorerecord.setAgentid(betMember.getAgentid());
//									scorerecord.setAgentparentid(betMember.getAgentparentid());
//									scorerecord.setAgentparentids(betMember.getAgentparentids());
//									betScorerecordService.save(scorerecord);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//								//投注方案
//								soccerSchemeService.update(new Finder("update soccer_scheme set plusawards=:plusawards,bettingwin=:bettingwin,situation=1,settlementtime=:settlementtime,pretaxamount=:pretaxamount WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("pretaxamount", schemereward).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("bettingwin", schemereward+plusawards));
//								
//								//汇总投注
//								soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and state=0 and type=1 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
////										
//								//代理退佣
//								try {
//									String agentid = betMember.getAgentid();
//									double bettingmoney = (soccersheme.getBettingmoney()).doubleValue();
//									BetAgent betaggg = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
//									if(bettingmoney>=0.){
//										bettingmoneyagentrebate(betaggg,bettingmoney,betMember,schemeid1,dd,0.);
//									}
//									
//									
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//							
//						}else{
//							returnObject.setStatus(ReturnDatas.ERROR);
//							returnObject.setMessage("未结算，此方案无订单");
//							return returnObject;
//						}
//					}else{
//						returnObject.setStatus(ReturnDatas.ERROR);
//						returnObject.setMessage("已结算方案无法结算");
//					}
//				}else{
//					returnObject.setStatus(ReturnDatas.ERROR);
//					returnObject.setMessage("无此方案");
//				}
			}else if("1".equals(request.getParameter("recalculate"))){
				//重新结算
				String schemeid = request.getParameter("schemeid");
				LotteryScheme scheme = null;
				if(schemeid!=null){
					scheme = lotterySchemeService.queryForObject(new Finder("select * from lottery_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), LotteryScheme.class);
				}else{
					scheme = lotterySchemeService.queryForObject(new Finder("select * from lottery_scheme where id=:id and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id", lotteryScheme.getId()), LotteryScheme.class);
				}
				
				if(scheme!=null&&scheme.getSituation()==1){
					String schemeid1=scheme.getSchemeid();	
					
					BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("id2", scheme.getMemberid2()), BetMember.class);
					betMember.setBankscore(betMember.getBankscore()-scheme.getAward().doubleValue()+scheme.getPlusawards().doubleValue());
					betMember.setGamescore(betMember.getGamescore()-scheme.getPlusawards().doubleValue());
					betMember.setScore(betMember.getScore() - scheme.getAward().doubleValue() + scheme.getAmount().doubleValue());
					
					betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score where id2=:id2 ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id2", betMember.getId2()).setParam("score", betMember.getScore()));
					
					Date dd=new Date();
					BetScorerecord scorerecord = new BetScorerecord();
					scorerecord.setId(null);
					scorerecord.setMemberid2(scheme.getMemberid2());
					scorerecord.setTime(dd);
					scorerecord.setContent("减去原有奖金");					
					scorerecord.setOriginalscore(betMember.getScore() + scheme.getAward().doubleValue() - scheme.getAmount().doubleValue());
					scorerecord.setChangescore(-scheme.getAward().doubleValue());
					scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore() +scheme.getPlusawards().doubleValue()));
					scorerecord.setObankscore(new BigDecimal(betMember.getBankscore() + scheme.getAward().doubleValue() - scheme.getPlusawards().doubleValue()));
					scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setBalance(betMember.getScore());
					scorerecord.setState(1);
					scorerecord.setType(28);			
					scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
					scorerecord.setAgentid(betMember.getAgentid());
					scorerecord.setAgentparentid(betMember.getAgentparentid());
					scorerecord.setAgentparentids(betMember.getAgentparentids());
					scorerecord.setOrderid(scheme.getSchemeid());
					betScorerecordService.save(scorerecord);
					
					Integer untreatedcontent = lotteryOrderService.queryForObject(new Finder("select count(*) from lottery_order where schemeid = :schemeid and state=1 and result=0 ").setParam("schemeid", schemeid1), Integer.class);
					if(untreatedcontent==0){
						try{
							List<LotteryOrder> lotteryorderlist = lotteryOrderService.queryForList(new Finder("select * from lottery_order where schemeid=:schemeid and state=1 ").setParam("schemeid", schemeid1), LotteryOrder.class);
							if((lotteryorderlist !=null)&&(!lotteryorderlist.isEmpty())){
								BigDecimal schemepretaxamount=BigDecimal.ZERO;//税前奖金
								BigDecimal schemereward=BigDecimal.ZERO;
								for(LotteryOrder lotteryOrder : lotteryorderlist){
									if(lotteryOrder.getResult()==1||lotteryOrder.getResult()==3){
										//中奖或未中奖，都需要返奖（0）和结算
										BigDecimal posttaxprize = lotteryOrder.getPosttaxprize();//税前奖金
										BigDecimal award = posttaxprize.multiply(new BigDecimal("1"));//税后奖金，可进行处理。若安0.05%征税，则1改(1-0.0005),int类型
										schemepretaxamount = schemepretaxamount.add(posttaxprize);
										schemepretaxamount = schemereward.add(award);
										Date settletime=new Date();
										lotteryOrderService.update(new Finder("update lottery_order set result=:result,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid ").setParam("orderid", lotteryOrder.getOrderid()).setParam("settletime", settletime).setParam("award", award).setParam("posttaxprize", posttaxprize).setParam("result", lotteryOrder.getResult()));
									}
								}
								
								String firstagentid=null;
								Double bbb=null;
								try {
									String parentids = betMember.getAgentparentids();
									String parentid=betMember.getAgentparentid();
									if("A101".equals(parentid)){
										bbb = lotterySchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:dlt limit 1").setParam("dlt", "dlt").setParam("agentid", betMember.getAgentid()),Double.class);
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
										bbb = lotterySchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:dlt limit 1").setParam("dlt", "dlt").setParam("agentid", firstagentid),Double.class);
									}
								} catch (Exception e) {
									System.out.println("大乐透重新结算有异常");
								}
								
								Double plusawards=0.;
								if(bbb!=null){
									plusawards=bbb*(schemereward.doubleValue());
								}
								betMember.setBankscore(betMember.getBankscore()+schemereward.doubleValue());
								betMember.setGamescore(betMember.getGamescore()+plusawards);
								betMember.setScore(betMember.getScore() + schemereward.doubleValue() + plusawards - scheme.getAmount().doubleValue());
								betMember.setDayscore(betMember.getDayscore()  + schemereward.doubleValue() + plusawards - scheme.getAmount().doubleValue());
								betMember.setWinorfail(betMember.getWinorfail() + schemereward.doubleValue() + plusawards - scheme.getAmount().doubleValue());
								
								Integer updatenum=null;
								for(int i=0;i<10;i++){
									if(updatenum==null){
										updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
									}else if(updatenum==0){
										betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
										betMember.setBankscore(betMember.getBankscore()+schemereward.doubleValue());
										betMember.setGamescore(betMember.getGamescore()+plusawards);
										betMember.setScore(betMember.getScore() + schemereward.doubleValue()+plusawards - scheme.getAmount().doubleValue());
										betMember.setDayscore(betMember.getDayscore()  + schemereward.doubleValue()+plusawards - scheme.getAmount().doubleValue());
										betMember.setWinorfail(betMember.getWinorfail() + schemereward.doubleValue()+plusawards - scheme.getAmount().doubleValue());
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
										scorerecord.setMemberid2(scheme.getMemberid2());
										scorerecord.setTime(dd);
										scorerecord.setContent("重新结算大乐透返奖");
										scorerecord.setOriginalscore(betMember.getScore()- schemereward.doubleValue()-plusawards + scheme.getAmount().doubleValue());
										scorerecord.setBalance(betMember.getScore());
										scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore()-plusawards));
										scorerecord.setObankscore(new BigDecimal(betMember.getBankscore()-schemereward.doubleValue()));
										scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()));
										scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()));
										scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()));
										scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()));
										scorerecord.setOrderid(schemeid1);
										scorerecord.setChangescore(schemereward.doubleValue()+plusawards);
										scorerecord.setState(1);
										scorerecord.setType(27);
										scorerecord.setAgentid(betMember.getAgentid());
										scorerecord.setAgentparentid(betMember.getAgentparentid());
										scorerecord.setAgentparentids(betMember.getAgentparentids());
										betScorerecordService.save(scorerecord);
									} catch (Exception e) {
										System.out.println(e);
									}
									//投注方案
									lotterySchemeService.update(new Finder("update lottery_scheme set plusawards=:plusawards,situation=1,award=:award,pretaxamount=:pretaxamount,settlementtime=:settlementtime WHERE schemeid=:schemeid ").setParam("pretaxamount", schemepretaxamount).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("award", schemereward.add(new BigDecimal(plusawards))).setParam("plusawards", plusawards));
									//汇总投注
									soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id and type=5 ").setParam("bettingscore", schemereward.add(new BigDecimal(plusawards))).setParam("settlementtime", dd).setParam("id", schemeid1));														
								}
							}
						} catch (Exception e) {
							System.out.println(e);
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
		/** 
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
				halfscore1 = halfscores[0];
				halfscore2 = halfscores[1];
			}
			if(allscore!=null){
				String[] allscores = allscore.split(":");
				allscore1 = allscores[0];
				allscore2 = allscores[1];
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
		 */
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/loptteryscheme/lotteryschemeCru";
		}		
//	}
	
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
				lotterySchemeService.deleteById(id,LotteryScheme.class);
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
			lotterySchemeService.deleteByIds(ids,LotteryScheme.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
//	void bettingmoneyagentrebate(BetAgent betaggg,double bettingmoney,BetMember betmember11,String orderid,Date settlementtime,Double bettingrebate1){
//		try {
//			if("A101".equals(betaggg.getAgentid())){
//				return;
//			}else{
//				if(betaggg!=null){
//					if(betaggg.getActive()==1){
//						Double bettingrebate =betaggg.getBettingrebate()-bettingrebate1;
//						if((bettingrebate!=null)&&(bettingrebate<=1)&&(bettingrebate>=0)){
//							BetCommission betcommission =new BetCommission();
//							
//							SoccerAllbetting allbet = soccerAllbettingService.queryForObject(new Finder("select bettingtime from soccer_allbetting where id = :id").setParam("id", orderid), SoccerAllbetting.class);
//							if(allbet!=null){
//								betcommission.setBettingtime(allbet.getBettingtime());
//							}
//							betcommission.setAgentparentid(betaggg.getParentid());
//							betcommission.setAgentparentids(betaggg.getParentids());
//							if(betmember11!=null){
//								betcommission.setMemberagentid(betmember11.getAgentid());
//							}
//							
//							betcommission.setAgentid(betaggg.getAgentid());
//							betcommission.setCommission(bettingmoney*bettingrebate);
//							betcommission.setMemberid2(betmember11.getId2());
//							betcommission.setOrderid(orderid);
//							betcommission.setSettlementtime(settlementtime);
//							if(betcommission.getCommission()!=0.){
//								betCommissionService.save(betcommission);
//								betAgentService.update(new Finder("update bet_agent set score=IFNULL(score,0)+:score,bettingty=IFNULL(bettingty,0)+:score where agentid=:agentid ").setParam("agentid", betaggg.getAgentid()).setParam("score", betcommission.getCommission()));
//							}
//							BetAgent betaggg1 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", betaggg.getParentid()), BetAgent.class);
//							
//							bettingmoneyagentrebate(betaggg1,bettingmoney,betmember11,orderid,settlementtime,betaggg.getBettingrebate());
//						}
//					
//					}else{
//						BetAgent betaggg1 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", betaggg.getParentid()), BetAgent.class);
//						
//						bettingmoneyagentrebate(betaggg1,bettingmoney,betmember11,orderid,settlementtime,bettingrebate1);
//					}
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("足球手动结算有异常");
//		}	
//	}
}
