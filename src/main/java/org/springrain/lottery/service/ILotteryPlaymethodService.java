package org.springrain.lottery.service;

import org.springrain.lottery.entity.LotteryPlaymethod;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 玩法接口
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:35:18
 * @see org.springrain.lottery.service.LotteryPlaymethod
 */
public interface ILotteryPlaymethodService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LotteryPlaymethod findLotteryPlaymethodById(Object id) throws Exception;
	
	
	
}
