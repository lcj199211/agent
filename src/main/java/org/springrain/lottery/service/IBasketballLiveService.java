package org.springrain.lottery.service;

import org.springrain.lottery.entity.BasketballLive;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 10:32:58
 * @see org.springrain.lottery.service.BasketballLive
 */
public interface IBasketballLiveService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BasketballLive findBasketballLiveById(Object id) throws Exception;
	
	
	
}
