package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetActivityCenter;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-08 09:55:56
 * @see org.springrain.lottery.service.BetActivityCenter
 */
public interface IBetActivityCenterService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetActivityCenter findBetActivityCenterById(Object id) throws Exception;
	
	
	
}
