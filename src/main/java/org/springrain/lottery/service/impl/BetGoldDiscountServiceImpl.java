package org.springrain.lottery.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetGoldDiscount;
import org.springrain.lottery.service.IBetGoldDiscountService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

@Service
public class BetGoldDiscountServiceImpl extends BaseSpringrainServiceImpl implements IBetGoldDiscountService{

	@Override
	public BetGoldDiscount findBetGoldDiscountById(Integer id) throws Exception {
		return super.findById(id,BetGoldDiscount.class);
	}

}
