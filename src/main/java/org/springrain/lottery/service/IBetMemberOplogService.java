package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetMemberOplog;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-17 23:56:12
 * @see org.springrain.lottery.service.BetMemberOplog
 */
public interface IBetMemberOplogService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetMemberOplog findBetMemberOplogById(Object id) throws Exception;
	
	
	
}
