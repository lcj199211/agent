package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetWeekwinorfailrebate;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-10 09:50:39
 * @see org.springrain.lottery.service.BetWeekwinorfailrebate
 */
public interface IBetWeekwinorfailrebateService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetWeekwinorfailrebate findBetWeekwinorfailrebateById(Object id) throws Exception;
	
	
	
}
