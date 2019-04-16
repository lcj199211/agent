package org.springrain.lottery.service;

import org.springrain.lottery.entity.LotteryEndtime;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-12 14:50:03
 * @see org.springrain.lottery.service.LotteryEndtime
 */
public interface ILotteryEndtimeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LotteryEndtime findLotteryEndtimeById(Object id) throws Exception;
	
	
	
}
