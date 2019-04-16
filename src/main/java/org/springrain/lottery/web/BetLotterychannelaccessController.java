package  org.springrain.lottery.web;

import java.io.File;
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
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetLotterychannelaccess;
import org.springrain.lottery.entity.Lotterygameplusawardsproportion;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetLotterychannelaccessService;
import org.springrain.system.service.IDicDataService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-28 13:48:31
 * @see org.springrain.lottery.web.BetLotterychannelaccess
 */
@Controller
@RequestMapping(value="/betlotterychannelaccess")
public class BetLotterychannelaccessController  extends BaseController {
	@Resource
	private IBetLotterychannelaccessService betLotterychannelaccessService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betlotterychannelaccess/betlotterychannelaccessList";
	
	
	/**
	 * 进入出票参数页面
	 */
	@RequestMapping(value = "/detail")
	public String updatedetail(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		String company = "";
		if(",A101,".equals(agent.getParentids())){
			company = agentid;
		}else{
			company = agent.getParentids().split(",")[2];
		}
		//出票倍数
		//足球
		String issuebetmulriple = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:code and remark=:company ").setParam("code", "issuebetmulriple").setParam("company", company), String.class);
		//篮球
		String issuebetmulriple_lq = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:code and remark=:company ").setParam("code", "issuebetmulriple_lq").setParam("company", company), String.class);
		//北京单场
		String issuebetmulriple_bjdc = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:code and remark=:company ").setParam("code", "issuebetmulriple_bjdc").setParam("company", company), String.class);
		//大乐透
		String issuebetmultiple_dlt = dicDataService.queryForObject(new Finder("select value from t_dic_data where code=:code and remark=:company ").setParam("code", "issuebetmultiple_dlt").setParam("company", company), String.class);
		//自动出票
		//足球
		Lotterygameplusawardsproportion gameplusawardsproportion_zq = dicDataService.queryForObject(new Finder("select * from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lotteryid limit 1 ").setParam("agentid", company).setParam("lotteryid", "zuqiu"), Lotterygameplusawardsproportion.class);
		//篮球
		Lotterygameplusawardsproportion gameplusawardsproportion_lq = dicDataService.queryForObject(new Finder("select * from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lotteryid limit 1 ").setParam("agentid", company).setParam("lotteryid", "lq"), Lotterygameplusawardsproportion.class);
		//北京单场
		Lotterygameplusawardsproportion gameplusawardsproportion_bjdc = dicDataService.queryForObject(new Finder("select * from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lotteryid limit 1 ").setParam("agentid", company).setParam("lotteryid", "bjdc"), Lotterygameplusawardsproportion.class);
		//大乐透
		Lotterygameplusawardsproportion gameplusawardsproportion_dlt = dicDataService.queryForObject(new Finder("select * from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lotteryid limit 1 ").setParam("agentid", company).setParam("lotteryid", "dlt"), Lotterygameplusawardsproportion.class);
		model.addAttribute("issuebetmulriple", issuebetmulriple);
		model.addAttribute("issuebetmulriple_lq", issuebetmulriple_lq);
		model.addAttribute("issuebetmulriple_bjdc", issuebetmulriple_bjdc);
		model.addAttribute("issuebetmultiple_dlt", issuebetmultiple_dlt);
		model.addAttribute("gameplusawardsproportion_zq", gameplusawardsproportion_zq);
		model.addAttribute("gameplusawardsproportion_lq", gameplusawardsproportion_lq);
		model.addAttribute("gameplusawardsproportion_bjdc", gameplusawardsproportion_bjdc);
		model.addAttribute("gameplusawardsproportion_dlt", gameplusawardsproportion_dlt);
		return "/lottery/betlotterychannelaccess/detail";
	}
	/**
	 * 出票参数修改
	 */
	@RequestMapping(value = "/detail/update")
	public @ResponseBody
	ReturnDatas detailupdateparam(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			String company = "";
			if(",A101,".equals(agent.getParentids())){
				company = agentid;
			}else{
				company = agent.getParentids().split(",")[2];
			}
			String ticketzuqiu = request.getParameter("ticketzuqiu");
			String ticketlq = request.getParameter("ticketlq");
			String ticketbjdc = request.getParameter("ticketbjdc");
			String ticketdlt = request.getParameter("ticketdlt");
			
			String issuebetmulriple = request.getParameter("issuebetmulriple");
			String issuebetmulriple_lq = request.getParameter("issuebetmulriple_lq");
			String issuebetmulriple_bjdc = request.getParameter("issuebetmulriple_bjdc");
			String issuebetmultiple_dlt = request.getParameter("issuebetmultiple_dlt");
			
			String bigticketodds_zq = request.getParameter("bigticketodds_zq");
			String bigticketodds_lq = request.getParameter("bigticketodds_lq");
			String bigticketodds_bjdc = request.getParameter("bigticketodds_bjdc");
			
			String ticketbetmultiple_zq = request.getParameter("ticketbetmultiple_zq");
			String ticketbetmultiple_lq = request.getParameter("ticketbetmultiple_lq");
			String ticketbetmultiple_bjdc = request.getParameter("ticketbetmultiple_bjdc");
			
			String autoticketzq = request.getParameter("autoticketzq");
			String autoticketlq = request.getParameter("autoticketlq");
			String autoticketbjdc = request.getParameter("autoticketbjdc");
			
			if("1".equals(ticketzuqiu)){
				autoticketzq = "3";
			}
			if("1".equals(ticketlq)){
				autoticketlq = "3";
			}
			if("1".equals(ticketbjdc)){
				autoticketbjdc = "3";
			}
			
			dicDataService.update(new Finder("update t_dic_data set value =:value where code=:code and remark=:company ").setParam("value", issuebetmulriple).setParam("code", "issuebetmulriple").setParam("company", company));
			dicDataService.update(new Finder("update t_dic_data set value =:value where code=:code and remark=:company ").setParam("value", issuebetmulriple_lq).setParam("code", "issuebetmulriple_lq").setParam("company", company));
			dicDataService.update(new Finder("update t_dic_data set value =:value where code=:code and remark=:company ").setParam("value", issuebetmulriple_bjdc).setParam("code", "issuebetmulriple_bjdc").setParam("company", company));
			dicDataService.update(new Finder("update t_dic_data set value =:value where code=:code and remark=:company ").setParam("value", issuebetmultiple_dlt).setParam("code", "issuebetmultiple_dlt").setParam("company", company));
			
			dicDataService.update(new Finder("update lottery_gameplusawardsproportion set systemissue=:systemissue,autobigticket=:autobigticket,bigticketodds=:bigticketodds,ticketbetmultiple=:ticketbetmultiple where lotteryid=:lotteryid and agentid=:agentid").setParam("systemissue", ticketzuqiu).setParam("autobigticket", autoticketzq).setParam("bigticketodds", bigticketodds_zq).setParam("ticketbetmultiple", ticketbetmultiple_zq).setParam("lotteryid", "zuqiu").setParam("agentid", company));
			dicDataService.update(new Finder("update lottery_gameplusawardsproportion set systemissue=:systemissue,autobigticket=:autobigticket,bigticketodds=:bigticketodds,ticketbetmultiple=:ticketbetmultiple where lotteryid=:lotteryid and agentid=:agentid").setParam("systemissue", ticketlq).setParam("autobigticket", autoticketlq).setParam("bigticketodds", bigticketodds_lq).setParam("ticketbetmultiple", ticketbetmultiple_lq).setParam("lotteryid", "lq").setParam("agentid", company));
			dicDataService.update(new Finder("update lottery_gameplusawardsproportion set systemissue=:systemissue,autobigticket=:autobigticket,bigticketodds=:bigticketodds,ticketbetmultiple=:ticketbetmultiple where lotteryid=:lotteryid and agentid=:agentid").setParam("systemissue", ticketbjdc).setParam("autobigticket", autoticketbjdc).setParam("bigticketodds", bigticketodds_bjdc).setParam("ticketbetmultiple", ticketbetmultiple_bjdc).setParam("lotteryid", "bjdc").setParam("agentid", company));
			
			dicDataService.update(new Finder("update lottery_gameplusawardsproportion set systemissue=:systemissue where lotteryid=:lotteryid and agentid=:agentid").setParam("systemissue", ticketdlt).setParam("lotteryid", "dlt").setParam("agentid", company));
			
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	}
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betLotterychannelaccess
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetLotterychannelaccess betLotterychannelaccess) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betLotterychannelaccess);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betLotterychannelaccess
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetLotterychannelaccess betLotterychannelaccess) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetLotterychannelaccess> datas=betLotterychannelaccessService.findListDataByFinder(null,page,BetLotterychannelaccess.class,betLotterychannelaccess);
			returnObject.setQueryBean(betLotterychannelaccess);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetLotterychannelaccess betLotterychannelaccess) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betLotterychannelaccessService.findDataExportExcel(null,listurl, page,BetLotterychannelaccess.class,betLotterychannelaccess);
		String fileName="betLotterychannelaccess"+GlobalStatic.excelext;
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
		return "/lottery/betlotterychannelaccess/betlotterychannelaccessLook";
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
		  BetLotterychannelaccess betLotterychannelaccess = betLotterychannelaccessService.findBetLotterychannelaccessById(id);
		   returnObject.setData(betLotterychannelaccess);
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
	ReturnDatas saveorupdate(Model model,BetLotterychannelaccess betLotterychannelaccess,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			Double rebate = betLotterychannelaccess.getRebate();
			if(rebate==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				return returnObject;
			}
			betLotterychannelaccess.setRebate(rebate/100);
			String id = request.getParameter("id");
			if(StringUtils.isEmpty(id)){//设置渠道归属
				betLotterychannelaccess.setCompany("A101");
				betLotterychannelaccess.setBelonging(1);
				betLotterychannelaccessService.save(betLotterychannelaccess);
			}else{
				betLotterychannelaccessService.update(betLotterychannelaccess);
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
		return "/lottery/betlotterychannelaccess/betlotterychannelaccessCru";
	}
	
	/**
	 * 删除操作
	 */
//	@RequestMapping(value="/delete")
//	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {
//
//			// 执行删除
//		try {
//		  String  strId=request.getParameter("id");
//		  java.lang.Integer id=null;
//		  if(StringUtils.isNotBlank(strId)){
//			 id= java.lang.Integer.valueOf(strId.trim());
//				betLotterychannelaccessService.deleteById(id,BetLotterychannelaccess.class);
//				return new ReturnDatas(ReturnDatas.SUCCESS,
//						MessageUtils.DELETE_SUCCESS);
//			} else {
//				return new ReturnDatas(ReturnDatas.WARNING,
//						MessageUtils.DELETE_WARNING);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
//	}
	
	/**
	 * 删除多条记录
	 * 
	 */
//	@RequestMapping("/delete/more")
//	public @ResponseBody
//	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
//		String records = request.getParameter("records");
//		if(StringUtils.isBlank(records)){
//			 return new ReturnDatas(ReturnDatas.ERROR,
//					MessageUtils.DELETE_ALL_FAIL);
//		}
//		String[] rs = records.split(",");
//		if (rs == null || rs.length < 1) {
//			return new ReturnDatas(ReturnDatas.ERROR,
//					MessageUtils.DELETE_NULL_FAIL);
//		}
//		try {
//			List<String> ids = Arrays.asList(rs);
//			betLotterychannelaccessService.deleteByIds(ids,BetLotterychannelaccess.class);
//		} catch (Exception e) {
//			return new ReturnDatas(ReturnDatas.ERROR,
//					MessageUtils.DELETE_ALL_FAIL);
//		}
//		return new ReturnDatas(ReturnDatas.SUCCESS,
//				MessageUtils.DELETE_ALL_SUCCESS);
//		
//		
//	}

}
