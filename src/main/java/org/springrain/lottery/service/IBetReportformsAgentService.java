package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetReportformsAgent;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-19 17:45:45
 * @see org.springrain.lottery.service.BetReportformsAgent
 */
public interface IBetReportformsAgentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetReportformsAgent findBetReportformsAgentById(Object id) throws Exception;
	
	
	
}
