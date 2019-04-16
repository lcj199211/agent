package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetMemberLetter;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-07-10 17:29:40
 * @see org.springrain.lottery.service.BetMemberLetter
 */
public interface IBetMemberLetterService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetMemberLetter findBetMemberLetterById(Object id) throws Exception;
	
	
	
}
