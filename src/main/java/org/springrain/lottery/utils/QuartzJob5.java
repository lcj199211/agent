package org.springrain.lottery.utils;

import java.util.List;

import javax.annotation.Resource;

import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.service.IBetAgentalipayService;
import org.springrain.lottery.service.IBetGoldService;

public class QuartzJob5 {
	@Resource
	private IBetAgentalipayService betAgentalipayService
	;

	private static String[] ctx = new String[] { "/applicationContext.xml" };

	public void work() throws Exception {
		betAgentalipayService.update(new Finder("update bet_agentalipay set daymoney=0 "));
	}

	

	
}
