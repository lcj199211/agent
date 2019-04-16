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

import org.springrain.lottery.entity.SoccerAdjustodds;
import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.service.ISoccerAdjustoddsService;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;
import org.springrain.frame.cached.ICached;
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
 * @version  2017-08-17 17:47:41
 * @see org.springrain.lottery.web.SoccerLeagueOdds
 */
@Controller
@RequestMapping(value="/soccerleagueodds")
public class SoccerLeagueOddsController  extends BaseController {
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
	@Resource
	private ICached cached;
	@Resource
	private IUserService userService;
	@Resource
	private ISoccerAdjustoddsService soccerAdjustoddsService;
	private String listurl="/lottery/soccerleagueodds/soccerleagueoddsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueOdds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueOdds soccerLeagueOdds) 
			throws Exception {
		if(soccerLeagueOdds.getState()==null){
			soccerLeagueOdds.setState(1);
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		if(StringUtils.isBlank(starttime)){
			starttime="0000-01-01";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-01-01";
		}
		java.sql.Date startDate = java.sql.Date.valueOf(starttime);
		java.sql.Date endDate=java.sql.Date.valueOf(endtime);
		List<SoccerLeagueOdds> datas = null;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			returnObject = listjson(request, model, soccerLeagueOdds);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}else{
			//datas = soccerLeagueOddsService.findListDataByFinder(new Finder("select * from soccer_league_odds where substr(date,1,10)>=:starttime and substr(date,1,10)<=:endtime").setParam("starttime",startDate).setParam("endtime", endDate),page,SoccerLeagueOdds.class,soccerLeagueOdds);
			datas =soccerLeagueOddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname from soccer_league_odds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state and substr(a.date,1,10)>=:starttime and substr(a.date,1,10)<=:endtime ").setParam("state", soccerLeagueOdds.getState()).setParam("starttime",startDate).setParam("endtime", endDate),page,SoccerLeagueOdds.class,null);
		}
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}
		returnObject.setQueryBean(soccerLeagueOdds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("startTime", startDate);
		model.addAttribute("endTime", endDate);
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
	ReturnDatas listjsonMobile(SoccerLeagueOdds soccerLeagueOdds,Integer pageIndex,String token,HttpServletRequest request, Model model) 
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
			System.out.println("SoccerLeagueOddsController.listjsonMobile():uid="+uid);
		}
		else{
			returnObject=ReturnDatas.getErrorReturnDatas();
			returnObject.setMessage("登录超时，请重新登录");
			returnObject.setStatusCode("0");
			return returnObject;
		}
		System.out.println("SoccerLeagueOddsController.listjsonMobile():token="+token);
		
		User u = userService.queryForObject(new Finder("select * from t_user where id=:id").setParam("id", uid), User.class);
		  if(u==null){
			  returnObject=ReturnDatas.getErrorReturnDatas();
			  returnObject.setMessage("代理商信息有误，请联系管理员");
			  returnObject.setStatusCode("0");
			  return returnObject;
		  }
		  
