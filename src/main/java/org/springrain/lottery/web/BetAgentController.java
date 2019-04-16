package  org.springrain.lottery.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentAccount;
import org.springrain.lottery.entity.BetAgentGamemanage;
import org.springrain.lottery.entity.BetAgentRechargeRebate;
import org.springrain.lottery.entity.BetBetting;
import org.springrain.lottery.entity.BetCommission;
import org.springrain.lottery.entity.BetCommissionDto;
import org.springrain.lottery.entity.BetGame;
import org.springrain.lottery.entity.BetGameAgent;
import org.springrain.lottery.entity.BetGameclass;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetOptLog;
import org.springrain.lottery.entity.BetTransferagentAccounts;
import org.springrain.lottery.entity.CommissionDto;
import org.springrain.lottery.service.IBetAgentAccountService;
import org.springrain.lottery.service.IBetAgentGamemanageService;
import org.springrain.lottery.service.IBetAgentRechargeRebateService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetCommissionService;
import org.springrain.lottery.service.IBetGameAgentService;
import org.springrain.lottery.service.IBetGameService;
import org.springrain.lottery.service.IBetGameclassService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetTransferagentAccountsService;
import org.springrain.lottery.service.ICustomServerService;
import org.springrain.lottery.service.ISoccerAllbettingService;
import org.springrain.lottery.utils.AgentToolUtil;
import org.springrain.system.entity.Fwlog;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.entity.UserRole;
import org.springrain.system.service.IFwlogService;
import org.springrain.system.service.IUserOrgService;
import org.springrain.system.service.IUserRoleMenuService;
import org.springrain.system.service.IUserService;

import com.alibaba.fastjson.JSON;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-11 15:44:16
 * @see org.springrain.lottery.web.BetAgent
 */
