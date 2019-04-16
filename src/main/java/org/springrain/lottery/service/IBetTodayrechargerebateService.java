package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetTodayrechargerebate;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-12 13:25:02
 * @see org.springrain.lottery.service.BetTodayrechargerebate
 */
public interface IBetTodayrechargerebateService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetTodayrechargerebate findBetTodayrechargerebateById(Object id) throws Exception;
	
	
	
}
