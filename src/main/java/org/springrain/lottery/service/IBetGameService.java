package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGame;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-04-11 14:54:00
 * @see org.springrain.lottery.service.BetGame
 */
public interface IBetGameService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetGame findBetGameById(Object id) throws Exception;
	
	
	
}
