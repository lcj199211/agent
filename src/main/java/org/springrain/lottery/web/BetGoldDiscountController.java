package org.springrain.lottery.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetGoldDiscount;
import org.springrain.lottery.service.IBetGoldDiscountService;

import com.alibaba.fastjson.JSON;
@Controller
@RequestMapping(value="/betGoldDiscount")
public class BetGoldDiscountController extends BaseController{

	@Resource
	private IBetGoldDiscountService betGoldDiscountService;
	
	@Resource
	private ICached cached;
	/**
	 * 
	
	* @Title: listjson 
	
	* @Description: TODO   充值优惠列表查询
	
	*  @param request
	*  @param model
	*  @param betGoldDiscount
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public ReturnDatas listjson(HttpServletRequest request, Model model, BetGoldDiscount betGoldDiscount)
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		betGoldDiscount.setCompany(agentid);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetGoldDiscount> datas = betGoldDiscountService.findListDataByFinder(null, page, BetGoldDiscount.class,
				betGoldDiscount);
		returnObject.setQueryBean(betGoldDiscount);
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
	*  @param betGoldDiscount
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetGoldDiscount betGoldDiscount) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betGoldDiscount);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgolddiscount/betgolddiscountList";
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
		  if(org.apache.commons.lang3.StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BetGoldDiscount betGoldDiscount = betGoldDiscountService.findBetGoldDiscountById(id);
		   returnObject.setData(betGoldDiscount);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgolddiscount/betgolddiscountCru";
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
	public ReturnDatas update(Model model,BetGoldDiscount betGoldDiscount,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String agentid = SessionUser.getShiroUser().getAgentid();
			betGoldDiscount.setCompany(agentid);
			betGoldDiscountService.saveorupdate(betGoldDiscount);
			List<BetGoldDiscount> datas = betGoldDiscountService.findListDataByFinder(null, null, BetGoldDiscount.class,betGoldDiscount);
			cached.updateCached(("goldDiscountAll"+agentid).getBytes("utf-8"), JSON.toJSONString(datas).getBytes("utf-8"), null);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
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
	public ReturnDatas delete(Model model,BetGoldDiscount betGoldDiscount,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				
				if(!StringUtils.isEmpty(betGoldDiscount)){
					betGoldDiscountService.deleteById(betGoldDiscount.getId(), BetGoldDiscount.class);
					cached.deleteCached(("goldDiscountAll*").getBytes("utf-8"));
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
	public ReturnDatas findById(Model model,BetGoldDiscount betGoldDiscount,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				
				if(!StringUtils.isEmpty(betGoldDiscount)){
					betGoldDiscountService.deleteById(betGoldDiscount.getId(), BetGoldDiscount.class);
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
