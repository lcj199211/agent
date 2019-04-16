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
import org.springrain.lottery.entity.BetReportformDistributor;
import org.springrain.lottery.service.IBetReportformDistributorService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-31 10:10:59
 * @see org.springrain.lottery.web.BetReportformDistributor
 */
@Controller
@RequestMapping(value="/betreportformdistributor")
public class BetReportformDistributorController  extends BaseController {
	@Resource
	private IBetReportformDistributorService betReportformDistributorService;
	
	private String listurl="/lottery/betreportformdistributor/betreportformdistributorList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betReportformDistributor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetReportformDistributor betReportformDistributor) 
			throws Exception {
		if("1".equals(request.getParameter("sub"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(50);
			List<BetReportformDistributor> datas=betReportformDistributorService.findListDataByFinder(null,page,BetReportformDistributor.class,betReportformDistributor);
			returnObject.setQueryBean(betReportformDistributor);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betreportformdistributor/subbetreportformdistributorList";
		}else{
			ReturnDatas returnObject = listjson(request, model, betReportformDistributor);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betReportformDistributor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetReportformDistributor betReportformDistributor) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(50);
		
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		if(StringUtils.isBlank(starttime)){
			starttime="0000-00-00";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-00-00";
		}
		// ==执行分页查询
		List<BetReportformDistributor> datas=null;
		if(betReportformDistributor.getDistributorid2()!=null){
			datas=betReportformDistributorService.queryForList(new Finder("select distributorid2,distributornickname,recommendnum,sum(sb) as sb,sum(sc) as sc,sum(sw) as sw, time from bet_reportform_distributor where time>=:starttime and time<=:endtime and distributorid2=:distributorid2   group by time,distributorid2 order by time desc,recommendnum desc").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("distributorid2", betReportformDistributor.getDistributorid2()), BetReportformDistributor.class, page);			
		}else{
			datas=betReportformDistributorService.queryForList(new Finder("select distributorid2,distributornickname,recommendnum,sum(sb) as sb,sum(sc) as sc,sum(sw) as sw, time from bet_reportform_distributor where time>=:starttime and time<=:endtime   group by time,distributorid2 order by time desc,recommendnum desc").setParam("starttime",starttime ).setParam("endtime", endtime), BetReportformDistributor.class, page);
		}
		
		if("0000-00-00".equals(starttime)){
			starttime="";
		}
		if("9999-00-00".equals(endtime)){
			endtime="";
		}
		model.addAttribute("startTime", starttime);
		model.addAttribute("endTime", endtime);
		
		
		
		// ==执行分页查询
//		List<BetReportformDistributor> datas=betReportformDistributorService.findListDataByFinder(null,page,BetReportformDistributor.class,betReportformDistributor);
//		List<BetReportformDistributor> datas=betReportformDistributorService.queryForList(new Finder("select distributorid2,distributornickname,recommendnum,sum(sb) as sb,sum(sc) as sc,sum(sw) as sw, time from bet_reportform_distributor  group by time,distributorid2 order by time desc,recommendnum desc"), BetReportformDistributor.class, page);
		returnObject.setQueryBean(betReportformDistributor);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetReportformDistributor betReportformDistributor) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betReportformDistributorService.findDataExportExcel(null,listurl, page,BetReportformDistributor.class,betReportformDistributor);
		String fileName="betReportformDistributor"+GlobalStatic.excelext;
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
		return "/lottery/betreportformdistributor/betreportformdistributorLook";
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
		  BetReportformDistributor betReportformDistributor = betReportformDistributorService.findBetReportformDistributorById(id);
		   returnObject.setData(betReportformDistributor);
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
	ReturnDatas saveorupdate(Model model,BetReportformDistributor betReportformDistributor,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			betReportformDistributorService.saveorupdate(betReportformDistributor);
			
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
		return "/lottery/betreportformdistributor/betreportformdistributorCru";
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
				betReportformDistributorService.deleteById(id,BetReportformDistributor.class);
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
			betReportformDistributorService.deleteByIds(ids,BetReportformDistributor.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
