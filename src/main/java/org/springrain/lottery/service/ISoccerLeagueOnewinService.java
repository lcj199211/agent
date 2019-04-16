package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueOnewin;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-30 10:10:03
 * @see org.springrain.lottery.service.SoccerLeagueOnewin
 */
public interface ISoccerLeagueOnewinService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueOnewin findSoccerLeagueOnewinById(Object id) throws Exception;
	
	
	
}
