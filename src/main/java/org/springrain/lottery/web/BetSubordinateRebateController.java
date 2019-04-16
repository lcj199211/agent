package  org.springrain.lottery.web;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.lottery.service.IBetSubordinateRebateService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-08 09:57:32
 * @see org.springrain.lottery.web.BetSubordinateRebate
 */
@Controller
@RequestMapping(value="/betsubordinaterebate")
public class BetSubordinateRebateController  extends BaseController {
	@Resource
	private IBetSubordinateRebateService betSubordinateRebateService;
	
	private String listurl="/lottery/betsubordinaterebate/betsubordinaterebateList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param betSubordinateRebate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,BetSubordinateRebate betSubordinateRebate) 
			throws Exception {
		
		ReturnDatas returnObject = listjson(request, model, betSubordinateRebate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
		
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param betSubordinateRebate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,BetSubordinateRebate betSubordinateRebate) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		//获取当前登录用户agentid
		String agentid = SessionUser.getAgentId();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("id");
		page.setSort("asc");
		String remark = request.getParameter("mark");
		if(StringUtils.isNoneEmpty(remark)&&"t".equals(remark)){
			List<BetSubordinateRebate> datas=betSubordinateRebateService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark=:remark").setParam("remark", "t"),page,BetSubordinateRebate.class,betSubordinateRebate);
			if(datas!=null){
				Double maxbetting=0.;
				for (BetSubordinateRebate betSubordinateRebate2 : datas) {
					Double betstream = betSubordinateRebate2.getBetstream();
					if(betstream!=null){
						if(5!=betSubordinateRebate2.getId()){
							if(betstream>maxbetting){
								maxbetting=betstream;
							}
						}
					}
					model.addAttribute("maxbetting",maxbetting);
				}
			}
			returnObject.setQueryBean(betSubordinateRebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else if(StringUtils.isNoneEmpty(remark)&&"dbcz".equals(remark)){
			Double mode = betSubordinateRebateService.queryForObject(new Finder("select rebate from bet_subordinate_rebate WHERE remark=:remark  and (agentid = :agentid or agentparentids like :agentparentids)").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("remark", "dbczmode"), Double.class);
			if(mode!=null){
				try{
					if(1.0==mode){
						model.addAttribute("dbczmode", 1);
					}else if(0.==mode){
						model.addAttribute("dbczmode", 0);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<BetSubordinateRebate> datas=betSubordinateRebateService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark=:remark and (agentid = :agentid or agentparentids like :agentparentids) ").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("remark", "dbcz"),page,BetSubordinateRebate.class,betSubordinateRebate);
			returnObject.setQueryBean(betSubordinateRebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else if(StringUtils.isNoneEmpty(remark)&&"rc".equals(remark)){
			List<BetSubordinateRebate> datas=betSubordinateRebateService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark=:remark").setParam("remark", "rc"),page,BetSubordinateRebate.class,betSubordinateRebate);
			returnObject.setQueryBean(betSubordinateRebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}else if(StringUtils.isNoneEmpty(remark)&&"sc".equals(remark)){
			Double mode = betSubordinateRebateService.queryForObject(new Finder("select rebate from bet_subordinate_rebate WHERE remark=:remark").setParam("remark", "scmode"), Double.class);
			if(mode!=null){
				try{
					if(1.0==mode){
						model.addAttribute("scmode", 1);
					}else if(0.==mode){
						model.addAttribute("scmode", 0);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<BetSubordinateRebate> datas=betSubordinateRebateService.findListDataByFinder(new Finder("SELECT * FROM bet_subordinate_rebate WHERE remark=:remark and (agentid = :agentid or agentparentids like :agentparentids)").setParam("remark", "sc").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%"),page,BetSubordinateRebate.class,betSubordinateRebate);
			returnObject.setQueryBean(betSubordinateRebate);
			returnObject.setPage(page);
			returnObject.setData(datas);
			return returnObject;
		}
		// ==执行分页查询
		List<BetSubordinateRebate> datas=betSubordinateRebateService.findListDataByFinder(null,page,BetSubordinateRebate.class,betSubordinateRebate);
		
		returnObject.setQueryBean(betSubordinateRebate);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,BetSubordinateRebate betSubordinateRebate) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = betSubordinateRebateService.findDataExportExcel(null,listurl, page,BetSubordinateRebate.class,betSubordinateRebate);
		String fileName="betSubordinateRebate"+GlobalStatic.excelext;
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
		return "/lottery/betsubordinaterebate/betsubordinaterebateLook";
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
		  BetSubordinateRebate betSubordinateRebate = betSubordinateRebateService.findBetSubordinateRebateById(id);
		   returnObject.setData(betSubordinateRebate);
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
	ReturnDatas saveorupdate(Model model,BetSubordinateRebate betSubordinateRebate,HttpServletRequest request,HttpServletResponse response,
			String id,String rebate,String betstream,String betamounts,String losescore,String scbettingmultiple) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		//获取当前登录用户agentid
		String agentid = SessionUser.getAgentId();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String mark = request.getParameter("mark");
			String remark = request.getParameter("remark");
			String strrebateSingle = request.getParameter("rebate");
			Double rebateSingle=null;
			try{
				Double rebateSingle1 = Double.valueOf(strrebateSingle);
				if("zc".equals(remark)||"dbcz".equals(remark)||"relief".equals(remark)){
					rebateSingle=rebateSingle1;
				}else{
					rebateSingle=rebateSingle1/100.;
				}
			}catch(Exception e){
				String errorMessage = e.getLocalizedMessage();
				logger.error(errorMessage,e);
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("返利设置必须为数字");
				return returnObject;
			}
			
			if(StringUtils.isNotEmpty(mark)){
				if("t".equals(mark)){
					String[] ids = id.split(",");
					String[] rebates = rebate.split(",");
					String[] betstreams = betstream.split(",");
					String[] betamountss = betamounts.split(",");
					String[] losescores = losescore.split(",");
					int len1 = betstreams.length;
					int len2 = betamountss.length;
					int len3 = losescores.length;
					if(ids.length==(len1+len2+len3)){
						for (int i = 0; i < len1; i++) {
							BetSubordinateRebate bConfig = new BetSubordinateRebate();
							bConfig.setId(Integer.valueOf(ids[i]));
							bConfig.setRebate(Double.valueOf(rebates[i])/100.);
							bConfig.setBetstream(Double.valueOf(betstreams[i]));
							bConfig.setRemark(mark);
							betSubordinateRebateService.update(bConfig, true);
						}
						for (int i = 0; i < len2; i++) {
							BetSubordinateRebate bConfig = new BetSubordinateRebate();
							bConfig.setId(Integer.valueOf(ids[len1+i]));
							bConfig.setRebate(Double.valueOf(rebates[len1+i])/100.);
							bConfig.setBetamounts(Double.valueOf(betamountss[i]));
							bConfig.setRemark(mark);
							betSubordinateRebateService.update(bConfig, true);
						}
						for (int i = 0; i < len3; i++) {
							BetSubordinateRebate bConfig = new BetSubordinateRebate();
							bConfig.setId(Integer.valueOf(ids[len1+len2+i]));
							bConfig.setRebate(Double.valueOf(rebates[len1+len2+i])/100.);
							bConfig.setLosescore(Double.valueOf(losescores[i]));
							bConfig.setRemark(mark);
							betSubordinateRebateService.update(bConfig, true);
						}
					}
				}else if("dbcz".equals(mark)){
					String parameter = request.getParameter("dbczmode");
					if(StringUtils.isNoneBlank(parameter)){
						try{
							Integer valueOf = Integer.valueOf(parameter);
							if((valueOf!=null)&&valueOf==1){
					            String[] ids = id.split(",");
				             	String[] rebates = rebate.split(",");
					            String[] betamountss = betamounts.split(",");
				             	String[] scbettingmultiple1 = scbettingmultiple.split(",");
				             	String[] betstreams = betstream.split(",");
				            	int len1 = ids.length;
					            int len2 = rebates.length;
					            int len3 = betamountss.length;
					            int len4 = scbettingmultiple1.length;
					            int len5 = betstreams.length;
				            	if(len1==len2&&len1==len3&&len1==len4&&len1==len5){
				            		betSubordinateRebateService.update(new Finder("update bet_subordinate_rebate set rebate=1 where remark=:remark and (agentid=:agentid or agentparentids like :agentparentids) ").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("remark", "dbczmode"));
				           		    for (int i = 0; i < len1; i++) {
					     		        BetSubordinateRebate bConfig = new BetSubordinateRebate();
					     		        bConfig.setId(Integer.valueOf(ids[i]));
									    Double valueOf2 = Double.valueOf(betstreams[i]);
									    if(valueOf2==1){
									    	 bConfig.setId(Integer.valueOf(ids[i]));
									         bConfig.setRebate(Double.valueOf(rebates[i]));
						    			     bConfig.setBetamounts(Double.valueOf(betamountss[i]));
							     		     bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
							    		     bConfig.setRemark(mark);
//								             betSubordinateRebateService.update(bConfig, true);
								    	     betSubordinateRebateService.update(new Finder("UPDATE bet_subordinate_rebate SET rebate=:rebate,betamounts = :betamounts, scbettingmultiple=:scbettingmultiple WHERE remark=:remark and (agentid=:agentid or agentparentids like :agentparentids) and id=:id").setParam("id", ids[i]).setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("scbettingmultiple", Double.valueOf(scbettingmultiple1[i])).setParam("rebate", Double.valueOf(rebates[i])).setParam("remark", mark).setParam("betamounts", Double.valueOf(betamountss[i])));
									    }else if(valueOf2==0){
									    	 bConfig.setId(Integer.valueOf(ids[i]));
									         bConfig.setRebate(Double.valueOf(rebates[i])/100);
						    			     bConfig.setBetamounts(Double.valueOf(betamountss[i]));
							     		     bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
							    		     bConfig.setRemark(mark);
//								             betSubordinateRebateService.update(bConfig, true);
								    	     betSubordinateRebateService.update(new Finder("UPDATE bet_subordinate_rebate SET rebate=:rebate,betamounts = :betamounts, scbettingmultiple=:scbettingmultiple WHERE remark=:remark and (agentid=:agentid or agentparentids like :agentparentids) and id=:id").setParam("id", ids[i]).setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("scbettingmultiple", Double.valueOf(scbettingmultiple1[i])).setParam("rebate", Double.valueOf(rebates[i])/100).setParam("remark", mark).setParam("betamounts", Double.valueOf(betamountss[i])));
										}
						            }  
				     	        } 
					      }else if((valueOf!=null)&&valueOf==0){
					            String[] ids = id.split(",");
				             	String[] rebates = rebate.split(",");
					            String[] betamountss = betamounts.split(",");
				             	String[] scbettingmultiple1 = scbettingmultiple.split(",");
				             	String[] betstreams = betstream.split(",");
				            	int len1 = ids.length;
					            int len2 = rebates.length;
					            int len3 = betamountss.length;
					            int len4 = scbettingmultiple1.length;
					            int len5 = betstreams.length;
				            	if(len1==len2&&len1==len3&&len1==len4&&len1==len5){
				            		betSubordinateRebateService.update(new Finder("update bet_subordinate_rebate set rebate=0 where remark=:remark and (agentid=:agentid or agentparentids like :agentparentids) ").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("remark", "dbczmode"));
				           		    for (int i = 0; i < len1; i++) {
					     		        BetSubordinateRebate bConfig = new BetSubordinateRebate();
					     		        bConfig.setId(Integer.valueOf(ids[i]));
									    Double valueOf2 = Double.valueOf(betstreams[i]);
									    if(valueOf2==1){
									    	 bConfig.setId(Integer.valueOf(ids[i]));
									         bConfig.setRebate(Double.valueOf(rebates[i]));
						    			     bConfig.setBetamounts(Double.valueOf(betamountss[i]));
							     		     bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
							    		     bConfig.setRemark(mark);
//								             betSubordinateRebateService.update(bConfig, true);
								    	     betSubordinateRebateService.update(new Finder("UPDATE bet_subordinate_rebate SET rebate=:rebate,betamounts = :betamounts, scbettingmultiple=:scbettingmultiple WHERE remark=:remark and (agentid=:agentid or agentparentids like :agentparentids) and id=:id").setParam("id", ids[i]).setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("scbettingmultiple", Double.valueOf(scbettingmultiple1[i])).setParam("rebate", Double.valueOf(rebates[i])).setParam("remark", mark).setParam("betamounts", Double.valueOf(betamountss[i])));
									    }else if(valueOf2==0){
									    	 bConfig.setId(Integer.valueOf(ids[i]));
									         bConfig.setRebate(Double.valueOf(rebates[i])/100);
						    			     bConfig.setBetamounts(Double.valueOf(betamountss[i]));
							     		     bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
							    		     bConfig.setRemark(mark);
//								             betSubordinateRebateService.update(bConfig, true);
								    	     betSubordinateRebateService.update(new Finder("UPDATE bet_subordinate_rebate SET rebate=:rebate,betamounts = :betamounts, scbettingmultiple=:scbettingmultiple WHERE remark=:remark and (agentid=:agentid or agentparentids like :agentparentids) and id=:id").setParam("id", ids[i]).setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("scbettingmultiple", Double.valueOf(scbettingmultiple1[i])).setParam("rebate", Double.valueOf(rebates[i])/100).setParam("remark", mark).setParam("betamounts", Double.valueOf(betamountss[i])));
										}
						            }  
				     	        } 
					      }
					   }catch (Exception e) {
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					}
				}else if("rc".equals(mark)){
					String[] ids = id.split(",");
					String[] rebates = rebate.split(",");
					String[] betamountss = betamounts.split(",");
					String[] scbettingmultiple1 = scbettingmultiple.split(",");
					int len1 = ids.length;
					int len2 = rebates.length;
					int len4 = betamountss.length;
					int len3 = scbettingmultiple1.length;
					if(len1==len2&&len1==len3&&len1==len4){
						for (int i = 0; i < len1; i++) {
							BetSubordinateRebate bConfig = new BetSubordinateRebate();
							bConfig.setId(Integer.valueOf(ids[i]));
							bConfig.setRebate(Double.valueOf(rebates[i])/100);
							bConfig.setBetamounts(Double.valueOf(betamountss[i]));
							bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
							bConfig.setRemark(mark);
							betSubordinateRebateService.update(bConfig, true);
						}
					}
				}else if("sc".equals(mark)){
					String parameter = request.getParameter("scmode");
					if(StringUtils.isNoneBlank(parameter)){
						try{
							Integer valueOf = Integer.valueOf(parameter);
							if((valueOf!=null)&&valueOf==1){
								String[] ids = id.split(",");
								String[] rebates = rebate.split(",");
								String[] betamountss = betamounts.split(",");
								String[] scbettingmultiple1 = scbettingmultiple.split(",");
								String[] betstreams = betstream.split(",");
								int len1 = ids.length;
								int len2 = rebates.length;
								int len4 = betamountss.length;
								int len3 = scbettingmultiple1.length;
								int len5 = betstreams.length;
								if(len1==len2&&len1==len3&&len1==len4&&len1==len5){
									try{
										betSubordinateRebateService.update(new Finder("update bet_subordinate_rebate set rebate=1 where remark=:remark ").setParam("remark", "scmode"));
										for (int i = 0; i < len1; i++) {
											BetSubordinateRebate bConfig = new BetSubordinateRebate();
											bConfig.setId(Integer.valueOf(ids[i]));
											Double valueOf2 = Double.valueOf(betstreams[i]);
											if(valueOf2==1){
												bConfig.setRebate(Double.valueOf(rebates[i]));
												bConfig.setBetamounts(Double.valueOf(betamountss[i]));
												bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
												bConfig.setRemark(mark);
												betSubordinateRebateService.update(bConfig, true);
											}else if(valueOf2==0){
												bConfig.setRebate(Double.valueOf(rebates[i])/100);
												bConfig.setBetamounts(Double.valueOf(betamountss[i]));
												bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
												bConfig.setRemark(mark);
												betSubordinateRebateService.update(bConfig, true);
											}
											
										}
									}catch (Exception e) {
										returnObject.setStatus(ReturnDatas.ERROR);
										returnObject.setMessage(MessageUtils.UPDATE_ERROR);
									}
								}
							}else if((valueOf!=null)&&valueOf==0){
								betSubordinateRebateService.update(new Finder("update bet_subordinate_rebate set rebate=0 where remark=:remark ").setParam("remark", "scmode"));
								String[] ids = id.split(",");
								String[] rebates = rebate.split(",");
								String[] betamountss = betamounts.split(",");
								String[] scbettingmultiple1 = scbettingmultiple.split(",");
								String[] betstreams = betstream.split(",");
								int len1 = ids.length;
								int len2 = rebates.length;
								int len4 = betamountss.length;
								int len3 = scbettingmultiple1.length;
								int len5 = betstreams.length;
								if(len1==len2&&len1==len3&&len1==len4&&len1==len5){
									for (int i = 0; i < len1; i++) {
										BetSubordinateRebate bConfig = new BetSubordinateRebate();
										bConfig.setId(Integer.valueOf(ids[i]));
										try{
											Double valueOf2 = Double.valueOf(betstreams[i]);
											if(valueOf2==1){
												bConfig.setRebate(Double.valueOf(rebates[i]));
												bConfig.setBetamounts(Double.valueOf(betamountss[i]));
												bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
												bConfig.setRemark(mark);
												betSubordinateRebateService.update(bConfig, true);
											}else if(valueOf2==0){
												bConfig.setRebate(Double.valueOf(rebates[i])/100);
												bConfig.setBetamounts(Double.valueOf(betamountss[i]));
												bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
												bConfig.setRemark(mark);
												betSubordinateRebateService.update(bConfig, true);
											}
										}catch(Exception e){
											returnObject.setStatus(ReturnDatas.ERROR);
											returnObject.setMessage(MessageUtils.UPDATE_ERROR);
										}
										
									}
								}
							}
						}catch (Exception e) {
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage(MessageUtils.UPDATE_ERROR);
					}
//					String[] ids = id.split(",");
//					String[] rebates = rebate.split(",");
//					String[] betamountss = betamounts.split(",");
//					String[] scbettingmultiple1 = scbettingmultiple.split(",");
//					int len1 = ids.length;
//					int len2 = rebates.length;
//					int len4 = betamountss.length;
//					int len3 = scbettingmultiple1.length;
//					if(len1==len2&&len1==len3&&len1==len4){
//						for (int i = 0; i < len1; i++) {
//							BetSubordinateRebate bConfig = new BetSubordinateRebate();
//							bConfig.setId(Integer.valueOf(ids[i]));
//							bConfig.setRebate(Double.valueOf(rebates[i])/100);
//							bConfig.setBetamounts(Double.valueOf(betamountss[i]));
//							bConfig.setScbettingmultiple(Double.valueOf(scbettingmultiple1[i]));
//							bConfig.setRemark(mark);
//							betSubordinateRebateService.update(bConfig, true);
//						}
//					}
				}
			
			}else if(StringUtils.isNotEmpty(remark)){
				if(StringUtils.isNotEmpty(remark)){
					if("r".equals(remark) || "z".equals(remark) || "sc".equals(remark)||"zc".equals(remark)||"rc".equals(remark)||"dbcz".equals(remark)||"relief".equals(remark)||"qd".equals(remark)){
						Double scbettingmultiple11=null;
						String parameter = request.getParameter("scbettingmultiple");
						if(parameter!=null){
							scbettingmultiple11 = Double.valueOf(parameter);
						}
						betSubordinateRebateService.update(new Finder("UPDATE bet_subordinate_rebate SET rebate=:rebate,scbettingmultiple=:scbettingmultiple WHERE remark=:remark and (agentid=:agentid or agentparentids like :agentparentids)").setParam("agentid", agentid).setParam("agentparentids", "%"+agentid+"%").setParam("scbettingmultiple", scbettingmultiple11).setParam("rebate", rebateSingle).setParam("remark", remark));
						return returnObject;
					}
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
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response,BetSubordinateRebate betSubordinateRebate)  throws Exception{
//		ReturnDatas returnObject = lookjson(model, request, response);
//		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		ReturnDatas returnObject = listjson(request, model, betSubordinateRebate);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		String remark = request.getParameter("mark");
		if(StringUtils.isNoneEmpty(remark)&&"dbcz".equals(remark)){
			return "/lottery/betsubordinaterebate/betsubordinaterebateCrudbcz";
		}else if(StringUtils.isNoneEmpty(remark)&&"rc".equals(remark)){
			return "/lottery/betsubordinaterebate/betsubordinaterebateCrurc";
		}else if(StringUtils.isNoneEmpty(remark)&&"sc".equals(remark)){
			return "/lottery/betsubordinaterebate/betsubordinaterebateCrusc";
		}
		return "/lottery/betsubordinaterebate/betsubordinaterebateCru";
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
				betSubordinateRebateService.deleteById(id,BetSubordinateRebate.class);
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
			betSubordinateRebateService.deleteByIds(ids,BetSubordinateRebate.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
