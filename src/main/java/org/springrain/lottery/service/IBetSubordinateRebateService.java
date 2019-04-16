package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-08 09:57:32
 * @see org.springrain.lottery.service.BetSubordinateRebate
 */
public interface IBetSubordinateRebateService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetSubordinateRebate findBetSubordinateRebateById(Object id) throws Exception;
	
	
	
}
