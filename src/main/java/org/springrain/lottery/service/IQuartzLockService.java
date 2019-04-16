package org.springrain.lottery.service;

import org.springrain.lottery.entity.QuartzLock;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-05 12:49:18
 * @see org.springrain.lottery.service.QuartzLock
 */
public interface IQuartzLockService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuartzLock findQuartzLockById(Object id) throws Exception;
	
	
	
}
