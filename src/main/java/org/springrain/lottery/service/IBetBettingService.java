package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetBetting;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-31 10:08:58
 * @see org.springrain.lottery.service.BetBetting
 */
public interface IBetBettingService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetBetting findBetBettingById(Object id) throws Exception;
	
	
	
}
