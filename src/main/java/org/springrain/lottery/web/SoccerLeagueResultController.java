package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.SoccerLeague;
import org.springrain.lottery.entity.SoccerLeagueResult;
import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.ISoccerLeagueOrderContentService;
import org.springrain.lottery.service.ISoccerLeagueResultService;
import org.springrain.lottery.service.ISoccerLeagueService;
import org.springrain.lottery.service.ISoccerSchemeService;
import org.springrain.frame.common.SessionUser;
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
 * @version  2017-08-24 16:23:56
 * @see org.springrain.lottery.web.SoccerLeagueResult
 */
@Controller
@RequestMapping(value="/soccerleagueresult")
public class SoccerLeagueResultController  extends BaseController {
	@Resource
	private ISoccerLeagueResultService soccerLeagueResultService;
	@Resource
	private ISoccerLeagueService soccerLeagueService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ISoccerSchemeService soccerSchemeService;
	@Resource
	private ISoccerLeagueOrderContentService soccerLeagueOrderContentService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	
	private String listurl="/lottery/soccerleagueresult/soccerleagueresultList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param soccerLeagueResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SoccerLeagueResult soccerLeagueResult) 
			throws Exception {
		String pagentid = SessionUser.getShiroUser().getAgentid();
		String agentparentids = ","+pagentid+",";
		if("1".equals(request.getParameter("k"))){
			//查询投此场会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String mid = request.getParameter("mid");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 in (select DISTINCT a.memberid2  from  soccer_scheme a LEFT JOIN soccer_scheme_match b on a.schemeid = b.schemeid  where b.mid=:mid and (a.agentid = :agentid or a.agentparentids like :agentparentids)) ").setParam("agentid", pagentid).setParam("agentparentids", "%"+agentparentids+"%").setParam("mid", mid),BetMember.class,page);
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("mid", mid);
			return  "/lottery/soccerleagueorder/soccermemberList";
		
		}else{
			if(soccerLeagueResult.getState()==null){
				soccerLeagueResult.setState(3);
			}else if(soccerLeagueResult.getState()==0){
				soccerLeagueResult.setState(null);
			}
			String name = null;
			if(soccerLeagueResult.getName()!=null){
				name = "%"+soccerLeagueResult.getName()+"%";
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
			List<SoccerLeagueResult> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas=soccerLeagueResultService.findListDataByFinder(new Finder("select a.*,b.name from soccer_league_result a left join soccer_league b on a.arrangeid2 = b.id2 where (:state is null or a.state = :state) and (:name is null or b.name like :name)").setParam("state",soccerLeagueResult.getState()).setParam("name",name ),page,SoccerLeagueResult.class,null);
			}else{
				datas=soccerLeagueResultService.findListDataByFinder(new Finder("select a.*,b.name from soccer_league_result a left join soccer_league b on a.arrangeid2 = b.id2 where (:state is null or a.state = :state) and (:name is null or b.name like :name) and substr(a.matchtime,1,10)>=:starttime and substr(a.matchtime,1,10)<=:endtime").setParam("state",soccerLeagueResult.getState() ).setParam("name", name).setParam("starttime",startDate).setParam("endtime", endDate),page,SoccerLeagueResult.class,null);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			returnObject.setQueryBean(soccerLeagueResult);
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
	 * @param soccerLeagueResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,SoccerLeagueResult soccerLeagueResult) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<SoccerLeagueResult> datas=soccerLeagueResultService.findListDataByFinder(null,page,SoccerLeagueResult.class,soccerLeagueResult);
		returnObject.setQueryBean(soccerLeagueResult);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SoccerLeagueResult soccerLeagueResult) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = soccerLeagueResultService.findDataExportExcel(null,listurl, page,SoccerLeagueResult.class,soccerLeagueResult);
		String fileName="soccerLeagueResult"+GlobalStatic.excelext;
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
		return "/lottery/soccerleagueresult/soccerleagueresultLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if("1".equals(request.getParameter("k"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			List<SoccerLeague> datas = soccerLeagueService.queryForList(new Finder("select a.name from soccer_league a left join soccer_league_result b on a.id2 = b.arrangeid2 where a.id2 = b.arrangeid2 group by a.id2 order by a.id asc"), SoccerLeague.class);
			returnObject.setData(datas);
			return returnObject;
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			  String  strId=request.getParameter("id");
			  java.lang.Integer id=null;
			  if(StringUtils.isNotBlank(strId)){
				 id= java.lang.Integer.valueOf(strId.trim());
			  SoccerLeagueResult soccerLeagueResult = soccerLeagueResultService.findSoccerLeagueResultById(id);
				String halfscore1 = "";
				String halfscore2 = "";
				String allscore1 = "";
				String allscore2 = "";
				String halfscore = soccerLeagueResult.getHalfscore();
				String allscore = soccerLeagueResult.getAllscore();
				if(halfscore!=null){
					String[] halfscores = halfscore.split(":");
					if(halfscores.length>=2){
						halfscore1 = halfscores[0];
						halfscore2 = halfscores[1];
					}
				}
				if(allscore!=null){
					String[] allscores = allscore.split(":");
					if(allscores.length>=2){
						allscore1 = allscores[0];
						allscore2 = allscores[1];
					}
				}
				model.addAttribute("halfscore1", halfscore1);
				model.addAttribute("halfscore2", halfscore2);
				model.addAttribute("allscore1", allscore1);
				model.addAttribute("allscore2", allscore2);
				returnObject.setData(soccerLeagueResult);
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
	@ResponseBody      
	public ReturnDatas saveorupdate(Model model,SoccerLeagueResult soccerLeagueResult,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String halfscore1 = null;
			String halfscore2 = null;
			String allscore1 = null;
			String allscore2 = null;
			if(request.getParameter("halfscore1")!=null){
				halfscore1 = request.getParameter("halfscore1").trim();
			}
			if(request.getParameter("halfscore2")!=null){
				halfscore2 = request.getParameter("halfscore2").trim();
			}
			if(request.getParameter("allscore1")!=null){
				allscore1 = request.getParameter("allscore1").trim();
			}
			if(request.getParameter("allscore2")!=null){
				allscore2 = request.getParameter("allscore2").trim();
			}
			if(halfscore1!=null&&halfscore2!=null){
				soccerLeagueResult.setHalfscore(halfscore1+":"+halfscore2);
			}
			if(allscore1!=null&&allscore2!=null){
				soccerLeagueResult.setAllscore(allscore1+":"+allscore2);
			}
			
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				soccerLeagueResultService.update(new Finder("update soccer_league_result set state=:state where id=:id").setParam("state",state).setParam("id", id));
				//更新实况表
				if(state!=null&&state.equals("1")){
					SoccerLeagueResult soccerResult = soccerLeagueResultService.queryForObject(new Finder("select * from soccer_league_result where id=:id ").setParam("id", id),SoccerLeagueResult.class);
					String midlive = soccerResult.getMid();
					String allscorelive = soccerResult.getAllscore();
					String halfscorelive = soccerResult.getHalfscore();				
					soccerLeagueResultService.update(new Finder("update soccer_live set time=90,state=1,realscore=:realscore,halfscore=:halfscore where mid=:mid").setParam("realscore",allscorelive).setParam("halfscore",halfscorelive).setParam("mid", midlive));				
				}
			}else if("2".equals(request.getParameter("k"))){
				//冻结、解冻
				String  id = request.getParameter("id");
				String  status = request.getParameter("status");
				String mid = soccerLeagueResultService.queryForObject(new Finder("select mid from soccer_league_result where id=:id ").setParam("id", id),String.class);
				if(mid == null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("场次不存在");
				}
				List<String> id2s = soccerLeagueOrderContentService.queryForList(new Finder("select DISTINCT memberid2 from soccer_scheme left join soccer_league_order_content on soccer_scheme.schemeid=soccer_league_order_content.schemeid where mid=:mid  order by soccer_scheme.id ").setParam("mid", mid), String.class);
				if(!id2s.isEmpty()){
					betMemberService.update(new Finder("update bet_member set status=:status where id2 in (:id2s) ").setParam("status", status).setParam("id2s", id2s));
				}
			}else if("3".equals(request.getParameter("k"))){
				//重新结算所有方案
				String mid2=request.getParameter("mid");
				if(mid2==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("场次不存在");
				}
				List<String> soccerschemedatas = soccerSchemeService.queryForList(new Finder("select DISTINCT schemeid from soccer_league_order_content where mid=:mid").setParam("mid", mid2), String.class);
				if(soccerschemedatas!=null){
					for(String schemeid : soccerschemedatas){
						//重新结算
						SoccerScheme scheme = null;
						scheme = soccerSchemeService.queryForObject(new Finder("select * from soccer_scheme where schemeid=:schemeid ").setParam("schemeid", schemeid), SoccerScheme.class);
						
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
								soccerSchemeService.update(new Finder("update soccer_scheme set brokeragemoney = 0 where schemeid = :schemeid").setParam("schemeid", scheme.getSchemeid()));
							}
							betMemberService.update(new Finder("update bet_member set gamescore=:gamescore,bankscore=:bankscore, score=:score where id2=:id2 ").setParam("bankscore", betMember.getBankscore()).setParam("gamescore", betMember.getGamescore()).setParam("id2", betMember.getId2()).setParam("score", betMember.getScore()));
							
							Date dd=new Date();
							BetScorerecord scorerecord = new BetScorerecord();
							scorerecord.setId(null);
							scorerecord.setMemberid2(scheme.getMemberid2());
							scorerecord.setTime(dd);
							//scorerecord.setContent("用户"+betMember.getId2()+"投注的足彩竞猜方案号为"+schemeid1+"的投注方案支付佣金"+brokeragemoney.setScale(2, BigDecimal.ROUND_HALF_UP)+"元"+"(此方案押"+f2+"返奖"+f1+"元)");
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
					}
					
					soccerSchemeService.update(new Finder("update soccer_scheme set situation=0,bettingwin=null,brokerageagentmoney=null,brokeragemembermoney=null,pretaxamount=null where schemeid in (:schemeids)").setParam("schemeids", soccerschemedatas));
					soccerSchemeService.update(new Finder("update soccer_league_order set result = 0 ,bettingwin=null where schemeid in (:schemeids)").setParam("schemeids", soccerschemedatas));
					soccerSchemeService.update(new Finder("update soccer_league_order_content set result=0,resultname=null where mid=:mid").setParam("mid", mid2));
					soccerSchemeService.update(new Finder("update soccer_league_result set issettle=3 where mid=:mid").setParam("mid", mid2));
					
				}
			}else{
				//soccerLeagueResultService.saveorupdate(soccerLeagueResult);
				String starttime = soccerLeagueResultService.queryForObject(new Finder("select starttime from soccer_league_arrange where mid=:mid ").setParam("mid",soccerLeagueResult.getMid()),String.class);
				if(new SimpleDateFormat("yyyy-MM-dd").parse(starttime).before(new Date())){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("比賽未開始");
				}
				
				if(soccerLeagueResult.getId()!=null){
					soccerLeagueResultService.update(soccerLeagueResult, true);
				}else{
					soccerLeagueResultService.save(soccerLeagueResult);
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
		return "/lottery/soccerleagueresult/soccerleagueresultCru";
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
				soccerLeagueResultService.deleteById(id,SoccerLeagueResult.class);
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
			soccerLeagueResultService.deleteByIds(ids,SoccerLeagueResult.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
