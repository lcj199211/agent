package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLeagueResult;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-10 09:23:04
 * @see org.springrain.lottery.service.BasketballLeagueResult
 */
public interface IBasketballLeagueResultService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLeagueResult findBasketballLeagueResultById(Object id) throws Exception;
	
	
	
}
