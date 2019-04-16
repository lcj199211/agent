package org.springrain.lottery.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.SoccerLive;
import org.springrain.lottery.entity.SoccerLiveSituation;
import org.springrain.lottery.service.ISoccerLiveService;
import org.springrain.lottery.service.ISoccerLiveSituationService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.alibaba.fastjson.JSON;
//实况
public class QuartzJob43 {
	@Resource
	private ISoccerLiveService liveService;
	@Resource
	private ISoccerLiveSituationService liveSituationService;
	@Resource
	private ICached  cached;
	@Value("${redis.ip}")
	private String redisIp;
	@Value("${redis.port}")
	private Integer redisPort;
	private static String[] imgLable = {"入球","乌龙","点球","黄牌","红牌","两黄变红","入球无效","换人","换入","换出"};
	// 日志记录器
	static Logger logger = LoggerFactory.getLogger(QuartzJob43.class);
	public void work() {
		try {
			Random random = new Random();
			List<SoccerLive> soccerLives = liveService.queryForList(new Finder("select * from soccer_live where starttime>date_format(date_add(now(),interval -1 day),:format) and starttime<date_format(date_add(now(),interval 1 day),:format)").setParam("format", "%Y-%m-%d"), SoccerLive.class);
			List<SoccerLiveSituation> situationsS = new ArrayList<SoccerLiveSituation>();
			Map<String,String> redis = new HashMap<String,String>();
			//List<SoccerLiveSituation> situationsU = new ArrayList<SoccerLiveSituation>();
			for (SoccerLive soccerLive : soccerLives) {
				String fid = soccerLive.getFid();
				if(StringUtils.isEmpty(fid)){
					continue;
				}
				List<SoccerLiveSituation> situations = liveSituationService.queryForList(new Finder("select * from soccer_live_situation where fid=:fid").setParam("fid", fid), SoccerLiveSituation.class);
				if(!situations.isEmpty()){
					continue;
				}
				String url = "http://live.500.com/detail.php?fid="+fid+"&r=1&t="+random.nextInt(10000);
				Document document = Jsoup.connect(url).ignoreContentType(true).timeout(60000).get();
				if (document!=null) {
					String[] homeTeammates = new String[11];
					String[] awayTeammates = new String[11];
					String[] homeSupl = new String[12];
					String[] awaySupl = new String[12];
					String[] total = new String[12];
					String realscore = ""; // 比分
					String halfscore = ""; // 半场
					Elements tables = document.getElementsByTag("table");
					try {
						Elements table0 = tables.get(0).getElementsByTag("tr").get(0).getElementsByTag("td").get(2).getElementsByTag("span"); // 获取比分
						realscore = table0.get(0).text().trim().replaceAll(" - ", ":");
						halfscore = table0.get(0).text().trim().replaceAll(" - ", ":").replaceAll("半场", "");
					} catch (Exception e) {
						logger.error("实况详情获取比分失败，异常：{}", e);
						logger.error("QuartzJob43 实况详情获取比分失败，异常：{}", e.getLocalizedMessage());
					}
					Elements table1 = tables.get(2).getElementsByTag("tr");//主首发
					for (int i = 0; i < table1.size(); i++) {
						homeTeammates[i] = table1.get(i).text();
						if("\u00a0".equals(homeTeammates[i])){
							homeTeammates[i] = null;
						}
					}
					Elements table2 = tables.get(3).getElementsByTag("tr");//主替补
					for (int i = 0; i < table2.size() && i < homeSupl.length; i++) {
						homeSupl[i] = table2.get(i).text();
						if("\u00a0".equals(homeSupl[i])){
							homeSupl[i] = null;
						}
					}
					Elements table3 = tables.get(5).getElementsByTag("tr");//客首发
					for (int i = 0; i < table3.size(); i++) {
						awayTeammates[i] = table3.get(i).text();
						if("\u00a0".equals(awayTeammates[i])){
							awayTeammates[i] = null;
						}
					}
					Elements table4 = tables.get(6).getElementsByTag("tr");//客替补
					for (int i = 0; i < table4.size() && i < awaySupl.length; i++) {
						awaySupl[i] = table4.get(i).text();
						if("\u00a0".equals(awaySupl[i])){
							awaySupl[i] = null;
						}
					}
					//技术统计
					Elements table5 = tables.get(8).getElementsByTag("tr");
					for (int i = 0; i < table5.size() && i < total.length; i++) {
						total[i] = table5.get(i).text().replaceAll("[\\D&&[^%]]+", ",");
					}
					//赛况
					List<SoccerLiveSituation> situationsR = new ArrayList<SoccerLiveSituation>();//redis
					Elements table6 = tables.get(4).getElementsByTag("tr");
					for (int i = 1; i < table6.size(); i++) {
						SoccerLiveSituation situation = new SoccerLiveSituation();
						Integer type = 0;
						situation.setFid(fid);
						Elements tds = table6.get(i).getElementsByTag("td");
						String time = tds.get(2).text().replaceAll("[\\D]+", "");
						if("".equals(time)){
							break;
						}
						situation.setTime(Integer.valueOf(time));
						String homeImg = tds.get(0).getElementsByTag("img").attr("src");
						if(homeImg != null && !"".equals(homeImg)){
							type = Integer.valueOf(homeImg.replaceAll("[\\D]+", ""));
							situation.setType(type);
							if(type>0&&type<=imgLable.length){
								situation.setIncident(imgLable[type-1]);
							}
						}
						String homeName = tds.get(1).text();
						if("\u00a0".equals(homeName)){
							homeName = null;
						}else{
							situation.setTeam("left");
							String[] name = homeName.split("\\)\\(");
							if(name.length == 2){
								situation.setName1(name[0].replaceAll("\\(", ""));
								situation.setName2(name[1].replaceAll("\\)", ""));
							}else{
								situation.setName1(homeName);
							}
							if(situations.contains(situation)){
//								situation.setId(situations.get(situations.indexOf(situation)).getId());
//								situationsU.add(situation);
							}else{
								if(System.currentTimeMillis()<soccerLive.getStarttime().getTime()+180*60*1000L){
									//正在比赛的保存到redis中
									situationsR.add(situation);
								}else{
									situationsS.add(situation);
								}
							}
							continue;
						}
						String awayName = tds.get(3).text();
						if("\u00a0".equals(awayName)){
							awayName = null;
						}else{
							situation.setTeam("right");
							String[] name = awayName.split("\\)\\(");
							if(name.length == 2){
								situation.setName1(name[0].replaceAll("\\(", ""));
								situation.setName2(name[1].replaceAll("\\)", ""));
							}else{
								situation.setName1(awayName);
							}
						}
						String awayImg = tds.get(4).getElementsByTag("img").attr("src");
						if(awayImg != null && !"".equals(awayImg)){
							type = Integer.valueOf(awayImg.replaceAll("[\\D]+", ""));
							situation.setType(type);
							if(type>0&&type<=imgLable.length){
								situation.setIncident(imgLable[type-1]);
							}
						}
						if(situations.contains(situation)){
//							situation.setId(situations.get(situations.indexOf(situation)).getId());
//							situationsU.add(situation);
						}else{
							if(System.currentTimeMillis()<soccerLive.getStarttime().getTime()+180*60*1000L){
								//正在比赛的保存到redis中
								situationsR.add(situation);
							}else{
								situationsS.add(situation);
							}
						}
					}
					if(!situationsR.isEmpty()){
						redis.put("SK"+fid, JSON.toJSONString(situationsR));
					}
					
					//主队
					soccerLive.setLeftpub1(homeTeammates[0]);
					soccerLive.setLeftpub2(homeTeammates[1]);
					soccerLive.setLeftpub3(homeTeammates[2]);
					soccerLive.setLeftpub4(homeTeammates[3]);
					soccerLive.setLeftpub5(homeTeammates[4]);
					soccerLive.setLeftpub6(homeTeammates[5]);
					soccerLive.setLeftpub7(homeTeammates[6]);
					soccerLive.setLeftpub8(homeTeammates[7]);
					soccerLive.setLeftpub9(homeTeammates[8]);
					soccerLive.setLeftpub10(homeTeammates[9]);
					soccerLive.setLeftpub11(homeTeammates[10]);
					soccerLive.setLeftres1(homeSupl[0]);
					soccerLive.setLeftres2(homeSupl[1]);
					soccerLive.setLeftres3(homeSupl[2]);
					soccerLive.setLeftres4(homeSupl[3]);
					soccerLive.setLeftres5(homeSupl[4]);
					soccerLive.setLeftres6(homeSupl[5]);
					soccerLive.setLeftres7(homeSupl[6]);
					soccerLive.setLeftres8(homeSupl[7]);
					soccerLive.setLeftres9(homeSupl[8]);
					soccerLive.setLeftres10(homeSupl[9]);
					soccerLive.setLeftres11(homeSupl[10]);
					soccerLive.setLeftres12(homeSupl[11]);
					//客队
					soccerLive.setRightpub1(awayTeammates[0]);
					soccerLive.setRightpub2(awayTeammates[1]);
					soccerLive.setLeftpub3(awayTeammates[2]);
					soccerLive.setRightpub4(awayTeammates[3]);
					soccerLive.setLeftpub5(awayTeammates[4]);
					soccerLive.setRightpub6(awayTeammates[5]);
					soccerLive.setLeftpub7(awayTeammates[6]);
					soccerLive.setRightpub8(awayTeammates[7]);
					soccerLive.setLeftpub9(awayTeammates[8]);
					soccerLive.setRightpub10(awayTeammates[9]);
					soccerLive.setRightpub11(awayTeammates[10]);
					soccerLive.setRightres1(awaySupl[0]);
					soccerLive.setRightres2(awaySupl[1]);
					soccerLive.setRightres3(awaySupl[2]);
					soccerLive.setRightres4(awaySupl[3]);
					soccerLive.setRightres5(awaySupl[4]);
					soccerLive.setRightres6(awaySupl[5]);
					soccerLive.setRightres7(awaySupl[6]);
					soccerLive.setRightres8(awaySupl[7]);
					soccerLive.setRightres9(awaySupl[8]);
					soccerLive.setRightres10(awaySupl[9]);
					soccerLive.setRightres11(awaySupl[10]);
					soccerLive.setRightres12(awaySupl[11]);
					//技术统计
					soccerLive.setShemeng(total[0]);
					soccerLive.setShezheng(total[1]);
					soccerLive.setFangui(total[2]);
					soccerLive.setJiaoqiu(total[3]);
					soccerLive.setYuewei(total[4]);
					soccerLive.setRed(total[5]);
					soccerLive.setYellow(total[6]);
					soccerLive.setJinggong(total[7]);
					soccerLive.setWeixiejinggong(total[8]);
					soccerLive.setRenyiqiu(total[9]);
					soccerLive.setJiuqiu(total[10]);
					soccerLive.setKongqiulv(total[11]);
					
					soccerLive.setState(null);
					soccerLive.setTime(null);
					if (StringUtils.isNotBlank(realscore) && StringUtils.isNotBlank(halfscore)) {
						soccerLive.setHalfscore(halfscore);
						soccerLive.setRealscore(realscore);
					} else {
						soccerLive.setHalfscore(null);
						soccerLive.setRealscore(null);
					}
					liveService.update(soccerLive, true);
				}
			}
			
			//提交实况缓存
			commit(redis);
			
			cached.updateCached("SoccerLive".getBytes("utf-8"), JSON.toJSONString(soccerLives).getBytes("utf-8"), 3*60L);
			//liveService.update(soccerLives,true);
			if(!situationsS.isEmpty()){
				liveSituationService.save(situationsS);
			}
//			if(!situationsU.isEmpty()){
//				liveSituationService.update(situationsU);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("QuartzJob43有异常");
		}
	}
	
	public static void main(String[] args) {
		SoccerLiveSituation a1 = new SoccerLiveSituation();
		a1.setFid("123456");
		a1.setTeam("home");
		a1.setType(2);
		a1.setTeam("123");
		a1.setName1("");
		SoccerLiveSituation a2 = new SoccerLiveSituation();
		a2.setId(1);
		a2.setFid("123456");
		a2.setTeam("home");
		a2.setType(2);
		a2.setTeam("123");
		a2.setName1("");
		System.out.println(a1.equals(a2)+"*"+a2.equals(a1));
	}
	
	public void commit(Map<String,String> map) {
        Jedis jr = null;
        try {
        	if(redisIp == null || redisPort == null){
        		return;
        	}
            jr = new Jedis(redisIp, redisPort);
            Pipeline pl = jr.pipelined();
            if(map != null && !map.isEmpty()){
				for (Entry<String,String> entry : map.entrySet()) {
					pl.set(entry.getKey(),entry.getValue());
					pl.expire(entry.getKey(), 180*60);
				}
				pl.sync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        	System.out.println("commit-----------QuartzJob43有异常");
        } finally {
            if(jr!=null){
                jr.disconnect();
            }
        }
	}
	
}
