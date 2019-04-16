package org.springrain.lottery.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetGoldGoods;
import org.springrain.lottery.service.IBetGoldGoodsService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

@Service
public class BetGoldGoodsServiceImpl extends BaseSpringrainServiceImpl implements IBetGoldGoodsService{

	@Override
	public BetGoldGoods findBetGoldGoodsById(Integer id) throws Exception {
		return super.findById(id,BetGoldGoods.class);
	}

}
