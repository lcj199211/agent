package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetPayforannotherDict;
import org.springrain.system.service.IBaseSpringrainService;

/**
 * 

* @ClassName: IBetPayforannotherDictService 

* @Description: TODO 代付的参数字典

* @author zwg 

* @date 2018年12月4日 上午11:33:40 

*
 */
public interface IBetPayforannotherDictService extends IBaseSpringrainService {
	/**
	 * @throws Exception 
	 * 
	
	* @Title: findBetPayforannotherDictById 
	
	* @Description: TODO 根据id查询
	
	*  @param id
	*  @return  
	
	* BetPayforannotherDict    返回类型 
	
	* @throws
	 */
	public BetPayforannotherDict findBetPayforannotherDictById(Object id) throws Exception;

}
