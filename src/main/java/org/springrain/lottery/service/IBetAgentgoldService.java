package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentgold;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-01 09:41:49
 * @see org.springrain.lottery.service.BetAgentgold
 */
public interface IBetAgentgoldService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentgold findBetAgentgoldById(Object id) throws Exception;
	
	
	
}
