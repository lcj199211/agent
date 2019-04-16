package  org.springrain.lottery.web;

import java.io.File;
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

import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetMemberLetter;
import org.springrain.lottery.service.IBetMemberLetterService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-07-10 17:29:40
 * @see org.springrain.lottery.web.BetMemberLetter
 */
@Controller
@RequestMapping(value="/betmemberletter")
public class BetMemberLetterController  extends BaseController {
	@Resource
	private IBetMemberLetterService betMemberLetterService;
	@Resource
	private IBetMemberService betMemberService;
	
	private String listurl="/lottery/betmemberletter/betmemberletterList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betMemberLetter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetMemberLetter betMemberLetter) 
			throws Exception {
//		String agentid = SessionUser.getShiroUser().getAgentid();
//		String agentparentids = ","+agentid+",";
		
		String memberid2 = request.getParameter("id2");
		if(memberid2!=null){
			model.addAttribute("memberid2", memberid2);
		}
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		if(StringUtils.isBlank(type)){
			type=null;
		}
		if(StringUtils.isBlank(state)){
			state=null;
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
		List<BetMemberLetter> datas = null;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			datas = betMemberLetterService.findListDataByFinder(new Finder("select * from bet_member_letter where memberid2=:memberid2 and (:type is null or type=:type) and (:state is null or state=:state)").setParam("memberid2", memberid2).setParam("type", type).setParam("state", state), page, BetMemberLetter.class, null);
		}else{
			datas = betMemberLetterService.findListDataByFinder(new Finder("select * from bet_member_letter where memberid2=:memberid2 and (:type is null or type=:type) and (:state is null or state=:state) and substr(entrytime,1,10)>=:starttime and substr(entrytime,1,10)<=:endtime").setParam("memberid2", memberid2).setParam("type", type).setParam("state", state).setParam("starttime",startDate).setParam("endtime", endDate), page, BetMemberLetter.class, null);
		}
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}
		returnObject.setQueryBean(betMemberLetter);
		returnObject.setPage(page);
		returnObject.setData(datas);	
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betMemberLetter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetMemberLetter betMemberLetter) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetMemberLetter> datas=betMemberLetterService.findListDataByFinder(null,page,BetMemberLetter.class,betMemberLetter);
			returnObject.setQueryBean(betMemberLetter);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetMemberLetter betMemberLetter) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betMemberLetterService.findDataExportExcel(null,listurl, page,BetMemberLetter.class,betMemberLetter);
		String fileName="betMemberLetter"+GlobalStatic.excelext;
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
		return "/lottery/betmemberletter/betmemberletterLook";
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
		  BetMemberLetter betMemberLetter = betMemberLetterService.findBetMemberLetterById(id);
		   returnObject.setData(betMemberLetter);
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
	ReturnDatas saveorupdate(Model model,BetMemberLetter betMemberLetter,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String memberid2 = request.getParameter("id2");
		try {
			if(memberid2!=null){				
				betMemberLetter.setMemberid2(memberid2);
				betMemberLetter.setEntrytime(new Date());
				betMemberLetter.setState(3);
				BetMember betmember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2").setParam("id2", memberid2), BetMember.class);
				betMemberLetter.setAgentid(betmember.getAgentid());
				betMemberLetter.setAgentparentid(betmember.getAgentparentid());
				betMemberLetter.setAgentparentids(betmember.getAgentparentids());
				
				if(betMemberLetter.getId()!=null){
					betMemberLetterService.update(betMemberLetter, true);
				}else{
					betMemberLetterService.save(betMemberLetter);
				}
				//betMemberLetterService.saveorupdate(memberLetter);
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
		String memberid2 = request.getParameter("id2");
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		model.addAttribute("memberid2", memberid2);
		return "/lottery/betmemberletter/betmemberletterCru";
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
				betMemberLetterService.deleteById(id,BetMemberLetter.class);
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
			betMemberLetterService.deleteByIds(ids,BetMemberLetter.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
