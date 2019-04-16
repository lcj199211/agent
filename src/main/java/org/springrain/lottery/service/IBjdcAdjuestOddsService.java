package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcAdjuestOdds;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-04 13:33:08
 * @see org.springrain.lottery.service.BjdcAdjuestOdds
 */
public interface IBjdcAdjuestOddsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcAdjuestOdds findBjdcAdjuestOddsById(Object id) throws Exception;
	
	
	
}
