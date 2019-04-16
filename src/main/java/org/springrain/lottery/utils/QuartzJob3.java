package org.springrain.lottery.utils;

import java.util.List;

import javax.annotation.Resource;

import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.system.entity.BetAgentData;
import org.springrain.system.service.IBetAgentDataService;

public class QuartzJob3 {
	@Resource
	private IBetGoldService betGoldService;
	@Resource
	private IBetAgentDataService betAgentDataService;
	
	//充值訂單到時自動取消
	public void work() throws Exception {
		List<BetAgentData> agentDatas = betAgentDataService.queryForList(new Finder("select * from bet_agent_data where code=:code ").setParam("code", "chargepasstime"), BetAgentData.class);
		for (BetAgentData betAgentData : agentDatas) {
			List<BetGold> betGoldList = betGoldService.queryForList(new Finder("SELECT*from bet_gold where submittime<=CURRENT_TIMESTAMP - INTERVAL :value MINUTE and state=0 and (agentid=:agentid or agentparentids like :agentparentids) ").setParam("value", betAgentData.getValue()).setParam("agentid", betAgentData.getCompany()).setParam("agentparentids", "%,"+betAgentData.getCompany()+",%"), BetGold.class);
			if(betGoldList!=null){
				for (BetGold betGold : betGoldList) {
					betGold.setOperator("超时自动取消");
					betGold.setState(1);
					betGoldService.update(betGold);
				}
			}
		}
	}

	

	
}
