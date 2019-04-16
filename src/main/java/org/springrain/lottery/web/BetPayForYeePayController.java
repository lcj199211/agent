package  org.springrain.lottery.web;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetPayforYeePay;
import org.springrain.lottery.entity.BetPayforannotherDict;
import org.springrain.lottery.entity.BetPayforanother;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetPayforYeePayService;
import org.springrain.lottery.service.IBetPayforannotherDictService;
import org.springrain.lottery.service.IBetPayforannotherService;
import org.springrain.lottery.service.IBetWithdrawcashService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.daifu.TdExpBasicFunctions;
import org.springrain.lottery.utils.daifu.YeePayConfig;

import com.sun.tools.internal.jxc.gen.config.Config;
import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;

import freemarker.template.utility.StringUtil;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author 无名
 * @version  2019-03-25 13:58:21
 * @see org.springrain.lottery.web.betpayforyeepay
 */
@Controller
@RequestMapping(value="/betpayforyeepay")
public class BetPayForYeePayController  extends BaseController {
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetPayforannotherService betPayforannotherService;
	@Resource
	private IBetPayforYeePayService betPayforYeePayService;
	@Resource
	private IBetPayforannotherDictService betPayforannotherDictService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetOptLogService betOptLogService;
	
	
	/**
	 * 用户提现代付
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnDatas payforyeepay(Model model,BetWithdrawcash betWithdrawcash,HttpServletRequest request) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			//BetWithdrawcash这是提现申请列表的实体
			BetWithdrawcash betWithdrawcash1 = betWithdrawcashService.findBetWithdrawcashById(betWithdrawcash.getId());
			
			BetMember betMember = betMemberService.findBetMemberById(betWithdrawcash1.getMemberid());
			BetPayforYeePay betPayforYeePay_isPayed = betPayforYeePayService.queryForObject(new Finder("select * from bet_payforyeepay where weithdrawcashId=:weithdrawcashId")
					.setParam("weithdrawcashId", betWithdrawcash1.getId()), BetPayforYeePay.class);
			
			//判断用户是否存在
			if(betMember==null||betMember.getIsinternal()==1){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此用户或为内部用户");
				return returnObject;
			}
			//这里得出发起过的会保存在   BetPayforanother
			if(betPayforYeePay_isPayed !=null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("已发起代付");
				return returnObject;
			}
			java.lang.String id =betWithdrawcash.getId();
			if(StringUtils.isBlank(id)){
			  betWithdrawcash.setId(null);
			}
			String bankofdeposit = betWithdrawcash1.getBankofdeposit();
			if(!YeePayConfig.isExistKey(bankofdeposit)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此开户行不可使用易宝代付，请选择其他代付！");
				return returnObject;
			}
			
			//易宝代付
			if(betWithdrawcash.getId()!=null&&betWithdrawcash.getState()!=null&&betWithdrawcash.getMemberid()==null){
				String agentid=null;
				String[] agentids = betMember.getAgentparentids().split(",");
				if(agentids.length>2) {
					agentid = agentids[2];
				}else {
					agentid = betMember.getAgentid();
				}
				
				List<Map<String, Object>> betPayforYeePayDict = betPayforYeePayService.queryForList(new Finder("select * from bet_payforyeepay_dict where id=:id").setParam("id", agentId));
				if(betPayforYeePayDict!=null) {
					String privatekey = betPayforYeePayDict.get(0).get("privatekey").toString();//私钥
					String merid = betPayforYeePayDict.get(0).get("merid").toString();//商户id
					YopRequest yoprequest = new YopRequest("OPR:"+merid, privatekey);
					yoprequest.addParam("customerNumber", merid);
					//集团商户编号
					yoprequest.addParam("groupNumber", merid);
					//批次号/流水号---长度18，仅数字 
					String batchNo = TdExpBasicFunctions.RANDOM(18, "2");
					yoprequest.addParam("batchNo", batchNo);
					//订单号---长度19，允许数字、字母
					String ordId = TdExpBasicFunctions.RANDOM(19, "0");
					yoprequest.addParam("orderId", ordId);
					//出款金额---单位：元，非负浮点数，保留2位小数
					Double txnAmt = betWithdrawcash1.getMoney();
					DecimalFormat df = new DecimalFormat("#.00");
					yoprequest.addParam("amount", df.format(txnAmt));
					//账户名----收款帐户的开户名称
					yoprequest.addParam("accountName", betWithdrawcash1.getRealname());
					//卡号---收款帐户的卡号
					yoprequest.addParam("accountNumber", betWithdrawcash1.getAccount());
					//银行名称
					yoprequest.addParam("bankName", bankofdeposit);
					//银行编码
					String bankCode=YeePayConfig.getProperty(bankofdeposit);
					yoprequest.addParam("bankCode", bankCode);
					//手续费方式;SOURCE：商户承担 TARGET：用户承担
					yoprequest.addParam("feeType", "SOURCE"); 
					
					YopResponse response = YopClient3.postRsa("/rest/v1.0/balance/transfer_send",yoprequest);
					
					if(response.getState().equals("SUCCESS")) { 
						//记录代付
						BetPayforYeePay betPayforYeePay = new BetPayforYeePay();
						betPayforYeePay.setCompany(agentid);
						betPayforYeePay.setAccountNo(betWithdrawcash1.getAccount());
						betPayforYeePay.setTxnAmt(betWithdrawcash1.getMoney());
						betPayforYeePay.setOrderId(ordId);
						String time = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss").format(new Date());
						betPayforYeePay.setTranDate(time);
						betPayforYeePay.setTranId(batchNo);
						betPayforYeePay.setCreatetime(new Date());
						betPayforYeePay.setWeithdrawcashId(betWithdrawcash1.getId());
						betPayforYeePay.setState(0);
						
						betPayforYeePayService.save(betPayforYeePay);
						
						//更为处理中
						betWithdrawcash1.setState(3);//状态： 0、已申请；1、取消；2、已处理；3、代付处理中；4、代付失败；5、代付成功；
						betWithdrawcashService.update(betWithdrawcash1);
						//操作日志
						String details = "";
					    details = "发起ID为："+betMember.getId2()+"的用户提现易支付代付"+betWithdrawcash1.getMoney()+"元的申请";
					    String ip = IPUtils.getClientAddress(request);
					    String tool = AgentToolUtil.getAgentTool(request);
					    betOptLogService.saveoptLog(tool,ip,details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
					}else {
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage(response.getError().getMessage());
					}
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
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betNotice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetPayforanother betPayforanother) 
			throws Exception {
		Page page = newPage(request);
		page.setOrder("createtime");
		page.setSort("desc");
		String starttime = request.getParameter("startTime");
		String endtime = request.getParameter("endTime");
		String userId = request.getParameter("userId");
		String state = request.getParameter("state");

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
		List<Map<String, Object>> datas=new ArrayList<>();
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append(" select * from (select yeepay.*,member.id2  from bet_payforyeepay yeepay left join bet_withdrawcash cash on yeepay.weithdrawcashId=cash.id ");
		stringBuffer.append(" left join bet_member member on member.id=cash.memberid ");
		stringBuffer.append(" where 1=1 and createtime>=:starttime and createtime<:endtime");
		if(userId!=null&&StringUtil.emptyToNull(userId)!=null){
			stringBuffer.append(" and id2 =:userId  ");
			model.addAttribute("userId", userId);
		}
		if(state!=null&&StringUtil.emptyToNull(state)!=null){
			stringBuffer.append(" and yeepay.state =:state  ");
			betPayforanother.setState(Integer.valueOf(state));
		}
		
		stringBuffer.append(" ) tableYeepay ");
	
		datas=betPayforannotherService.queryForList(new Finder(stringBuffer.toString()).setParam("starttime",starttime ).setParam("endtime", endtime).setParam("userId",userId ).setParam("state",state ), page);
		
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
		
		//昨日代付，今日代付
		Double yesterdayPay=0.0;
		Double todayPay=0.0;
		String agentId = SessionUser.getAgentId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		todayPay = betPayforannotherService.queryForObject(new Finder("select sum(txnAmt) from bet_payforyeepay yeepay left join bet_withdrawcash cash on yeepay.weithdrawcashId=cash.id  where cash.state=5 and createtime>=:today and (agentid=:agentid or agentparentids like :agentids ) ").setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
		yesterdayPay = betPayforannotherService.queryForObject(new Finder("select sum(txnAmt) from bet_payforyeepay yeepay left join bet_withdrawcash cash on yeepay.weithdrawcashId=cash.id where cash.state=5 and createtime>=:yesterday and createtime<:today and (agentid=:agentid or agentparentids like :agentids ) ").setParam("yesterday", sdf.format(cal.getTime())).setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
		if(todayPay==null){
			todayPay=0.;
		}
		if(yesterdayPay==null){
			yesterdayPay=0.;
		}
		model.addAttribute("todayRecharge", todayPay);
		model.addAttribute("yesterdayRecharge", yesterdayPay);
		
		returnObject.setQueryBean(betPayforanother);
		returnObject.setPage(page);
		returnObject.setData(datas);
		
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betpayforyeepay/betpayforyeepayList";
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betNotice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
			ReturnDatas listjson(HttpServletRequest request, Model model, BetPayforanother betPayforanother)
					throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(30);
		// ==执行分页查询
		List<BetPayforanother> datas = betPayforannotherService.findListDataByFinder(null, page, BetPayforanother.class,
				betPayforanother);
		returnObject.setQueryBean(betPayforanother);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
}
