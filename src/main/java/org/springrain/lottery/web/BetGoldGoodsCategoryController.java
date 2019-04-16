package org.springrain.lottery.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetGoldGoods;
import org.springrain.lottery.entity.BetGoldGoodsCategory;
import org.springrain.lottery.service.IBetGoldGoodsCategoryService;
import org.springrain.lottery.service.IBetGoldGoodsService;
import org.springrain.lottery.utils.DateUtils;

import com.alibaba.fastjson.JSON;
@Controller
@RequestMapping(value="/betgoldgoodscategory")
public class BetGoldGoodsCategoryController  extends BaseController{
	@Resource
	private IBetGoldGoodsService betGoldGoodsService;
	@Resource
	private IBetGoldGoodsCategoryService betGoldGoodsCategoryService;
	
	
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
	public ReturnDatas listjson(HttpServletRequest request, Model model, BetGoldGoodsCategory betGoldGoodsCategory)
			throws Exception {
//		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		Finder finder = new Finder("SELECT id,parentId pId,categoryName name FROM bet_gold_goods_category");
		  List<Map<String, Object>> goodsCategorys = betGoldGoodsCategoryService.queryForList(finder);
		returnObject.setQueryBean(betGoldGoodsCategory);
		returnObject.setPage(page);
		returnObject.setData(goodsCategorys);
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
	public String list(HttpServletRequest request, Model model,BetGoldGoodsCategory betGoldGoodsCategory) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betGoldGoodsCategory);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgoldgoodscategory/betgoldgoodscategoryList";
	}
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @param model
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/all/json")
	public @ResponseBody
	ReturnDatas listalljson(HttpServletRequest request, Model model, BetGoldGoodsCategory betGoldGoodsCategory) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT id,parentId pid,categoryName name FROM bet_gold_goods_category");
		List<Map<String, Object>> goodsCategorys = betGoldGoodsCategoryService.queryForList(finder);
		returnObject.setQueryBean(betGoldGoodsCategory);
		//returnObject.setPage(page);
		returnObject.setData(goodsCategorys);
		return returnObject;
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
	public ReturnDatas update(Model model,BetGoldGoodsCategory betGoldGoodsCategory,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(betGoldGoodsCategory.getParentId()==null) {
				betGoldGoodsCategory.setParentId(0);
			}
			betGoldGoodsCategory.setCreatedate(DateUtils.getCurrentDateNew("YYYY-MM-DD hh:mm:ss"));
			betGoldGoodsCategoryService.saveorupdate(betGoldGoodsCategory);
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
	public ReturnDatas delete(Model model,BetGoldGoodsCategory betGoldGoodsCategory,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				
				if(!StringUtils.isEmpty(betGoldGoodsCategory)){
					betGoldGoodsCategoryService.deleteById(betGoldGoodsCategory.getId(), BetGoldGoodsCategory.class);
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
