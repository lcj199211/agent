package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetKeyvalue;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-22 16:22:30
 * @see org.springrain.lottery.service.BetKeyvalue
 */
public interface IBetKeyvalueService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetKeyvalue findBetKeyvalueById(Object id) throws Exception;
	
	
	
}
