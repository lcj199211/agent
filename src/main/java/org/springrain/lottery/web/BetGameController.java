package  org.springrain.lottery.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
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
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetGame;
import org.springrain.lottery.entity.BetGameAgent;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetGameAgentService;
import org.springrain.lottery.service.IBetGameService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-04-11 14:54:00
 * @see org.springrain.lottery.web.BetGame
 */
@Controller
@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@RequestMapping(value="/betgame")
public class BetGameController  extends BaseController {
	@Resource
	private IBetGameService betGameService;
	@Resource
	private IBetGameAgentService betGameAgentService;
	@Resource
	private IBetAgentService betAgentService;
	
	@Value("${activity}")
	private String activity;
	@Value("${activity2}")
	private String activity2;
	private String listurl="/lottery/betgame/betgameList";
	

	@RequestMapping(value = "/img")
	public void getImg(String img,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(img!=null){
				img = new String(img.getBytes("ISO8859-1"), "utf-8");
			}
			String replace = activity.replace(activity2,"");
			if(img==null){
				return;
			}else{
				if(img.startsWith(activity2)){
					if(img.indexOf("..")!=-1){
						return;
					}else{
						
					}
				}else{
//					return;
				}
			}
			
			FileInputStream fis = new FileInputStream(replace+img);
			
			ServletOutputStream ops = response.getOutputStream();
			int len = -1;
			byte[] datas = new byte[1024*100];
			while((len = fis.read(datas))!=-1){
				ops.write(datas,0,len);
			}
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betGame
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetGame betGame) 
			throws Exception {
		String company = SessionUser.getShiroUser().getAgentid();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetGame> datas=betGameService.findListDataByFinder(new Finder("select * from bet_game where company=:company ").setParam("company", company),page,BetGame.class,betGame);
		returnObject.setQueryBean(betGame);
		returnObject.setPage(page);
		returnObject.setData(datas);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betGame
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetGame betGame) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BetGame> datas=betGameService.findListDataByFinder(null,page,BetGame.class,betGame);
			returnObject.setQueryBean(betGame);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetGame betGame) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betGameService.findDataExportExcel(null,listurl, page,BetGame.class,betGame);
		String fileName="betGame"+GlobalStatic.excelext;
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
		return "/lottery/betgame/betgameLook";
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
		  BetGame betGame = betGameService.findBetGameById(id);
		   returnObject.setData(betGame);
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
	ReturnDatas saveorupdate(Model model,BetGame betGame,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			String company = SessionUser.getShiroUser().getAgentid();
			String agentid = "%," + company + ",%";
			betGame.setCompany(company);
			betGameService.saveorupdate(betGame);
			if(betGame.getId() == null){
				List<BetAgent> agentids = betAgentService.queryForList(new Finder("select agentid,parentids from bet_agent where parentids like :agentid ").setParam("agentid", agentid), BetAgent.class);
				for (BetAgent betAgent : agentids) {
					BetGameAgent betGameAgent = new BetGameAgent();
					betGameAgent.setId(null);
					betGameAgent.setCompany(company);
					betGameAgent.setAgentid(betAgent.getAgentid());
					betGameAgent.setGamename(betGame.getGamename());
					betGameAgent.setImg(betGame.getImg());
					betGameAgent.setRemark(betGame.getRemark());
					betGameAgent.setState(betGame.getState());
					betGameAgent.setTitle(betGame.getTitle());
					betGameAgentService.save(betGameAgent);
				}
			}else{
				if(betGame.getAllowupdate()==3){
					betGameAgentService.update(new Finder("update bet_game_agent set title=:title,img=:img,remark=:remark,state=:state where company=:company and gamename=:gamename")
					.setParam("title", betGame.getTitle()).setParam("img", betGame.getImg()).setParam("remark", betGame.getRemark())
					.setParam("state", betGame.getState()).setParam("company", betGame.getCompany()).setParam("gamename", betGame.getGamename()));
				}else{
					betGameAgentService.update(new Finder("update bet_game_agent set remark=:remark where company=:company and gamename=:gamename")
					.setParam("remark", betGame.getRemark()).setParam("company", betGame.getCompany()).setParam("gamename", betGame.getGamename()));
				}
			}
			returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
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
		return "/lottery/betgame/betgameCru";
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
				betGameService.deleteById(id,BetGame.class);
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
			betGameService.deleteByIds(ids,BetGame.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	/**
	 * 图片上传
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	public @ResponseBody
	ReturnDatas upload(HttpServletRequest request) throws Exception{
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Iterator<String> fileNames = multiRequest.getFileNames();
		List<String> attachmentPathList = new ArrayList<String>();
		while(fileNames.hasNext()){
			MultipartFile file = multiRequest.getFile(fileNames.next());
			String uuid = SecUtils.getUUID();
			String originalFilename = file.getOriginalFilename();
			String fileUrl=activity+uuid+originalFilename;
			File destFile = new File(fileUrl);
			if(!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			if(!destFile.exists())
				destFile.createNewFile();
			file.transferTo(destFile);
			attachmentPathList.add(activity2+uuid+originalFilename);
		}
		
		returnDatas.setData(attachmentPathList);
		return returnDatas;
		
	
	}
}
