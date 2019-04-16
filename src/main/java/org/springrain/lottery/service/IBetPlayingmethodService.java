package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetPlayingmethod;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-09 17:19:50
 * @see org.springrain.lottery.service.BetPlayingmethod
 */
public interface IBetPlayingmethodService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetPlayingmethod findBetPlayingmethodById(Object id) throws Exception;
	
	
	
}
