package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetLotterychannelaccess;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-28 13:48:31
 * @see org.springrain.lottery.service.BetLotterychannelaccess
 */
public interface IBetLotterychannelaccessService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetLotterychannelaccess findBetLotterychannelaccessById(Object id) throws Exception;
	
	
	
}
