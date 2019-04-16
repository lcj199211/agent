package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetAgentPaymentCode;
import org.springrain.lottery.service.IBetAgentPaymentCodeService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2019-03-19 11:28:46
 * @see org.springrain.news.service.impl.BetAgentPaymentCode
 */
@Service("betAgentPaymentCodeService")
public class BetAgentPaymentCodeServiceImpl extends BaseSpringrainServiceImpl implements IBetAgentPaymentCodeService {

	@Override
	public String save(Object entity) throws Exception {
		BetAgentPaymentCode betAgentPaymentCode = (BetAgentPaymentCode) entity;
		betAgentPaymentCode.setId(UUID.randomUUID().toString());
		betAgentPaymentCode.setCreatetime(new Date());
		return super.save(betAgentPaymentCode).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		BetAgentPaymentCode betAgentPaymentCode = (BetAgentPaymentCode) entity;
		if (StringUtils.isNotBlank(betAgentPaymentCode.getId())) {
			betAgentPaymentCode.setModifytime(new Date());
			return super.update(betAgentPaymentCode, true).toString();
		} else {
			return this.save(entity);
		}
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		BetAgentPaymentCode betAgentPaymentCode = (BetAgentPaymentCode) entity;
		return super.update(betAgentPaymentCode);
	}

	@Override
	public BetAgentPaymentCode findBetAgentPaymentCodeById(Object id) throws Exception {
		return super.findById(id, BetAgentPaymentCode.class);
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
