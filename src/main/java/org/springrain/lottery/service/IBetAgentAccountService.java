package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentAccount;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-23 15:55:19
 * @see org.springrain.lottery.service.BetAgentAccount
 */
public interface IBetAgentAccountService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentAccount findBetAgentAccountById(Object id) throws Exception;
	
	
	
}
