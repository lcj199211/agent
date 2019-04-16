package org.springrain.lottery.utils;

import java.util.List;

import javax.annotation.Resource;

import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetBettingService;
import org.springrain.lottery.service.IBetMemberService;

public class QuartzJob {
	@Resource
	private IBetAgentService agentService;
	@Resource
	private IBetBettingService betBettingService;
	@Resource
	private IBetMemberService betMemberService;

	public void work() {
		try {
			List<BetAgent> agents = agentService.queryForList(new Finder(
					"select * from bet_agent"), BetAgent.class);
			for (BetAgent betAgent : agents) {
				String sx = betBettingService
						.queryForObject(
								new Finder(
										"select sum(bettingmoney) from bet_betting where state=1 and (agentid=:agentid or agentparentids like :aid ) ")
										.setParam("agentid",
												betAgent.getAgentid())
										.setParam(
												"aid",
												"%," + betAgent.getAgentid()
														+ ",%"), String.class);
				String ty2 = betBettingService
						.queryForObject(
								new Finder(
										"select sum(ty) from bet_betting where state=1 and (agentid=:agentid or agentparentids like :aid ) ")
										.setParam("agentid",
												betAgent.getAgentid())
										.setParam(
												"aid",
												"%," + betAgent.getAgentid()
														+ ",%"), String.class);
				String memberNum = betMemberService
						.queryForObject(
								new Finder(
										"select count(*) from bet_member where agentid=:agentid ")
										.setParam("agentid",
												betAgent.getAgentid()),
								String.class);

				if (sx != null) {
					agentService
							.update(new Finder(
									"update bet_agent set sx=:sx where agentid=:agentid")
									.setParam("sx", sx).setParam("agentid",
											betAgent.getAgentid()));
				}
				if (ty2 != null) {
					agentService
							.update(new Finder(
									"update bet_agent set ty2=:ty2 where agentid=:agentid")
									.setParam("ty2", ty2).setParam("agentid",
											betAgent.getAgentid()));
				}
				if (!memberNum.isEmpty()) {
					agentService
							.update(new Finder(
									"update bet_agent set membernum=:membernum where agentid=:agentid")
									.setParam("membernum", memberNum).setParam(
											"agentid", betAgent.getAgentid()));
				}
			}
		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
			System.out.println("QuartzJob有异常");
		}
	}
}
