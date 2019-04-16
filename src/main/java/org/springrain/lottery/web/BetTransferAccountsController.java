package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetTransferAccounts;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetCentralbankService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBetTransferAccountsService;
import org.springrain.lottery.utils.AgentToolUtil;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-02 10:06:57
 * @see org.springrain.lottery.web.BetTransferAccounts
 */
@Controller
@RequestMapping(value="/bettransferaccounts")
public class BetTransferAccountsController  extends BaseController {
	@Resource
	private IBetTransferAccountsService betTransferAccountsService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetCentralbankService iBetCentralbankService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/bettransferaccounts/bettransferaccountsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betTransferAccounts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetTransferAccounts betTransferAccounts) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			ReturnDatas returnObject = listjson(request, model, betTransferAccounts);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}else{
			return "/lottery/bettransferaccounts/bettransferaccounts";
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betTransferAccounts
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetTransferAccounts betTransferAccounts) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("time");
		page.setSort("desc");
		// ==执行分页查询
		List<BetTransferAccounts> datas=betTransferAccountsService.findListDataByFinder(null,page,BetTransferAccounts.class,betTransferAccounts);
			returnObject.setQueryBean(betTransferAccounts);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetTransferAccounts betTransferAccounts) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betTransferAccountsService.findDataExportExcel(null,listurl, page,BetTransferAccounts.class,betTransferAccounts);
		String fileName="betTransferAccounts"+GlobalStatic.excelext;
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
		return "/lottery/bettransferaccounts/bettransferaccountsLook";
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
		  BetTransferAccounts betTransferAccounts = betTransferAccountsService.findBetTransferAccountsById(id);
		   returnObject.setData(betTransferAccounts);
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
	ReturnDatas saveorupdate(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		//订单䃼填-漏单来源：1、线下支付加分；2、平台支付加分；
		if("1".equals(request.getParameter("u"))){//线下支付加分
			Date date=new Date();
			Integer memberid2 =Integer.parseInt( request.getParameter("memberid2"));
			String remark = request.getParameter("remark");
			Double transferaccountsscore = Double.parseDouble( request.getParameter("transferaccountsscore"));// betTransferAccounts.getTransferaccountsscore();
			Integer type=Integer.parseInt( request.getParameter("type")); //
			//betTransferAccounts.getType();
			BetMember betMember=null;
			if(memberid2!=null){
				betMember = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2), BetMember.class);
				if(betMember==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("无此接收ID用户");
					return returnObject;
				}else if(betMember.getIsinternal()==1){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("内部用户无法转账");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("接收ID不能为空");
				return returnObject;
			}
			String agentid = SessionUser.getShiroUser().getAgentid();
			BetAgent agent1 = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", SessionUser.getShiroUser().getAgentid()), BetAgent.class);
			if(agent1.getTransferaccount()==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("您没有转账权限,请联系上级");
				return returnObject;
			}
			if(!agent1.getBankpassword().equals(SecUtils.encoderByMd5With32Bit(request.getParameter("pwd")))){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("密码错误");
				return returnObject;
			}
			if(transferaccountsscore==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("转账分不能为零");
				return returnObject;
			}
			/*if(transferaccountsscore<0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("转账分不能小于零");
				return returnObject;
			}*/
			
			BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			String agentparentid=betAgent.getParentid();
			if(!"A101".equals(agentparentid) && transferaccountsscore<0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("转账分不能小于零");
				return returnObject;
			}
			
			//Map<String, Object> agent = betAgentService.queryForObject(new Finder("select active,score from bet_agent where agentid=:agent ").setParam("agent", agentid));
			if(betAgent!=null){
				Integer i=betAgent.getActive();
				if(i!=null&1==i){
					//转账
					Double score = betAgent.getScore();
					if(score!=null){
						if(transferaccountsscore>0&&transferaccountsscore>score){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("转账分不能超过金币余额！");
							return returnObject;
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						return returnObject;
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("当前代理被冻结！");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此代理！");
				return returnObject;
			}
			
			String account = SessionUser.getShiroUser().getAccount();
			String name = SessionUser.getShiroUser().getName();
			String id = SessionUser.getShiroUser().getId();
			if(name==null){
				name="";
			}
			BetTransferAccounts betTranferAccounts2=new BetTransferAccounts();
			betTranferAccounts2.setMemberid2(memberid2);
			betTranferAccounts2.setRemark(remark);
			betTranferAccounts2.setTime(date);
			betTranferAccounts2.setTransferaccountsscore(transferaccountsscore);
			betTranferAccounts2.setTransferman(account+"("+name+")");
			betTranferAccounts2.setTransfermanid(id);
			String ip = IPUtils.getClientAddress(request);
			betTranferAccounts2.setIp(ip);
			betTranferAccounts2.setType(type);
			
			BetMember betMember2 = betMemberService.queryForObject(new Finder("select *from bet_member where id2=:id2 ").setParam("id2", memberid2), BetMember.class);
			betTranferAccounts2.setAgentid(betMember2.getAgentid());
			betTranferAccounts2.setAgentparentid(betMember2.getAgentparentid());
			betTranferAccounts2.setAgentparentids(betMember2.getAgentparentids());
			if(1==betTranferAccounts2.getType()){
				if(transferaccountsscore<0){
					if(betMember2.getBankscore()<(-transferaccountsscore)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("转账分不能大于用户银行分！");
						return returnObject;
					}
				}
				
				//扣除代理金币余额
				betAgentService.update(new Finder("update bet_agent set score = score - :transferaccountsscore where agentid = :agentid ").setParam("transferaccountsscore", transferaccountsscore).setParam("agentid", agentid));
				//银行分
				betTransferAccountsService.save(betTranferAccounts2);
				betMember2.setBankscore(betMember2.getBankscore()+transferaccountsscore);
				betMember2.setScore(betMember2.getScore()+transferaccountsscore);
				betMemberService.update(betMember2, true);
//				iBetCentralbankService.update(new Finder("update bet_centralbank set admintransfer = admintransfer-:transferaccountsscore ").setParam("transferaccountsscore", transferaccountsscore));
				//操作日志
				 String details = "";
			     details = "给ID为："+memberid2+"的用户的银行分转账："+transferaccountsscore+"元";
			     String ip1 = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip1,details,agentid,betAgent.getParentid(),betAgent.getParentids());
				//清除缓存
				String ticket = betMember2.getTicket();
				if(ticket!=null){
					try{
//						cached.deleteCached(("TICKET_"+ticket).getBytes());
						ObjectMapper mapper=new ObjectMapper();
						byte[] json = mapper.writeValueAsBytes(betMember2);
						
						cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
					}catch(Exception e){
						String errorMessage = e.getLocalizedMessage();
						logger.error(errorMessage,e);
					}
					
				}
				 //金币记录
			     BetScorerecord betScorerecord=new BetScorerecord();
			     String content="";
			     content="系统转账到银行分"+transferaccountsscore+"元";
			     betScorerecord.setMemberid2(memberid2);
			     betScorerecord.setTime(date);
			     betScorerecord.setContent(content);
			     betScorerecord.setOriginalscore(betMember2.getScore()-transferaccountsscore);
			     betScorerecord.setChangescore(transferaccountsscore);
			     betScorerecord.setGamescore(BigDecimal.valueOf(betMember2.getGamescore()));
			     betScorerecord.setBankscore(BigDecimal.valueOf(betMember2.getBankscore()));
			     betScorerecord.setFreezescore(BigDecimal.valueOf(betMember2.getFreezingscore()));
			     betScorerecord.setAgentid(betMember2.getAgentid());
			     betScorerecord.setAgentparentid(betMember2.getAgentparentid());
			     betScorerecord.setAgentparentids(betMember2.getAgentparentids());
			     betScorerecord.setBalance(betMember2.getScore());
			     betScorerecord.setState(1);
			     betScorerecord.setType(11);
			     betScorerecordService.saveBetScorerecord(betScorerecord);
				returnObject.setMessage("转账成功");
				returnObject.setData(betMember2);
				return returnObject;
			}else if(2==betTranferAccounts2.getType()){
				if(transferaccountsscore<0){
					if(betMember2.getGamescore()<(-transferaccountsscore)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("转账分不能大于用户游戏分！");
						return returnObject;
					}
				}
				//扣除代理金币余额
				betAgentService.update(new Finder("update bet_agent set score = score - :transferaccountsscore where agentid = :agentid ").setParam("transferaccountsscore", transferaccountsscore).setParam("agentid", agentid));
				//游戏分
				betTransferAccountsService.save(betTranferAccounts2);
				betMember2.setGamescore(betMember2.getGamescore()+transferaccountsscore);
				betMember2.setScore(betMember2.getScore()+transferaccountsscore);
				betMemberService.update(betMember2, true);
//				iBetCentralbankService.update(new Finder("update bet_centralbank set admintransfer = admintransfer-:transferaccountsscore ").setParam("transferaccountsscore", transferaccountsscore));
				//操作日志
				 String details = "";
			     details = "给ID为："+memberid2+"的用户的游戏分转账："+transferaccountsscore+"元";
			     String ip1 = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip1,details,agentid,betAgent.getParentid(),betAgent.getParentids());
				//清除缓存
				String ticket = betMember2.getTicket();
				if(ticket!=null){
					try{
//						cached.deleteCached(("TICKET_"+ticket).getBytes());
						ObjectMapper mapper=new ObjectMapper();
						byte[] json = mapper.writeValueAsBytes(betMember2);
						
						cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
					}catch(Exception e){
						String errorMessage = e.getLocalizedMessage();
						logger.error(errorMessage,e);
					}
					
				}
				 //金币记录
			     BetScorerecord betScorerecord=new BetScorerecord();
			     String content="";
			     content="系统转账到游戏分"+transferaccountsscore+"元";
			     betScorerecord.setMemberid2(memberid2);
			     betScorerecord.setTime(date);
			     betScorerecord.setContent(content);
			     betScorerecord.setOriginalscore(betMember2.getScore()-transferaccountsscore);
			     betScorerecord.setChangescore(transferaccountsscore);
			     betScorerecord.setGamescore(BigDecimal.valueOf(betMember2.getGamescore()));
			     betScorerecord.setBankscore(BigDecimal.valueOf(betMember2.getBankscore()));
			     betScorerecord.setFreezescore(BigDecimal.valueOf(betMember2.getFreezingscore()));
			     betScorerecord.setAgentid(betMember2.getAgentid());
			     betScorerecord.setAgentparentid(betMember2.getAgentparentid());
			     betScorerecord.setAgentparentids(betMember2.getAgentparentids());
			     betScorerecord.setBalance(betMember2.getScore());
			     betScorerecord.setState(1);
			     betScorerecord.setType(11);
			     betScorerecordService.saveBetScorerecord(betScorerecord);
				returnObject.setMessage("转账成功");
				returnObject.setData(betMember2);
				return returnObject;
			}else if(3==betTranferAccounts2.getType()){
				if(transferaccountsscore<0){
					if(betMember2.getFreezingscore()<(-transferaccountsscore)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("转账分不能大于用户冻结分！");
						return returnObject;
					}
				}
				
				//扣除代理金币余额
				betAgentService.update(new Finder("update bet_agent set score = score - :transferaccountsscore where agentid = :agentid ").setParam("transferaccountsscore", transferaccountsscore).setParam("agentid", agentid));
				//冻结分
				betTransferAccountsService.save(betTranferAccounts2);
				betMember2.setFreezingscore(betMember2.getFreezingscore()+transferaccountsscore);
				betMember2.setScore(betMember2.getScore()+transferaccountsscore);
				betMemberService.update(betMember2, true);
//				iBetCentralbankService.update(new Finder("update bet_centralbank set admintransfer = admintransfer-:transferaccountsscore ").setParam("transferaccountsscore", transferaccountsscore));
				//操作日志
				 String details = "";
			     details = "给ID为："+memberid2+"的用户的冻结分转账："+transferaccountsscore+"元";
			     String ip1 = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip1,details,agentid,betAgent.getParentid(),betAgent.getParentids());
				//清除缓存
				String ticket = betMember2.getTicket();
				if(ticket!=null){
					try{
//						cached.deleteCached(("TICKET_"+ticket).getBytes());
						ObjectMapper mapper=new ObjectMapper();
						byte[] json = mapper.writeValueAsBytes(betMember2);
						
						cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
					}catch(Exception e){
						String errorMessage = e.getLocalizedMessage();
						logger.error(errorMessage,e);
					}
					
				}
				 //金币记录
			     BetScorerecord betScorerecord=new BetScorerecord();
			     String content="";
			     content="系统转账到冻结分"+transferaccountsscore+"元";
			     betScorerecord.setMemberid2(memberid2);
			     betScorerecord.setTime(date);
			     betScorerecord.setContent(content);
			     betScorerecord.setOriginalscore(betMember2.getScore()-transferaccountsscore);
			     betScorerecord.setChangescore(transferaccountsscore);
			     betScorerecord.setGamescore(BigDecimal.valueOf(betMember2.getGamescore()));
			     betScorerecord.setBankscore(BigDecimal.valueOf(betMember2.getBankscore()));
			     betScorerecord.setFreezescore(BigDecimal.valueOf(betMember2.getFreezingscore()));
			     betScorerecord.setAgentid(betMember2.getAgentid());
			     betScorerecord.setAgentparentid(betMember2.getAgentparentid());
			     betScorerecord.setAgentparentids(betMember2.getAgentparentids());
			     betScorerecord.setBalance(betMember2.getScore());
			     betScorerecord.setState(1);
			     betScorerecord.setType(11);
			     betScorerecordService.saveBetScorerecord(betScorerecord);
				returnObject.setMessage("转账成功");
				returnObject.setData(betMember2);
				return returnObject;
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("转账类型错误");
				return returnObject;
			}
			
		} else{
			try {
//				betTransferAccountsService.saveorupdate(betTransferAccounts);
			} catch (Exception e) {
				String errorMessage = e.getLocalizedMessage();
				logger.error(errorMessage,e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
			return returnObject;
		}
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/bettransferaccounts/bettransferaccountsCru";
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
				betTransferAccountsService.deleteById(id,BetTransferAccounts.class);
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
			betTransferAccountsService.deleteByIds(ids,BetTransferAccounts.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
