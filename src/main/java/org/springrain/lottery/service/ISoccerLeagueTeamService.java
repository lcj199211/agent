package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueTeam;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 17:05:08
 * @see org.springrain.lottery.service.SoccerLeagueTeam
 */
public interface ISoccerLeagueTeamService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueTeam findSoccerLeagueTeamById(Object id) throws Exception;
	
	
	
}
