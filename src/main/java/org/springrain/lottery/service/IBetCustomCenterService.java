package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetCustomCenter;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-10 17:42:49
 * @see org.springrain.lottery.service.BetCustomCenter
 */
public interface IBetCustomCenterService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetCustomCenter findBetCustomCenterById(Object id) throws Exception;
	
	
	
}
