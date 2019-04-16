package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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
import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.entity.BasketballLeagueResult;
import org.springrain.lottery.entity.BasketballOrder;
import org.springrain.lottery.entity.BasketballOrderContent;
import org.springrain.lottery.entity.BasketballScheme;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.service.IBasketballLeagueArrangeService;
import org.springrain.lottery.service.IBasketballLeagueOddsService;
import org.springrain.lottery.service.IBasketballLeagueResultService;
import org.springrain.lottery.service.IBasketballLeagueService;
import org.springrain.lottery.service.IBasketballOrderContentService;
import org.springrain.lottery.service.IBasketballOrderService;
import org.springrain.lottery.service.IBasketballSchemeMatchService;
import org.springrain.lottery.service.IBasketballSchemeService;
import org.springrain.lottery.service.IBetAgentBrokerageService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.utils.basketballWeekOfDate;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:43:08
 * @see org.springrain.lottery.web.BasketballLeagueArrange
 */
@Controller
@RequestMapping(value="/basketballleaguearrange")
public class BasketballLeagueArrangeController  extends BaseController {
	@Resource
	private IBasketballLeagueArrangeService basketballLeagueArrangeService;
	@Resource
	private IBasketballLeagueService basketballLeagueService;
	@Resource
	private IBasketballLeagueOddsService basketballLeagueOddsService;
	@Resource
	private IBasketballLeagueResultService basketballLeagueResultService;
	@Resource
	private IBasketballOrderContentService basketballOrderContentService;
	@Resource
	private IBasketballOrderService basketballOrderService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentBrokerageService betAgentBrokerageService;
	@Resource
	private ICached cached;
	@Resource
	private IBasketballSchemeService basketballSchemeService;
	@Resource
	private IBasketballSchemeMatchService basketballSchemeMatchService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	private String listurl="/lottery/basketballleaguearrange/basketballleaguearrangeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballLeagueArrange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BasketballLeagueArrange basketballLeagueArrange) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String zid = request.getParameter("zid");
			//BasketballLeagueOdds basketballLeagueOdds = basketballLeagueOddsService.queryForObject(new Finder("select * from basketball_league_odds  where  zid = :zid" ).setParam("zid", zid), BasketballLeagueOdds.class);
			List<BasketballLeagueOdds> datas=basketballLeagueOddsService.queryForList(new Finder("select * from basketball_league_odds  where  zid = :zid" ).setParam("zid", zid), BasketballLeagueOdds.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BasketballLeagueOdds());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);	
			return  "/lottery/basketballleaguearrange/basketballleagueodds";
			
		}else{
			String matchname = null;
			String num  = null;
			if(basketballLeagueArrange.getMatchname()!=null){
				matchname = "%"+basketballLeagueArrange.getMatchname()+"%";
			}
			if(!StringUtils.isEmpty(basketballLeagueArrange.getNum())){
				num = basketballLeagueArrange.getNum();
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
			List<BasketballLeagueArrange> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas=basketballLeagueArrangeService.queryForList(new Finder("select * from basketball_league_arrange where (:matchname is null or matchname like :matchname) and (:state is null or state = :state) and (:ishot is null or ishot = :ishot) and (:num is null or num = :num) ").setParam("matchname", matchname).setParam("state", state).setParam("num", num).setParam("ishot", ishot), BasketballLeagueArrange.class, page);
			}else{
				datas=basketballLeagueArrangeService.queryForList(new Finder("select * from basketball_league_arrange where (:matchname is null or matchname like :matchname) and (:state is null or state = :state) and (:ishot is null or ishot = :ishot) and (:num is null or num = :num) and substr(starttime,1,10)>=:starttime and substr(starttime,1,10)<=:endtime").setParam("matchname", matchname).setParam("num", num).setParam("state", state).setParam("ishot", ishot).setParam("starttime",startDate).setParam("endtime", endDate), BasketballLeagueArrange.class, page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			if(datas!=null){
				for(BasketballLeagueArrange basketballArrange : datas){
					basketballArrange.setNum(basketballWeekOfDate.getWeekOfDate(basketballArrange.getMatchdate())+basketballArrange.getNum());
				}
			}
			returnObject.setQueryBean(basketballLeagueArrange);
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
	 * @param basketballLeagueArrange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BasketballLeagueArrange basketballLeagueArrange) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BasketballLeagueArrange> datas=basketballLeagueArrangeService.findListDataByFinder(null,page,BasketballLeagueArrange.class,basketballLeagueArrange);
			returnObject.setQueryBean(basketballLeagueArrange);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BasketballLeagueArrange basketballLeagueArrange) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = basketballLeagueArrangeService.findDataExportExcel(null,listurl, page,BasketballLeagueArrange.class,basketballLeagueArrange);
		String fileName="basketballLeagueArrange"+GlobalStatic.excelext;
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
		return "/lottery/basketballleaguearrange/basketballleaguearrangeLook";
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
		  BasketballLeagueArrange basketballLeagueArrange = basketballLeagueArrangeService.findBasketballLeagueArrangeById(id);
		   returnObject.setData(basketballLeagueArrange);
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
	ReturnDatas saveorupdate(Model model,BasketballLeagueArrange basketballLeagueArrange,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				BasketballLeagueArrange basketballLeagueArrange2 = basketballLeagueArrangeService.findBasketballLeagueArrangeById(id);
				BasketballLeague basketballLeague = basketballLeagueService.queryForObject(new Finder("select * from basketball_league where  id2 = :id2").setParam("id2", basketballLeagueArrange2.getMatchid2()), BasketballLeague.class);
				if(basketballLeague!=null){
					if(3==basketballLeague.getState()&&"1".equals(state)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次所属联赛已禁用,不可启用该场次");
						return returnObject;
					}
				}
				basketballLeagueArrangeService.update(new Finder("update basketball_league_arrange set state=:state where id=:id").setParam("state",state).setParam("id", id));
				basketballLeagueOddsService.update(new Finder("update basketball_league_odds set state=:state where  zid = :zid").setParam("state",state).setParam("zid", basketballLeagueArrange2.getZid()));
			}else if("2".equals(request.getParameter("k"))){

				//重新结算
				String zid = request.getParameter("zid");
				System.out.println("zid="+zid);
				BasketballLeagueResult result = basketballLeagueResultService.queryForObject(new Finder("select * from basketball_league_result where zid=:zid").setParam("zid", zid), BasketballLeagueResult.class);
				if(result==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次未出结果");
					return returnObject;
				}
				if(result.getState()!=1){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次未确认");
					return returnObject;
				}
				List<String> schemeidlist = basketballOrderContentService.queryForList(new Finder("select DISTINCT schemeid from basketball_order_content where zid=:zid ").setParam("zid", zid), String.class);
				for (String schemeid : schemeidlist) {
					//System.out.println("schemeidlist.size="+schemeidlist.size());
					BasketballScheme scheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid), BasketballScheme.class);
					if(scheme!=null&&scheme.getSituation()==1){
						BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", scheme.getMemberid2()), BetMember.class);
						betMember.setBankscore(betMember.getBankscore()-scheme.getBettingwin().doubleValue()+scheme.getPlusawards().doubleValue());
						betMember.setGamescore(betMember.getGamescore()-scheme.getPlusawards().doubleValue());
						betMember.setScore(betMember.getScore() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
						if(scheme.getBuytype()==2){
							if(scheme.getBrokeragemoney()!=null){
								betMember.setBankscore(betMember.getBankscore()-scheme.getBrokeragemoney().doubleValue());
								betMember.setScore(betMember.getScore()-scheme.getBrokeragemoney().doubleValue());
							}
							basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = 0 where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()));
						}
						betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score where id2=:id2 ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id2", betMember.getId2()).setParam("score", betMember.getScore()));
						Date dd=new Date();
						BetScorerecord scorerecord = new BetScorerecord();
						scorerecord.setId(null);
						scorerecord.setMemberid2(scheme.getMemberid2());
						scorerecord.setTime(dd);
						scorerecord.setContent("减去原有奖金");
						scorerecord.setOriginalscore(betMember.getScore() + scheme.getBettingwin().doubleValue() - scheme.getBettingmoney().doubleValue());
						scorerecord.setChangescore(-scheme.getBettingwin().doubleValue());
						scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore() +scheme.getPlusawards().doubleValue()));
						scorerecord.setObankscore(new BigDecimal(betMember.getBankscore() + scheme.getBettingwin().doubleValue() - scheme.getPlusawards().doubleValue()));
						scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
						if(scheme.getBuytype()==2){
							if(scheme.getBrokeragemoney()!=null){
								scorerecord.setChangescore(-scheme.getBettingwin().doubleValue()-scheme.getBrokeragemoney().doubleValue());
								scorerecord.setOriginalscore(scorerecord.getOriginalscore()+scheme.getBrokeragemoney().doubleValue());
								scorerecord.setObankscore(new BigDecimal(scorerecord.getObankscore().doubleValue()+scheme.getBrokeragemoney().doubleValue()));
							}
						}
						scorerecord.setBalance(betMember.getScore());
						scorerecord.setState(1);
						scorerecord.setType(25);			
						scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
						scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
						scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
						scorerecord.setAgentid(betMember.getAgentid());
						scorerecord.setAgentparentid(betMember.getAgentparentid());
						scorerecord.setAgentparentids(betMember.getAgentparentids());
						scorerecord.setOrderid(scheme.getSchemeid());
						betScorerecordService.save(scorerecord);
						if(scheme.getBuytype()==1){
							BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
							if(!"A101".equals(betAgent.getParentid())){
								betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
							}
							
							if(scheme.getBrokerageagentmoney()!=null){
								betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)-:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)-:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", scheme.getBrokerageagentmoney()).setParam("agentid", betAgent.getAgentid()));
							}
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("此方案信息有误");
					}	
					//List<String> zids = basketballSchemeMatchService.queryForList(new Finder("select zid from basketball_scheme_match where schemeid = :schemeid group by zid").setParam("schemeid", scheme.getSchemeid()), String.class);
					//List<BasketballLeagueResult> datas=basketballLeagueResultService.queryForList(new Finder("select * from basketball_league_result  where  state = 1 and zid in (:zid) ").setParam("zid", zids), BasketballLeagueResult.class);
					
					List<BasketballLeagueResult> datas=basketballLeagueResultService.queryForList(new Finder("select * from basketball_league_result  where  state = 1 and zid=:zid ").setParam("zid", zid), BasketballLeagueResult.class);
					if(datas!=null&&!datas.isEmpty()){
						for(BasketballLeagueResult basketballResult : datas){
							//System.out.println("basketballResult.matchname="+basketballResult.getZid()+" awayteam="+basketballResult.getAwayteam()+" hometeam="+basketballResult.getHometeam());
							String sfresult = null;
							String allscore[] = basketballResult.getScore().split(":");
							if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
								basketballResult.setSf("sfzf");
								sfresult="主负";
							}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])){
								basketballResult.setSf("sfzs");
								sfresult="主胜";
							}
							//System.out.println("sfresult="+sfresult);
							basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:sfresult where zid = :zid and oddsname in(:s,:f) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfresult", sfresult).setParam("zid", basketballResult.getZid()).setParam("s", "sfzs").setParam("f", "sfzf").setParam("resultname", basketballResult.getSf()));
							basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:sfresult where zid = :zid and oddsname in(:s,:f) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfresult", sfresult).setParam("zid", basketballResult.getZid()).setParam("s", "sfzs").setParam("f", "sfzf").setParam("resultname", basketballResult.getSf()));
							//System.out.println("sfresult================end");
							
							List<BasketballOrderContent> rfdatas = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where zid = :zid and oddsname in(:rs,:rf) and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("zid", basketballResult.getZid()).setParam("rs", "rfzs").setParam("rf", "rfzf"), BasketballOrderContent.class);
							if(rfdatas!=null&&!rfdatas.isEmpty()){
								System.out.println("rfdatas.size="+rfdatas.size());
								for(BasketballOrderContent rfOrderContent : rfdatas){
									Double letpoints = null;
									if(rfOrderContent.getBase()!=null){
										letpoints = rfOrderContent.getBase().doubleValue();
									}
									if(letpoints==null){
										letpoints = basketballLeagueOddsService.queryForObject(new Finder("select letpoints from basketball_league_odds where zid = :zid").setParam("zid", basketballResult.getZid()), Double.class);
										if(letpoints==null){
											try {
												letpoints =  Double.valueOf(basketballResult.getLetpoints());
											} catch (Exception e) {
												System.out.println(e);
											}
										}
									}
									String rfsfresult = null;
									if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])+letpoints){
										basketballResult.setRfsf("rfzf");
										rfsfresult = "让分主负";
									}else if(Integer.valueOf(allscore[0])<Integer.valueOf(allscore[1])+letpoints){
										basketballResult.setRfsf("rfzs");
										rfsfresult = "让分主胜";
									}
									//System.out.println("rfsfresult="+rfsfresult);
									if(rfOrderContent.getOddsname().equals(basketballResult.getRfsf())){
										basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:rfsfresult where id = :id and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("rfsfresult", rfsfresult).setParam("id", rfOrderContent.getId()));
									}else{
										basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:rfsfresult where id = :id  and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("rfsfresult", rfsfresult).setParam("id", rfOrderContent.getId()));
									}
									//System.out.println("rfsfresult================end");
								}
							}
							//大小分
							List<BasketballOrderContent> dxfdatas = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where zid = :zid and oddsname in(:b,:s) and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("zid", basketballResult.getZid()).setParam("b", "big").setParam("s", "small"), BasketballOrderContent.class);
							if(dxfdatas!=null&&!dxfdatas.isEmpty()){
								System.out.println("dxfdatas.size="+dxfdatas.size());
								for(BasketballOrderContent dxfOrderContent : dxfdatas){
									String dxfresult = null;
									int allf = Integer.valueOf(allscore[0])+Integer.valueOf(allscore[1]);
									Double dxfys = null;
									if(dxfOrderContent.getBase()!=null){
										dxfys = dxfOrderContent.getBase().doubleValue();
									}
									if(dxfys==null){
										dxfys = basketballLeagueOddsService.queryForObject(new Finder("select dxf from basketball_league_odds where zid = :zid").setParam("zid", basketballResult.getZid()), Double.class);
									}
									if(dxfys==null){
										try {
											dxfys =  Double.valueOf(basketballResult.getDxf());
										} catch (Exception e) {
											System.out.println(e);
										}
									}
									if(allf>dxfys){
										basketballResult.setDxf("big");
										dxfresult = "大";
									}else{
										basketballResult.setDxf("small");
										dxfresult = "小";
									}
									//System.out.println("dxfresult="+dxfresult);
									if(dxfOrderContent.getOddsname().equals(basketballResult.getDxf())){
										basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:dxfresult where  id = :id and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("dxfresult", dxfresult).setParam("id", dxfOrderContent.getId()));
									}else{
										basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:dxfresult where  id = :id and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("dxfresult", dxfresult).setParam("id", dxfOrderContent.getId()));
									}
									//System.out.println("dxfresult================end");
								}
							}
							String sfcresult = null;
							if(Integer.valueOf(allscore[0])>Integer.valueOf(allscore[1])){
								int fc = Integer.valueOf(allscore[0]) - Integer.valueOf(allscore[1]);
								if(fc>=1&&fc<=5){
									basketballResult.setSfc("ksfc1t5");
									sfcresult = "客胜1-5";
								}else if(fc>=6&&fc<=10){
									basketballResult.setSfc("ksfc6t10");
									sfcresult = "客胜6-10";
								}else if(fc>=11&&fc<=15){
									basketballResult.setSfc("ksfc11t15");
									sfcresult = "客胜11-15";
								}else if(fc>=16&&fc<=20){
									basketballResult.setSfc("ksfc16t20");
									sfcresult = "客胜16-20";
								}else if(fc>=21&&fc<=25){
									basketballResult.setSfc("ksfc21t25");
									sfcresult = "客胜21-25";
								}else if(fc>=26){
									basketballResult.setSfc("ksfc26");
									sfcresult = "客胜26+";
								}
							}else{
								int fc =  Integer.valueOf(allscore[1]) -  Integer.valueOf(allscore[0]);
								if(fc>=1&&fc<=5){
									basketballResult.setSfc("zsfc1t5");
									sfcresult = "主胜1-5";
								}else if(fc>=6&&fc<=10){
									basketballResult.setSfc("zsfc6t10");
									sfcresult = "主胜6-10";
								}else if(fc>=11&&fc<=15){
									basketballResult.setSfc("zsfc11t15");
									sfcresult = "主胜11-15";
								}else if(fc>=16&&fc<=20){
									basketballResult.setSfc("zsfc16t20");
									sfcresult = "主胜16-20";
								}else if(fc>=21&&fc<=25){
									basketballResult.setSfc("zsfc21t25");
									sfcresult = "主胜21-25";
								}else if(fc>=26){
									basketballResult.setSfc("zsfc26");
									sfcresult = "主胜26+";
								}
							}
							//System.out.println("sfcresult="+sfcresult);
							basketballOrderContentService.update(new Finder("update basketball_order_content set result=3,settletime=now(),resultname=:sfcresult where zid = :zid and left(oddsname, 4) in(:zsfc,:ksfc) and oddsname!=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfcresult", sfcresult).setParam("zid", basketballResult.getZid()).setParam("zsfc", "zsfc").setParam("ksfc", "ksfc").setParam("resultname", basketballResult.getSfc()));
							basketballOrderContentService.update(new Finder("update basketball_order_content set result=1,settletime=now(),resultname=:sfcresult where zid = :zid and left(oddsname, 4) in(:zsfc,:ksfc) and oddsname=:resultname and schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()).setParam("sfcresult", sfcresult).setParam("zid", basketballResult.getZid()).setParam("zsfc", "zsfc").setParam("ksfc", "ksfc").setParam("resultname", basketballResult.getSfc()));
							basketballLeagueResultService.update(new Finder("update basketball_league_result set issettle=1 where zid = :zid").setParam("zid", basketballResult.getZid()));
							//System.out.println("sfcresult================end");
						}
					}
					//篮球重新返奖
					List<String> schemelist = basketballSchemeService.queryForList(new Finder("select schemeid from basketball_scheme where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()), String.class);
					if(schemelist!=null){
						System.out.println("schemelist.size="+schemelist.size());
						for (String schemeid1 : schemelist) {
							Integer untreatedcontent = basketballOrderService.queryForObject(new Finder("select count(*) from basketball_order a right join basketball_order_content b on b.orderid=a.orderid where a.schemeid=:schemeid and b.result=0 ").setParam("schemeid", schemeid1), Integer.class);
							if(untreatedcontent==0){
								try{
									List<BasketballOrder> orderList = basketballOrderService.queryForList(new Finder("select * from basketball_order where state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), BasketballOrder.class);
									if((orderList !=null)&&(!orderList.isEmpty())){
										Double schemereward=0.;
										for (BasketballOrder basketballOrder : orderList) {
											List<BasketballOrderContent>  contentList = basketballOrderContentService.queryForList(new Finder("select * from basketball_order_content where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()), BasketballOrderContent.class);
											int count = 0 ;
											if((contentList!=null)&&(!contentList.isEmpty())){
												for (BasketballOrderContent basketballOrderContent : contentList) {
													if(basketballOrderContent.getResult()==1){
														count++;
													}
												}
												if(contentList.size() == count){
													//中奖了 返奖
													Double odds = 1.;
													for (BasketballOrderContent basketballOrderContent : contentList) {
														odds*=basketballOrderContent.getOdds();
													}
													Double award = odds*(basketballOrder.getBettingmoney().doubleValue());
													Double posttaxprice=award;
													schemereward+=posttaxprice;
													basketballOrderService.update(new Finder("update basketball_order set result=1,settletime=now(),bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid ").setParam("award", posttaxprice).setParam("posttaxprize", award).setParam("orderid", basketballOrder.getOrderid()));
												}else{
													basketballOrderService.update(new Finder("update basketball_order set result=3,settletime=now(),bettingwin=0,posttaxprize=0 where orderid=:orderid ").setParam("orderid", basketballOrder.getOrderid()));
												}
											}
										}
										BasketballScheme basketballScheme2 = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), BasketballScheme.class);
										BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", basketballScheme2.getMemberid2()), BetMember.class);
										
										try {
											if(basketballScheme2.getBuytype()==1){
												//跟单
												BasketballScheme sdbasketballsheme = basketballSchemeService.queryForObject(new Finder("select * from basketball_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", basketballScheme2.getSchemeid2()), BasketballScheme.class);
												if(sdbasketballsheme.getPubstate()!=null&&sdbasketballsheme.getPubstate()==2){
													BigDecimal guarantee = sdbasketballsheme.getGuarantee();
													BigDecimal guaranteeMoney = null;
													if(guarantee!=null){
														guaranteeMoney = guarantee.multiply(basketballScheme2.getBettingmoney());
													}
													if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
														BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join basketball_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 limit 1").setParam("schemeid2",basketballScheme2.getSchemeid2() ), BetMember.class);
														BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
														if(!"A101".equals(betAgent.getParentid())){
															betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
														}
														
														BigDecimal brokeragemember = sdbasketballsheme.getBrokeragemember(); // 大神佣金比例
														BigDecimal brokerageagent = sdbasketballsheme.getBrokerageagent(); // 一级代理佣金比例
														
														if (brokeragemember == null || brokerageagent == null
																|| brokeragemember.compareTo(BigDecimal.ZERO) <= 0
																|| brokerageagent.compareTo(new BigDecimal(0)) <= 0) {
															BetAgentBrokerage agentBrokerage = betAgentBrokerageService
																	.queryForObject(
																			new Finder(
																					"select * from bet_agent_brokerage where agentid = :agentid")
																							.setParam("agentid",
																									betAgent.getAgentid()),
																			BetAgentBrokerage.class);
															if (agentBrokerage == null) {
																BetAgentBrokerage brokerage = new BetAgentBrokerage();
																brokerage.setAgentid(betAgent.getAgentid());
																brokerage.setAgentparentid(betAgent.getParentid());
																brokerage.setAgentparentids(betAgent.getParentids());
																brokerage.setBrokerageagent(new BigDecimal(0.02));
																brokerage.setBrokeragemember(new BigDecimal(0.08));
																betAgentBrokerageService.save(brokerage);
																agentBrokerage = brokerage;
															}

															brokeragemember = agentBrokerage.getBrokeragemember();
															brokerageagent = agentBrokerage.getBrokerageagent();
														}
														BigDecimal bettingwin = new BigDecimal(Double.toString(schemereward));
														//跟单方案大神佣金
														BigDecimal brokeragemoney = bettingwin.multiply(brokeragemember);
														basketballScheme2.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
														//跟单方案代理佣金
														BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
														basketballScheme2.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
														basketballScheme2.setBrokerageagentid(betAgent.getAgentid());
														//返给代理佣金
														Integer agentupdatenum=null;
														for(int i=0;i<10;i++){
															if(agentupdatenum==null){
																agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
															}else if(agentupdatenum==0){
																agentupdatenum = betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)+:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)+:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", brokerageagentmoney).setParam("agentid", betAgent.getAgentid()));
																
															}else if(agentupdatenum==1){
																break;
															}
														}
														//跟单方案减总佣金
														BigDecimal sumbrokeragemoney = brokeragemoney.add(brokerageagentmoney);
														schemereward = bettingwin.subtract(sumbrokeragemoney).doubleValue();
														basketballScheme2.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
														if(sdbasketballsheme.getBrokeragemoney()!=null){
															sdbasketballsheme.setBrokeragemoney(sdbasketballsheme.getBrokeragemoney().add(brokeragemoney));
														}else{
															sdbasketballsheme.setBrokeragemoney(brokeragemoney);
														}
														sdmember.setBankscore(sdmember.getBankscore() + brokeragemoney.doubleValue());
														sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
														sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
														sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
														Integer updatenum=null;
														for(int i=0;i<10;i++){
															if(updatenum==null){
																updatenum = betMemberService.update(new Finder("update bet_member set bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", sdmember.getBankscore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
															}else if(updatenum==0){
																sdmember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", sdmember.getId()), BetMember.class);
																sdmember.setGamescore(sdmember.getGamescore() + brokeragemoney.doubleValue());
																sdmember.setScore(sdmember.getScore() + brokeragemoney.doubleValue());
																sdmember.setDayscore(sdmember.getDayscore()  + brokeragemoney.doubleValue());
																sdmember.setWinorfail(sdmember.getWinorfail() + brokeragemoney.doubleValue());
																updatenum = betMemberService.update(new Finder("update bet_member set bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", sdmember.getBankscore()).setParam("id", sdmember.getId()).setParam("score", sdmember.getScore()).setParam("dayscore", sdmember.getDayscore()).setParam("winorfail", sdmember.getWinorfail()).setParam("version", sdmember.getVersion()));
																
															}else if(updatenum==1){
																break;
															}
														}
														if(updatenum==1){
															
															//更新缓存
															String ticket = sdmember.getTicket();
															if(ticket!=null){
																try{
																	ObjectMapper mapper=new ObjectMapper();
																	byte[] json = mapper.writeValueAsBytes(sdmember);
																	cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
																}catch(Exception e){
																	System.out.println(e);
																}
															}
															Date dd=new Date();
															BetScorerecord scorerecord = new BetScorerecord();
															scorerecord.setId(null);
															scorerecord.setMemberid2(sdbasketballsheme.getMemberid2());
															scorerecord.setTime(dd);
															scorerecord.setContent("重新结算篮彩佣金");
															scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
															scorerecord.setChangescore(brokeragemoney.doubleValue());
															scorerecord.setBalance(sdmember.getScore());
															scorerecord.setState(1);
															scorerecord.setType(17);
															scorerecord.setOgamescore(new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setObankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setOfreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setGamescore(new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setBankscore(new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setFreezescore(new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP));
															scorerecord.setAgentid(sdmember.getAgentid());
															scorerecord.setAgentparentid(sdmember.getAgentparentid());
															scorerecord.setAgentparentids(sdmember.getAgentparentids());
															scorerecord.setOrderid(schemeid1);
															betScorerecordService.save(scorerecord);
															//投注方案
															basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", basketballScheme2.getBrokeragemoney()).setParam("brokerageagentmoney", basketballScheme2.getBrokerageagentmoney()).setParam("brokeragemembermoney", basketballScheme2.getBrokeragemembermoney()).setParam("brokerageagentid", basketballScheme2.getBrokerageagentid()));
															basketballSchemeService.update(new Finder("update basketball_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdbasketballsheme.getSchemeid()).setParam("brokeragemoney", sdbasketballsheme.getBrokeragemoney()));
														}
													}
												}
												
											}
										} catch (Exception e) {
											System.out.println(e);
										}
										String firstagentid=null;
										Double bbb=null;
										try {
											String parentids = betMember.getAgentparentids();
											String parentid=betMember.getAgentparentid();
											if("A101".equals(parentid)){
												bbb = basketballSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lq limit 1").setParam("lq", "lq").setParam("agentid", betMember.getAgentid()),Double.class);
											}else{
												String[] split = parentids.split(",");
												int i=0;
												for (String string : split) {
													if(i==0){
														if("A101".equals(string)){
															i=1;
														}
													}else{
														firstagentid=string;
														break;
													}
												}
												bbb = basketballSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:lq limit 1").setParam("lq", "lq").setParam("agentid", firstagentid),Double.class);
											}
										} catch (Exception e) {
											System.out.println(e);
										}
										Double plusawards=0.;
										if(bbb!=null){
											plusawards=bbb*schemereward;
										}
										betMember.setBankscore(betMember.getBankscore()+schemereward);
										betMember.setGamescore(betMember.getGamescore() + plusawards);
										betMember.setScore(betMember.getScore() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
										betMember.setDayscore(betMember.getDayscore()  + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
										betMember.setWinorfail(betMember.getWinorfail() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
										Integer updatenum=null;
										for(int i=0;i<10;i++){
											if(updatenum==null){
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("version", betMember.getVersion()));
											}else if(updatenum==0){
												betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
												betMember.setBankscore(betMember.getBankscore()+schemereward);
												betMember.setGamescore(betMember.getGamescore() + plusawards);
												betMember.setScore(betMember.getScore() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
												betMember.setDayscore(betMember.getDayscore()  + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
												betMember.setWinorfail(betMember.getWinorfail() + schemereward +plusawards - basketballScheme2.getBettingmoney().doubleValue());
												updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("version", betMember.getVersion()));
											}else if(updatenum==1){
												break;
											}
										}	
										if(updatenum==1){
											//更新缓存
											String ticket = betMember.getTicket();
											if(ticket!=null){
												try{
													ObjectMapper mapper=new ObjectMapper();
													byte[] json = mapper.writeValueAsBytes(betMember);
													cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
												}catch(Exception e){
													System.out.println(e);
												}
											}
											Date dd=new Date();
											try {
												BetScorerecord scorerecord = new BetScorerecord();
												scorerecord.setId(null);
												scorerecord.setMemberid2(basketballScheme2.getMemberid2());
												scorerecord.setTime(dd);
												scorerecord.setContent("重新结算篮彩返奖");
												scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + basketballScheme2.getBettingmoney().doubleValue());
												scorerecord.setBalance(betMember.getScore());
												scorerecord.setOgamescore(new BigDecimal(betMember.getGamescore()-plusawards));
												scorerecord.setObankscore(new BigDecimal(betMember.getBankscore()-schemereward));
												scorerecord.setOfreezescore(new BigDecimal(betMember.getFreezingscore()));
												scorerecord.setGamescore(new BigDecimal(betMember.getGamescore()));
												scorerecord.setBankscore(new BigDecimal(betMember.getBankscore()));
												scorerecord.setFreezescore(new BigDecimal(betMember.getFreezingscore()));
												scorerecord.setOrderid(schemeid1);
												scorerecord.setChangescore(schemereward+plusawards);
												scorerecord.setState(1);
												scorerecord.setType(16);
												scorerecord.setAgentid(betMember.getAgentid());
												scorerecord.setAgentparentid(betMember.getAgentparentid());
												scorerecord.setAgentparentids(betMember.getAgentparentids());
												betScorerecordService.save(scorerecord);
											} catch (Exception e) {
												System.out.println(e);
											}
											//投注方案
											basketballSchemeService.update(new Finder("update basketball_scheme set plusawards=:plusawards,bettingwin=:xxxxk, situation=1,settlementtime=:settlementtime,pretaxamount=:xxxx WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("xxxxk", schemereward+plusawards).setParam("xxxx", schemereward));
											//汇总投注
											soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id  and type=3 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
											
											//连红========================================
											if(basketballScheme2.getBuytype()==2){
												List<Double> hong = soccerAllbettingService.queryForList(new Finder("select bettingscore from soccer_allbetting" +
														" where memberid2=:id2 and state=1 and buytype=2 order by bettingtime desc limit 30 ").setParam("id2", basketballScheme2.getMemberid2()), Double.class);
												Integer lianhong = 0;
												if(hong.size()>0){
													for (Double score : hong) {
														if(score<=0){
															break;
														}else{
															lianhong++;
														}
													}
												}
												betMemberService.update(new Finder("update bet_member set lianhong =:lianhong where id2=:id2 ").setParam("lianhong", lianhong).setParam("id2", basketballScheme2.getMemberid2()));
	 										}
											//连红结束======================================
										}
									}
								} catch (Exception e) {
									System.out.println(e);
								}
							}else{
								continue;
							}
						}
					}
				}
				System.out.println("===================================重新结算成功==================================");
				return returnObject;
			}if("3".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  danguan=request.getParameter("danguan");
				BasketballLeagueArrange basketballLeagueArrange2 = basketballLeagueArrangeService.findBasketballLeagueArrangeById(id);
				BasketballLeague basketballLeague = basketballLeagueService.queryForObject(new Finder("select * from basketball_league where  id2 = :id2").setParam("id2", basketballLeagueArrange2.getMatchid2()), BasketballLeague.class);
				if(basketballLeague!=null){
					if(3==basketballLeague.getState()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次所属联赛已禁用,不可启用该场次");
						return returnObject;
					}
				}
				basketballLeagueArrangeService.update(new Finder("update basketball_league_arrange set danguan=:danguan where id=:id").setParam("danguan",danguan).setParam("id", id));
			}else if("4".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  danguan=request.getParameter("danguan");
				BasketballLeagueArrange basketballLeagueArrange2 = basketballLeagueArrangeService.findBasketballLeagueArrangeById(id);
				BasketballLeague basketballLeague = basketballLeagueService.queryForObject(new Finder("select * from basketball_league where  id2 = :id2").setParam("id2", basketballLeagueArrange2.getMatchid2()), BasketballLeague.class);
				if(basketballLeague!=null){
					if(3==basketballLeague.getState()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次所属联赛已禁用,不可启用该场次");
						return returnObject;
					}
				}
				basketballLeagueArrangeService.update(new Finder("update basketball_league_arrange set rfsfdanguan=:rfsfdanguan where id=:id").setParam("rfsfdanguan",danguan).setParam("id", id));
			}else if("5".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  danguan=request.getParameter("danguan");
				BasketballLeagueArrange basketballLeagueArrange2 = basketballLeagueArrangeService.findBasketballLeagueArrangeById(id);
				BasketballLeague basketballLeague = basketballLeagueService.queryForObject(new Finder("select * from basketball_league where  id2 = :id2").setParam("id2", basketballLeagueArrange2.getMatchid2()), BasketballLeague.class);
				if(basketballLeague!=null){
					if(3==basketballLeague.getState()){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次所属联赛已禁用,不可启用该场次");
						return returnObject;
					}
				}
				basketballLeagueArrangeService.update(new Finder("update basketball_league_arrange set dxfdanguan=:dxfdanguan where id=:id").setParam("dxfdanguan",danguan).setParam("id", id));
			}else{
				//basketballLeagueArrangeService.saveorupdate(basketballLeagueArrange);
				if(basketballLeagueArrange.getId()!=null){
					basketballLeagueArrangeService.update(basketballLeagueArrange, true);
				}else{
					basketballLeagueArrangeService.save(basketballLeagueArrange);
				}
			}
		
		
			
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			System.out.println(e);
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
		return "/lottery/basketballleaguearrange/basketballleaguearrangeCru";
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
				basketballLeagueArrangeService.deleteById(id,BasketballLeagueArrange.class);
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
			basketballLeagueArrangeService.deleteByIds(ids,BasketballLeagueArrange.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
