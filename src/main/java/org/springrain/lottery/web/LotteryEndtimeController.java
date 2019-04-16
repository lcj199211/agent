package  org.springrain.lottery.web;

import java.io.File;
import java.util.Arrays;
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

import org.springrain.lottery.entity.LotteryEndtime;
import org.springrain.lottery.entity.Lotterygameplusawardsproportion;
import org.springrain.lottery.service.ILotteryEndtimeService;
import org.springrain.system.service.IDicDataService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;

import com.alibaba.fastjson.JSON;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-12 14:50:03
 * @see org.springrain.lottery.web.LotteryEndtime
 */
@Controller
@RequestMapping(value="/lotteryendtime")
public class LotteryEndtimeController  extends BaseController {
	@Resource
	private ILotteryEndtimeService lotteryEndtimeService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private ICached cached;
	private String listurl="/lottery/lotteryendtime/lotteryendtimeList";
	
	
	
	/**
	 * 游戏加奖页面
	 * 
	 * @param request
	 * @param model
	 * @param lotteryEndtime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gameplusawardsproportion")
	public String gameplusawardsproportion(HttpServletRequest request, Model model,Lotterygameplusawardsproportion lotterygameplusawardsproportion) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Lotterygameplusawardsproportion> queryForList = lotteryEndtimeService.queryForList(new Finder("select *from lottery_gameplusawardsproportion where agentid=:agentid ").setParam("agentid", agentId),Lotterygameplusawardsproportion.class);
		returnObject.setQueryBean(lotterygameplusawardsproportion);
		returnObject.setPage(page);
		returnObject.setData(queryForList);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/lotteryendtime/lotterygameplusawardsproportionList";
	}
	   
	@RequestMapping(value = "/gameplusawardsproportion/update/pre")
	public String updateprexxx(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 Lotterygameplusawardsproportion lotterygameplusawardsproportion = lotteryEndtimeService.queryForObject(new Finder("select *from lottery_gameplusawardsproportion where id=:id ").setParam("id", id), Lotterygameplusawardsproportion.class);
		   returnObject.setData(lotterygameplusawardsproportion);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/lotteryendtime/lotterygameplusawardsproportionCru";
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/gameplusawardsproportion/update")
	@ResponseBody      
	public ReturnDatas updategameplusawardsproportion(Model model,Lotterygameplusawardsproportion lotterygameplusawardsproportion,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			lotteryEndtimeService.update(new Finder("update lottery_gameplusawardsproportion set plusawardsproportion=:plusawardsproportion where id=:id ").setParam("plusawardsproportion", lotterygameplusawardsproportion.getPlusawardsproportion()/100).setParam("id", lotterygameplusawardsproportion.getId()));
		} catch (Exception e) {
			e.printStackTrace();
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
	 * @param lotteryEndtime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,LotteryEndtime lotteryEndtime) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, lotteryEndtime);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param lotteryEndtime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,LotteryEndtime lotteryEndtime) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<LotteryEndtime> datas=lotteryEndtimeService.findListDataByFinder(null,page,LotteryEndtime.class,lotteryEndtime);
		if(datas!=null){
			for(LotteryEndtime lottery : datas ){
				if("zuqiu".equals(lottery.getLotteryid())){
					Integer wbcp = dicDataService.queryForObject(new Finder("select value from t_dic_data where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "500caipiao"), Integer.class);
					Integer zgjc = dicDataService.queryForObject(new Finder("select value from t_dic_data where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "sporttery"), Integer.class);
					Integer zlc = dicDataService.queryForObject(new Finder("select value from t_dic_data where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "zhiliaocai"), Integer.class);
					lottery.setWbcp(wbcp);
					lottery.setZgjc(zgjc);
					lottery.setZlc(zlc);
				}
			}
		}	
		returnObject.setQueryBean(lotteryEndtime);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,LotteryEndtime lotteryEndtime) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = lotteryEndtimeService.findDataExportExcel(null,listurl, page,LotteryEndtime.class,lotteryEndtime);
		String fileName="lotteryEndtime"+GlobalStatic.excelext;
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
		return "/lottery/lotteryendtime/lotteryendtimeLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  LotteryEndtime lotteryEndtime = lotteryEndtimeService.findLotteryEndtimeById(id);
		  if("zuqiu".equals(lotteryEndtime.getLotteryid())){
				Integer wbcp = dicDataService.queryForObject(new Finder("select value from t_dic_data where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "500caipiao"), Integer.class);
				Integer zgjc = dicDataService.queryForObject(new Finder("select value from t_dic_data where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "sporttery"), Integer.class);
				Integer zlc = dicDataService.queryForObject(new Finder("select value from t_dic_data where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "zhiliaocai"), Integer.class);
				lotteryEndtime.setWbcp(wbcp);
				lotteryEndtime.setZgjc(zgjc);
				lotteryEndtime.setZlc(zlc);
			}
		   returnObject.setData(lotteryEndtime);
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
	@ResponseBody      
	public ReturnDatas saveorupdate(Model model,LotteryEndtime lotteryEndtime,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//lotteryEndtimeService.saveorupdate(lotteryEndtime);
			String soccerodds = request.getParameter("soccerodds");
			if("1".equals(soccerodds)){
				//500彩票
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "500caipiao").setParam("value", 1));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "sporttery").setParam("value", 0));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "zhiliaocai").setParam("value", 0));
			}else if("2".equals(soccerodds)){
				//中国竞彩
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "500caipiao").setParam("value", 0));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "sporttery").setParam("value", 1));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "zhiliaocai").setParam("value", 0));
			}else if("3".equals(soccerodds)){
				//知了彩
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "500caipiao").setParam("value", 0));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "sporttery").setParam("value", 0));
				dicDataService.update(new Finder("update t_dic_data set value = :value where code = :code and id = :id").setParam("code", "soccer_odds").setParam("id", "zhiliaocai").setParam("value", 1));
			}
			if(lotteryEndtime.getId()!=null){
				lotteryEndtimeService.update(lotteryEndtime, true);
			}else{
				lotteryEndtimeService.save(lotteryEndtime);
			}
			cached.updateCached("findEndTime".getBytes(), JSON.toJSONString(lotteryEndtime.getEndsecond()).getBytes(), null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
		return "/lottery/lotteryendtime/lotteryendtimeCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody      
	public  ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				lotteryEndtimeService.deleteById(id,LotteryEndtime.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
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
	@ResponseBody      
	public ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			lotteryEndtimeService.deleteByIds(ids,LotteryEndtime.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
