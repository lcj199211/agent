package org.springrain.lottery.service;

import java.util.Date;
import java.util.List;

import org.springrain.frame.util.Page;
import org.springrain.lottery.entity.BetOptLog;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:51:24
 * @see org.springrain.lottery.service.BetOptLog
 */
public interface IBetOptLogService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetOptLog findBetOptLogById(Object id) throws Exception;

	List<BetOptLog> findListDataByDate(Page page, Date startTime, Date endTime) throws Exception;
	
	public  void saveoptLog(String tool,String ip,String details,String agentid,String agentparentid,String agentparentids) ;
	
}
