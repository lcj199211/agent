package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerLive;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-19 09:38:14
 * @see org.springrain.lottery.service.SoccerLive
 */
public interface ISoccerLiveService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerLive findSoccerLiveById(Object id) throws Exception;
	
	
	
}
