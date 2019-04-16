package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLeague;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:38:01
 * @see org.springrain.lottery.service.BasketballLeague
 */
public interface IBasketballLeagueService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLeague findBasketballLeagueById(Object id) throws Exception;
	
	
	
}
