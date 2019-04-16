package org.springrain.lottery.service;

import org.springrain.lottery.entity.RenjiuMatch;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-02-23 11:24:47
 * @see org.springrain.lottery.service.RenjiuMatch
 */
public interface IRenjiuMatchService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	RenjiuMatch findRenjiuMatchById(Object id) throws Exception;
	
	
	
}
