package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetSelfdestroy;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-13 17:17:26
 * @see org.springrain.lottery.service.BetSelfdestroy
 */
public interface IBetSelfdestroyService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetSelfdestroy findBetSelfdestroyById(Object id) throws Exception;
	
	
	
}
