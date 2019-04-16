package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGold;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-23 16:46:05
 * @see org.springrain.lottery.service.BetGold
 */
public interface IBetGoldService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetGold findBetGoldById(Object id) throws Exception;
	
	
	
}
