package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGoldGoods;
import org.springrain.system.service.IBaseSpringrainService;

public interface IBetGoldGoodsService extends IBaseSpringrainService {
	
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
	BetGoldGoods findBetGoldGoodsById(Integer id) throws Exception;

}
