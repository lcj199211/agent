package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAgentgoldService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-01 09:41:49
 * @see org.springrain.lottery.web.BetAgentgold
 */
@Controller
@RequestMapping(value="/betagentgold")
public class BetAgentgoldController  extends BaseController {
	@Resource
	private IBetAgentgoldService betAgentgoldService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetOptLogService betOptLogService;
	
	private String listurl="/lottery/betagentgold/betagentgoldList";
	
	
	  
	/*一级代理充值列表*/
	@RequestMapping("/list1")
	public String list1(HttpServletRequest request, Model model,BetAgentgold betAgentgold) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		
		Page page = newPage(request);
		page.setOrder("submittime");
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
		List<BetAgentgold> datas=betAgentgoldService.queryForList(new Finder("select * from bet_agentgold where submittime>=:starttime and submittime<:endtime and (agentid=:agentid or agentparentids like :aid) ").setParam("agentid", agentid).setParam("aid", "%,"+agentid+",%").setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentgold.class, page);
		returnObject.setQueryBean(betAgentgold);
		returnObject.setPage(page);
		returnObject.setData(datas);
		
		
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
		
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betagentgold/firstbetagentgoldList";
	}
	
	
	@RequestMapping("/list1/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetAgentgold betAgentgold) throws Exception{
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("untreated"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Integer untreatedgold = betAgentgoldService.queryForObject(new Finder("select count(*) from bet_agentgold where state=0 and (agentid=:agentid or agentparentids like :aid ) ").setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%"), Integer.class);
			returnObject.setData(untreatedgold);
			return returnObject;
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetAgentgold> datas=null;
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAgentgold
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetAgentgold betAgentgold) 
			throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		Page page = newPage(request);
		page.setOrder("submittime");
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
		List<BetAgentgold> datas=betAgentgoldService.queryForList(new Finder("select * from bet_agentgold where agentid=:agentid and submittime>=:starttime and submittime<:endtime ").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentgold.class, page);
			returnObject.setQueryBean(betAgentgold);
		returnObject.setPage(page);
		returnObject.setData(datas);
		
		
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
		
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetAgentgold betAgentgold) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betAgentgoldService.findDataExportExcel(null,listurl, page,BetAgentgold.class,betAgentgold);
		String fileName="betAgentgold"+GlobalStatic.excelext;
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
		return "/lottery/betagentgold/betagentgoldLook";
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
		  BetAgentgold betAgentgold = betAgentgoldService.findBetAgentgoldById(id);
		   returnObject.setData(betAgentgold);
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
	ReturnDatas saveorupdate(Model model,BetAgentgold betAgentgold,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			BigDecimal money = betAgentgold.getMoney();
			if(money==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可为空！");
				return returnObject;
			}
			Double money1=money.doubleValue();
			if(money1<=0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("金额不可小于或等于0！");
				return returnObject;
			}
			
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			betAgentgold.setId(System.currentTimeMillis()+new Random().nextInt(100)+"");
			betAgentgold.setAgentid(agentid);
			betAgentgold.setAgentaccount(betagent.getAccount());
			betAgentgold.setAgentnickname(betagent.getNickname());
			betAgentgold.setMoney(money);
			betAgentgold.setState(0);
			betAgentgold.setSubmittime(new Date());
			betAgentgold.setAgentparentid(betagent.getParentid());
			betAgentgold.setAgentparentids(betagent.getParentids());
			betAgentgoldService.save(betAgentgold);
			
			//操作日志
			 String details = "";
		     details = "提交订单号为："+betAgentgold.getId()+"的代理充值订单";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
			
			
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
	 * 一级代理
	 * 
	 */
	@RequestMapping("/update1")
	public @ResponseBody
	ReturnDatas saveorupdate1(Model model,BetAgentgold betAgentgold,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(betAgentgold.getId()!=null&&betAgentgold.getState()!=null){
				BetAgentgold betagentGold1 = betAgentgoldService.findBetAgentgoldById(betAgentgold.getId());
				if(betagentGold1==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此代理充值订单！");
					return returnObject;
				}
				String agentid2 = betagentGold1.getAgentid();
				String agentparentids = betagentGold1.getAgentparentids();
				if(!(agentid2.equals(agentId)||agentparentids.indexOf(agentId)!=-1)){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("garbage,rubbish,trash,litter");
					return returnObject;
				}
				BetAgent betagent=betAgentService.queryForObject(new Finder("select*from bet_agent where agentid=:id ").setParam("id", betagentGold1.getAgentid()),BetAgent.class);
				if(betagent==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此代理！");
					return returnObject;
				}
				Integer state = betagentGold1.getState();
				Integer state1=betAgentgold.getState();
				if(0==state){
					if(1==state1){
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betAgentgold.setOperator(account+"("+name+")");
						betAgentgold.setArs(new BigDecimal(betagent.getScore()));
						betAgentgold.setBrs(new BigDecimal(betagent.getScore()));
						betAgentgoldService.update(new Finder("update bet_agentgold set operator=:operator,ars=:ars,brs=:brs,state=1 where id=:id ").setParam("operator", betAgentgold.getOperator()).setParam("ars", betAgentgold.getArs()).setParam("brs", betAgentgold.getBrs()).setParam("id", betAgentgold.getId()));
						//操作日志
						 String details = "";
					     details = "取消订单号为："+betAgentgold.getId()+"的代理充值订单";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agent.getAgentid(),agent.getParentid(),agent.getParentids());
					}else if(2==state1){
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						Date date=new Date();
						betAgentgold.setOperator(account+"("+name+")");
						betAgentgold.setRechargetime(date);
						if(betagent.getScore()==null){
							betagent.setScore(0.);
						}
						betAgentgold.setArs(new BigDecimal(betagent.getScore()+betagentGold1.getMoney().doubleValue()));
						betAgentgold.setBrs(new BigDecimal(betagent.getScore()));
						betAgentService.update(new Finder("update bet_agent set score=score+:score where agentid=:agentid ").setParam("agentid", betagent.getAgentid()).setParam("score", betagentGold1.getMoney().doubleValue()));
						betAgentgoldService.update(new Finder("update bet_agentgold set operator=:operator,ars=:ars,brs=:brs,state=2,rechargetime=:rechargetime where id=:id ").setParam("rechargetime", betAgentgold.getRechargetime()).setParam("operator", betAgentgold.getOperator()).setParam("ars", betAgentgold.getArs()).setParam("brs", betAgentgold.getBrs()).setParam("id", betAgentgold.getId()));
						//操作日志
						 String details = "";
					     details = "确定订单号为："+betAgentgold.getId()+"的代理充值订单";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agent.getAgentid(),agent.getParentid(),agent.getParentids());
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值状态只能修改一次");
					return returnObject;
				}
				
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			e.printStackTrace();
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
		return "/lottery/betagentgold/betagentgoldCru";
	}
	
}