@Controller
@RequestMapping(value="/betagent")
public class BetAgentController  extends BaseController {
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentAccountService betAgentAccountService;
	@Resource
	private IBetAgentGamemanageService betAgentGamemanageService;
	@Resource
	private IBetGameclassService betGameclassService;
	@Resource
	private IUserService userService;
	@Resource
	private IUserRoleMenuService  userRoleMenuService;
	@Resource
	private IUserOrgService userOrgService;
	@Resource
	private IBetTransferagentAccountsService betTransferagentAccountsService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentRechargeRebateService betAgentRechargeRebateService;
	@Resource 
	private IBetBettingService betBettingService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private IFwlogService fwlogService;
	@Resource
	private ISoccerAllbettingService soccerAllbettingService;
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private ICached cached;
	@Resource
	private IBetGameAgentService betGameAgentService;
	@Resource
	private IBetGameService betGameService;
	@Resource
	private IBetCommissionService betCommissionService;
	@Resource
	private ICustomServerService customServerService;
	private String listurl="/lottery/betagent/betagentList";
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value = "/customservice")
	public String customservice(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String agentid = SessionUser.getAgentId();
		String userid = SessionUser.getUserId();
		String company = betAgentService.queryForObject(new Finder("select company from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class);
		String url = customServerService.queryForObject(new Finder("select url from custom_url where company=:company ").setParam("company", company), String.class);
//		CustomServer server = customServerService.queryForObject(new Finder("select * from custom_server where userid=:userid").setParam("userid", userid), CustomServer.class);
//		if(server==null){
//			CustomServer customServer = new CustomServer();
//			customServer.setId(null);
//			customServer.setActive(1);
//			customServer.setCharnum(0);
//			customServer.setUserid(userid);
//			customServer.setCompany(company);
//			customServerService.save(customServer);
//		}else{
//			customServerService.update(new Finder("update custom_server set active=1,charnum=0 where userid=:userid").setParam("userid", userid));
//		}
		model.addAttribute("url", url);
		model.addAttribute("userid", userid);
		model.addAttribute("company", company);
		return "/lottery/betagent/custompage";
		
	}
	/**
	 * APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betAgent
	 * @return
	 * @throws Exception
	 * /betagent/updateagent
	 */
	@RequestMapping("/updateagent")
	public @ResponseBody
	ReturnDatas updateagentjson(HttpServletRequest request){
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			String agentid = SessionUser.getAgentId();
			String bettingrebate=request.getParameter("bettingrebate");
			String bettingrebate2=request.getParameter("bettingrebate2");
			BetAgent paeddd = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			if(paeddd.getBettingrebate()<Double.valueOf(bettingrebate)||paeddd.getBettingrebate2()<Double.valueOf(bettingrebate2)){
				System.out.println("返佣比例不正确: agentid="+agentid+" paeddd.getBettingrebate()="+paeddd.getBettingrebate()+" paeddd.getBettingrebate2()="+paeddd.getBettingrebate2());
				
				returnObject.setMessage("返佣比例不正确");
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				return returnObject;
			}
			String id2=request.getParameter("id2");
			String aid="";
			BetMember mem = betMemberService.queryForObject(new Finder("select * from bet_member where  id2=:id2").setParam("id2", id2), BetMember.class);
			if(mem == null){
				aid=id2;
				betAgentService.update(new Finder("update bet_agent set bettingrebate=:bettingrebate,bettingrebate2=:bettingrebate2 where agentid=:agentid").setParam("bettingrebate", bettingrebate).setParam("bettingrebate2", bettingrebate2).setParam("agentid", aid));
			}else{
				BetMember betMember = betMemberService.queryForObject(new Finder("select * from bet_member where id2=:id2 ").setParam("id2", id2), BetMember.class);
				BetAgent betAgent = new BetAgent();
				betAgent.setId(null);
				betAgent.setAgentid(betMember.getAccount());
				betAgent.setAccount(betMember.getAccount());
				betAgent.setNickname(betMember.getNickname());
				betAgent.setPassword(SecUtils.encoderByMd5With32Bit("123456"));
				betAgent.setBankpassword(SecUtils.encoderByMd5With32Bit("123456"));
				
				//复制用户电话号码到代理电话号码，只有天天彩和爱客复制
				if(betMember!=null && betMember.getAgentparentids()!=null){
					if("ttcai".equals(betMember.getAgentid()) || "aike".equals(betMember.getAgentid())
						|| betMember.getAgentparentids().contains(",ttcai,") 
						|| betMember.getAgentparentids().contains(",aike,")){
						betAgent.setMobile(betMember.getMobile());
					}
				}
				
				betAgent.setQq(null);
				betAgent.setWeixin(null);
				betAgent.setActive(1);
				betAgent.setScore(0d);
				betAgent.setTransferaccountsscore(0d);
				betAgent.setSx(0d);
				betAgent.setTy(0d);
				betAgent.setTy2(0d);
				betAgent.setSubordinate(0);
				betAgent.setLogin(0);
				betAgent.setRechargerebate(0.);
				betAgent.setWinorlossrebate(0d);
				betAgent.setBettingrebate(Double.valueOf(bettingrebate));
				betAgent.setCompanyproportion(0.);
				betAgent.setRegistrationtime(new Date());
				betAgent.setParentid(paeddd.getAgentid());
				betAgent.setParentids(paeddd.getParentids()+paeddd.getAgentid()+",");
				betAgent.setAllowagent(1);
				betAgent.setTransferaccount(1);
				betAgent.setRecharge(1);
				betAgent.setExchange(1);
				betAgent.setMembernum(1);
				betAgent.setBettingty(0.);
				betAgent.setMercnum(null);
				betAgent.setPrivatekey(null);
				betAgent.setCompany(paeddd.getCompany());
				betAgent.setIssuemodel(0);
				betAgent.setIssuebalance(0.);
				betAgent.setSubordinaterebate(null);
				betAgent.setOwnsubordinaterebate(null);
				betAgent.setBettingrebate2(Double.valueOf(bettingrebate2));
				betAgent.setBettingty2(0.);
				betAgent.setIswithdraw(0);
					
				String id=UUID.randomUUID().toString();
				User user = new User();
				user.setId(id);
				user.setName(betAgent.getNickname());
				user.setAccount(betAgent.getAccount());
				user.setPassword(betAgent.getPassword());
				user.setMobile(betAgent.getMobile());
				user.setWeixinId(betAgent.getWeixin());
				user.setUserType(0);
				user.setActive(1);
				user.setQq(betAgent.getQq());
				user.setIsagent(1);
				user.setAgentid(betAgent.getAgentid());
				userService.save(user);
				UserRole role = new UserRole();
				role.setId(UUID.randomUUID().toString());
				role.setUserId(id);
				role.setRoleId("r_10002");
				userRoleMenuService.save(role);
				UserOrg userOrg = new UserOrg();
				userOrg.setId(UUID.randomUUID().toString());
				userOrg.setUserId(id);
				userOrg.setOrgId("o_10001");
				userOrg.setManagerType(11);
				userOrg.setHasleaf(1);
				userOrgService.save(userOrg);
				betOptLogService.saveoptLog(AgentToolUtil.getAgentTool(request),IPUtils.getClientAddress(request),"新增代理商,id为:"+betAgent.getAgentid(),agentid,betAgent.getParentid(),betAgent.getParentids());
				betAgentService.save(betAgent);

				String[] split = betAgent.getParentids().split(",");
				if(split.length>=3){
					List<BetGame> betGames = betGameService.queryForList(new Finder("select * from bet_game where company=:company").setParam("company", split[2]), BetGame.class);
					if(!betGames.isEmpty()){
						for (BetGame betGame : betGames) {
							BetGameAgent betGameAgent = new BetGameAgent();
							betGameAgent.setId(null);
							betGameAgent.setAgentid(betAgent.getAgentid());
							betGameAgent.setGamename(betGame.getGamename());
							betGameAgent.setImg(betGame.getImg());
							betGameAgent.setRemark(betGame.getRemark());
							betGameAgent.setState(betGame.getState());
							betGameAgent.setTitle(betGame.getTitle());
							betGameAgent.setCompany(split[2]);
							betGameAgentService.save(betGameAgent);
						}
					}
				}
				BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", mem.getAccount()), BetAgent.class);
				betMemberService.update(new Finder("update bet_member set agentid=:agentid,agentparentid=:agentparentid,agentparentids=:agentparentids where id2=:id2").setParam("agentid", agent.getAgentid()).setParam("agentparentid", agent.getParentid()).setParam("agentparentids", agent.getParentids()).setParam("id2", id2));
				
				//代理本身更新用户个数和下属代理个数
				Integer count = betMemberService.queryForObject(new Finder("select count(*) from bet_member where agentid=:agentid or agentparentids like :agentparentids ").setParam("agentid", agentid).setParam("agentparentids","%,"+agentid+",%"), Integer.class);
				Integer agcount = betAgentService.queryForObject(new Finder("select count(*) from bet_agent where parentid=:parentid or parentids like :parentids ").setParam("parentid", agentid).setParam("parentids", "%,"+agentid+",%"), Integer.class);
				betAgentService.update(new Finder("update bet_agent set membernum=:membernum,subordinate=:subordinate where agentid=:agentid ").setParam("membernum", count).setParam("subordinate", agcount).setParam("agentid", agentid));
				
				//被转移成代理的用户更新用户数量（因为他自己会变成自己代理的用户）
				betAgentService.update(new Finder("update bet_agent set membernum=:membernum,subordinate=:subordinate where agentid=:agentid ").setParam("membernum", 1).setParam("subordinate", 0).setParam("agentid", agent.getAgentid()));
				
			}
			
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			returnObject.setMessage("修改成功");
			return returnObject;
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 代理佣金明细
	 * @param request
	 * @param model
	 * @param betAgent
	 * @return
	 * @throws Exception
	 * /betagent/commission
	 */
	
	@RequestMapping("/commission/json")
	public @ResponseBody
	ReturnDatas commissionjson(HttpServletRequest request, Model model,BetCommission betCommission) throws Exception{
		String agentid = SessionUser.getAgentId();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		String starttime = request.getParameter("starttime");
		if(StringUtils.isEmpty(starttime)){
			starttime="2000-01-01";
		}
		String endtime = request.getParameter("endtime");
		if(StringUtils.isEmpty(endtime)){
			endtime="2100-01-01";
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(endtime));
		cal.add(Calendar.DATE,1);
		Date time = cal.getTime();
		String pageIndex=request.getParameter("pageIndex");
		if(StringUtils.isEmpty(pageIndex)){
			pageIndex = "1";
		}
		//System.out.println("starttime=="+starttime+" endtime=="+endtime+" time=="+time);
		Page page = newPage(request);
		page.setPageSize(20);
		page.setPageIndex(Integer.valueOf(pageIndex));
		page.setOrder("a.id");
		List<CommissionDto> list = betCommissionService.queryForList(new Finder("select a.commission,a.settlementtime,b.nickname from bet_commission a left join bet_member b on a.memberid2=b.id2 where a.agentid=:agentid and a.settlementtime>=:start and a.settlementtime<:end").setParam("agentid", agentid).setParam("start", starttime).setParam("end", time), CommissionDto.class,page);
		String sum = betCommissionService.queryForObject(new Finder("select sum(commission) from bet_commission where agentid=:agentid and settlementtime>=:start and settlementtime<:end ").setParam("agentid", agentid).setParam("start", starttime).setParam("end", time), String.class);
		BetCommissionDto datas = new BetCommissionDto();
		datas.setList(list);
		datas.setSum(sum);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	@RequestMapping("/commission")
	@ResponseBody
	public ReturnDatas commission(HttpServletRequest request, Model model,BetCommission betCommission) throws Exception{
		ReturnDatas returnObject = commissionjson(request, model, betCommission);
		return returnObject;
	}
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betAgent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/carouselwall/json")
	public @ResponseBody
	ReturnDatas carouselwaljson(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent queryForObject = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid and active=1 ").setParam("agentid", agentId), BetAgent.class);
		String parentids = queryForObject.getParentids();
		if(StringUtils.isBlank(parentids)){
			ReturnDatas returnObject = ReturnDatas.getErrorReturnDatas();
			return returnObject;
		}
		if("1".equals(request.getParameter("k"))){
			Page page = newPage(request);
			page.setPageSize(20);
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			List<BetMember> betmemberlist = betMemberService.queryForList(new Finder("select a.signdate,a.nickname,bb.nickname as operate from bet_member a left join bet_agent bb on a.agentid=bb.agentid where a.signdate>= :today and (a.agentid=:agentid or a.agentparentids like :aid) order by signdate desc ").setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%"), BetMember.class,page);
			returnObject.setQueryBean(betAgent);
			returnObject.setPage(page);
			returnObject.setData(betmemberlist);
			return returnObject;
		}else{
			Page page = newPage(request);
			page.setPageSize(20);
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			List<BetGold> betgoldlist = betGoldService.queryForList(new Finder("select a.money,a.rechargetime,a.accountnickname,bb.nickname as agentnickname from bet_gold a left join bet_agent bb on a.agentid=bb.agentid where state=2 and  a.rechargetime>= :today and (a.agentid=:agentid or a.agentparentids like :aid) order by a.rechargetime desc ").setParam("today", sdf.format(new Date())).setParam("agentid", agentId).setParam("aid", "%,"+agentId+",%"), BetGold.class,page);
			returnObject.setQueryBean(betAgent);
			returnObject.setPage(page);
			returnObject.setData(betgoldlist);
			return returnObject;
		}
	}
	
	@RequestMapping("/carouselwall")
	public String carouselwal(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception{
		return "/lottery/betagent/carouselwal";
	}
	
	@RequestMapping("/lookscorejson")
	public @ResponseBody
	ReturnDatas lookscorejson(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		BetAgent agents = betAgentService.queryForObject(new Finder("select * from bet_agent where (agentid=:id) and active=1 ").setParam("id", agentid), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		returnObject.setQueryBean(betAgent);
		returnObject.setPage(page);
		
		returnObject.setData(agents.getScore());
		return returnObject;
	}
	
	@RequestMapping(value = "/modifiymercnum/pre")
	public String modifiymercnumpre(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String agentId = SessionUser.getAgentId();
		if(StringUtils.isEmpty(agentId)){
			return "/lottery/betagent/modifiymercnum";
		}
		//获取当前登录人
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid and active=1 ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setData(betagent);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betagent/modifiymercnum";
	}
	
	@RequestMapping(value="/modifiymercnum/save")
	public @ResponseBody ReturnDatas modifiymercnumsave(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String agentid = SessionUser.getAgentId();
		ReturnDatas datas=ReturnDatas.getSuccessReturnDatas();
		String mercnum=request.getParameter("mercnum");
		String privatekey=request.getParameter("privatekey");
		
		mercnum=mercnum.trim();
		privatekey=privatekey.trim();
		try{
//			user.setPassword(SecUtils.encoderByMd5With32Bit(pwd));
//			userService.update(user);
			betAgentService.update(new Finder("update bet_agent set mercnum=:mercnum,privatekey=:privatekey where agentid=:id ").setParam("privatekey", privatekey).setParam("id", agentid).setParam("mercnum", mercnum));
			datas.setMessage("修改成功。");
			return datas;
		}catch(Exception e){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("系统故障，请稍后再试。");
			return datas;
		}
		
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/modifiypwd/pre")
	public String modifiypwdpre(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String agentId = SessionUser.getAgentId();
		if(StringUtils.isEmpty(agentId)){
			return "/lottery/betagent/modifiypwd";
		}
		//获取当前登录人
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setData(betagent);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betagent/modifiypwd";
	}
	@RequestMapping(value="/modifiypwd/ispwd")
	public @ResponseBody Map<String, Object> checkPwd(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
//		String userId = SessionUser.getUserId();
		String agentId = SessionUser.getAgentId();
		String pwd=request.getParameter("pwd");
		Map<String, Object> maps=new HashMap<String, Object>();
//		User user = userService.findById(userId, User.class);
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentId), BetAgent.class);
		if(betagent==null){
			maps.put("msg", "-1");
			maps.put("msgbox", "数据有问题，正在返回");
			return maps;
		}
		if(betagent.getBankpassword().equals(SecUtils.encoderByMd5With32Bit(pwd))){
			maps.put("msg", "1");
			maps.put("msgbox", "正确");
			return maps;
		}else{
			maps.put("msg", "0");
			maps.put("msgbox", "原始银行密码错误，请修改");
			return maps;
		}
		
	}
	@RequestMapping(value="/modifiypwd/save")
	public @ResponseBody ReturnDatas modifiySave(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String agentid = SessionUser.getAgentId();
		ReturnDatas datas=ReturnDatas.getSuccessReturnDatas();
//		String id=request.getParameter("id");
		String pwd=request.getParameter("newpwd");
		String repwd=request.getParameter("renewpwd");
		String oldpassword=request.getParameter("oldpassword");
//		User user = userService.findById(userId, User.class);
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:id").setParam("id",agentid), BetAgent.class);
		if(betagent==null){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("数据有问题，正在返回");
			return datas;
		}
		if(!betagent.getBankpassword().equals(SecUtils.encoderByMd5With32Bit(oldpassword))){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("原始银行密码错误，请修改");
			return datas;
		}
		
		if(StringUtils.isEmpty(pwd)||StringUtils.isEmpty(repwd)){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("新银行密码或重复银行密码为空，请修改。");
			return datas;
		}
		pwd=pwd.trim();
		repwd=repwd.trim();
		if(!pwd.equals(repwd)){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("两次银行密码不一致，请修改。");
			return datas;
		}
		try{
//			user.setPassword(SecUtils.encoderByMd5With32Bit(pwd));
//			userService.update(user);
			betAgentService.update(new Finder("update bet_agent set bankpassword=:bankpassword where agentid=:id ").setParam("id", agentid).setParam("bankpassword", SecUtils.encoderByMd5With32Bit(pwd)));
			datas.setMessage("修改成功。");
			return datas;
		}catch(Exception e){
			datas.setStatus(ReturnDatas.ERROR);
			datas.setMessage("系统故障，请稍后再试。");
			return datas;
		}
		
	}
	
	/**
	 * 手动退还代理投注退佣
	 * 
	 */
	@RequestMapping("/manualrefundbettingty")
	public @ResponseBody
	ReturnDatas manualrefundbettingty(Model model,BetAgent betAgent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String parameter = request.getParameter("gameclassid");
		Integer gameclassid = null;
		String agentId = SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentId), BetAgent.class);
		try{
			gameclassid = Integer.valueOf(parameter);
		}catch(Exception e){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
		
		if(gameclassid==null){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
		BetGameclass bgc = betGameclassService.queryForObject(new Finder("select * from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", gameclassid), BetGameclass.class);
		if(bgc.getTymode()!=0){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}
		Object cached2=null;
		try{
			cached2 = cached.getCached("updateagentbettingty2134".getBytes());
		}catch (Exception e) {
			
		}
		if(cached2!=null){
			if("1".equals(cached2)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				return returnObject;
			}
			
		}
		cached.updateCached("updateagentbettingty2134".getBytes(), "1".getBytes(),10000L);
			
		try{
			List<BetAgent> queryForList = betAgentService.queryForList(new Finder("select * from bet_agent and active=1"), BetAgent.class);
			if(queryForList!=null){
				for (BetAgent betAgent2 : queryForList) {
					String agentid = betAgent2.getAgentid();
					Double agentty = betBettingService.queryForObject(new Finder("select sum(ty) from bet_betting where gameclassid=:gameclassid and tystate=0 and state=1 and (agentid=:agentid or agentparentids like :aaaaa) ").setParam("aaaaa", "%,"+agentid+",%").setParam("agentid", agentid).setParam("gameclassid", gameclassid), Double.class);
					if(agentty==null||agentty==0){
						
					}else{
						betAgentService.update(new Finder("update bet_agent set score=score+:xxx,ty2=isnull(ty2,0)+:xxxx where agentid=:agentid ").setParam("agentid", agentid).setParam("xxxx", agentty).setParam("xxx", agentty));
					}
					
				}
			}
		
			betBettingService.update(new Finder("update bet_betting set tystate=1,tytime=:tytime where gameclassid=:gameclassid and tystate=0 ").setParam("gameclassid", gameclassid).setParam("tytime", new Date()));
			//操作日志
			 String details = "";
			
		     details = "手动退还"+bgc.getGcname()+"的代理投注退佣";
		     String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agent.getAgentid(),agent.getParentid(),agent.getParentids());
			return returnObject;
		}catch (Exception e) {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
			return returnObject;
		}finally{
			cached.deleteCached("updateagentbettingty2134".getBytes());
		}
		
		
	
	}
	/**
	 * 代理投注退佣列表
	 * 
	 * @param request
	 * @param model
	 * @param betAgent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/agentbettingtylist")
	public String agentbettingtylist(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception {
			if("1".equals(request.getParameter("sd"))){
				String parameter = request.getParameter("gameclassid");
				Integer gameclassid = null;
				try{
					gameclassid = Integer.valueOf(parameter);
				}catch(Exception e){
					return "/errorpage/error";
				}
				
				if(gameclassid==null){
					return "/errorpage/error";
				}

				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				Date date1 =DateUtils.convertString2Date(endtime);
				Calendar calendar = new GregorianCalendar();
				if(date1!=null){
					calendar.setTime(date1); 
					calendar.add(Calendar.DATE,1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
				}
				model.addAttribute("starttime", starttime);
				
				if(StringUtils.isBlank(starttime)) {
					starttime="0000-00-00";
				}
				if(StringUtils.isBlank(endtime)){
					endtime="9999-00-00";
				}
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				// ==执行分页查询
				List<BetBetting> datas = betBettingService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where (a.bettingtime >=:starttime and a.bettingtime<:endtime) and a.gameclassid=:gameclassid and a.tystate=0 and a.ty>0 and a.state=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("gameclassid", gameclassid).setParam("starttime", starttime).setParam("endtime", endtime).setParam("agentid", request.getParameter("agentid")).setParam("aid", "%,"+request.getParameter("agentid")+",%"), BetBetting.class,page);
				returnObject.setQueryBean(betAgent);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject); 	
				model.addAttribute("agentid", request.getParameter("agentid"));
				if(!"9999-00-00".equals(endtime)){
					Date date2 =DateUtils.convertString2Date(endtime);
					calendar.setTime(date2); 
					calendar.add(Calendar.DATE,-1);
					Date date3=calendar.getTime();
					endtime = DateUtils.convertDate2String(date3);
					model.addAttribute("endTime", endtime);
				}
				return "/lottery/betagent/agentbettingty";
			}else{
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				String parameter = request.getParameter("gameclassid");
				Integer gameclassid = null;
				try{
					gameclassid = Integer.valueOf(parameter);
				}catch(Exception e){
					return "/errorpage/error";
				}
				
				if(gameclassid==null){
					return "/errorpage/error";
				}
				List<BetAgent> queryForList = betAgentService.queryForList(new Finder("select * from bet_agent and active=1"), BetAgent.class);
				List<BetAgent> data=new ArrayList<BetAgent>();
				if(queryForList!=null){
					for (BetAgent betAgent2 : queryForList) {
						String agentid = betAgent2.getAgentid();
						Double agentty = betBettingService.queryForObject(new Finder("select sum(ty) from bet_betting where gameclassid=:gameclassid and state=1 and tystate=0 and (agentid=:agentid or agentparentids like :aaaaa) ").setParam("aaaaa", "%,"+agentid+",%").setParam("agentid", agentid).setParam("gameclassid", gameclassid), Double.class);
						if(agentty==null||agentty==0){
							
						}else{
							betAgent2.setTy2(agentty);
							data.add(betAgent2);
						}
						
					}
				}
				returnObject.setData(data);
				String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", gameclassid),String.class);
				model.addAttribute("gcname", gcname);
				model.addAttribute("gameclassid", gameclassid);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return "/lottery/betagent/agentbettingtyList";
			}
	}
	
	@RequestMapping("/lookmyself")
	public String lookmyself(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		model.addAttribute("agentid", agentid);
//		ReturnDatas returnObject = lookjson(model, request, response);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		BetAgent parentAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agent.getParentid()), BetAgent.class);
		
		returnObject.setData(agent);
		model.addAttribute("agent", agent);
		model.addAttribute("parentAgent", parentAgent);

		if(!(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1==1&& Integer.valueOf(new SimpleDateFormat("HH").format(new Date()))>=0 && Integer.valueOf(new SimpleDateFormat("HH").format(new Date()))<12)){
			//每周一0~12点解锁
			model.addAttribute("lock", "1");
		}
		List<BetAgent> betAgentList = betAgentService.findListDataByFinder(new Finder("select agentid from bet_agent where allowagent=:allowagent order by agentid ").setParam("allowagent", 1), null, BetAgent.class, null);
		//代理充值返利设置
		List<BetAgentRechargeRebate> betagentrechargerebatelist = betAgentRechargeRebateService.queryForList(new Finder("select *from bet_agent_recharge_rebate where agentid=:agentid order by lowerlimit ").setParam("agentid", request.getParameter("agentid")), BetAgentRechargeRebate.class);
		model.addAttribute("betagentrechargerebatelist", betagentrechargerebatelist);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		model.addAttribute("betAgentList", betAgentList);

		List<BetAgentGamemanage> betAgentGamemanagesList = betAgentGamemanageService.findListDataByFinder(new Finder("select a.id,a.gameclassid,a.gcname,a.agentid,a.state from bet_agent_gamemanage a left join bet_gameclass b on a.gameclassid = b.gameclassid where a.agentid=:agentid").setParam("agentid", agentid), null, BetAgentGamemanage.class, null);
		model.addAttribute("betAgentGamemanagesList", betAgentGamemanagesList);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
	
		return "/lottery/betagent/betagentlookmyself";
	
	}
	
	
	@RequestMapping("/lookmyself/json")
	public  @ResponseBody
	ReturnDatas lookmyselfjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		agent.setMercnum(null);
		agent.setPrivatekey(null);
		agent.setPassword(null);
		agent.setBankpassword(null);
		returnObject.setData(agent);
		return returnObject;
	
	}
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betAgent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception {
			String agentid = SessionUser.getShiroUser().getAgentid();
			if("1".equals(request.getParameter("a"))){
				List<BetAgentAccount> agentAccountList = betAgentAccountService.queryForList(new Finder("select * from bet_agent_account where agentid=:agentid").setParam("agentid", agentid), BetAgentAccount.class);
				model.addAttribute("agentAccountList", agentAccountList);
				return "/lottery/betagent/betagentaccount";
			}else if("1".equals(request.getParameter("t"))){
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				model.addAttribute("starttime", starttime);
				model.addAttribute("endtime", endtime);
				if(StringUtils.isBlank(starttime)) {
					starttime="1970-01-01";
				}
				if(StringUtils.isBlank(endtime)){
					endtime="6666-01-01";
				}
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				// ==执行分页查询
				List<BetBetting> datas = null;
				if(starttime.equals(endtime)){
					datas = betBettingService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where a.bettingtime>= :starttime and a.ty>0 and a.state=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime", starttime).setParam("agentid", request.getParameter("agentid")).setParam("aid", "%,"+request.getParameter("agentid")+",%"), BetBetting.class,page);
				}else{
					datas = betBettingService.queryForList(new Finder("select a.*,b.id2 as memberid2 from bet_betting a LEFT JOIN bet_member b ON a.memberid=b.id where (a.bettingtime between :starttime and :endtime) and a.ty>0 and a.state=1 and (a.agentid=:agentid or a.agentparentids like :aid) ").setParam("starttime", starttime).setParam("endtime", endtime).setParam("agentid", request.getParameter("agentid")).setParam("aid", "%,"+request.getParameter("agentid")+",%"), BetBetting.class,page);
				}
				returnObject.setQueryBean(betAgent);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				
				return "/lottery/betagent/betagentbettingty";
			}else if("1".equals(request.getParameter("m"))){
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				// ==构造分页请求
				Page page = newPage(request);
				String parameter = request.getParameter("agentid");
				// ==执行分页查询
				List<BetMember> datas = betMemberService.queryForList(new Finder("select * from bet_member where isinternal=0 and (agentid=:agentid or agentparentids like :aid ) ").setParam("aid", "%,"+parameter+",%").setParam("agentid", parameter), BetMember.class, page);
				returnObject.setQueryBean(new BetMember());
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				model.addAttribute("agentid", parameter);
				return "/lottery/betagent/betagentmemberList";
			}else if("1".equals(request.getParameter("x"))){
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				if(betAgent.getActive()==null) {
					betAgent.setActive(1);
				}else if(betAgent.getActive()==3) {
					betAgent.setActive(0);
				}
				// ==构造分页请求
				Page page = newPage(request);
				// ==执行分页查询
				List<BetAgent> betList = betAgentService.queryForList(new Finder("select * from bet_agent where parentids like :agentid and active=1").setParam("agentid", "%,"+agentid+",%"), BetAgent.class,page);
				returnObject.setQueryBean(betAgent);
				returnObject.setPage(page);
				returnObject.setData(betList);
				model.addAttribute("myagentid", agentid);
				model.addAttribute("tran", betAgentAccountService.queryForObject(new Finder("select transferaccount from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class));
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				return listurl;
			}else if("1".equals(request.getParameter("sr"))){
				ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
				if(betAgent.getActive()==null) {
					betAgent.setActive(1);
				}else if(betAgent.getActive()==3) {
					betAgent.setActive(0);
				}
				// ==构造分页请求
				Page page = newPage(request);
				// ==执行分页查询
				String id = request.getParameter("agentid");
				List<BetAgent> datas = null;
				if(id == null){
					id = agentid;
				}
				if(betAgent.getNickname()==""){
					betAgent.setNickname(null);
				}
				if(betAgent.getNickname()==""){
					betAgent.setAccount(null);
				}
//				datas = betAgentService.queryForList(new Finder("select * from bet_agent where parentid =:id and( nickname like :nickname or :nick is null) and (account like :account or :accounts is null)").setParam("id", id),BetAgent.class,page);
				datas = betAgentService.queryForList(new Finder("select * from bet_agent where active=:active and parentids like :id and( nickname like :nickname or :nick is null) and (account like :account or :accounts is null)").setParam("nick", betAgent.getNickname()).setParam("accounts", betAgent.getAccount()).setParam("nickname", "%"+betAgent.getNickname()+"%").setParam("account", "%"+betAgent.getAccount()+"%").setParam("active", betAgent.getActive()).setParam("id", "%,"+id+",%"),BetAgent.class, page);
				String parentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", id), String.class); 
				String agent[] = parentids.split(",");
				List<String> list = new ArrayList<String>();
				for (int i = 1; i < agent.length; i++) {
					list.add(agent[i]);
				}
				list.add(id);
				String currentparentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class);
				String cuagent[] = currentparentids.split(",");
				List<String> list2 = new ArrayList<String>();
				for (int i = 1; i < cuagent.length; i++) {
					list2.add(cuagent[i]);
				}
				list.removeAll(list2);
				List<Map<String ,String >> list1 = new ArrayList<Map<String ,String >>();
				for (String string : list) {
					String nickname = betAgentService.queryForObject(new Finder("select nickname from bet_agent where agentid=:agentid ").setParam("agentid", string), String.class);
					Map<String ,String > map=new HashMap<>();
					map.put("agentid", string);
					if(StringUtils.isBlank(nickname)){
						map.put("agentnickname", "此账号无昵称");
					}else{
						map.put("agentnickname", nickname);
					}
					list1.add(map);
				}
				if(datas!=null){
					for(BetAgent betagent : datas){
						Double  bettingwin = soccerAllbettingService.queryForObject(new Finder("select sum(a.bettingscore-a.bettingmoney) from soccer_allbetting a  where( a.agentid=:agentid or a.agentparentids like :agentparentids) ").setParam("agentid", betagent.getAgentid()).setParam("agentparentids", "%,"+betagent.getAgentid()+",%"), Double.class);
						betagent.setBettingwin(bettingwin);		
					}
				}
				returnObject.setQueryBean(betAgent);
				returnObject.setPage(page);
				returnObject.setData(datas);
				model.addAttribute("agentid", agentid);
				model.addAttribute("List", list1);
				model.addAttribute("tran", betAgentAccountService.queryForObject(new Finder("select transferaccount from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class));
				model.addAttribute(GlobalStatic.returnDatas, returnObject);
				if("2".equals(request.getParameter("a"))){
					return "/lottery/betagent/businessPartnerList";
				}else{
					return listurl;
				}
			}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			if(betAgent.getActive()==null) {
				betAgent.setActive(1);
			}else if(betAgent.getActive()==3) {
				betAgent.setActive(0);
			}
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			String id = request.getParameter("agentid");
			List<BetAgent> datas = null;
			if(id == null){
				id = agentid;
			}
			if(betAgent.getNickname()==""){
				betAgent.setNickname(null);
			}
			if(betAgent.getNickname()==""){
				betAgent.setAccount(null);
			}
//			datas = betAgentService.queryForList(new Finder("select * from bet_agent where parentid =:id and( nickname like :nickname or :nick is null) and (account like :account or :accounts is null)").setParam("id", id),BetAgent.class,page);
			datas = betAgentService.queryForList(new Finder("select * from bet_agent where active=:active and parentid = :id and( nickname like :nickname or :nick is null) and (account like :account or :accounts is null)").setParam("nick", betAgent.getNickname()).setParam("accounts", betAgent.getAccount()).setParam("nickname", "%"+betAgent.getNickname()+"%").setParam("account", "%"+betAgent.getAccount()+"%").setParam("active", betAgent.getActive()).setParam("id", id),BetAgent.class, page);
			String parentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", id), String.class); 
			String agent[] = parentids.split(",");
			List<String> list = new ArrayList<String>();
			for (int i = 1; i < agent.length; i++) {
				list.add(agent[i]);
			}
			list.add(id);
			String currentparentids = betAgentService.queryForObject(new Finder("select parentids from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class);
			String cuagent[] = currentparentids.split(",");
			List<String> list2 = new ArrayList<String>();
			for (int i = 1; i < cuagent.length; i++) {
				list2.add(cuagent[i]);
			}
			list.removeAll(list2);
			List<Map<String ,String >> list1 = new ArrayList<Map<String ,String >>();
			for (String string : list) {
				String nickname = betAgentService.queryForObject(new Finder("select nickname from bet_agent where agentid=:agentid ").setParam("agentid", string), String.class);
				Map<String ,String > map=new HashMap<>();
				map.put("agentid", string);
				if(StringUtils.isBlank(nickname)){
					map.put("agentnickname", "此账号无昵称");
				}else{
					map.put("agentnickname", nickname);
				}
				list1.add(map);
			}
			if(datas!=null){
				for(BetAgent betagent : datas){
					Double  bettingwin = soccerAllbettingService.queryForObject(new Finder("select sum(a.bettingscore-a.bettingmoney) from soccer_allbetting a  where( a.agentid=:agentid or a.agentparentids like :agentparentids) ").setParam("agentid", betagent.getAgentid()).setParam("agentparentids", "%,"+betagent.getAgentid()+",%"), Double.class);
					betagent.setBettingwin(bettingwin);		
				}
			}
			returnObject.setQueryBean(betAgent);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("agentid", agentid);
			model.addAttribute("List", list1);
			model.addAttribute("tran", betAgentAccountService.queryForObject(new Finder("select transferaccount from bet_agent where agentid=:agentid").setParam("agentid", agentid), String.class));
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			if("2".equals(request.getParameter("a"))){
				return "/lottery/betagent/businessPartnerList";
			}else{
				return listurl;
			}
		}
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betAgent
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetAgent betAgent) throws Exception{
		String agentid = SessionUser.getShiroUser().getAgentid();
		List<BetAgent> agents = betAgentService.queryForList(new Finder("select * from bet_agent where (agentid=:id or parentids like :agentid) and allowagent=1 and active=1 ").setParam("id", agentid).setParam("agentid", "%,"+agentid+",%"), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		returnObject.setQueryBean(betAgent);
		returnObject.setPage(page);
		
		List<BetAgent> nodeList = new ArrayList<BetAgent>();      
		for(BetAgent node1 : agents){//taskDTOList 是数据库获取的List列表数据或者来自其他数据源的List  
		  
		            boolean mark = false;  
		            for(BetAgent node2 : agents){  
		                if(node1.getParentid()!=null && node1.getParentid().equals(node2.getAgentid())){  
		                    mark = true;  
		                    if(node2.getChildren() == null)  
		                        node2.setChildren(new ArrayList<BetAgent>());  
		                    node2.getChildren().add(node1);  
		                    break;  
		                }  
		            }  
		            if(!mark){  
		                nodeList.add(node1);  
		            }  
		        }  
		String jsonText = JSON.toJSONString(nodeList, true);  
		returnObject.setData(jsonText);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetAgent betAgent) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betAgentService.findDataExportExcel(null,listurl, page,BetAgent.class,betAgent);
		String fileName="betAgent"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		String agentid = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		String id=request.getParameter("id");
		BetAgent agent = betAgentService.findBetAgentById(id);
		model.addAttribute("id", agent.getId());
		model.addAttribute("agentid", agent.getAgentid());
		model.addAttribute("agentsx", agent.getScore());
		if("1".equals(request.getParameter("k"))){
			BetAgent betAgent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			Double sx = betAgent.getScore();
			model.addAttribute("sx", sx);
			return "/lottery/betagent/betagenttransferaccount";
		}else if("2".equals(request.getParameter("k"))){
			returnObject = ReturnDatas.getSuccessReturnDatas();
			// ==构造分页请求
			Page page = newPage(request);
			// ==执行分页查询
			List<BetTransferagentAccounts> accountsList = betTransferagentAccountsService.queryForList(new Finder("select * from bet_transferagent_accounts where transfermanagentid=:transfermanagentid").setParam("transfermanagentid", request.getParameter("agentid")), BetTransferagentAccounts.class,page);
			returnObject.setPage(page);
			returnObject.setData(accountsList);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betagenttransferaccountList";
		}else if("3".equals(request.getParameter("k"))){
			//登录日志
			String account = request.getParameter("account");
			returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("strDate");
			page.setSort("desc");
			List<Fwlog> datas = fwlogService.queryForList(new Finder("select * from t_fwlog_history_2017 where userCode=:account").setParam("account", account), Fwlog.class,page);
			returnObject.setQueryBean(new Fwlog());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("account", account);
			model.addAttribute("id", id);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betagentfwlogList";
		}else if("4".equals(request.getParameter("k"))){
			//操作日志
			String account = request.getParameter("account");
			returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			page.setOrder("time");
			page.setSort("desc");
			String starttime = request.getParameter("startTime");
			String endtime = request.getParameter("endTime");
			if(StringUtils.isBlank(starttime)){
				starttime="0000-01-01";
			}
			if(StringUtils.isBlank(endtime)){
				endtime="9999-01-01";
			}
			java.sql.Date startDate = java.sql.Date.valueOf(starttime);
			java.sql.Date endDate=java.sql.Date.valueOf(endtime);
			List<BetOptLog> datas = null;
			if(starttime=="0000-01-01" && endtime=="9999-01-01"){
				datas = betOptLogService.queryForList(new Finder("select * from bet_opt_log where account=:account").setParam("account", account), BetOptLog.class,page);
			}else{
				datas = betOptLogService.queryForList(new Finder("select * from bet_opt_log where account=:account and time>=:starttime and time<=:endtime").setParam("account", account).setParam("starttime",startDate).setParam("endtime", endDate), BetOptLog.class,page);
			}
			if(starttime=="0000-01-01"){
				startDate=null;
			}
			if(endtime=="9999-01-01"){
				endDate=null;
			}
			returnObject.setQueryBean(new BetOptLog());
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute("account", account);
			model.addAttribute("id", id);
			model.addAttribute("startTime", startDate);
			model.addAttribute("endTime", endDate);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betoptlogList";
		}else{
			return "/lottery/betagent/betagentLook";
			
		}
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String parameter = request.getParameter("agentid");
		if(StringUtils.isNotBlank(parameter)){
			BetAgent queryForObject = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid and active=1").setParam("agentid", parameter), BetAgent.class);
			returnObject.setData(queryForObject);
			return returnObject;
		}
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  BetAgent betAgent = betAgentService.findBetAgentById(id);
		   returnObject.setData(betAgent);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
	}
	
	
	
	/**
	 * 转移代理下的用户
	 */
	@RequestMapping("/updatememberagentid")
	public @ResponseBody
	ReturnDatas updatememberagentid(Model model,BetAgent betAgent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid = SessionUser.getShiroUser().getAgentid();
		try {
			String parentid = betAgentService.queryForObject(new Finder("select parentid from bet_agent where agentid=:agentid ").setParam("agentid", agentid), String.class);
			if(StringUtils.isEmpty(parentid)||!"A101".equals(parentid)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("只有一級代理可以转移用戶");
				return returnObject;
			}
			//输入的代理
			String agentaccount = request.getParameter("agentaccount");
			BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where account=:account ").setParam("account", agentaccount), BetAgent.class);
			if(agent==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此代理账号");
				return returnObject;
			}
			//选择的代理
			String a = request.getParameter("a");
			BetAgent agent1 = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", a), BetAgent.class);
			//选择代理的上级代理
			String agentid2 = agent1.getAgentid();
			//选择代理的Parentids
			String parentids = agent1.getParentids();
			
			//当前操作者
			BetAgent agent2 = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			if(!(agentid2.equals(agent2.getAgentid())||parentids.indexOf(agent2.getAgentid())!=-1)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("不可转移非本级代理下用户");
				return returnObject;
			}
			if(!agentid2.equals(agent.getAgentid())){
				Integer num = betMemberService.update(new Finder("update bet_member set agentid=:agentid,agentparentid=:agentparentid,agentparentids=:agentparentids where agentid=:agentxxx ").setParam("agentxxx", agentid2).setParam("agentid", agent.getAgentid()).setParam("agentparentid",agent.getParentid()).setParam("agentparentids", agent.getParentids()));
				betAgentService.update(new Finder("update bet_agent set membernum=membernum+:num where agentid=:agentid ").setParam("num", num).setParam("agentid", agent.getAgentid()));
				betAgentService.update(new Finder("update bet_agent set membernum=membernum-:num where agentid=:agentid ").setParam("num", num).setParam("agentid", agentid2));
				
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("原代理和要转移代理不可相同");
				return returnObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	/**
	 * 转移代理
	 * 
	 */
	@RequestMapping("/transferagent")
	public @ResponseBody
	ReturnDatas transferagent(Model model,BetAgent betAgent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid = SessionUser.getShiroUser().getAgentid();
		try {
			//输入的代理(大)
			String agentaccount = request.getParameter("agentaccount");
			BetAgent agent = betAgentService.queryForObject(new Finder("select *from bet_agent where account=:account ").setParam("account", agentaccount), BetAgent.class);
			if(agent==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("无此代理账号");
				return returnObject;
			}
			//选择的代理(被转移的)agentid
			String a = request.getParameter("a");
			BetAgent agent1 = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", a), BetAgent.class);
			String agentid2 = agent1.getAgentid();
			String parentids = agent1.getParentids();
			String parentid2 = agent1.getParentid();//被转移代理的上级代理id
			
			//当前操作者
			BetAgent agent2 = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
			if(!(agentid2.equals(agent2.getAgentid())||parentids.indexOf(agent2.getAgentid())!=-1)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("不可转移非本级代理下代理");
				return returnObject;
			}
			
			//String agentparentids = agent.getParentids() + agentaccount + "," + a + ",";
			String agentparentids = agent.getParentids() + agentaccount + ",";
			if(!agentid2.equals(agent.getAgentid())){
				//被转移代理的下级所有代理
				List<BetAgent> agents = betAgentService.queryForList(new Finder("select * from bet_agent where parentid=:parentid or parentids like :parentids ").setParam("parentid", a).setParam("parentids", "%,"+a+",%"), BetAgent.class);
				if(!agents.isEmpty()){
					for (BetAgent ag : agents) {
						String[] split = ag.getParentids().split(","+a);
						if(split.length>=2){
							//betAgentService.update(new Finder("update bet_agent set parentids=:parentids where agentid=:agentid ").setParam("parentids", agent.getParentids()+a+split[1]).setParam("agentid", ag.getAgentid()));
							betAgentService.update(new Finder("update bet_agent set parentids=:parentids where agentid=:agentid ").setParam("parentids", agent.getParentids()+agentaccount+","+a+split[1]).setParam("agentid", ag.getAgentid()));
							//该代理下的用户
							//betMemberService.update(new Finder("update bet_member set agentparentids=:agentparentids where agentid=:agentid ").setParam("agentparentids", agent.getParentids()+a+split[1]).setParam("agentid", ag.getAgentid()));
							betMemberService.update(new Finder("update bet_member set agentparentids=:agentparentids where agentid=:agentid ").setParam("agentparentids", agent.getParentids()+agentaccount+","+a+split[1]).setParam("agentid", ag.getAgentid()));
						}
					}
				}
				betAgentService.update(new Finder("update bet_agent set parentid=:parentid,parentids=:parentids where agentid=:agentid ").setParam("parentid", agentaccount).setParam("parentids", agent.getParentids() + agentaccount + ",").setParam("agentid", a));
				//betMemberService.update(new Finder("update bet_member set agentparentids=:agentparentids where agentid=:agentid ").setParam("agentparentids", agentparentids).setParam("agentid", a));
				betMemberService.update(new Finder("update bet_member set agentparentids=:agentparentids,agentparentid=:agentparentid where agentid=:agentid ").setParam("agentparentids", agentparentids).setParam("agentparentid", agentaccount).setParam("agentid", a));
				Integer count = betMemberService.queryForObject(new Finder("select count(*) from bet_member where agentid=:agentid or agentparentids like :agentparentids ").setParam("agentid", agentaccount).setParam("agentparentids","%,"+agentaccount+",%"), Integer.class);
				Integer agcount = betAgentService.queryForObject(new Finder("select count(*) from bet_agent where parentid=:parentid or parentids like :parentids ").setParam("parentid", agentaccount).setParam("parentids", "%,"+agentaccount+",%"), Integer.class);
				betAgentService.update(new Finder("update bet_agent set membernum=:membernum,subordinate=:subordinate where agentid=:agentid ").setParam("membernum", count).setParam("subordinate", agcount).setParam("agentid", agentaccount));
				
				//被转移的代理原上级也要更新用户个数和下属代理个数
				Integer countBP = betMemberService.queryForObject(new Finder("select count(*) from bet_member where agentid=:agentid or agentparentids like :agentparentids ").setParam("agentid", parentid2).setParam("agentparentids","%,"+parentid2+",%"), Integer.class);
				Integer agcountBP = betAgentService.queryForObject(new Finder("select count(*) from bet_agent where parentid=:parentid or parentids like :parentids ").setParam("parentid", parentid2).setParam("parentids", "%,"+parentid2+",%"), Integer.class);
				betAgentService.update(new Finder("update bet_agent set membernum=:membernum,subordinate=:subordinate where agentid=:agentid ").setParam("membernum", countBP).setParam("subordinate", agcountBP).setParam("agentid", parentid2));
				
				
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("原代理和要转移代理不可相同");
				return returnObject;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		
		/*String[] split = ",A101,fhjc,fhjc-1,zhaoyue,shenhuabu,9999999".split(",shenhuabu");
		if(split.length>=2){
			System.out.println("split0="+split[0]+"  split1="+split[1]);
		}else{
			System.out.println("split0="+split[0]);
		}*/
		
		return returnObject;
	
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,BetAgent betAgent,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid = SessionUser.getShiroUser().getAgentid();
		
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		try {
			if("1".equals(request.getParameter("g"))){
				String check = request.getParameter("check");
				String str = request.getParameter("agentid");
				if(!check.isEmpty()){
					String[] gameclassid = check.split(",");
					List<BetAgentGamemanage> betAgentGamemanage=betAgentGamemanageService.findListDataByFinder(new Finder("select agentid from bet_agent_gamemanage where agentid=:agentid").setParam("agentid", str), null, BetAgentGamemanage.class, null);
					if(betAgentGamemanage.size()>0){
						betAgentGamemanageService.update(new Finder("update bet_agent_gamemanage set state=:state where agentid=:agentid").setParam("state", 0).setParam("agentid", str));
						if(gameclassid.length>0){
							for (int i = 0; i < gameclassid.length; i++) {
								betAgentGamemanageService.update(new Finder("update bet_agent_gamemanage set state=:state where gameclassid=:gameclassid and agentid=:agentid").setParam("state", 1).setParam("gameclassid", gameclassid[i]).setParam("agentid", str));
							}
						}
					}
				}else{
					betAgentGamemanageService.update(new Finder("update bet_agent_gamemanage set state=0 where agentid=:agentid").setParam("agentid", str));
				}
				betOptLogService.saveoptLog(AgentToolUtil.getAgentTool(request),IPUtils.getClientAddress(request),"id为:"+agentid+"的代理商修改id为:"+str+"的代理商的游戏权限",agentid,betAgent.getParentid(),betAgent.getParentids());
			}else if("1".equals(request.getParameter("k"))){
				Double subordinaterebate = betAgent.getSubordinaterebate()/100;
				Double ownsubordinaterebate = betAgent.getOwnsubordinaterebate()/100;
				if("A101".equals(betAgent.getParentid())){
					betAgentService.update(new Finder("update bet_agent set subordinaterebate=:subordinaterebate,ownsubordinaterebate=:ownsubordinaterebate where agentid=:agentid")
					.setParam("ownsubordinaterebate", ownsubordinaterebate).setParam("subordinaterebate", subordinaterebate).setParam("agentid", betAgent.getAgentid()));
					returnObject.setMessage("修改推广返利成功");
					return returnObject;
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("只有一级代理可以修改");
					return returnObject;
				}
				
			}else{
				if("1".equals(request.getParameter("j"))){
					String state = request.getParameter("state");
					String aid = request.getParameter("agentid");
					betAgentService.update(new Finder("update bet_agent set active=:state where agentid=:agentid").setParam("state", state).setParam("agentid", aid));
					betAgentService.update(new Finder("update t_user set active=:state where agentid=:agentid ").setParam("state", state).setParam("agentid", aid));
				}else{
					if("1".equals(request.getParameter("u"))){
						String account = request.getParameter("account");
						String str = userService.queryForObject(new Finder("select account from t_user where account=:account").setParam("account", account), String.class);
						if(str!=null){
							returnObject.setStatus(ReturnDatas.ERROR);
						}
			}else{
				if("1".equals(request.getParameter("i"))){
					String accountname = request.getParameter("accountname");
					String accountnum = request.getParameter("accountnum");
					String bankofdeposit = request.getParameter("bankofdeposit");
					BetAgent agent = betAgentService.queryForObject(new Finder("select parentid,parentids from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
					BetAgentAccount betAgentAccount = new BetAgentAccount();
					betAgentAccount.setAccountname(accountname);
					betAgentAccount.setAccountnum(accountnum);
					betAgentAccount.setAgentid(agentid);
					betAgentAccount.setBankofdeposit(bankofdeposit);
					betAgentAccount.setParentid(agent.getParentid());
					betAgentAccount.setParentids(agent.getParentids());
					betAgentAccountService.save(betAgentAccount);
				}else{
					if(betAgent.getRegistrationtime()==null){
						if(betAgent.getAccount().contains(",")||betAgent.getAccount().contains("，")){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("代理账号不能包含逗号！");
							return returnObject;
						}
						//代理账号不能含有中文
						Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
				        Matcher matcher = pattern.matcher(betAgent.getAccount());
				        if (matcher.find()) {
				        	returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("代理账号不能包含中文！");
							return returnObject;
				        }
						
						BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", betAgent.getAccount()), BetAgent.class);
						if(agent!=null){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("此账号已存在！");
							return returnObject;
						}
						String str = userService.queryForObject(new Finder("select account from t_user where account=:account").setParam("account", betAgent.getAccount()), String.class);
						if(str!=null){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("此账号已存在！");
							return returnObject;
						}

						Integer count = betMemberService.queryForObject(new Finder("select count(*) from bet_member where agentparentids like :agentparentids ").setParam("agentparentids", "%," + betAgent.getAccount() + ",%"), Integer.class);
						if(count>0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("此账号已存在！");
							return returnObject;
						}

						betAgent.setMembernum(0);
						if(!betAgent.getParentid().isEmpty()){
							BetAgent paeddd = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", betAgent.getParentid()), BetAgent.class);
							if((paeddd.getAgentid().equals(agentid))||(paeddd.getParentids().contains(agentid))){
								
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("添加失败，不能添加上级代理的下级代理！");
								return returnObject;
							}
							if(paeddd.getBettingrebate()!=null){
								if(betAgent.getBettingrebate()!=null){
									betAgent.setBettingrebate(betAgent.getBettingrebate()/100);
									if(paeddd.getBettingrebate()<(betAgent.getBettingrebate())){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，下级代理投注退佣比例不能大于上级代理投注退佣比例！");
										return returnObject;
									}else if((betAgent.getBettingrebate()>1)||(betAgent.getBettingrebate()<0)){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，投注退佣比例在0~1之间！");
										return returnObject;
									}
									
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage("添加失败，投注退佣比例不能为空！");
									return returnObject;
								}
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("添加失败，上级代理未设置投注退佣比例！");
								return returnObject;
							}
							if(paeddd.getBettingrebate2()!=null){
								if(betAgent.getBettingrebate2()!=null){
									betAgent.setBettingrebate2(betAgent.getBettingrebate2()/100);
									if(paeddd.getBettingrebate2()<(betAgent.getBettingrebate2())){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，下级代理跟单退佣比例不能大于上级代理跟单退佣比例！");
										return returnObject;
									}else if((betAgent.getBettingrebate2()>1)||(betAgent.getBettingrebate2()<0)){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，跟单退佣比例在0~1之间！");
										return returnObject;
									}
									
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage("添加失败，跟单退佣比例不能为空！");
									return returnObject;
								}
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("添加失败，上级代理未设置跟单退佣比例！");
								return returnObject;
							}
							
							
							
							List<BetAgent> parentAgent = betAgentService.findListDataByFinder(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", betAgent.getParentid()), null, BetAgent.class, null);
							if(parentAgent.get(0).getParentids()==null){
								betAgent.setParentids(","+parentAgent.get(0).getAgentid()+",");
							}else{
								betAgent.setParentids(parentAgent.get(0).getParentids()+parentAgent.get(0).getAgentid()+",");
							}
							String parentids = betAgent.getParentids();
							if(!(betAgent.getParentids().startsWith(","))){
								betAgent.setParentids(","+betAgent.getParentids());
							}
							if(parentids!=null){
								
								if(parentids.startsWith(",")){
									String replace = parentids.substring(1);
									parentids=replace;
								}
								String[] parentid = parentids.split(",");
								
								for (int i = 1; i < parentid.length; i++) {
									betAgentService.update(new Finder("update bet_agent set subordinate = subordinate+1 where agentid=:agentid").setParam("agentid", parentid[i]));
								}
							}
						if(!"A101".equals(betagent.getParentid())){
							betAgent.setIswithdraw(0);
						}
						betAgent.setSx(0d);
						betAgent.setTy2(0d);
						betAgent.setMembernum(0);
						betAgent.setTransferaccountsscore(0d);
						betAgent.setTy(0d);
						betAgent.setScore(0d);
						betAgent.setLogin(0);
						betAgent.setSubordinate(0);
						betAgent.setRegistrationtime(new Date());
						betAgent.setAllowagent(1);
						betAgent.setTransferaccount(1);
						betAgent.setRecharge(1);
						betAgent.setExchange(1);
						betAgent.setRechargerebate(0d);
						betAgent.setAgentid(betAgent.getAccount());
						betAgent.setWinorlossrebate(0d);
						betAgent.setCompanyproportion(0.);
						betAgent.setPassword(SecUtils.encoderByMd5With32Bit(betAgent.getPassword()));
						betAgent.setBankpassword(SecUtils.encoderByMd5With32Bit(betAgent.getBankpassword()));
						List<BetGameclass> gameList = betGameclassService.findListDataByFinder(new Finder("select gameclassid,gcname from bet_gameclass order by gameclassid"), null, BetGameclass.class, null);
						if(gameList!=null&&(!gameList.isEmpty())){
							List<BetAgentGamemanage> list = new ArrayList<BetAgentGamemanage>();
							for (BetGameclass betGameclass : gameList) {
								BetAgentGamemanage gamemanage = new BetAgentGamemanage();
								gamemanage.setAgentid(betAgent.getAgentid());
								gamemanage.setState(0);
								gamemanage.setGameclassid(betGameclass.getGameclassid());
								gamemanage.setGcname(betGameclass.getGcname());
								list.add(gamemanage);
							}
							betAgentGamemanageService.save(list);
						}
					}
						betAgent.setId(null);
						String id=UUID.randomUUID().toString();
						User user = new User();
						user.setId(id);
						user.setName(betAgent.getNickname());
						user.setAccount(betAgent.getAccount());
						user.setPassword(betAgent.getPassword());
						user.setMobile(betAgent.getMobile());
						user.setWeixinId(betAgent.getWeixin());
						user.setUserType(0);
						user.setActive(1);
						user.setQq(betAgent.getQq());
						user.setIsagent(1);
						user.setAgentid(betAgent.getAgentid());
						userService.save(user);
						UserRole role = new UserRole();
						role.setId(UUID.randomUUID().toString());
						role.setUserId(id);
						role.setRoleId("r_10002");
						userRoleMenuService.save(role);
						UserOrg userOrg = new UserOrg();
						userOrg.setId(UUID.randomUUID().toString());
						userOrg.setUserId(id);
						userOrg.setOrgId("o_10001");
						userOrg.setManagerType(11);
						userOrg.setHasleaf(1);
						userOrgService.save(userOrg);
						betOptLogService.saveoptLog(AgentToolUtil.getAgentTool(request),IPUtils.getClientAddress(request),"新增代理商,id为:"+betAgent.getAgentid(),agentid,betAgent.getParentid(),betAgent.getParentids());
						if(betAgent.getAllowagent()==null){
							betAgent.setAllowagent(0);
						}
						if(betAgent.getTransferaccount()==null){
							betAgent.setTransferaccount(0);
						}
						if(betAgent.getRecharge()==null){
							betAgent.setRecharge(0);
						}
						if (betAgent.getExchange()==null) {
							betAgent.setExchange(0);
						}
						
						betAgentService.save(betAgent);
						String[] split = betAgent.getParentids().split(",");
						if(split.length>=3){
							List<BetGame> betGames = betGameService.queryForList(new Finder("select * from bet_game where company=:company").setParam("company", split[2]), BetGame.class);
							if(!betGames.isEmpty()){
								for (BetGame betGame : betGames) {
									BetGameAgent betGameAgent = new BetGameAgent();
									betGameAgent.setId(null);
									betGameAgent.setAgentid(betAgent.getAgentid());
									betGameAgent.setGamename(betGame.getGamename());
									betGameAgent.setImg(betGame.getImg());
									betGameAgent.setRemark(betGame.getRemark());
									betGameAgent.setState(betGame.getState());
									betGameAgent.setTitle(betGame.getTitle());
									betGameAgent.setCompany(split[2]);
									betGameAgentService.save(betGameAgent);
								}
							}
						}
					}else{
						BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", betAgent.getAgentid()), BetAgent.class);
						BetAgent paeddd = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", betAgent.getParentid()), BetAgent.class);
						if(paeddd!=null){
							if((paeddd.getAgentid().equals(agentid))||(paeddd.getParentids().contains(agentid))){
								
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("添加失败，不能添加上级代理的下级代理！");
								return returnObject;
							}
							if(paeddd.getBettingrebate()!=null){
								if(betAgent.getBettingrebate()!=null){
									betAgent.setBettingrebate(betAgent.getBettingrebate()/100);
									if(paeddd.getBettingrebate()<betAgent.getBettingrebate()){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，投注退佣比例不能大于上级代理投注退佣比例！");
										return returnObject;
									}else if((betAgent.getBettingrebate()>1)||(betAgent.getBettingrebate()<0)){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，投注退佣比例在0~1之间！");
										return returnObject;
									}
									
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage("添加失败，投注退佣比例不能为空！");
									return returnObject;
								}
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("添加失败，上级代理未设置投注退佣比例！");
								return returnObject;
							}
							if(paeddd.getBettingrebate2()!=null){
								if(betAgent.getBettingrebate2()!=null){
									betAgent.setBettingrebate2(betAgent.getBettingrebate2()/100);
									if(paeddd.getBettingrebate2()<betAgent.getBettingrebate2()){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，跟投退佣比例不能大于上级代理跟投退佣比例！");
										return returnObject;
									}else if((betAgent.getBettingrebate2()>1)||(betAgent.getBettingrebate2()<0)){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage("添加失败，跟投退佣比例在0~1之间！");
										return returnObject;
									}
									
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage("添加失败，跟投退佣比例不能为空！");
									return returnObject;
								}
							}else{
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("添加失败，上级代理未设置跟投退佣比例！");
								return returnObject;
							}
						}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("添加失败，无此上级用户！");
							return returnObject;
						}
						
						if(agent==null){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("无此代理！");
							return returnObject;
						}
						
						if(!(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1==1&& Integer.valueOf(new SimpleDateFormat("HH").format(new Date()))>=0 && Integer.valueOf(new SimpleDateFormat("HH").format(new Date()))<12)){
//							betAgent.setCompanyproportion(agent.getCompanyproportion());
						}
						
						if(!betAgent.getPassword().equals(agent.getPassword())){
							betAgent.setPassword(SecUtils.encoderByMd5With32Bit(betAgent.getPassword()));
							userService.update(new Finder("update t_user set password=:password where agentid=:id").setParam("password", betAgent.getPassword()).setParam("id", betAgent.getAgentid()));
							betAgentService.update(new Finder("update bet_agent set password=:password where agentid=:agentid ").setParam("password", betAgent.getPassword()).setParam("agentid", betAgent.getAgentid()));
						}else{
							betAgent.setPassword(agent.getPassword());
						}
						if(!betAgent.getBankpassword().equals(agent.getBankpassword())){
							betAgent.setBankpassword(SecUtils.encoderByMd5With32Bit(betAgent.getBankpassword()));
						}else{
							betAgent.setBankpassword(betAgent.getBankpassword());
						}
						betOptLogService.saveoptLog(AgentToolUtil.getAgentTool(request),IPUtils.getClientAddress(request),"修改代理商id为:"+betAgent.getAgentid()+"的信息",agentid,betAgent.getParentid(),betAgent.getParentids());
						
						
						//机器人底下用户全部为不出票
						if(betAgent.getIsrobot()==1){
							String agentId=betAgent.getAgentid();
							String parentids = ","+agentId+",";
							betAgentService.update(new Finder("update bet_member set isissue=:isissue where agentid=:agentid or agentparentids like :agentparentids ").setParam("isissue", 0).setParam("agentid", agentId).setParam("agentparentids", "%"+parentids+"%"));
						}
						
						
						if(betAgent.getAllowagent()==null){
							betAgent.setAllowagent(0);
						}
						if(betAgent.getTransferaccount()==null){
							betAgent.setTransferaccount(0);
						}
						if(betAgent.getRecharge()==null){
							betAgent.setRecharge(0);
						}
						if (betAgent.getExchange()==null) {
							betAgent.setExchange(0);
						}
						if(!"A101".equals(betagent.getParentid())){
							betAgent.setIswithdraw(agent.getIswithdraw());
						}
						betAgent.setId(agent.getId());
						betAgent.setAgentid(null);
						betAgent.setParentid(null);
						betAgent.setParentids(null);
						betAgent.setScore(null);
						betAgent.setAccount(null);
						betAgent.setTransferaccountsscore(null);
						betAgent.setTy(null);
						betAgent.setSx(null);
						betAgent.setTy2(null);
						betAgent.setSubordinate(null);
						betAgent.setLogin(null);
						betAgent.setRegistrationtime(null);
						betAgent.setMembernum(null);
						
						betAgentService.update(betAgent,true);
						
					}
					
					}
				}
			}
			}
		} catch (Exception e) {
			System.out.println(e);
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
		String agentid = SessionUser.getShiroUser().getAgentid();
		model.addAttribute("agentid", agentid);
		ReturnDatas returnObject = lookjson(model, request, response);
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
		model.addAttribute("agent", agent);
		model.addAttribute("isRobot", 0);
		if("1".equals(request.getParameter("a"))){
			
			return "/lottery/betagent/betagentManage";
		}else if("2".equals(request.getParameter("a"))){
			if(!(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1==1&& Integer.valueOf(new SimpleDateFormat("HH").format(new Date()))>=0 && Integer.valueOf(new SimpleDateFormat("HH").format(new Date()))<12)){
				//每周一0~12点解锁
				model.addAttribute("lock", "1");
			}
			List<BetAgent> betAgentList = betAgentService.findListDataByFinder(new Finder("select agentid from bet_agent where allowagent=:allowagent order by agentid ").setParam("allowagent", 1), null, BetAgent.class, null);
			//代理充值返利设置
			List<BetAgentRechargeRebate> betagentrechargerebatelist = betAgentRechargeRebateService.queryForList(new Finder("select *from bet_agent_recharge_rebate where agentid=:agentid order by lowerlimit ").setParam("agentid", request.getParameter("agentid")), BetAgentRechargeRebate.class);
			model.addAttribute("betagentrechargerebatelist", betagentrechargerebatelist);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("betAgentList", betAgentList);
			
			//是否机器人设置
			BetAgent data = (BetAgent)returnObject.getData();
			String parentids = data.getParentids();
			String[] splitParent=parentids.split(",");
			if(splitParent.length==3 && (parentids.contains("99jc") || parentids.contains("fhjc") || parentids.contains("klc") || parentids.contains("leying"))){
				model.addAttribute("isRobot", 1);
			}/*else{
				model.addAttribute("isRobot", 0);
			}*/
			
			return "/lottery/betagent/betagentCru";
		}else if("3".equals(request.getParameter("a"))){
			List<BetAgentGamemanage> betAgentGamemanagesList = betAgentGamemanageService.findListDataByFinder(new Finder("select a.id,a.gameclassid,a.gcname,a.agentid,a.state from bet_agent_gamemanage a left join bet_gameclass b on a.gameclassid = b.gameclassid where a.agentid=:agentid").setParam("agentid", request.getParameter("agentid")), null, BetAgentGamemanage.class, null);
			model.addAttribute("betAgentGamemanagesList", betAgentGamemanagesList);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betAgentGameManage";
		}else{
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betagent/betagentCru";
		}
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
			java.lang.String id=request.getParameter("id");
			if("1".equals(request.getParameter("d"))){
				
				if(StringUtils.isNotBlank(id)){
					betAgentAccountService.deleteById(id, BetAgentAccount.class);
					return new ReturnDatas(ReturnDatas.SUCCESS,
							MessageUtils.DELETE_SUCCESS);
				} else {
					return new ReturnDatas(ReturnDatas.WARNING,
							MessageUtils.DELETE_WARNING);
				}
			}else{
		
		if(StringUtils.isNotBlank(id)){
				betAgentService.deleteById(id,BetAgent.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
			}
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
			betAgentService.deleteByIds(ids,BetAgent.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
