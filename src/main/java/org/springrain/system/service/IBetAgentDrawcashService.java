package org.springrain.system.service;

import org.springrain.system.entity.BetAgentDrawcash;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-26 10:15:07
 * @see org.springrain.lottery.service.BetAgentDrawcash
 */
public interface IBetAgentDrawcashService extends IBaseSpringrainService  {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentDrawcash findIBetAgentDrawcashById(Object id) throws Exception;

}
