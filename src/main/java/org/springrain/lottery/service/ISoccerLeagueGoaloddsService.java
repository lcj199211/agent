package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueGoalodds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-23 15:18:40
 * @see org.springrain.lottery.service.SoccerLeagueGoalodds
 */
public interface ISoccerLeagueGoaloddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueGoalodds findSoccerLeagueGoaloddsById(Object id) throws Exception;
	
	
	
}
