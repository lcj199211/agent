package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLeagueOdds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:46:58
 * @see org.springrain.lottery.service.BasketballLeagueOdds
 */
public interface IBasketballLeagueOddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLeagueOdds findBasketballLeagueOddsById(Object id) throws Exception;
	
	
	
}
