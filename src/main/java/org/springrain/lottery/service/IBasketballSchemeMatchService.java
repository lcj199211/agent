package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballSchemeMatch;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 16:06:43
 * @see org.springrain.lottery.service.BasketballSchemeMatch
 */
public interface IBasketballSchemeMatchService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballSchemeMatch findBasketballSchemeMatchById(Object id) throws Exception;
	
	
	
}
