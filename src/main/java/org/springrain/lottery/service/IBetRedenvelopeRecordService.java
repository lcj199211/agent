package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetRedenvelopeRecord;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-02 01:50:02
 * @see org.springrain.lottery.service.BetRedenvelopeRecord
 */
public interface IBetRedenvelopeRecordService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetRedenvelopeRecord findBetRedenvelopeRecordById(Object id) throws Exception;
	
	
	
}
