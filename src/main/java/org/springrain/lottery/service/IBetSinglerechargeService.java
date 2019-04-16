package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetSinglerecharge;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-12 15:22:38
 * @see org.springrain.lottery.service.BetSinglerecharge
 */
public interface IBetSinglerechargeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetSinglerecharge findBetSinglerechargeById(Object id) throws Exception;
	
	
	
}
