package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetActivityCenter;
import org.springrain.lottery.entity.BetFirstrechargerebate;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.entity.BetMember;
import org.springrain.lottery.entity.BetScorerecord;
import org.springrain.lottery.entity.BetSinglerecharge;
import org.springrain.lottery.entity.BetSubordinateRebate;
import org.springrain.lottery.service.IBetScorerecordService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-18 14:23:24
 * @see org.springrain.lottery.service.impl.BetScorerecord
 */
@Service("betScorerecordService")
public class BetScorerecordServiceImpl extends BaseSpringrainServiceImpl implements IBetScorerecordService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      BetScorerecord betScorerecord=(BetScorerecord) entity;
	       return super.save(betScorerecord).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BetScorerecord betScorerecord=(BetScorerecord) entity;
		 return super.saveorupdate(betScorerecord).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BetScorerecord betScorerecord=(BetScorerecord) entity;
	return super.update(betScorerecord);
    }
    @Override
	public BetScorerecord findBetScorerecordById(Object id) throws Exception{
	 return super.findById(id,BetScorerecord.class);
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

	@Override
	@Async
	public void saveBetScorerecord(BetScorerecord betScorerecord) throws Exception {
		save(betScorerecord);
		
	}

	@Override
	public void saveBetSinglerecharge(BetSinglerecharge betSinglerecharge)
			throws Exception {
		// TODO Auto-generated method stub
		super.save(betSinglerecharge).toString();
	}

	@Override
	public void saveBetFirstrechargerebate(
			BetFirstrechargerebate betFirstrechargerebate) throws Exception {
		// TODO Auto-generated method stub
		super.save(betFirstrechargerebate).toString();
	}

}
