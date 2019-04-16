package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcOrderContent;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 09:37:03
 * @see org.springrain.lottery.service.BjdcOrderContent
 */
public interface IBjdcOrderContentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcOrderContent findBjdcOrderContentById(Object id) throws Exception;
	
	
	
}
