package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentRechargeRebate;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-23 15:57:45
 * @see org.springrain.lottery.service.BetAgentRechargeRebate
 */
public interface IBetAgentRechargeRebateService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentRechargeRebate findBetAgentRechargeRebateById(Object id) throws Exception;
	
	
	
}
