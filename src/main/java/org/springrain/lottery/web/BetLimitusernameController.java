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
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetLimitusername;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetLimitusernameService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-05 13:15:35
 * @see org.springrain.lottery.web.BetLimitusername
 */
@Controller
@RequestMapping(value="/betlimitusername")
public class BetLimitusernameController  extends BaseController {
	@Resource
	private IBetLimitusernameService betLimitusernameService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betlimitusername/betlimitusernameList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betLimitusername
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetLimitusername betLimitusername) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betLimitusername);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betLimitusername
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetLimitusername betLimitusername) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetLimitusername> datas=betLimitusernameService.findListDataByFinder(null,page,BetLimitusername.class,betLimitusername);
			returnObject.setQueryBean(betLimitusername);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetLimitusername betLimitusername) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betLimitusernameService.findDataExportExcel(null,listurl, page,BetLimitusername.class,betLimitusername);
		String fileName="betLimitusername"+GlobalStatic.excelext;
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
		return "/lottery/betlimitusername/betlimitusernameLook";
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
		  BetLimitusername betLimitusername = betLimitusernameService.findBetLimitusernameById(id);
		   returnObject.setData(betLimitusername);
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
	ReturnDatas saveorupdate(Model model,BetLimitusername betLimitusername,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid"), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			betLimitusernameService.saveorupdate(betLimitusername);
			if(betLimitusername.getId()==null){
				//操作日志
				 String details = "";
			     details = "增加限制用户名："+betLimitusername.getNickname();
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
			}else{
				//操作日志
				 String details = "";
				 details = "修改限制用户名："+betLimitusername.getNickname();
			     String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
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
		return "/lottery/betlimitusername/betlimitusernameCru";
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
				betLimitusernameService.deleteById(id,BetLimitusername.class);
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
			betLimitusernameService.deleteByIds(ids,BetLimitusername.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
