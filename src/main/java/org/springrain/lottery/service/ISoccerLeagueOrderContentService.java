package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLeagueOrderContent;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-04 09:18:59
 * @see org.springrain.lottery.service.SoccerLeagueOrderContent
 */
public interface ISoccerLeagueOrderContentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLeagueOrderContent findSoccerLeagueOrderContentById(Object id) throws Exception;
	
	
	
}
