package org.springrain.lottery.service;

import org.springrain.lottery.entity.RenjiuScheme;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-07-30 16:29:19
 * @see org.springrain.lottery.service.RenjiuScheme
 */
public interface IRenjiuSchemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	RenjiuScheme findRenjiuSchemeById(Object id) throws Exception;
	
	
	
}
