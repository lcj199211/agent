package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetChannelVersionControl;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 版本控制栏目 - service
 * @copyright {@link weicms.net}
 * @author qiang
 * @version  2019-03-13 18:25:56
 * @see org.springrain.news.service.BetChannelVersionControl
 */
public interface IBetChannelVersionControlService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetChannelVersionControl findBetChannelVersionControlById(Object id) throws Exception;
	
	
	
}
