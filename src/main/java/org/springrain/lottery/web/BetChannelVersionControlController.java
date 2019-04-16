package org.springrain.lottery.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springrain.lottery.entity.BetChannelVersionControl;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetChannelVersionControlService;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;

/**
 * 版本控制栏目 - Controller
 * 
 * @copyright {@link weicms.net}
 * @author qiang
 * @version 2019-03-13 18:25:56
 * @see org.springrain.news.web.BetChannelVersionControl
 */
@Controller
@RequestMapping(value = "/betchannelversioncontrol")
public class BetChannelVersionControlController extends BaseController {
	@Resource
	private IBetChannelVersionControlService betChannelVersionControlService;

	private String listurl = "/lottery/betchannelversioncontrol/betchannelversioncontrolList";

	@Resource
	private IBetAgentService betAgentService;

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
	 * @param betChannelVersionControl
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, BetChannelVersionControl betChannelVersionControl)
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betChannelVersionControl);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}

	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betChannelVersionControl
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody ReturnDatas listjson(HttpServletRequest request, Model model,
			BetChannelVersionControl betChannelVersionControl) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String order = page.getOrder();
		if (StringUtils.isNotBlank(order)) {
			page.setOrder(order.replaceAll("-", "_"));
		}
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
		List<BetChannelVersionControl> datas = betChannelVersionControlService.findListDataByFinder(new Finder("select * from bet_channel_version_control where company = :company").setParam("company", company), page,
				BetChannelVersionControl.class, betChannelVersionControl);
		if (StringUtils.isNotBlank(order)) {
			page.setOrder(order.replaceAll("_", "-"));
		}
		returnObject.setQueryBean(betChannelVersionControl);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request, HttpServletResponse response, Model model,
			BetChannelVersionControl betChannelVersionControl) throws Exception {
		// ==构造分页请求
		Page page = newPage(request);

		File file = betChannelVersionControlService.findDataExportExcel(null, listurl, page,
				BetChannelVersionControl.class, betChannelVersionControl);
		String fileName = "betChannelVersionControl" + GlobalStatic.excelext;
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
		return "/lottery/betchannelversioncontrol/betchannelversioncontrolLook";
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
			BetChannelVersionControl betChannelVersionControl = betChannelVersionControlService
					.findBetChannelVersionControlById(id);
			returnObject.setData(betChannelVersionControl);
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
	public @ResponseBody ReturnDatas saveorupdate(Model model, BetChannelVersionControl betChannelVersionControl,
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

			betChannelVersionControl.setCompany(company);
			betChannelVersionControlService.saveorupdate(betChannelVersionControl);
			// 添加或修改栏目、删除redis
			String versioncontrol = "";
			if (betChannelVersionControl.getVersion_status() == 1) {
				versioncontrol = "A";
			} else if (betChannelVersionControl.getVersion_status() == 2) {
				versioncontrol = "B";
			}
			if (StringUtils.isNoneBlank(versioncontrol)) {
				// A、B版只删除对应的缓存
				String rkey = "betChannelVersionControls_".concat(company).concat("_").concat(versioncontrol)
						.concat("_1"); // "betChannelVersionControls_)+代理商名称_+版本号_+开启状态
				cached.deleteCached(rkey.getBytes());
			} else {
				// 公共版删除所有缓存
				Set<byte[]> keys = cached.getKeyList("betChannelVersionControls_*".getBytes());
				if (keys != null && !keys.isEmpty()) {
					for (byte[] key : keys) {
						cached.deleteCached(key);
					}
				}
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
		return "/lottery/betchannelversioncontrol/betchannelversioncontrolCru";
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
				betChannelVersionControlService.deleteById(id, BetChannelVersionControl.class);
				// 删除所有栏目缓存，可以改为删除单个，但是需要获取栏目、代理的信息
				Set<byte[]> keys = cached.getKeyList("betChannelVersionControls_*".getBytes());
				if (keys != null && !keys.isEmpty()) {
					for (byte[] key : keys) {
						cached.deleteCached(key);
					}
				}
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
			betChannelVersionControlService.deleteByIds(ids, BetChannelVersionControl.class);
			// 删除所有栏目缓存，可以改为删除单个，但是需要获取栏目、代理的信息
			Set<byte[]> keys = cached.getKeyList("betChannelVersionControls_*".getBytes());
			if (keys != null && !keys.isEmpty()) {
				for (byte[] key : keys) {
					cached.deleteCached(key);
				}
			}
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
