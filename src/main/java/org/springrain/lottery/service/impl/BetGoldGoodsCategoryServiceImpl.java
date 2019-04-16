package org.springrain.lottery.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetGoldGoods;
import org.springrain.lottery.service.IBetGoldGoodsCategoryService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

@Service
public class BetGoldGoodsCategoryServiceImpl extends BaseSpringrainServiceImpl implements IBetGoldGoodsCategoryService{

	@Override
	public BetGoldGoods findBetGoldGoodsById(Integer id) throws Exception {
		return super.findById(id,BetGoldGoods.class);
	}

}
