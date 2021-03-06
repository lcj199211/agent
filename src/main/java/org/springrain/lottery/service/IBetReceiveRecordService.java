package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetReceiveRecord;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:53:06
 * @see org.springrain.lottery.service.BetReceiveRecord
 */
public interface IBetReceiveRecordService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetReceiveRecord findBetReceiveRecordById(Object id) throws Exception;
	
	
	
}
