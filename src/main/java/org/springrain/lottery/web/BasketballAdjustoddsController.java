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

import org.springrain.lottery.entity.BasketballAdjustodds;
import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.service.IBasketballAdjustoddsService;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.utils.basketballWeekOfDate;
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
 * @version  2017-11-07 13:59:14
 * @see org.springrain.lottery.web.BasketballAdjustodds
 */
@Controller
@RequestMapping(value="/basketballadjustodds")
public class BasketballAdjustoddsController  extends BaseController {
	@Resource
	private IBasketballAdjustoddsService basketballAdjustoddsService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	private String listurl="/lottery/basketballadjustodds/basketballadjustoddsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballAdjustodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BasketballAdjustodds basketballAdjustodds) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, basketballAdjustodds);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param basketballAdjustodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BasketballAdjustodds basketballAdjustodds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BasketballAdjustodds> datas=basketballAdjustoddsService.findListDataByFinder(null,page,BasketballAdjustodds.class,basketballAdjustodds);
			returnObject.setQueryBean(basketballAdjustodds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BasketballAdjustodds basketballAdjustodds) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = basketballAdjustoddsService.findDataExportExcel(null,listurl, page,BasketballAdjustodds.class,basketballAdjustodds);
		String fileName="basketballAdjustodds"+GlobalStatic.excelext;
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
		return "/lottery/basketballadjustodds/basketballadjustoddsLook";
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
		  BasketballAdjustodds basketballAdjustodds = basketballAdjustoddsService.findBasketballAdjustoddsById(id);
		   returnObject.setData(basketballAdjustodds);
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
	ReturnDatas saveorupdate(Model model,BasketballAdjustodds basketballAdjustodds,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			basketballAdjustoddsService.saveorupdate(basketballAdjustodds);
			
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
		if("1".equals(request.getParameter("k"))){
			  ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			  String  zid=request.getParameter("zid");
			  if(StringUtils.isNotBlank(zid)){
				  BasketballAdjustodds basketballAdjustodds = basketballAdjustoddsService.queryForObject( new Finder("select a.*,b.num,b.matchname,b.starttime,b.hometeam,b.awayteam,b.endtime,b.matchdate  from basketball_adjustodds a left join basketball_league_arrange b on a.zid = b.zid where a.zid=:zid ").setParam("zid", zid), BasketballAdjustodds.class);
				  basketballAdjustodds.setNum(basketballWeekOfDate.getWeekOfDate(basketballAdjustodds.getMatchdate())+basketballAdjustodds.getNum());
				  BasketballLeagueOdds basketballLeagueOdds = basketballLeagueOddsService.queryForObject(new Finder("select * from basketball_league_odds where zid = :zid").setParam("zid", zid), BasketballLeagueOdds.class);
				  model.addAttribute("basketballLeagueOdds", basketballLeagueOdds);
				  returnObject.setData(basketballAdjustodds);
			  }
			  model.addAttribute(GlobalStatic.returnDatas, returnObject);
			  return "/lottery/basketballleagueodds/adjustoddsCru";
			
		}else{
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/basketballadjustodds/basketballadjustoddsCru";
		}
		
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
				basketballAdjustoddsService.deleteById(id,BasketballAdjustodds.class);
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
			basketballAdjustoddsService.deleteByIds(ids,BasketballAdjustodds.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
