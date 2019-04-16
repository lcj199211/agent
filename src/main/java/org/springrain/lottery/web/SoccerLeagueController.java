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
import org.springrain.lottery.service.ISoccerAdjustoddsService;
import org.springrain.lottery.service.ISoccerLeague2choose1oddsService;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLeagueGoaloddsService;
import org.springrain.lottery.service.ISoccerLeagueHalfalloddsService;
import org.springrain.lottery.service.ISoccerLeagueOddsService;
import org.springrain.lottery.service.ISoccerLeagueScoreoddsService;
import org.springrain.lottery.service.ISoccerLeagueService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;

import com.alibaba.fastjson.JSON;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 16:51:27
 * @see org.springrain.lottery.web.SoccerLeague
 */
@Controller
@RequestMapping(value="/soccerleague")
public class SoccerLeagueController  extends BaseController {
	@Resource
	private ISoccerLeagueService soccerLeagueService;
	@Resource
	private ISoccerLeagueArrangeService soccerLeagueArrangeService;
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
	@Resource
	private ICached cached;
	@Resource
	private ISoccerAdjustoddsService soccerAdjustoddsService;
	private String listurl="/lottery/soccerleague/soccerleagueList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeague
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeague soccerLeague) 
			throws Exception {
		//场次信息
		if("1".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String matchid2 = request.getParameter("id2");
			List<SoccerLeagueArrange> datas = soccerLeagueArrangeService.queryForList(new Finder("select * from soccer_league_arrange where  matchid2 = :matchid2" ).setParam("matchid2", matchid2), SoccerLeagueArrange.class,page);
			returnObject.setData(datas);
			returnObject.setQueryBean(soccerLeague);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/soccerleague/soccerarrangeList";
		}else{
			if(soccerLeague.getState()==null){
				soccerLeague.setState(1);
			}
			String name = null;
			if(soccerLeague.getName()!=null){
				name = "%"+soccerLeague.getName()+"%";
			}
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			List<SoccerLeague> datas=soccerLeagueService.queryForList(new Finder("select * from soccer_league where (:name is null or name like :name) and state = :state and (:ishot is null or ishot = :ishot)").setParam("name", name).setParam("state", soccerLeague.getState()).setParam("ishot", soccerLeague.getIshot()), SoccerLeague.class, page);
			returnObject.setQueryBean(soccerLeague);
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
	 * @param soccerLeague
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeague soccerLeague) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeague> datas=soccerLeagueService.findListDataByFinder(null,page,SoccerLeague.class,soccerLeague);
		returnObject.setQueryBean(soccerLeague);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeague soccerLeague) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueService.findDataExportExcel(null,listurl, page,SoccerLeague.class,soccerLeague);
		String fileName="soccerLeague"+GlobalStatic.excelext;
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
		return "/lottery/soccerleague/soccerleagueLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if("1".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			List<SoccerLeague> datas = soccerLeagueService.queryForList(new Finder("select name from soccer_league"), SoccerLeague.class);
			returnObject.setData(datas);
			return returnObject;
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			  String  strId=request.getParameter("id");
			  java.lang.Integer id=null;
			  if(StringUtils.isNotBlank(strId)){
				 id= java.lang.Integer.valueOf(strId.trim());
			  SoccerLeague soccerLeague = soccerLeagueService.findSoccerLeagueById(id);
			   returnObject.setData(soccerLeague);
			}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			}
			return returnObject;
		}
		
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,SoccerLeague soccerLeague,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String  id=request.getParameter("id");
			String  state=request.getParameter("state");
			SoccerLeague soccerLeague2 = soccerLeagueService.findSoccerLeagueById(id);
			if("1".equals(request.getParameter("k"))){
				soccerLeagueService.update(new Finder("update soccer_league set state=:state where id=:id").setParam("state",state).setParam("id", id));
				soccerLeagueArrangeService.update(new Finder("update soccer_league_arrange set state=:state where matchid2 = :matchid2 and endtime > now()").setParam("state",state).setParam("matchid2", soccerLeague2.getId2()));
				soccerLeagueOddsService.update(new Finder("update soccer_league_odds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid set a.state=:state where  a.arrangeid2 = :arrangeid2 and b.endtime >NOW()").setParam("state",state).setParam("arrangeid2", soccerLeague2.getId2()));
				soccerLeagueScoreoddsService.update(new Finder("update soccer_league_scoreodds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid set a.state=:state where  a.arrangeid2 = :arrangeid2 and b.endtime >NOW()").setParam("state",state).setParam("arrangeid2", soccerLeague2.getId2()));
				soccerLeagueHalfalloddsService.update(new Finder("update soccer_league_halfallodds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid set a.state=:state where  a.arrangeid2 = :arrangeid2 and b.endtime >NOW()").setParam("state",state).setParam("arrangeid2", soccerLeague2.getId2()));
				soccerLeagueGoaloddsService.update(new Finder("update soccer_league_goalodds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid set a.state=:state where  a.arrangeid2 = :arrangeid2 and b.endtime >NOW()").setParam("state",state).setParam("arrangeid2", soccerLeague2.getId2()));
				soccerLeague2choose1oddsService.update(new Finder("update soccer_league_2choose1odds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid set a.state=:state where  a.arrangeid2 = :arrangeid2 and b.endtime >NOW()").setParam("state",state).setParam("arrangeid2", soccerLeague2.getId2()));
				
				List<SoccerLeagueArrange> arrangedatas = soccerLeagueArrangeService.queryForList(new Finder("select * from soccer_league_arrange where matchid2 = :matchid2 and endtime > now()").setParam("matchid2", soccerLeague2.getId2()), SoccerLeagueArrange.class);
				if(arrangedatas!=null){
					for(SoccerLeagueArrange arrange : arrangedatas){
						arrange.setState(Integer.valueOf(state));
						cached.updateCached(("arrange_"+arrange.getMid()).getBytes(), JSON.toJSONString(arrange).getBytes(), 432000L);
						
					}
				}
				//胜平负赔率
				List<SoccerLeagueOdds> sfpOddsdatas = soccerLeagueOddsService.queryForList(new Finder("select a.* from soccer_league_odds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where  a.arrangeid2 = :arrangeid2 and a.type = 0 and b.endtime > NOW()").setParam("arrangeid2", soccerLeague2.getId2()), SoccerLeagueOdds.class);
				if(sfpOddsdatas!=null){
					for(SoccerLeagueOdds sfpOdds : sfpOddsdatas){
						sfpOdds.setState(Integer.valueOf(state));
						SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select win,flat,lose from soccer_adjustodds where mid = :mid").setParam("mid", sfpOdds.getMid()), SoccerAdjustodds.class);
						sfpOdds.setWin(sfpOdds.getWin()+soccerAdjustodds.getWin().doubleValue());
						sfpOdds.setFlat(sfpOdds.getFlat()+soccerAdjustodds.getFlat().doubleValue());
						sfpOdds.setLose(sfpOdds.getLose()+soccerAdjustodds.getLose().doubleValue());
						cached.updateCached(("sfp_"+sfpOdds.getMid()).getBytes(), JSON.toJSONString(sfpOdds).getBytes(), null);
					}
				}
				//让球胜平负
				List<SoccerLeagueOdds> rqsfpOddsdatas = soccerLeagueOddsService.queryForList(new Finder("select a.* from soccer_league_odds  a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where  a.arrangeid2 = :arrangeid2 and a.type = 1 and b.endtime > NOW()").setParam("arrangeid2", soccerLeague2.getId2()), SoccerLeagueOdds.class);
				if(rqsfpOddsdatas!=null){
					for(SoccerLeagueOdds rqsfpOdds : rqsfpOddsdatas){
						rqsfpOdds.setState(Integer.valueOf(state));
						SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select rqwin,rqflat,rqlose from soccer_adjustodds where mid = :mid").setParam("mid", rqsfpOdds.getMid()), SoccerAdjustodds.class);
						rqsfpOdds.setWin(rqsfpOdds.getWin()+soccerAdjustodds.getRqwin().doubleValue());
						rqsfpOdds.setFlat(rqsfpOdds.getFlat()+soccerAdjustodds.getRqflat().doubleValue());
						rqsfpOdds.setLose(rqsfpOdds.getLose()+soccerAdjustodds.getRqlose().doubleValue());
						cached.updateCached(("rqsfp_"+rqsfpOdds.getMid()).getBytes(), JSON.toJSONString(rqsfpOdds).getBytes(), null);
					}
				}
				//比分赔率
				List<SoccerLeagueScoreodds> scoreOddsdatas = soccerLeagueScoreoddsService.queryForList(new Finder("select a.* from soccer_league_scoreodds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where  a.arrangeid2 = :arrangeid2 and b.endtime > NOW()").setParam("arrangeid2", soccerLeague2.getId2()), SoccerLeagueScoreodds.class);
				if(scoreOddsdatas!=null){
					for(SoccerLeagueScoreodds scoreOdds : scoreOddsdatas){
						scoreOdds.setState(Integer.valueOf(state));
						SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select win10,win20,win21,win30,win31,win32,win40,win41,win42,win50,win51,win52,win3A,flat00,flat11,flat22,flat33,flat1A,lose01,lose02,lose03,lose04,lose05,lose12,lose13,lose14,lose15,lose23,lose24,lose25,lose0A from soccer_adjustodds where mid = :mid").setParam("mid", scoreOdds.getMid()), SoccerAdjustodds.class);
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
					}
				}
				//半全场
				List<SoccerLeagueHalfallodds> halfallOddsdatas = soccerLeagueHalfalloddsService.queryForList(new Finder("select a.* from soccer_league_halfallodds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where  a.arrangeid2 = :arrangeid2 and b.endtime > NOW()").setParam("arrangeid2", soccerLeague2.getId2()), SoccerLeagueHalfallodds.class);
				if(halfallOddsdatas!=null){
					for(SoccerLeagueHalfallodds halfallOdds : halfallOddsdatas){
						halfallOdds.setState(Integer.valueOf(state));
						SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select sp00,sp01,sp03,sp10,sp11,sp13,sp30,sp31,sp33 from soccer_adjustodds where mid = :mid").setParam("mid", halfallOdds.getMid()), SoccerAdjustodds.class);
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
					}
				}
				//进球数
				List<SoccerLeagueGoalodds> goalOddsdatas = soccerLeagueGoaloddsService.queryForList(new Finder("select a.* from soccer_league_goalodds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid where  a.arrangeid2 = :arrangeid2 and b.endtime > NOW()").setParam("arrangeid2", soccerLeague2.getId2()), SoccerLeagueGoalodds.class);
				if(goalOddsdatas!=null){
					for(SoccerLeagueGoalodds goalOdds : goalOddsdatas){
						goalOdds.setState(Integer.valueOf(state));
						SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select ball0,ball1,ball2,ball3,ball4,ball5,ball6,ball7 from soccer_adjustodds where mid = :mid").setParam("mid", goalOdds.getMid()), SoccerAdjustodds.class);
						goalOdds.setBall0(goalOdds.getBall0()+soccerAdjustodds.getBall0().doubleValue());
						goalOdds.setBall1(goalOdds.getBall1()+soccerAdjustodds.getBall1().doubleValue());
						goalOdds.setBall2(goalOdds.getBall2()+soccerAdjustodds.getBall2().doubleValue());
						goalOdds.setBall3(goalOdds.getBall3()+soccerAdjustodds.getBall3().doubleValue());
						goalOdds.setBall4(goalOdds.getBall4()+soccerAdjustodds.getBall4().doubleValue());
						goalOdds.setBall5(goalOdds.getBall5()+soccerAdjustodds.getBall5().doubleValue());
						goalOdds.setBall6(goalOdds.getBall6()+soccerAdjustodds.getBall6().doubleValue());
						goalOdds.setBall7(goalOdds.getBall7()+soccerAdjustodds.getBall7().doubleValue());
						cached.updateCached(("jqs_"+goalOdds.getMid()).getBytes(), JSON.toJSONString(goalOdds).getBytes(), null);
					}
				}
				//2选1
				List<SoccerLeague2choose1odds> chooseOddsdatas = soccerLeague2choose1oddsService.queryForList(new Finder("select a.* from soccer_league_2choose1odds a LEFT JOIN soccer_league_arrange b on a.mid = b.mid  where  a.arrangeid2 = :arrangeid2 and b.endtime > NOW()").setParam("arrangeid2", soccerLeague2.getId2()), SoccerLeague2choose1odds.class);
				if(chooseOddsdatas!=null){
					for(SoccerLeague2choose1odds chooseOdds : chooseOddsdatas){
						chooseOdds.setState(Integer.valueOf(state));
						SoccerAdjustodds soccerAdjustodds = soccerAdjustoddsService.queryForObject(new Finder("select left_odds,right_odds from soccer_adjustodds where mid = :mid").setParam("mid", chooseOdds.getMid()), SoccerAdjustodds.class);
						chooseOdds.setLeft_odds(chooseOdds.getLeft_odds()+soccerAdjustodds.getLeft_odds().doubleValue());
						chooseOdds.setRight_odds(chooseOdds.getRight_odds()+soccerAdjustodds.getRight_odds().doubleValue());
						cached.updateCached(("2x1_"+chooseOdds.getMid()).getBytes(), JSON.toJSONString(chooseOdds).getBytes(), null);
					}
				}
			}else{
				//soccerLeagueService.saveorupdate(soccerLeague);
				if(soccerLeague.getId()!=null){
					soccerLeagueService.update(soccerLeague, true);
				}else{
					soccerLeagueService.save(soccerLeague);
				}
			}
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			e.printStackTrace();
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
		return "/lottery/soccerleague/soccerleagueCru";
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
				soccerLeagueService.deleteById(id,SoccerLeague.class);
			 	//soccerLeagueService.update(new Finder("update soccer_league set state=:state where id=:id").setParam("state",3).setParam("id", id));
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
			soccerLeagueService.deleteByIds(ids,SoccerLeague.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
