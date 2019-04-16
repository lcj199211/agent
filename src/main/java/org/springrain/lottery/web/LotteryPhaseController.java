package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.lottery.entity.BjdcArrange;
import org.springrain.lottery.entity.BjdcResult;
import org.springrain.lottery.entity.LotteryPhase;
import org.springrain.lottery.service.ILotteryPhaseService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;


/**
 * TODO 期次控制类
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:34:26
 * @see org.springrain.lottery.web.LotteryPhase
 */
@Controller
@RequestMapping(value="/lotteryphase")
public class LotteryPhaseController  extends BaseController {
	@Resource
	private ILotteryPhaseService lotteryPhaseService;
	
	private String listurl="/lottery/lotteryphase/lotteryphaseList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param lotteryPhase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,LotteryPhase lotteryPhase) 
			throws Exception {
		String phaseno = null;
		Integer istrue = null;
		if(!StringUtils.isEmpty(lotteryPhase.getPhaseno())){
			if(lotteryPhase.getPhaseno()!=null){
				phaseno = lotteryPhase.getPhaseno();
			}
		}
		if(lotteryPhase.getIstrue()!=null){
			if(lotteryPhase.getIstrue()==100){
				istrue = null;
			}else{
				istrue = lotteryPhase.getIstrue();
			}
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		if(StringUtils.isBlank(starttime)){
			starttime="0000-01-01";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-01-01";
		}
		java.sql.Date startDate = java.sql.Date.valueOf(starttime);
		java.sql.Date endDate=java.sql.Date.valueOf(endtime);
		List<LotteryPhase> datas = null;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			datas = lotteryPhaseService.queryForList(new Finder("select * from lottery_phase where (:phaseno is null or phaseno like :phaseno) and (:istrue is null or istrue = :istrue) order by phaseno DESC ").setParam("phaseno", phaseno).setParam("istrue", istrue),LotteryPhase.class,page);
		}else{
			datas = lotteryPhaseService.queryForList(new Finder("select * from lottery_phase where (:phaseno is null or phaseno like :phaseno) and (:istrue is null or istrue = :istrue) and substr(starttime,1,10)>=:starttime and substr(starttime,1,10)<=:endtime order by phaseno DESC").setParam("phaseno", phaseno).setParam("istrue", istrue).setParam("starttime",startDate).setParam("endtime", endDate),LotteryPhase.class,page);
		}
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}

		returnObject.setQueryBean(lotteryPhase);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("startTime", startDate);
		model.addAttribute("endTime", endDate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param lotteryPhase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,LotteryPhase lotteryPhase) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<LotteryPhase> datas=lotteryPhaseService.findListDataByFinder(null,page,LotteryPhase.class,lotteryPhase);
			returnObject.setQueryBean(lotteryPhase);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,LotteryPhase lotteryPhase) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = lotteryPhaseService.findDataExportExcel(null,listurl, page,LotteryPhase.class,lotteryPhase);
		String fileName="lotteryPhase"+GlobalStatic.excelext;
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
		return "/lottery/lotteryphase/lotteryphaseLook";
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
		  LotteryPhase lotteryPhase = lotteryPhaseService.findLotteryPhaseById(id);
		   returnObject.setData(lotteryPhase);
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
	ReturnDatas saveorupdate(Model model,LotteryPhase lotteryPhase,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//确认期次信息，人工确认。
			if("1".equals(request.getParameter("k"))){
				//确认
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				LotteryPhase lotteryphase = lotteryPhaseService.queryForObject(new Finder("select * from lottery_phase where id=:id ").setParam("id", id), LotteryPhase.class);
				if(lotteryphase!=null){
					lotteryPhaseService.update(new Finder("update lottery_phase set istrue=:istrue where id=:id").setParam("istrue",state).setParam("id", id));
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("大乐透期次信息不正确");
					return returnObject;
				}
			}else{
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String wincode = null;
				String poolamount = null;
				String addpoolamount = null;
				String saleamount = null;
				String starttime = null;
				String endtime = null;
				String localendtime = null;
				
				if(request.getParameter("wincode")!=null){
					wincode = request.getParameter("wincode").trim();
					lotteryPhase.setWincode(wincode);
				}
				if(request.getParameter("poolamount")!=null){
					poolamount = request.getParameter("poolamount").trim();
					lotteryPhase.setPoolamount(new BigDecimal(poolamount));
				}
				if(request.getParameter("addpoolamount")!=null){
					addpoolamount = request.getParameter("addpoolamount").trim();
					lotteryPhase.setAddpoolamount(new BigDecimal(addpoolamount));
				}
				if(request.getParameter("saleamount")!=null){
					saleamount = request.getParameter("saleamount").trim();
					lotteryPhase.setSaleamount(new BigDecimal(saleamount));
				}
				if(request.getParameter("starttime")!=null){
					starttime = request.getParameter("starttime").trim();
					lotteryPhase.setStarttime(format.parse(starttime));
				}
				if(request.getParameter("endtime")!=null){
					endtime = request.getParameter("endtime").trim();
					lotteryPhase.setEndtime(format.parse(endtime));
				}
				if(request.getParameter("localendtime")!=null){
					localendtime = request.getParameter("localendtime").trim();
					lotteryPhase.setLocalendtime(format.parse(localendtime));
				}
				String phaseno = request.getParameter("phaseno");
				lotteryPhaseService.update(new Finder("update lottery_phase set wincode=:wincode,poolamount=:poolamount,addpoolamount=:addpoolamount,saleamount=:saleamount,starttime=:starttime,endtime=:endtime,localendtime=:localendtime where phaseno=:phaseno ").setParam("wincode", wincode).setParam("poolamount", poolamount).setParam("addpoolamount", addpoolamount).setParam("saleamount", saleamount).setParam("starttime", starttime).setParam("endtime", endtime).setParam("localendtime", localendtime).setParam("phaseno", phaseno));
				//System.out.println("欢迎来到大乐透修改页面");
				//lotteryPhaseService.saveorupdate(lotteryPhase);
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
		return "/lottery/lotteryphase/lotteryphaseCru";
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
				lotteryPhaseService.deleteById(id,LotteryPhase.class);
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
			lotteryPhaseService.deleteByIds(ids,LotteryPhase.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
