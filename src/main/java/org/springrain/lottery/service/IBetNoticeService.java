package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetNotice;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-11 17:54:51
 * @see org.springrain.lottery.service.BetNotice
 */
public interface IBetNoticeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetNotice findBetNoticeById(Object id) throws Exception;
	
	
	
}
