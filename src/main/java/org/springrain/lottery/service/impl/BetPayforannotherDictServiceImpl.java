package org.springrain.lottery.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetPayforannotherDict;
import org.springrain.lottery.service.IBetPayforannotherDictService;
import org.springrain.system.service.BaseSpringrainServiceImpl;

@Service
public class BetPayforannotherDictServiceImpl extends BaseSpringrainServiceImpl implements IBetPayforannotherDictService{

	@Override
	public BetPayforannotherDict findBetPayforannotherDictById(Object id) throws Exception {
		 return super.findById(id,BetPayforannotherDict.class);
	}

}
