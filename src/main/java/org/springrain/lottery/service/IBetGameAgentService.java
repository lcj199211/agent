package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGameAgent;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-10 11:02:59
 * @see org.springrain.lottery.service.BetGameAgent
 */
public interface IBetGameAgentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetGameAgent findBetGameAgentById(Object id) throws Exception;
	
	
	
}
