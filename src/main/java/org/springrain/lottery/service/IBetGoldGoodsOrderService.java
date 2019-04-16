package org.springrain.lottery.service;

import java.util.List;

import org.springrain.frame.util.Page;
import org.springrain.lottery.entity.BetGoldGoodsOrder;
import org.springrain.system.service.IBaseSpringrainService;

public interface IBetGoldGoodsOrderService extends IBaseSpringrainService {
	
	/**
	 * @throws Exception 
	 * 
	
	* @Title: findBetGoldGoodsOrderById 
	
	* @Description: TODO  根据id查询数据
	
	*  @param id
	*  @return  
	
	* BetGoldDiscount    返回类型 
	
	* @throws
	 */
	BetGoldGoodsOrder findBetGoldGoodsOrderById(Integer id) throws Exception;
	/**
	 * @throws Exception 
	 * 
	
	* @Title: findGoodsOrderList 
	
	* @Description: TODO 订单列表查询
	
	*  @param betGoldGoodsOrder
	*  @return  
	
	* List<BetGoldGoodsOrder>    返回类型 
	
	* @throws
	 */
	List<BetGoldGoodsOrder> findGoodsOrderList(BetGoldGoodsOrder betGoldGoodsOrder, Page page) throws Exception;

}
