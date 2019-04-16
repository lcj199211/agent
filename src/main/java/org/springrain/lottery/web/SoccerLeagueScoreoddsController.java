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
import org.springrain.lottery.entity.SoccerLeagueScoreodds;
import org.springrain.lottery.service.ISoccerAdjustoddsService;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueScoreoddsService;
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
 * @version  2017-08-23 09:03:03
 * @see org.springrain.lottery.web.SoccerLeagueScoreodds
 */
@Controller
@RequestMapping(value="/soccerleaguescoreodds")
public class SoccerLeagueScoreoddsController  extends BaseController {
	@Resource
	private ISoccerLeagueScoreoddsService soccerLeagueScoreoddsService;
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
	@Resource
	private ICached cached;
	@Resource
	private IUserService userService;
	@Resource
	private ISoccerAdjustoddsService soccerAdjustoddsService;
	private String listurl="/lottery/soccerleaguescoreodds/soccerleaguescoreoddsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueScoreodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueScoreodds soccerLeagueScoreodds) 
			throws Exception {
		//soccerLeagueScoreoddsService.update(new Finder("update soccer_league_scoreodds set state=:state where win21=1 and win3A=1 and flat1A=1 and lose0A=1 and lose23=1").setParam("state",1));
		if(soccerLeagueScoreodds.getState()==null){
			soccerLeagueScoreodds.setState(1);
		}
		ReturnDatas returnObject = listjson(request, model, soccerLeagueScoreodds);
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
	ReturnDatas listjsonMobile(SoccerLeagueScoreodds soccerLeagueScoreodds,Integer pageIndex,String token,HttpServletRequest request, Model model) 
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
			System.out.println("SoccerLeagueScoreoddsController.listjsonMobile():uid="+uid);
		}
		else{
			returnObject=ReturnDatas.getErrorReturnDatas();
			returnObject.setMessage("登录超时，请重新登录");
			returnObject.setStatusCode("0");
			return returnObject;
		}
		System.out.println("SoccerLeagueScoreoddsController.listjsonMobile():token="+token);
		
		User u = userService.queryForObject(new Finder("select * from t_user where id=:id").setParam("id", uid), User.class);
		  if(u==null){
			  returnObject=ReturnDatas.getErrorReturnDatas();
			  returnObject.setMessage("代理商信息有误，请联系管理员");
			  returnObject.setStatusCode("0");
			  return returnObject;
		  }
		  
