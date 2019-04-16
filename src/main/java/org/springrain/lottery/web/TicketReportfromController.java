package  org.springrain.lottery.web;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
import org.springrain.lottery.entity.BasketballOrder;
import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.lottery.entity.TicketReportfrom;
import org.springrain.lottery.service.IBasketballOrderService;
import org.springrain.lottery.service.IBjdcOrderService;
import org.springrain.lottery.service.ILotteryOrderService;
import org.springrain.lottery.service.ISoccerLeagueOrderService;
import org.springrain.lottery.service.ITicketReportfromService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-07 09:40:04
 * @see org.springrain.lottery.web.TicketReportfrom
 */
@Controller
@RequestMapping(value="/ticketreportfrom")
public class TicketReportfromController  extends BaseController {
	@Resource
	private ITicketReportfromService ticketReportfromService;
	@Resource
	private ISoccerLeagueOrderService soccerLeagueOrderService;
	@Resource
	private IBasketballOrderService basketballOrderService;
	@Resource
	private IBjdcOrderService bjdcOrderService;
	@Resource
	private ILotteryOrderService lotteryOrderService;
	
	private String listurl="/lottery/ticketreportfrom/ticketreportfromList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param ticketReportfrom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,TicketReportfrom ticketReportfrom) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		if("1".equals(request.getParameter("k"))){
			//足球  成功 
			String date = request.getParameter("date");
			List<SoccerLeagueOrder> datas = soccerLeagueOrderService.queryForList(new Finder("select a.* from soccer_league_order a left join soccer_scheme b on a.schemeid=b.schemeid where a.issuestate=3 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("agentparentids", "%"+agentid+"%").setParam("parsetime", "%Y-%m-%d").setParam("date", date), SoccerLeagueOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/soccerList";
		}else if("11".equals(request.getParameter("k"))){
			//足球  失败 
			String date = request.getParameter("date");
			List<SoccerLeagueOrder> datas = soccerLeagueOrderService.queryForList(new Finder("select a.* from soccer_league_order a left join soccer_scheme b on a.schemeid=b.schemeid where a.issuestate=4 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("parsetime", "%Y-%m-%d").setParam("date", date).setParam("agentparentids", "%"+agentid+"%"), SoccerLeagueOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/soccerList";
		}else if("2".equals(request.getParameter("k"))){
			//篮球  成功 
			String date = request.getParameter("date");
			List<BasketballOrder> datas = basketballOrderService.queryForList(new Finder("select a.* from basketball_order a left join basketball_scheme b on a.schemeid=b.schemeid where a.issuestate=3 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("parsetime", "%Y-%m-%d").setParam("date", date).setParam("agentparentids", "%"+agentid+"%"),BasketballOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/basketballList";
		}else if("22".equals(request.getParameter("k"))){
			//篮球  失败
			String date = request.getParameter("date");
			List<BasketballOrder> datas = basketballOrderService.queryForList(new Finder("select a.* from basketball_order a left join basketball_scheme b on a.schemeid=b.schemeid where a.issuestate=4 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("parsetime", "%Y-%m-%d").setParam("date", date).setParam("agentparentids", "%"+agentid+"%"),BasketballOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/basketballList";
		}else if("3".equals(request.getParameter("k"))){
			//北单  成功 
			String date = request.getParameter("date");
			List<BjdcOrder> datas = bjdcOrderService.queryForList(new Finder("select a.* from bjdc_order a left join bjdc_scheme b on a.schemeid=b.schemeid where a.issuestate=3 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("parsetime", "%Y-%m-%d").setParam("date", date).setParam("agentparentids", "%"+agentid+"%"),BjdcOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/bjdcList";
		}else if("33".equals(request.getParameter("k"))){
			//北单  失败
			String date = request.getParameter("date");
			List<BjdcOrder> datas = bjdcOrderService.queryForList(new Finder("select a.* from bjdc_order a left join bjdc_scheme b on a.schemeid=b.schemeid where a.issuestate=4 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("parsetime", "%Y-%m-%d").setParam("date", date).setParam("agentparentids", "%"+agentid+"%"),BjdcOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/bjdcList";
		}else if("4".equals(request.getParameter("k"))){
			//大乐透 成功 
			String date = request.getParameter("date");
			List<LotteryOrder> datas = lotteryOrderService.queryForList(new Finder("select a.* from lottery_order a left join lottery_scheme b on a.schemeid=b.schemeid where a.issuestate=3 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("agentparentids", "%"+agentid+"%").setParam("parsetime", "%Y-%m-%d").setParam("date", date), LotteryOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/lotteryList";
		}else if("44".equals(request.getParameter("k"))){
			//大乐透  失败 
			String date = request.getParameter("date");
			List<LotteryOrder> datas = lotteryOrderService.queryForList(new Finder("select a.* from lottery_order a left join lottery_scheme b on a.schemeid=b.schemeid where a.issuestate=4 AND date_format(b.bettingtime,:parsetime) =:date and a.agentparentids like :agentparentids ").setParam("parsetime", "%Y-%m-%d").setParam("date", date).setParam("agentparentids", "%"+agentid+"%"), LotteryOrder.class, page);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("date", date);
			model.addAttribute("k", request.getParameter("k"));
			return "/lottery/ticketreportfrom/lotteryList";
		}else{
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if(StringUtils.isBlank(startTime)){
				startTime = "1999-09-09";
			}
			if(StringUtils.isBlank(endTime)){
				endTime = "2999-09-09";
			}
			Double soccersuccess = ticketReportfromService.queryForObject(new Finder("select sum(soccersuccess) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double soccerfail = ticketReportfromService.queryForObject(new Finder("select sum(soccerfail) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double lqsuccess = ticketReportfromService.queryForObject(new Finder("select sum(lqsuccess) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double lqfail = ticketReportfromService.queryForObject(new Finder("select sum(lqfail) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double bjdcsuccess = ticketReportfromService.queryForObject(new Finder("select sum(bjdcsuccess) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double bjdcfail = ticketReportfromService.queryForObject(new Finder("select sum(bjdcfail) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double lotterysuccess = ticketReportfromService.queryForObject(new Finder("select sum(lotterysuccess) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			Double lotteryfail = ticketReportfromService.queryForObject(new Finder("select sum(lotteryfail) from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid), Double.class);
			
			// ==执行分页查询
			List<TicketReportfrom> datas=ticketReportfromService.findListDataByFinder(new Finder("select * from ticket_reportfrom where date>=:date1 and date<=:date2 and agentid=:agentid ").setParam("date1", startTime).setParam("date2", endTime).setParam("agentid", agentid),page,TicketReportfrom.class,ticketReportfrom);
			returnObject.setQueryBean(ticketReportfrom);
			returnObject.setPage(page);
			returnObject.setData(datas);
			if(!"1999-09-09".equals(startTime)){
				model.addAttribute("startTime", startTime);
			}
			if(!"2999-09-09".equals(endTime)){
				model.addAttribute("endTime", endTime);
			}
			model.addAttribute("soccersuccess", soccersuccess);
			model.addAttribute("soccerfail", soccerfail);
			model.addAttribute("lqsuccess", lqsuccess);
			model.addAttribute("lqfail", lqfail);
			model.addAttribute("bjdcsuccess", bjdcsuccess);
			model.addAttribute("bjdcfail", bjdcfail);
			model.addAttribute("lotterysuccess", lotterysuccess);
			model.addAttribute("lotteryfail", lotteryfail);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param ticketReportfrom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,TicketReportfrom ticketReportfrom) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<TicketReportfrom> datas=ticketReportfromService.findListDataByFinder(null,page,TicketReportfrom.class,ticketReportfrom);
			returnObject.setQueryBean(ticketReportfrom);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,TicketReportfrom ticketReportfrom) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = ticketReportfromService.findDataExportExcel(null,listurl, page,TicketReportfrom.class,ticketReportfrom);
		String fileName="ticketReportfrom"+GlobalStatic.excelext;
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
		return "/lottery/ticketreportfrom/ticketreportfromLook";
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
		  TicketReportfrom ticketReportfrom = ticketReportfromService.findTicketReportfromById(id);
		   returnObject.setData(ticketReportfrom);
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
	ReturnDatas saveorupdate(Model model,TicketReportfrom ticketReportfrom,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			ticketReportfromService.saveorupdate(ticketReportfrom);
			
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
		return "/lottery/ticketreportfrom/ticketreportfromCru";
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
				ticketReportfromService.deleteById(id,TicketReportfrom.class);
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
			ticketReportfromService.deleteByIds(ids,TicketReportfrom.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
