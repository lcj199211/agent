package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetLimitusername;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-05 13:15:35
 * @see org.springrain.lottery.service.BetLimitusername
 */
public interface IBetLimitusernameService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetLimitusername findBetLimitusernameById(Object id) throws Exception;
	
	
	
}
