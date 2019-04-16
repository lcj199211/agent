package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BjdcArrange;
import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.lottery.entity.BjdcOrderContent;
import org.springrain.lottery.entity.BjdcPlaymethodOddsname;
import org.springrain.lottery.entity.BjdcResult;
import org.springrain.lottery.entity.BjdcScheme;
import org.springrain.lottery.service.IBetAgentBrokerageService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBjdcArrangeService;
import org.springrain.lottery.service.IBjdcOddsService;
import org.springrain.lottery.service.IBjdcOrderContentService;
import org.springrain.lottery.service.IBjdcOrderService;
import org.springrain.lottery.service.IBjdcPlaymethodOddsnameService;
import org.springrain.lottery.service.IBjdcResultService;
import org.springrain.lottery.service.IBjdcSchemeMatchService;
import org.springrain.lottery.service.IBjdcSchemeService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
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
 * @version  2017-11-29 15:01:22
 * @see org.springrain.lottery.web.BjdcArrange
 */
@Controller
@RequestMapping(value="/bjdcarrange")
public class BjdcArrangeController  extends BaseController {
	@Resource
	private IBjdcArrangeService bjdcArrangeService;
	@Resource
	private IBjdcOddsService bjdcOddsService;
	@Resource
	private IBjdcResultService bjdcResultService;
	@Resource
	private IBjdcOrderContentService bjdcOrderContentService;
	@Resource
	private IBjdcSchemeService bjdcSchemeService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBjdcSchemeMatchService bjdcSchemeMatchService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBjdcOrderService bjdcOrderService;
	@Resource
	private IBjdcPlaymethodOddsnameService bjdcPlaymethodOddsnameService;
	@Resource
	private IBetAgentBrokerageService betAgentBrokerageService;
	@Resource
	private ICached cached;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	
	private String listurl="/lottery/bjdcarrange/bjdcarrangeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param bjdcArrange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BjdcArrange bjdcArrange) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//赔率
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			String fid = request.getParameter("fid");
			List<BjdcOdds> datas=bjdcOddsService.queryForList(new Finder("select * from bjdc_odds  where  fid = :fid" ).setParam("fid", fid), BjdcOdds.class);
			returnObject.setData(datas);
			returnObject.setQueryBean(new BasketballLeagueOdds());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);	
			return  "/lottery/bjdcarrange/bjdcodds";
		}else{
			String matchname = null;
			String periodnum = null;
			String num = null;
			Integer state = null;
			if(!StringUtils.isEmpty(bjdcArrange.getMatchname())){
				if(bjdcArrange.getMatchname()!=null){
					matchname = "%"+bjdcArrange.getMatchname()+"%";
				}
			}
			if(!StringUtils.isEmpty(bjdcArrange.getPeriodnum())){
				if(bjdcArrange.getPeriodnum()!=null){
					periodnum = bjdcArrange.getPeriodnum();
				}
			}
			if(!StringUtils.isEmpty(bjdcArrange.getNum())){
				if(bjdcArrange.getNum()!=null){
					num = bjdcArrange.getNum();
				}
			}
			if(bjdcArrange.getState()!=null){
				if(bjdcArrange.getState()==100){
					state = null;
				}else{
					state = bjdcArrange.getState();
				}
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
			List<BjdcArrange> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas = bjdcArrangeService.queryForList(new Finder("select * from bjdc_arrange where (:periodnum is null or periodnum like :periodnum) and (:num is null or num like :num) and (:matchname is null or matchname like :matchname) and (:state is null or state = :state) ").setParam("periodnum", periodnum).setParam("num", num).setParam("matchname", matchname).setParam("state", state),BjdcArrange.class,page);
			}else{
				datas = bjdcArrangeService.queryForList(new Finder("select * from bjdc_arrange where (:periodnum is null or periodnum like :periodnum) and (:num is null or num like :num) and (:matchname is null or matchname like :matchname) and (:state is null or state = :state) and substr(starttime,1,10)>=:starttime and substr(starttime,1,10)<=:endtime").setParam("periodnum", periodnum).setParam("num", num).setParam("matchname", matchname).setParam("state", state).setParam("starttime",startDate).setParam("endtime", endDate),BjdcArrange.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			returnObject.setQueryBean(bjdcArrange);
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
	 * @param bjdcArrange
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BjdcArrange bjdcArrange) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BjdcArrange> datas=bjdcArrangeService.findListDataByFinder(null,page,BjdcArrange.class,bjdcArrange);
			returnObject.setQueryBean(bjdcArrange);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BjdcArrange bjdcArrange) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = bjdcArrangeService.findDataExportExcel(null,listurl, page,BjdcArrange.class,bjdcArrange);
		String fileName="bjdcArrange"+GlobalStatic.excelext;
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
		return "/lottery/bjdcarrange/bjdcarrangeLook";
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
		  BjdcArrange bjdcArrange = bjdcArrangeService.findBjdcArrangeById(id);
		   returnObject.setData(bjdcArrange);
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
	ReturnDatas saveorupdate(Model model,BjdcArrange bjdcArrange,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			 String pagentid = SessionUser.getShiroUser().getAgentid();
			 String agentparentids = ","+pagentid+",";
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				bjdcArrangeService.update(new Finder("update bjdc_arrange set state=:state where id=:id").setParam("state",state).setParam("id", id));
			}else if("2".equals(request.getParameter("k"))){
				//重新结算
				String fid = request.getParameter("fid");
				if(fid==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次不存在");
				}
				BjdcResult result = bjdcResultService.queryForObject(new Finder("select * from bjdc_result where fid=:fid").setParam("fid", fid), BjdcResult.class);
				if(result.getState()!=1){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次未完赛");
				}
				if(result.getIstrue()!=1){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次未确认");
				}
				List<String> schemeidlist = bjdcOrderContentService.queryForList(new Finder("select DISTINCT schemeid from bjdc_order_content where fid=:fid ").setParam("fid", fid), String.class);
				if(!schemeidlist.isEmpty()){
					for (String schemeid : schemeidlist) {
						BjdcScheme scheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid and (agentid = :agentid or agentparentids like :agentparentids) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("schemeid", schemeid), BjdcScheme.class);
						if(scheme!=null&&scheme.getSituation()==1){
							BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", scheme.getMemberid2()), BetMember.class);
							betMember.setBankscore(betMember.getBankscore()-scheme.getBettingwin().doubleValue()+scheme.getPlusawards().doubleValue());
							betMember.setGamescore(betMember.getGamescore()-scheme.getPlusawards().doubleValue());
							betMember.setScore(betMember.getScore() - scheme.getBettingwin().doubleValue() + scheme.getBettingmoney().doubleValue());
							if(scheme.getBuytype()==2){
								if(scheme.getBrokeragemembermoney()!=null){
									betMember.setBankscore(betMember.getBankscore()-scheme.getBrokeragemoney().doubleValue());
									betMember.setScore(betMember.getScore()-scheme.getBrokeragemoney().doubleValue());
								}
								bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = 0 where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()));
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
								if(scheme.getBrokeragemembermoney()!=null){
									scorerecord.setChangescore(-scheme.getBettingwin().doubleValue()-scheme.getBrokeragemoney().doubleValue());
									scorerecord.setOriginalscore(scorerecord.getOriginalscore()+scheme.getBrokeragemoney().doubleValue());
									scorerecord.setObankscore(new BigDecimal(scorerecord.getObankscore().doubleValue()+scheme.getBrokeragemoney().doubleValue()));
								}
								
							}
							scorerecord.setBalance(betMember.getScore());
							scorerecord.setState(1);
							scorerecord.setType(24);			
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
								if("A101".equals(betAgent.getParentid())){
									
								}else{
									betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
								}
								
								if(scheme.getBrokerageagentmoney()!=null){
									betAgentService.update(new Finder("update bet_agent set score = IFNULL(score,0)-:brokerageagentmoney,brokeragemoney = IFNULL(brokeragemoney,0)-:brokerageagentmoney where agentid = :agentid ").setParam("brokerageagentmoney", scheme.getBrokerageagentmoney()).setParam("agentid", betAgent.getAgentid()));
								}
							}
							List<String> fids = bjdcSchemeMatchService.queryForList(new Finder("select fid from bjdc_scheme_match where schemeid = :schemeid group by schemeid").setParam("schemeid", scheme.getSchemeid()), String.class);
							List<BjdcResult> datas=bjdcResultService.queryForList(new Finder("select * from bjdc_result  where  state = 1 and fid in (:fid) ").setParam("fid", fids), BjdcResult.class);
							Map<String,String> map = new HashMap<String, String>();
							List<BjdcPlaymethodOddsname> bjdcPlaymethodOddsnameList = bjdcPlaymethodOddsnameService.queryForList(new Finder("select oddsname,shortname from bjdc_playmethod_oddsname "), BjdcPlaymethodOddsname.class);
							for (BjdcPlaymethodOddsname bjdcPlaymethodOddsname : bjdcPlaymethodOddsnameList) {
								map.put(bjdcPlaymethodOddsname.getOddsname(), bjdcPlaymethodOddsname.getShortname());
							}
							if(datas!=null&&!datas.isEmpty()){
								for(BjdcResult bjdcResult : datas){
									String letpoints = bjdcResult.getLetpoints();
									String[] halfscore = bjdcResult.getHalfscore().split(":");
									String[] allscore = bjdcResult.getAllscore().split(":");
									String lefthalfscore = halfscore[0];
									String righthalfscore = halfscore[1];
									String leftallscore = allscore[0];
									String rightallscore = allscore[1];
									//胜平负
									String spf = "";
									if(Integer.valueOf(leftallscore)+Integer.valueOf(letpoints) > Integer.valueOf(rightallscore)){
										spf = "rqwin";
									}else if(Integer.valueOf(leftallscore)+Integer.valueOf(letpoints) == Integer.valueOf(rightallscore)){
										spf = "rqflat";
									}else{
										spf = "rqlose";
									}
									List<BjdcOrderContent> spfList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and SUBSTRING(oddsname,1,2)=:str AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str", "rq"), BjdcOrderContent.class);
									if(!spfList.isEmpty()){
										for (BjdcOrderContent bjdcOrderContent : spfList) {
											if(spf.equals(bjdcOrderContent.getOddsname())){
												//中
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(spf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
											}else{
												//没
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(spf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
											}
										}
									}
									//进球数
									String jqs = "ball";
									Integer alljqs = Integer.valueOf(leftallscore)+Integer.valueOf(rightallscore);
									if(alljqs>=7){
										jqs = "ball7";
									}else{
										jqs += alljqs;
									}
									List<BjdcOrderContent> ballList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and SUBSTRING(oddsname,1,4)=:str AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str", "ball"), BjdcOrderContent.class);
									if(!ballList.isEmpty()){
										for (BjdcOrderContent bjdcOrderContent : ballList) {
											if(jqs.equals(bjdcOrderContent.getOddsname())){
												//中
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(jqs)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
											}else{
												//没
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(jqs)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
											}
										}
									}
									//上下单双
									String sxds = "";
									if(alljqs==0||alljqs==2){
										sxds = "downdouble";
									}else if(alljqs==1){
										sxds = "downsingle";
									}else if(alljqs%2==1){
										sxds = "topsingle";
									}else{
										sxds = "topdouble";
									}
									List<BjdcOrderContent> sxdsList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and (oddsname=:o1 or oddsname=:o2 or oddsname=:o3 or oddsname=:o4 ) AND result=0").setParam("fid", bjdcResult.getFid()).setParam("o1", "downdouble").setParam("o2", "downsingle").setParam("o3", "topsingle").setParam("o4", "topdouble"), BjdcOrderContent.class);
									if(!sxdsList.isEmpty()){
										for (BjdcOrderContent bjdcOrderContent : sxdsList) {
											if(sxds.equals(bjdcOrderContent.getOddsname())){
												//中
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:o1 ").setParam("settletime", new Date()).setParam("resultname", map.get(sxds)).setParam("fid", bjdcOrderContent.getFid()).setParam("o1", bjdcOrderContent.getOddsname()));
											}else{
												//没
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:o1 ").setParam("settletime", new Date()).setParam("resultname", map.get(sxds)).setParam("fid", bjdcOrderContent.getFid()).setParam("o1", bjdcOrderContent.getOddsname()));
											}
										}
									}
									//半全场
									String bqc = "sp";
									if(Integer.valueOf(lefthalfscore) > Integer.valueOf(righthalfscore)){
										bqc += "3";
									}else if(Integer.valueOf(lefthalfscore) == Integer.valueOf(righthalfscore)){
										bqc += "1";
									}else{
										bqc += "0";
									}
									if(Integer.valueOf(leftallscore) > Integer.valueOf(rightallscore)){
										bqc += "3";
									}else if(Integer.valueOf(leftallscore) == Integer.valueOf(rightallscore)){
										bqc += "1";
									}else{
										bqc += "0";
									}
									List<BjdcOrderContent> bqcList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and SUBSTRING(oddsname,1,2)=:str AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str", "sp"), BjdcOrderContent.class);
									if(!bqcList.isEmpty()){
										for (BjdcOrderContent bjdcOrderContent : bqcList) {
											if(bqc.equals(bjdcOrderContent.getOddsname())){
												//中
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(bqc)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
											}else{
												//没
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str ").setParam("settletime", new Date()).setParam("resultname", map.get(bqc)).setParam("fid", bjdcOrderContent.getFid()).setParam("str", bjdcOrderContent.getOddsname()));
											}
										}
									}
									//比分
									String bf = "";
									if(Integer.valueOf(leftallscore) > Integer.valueOf(rightallscore)){
										bf = "win"+leftallscore+rightallscore;
										if(bjdcPlaymethodOddsnameService.queryForObject(new Finder("select count(*) from bjdc_playmethod_oddsname where oddsname=:oddsname ").setParam("oddsname", bf), Integer.class)==0){
											bf = "win3A";
										}
									}else if(Integer.valueOf(leftallscore) == Integer.valueOf(rightallscore)){
										bf = "flat"+leftallscore+rightallscore;
										if(bjdcPlaymethodOddsnameService.queryForObject(new Finder("select count(*) from bjdc_playmethod_oddsname where oddsname=:oddsname ").setParam("oddsname", bf), Integer.class)==0){
											bf = "flat1A";
										}
									}else{
										bf = "lose"+leftallscore+rightallscore;
										if(bjdcPlaymethodOddsnameService.queryForObject(new Finder("select count(*) from bjdc_playmethod_oddsname where oddsname=:oddsname ").setParam("oddsname", bf), Integer.class)==0){
											bf = "lose0A";
										}
									}
									List<BjdcOrderContent> bfList = bjdcOrderContentService.queryForList(new Finder("SELECT * FROM bjdc_order_content WHERE fid=:fid and (SUBSTRING(oddsname,1,3)=:str1 or SUBSTRING(oddsname,1,4)=:str2 or SUBSTRING(oddsname,1,4)=:str3 ) AND result=0").setParam("fid", bjdcResult.getFid()).setParam("str1", "win").setParam("str2", "flat").setParam("str3", "lose"), BjdcOrderContent.class);
									if(!bfList.isEmpty()){
										for (BjdcOrderContent bjdcOrderContent : bfList) {
											if(bf.equals(bjdcOrderContent.getOddsname())){
												//中
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=1,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str1 ").setParam("settletime", new Date()).setParam("resultname", map.get(bf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str1", bjdcOrderContent.getOddsname()));
											}else{
												//没
												bjdcOrderContentService.update(new Finder("update bjdc_order_content set result=3,settletime=:settletime,resultname=:resultname where fid=:fid and oddsname=:str1 ").setParam("settletime", new Date()).setParam("resultname", map.get(bf)).setParam("fid", bjdcOrderContent.getFid()).setParam("str1", bjdcOrderContent.getOddsname()));
											}
										}
									}
								}
							}
							List<String> schemelist = bjdcSchemeService.queryForList(new Finder("select schemeid from bjdc_scheme where schemeid = :schemeid ").setParam("schemeid", scheme.getSchemeid()), String.class);
							if(schemelist!=null){
								for (String schemeid1 : schemelist) {
									Integer untreatedcontent = bjdcOrderService.queryForObject(new Finder("select count(*) from bjdc_order a right join bjdc_order_content b on b.orderid=a.orderid where a.schemeid=:schemeid and b.result=0 ").setParam("schemeid", schemeid1), Integer.class);
									if(untreatedcontent==0){
										try{
											List<BjdcOrder> orderList = bjdcOrderService.queryForList(new Finder("select * from bjdc_order where state=1 and schemeid=:schemeid ").setParam("schemeid", schemeid1), BjdcOrder.class);
											if((orderList !=null)&&(!orderList.isEmpty())){
												Double schemereward=0.;
												for (BjdcOrder bjdcOrder : orderList) {
													List<BjdcOrderContent>  contentList = bjdcOrderContentService.queryForList(new Finder("select * from bjdc_order_content where orderid=:orderid ").setParam("orderid", bjdcOrder.getOrderid()), BjdcOrderContent.class);
													int count = 0 ;
													if((contentList!=null)&&(!contentList.isEmpty())){
														for (BjdcOrderContent bjdcOrderContent : contentList) {
															if(bjdcOrderContent.getResult()==1){
																count++;
															}
														}
														if(contentList.size() == count){
															//中奖了 返奖
															Double odds = 1.;
															for (BjdcOrderContent bjdcOrderContent : contentList) {
																odds*=bjdcOrderContent.getOdds();
															}
															Double award = odds*(bjdcOrder.getBettingmoney().doubleValue())*Double.valueOf(0.65);
															Double posttaxprice=award;
															schemereward+=posttaxprice;
															Date dddd=new Date();
															bjdcOrderService.update(new Finder("update bjdc_order set result=1,settletime=:settletime,bettingwin=:award,posttaxprize=:posttaxprize where orderid=:orderid  ").setParam("posttaxprize", award).setParam("orderid", bjdcOrder.getOrderid()).setParam("settletime", dddd).setParam("award", posttaxprice));
														}else{
															Date ddddd=new Date();
															bjdcOrderService.update(new Finder("update bjdc_order set result=3,settletime=:settletime,bettingwin=0,posttaxprize=0 where orderid=:orderid  ").setParam("orderid", bjdcOrder.getOrderid()).setParam("settletime", ddddd));
														}
													}
												}
												BjdcScheme bjdcsheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid1), BjdcScheme.class);
												betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", bjdcsheme.getMemberid2()), BetMember.class);
												try {
													if(bjdcsheme.getBuytype()==1){
														//跟单
														BjdcScheme sdsoccersheme = bjdcSchemeService.queryForObject(new Finder("select * from bjdc_scheme where buytype = 2 and schemeid2 = :schemeid2 ").setParam("schemeid2", bjdcsheme.getSchemeid2()), BjdcScheme.class);
														if(sdsoccersheme.getPubstate()!=null&&sdsoccersheme.getPubstate()==2){
															BigDecimal guarantee = sdsoccersheme.getGuarantee();
															BigDecimal guaranteeMoney = null;
															if(guarantee!=null){
																guaranteeMoney = guarantee.multiply(bjdcsheme.getBettingmoney());
															}
															if(guarantee==null||schemereward>guaranteeMoney.doubleValue()){
																BetMember sdmember = betMemberService.queryForObject(new Finder("select a.* from bet_member a right join soccer_scheme b on a.id2 = b.memberid2 where b.buytype = 2 and b.schemeid2 = :schemeid2 limit 1").setParam("schemeid2",bjdcsheme.getSchemeid2() ), BetMember.class);
																
																BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", betMember.getAgentid()), BetAgent.class);
																if("A101".equals(betAgent.getParentid())){
																	
																}else{
																	betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where parentid = :parentid and  locate(CONCAT(:douhao, agentid, :douhao),:parentids)!=0").setParam("douhao", ",").setParam("parentid", "A101").setParam("parentids", betAgent.getParentids()), BetAgent.class);
																}
																BigDecimal brokeragemember = sdsoccersheme.getBrokeragemember(); // 大神佣金比例
																BigDecimal brokerageagent = sdsoccersheme.getBrokerageagent(); // 一级代理佣金比例
																
																if (brokeragemember == null || brokerageagent == null
																		|| brokeragemember.compareTo(BigDecimal.ZERO) <= 0
																		|| brokerageagent.compareTo(new BigDecimal(0)) <= 0) {
																	BetAgentBrokerage agentBrokerage = betAgentBrokerageService.queryForObject(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", betAgent.getAgentid()), BetAgentBrokerage.class);
																	if(agentBrokerage==null){
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
																bjdcsheme.setBrokeragemembermoney(brokeragemoney.multiply(new BigDecimal("-1")));
																//跟单方案代理佣金
																BigDecimal brokerageagentmoney = bettingwin.multiply(brokerageagent);
																bjdcsheme.setBrokerageagentmoney(brokerageagentmoney.multiply(new BigDecimal("-1")));
																bjdcsheme.setBrokerageagentid(betAgent.getAgentid());
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
																bjdcsheme.setBrokeragemoney(sumbrokeragemoney.multiply(new BigDecimal("-1")));
																if(sdsoccersheme.getBrokeragemoney()!=null){
																	sdsoccersheme.setBrokeragemoney(sdsoccersheme.getBrokeragemoney().add(brokeragemoney));
																}else{
																	sdsoccersheme.setBrokeragemoney(brokeragemoney);
																}
																sdmember.setBankscore(sdmember.getBankscore()+ brokeragemoney.doubleValue());
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
																	dd=new Date();
																	scorerecord = new BetScorerecord();
																	scorerecord.setId(null);
																	scorerecord.setMemberid2(sdsoccersheme.getMemberid2());
																	scorerecord.setTime(dd);
																	scorerecord.setContent("重新结算北单佣金");
																	scorerecord.setOriginalscore(sdmember.getScore() - brokeragemoney.doubleValue());
																	scorerecord.setChangescore(brokeragemoney.doubleValue());
																	scorerecord.setBalance(sdmember.getScore());
																	scorerecord.setState(1);
																	scorerecord.setType(21);
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
																	double gsq = new BigDecimal(sdmember.getGamescore() - brokeragemoney.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
																    double gsh = new BigDecimal(sdmember.getGamescore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
																    double bs = new BigDecimal(sdmember.getBankscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
																    double dsq = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
																    double dsh = new BigDecimal(sdmember.getFreezingscore()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
																    scorerecord.setRemark("gq:"+gsq+"gh"+gsh+",b:"+bs+",dq:"+dsq+",dh:"+dsh);
																	betScorerecordService.save(scorerecord);
																	
																	//投注方案
																	bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = :brokeragemoney,brokerageagentmoney = :brokerageagentmoney,brokeragemembermoney = :brokeragemembermoney,brokerageagentid = :brokerageagentid  WHERE schemeid=:schemeid ").setParam("schemeid", schemeid1).setParam("brokeragemoney", bjdcsheme.getBrokeragemoney()).setParam("brokerageagentmoney", bjdcsheme.getBrokerageagentmoney()).setParam("brokeragemembermoney", bjdcsheme.getBrokeragemembermoney()).setParam("brokerageagentid", bjdcsheme.getBrokerageagentid()));
																	bjdcSchemeService.update(new Finder("update bjdc_scheme set brokeragemoney = :brokeragemoney WHERE schemeid=:schemeid ").setParam("schemeid", sdsoccersheme.getSchemeid()).setParam("brokeragemoney", sdsoccersheme.getBrokeragemoney()));
																	
																	
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
														bbb = bjdcSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:bjdc limit 1").setParam("bjdc", "bjdc").setParam("agentid", betMember.getAgentid()),Double.class);
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
														bbb = bjdcSchemeService.queryForObject(new Finder("select plusawardsproportion from lottery_gameplusawardsproportion where agentid=:agentid and lotteryid=:bjdc limit 1").setParam("bjdc", "bjdc").setParam("agentid", firstagentid),Double.class);
													}
												} catch (Exception e) {
													System.out.println(e);
												}
												
												Double plusawards=0.;
												if(bbb!=null){
													plusawards=bbb*schemereward;
												}
												betMember.setBankscore(betMember.getBankscore()+schemereward);
												betMember.setGamescore(betMember.getGamescore()+plusawards);
												betMember.setScore(betMember.getScore() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
												betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
												betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
												
												Integer updatenum=null;
												for(int i=0;i<10;i++){
													if(updatenum==null){
														updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
													}else if(updatenum==0){
														betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
														betMember.setBankscore(betMember.getBankscore()+schemereward);
														betMember.setGamescore(betMember.getGamescore()+plusawards);
														betMember.setScore(betMember.getScore() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
														betMember.setDayscore(betMember.getDayscore()  + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
														betMember.setWinorfail(betMember.getWinorfail() + schemereward+plusawards - bjdcsheme.getBettingmoney().doubleValue());
														updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score ,dayscore=:dayscore ,winorfail=:winorfail,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("dayscore", betMember.getDayscore()).setParam("winorfail", betMember.getWinorfail()).setParam("freezingscore", betMember.getFreezingscore()).setParam("version", betMember.getVersion()));
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
													dd=new Date();
													try {
														scorerecord = new BetScorerecord();
														scorerecord.setId(null);
														scorerecord.setMemberid2(bjdcsheme.getMemberid2());
														scorerecord.setTime(dd);
														scorerecord.setContent("重新结算北单返奖");
														scorerecord.setOriginalscore(betMember.getScore()- schemereward-plusawards + bjdcsheme.getBettingmoney().doubleValue());
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
														scorerecord.setType(20);
														scorerecord.setAgentid(betMember.getAgentid());
														scorerecord.setAgentparentid(betMember.getAgentparentid());
														scorerecord.setAgentparentids(betMember.getAgentparentids());
														betScorerecordService.save(scorerecord);
													} catch (Exception e) {
														System.out.println(e);
													}
													//投注方案
													bjdcSchemeService.update(new Finder("update bjdc_scheme set plusawards=:plusawards,bettingwin=:xxxxk, situation=1,settlementtime=:settlementtime,pretaxamount=:xxxx WHERE schemeid=:schemeid ").setParam("plusawards", plusawards).setParam("settlementtime", dd).setParam("schemeid", schemeid1).setParam("xxxxk", schemereward+plusawards).setParam("xxxx", schemereward));
													//汇总投注
													soccerAllbettingService.update(new Finder("update soccer_allbetting set bettingscore=:bettingscore,state=1,settlementtime=:settlementtime where id=:id  and type=1 ").setParam("bettingscore", schemereward+plusawards).setParam("settlementtime", dd).setParam("id", schemeid1));
													//连红========================================
													if(bjdcsheme.getBuytype()==2){
														List<Double> hong = soccerAllbettingService.queryForList(new Finder("select bettingscore from soccer_allbetting" +
																" where memberid2=:id2 and state=1 and buytype=2 order by bettingtime desc limit 30 ").setParam("id2", bjdcsheme.getMemberid2()), Double.class);
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
														betMemberService.update(new Finder("update bet_member set lianhong =:lianhong where id2=:id2 ").setParam("lianhong", lianhong).setParam("id2", bjdcsheme.getMemberid2()));
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
					
					}
				}
			
			}else{
				//bjdcArrangeService.saveorupdate(bjdcArrange);
				if(bjdcArrange.getId()!=null){
					bjdcArrangeService.update(bjdcArrange, true);
				}else{
					bjdcArrangeService.save(bjdcArrange);
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
		return "/lottery/bjdcarrange/bjdcarrangeCru";
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
				bjdcArrangeService.deleteById(id,BjdcArrange.class);
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
			bjdcArrangeService.deleteByIds(ids,BjdcArrange.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
