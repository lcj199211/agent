package org.springrain.lottery.service;

import org.springrain.lottery.entity.RenjiuResult;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-03-09 11:07:04
 * @see org.springrain.lottery.service.RenjiuResult
 */
public interface IRenjiuResultService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	RenjiuResult findRenjiuResultById(Object id) throws Exception;
	
	
	
}
