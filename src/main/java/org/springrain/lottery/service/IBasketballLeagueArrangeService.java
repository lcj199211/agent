package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLeagueArrange;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:43:08
 * @see org.springrain.lottery.service.BasketballLeagueArrange
 */
public interface IBasketballLeagueArrangeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLeagueArrange findBasketballLeagueArrangeById(Object id) throws Exception;
	
	
	
}
