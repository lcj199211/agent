package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeague2choose1odds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-23 16:47:58
 * @see org.springrain.lottery.service.SoccerLeague2choose1odds
 */
public interface ISoccerLeague2choose1oddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeague2choose1odds findSoccerLeague2choose1oddsById(Object id) throws Exception;
	
	
	
}
