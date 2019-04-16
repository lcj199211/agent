package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcLiveSituation;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-12 16:08:47
 * @see org.springrain.lottery.service.BjdcLiveSituation
 */
public interface IBjdcLiveSituationService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcLiveSituation findBjdcLiveSituationById(Object id) throws Exception;
	
	
	
}
