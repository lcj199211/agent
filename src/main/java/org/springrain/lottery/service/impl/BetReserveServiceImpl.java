package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetReserve;
import org.springrain.lottery.service.IBetReserveService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-09 16:22:01
 * @see org.springrain.lottery.service.impl.BetReserve
 */
@Service("betReserveService")
public class BetReserveServiceImpl extends BaseSpringrainServiceImpl implements IBetReserveService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BetReserve betReserve=(BetReserve) entity;
	       return super.save(betReserve).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BetReserve betReserve=(BetReserve) entity;
		 return super.saveorupdate(betReserve).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BetReserve betReserve=(BetReserve) entity;
	return super.update(betReserve);
    }
    @Override
	public BetReserve findBetReserveById(Object id) throws Exception{
	 return super.findById(id,BetReserve.class);
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
