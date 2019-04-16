package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.LotteryPlaymethod;
import org.springrain.lottery.service.ILotteryPlaymethodService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 玩法接口实现类
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:35:18
 * @see org.springrain.lottery.service.impl.LotteryPlaymethod
 */
@Service("lotteryPlaymethodService")
public class LotteryPlaymethodServiceImpl extends BaseSpringrainServiceImpl implements ILotteryPlaymethodService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      LotteryPlaymethod lotteryPlaymethod=(LotteryPlaymethod) entity;
	       return super.save(lotteryPlaymethod).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      LotteryPlaymethod lotteryPlaymethod=(LotteryPlaymethod) entity;
		 return super.saveorupdate(lotteryPlaymethod).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 LotteryPlaymethod lotteryPlaymethod=(LotteryPlaymethod) entity;
	return super.update(lotteryPlaymethod);
    }
    @Override
	public LotteryPlaymethod findLotteryPlaymethodById(Object id) throws Exception{
	 return super.findById(id,LotteryPlaymethod.class);
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
