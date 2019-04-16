package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcConcern;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 09:35:21
 * @see org.springrain.lottery.service.BjdcConcern
 */
public interface IBjdcConcernService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcConcern findBjdcConcernById(Object id) throws Exception;
	
	
	
}
