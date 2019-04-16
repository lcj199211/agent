package org.springrain.lottery.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetGoldGoods;
import org.springrain.lottery.entity.BetGoldGoodsCategory;
import org.springrain.lottery.service.IBetGoldGoodsCategoryService;
import org.springrain.lottery.service.IBetGoldGoodsService;
import org.springrain.lottery.service.impl.BetGoldGoodsCategoryServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value="/betgoldgoods")
public class BetGoldGoodsController  extends BaseController{
	@Resource
	private IBetGoldGoodsService betGoldGoodsService;
	@Resource
	private IBetGoldGoodsCategoryService betGoldGoodsCategoryService;
	@Value("${server}")
	private String server;
	@Resource
	private ICached cached;
	/**
	 * 
	
	* @Title: upload 
	
	* @Description: TODO 图片上传 
	
	*  @param request
	*  @param betGoldGoods
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/upload")
	public @ResponseBody
	 ReturnDatas upload(HttpServletRequest request) throws Exception{
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Iterator<String> fileNames = multiRequest.getFileNames();
		List<String> attachmentPathList = new ArrayList<>();
		while(fileNames.hasNext()){
			MultipartFile file = multiRequest.getFile(fileNames.next());
			String uuid = SecUtils.getUUID();
			String originalFilename = file.getOriginalFilename();
			String savePath = request.getSession().getServletContext().getRealPath("/upload")
					+ File.separator + "goldgoodsimg" + File.separator + uuid + originalFilename;
			File destFile = new File(savePath);
			if(!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			if(!destFile.exists())
				destFile.createNewFile();
			file.transferTo(destFile);
			attachmentPathList.add(server+"upload/goldgoodsimg/"+uuid+originalFilename);
		}
		returnDatas.setData(attachmentPathList);
		return returnDatas;
	
	}
	
	
	
	/**
	 * 
	
	* @Title: listjson 
	
	* @Description: TODO   充值图片列表查询
	
	*  @param request
	*  @param model
	*  @param betGoldGoods
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public ReturnDatas listjson(HttpServletRequest request, Model model, BetGoldGoods betGoldGoods)
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		betGoldGoods.setCompany(agentid);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetGoldGoods> datas = betGoldGoodsService.findListDataByFinder(null, page, BetGoldGoods.class,
				betGoldGoods);
		returnObject.setQueryBean(betGoldGoods);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	/**
	 * 
	
	* @Title: listjson 
	
	* @Description: TODO   充值优惠列表查询
	
	*  @param request
	*  @param model
	*  @param betGoldGoods
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetGoldGoods betGoldGoods) 
			throws Exception {
		if(betGoldGoods!=null && betGoldGoods.getType()!=null && betGoldGoods.getType()==0) {
			betGoldGoods.setType(null);
		}
		ReturnDatas returnObject = listjson(request, model, betGoldGoods);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgoldgoods/betgoldgoodsList";
	}
	
	/**
	 * 
	
	* @Title: updatepre 
	
	* @Description: TODO 跳转修改页面
	
	*  @param model
	*  @param request
	*  @param response
	*  @return
	*  @throws Exception  
	
	* String    返回类型 
	
	* @throws
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  Map<String, Object> map = new HashMap<>();
		  if(org.apache.commons.lang3.StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BetGoldGoods betGoldGoods = betGoldGoodsService.findBetGoldGoodsById(id);
		  map.put("goods", betGoldGoods);
		  BetGoldGoodsCategory category = betGoldGoodsCategoryService.findById(betGoldGoods.getCategoryid(), BetGoldGoodsCategory.class);
		  if(category!=null) {
			  map.put("categoryname", category.getCategoryName());
		  }
		}
		  Finder finder = new Finder("SELECT id,parentId pId,categoryName name FROM bet_gold_goods_category");
		  List<Map<String, Object>> goodsCategorys = betGoldGoodsCategoryService.queryForList(finder);
		  map.put("category", JSON.toJSONString(goodsCategorys));
		  returnObject.setData(map);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgoldgoods/betgoldgoodsCru";
	}
	/**
	 * 
	
	* @Title: update 
	
	* @Description: TODO  新增/修改充值优惠条件
	
	*  @param model
	*  @param request
	*  @param response
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnDatas update(Model model,BetGoldGoods betGoldGoods,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			betGoldGoods.setCompany(agentid);
			betGoldGoods.setCreatetime(new Date());
			betGoldGoodsService.saveorupdate(betGoldGoods);
			List<BetGoldGoods> datas = betGoldGoodsService.findListDataByFinder(null, null, BetGoldGoods.class,betGoldGoods);
			cached.updateCached(("goldgoodsimgAll"+agentid).getBytes("utf-8"), JSON.toJSONString(datas).getBytes("utf-8"), null);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			e.printStackTrace();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
		
	}
	
	/**
	 * 
	
	* @Title: delete 
	
	* @Description: TODO  删除
	
	*  @param model
	*  @param betGameplay
	*  @param request
	*  @param response
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws  
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnDatas delete(Model model,BetGoldGoods betGoldGoods,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				
				if(!StringUtils.isEmpty(betGoldGoods)){
					betGoldGoodsService.deleteById(betGoldGoods.getId(), BetGoldGoods.class);
					cached.deleteCached(("goldgoodsimgAll*").getBytes("utf-8"));
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					return returnObject;
				}
				
			} catch (Exception e) {
				String errorMessage = e.getLocalizedMessage();
				logger.error(errorMessage,e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
			return returnObject;
		
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ReturnDatas findById(Model model,BetGoldGoods betGoldGoods,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				
				if(!StringUtils.isEmpty(betGoldGoods)){
					betGoldGoodsService.deleteById(betGoldGoods.getId(), BetGoldGoods.class);
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					return returnObject;
				}
				
			} catch (Exception e) {
				String errorMessage = e.getLocalizedMessage();
				logger.error(errorMessage,e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
			return returnObject;
		
	}
}
