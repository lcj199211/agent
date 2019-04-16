package org.springrain.lottery.service;

import org.springrain.lottery.entity.LotteryPhase;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 期次信息接口
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:34:26
 * @see org.springrain.lottery.service.LotteryPhase
 */
public interface ILotteryPhaseService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LotteryPhase findLotteryPhaseById(Object id) throws Exception;
	
	
	
}
