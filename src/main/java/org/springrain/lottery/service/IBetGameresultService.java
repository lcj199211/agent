package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGameresult;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-18 10:58:47
 * @see org.springrain.lottery.service.BetGameresult
 */
public interface IBetGameresultService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetGameresult findBetGameresultById(Object id) throws Exception;
	
	
	
}
