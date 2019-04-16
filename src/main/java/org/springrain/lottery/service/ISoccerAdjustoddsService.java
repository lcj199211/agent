package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerAdjustodds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-10 14:03:43
 * @see org.springrain.lottery.service.SoccerAdjustodds
 */
public interface ISoccerAdjustoddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerAdjustodds findSoccerAdjustoddsById(Object id) throws Exception;
	
	
	
}
