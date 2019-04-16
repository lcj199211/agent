package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetPaymentInterface;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:51:48
 * @see org.springrain.lottery.service.BetPaymentInterface
 */
public interface IBetPaymentInterfaceService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetPaymentInterface findBetPaymentInterfaceById(Object id) throws Exception;
	
	
	
}
