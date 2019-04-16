package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetGodMember;
import org.springrain.system.service.IBaseSpringrainService;

public interface IBetGodMemberService extends IBaseSpringrainService {
	/**
	 * 
	
	* @Title: findBetGodById 
	
	* @Description: TODO 根据id查询爆红大神
	
	*  @param id
	*  @return  
	
	* BetGodMember    返回类型 
	
	* @throws
	 */
	BetGodMember findBetGodById(Object id) throws Exception;

}
