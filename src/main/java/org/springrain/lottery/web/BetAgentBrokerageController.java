package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
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

import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.lottery.service.IBetAgentBrokerageService;
import org.springrain.lottery.service.IBetAgentService;
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
 * @version  2017-12-14 09:50:03
 * @see org.springrain.lottery.web.BetAgentBrokerage
 */
@Controller
@RequestMapping(value="/betagentbrokerage")
public class BetAgentBrokerageController  extends BaseController {
	@Resource
	private IBetAgentBrokerageService betAgentBrokerageService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betagentbrokerage/betagentbrokerageList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAgentBrokerage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetAgentBrokerage betAgentBrokerage) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid = :agentid").setParam("agentid", agentid), BetAgent.class);
		List<BetAgentBrokerage> datas=betAgentBrokerageService.queryForList(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", agentid), BetAgentBrokerage.class);
		if((datas==null||datas.isEmpty())&&"A101".equals(betAgent.getParentid())){
			BetAgentBrokerage brokerage = new BetAgentBrokerage();
			brokerage.setAgentid(betAgent.getAgentid());
			brokerage.setAgentparentid(betAgent.getParentid());
			brokerage.setAgentparentids(betAgent.getParentids());
			brokerage.setBrokerageagent(new BigDecimal(0.02));
			brokerage.setBrokeragemember(new BigDecimal(0.08));
			betAgentBrokerageService.save(brokerage);
			datas=betAgentBrokerageService.queryForList(new Finder("select * from bet_agent_brokerage where agentid = :agentid").setParam("agentid", agentid), BetAgentBrokerage.class);
		}
		returnObject.setQueryBean(betAgentBrokerage);
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
	 * @param betAgentBrokerage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetAgentBrokerage betAgentBrokerage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String agentid = SessionUser.getShiroUser().getAgentid();
		betAgentBrokerage.setAgentid(agentid);
		List<BetAgentBrokerage> datas=betAgentBrokerageService.findListDataByFinder(null,page,BetAgentBrokerage.class,betAgentBrokerage);
		returnObject.setQueryBean(betAgentBrokerage);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetAgentBrokerage betAgentBrokerage) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betAgentBrokerageService.findDataExportExcel(null,listurl, page,BetAgentBrokerage.class,betAgentBrokerage);
		String fileName="betAgentBrokerage"+GlobalStatic.excelext;
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
		return "/lottery/betagentbrokerage/betagentbrokerageLook";
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
		  BetAgentBrokerage betAgentBrokerage = betAgentBrokerageService.findBetAgentBrokerageById(id);
		   returnObject.setData(betAgentBrokerage);
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
	ReturnDatas saveorupdate(Model model,BetAgentBrokerage betAgentBrokerage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			betAgentBrokerage.setBrokerageagent(betAgentBrokerage.getBrokerageagent().divide(new BigDecimal(100)));
			betAgentBrokerage.setBrokeragemember(betAgentBrokerage.getBrokeragemember().divide(new BigDecimal(100)));
			if(betAgentBrokerage.getId()!=null){
				betAgentBrokerageService.update(betAgentBrokerage,true);
			}else{
				betAgentBrokerageService.save(betAgentBrokerage);
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
		return "/lottery/betagentbrokerage/betagentbrokerageCru";
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
				betAgentBrokerageService.deleteById(id,BetAgentBrokerage.class);
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
			betAgentBrokerageService.deleteByIds(ids,BetAgentBrokerage.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
