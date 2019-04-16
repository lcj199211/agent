package  org.springrain.lottery.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.lottery.entity.BetSubordinaterebateDetail;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetSubordinateRebateService;
import org.springrain.lottery.service.IBetSubordinaterebateDetailService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-08 09:58:06
 * @see org.springrain.lottery.web.BetSubordinaterebateDetail
 */
@Controller
@RequestMapping(value="/betsubordinaterebatedetail")
public class BetSubordinaterebateDetailController  extends BaseController {
	@Resource
	private IBetSubordinaterebateDetailService betSubordinaterebateDetailService;
	@Resource
	private IBetSubordinateRebateService betSubordinateRebateService;
	@Resource
	private IBetMemberService betMemberServiceImpl;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetBettingService betBettingServiceImpl;
	
	private String listurl="/lottery/betsubordinaterebatedetail/betsubordinaterebatedetailList";
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betSubordinaterebateDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetSubordinaterebateDetail betSubordinaterebateDetail) 
			throws Exception {
		 if("1".equals(request.getParameter("subordinatedetailrebate"))){
//			String id2 = request.getParameter("memberid2");
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			page.setOrder("subtime");
			page.setSort("desc");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			Integer id2 = betSubordinaterebateDetail.getMemberid2();
			if(id2!=null){
				List<Map<String, Object>> datas = betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime>=:starttime and subtime<=:endtime and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("parentmemberid2", betSubordinaterebateDetail.getMemberid2()).setParam("starttime",starttime ).setParam("endtime", endtime),page);
				returnObject.setQueryBean(betSubordinaterebateDetail);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("id2", id2);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				if("0000-00-00".equals(starttime)){
					starttime="";
				}
				if("9999-00-00".equals(endtime)){
					endtime="";
				}
				model.addAttribute("startTime", starttime);
				model.addAttribute("endTime", endtime);
				return "/lottery/betsubordinaterebatedetail/betsubordinaterebatedetailList2";			
			}else{
				return "/errorpage/error";
			}
			
		}else{
			ReturnDatas returnObject = listjson(request, model, betSubordinaterebateDetail);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betSubordinaterebateDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetSubordinaterebateDetail betSubordinaterebateDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
//		page.setPageSize(50);
//		page.setOrder("subtime");
//		page.setSort("desc");
		String dateDetail = request.getParameter("date");
		List<BetSubordinaterebateDetail> datas = null;
		List<Map<String, Object>> datas2=new ArrayList<>();
		String starttime = "";
		String endtime = "";
		if(StringUtils.isNotEmpty(dateDetail)){
			if("today".equals(dateDetail)){
//				Date date=new Date();
//    			//用户推广返利
//			 	List<BetSubordinateRebate> subordinaterebatelist = betSubordinateRebateService.queryForList(new Finder("select *from bet_subordinate_rebate where remark=:t ").setParam("t", "t"), BetSubordinateRebate.class);
//				page.setPageSize(10);
//				page.setOrder("id2");
//			 	List<BetMember> betmemberlist1 = betMemberServiceImpl.queryForList(new Finder("select id2,nickname,subordinate from bet_member where status=1 and subordinate>0 "), BetMember.class,page);
//				
//				if(betmemberlist1!=null){
//					for (BetMember betMember : betmemberlist1) {
//						List<BetMember> subordinatelist = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and parentid=:parentid ").setParam("parentid", betMember.getId2()), BetMember.class);
//						Double sb=0.;
//						Double sc=0.;
//						Double sl=0.;
//						
//						if(subordinatelist!=null){
//							for (BetMember betMember2 : subordinatelist) {
//								Double dayscore = betMember2.getDayscore();
////								Integer todayexp = betMember2.getTodayexp();
//								Double todayexp = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid  and TO_DAYS(rechargetime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date",date), Double.class);
//								Double bettingmoney = betBettingServiceImpl.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where memberid=:memberid and TO_DAYS(bettingtime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date", date), Double.class);
//								if(bettingmoney==null){
//								   bettingmoney=0.;
//								}
//								if(dayscore==null){
//									dayscore=0.;
//								}
//								if(todayexp==null){
//									todayexp=0.;
//								}
//								betMember2.setSbproportion(bettingmoney);
//								betMember2.setScproportion(todayexp);
//								sb+=bettingmoney;
//								sc+=todayexp;
//								if(dayscore<0){
//									betMember2.setSlproportion(-dayscore);
//									sl-=dayscore;
//								}else{
//									betMember2.setSlproportion(0.);
//								}
//								
//							}
//							
//							//下线投注流水返利比例
//							Double sbrebate=0.;
//							Double sb1=null;
//							Double sb11=0.;
//							//下线充值返利比例
//							Double screbate=0.;
//							Double sb2=null;
//							Double sb22=0.;
//							//下线输返利比例
//							Double slrebate=0.;
//							Double sb3=null;
//							Double sb33=0.;
//							if(subordinaterebatelist!=null){
//								for(BetSubordinateRebate bsr:subordinaterebatelist){
//									Double betstream = bsr.getBetstream();
//									Double betamounts= bsr.getBetamounts();
//									Double losescore = bsr.getLosescore();
//									Double rebate = bsr.getRebate();
//									if(betstream!=null){
//										if(sb<betstream){
//											if(sb1==null){
//												sb11=rebate;
//												sb1=betstream;
//											}else{
//												if(betstream<sb1){
//													sb11=rebate;
//													sb1=betstream;
//												}
//											}
//										}
//									}
//									if(betamounts!=null){
//										if(sb>betamounts){
//											if(sb2==null){
//												sb22=rebate;
//												sb2=betamounts;
//											}else{
//												if(betamounts<sb2){
//													sb22=rebate;
//													sb2=betamounts;
//												}
//											}
//										}
//									}
//									if(losescore!=null){
//										if(sl>losescore){
//											if(sb3==null){
//												sb33=rebate;
//												sb3=losescore;
//											}else{
//												if(losescore>sb3){
//													sb33=rebate;
//													sb3=losescore;
//												}
//											}
//										}
//									}
//								}
//							}
//							Map<String,Object>map=new HashMap<String,Object>();
//							map.put("subtime", date);
//							map.put("parentnickname", betMember.getNickname());
//							map.put("parentmemberid2",betMember.getId2());
//							map.put("totalsb",sb);
//							map.put("recommendnum",betMember.getSubordinate());
//							map.put("totalsubordinaterecharge",sc*sb22);
//							map.put("totalsubordinatebet",sb*sb11);
//							map.put("totalsubordinatelose",sl*sb33);
//							map.put("totalincome",sc*sb22+sb*sb11+sl*sb33);
//							map.put("state",0);
//							
//							datas2.add(map);
////							betSubordinaterebateDetail1.setParentmemberid2(betMember.getId2());
////							betSubordinaterebateDetail1.setMemberid2(betMember2.getId2());
////							betSubordinaterebateDetail1.setNickname(betMember2.getNickname());
////							betSubordinaterebateDetail1.setParentnickname(betMember.getNickname());
////							betSubordinaterebateDetail1.setRecommendnum(betMember.getSubordinate());
////							betSubordinaterebateDetail1.setAgentid(betMember2.getAgentid());
////							betSubordinaterebateDetail1.setAgentparentid(betMember2.getAgentparentid());
////							betSubordinaterebateDetail1.setAgentparentids(betMember2.getAgentparentids());
////							betSubordinaterebateDetail1.setSb(betMember2.getSbproportion());
////							betSubordinaterebateDetail1.setSubordinatebet(sb11*betMember2.getSbproportion());
////							betSubordinaterebateDetail1.setSc(betMember2.getScproportion());
////							betSubordinaterebateDetail1.setSubordinaterecharge(betMember2.getScproportion()*sb22);
////							betSubordinaterebateDetail1.setSl(betMember2.getSlproportion());
////							betSubordinaterebateDetail1.setSubordinatelose(betMember2.getSlproportion()*sb33);
////							betSubordinaterebateDetail1.setIncome(betSubordinaterebateDetail.getSubordinatebet()+betSubordinaterebateDetail.getSubordinaterecharge()+betSubordinaterebateDetail.getSubordinatelose());
////							betSubordinaterebateDetail1.setSubtime(date);
////							betSubordinaterebateDetail1.setState(0);
////								betSubordinateRebateDetailService.save(betSubordinaterebateDetail1);
//						}
//					}
//				}
//    		
//				
//				String time = DateUtils.convertDate2String(date);
//				starttime = time;
//				endtime = time;
			}else if("week".equals(dateDetail)){
				datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE YEARWEEK(SUBTIME,1)=YEARWEEK(NOW(),1) group by parentmemberid2 ,subtime "), page);
//				datas=betSubordinaterebateDetailService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinaterebate_detail WHERE YEARWEEK(DATE_ADD(SUBTIME,INTERVAL -1 DAY))=YEARWEEK(NOW())"),page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int d = 0;
				if(cal.get(Calendar.DAY_OF_WEEK)==1){
					d = -6;
				}else{
					d = 2-cal.get(Calendar.DAY_OF_WEEK);
				}
				cal.add(Calendar.DAY_OF_WEEK, d);
				//所在周开始日期
				starttime =DateUtils.convertDate2String("yyyy-MM-dd", cal.getTime());
				cal.add(Calendar.DAY_OF_WEEK, 6);
				//所在周结束日期
				endtime =DateUtils.convertDate2String("yyyy-MM-dd", cal.getTime());

//				Date date=new Date();
    			//用户推广返利
//			 	List<BetSubordinateRebate> subordinaterebatelist = betSubordinateRebateService.queryForList(new Finder("select *from bet_subordinate_rebate where remark=:t ").setParam("t", "t"), BetSubordinateRebate.class);
//				List<BetMember> betmemberlist1 = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and subordinate>0 "), BetMember.class);
//				if(betmemberlist1!=null){
//					for (BetMember betMember : betmemberlist1) {
//						List<BetMember> subordinatelist = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and parentid=:parentid ").setParam("parentid", betMember.getId2()), BetMember.class);
//						Double sb=0.;
//						Double sc=0.;
//						Double sl=0.;
//						
//						if(subordinatelist!=null){
//							for (BetMember betMember2 : subordinatelist) {
//								Double dayscore = betMember2.getDayscore();
////								Integer todayexp = betMember2.getTodayexp();
//								Double todayexp = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid  and TO_DAYS(rechargetime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date",date), Double.class);
//								Double bettingmoney = betBettingServiceImpl.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where memberid=:memberid and TO_DAYS(bettingtime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date", date), Double.class);
//								if(bettingmoney==null){
//								   bettingmoney=0.;
//								}
//								if(dayscore==null){
//									dayscore=0.;
//								}
//								if(todayexp==null){
//									todayexp=0.;
//								}
//								betMember2.setSbproportion(bettingmoney);
//								betMember2.setScproportion(todayexp);
//								sb+=bettingmoney;
//								sc+=todayexp;
//								if(dayscore<0){
//									betMember2.setSlproportion(-dayscore);
//									sl-=dayscore;
//								}else{
//									betMember2.setSlproportion(0.);
//								}
//								
//							}
//							
//							//下线投注流水返利比例
//							Double sbrebate=0.;
//							Double sb1=null;
//							Double sb11=0.;
//							//下线充值返利比例
//							Double screbate=0.;
//							Double sb2=null;
//							Double sb22=0.;
//							//下线输返利比例
//							Double slrebate=0.;
//							Double sb3=null;
//							Double sb33=0.;
//							if(subordinaterebatelist!=null){
//								for(BetSubordinateRebate bsr:subordinaterebatelist){
//									Double betstream = bsr.getBetstream();
//									Double betamounts= bsr.getBetamounts();
//									Double losescore = bsr.getLosescore();
//									Double rebate = bsr.getRebate();
//									if(betstream!=null){
//										if(sb<betstream){
//											if(sb1==null){
//												sb11=rebate;
//												sb1=betstream;
//											}else{
//												if(betstream<sb1){
//													sb11=rebate;
//													sb1=betstream;
//												}
//											}
//										}
//									}
//									if(betamounts!=null){
//										if(sb>betamounts){
//											if(sb2==null){
//												sb22=rebate;
//												sb2=betamounts;
//											}else{
//												if(betamounts<sb2){
//													sb22=rebate;
//													sb2=betamounts;
//												}
//											}
//										}
//									}
//									if(losescore!=null){
//										if(sl>losescore){
//											if(sb3==null){
//												sb33=rebate;
//												sb3=losescore;
//											}else{
//												if(losescore>sb3){
//													sb33=rebate;
//													sb3=losescore;
//												}
//											}
//										}
//									}
//								}
//							}
//							Map<String,Object>map=new HashMap<String,Object>();
//							map.put("subtime", date);
//							map.put("parentnickname", betMember.getNickname());
//							map.put("parentmemberid2",betMember.getId2());
//							map.put("totalsb",sb);
//							map.put("recommendnum",betMember.getSubordinate());
//							map.put("totalsubordinaterecharge",sc*sb22);
//							map.put("totalsubordinatebet",sb*sb11);
//							map.put("totalsubordinatelose",sl*sb33);
//							map.put("totalincome",sc*sb22+sb*sb11+sl*sb33);
//							map.put("state",0);
//							
//							
//							
//							if(page.getPageIndex()!=null&&1==page.getPageIndex()){
//								if(datas2==null){
//									datas2=new ArrayList<>();
//								}
//								datas2.add(0, map);
//      						 }
//      						 
//							 page.setTotalCount(page.getTotalCount()+1);
//      						 
////							betSubordinaterebateDetail1.setParentmemberid2(betMember.getId2());
////							betSubordinaterebateDetail1.setMemberid2(betMember2.getId2());
////							betSubordinaterebateDetail1.setNickname(betMember2.getNickname());
////							betSubordinaterebateDetail1.setParentnickname(betMember.getNickname());
////							betSubordinaterebateDetail1.setRecommendnum(betMember.getSubordinate());
////							betSubordinaterebateDetail1.setAgentid(betMember2.getAgentid());
////							betSubordinaterebateDetail1.setAgentparentid(betMember2.getAgentparentid());
////							betSubordinaterebateDetail1.setAgentparentids(betMember2.getAgentparentids());
////							betSubordinaterebateDetail1.setSb(betMember2.getSbproportion());
////							betSubordinaterebateDetail1.setSubordinatebet(sb11*betMember2.getSbproportion());
////							betSubordinaterebateDetail1.setSc(betMember2.getScproportion());
////							betSubordinaterebateDetail1.setSubordinaterecharge(betMember2.getScproportion()*sb22);
////							betSubordinaterebateDetail1.setSl(betMember2.getSlproportion());
////							betSubordinaterebateDetail1.setSubordinatelose(betMember2.getSlproportion()*sb33);
////							betSubordinaterebateDetail1.setIncome(betSubordinaterebateDetail.getSubordinatebet()+betSubordinaterebateDetail.getSubordinaterecharge()+betSubordinaterebateDetail.getSubordinatelose());
////							betSubordinaterebateDetail1.setSubtime(date);
////							betSubordinaterebateDetail1.setState(0);
////								betSubordinateRebateDetailService.save(betSubordinaterebateDetail1);
//						}
//					}
//				}
				
			
				
			}else if("month".equals(dateDetail)){
				datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE DATE_FORMAT(SUBTIME,:format)=DATE_FORMAT(NOW(),:format) group by parentmemberid2 ,subtime ").setParam("format", "%Y-%m"), page);
//				datas=betSubordinaterebateDetailService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinaterebate_detail WHERE DATE_FORMAT(SUBTIME,:format)=DATE_FORMAT(NOW(),:format)").setParam("format", "%Y-%m"),page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
				Calendar c = Calendar.getInstance();    
		        c.add(Calendar.MONTH, 0);
		        c.set(Calendar.DAY_OF_MONTH,1);
		        starttime =DateUtils.convertDate2String("yyyy-MM-dd", c.getTime());
		        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
		        endtime =DateUtils.convertDate2String("yyyy-MM-dd", c.getTime());
		        
//		        Date date=new Date();
//    			//用户推广返利
//			 	List<BetSubordinateRebate> subordinaterebatelist = betSubordinateRebateService.queryForList(new Finder("select *from bet_subordinate_rebate where remark=:t ").setParam("t", "t"), BetSubordinateRebate.class);
//				List<BetMember> betmemberlist1 = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and subordinate>0 "), BetMember.class);
//				if(betmemberlist1!=null){
//					for (BetMember betMember : betmemberlist1) {
//						List<BetMember> subordinatelist = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and parentid=:parentid ").setParam("parentid", betMember.getId2()), BetMember.class);
//						Double sb=0.;
//						Double sc=0.;
//						Double sl=0.;
//						
//						if(subordinatelist!=null){
//							for (BetMember betMember2 : subordinatelist) {
//								Double dayscore = betMember2.getDayscore();
////								Integer todayexp = betMember2.getTodayexp();
//								Double todayexp = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid  and TO_DAYS(rechargetime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date",date), Double.class);
//								Double bettingmoney = betBettingServiceImpl.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where memberid=:memberid and TO_DAYS(bettingtime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date", date), Double.class);
//								if(bettingmoney==null){
//								   bettingmoney=0.;
//								}
//								if(dayscore==null){
//									dayscore=0.;
//								}
//								if(todayexp==null){
//									todayexp=0.;
//								}
//								betMember2.setSbproportion(bettingmoney);
//								betMember2.setScproportion(todayexp);
//								sb+=bettingmoney;
//								sc+=todayexp;
//								if(dayscore<0){
//									betMember2.setSlproportion(-dayscore);
//									sl-=dayscore;
//								}else{
//									betMember2.setSlproportion(0.);
//								}
//								
//							}
//							
//							//下线投注流水返利比例
//							Double sbrebate=0.;
//							Double sb1=null;
//							Double sb11=0.;
//							//下线充值返利比例
//							Double screbate=0.;
//							Double sb2=null;
//							Double sb22=0.;
//							//下线输返利比例
//							Double slrebate=0.;
//							Double sb3=null;
//							Double sb33=0.;
//							if(subordinaterebatelist!=null){
//								for(BetSubordinateRebate bsr:subordinaterebatelist){
//									Double betstream = bsr.getBetstream();
//									Double betamounts= bsr.getBetamounts();
//									Double losescore = bsr.getLosescore();
//									Double rebate = bsr.getRebate();
//									if(betstream!=null){
//										if(sb<betstream){
//											if(sb1==null){
//												sb11=rebate;
//												sb1=betstream;
//											}else{
//												if(betstream<sb1){
//													sb11=rebate;
//													sb1=betstream;
//												}
//											}
//										}
//									}
//									if(betamounts!=null){
//										if(sb>betamounts){
//											if(sb2==null){
//												sb22=rebate;
//												sb2=betamounts;
//											}else{
//												if(betamounts<sb2){
//													sb22=rebate;
//													sb2=betamounts;
//												}
//											}
//										}
//									}
//									if(losescore!=null){
//										if(sl>losescore){
//											if(sb3==null){
//												sb33=rebate;
//												sb3=losescore;
//											}else{
//												if(losescore>sb3){
//													sb33=rebate;
//													sb3=losescore;
//												}
//											}
//										}
//									}
//								}
//							}
//							Map<String,Object>map=new HashMap<String,Object>();
//							map.put("subtime", date);
//							map.put("parentnickname", betMember.getNickname());
//							map.put("parentmemberid2",betMember.getId2());
//							map.put("totalsb",sb);
//							map.put("recommendnum",betMember.getSubordinate());
//							map.put("totalsubordinaterecharge",sc*sb22);
//							map.put("totalsubordinatebet",sb*sb11);
//							map.put("totalsubordinatelose",sl*sb33);
//							map.put("totalincome",sc*sb22+sb*sb11+sl*sb33);
//							map.put("state",0);
//							
//							
//							
//							if(page.getPageIndex()!=null&&1==page.getPageIndex()){
//								if(datas2==null){
//									datas2=new ArrayList<>();
//								}
//								datas2.add(0, map);
//      						 }
//      						 
//      						 page.setTotalCount(page.getTotalCount()+1);
//							betSubordinaterebateDetail1.setParentmemberid2(betMember.getId2());
//							betSubordinaterebateDetail1.setMemberid2(betMember2.getId2());
//							betSubordinaterebateDetail1.setNickname(betMember2.getNickname());
//							betSubordinaterebateDetail1.setParentnickname(betMember.getNickname());
//							betSubordinaterebateDetail1.setRecommendnum(betMember.getSubordinate());
//							betSubordinaterebateDetail1.setAgentid(betMember2.getAgentid());
//							betSubordinaterebateDetail1.setAgentparentid(betMember2.getAgentparentid());
//							betSubordinaterebateDetail1.setAgentparentids(betMember2.getAgentparentids());
//							betSubordinaterebateDetail1.setSb(betMember2.getSbproportion());
//							betSubordinaterebateDetail1.setSubordinatebet(sb11*betMember2.getSbproportion());
//							betSubordinaterebateDetail1.setSc(betMember2.getScproportion());
//							betSubordinaterebateDetail1.setSubordinaterecharge(betMember2.getScproportion()*sb22);
//							betSubordinaterebateDetail1.setSl(betMember2.getSlproportion());
//							betSubordinaterebateDetail1.setSubordinatelose(betMember2.getSlproportion()*sb33);
//							betSubordinaterebateDetail1.setIncome(betSubordinaterebateDetail.getSubordinatebet()+betSubordinaterebateDetail.getSubordinaterecharge()+betSubordinaterebateDetail.getSubordinatelose());
//							betSubordinaterebateDetail1.setSubtime(date);
//							betSubordinaterebateDetail1.setState(0);
//								betSubordinateRebateDetailService.save(betSubordinaterebateDetail1);
//						}
//					}
//				}
			}else if("lmonth".equals(dateDetail)){
				datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE DATE_FORMAT(SUBTIME,:format)=DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 1 MONTH),:format) group by parentmemberid2 ,subtime ").setParam("format", "%Y-%m"), page);
//				datas=betSubordinaterebateDetailService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinaterebate_detail WHERE DATE_FORMAT(SUBTIME,:format)=DATE_FORMAT(DATE_SUB(NOW(),INTERVAL 1 MONTH),:format)").setParam("format", "%Y-%m"),page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
				 Calendar calendar1 = Calendar.getInstance();
			        calendar1.add(Calendar.MONTH, -1);
			        calendar1.set(Calendar.DAY_OF_MONTH,1);
			        starttime =DateUtils.convertDate2String("yyyy-MM-dd", calendar1.getTime());
			        calendar1.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
			        endtime =DateUtils.convertDate2String("yyyy-MM-dd", calendar1.getTime());
			}
			
		}else if("1".equals(request.getParameter("k"))){
			starttime = request.getParameter("startTime");
			endtime = request.getParameter("endTime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			// ==执行分页查询
//			datas=betSubordinaterebateDetailService.findListDataByFinder(new Finder("select *from bet_subordinaterebate_detail where subtime>=:starttime and subtime<=:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
			if(betSubordinaterebateDetail.getMemberid2()!=null){
				datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime>=:starttime and subtime<=:endtime and parentmemberid2=:parentmemberid2 group by parentmemberid2 ,subtime ").setParam("parentmemberid2", betSubordinaterebateDetail.getMemberid2()).setParam("starttime",starttime ).setParam("endtime", endtime),page);
							
			}else{
				datas2=betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime>=:starttime and subtime<=:endtime group by parentmemberid2 ,subtime ").setParam("starttime",starttime ).setParam("endtime", endtime),page);
			}
			
//			 Date date=new Date();
//			String strdate = DateUtils.convertDate2String("yyyy-MM-dd", date);
//			if((starttime.equals(strdate)||DateUtils.beforeDate(starttime, strdate))&&(endtime.equals(strdate)||DateUtils.beforeDate(strdate, endtime))){
//				
//	    			//用户推广返利
//				 	List<BetSubordinateRebate> subordinaterebatelist = betSubordinateRebateService.queryForList(new Finder("select *from bet_subordinate_rebate where remark=:t ").setParam("t", "t"), BetSubordinateRebate.class);
//					List<BetMember> betmemberlist1 = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and subordinate>0 "), BetMember.class);
//					if(betmemberlist1!=null){
//						for (BetMember betMember : betmemberlist1) {
//							List<BetMember> subordinatelist = betMemberServiceImpl.queryForList(new Finder("select*from bet_member where status=1 and parentid=:parentid ").setParam("parentid", betMember.getId2()), BetMember.class);
//							Double sb=0.;
//							Double sc=0.;
//							Double sl=0.;
//							
//							if(subordinatelist!=null){
//								for (BetMember betMember2 : subordinatelist) {
//									Double dayscore = betMember2.getDayscore();
////									Integer todayexp = betMember2.getTodayexp();
//									Double todayexp = betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where state=2 and memberid=:memberid  and TO_DAYS(rechargetime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date",date), Double.class);
//									Double bettingmoney = betBettingServiceImpl.queryForObject(new Finder("select sum(bettingmoney) from bet_betting where memberid=:memberid and TO_DAYS(bettingtime) = TO_DAYS(:date) ").setParam("memberid", betMember2.getId()).setParam("date", date), Double.class);
//									if(bettingmoney==null){
//									   bettingmoney=0.;
//									}
//									if(dayscore==null){
//										dayscore=0.;
//									}
//									if(todayexp==null){
//										todayexp=0.;
//									}
//									betMember2.setSbproportion(bettingmoney);
//									betMember2.setScproportion(todayexp);
//									sb+=bettingmoney;
//									sc+=todayexp;
//									if(dayscore<0){
//										betMember2.setSlproportion(-dayscore);
//										sl-=dayscore;
//									}else{
//										betMember2.setSlproportion(0.);
//									}
//									
//								}
//								
//								//下线投注流水返利比例
//								Double sbrebate=0.;
//								Double sb1=null;
//								Double sb11=0.;
//								//下线充值返利比例
//								Double screbate=0.;
//								Double sb2=null;
//								Double sb22=0.;
//								//下线输返利比例
//								Double slrebate=0.;
//								Double sb3=null;
//								Double sb33=0.;
//								if(subordinaterebatelist!=null){
//									for(BetSubordinateRebate bsr:subordinaterebatelist){
//										Double betstream = bsr.getBetstream();
//										Double betamounts= bsr.getBetamounts();
//										Double losescore = bsr.getLosescore();
//										Double rebate = bsr.getRebate();
//										if(betstream!=null){
//											if(sb<betstream){
//												if(sb1==null){
//													sb11=rebate;
//													sb1=betstream;
//												}else{
//													if(betstream<sb1){
//														sb11=rebate;
//														sb1=betstream;
//													}
//												}
//											}
//										}
//										if(betamounts!=null){
//											if(sb>betamounts){
//												if(sb2==null){
//													sb22=rebate;
//													sb2=betamounts;
//												}else{
//													if(betamounts<sb2){
//														sb22=rebate;
//														sb2=betamounts;
//													}
//												}
//											}
//										}
//										if(losescore!=null){
//											if(sl>losescore){
//												if(sb3==null){
//													sb33=rebate;
//													sb3=losescore;
//												}else{
//													if(losescore>sb3){
//														sb33=rebate;
//														sb3=losescore;
//													}
//												}
//											}
//										}
//									}
//								}
//								Map<String,Object>map=new HashMap<String,Object>();
//								map.put("subtime", date);
//								map.put("parentnickname", betMember.getNickname());
//								map.put("parentmemberid2",betMember.getId2());
//								map.put("totalsb",sb);
//								map.put("recommendnum",betMember.getSubordinate());
//								map.put("totalsubordinaterecharge",sc*sb22);
//								map.put("totalsubordinatebet",sb*sb11);
//								map.put("totalsubordinatelose",sl*sb33);
//								map.put("totalincome",sc*sb22+sb*sb11+sl*sb33);
//								map.put("state",0);
//								
//								
//								
//								if(page.getPageIndex()!=null&&1==page.getPageIndex()){
//									if(datas2==null){
//										datas2=new ArrayList<>();
//									}
//									datas2.add(0, map);
//	      						 }
//	      						 
//	      						 page.setTotalCount(page.getTotalCount()+1);
////								betSubordinaterebateDetail1.setParentmemberid2(betMember.getId2());
////								betSubordinaterebateDetail1.setMemberid2(betMember2.getId2());
////								betSubordinaterebateDetail1.setNickname(betMember2.getNickname());
////								betSubordinaterebateDetail1.setParentnickname(betMember.getNickname());
////								betSubordinaterebateDetail1.setRecommendnum(betMember.getSubordinate());
////								betSubordinaterebateDetail1.setAgentid(betMember2.getAgentid());
////								betSubordinaterebateDetail1.setAgentparentid(betMember2.getAgentparentid());
////								betSubordinaterebateDetail1.setAgentparentids(betMember2.getAgentparentids());
////								betSubordinaterebateDetail1.setSb(betMember2.getSbproportion());
////								betSubordinaterebateDetail1.setSubordinatebet(sb11*betMember2.getSbproportion());
////								betSubordinaterebateDetail1.setSc(betMember2.getScproportion());
////								betSubordinaterebateDetail1.setSubordinaterecharge(betMember2.getScproportion()*sb22);
////								betSubordinaterebateDetail1.setSl(betMember2.getSlproportion());
////								betSubordinaterebateDetail1.setSubordinatelose(betMember2.getSlproportion()*sb33);
////								betSubordinaterebateDetail1.setIncome(betSubordinaterebateDetail.getSubordinatebet()+betSubordinaterebateDetail.getSubordinaterecharge()+betSubordinaterebateDetail.getSubordinatelose());
////								betSubordinaterebateDetail1.setSubtime(date);
////								betSubordinaterebateDetail1.setState(0);
////									betSubordinateRebateDetailService.save(betSubordinaterebateDetail1);
//							}
//						}
//					}
//			}
		}else{
			datas2 = betSubordinaterebateDetailService.queryForList(new Finder("SELECT parentmemberid2,parentnickname,sum(sb) as totalsb,recommendnum,sum(subordinaterecharge) as totalsubordinaterecharge,sum(subordinatebet) as totalsubordinatebet,sum(subordinatelose) as totalsubordinatelose,sum(income) as totalincome,state,subtime FROM bet_subordinaterebate_detail WHERE subtime=DATE_FORMAT(DATE_ADD(NOW(),INTERVAL -1 DAY),:format) group by parentmemberid2 ").setParam("format", "%Y-%m-%d"), page);
//			xxxx
			Calendar now = Calendar.getInstance(); 
			now.add(Calendar.DATE, -1);
			Date date1 = now.getTime();
			String time = DateUtils.convertDate2String(date1);
			starttime = time;
			endtime = time;
		}
		if("0000-00-00".equals(starttime)){
			starttime="";
		}
		if("9999-00-00".equals(endtime)){
			endtime="";
		}
		model.addAttribute("startTime", starttime);
		model.addAttribute("endTime", endtime);
		returnObject.setQueryBean(betSubordinaterebateDetail);
		returnObject.setPage(page);
		returnObject.setData(datas2);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetSubordinaterebateDetail betSubordinaterebateDetail) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betSubordinaterebateDetailService.findDataExportExcel(null,listurl, page,BetSubordinaterebateDetail.class,betSubordinaterebateDetail);
		String fileName="betSubordinaterebateDetail"+GlobalStatic.excelext;
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
		return "/lottery/betsubordinaterebatedetail/betsubordinaterebatedetailLook";
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
		  BetSubordinaterebateDetail betSubordinaterebateDetail = betSubordinaterebateDetailService.findBetSubordinaterebateDetailById(id);
		   returnObject.setData(betSubordinaterebateDetail);
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
	ReturnDatas saveorupdate(Model model,BetSubordinaterebateDetail betSubordinaterebateDetail,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			betSubordinaterebateDetailService.saveorupdate(betSubordinaterebateDetail);
			
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
		return "/lottery/betsubordinaterebatedetail/betsubordinaterebatedetailCru";
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
				betSubordinaterebateDetailService.deleteById(id,BetSubordinaterebateDetail.class);
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
			betSubordinaterebateDetailService.deleteByIds(ids,BetSubordinaterebateDetail.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
