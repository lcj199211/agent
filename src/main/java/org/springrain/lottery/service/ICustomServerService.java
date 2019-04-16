package org.springrain.lottery.service;

import org.springrain.lottery.entity.CustomServer;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-07-02 19:40:30
 * @see org.springrain.lottery.service.CustomServer
 */
public interface ICustomServerService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CustomServer findCustomServerById(Object id) throws Exception;
	
	
	
}
