package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetMemberbankcard;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-13 12:47:57
 * @see org.springrain.lottery.service.BetMemberbankcard
 */
public interface IBetMemberbankcardService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetMemberbankcard findBetMemberbankcardById(Object id) throws Exception;
	
	
	
}
