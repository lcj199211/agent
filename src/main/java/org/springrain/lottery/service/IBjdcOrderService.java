package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcOrder;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 09:36:43
 * @see org.springrain.lottery.service.BjdcOrder
 */
public interface IBjdcOrderService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcOrder findBjdcOrderById(Object id) throws Exception;
	
	
	
}
