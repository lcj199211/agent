package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BasketballAdjustodds;
import org.springrain.lottery.service.IBasketballAdjustoddsService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-07 13:59:14
 * @see org.springrain.lottery.service.impl.BasketballAdjustodds
 */
@Service("basketballAdjustoddsService")
public class BasketballAdjustoddsServiceImpl extends BaseSpringrainServiceImpl implements IBasketballAdjustoddsService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BasketballAdjustodds basketballAdjustodds=(BasketballAdjustodds) entity;
	       return super.save(basketballAdjustodds).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BasketballAdjustodds basketballAdjustodds=(BasketballAdjustodds) entity;
		 return super.saveorupdate(basketballAdjustodds).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BasketballAdjustodds basketballAdjustodds=(BasketballAdjustodds) entity;
	return super.update(basketballAdjustodds);
    }
    @Override
	public BasketballAdjustodds findBasketballAdjustoddsById(Object id) throws Exception{
	 return super.findById(id,BasketballAdjustodds.class);
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