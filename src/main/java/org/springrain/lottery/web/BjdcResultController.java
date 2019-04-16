package  org.springrain.lottery.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BjdcResult;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBjdcOrderContentService;
import org.springrain.lottery.service.IBjdcResultService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-05 11:06:35
 * @see org.springrain.lottery.web.BjdcResult
 */
@Controller
@RequestMapping(value="/bjdcresult")
public class BjdcResultController  extends BaseController {
	@Resource
	private IBjdcResultService bjdcResultService;
	@Resource
	private IBjdcOrderContentService bjdcOrderContentService;
	@Resource
	private IBetMemberService betMemberService;
	private String listurl="/lottery/bjdcresult/bjdcresultList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param bjdcResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BjdcResult bjdcResult) 
			throws Exception {
		if("1".equals(request.getParameter("k"))){
			//查询投此场会员
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			String fid = request.getParameter("fid");
			List<BetMember> datas=betMemberService.queryForList(new Finder("select * from bet_member  where  id2 in (select DISTINCT a.memberid2  from  bjdc_scheme a LEFT JOIN bjdc_scheme_match b on a.schemeid = b.schemeid  where b.fid=:fid) ").setParam("fid", fid),BetMember.class,page);
			returnObject.setData(datas);
			returnObject.setPage(page);
			returnObject.setQueryBean(new BetMember());
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			model.addAttribute("fid", fid);
			return  "/lottery/bjdcorder/bjdcmemberList";
		}else{
			ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
			Page page = newPage(request);
			if(bjdcResult.getIstrue()==null){
				bjdcResult.setIstrue(3);
			}
			if(bjdcResult.getState()==null){
				bjdcResult.setState(1);
			}
			if(StringUtils.isBlank(bjdcResult.getNum())){
				bjdcResult.setNum(null);
			}
			if(StringUtils.isBlank(bjdcResult.getPeriodnum())){
				bjdcResult.setPeriodnum(null);
			}
			List<BjdcResult> datas = bjdcResultService.findListDataByFinder(new Finder("select * from bjdc_result where istrue=:istrue and state =:state and (:num is null or num =:num) and (:periodnum is null or periodnum=:periodnum) ").setParam("num", bjdcResult.getNum()).setParam("periodnum", bjdcResult.getPeriodnum()).setParam("istrue",bjdcResult.getIstrue()).setParam("state", bjdcResult.getState()),page,BjdcResult.class,null);;
			returnObject.setQueryBean(bjdcResult);
			returnObject.setPage(page);
			returnObject.setData(datas);
			model.addAttribute(GlobalStatic.returnDatas, returnObject);
			return listurl;
		}
	}
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param bjdcResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BjdcResult bjdcResult) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<BjdcResult> datas=bjdcResultService.findListDataByFinder(null,page,BjdcResult.class,bjdcResult);
			returnObject.setQueryBean(bjdcResult);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BjdcResult bjdcResult) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = bjdcResultService.findDataExportExcel(null,listurl, page,BjdcResult.class,bjdcResult);
		String fileName="bjdcResult"+GlobalStatic.excelext;
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
		return "/lottery/bjdcresult/bjdcresultLook";
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
			 BjdcResult bjdcResult = bjdcResultService.findBjdcResultById(id);
			 String halfscore1 = "";
			 String halfscore2 = "";
			 String allscore1 = "";
			 String allscore2 = "";
			 String halfscore = bjdcResult.getHalfscore();
			 String allscore = bjdcResult.getAllscore();
			if(halfscore!=null){
				String[] halfscores = halfscore.split(":");
				halfscore1 = halfscores[0];
				halfscore2 = halfscores[1];
			}
			if(allscore!=null){
				String[] allscores = allscore.split(":");
				allscore1 = allscores[0];
				allscore2 = allscores[1];
			}
			model.addAttribute("halfscore1", halfscore1);
			model.addAttribute("halfscore2", halfscore2);
			model.addAttribute("allscore1", allscore1);
			model.addAttribute("allscore2", allscore2);
			returnObject.setData(bjdcResult);
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
	ReturnDatas saveorupdate(Model model,BjdcResult bjdcResult,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if("1".equals(request.getParameter("k"))){
				//确认
				String  id=request.getParameter("id");
				String  state=request.getParameter("state");
				BjdcResult result = bjdcResultService.queryForObject(new Finder("select * from bjdc_result where id=:id ").setParam("id", id), BjdcResult.class);
				if(!":".equals(result.getAllscore())&&!":".equals(result.getHalfscore())&&result.getAllscore()!=null&&result.getHalfscore()!=null){
					bjdcResultService.update(new Finder("update bjdc_result set istrue=:istrue where id=:id").setParam("istrue",state).setParam("id", id));
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("比分不正确");
					return returnObject;
				}
			}else if("2".equals(request.getParameter("k"))){
				//冻结、解冻
				String  id = request.getParameter("id");
				String  status = request.getParameter("status");
				String fid = bjdcResultService.queryForObject(new Finder("select fid from bjdc_result where id=:id ").setParam("id", id),String.class);
				if(fid == null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("场次不存在");
				}
				List<String> id2s = bjdcOrderContentService.queryForList(new Finder("select DISTINCT memberid2 from bjdc_scheme left join bjdc_order_content on bjdc_scheme.schemeid=bjdc_order_content.schemeid where fid=:fid  order by bjdc_scheme.id ").setParam("fid", fid), String.class);
				if(!id2s.isEmpty()){
					betMemberService.update(new Finder("update bet_member set status=:status where id2 in (:id2s) ").setParam("status", status).setParam("id2s", id2s));
				}
			}else{
				String starttime = bjdcResultService.queryForObject(new Finder("select starttime from bjdc_arrange where fid=:fid ").setParam("fid",bjdcResult.getFid()),String.class);
				if(new SimpleDateFormat("yyyy-MM-dd").parse(starttime).before(new Date())){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("比賽未開始");
				}
				
				String halfscore1 = null;
				String halfscore2 = null;
				String allscore1 = null;
				String allscore2 = null;
				if(request.getParameter("halfscore1")!=null){
					halfscore1 = request.getParameter("halfscore1").trim();
				}
				if(request.getParameter("halfscore2")!=null){
					halfscore2 = request.getParameter("halfscore2").trim();
				}
				if(request.getParameter("allscore1")!=null){
					allscore1 = request.getParameter("allscore1").trim();
				}
				if(request.getParameter("allscore2")!=null){
					allscore2 = request.getParameter("allscore2").trim();
				}
				if(halfscore1!=null&&halfscore2!=null){
					bjdcResult.setHalfscore(halfscore1+":"+halfscore2);
				}
				if(allscore1!=null&&allscore2!=null){
					bjdcResult.setAllscore(allscore1+":"+allscore2);
				}
				String fid = request.getParameter("fid");
				bjdcResultService.update(new Finder("update bjdc_result set halfscore=:halfscore,allscore=:allscore where fid=:fid ").setParam("halfscore", bjdcResult.getHalfscore()).setParam("allscore", bjdcResult.getAllscore()).setParam("fid", fid));
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
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/lottery/bjdcresult/bjdcresultCru";
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
				bjdcResultService.deleteById(id,BjdcResult.class);
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
			bjdcResultService.deleteByIds(ids,BjdcResult.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
