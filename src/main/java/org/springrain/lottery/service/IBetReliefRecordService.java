package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetReliefRecord;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-11 10:31:17
 * @see org.springrain.lottery.service.BetReliefRecord
 */
public interface IBetReliefRecordService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetReliefRecord findBetReliefRecordById(Object id) throws Exception;
	
	
	
}
