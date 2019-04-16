package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentBrokerage;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-14 09:50:03
 * @see org.springrain.lottery.service.BetAgentBrokerage
 */
public interface IBetAgentBrokerageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentBrokerage findBetAgentBrokerageById(Object id) throws Exception;
	
	
	
}
