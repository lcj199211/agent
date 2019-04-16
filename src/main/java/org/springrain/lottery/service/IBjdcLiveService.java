package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcLive;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 16:08:20
 * @see org.springrain.lottery.service.BjdcLive
 */
public interface IBjdcLiveService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcLive findBjdcLiveById(Object id) throws Exception;
	
	
	
}
