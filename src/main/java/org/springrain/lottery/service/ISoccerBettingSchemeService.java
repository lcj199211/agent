package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerBettingScheme;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-14 16:25:05
 * @see org.springrain.lottery.service.SoccerBettingScheme
 */
public interface ISoccerBettingSchemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerBettingScheme findSoccerBettingSchemeById(Object id) throws Exception;
	
	
	
}
