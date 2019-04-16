package org.springrain.pay.service;

import org.springrain.pay.entity.BetBankType;
import org.springrain.system.service.IBaseSpringrainService;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-26 10:15:07
 * @see org.springrain.lottery.service.BetBankType
 */
public interface IBetBankTypeService extends IBaseSpringrainService  {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetBankType findIBetBankTypeById(Object id) throws Exception;

}
