package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueResult;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-24 16:23:56
 * @see org.springrain.lottery.service.SoccerLeagueResult
 */
public interface ISoccerLeagueResultService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueResult findSoccerLeagueResultById(Object id) throws Exception;
	
	
	
}
