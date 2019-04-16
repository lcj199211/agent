package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentalipay;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-06 15:13:59
 * @see org.springrain.lottery.service.BetAgentalipay
 */
public interface IBetAgentalipayService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentalipay findBetAgentalipayById(Object id) throws Exception;
	
	
	
}
