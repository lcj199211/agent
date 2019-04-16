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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.lottery.entity.LotteryPhase;
import org.springrain.lottery.entity.LotteryPhaseDlt;
import org.springrain.lottery.service.ILotteryPhaseDltService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;


/**
 * TODO 结果控制类
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-02-24 10:48:11
 * @see org.springrain.lottery.web.LotteryPhaseDlt
 */
@Controller
@RequestMapping(value="/lotteryphasedlt")
public class LotteryPhaseDltController  extends BaseController {
	@Resource
	private ILotteryPhaseDltService lotteryPhaseDltService;
	
	private String listurl="/lottery/lotteryphasedlt/lotteryphasedltList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param lotteryPhaseDlt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,LotteryPhaseDlt lotteryPhaseDlt) 
			throws Exception {
		Integer istrue = null;
		String phaseno = null;
		if(!StringUtils.isEmpty(lotteryPhaseDlt.getPhaseno())){
			if(lotteryPhaseDlt.getPhaseno()!=null){
				phaseno = lotteryPhaseDlt.getPhaseno();
			}
		}
		if(lotteryPhaseDlt.getIstrue()!=null){
			if(lotteryPhaseDlt.getIstrue()==100){
				istrue = null;
			}else{
				istrue = lotteryPhaseDlt.getIstrue();
			}
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		List<LotteryPhaseDlt> datas = lotteryPhaseDltService.queryForList(new Finder("select * from lottery_phase_dlt where (:istrue is null or istrue = :istrue) and  (:phaseno is null or phaseno like :phaseno) order by phaseno DESC").setParam("istrue",istrue).setParam("phaseno",phaseno),LotteryPhaseDlt.class,page);
		returnObject.setQueryBean(lotteryPhaseDlt);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param lotteryPhaseDlt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,LotteryPhaseDlt lotteryPhaseDlt) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<LotteryPhaseDlt> datas=lotteryPhaseDltService.findListDataByFinder(null,page,LotteryPhaseDlt.class,lotteryPhaseDlt);
			returnObject.setQueryBean(lotteryPhaseDlt);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,LotteryPhaseDlt lotteryPhaseDlt) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = lotteryPhaseDltService.findDataExportExcel(null,listurl, page,LotteryPhaseDlt.class,lotteryPhaseDlt);
		String fileName="lotteryPhaseDlt"+GlobalStatic.excelext;
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
		return "/lottery/lotteryphasedlt/lotteryphasedltLook";
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
			LotteryPhaseDlt lotteryPhaseDlt = lotteryPhaseDltService.findLotteryPhaseDltById(id);
			returnObject.setData(lotteryPhaseDlt);
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
	ReturnDatas saveorupdate(Model model,LotteryPhaseDlt lotteryPhaseDlt,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//确认期次信息，人工确认。
			if("1".equals(request.getParameter("k"))){
				//确认
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				LotteryPhaseDlt lotteryphasedlt = lotteryPhaseDltService.queryForObject(new Finder("select * from lottery_phase_dlt where id=:id ").setParam("id", id), LotteryPhaseDlt.class);
				if(lotteryphasedlt!=null){
					lotteryPhaseDltService.update(new Finder("update lottery_phase_dlt set istrue=:istrue where id=:id").setParam("istrue",state).setParam("id", id));
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("大乐透结果信息不正确");
					return returnObject;
				}
			}else{
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String win1 = null;
				String win1p = null;
				String win2 = null;
				String win3 = null;
				String zwin1 = null;
				String zwin1p = null;
				String zwin2 = null;
				String zwin3 = null;
				String poolamount = null;
				String addpoolamount = null;
				String starttime = null;
				String endtime = null;
				String localendtime = null;
				if(request.getParameter("win1")!=null){
					win1 = request.getParameter("win1").trim();
					lotteryPhaseDlt.setWin1(new BigDecimal(win1));
				}
				if(request.getParameter("win1p")!=null){
					win1p = request.getParameter("win1p").trim();
					lotteryPhaseDlt.setWin1p(new BigDecimal(win1p));
				}
				if(request.getParameter("win2")!=null){
					win2 = request.getParameter("win2").trim();
					lotteryPhaseDlt.setWin2(new BigDecimal(win2));
				}
				if(request.getParameter("win3")!=null){
					win3 = request.getParameter("win3").trim();
					lotteryPhaseDlt.setWin3(new BigDecimal(win3));
				}
				if(request.getParameter("zwin1")!=null){
					zwin1 = request.getParameter("zwin1").trim();
					lotteryPhaseDlt.setZwin1(new BigDecimal(zwin1));
				}
				if(request.getParameter("zwin1p")!=null){
					zwin1p = request.getParameter("zwin1p").trim();
					lotteryPhaseDlt.setZwin1p(new BigDecimal(zwin1p));
				}
				if(request.getParameter("zwin2")!=null){
					zwin2 = request.getParameter("zwin2").trim();
					lotteryPhaseDlt.setZwin2(new BigDecimal(zwin2));
				}
				if(request.getParameter("zwin3")!=null){
					zwin3 = request.getParameter("zwin3").trim();
					lotteryPhaseDlt.setZwin3(new BigDecimal(zwin3));
				}
				if(request.getParameter("poolamount")!=null){
					poolamount = request.getParameter("poolamount").trim();
					lotteryPhaseDlt.setPoolamount(new BigDecimal(poolamount));
				}
				if(request.getParameter("addpoolamount")!=null){
					addpoolamount = request.getParameter("addpoolamount").trim();
					lotteryPhaseDlt.setAddpoolamount(new BigDecimal(addpoolamount));
				}
				if(request.getParameter("starttime")!=null){
					starttime = request.getParameter("starttime").trim();
					lotteryPhaseDlt.setStarttime(format.parse(starttime));
				}
				if(request.getParameter("endtime")!=null){
					endtime = request.getParameter("endtime").trim();
					lotteryPhaseDlt.setEndtime(format.parse(endtime));
				}
				if(request.getParameter("localendtime")!=null){
					localendtime = request.getParameter("localendtime").trim();
					lotteryPhaseDlt.setLocalendtime(format.parse(localendtime));
				}
				String phaseno = request.getParameter("phaseno");
				lotteryPhaseDltService.update(new Finder("update lottery_phase_dlt set win1=:win1,win1p=:win1p,win2=:win2,win3=:win3,zwin1=:zwin1,zwin1p=:zwin1p,zwin2=:zwin2,zwin3=:zwin3,poolamount=:poolamount,addpoolamount=:addpoolamount,starttime=:starttime,endtime=:endtime,localendtime=:localendtime where phaseno=:phaseno ")
				.setParam("win1", win1).setParam("win1p", win1p).setParam("win2", win2).setParam("win3", win3).setParam("zwin1", zwin1).setParam("zwin1p", zwin1p).setParam("zwin2", zwin2).setParam("zwin3", zwin3).setParam("poolamount", poolamount).setParam("addpoolamount", addpoolamount).setParam("starttime", starttime).setParam("endtime", endtime).setParam("localendtime", localendtime).setParam("phaseno", phaseno));
				//System.out.println("欢迎来到大乐透修改页面");
			//lotteryPhaseDltService.saveorupdate(lotteryPhaseDlt);
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
		return "/lottery/lotteryphasedlt/lotteryphasedltCru";
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
				lotteryPhaseDltService.deleteById(id,LotteryPhaseDlt.class);
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
			lotteryPhaseDltService.deleteByIds(ids,LotteryPhaseDlt.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
