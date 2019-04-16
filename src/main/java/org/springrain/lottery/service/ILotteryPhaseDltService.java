package org.springrain.lottery.service;

import org.springrain.lottery.entity.LotteryPhaseDlt;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 结果接口
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-02-24 10:48:11
 * @see org.springrain.lottery.service.LotteryPhaseDlt
 */
public interface ILotteryPhaseDltService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LotteryPhaseDlt findLotteryPhaseDltById(Object id) throws Exception;
	
	
	
}
