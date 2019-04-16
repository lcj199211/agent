package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetAgentRechargeRebate;
import org.springrain.lottery.service.IBetAgentRechargeRebateService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-06-23 15:57:45
 * @see org.springrain.lottery.service.impl.BetAgentRechargeRebate
 */
@Service("betAgentRechargeRebateService")
public class BetAgentRechargeRebateServiceImpl extends BaseSpringrainServiceImpl implements IBetAgentRechargeRebateService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BetAgentRechargeRebate betAgentRechargeRebate=(BetAgentRechargeRebate) entity;
	       return super.save(betAgentRechargeRebate).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BetAgentRechargeRebate betAgentRechargeRebate=(BetAgentRechargeRebate) entity;
		 return super.saveorupdate(betAgentRechargeRebate).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BetAgentRechargeRebate betAgentRechargeRebate=(BetAgentRechargeRebate) entity;
	return super.update(betAgentRechargeRebate);
    }
    @Override
	public BetAgentRechargeRebate findBetAgentRechargeRebateById(Object id) throws Exception{
	 return super.findById(id,BetAgentRechargeRebate.class);
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

}
