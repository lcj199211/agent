package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetCommodity;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-11 11:16:54
 * @see org.springrain.lottery.service.BetCommodity
 */
public interface IBetCommodityService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetCommodity findBetCommodityById(Object id) throws Exception;
	
	
	
}
