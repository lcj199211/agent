package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerSchemeMatch;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-18 09:08:37
 * @see org.springrain.lottery.service.SoccerSchemeMatch
 */
public interface ISoccerSchemeMatchService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerSchemeMatch findSoccerSchemeMatchById(Object id) throws Exception;
	
	
	
}
