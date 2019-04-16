package org.springrain.lottery.utils;

import com.google.gson.Gson;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentgoldDaylyRank;
import org.springrain.lottery.service.IBetAgentGoldRankService;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAgentgoldService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class QuartzJobToDaylyRankJob {

	@Resource
	private IBetAgentgoldService betAgentgoldService;
	@Resource
	private IBetAgentService betAgentService;
	@Resource
	private IBetAgentGoldRankService betAgentGoldRankService;

	/**
	 * 每天凌晨查一遍昨日充值记录
	*/
	public void work() {
		try {
			Calendar cal= Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			Date date=cal.getTime();
			SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
			String yestoday=sp.format(date);//获取昨天日期
			//循环所有代理，一个一个查询该代理及下属的充值
			List<BetAgent> betAgents = betAgentService.queryForList(new Finder("select *from bet_agent "), BetAgent.class);

			for (int i = 0; i < betAgents.size(); i++) {
				BetAgent betAgent = betAgents.get(i);
				Map<String ,Object> rankmap = betAgentgoldService.queryForObject(new Finder("" +
						"select   0 as id, agent.agentid agentid,agent.account account,agent.nickname nickname,agent.parentid parentid,agent.parentids parentids,agent.subordinate subordinate,agent.membernum membernum,agent.active active,(select sum(gold.money) from bet_gold gold where gold.rechargetime like :yestoday  and gold.state=2 and (gold.agentid= :agentid or gold.agentparentids like :agentparentids)) totalmoney from bet_agent agent where agent.agentid= :agentid  ").setParam("yestoday", "%" + yestoday+ "%").setParam("agentid", betAgent.getAgentid()).setParam("agentparentids", "%," + betAgent.getAgentid() + ",%"));
				if (rankmap!=null&&rankmap.get("agentid") != null) {
					Gson gson = new Gson();
					BetAgentgoldDaylyRank rank=gson.fromJson(gson.toJson(rankmap),BetAgentgoldDaylyRank.class);
					List<BetAgentgoldDaylyRank> r = betAgentgoldService.queryForList(new Finder("select id from bet_agentgold_daylyrank where   creatTime like :yestoday  and  agentid = :agentid").setParam("yestoday", "%" + yestoday + "%").setParam("agentid", rank.getAgentid()), BetAgentgoldDaylyRank.class);
					if (r.size()==0) {
						rank.setCreatTime(date);
						betAgentGoldRankService.save(rank);
					} else if(r.size()==1){
						rank.setId(r.get(0).getId());
						betAgentGoldRankService.update(rank, true);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("daylyRankJob代理充值报表异常");
		}
	}
}
