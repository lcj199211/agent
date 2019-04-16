package org.springrain.lottery.service;

import org.springrain.lottery.entity.LotteryScheme;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:51:48
 * @see org.springrain.lottery.service.LotteryScheme
 */
public interface ILotterySchemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LotteryScheme findLotterySchemeById(Object id) throws Exception;
	
	
	
}
