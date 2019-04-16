package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLeagueTeam;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-10 16:55:00
 * @see org.springrain.lottery.service.BasketballLeagueTeam
 */
public interface IBasketballLeagueTeamService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLeagueTeam findBasketballLeagueTeamById(Object id) throws Exception;
	
	
	
}
