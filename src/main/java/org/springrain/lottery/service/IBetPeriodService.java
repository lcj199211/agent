package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetPeriod;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-09 16:17:46
 * @see org.springrain.lottery.service.BetPeriod
 */
public interface IBetPeriodService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetPeriod findBetPeriodById(Object id) throws Exception;
	
	
	
}
