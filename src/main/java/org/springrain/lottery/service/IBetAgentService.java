package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgent;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-11 15:44:16
 * @see org.springrain.lottery.service.BetAgent
 */
public interface IBetAgentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgent findBetAgentById(Object id) throws Exception;
	
	
	
}
