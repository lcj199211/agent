package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentreportformJc;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-24 10:41:36
 * @see org.springrain.lottery.service.BetAgentreportformJc
 */
public interface IBetAgentreportformJcService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentreportformJc findBetAgentreportformJcById(Object id) throws Exception;
	
	
	
}
