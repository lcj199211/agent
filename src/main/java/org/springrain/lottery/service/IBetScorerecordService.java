package org.springrain.lottery.service;

import org.springrain.lottery.entity.BetFirstrechargerebate;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetSinglerecharge;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-18 14:23:24
 * @see org.springrain.lottery.service.BetScorerecord
 */
public interface IBetScorerecordService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetScorerecord findBetScorerecordById(Object id) throws Exception;
	
	public void saveBetScorerecord (BetScorerecord betScorerecord) throws Exception ;
	
	public void saveBetSinglerecharge (BetSinglerecharge betSinglerecharge) throws Exception;
	
	public void saveBetFirstrechargerebate (BetFirstrechargerebate betFirstrechargerebate) throws Exception;
	
	
	
}
