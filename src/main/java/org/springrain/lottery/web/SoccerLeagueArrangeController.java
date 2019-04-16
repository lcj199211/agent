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


import org.springrain.lottery.entity.SoccerAdjustodds;
import org.springrain.lottery.entity.SoccerLeague;
import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.lottery.entity.SoccerLeagueGoalodds;
import org.springrain.lottery.entity.SoccerLeagueHalfallodds;
import org.springrain.lottery.entity.SoccerLeagueOdds;

import org.springrain.lottery.entity.SoccerLeagueScoreodds;
import org.springrain.lottery.entity.SoccerLeagueTeam;
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
 * @version  2017-08-17 17:49:11
 * @see org.springrain.lottery.web.SoccerLeagueArrange
 */
@Controller
@RequestMapping(value="/soccerleaguearrange")
public class SoccerLeagueArrangeController  extends BaseController {
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
	@Resource
	private ISoccerLeagueOddsService soccerLeagueOddsService;
	@Resource
	private ISoccerLeagueTeamService soccerLeagueTeamService;
	@Resource
	private ISoccerLeagueService soccerLeagueService;
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
	@Resource
	private ISoccerAdjustoddsService soccerAdjustoddsService;
	private String listurl="/lottery/soccerleaguearrange/soccerleaguearrangeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueArrange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueArrange soccerLeagueArrange) 
			throws Exception {
		
		if("1".equals(request.getParameter("k"))){
			//赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String mid = request.getParameter("mid");
			List<SoccerLeagueOdds> datas = soccerLeagueOddsService.queryForList(new Finder("select a.*,b.name as playmethod from soccer_league_odds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id  where  a.mid = :mid" ).setParam("mid", mid), SoccerLeagueOdds.class);
			SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", mid), SoccerAdjustodds.class);
			if(datas!=null){
				for(SoccerLeagueOdds Odds : datas){
					if(Odds.getType()==0){
						//不让球
						Odds.setWin(Odds.getWin()+soccerAdjustodds.getWin().doubleValue());
						Odds.setFlat(Odds.getFlat()+soccerAdjustodds.getFlat().doubleValue());
						Odds.setLose(Odds.getLose()+soccerAdjustodds.getLose().doubleValue());
					}else if(Odds.getType()==1){
						//让球
						Odds.setWin(Odds.getWin()+soccerAdjustodds.getRqwin().doubleValue());
						Odds.setFlat(Odds.getFlat()+soccerAdjustodds.getRqflat().doubleValue());
						Odds.setLose(Odds.getLose()+soccerAdjustodds.getRqlose().doubleValue());
					}
					
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(soccerLeagueArrange);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);	
			model.addAttribute("mid",mid);
			return  "/lottery/soccerleaguearrange/soccerarrangeoddsList";
			
		}else if("2".equals(request.getParameter("k"))){
			//队伍
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String id2 = request.getParameter("id2");
			List<SoccerLeagueTeam> datas = soccerLeagueTeamService.queryForList(new Finder("select * from soccer_league_team where  id2 = :id2" ).setParam("id2", id2), SoccerLeagueTeam.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(soccerLeagueArrange);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return  "/lottery/soccerleaguearrange/soccerarrangeteamList";
			
		}else if("3".equals(request.getParameter("k"))){
			//比分赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String mid = request.getParameter("mid");
			List<SoccerLeagueScoreodds> datas = soccerLeagueScoreoddsService.queryForList(new Finder("select a.*,b.name as playmethod from soccer_league_scoreodds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", mid), SoccerLeagueScoreodds.class);
			SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", mid), SoccerAdjustodds.class);
			if(datas!=null){
				for(SoccerLeagueScoreodds Scoreodds : datas){
					Scoreodds.setWin3A(Scoreodds.getWin3A()+soccerAdjustodds.getWin3A().doubleValue());
					Scoreodds.setWin10(Scoreodds.getWin10()+soccerAdjustodds.getWin10().doubleValue());
					Scoreodds.setWin20(Scoreodds.getWin20()+soccerAdjustodds.getWin20().doubleValue());
					Scoreodds.setWin21(Scoreodds.getWin21()+soccerAdjustodds.getWin21().doubleValue());
					Scoreodds.setWin30(Scoreodds.getWin30()+soccerAdjustodds.getWin30().doubleValue());
					Scoreodds.setWin31(Scoreodds.getWin31()+soccerAdjustodds.getWin31().doubleValue());
					Scoreodds.setWin32(Scoreodds.getWin32()+soccerAdjustodds.getWin32().doubleValue());
					Scoreodds.setWin40(Scoreodds.getWin40()+soccerAdjustodds.getWin40().doubleValue());
					Scoreodds.setWin41(Scoreodds.getWin41()+soccerAdjustodds.getWin41().doubleValue());
					Scoreodds.setWin42(Scoreodds.getWin42()+soccerAdjustodds.getWin42().doubleValue());
					Scoreodds.setWin50(Scoreodds.getWin50()+soccerAdjustodds.getWin50().doubleValue());
					Scoreodds.setWin51(Scoreodds.getWin51()+soccerAdjustodds.getWin51().doubleValue());
					Scoreodds.setWin52(Scoreodds.getWin52()+soccerAdjustodds.getWin52().doubleValue());
					Scoreodds.setFlat1A(Scoreodds.getFlat1A()+soccerAdjustodds.getFlat1A().doubleValue());
					Scoreodds.setFlat00(Scoreodds.getFlat00()+soccerAdjustodds.getFlat00().doubleValue());
					Scoreodds.setFlat11(Scoreodds.getFlat11()+soccerAdjustodds.getFlat11().doubleValue());
					Scoreodds.setFlat22(Scoreodds.getFlat22()+soccerAdjustodds.getFlat22().doubleValue());
					Scoreodds.setFlat33(Scoreodds.getFlat33()+soccerAdjustodds.getFlat33().doubleValue());
					Scoreodds.setLose0A(Scoreodds.getLose0A()+soccerAdjustodds.getLose0A().doubleValue());
					Scoreodds.setLose01(Scoreodds.getLose01()+soccerAdjustodds.getLose01().doubleValue());
					Scoreodds.setLose02(Scoreodds.getLose02()+soccerAdjustodds.getLose02().doubleValue());
					Scoreodds.setLose12(Scoreodds.getLose12()+soccerAdjustodds.getLose12().doubleValue());
					Scoreodds.setLose03(Scoreodds.getLose03()+soccerAdjustodds.getLose03().doubleValue());
					Scoreodds.setLose13(Scoreodds.getLose13()+soccerAdjustodds.getLose13().doubleValue());
					Scoreodds.setLose23(Scoreodds.getLose23()+soccerAdjustodds.getLose23().doubleValue());
					Scoreodds.setLose04(Scoreodds.getLose04()+soccerAdjustodds.getLose04().doubleValue());
					Scoreodds.setLose14(Scoreodds.getLose14()+soccerAdjustodds.getLose14().doubleValue());
					Scoreodds.setLose24(Scoreodds.getLose24()+soccerAdjustodds.getLose24().doubleValue());
					Scoreodds.setLose05(Scoreodds.getLose05()+soccerAdjustodds.getLose05().doubleValue());
					Scoreodds.setLose15(Scoreodds.getLose15()+soccerAdjustodds.getLose15().doubleValue());
					Scoreodds.setLose25(Scoreodds.getLose25()+soccerAdjustodds.getLose25().doubleValue());
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerLeagueScoreodds());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("mid",mid);
			return  "/lottery/soccerleaguearrange/soccerarrangescoreoddsList";
			
		}else if("4".equals(request.getParameter("k"))){
			//半全场赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String mid = request.getParameter("mid");
			List<SoccerLeagueHalfallodds> datas = soccerLeagueHalfalloddsService.queryForList(new Finder("select a.*,b.name as playmethod from soccer_league_halfallodds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", mid), SoccerLeagueHalfallodds.class);
			SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", mid), SoccerAdjustodds.class);
			if(datas!=null){
				for(SoccerLeagueHalfallodds halfallodds : datas){
					halfallodds.setSp33(halfallodds.getSp33()+soccerAdjustodds.getSp33().doubleValue());
					halfallodds.setSp31(halfallodds.getSp31()+soccerAdjustodds.getSp31().doubleValue());
					halfallodds.setSp30(halfallodds.getSp30()+soccerAdjustodds.getSp30().doubleValue());
					halfallodds.setSp13(halfallodds.getSp13()+soccerAdjustodds.getSp13().doubleValue());
					halfallodds.setSp11(halfallodds.getSp11()+soccerAdjustodds.getSp11().doubleValue());
					halfallodds.setSp10(halfallodds.getSp10()+soccerAdjustodds.getSp10().doubleValue());
					halfallodds.setSp03(halfallodds.getSp03()+soccerAdjustodds.getSp03().doubleValue());
					halfallodds.setSp01(halfallodds.getSp01()+soccerAdjustodds.getSp01().doubleValue());
					halfallodds.setSp00(halfallodds.getSp00()+soccerAdjustodds.getSp00().doubleValue());
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerLeagueHalfallodds());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("mid",mid);
			return  "/lottery/soccerleaguearrange/soccerarrangehalfalloddsList";
			
		}else if("5".equals(request.getParameter("k"))){
			//进球数赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String mid = request.getParameter("mid");
			List<SoccerLeagueGoalodds> datas = soccerLeagueGoaloddsService.queryForList(new Finder("select a.*,b.name as playmethod from soccer_league_goalodds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", mid), SoccerLeagueGoalodds.class);
			SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", mid), SoccerAdjustodds.class);
			if(datas!=null){
				for(SoccerLeagueGoalodds goalodds : datas){
					goalodds.setBall0(goalodds.getBall0()+soccerAdjustodds.getBall0().doubleValue());
					goalodds.setBall1(goalodds.getBall1()+soccerAdjustodds.getBall1().doubleValue());
					goalodds.setBall2(goalodds.getBall2()+soccerAdjustodds.getBall2().doubleValue());
					goalodds.setBall3(goalodds.getBall3()+soccerAdjustodds.getBall3().doubleValue());
					goalodds.setBall4(goalodds.getBall4()+soccerAdjustodds.getBall4().doubleValue());
					goalodds.setBall5(goalodds.getBall5()+soccerAdjustodds.getBall5().doubleValue());
					goalodds.setBall6(goalodds.getBall6()+soccerAdjustodds.getBall6().doubleValue());
					goalodds.setBall7(goalodds.getBall7()+soccerAdjustodds.getBall7().doubleValue());
				}
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerLeagueGoalodds());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("mid",mid);
			return  "/lottery/soccerleaguearrange/soccerarrangegoaloddsList";
			
		}else if("6".equals(request.getParameter("k"))){
			//2选1赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String mid = request.getParameter("mid");
			List<SoccerLeague2choose1odds> datas = soccerLeague2choose1oddsService.queryForList(new Finder("select a.*,b.name as playmethod from soccer_league_2choose1odds a LEFT JOIN soccer_league_playmethod b on a.playmethodid = b.id where  a.mid = :mid" ).setParam("mid", mid), SoccerLeague2choose1odds.class);
			SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", mid), SoccerAdjustodds.class);
			if(datas!=null){
				for(SoccerLeague2choose1odds chooseodds : datas){
					chooseodds.setLeft_odds(chooseodds.getLeft_odds()+soccerAdjustodds.getLeft_odds().doubleValue());
					chooseodds.setRight_odds(chooseodds.getRight_odds()+soccerAdjustodds.getRight_odds().doubleValue());
				}
				
			}
			returnObject.setData(datas);
			returnObject.setQueryBean(new SoccerLeague2choose1odds());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("mid",mid);
			return  "/lottery/soccerleaguearrange/soccerarrange2choose1oddsList";
			
		}else{
			if(soccerLeagueArrange.getState()==null){
				soccerLeagueArrange.setState(1);
			}
			String matchname = null;
			if(soccerLeagueArrange.getMatchname()!=null){
				matchname = "%"+soccerLeagueArrange.getMatchname()+"%";
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
			List<SoccerLeagueArrange> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas = soccerLeagueArrangeService.findListDataByFinder(new Finder("select * from soccer_league_arrange where (:matchname is null or matchname like :matchname) and state = :state and (:ishot is null or ishot = :ishot)").setParam("matchname", matchname).setParam("state", soccerLeagueArrange.getState()).setParam("ishot", soccerLeagueArrange.getIshot()),page,SoccerLeagueArrange.class,null);
			}else{
				datas = soccerLeagueArrangeService.findListDataByFinder(new Finder("select * from soccer_league_arrange where (:matchname is null or matchname like :matchname) and state = :state and (:ishot is null or ishot = :ishot) and substr(starttime,1,10)>=:starttime and substr(starttime,1,10)<=:endtime").setParam("matchname", matchname).setParam("state", soccerLeagueArrange.getState()).setParam("ishot", soccerLeagueArrange.getIshot()).setParam("starttime",startDate).setParam("endtime", endDate),page,SoccerLeagueArrange.class,null);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				for(SoccerLeagueArrange soccerArrange : datas){
					soccerArrange.setNum(WeekOfDate.getWeekOfDate(soccerArrange.getEndtime())+soccerArrange.getNum());
				}
			}
			returnObject.setQueryBean(soccerLeagueArrange);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
		
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueArrange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueArrange soccerLeagueArrange) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeagueArrange> datas=soccerLeagueArrangeService.findListDataByFinder(null,page,SoccerLeagueArrange.class,soccerLeagueArrange);
		if(datas!=null){
			for(SoccerLeagueArrange soccerArrange : datas){
				soccerArrange.setNum(WeekOfDate.getWeekOfDate(soccerArrange.getEndtime())+soccerArrange.getNum());
			}
		}
		returnObject.setQueryBean(soccerLeagueArrange);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueArrange soccerLeagueArrange) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueArrangeService.findDataExportExcel(null,listurl, page,SoccerLeagueArrange.class,soccerLeagueArrange);
		String fileName="soccerLeagueArrange"+GlobalStatic.excelext;
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
		return "/lottery/soccerleaguearrange/soccerleaguearrangeLook";
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
		  SoccerLeagueArrange soccerLeagueArrange = soccerLeagueArrangeService.findSoccerLeagueArrangeById(id);
		   returnObject.setData(soccerLeagueArrange);
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
	public ReturnDatas saveorupdate(Model model,SoccerLeagueArrange soccerLeagueArrange,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				SoccerLeagueArrange soccerLeagueArrange2 = soccerLeagueArrangeService.findSoccerLeagueArrangeById(id);
				SoccerLeague soccerLeague2 = soccerLeagueService.queryForObject(new Finder("select * from soccer_league where  id2 = :id2").setParam("id2", soccerLeagueArrange2.getMatchid2()), SoccerLeague.class);
				if(soccerLeague2!=null){
					if(3==soccerLeague2.getState()&&"1".equals(state)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次所属联赛已禁用,不可启用该场次");
						return returnObject;
					}
				}
				soccerLeagueArrangeService.update(new Finder("update soccer_league_arrange set state=:state where id=:id").setParam("state",state).setParam("id", id));
				soccerLeagueOddsService.update(new Finder("update soccer_league_odds set state=:state where  mid = :mid").setParam("state",state).setParam("mid", soccerLeagueArrange2.getMid()));
				soccerLeagueScoreoddsService.update(new Finder("update soccer_league_scoreodds set state=:state where  mid = :mid").setParam("state",state).setParam("mid", soccerLeagueArrange2.getMid()));
				soccerLeagueHalfalloddsService.update(new Finder("update soccer_league_halfallodds set state=:state where  mid = :mid").setParam("state",state).setParam("mid", soccerLeagueArrange2.getMid()));
				soccerLeagueGoaloddsService.update(new Finder("update soccer_league_goalodds set state=:state where  mid = :mid").setParam("state",state).setParam("mid", soccerLeagueArrange2.getMid()));
				soccerLeague2choose1oddsService.update(new Finder("update soccer_league_2choose1odds set state=:state where  mid = :mid").setParam("state",state).setParam("mid", soccerLeagueArrange2.getMid()));
				
				SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select * from soccer_adjustodds where mid = :mid").setParam("mid", soccerLeagueArrange2.getMid()), SoccerAdjustodds.class);
				soccerLeagueArrange2.setState(Integer.valueOf(state));
				cached.updateCached(("arrange_"+soccerLeagueArrange2.getMid()).getBytes(), JSON.toJSONString(soccerLeagueArrange2).getBytes(), 432000L);
				//胜平负
				SoccerLeagueOdds sfpOdds = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_odds where  mid = :mid and type = 0").setParam("mid", soccerLeagueArrange2.getMid()), SoccerLeagueOdds.class);
				sfpOdds.setState(Integer.valueOf(state));
				sfpOdds.setWin(sfpOdds.getWin()+soccerAdjustodds.getWin().doubleValue());
				sfpOdds.setFlat(sfpOdds.getFlat()+soccerAdjustodds.getFlat().doubleValue());
				sfpOdds.setLose(sfpOdds.getLose()+soccerAdjustodds.getLose().doubleValue());
				cached.updateCached(("sfp_"+sfpOdds.getMid()).getBytes(), JSON.toJSONString(sfpOdds).getBytes(), null);
				//让球胜平负
				SoccerLeagueOdds rqsfpOdds = soccerLeagueOddsService.queryForObject(new Finder("select * from soccer_league_odds where  mid = :mid and type = 1").setParam("mid", soccerLeagueArrange2.getMid()), SoccerLeagueOdds.class);
				rqsfpOdds.setState(Integer.valueOf(state));
				rqsfpOdds.setWin(rqsfpOdds.getWin()+soccerAdjustodds.getRqwin().doubleValue());
				rqsfpOdds.setFlat(rqsfpOdds.getFlat()+soccerAdjustodds.getRqflat().doubleValue());
				rqsfpOdds.setLose(rqsfpOdds.getLose()+soccerAdjustodds.getRqlose().doubleValue());
				cached.updateCached(("rqsfp_"+rqsfpOdds.getMid()).getBytes(), JSON.toJSONString(rqsfpOdds).getBytes(), null);
				//比分
				SoccerLeagueScoreodds scoreOdds = soccerLeagueScoreoddsService.queryForObject(new Finder("select * from soccer_league_scoreodds where  mid = :mid ").setParam("mid", soccerLeagueArrange2.getMid()), SoccerLeagueScoreodds.class);
				scoreOdds.setState(Integer.valueOf(state));
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
				SoccerLeagueHalfallodds halfallOdds = soccerLeagueHalfalloddsService.queryForObject(new Finder("select * from soccer_league_halfallodds where  mid = :mid ").setParam("mid", soccerLeagueArrange2.getMid()), SoccerLeagueHalfallodds.class);
				halfallOdds.setState(Integer.valueOf(state));
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
				SoccerLeagueGoalodds goalOdds = soccerLeagueGoaloddsService.queryForObject(new Finder("select * from soccer_league_goalodds where  mid = :mid ").setParam("mid", soccerLeagueArrange2.getMid()), SoccerLeagueGoalodds.class);
				goalOdds.setState(Integer.valueOf(state));
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
				SoccerLeague2choose1odds chooseOdds = soccerLeague2choose1oddsService.queryForObject(new Finder("select * from soccer_league_2choose1odds where  mid = :mid ").setParam("mid", soccerLeagueArrange2.getMid()), SoccerLeague2choose1odds.class);
				chooseOdds.setState(Integer.valueOf(state));
				chooseOdds.setLeft_odds(chooseOdds.getLeft_odds()+soccerAdjustodds.getLeft_odds().doubleValue());
				chooseOdds.setRight_odds(chooseOdds.getRight_odds()+soccerAdjustodds.getRight_odds().doubleValue());
				cached.updateCached(("2x1_"+chooseOdds.getMid()).getBytes(), JSON.toJSONString(chooseOdds).getBytes(), null);
			}else{
				if(soccerLeagueArrange.getId()!=null){
					soccerLeagueArrangeService.update(soccerLeagueArrange, true);
				}else{
					soccerLeagueArrangeService.save(soccerLeagueArrange);
				}
				cached.updateCached(("arrange_"+soccerLeagueArrange.getMid()).getBytes(), JSON.toJSONString(soccerLeagueArrange).getBytes(), 432000L);
				//soccerLeagueArrangeService.saveorupdate(soccerLeagueArrange);
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
		return "/lottery/soccerleaguearrange/soccerleaguearrangeCru";
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
				soccerLeagueArrangeService.deleteById(id,SoccerLeagueArrange.class);
		  		//soccerLeagueArrangeService.update(new Finder("update soccer_league_arrange set state=:state where id=:id").setParam("state",3).setParam("id", id));
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
			soccerLeagueArrangeService.deleteByIds(ids,SoccerLeagueArrange.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
