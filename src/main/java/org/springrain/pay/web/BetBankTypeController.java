package org.springrain.pay.web;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.springrain.lottery.entity.BasketballAdjustodds;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetTransferagentAccounts;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.pay.entity.BetAgentProxies;
import org.springrain.pay.entity.BetBankType;
import org.springrain.pay.service.IBetBankTypeService;
import org.springrain.pay.utils.BankUtil;
import org.springrain.pay.utils.TdExpBasicFunctions;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:43:08
 * @see org.springrain.lottery.web.BalancePaymentController
 */
@Controller
@RequestMapping(value="/bankType")
public class BetBankTypeController   extends BaseController {
	@Resource
	private IBetBankTypeService bankTypeService;
	
	private String listurl="/lottery/basketballadjustodds/basketballadjustoddsList";
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param basketballAdjustodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetBankType betBankType) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betBankType);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param basketballAdjustodds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetBankType betBankType) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetBankType> datas=bankTypeService.findListDataByFinder(null,page,BetBankType.class,betBankType);
			returnObject.setQueryBean(betBankType);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping(value = "/look/json")
	@ResponseBody
	public ReturnDatas lookjson(Model model, HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String strId = request.getParameter("id");
		java.lang.Integer id = null;
		if (StringUtils.isNotBlank(strId)) {
			id = java.lang.Integer.valueOf(strId.trim());
			BetBankType betBankType = bankTypeService.findIBetBankTypeById(id);
			returnObject.setData(betBankType);
		} else {
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;

	}
	/**
	 * 查询
	 * 
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/basketballleagueteam/basketballleagueteamCru";
	}
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BetBankType betBankType,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userid = SessionUser.getUserId();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			bankTypeService.update(betBankType);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
}
