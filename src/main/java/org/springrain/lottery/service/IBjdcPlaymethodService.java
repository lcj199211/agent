package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcPlaymethod;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:02:41
 * @see org.springrain.lottery.service.BjdcPlaymethod
 */
public interface IBjdcPlaymethodService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcPlaymethod findBjdcPlaymethodById(Object id) throws Exception;
	
	
	
}
