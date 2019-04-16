package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetCollectionAddress;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-14 15:56:46
 * @see org.springrain.lottery.service.BetCollectionAddress
 */
public interface IBetCollectionAddressService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetCollectionAddress findBetCollectionAddressById(Object id) throws Exception;
	
	
	
}
