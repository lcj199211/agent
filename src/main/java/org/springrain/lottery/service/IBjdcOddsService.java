package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcOdds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:02:15
 * @see org.springrain.lottery.service.BjdcOdds
 */
public interface IBjdcOddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcOdds findBjdcOddsById(Object id) throws Exception;
	
	
	
}
