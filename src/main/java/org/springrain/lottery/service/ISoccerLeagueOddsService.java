package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueOdds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 17:47:41
 * @see org.springrain.lottery.service.SoccerLeagueOdds
 */
public interface ISoccerLeagueOddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueOdds findSoccerLeagueOddsById(Object id) throws Exception;
	
	
	
}
