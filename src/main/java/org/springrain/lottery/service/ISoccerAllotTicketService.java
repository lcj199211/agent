package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerAllotTicket;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-09-06 10:24:53
 * @see org.springrain.lottery.service.SoccerAllotTicket
 */
public interface ISoccerAllotTicketService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerAllotTicket findSoccerAllotTicketById(Object id) throws Exception;
	
	
	
}
