package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetSubordinaterebateDetail;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-05-08 09:58:06
 * @see org.springrain.lottery.service.BetSubordinaterebateDetail
 */
public interface IBetSubordinaterebateDetailService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetSubordinaterebateDetail findBetSubordinaterebateDetailById(Object id) throws Exception;
	
	
	
}
