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
import org.springrain.frame.cached.ICached;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.SoccerAdjustodds;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueGoalodds;
import org.springrain.lottery.entity.SoccerLeagueHalfallodds;
import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.lottery.entity.SoccerLeagueScoreodds;
import org.springrain.lottery.service.ISoccerAdjustoddsService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueGoaloddsService;
import org.springrain.lottery.service.ISoccerLeagueHalfalloddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerLeagueScoreoddsService;
import org.springrain.lottery.service.ISoccerLeagueService;
import org.springrain.lottery.service.ISoccerLeagueTeamService;
import org.springrain.lottery.utils.WeekOfDate;

import com.alibaba.fastjson.JSON;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-10 14:03:43
 * @see org.springrain.lottery.web.SoccerAdjustodds
 */
@Controller
@RequestMapping(value="/socceradjustodds")
public class SoccerAdjustoddsController  extends BaseController {
	@Resource
	private ISoccerAdjustoddsService soccerAdjustoddsService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerLeagueHalfalloddsService soccerLeagueHalfalloddsService;
	@Resource
	private ISoccerLeagueScoreoddsService soccerLeagueScoreoddsService;
	@Resource
	private ISoccerLeagueGoaloddsService soccerLeagueGoaloddsService;
	@Resource
	private ISoccerLeague2choose1oddsService soccerLeague2choose1oddsService;
	@Resource
	private ICached cached;
	private String listurl="/lottery/socceradjustodds/socceradjustoddsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerAdjustodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerAdjustodds soccerAdjustodds) 
			throws Exception {
		/*
		List<SoccerLeagueArrange> datas = soccerLeagueArrangeService.queryForList(new Finder("select * from soccer_league_arrange "),SoccerLeagueArrange.class);
		if(datas!=null){
			for(SoccerLeagueArrange arrange : datas){
				SoccerAdjustodds s = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid ").setParam("mid",arrange.getMid() ), SoccerAdjustodds.class);
				if(s==null){
					SoccerAdjustodds adjust = new SoccerAdjustodds();
					adjust.setMid(arrange.getMid());
					adjust.setZid(arrange.getZid());
					soccerAdjustoddsService.save(adjust);
				}
			}
		}
		*/
		ReturnDatas returnObject = listjson(request, model, soccerAdjustodds);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerAdjustodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerAdjustodds soccerAdjustodds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerAdjustodds> datas=soccerAdjustoddsService.findListDataByFinder(null,page,SoccerAdjustodds.class,soccerAdjustodds);
			returnObject.setQueryBean(soccerAdjustodds);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerAdjustodds soccerAdjustodds) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerAdjustoddsService.findDataExportExcel(null,listurl, page,SoccerAdjustodds.class,soccerAdjustodds);
		String fileName="soccerAdjustodds"+GlobalStatic.excelext;
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
		return "/lottery/socceradjustodds/socceradjustoddsLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  String  strmId=request.getParameter("mid");
		  if(StringUtils.isNotBlank(strmId)){
			  SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select a.*,b.num,b.matchname,b.starttime,b.leftteamname,b.rightteamname,b.endtime  from soccer_adjustodds a left join soccer_league_arrange b on a.mid = b.mid where a.mid=:mid ").setParam("mid", strmId), SoccerAdjustodds.class);
			  soccerAdjustodds.setNum(WeekOfDate.getWeekOfDate(soccerAdjustodds.getEndtime())+soccerAdjustodds.getNum());
			  SoccerLeagueOdds spfodd = soccerLeagueOddsService.queryForObject(new Finder("select a.*,b.name as playmethod from soccer_league_odds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id  where  a.mid = :mid and a.type=0" ).setParam("mid", strmId), SoccerLeagueOdds.class);
			  SoccerLeagueOdds rqspfodd = soccerLeagueOddsService.queryForObject(new Finder("select a.*,b.name as playmethod from soccer_league_odds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id  where  a.mid = :mid and a.type=1" ).setParam("mid", strmId), SoccerLeagueOdds.class);
			  SoccerLeagueScoreodds bfodd = soccerLeagueScoreoddsService.queryForObject(new Finder("select a.*,b.name as playmethod from soccer_league_scoreodds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", strmId), SoccerLeagueScoreodds.class);
			  SoccerLeagueHalfallodds bqcodd = soccerLeagueHalfalloddsService.queryForObject(new Finder("select a.*,b.name as playmethod from soccer_league_halfallodds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", strmId), SoccerLeagueHalfallodds.class);
			  SoccerLeagueGoalodds jqsodd = soccerLeagueGoaloddsService.queryForObject(new Finder("select a.*,b.name as playmethod from soccer_league_goalodds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", strmId), SoccerLeagueGoalodds.class);
			  SoccerLeague2choose1odds  chooseodd = soccerLeague2choose1oddsService.queryForObject(new Finder("select a.*,b.name as playmethod from soccer_league_2choose1odds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", strmId), SoccerLeague2choose1odds.class);
			  model.addAttribute("spfodd", spfodd);
			  model.addAttribute("rqspfodd", rqspfodd);
			  model.addAttribute("bfodd", bfodd);
			  model.addAttribute("bqcodd", bqcodd);
			  model.addAttribute("jqsodd", jqsodd);
			  model.addAttribute("chooseodd", chooseodd);
			  returnObject.setData(soccerAdjustodds);
			  return returnObject;
		  }
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.findSoccerAdjustoddsById(id);
		   returnObject.setData(soccerAdjustodds);
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
	public ReturnDatas saveorupdate(Model model,SoccerAdjustodds soccerAdjustodds,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//胜平负
			SoccerLeagueOdds sfpOdds = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_odds where  mid = :mid and type = 0").setParam("mid", soccerAdjustodds.getMid()), SoccerLeagueOdds.class);
			sfpOdds.setWin(sfpOdds.getWin()+soccerAdjustodds.getWin().doubleValue());
			sfpOdds.setFlat(sfpOdds.getFlat()+soccerAdjustodds.getFlat().doubleValue());
			sfpOdds.setLose(sfpOdds.getLose()+soccerAdjustodds.getLose().doubleValue());
			cached.updateCached(("sfp_"+sfpOdds.getMid()).getBytes(), JSON.toJSONString(sfpOdds).getBytes(), null);
			//让球胜平负
			SoccerLeagueOdds rqsfpOdds = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_odds where  mid = :mid and type = 1").setParam("mid", soccerAdjustodds.getMid()), SoccerLeagueOdds.class);
			rqsfpOdds.setWin(rqsfpOdds.getWin()+soccerAdjustodds.getRqwin().doubleValue());
			rqsfpOdds.setFlat(rqsfpOdds.getFlat()+soccerAdjustodds.getRqflat().doubleValue());
			rqsfpOdds.setLose(rqsfpOdds.getLose()+soccerAdjustodds.getRqlose().doubleValue());
			cached.updateCached(("rqsfp_"+rqsfpOdds.getMid()).getBytes(), JSON.toJSONString(rqsfpOdds).getBytes(), null);
			//比分
			SoccerLeagueScoreodds scoreOdds = soccerLeagueScoreoddsService.queryForObject(new Finder("select * from soccer_league_scoreodds where  mid = :mid ").setParam("mid", soccerAdjustodds.getMid()), SoccerLeagueScoreodds.class);
			scoreOdds.setWin3A(scoreOdds.getWin3A()+soccerAdjustodds.getWin3A().doubleValue());
			scoreOdds.setWin10(scoreOdds.getWin10()+soccerAdjustodds.getWin10().doubleValue());
			scoreOdds.setWin20(scoreOdds.getWin20()+soccerAdjustodds.getWin20().doubleValue());
			scoreOdds.setWin21(scoreOdds.getWin21()+soccerAdjustodds.getWin21().doubleValue());
			scoreOdds.setWin30(scoreOdds.getWin30()+soccerAdjustodds.getWin30().doubleValue());
			scoreOdds.setWin31(scoreOdds.getWin31()+soccerAdjustodds.getWin31().doubleValue());
			scoreOdds.setWin32(scoreOdds.getWin32()+soccerAdjustodds.getWin32().doubleValue());
			scoreOdds.setWin40(scoreOdds.getWin40()+soccerAdjustodds.getWin40().doubleValue());
			scoreOdds.setWin41(scoreOdds.getWin41()+soccerAdjustodds.getWin41().doubleValue());
			scoreOdds.setWin42(scoreOdds.getWin42()+soccerAdjustodds.getWin42().doubleValue());
			scoreOdds.setWin50(scoreOdds.getWin50()+soccerAdjustodds.getWin50().doubleValue());
			scoreOdds.setWin51(scoreOdds.getWin51()+soccerAdjustodds.getWin51().doubleValue());
			scoreOdds.setWin52(scoreOdds.getWin52()+soccerAdjustodds.getWin52().doubleValue());
			scoreOdds.setFlat1A(scoreOdds.getFlat1A()+soccerAdjustodds.getFlat1A().doubleValue());
			scoreOdds.setFlat00(scoreOdds.getFlat00()+soccerAdjustodds.getFlat00().doubleValue());
			scoreOdds.setFlat11(scoreOdds.getFlat11()+soccerAdjustodds.getFlat11().doubleValue());
			scoreOdds.setFlat22(scoreOdds.getFlat22()+soccerAdjustodds.getFlat22().doubleValue());
			scoreOdds.setFlat33(scoreOdds.getFlat33()+soccerAdjustodds.getFlat33().doubleValue());
			scoreOdds.setLose0A(scoreOdds.getLose0A()+soccerAdjustodds.getLose0A().doubleValue());
			scoreOdds.setLose01(scoreOdds.getLose01()+soccerAdjustodds.getLose01().doubleValue());
			scoreOdds.setLose02(scoreOdds.getLose02()+soccerAdjustodds.getLose02().doubleValue());
			scoreOdds.setLose12(scoreOdds.getLose12()+soccerAdjustodds.getLose12().doubleValue());
			scoreOdds.setLose03(scoreOdds.getLose03()+soccerAdjustodds.getLose03().doubleValue());
			scoreOdds.setLose13(scoreOdds.getLose13()+soccerAdjustodds.getLose13().doubleValue());
			scoreOdds.setLose23(scoreOdds.getLose23()+soccerAdjustodds.getLose23().doubleValue());
			scoreOdds.setLose04(scoreOdds.getLose04()+soccerAdjustodds.getLose04().doubleValue());
			scoreOdds.setLose14(scoreOdds.getLose14()+soccerAdjustodds.getLose14().doubleValue());
			scoreOdds.setLose24(scoreOdds.getLose24()+soccerAdjustodds.getLose24().doubleValue());
			scoreOdds.setLose05(scoreOdds.getLose05()+soccerAdjustodds.getLose05().doubleValue());
			scoreOdds.setLose15(scoreOdds.getLose15()+soccerAdjustodds.getLose15().doubleValue());
			scoreOdds.setLose25(scoreOdds.getLose25()+soccerAdjustodds.getLose25().doubleValue());
			cached.updateCached(("bf_"+scoreOdds.getMid()).getBytes(), JSON.toJSONString(scoreOdds).getBytes(), null);
			
			//半全场
			SoccerLeagueHalfallodds halfallOdds = soccerLeagueHalfalloddsService.queryForObject(new Finder("select * from soccer_league_halfallodds where  mid = :mid ").setParam("mid", soccerAdjustodds.getMid()), SoccerLeagueHalfallodds.class);
			halfallOdds.setSp33(halfallOdds.getSp33()+soccerAdjustodds.getSp33().doubleValue());
			halfallOdds.setSp31(halfallOdds.getSp31()+soccerAdjustodds.getSp31().doubleValue());
			halfallOdds.setSp30(halfallOdds.getSp30()+soccerAdjustodds.getSp30().doubleValue());
			halfallOdds.setSp13(halfallOdds.getSp13()+soccerAdjustodds.getSp13().doubleValue());
			halfallOdds.setSp11(halfallOdds.getSp11()+soccerAdjustodds.getSp11().doubleValue());
			halfallOdds.setSp10(halfallOdds.getSp10()+soccerAdjustodds.getSp10().doubleValue());
			halfallOdds.setSp03(halfallOdds.getSp03()+soccerAdjustodds.getSp03().doubleValue());
			halfallOdds.setSp01(halfallOdds.getSp01()+soccerAdjustodds.getSp01().doubleValue());
			halfallOdds.setSp00(halfallOdds.getSp00()+soccerAdjustodds.getSp00().doubleValue());
			cached.updateCached(("bqc_"+halfallOdds.getMid()).getBytes(), JSON.toJSONString(halfallOdds).getBytes(), null);
			//进球数
			SoccerLeagueGoalodds goalOdds = soccerLeagueGoaloddsService.queryForObject(new Finder("select * from soccer_league_goalodds where  mid = :mid ").setParam("mid", soccerAdjustodds.getMid()), SoccerLeagueGoalodds.class);
			goalOdds.setBall0(goalOdds.getBall0()+soccerAdjustodds.getBall0().doubleValue());
			goalOdds.setBall1(goalOdds.getBall1()+soccerAdjustodds.getBall1().doubleValue());
			goalOdds.setBall2(goalOdds.getBall2()+soccerAdjustodds.getBall2().doubleValue());
			goalOdds.setBall3(goalOdds.getBall3()+soccerAdjustodds.getBall3().doubleValue());
			goalOdds.setBall4(goalOdds.getBall4()+soccerAdjustodds.getBall4().doubleValue());
			goalOdds.setBall5(goalOdds.getBall5()+soccerAdjustodds.getBall5().doubleValue());
			goalOdds.setBall6(goalOdds.getBall6()+soccerAdjustodds.getBall6().doubleValue());
			goalOdds.setBall7(goalOdds.getBall7()+soccerAdjustodds.getBall7().doubleValue());
			cached.updateCached(("jqs_"+goalOdds.getMid()).getBytes(), JSON.toJSONString(goalOdds).getBytes(), null);
			//2选1
			SoccerLeague2choose1odds chooseOdds = soccerLeague2choose1oddsService.queryForObject(new Finder("select * from soccer_league_2choose1odds where  mid = :mid ").setParam("mid", soccerAdjustodds.getMid()), SoccerLeague2choose1odds.class);
			chooseOdds.setLeft_odds(chooseOdds.getLeft_odds()+soccerAdjustodds.getLeft_odds().doubleValue());
			chooseOdds.setRight_odds(chooseOdds.getRight_odds()+soccerAdjustodds.getRight_odds().doubleValue());
			cached.updateCached(("2x1_"+chooseOdds.getMid()).getBytes(), JSON.toJSONString(chooseOdds).getBytes(), null);
		
			soccerAdjustoddsService.update(soccerAdjustodds,true);
			
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
		return "/lottery/socceradjustodds/socceradjustoddsCru";
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
				soccerAdjustoddsService.deleteById(id,SoccerAdjustodds.class);
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
			soccerAdjustoddsService.deleteByIds(ids,SoccerAdjustodds.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
