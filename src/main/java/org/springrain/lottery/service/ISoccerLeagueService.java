package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeague;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 16:51:27
 * @see org.springrain.lottery.service.SoccerLeague
 */
public interface ISoccerLeagueService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeague findSoccerLeagueById(Object id) throws Exception;
	
	
	
}
