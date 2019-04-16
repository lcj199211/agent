package  org.springrain.lottery.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetGameclass;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetGameclassService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetPtService;
import org.springrain.lottery.utils.AgentToolUtil;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-09 11:41:19
 * @see org.springrain.lottery.web.BetGameclass
 */
@Controller
@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@RequestMapping(value="/betgameclass")
public class BetGameclassController  extends BaseController {
	@Resource
	private IBetGameclassService betGameclassService;
	@Resource
	private IBetPtService betPtService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetAgentService betAgentService;
	@Value("${gameiconurl}")
	private String gameiconurl;
	@Value("${gameiconurl2}")
	private String gameiconurl2;
	private String listurl="/lottery/betgameclass/betgameclassList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betGameclass
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetGameclass betGameclass) 
			throws Exception {
		String parameter = request.getParameter("totallist");
		if("1".equals(parameter)){
			betGameclass.setGameclassid(null);
		}
		ReturnDatas returnObject = listjson(request, model, betGameclass);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		if(betGameclass.getGameclassid()==null){
			return "/lottery/betgameclass/totalbetgameclassList";
		}else{
			return listurl;
		}
	}
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betGameclass
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetGameclass betGameclass) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request,"grank", "asc");
		page.setPageSize(50);
		List<Map<String, Object>> ptlist = betPtService.queryForList(new Finder("select * from bet_pt "));
