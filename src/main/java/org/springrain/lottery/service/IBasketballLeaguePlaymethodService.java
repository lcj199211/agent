package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLeaguePlaymethod;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:47:30
 * @see org.springrain.lottery.service.BasketballLeaguePlaymethod
 */
public interface IBasketballLeaguePlaymethodService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLeaguePlaymethod findBasketballLeaguePlaymethodById(Object id) throws Exception;
	
	
	
}
