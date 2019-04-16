package  org.springrain.lottery.web;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
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
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetRechargecard;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetRechargecardService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.RandomCharData;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-26 16:43:02
 * @see org.springrain.lottery.web.BetRechargecard
 */
@Controller
@RequestMapping(value="/betrechargecard")
public class BetRechargecardController  extends BaseController {
	@Resource
	private IBetRechargecardService betRechargecardService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betrechargecard/betrechargecardList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betRechargecard
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetRechargecard betRechargecard) 
			throws Exception {
		if("1".equals(request.getParameter("z"))){
			ReturnDatas returnObject = listjson(request, model, betRechargecard);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}else if("2".equals(request.getParameter("z"))){
			return "lottery/betrechargecard/betgeneraterechargecard";
		}
		return "/lottery/betrechargecard/betgeneraterechargecard";
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betRechargecard
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetRechargecard betRechargecard) throws Exception{
		Date date=new Date();
		long longtime2 = date.getTime();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		page.setOrder("time");
		page.setSort("desc");
		if("1".equals(request.getParameter("k"))){
			//作废
			List<BetRechargecard> datas1=betRechargecardService.findListDataByFinder(new Finder("SELECT *FROM bet_rechargecard where state=2 or (state=0 and (UNIX_TIMESTAMP(time)+validity*86400)<UNIX_TIMESTAMP(NOW())) "),page,BetRechargecard.class,betRechargecard);
//			for (BetRechargecard betRechargecard2 : datas1) {
//				if(1!=betRechargecard2.getState()){
//					Integer validity = betRechargecard2.getValidity();
//					Date time = betRechargecard2.getTime();
//					long longtime = time.getTime();
//					long longtime3 =validity*24*60*60*1000L;
//					if((longtime2-longtime)>longtime3){
//						betRechargecard2.setState(2);
//						datas.add(betRechargecard2);
//					}
//				}
//			}
//			page.setTotalCount(datas.size());
			if(datas1!=null){
				for (BetRechargecard betRechargecard2 : datas1) {
					betRechargecard2.setState(2);
				}
			}
			returnObject.setQueryBean(betRechargecard);
			returnObject.setPage(page);
			returnObject.setData(datas1);
			model.addAttribute("k",1);
			return returnObject;
		}else if("2".equals(request.getParameter("k"))){
			//未充
//			List<BetRechargecard> datas=new ArrayList<BetRechargecard>();
			List<BetRechargecard> datas1=betRechargecardService.findListDataByFinder(new Finder("SELECT *FROM bet_rechargecard where state=0 and (UNIX_TIMESTAMP(time)+validity*86400)>UNIX_TIMESTAMP(NOW()) "),page,BetRechargecard.class,betRechargecard);
//			for (BetRechargecard betRechargecard2 : datas1) {
//				if(1!=betRechargecard2.getState()){
//					Integer validity = betRechargecard2.getValidity();
//					Date time = betRechargecard2.getTime();
//					long longtime = time.getTime();
//					long longtime3 =validity*24*60*60*1000L;
//					if((longtime2-longtime)<=longtime3){
//						datas.add(betRechargecard2);
//					}
//				}
//			}
			returnObject.setQueryBean(betRechargecard);
			returnObject.setPage(page);
			returnObject.setData(datas1);
			model.addAttribute("k",2);
			return returnObject;
		}else if("3".equals(request.getParameter("k"))){
			//已使用
			BetRechargecard betRechargecard2=new BetRechargecard();
			betRechargecard2.setState(1);
			List<BetRechargecard> datas=betRechargecardService.findListDataByFinder(null,page,BetRechargecard.class,betRechargecard2);
//			if(datas!=null){
//				for (BetRechargecard betRechargecard3 : datas) {
//					if(1!=betRechargecard3.getState()){
//						Integer validity = betRechargecard3.getValidity();
//						Date time = betRechargecard3.getTime();
//						long longtime = time.getTime();
//						long longtime3 =validity*24*60*60*1000L;
//						if((longtime2-longtime)>longtime3){
//							betRechargecard3.setState(2);
//						}
//					}
//				}
//			}
			returnObject.setQueryBean(betRechargecard);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("k",3);
			return returnObject;
		}else{
			List<BetRechargecard> datas=betRechargecardService.findListDataByFinder(null,page,BetRechargecard.class,betRechargecard);
			if(datas!=null){
				for (BetRechargecard betRechargecard2 : datas) {
					if(1!=betRechargecard2.getState()&&2!=betRechargecard2.getState()){
						Integer validity = betRechargecard2.getValidity();
						Date time = betRechargecard2.getTime();
						long longtime = time.getTime();
						long longtime3 =validity*24*60*60*1000L;
						if((longtime2-longtime)>longtime3){
							betRechargecard2.setState(2);
						}
					}
				}
			}
			returnObject.setQueryBean(betRechargecard);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetRechargecard betRechargecard) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betRechargecardService.findDataExportExcel(null,listurl, page,BetRechargecard.class,betRechargecard);
		String fileName="betRechargecard"+GlobalStatic.excelext;
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
		return "/lottery/betrechargecard/betrechargecardLook";
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
		  BetRechargecard betRechargecard = betRechargecardService.findBetRechargecardById(id);
		   returnObject.setData(betRechargecard);
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
	ReturnDatas saveorupdate(Model model,BetRechargecard betRechargecard,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		Date date=new Date();
		long time = date.getTime();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		if("1".equals(request.getParameter("k"))){
//				try {
//					Integer memberid2 = betRechargecard.getMemberid2();
//					BetMember betMember = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", memberid2), BetMember.class);
//					if(betMember!=null){
//						String id = betRechargecard.getId();
//						if(StringUtils.isNoneBlank(id)){
//							BetRechargecard betRechargecard2 = betRechargecardService.queryForObject(new Finder("select*from bet_rechargecard where id=:id").setParam("id", id), BetRechargecard.class);
//							if(betRechargecard2!=null){
//								String password = betRechargecard2.getPassword();
//								String password2=betRechargecard.getPassword();
//								if(StringUtils.isNoneBlank(password2)){
//									if(password2.equals(password)){
//										Date time2 = betRechargecard2.getTime();
//										Integer validity = betRechargecard2.getValidity();
//										long time3 = time2.getTime();
//										long time4 = validity*24*60*60*1000L;
//										if((time4+time3)>time){
//											if(!(betRechargecard2.getState()==1)){
//												Double money = betRechargecard2.getMoney();
//												betMember.setScore(betMember.getScore()+money);
//												betMember.setBankscore(betMember.getBankscore()+money);
//												betRechargecard2.setState(1);
//												String ip = IPUtils.getClientAddress(request);
//												betRechargecard2.setIp(ip);
//												betRechargecard2.setMemberid2(memberid2);
////												String account = SessionUser.getShiroUser().getAccount();
////												String name = SessionUser.getShiroUser().getName();
////												if(name==null){
////													name="";
////												}
////												betRechargecard2.setOperator(account+"("+name+")");
//												betRechargecard2.setRechargetime(date);
//												try{
//													betMemberService.update(betMember);
//													betRechargecardService.update(betRechargecard2);
//												}catch(Exception e){
//													returnObject.setStatus(ReturnDatas.ERROR);
//													returnObject.setMessage("数据更新错误");
//												}
//												returnObject.setStatus(ReturnDatas.ERROR);
//												returnObject.setMessage("充值卡已充值");
//											}
//										}else{
//											returnObject.setStatus(ReturnDatas.ERROR);
//											returnObject.setMessage("充值卡已过期");
//										}
//									}
//								}else{
//									returnObject.setStatus(ReturnDatas.ERROR);
//									returnObject.setMessage("充值密码不能为空");
//								}
//							}else{
//								returnObject.setStatus(ReturnDatas.ERROR);
//								returnObject.setMessage("充值卡号有误");
//							}
//						}else{
//							returnObject.setStatus(ReturnDatas.ERROR);
//							returnObject.setMessage("充值卡号不能为空");
//						}
//					}else{
//						returnObject.setStatus(ReturnDatas.ERROR);
//						returnObject.setMessage("无此ID用户");
//					}
//			} catch (Exception e) {
//				String errorMessage = e.getLocalizedMessage();
//				logger.error(errorMessage,e);
//				returnObject.setStatus(ReturnDatas.ERROR);
//				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
//			}
		}else if("2".equals(request.getParameter("k"))){
			Date date3 =new Date();
			String strNum = request.getParameter("num");
			String strMoney = request.getParameter("money1");
			String strKhws = request.getParameter("khws");
			String strKhlx = request.getParameter("khlx");
			String strMmws = request.getParameter("mmws");
			String strMmlx = request.getParameter("mmlx");
			String strValidity = request.getParameter("validity");
			Integer num=0;
			Double money=0.;
			Integer khws=0;
			Integer mmws=0;
			Integer validity=0;
			if(StringUtils.isNoneBlank(strNum)){
				try{
					num = Integer.valueOf(strNum);
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡张数必须为数字");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡张数不能为空");
				return returnObject;
			}
			
			if(StringUtils.isNoneBlank(strMoney)){
				try{
					money = Double.valueOf(strMoney);
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡面值必须为数字");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡面值不能为空");
				return returnObject;
			}
			
			if(StringUtils.isNoneBlank(strKhws)){
				try{
					khws = Integer.valueOf(strKhws);
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡号位数必须为数字");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡号位数不能为空");
				return returnObject;
			}
			
			if(StringUtils.isNoneBlank(strKhlx)){
				if("sz".equals(strKhlx)||"szyw".equals(strKhlx)){
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡号类型错误");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡号类型不能为空");
				return returnObject;
			}
			
			if(StringUtils.isNoneBlank(strMmws)){
				try{
					mmws = Integer.valueOf(strMmws);
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡密码必须为数字");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡密码不能为空");
				return returnObject;
			}
			
			if(StringUtils.isNoneBlank(strMmlx)){
				if("sz".equals(strMmlx)||"szyw".equals(strMmlx)){
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡密码类型错误");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡密码类型不能为空");
				return returnObject;
			}
			
			if(StringUtils.isNoneBlank(strValidity)){
				try{
					validity = Integer.valueOf(strValidity);
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("充值卡有效期必须为数字");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡有效期不能为空");
				return returnObject;
			}
			if(num<=0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡张数不能为0或负数");
				return returnObject;
			}else if(money<=0.){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡面值不能为0或负数");
				return returnObject;
			}else if(khws<=0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡号位数不能为0或负数");
				return returnObject;
			}else if(mmws<=0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡密码位数不能为0或负数");
				return returnObject;
			}else if(validity<=0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("充值卡有效期不能为0或负数");
				return returnObject;
			}
			for(int i=0;i<num;i++){
				BetRechargecard betRechargecard3=new BetRechargecard();
				if("sz".equals(strKhlx)){
					betRechargecard3.setId(RandomCharData.createData(khws));
					if("sz".equals(strMmlx)){
						betRechargecard3.setPassword(RandomCharData.createData(mmws));
						betRechargecard3.setMoney(money);
						betRechargecard3.setState(0);
						betRechargecard3.setTime(date3);
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betRechargecard3.setOperator(account+"("+name+")");
						betRechargecard3.setValidity(validity);
						try{
							betRechargecardService.save(betRechargecard3);
							//操作日志
							 String details = "";
						     details = "生成卡号为："+betRechargecard3.getId()+"的充值卡";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}else if("szyw".equals(strMmlx)){
						betRechargecard3.setPassword(RandomCharData.createRandomCharData(mmws));
						betRechargecard3.setMoney(money);
						betRechargecard3.setState(0);
						betRechargecard3.setTime(date3);
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betRechargecard3.setOperator(account+"("+name+")");
						betRechargecard3.setValidity(validity);
						try{
							betRechargecardService.save(betRechargecard3);
							//操作日志
							 String details = "";
						     details = "生成卡号为："+betRechargecard3.getId()+"的充值卡";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}
				}else if("szyw".equals(strKhlx)){
					betRechargecard3.setId(RandomCharData.createRandomCharData(khws));
					if("sz".equals(strMmlx)){
						betRechargecard3.setPassword(RandomCharData.createData(mmws));
						betRechargecard3.setMoney(money);
						betRechargecard3.setState(0);
						betRechargecard3.setTime(date3);
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betRechargecard3.setOperator(account+"("+name+")");
						betRechargecard3.setValidity(validity);
						try{
							betRechargecardService.save(betRechargecard3);
							//操作日志
							 String details = "";
						     details = "生成卡号为："+betRechargecard3.getId()+"的充值卡";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}else if("szyw".equals(strMmlx)){
						betRechargecard3.setPassword(RandomCharData.createRandomCharData(mmws));
						betRechargecard3.setMoney(money);
						betRechargecard3.setState(0);
						betRechargecard3.setTime(date3);
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betRechargecard3.setOperator(account+"("+name+")");
						betRechargecard3.setValidity(validity);
						try{
							betRechargecardService.save(betRechargecard3);
							//操作日志
							 String details = "";
						     details = "生成卡号为："+betRechargecard3.getId()+"的充值卡";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool,ip,details,agentid,betagent.getParentid(),betagent.getParentids());
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}
				}
			}
		}else{
			try {
				java.lang.String id =betRechargecard.getId();
				if(StringUtils.isBlank(id)){
				  betRechargecard.setId(null);
				}
//				betRechargecardService.saveorupdate(betRechargecard);
			} catch (Exception e) {
				String errorMessage = e.getLocalizedMessage();
				logger.error(errorMessage,e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			}
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
		return "/lottery/betrechargecard/betrechargecardCru";
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
				betRechargecardService.deleteById(id,BetRechargecard.class);
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
			betRechargecardService.deleteByIds(ids,BetRechargecard.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
