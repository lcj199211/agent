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

import org.springrain.lottery.entity.RenjiuMatch;
import org.springrain.lottery.service.IRenjiuMatchService;
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
 * @version  2018-02-23 11:24:47
 * @see org.springrain.lottery.web.RenjiuMatch
 */
@Controller
@RequestMapping(value="/renjiumatch")
public class RenjiuMatchController  extends BaseController {
	@Resource
	private IRenjiuMatchService renjiuMatchService;
	
	private String listurl="/lottery/renjiumatch/renjiumatchList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param renjiuMatch
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,RenjiuMatch renjiuMatch) 
			throws Exception {
		String leaguename = null;
		if(renjiuMatch.getLeaguename()!=null){
			leaguename = "%"+renjiuMatch.getLeaguename()+"%";
		}
		String periodnum = null;
		if(renjiuMatch.getPeriodnum()!=null){
			periodnum = "%"+renjiuMatch.getPeriodnum()+"%";
		}
		String state = null;
		if(renjiuMatch.getState()!=null){
			state = "4";
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
		List<RenjiuMatch> datas = null;
		if(starttime=="0000-01-01" && endtime=="9999-01-01"){
			datas = renjiuMatchService.findListDataByFinder(new Finder("select * from renjiu_match where (:leaguename is null or leaguename like :leaguename) and (:periodnum is null or periodnum like :periodnum) and (:state is null or state = :state)").setParam("leaguename", leaguename).setParam("periodnum", periodnum).setParam("state", state),page,RenjiuMatch.class,null);
		}else{
			datas = renjiuMatchService.findListDataByFinder(new Finder("select * from renjiu_match where (:leaguename is null or leaguename like :leaguename) and (:periodnum is null or periodnum like :periodnum) and (:state is null or state = :state) and substr(starttime,1,10)>=:starttime and substr(starttime,1,10)<=:endtime").setParam("leaguename", leaguename).setParam("periodnum", periodnum).setParam("state", state).setParam("starttime",startDate).setParam("endtime", endDate),page,RenjiuMatch.class,null);
		}
		if(starttime=="0000-01-01"){
			startDate=null;
		}
		if(endtime=="9999-01-01"){
			endDate=null;
		}
		returnObject.setQueryBean(renjiuMatch);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute("startTime", startDate);
		model.addAttribute("endTime", endDate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param renjiuMatch
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,RenjiuMatch renjiuMatch) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<RenjiuMatch> datas=renjiuMatchService.findListDataByFinder(null,page,RenjiuMatch.class,renjiuMatch);
			returnObject.setQueryBean(renjiuMatch);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,RenjiuMatch renjiuMatch) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = renjiuMatchService.findDataExportExcel(null,listurl, page,RenjiuMatch.class,renjiuMatch);
		String fileName="renjiuMatch"+GlobalStatic.excelext;
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
		return "/lottery/renjiumatch/renjiumatchLook";
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
		  RenjiuMatch renjiuMatch = renjiuMatchService.findRenjiuMatchById(id);
		   returnObject.setData(renjiuMatch);
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
	ReturnDatas saveorupdate(Model model,RenjiuMatch renjiuMatch,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				//无效比赛处理
				String rid = request.getParameter("rid");
				RenjiuMatch match = renjiuMatchService.queryForObject(new Finder("select * from renjiu_match where rid = :rid").setParam("rid", rid), RenjiuMatch.class);
				if(match!=null){
					if(match.getStarttime().after(new Date())){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该场次未开赛");
					}else{
						Integer result = match.getResult();
						if(result!=null){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该场次已有结果");
						}else{
							renjiuMatchService.update(new Finder("update renjiu_match set state=4 where rid = :rid").setParam("rid", rid));
						}
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该场次不存在");
				}							
			}else{
				if(renjiuMatch.getId()!=null){
					renjiuMatchService.update(renjiuMatch, true);
				}else{
					renjiuMatchService.save(renjiuMatch);
				}
			}			
			//renjiuMatchService.saveorupdate(renjiuMatch);		
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
		return "/lottery/renjiumatch/renjiumatchCru";
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
				renjiuMatchService.deleteById(id,RenjiuMatch.class);
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
			renjiuMatchService.deleteByIds(ids,RenjiuMatch.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
