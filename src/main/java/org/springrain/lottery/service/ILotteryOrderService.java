package org.springrain.lottery.service;

import org.springrain.lottery.entity.LotteryOrder;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-04-10 13:36:43
 * @see org.springrain.lottery.service.LotteryOrder
 */
public interface ILotteryOrderService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LotteryOrder findLotteryOrderById(Object id) throws Exception;
	
	
	
}
