package org.springrain.lottery.web;

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
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAreaVersioncontrol;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAreaVersioncontrolService;

/**
 * 地域栏目控制
 * 
 * @version 2019-04-02 19:18:09
 */
@Controller
@RequestMapping(value = "/betareaversioncontrol")
public class BetAreaVersioncontrolController extends BaseController {
	@Resource
	private IBetAreaVersioncontrolService betAreaVersioncontrolService;

	private String listurl = "/lottery/betareaversioncontrol/betareaversioncontrolList";

	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private ICached cached;
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAreaVersioncontrol
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, BetAreaVersioncontrol betAreaVersioncontrol)
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betAreaVersioncontrol);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betAreaVersioncontrol
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody ReturnDatas listjson(HttpServletRequest request, Model model,
			BetAreaVersioncontrol betAreaVersioncontrol) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String agentid = SessionUser.getShiroUser().getAgentid();
		String company = "";
		BetAgent agent = betAgentService.queryForObject(
				new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),
				BetAgent.class);
		if (agent != null) { //
			if ("A101".equals(agent.getParentid())) {
				company = agent.getAgentid();
			} else {
				String[] spilt = agent.getParentids().split(",");// ,A101,agent1,agent2
				company = spilt[2];
			}
		}
		List<BetAreaVersioncontrol> datas = betAreaVersioncontrolService.findListDataByFinder(new Finder("select * from bet_area_versioncontrol where company = :company").setParam("company", company), page,
				BetAreaVersioncontrol.class, betAreaVersioncontrol);
		returnObject.setQueryBean(betAreaVersioncontrol);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model,
			BetAreaVersioncontrol betAreaVersioncontrol) throws Exception {
		// ==构造分页请求
		Page page = newPage(request);

		File file = betAreaVersioncontrolService.findDataExportExcel(null, listurl, page, BetAreaVersioncontrol.class,
				betAreaVersioncontrol);
		String fileName = "betAreaVersioncontrol" + GlobalStatic.excelext;
		downFile(response, file, fileName, true);
		return;
	}

	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betareaversioncontrol/betareaversioncontrolLook";
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody ReturnDatas lookjson(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			BetAreaVersioncontrol betAreaVersioncontrol = betAreaVersioncontrolService
					.findBetAreaVersioncontrolById(id);
			returnObject.setData(betAreaVersioncontrol);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;

	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody ReturnDatas saveorupdate(Model model, BetAreaVersioncontrol betAreaVersioncontrol,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid = SessionUser.getShiroUser().getAgentid();
		String company = "";
		BetAgent agent = betAgentService.queryForObject(
				new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),
				BetAgent.class);
		if (agent != null) { //
			if ("A101".equals(agent.getParentid())) {
				company = agent.getAgentid();
			} else {
				String[] spilt = agent.getParentids().split(",");// ,A101,agent1,agent2
				company = spilt[2];
			}
		}
		try {
			java.lang.String id = betAreaVersioncontrol.getId();
			betAreaVersioncontrol.setCompany(company);
			String areaname = betAreaVersioncontrol.getAreanames();
			if (StringUtils.isBlank(areaname)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR + "，地区名称不能为空");
				return returnObject;
			}
			areaname = areaname.replaceAll("省", "");
			areaname = areaname.replaceAll("市", "");
			areaname = areaname.replaceAll("区", "");
			if (StringUtils.isBlank(areaname)) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR + "，地区名称有误，请去掉省，市，区");
				return returnObject;
			}
			betAreaVersioncontrol.setAreanames(areaname);
			if (StringUtils.isBlank(id)) {
				betAreaVersioncontrol.setCreatedate(new Date());
				betAreaVersioncontrol.setId(null);
				betAreaVersioncontrolService.save(betAreaVersioncontrol);
			} else {
				betAreaVersioncontrol.setModifydate(new Date());
				betAreaVersioncontrolService.update(betAreaVersioncontrol, true);
			}

		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage, e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;

	}

	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betareaversioncontrol/betareaversioncontrolCru";
	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

		// 执行删除
		try {
			java.lang.String id = request.getParameter("id");
			if (StringUtils.isNotBlank(id)) {
				betAreaVersioncontrolService.deleteById(id, BetAreaVersioncontrol.class);
				return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
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
	public @ResponseBody ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if (StringUtils.isBlank(records)) {
			return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			betAreaVersioncontrolService.deleteByIds(ids, BetAreaVersioncontrol.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

	}

}
