package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcSchemeMatch;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 09:37:41
 * @see org.springrain.lottery.service.BjdcSchemeMatch
 */
public interface IBjdcSchemeMatchService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcSchemeMatch findBjdcSchemeMatchById(Object id) throws Exception;
	
	
	
}
