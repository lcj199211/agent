package org.springrain.lottery.service;

import org.springrain.lottery.entity.BjdcTeam;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:03:22
 * @see org.springrain.lottery.service.BjdcTeam
 */
public interface IBjdcTeamService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BjdcTeam findBjdcTeamById(Object id) throws Exception;
	
	
	
}
