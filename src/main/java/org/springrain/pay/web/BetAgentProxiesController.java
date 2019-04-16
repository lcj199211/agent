package  org.springrain.pay.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentgold;
import org.springrain.lottery.entity.BetAgentwithdraw;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.pay.entity.BetAgentProxies;
import org.springrain.pay.service.IBetAgentProxiesService;
import org.springrain.pay.service.IBetBankTypeService;
import org.springrain.pay.utils.BankUtil;
import org.springrain.pay.utils.TdExpBasicFunctions;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 09:58:21
 * @see org.springrain.lottery.web.BetAgentProxies
 */
@Controller
@RequestMapping(value="/betagentproxie")
public class BetAgentProxiesController  extends BaseController {
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentProxiesService betAgentProxiesService;

	private String listurl="/lottery/betAgentProxies/betagentproxiesList";
	
	@RequestMapping("/list1")
	public String list1(HttpServletRequest request, Model model,BetAgentProxies betAgentProxies) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		Page page = newPage(request);
		page.setOrder("applicationtime");
		page.setSort("desc");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		String starttime1 = request.getParameter("startTime1");
		String endtime1 = request.getParameter("endTime1");
		Date date1 =DateUtils.convertString2Date(endtime);
		Calendar calendar = new GregorianCalendar();
		if(date1!=null){
			calendar.setTime(date1); 
			calendar.add(Calendar.DATE,1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime)){
			starttime="0000-00-00";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-00-00";
		}
		Date date2 =DateUtils.convertString2Date(endtime1);
		Calendar calendar2 = new GregorianCalendar();
		if(date2!=null){
			calendar2.setTime(date2); 
			calendar2.add(Calendar.DATE,1);
			Date date3=calendar2.getTime();
			endtime1 = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime1)){
			starttime1="0000-00-00";
		}
		if(StringUtils.isBlank(endtime1)){
			endtime1="9999-00-00";
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==执行分页查询
		betAgentProxies.setAgentid(agentId);
//		List<BetAgentProxies> datas=betAgentProxiesService.findListDataByFinder(null,page,BetAgentProxies.class,betAgentProxies);
		List<BetAgentProxies> datas=new ArrayList<>();
		if("0000-00-00".equals(starttime1)&&"9999-00-00".equals(endtime1)){
			datas=betAgentProxiesService.queryForList(new Finder("select * from bet_agentproxies where (agentid=:agentid or agentparentids like :aid) and applicationtime>=:starttime and applicationtime<:endtime ").setParam("aid", "%,"+agentId+",%").setParam("agentid", agentId).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentProxies.class, page);
		}else{
			datas=betAgentProxiesService.queryForList(new Finder("select * from bet_agentproxies where (agentid=:agentid or agentparentids like :aid) and applicationtime>=:starttime and applicationtime<:endtime ").setParam("aid", "%,"+agentId+",%").setParam("agentid", agentId).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentProxies.class, page);
		}
		if(!"0000-00-00".equals(starttime)){
			model.addAttribute("startTime", starttime);
		}
		if(!"9999-00-00".equals(endtime)){
			Date date4 =DateUtils.convertString2Date(endtime);
			calendar.setTime(date4); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime", endtime);
		}
		if(!"0000-00-00".equals(starttime1)){
			model.addAttribute("startTime1", starttime1);
		}
		if(!"9999-00-00".equals(endtime1)){
			Date date4 =DateUtils.convertString2Date(endtime1);
			calendar.setTime(date4); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime1 = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime1", endtime1);
		}
		try{
			if(datas!=null){
				for(int i=0;i<datas.size();i++){
					Map<String, Object> map  = betAgentProxiesService.payforQuery(datas.get(i).getOrderid());
					if(map.get("subcode").equals("0000")){
						datas.get(i).setState(0);
						datas.get(i).setFailreason((String)map.get("subcode"));
						betAgentProxiesService.update(datas.get(i));
					}else if(map.get("subcode").equals("T006")){
						datas.get(i).setState(2);
						datas.get(i).setFailreason((String)map.get("subcode"));
						betAgentProxiesService.update(datas.get(i));
					}else if(map.get("subcode").equals("T010")){
						datas.get(i).setState(1);
						datas.get(i).setFailreason((String)map.get("subcode"));
						betAgentProxiesService.update(datas.get(i));
					}else if(map.get("subcode").equals("T000")){
						datas.get(i).setState(3);
						datas.get(i).setFailreason((String)map.get("subcode"));
						betAgentProxiesService.update(datas.get(i));
					}else if(map.get("subcode").equals("T011")){
						datas.get(i).setState(4);
						datas.get(i).setFailreason((String)map.get("subcode"));
						betAgentProxiesService.update(datas.get(i));
					}else{
						datas.get(i).setState(5);
						datas.get(i).setFailreason((String)map.get("subcode"));
						betAgentProxiesService.update(datas.get(i));
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		returnObject.setQueryBean(betAgentProxies);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betAgentProxies/firstbetagentproxiesList";
	}
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAgentProxies
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetAgentProxies betAgentProxies) 
			throws Exception {
//		ReturnDatas returnObject = listjson(request, model, betAgentProxies);
		String agentId = SessionUser.getAgentId();
		Page page = newPage(request);
		page.setOrder("applicationtime");
		page.setSort("desc");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		Date date1 =DateUtils.convertString2Date(endtime);
		Calendar calendar = new GregorianCalendar();
		if(date1!=null){
			calendar.setTime(date1); 
			calendar.add(Calendar.DATE,1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
		}
		if(StringUtils.isBlank(starttime)){
			starttime="0000-00-00";
		}
		if(StringUtils.isBlank(endtime)){
			endtime="9999-00-00";
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==执行分页查询
		betAgentProxies.setAgentid(agentId);
//		List<BetAgentProxies> datas=betAgentProxiesService.findListDataByFinder(null,page,BetAgentProxies.class,betAgentProxies);
		List<BetAgentProxies> datas=betAgentProxiesService.queryForList(new Finder("select * from bet_agentproxies where agentid=:agentid and applicationtime>=:starttime and applicationtime<:endtime ").setParam("agentid", agentId).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentProxies.class, page);
		if(!"0000-00-00".equals(starttime)){
			model.addAttribute("startTime", starttime);
		}
		if(!"9999-00-00".equals(endtime)){
			Date date2 =DateUtils.convertString2Date(endtime);
			calendar.setTime(date2); 
			calendar.add(Calendar.DATE,-1);
			Date date3=calendar.getTime();
			endtime = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime", endtime);
		}
		returnObject.setQueryBean(betAgentProxies);
		returnObject.setPage(page);
		returnObject.setData(datas);
		Double memberMoney = betAgentProxiesService.memberQuery();
		model.addAttribute("memberMoney", memberMoney);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 一级代理
	 * 
	 * @param request
	 * @param model
	 * @param betAgentProxies
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list1/json")
	public @ResponseBody
	ReturnDatas listjson1(HttpServletRequest request, Model model,BetAgentProxies betAgentProxies) throws Exception{
		
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("untreated"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Integer untreated = betAgentProxiesService.queryForObject(new Finder("select count(*) from bet_agentproxies where state=0 and (agentid=:agentid or agentparentids like :aid ) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%"), Integer.class);
			returnObject.setData(untreated);
			return returnObject;
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
//		List<BetAgentgold> datas=betAgentgoldService.findListDataByFinder(null,page,BetAgentgold.class,betAgentgold);
//			returnObject.setQueryBean(betAgentgold);
			List<BetAgentgold> datas=null;
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
		
//		String agentId = SessionUser.getAgentId();
//		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//		// ==构造分页请求
//		Page page = newPage(request);
//		// ==执行分页查询
//		betAgentProxies.setAgentid(agentId);
//		List<BetAgentProxies> datas=betAgentProxiesService.findListDataByFinder(null,page,BetAgentProxies.class,betAgentProxies);
//			returnObject.setQueryBean(betAgentProxies);
//		returnObject.setPage(page);
//		returnObject.setData(datas);
//		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetAgentProxies betAgentProxies) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betAgentProxiesService.findDataExportExcel(null,listurl, page,BetAgentProxies.class,betAgentProxies);
		String fileName="betAgentProxies"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BetAgentProxies betAgentProxies,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userid = SessionUser.getUserId();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
//		BetAgentProxies betAgentProxies2 = new BetAgentProxies();
/*		if(betAgentProxies.getId()!=""){
			betAgentProxies2 = betAgentProxiesService.findIBetAgentProxiesById(betAgentProxies.getId());
		}*/
		try {
			Double money = betAgentProxies.getMoney();
			if(money==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可为空！");
				return returnObject;
			}
			double money1=money.doubleValue();
			if(money1<10){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("单笔提现金额不可小于10元！");
				return returnObject;
			}
			if(money1>50000){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("单笔提现金额不可大于50000元！");
				return returnObject;
			}
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			Double memberMoney = betAgentProxiesService.memberQuery();
			if(money1>memberMoney){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可大于商户余额！");
				return returnObject;
			}
			String orderid = TdExpBasicFunctions.RANDOM(19, "2");
			String bankName = BankUtil.getNameOfBank(betAgentProxies.getAccountno());
			betAgentProxies.setBankname(bankName);
			betAgentProxies.setOrderid(orderid);
			betAgentProxies.setAccounttype(1);
			Map<String, Object> map = betAgentProxiesService.payfor(betAgentProxies);
//			if(betAgentProxies.getId()!=""){
//			    map  = betAgentProxiesService.payfor(betAgentProxies);
//			}else{
//				map  = betAgentProxiesService.payfor(betAgentProxies2);
//			}
			if(map.get("rspcode").equals("000000")){
//				if(betAgentProxies2.getState()!=null&&betAgentProxies2.getState()==1){
//					betAgentProxies2.setApplicationtime(new Date());
//					betAgentProxies2.setState(2);
//					betAgentProxiesService.update(betAgentProxies2);
//					//操作日志
//					String details = "";
//					details = "重新提交订单号为："+betAgentProxies2.getOrderid()+"的商户提现订单成功";
//					String ip = IPUtils.getClientAddress(request);
//					String tool = AgentToolUtil.getAgentTool(request);
//					betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());	
//				}else{
					betAgentProxies.setId(System.currentTimeMillis()+new Random().nextInt(100)+"");
					betAgentProxies.setMemberid(userid);
					betAgentProxies.setApplicationtime(new Date());
					betAgentProxies.setState(2);
					betAgentProxies.setAgentid(agentid);
					betAgentProxies.setAgentparentid(betagent.getParentid());
					betAgentProxies.setAgentparentids(betagent.getParentids());
					betAgentProxies.setAwcs(betagent.getScore());
					betAgentProxies.setBwcs(betagent.getScore()+money);
					betAgentProxies.setCnaps("105793000108");
					betAgentProxiesService.save(betAgentProxies);
					//操作日志
					String details = "";
					details = "提交订单号为："+betAgentProxies.getOrderid()+"的商户提现订单成功";
					String ip = IPUtils.getClientAddress(request);
					String tool = AgentToolUtil.getAgentTool(request);
					betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());	
//				}
			}else{
//				if(betAgentProxies2.getState()!=null&&betAgentProxies2.getState()==1){
//					betAgentProxies2.setApplicationtime(new Date());
//					betAgentProxies2.setState(1	);
//					betAgentProxiesService.update(betAgentProxies2);
//					//操作日志
//					String details = "";
//					details = "重新提交订单号为："+betAgentProxies2.getOrderid()+"的商户提现订单失败";
//					String ip = IPUtils.getClientAddress(request);
//					String tool = AgentToolUtil.getAgentTool(request);
//					betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());	
//				}else{
					betAgentProxies.setId(System.currentTimeMillis()+new Random().nextInt(100)+"");
					betAgentProxies.setMemberid(userid);
					betAgentProxies.setApplicationtime(new Date());
					betAgentProxies.setState(1);
					betAgentProxies.setAgentid(agentid);
					betAgentProxies.setAgentparentid(betagent.getParentid());
					betAgentProxies.setAgentparentids(betagent.getParentids());
					betAgentProxies.setCnaps("105793000108");
					betAgentProxies.setAwcs(betagent.getScore());
					betAgentProxies.setBwcs(betagent.getScore());
					betAgentProxies.setFailreason((String)map.get("rspcode"));
					betAgentProxiesService.save(betAgentProxies);
					//操作日志
					String details = "";
					details = "提交订单号为："+betAgentProxies.getOrderid()+"的商户提现订单失败";
					String ip = IPUtils.getClientAddress(request);
					String tool = AgentToolUtil.getAgentTool(request);
					betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());	
//				}
				
				returnObject.setMessage(map.get("rspmsg").toString());
			}
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	@RequestMapping("/update1")
	public @ResponseBody
	ReturnDatas saveorupdate1(Model model,BetAgentProxies betAgentProxies,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentId = SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		try {
			if(betAgentProxies.getId()!=null&&betAgentProxies.getState()!=null){

				BetAgentProxies betAgentProxies1 = betAgentProxiesService.findIBetAgentProxiesById(betAgentProxies.getId());
				if(betAgentProxies1==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此代理提现订单！");
					return returnObject;
				}
				String agentid2 = betAgentProxies1.getAgentid();
				String agentparentids = betAgentProxies1.getAgentparentids();
				if(!(agentid2.equals(agentId)||agentparentids.indexOf(agentId)!=-1)){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("garbage,rubbish,trash,litter");
					return returnObject;
				}
				BetAgent betagent=betAgentService.queryForObject(new Finder("select*from bet_agent where agentid=:id ").setParam("id", betAgentProxies1.getAgentid()),BetAgent.class);
				if(betagent==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此代理！");
					return returnObject;
				}
				Integer state = betAgentProxies1.getState();
				Integer state1=betAgentProxies1.getState();
				if(1==state){
					if(1==state1){
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						int sdsd = betAgentProxiesService.update(new Finder("update bet_agentproxies set state=3 where id=:id ").setParam("id", betAgentProxies.getId()));
						//操作日志
						 String details = "";
					     details = "取消订单号为："+betAgentProxies.getOrderid()+"的代理充值订单";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agent.getAgentid(),agent.getParentid(),agent.getParentids());
					}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("提现状态只能修改一次");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
			}
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
//		List<String> bank = betBankTypeService.queryForList(new Finder("select bankname from bet_banktype"),String.class);
		Double memberMoney = betAgentProxiesService.memberQuery();
		model.addAttribute("memberMoney", memberMoney);
//		model.addAttribute("bankname", bank);
		return "/lottery/betAgentProxies/betagentproxiesCru";
	}
	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			BetAgentProxies betAgentProxies = betAgentProxiesService.findIBetAgentProxiesById(id);
		   returnObject.setData(betAgentProxies);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				betAgentProxiesService.deleteById(id,BetAgentProxies.class);
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
			betAgentProxiesService.deleteByIds(ids,BetAgentProxies.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
