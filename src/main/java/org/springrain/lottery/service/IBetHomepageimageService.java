package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetHomepageimage;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-28 15:56:47
 * @see org.springrain.lottery.service.BetHomepageimage
 */
public interface IBetHomepageimageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetHomepageimage findBetHomepageimageById(Object id) throws Exception;
	
	
	
}
