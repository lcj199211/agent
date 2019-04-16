package org.springrain.lottery.service;

import org.springrain.lottery.entity.TicketReportfrom;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-07 09:40:04
 * @see org.springrain.lottery.service.TicketReportfrom
 */
public interface ITicketReportfromService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	TicketReportfrom findTicketReportfromById(Object id) throws Exception;
	
	
	
}
