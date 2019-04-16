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
import org.springrain.lottery.entity.SoccerLeagueGoalodds;
import org.springrain.lottery.service.ISoccerAdjustoddsService;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueGoaloddsService;
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
 * @version  2017-08-23 15:18:40
 * @see org.springrain.lottery.web.SoccerLeagueGoalodds
 */
@Controller
@RequestMapping(value="/soccerleaguegoalodds")
public class SoccerLeagueGoaloddsController  extends BaseController {
	@Resource
	private ISoccerLeagueGoaloddsService soccerLeagueGoaloddsService;
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
	@Resource
	private ICached cached;
	@Resource
	private IUserService userService;
	@Resource
	private ISoccerAdjustoddsService soccerAdjustoddsService;
	private String listurl="/lottery/soccerleaguegoalodds/soccerleaguegoaloddsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueGoalodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueGoalodds soccerLeagueGoalodds) 
			throws Exception {
		//soccerLeagueGoaloddsService.update(new Finder("update soccer_league_goalodds set state=:state where ball0=1 and ball1=1 and ball2=1 and ball3=1").setParam("state",1));
		if(soccerLeagueGoalodds.getState()==null){
			soccerLeagueGoalodds.setState(1);
		}
		ReturnDatas returnObject = listjson(request, model, soccerLeagueGoalodds);
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
	ReturnDatas listjsonMobile(SoccerLeagueGoalodds soccerLeagueGoalodds,Integer pageIndex,String token,HttpServletRequest request, Model model) 
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
			System.out.println("SoccerLeagueGoaloddsController.listjsonMobile():uid="+uid);
		}
		else{
			returnObject=ReturnDatas.getErrorReturnDatas();
			returnObject.setMessage("登录超时，请重新登录");
			returnObject.setStatusCode("0");
			return returnObject;
		}
		System.out.println("SoccerLeagueGoaloddsController.listjsonMobile():token="+token);
		
		User u = userService.queryForObject(new Finder("select * from t_user where id=:id").setParam("id", uid), User.class);
		  if(u==null){
			  returnObject=ReturnDatas.getErrorReturnDatas();
			  returnObject.setMessage("代理商信息有误，请联系管理员");
			  returnObject.setStatusCode("0");
			  return returnObject;
		  }
		  
		if(soccerLeagueGoalodds.getState()==null){
			soccerLeagueGoalodds.setState(1);
			}
		Page page = newPage(request);
		if(pageIndex!=null&&pageIndex<1)pageIndex=1;
		page.setPageIndex(pageIndex);
		// ==执行分页查询
		List<SoccerLeagueGoalodds> datas=soccerLeagueGoaloddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname from soccer_league_goalodds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state ").setParam("state", soccerLeagueGoalodds.getState()),page,SoccerLeagueGoalodds.class,null);
		returnObject.setQueryBean(soccerLeagueGoalodds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueGoalodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueGoalodds soccerLeagueGoalodds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeagueGoalodds> datas=soccerLeagueGoaloddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname from soccer_league_goalodds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state ").setParam("state", soccerLeagueGoalodds.getState()),page,SoccerLeagueGoalodds.class,null);
			returnObject.setQueryBean(soccerLeagueGoalodds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueGoalodds soccerLeagueGoalodds) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueGoaloddsService.findDataExportExcel(null,listurl, page,SoccerLeagueGoalodds.class,soccerLeagueGoalodds);
		String fileName="soccerLeagueGoalodds"+GlobalStatic.excelext;
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
		return "/lottery/soccerleaguegoalodds/soccerleaguegoaloddsLook";
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
		  SoccerLeagueGoalodds soccerLeagueGoalodds = soccerLeagueGoaloddsService.findSoccerLeagueGoaloddsById(id);
		   returnObject.setData(soccerLeagueGoalodds);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeagueGoalodds soccerLeagueGoalodds,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				SoccerLeagueGoalodds soccerLeagueGoalodds2 = soccerLeagueGoaloddsService.findSoccerLeagueGoaloddsById(id);
				 SoccerLeagueArrange SoccerLeagueArrange2 = soccerLeagueArrangeService.queryForObject(new Finder("select * from soccer_league_arrange where  mid = :mid").setParam("mid", soccerLeagueGoalodds2.getMid()), SoccerLeagueArrange.class);
					if(SoccerLeagueArrange2!=null){
						if(3==SoccerLeagueArrange2.getState()&&"1".equals(state)){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该赔率所属赛次已禁用,不可启用该赔率");
							return returnObject;
						}
					}
				SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", soccerLeagueGoalodds2.getMid()), SoccerAdjustodds.class);
				soccerLeagueGoalodds2.setState(Integer.valueOf(state));	
				soccerLeagueGoaloddsService.update(new Finder("update soccer_league_goalodds set state=:state where id=:id").setParam("state",state).setParam("id", id));
				soccerLeagueGoalodds2.setBall0(soccerLeagueGoalodds2.getBall0()+soccerAdjustodds.getBall0().doubleValue());
				soccerLeagueGoalodds2.setBall1(soccerLeagueGoalodds2.getBall1()+soccerAdjustodds.getBall1().doubleValue());
				soccerLeagueGoalodds2.setBall2(soccerLeagueGoalodds2.getBall2()+soccerAdjustodds.getBall2().doubleValue());
				soccerLeagueGoalodds2.setBall3(soccerLeagueGoalodds2.getBall3()+soccerAdjustodds.getBall3().doubleValue());
				soccerLeagueGoalodds2.setBall4(soccerLeagueGoalodds2.getBall4()+soccerAdjustodds.getBall4().doubleValue());
				soccerLeagueGoalodds2.setBall5(soccerLeagueGoalodds2.getBall5()+soccerAdjustodds.getBall5().doubleValue());
				soccerLeagueGoalodds2.setBall6(soccerLeagueGoalodds2.getBall6()+soccerAdjustodds.getBall6().doubleValue());
				soccerLeagueGoalodds2.setBall7(soccerLeagueGoalodds2.getBall7()+soccerAdjustodds.getBall7().doubleValue());
				cached.updateCached(("jqs_"+soccerLeagueGoalodds2.getMid()).getBytes(), JSON.toJSONString(soccerLeagueGoalodds2).getBytes(), null);
			}else{
				//soccerLeagueGoaloddsService.saveorupdate(soccerLeagueGoalodds);
				if(soccerLeagueGoalodds.getId()!=null){
					soccerLeagueGoaloddsService.update(soccerLeagueGoalodds, true);
				}else{
					soccerLeagueGoaloddsService.save(soccerLeagueGoalodds);
				}
				cached.updateCached(("jqs_"+soccerLeagueGoalodds.getMid()).getBytes(), JSON.toJSONString(soccerLeagueGoalodds).getBytes(), null);
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
		return "/lottery/soccerleaguegoalodds/soccerleaguegoaloddsCru";
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
				soccerLeagueGoaloddsService.deleteById(id,SoccerLeagueGoalodds.class);
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
			soccerLeagueGoaloddsService.deleteByIds(ids,SoccerLeagueGoalodds.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
