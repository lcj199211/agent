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

import org.springrain.lottery.entity.BasketballLeaguePlaymethod;
import org.springrain.lottery.service.IBasketballLeaguePlaymethodService;
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
 * @version  2017-11-07 13:47:30
 * @see org.springrain.lottery.web.BasketballLeaguePlaymethod
 */
@Controller
@RequestMapping(value="/basketballleagueplaymethod")
public class BasketballLeaguePlaymethodController  extends BaseController {
	@Resource
	private IBasketballLeaguePlaymethodService basketballLeaguePlaymethodService;
	
	private String listurl="/lottery/basketballleagueplaymethod/basketballleagueplaymethodList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballLeaguePlaymethod
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BasketballLeaguePlaymethod basketballLeaguePlaymethod) 
			throws Exception {
		String name = null;
		if(basketballLeaguePlaymethod.getName()!=null){
			name = "%"+basketballLeaguePlaymethod.getName()+"%";
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		List<BasketballLeaguePlaymethod> datas=basketballLeaguePlaymethodService.queryForList(new Finder("select * from basketball_league_playmethod where (:name is null or name like :name) ").setParam("name", name), BasketballLeaguePlaymethod.class, page);
		returnObject.setQueryBean(basketballLeaguePlaymethod);
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
	 * @param basketballLeaguePlaymethod
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BasketballLeaguePlaymethod basketballLeaguePlaymethod) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BasketballLeaguePlaymethod> datas=basketballLeaguePlaymethodService.findListDataByFinder(null,page,BasketballLeaguePlaymethod.class,basketballLeaguePlaymethod);
			returnObject.setQueryBean(basketballLeaguePlaymethod);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BasketballLeaguePlaymethod basketballLeaguePlaymethod) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = basketballLeaguePlaymethodService.findDataExportExcel(null,listurl, page,BasketballLeaguePlaymethod.class,basketballLeaguePlaymethod);
		String fileName="basketballLeaguePlaymethod"+GlobalStatic.excelext;
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
		return "/lottery/basketballleagueplaymethod/basketballleagueplaymethodLook";
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
		  BasketballLeaguePlaymethod basketballLeaguePlaymethod = basketballLeaguePlaymethodService.findBasketballLeaguePlaymethodById(id);
		   returnObject.setData(basketballLeaguePlaymethod);
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
	ReturnDatas saveorupdate(Model model,BasketballLeaguePlaymethod basketballLeaguePlaymethod,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String  id=request.getParameter("id");
			String  state=request.getParameter("state");
			if("1".equals(request.getParameter("k"))){
				basketballLeaguePlaymethodService.update(new Finder("update basketball_league_playmethod set state=:state where id=:id").setParam("state",state).setParam("id", id));
			}else{
				if(basketballLeaguePlaymethod.getId()!=null){
					basketballLeaguePlaymethodService.update(basketballLeaguePlaymethod, true);
				}else{
					basketballLeaguePlaymethodService.save(basketballLeaguePlaymethod);
				}
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
		return "/lottery/basketballleagueplaymethod/basketballleagueplaymethodCru";
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
				basketballLeaguePlaymethodService.deleteById(id,BasketballLeaguePlaymethod.class);
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
			basketballLeaguePlaymethodService.deleteByIds(ids,BasketballLeaguePlaymethod.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
