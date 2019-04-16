package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGameplay;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 15:19:18
 * @see org.springrain.lottery.service.BetGameplay
 */
public interface IBetGameplayService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetGameplay findBetGameplayById(Object id) throws Exception;
	
	
	
}
