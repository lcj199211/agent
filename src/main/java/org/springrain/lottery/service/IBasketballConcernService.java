package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballConcern;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 16:05:32
 * @see org.springrain.lottery.service.BasketballConcern
 */
public interface IBasketballConcernService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballConcern findBasketballConcernById(Object id) throws Exception;
	
	
	
}
