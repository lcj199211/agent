package  org.springrain.lottery.web;

import java.io.File;
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

import org.springrain.lottery.entity.BasketballLeagueResult;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.service.IBasketballLeagueResultService;
import org.springrain.lottery.service.IBasketballOrderContentService;
import org.springrain.lottery.service.IBetMemberService;
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
 * @version  2017-11-10 09:23:04
 * @see org.springrain.lottery.web.BasketballLeagueResult
 */
@Controller
@RequestMapping(value="/basketballleagueresult")
public class BasketballLeagueResultController  extends BaseController {
	@Resource
	private IBasketballLeagueResultService basketballLeagueResultService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBasketballOrderContentService basketballOrderContentService;
	
	private String listurl="/lottery/basketballleagueresult/basketballleagueresultList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballLeagueResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BasketballLeagueResult basketballLeagueResult) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		String parentids = ","+agentid+",";
		if("1".equals(request.getParameter("k"))){
			//查询投此场会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String zid = request.getParameter("zid");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 in (select DISTINCT a.memberid2  from  basketball_scheme a LEFT JOIN basketball_scheme_match b on a.schemeid = b.schemeid  where b.zid=:zid) and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", agentid).setParam("agentparentids", "%"+parentids+"%").setParam("zid", zid),BetMember.class,page);
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("zid", zid);
			return  "/lottery/basketballorder/basketballmemberList";
		}else{
			String matchname = null;
			if(basketballLeagueResult.getMatchname()!=null){
				matchname = "%"+basketballLeagueResult.getMatchname()+"%";
			}
			String num = request.getParameter("num");
			if(num!=null){
				num = "%"+num+"%";
			}
			String state = request.getParameter("state");
			if(StringUtils.isEmpty(state)){
				state = "3";
			}
			if("100".equals(state)){
				state = null;
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
			List<BasketballLeagueResult> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas=basketballLeagueResultService.queryForList(new Finder("select * from basketball_league_result where (:matchname is null or matchname like :matchname) and (:num is null or num like :num) and (:state is null or state = :state)").setParam("matchname", matchname).setParam("num", num).setParam("state", state), BasketballLeagueResult.class, page);
				
			}else{
				datas=basketballLeagueResultService.queryForList(new Finder("select * from basketball_league_result where (:matchname is null or matchname like :matchname) and (:num is null or num like :num) and (:state is null or state = :state) and substr(starttime,1,10)>=:starttime and substr(starttime,1,10)<=:endtime").setParam("matchname", matchname).setParam("num", num).setParam("state", state).setParam("starttime",startDate).setParam("endtime", endDate), BasketballLeagueResult.class, page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			
			returnObject.setQueryBean(basketballLeagueResult);
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
	 * @param basketballLeagueResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,BasketballLeagueResult basketballLeagueResult) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BasketballLeagueResult> datas=basketballLeagueResultService.findListDataByFinder(null,page,BasketballLeagueResult.class,basketballLeagueResult);
			returnObject.setQueryBean(basketballLeagueResult);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BasketballLeagueResult basketballLeagueResult) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = basketballLeagueResultService.findDataExportExcel(null,listurl, page,BasketballLeagueResult.class,basketballLeagueResult);
		String fileName="basketballLeagueResult"+GlobalStatic.excelext;
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
		return "/lottery/basketballleagueresult/basketballleagueresultLook";
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
		  BasketballLeagueResult basketballLeagueResult = basketballLeagueResultService.findBasketballLeagueResultById(id);
		  
			String allscore1 = "";
			String allscore2 = "";
			String allscore = basketballLeagueResult.getScore();
			if(allscore!=null&&allscore.contains(":")){
				String[] allscores = allscore.split(":");
				allscore1 = allscores[0];
				allscore2 = allscores[1];
			}
			model.addAttribute("allscore1", allscore1);
			model.addAttribute("allscore2", allscore2);
		  returnObject.setData(basketballLeagueResult);
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
	public ReturnDatas saveorupdate(Model model,BasketballLeagueResult basketballLeagueResult,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String allscore1 = null;
			String allscore2 = null;
			if(request.getParameter("allscore1")!=null){
				allscore1 = request.getParameter("allscore1").trim();
			}
			if(request.getParameter("allscore2")!=null){
				allscore2 = request.getParameter("allscore2").trim();
			}
			if(allscore1!=null&&allscore2!=null){
				basketballLeagueResult.setScore(allscore1+":"+allscore2);
			}
			
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				basketballLeagueResultService.update(new Finder("update basketball_league_result set state=:state where id=:id").setParam("state",state).setParam("id", id));
			}else if("2".equals(request.getParameter("k"))){
				//冻结、解冻
				String  id = request.getParameter("id");
				String  status = request.getParameter("status");
				String zid = basketballLeagueResultService.queryForObject(new Finder("select zid from basketball_league_result where id=:id ").setParam("id", id),String.class);
				if(zid == null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("场次不存在");
				}
				List<String> id2s = basketballOrderContentService.queryForList(new Finder("select DISTINCT memberid2 from basketball_scheme left join basketball_order_content on basketball_scheme.schemeid=basketball_order_content.schemeid where zid=:zid order by basketball_scheme.id ").setParam("zid", zid), String.class);
				if(!id2s.isEmpty()){
					betMemberService.update(new Finder("update bet_member set status=:status where id2 in (:id2s) ").setParam("status", status).setParam("id2s", id2s));
				}
			}else{
				String starttime = basketballLeagueResultService.queryForObject(new Finder("select starttime from basketball_league_arrange where zid=:zid ").setParam("zid",basketballLeagueResult.getZid()),String.class);
				if(new SimpleDateFormat("yyyy-MM-dd").parse(starttime).before(new Date())){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("比賽未開始");
				}
				if(basketballLeagueResult.getId()!=null){
					basketballLeagueResultService.update(basketballLeagueResult, true);
				}else{
					basketballLeagueResultService.save(basketballLeagueResult);
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
		return "/lottery/basketballleagueresult/basketballleagueresultCru";
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
				basketballLeagueResultService.deleteById(id,BasketballLeagueResult.class);
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
			basketballLeagueResultService.deleteByIds(ids,BasketballLeagueResult.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
	}

}
