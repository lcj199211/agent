package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetTransferAccounts;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-02 10:06:57
 * @see org.springrain.lottery.service.BetTransferAccounts
 */
public interface IBetTransferAccountsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetTransferAccounts findBetTransferAccountsById(Object id) throws Exception;
	
	
	
}
