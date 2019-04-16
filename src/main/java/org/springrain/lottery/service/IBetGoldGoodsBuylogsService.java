package org.springrain.lottery.service;

import java.util.List;
import java.util.Map;

import org.springrain.lottery.entity.BetGoldGoodsBuylogs;
import org.springrain.system.service.IBaseSpringrainService;

public interface IBetGoldGoodsBuylogsService extends IBaseSpringrainService {
	
	/**
	 * @throws Exception 
	 * 
	
	* @Title: findBetGoldGoodsBuylogsById 
	
	* @Description: TODO  根据id查询数据
	
	*  @param id
	*  @return  
	
	* BetGoldDiscount    返回类型 
	
	* @throws
	 */
	BetGoldGoodsBuylogs findBetGoldGoodsBuylogsById(Integer id) throws Exception;
	/**
	 * @throws Exception 
	 * 
	
	* @Title: findGoodsBuylogsList 
	
	* @Description: TODO 订单列表查询
	
	*  @param betGoldGoodsBuylogs
	*  @return  
	
	* List<BetGoldGoodsBuylogs>    返回类型 
	
	* @throws
	 */
	List<Map<String, Object>> findGoodsBuylogsList(BetGoldGoodsBuylogs betGoldGoodsBuylogs) throws Exception;
	/**
	 * @throws Exception 
	 * 
	
	* @Title: deleteByOrderId 
	
	* @Description: TODO 删除 根据订单id删除商品
	
	*  @param orderid  
	
	* void    返回类型 
	
	* @throws
	 */
	void deleteByOrderId(String orderid) throws Exception;

}
