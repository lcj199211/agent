package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetRegisterReward;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-12 11:04:57
 * @see org.springrain.lottery.service.BetRegisterReward
 */
public interface IBetRegisterRewardService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetRegisterReward findBetRegisterRewardById(Object id) throws Exception;
	
	
	
}
