package  org.springrain.lottery.web;

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
import org.springrain.lottery.entity.BetPayforannotherDict;
import org.springrain.lottery.entity.BetPayforanother;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetPayforannotherDictService;
import org.springrain.lottery.service.IBetPayforannotherService;
import org.springrain.lottery.service.IBetWithdrawcashService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.daifu.HttpApi;
import org.springrain.lottery.utils.daifu.JUtil;
import org.springrain.lottery.utils.daifu.SecurityUtil;
import org.springrain.lottery.utils.daifu.TdExpBasicFunctions;

import freemarker.template.utility.StringUtil;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 09:58:21
 * @see org.springrain.lottery.web.betpayforannother
 */
@Controller
@RequestMapping(value="/betpayforanother")
public class BetPayforannotherController  extends BaseController {
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetPayforannotherService betPayforannotherService;
	@Resource
	private IBetPayforannotherDictService betPayforannotherDictService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetOptLogService betOptLogService;
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> payforanother(Map<String, Object> data,String agentid) throws Exception {
		BetPayforannotherDict betPayforannotherDict =  betPayforannotherDictService.findBetPayforannotherDictById(agentid);
		if(betPayforannotherDict!=null) {
			HttpApi http = new HttpApi(betPayforannotherDict.getUrl(), HttpApi.POST);
			Map<String, Object> hdata = new HashMap<String, Object>();
			data.put("merId", betPayforannotherDict.getMerid());
			data.put("nonceStr", TdExpBasicFunctions.RANDOM(16, "0"));
			
			String sign = HttpApi.getSign(data, betPayforannotherDict.getMd5key());
			System.out.println(sign);
			sign = SecurityUtil.sign(sign, betPayforannotherDict.getPrivatekey(), true);
			
			hdata.put("sign", sign);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("REQ_BODY", data);
			map.put("REQ_HEAD", hdata);
			String string = JUtil.toJsonString(map);
			System.out.println(string);
			String rdata = http.post(string);
			System.out.println(":" + rdata);
			Map<String, Object> rmap = JUtil.toMap(rdata);
			System.out.println(rmap);
			
			Map<String, Object> _body =  (Map<String, Object>) rmap.get("REP_BODY");
			Map<String, Object> _head = (Map<String, Object>) rmap.get("REP_HEAD");
			System.out.println(rmap);
			String vsign = HttpApi.getSign(_body, betPayforannotherDict.getMd5key());
			System.out.println(vsign);
			
			String _sign = _head.get("sign").toString();
			boolean flag = SecurityUtil.verify(vsign, _sign, betPayforannotherDict.getPublickey(), true);
			if(!flag) {
				_body.put("rspcode", 100012);
				_body.put("rspmsg", "秘钥验证失败");
			}
			
			return _body;
		}
		return null;

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
		stringBuffer.append(" select * from (select another.*,member.id2  from bet_payforanother another left join bet_withdrawcash cash on another.weithdrawcashId=cash.id ");
		stringBuffer.append(" left join bet_member member on member.id=cash.memberid ");
		stringBuffer.append(" where 1=1 and createtime>=:starttime and createtime<:endtime");
		if(userId!=null&&StringUtil.emptyToNull(userId)!=null){
			stringBuffer.append(" and id2 =:userId  ");
			model.addAttribute("userId", userId);
		}
		if(state!=null&&StringUtil.emptyToNull(state)!=null){
			stringBuffer.append(" and another.state =:state  ");
			betPayforanother.setState(Integer.valueOf(state));
		}
		
		stringBuffer.append(" ) tableAnother ");
	
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
		todayPay = betPayforannotherService.queryForObject(new Finder("select sum(txnAmt) from bet_payforanother another left join bet_withdrawcash cash on another.weithdrawcashId=cash.id  where cash.state=5 and createtime>=:today and (agentid=:agentid or agentparentids like :agentids ) ").setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
		yesterdayPay = betPayforannotherService.queryForObject(new Finder("select sum(txnAmt) from bet_payforanother another left join bet_withdrawcash cash on another.weithdrawcashId=cash.id where cash.state=5 and createtime>=:yesterday and createtime<:today and (agentid=:agentid or agentparentids like :agentids ) ").setParam("yesterday", sdf.format(cal.getTime())).setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
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
		return "/lottery/betpayforanother/betpayforanotherList";
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
	
	/**
	 * 用户提现代付
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ReturnDatas saveorupdatepay(Model model,BetWithdrawcash betWithdrawcash,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			BetWithdrawcash betWithdrawcash1 = betWithdrawcashService.findBetWithdrawcashById(betWithdrawcash.getId());
			BetMember betMember = betMemberService.findBetMemberById(betWithdrawcash1.getMemberid());
			BetPayforanother betPayforanother_regist = betPayforannotherService.queryForObject(new Finder("select * from bet_payforanother where weithdrawcashId=:weithdrawcashId")
					.setParam("weithdrawcashId", betWithdrawcash1.getId()), BetPayforanother.class);
			if(betMember==null||betMember.getIsinternal()==1){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此用户或为内部用户");
				return returnObject;
			}
			if(betPayforanother_regist !=null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("已发起代付");
				return returnObject;
			}
			java.lang.String id =betWithdrawcash.getId();
			if(StringUtils.isBlank(id)){
			  betWithdrawcash.setId(null);
			}
			if(betWithdrawcash.getId()!=null&&betWithdrawcash.getState()!=null&&betWithdrawcash.getMemberid()==null){
				Map<String, Object> data = new HashMap<String, Object>();
				Double txnAmt = betWithdrawcash1.getMoney()*100;
				data.put("tranCode", "20000");   //交易号  看文档
				data.put("txnAmt", String.valueOf(txnAmt));//商户订单金额 单位分
				data.put("accountNo", betWithdrawcash1.getAccount());// 卡号
				data.put("bankName", TdExpBasicFunctions.STR2HEX(betWithdrawcash1.getPaymentmethod()));// 银行名称
				data.put("accountName", TdExpBasicFunctions.STR2HEX(betWithdrawcash1.getRealname()));// 持卡人
				data.put("bankBranch", TdExpBasicFunctions.STR2HEX(betWithdrawcash1.getBankofdeposit()));
				if(betWithdrawcash1.getPaymentmethod().equals("支付宝")) {
					data.put("accountType", "1");//类型 0 银行卡 1 支付宝
				}else {
					data.put("accountType", "0");//类型 0 银行卡 1 支付宝
				}
				String ordId = TdExpBasicFunctions.RANDOM(19, "2");
				data.put("orderId", ordId);
				String time = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss").format(new Date());
				data.put("tranDate", time);
				
//				data.put("certNum", "411024199101017012");// 身份证号
//				data.put("bankCode", "CMB");// 银行编码
//				data.put("bankProv", "310000");// 开户省
//				data.put("bankCity", "310100");// 开户市
//				data.put("cnaps", "308290003298");// 联行号
//				data.put("bankBranch", TdExpBasicFunctions.STR2HEX("招商银行股份有限公司上海天山支行"));// 支行
				data.put("mobile", betWithdrawcash.getMobile());
				String agentid=null;
				String[] agentids = betMember.getAgentparentids().split(",");
				if(agentids.length>2) {
					agentid = agentids[2];
				}else {
					agentid = betMember.getAgentid();
				}
				Map<String, Object> rmap = payforanother(data, agentid);
				String rspcode = rmap.get("rspcode").toString();
				if(rspcode.equals("10007")) {
					//记录代付
					BetPayforanother betPayforanother = new BetPayforanother();
					betPayforanother.setCompany(agentid);
					betPayforanother.setAccountNo(betWithdrawcash1.getAccount());
					betPayforanother.setTxnAmt(betWithdrawcash1.getMoney());
					betPayforanother.setOrderId(ordId);
					betPayforanother.setTranDate(time);
					betPayforanother.setTranId(String.valueOf(rmap.get("tranId")));
					betPayforanother.setCreatetime(new Date());
					betPayforanother.setWeithdrawcashId(betWithdrawcash1.getId());
					betPayforanother.setState(0);
					betPayforannotherService.save(betPayforanother);
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
					returnObject.setMessage(rmap.get("rspmsg").toString());
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
	 * 
	
	* @Title: querypay 
	
	* @Description: TODO 代付状态查询 (需要改下才能用)
	
	*  @param model
	*  @param betPayforanother
	*  @param request
	*  @param response
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/querypay")
	public ReturnDatas querypay(Model model,BetPayforanother betPayforanother,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("tranCode", "20001");   //交易号  看文档
				data.put("orderId", betPayforanother.getOrderId());//订单编号
//				String time = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss").format();
				data.put("tranDate", betPayforanother.getTranDate());
				Map<String, Object> rmap =payforanother(data,betPayforanother.getCompany());
				String rspcode = rmap.get("rspcode").toString();
				if(rspcode.equals("00000")) {
					//记录代付
					betPayforanother.setState(2);
					betPayforannotherService.update(betPayforanother);
				}else {
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(rmap.get("rspmsg").toString());
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
	 * 
	
	* @Title: querybalance 
	
	* @Description: TODO 商户余额查询 (需要改下才能用)
	
	*  @param model
	*  @param betWithdrawcash
	*  @param request
	*  @param response
	*  @return
	*  @throws Exception  
	
	* ReturnDatas    返回类型 
	
	* @throws
	 */
	@RequestMapping("/querybalance")
	public ReturnDatas querybalance(Model model,BetPayforanother betPayforanother,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
				Map<String, Object> data = new HashMap<String, Object>();
				Double money = 100.0;
				Double txnAmt = money*100;
				data.put("tranCode", "20002");   //交易号  看文档
				data.put("orderId", String.valueOf(txnAmt));//订单编号
				String time = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss").format(new Date());
				data.put("tranDate", time);
//				data.put("mobile", betWithdrawcash.getMobile());
				Map<String, Object> rmap = payforanother(data,betPayforanother.getCompany());
				returnObject.setData(rmap);
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	

}
