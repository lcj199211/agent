package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballOrderContent;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 16:09:18
 * @see org.springrain.lottery.service.BasketballOrderContent
 */
public interface IBasketballOrderContentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballOrderContent findBasketballOrderContentById(Object id) throws Exception;
	
	
	
}
