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

import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.lottery.entity.BjdcOrderContent;
import org.springrain.lottery.service.IBjdcOrderContentService;
import org.springrain.lottery.service.IBjdcOrderService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 09:36:43
 * @see org.springrain.lottery.web.BjdcOrder
 */
@Controller
@RequestMapping(value="/bjdcorder")
public class BjdcOrderController  extends BaseController {
	@Resource
	private IBjdcOrderService bjdcOrderService;
	@Resource
	private IBjdcOrderContentService bjdcOrderContentService;
	private String listurl="/lottery/bjdcorder/bjdcorderList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param bjdcOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BjdcOrder bjdcOrder) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//订单详情
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String orderid = request.getParameter("orderid");
			List<BjdcOrderContent> datas=bjdcOrderContentService.queryForList(new Finder("select a.*,b.matchname,b.hometeam,b.guestteam,b.starttime,b.num,b.endtime,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid ").setParam("orderid", orderid),BjdcOrderContent.class);
			if(datas!=null){
				for(BjdcOrderContent orderContent : datas){
					orderContent.setNum(orderContent.getNum());
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new BjdcOrderContent());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/bjdcorder/bjdcordercontent";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			String result = request.getParameter("result");
			String memberid2 = request.getParameter("memberid2");
			String playmethodid = request.getParameter("playmethodid");
			if("100".equals(playmethodid)){
				playmethodid = null;
			}
			if("100".equals(result)){
				result = null;
			}
			if(StringUtils.isBlank(memberid2)){
				memberid2=null;
			}else{
				memberid2="%"+memberid2+"%";
			}
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BjdcOrder> datas = null;
			Integer contentTotal = 0;
			Double bettingmoneyTotal = 0d;
			Double bettingwinTotal = 0d;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				 datas= bjdcOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("memberid2", memberid2).setParam("result", result).setParam("playmethodid", playmethodid),BjdcOrder.class,page);
				 contentTotal = bjdcOrderService.queryForObject(new Finder("select count(a.id) from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Integer.class);
				 bettingmoneyTotal =bjdcOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
				 bettingwinTotal = bjdcOrderService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where  (:playmethodid is null or b.playmethodid = :playmethodid) and (:memberid2 is null or a.memberid2 like :memberid2) and (:result is null or a.result = :result) order by a.id desc").setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
			}else{
			     datas= bjdcOrderService.queryForList(new Finder("select a.*,b.bettingtime,b.playmethodid,c.name as playmethod from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid LEFT JOIN bjdc_playmethod c on b.playmethodid = c.id where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),BjdcOrder.class,page);
				 contentTotal = bjdcOrderService.queryForObject(new Finder("select count(a.id) from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Integer.class);
				 bettingmoneyTotal =bjdcOrderService.queryForObject(new Finder("select sum(a.bettingmoney) from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
				 bettingwinTotal = bjdcOrderService.queryForObject(new Finder("select sum(a.bettingwin) from bjdc_order a LEFT JOIN bjdc_scheme b on a.schemeid = b.schemeid where substr(b.bettingtime,1,10)>=:starttime and substr(b.bettingtime,1,10)<=:endtime and (:memberid2 is null or a.memberid2 like :memberid2) and (:playmethodid is null or b.playmethodid = :playmethodid) and (:result is null or a.result = :result) order by a.id desc").setParam("starttime",startDate).setParam("endtime", endDate).setParam("playmethodid", playmethodid).setParam("result", result).setParam("memberid2", memberid2),Double.class);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				for(BjdcOrder soccerOrder : datas){
					List<BjdcOrderContent> contentDatas=bjdcOrderContentService.queryForList(new Finder("select a.oddsname,a.fid,a.odds,b.hometeam,b.guestteam,c.oddsrealname from bjdc_order_content a left join bjdc_arrange b on a.fid = b.fid left join bjdc_playmethod_oddsname c on a.oddsname = c.oddsname where  a.orderid = :orderid order by a.orderid").setParam("orderid", soccerOrder.getOrderid()),BjdcOrderContent.class);
					soccerOrder.setOrdercontent(contentDatas);
				}
			}
			returnObject.setQueryBean(bjdcOrder);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute("contentTotal", contentTotal);
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
	 * @param bjdcOrder
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BjdcOrder bjdcOrder) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BjdcOrder> datas=bjdcOrderService.findListDataByFinder(null,page,BjdcOrder.class,bjdcOrder);
			returnObject.setQueryBean(bjdcOrder);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BjdcOrder bjdcOrder) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = bjdcOrderService.findDataExportExcel(null,listurl, page,BjdcOrder.class,bjdcOrder);
		String fileName="bjdcOrder"+GlobalStatic.excelext;
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
		return "/lottery/bjdcorder/bjdcorderLook";
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
		  BjdcOrder bjdcOrder = bjdcOrderService.findBjdcOrderById(id);
		   returnObject.setData(bjdcOrder);
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
	ReturnDatas saveorupdate(Model model,BjdcOrder bjdcOrder,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			
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
		return "/lottery/bjdcorder/bjdcorderCru";
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
				bjdcOrderService.deleteById(id,BjdcOrder.class);
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
			bjdcOrderService.deleteByIds(ids,BjdcOrder.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
