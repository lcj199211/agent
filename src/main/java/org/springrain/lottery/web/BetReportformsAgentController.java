package  org.springrain.lottery.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgentRechargeRebate;
import org.springrain.lottery.entity.BetBetting;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.entity.BetReportformsAgent;
import org.springrain.lottery.entity.BetTransferagentAccounts;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetReportformsAgentService;
import org.springrain.lottery.service.IBetTransferagentAccountsService;
import org.springrain.lottery.service.IBetWithdrawcashService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-19 17:45:45
 * @see org.springrain.lottery.web.BetReportformsAgent
 */
@Controller
@RequestMapping(value="/betreportformsagent")
public class BetReportformsAgentController  extends BaseController {
	@Resource
	private IBetReportformsAgentService betReportformsAgentService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource 
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetTransferagentAccountsService betTransferagentAccountsService;
	
	private String listurl="/lottery/betreportformsagent/betreportformsagentList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betReportformsAgent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetReportformsAgent betReportformsAgent) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		
		if("1".equals(request.getParameter("k"))){
			//k=1上月
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        cal.add(Calendar.MONTH, -1);
			List<BetReportformsAgent> datas=betReportformsAgentService.findListDataByFinder(new Finder("select * from bet_reportforms_agent where substring(date,1,7)=:date and agentid=:agentid ").setParam("date",new SimpleDateFormat("yyyy-MM").format(cal.getTime())).setParam("agentid", agentid),page,BetReportformsAgent.class,betReportformsAgent);
			returnObject.setQueryBean(betReportformsAgent);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}else if("2".equals(request.getParameter("k"))){
			//k=2充值
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			if(page.getOrder()=="id"){
				page.setOrder("a.id");
			}
			List<BetGold> datas = betGoldService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_gold a left join bet_member b on a.memberid=b.id where a.state=2 and substring(a.rechargetime,1,10)=:date and a.agentid=:agentid ").setParam("date", request.getParameter("date")).setParam("agentid", agentid), BetGold.class, page);
			returnObject.setQueryBean(new BetGold());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentgoldList";
		}else if("3".equals(request.getParameter("k"))){
			//k=3充值佣金
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<BetTransferagentAccounts> datas = betTransferagentAccountsService.queryForList(new Finder("select * from bet_transferagent_accounts where type=1 and time=:date and agentid=:agentid").setParam("date", request.getParameter("date")).setParam("agentid", agentid), BetTransferagentAccounts.class,page);
			returnObject.setQueryBean(new BetTransferagentAccounts());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentgoldyjList";
		}else if("4".equals(request.getParameter("k"))){
			//k=4提现
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			if(page.getOrder()=="id"){
				page.setOrder("a.id");
			}
			List<BetWithdrawcash> datas = betWithdrawcashService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_withdrawcash a left join bet_member b on a.memberid=b.id where a.state=2 and substring(a.audittime,1,10)=:date and a.agentid=:agentid ").setParam("date", request.getParameter("date")).setParam("agentid", agentid), BetWithdrawcash.class,page);
			returnObject.setQueryBean(new BetWithdrawcash());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentwithdrawcashList";
		}else if("5".equals(request.getParameter("k"))){
			//k=5投注额
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			if(page.getOrder()=="id"){
				page.setOrder("a.id");
			}
			List<BetBetting> datas = betBettingService.queryForList(new Finder("select a.bettingtime,a.gcname,a.name1,sum(a.bettingmoney) AS bettingmoney from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where a.agentid=:agentid and b.isinternal=0 AND substr(a.bettingtime,1,10)=:date GROUP BY a.name1 ").setParam("date", request.getParameter("date")).setParam("agentid", agentid), BetBetting.class, page);
			returnObject.setQueryBean(new BetBetting());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentbettingmoneyList";
		}else if("6".equals(request.getParameter("k"))){
			//k=6投注佣金
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			if(page.getOrder()=="id"){
				page.setOrder("a.id");
			}
			List<BetBetting> datas = betBettingService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_betting a left join bet_member b on a.memberid=b.id where a.agentid=:agentid and a.state=1 and substring(a.settlementtime,1,10)=:date and a.ty>0 ").setParam("date", request.getParameter("date")).setParam("agentid", agentid), BetBetting.class, page);
			returnObject.setQueryBean(new BetBetting());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentbetyjList";
		}else if("7".equals(request.getParameter("k"))){
			//k=7游戏输赢
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			List<Map<String, Object>> datas = betBettingService.queryForList(new Finder("select gcname,name1,sum(bettingmoney-bettingscore) as bettingwinorlose from bet_betting where agentid=:agentid and substring(settlementtime,1,10)=:date group by gcname,name1").setParam("agentid", agentid).setParam("date", request.getParameter("date")),page);
			returnObject.setQueryBean(new BetBetting());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentgamewinList";
		}else if("8".equals(request.getParameter("k"))){
			//k=8游戏佣金
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformsagent/betreportformsagentgameyjList";
		}else{
			//本月 and 搜索
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			String starttime = request.getParameter("starttime");
			String endtime = request.getParameter("endtime");
			List<BetReportformsAgent> datas = null;
			if(StringUtils.isBlank(starttime) && StringUtils.isBlank(endtime)){
				datas = betReportformsAgentService.findListDataByFinder(new Finder("select * from bet_reportforms_agent where substring(date,1,7)=:date and agentid=:agentid ").setParam("date", new SimpleDateFormat("yyyy-MM").format(new Date())).setParam("agentid", agentid),page,BetReportformsAgent.class,betReportformsAgent);
			}else{
				model.addAttribute("starttime", starttime);
				model.addAttribute("endtime", endtime);
				if(StringUtils.isBlank(starttime)){
					starttime="1970-01-01";
				}
				if(StringUtils.isBlank(endtime)){
					endtime="2060-12-31";
				}
				if(starttime.equals(endtime)){
					datas=betReportformsAgentService.findListDataByFinder(new Finder("select * from bet_reportforms_agent where date=:date and agentid=:agentid ").setParam("date", starttime).setParam("agentid", agentid),page,BetReportformsAgent.class,betReportformsAgent);
				}else{
					datas=betReportformsAgentService.findListDataByFinder(new Finder("select * from bet_reportforms_agent where (date between :starttime and :endtime) and agentid=:agentid ").setParam("starttime", starttime).setParam("endtime", endtime).setParam("agentid", agentid),page,BetReportformsAgent.class,betReportformsAgent);
				}
			}
			returnObject.setQueryBean(betReportformsAgent);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betReportformsAgent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetReportformsAgent betReportformsAgent) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetReportformsAgent> datas=betReportformsAgentService.findListDataByFinder(null,page,BetReportformsAgent.class,betReportformsAgent);
			returnObject.setQueryBean(betReportformsAgent);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetReportformsAgent betReportformsAgent) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betReportformsAgentService.findDataExportExcel(null,listurl, page,BetReportformsAgent.class,betReportformsAgent);
		String fileName="betReportformsAgent"+GlobalStatic.excelext;
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
		return "/lottery/betreportformsagent/betreportformsagentLook";
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
		  BetReportformsAgent betReportformsAgent = betReportformsAgentService.findBetReportformsAgentById(id);
		   returnObject.setData(betReportformsAgent);
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
	ReturnDatas saveorupdate(Model model,BetReportformsAgent betReportformsAgent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			betReportformsAgentService.saveorupdate(betReportformsAgent);
			
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
		return "/lottery/betreportformsagent/betreportformsagentCru";
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
				betReportformsAgentService.deleteById(id,BetReportformsAgent.class);
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
			betReportformsAgentService.deleteByIds(ids,BetReportformsAgent.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
