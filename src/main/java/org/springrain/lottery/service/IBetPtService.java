package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetPt;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-20 18:12:06
 * @see org.springrain.lottery.service.BetPt
 */
public interface IBetPtService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetPt findBetPtById(Object id) throws Exception;
	
	
	
}
