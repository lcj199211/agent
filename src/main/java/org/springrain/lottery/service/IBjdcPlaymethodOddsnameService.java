package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcPlaymethodOddsname;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:03:03
 * @see org.springrain.lottery.service.BjdcPlaymethodOddsname
 */
public interface IBjdcPlaymethodOddsnameService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcPlaymethodOddsname findBjdcPlaymethodOddsnameById(Object id) throws Exception;
	
	
	
}
