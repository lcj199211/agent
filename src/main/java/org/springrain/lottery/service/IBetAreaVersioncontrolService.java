package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetAreaVersioncontrol;
import org.springrain.system.service.IBaseSpringrainService;

/**
 * 
 * 地域栏目控制
 * 
 * @version 2019-04-02 19:18:09
 */
public interface IBetAreaVersioncontrolService extends IBaseSpringrainService {

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAreaVersioncontrol findBetAreaVersioncontrolById(Object id) throws Exception;

}
