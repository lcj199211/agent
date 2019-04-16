package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentPaymentCode;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2019-03-19 11:28:46
 * @see org.springrain.news.service.BetAgentPaymentCode
 */
public interface IBetAgentPaymentCodeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentPaymentCode findBetAgentPaymentCodeById(Object id) throws Exception;
	
	
	
}
