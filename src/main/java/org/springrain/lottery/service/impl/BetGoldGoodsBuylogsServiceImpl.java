package org.springrain.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetGoldGoodsBuylogs;
import org.springrain.lottery.service.IBetGoldGoodsBuylogsService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

@Service
public class BetGoldGoodsBuylogsServiceImpl extends BaseSpringrainServiceImpl implements IBetGoldGoodsBuylogsService{


	@Override
	public BetGoldGoodsBuylogs findBetGoldGoodsBuylogsById(Integer id) throws Exception {
		return super.findById(id, BetGoldGoodsBuylogs.class);
	}

	@Override
	public List<Map<String, Object>> findGoodsBuylogsList(BetGoldGoodsBuylogs betGoldGoodsBuylogs) throws Exception {
		
		Finder finder = new Finder("select b.goodsid,b.goodsnum,b.totalmoney,a.totalmoney summoney,b.goodsname " + 
				"		,c.image,b.orderid,d.account,d.nickname from bet_gold_goods_order a " + 
				"	left join	bet_gold_goods_buylogs b  on a.orderid=b.orderid  " + 
				"		left join bet_gold_goods c on b.goodsid=c.id " + 
				"		left join bet_member d on a.memberid=d.id2 ");
		if(!StringUtils.isEmpty(betGoldGoodsBuylogs.getOrderid())) {
			finder.append(" where a.orderid=:orderid");
			finder.setParam("orderid", betGoldGoodsBuylogs.getOrderid());
		}
		return super.queryForList(finder);
	}

	@Override
	public void deleteByOrderId(String orderid) throws Exception {
		Finder finder = new Finder("select * from bet_gold_goods where orderid=:orderid ");
		super.update(finder);
	}

}
