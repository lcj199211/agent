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

import org.springrain.lottery.entity.BasketballLeague;
import org.springrain.lottery.entity.BasketballLeagueArrange;
import org.springrain.lottery.service.IBasketballLeagueArrangeService;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.service.IBasketballLeagueService;
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
 * @version  2017-11-07 13:38:01
 * @see org.springrain.lottery.web.BasketballLeague
 */
@Controller
@RequestMapping(value="/basketballleague")
public class BasketballLeagueController  extends BaseController {
	@Resource
	private IBasketballLeagueService basketballLeagueService;
	@Resource
	private IBasketballLeagueArrangeService basketballLeagueArrangeService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	private String listurl="/lottery/basketballleague/basketballleagueList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballLeague
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BasketballLeague basketballLeague) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String matchid2 = request.getParameter("id2");
			List<BasketballLeagueArrange> datas = basketballLeagueService.queryForList(new Finder("select * from basketball_league_arrange where  matchid2 = :matchid2" ).setParam("matchid2", matchid2), BasketballLeagueArrange.class,page);
			returnObject.setData(datas);
			returnObject.setQueryBean(basketballLeague);
			returnObject.setPage(page);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("matchid2", matchid2);
			return "/lottery/basketballleague/basketballarrangeList";
		}else{
			String name = null;
			if(basketballLeague.getName()!=null){
				name = "%"+basketballLeague.getName()+"%";
			}
			String state = request.getParameter("state");
			if("100".equals(state)){
				state = null;
			}
			String ishot = request.getParameter("ishot");
			if("100".equals(ishot)){
				ishot = null;
			}
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			List<BasketballLeague> datas=basketballLeagueService.queryForList(new Finder("select * from basketball_league where (:name is null or name like :name) and (:state is null or state = :state) and (:ishot is null or ishot = :ishot)").setParam("name", name).setParam("state", state).setParam("ishot", ishot), BasketballLeague.class, page);
			returnObject.setQueryBean(basketballLeague);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param basketballLeague
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BasketballLeague basketballLeague) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BasketballLeague> datas=basketballLeagueService.findListDataByFinder(null,page,BasketballLeague.class,basketballLeague);
			returnObject.setQueryBean(basketballLeague);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BasketballLeague basketballLeague) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = basketballLeagueService.findDataExportExcel(null,listurl, page,BasketballLeague.class,basketballLeague);
		String fileName="basketballLeague"+GlobalStatic.excelext;
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
		return "/lottery/basketballleague/basketballleagueLook";
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
		  BasketballLeague basketballLeague = basketballLeagueService.findBasketballLeagueById(id);
		   returnObject.setData(basketballLeague);
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
	ReturnDatas saveorupdate(Model model,BasketballLeague basketballLeague,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String  id=request.getParameter("id");
			String  state=request.getParameter("state");
			 BasketballLeague basketballLeague2 = basketballLeagueService.findBasketballLeagueById(id);
			if("1".equals(request.getParameter("k"))){
				basketballLeagueService.update(new Finder("update basketball_league set state=:state where id=:id").setParam("state",state).setParam("id", id));
				basketballLeagueArrangeService.update(new Finder("update basketball_league_arrange set state=:state where matchid2 = :matchid2 and endtime > now()").setParam("state",state).setParam("matchid2", basketballLeague2.getId2()));
				basketballLeagueOddsService.update(new Finder("update basketball_league_odds a LEFT JOIN basketball_league_arrange b on a.zid = b.zid set a.state=:state where  a.arrangeid2 = :arrangeid2 and b.endtime >NOW()").setParam("state",state).setParam("arrangeid2", basketballLeague2.getId2()));
			}else{
				//basketballLeagueService.saveorupdate(basketballLeague);
				if(basketballLeague.getId()!=null){
					basketballLeagueService.update(basketballLeague, true);
				}else{
					basketballLeagueService.save(basketballLeague);
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
		return "/lottery/basketballleague/basketballleagueCru";
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
				basketballLeagueService.deleteById(id,BasketballLeague.class);
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
			basketballLeagueService.deleteByIds(ids,BasketballLeague.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