		  if(soccerLeagueScoreodds.getState()==null){
				soccerLeagueScoreodds.setState(1);
			}
		Page page = newPage(request);
		if(pageIndex!=null&&pageIndex<1)pageIndex=1;
		page.setPageIndex(pageIndex);
		// ==执行分页查询
		List<SoccerLeagueScoreodds> datas=soccerLeagueScoreoddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname,b.starttime from soccer_league_scoreodds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state ").setParam("state",soccerLeagueScoreodds.getState() ),page,SoccerLeagueScoreodds.class,null);
		returnObject.setQueryBean(soccerLeagueScoreodds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueScoreodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueScoreodds soccerLeagueScoreodds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		//List<SoccerLeagueScoreodds> datas=soccerLeagueScoreoddsService.findListDataByFinder(null,page,SoccerLeagueScoreodds.class,soccerLeagueScoreodds);
		List<SoccerLeagueScoreodds> datas=soccerLeagueScoreoddsService.findListDataByFinder(new Finder("select a.*,b.matchname,b.leftteamname,b.rightteamname,b.starttime from soccer_league_scoreodds a left join soccer_league_arrange b on a.mid = b.mid where a.state = :state ").setParam("state",soccerLeagueScoreodds.getState() ),page,SoccerLeagueScoreodds.class,null);
		returnObject.setQueryBean(soccerLeagueScoreodds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueScoreodds soccerLeagueScoreodds) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueScoreoddsService.findDataExportExcel(null,listurl, page,SoccerLeagueScoreodds.class,soccerLeagueScoreodds);
		String fileName="soccerLeagueScoreodds"+GlobalStatic.excelext;
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
		return "/lottery/soccerleaguescoreodds/soccerleaguescoreoddsLook";
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
		  SoccerLeagueScoreodds soccerLeagueScoreodds = soccerLeagueScoreoddsService.findSoccerLeagueScoreoddsById(id);
		   returnObject.setData(soccerLeagueScoreodds);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeagueScoreodds soccerLeagueScoreodds,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				SoccerLeagueScoreodds soccerLeagueScoreodds2 = soccerLeagueScoreoddsService.findSoccerLeagueScoreoddsById(id);
				 SoccerLeagueArrange SoccerLeagueArrange2 = soccerLeagueArrangeService.queryForObject(new Finder("select * from soccer_league_arrange where  mid = :mid").setParam("mid", soccerLeagueScoreodds2.getMid()), SoccerLeagueArrange.class);
					if(SoccerLeagueArrange2!=null){
						if(3==SoccerLeagueArrange2.getState()&&"1".equals(state)){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该赔率所属赛次已禁用,不可启用该赔率");
							return returnObject;
						}
					}
				SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", soccerLeagueScoreodds2.getMid()), SoccerAdjustodds.class);
				soccerLeagueScoreodds2.setState(Integer.valueOf(state));
				soccerLeagueScoreoddsService.update(new Finder("update soccer_league_scoreodds set state=:state where id=:id").setParam("state",state).setParam("id", id));
				soccerLeagueScoreodds2.setWin3A(soccerLeagueScoreodds2.getWin3A()+soccerAdjustodds.getWin3A().doubleValue());
				soccerLeagueScoreodds2.setWin10(soccerLeagueScoreodds2.getWin10()+soccerAdjustodds.getWin10().doubleValue());
				soccerLeagueScoreodds2.setWin20(soccerLeagueScoreodds2.getWin20()+soccerAdjustodds.getWin20().doubleValue());
				soccerLeagueScoreodds2.setWin21(soccerLeagueScoreodds2.getWin21()+soccerAdjustodds.getWin21().doubleValue());
				soccerLeagueScoreodds2.setWin30(soccerLeagueScoreodds2.getWin30()+soccerAdjustodds.getWin30().doubleValue());
				soccerLeagueScoreodds2.setWin31(soccerLeagueScoreodds2.getWin31()+soccerAdjustodds.getWin31().doubleValue());
				soccerLeagueScoreodds2.setWin32(soccerLeagueScoreodds2.getWin32()+soccerAdjustodds.getWin32().doubleValue());
				soccerLeagueScoreodds2.setWin40(soccerLeagueScoreodds2.getWin40()+soccerAdjustodds.getWin40().doubleValue());
				soccerLeagueScoreodds2.setWin41(soccerLeagueScoreodds2.getWin41()+soccerAdjustodds.getWin41().doubleValue());
				soccerLeagueScoreodds2.setWin42(soccerLeagueScoreodds2.getWin42()+soccerAdjustodds.getWin42().doubleValue());
				soccerLeagueScoreodds2.setWin50(soccerLeagueScoreodds2.getWin50()+soccerAdjustodds.getWin50().doubleValue());
				soccerLeagueScoreodds2.setWin51(soccerLeagueScoreodds2.getWin51()+soccerAdjustodds.getWin51().doubleValue());
				soccerLeagueScoreodds2.setWin52(soccerLeagueScoreodds2.getWin52()+soccerAdjustodds.getWin52().doubleValue());
				soccerLeagueScoreodds2.setFlat1A(soccerLeagueScoreodds2.getFlat1A()+soccerAdjustodds.getFlat1A().doubleValue());
				soccerLeagueScoreodds2.setFlat00(soccerLeagueScoreodds2.getFlat00()+soccerAdjustodds.getFlat00().doubleValue());
				soccerLeagueScoreodds2.setFlat11(soccerLeagueScoreodds2.getFlat11()+soccerAdjustodds.getFlat11().doubleValue());
				soccerLeagueScoreodds2.setFlat22(soccerLeagueScoreodds2.getFlat22()+soccerAdjustodds.getFlat22().doubleValue());
				soccerLeagueScoreodds2.setFlat33(soccerLeagueScoreodds2.getFlat33()+soccerAdjustodds.getFlat33().doubleValue());
				soccerLeagueScoreodds2.setLose0A(soccerLeagueScoreodds2.getLose0A()+soccerAdjustodds.getLose0A().doubleValue());
				soccerLeagueScoreodds2.setLose01(soccerLeagueScoreodds2.getLose01()+soccerAdjustodds.getLose01().doubleValue());
				soccerLeagueScoreodds2.setLose02(soccerLeagueScoreodds2.getLose02()+soccerAdjustodds.getLose02().doubleValue());
				soccerLeagueScoreodds2.setLose12(soccerLeagueScoreodds2.getLose12()+soccerAdjustodds.getLose12().doubleValue());
				soccerLeagueScoreodds2.setLose03(soccerLeagueScoreodds2.getLose03()+soccerAdjustodds.getLose03().doubleValue());
				soccerLeagueScoreodds2.setLose13(soccerLeagueScoreodds2.getLose13()+soccerAdjustodds.getLose13().doubleValue());
				soccerLeagueScoreodds2.setLose23(soccerLeagueScoreodds2.getLose23()+soccerAdjustodds.getLose23().doubleValue());
				soccerLeagueScoreodds2.setLose04(soccerLeagueScoreodds2.getLose04()+soccerAdjustodds.getLose04().doubleValue());
				soccerLeagueScoreodds2.setLose14(soccerLeagueScoreodds2.getLose14()+soccerAdjustodds.getLose14().doubleValue());
				soccerLeagueScoreodds2.setLose24(soccerLeagueScoreodds2.getLose24()+soccerAdjustodds.getLose24().doubleValue());
				soccerLeagueScoreodds2.setLose05(soccerLeagueScoreodds2.getLose05()+soccerAdjustodds.getLose05().doubleValue());
				soccerLeagueScoreodds2.setLose15(soccerLeagueScoreodds2.getLose15()+soccerAdjustodds.getLose15().doubleValue());
				soccerLeagueScoreodds2.setLose25(soccerLeagueScoreodds2.getLose25()+soccerAdjustodds.getLose25().doubleValue());
				cached.updateCached(("bf_"+soccerLeagueScoreodds2.getMid()).getBytes(), JSON.toJSONString(soccerLeagueScoreodds2).getBytes(), null);
			}else{
				//soccerLeagueScoreoddsService.saveorupdate(soccerLeagueScoreodds);
				if(soccerLeagueScoreodds.getId()!=null){
					soccerLeagueScoreoddsService.update(soccerLeagueScoreodds, true);
				}else{
					soccerLeagueScoreoddsService.save(soccerLeagueScoreodds);
				}
				cached.updateCached(("bf_"+soccerLeagueScoreodds.getMid()).getBytes(), JSON.toJSONString(soccerLeagueScoreodds).getBytes(), null);
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
		return "/lottery/soccerleaguescoreodds/soccerleaguescoreoddsCru";
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
				soccerLeagueScoreoddsService.deleteById(id,SoccerLeagueScoreodds.class);
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
			soccerLeagueScoreoddsService.deleteByIds(ids,SoccerLeagueScoreodds.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
