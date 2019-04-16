package org.springrain.lottery.service;

import org.springrain.lottery.entity.SoccerScheme;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-18 09:08:21
 * @see org.springrain.lottery.service.SoccerScheme
 */
public interface ISoccerSchemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SoccerScheme findSoccerSchemeById(Object id) throws Exception;
	
	
	
}
