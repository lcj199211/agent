package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentGamemanage;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-24 10:03:23
 * @see org.springrain.lottery.service.BetAgentGamemanage
 */
public interface IBetAgentGamemanageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentGamemanage findBetAgentGamemanageById(Object id) throws Exception;
	
	
	
}
