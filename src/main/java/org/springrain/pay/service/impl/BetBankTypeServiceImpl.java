package org.springrain.pay.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springrain.pay.entity.BetAgentProxies;
import org.springrain.pay.entity.BetBankType;
import org.springrain.pay.service.IBetAgentProxiesService;
import org.springrain.pay.service.IBetBankTypeService;
import org.springrain.pay.utils.HttpApi;
import org.springrain.pay.utils.JUtil;
import org.springrain.pay.utils.SecurityUtil;
import org.springrain.pay.utils.TdExpBasicFunctions;
import org.springrain.system.entity.BetAgentData;
import org.springrain.system.entity.BetAgentDrawcash;
import org.springrain.system.service.IBetAgentDrawcashService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-01-26 10:15:07
 * @see org.springrain.lottery.service.impl.BetAgentDrawcash
 */
@Service("betBankTypeService")
public class BetBankTypeServiceImpl extends BaseSpringrainServiceImpl implements IBetBankTypeService {
   
    @Override
	public String  save(Object entity ) throws Exception{
    	BetBankType betBankType=(BetBankType) entity;
	       return super.save(betBankType).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
    	BetBankType betBankType=(BetBankType) entity;
		 return super.saveorupdate(betBankType).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		BetBankType betBankType=(BetBankType) entity;
	     return super.update(betBankType);
    }
	
    @Override
	public BetBankType findIBetBankTypeById(Object id) throws Exception{
	 return super.findById(id,BetBankType.class);
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
