package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcArrange;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:01:22
 * @see org.springrain.lottery.service.BjdcArrange
 */
public interface IBjdcArrangeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcArrange findBjdcArrangeById(Object id) throws Exception;
	
	
	
}
