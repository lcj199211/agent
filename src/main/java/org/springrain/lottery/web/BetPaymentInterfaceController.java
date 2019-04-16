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
import org.springrain.frame.shiro.ShiroUser;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.IPUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetPaymentInterface;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.lottery.service.IBetPaymentInterfaceService;
import org.springrain.lottery.utils.AgentToolUtil;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 11:30:36
 * @see org.springrain.lottery.web.BetPaymentInterface
 */
@Controller
@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@RequestMapping(value="/betpaymentinterface")
public class BetPaymentInterfaceController  extends BaseController {
	@Resource
	private IBetPaymentInterfaceService betPaymentInterfaceService;
	@Resource
	private IBetOptLogService betOptLogService;
	@Resource
	private IBetAgentService betAgentService;
	private String listurl="/lottery/betpaymentinterface/betpaymentinterfaceList";
	
	@Value("${ewmurl}")
	private String ewmurl;
	@Value("${ewmurl2}")
	private String ewmurl2;
	   
	
	
	
	
	@RequestMapping(value = "/img")
	
	public void getImg(String url,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(url!=null){
				url = new String(url.getBytes("ISO8859-1"), "utf-8");
			}
			String replace = ewmurl.replace(ewmurl2,"");
			if(url==null){
				return;
			}else{
				if(url.startsWith(ewmurl2)){
					if(url.indexOf("..")!=-1){
						return;
					}else{
						
					}
				}else{
					return;
				}
			}
			
			FileInputStream fis = new FileInputStream(replace+url);
			
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
	 * @param betPaymentInterface
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetPaymentInterface betPaymentInterface) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, betPaymentInterface);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betPaymentInterface
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetPaymentInterface betPaymentInterface) throws Exception{
		String agentId = SessionUser.getAgentId();
		String agentid = SessionUser.getShiroUser().getAgentid();
		betPaymentInterface.setAgentid(agentId);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("order1");
		page.setSort("asc");
		// ==执行分页查询
		List<BetPaymentInterface> datas=betPaymentInterfaceService.findListDataByFinder(new Finder("select * from bet_payment_interface where state<>2 "),page,BetPaymentInterface.class,betPaymentInterface);
			returnObject.setQueryBean(betPaymentInterface);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetPaymentInterface betPaymentInterface) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betPaymentInterfaceService.findDataExportExcel(null,listurl, page,BetPaymentInterface.class,betPaymentInterface);
		String fileName="betPaymentInterface"+GlobalStatic.excelext;
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
		return "/lottery/betpaymentinterface/betpaymentinterfaceLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			BetPaymentInterface betPaymentInterface = betPaymentInterfaceService.findBetPaymentInterfaceById(id);
			if(agentId.equals(betPaymentInterface.getAgentid())){
				returnObject.setData(betPaymentInterface);
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
	ReturnDatas saveorupdate(Model model,BetPaymentInterface betPaymentInterface,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String agentId = SessionUser.getAgentId();
		BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =betPaymentInterface.getId();
			if(StringUtils.isBlank(id)){
			  betPaymentInterface.setId(null);
			  betPaymentInterface.setAgentid(betagent.getAgentid());
			  betPaymentInterface.setAgentparentid(betagent.getParentid());
			  betPaymentInterface.setAgentparentids(betagent.getParentids());
			  betPaymentInterfaceService.save(betPaymentInterface);
			}else{
				BetPaymentInterface findBetPaymentInterfaceById = betPaymentInterfaceService.findBetPaymentInterfaceById(id);
				if(agentId.equals(findBetPaymentInterfaceById.getAgentid())){
					betPaymentInterface.setAgentid(betagent.getAgentid());
					betPaymentInterface.setAgentparentid(betagent.getParentid());
					betPaymentInterface.setAgentparentids(betagent.getParentids());
					betPaymentInterfaceService.update(betPaymentInterface);
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					return returnObject;
				}
				
			}
			//String twobarcode = betPaymentInterface.getTwobarcode();
			//betPaymentInterface.setTwobarcode(java.net.URLEncoder.encode(twobarcode,"utf-8"));
			
			//日志记录
		    String details = "";
		    if(id==null||"".equals(id)){
		    	details = "添加"+betPaymentInterface.getBankname()+"的支付接口";
		    }else{
		    	details = "更新"+betPaymentInterface.getBankname()+"的支付接口";
		    }
		    
		    String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,betagent.getAgentid(),betagent.getParentid(),betagent.getParentids());
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	/**
	 * 修改上线下线状态
	 * 
	 */
	@RequestMapping("/update/s")
	public String update(BetPaymentInterface betPaymentInterface,HttpServletRequest request) throws Exception{
		try {
			String agentId = SessionUser.getAgentId();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			String id =betPaymentInterface.getId();
			BetPaymentInterface betPaymentInterface1 = betPaymentInterfaceService.queryForObject(new Finder("select *from bet_payment_interface where id=:id ").setParam("id", id), BetPaymentInterface.class);
			if(agentId.equals(betPaymentInterface1.getAgentid())){
				betPaymentInterfaceService.update(betPaymentInterface,true);
			}else{
				return "/errorpage/error";
			}
			
			//日志记录
		    String details = "";
		    if(betPaymentInterface.getState()==1){
		    	 details = "启用"+betPaymentInterface1.getBankname()+"的支付接口";
		    }else if(betPaymentInterface.getState()==0){
		    	details = "停用"+betPaymentInterface1.getBankname()+"的支付接口";
		    }
		    String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,betagent.getAgentid(),betagent.getParentid(),betagent.getParentids());
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage,e);
		}
		return "redirect:/betpaymentinterface/list";
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/betpaymentinterface/betpaymentinterfaceCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
			String agentId = SessionUser.getAgentId();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				BetPaymentInterface betPaymentInterface = betPaymentInterfaceService.queryForObject(new Finder("select *from bet_payment_interface where id=:id ").setParam("id", id), BetPaymentInterface.class);
				betPaymentInterfaceService.deleteById(id,BetPaymentInterface.class);
				//日志记录
			    String details = "删除"+betPaymentInterface.getBankname()+"的支付接口";
			    String ip = IPUtils.getClientAddress(request);
			     String tool = AgentToolUtil.getAgentTool(request);
			     betOptLogService.saveoptLog(tool,ip,details,betagent.getAgentid(),betagent.getParentid(),betagent.getParentids());
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
			String agentId = SessionUser.getAgentId();
			BetAgent betagent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid ").setParam("agentid", agentId), BetAgent.class);
			List<String> ids = Arrays.asList(rs);
			betPaymentInterfaceService.deleteByIds(ids,BetPaymentInterface.class);
			//日志记录
		    String details = "批量删除支付接口";
		    String ip = IPUtils.getClientAddress(request);
		     String tool = AgentToolUtil.getAgentTool(request);
		     betOptLogService.saveoptLog(tool,ip,details,betagent.getAgentid(),betagent.getParentid(),betagent.getParentids());
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 上传二维码
	 * @throws IOException 
	 * */
	@RequestMapping("/twobarcodeupload")
	public @ResponseBody ReturnDatas twobarcodeUpload(HttpServletRequest request) throws IOException{
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Iterator<String> fileNames = multiRequest.getFileNames();
		List<String> attachmentPathList = new ArrayList<>();
		while(fileNames.hasNext()){
			MultipartFile file = multiRequest.getFile(fileNames.next());
//			String fileUrl = "/upload/"+SecUtils.getUUID()+file.getOriginalFilename();
//			String filePath = GlobalStatic.rootdir+fileUrl;
			String uuid = SecUtils.getUUID();
			String originalFilename = file.getOriginalFilename();
			String fileUrl=ewmurl+uuid+originalFilename;
			File destFile = new File(fileUrl);
			if(!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
			if(!destFile.exists())
				destFile.createNewFile();
			file.transferTo(destFile);
			attachmentPathList.add(ewmurl2+uuid+originalFilename);
		}
		
		returnDatas.setData(attachmentPathList);
		return returnDatas;
	}

}
