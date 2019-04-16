package  org.springrain.lottery.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
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
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetGameplay;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetGameclassService;
import org.springrain.lottery.service.IBetGameplayService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.utils.AgentToolUtil;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 15:19:18
 * @see org.springrain.lottery.web.BetGameplay
 */
@Controller
@RequestMapping(value="/betgameplay")
public class BetGameplayController  extends BaseController {
	@Resource
	private IBetGameplayService betGameplayService;
	@Resource
	private IBetGameclassService betGameclassService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private ICached cached;
	private String listurl="/lottery/betgameplay/betgameplayList";
	
	
	/**
	 * 玩法状态
	 * 
	 * @param request
	 * @param model
	 * @param betGameplay
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param request
	 * @param model
	 * @param betGameplay
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/name1list")
	public String name1list(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		Integer gameclassid = betGameplay.getGameclassid();
		if(gameclassid!=null){
			List<Map<String, Object>> queryForList = betGameplayService.queryForList(new Finder("select name1,state from bet_gameplay where gameclassid=:gameclassid group by name1 ").setParam("gameclassid", gameclassid));
			model.addAttribute("name1list", queryForList);
			model.addAttribute("gameclassid", gameclassid);
			return "/lottery/betgameplay/name1List";
		}else{
			return "/errorpage/error";
		}
	} 
	/**
	 * 修改玩法状态
	 * 
	 * @param request
	 * @param model
	 * @param betGameplay
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param request
	 * @param model
	 * @param betGameplay
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/name1list/update")
	@ResponseBody
	public ReturnDatas name1listupdate(Model model,BetGameplay betGameplay,HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
			String agentid= SessionUser.getAgentId();
			BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
			try {
				Integer gameclassid = betGameplay.getGameclassid();
				String name1 = betGameplay.getName1();
				String checked = request.getParameter("checked");
				Integer state=null;
				if("false".equals(checked)){
					state=0;
				}else if("true".equals(checked)){
					state=1;
				}
				
				if(gameclassid!=null&&name1!=null&&checked!=null&&state!=null){
					betGameplayService.update(new Finder("update bet_gameplay set state =:state where gameclassid=:gameclassid and name1=:name1 ").setParam("gameclassid", gameclassid).setParam("name1", name1).setParam("state", state));
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					return returnObject;
				}
				//删除缓存
				try{
					String l=gameclassid+name1;
					cached.deleteCached(l.getBytes("UTF-8"));
					cached.deleteCached((gameclassid+"").getBytes("UTF-8"));
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
				}
				
				//日志记录
				String details="";
				if(state==1){
					details = "启用ID为"+betGameplay.getGameclassid()+"的游戏的"+betGameplay.getName1()+"的玩法";
				}else if(state==0){
					details = "停用ID为"+betGameplay.getGameclassid()+"的游戏的"+betGameplay.getName1()+"的玩法";
				}
				 
				String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
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
	 * @param betGameplay
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		if("1".equals(request.getParameter("currentBetting"))){
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			Integer gameclassid = betGameplay.getGameclassid();
			if(gameclassid==5||gameclassid==6||gameclassid==10||gameclassid==11||gameclassid==12||gameclassid==16||gameclassid==17||gameclassid==18||gameclassid==19||gameclassid==20||gameclassid==21|gameclassid==22||gameclassid==23||gameclassid==24||gameclassid==29||gameclassid==30||gameclassid==31||gameclassid==32||gameclassid==33||gameclassid==36||gameclassid==35){
				return "/lottery/betgameplay/bet28gameplayList";
			}
			return listurl;
		}else{
			return listurl;
		}
	}
	
	@RequestMapping("/list1")
	public String list1(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//新疆时时彩
			betGameplay.setGameclassid(8);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/currentBettingList";
		
		
	}
	@RequestMapping("/list2")
	public String list2(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
			//即时注单
			//新加坡5D
			betGameplay.setGameclassid(9);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/currentBettingList";
		
		
	}
	
	@RequestMapping("/list3")
	public String list3(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//北京28
			betGameplay.setGameclassid(5);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list4")
	public String list4(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
		//即时注单
		//重庆时时彩
		betGameplay.setGameclassid(7);
		ReturnDatas returnObject = listjson(request, model, betGameplay);
		model.addAttribute("gameclassid", betGameplay.getGameclassid());
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgameplay/currentBettingList";
		
		
	}
	@RequestMapping("/list5")
	public String list5(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//北京11
			betGameplay.setGameclassid(10);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list6")
	public String list6(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//北京16
			betGameplay.setGameclassid(11);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list7")
	public String list7(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//北京36
			betGameplay.setGameclassid(12);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list8")
	public String list8(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//加拿大11
			betGameplay.setGameclassid(16);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list9")
	public String list9(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//加拿大16
			betGameplay.setGameclassid(17);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list10")
	public String list10(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//加拿大28
			betGameplay.setGameclassid(6);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list11")
	public String list11(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//加拿大36
			betGameplay.setGameclassid(18);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list12")
	public String list12(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//蛋蛋28
			betGameplay.setGameclassid(19);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list13")
	public String list13(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//蛋蛋36
			betGameplay.setGameclassid(20);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list14")
	public String list14(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//新加坡11
			betGameplay.setGameclassid(21);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list15")
	public String list15(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//新加坡16
			betGameplay.setGameclassid(22);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list16")
	public String list16(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//新加坡28
			betGameplay.setGameclassid(23);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list17")
	public String list17(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//新加坡36
			betGameplay.setGameclassid(24);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list18")
	public String list18(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//PK10
			betGameplay.setGameclassid(29);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list19")
	public String list19(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
			//即时注单
			//PK22
			betGameplay.setGameclassid(30);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list20")
	public String list20(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//PK冠军
			betGameplay.setGameclassid(31);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list21")
	public String list21(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//PK龙虎
			betGameplay.setGameclassid(32);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list22")
	public String list22(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//PK冠亚军
			betGameplay.setGameclassid(33);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list23")
	public String list23(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
			//即时注单
			//韩国五朵金花
			betGameplay.setGameclassid(34);
			ReturnDatas returnObject = listjson(request, model, betGameplay);
			model.addAttribute("gameclassid", betGameplay.getGameclassid());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return "/lottery/betgameplay/currentBettingList";
		
		
	}
	@RequestMapping("/list24")
	public String list24(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
		//即时注单
		//蛋蛋11
		betGameplay.setGameclassid(35);
		ReturnDatas returnObject = listjson(request, model, betGameplay);
		model.addAttribute("gameclassid", betGameplay.getGameclassid());
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgameplay/28currentBettingList";
		
		
	}
	@RequestMapping("/list25")
	public String list25(HttpServletRequest request, Model model,BetGameplay betGameplay) 
			throws Exception {
		
		//即时注单
		//蛋蛋16
		betGameplay.setGameclassid(36);
		ReturnDatas returnObject = listjson(request, model, betGameplay);
		model.addAttribute("gameclassid", betGameplay.getGameclassid());
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgameplay/28currentBettingList";
		
	}
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betGameplay
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetGameplay betGameplay) throws Exception{
		if(betGameplay.getGameclassid()==null){
			betGameplay.setGameclassid(7);
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("order1");
		page.setSort("asc");
		// ==执行分页查询
		page.setPageSize(3000);
		List<BetGameplay> datas=betGameplayService.findListDataByFinder(null,page,BetGameplay.class,betGameplay);
		//
		if(!"1".equals(request.getParameter("currentBetting"))){
			//即时注单
			List<Map<String, Object>> list = betBettingService.queryForList(new Finder("select gameplayid,sum(bettingmoney) from bet_betting where state=0 group by gameplayid "));
			if(datas!=null){
				Double one=0.;
				Double two=0.;
				Double three=0.;
				Double four=0.;
				Double five=0.;
				for (BetGameplay betGameplay2 : datas) {
					Integer gameplayid = betGameplay2.getId();
					Double gameplaybetting =0.;
					
					if(list!=null){
						for (Map<String, Object> map : list) {
							 if(gameplayid.equals(map.get("gameplayid"))){
								 gameplaybetting=((BigDecimal)map.get("sum(bettingmoney)")).doubleValue();
							 }
						}
					}
					if("连码".equals(betGameplay2.getName1())){
						if("一中多".equals(betGameplay2.getName2())){
							one+=gameplaybetting;
						}else if("二中二".equals(betGameplay2.getName2())){
							two+=gameplaybetting;
						}else if("三中三".equals(betGameplay2.getName2())){
							three+=gameplaybetting;
						}else if("四中四".equals(betGameplay2.getName2())){
							four+=gameplaybetting;
						}else if("五中五".equals(betGameplay2.getName2())){
							five+=gameplaybetting;
						}
					}
					betGameplay2.setGameplaybetting(gameplaybetting);
				}
				for (BetGameplay betGameplay2 : datas) {
					if("一中多".equals(betGameplay2.getName2())&&"连码".equals(betGameplay2.getName1())&&"0".equals(betGameplay2.getName3())){
						betGameplay2.setGameplaybetting(one);
					}else if("二中二".equals(betGameplay2.getName2())&&"连码".equals(betGameplay2.getName1())&&"0".equals(betGameplay2.getName3())){
						betGameplay2.setGameplaybetting(two);
					}else if("三中三".equals(betGameplay2.getName2())&&"连码".equals(betGameplay2.getName1())&&"0".equals(betGameplay2.getName3())){
						betGameplay2.setGameplaybetting(three);
					}else if("四中四".equals(betGameplay2.getName2())&&"连码".equals(betGameplay2.getName1())&&"0".equals(betGameplay2.getName3())){
						betGameplay2.setGameplaybetting(four);
					}else if("五中五".equals(betGameplay2.getName2())&&"连码".equals(betGameplay2.getName1())&&"0".equals(betGameplay2.getName3())){
						betGameplay2.setGameplaybetting(five);
					}
				}
			}
		}

		
	
			returnObject.setQueryBean(betGameplay);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetGameplay betGameplay) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betGameplayService.findDataExportExcel(null,listurl, page,BetGameplay.class,betGameplay);
		String fileName="betGameplay"+GlobalStatic.excelext;
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
		return "/lottery/betgameplay/betgameplayLook";
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
		  BetGameplay betGameplay = betGameplayService.findBetGameplayById(id);
		   returnObject.setData(betGameplay);
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
	ReturnDatas saveorupdate(Model model,BetGameplay betGameplay,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid= SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		try {
		
		
			betGameplayService.saveorupdate(betGameplay);
			//日志记录
			String details="";
			if(betGameplay.getId()!=null){
				details = "更新ID为"+betGameplay.getGameclassid()+"的游戏的"+betGameplay.getName1()+betGameplay.getName2()+betGameplay.getName3()+"的玩法";
			}else{
				details = "新增ID为"+betGameplay.getGameclassid()+"的游戏的"+betGameplay.getName1()+betGameplay.getName2()+betGameplay.getName3()+"的玩法";
			}
			 
			String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	/**
	 * 修改赔率、限额,返回json格式数据
	 * 
	 */
	@RequestMapping("/updateoddsandbettinglimit")
	public @ResponseBody
	ReturnDatas updateoddsandbettinglimit(Model model,BetGameplay betGameplay,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String agentid= SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		try {
			if("1".equals(request.getParameter("k"))){
				//
				Integer id = betGameplay.getId();
				BetGameplay betgameplay2 = betGameplayService.queryForObject(new Finder("select *from bet_gameplay where id=:id ").setParam("id", id), BetGameplay.class);
				if(betgameplay2!=null){
					Double maxbetting = betGameplay.getMaxbetting();
					Double minbetting = betGameplay.getMinbetting();
					Double odds = betGameplay.getOdds();
					if(betgameplay2.getMaxodds()!=null&&betgameplay2.getMaxodds()>=odds){
						betGameplayService.update(new Finder("update bet_gameplay set odds=:odds , maxbetting=:maxbetting , minbetting=:minbetting where gameclassid=:gameclassid and name2=:name2 and name1=:name1 ").setParam("odds", odds).setParam("maxbetting", maxbetting).setParam("minbetting", minbetting).setParam("gameclassid", betgameplay2.getGameclassid()).setParam("name2", betgameplay2.getName2()).setParam("name1", betgameplay2.getName1()));
						String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", betgameplay2.getGameclassid()), String.class);
						//删除缓存
						try{
							String l=betgameplay2.getGameclassid()+betgameplay2.getName1();
							cached.deleteCached(l.getBytes("UTF-8"));
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
						}
						//日志记录
						String details="";
						details = "更新"+gcname+"的"+betgameplay2.getName1()+" "+betgameplay2.getName2()+"的玩法的赔率和限额";
						String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("赔率不能超过最大赔率！");
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				}
			}else if("2".equals(request.getParameter("k"))){
				//
//				Integer id = betGameplay.getId();
//				BetGameplay betgameplay2 = betGameplayService.queryForObject(new Finder("select *from bet_gameplay where id=:id ").setParam("id", id), BetGameplay.class);
//				if(betgameplay2!=null){
//					Double maxbetting = betGameplay.getMaxbetting();
//					Double minbetting = betGameplay.getMinbetting();
//					Double odds = betGameplay.getOdds();
//					if(betgameplay2.getMaxodds()!=null&&betgameplay2.getMaxodds()>=odds){
//						betGameplayService.update(new Finder("update bet_gameplay set odds=:odds , maxbetting=:maxbetting , minbetting=:minbetting where gameclassid=:gameclassid and name2=:name2 and name1=:name1 ").setParam("odds", odds).setParam("maxbetting", maxbetting).setParam("minbetting", minbetting).setParam("gameclassid", betgameplay2.getGameclassid()).setParam("name2", betgameplay2.getName2()).setParam("name1", betgameplay2.getName1()));
//						String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", betgameplay2.getGameclassid()), String.class);
//						//删除缓存
//						try{
//							String l=betgameplay2.getGameclassid()+betgameplay2.getName1();
//							cached.deleteCached(l.getBytes("UTF-8"));
//						}catch(Exception e){
//							String errorMessage = e.getLocalizedMessage();
//							logger.error(errorMessage,e);
//						}
//						//日志记录
//						String details="";
//						details = "更新"+gcname+"的"+betgameplay2.getName1()+" "+betgameplay2.getName2()+"的玩法的赔率和限额";
//						String ip = IPUtils.getClientAddress(request);
//					     String tool = AgentToolUtil.getAgentTool(request);
//					     betOptLogService.saveoptLog(tool,ip,details);
//					}else{
//						returnObject.setStatus(ReturnDatas.ERROR);
//						returnObject.setMessage("赔率不能超过最大赔率！");
//					}
//				}else{
//					returnObject.setStatus(ReturnDatas.ERROR);
//					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
//				}
				String gcnamexxx="";
				BetGameplay betxxxxx=null;
				String arr122 = request.getParameter("arr122");
				String[] split = arr122.split(",");
				if(split!=null){
					for (String string : split) {
						if(string!=null){
							String[] split2 = string.split(":");
							if(split2!=null){
								if(split2.length==4){
									try{
										Integer id = Integer.valueOf(split2[0]);
										Double odds = Double.valueOf(split2[1]);
										Double minbetting = Double.valueOf(split2[2]);
										Double maxbetting = Double.valueOf(split2[3]);
										BetGameplay betgameplay2 = betGameplayService.queryForObject(new Finder("select *from bet_gameplay where id=:id ").setParam("id", id), BetGameplay.class);
										if(betgameplay2!=null){
											if(betxxxxx==null){
												betxxxxx=betgameplay2;
											}
											if(betgameplay2.getMaxodds()!=null&&betgameplay2.getMaxodds()>=odds){
												BetGameplay b=new BetGameplay();
												b.setId(id);
												b.setMaxbetting(maxbetting);
												b.setMinbetting(minbetting);
												b.setOdds(odds);
												betGameplayService.update(b,true);
												String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", betgameplay2.getGameclassid()), String.class);
												if(StringUtils.isBlank(gcnamexxx)){
													gcnamexxx=gcname;
												}
											}else{
												returnObject.setStatus(ReturnDatas.ERROR);
												returnObject.setMessage("赔率不能超过最大赔率！");
												return returnObject;
											}
										}else{
											returnObject.setStatus(ReturnDatas.ERROR);
											returnObject.setMessage(MessageUtils.UPDATE_ERROR);
											return returnObject;
										}
									}catch(Exception e){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage(MessageUtils.UPDATE_ERROR);
										return returnObject;
									}
									
									
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage(MessageUtils.UPDATE_ERROR);
									return returnObject;
								}
								
							}
						}
					}
				}
				//删除缓存
				try{
					String l=betxxxxx.getGameclassid()+betxxxxx.getName1();
					cached.deleteCached(l.getBytes("UTF-8"));
					cached.deleteCached((betxxxxx.getGameclassid()+"").getBytes("UTF-8"));
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
				}
				//日志记录
				String details="";
				details = "更新"+gcnamexxx+"的"+betxxxxx.getName1()+"的玩法的赔率和限额";
				String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
//				System.out.println(arr122);
			}else if("3".equals(request.getParameter("k"))){

				String gcnamexxx="";
				BetGameplay betxxxxx=null;
				String arr122 = request.getParameter("arr122");
				String[] split = arr122.split(",");
				if(split!=null){
					for (String string : split) {
						if(string!=null){
							String[] split2 = string.split(":");
							if(split2!=null){
								if(split2.length==4){
									try{
										Integer id = Integer.valueOf(split2[0]);
										Double odds = Double.valueOf(split2[1]);
										Double minbetting = Double.valueOf(split2[2]);
										Double maxbetting = Double.valueOf(split2[3]);
										BetGameplay betgameplay2 = betGameplayService.queryForObject(new Finder("select *from bet_gameplay where id=:id ").setParam("id", id), BetGameplay.class);
										if(betgameplay2!=null){
											if(betxxxxx==null){
												betxxxxx=betgameplay2;
											}
											if(betgameplay2.getMaxodds()!=null&&betgameplay2.getMaxodds()>=odds){
//												BetGameplay b=new BetGameplay();
//												b.setId(id);
//												b.setMaxbetting(maxbetting);
//												b.setMinbetting(minbetting);
//												b.setOdds(odds);
//												betGameplayService.update(b,true);
												betGameplayService.update(new Finder("update bet_gameplay set odds=:odds , maxbetting=:maxbetting , minbetting=:minbetting where gameclassid=:gameclassid and name2=:name2 and name1=:name1 ").setParam("odds", odds).setParam("maxbetting", maxbetting).setParam("minbetting", minbetting).setParam("gameclassid", betgameplay2.getGameclassid()).setParam("name2", betgameplay2.getName2()).setParam("name1", betgameplay2.getName1()));
												String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", betgameplay2.getGameclassid()), String.class);
												if(StringUtils.isBlank(gcnamexxx)){
													gcnamexxx=gcname;
												}
											}else{
												returnObject.setStatus(ReturnDatas.ERROR);
												returnObject.setMessage("赔率不能超过最大赔率！");
												return returnObject;
											}
										}else{
											returnObject.setStatus(ReturnDatas.ERROR);
											returnObject.setMessage(MessageUtils.UPDATE_ERROR);
											return returnObject;
										}
									}catch(Exception e){
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage(MessageUtils.UPDATE_ERROR);
										return returnObject;
									}
									
									
								}else{
									returnObject.setStatus(ReturnDatas.ERROR);
									returnObject.setMessage(MessageUtils.UPDATE_ERROR);
									return returnObject;
								}
								
							}
						}
					}
				}
				//删除缓存
				try{
					String l=betxxxxx.getGameclassid()+betxxxxx.getName1();
					cached.deleteCached(l.getBytes("UTF-8"));
					cached.deleteCached((betxxxxx.getGameclassid()+"").getBytes("UTF-8"));
				}catch(Exception e){
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage,e);
				}
				//日志记录
				String details="";
				details = "更新"+gcnamexxx+"的"+betxxxxx.getName1()+"的玩法的赔率和限额";
				String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
//				System.out.println(arr122);
			
			}else{
				Integer id = betGameplay.getId();
				BetGameplay betgameplay2 = betGameplayService.queryForObject(new Finder("select *from bet_gameplay where id=:id ").setParam("id", id), BetGameplay.class);
				if(betgameplay2!=null){
					Double maxbetting = betGameplay.getMaxbetting();
					Double minbetting = betGameplay.getMinbetting();
					Double odds = betGameplay.getOdds();
					if(betgameplay2.getMaxodds()!=null&&betgameplay2.getMaxodds()>=odds){
						BetGameplay b=new BetGameplay();
						b.setId(id);
						b.setMaxbetting(maxbetting);
						b.setMinbetting(minbetting);
						b.setOdds(odds);
						betGameplayService.update(b,true);
						String gcname = betGameclassService.queryForObject(new Finder("select gcname from bet_gameclass where gameclassid=:gameclassid ").setParam("gameclassid", betgameplay2.getGameclassid()), String.class);
						//删除缓存
						try{
							String l=betgameplay2.getGameclassid()+betgameplay2.getName1();
							cached.deleteCached(l.getBytes("UTF-8"));
							cached.deleteCached((betgameplay2.getGameclassid()+"").getBytes("UTF-8"));
						}catch(Exception e){
							String errorMessage = e.getLocalizedMessage();
							logger.error(errorMessage,e);
						}
						//日志记录
						String details="";
						details = "更新"+gcname+"的"+betgameplay2.getName1()+" "+betgameplay2.getName2()+" "+betgameplay2.getName3()+"的玩法的赔率和限额";
						String ip = IPUtils.getClientAddress(request);
					     String tool = AgentToolUtil.getAgentTool(request);
					     betOptLogService.saveoptLog(tool,ip,details,agentid,agent.getParentid(),agent.getParentids());
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("赔率不能超过最大赔率！");
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
	 * 批量新增/修改赔率 ,返回json格式数据
	 * 
	 */
//	@RequestMapping("/updateodds")
//	public 
//	String updateodds(Model model,BetGameplay betGameplay,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
//
//		Map<String, String[]> parameterMap = request.getParameterMap();
//		Set<String> keySet = parameterMap.keySet();
//		Integer i=null;
//		try {
//			for (String string : keySet) {
//				BetGameplay b=new BetGameplay();
//				Integer gameplayid = Integer.valueOf(string);
//				b.setId(gameplayid);
//				BetGameplay gameplay = betGameplayService.findBetGameplayById(gameplayid);
//				i=gameplay.getGameclassid();
//				String name1 = gameplay.getName1();
//				Double odds = Double.valueOf((parameterMap.get(string))[0]);
//				if(odds>gameplay.getMaxodds()){
//					return "/errorpage/oddsOverrun";
//				}
//				b.setOdds(odds);
//				betGameplayService.update(b, true);
//				//清除赔率缓存
//				try{
//					cached.deleteCached((gameplayid+name1).getBytes());
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				
//				//日志记录
//			    String details = "更新ID为"+gameplay.getGameclassid()+"的游戏的"+gameplay.getName1()+gameplay.getName2()+gameplay.getName3()+"玩法赔率为"+parameterMap.get(string)[0];
//			    
//			    String ip = IPUtils.getClientAddress(request);
//			     String tool = AgentToolUtil.getAgentTool(request);
//			     betOptLogService.saveoptLog(tool,ip,details);
//				
//			}
//		} catch (Exception e) {
//			String errorMessage = e.getLocalizedMessage();
//			logger.error(errorMessage,e);
//		}
//		return redirect+"/betgameplay/list?currentBetting=1&gameclassid="+i;
//	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betgameplay/betgameplayCru";
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
				betGameplayService.deleteById(id,BetGameplay.class);
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
			betGameplayService.deleteByIds(ids,BetGameplay.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
