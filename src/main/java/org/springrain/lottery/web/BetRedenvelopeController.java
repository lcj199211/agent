package  org.springrain.lottery.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.Iterators.Getter;
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
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetRedenvelope;
import org.springrain.lottery.entity.BetRedenvelopeRecord;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetRedenvelopeRecordService;
import org.springrain.lottery.service.IBetRedenvelopeService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.lottery.utils.RandomCharData;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-02 00:22:46
 * @see org.springrain.lottery.web.BetRedenvelope
 */
@Controller
@RequestMapping(value="/betredenvelope")
public class BetRedenvelopeController  extends BaseController {
	@Resource
	private IBetRedenvelopeService betRedenvelopeService;
	@Resource
	private IBetRedenvelopeRecordService betRedenvelopeRecordService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betredenvelope/betredenvelopeList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betRedenvelope
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetRedenvelope betRedenvelope) 
			throws Exception {
		if("1".equals(request.getParameter("lqjl"))){
			//领取记录
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			BetRedenvelopeRecord betRedenvelopeRecord=new BetRedenvelopeRecord();
			String redenvelopeid = request.getParameter("redenvelopeid");
			if(redenvelopeid!=null){
				betRedenvelopeRecord.setRedenvelopeid(Integer.valueOf(redenvelopeid));
			}
			List<BetRedenvelopeRecord> datas=betRedenvelopeRecordService.findListDataByFinder(null,page,BetRedenvelopeRecord.class,betRedenvelopeRecord);
				returnObject.setQueryBean(betRedenvelopeRecord);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betredenveloperecord/betredenveloperecordList";
		}else if("1".equals(request.getParameter("memberredenvelope"))){
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			page.setOrder("receivetime");
			page.setSort("desc");
			if(request.getParameter("memberid2")!=null){
				String parameter = request.getParameter("memberid2");
				List<BetRedenvelopeRecord> datas=	betRedenvelopeRecordService.queryForList(new Finder("select * from bet_redenvelope_record where memberid2=:memberid2 and state=0 ").setParam("memberid2", parameter), BetRedenvelopeRecord.class,page);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				
				return "/lottery/betmember/betredenveloperecordList1";
			}else{
				return "/errorpage/error";
			}
			
			
		}else{
			ReturnDatas returnObject = listjson(request, model, betRedenvelope);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
//			return "/lottery/betredenvelope/betredenvelopegenerate";
		}
		
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betRedenvelope
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetRedenvelope betRedenvelope) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Double totalcountpayscore=0.;
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("createtime");
		page.setSort("desc");
		page.setPageSize(50);
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
		
		List<BetRedenvelope> datas=new ArrayList<>();
		if("1".equals(request.getParameter("k"))){
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			//生效
			 datas=betRedenvelopeService.findListDataByFinder(new Finder("select *from bet_redenvelope where state=2 and createtime>=:starttime and createtime<:endtime and createtime > DATE_SUB(NOW(),INTERVAL IFNULL(lifetime,1) DAY) ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetRedenvelope.class,betRedenvelope);
			 model.addAttribute("k", 1);
			 List<BetRedenvelope> datas2=betRedenvelopeService.queryForList(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),BetRedenvelope.class);
				if(datas2!=null){
					for (BetRedenvelope betRedenvelope2 : datas2) {
						Double countpayscore = betRedenvelope2.getCountpayscore();
						if(countpayscore==null){
							countpayscore=0.;
						}
						totalcountpayscore+=countpayscore;
					}
				}
		}else if("2".equals(request.getParameter("k"))){
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			//禁用
			datas=betRedenvelopeService.findListDataByFinder(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime and state=1 ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetRedenvelope.class,betRedenvelope);
			model.addAttribute("k", 2);
			List<BetRedenvelope> datas2=betRedenvelopeService.queryForList(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),BetRedenvelope.class);
				if(datas2!=null){
					for (BetRedenvelope betRedenvelope2 : datas2) {
						Double countpayscore = betRedenvelope2.getCountpayscore();
						if(countpayscore==null){
							countpayscore=0.;
						}
						totalcountpayscore+=countpayscore;
						Date createtime = betRedenvelope2.getCreatetime();
					}
				}
		}else if("4".equals(request.getParameter("k"))){
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			//全部
			 datas=betRedenvelopeService.findListDataByFinder(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetRedenvelope.class,betRedenvelope);
			if(datas!=null){
				for (BetRedenvelope betRedenvelope2 : datas) {
					Double countpayscore = betRedenvelope2.getCountpayscore();
					if(countpayscore==null){
						countpayscore=0.;
					}
					totalcountpayscore+=countpayscore;
					Date createtime = betRedenvelope2.getCreatetime();
					
					if(2==betRedenvelope2.getState()){
						long time = createtime.getTime();
						Date date=new Date();
						long time2 = date.getTime();
						if((time2-time)>60*60*1000){
							betRedenvelope2.setState(0);
						}
					}
				}
			}
			model.addAttribute("k", 4);
		}else if("3".equals(request.getParameter("k"))){
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			//作废
			datas=betRedenvelopeService.findListDataByFinder(new Finder("select *from bet_redenvelope where state!=1 and createtime>=:starttime and createtime<:endtime and createtime < DATE_SUB(NOW(),INTERVAL  1 HOUR)  ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetRedenvelope.class,betRedenvelope);
			model.addAttribute("k", 3);
			if(datas!=null){
				for (BetRedenvelope betRedenvelope2 : datas) {
					betRedenvelope2.setState(0);
				}
			}
			List<BetRedenvelope> datas2=betRedenvelopeService.queryForList(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),BetRedenvelope.class);
			if(datas2!=null){
				for (BetRedenvelope betRedenvelope2 : datas2) {
					Double countpayscore = betRedenvelope2.getCountpayscore();
					if(countpayscore==null){
						countpayscore=0.;
					}
					totalcountpayscore+=countpayscore;
					Date createtime = betRedenvelope2.getCreatetime();
				}
			}
		}else if("5".equals(request.getParameter("k"))){
			if(StringUtils.isBlank(starttime)){
				starttime="0000-00-00";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-00-00";
			}
			//有效领取
			datas=betRedenvelopeService.findListDataByFinder(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime and receivenum>0 ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetRedenvelope.class,betRedenvelope);
			model.addAttribute("k", 5);
			List<BetRedenvelope> datas2=betRedenvelopeService.queryForList(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),BetRedenvelope.class);
			if(datas2!=null){
				for (BetRedenvelope betRedenvelope2 : datas2) {
					Double countpayscore = betRedenvelope2.getCountpayscore();
					if(countpayscore==null){
						countpayscore=0.;
					}
					totalcountpayscore+=countpayscore;
					Date createtime = betRedenvelope2.getCreatetime();
				}
			}
		}else{
			//默认今天
			String strdate=DateUtils.convertDate2String("yyyy-MM-dd", new Date());
			if(StringUtils.isBlank(starttime)){
				starttime=strdate;
			}
			Date date2 =DateUtils.convertString2Date(strdate);
			Calendar calendar2 = new GregorianCalendar();
			if(date2!=null){
				calendar2.setTime(date2); 
				calendar2.add(Calendar.DATE,1);
				Date date3=calendar2.getTime();
				if(StringUtils.isBlank(endtime)){
					endtime = DateUtils.convertDate2String(date3);
				}
			}
			
			// ==执行分页查询
			datas=betRedenvelopeService.findListDataByFinder(new Finder("select *from bet_redenvelope where createtime>=:starttime and createtime<:endtime ").setParam("starttime",starttime ).setParam("endtime", endtime),page,BetRedenvelope.class,betRedenvelope);
			if(datas!=null){
				for (BetRedenvelope betRedenvelope2 : datas) {
					Double countpayscore = betRedenvelope2.getCountpayscore();
					if(countpayscore==null){
						countpayscore=0.;
					}
					totalcountpayscore+=countpayscore;
					Date createtime = betRedenvelope2.getCreatetime();
					
					if(2==betRedenvelope2.getState()){
						long time = createtime.getTime();
						Date date=new Date();
						long time2 = date.getTime();
						if((time2-time)>60*60*1000){
							betRedenvelope2.setState(0);
						}
					}
				}
			}
		}
		model.addAttribute("totalcountpayscore", totalcountpayscore);
		if(!"0000-00-00".equals(starttime)){
			model.addAttribute("startTime", starttime);
		}
		if(!"9999-00-00".equals(endtime)){
			Date date2 =DateUtils.convertString2Date(endtime);
			Calendar calendar3 = new GregorianCalendar();
			calendar3.setTime(date2); 
			calendar3.add(Calendar.DATE,-1);
			Date date3=calendar3.getTime();
			endtime = DateUtils.convertDate2String(date3);
			model.addAttribute("endTime", endtime);
		}
		returnObject.setQueryBean(betRedenvelope);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetRedenvelope betRedenvelope) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betRedenvelopeService.findDataExportExcel(null,listurl, page,BetRedenvelope.class,betRedenvelope);
		String fileName="betRedenvelope"+GlobalStatic.excelext;
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
		return "/lottery/betredenvelope/betredenvelopeLook";
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
		  BetRedenvelope betRedenvelope = betRedenvelopeService.findBetRedenvelopeById(id);
		   returnObject.setData(betRedenvelope);
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
	ReturnDatas saveorupdate(Model model,BetRedenvelope betRedenvelope,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage("生成红包成功！");
		try {
			String agentId = SessionUser.getAgentId();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			if(betRedenvelope.getType()!=null&&1==betRedenvelope.getType()){
				if(betRedenvelope.getMinscore()!=null){
					if(betRedenvelope.getMinscore()<0){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("最小分不能小于0！");
						return returnObject;
					}
					
					
					if(betRedenvelope.getMaxscore()!=null){
						if(betRedenvelope.getMaxscore()<=0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("最大分不能小于或等于0！");
							return returnObject;
						}
						
						if(betRedenvelope.getMaxscore()<betRedenvelope.getMinscore()){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("最大分不能小于最小分！");
							return returnObject;
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("红包最大分不能为空！");
						return returnObject;
					}
					if(betRedenvelope.getSendnum()!=null){
						if(betRedenvelope.getSendnum()<=0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("领取人数不能小于或等于0！");
							return returnObject;
						}
//						if(((betRedenvelope.getTotalscore()==null)||(betRedenvelope.getTotalscore()!=null&&betRedenvelope.getTotalscore()<=0))){
//							returnObject.setStatus(ReturnDatas.ERROR);
//							returnObject.setMessage("总金额填写错误！");
//							return returnObject;
//						}else{
//							if(!(betRedenvelope.getMinscore()*betRedenvelope.getSendnum()<=betRedenvelope.getTotalscore())){
//								returnObject.setStatus(ReturnDatas.ERROR);
//								returnObject.setMessage("最小分填写错误！");
//								return returnObject;
//							}
//						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("领取人数不能为空！");
						return returnObject;
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("红包最小分不能为空！");
					return returnObject;
				}
				String redxxx = request.getParameter("redxxx");
				if(redxxx==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("红包个数不能为空！");
					return returnObject;
				}
				
				Integer redxxxx=null;
				try{
					 redxxxx = Integer.valueOf(redxxx);
				}catch (Exception e) {
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("红包个数填写错误！");
					return returnObject;
				}
				if(redxxxx==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("红包个数填写错误！");
					return returnObject;
				}
				if(redxxxx<=0){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("红包个数不能小于或等于0！");
					return returnObject;
				}
				if(redxxxx>50){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("红包个数不能超过50个！");
					return returnObject;
				}
				if(betRedenvelope.getLifetime()==null || betRedenvelope.getLifetime()<1){
					betRedenvelope.setLifetime(1);
				}
				List<BetRedenvelope> lis=new ArrayList<BetRedenvelope>();
				for (int i = 0; i < redxxxx; i++) {
					BetRedenvelope b=new BetRedenvelope();
					
					b.setId(null);
					b.setRedenvelopecode(RandomCharData.createData(8));
					b.setCreatetime(new Date());
					b.setState(2);
					b.setReceivenum(0);
					b.setCountpayscore(0.);
					b.setTotalscore(null);
					b.setSendnum(betRedenvelope.getSendnum());
					b.setMinscore(betRedenvelope.getMinscore());
					b.setMaxscore(betRedenvelope.getMaxscore());
					b.setCaption(betRedenvelope.getCaption());
					b.setLifetime(1);
					
					lis.add(b);
				}
				
				betRedenvelopeService.save(lis);
				//日志记录
			    String details = "生成"+redxxxx+"个随机红包";
			    
			    String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
			}else if(betRedenvelope.getType()!=null&&2==betRedenvelope.getType()){
				
				if((betRedenvelope.getTotalscore()==null)||(betRedenvelope.getTotalscore()!=null&&betRedenvelope.getTotalscore()<=0)){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("金额填写错误！");
					return returnObject;
				}
				String memberid2 = betRedenvelope.getMemberid2();
				if(StringUtils.isNoneBlank(memberid2)){
					if(memberid2.contains("，")){
						memberid2.replace("，", ",");
					}
					String[] split = memberid2.split(",");
					Boolean b=true;
					int i=0;
					if(split.length<=0){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("指定ID不能为空");
						return returnObject;
					}
					for (String string : split) {
						Integer id2 = Integer.valueOf(string);
						BetMember betMember = betMemberService.queryForObject(new Finder("select*from bet_member where id2=:id ").setParam("id", id2), BetMember.class);
						if(betMember==null){
							b=false;
						}
						i++;
					}
					if(b==false){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("无此用户ID");
						return returnObject;
					}else{
						
						
						String redxxx = request.getParameter("redxxx");
						if(redxxx==null){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("红包个数不能为空！");
							return returnObject;
						}
						
						Integer redxxxx=null;
						try{
							 redxxxx = Integer.valueOf(redxxx);
						}catch (Exception e) {
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("红包个数填写错误！");
							return returnObject;
						}
						if(redxxxx==null){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("红包个数填写错误！");
							return returnObject;
						}
						if(redxxxx<=0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("红包个数不能小于或等于0！");
							return returnObject;
						}
						if(redxxxx>50){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("红包个数不能超过50个！");
							return returnObject;
						}
						if(betRedenvelope.getLifetime()==null || betRedenvelope.getLifetime()<1){
							betRedenvelope.setLifetime(1);
						}
						List<BetRedenvelope> lis=new ArrayList<BetRedenvelope>();
						for (int d = 0; d < redxxxx; d++) {
							BetRedenvelope b1=new BetRedenvelope();
							b1.setId(null);
							b1.setRedenvelopecode(RandomCharData.createData(8));
							b1.setCreatetime(new Date());
							b1.setState(2);
							b1.setReceivenum(0);
							b1.setCountpayscore(0.);
							b1.setTotalscore(null);
							b1.setSendnum(i);
							b1.setMinscore(betRedenvelope.getTotalscore());
							b1.setMaxscore(betRedenvelope.getTotalscore());
							b1.setCaption(betRedenvelope.getCaption());
							b1.setMemberid2(memberid2);
							b1.setLifetime(1);
							
							lis.add(b1);
						}
						
						betRedenvelopeService.save(lis);
						
						//日志记录
					    String details = "生成"+redxxxx+"个指定红包";
					    String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details, betagent.getAgentid(), betagent.getParentid(), betagent.getParentids());
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("指定ID不能为空");
					return returnObject;
				}
				
			}else{
				Integer state = betRedenvelope.getState();
				Integer id = betRedenvelope.getId();
				if(state==1&&id!=null){
					BetRedenvelope b=new BetRedenvelope();
					b.setState(state);
					b.setId(id);
					betRedenvelopeService.update(b, true);
					returnObject.setMessage("禁用成功");
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
		return "/lottery/betredenvelope/betredenvelopeCru";
	}
	
	/**
	 * 删除操作
	 */
	/*@RequestMapping(value="/delete")*/
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				betRedenvelopeService.deleteById(id,BetRedenvelope.class);
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
	/*@RequestMapping("/delete/more")*/
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
			betRedenvelopeService.deleteByIds(ids,BetRedenvelope.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
