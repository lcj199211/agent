package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentwithdraw;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-25 15:08:35
 * @see org.springrain.lottery.service.BetAgentwithdraw
 */
public interface IBetAgentwithdrawService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentwithdraw findBetAgentwithdrawById(Object id) throws Exception;
	
	
	
}
