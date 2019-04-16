package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetSigninReward;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-07 09:45:15
 * @see org.springrain.lottery.service.BetSigninReward
 */
public interface IBetSigninRewardService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetSigninReward findBetSigninRewardById(Object id) throws Exception;
	
	
	
}
