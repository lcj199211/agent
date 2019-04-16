package org.springrain.lottery.utils;

import java.util.List;

import javax.annotation.Resource;

import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetGame;
import org.springrain.lottery.entity.BetGameAgent;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetGameAgentService;
import org.springrain.lottery.service.IBetGameService;

public class QuartzJobGame {
	@Resource
	private IBetGameService betGameService;
	@Resource
	private IBetGameAgentService betGameAgentService;
	@Resource
	private IBetAgentService betAgentService;
	//初始数据
	public void work() throws Exception {
		try {
//			List<BetGame> betGames = betGameService.queryForList(new Finder("select * from bet_game"), BetGame.class);
//			List<String> agentids = betAgentService.queryForList(new Finder("select agentid from bet_agent where parentid =:parentid ").setParam("parentid", "A101"), String.class);
//			for (String agentid : agentids) {
//				for (BetGame betGame : betGames) {
//					if(betGame.getCompany().equals(agentid)){
//						break;
//					}
//					BetGame game = new BetGame();
//					game.setId(null);
//					game.setCompany(agentid);
//					game.setAllowupdate(betGame.getAllowupdate());
//					game.setGamename(betGame.getGamename());
//					game.setImg(betGame.getImg());
//					game.setRemark(betGame.getRemark());
//					game.setState(betGame.getState());
//					game.setTitle(betGame.getTitle());
//					betGameService.save(game);
//				}
//			}
//			System.out.println(".....betGame...end.....");
//			List<BetAgent> subagentids = betGameAgentService.queryForList(new Finder("select agentid,parentids from bet_agent where parentid!=:parentid and parentid is not null and parentid!=:empty").setParam("parentid", "A101").setParam("empty", ""), BetAgent.class);
//			for (BetAgent betAgent : subagentids) {
//				String[] split = betAgent.getParentids().split(",");
//				String company = split[2];
//				List<BetGame> games = betGameService.queryForList(new Finder("select * from bet_game where company=:company ").setParam("company", company), BetGame.class);
//				for (BetGame betGame : games) {
//					BetGameAgent betGameAgent = new BetGameAgent();
//					betGameAgent.setAgentid(betAgent.getAgentid());
//					betGameAgent.setCompany(company);
//					betGameAgent.setId(null);
//					betGameAgent.setGamename(betGame.getGamename());
//					betGameAgent.setImg(betGame.getImg());
//					betGameAgent.setRemark(betGame.getRemark());
//					betGameAgent.setState(betGame.getState());
//					betGameAgent.setTitle(betGame.getTitle());
//					betGameAgentService.save(betGameAgent);
//				}
//			}
//			System.out.println(".....end.....");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("QuartzJobGame有异常");
		}
	}
}
