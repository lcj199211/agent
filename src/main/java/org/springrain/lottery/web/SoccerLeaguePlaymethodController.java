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

import org.springrain.lottery.entity.SoccerLeaguePlaymethod;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueGoaloddsService;
import org.springrain.lottery.service.ISoccerLeagueHalfalloddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerLeaguePlaymethodService;
import org.springrain.lottery.service.ISoccerLeagueScoreoddsService;
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
 * @version  2017-08-17 17:59:17
 * @see org.springrain.lottery.web.SoccerLeaguePlaymethod
 */
@Controller
@RequestMapping(value="/soccerleagueplaymethod")
public class SoccerLeaguePlaymethodController  extends BaseController {
	@Resource
	private ISoccerLeaguePlaymethodService soccerLeaguePlaymethodService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerLeagueScoreoddsService soccerLeagueScoreoddsService;
	@Resource
	private ISoccerLeagueHalfalloddsService soccerLeagueHalfalloddsService;
	@Resource
	private ISoccerLeagueGoaloddsService soccerLeagueGoaloddsService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	private String listurl="/lottery/soccerleagueplaymethod/soccerleagueplaymethodList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeaguePlaymethod
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeaguePlaymethod soccerLeaguePlaymethod) 
			throws Exception {
		if(soccerLeaguePlaymethod.getState()==null){
			soccerLeaguePlaymethod.setState(1);
		}
		ReturnDatas returnObject = listjson(request, model, soccerLeaguePlaymethod);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeaguePlaymethod
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeaguePlaymethod soccerLeaguePlaymethod) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeaguePlaymethod> datas=soccerLeaguePlaymethodService.findListDataByFinder(null,page,SoccerLeaguePlaymethod.class,soccerLeaguePlaymethod);
			returnObject.setQueryBean(soccerLeaguePlaymethod);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeaguePlaymethod soccerLeaguePlaymethod) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeaguePlaymethodService.findDataExportExcel(null,listurl, page,SoccerLeaguePlaymethod.class,soccerLeaguePlaymethod);
		String fileName="soccerLeaguePlaymethod"+GlobalStatic.excelext;
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
		return "/lottery/soccerleagueplaymethod/soccerleagueplaymethodLook";
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
		  SoccerLeaguePlaymethod soccerLeaguePlaymethod = soccerLeaguePlaymethodService.findSoccerLeaguePlaymethodById(id);
		   returnObject.setData(soccerLeaguePlaymethod);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeaguePlaymethod soccerLeaguePlaymethod,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				soccerLeaguePlaymethodService.update(new Finder("update soccer_league_playmethod set state=:state where id=:id").setParam("state",state).setParam("id", id));
				/*
				if("1".equals(id)||"2".equals(id)||"11".equals(id)||"12".equals(id)){
					soccerLeagueOddsService.update(new Finder("update soccer_league_odds set state=:state where playmethodid=:playmethodid").setParam("state",state).setParam("playmethodid", id));
				}else if("3".equals(id)||"15".equals(id)){
					soccerLeagueScoreoddsService.update(new Finder("update soccer_league_scoreodds set state=:state where playmethodid=:playmethodid").setParam("state",state).setParam("playmethodid", id));
				}else if("4".equals(id)||"8".equals(id)){
					soccerLeagueHalfalloddsService.update(new Finder("update soccer_league_halfallodds set state=:state where playmethodid=:playmethodid").setParam("state",state).setParam("playmethodid", id));
				}else if("5".equals(id)||"9".equals(id)){
					soccerLeagueGoaloddsService.update(new Finder("update soccer_league_goalodds set state=:state where playmethodid=:playmethodid").setParam("state",state).setParam("playmethodid", id));
				}else if("7".equals(id)){
					soccerLeague2choose1oddsService.update(new Finder("update soccer_league_2choose1odds set state=:state where playmethodid=:playmethodid").setParam("state",state).setParam("playmethodid", id));
				}
				*/
			}else{
				Integer type = soccerLeaguePlaymethod.getType();
				if(type==1){
					soccerLeaguePlaymethod.setPlayclass("单关");
				}else if(type==3){
					soccerLeaguePlaymethod.setPlayclass("串关");
				}else{
					throw new Exception("类型错误！");
				}
				soccerLeaguePlaymethodService.saveorupdate(soccerLeaguePlaymethod);
			}
		
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			e.printStackTrace();
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
		return "/lottery/soccerleagueplaymethod/soccerleagueplaymethodCru";
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
				soccerLeaguePlaymethodService.deleteById(id,SoccerLeaguePlaymethod.class);
		  		//soccerLeaguePlaymethodService.update(new Finder("update soccer_league_playmethod set state=:state where id=:id").setParam("state",3).setParam("id", id));
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
			soccerLeaguePlaymethodService.deleteByIds(ids,SoccerLeaguePlaymethod.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
