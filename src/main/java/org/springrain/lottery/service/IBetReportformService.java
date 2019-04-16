package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetReportform;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-12 11:30:45
 * @see org.springrain.lottery.service.BetReportform
 */
public interface IBetReportformService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetReportform findBetReportformById(Object id) throws Exception;
	
	
	
}
