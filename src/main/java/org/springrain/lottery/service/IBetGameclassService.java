package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGameclass;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-09 11:41:19
 * @see org.springrain.lottery.service.BetGameclass
 */
public interface IBetGameclassService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetGameclass findBetGameclassById(Object id) throws Exception;
	
	
	
}
