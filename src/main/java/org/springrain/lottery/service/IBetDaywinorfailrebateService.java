package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetDaywinorfailrebate;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-25 11:30:16
 * @see org.springrain.lottery.service.BetDaywinorfailrebate
 */
public interface IBetDaywinorfailrebateService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetDaywinorfailrebate findBetDaywinorfailrebateById(Object id) throws Exception;
	
	
	
}
