package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueOrder;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.service.SoccerLeagueOrder
 */
public interface ISoccerLeagueOrderService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueOrder findSoccerLeagueOrderById(Object id) throws Exception;
	
	
	
}
