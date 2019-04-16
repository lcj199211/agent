package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetRankMember;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:52:41
 * @see org.springrain.lottery.service.BetRankMember
 */
public interface IBetRankMemberService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetRankMember findBetRankMemberById(Object id) throws Exception;
	
	
	
}