		if(soccerLeagueOdds.getState()==null){
			soccerLeagueOdds.setState(1);
		}
		Page page = newPage(request);
		if(pageIndex!=null&&pageIndex<1)pageIndex=1;
		page.setPageIndex(pageIndex);
		// ==执行分页查询
		List<SoccerLeagueOdds> datas=soccerLeagueOddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname from soccer_league_odds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state ").setParam("state", soccerLeagueOdds.getState()),page,SoccerLeagueOdds.class,null);
		returnObject.setQueryBean(soccerLeagueOdds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueOdds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueOdds soccerLeagueOdds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		//List<SoccerLeagueOdds> datas=soccerLeagueOddsService.findListDataByFinder(null,page,SoccerLeagueOdds.class,soccerLeagueOdds);
		List<SoccerLeagueOdds> datas=soccerLeagueOddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname from soccer_league_odds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state ").setParam("state", soccerLeagueOdds.getState()),page,SoccerLeagueOdds.class,null);
		returnObject.setQueryBean(soccerLeagueOdds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueOdds soccerLeagueOdds) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueOddsService.findDataExportExcel(null,listurl, page,SoccerLeagueOdds.class,soccerLeagueOdds);
		String fileName="soccerLeagueOdds"+GlobalStatic.excelext;
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
		return "/lottery/soccerleagueodds/soccerleagueoddsLook";
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
		  SoccerLeagueOdds soccerLeagueOdds = soccerLeagueOddsService.findSoccerLeagueOddsById(id);
		   returnObject.setData(soccerLeagueOdds);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeagueOdds soccerLeagueOdds,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				 SoccerLeagueOdds soccerLeagueOdds2 = soccerLeagueOddsService.findSoccerLeagueOddsById(id);
				 SoccerLeagueArrange SoccerLeagueArrange2 = soccerLeagueArrangeService.queryForObject(new Finder("select * from soccer_league_arrange where  mid = :mid").setParam("mid", soccerLeagueOdds2.getMid()), SoccerLeagueArrange.class);
					if(SoccerLeagueArrange2!=null){
						if(3==SoccerLeagueArrange2.getState()&&"1".equals(state)){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该赔率所属赛次已禁用,不可启用该赔率");
							return returnObject;
						}
					}
				soccerLeagueOdds2.setState(Integer.valueOf(state));
				soccerLeagueOddsService.update(new Finder("update soccer_league_odds set state=:state where id=:id").setParam("state",state).setParam("id", id));
				
				SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", soccerLeagueOdds2.getMid()), SoccerAdjustodds.class);
				
				if(soccerLeagueOdds2.getType()==0){
					soccerLeagueOdds2.setWin(soccerLeagueOdds2.getWin()+soccerAdjustodds.getWin().doubleValue());
					soccerLeagueOdds2.setFlat(soccerLeagueOdds2.getFlat()+soccerAdjustodds.getFlat().doubleValue());
					soccerLeagueOdds2.setLose(soccerLeagueOdds2.getLose()+soccerAdjustodds.getLose().doubleValue());
					cached.updateCached(("sfp_"+soccerLeagueOdds2.getMid()).getBytes(), JSON.toJSONString(soccerLeagueOdds2).getBytes(), null);
				}else{
					soccerLeagueOdds2.setWin(soccerLeagueOdds2.getWin()+soccerAdjustodds.getRqwin().doubleValue());
					soccerLeagueOdds2.setFlat(soccerLeagueOdds2.getFlat()+soccerAdjustodds.getRqflat().doubleValue());
					soccerLeagueOdds2.setLose(soccerLeagueOdds2.getLose()+soccerAdjustodds.getRqlose().doubleValue());
					cached.updateCached(("rqsfp_"+soccerLeagueOdds2.getMid()).getBytes(), JSON.toJSONString(soccerLeagueOdds2).getBytes(), null);
				}
			}else{
				//soccerLeagueOddsService.saveorupdate(soccerLeagueOdds);
				if(soccerLeagueOdds.getId()!=null){
					soccerLeagueOddsService.update(soccerLeagueOdds, true);
				}else{
					soccerLeagueOddsService.save(soccerLeagueOdds);
				}
				
				if(soccerLeagueOdds.getType()==0){
					cached.updateCached(("sfp_"+soccerLeagueOdds.getMid()).getBytes(), JSON.toJSONString(soccerLeagueOdds).getBytes(), null);
				}else{
					cached.updateCached(("rqsfp_"+soccerLeagueOdds.getMid()).getBytes(), JSON.toJSONString(soccerLeagueOdds).getBytes(), null);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
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
		return "/lottery/soccerleagueodds/soccerleagueoddsCru";
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
				soccerLeagueOddsService.deleteById(id,SoccerLeagueOdds.class);
			 	//soccerLeagueOddsService.update(new Finder("update soccer_league_odds set state=:state where id=:id").setParam("state",3).setParam("id", id));
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
			soccerLeagueOddsService.deleteByIds(ids,SoccerLeagueOdds.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
