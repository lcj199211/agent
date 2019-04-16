package org.springrain.lottery.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.lottery.entity.BetGodMember;
import org.springrain.lottery.service.IBetGodMemberService;
import org.springrain.system.service.BaseSpringrainServiceImpl;
@Service
public class BetGodMemberServiceImpl extends BaseSpringrainServiceImpl implements IBetGodMemberService{

	@Override
	public BetGodMember findBetGodById(Object id) throws Exception{
	 return super.findById(id,BetGodMember.class);
	}

}
