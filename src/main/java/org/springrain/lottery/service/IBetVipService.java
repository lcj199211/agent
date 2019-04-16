package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetVip;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:52:01
 * @see org.springrain.lottery.service.BetVip
 */
public interface IBetVipService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetVip findBetVipById(Object id) throws Exception;
	
	
}
