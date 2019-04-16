package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAgentannouncement;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-15 11:11:45
 * @see org.springrain.lottery.service.BetAgentannouncement
 */
public interface IBetAgentannouncementService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentannouncement findBetAgentannouncementById(Object id) throws Exception;
	
	
	
}
