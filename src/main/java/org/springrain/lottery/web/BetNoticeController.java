package org.springrain.lottery.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetNotice;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetNoticeService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-04-11 17:54:51
 * @see org.springrain.lottery.web.BetNotice
 */
@Controller
@RequestMapping(value = "/betnotice")
public class BetNoticeController extends BaseController {
	@Resource
	private IBetNoticeService betNoticeService;
	@Resource
	private IBetAgentService betAgentService;

	private String listurl = "/lottery/betnotice/betnoticeList";
	private String newsurl = "/lottery/betnotice/newsList";
	private String newsurlOnlyNews = "/lottery/betnotice/newsListOnlyNews";

	@Value("${activity}")
	private String activity;
	@Value("${activity2}")
	private String activity2;

	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betNotice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{mark}/list")
	public String list(@PathVariable("mark") String mark, HttpServletRequest request, Model model, BetNotice betNotice)
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agent = betAgentService.queryForObject(
				new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),
				BetAgent.class);
		String company = "";
		if ("A101".equals(agent.getParentid())) {
			company = agentid;
		} else {
			company = agent.getParentids().split(",")[2];
		}
		if (mark == null || "notice".equals(mark)) {
			mark = "notice";
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(30);
			// ==执行分页查询
			List<BetNotice> datas = betNoticeService.findListDataByFinder(
					new Finder("select *from bet_notice where mark=:BetNotice_mark and agentid=:BetNotice_agentid ")
							.setParam("BetNotice_mark", mark).setParam("BetNotice_agentid", company),
					page, BetNotice.class, betNotice);
			returnObject.setQueryBean(betNotice);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		} else if ("news".equals(mark)) {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(30);
			// ==执行分页查询
			List<BetNotice> datas = betNoticeService.findListDataByFinder(
					new Finder("select *from bet_notice where mark=:BetNotice_mark and agentid=:BetNotice_agentid ")
							.setParam("BetNotice_mark", mark).setParam("BetNotice_agentid", company),
					page, BetNotice.class, betNotice);
			returnObject.setQueryBean(betNotice);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("contentremark", mark);
			return newsurlOnlyNews;
		} else if ("rule".equals(mark) || "help".equals(mark) || "page".equals(mark)) {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setPageSize(30);
			// ==执行分页查询
			List<BetNotice> datas = betNoticeService.findListDataByFinder(
					new Finder("select *from bet_notice where mark=:BetNotice_mark and agentid=:BetNotice_agentid ")
							.setParam("BetNotice_mark", mark).setParam("BetNotice_agentid", company),
					page, BetNotice.class, betNotice);
			returnObject.setQueryBean(betNotice);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("contentremark", mark);
			return newsurl;
		}
		return null;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betNotice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody ReturnDatas listjson(HttpServletRequest request, Model model, BetNotice betNotice, String mark,
			String agentid) throws Exception {

		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(30);
		// ==执行分页查询
		List<BetNotice> datas = betNoticeService.findListDataByFinder(
				new Finder("select *from bet_notice where mark=:BetNotice_mark and agentid=:company ")
						.setParam("BetNotice_mark", mark).setParam("BetNotice_agentid", agentid),
				page, BetNotice.class, betNotice);
		returnObject.setQueryBean(betNotice);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, BetNotice betNotice)
			throws Exception {
		// ==构造分页请求
		Page page = newPage(request);

		File file = betNoticeService.findDataExportExcel(null, listurl, page, BetNotice.class, betNotice);
		String fileName = "betNotice" + GlobalStatic.excelext;
		downFile(response, file, fileName, true);
		return;
	}

	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/{mark}/look")
	public String look(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betnotice/betnoticeLook";
	}

	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody ReturnDatas lookjson(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String strId = request.getParameter("id");
		java.lang.Integer id = null;
		if (StringUtils.isNotBlank(strId)) {
			id = java.lang.Integer.valueOf(strId.trim());
			BetNotice betNotice = betNoticeService.findBetNoticeById(id);
			returnObject.setData(betNotice);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;

	}

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/{mark}/update")
	public @ResponseBody ReturnDatas saveorupdate(@PathVariable("mark") String mark, Model model, BetNotice betNotice,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agent = betAgentService.queryForObject(
				new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid),
				BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String state = request.getParameter("state");
			String id = request.getParameter("id");
			if (StringUtils.isBlank(id)) {
				betNotice.setId(null);
			} else if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(state)) {
				betNotice.setState(Integer.parseInt(state));
				betNotice.setMark(betNoticeService.findById(betNotice.getId(), BetNotice.class).getMark());
				betNoticeService.update(betNotice, true);
				return returnObject;
			}
			betNotice.setTime(new Date());
			betNotice.setAgentid(agentid);
			betNotice.setAgentparentid(agent.getParentid());
			betNotice.setAgentparentids(agent.getParentids());
			betNoticeService.saveorupdate(betNotice);

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
	@RequestMapping(value = "/{mark}/update/pre")
	public String updatepre(@PathVariable("mark") String mark, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (mark == null || "notice".equals(mark)) {
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betnotice/betnoticeCru";
		} else if ("news".equals(mark)) {
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("contentremark", mark);
			return"/lottery/betnotice/newsCruOnlyNews";
		} else {
			ReturnDatas returnObject = lookjson(model, request, response);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("contentremark", mark);
			return "/lottery/betnotice/newsCru";
		}
	}

	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

		// 执行删除
		try {
			String strId = request.getParameter("id");
			java.lang.Integer id = null;
			if (StringUtils.isNotBlank(strId)) {
				id = java.lang.Integer.valueOf(strId.trim());
				betNoticeService.deleteById(id, BetNotice.class);
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
			betNoticeService.deleteByIds(ids, BetNotice.class);
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
