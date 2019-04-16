package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetCommission;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-20 20:37:36
 * @see org.springrain.lottery.service.BetCommission
 */
public interface IBetCommissionService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetCommission findBetCommissionById(Object id) throws Exception;
	
	
	
}
