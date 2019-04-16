package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetRedenvelope;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-02 00:22:46
 * @see org.springrain.lottery.service.BetRedenvelope
 */
public interface IBetRedenvelopeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetRedenvelope findBetRedenvelopeById(Object id) throws Exception;
	
	
	
}
