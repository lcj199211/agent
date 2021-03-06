package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGoldDiscount;
import org.springrain.system.service.IBaseSpringrainService;

public interface IBetGoldDiscountService extends IBaseSpringrainService {
	
	/**
	 * @throws Exception 
	 * 
	
	* @Title: findBetGodById 
	
	* @Description: TODO  根据id查询数据
	
	*  @param id
	*  @return  
	
	* BetGoldDiscount    返回类型 
	
	* @throws
	 */
	BetGoldDiscount findBetGoldDiscountById(Integer id) throws Exception;

}
