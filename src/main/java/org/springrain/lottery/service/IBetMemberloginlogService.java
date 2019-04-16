package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetMemberloginlog;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-31 13:36:49
 * @see org.springrain.lottery.service.BetMemberloginlog
 */
public interface IBetMemberloginlogService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetMemberloginlog findBetMemberloginlogById(Object id) throws Exception;
	
	
	
}
