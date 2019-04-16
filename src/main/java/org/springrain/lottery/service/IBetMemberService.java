package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetMember;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-03 16:41:36
 * @see org.springrain.lottery.service.BetMember
 */
public interface IBetMemberService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetMember findBetMemberById(Object id) throws Exception;
	
	
	
}
