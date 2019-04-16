package org.springrain.lottery.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.lottery.entity.BetGoldGoodsOrder;
import org.springrain.lottery.service.IBetGoldGoodsOrderService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

@Service
public class BetGoldGoodsOrderServiceImpl extends BaseSpringrainServiceImpl implements IBetGoldGoodsOrderService{


	@Override
	public BetGoldGoodsOrder findBetGoldGoodsOrderById(Integer id) throws Exception {
		return super.findById(id, BetGoldGoodsOrder.class);
	}

	@Override
	public List<BetGoldGoodsOrder> findGoodsOrderList(BetGoldGoodsOrder betGoldGoodsOrder, Page page) throws Exception {
		Finder finder = new Finder("select * from bet_gold_goods_order where 1=1 ");
		if(!StringUtils.isEmpty(betGoldGoodsOrder.getOrderid())) {
			finder.append(" and orderid=:orderid");
			finder.setParam("orderid", betGoldGoodsOrder.getOrderid());
		}
		return super.queryForList(finder, BetGoldGoodsOrder.class,page);
	}

}
