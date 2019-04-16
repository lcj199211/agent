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

import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.RenjiuResult;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IRenjiuResultService;
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
 * @version  2018-03-09 11:07:04
 * @see org.springrain.lottery.web.RenjiuResult
 */
@Controller
@RequestMapping(value="/renjiuresult")
public class RenjiuResultController  extends BaseController {
	@Resource
	private IRenjiuResultService renjiuResultService;
	@Resource
	private IBetMemberService betMemberService;
	
	private String listurl="/lottery/renjiuresult/renjiuresultList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param renjiuResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,RenjiuResult renjiuResult)     
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//查询投此场会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String periodnum = request.getParameter("periodnum");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member where id2 in (select DISTINCT memberid2 from renjiu_scheme where periodnum=:periodnum) ").setParam("periodnum", periodnum),BetMember.class,page);
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("periodnum", periodnum);
			return  "/lottery/renjiuscheme/renjiumemberList";		
		}else{
			Integer state = null;
			String periodnum = null;
			if(!StringUtils.isEmpty(renjiuResult.getPeriodnum())){
				if(renjiuResult.getPeriodnum()!=null){
					periodnum = "%"+renjiuResult.getPeriodnum()+"%";
				}
			}
			if(renjiuResult.getState()!=null){
				if(renjiuResult.getState()==100){
					state = null;
				}else{
					state = renjiuResult.getState();
				}
			}
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			List<RenjiuResult> datas = renjiuResultService.queryForList(new Finder("select * from renjiu_result where (:state is null or state = :state) and  (:periodnum is null or periodnum like :periodnum) order by periodnum DESC").setParam("state",state).setParam("periodnum",periodnum),RenjiuResult.class,page);
			returnObject.setQueryBean(renjiuResult);
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
	 * @param renjiuResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,RenjiuResult renjiuResult) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<RenjiuResult> datas=renjiuResultService.findListDataByFinder(null,page,RenjiuResult.class,renjiuResult);
			returnObject.setQueryBean(renjiuResult);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,RenjiuResult renjiuResult) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = renjiuResultService.findDataExportExcel(null,listurl, page,RenjiuResult.class,renjiuResult);
		String fileName="renjiuResult"+GlobalStatic.excelext;
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
		return "/lottery/renjiuresult/renjiuresultLook";
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
		  RenjiuResult renjiuResult = renjiuResultService.findRenjiuResultById(id);
		   returnObject.setData(renjiuResult);
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
	ReturnDatas saveorupdate(Model model,RenjiuResult renjiuResult,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String periodnum = null;
			String result = null;
			if(request.getParameter("periodnum")!=null){
				periodnum = request.getParameter("periodnum").trim();
			}
			if(request.getParameter("result")!=null){
				result = request.getParameter("result").trim();
			}
			if(periodnum!=null){
				renjiuResult.setPeriodnum(periodnum);
			}
			if(result!=null){
				renjiuResult.setResult(result);
			}
			if("1".equals(request.getParameter("k"))){
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				renjiuResultService.update(new Finder("update renjiu_result set state=:state where id=:id").setParam("state",state).setParam("id", id));
			}else{
				if(renjiuResult.getId()!=null){
					renjiuResultService.update(renjiuResult, true);
				}else{
					renjiuResultService.save(renjiuResult);
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
		return "/lottery/renjiuresult/renjiuresultCru";
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
				renjiuResultService.deleteById(id,RenjiuResult.class);
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
			renjiuResultService.deleteByIds(ids,RenjiuResult.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
