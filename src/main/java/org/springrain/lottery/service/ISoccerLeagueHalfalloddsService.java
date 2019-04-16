package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueHalfallodds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-23 14:01:58
 * @see org.springrain.lottery.service.SoccerLeagueHalfallodds
 */
public interface ISoccerLeagueHalfalloddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueHalfallodds findSoccerLeagueHalfalloddsById(Object id) throws Exception;
	
	
	
}
