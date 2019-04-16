package  org.springrain.lottery.web;

import java.io.File;
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

import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.lottery.entity.BetWeekwinorfailrebate;
import org.springrain.lottery.service.IBetSubordinateRebateService;
import org.springrain.lottery.service.IBetWeekwinorfailrebateService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-10 09:50:39
 * @see org.springrain.lottery.web.BetWeekwinorfailrebate
 */
@Controller
@RequestMapping(value="/betweekwinorfailrebate")
public class BetWeekwinorfailrebateController  extends BaseController {
	@Resource
	private IBetWeekwinorfailrebateService betWeekwinorfailrebateService;
	@Resource
	private IBetSubordinateRebateService betSubordinateRebateService;
	
	private String listurl="/lottery/betweekwinorfailrebate/betweekwinorfailrebateList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betWeekwinorfailrebate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetWeekwinorfailrebate betWeekwinorfailrebate) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betWeekwinorfailrebate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betWeekwinorfailrebate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetWeekwinorfailrebate betWeekwinorfailrebate) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(50);
		page.setOrder("weekbetting");
		page.setSort("desc");
		String dateDetail = request.getParameter("date");
		List<BetWeekwinorfailrebate> datas = null;
		String starttime = "";
		String endtime = "";
		if(StringUtils.isNotEmpty(dateDetail)){
			if("week".equals(dateDetail)){
				datas=betWeekwinorfailrebateService.findListDataByFinder(new Finder("SELECT * FROM bet_weekwinorfailrebate WHERE YEARWEEK(date,1)=YEARWEEK(NOW(),1)"),page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
			}else if("lweek".equals(dateDetail)){
				datas=betWeekwinorfailrebateService.findListDataByFinder(new Finder("SELECT * FROM bet_weekwinorfailrebate WHERE YEARWEEK(DATE_ADD(DATE_ADD(date,INTERVAL -1 DAY),INTERVAL -1 WEEK))"),page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
			}
			String time = DateUtils.convertDate2String(new Date());
			starttime = time;
			endtime = time;
		}else if("1".equals(request.getParameter("k"))){
			starttime = request.getParameter("startTime");
			endtime = request.getParameter("endTime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			// ==执行分页查询
			datas=betWeekwinorfailrebateService.findListDataByFinder(new Finder("select *from bet_weekwinorfailrebate where date>=:starttime and date<=:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
		}else{
			datas=betWeekwinorfailrebateService.findListDataByFinder(new Finder("SELECT * FROM bet_weekwinorfailrebate WHERE date=DATE_FORMAT(NOW(),:format)").setParam("format", "%Y-%m-%d"),page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
			String time = DateUtils.convertDate2String(new Date());
			starttime = time;
			endtime = time;
		}
		List<BetSubordinateRebate> rebate = betSubordinateRebateService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark=:remark").setParam("remark", "z"), null, BetSubordinateRebate.class, new BetSubordinateRebate());
		model.addAttribute("rebate", rebate);
		model.addAttribute("startTime", starttime);
		model.addAttribute("endTime", endtime);
		returnObject.setQueryBean(betWeekwinorfailrebate);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetWeekwinorfailrebate betWeekwinorfailrebate) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betWeekwinorfailrebateService.findDataExportExcel(null,listurl, page,BetWeekwinorfailrebate.class,betWeekwinorfailrebate);
		String fileName="betWeekwinorfailrebate"+GlobalStatic.excelext;
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
		return "/lottery/betweekwinorfailrebate/betweekwinorfailrebateLook";
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
		  BetWeekwinorfailrebate betWeekwinorfailrebate = betWeekwinorfailrebateService.findBetWeekwinorfailrebateById(id);
		   returnObject.setData(betWeekwinorfailrebate);
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
	ReturnDatas saveorupdate(Model model,BetWeekwinorfailrebate betWeekwinorfailrebate,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			betWeekwinorfailrebateService.saveorupdate(betWeekwinorfailrebate);
			
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
		return "/lottery/betweekwinorfailrebate/betweekwinorfailrebateCru";
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
				betWeekwinorfailrebateService.deleteById(id,BetWeekwinorfailrebate.class);
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
			betWeekwinorfailrebateService.deleteByIds(ids,BetWeekwinorfailrebate.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
