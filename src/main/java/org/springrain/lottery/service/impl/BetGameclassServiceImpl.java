package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetGameclass;
import org.springrain.lottery.service.IBetGameclassService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-09 11:41:19
 * @see org.springrain.lottery.service.impl.BetGameclass
 */
@Service("betGameclassService")
public class BetGameclassServiceImpl extends BaseSpringrainServiceImpl implements IBetGameclassService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BetGameclass betGameclass=(BetGameclass) entity;
	       return super.save(betGameclass).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BetGameclass betGameclass=(BetGameclass) entity;
		 return super.saveorupdate(betGameclass).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BetGameclass betGameclass=(BetGameclass) entity;
	return super.update(betGameclass);
    }
    @Override
	public BetGameclass findBetGameclassById(Object id) throws Exception{
	 return super.findById(id,BetGameclass.class);
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