//		List<BetGameclass> datas=betGameclassService.findListDataByFinder(null,page,BetGameclass.class,betGameclass);
		Integer gameclassid2 = betGameclass.getGameclassid();
		List<BetGameclass> datas=null;
		if(gameclassid2!=null){
			datas=betGameclassService.queryForList(new Finder("select gameclassid,gcname,gsname,grank,glock,gkind,pt,nlen,nmin,nmax,gametxt,gstockid,gametype,submenu,maxlost,ratelost,ratewin,mclass,mlock,mstep,mnums,mopen,mstop,mfine,msnum,mstime,msurl,bettingceiling,bettinglimit,ty,state,tymode,membertymode,memberty from bet_gameclass where gameclassid=:gameclassid   ").setParam("gameclassid", gameclassid2), BetGameclass.class, page);
		}else{
			datas=betGameclassService.queryForList(new Finder("select gameclassid,gcname,gsname,grank,glock,gkind,pt,nlen,nmin,nmax,gametxt,gstockid,gametype,submenu,maxlost,ratelost,ratewin,mclass,mlock,mstep,mnums,mopen,mstop,mfine,msnum,mstime,msurl,bettingceiling,bettinglimit,ty,state,tymode,membertymode,memberty from bet_gameclass where 1=1  "), BetGameclass.class, page);
		}
		
		if(datas!=null){
			 List<Map<String, Object>> tylist = betBettingService.queryForList(new Finder("select gameclassid,SUM(memberty) as totay from bet_betting where membertystate=0 and state=1 GROUP BY gameclassid "));
			 List<Map<String, Object>> agenttylist = betBettingService.queryForList(new Finder("select gameclassid,SUM((IFNULL(length(agentparentids)-length(replace(agentparentids,:xx,:xxx)),1))*ty) as agentdf from bet_betting where tystate=0 and state=1 GROUP BY gameclassid ").setParam("xxx", "").setParam("xx", ","));
			for (BetGameclass betGameclass2 : datas) {
				Integer pt = betGameclass2.getPt();
				if(ptlist!=null){
					for (Map<String, Object> map : ptlist) {
						Integer idd = (Integer)map.get("id");
						if(pt!=null&&idd!=null&&pt==idd){
							betGameclass2.setPtname((String)map.get("name"));
						}
					}
				}
				Integer gameclassid = betGameclass2.getGameclassid();
				//代理退佣
				if(agenttylist!=null){
					for (Map<String, Object> map : agenttylist) {
						if(gameclassid.equals(map.get("gameclassid"))){
							Double dd=((BigDecimal)map.get("agentdf")).doubleValue();
							betGameclass2.setTotalty(dd);
						}
					}
				}
//				Double totalty = betBettingService.queryForObject(new Finder("select SUM((IFNULL(length(agentparentids)-length(replace(agentparentids,:xx,:xxx)),1))*ty) from bet_betting where tystate=0 and state=1 and gameclassid=:gameclassid ").setParam("xxx", "").setParam("xx", ",").setParam("gameclassid", gameclassid), Double.class);
//				if(totalty==null){
//					totalty=0.;
//				}
//				betGameclass2.setTotalty(totalty);
				//用户退佣
				if(tylist!=null){
					for (Map<String, Object> map : tylist) {
						if(gameclassid.equals(map.get("gameclassid"))){
							Double dd=((BigDecimal)map.get("totay")).doubleValue();
							betGameclass2.setMembertotalty(dd);
						}
					}
				}
			}
		}
		model.addAttribute("gameclassid",betGameclass.getGameclassid() );
		returnObject.setQueryBean(betGameclass);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetGameclass betGameclass) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betGameclassService.findDataExportExcel(null,listurl, page,BetGameclass.class,betGameclass);
		String fileName="betGameclass"+GlobalStatic.excelext;
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
		return "/lottery/betgameclass/betgameclassLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("gameclassid");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  BetGameclass betGameclass = betGameclassService.findBetGameclassById(id);
		   returnObject.setData(betGameclass);
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
	ReturnDatas saveorupdate(Model model,BetGameclass betGameclass,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String agentid= SessionUser.getAgentId();
		BetAgent agent = betAgentService.queryForObject(new Finder("select * from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		betGameclass.setSpecialrules("");
		if("on".equals(request.getParameter("specialrules1"))){
			betGameclass.setSpecialrules(betGameclass.getSpecialrules()+"a");
		}
		if("on".equals(request.getParameter("specialrules2"))){
			betGameclass.setSpecialrules(betGameclass.getSpecialrules()+"b");
		}
//		if(1==betGameclass.getState()){
			//清除缓存
//			String ticket = betMember.getTicket();
//			if(ticket!=null){
//				try{
//					cached.deleteCached(ticket.getBytes());
//				}catch(Exception e){
//					String errorMessage = e.getLocalizedMessage();
//					logger.error(errorMessage,e);
//				}
//				
//			}
//		}
		if(0!=betGameclass.getTy()){
			betGameclass.setTy(betGameclass.getTy()/100);
		}
		if(0!=betGameclass.getMemberty()){
			betGameclass.setMemberty(betGameclass.getMemberty()/100);
		}
		
		try {
//			betGameclass.setTy(betGameclass.getTy()/1000);
		
			betGameclassService.saveorupdate(betGameclass);
			//操作日志
			 String details = "";
		     details = "更新"+betGameclass.getGcname()+"的游戏设置";
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
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		if("1".equals(request.getParameter("totallist"))){
			model.addAttribute("totallist", 1);
		}
		return "/lottery/betgameclass/betgameclassCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {
		try {
		  String  strId=request.getParameter("gameclassid");
		  if(StringUtils.isNotBlank(strId)){
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
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	
	/**
	 * 上传游戏图标
	 * @throws IOException 
	 * */
	@RequestMapping("/gameiconupload")
	public @ResponseBody ReturnDatas gameiconupload(HttpServletRequest request) throws IOException{
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Iterator<String> fileNames = multiRequest.getFileNames();
		List<String> attachmentPathList = new ArrayList<>();
		while(fileNames.hasNext()){
			MultipartFile file = multiRequest.getFile(fileNames.next());	
//			String fileUrl = "/upload/"+SecUtils.getUUID()+file.getOriginalFilename();
//			String filePath = GlobalStatic.rootdir+fileUrl;
//			String uuid = SecUtils.getUUID();
			String originalFilename = file.getOriginalFilename();
			String fileUrl=gameiconurl+originalFilename;
			File destFile = new File(fileUrl);
			if(!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			if(!destFile.exists())
				destFile.createNewFile();
			file.transferTo(destFile);
			attachmentPathList.add(gameiconurl2+originalFilename);
		}
		
		returnDatas.setData(attachmentPathList);
		return returnDatas;
	}

}
