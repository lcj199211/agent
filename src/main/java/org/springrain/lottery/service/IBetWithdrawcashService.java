package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetWithdrawcash;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 09:58:21
 * @see org.springrain.lottery.service.BetWithdrawcash
 */
public interface IBetWithdrawcashService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetWithdrawcash findBetWithdrawcashById(Object id) throws Exception;
	
	
	
}
