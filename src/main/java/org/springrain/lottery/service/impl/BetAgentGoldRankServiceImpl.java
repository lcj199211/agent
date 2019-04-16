package org.springrain.lottery.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.lottery.entity.BetAgentgold;
import org.springrain.lottery.entity.BetAgentgoldDaylyRank;
import org.springrain.lottery.service.IBetAgentGoldRankService;
import org.springrain.lottery.service.IBetAgentgoldService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

import java.io.File;
import java.util.List;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-01 09:41:49
 * @see org.springrain.lottery.service.impl.BetAgentGoldRankServiceImpl
 */
@Service("betAgentGoldRankService")
public class BetAgentGoldRankServiceImpl extends BaseSpringrainServiceImpl implements IBetAgentGoldRankService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BetAgentgoldDaylyRank betAgentgoldDaylyRank=(BetAgentgoldDaylyRank) entity;
	       return super.save(betAgentgoldDaylyRank).toString();
	}


	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		BetAgentgoldDaylyRank betAgentgoldDaylyRank=(BetAgentgoldDaylyRank) entity;
		return super.update(betAgentgoldDaylyRank);
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
