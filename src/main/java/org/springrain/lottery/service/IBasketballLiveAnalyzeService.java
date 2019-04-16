package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLiveAnalyze;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 10:33:12
 * @see org.springrain.lottery.service.BasketballLiveAnalyze
 */
public interface IBasketballLiveAnalyzeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLiveAnalyze findBasketballLiveAnalyzeById(Object id) throws Exception;
	
	
	
}
