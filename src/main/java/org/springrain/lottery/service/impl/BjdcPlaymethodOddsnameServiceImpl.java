package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BjdcPlaymethodOddsname;
import org.springrain.lottery.service.IBjdcPlaymethodOddsnameService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:03:03
 * @see org.springrain.lottery.service.impl.BjdcPlaymethodOddsname
 */
@Service("bjdcPlaymethodOddsnameService")
public class BjdcPlaymethodOddsnameServiceImpl extends BaseSpringrainServiceImpl implements IBjdcPlaymethodOddsnameService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BjdcPlaymethodOddsname bjdcPlaymethodOddsname=(BjdcPlaymethodOddsname) entity;
	       return super.save(bjdcPlaymethodOddsname).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BjdcPlaymethodOddsname bjdcPlaymethodOddsname=(BjdcPlaymethodOddsname) entity;
		 return super.saveorupdate(bjdcPlaymethodOddsname).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BjdcPlaymethodOddsname bjdcPlaymethodOddsname=(BjdcPlaymethodOddsname) entity;
	return super.update(bjdcPlaymethodOddsname);
    }
    @Override
	public BjdcPlaymethodOddsname findBjdcPlaymethodOddsnameById(Object id) throws Exception{
	 return super.findById(id,BjdcPlaymethodOddsname.class);
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
