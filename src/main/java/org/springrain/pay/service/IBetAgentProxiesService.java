package org.springrain.pay.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.pay.entity.BetAgentProxies;
import org.springrain.system.service.IBaseSpringrainService;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-26 10:15:07
 * @see org.springrain.lottery.service.BetAgentProxies
 */
public interface IBetAgentProxiesService extends IBaseSpringrainService  {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BetAgentProxies findIBetAgentProxiesById(Object id) throws Exception;
	
	/**
	 * 商户余额代付
	 * @return 
	 */
	public Map<String, Object> payfor(BetAgentProxies betAgentProxies);
	
	/**
	 * 商户余额代付查询
	 * @return 
	 */
	public Map<String, Object> payforQuery(String ordId);
	
	/**
	 * 商户余额查询
	 */
	public Double memberQuery();

}
