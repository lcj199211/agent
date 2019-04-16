package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetChannelcharge;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-02-06 13:40:17
 * @see org.springrain.lottery.service.BetChannelcharge
 */
public interface IBetChannelchargeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetChannelcharge findBetChannelchargeById(Object id) throws Exception;
	
	
	
}
