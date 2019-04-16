package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
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
import org.springrain.lottery.entity.BetActivityCenter;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetFirstrechargerebate;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetSinglerecharge;
import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetPaymentInterfaceService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.Constant;
import org.springrain.lottery.utils.RandomCharData;
import org.springrain.lottery.utils.SSLClientGetAndPost;
import org.springrain.lottery.utils.SignUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-23 16:46:05
 * @see org.springrain.lottery.web.BetGold
 */
@Controller
@RequestMapping(value="/betgold")
public class BetGoldController  extends BaseController {
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetPaymentInterfaceService betPaymentInterfaceService;
	@Resource
	private IBetAgentService betAgentService;
	
	private String listurl="/lottery/betgold/betgoldList";
	private final static String[] TYPES = {"","银行卡","支付宝","微信","信用卡","快捷支付","扫码支付","","支付宝移动支付"};
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betGold
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetGold betGold) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		BetAgent agag = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		String company = "";//一级代理
		company = getCompany(agag, company);
		
		List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface where agentid=:agentid ").setParam("agentid", agentId));
		String memberid = betGold.getMemberid();
		BetGold betGold4=new BetGold();
		if(memberid!=null){
			betGold4.setMemberid(memberid);
		}
		Double yesterdayRecharge=0.0;
		Double todayRecharge=0.0;
		Double chargeNumber=0.;
		Integer member=0;
		Double totolRecharge=0.0;
		String date = request.getParameter("date");

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
		if("1".equals(date)){
			//按时间查询
			Page page = newPage(request);
			page.setOrder("submittime");
			page.setSort("desc");
			page.setPageSize(50);
			List<BetGold>datas=null;
			if(memberid!=null){                                          
				datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.memberid=:memberid and a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
				totolRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where memberid=:memberid and submittime>=:starttime and submittime<:endtime and state=2 and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
				chargeNumber = betGoldService.queryForObject(new Finder("select Count(*) from bet_gold where memberid=:memberid and submittime>=:starttime and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
			}else{
				Integer type = betGold.getType();
				if(type!=null){
					if(9==type){
						datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) and a.type=9 ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
					}else{
						datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) and a.type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), page, BetGold.class,null);
					}
					totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where submittime>=:starttime and state=2 and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) and type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), Double.class);
					chargeNumber=betGoldService.queryForObject(new Finder("select count(1) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) and type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), Double.class);
					member=betGoldService.queryForObject(new Finder("select count(distinct memberid) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) and type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), Integer.class);
				}else{
					datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
					totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where submittime>=:starttime and state=2 and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
					chargeNumber=betGoldService.queryForObject(new Finder("select count(1) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
					member=betGoldService.queryForObject(new Finder("select count(distinct memberid) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Integer.class);
				}
			}
			if(datas!=null){
				for (BetGold betGold2 : datas) {
					String source = betGold2.getSource();
					if(idandbanktypelist!=null){
						for (Map<String, Object> map : idandbanktypelist) {
							if(source.equals(map.get("id"))){
								betGold2.setSource((String)map.get("banktype"));
								break;
							}else{
								betGold2.setSource(null);
							}
						}
					}
					
					if(!company.equals("zhouyunfei")){
						if(!"A101".equals(agag.getParentid())){//不是一级代理或者子账号，手机号码和身份证号码都要隐藏部分
							//隐藏用户真实姓名
							if(betGold2.getAccountname()!=null){
								if (betGold2.getAccountname().length() <= 1) {        
									betGold2.setAccountname("*");
								} else {        
									betGold2.setAccountname(betGold2.getAccountname().replaceAll("([\\u4e00-\\u9fa5]{1})(.*)", "$1***"));
								}
							}
						}
					}
				}
			}
			
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setQueryBean(betGold);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
			model.addAttribute("chargeNumber", chargeNumber);
			model.addAttribute("member", member);
			model.addAttribute("totolRecharge", totolRecharge);
			if(memberid!=null){
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betgoldList1";
			}
			return listurl;
			
		
		}else if("1".equals(request.getParameter("bdjl"))){
			ReturnDatas returnObject = listjson(request, model, betGold);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
			model.addAttribute("todayRecharge", todayRecharge);
			model.addAttribute("yesterdayRecharge", yesterdayRecharge);
			model.addAttribute("totolRecharge", totolRecharge);
			if(betGold.getMemberid()!=null){
				return "/lottery/betmember/betgoldList1";
			}
			return "/lottery/betgold/betbdjlList";
			
		}else{
			if(memberid!=null){
				totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid), Double.class);
			}
			todayRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and rechargetime>=:today and (agentid=:agentid or agentparentids like :agentids ) ").setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			yesterdayRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and submittime>=:yesterday and submittime<:today and (agentid=:agentid or agentparentids like :agentids ) ").setParam("yesterday", sdf.format(cal.getTime())).setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
			if(todayRecharge==null){
				todayRecharge=0.;
			}
			if(yesterdayRecharge==null){
				yesterdayRecharge=0.;
			}
		}
		ReturnDatas returnObject = listjson(request, model, betGold);
		String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
		model.addAttribute("id2", id2);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
//		model.addAttribute("untreated", i);
		
		model.addAttribute("todayRecharge", todayRecharge);
		model.addAttribute("yesterdayRecharge", yesterdayRecharge);
		model.addAttribute("totolRecharge", totolRecharge);
		if(betGold.getMemberid()!=null){
			return "/lottery/betmember/betgoldList1";
		}
		return listurl;
	}
	
	//获取一级代理名称
	private String getCompany(BetAgent agag, String company) {
		if(agag!=null){
			if("A101".equals(agag.getParentid())){
				company = agag.getAgentid();
			}else{
				String[] spilt = agag.getParentids().split(",");//,A101,agent1,agent2
				company = spilt[2];
			}
		}
		return company;
	}
	
	/**
	 * 
	
	* @Title: accounts 
	
	* @Description: TODO 转账
	
	*  @param request
	*  @param model
	*  @param betGold
	*  @return
	*  @throws Exception  
	
	* String    返回类型 
	
	* @throws
	 */
	@RequestMapping("/accounts")
	public String accounts(HttpServletRequest request, Model model,BetGold betGold) 
			throws Exception {
		String agentId = SessionUser.getAgentId();
		List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface where agentid=:agentid ").setParam("agentid", agentId));
		String memberid = betGold.getMemberid();
		BetGold betGold4=new BetGold();
		if(memberid!=null){
			betGold4.setMemberid(memberid);
		}
		Double yesterdayRecharge=0.0;
		Double todayRecharge=0.0;
		Double chargeNumber=0.;
		Integer member=0;
		Double totolRecharge=0.0;
		String date = request.getParameter("date");

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
		if("1".equals(date)){
			//按时间查询
			Page page = newPage(request);
			page.setOrder("submittime");
			page.setSort("desc");
			page.setPageSize(50);
			List<BetGold>datas=null;
			if(memberid!=null){                                          
				datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id "
						+ "where a.memberid=:memberid and a.submittime>=:starttime and a.submittime<:endtime "
						+ "and (a.agentid=:agentid or a.agentparentids like :agentids ) and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
				totolRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where memberid=:memberid and submittime>=:starttime and submittime<:endtime and state=2 and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
				chargeNumber = betGoldService.queryForObject(new Finder("select Count(*) from bet_gold where memberid=:memberid and submittime>=:starttime and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid).setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
			}else{
				Integer type = betGold.getType();
				if(type!=null){
					datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) and a.type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), page, BetGold.class,null);
					totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where submittime>=:starttime and state=2 and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) and type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), Double.class);
					chargeNumber=betGoldService.queryForObject(new Finder("select count(1) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) and type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), Double.class);
					member=betGoldService.queryForObject(new Finder("select count(distinct memberid) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) and type=:type ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime).setParam("type", type), Integer.class);
				}else{
					datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
					totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where submittime>=:starttime and state=2 and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
					chargeNumber=betGoldService.queryForObject(new Finder("select count(1) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
					member=betGoldService.queryForObject(new Finder("select count(distinct memberid) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Integer.class);
				}
			}
			if(datas!=null){
				for (BetGold betGold2 : datas) {
					String source = betGold2.getSource();
					if(idandbanktypelist!=null){
						for (Map<String, Object> map : idandbanktypelist) {
							if(source.equals(map.get("id"))){
								betGold2.setSource((String)map.get("banktype"));
								break;
							}else{
								betGold2.setSource(null);
							}
						}
					}
				}
			}
			
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setQueryBean(betGold);
			returnObject.setPage(page);
			returnObject.setData(datas);
			
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
			model.addAttribute("chargeNumber", chargeNumber);
			model.addAttribute("member", member);
			model.addAttribute("totolRecharge", totolRecharge);
			if(memberid!=null){
				String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
				model.addAttribute("id2", id2);
				return "/lottery/betmember/betgoldList1";
			}
			return "/lottery/betgold/betgoldaccountsList";
			
		
		}else if("1".equals(request.getParameter("bdjl"))){
			ReturnDatas returnObject = listjson(request, model, betGold);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
			model.addAttribute("todayRecharge", todayRecharge);
			model.addAttribute("yesterdayRecharge", yesterdayRecharge);
			model.addAttribute("totolRecharge", totolRecharge);
			if(betGold.getMemberid()!=null){
				return "/lottery/betmember/betgoldList1";
			}
			return "/lottery/betgold/betbdjlList";
			
		}else{
			if(memberid!=null){
				totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid), Double.class);
			}
			todayRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and rechargetime>=:today and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			yesterdayRecharge = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and submittime>=:yesterday and submittime<:today and (agentid=:agentid or agentparentids like :agentids )  and type in(1,2,3)").setParam("yesterday", sdf.format(cal.getTime())).setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Double.class);
			if(todayRecharge==null){
				todayRecharge=0.;
			}
			if(yesterdayRecharge==null){
				yesterdayRecharge=0.;
			}
		}
		ReturnDatas returnObject = listjson(request, model, betGold);
		String id2 = betMemberService.queryForObject(new Finder("select id2 from bet_member where id=:memberid ").setParam("memberid", memberid), String.class);
		model.addAttribute("id2", id2);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
//		model.addAttribute("untreated", i);
		
		model.addAttribute("todayRecharge", todayRecharge);
		model.addAttribute("yesterdayRecharge", yesterdayRecharge);
		model.addAttribute("totolRecharge", totolRecharge);
		if(betGold.getMemberid()!=null){
			return "/lottery/betmember/betgoldList1";
		}
		return "/lottery/betgold/betgoldaccountsList";
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betGold
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetGold betGold) throws Exception{
		String agentId = SessionUser.getAgentId();
		if("1".equals(request.getParameter("untreated"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Integer untreatedgold = betGoldService.queryForObject(new Finder("select count(*) from bet_gold where state=0 and (agentid=:agentid or agentparentids like :agentids )").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Integer.class);
			returnObject.setData(untreatedgold);
			return returnObject;
		}
		if("2".equals(request.getParameter("untreated"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Integer untreatedgold = betGoldService.queryForObject(new Finder("select count(*) from bet_gold where state=0 and (agentid=:agentid or agentparentids like :agentids ) and type in(1,2,3)").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), Integer.class);
			returnObject.setData(untreatedgold);
			return returnObject;
		}
		List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface where agentid=:agentid ").setParam("agentid", agentId));
		String memberid = betGold.getMemberid();
		BetGold betGold4=new BetGold();
		if(memberid!=null){
			betGold4.setMemberid(memberid);
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("submittime");
		page.setSort("desc");
		page.setPageSize(50);
		List<BetGold> datas=null;
		if(!("1".equals(request.getParameter("bdjl")))){
			if(memberid!=null){
				datas = betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id  where a.submittime>=:submittime and (a.agentid=:agentid or a.agentparentids like :agentids )").setParam("submittime", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), page, BetGold.class , betGold4);
				Date date=new Date();
				String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date);
				model.addAttribute("startTime", strdate);
				model.addAttribute("endTime", strdate);
			}else{
				datas = betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id  where a.submittime>=:submittime and (a.agentid=:agentid or a.agentparentids like :agentids )").setParam("submittime", sdf.format(new Date())).setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), page, BetGold.class, betGold4);
				Date date=new Date();
				String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date);
				model.addAttribute("startTime", strdate);
				model.addAttribute("endTime", strdate);
			}
			
			if(datas!=null){
				for (BetGold betGold2 : datas) {
					String source = betGold2.getSource();
					if(idandbanktypelist!=null){
						for (Map<String, Object> map : idandbanktypelist) {
							if(source.equals((String)map.get("id"))){
								betGold2.setSource((String)map.get("banktype"));
								break;
							}else{
								betGold2.setSource(null);
							}
						}
					}
				}
			}
			Integer chargeNumber=0;
			Integer member=0;
			List<String> memberList=new ArrayList<String>();
			if(memberid!=null){
				List<BetGold> betgoldlist = betGoldService.queryForList(new Finder("select*from bet_gold where memberid=:memberid and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("memberid", memberid), BetGold.class);
				if(betgoldlist!=null){
					chargeNumber=betgoldlist.size();
				}
			}else{
				if(datas!=null){
					for (BetGold betGold2 : datas) {
						chargeNumber++;
						if(!memberList.contains(betGold2.getMemberid())){
							member++;
							memberList.add(betGold2.getMemberid());
						}
					}
				}
			}
			
			model.addAttribute("chargeNumber", chargeNumber);
			model.addAttribute("member", member);
		}
		
		if("1".equals(request.getParameter("bdjl"))){
			datas=betGoldService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id  where a.isbt=1 and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%"), BetGold.class, page);
			if(datas!=null){
				for (BetGold betGold2 : datas) {
					String source = betGold2.getSource();
					if(idandbanktypelist!=null){
						for (Map<String, Object> map : idandbanktypelist) {
							if(source.equals(map.get("id"))){
								betGold2.setSource((String)map.get("banktype"));
								break;
							}else{
								betGold2.setSource(null);
							}
						}
					}
				}
			}
		}
		returnObject.setQueryBean(betGold);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetGold betGold) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betGoldService.findDataExportExcel(null,listurl, page,BetGold.class,betGold);
		String fileName="betGold"+GlobalStatic.excelext;
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
		return "/lottery/betgold/betgoldLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String agentId = SessionUser.getAgentId();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			 BetGold betGold = betGoldService.findBetGoldById(id);
			if(agentId.equals(betGold.getAgentid())){
				 returnObject.setData(betGold);
			}else{
				 returnObject.setData(null);
			}
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
	ReturnDatas saveorupdate(Model model,BetGold betGold,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		if("1".equals(request.getParameter("ddbt"))){
			//重新提交
			BetGold betgolda = betGoldService.queryForObject(new Finder("select*from bet_gold where id=:id and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("id", betGold.getId()), BetGold.class);
			
			if(betgolda!=null){
				betGoldService.update(new Finder("update bet_gold set isresubmit=1 where id=:id ").setParam("id",betGold.getId()));
				if(betgolda.getState()!=null&&betgolda.getState()==1){
					betgolda.setState(0);
					betgolda.setBrs(null);
					betgolda.setArs(null);
					betgolda.setSubmittime(new Date());
					betgolda.setRechargetime(null);
					betgolda.setOperator(null);
					betgolda.setIsbt(1);
					betgolda.setVisiblestate(1);
					if(StringUtils.isNotBlank(betGold.getRemark())){
						betgolda.setRemark(new String(betGold.getRemark().getBytes("iso-8859-1"),"utf-8"));
					}
					String idd=null;
					for(int i=50;i>1;i--){
						String idd1="b"+RandomCharData.createData(8);
						BetGold sssss = betGoldService.queryForObject(new Finder("select*from bet_gold where id=:id ").setParam("id", idd1), BetGold.class);
						if(sssss==null){
							idd=idd1;
							break;
						}
					}
					if(idd!=null){
						betgolda.setId(idd);
						betGoldService.save(betgolda);
						//操作日志
						 String details = "";
					     details = "重新提交订单号为："+betGold.getId()+"的充值订单";
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
						return returnObject;
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("补填订单过多，无法生成ID");
						return returnObject;
					}
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("重新提交的订单状态必须取消！");
					return returnObject;
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此订单ID");
				return returnObject;
			}

		}else if("1".equals(request.getParameter("xxcz"))){
			//平台充值
			if(!(StringUtils.isNotBlank(betGold.getMemberid())&&StringUtils.isNotBlank(betGold.getId()))){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("订单号和用户id不能为空！");
				return returnObject;
			}
			BetMember betMember = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id2 ").setParam("id2", betGold.getMemberid()), BetMember.class);
			Map<String, Object> flag = betGoldService.queryForObject(new Finder("select*from bet_gold where id=:id ").setParam("id", betGold.getId()));
			if(flag!=null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("订单号已存在，请重试！");
				return returnObject;
			}else{
				if(betMember!=null){
					if(betGold.getMoney()!=null&&betGold.getMoney()>0){
						betGold.setMemberid(betMember.getId());
						betGold.setAccountname(betMember.getRealname());
						Date date=new Date();
						betGold.setSubmittime(date);
						betGold.setState(0);
						betGold.setType(9);
						betGold.setSource("");
						betGold.setRealmoney(betGold.getMoney());
						betGold.setAgentid(betMember.getAgentid());
						betGold.setAgentparentid(betMember.getAgentparentid());
						betGold.setAgentparentids(betMember.getAgentparentids());
						betGold.setIsresubmit(1);
						String account = SessionUser.getShiroUser().getAccount();
						String name = SessionUser.getShiroUser().getName();
						if(name==null){
							name="";
						}
						betGold.setOperator(account+"("+name+")");
						betGoldService.save(betGold);
						//操作日志
						 String details = "";
					     details = "提交平台充值订单号为："+betGold.getId();
					     String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
						return returnObject;
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("错误，充值金额填写有误！");
						return returnObject;
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("错误，无此会员id");
					return returnObject;
				}
			}
		}else{
			try {
				BetGold findBetGoldById = betGoldService.findBetGoldById(betGold.getId());
				if((findBetGoldById.getAgentparentids().indexOf(","+agentId+",")==-1)&&(!findBetGoldById.getAgentid().equals(agentId))){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					return returnObject;
				}
				BetMember betMember=betMemberService.queryForObject(new Finder("select*from bet_member where id=:id ").setParam("id", findBetGoldById.getMemberid()),BetMember.class);
				java.lang.String id =betGold.getId();
				if(StringUtils.isBlank(id)){
				  betGold.setId(null);
				}
				if(betGold.getId()!=null&&betGold.getState()!=null&&betGold.getMemberid()==null){
					BetGold betGold1 = betGoldService.findBetGoldById(betGold.getId());
					Integer state = betGold1.getState();
					Integer state1=betGold.getState();
					if(0==state){
						if(1==state1){
							String account = SessionUser.getShiroUser().getAccount();
							String name = SessionUser.getShiroUser().getName();
							if(name==null){
								name="";
							}
							betGold.setOperator(account+"("+name+")");
							betGold.setArs(betMember.getScore());
							betGold.setBrs(betMember.getScore());
							betGoldService.update(betGold, true);
							//操作日志
							 String details = "";
						     details = "取消订单号为："+betGold.getId()+"的充值订单";
						     String ip = IPUtils.getClientAddress(request);
						     String tool = AgentToolUtil.getAgentTool(request);
						     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
						}else if(2==state1){
							String account = SessionUser.getShiroUser().getAccount();
							String name = SessionUser.getShiroUser().getName();
							if(name==null){
								name="";
							}
							Date date=new Date();
							betGold.setOperator(account+"("+name+")");
							betGold.setRechargetime(date);
							if(betMember.getScore()==null){
								betMember.setScore(0.);
							}
							
							betGold.setArs(betMember.getScore()+betGold1.getMoney());
							betGold.setBrs(betMember.getScore());
							
							betMember.setGamescore(betMember.getGamescore()+betGold1.getMoney());
							betMember.setScore(betMember.getScore()+betGold1.getMoney());
							betMember.setExp(betMember.getExp()+betGold1.getMoney().intValue());
							betMember.setTodayexp(betMember.getTodayexp()+betGold1.getMoney().intValue());
							BetGold betGolds = betGoldService.queryForObject(new Finder("select * from bet_gold where id=:id ").setParam("id", betGold.getId()),BetGold.class);
							if (!Integer.valueOf(9).equals(betGolds.getType())
									&& !Integer.valueOf(1).equals(betGolds.getType())
									&& !Integer.valueOf(2).equals(betGolds.getType())
									&& !Integer.valueOf(3).equals(betGolds.getType())
									&& !Integer.valueOf(1001).equals(betGolds.getType())
									&& !Integer.valueOf(2001).equals(betGolds.getType())) {
								betGoldService.update(new Finder("update bet_gold set type=10 where id=:id ")
										.setParam("id", betGold.getId()));
							}
							Integer updatenum=null;
							for(int i=0;i<10;i++){
								if(updatenum==null){
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore, score=:score,exp=:exp,todayexp=:todayexp ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("exp", betMember.getExp()).setParam("todayexp", betMember.getTodayexp()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("version", betMember.getVersion()));
								}else if(updatenum==0){
									betMember=betMemberService.queryForObject(new Finder("select *from bet_member where id=:id ").setParam("id", betMember.getId()), BetMember.class);
									betMember.setGamescore(betMember.getGamescore()+betGold1.getMoney());
									betMember.setScore(betMember.getScore()+betGold1.getMoney());
									betMember.setExp(betMember.getExp()+betGold1.getMoney().intValue());
									betMember.setTodayexp(betMember.getTodayexp()+betGold1.getMoney().intValue());
									updatenum = betMemberService.update(new Finder("update bet_member set gamescore=:gamescore, score=:score,exp=:exp,todayexp=:todayexp ,version=IFNULL(version,0)+1 where version=:version and id=:id ").setParam("exp", betMember.getExp()).setParam("todayexp", betMember.getTodayexp()).setParam("gamescore", betMember.getGamescore()).setParam("id", betMember.getId()).setParam("score", betMember.getScore()).setParam("version", betMember.getVersion()));
								}else if(updatenum==1){
									break;
								}
							}	
							if(updatenum==1){
								betGoldService.update(betGold, true);
								//操作日志
								 String details = "";
							     details = "确定订单号为："+betGold.getId()+"的充值订单";
							     String ip = IPUtils.getClientAddress(request);
							     String tool = AgentToolUtil.getAgentTool(request);
							     betOptLogService.saveoptLog(tool, ip, details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
							     //金币记录
							     BetScorerecord betScorerecord=new BetScorerecord();
							     String content="";
							     try {
							    	 content=TYPES[(betGold.getType() != null && betGold.getType()>0) ? (betGold.getType()) : 0]+"充值";
								} catch (Exception e) {
									e.printStackTrace();
								}
							     betScorerecord.setMemberid2(betMember.getId2());
							     betScorerecord.setTime(date);
							     betScorerecord.setContent(content);
							     betScorerecord.setOriginalscore(betGold.getBrs());
							     betScorerecord.setChangescore(betGold1.getMoney());
							     betScorerecord.setGamescore(BigDecimal.valueOf(betMember.getGamescore()));
							     betScorerecord.setBankscore(BigDecimal.valueOf(betMember.getBankscore()));
							     betScorerecord.setFreezescore(BigDecimal.valueOf(betMember.getFreezingscore()));
							     betScorerecord.setAgentid(betMember.getAgentid());
							     betScorerecord.setAgentparentid(betMember.getAgentparentid());
							     betScorerecord.setAgentparentids(betMember.getAgentparentids());
							     betScorerecord.setOrderid(betGold.getId());
							     betScorerecord.setBalance(betGold.getArs());
							     betScorerecord.setState(1);
							     betScorerecord.setType(1);
							     betScorerecordService.saveBetScorerecord(betScorerecord);
							     //充值送金
							     payPresent(betGold.getMemberid());
								//清除缓存
								String ticket = betMember.getTicket();
								if(ticket!=null){
									try{
										ObjectMapper mapper=new ObjectMapper();
										byte[] json = mapper.writeValueAsBytes(betMember);
										
										cached.updateCached(("TICKET_"+ticket).getBytes(), json, 7L*24*60*60);
									}catch(Exception e){
										e.printStackTrace();
									}
									
								}
							}
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("充值状态只能修改一次");
						return returnObject;
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
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
		String memberid =request.getParameter("memberid");
		String random = RandomCharData.createData(8);
		model.addAttribute("random", random);
		model.addAttribute("memberid", memberid);
		return "/lottery/betgold/betgoldCrufill";
	}
	
	
	//订单查询
	@RequestMapping(value="/scancode/orderquery")
	@ResponseBody
	public ReturnDatas scancodeorderquery(HttpServletRequest request){
		String agentId = SessionUser.getAgentId();
		try{
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			ReturnDatas returnobject=ReturnDatas.getSuccessReturnDatas();
			String MERCNUM = betagent.getMercnum();
			String privateKeyStr = betagent.getPrivatekey();
			request.setCharacterEncoding("UTF-8");
			String orderId = request.getParameter("ORDERNO").toString();
			String signDataSrc = "ORDERNO="+orderId;
			String SIGN = SignUtils.Signaturer(signDataSrc, privateKeyStr);
			String payUrl = Constant.REQUEST_URI + "/Pay/trans/queryPayOrder";
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("MERCNUM", MERCNUM);
			paramMap.put("TRANDATA", signDataSrc);
			paramMap.put("SIGN", SIGN);
			
			String json =SSLClientGetAndPost.postMethodUrl1(payUrl, null, paramMap, null);
			JSONObject json_test = JSON.parseObject(json);
			if("000000".equals(json_test.getString("RECODE"))){
				returnobject.setStatus(ReturnDatas.SUCCESS);
				returnobject.setData(json_test);
				return returnobject;
			}else{
				returnobject.setStatus(ReturnDatas.ERROR);
				returnobject.setData(json_test);
				return returnobject;
			}
			
			
		} catch (Exception e){
			return ReturnDatas.getErrorReturnDatas();
		}
	}
	
	public String payPresent(String memberid) {
		try {
			//通过用户id查找用户信息
			BetMember member = betScorerecordService.queryForObject(new Finder("select * from bet_member where id=:id ").setParam("id", memberid),BetMember.class);
			if(member==null){
				return "3";
			}
			String parentids=member.getAgentparentids();
			String company="";
			if(parentids!=null&&parentids!= ""){
				String[] ids=parentids.split(",");
				if(ids.length>=3){
					company=ids[2];
				}
			}
			if(company==""){
				company = member.getAgentid();
			}
			//寻找用户充值金额
			List<BetGold> gold = betScorerecordService.queryForList(new Finder("SELECT * FROM bet_gold WHERE state=2 and rgold=0 and memberid = :memberid order by submittime desc ").setParam("memberid", memberid),BetGold.class);
			Double money = null;
			if(gold.size()!=0){
				money = gold.get(0).getRealmoney();
			}
			//寻找用户充值金额条数
			int count = betScorerecordService.queryForObject(new Finder("SELECT count(1) FROM bet_gold WHERE state=2 and memberid = :memberid ").setParam("memberid", memberid),Integer.class);
			if(money!=null){
				if(count!=1){
					BetActivityCenter bac = betScorerecordService.queryForObject(new Finder("SELECT * FROM bet_activity_center WHERE type=:type and agentid=:agentid").setParam("type", 9).setParam("agentid", company),BetActivityCenter.class);
					Date date = gold.get(0).getSubmittime();
					if(bac!=null){
						Date activitybegintime = bac.getActivitybegintime();
						Date activityendtime = bac.getActivityendtime();
						int state = bac.getState();
						//判断是否处于活动中
						if(state==1){
							if(activitybegintime.before(date)&&date.before(activityendtime)){
								String mod="dbczmode";
								int mode = betScorerecordService.queryForObject(new Finder("SELECT rebate FROM bet_subordinate_rebate WHERE remark = :remark  AND agentid=:agentid" ).setParam("remark", mod).setParam("agentid", company),Integer.class);
								String remark = "dbcz";
								//进入奖金设置表内查找充值送金额
								List<BetSubordinateRebate>  rebateList = betScorerecordService.queryForList(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark = :remark  and agentid= :agentid and betstream=:mod  order by betamounts ASC").setParam("agentid", company).setParam("mod", mode).setParam("remark", remark),BetSubordinateRebate.class);
								Double[] rebateS=new Double[rebateList.size()];
								Double[] betamountsS=new Double[rebateList.size()];
								if(rebateList!=null&&rebateList.size()>0){
									for(int i=0;i<rebateList.size();i++){
										rebateS[i] = rebateList.get(i).getRebate();
										betamountsS[i] = rebateList.get(i).getBetamounts();
									}
								}
								Double rebate=0.;
								//判断充值送金额区间
								if(rebateS.length>0&&betamountsS.length>0){
									if(mode==0){
										for(int i=1;i<rebateS.length;i++){	
												if(betamountsS[0].compareTo(money)==1){ 
													rebate = 0.;
												}else if(betamountsS[i-1].compareTo(money)!=1&&betamountsS[i].compareTo(money)==1){
													rebate = rebateS[i-1]*money;
												}else if(betamountsS[rebateS.length-1].compareTo(money)!=1){
													rebate = rebateS[rebateS.length-1]*money;
												}	
											}
									}else{
										for(int i=1;i<rebateS.length;i++){
											if(betamountsS[0].compareTo(money)==1){ 
												rebate = 0.;
											}else if(betamountsS[i-1].compareTo(money)!=1&&betamountsS[i].compareTo(money)==1){
												rebate = rebateS[i-1];
											}else if(betamountsS[rebateS.length-1].compareTo(money)!=1){
												rebate = rebateS[rebateS.length-1];
											}	
										}
									}
								}
								if(rebate>0){
								Double score = rebate+member.getScore();
								Double gameScore = rebate+member.getGamescore();
								// 金币记录
								BetScorerecord betScorerecord = new BetScorerecord();
								String content1 = "";
								content1 = "[充值送]充值" +money + "元赠送"+rebate+"成功";
								betScorerecord.setMemberid2(member.getId2());
								betScorerecord.setTime(date);
								betScorerecord.setContent(content1);
								betScorerecord.setGamescore(new BigDecimal(member.getGamescore()));
								betScorerecord.setBankscore(new BigDecimal(member.getBankscore()));
								betScorerecord.setFreezescore(new BigDecimal(member.getFreezingscore()));
								betScorerecord.setOriginalscore(member.getScore());
								betScorerecord.setChangescore(rebate);
								betScorerecord.setBalance(score);
								betScorerecord.setState(1);
								betScorerecord.setType(1);
								betScorerecord.setAgentid(member.getAgentid());
								betScorerecord.setAgentparentid(member.getAgentparentid());
								betScorerecord.setAgentparentids(member.getAgentparentids());
								betScorerecordService.saveBetScorerecord(betScorerecord);
								//用户账户冲入充值奖励金
								betScorerecordService.update(new Finder("update bet_member set score=:score,gameScore=:gameScore where id2=:memberid2").setParam("score", score).setParam("gameScore", gameScore).setParam("memberid2",member.getId2()));
								BetSinglerecharge bsrs = new BetSinglerecharge();
								bsrs.setMemberid2(member.getId2());
								bsrs.setMemberid2(member.getId2());
								bsrs.setRecharge(money);
								bsrs.setNickname(member.getNickname());
								bsrs.setReceiveip(member.getSignip());
								bsrs.setTime(date);
								bsrs.setRebate(rebate);
								bsrs.setAgentid(member.getAgentid());
								bsrs.setAgentparentid(member.getAgentparentid());
								bsrs.setAgentparentids(member.getAgentparentids());
								bsrs.setState(1);
								bsrs.setMsg(0);
								//更新充值记录
								int rgold = 1;
								String mark = "用户参与活动单笔充值奖励"+rebate+"元。";
								//充值后分
								Double ars = gold.get(0).getArs()+rebate;
								//实际进账分
								Double moneys =money+rebate;
								betScorerecordService.update(new Finder("UPDATE bet_gold set rgold=:rgold,ars=:ars,money=:money,remark=:mark where id=:id").setParam("ars", ars).setParam("money",moneys).setParam("mark", mark).setParam("rgold", rgold).setParam("id", gold.get(0).getId()));
								//记录充值奖励金
								betScorerecordService.saveBetSinglerecharge(bsrs);
								return "1";
								}
							}
						}else{
							//更新充值记录
							int rgold = 1;
							betScorerecordService.update(new Finder("UPDATE bet_gold set rgold=:rgold where id=:id").setParam("rgold", rgold).setParam("id", gold.get(0).getId()));
							return "2";
						}
					}
				}else{
					BetActivityCenter bac = betScorerecordService.queryForObject(new Finder("SELECT * FROM bet_activity_center WHERE type=:type and agentid=:agentid").setParam("type", 5).setParam("agentid", company),BetActivityCenter.class);
					Date date = new Date();
					if(bac!=null){
						Date activitybegintime = bac.getActivitybegintime();
						Date activityendtime = bac.getActivityendtime();
						int state = bac.getState();
						//判断是否处于活动中
						if(state==1){
							if(activitybegintime.before(date)&&date.before(activityendtime)){
								//进入奖金设置表内查找首充送金额
								String remark = "sc";
								List<BetSubordinateRebate> rebateList = betScorerecordService.queryForList(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark = :remark and agentid=:agentid order by betamounts ASC").setParam("agentid", company).setParam("remark", remark),BetSubordinateRebate.class);
								Double rebate=0.;
								if(rebateList!=null&&rebateList.size()>0){
									//通过比例计算奖励金
									rebate = rebateList.get(0).getRebate()*money;
								}
								if(rebate>0){
								Double score = rebate+member.getScore();
								Double gameScore = rebate+member.getGamescore();
								//用户账户充值进首充奖励金
								BetScorerecord betScorerecord = new BetScorerecord();
								String content1 = "";
								content1 = "[首充送]充值" +money + "元赠送"+rebate+"成功";
								betScorerecord.setMemberid2(member.getId2());
								betScorerecord.setTime(date);
								betScorerecord.setContent(content1);
								betScorerecord.setGamescore(new BigDecimal(member.getGamescore()));
								betScorerecord.setBankscore(new BigDecimal(member.getBankscore()));
								betScorerecord.setFreezescore(new BigDecimal(member.getFreezingscore()));
								betScorerecord.setOriginalscore(member.getScore());
								betScorerecord.setChangescore(rebate);
								betScorerecord.setBalance(score);
								betScorerecord.setState(1);
								betScorerecord.setType(1);
								betScorerecord.setAgentid(member.getAgentid());
								betScorerecord.setAgentparentid(member.getAgentparentid());
								betScorerecord.setAgentparentids(member.getAgentparentids());
								betScorerecordService.saveBetScorerecord(betScorerecord);
								//用户账户冲入充值奖励金
								betScorerecordService.update(new Finder("update bet_member set score=:score,gameScore=:gameScore where id2=:memberid2").setParam("score", score).setParam("gameScore", gameScore).setParam("memberid2",member.getId2()));
								BetFirstrechargerebate bfrs = new BetFirstrechargerebate();
								bfrs.setMemberid2(member.getId2());
								bfrs.setNickname(member.getNickname());
								bfrs.setRecharge(money);
								bfrs.setBettingmoney(member.getScore());
								bfrs.setRebate(rebate);
								bfrs.setReceiveip(member.getSignip());
								bfrs.setBankscore(member.getGamescore());
								bfrs.setGamescore(member.getBankscore());
								bfrs.setDate(date);
								bfrs.setAgentid(member.getAgentid());
								bfrs.setAgentparentid(member.getAgentparentid());
								bfrs.setAgentParentids(member.getAgentparentids());
								bfrs.setState(1);
								bfrs.setMsg(0);
								//更新充值记录
								int rgold = 1;
								String mark = "用户参与活动首充送金"+rebate+"元。";
								//充值后分
								Double ars = gold.get(0).getArs()+rebate;
								//实际进账分
								Double moneys =money+rebate;
								betScorerecordService.update(new Finder("UPDATE bet_gold set rgold=:rgold,ars=:ars,money=:money,remark=:mark where id=:id").setParam("ars", ars).setParam("money",moneys).setParam("mark", mark).setParam("rgold", rgold).setParam("id", gold.get(0).getId()));
								//加入首充送记录
								betScorerecordService.saveBetFirstrechargerebate(bfrs);
								return "1";
								}
							}
						}else{
							//更新充值记录
							int rgold = 1;
							betScorerecordService.update(new Finder("UPDATE bet_gold set rgold=:rgold where id=:id").setParam("rgold", rgold).setParam("id", gold.get(0).getId()));
							return "3";
						}
					}
				}
			}
			return "3";
		} catch (Exception e) {
			e.printStackTrace();
			return "3";
		}
	}
}
