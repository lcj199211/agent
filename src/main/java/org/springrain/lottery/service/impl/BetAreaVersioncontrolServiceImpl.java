package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.lottery.entity.BetAreaVersioncontrol;
import org.springrain.lottery.service.IBetAreaVersioncontrolService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

/**
 * 地域栏目控制
 * 
 * @version 2019-04-02 19:18:09
 */
@Service("betAreaVersioncontrolService")
public class BetAreaVersioncontrolServiceImpl extends BaseSpringrainServiceImpl
		implements IBetAreaVersioncontrolService {

	@Override
	public String save(Object entity) throws Exception {
		BetAreaVersioncontrol betAreaVersioncontrol = (BetAreaVersioncontrol) entity;
		return super.save(betAreaVersioncontrol).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		BetAreaVersioncontrol betAreaVersioncontrol = (BetAreaVersioncontrol) entity;
		return super.saveorupdate(betAreaVersioncontrol).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		BetAreaVersioncontrol betAreaVersioncontrol = (BetAreaVersioncontrol) entity;
		return super.update(betAreaVersioncontrol);
	}

	@Override
	public BetAreaVersioncontrol findBetAreaVersioncontrolById(Object id) throws Exception {
		return super.findById(id, BetAreaVersioncontrol.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder
	 *            为空则只查询 clazz表
	 * @param ftlurl
	 *            类表的模版宏
	 * @param page
	 *            分页对象
	 * @param clazz
	 *            要查询的对象
	 * @param o
	 *            querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl, Page page, Class<T> clazz, Object o)
			throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, o);
	}

}
