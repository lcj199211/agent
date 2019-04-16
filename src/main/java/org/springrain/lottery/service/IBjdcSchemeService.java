package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcScheme;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 09:37:24
 * @see org.springrain.lottery.service.BjdcScheme
 */
public interface IBjdcSchemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcScheme findBjdcSchemeById(Object id) throws Exception;
	
	
	
}
