package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballScheme;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 16:06:05
 * @see org.springrain.lottery.service.BasketballScheme
 */
public interface IBasketballSchemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballScheme findBasketballSchemeById(Object id) throws Exception;
	
	
	
}
