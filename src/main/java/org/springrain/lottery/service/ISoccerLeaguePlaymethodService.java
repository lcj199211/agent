package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeaguePlaymethod;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 17:59:17
 * @see org.springrain.lottery.service.SoccerLeaguePlaymethod
 */
public interface ISoccerLeaguePlaymethodService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeaguePlaymethod findSoccerLeaguePlaymethodById(Object id) throws Exception;
	
	
	
}
