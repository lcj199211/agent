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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springrain.lottery.entity.SoccerLeagueTeam;
import org.springrain.lottery.service.ISoccerLeagueTeamService;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;
import org.springrain.frame.cached.ICached;
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
 * @version  2017-08-17 17:05:08
 * @see org.springrain.lottery.web.SoccerLeagueTeam
 */
@Controller
@RequestMapping(value="/soccerleagueteam")
public class SoccerLeagueTeamController  extends BaseController {
	@Resource
	private ISoccerLeagueTeamService soccerLeagueTeamService;
	@Resource
	private ICached cached;
	@Resource
	private IUserService userService;
	private String listurl="/lottery/soccerleagueteam/soccerleagueteamList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueTeam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueTeam soccerLeagueTeam) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		if(soccerLeagueTeam.getState()==null){
			soccerLeagueTeam.setState(1);
		}
		if(soccerLeagueTeam.getName()!=null&&!"".equals(soccerLeagueTeam.getName())){
			List<SoccerLeagueTeam> datas=soccerLeagueTeamService.findListDataByFinder(new Finder("select * from soccer_league_team where name like :name and state = :state").setParam("name", "%"+soccerLeagueTeam.getName()+"%").setParam("state", soccerLeagueTeam.getState()),page,SoccerLeagueTeam.class,null);
			returnObject.setQueryBean(soccerLeagueTeam);
			returnObject.setPage(page);
			returnObject.setData(datas);
		}else{
			 returnObject = listjson(request, model, soccerLeagueTeam);
		}
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 为APP提供数据
	 * @param request
	 * @param model
	 * @param accountsInfo
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin
	@RequestMapping(value = "/m/list/json")
	public @ResponseBody 
	ReturnDatas listjsonMobile(SoccerLeagueTeam soccerLeagueTeam,Integer pageIndex,String token,HttpServletRequest request, Model model) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String uid="";
		if(token!=null){
			uid=(String)cached.getCached(token.getBytes());
			if(uid==null){
				returnObject=ReturnDatas.getErrorReturnDatas();
				returnObject.setMessage("登录超时，请重新登录");
				returnObject.setStatusCode("0");
				return returnObject;
			}
			System.out.println("SoccerLeagueTeamController.listjsonMobile():uid="+uid);
		}
		else{
			returnObject=ReturnDatas.getErrorReturnDatas();
			returnObject.setMessage("登录超时，请重新登录");
			returnObject.setStatusCode("0");
			return returnObject;
		}
		System.out.println("SoccerLeagueTeamController.listjsonMobile():token="+token);
		
		User u = userService.queryForObject(new Finder("select * from t_user where id=:id").setParam("id", uid), User.class);
		  if(u==null){
			  returnObject=ReturnDatas.getErrorReturnDatas();
			  returnObject.setMessage("代理商信息有误，请联系管理员");
			  returnObject.setStatusCode("0");
			  return returnObject;
		  }
		  
	    if(soccerLeagueTeam.getState()==null){
			soccerLeagueTeam.setState(1);
		}
		Page page = newPage(request);
		if(pageIndex!=null&&pageIndex<1)pageIndex=1;
		page.setPageIndex(pageIndex);
		// ==执行分页查询
		List<SoccerLeagueTeam> datas=soccerLeagueTeamService.findListDataByFinder(null,page,SoccerLeagueTeam.class,soccerLeagueTeam);
		returnObject.setQueryBean(soccerLeagueTeam);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueTeam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueTeam soccerLeagueTeam) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeagueTeam> datas=soccerLeagueTeamService.findListDataByFinder(null,page,SoccerLeagueTeam.class,soccerLeagueTeam);
			returnObject.setQueryBean(soccerLeagueTeam);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueTeam soccerLeagueTeam) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueTeamService.findDataExportExcel(null,listurl, page,SoccerLeagueTeam.class,soccerLeagueTeam);
		String fileName="soccerLeagueTeam"+GlobalStatic.excelext;
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
		return "/lottery/soccerleagueteam/soccerleagueteamLook";
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
		  SoccerLeagueTeam soccerLeagueTeam = soccerLeagueTeamService.findSoccerLeagueTeamById(id);
		   returnObject.setData(soccerLeagueTeam);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeagueTeam soccerLeagueTeam,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//启用，禁用
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				soccerLeagueTeamService.update(new Finder("update soccer_league_team set state=:state where id=:id").setParam("state",state).setParam("id", id));
				
			}else{
				//soccerLeagueTeamService.saveorupdate(soccerLeagueTeam);
				if(soccerLeagueTeam.getId()!=null){
					soccerLeagueTeamService.update(soccerLeagueTeam, true);
				}else{
					soccerLeagueTeamService.save(soccerLeagueTeam);
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
			return "/lottery/soccerleagueteam/soccerleagueteamCru";
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
				soccerLeagueTeamService.deleteById(id,SoccerLeagueTeam.class);
			 	//soccerLeagueTeamService.update(new Finder("update soccer_league_team set state=:state where id=:id").setParam("state",3).setParam("id", id));
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
			soccerLeagueTeamService.deleteByIds(ids,SoccerLeagueTeam.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
