package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 17:49:11
 * @see org.springrain.lottery.service.SoccerLeagueArrange
 */
public interface ISoccerLeagueArrangeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueArrange findSoccerLeagueArrangeById(Object id) throws Exception;
	
	
	
}
