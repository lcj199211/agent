package  org.springrain.lottery.web;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetGameclass;
import org.springrain.lottery.entity.BetPeriod;
import org.springrain.lottery.service.IBetGameclassService;
import org.springrain.lottery.service.IBetPeriodService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-09 16:17:46
 * @see org.springrain.lottery.web.BetPeriod
 */
@Controller
@RequestMapping(value="/betperiod")
public class BetPeriodController  extends BaseController {
	@Resource
	private IBetPeriodService betPeriodService;
	@Resource
	private IBetGameclassService betGameclassService;
	
	private String listurl="/lottery/betperiod/betperiodList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betPeriod
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetPeriod betPeriod) 
			throws Exception {
		String parameter = request.getParameter("shoudongkaijiang");
		if(parameter==null){
			ReturnDatas returnObject = listjson(request, model, betPeriod);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("gameclassid", betPeriod.getGameclassid());
			return listurl;
		}else if ("1".equals(parameter)){
			String id = request.getParameter("id");
			BetPeriod betPeriod1 = betPeriodService.findBetPeriodById(Integer.valueOf(id));
			String strgameclassid = request.getParameter("gameclassid");
			Integer gameclassid = Integer.valueOf(strgameclassid);
			BetGameclass betGameclass = betGameclassService.findBetGameclassById(gameclassid);
			model.addAttribute("shoudongkaijiang", betGameclass);
			model.addAttribute("betPeriod1", betPeriod1);
			return "/lottery/shoudongkaijiang";
		}
		return null;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betPeriod
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetPeriod betPeriod) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		page.setOrder("time");
		page.setSort("desc");
		if(betPeriod.getGameclassid()==null){
			betPeriod.setGameclassid(7);
		}
		Date date=new Date();
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		if("1".equals(request.getParameter("yesterday"))){
			Calendar calendar1 = new GregorianCalendar();
			calendar1.setTime(date); 
			calendar1.add(Calendar.DATE,-1);
			Date date3=calendar1.getTime();
			starttime = DateUtils.convertDate2String(date3);
			endtime = DateUtils.convertDate2String(date3);
		}
		Date date1 =DateUtils.convertString2Date(endtime);
		Calendar calendar = new GregorianCalendar();
		if(date1!=null){
			calendar.setTime(date1); 
			calendar.add(Calendar.DATE,1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
		}else{
			calendar.setTime(date); 
			calendar.add(Calendar.DATE,1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime)){
			starttime="0000-00-00";
		}
		List<BetPeriod> datas=null;
		if(StringUtils.isNoneBlank(betPeriod.getQibie())){
			datas=betPeriodService.queryForList(new Finder("select*from bet_period where gameclassid=:gameclassid and qibie=:qibie and time>=:starttime and time<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("gameclassid", betPeriod.getGameclassid()).setParam("qibie", betPeriod.getQibie()), BetPeriod.class, page);
		}else{
			datas=betPeriodService.queryForList(new Finder("select*from bet_period where gameclassid=:gameclassid and time>=:starttime and time<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("gameclassid", betPeriod.getGameclassid()), BetPeriod.class, page);
		}
		
		returnObject.setQueryBean(betPeriod);
		returnObject.setPage(page);
		returnObject.setData(datas);
		if(!"0000-00-00".equals(starttime)){
			model.addAttribute("startTime", starttime);
		}
		Date date2 =DateUtils.convertString2Date(endtime);
		calendar.setTime(date2); 
		calendar.add(Calendar.DATE,-1);
		Date date3=calendar.getTime();
		endtime = DateUtils.convertDate2String(date3);
		model.addAttribute("endTime", endtime);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetPeriod betPeriod) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betPeriodService.findDataExportExcel(null,listurl, page,BetPeriod.class,betPeriod);
		String fileName="betPeriod"+GlobalStatic.excelext;
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
		return "/lottery/betperiod/betperiodLook";
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
		  BetPeriod betPeriod = betPeriodService.findBetPeriodById(id);
		   returnObject.setData(betPeriod);
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
	ReturnDatas saveorupdate(Model model,BetPeriod betPeriod,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			if(request.getParameter("x")!=null){
				String parameter = request.getParameter("id");
				String hs=request.getParameter("hs");
				BetPeriod b=new BetPeriod();
				b.setId(Integer.valueOf(parameter));
				b.setHs(hs);
				betPeriodService.update(b, true);
				
			}else{
				betPeriodService.saveorupdate(betPeriod);
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
		return "/lottery/betperiod/betperiodCru";
	}
	
	/**
	 * 删除操作
	 */
	/*@RequestMapping(value="/delete")*/
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				betPeriodService.deleteById(id,BetPeriod.class);
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
	/*@RequestMapping("/delete/more")*/
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
			betPeriodService.deleteByIds(ids,BetPeriod.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
