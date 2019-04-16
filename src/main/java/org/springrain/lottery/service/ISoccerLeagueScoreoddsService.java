package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueScoreodds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-23 09:03:03
 * @see org.springrain.lottery.service.SoccerLeagueScoreodds
 */
public interface ISoccerLeagueScoreoddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueScoreodds findSoccerLeagueScoreoddsById(Object id) throws Exception;
	
	
	
}
