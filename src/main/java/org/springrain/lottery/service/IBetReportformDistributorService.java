package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetReportformDistributor;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-31 10:10:59
 * @see org.springrain.lottery.service.BetReportformDistributor
 */
public interface IBetReportformDistributorService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetReportformDistributor findBetReportformDistributorById(Object id) throws Exception;
	
	
	
}
