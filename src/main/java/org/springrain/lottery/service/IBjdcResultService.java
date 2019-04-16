package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcResult;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-05 11:06:35
 * @see org.springrain.lottery.service.BjdcResult
 */
public interface IBjdcResultService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcResult findBjdcResultById(Object id) throws Exception;
	
	
	
}
