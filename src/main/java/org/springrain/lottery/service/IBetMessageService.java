package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetMessage;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:53:20
 * @see org.springrain.lottery.service.BetMessage
 */
public interface IBetMessageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetMessage findBetMessageById(Object id) throws Exception;
	
	
	
}
