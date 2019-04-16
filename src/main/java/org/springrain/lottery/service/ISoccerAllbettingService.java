package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerAllbetting;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-14 16:24:25
 * @see org.springrain.lottery.service.SoccerAllbetting
 */
public interface ISoccerAllbettingService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerAllbetting findSoccerAllbettingById(Object id) throws Exception;
	
	
	
}
