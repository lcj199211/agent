package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetTransferagentAccounts;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-08 09:55:02
 * @see org.springrain.lottery.service.BetTransferagentAccounts
 */
public interface IBetTransferagentAccountsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetTransferagentAccounts findBetTransferagentAccountsById(Object id) throws Exception;
	
	
	
}
