package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLiveSituation;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-19 09:38:37
 * @see org.springrain.lottery.service.SoccerLiveSituation
 */
public interface ISoccerLiveSituationService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLiveSituation findSoccerLiveSituationById(Object id) throws Exception;
	
	
	
}
