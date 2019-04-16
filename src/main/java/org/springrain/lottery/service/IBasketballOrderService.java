package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballOrder;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 16:08:39
 * @see org.springrain.lottery.service.BasketballOrder
 */
public interface IBasketballOrderService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballOrder findBasketballOrderById(Object id) throws Exception;
	
	
	
}
