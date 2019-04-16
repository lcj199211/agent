package  org.springrain.lottery.web;

import java.io.File;
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
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetRegisterReward;
import org.springrain.lottery.service.IBetRegisterRewardService;
import org.springrain.lottery.service.IBetSubordinateRebateService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-12 11:04:57
 * @see org.springrain.lottery.web.BetRegisterReward
 */
@Controller
@RequestMapping(value="/betregisterreward")
public class BetRegisterRewardController  extends BaseController {
	@Resource
	private IBetRegisterRewardService betRegisterRewardService;
	@Resource
	private IBetSubordinateRebateService betSubordinateRebateService;
	
	private String listurl="/lottery/betregisterreward/betregisterrewardList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betRegisterReward
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetRegisterReward betRegisterReward) 
			throws Exception {
		//获取当前登录用户agentid
		String agentid = SessionUser.getAgentId();
		if("1".equals(request.getParameter("memberregisterreward"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("receivetime");
			page.setSort("desc");
			if(request.getParameter("memberid2")!=null){
				String parameter = request.getParameter("memberid2");
				List<BetRegisterReward> datas=	betRegisterRewardService.queryForList(new Finder("select * from bet_register_reward where agentid=:agentid or agentparentids like :agentparentids ORDER BY receivetime DESC").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%"), BetRegisterReward.class,page);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				
				return "/lottery/betmember/betregisterrewardList1";
			}else{
				return "/errorpage/error";
			}
		}else{
			ReturnDatas returnObject = listjson(request, model, betRegisterReward);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			Double zcrebate = betSubordinateRebateService.queryForObject(new Finder("select rebate from bet_subordinate_rebate where remark=:remark  and (agentid=:agentid or agentparentids like :agentparentids) ").setParam("remark", "zc").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%"),Double.class);
			model.addAttribute("zcrebate", zcrebate);
			return listurl;
		}
		
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betRegisterReward
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetRegisterReward betRegisterReward) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetRegisterReward> datas=betRegisterRewardService.findListDataByFinder(null,page,BetRegisterReward.class,betRegisterReward);
			returnObject.setQueryBean(betRegisterReward);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetRegisterReward betRegisterReward) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betRegisterRewardService.findDataExportExcel(null,listurl, page,BetRegisterReward.class,betRegisterReward);
		String fileName="betRegisterReward"+GlobalStatic.excelext;
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
		return "/lottery/betregisterreward/betregisterrewardLook";
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
		  BetRegisterReward betRegisterReward = betRegisterRewardService.findBetRegisterRewardById(id);
		   returnObject.setData(betRegisterReward);
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
	ReturnDatas saveorupdate(Model model,BetRegisterReward betRegisterReward,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			betRegisterRewardService.saveorupdate(betRegisterReward);
			
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
		return "/lottery/betregisterreward/betregisterrewardCru";
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
				betRegisterRewardService.deleteById(id,BetRegisterReward.class);
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
			betRegisterRewardService.deleteByIds(ids,BetRegisterReward.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
