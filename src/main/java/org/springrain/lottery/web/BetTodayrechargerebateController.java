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
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetTodayrechargerebate;
import org.springrain.lottery.service.IBetSubordinateRebateService;
import org.springrain.lottery.service.IBetTodayrechargerebateService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-12 13:25:02
 * @see org.springrain.lottery.web.BetTodayrechargerebate
 */
@Controller
@RequestMapping(value="/bettodayrechargerebate")
public class BetTodayrechargerebateController  extends BaseController {
	@Resource
	private IBetTodayrechargerebateService betTodayrechargerebateService;
	@Resource
	private IBetSubordinateRebateService betSubordinateRebateService;
	
	private String listurl="/lottery/bettodayrechargerebate/bettodayrechargerebateList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betTodayrechargerebate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetTodayrechargerebate betTodayrechargerebate) 
			throws Exception {
		if("1".equals(request.getParameter("todayrechargerebate"))){
			String id2 = request.getParameter("memberid2");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setSort("desc");
			page.setOrder("receivetime");
			// ==执行分页查询
			List<BetTodayrechargerebate> datas=betTodayrechargerebateService.findListDataByFinder(new Finder("select * from bet_todayrechargerebate where memberid2=:id2 ").setParam("id2", id2),page,BetTodayrechargerebate.class,betTodayrechargerebate);
			returnObject.setQueryBean(betTodayrechargerebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("id2", id2);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/bettodayrechargerebate/bettodayrechargerebateList2";
		}else{
			ReturnDatas returnObject = listjson(request, model, betTodayrechargerebate);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
//			Double rcrebate = betSubordinateRebateService.queryForObject(new Finder("select rebate from bet_subordinate_rebate where remark=:remark ").setParam("remark", "rc"), Double.class);
//			model.addAttribute("rcrebate", rcrebate);
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betTodayrechargerebate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetTodayrechargerebate betTodayrechargerebate) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetTodayrechargerebate> datas=betTodayrechargerebateService.findListDataByFinder(null,page,BetTodayrechargerebate.class,betTodayrechargerebate);
			returnObject.setQueryBean(betTodayrechargerebate);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetTodayrechargerebate betTodayrechargerebate) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betTodayrechargerebateService.findDataExportExcel(null,listurl, page,BetTodayrechargerebate.class,betTodayrechargerebate);
		String fileName="betTodayrechargerebate"+GlobalStatic.excelext;
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
		return "/lottery/bettodayrechargerebate/bettodayrechargerebateLook";
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
		  BetTodayrechargerebate betTodayrechargerebate = betTodayrechargerebateService.findBetTodayrechargerebateById(id);
		   returnObject.setData(betTodayrechargerebate);
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
	ReturnDatas saveorupdate(Model model,BetTodayrechargerebate betTodayrechargerebate,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			betTodayrechargerebateService.saveorupdate(betTodayrechargerebate);
			
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
		return "/lottery/bettodayrechargerebate/bettodayrechargerebateCru";
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
				betTodayrechargerebateService.deleteById(id,BetTodayrechargerebate.class);
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
			betTodayrechargerebateService.deleteByIds(ids,BetTodayrechargerebate.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
