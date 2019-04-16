package org.springrain.lottery.web;

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
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetGoldGoodsBuylogs;
import org.springrain.lottery.entity.BetGoldGoodsOrder;
import org.springrain.lottery.service.IBetGoldGoodsBuylogsService;
import org.springrain.lottery.service.IBetGoldGoodsOrderService;
@Controller
@RequestMapping(value="/betgoldgoodsorder")
public class BetGoldGoodsOrderController  extends BaseController{
	@Resource
	private IBetGoldGoodsOrderService betGoldGoodsOrderService;
	@Resource
	private IBetGoldGoodsBuylogsService betGoldGoodsBuylogsService;
	
	
	
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
	public ReturnDatas listjson(HttpServletRequest request, Model model, BetGoldGoodsOrder betGoldGoodsOrder)
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		betGoldGoodsOrder.setAgentid(agentid);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetGoldGoodsOrder> datas = betGoldGoodsOrderService.findGoodsOrderList(betGoldGoodsOrder,page);
		returnObject.setQueryBean(betGoldGoodsOrder);
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
	public String list(HttpServletRequest request, Model model,BetGoldGoodsOrder betGoldGoodsOrder) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betGoldGoodsOrder);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgoldsgoodsorder/betgoldgoodsorderList";
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
	@RequestMapping(value = "/info")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(org.apache.commons.lang3.StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BetGoldGoodsOrder betGoldGoodsOrder = betGoldGoodsOrderService.findBetGoldGoodsOrderById(id);
		  BetGoldGoodsBuylogs betGoldGoodsBuylogs = new BetGoldGoodsBuylogs(); 
		  betGoldGoodsBuylogs.setOrderid(betGoldGoodsOrder.getOrderid());
		  List<Map<String, Object>> buylogs = betGoldGoodsBuylogsService.findGoodsBuylogsList(betGoldGoodsBuylogs);
		  Map<String, Object> map = new HashMap<>();
		  map.put("order", betGoldGoodsOrder);
		  map.put("goods", buylogs);
		  returnObject.setData(map);
		}else{
		  returnObject.setStatus(ReturnDatas.ERROR);
		}
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgoldsgoodsorder/betgoldgoodsbuylogsLook";
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
	public ReturnDatas delete(Model model,BetGoldGoodsOrder betGoldGoodsOrder,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			try {
				
				if(!StringUtils.isEmpty(betGoldGoodsOrder)){
					betGoldGoodsOrder = betGoldGoodsOrderService.findBetGoldGoodsOrderById(betGoldGoodsOrder.getId());
					betGoldGoodsBuylogsService.deleteByOrderId(betGoldGoodsOrder.getOrderid());
					betGoldGoodsOrderService.deleteById(betGoldGoodsOrder.getId(), BetGoldGoodsOrder.class);
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
