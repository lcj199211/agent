package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetRechargecard;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-26 16:43:02
 * @see org.springrain.lottery.service.BetRechargecard
 */
public interface IBetRechargecardService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetRechargecard findBetRechargecardById(Object id) throws Exception;
	
	
	
}
