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

import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.lottery.entity.SoccerLeagueOnewin;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueOnewinService;
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
 * @version  2017-08-30 10:10:03
 * @see org.springrain.lottery.web.SoccerLeagueOnewin
 */
@Controller
@RequestMapping(value="/soccerleagueonewin")
public class SoccerLeagueOnewinController  extends BaseController {
	@Resource
	private ISoccerLeagueOnewinService soccerLeagueOnewinService;
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
	
	private String listurl="/lottery/soccerleagueonewin/soccerleagueonewinList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueOnewin
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueOnewin soccerLeagueOnewin) 
			throws Exception {
		if(soccerLeagueOnewin.getState()==null){
			soccerLeagueOnewin.setState(1);
		}
		ReturnDatas returnObject = listjson(request, model, soccerLeagueOnewin);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueOnewin
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueOnewin soccerLeagueOnewin) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeagueOnewin> datas=soccerLeagueOnewinService.findListDataByFinder(null,page,SoccerLeagueOnewin.class,soccerLeagueOnewin);
			returnObject.setQueryBean(soccerLeagueOnewin);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueOnewin soccerLeagueOnewin) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueOnewinService.findDataExportExcel(null,listurl, page,SoccerLeagueOnewin.class,soccerLeagueOnewin);
		String fileName="soccerLeagueOnewin"+GlobalStatic.excelext;
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
		return "/lottery/soccerleagueonewin/soccerleagueonewinLook";
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
		  SoccerLeagueOnewin soccerLeagueOnewin = soccerLeagueOnewinService.findSoccerLeagueOnewinById(id);
		   returnObject.setData(soccerLeagueOnewin);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeagueOnewin soccerLeagueOnewin,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				SoccerLeagueOnewin soccerLeagueOnewin2 = soccerLeagueOnewinService.findSoccerLeagueOnewinById(id);
				 SoccerLeagueArrange SoccerLeagueArrange2 = soccerLeagueArrangeService.queryForObject(new Finder("select * from soccer_league_arrange where  mid = :mid").setParam("mid", soccerLeagueOnewin2.getMid()), SoccerLeagueArrange.class);
					if(SoccerLeagueArrange2!=null){
						if(3==SoccerLeagueArrange2.getState()&&"1".equals(state)){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该赔率所属赛次已禁用,不可启用该赔率");
							return returnObject;
						}
					}
					soccerLeagueOnewinService.update(new Finder("update soccer_league_onewin set state=:state where id=:id").setParam("state",state).setParam("id", id));
				
			}else{
				//soccerLeagueOnewinService.saveorupdate(soccerLeagueOnewin);
				if(soccerLeagueOnewin.getId()!=null){
					soccerLeagueOnewinService.update(soccerLeagueOnewin, true);
				}else{
					soccerLeagueOnewinService.save(soccerLeagueOnewin);
				}
			}
		
		
			
			
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
		return "/lottery/soccerleagueonewin/soccerleagueonewinCru";
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
				soccerLeagueOnewinService.deleteById(id,SoccerLeagueOnewin.class);
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
			soccerLeagueOnewinService.deleteByIds(ids,SoccerLeagueOnewin.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
