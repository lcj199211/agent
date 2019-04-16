package org.springrain.lottery.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentPaymentCode;
import org.springrain.lottery.service.IBetAgentPaymentCodeService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.system.entity.BetAgentData;
import org.springrain.system.service.IBetAgentDataService;
import org.springrain.system.service.IDicDataService;

import com.alibaba.fastjson.JSONArray;

import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;

/**
 * TODO 收款码
 * 
 * @author AA
 * @version 2019-03-19 11:28:46
 * @see org.springrain.news.web.BetAgentPaymentCode
 */
@Controller
@RequestMapping(value = "/betagentpaymentcode")
public class BetAgentPaymentCodeController extends BaseController {
	@Resource
	private IBetAgentPaymentCodeService betAgentPaymentCodeService;

	private String listurl = "/lottery/betagentpaymentcode/betagentpaymentcodeList";

	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IBetAgentDataService betAgentDataService;;
	
	@Value("${activity}")
	private String activity;
	@Value("${activity2}")
	private String activity2;
	@Resource
	private ICached cached;

	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAgentPaymentCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, BetAgentPaymentCode betAgentPaymentCode)
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betAgentPaymentCode);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betAgentPaymentCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody ReturnDatas listjson(HttpServletRequest request, Model model,
			BetAgentPaymentCode betAgentPaymentCode) throws Exception {
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
		List<BetAgentPaymentCode> datas = betAgentPaymentCodeService.findListDataByFinder(new Finder("select * from bet_agent_payment_code where company = :company").setParam("company", company), page,
				BetAgentPaymentCode.class, betAgentPaymentCode);
		returnObject.setQueryBean(betAgentPaymentCode);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model,
			BetAgentPaymentCode betAgentPaymentCode) throws Exception {
		// ==构造分页请求
		Page page = newPage(request);

		File file = betAgentPaymentCodeService.findDataExportExcel(null, listurl, page, BetAgentPaymentCode.class,
				betAgentPaymentCode);
		String fileName = "betAgentPaymentCode" + GlobalStatic.excelext;
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
		return "/lottery/betagentpaymentcode/betagentpaymentcodeLook";
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
			BetAgentPaymentCode betAgentPaymentCode = betAgentPaymentCodeService.findBetAgentPaymentCodeById(id);
			returnObject.setData(betAgentPaymentCode);
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
	public @ResponseBody ReturnDatas saveorupdate(Model model, BetAgentPaymentCode betAgentPaymentCode,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if (betAgentPaymentCode.getAmount() == null) {
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				return returnObject;
			}
			java.lang.String id = betAgentPaymentCode.getId();
			betAgentPaymentCode.setCompany(company);
			betAgentPaymentCodeService.saveorupdate(betAgentPaymentCode);

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
				company = spilt[1];
			}
		}
		BetAgentData betAgentData=betAgentDataService.queryForObject(new Finder("select * from bet_agent_data where company=:company and code=:code ").setParam("company", company).setParam("code", "chongzhi"), BetAgentData.class);
		if (betAgentData != null && StringUtils.isNotBlank(betAgentData.getValue())) {
			if (StringUtils.isNotBlank(betAgentData.getValue())) {
				String scopesStr = betAgentData.getValue().substring(1);
				scopesStr = scopesStr.substring(0, scopesStr.length() - 1);
				String[] scopes = scopesStr.split(",");
				model.addAttribute("scopes", scopes);
			}
		}
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betagentpaymentcode/betagentpaymentcodeCru";
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
				betAgentPaymentCodeService.deleteById(id, BetAgentPaymentCode.class);
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
			betAgentPaymentCodeService.deleteByIds(ids, BetAgentPaymentCode.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);

	}

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	public @ResponseBody ReturnDatas upload(HttpServletRequest request) throws Exception {
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multiRequest.getFileNames();
		List<String> attachmentPathList = new ArrayList<>();
		while (fileNames.hasNext()) {
			MultipartFile file = multiRequest.getFile(fileNames.next());
			// String fileUrl =
			// "/upload/"+SecUtils.getUUID()+file.getOriginalFilename();
			// String filePath = GlobalStatic.rootdir+fileUrl;
			String uuid = SecUtils.getUUID();
			String originalFilename = file.getOriginalFilename();
			// String savePath = request.getSession().getServletContext()
			// .getRealPath("/upload")
			// + File.separator + "activity" + File.separator + uuid +
			// originalFilename;
			String fileUrl = activity + uuid + originalFilename;
			File destFile = new File(fileUrl);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			if (!destFile.exists())
				destFile.createNewFile();
			file.transferTo(destFile);
			attachmentPathList.add(activity2 + uuid + originalFilename);
		}

		returnDatas.setData(attachmentPathList);
		return returnDatas;

	}

	/**
	 * 图片查看
	 * 
	 * @param url
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/img")
	public void getImg(String url, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (url != null) {
				url = new String(url.getBytes("ISO8859-1"), "utf-8");
			}
			String replace = activity.replace(activity2, "");
			if (url == null) {
				return;
			} else {
				if (url.startsWith(activity2)) {
					if (url.indexOf("..") != -1) {
						return;
					} else {

					}
				} else {
					return;
				}
			}

			FileInputStream fis = new FileInputStream(replace + url);

			ServletOutputStream ops = response.getOutputStream();
			int len = -1;
			byte[] datas = new byte[1024 * 100];
			while ((len = fis.read(datas)) != -1) {
				ops.write(datas, 0, len);
			}
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
