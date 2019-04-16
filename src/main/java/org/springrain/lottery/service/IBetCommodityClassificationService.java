package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetCommodityClassification;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-11 11:17:22
 * @see org.springrain.lottery.service.BetCommodityClassification
 */
public interface IBetCommodityClassificationService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetCommodityClassification findBetCommodityClassificationById(Object id) throws Exception;
	
	
	
}
