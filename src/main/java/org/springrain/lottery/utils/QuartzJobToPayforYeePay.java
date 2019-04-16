package org.springrain.lottery.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springrain.frame.cached.ICached;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetPayforYeePay;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.lottery.service.IBetCentralbankService;
import org.springrain.lottery.service.IBetMemberService;
import org.springrain.lottery.service.IBetPayforYeePayService;
import org.springrain.lottery.service.IBetPayforannotherDictService;
import org.springrain.lottery.service.IBetPayforannotherService;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.lottery.service.IBetWithdrawcashService;

import com.yeepay.g3.sdk.yop.client.YopClient3;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;

public class QuartzJobToPayforYeePay {
	@Resource
	private IBetWithdrawcashService betWithdrawcashService;
	@Resource
	private IBetMemberService betMemberService;
	@Resource
	private ICached cached;
	@Resource
	private IBetPayforannotherService betPayforannotherService;
	@Resource
	private IBetPayforannotherDictService betPayforannotherdictService;
	@Resource
	private IBetScorerecordService betScorerecordService;
	@Resource
	private IBetCentralbankService iBetCentralbankService;
	@Resource
	private IBetPayforYeePayService betPayforYeePayService;
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> payforQuery(Map<String, Object> data) {
		try{
            //定义代付参数
			YopRequest yoprequest = new YopRequest("OPR:"+data.get("customerNumber").toString(), data.get("privateKey").toString());
			//拼接代付参数
			yoprequest.addParam("customerNumber", data.get("customerNumber").toString());
			yoprequest.addParam("batchNo", data.get("batchNo").toString());
			yoprequest.addParam("product", "WTJS");
			yoprequest.addParam("orderId", data.get("orderId").toString());
			yoprequest.addParam("pageNo", "1");
			yoprequest.addParam("pageSize", "100");
			YopResponse response = YopClient3.postRsa("/rest/v1.0/balance/transfer_query",
					yoprequest);
			Map<String, Object> yopresponsemap = (Map<String, Object>) response.getResult();
			return yopresponsemap;
		}catch (Exception e) {
			return null;
		}
	}
	
	
	public void work() {
		try {
			System.out.println("易宝代付定时任务。。。。。");
			//循环代付记录表状态为0的
			List<BetPayforYeePay> agents = betPayforYeePayService.queryForList(new Finder("select * from bet_payforanother where state=0 order by id "), BetPayforYeePay.class);
			//找支付平台基本信息(多公司)
			List<Map<String, Object>> betPayforYeePayDicts = betPayforYeePayService.queryForList(new Finder("select * from bet_payforyeepay_dict "));
			BetWithdrawcash betWithdrawcash = new BetWithdrawcash();
			if (agents.size()==0) {
				System.out.println("没代付记录。。。。。。。");
			}
			for (BetPayforYeePay betPayforYeePay : agents) {
				for (Map<String, Object> betPayforYeePayDict : betPayforYeePayDicts) {
					if(betPayforYeePay.getCompany().equals(betPayforYeePayDict.get("id"))) {
						Map<String, Object> data = new HashMap<String, Object>();
						data.put("customerNumber", betPayforYeePayDict.get("merid"));//应用标识
						data.put("batchNo", betPayforYeePay.getTranId());//批次号  流水号
						data.put("product", "WTJS");
						data.put("orderId", betPayforYeePay.getOrderId());
						data.put("pageNo", "1");
						data.put("pageSize", "100");
						data.put("privateKey", betPayforYeePayDict.get("privatekey"));
						//查询
						Map<String, Object> rmap = payforQuery(data);
						if(rmap==null) {
							betPayforYeePay.setState(1);
							betPayforYeePayService.update(betPayforYeePay);
							System.out.println("订单"+betPayforYeePay.getOrderId()+"不存在");
							continue;
						}
						
						BetWithdrawcash betWithdrawcash1 = betWithdrawcashService.findBetWithdrawcashById(betPayforYeePay.getWeithdrawcashId());
						BetMember betMember = betMemberService.findBetMemberById(betWithdrawcash1.getMemberid());
						if(betMember!=null){
							String rspcode = rmap.get("rspcode").toString();//代付成功,....这里要修改不知道返回什么字段
							if (rspcode.equals("00000")) {
								// 记录代付
								betPayforYeePay.setState(2);
								betPayforYeePayService.update(betPayforYeePay);

								Date date=new Date();
								betWithdrawcash.setId(betPayforYeePay.getWeithdrawcashId());
								betWithdrawcash.setState(5);//状态： 0、已申请；1、取消；2、已处理；3、代付处理中；4、代付失败；5、代付成功；
								betWithdrawcash.setBwcs(betMember.getScore());//提现前分
								betWithdrawcash.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
								betWithdrawcash.setAwcs(betMember.getScore()-betWithdrawcash1.getMoney());//提现后分
								betWithdrawcash.setOperator("易宝代付");
								betWithdrawcash.setAudittime(date);
								betMember.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
								betMember.setScore(betMember.getScore()-betWithdrawcash1.getMoney());
								betMemberService.update(betMember);
								betWithdrawcashService.update(betWithdrawcash, true);
								//中央银行
							    iBetCentralbankService.update(new Finder("update bet_centralbank set withdrawcash = withdrawcash+:withdrawcash ").setParam("withdrawcash", betWithdrawcash1.getMoney()));
							    //金币记录
							    BetScorerecord betScorerecord=new BetScorerecord();
							    String content="";
							    content = betWithdrawcash1.getMoney() + "提现成功";
							    betScorerecord.setMemberid2(betMember.getId2());
							    betScorerecord.setTime(date);
							    betScorerecord.setContent(content);
							    betScorerecord.setOriginalscore(betWithdrawcash.getBwcs());
							    betScorerecord.setChangescore(0.0);
							    betScorerecord.setGamescore(BigDecimal.valueOf(betMember.getGamescore()));
							    betScorerecord.setBankscore(BigDecimal.valueOf(betMember.getBankscore()));
							    betScorerecord.setFreezescore(BigDecimal.valueOf(betMember.getFreezingscore()));
							    betScorerecord.setAgentid(betMember.getAgentid());
							    betScorerecord.setAgentparentid(betMember.getAgentparentid());
							    betScorerecord.setAgentparentids(betMember.getAgentparentids());
							    betScorerecord.setOrderid(betWithdrawcash.getId());
							    betScorerecord.setBalance(betWithdrawcash.getAwcs());
							    betScorerecord.setState(1);
							    betScorerecord.setType(2);
							    betScorerecordService.saveBetScorerecord(betScorerecord);
							     
							}else if(rspcode.equals("10006")) {
								betPayforYeePay.setState(1);
								betPayforYeePayService.update(betPayforYeePay);
	
								Date date =new Date();		
								betWithdrawcash.setId(betPayforYeePay.getWeithdrawcashId());
								betWithdrawcash.setState(4);//状态： 0、已申请；1、取消；2、已处理；3、代付处理中；4、代付失败；5、代付成功；
								betWithdrawcash.setBwcs(betMember.getScore());//提现前分
								betWithdrawcash.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());//冻结分
								betWithdrawcash.setAwcs(betMember.getScore());//提现后分
								betWithdrawcash.setOperator("易宝代付");
								betWithdrawcash.setAudittime(date);
								
								betMember.setBankscore(betMember.getBankscore()+betWithdrawcash1.getMoney());
								betMember.setFreezingscore(betMember.getFreezingscore()-betWithdrawcash1.getMoney());
								betMemberService.update(betMember);
								betWithdrawcashService.update(betWithdrawcash, true);

							    //金币记录
							    BetScorerecord betScorerecord=new BetScorerecord();
							    String content="";
							    content="提现失败";
							    betScorerecord.setMemberid2(betMember.getId2());
							    betScorerecord.setTime(date);
							    betScorerecord.setContent(content);
							    betScorerecord.setOriginalscore(betWithdrawcash.getBwcs());
							    betScorerecord.setChangescore(betWithdrawcash1.getMoney());
							    betScorerecord.setGamescore(BigDecimal.valueOf(betMember.getGamescore()));
							    betScorerecord.setBankscore(BigDecimal.valueOf(betMember.getBankscore()));
							    betScorerecord.setFreezescore(BigDecimal.valueOf(betMember.getFreezingscore()));
							    betScorerecord.setAgentid(betMember.getAgentid());
							    betScorerecord.setAgentparentid(betMember.getAgentparentid());
							    betScorerecord.setAgentparentids(betMember.getAgentparentids());
							    betScorerecord.setOrderid(betWithdrawcash.getId());
							    betScorerecord.setBalance(betWithdrawcash.getAwcs());
							    betScorerecord.setState(1);
							    betScorerecord.setType(2);
							    betScorerecordService.saveBetScorerecord(betScorerecord);
							}
						}
					}
				}
			}
		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
			System.out.println("QuartzJobToPayforannother代付状态查询有异常");
		}
	}
}
