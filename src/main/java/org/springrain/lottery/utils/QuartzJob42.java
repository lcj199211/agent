package org.springrain.lottery.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.cached.ICached;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.SoccerLeagueArrange;
import org.springrain.lottery.entity.SoccerLive;
import org.springrain.lottery.service.ISoccerLeagueArrangeService;
import org.springrain.lottery.service.ISoccerLiveService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

//实况抓取
public class QuartzJob42 {
    @Resource
    private ISoccerLiveService liveService;
    @Resource
    private ISoccerLeagueArrangeService arrangeService;
    @Resource
    private ICached cached;
    // 日志记录器
    static Logger logger = LoggerFactory.getLogger(QuartzJob42.class);

    public void work() {
        try {
            System.out.println("实况QuartzJob42抓取---------------start");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format1 = new SimpleDateFormat("MM-dd HH:mm");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date[] dates = new Date[3];//昨日今日明日
            //Date[] dates = new Date[2];//昨日今日
            dates[1] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            dates[0] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            dates[2] = calendar.getTime();
            String[] ds = {format.format(dates[0]), format.format(dates[1]), format.format(dates[2])};
            //String[] ds = {format.format(dates[1]),format.format(dates[2])};
            Random random = new Random();
            for (int i = 0; i < ds.length; i++) {
                System.out.println("ds[i]---------------" + ds[i]);
                String url = "http://live.500.com/?e=" + ds[i] + "&t=" + random.nextInt(10000);
                System.out.println("url:--------------" + url);
                Document document1 = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                        .ignoreContentType(true).timeout(5000).get();
                Document document2 = null;
                try {
                    String url2 = "http://live.500.com/static/info/bifen/xml/livedata/jczq/" + ds[i].replaceAll("-", "") + "Full.txt?_=" + System.currentTimeMillis() + "&t=" + random.nextInt(10000);
                    System.out.println("url2:--------------" + url2);
                    document2 = Jsoup.connect(url2)
                            .referrer("http://live.500.com/?e=" + ds[i])
                            .ignoreContentType(true)
                            .timeout(5000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0").get();
                } catch (Exception e) {
                    continue;
                }
                List<SoccerLive> saveList = new ArrayList<SoccerLive>();
                List<SoccerLive> updateList = new ArrayList<SoccerLive>();
                Map<String, Date> open = new HashMap<String, Date>();
                Map<String, Integer> statusMap = new HashMap<String, Integer>(); // 状态为0、为开场、1、上半场、2、中场休息、3、下半场、4、完场、5、取消、6、改期、7、腰斩、8、中断、9、待定
                Map<String, String> scoreMap = new HashMap<String, String>(); // 比分
                Map<String, String> halfscoreMap = new HashMap<String, String>(); // 半场
                if (document2 != null) {//对于未开赛是开赛时间，完赛是结束时间，进行则是上（下）半场真实开场时间
                    String json = document2.text();
                    JSONArray ja = JSON.parseArray(json);
                    for (int j = 0; j < ja.size(); j++) {
                        JSONArray ja1 = ja.getJSONArray(j);
                        open.put(ja1.getString(0), format2.parse(ja1.getString(4))); // 状态
                        statusMap.put(ja1.getString(0), ja1.getInteger(1));
                        String[] scoreLeft = ja1.getString(2).split(","); // 比分
                        String[] scoreRight = ja1.getString(3).split(",");
                        String scoreStr = scoreLeft[0] + ":" + scoreRight[0]; // 总比分
                        scoreMap.put(ja1.getString(0), scoreStr);
                        String halfscoreStr = scoreLeft[1] + ":" + scoreRight[1]; // 半场
                        halfscoreMap.put(ja1.getString(0), halfscoreStr);
                    }
                }
                if (document1 != null) {
                    Element table_match = document1.getElementById("table_match");
                    Element tbody = table_match.getElementsByTag("tbody").get(0);
                    Elements trs = tbody.getElementsByTag("tr");
                    List<String> fids = new ArrayList<String>();
                    for (int j = 0; j < trs.size(); j++) {
                        Element tr = trs.get(j);
                        String parentid = tr.attr("parentid");
                        if (StringUtils.isNotEmpty(parentid)) {
                            continue;
                        }
                        String fid = tr.attr("fid");
                        fids.add(fid);
                        String[] gy = tr.attr("gy").split(",");
                        String leagueName = gy[0];
                        String homeTeam = gy[1];
                        String awayTeam = gy[2];
//						String r = tr.attr("r");
                        Elements tds = tr.getElementsByTag("td");
                        Date starttime = null;
                        String realscore = "";
                        String halfscore = "";
                        Integer state = 0;
                        String leftteamid2 = "";
                        String rightteamid2 = "";
                        String num = "";
                        String time = "";
                        if (tds.size() == 13) {
                            //序号，球队id
                            num = tds.get(0).text().replaceAll("(\\D)+", "");
                            //System.out.println("num---------------"+num);
                            String homeTeamHref = tds.get(5).getElementsByTag("a").get(0).attr("href");
                            leftteamid2 = homeTeamHref.split("/")[4];
                            String awayTeamHref = tds.get(7).getElementsByTag("a").get(0).attr("href");
                            rightteamid2 = awayTeamHref.split("/")[4];

                            realscore = tds.get(6).text().replaceAll("(\\D)+", ":");
                            halfscore = tds.get(8).text().replaceAll("(\\D)+", ":");

                            //理论开场时间
                            starttime = format1.parse(tds.get(3).text());
                            int year = calendar.get(Calendar.YEAR);
                            calendar.setTime(starttime);
                            calendar.set(Calendar.YEAR, year);
                            starttime = calendar.getTime();
                            String stateStr = tds.get(4).text();
                            Map<String, Object> map = this.getStatus(statusMap, fid, open, halfscore, stateStr, halfscoreMap, scoreMap);
                            state = (Integer) map.get("state");
                            time = (String) map.get("time");
                            //及时比分
                            realscore = (String) map.get("scoreStr");
                            halfscore = (String) map.get("halfscoreStr");
                        }
                        SoccerLive soccerLive = new SoccerLive();
                        soccerLive.setFid(fid);
                        soccerLive.setLeaguename(leagueName);
                        soccerLive.setLeftteam(homeTeam);
                        soccerLive.setLeftteamid2(leftteamid2);
                        soccerLive.setRightteam(awayTeam);
                        soccerLive.setRightteamid2(rightteamid2);
                        soccerLive.setStarttime(starttime);
                        soccerLive.setRealscore(realscore);
                        soccerLive.setHalfscore(halfscore);
                        soccerLive.setState(state);
                        soccerLive.setTime(time);
                        SoccerLeagueArrange arrange = arrangeService.queryForObject(new Finder("select mid,zid from soccer_league_arrange where num=:num and starttime=:date ").setParam("num", num).setParam("date", starttime), SoccerLeagueArrange.class);
                        if (null != arrange) {
                            soccerLive.setMid(arrange.getMid());
                            soccerLive.setZid(arrange.getZid());
                            SoccerLive soccerLive2 = liveService.queryForObject(new Finder("select * from soccer_live where mid=:mid ").setParam("mid", arrange.getMid()), SoccerLive.class);
                            if (null != soccerLive2) {
                                //System.out.println("soccerLive2.getId()---------------"+soccerLive2.getId()+" mid---------------"+arrange.getMid());
                                //System.out.println("state="+state+" realscore="+realscore+" halfscore="+halfscore);
                                if (soccerLive2.getState() != null && soccerLive2.getState() != 1) {
                                    soccerLive.setId(soccerLive2.getId());
                                    updateList.add(soccerLive);
                                    liveService.update(soccerLive);
                                }
                                //System.out.println("reluteS="+reluteS+" mid---------------"+arrange.getMid());

                                //Integer reluteS=liveService.update(new Finder("update soccer_live set realscore=:realscore,halfscore=:halfscore,state=:state,time=:time where id=:id").setParam("realscore", realscore).setParam("halfscore", halfscore).setParam("state", state).setParam("time", time).setParam("id",soccerLive2.getId()));
                                //System.out.println("reluteS="+reluteS+" mid---------------"+arrange.getMid());
                            } else {
                                soccerLive.setShemeng("0,0");
                                soccerLive.setShezheng("0,0");
                                soccerLive.setFangui("0,0");
                                soccerLive.setJiaoqiu("0,0");
                                soccerLive.setYuewei("0,0");
                                soccerLive.setRed("0,0");
                                soccerLive.setYellow("0,0");
                                soccerLive.setJinggong("0,0");
                                soccerLive.setWeixiejinggong("0,0");
                                soccerLive.setRenyiqiu("0,0");
                                soccerLive.setJiuqiu("0,0");
                                soccerLive.setKongqiulv("0,0");
                                saveList.add(soccerLive);
                            }
                        }
//						System.out.println("fid:"+fid+"leagueName:"+leagueName+"homeTeam:"+homeTeam+"awayTeam:"+awayTeam+"r:"+r+format.format(starttime)+realscore);
                    }
                    //Document document2 = Jsoup.connect("http://live.500.com/detail.php?fid="+fids.get(0)+"&r=1"+"&t="+new Date().getTime()).ignoreContentType(true).timeout(5000).get();

//					System.out.println("--------------End--------------");
                }
                if (!saveList.isEmpty()) {
                    liveService.save(saveList);
                }
                if (!updateList.isEmpty()) {
                    cached.updateCached("SoccerLive".getBytes("utf-8"), JSON.toJSONString(updateList).getBytes("utf-8"), 3 * 60L);
                    //liveService.update(updateList);
                }
            }

            System.out.println("实况QuartzJob42抓取---------------end");
        } catch (Exception e) {
            System.out.println("实况QuartzJob42抓取有异常");
            e.printStackTrace();
        }
    }

    /**
     * 获取实况状态与时间，因为目前只抓取到0,1,2,3,4,9四种状态，可能存在其他状态，如果如此使用旧方法
     *
     * @param statusMap 抓取实况的状态 0、未开场，1、上半场，2、中场休息，3、下半场，4、已完场，9、待定
     * @param fid       实况id
     * @param open      开场时间
     * @param halfscore 时间
     * @param stateStr  抓取实况页面状态
     * @param scoreMap
     * @return
     */
    private static Map<String, Object> getStatus(Map<String, Integer> statusMap, String fid, Map<String, Date> open, String halfscore, String stateStr, Map<String, String> halfscoreMap, Map<String, String> scoreMap) {
        Integer state = 0;
        String time = "";
        if (!statusMap.isEmpty()) {
            Integer status = statusMap.get(fid);
            String scoreStr = scoreMap.get(fid);
            String halfscoreStr = halfscoreMap.get(fid);
            if (Integer.valueOf(0).equals(status)) { // 未开场
                state = 3;
                time = "0";
            } else if (Integer.valueOf(1).equals(status) || Integer.valueOf(3).equals(status)) {// 1、上半场，3、下半场
                state = 2;
                if (!open.isEmpty()) {
                    Date d = open.get(fid);
                    long realStart = (d == null ? 0L : d.getTime());
                    long ingTime = System.currentTimeMillis() - realStart;// 当前时间-开赛时间(每半场更新一次开赛时间，完赛后此时间是结束时间)
                    time = ingTime / (1000 * 60) + "";
                    // 上半场已结束加45分钟
                    if (!":".equals(halfscore)) {
                        time = (ingTime / (1000 * 60) + 45) + "";
                    }
                    if (!statusMap.isEmpty()) {
                        time = "90";
                        state = 1;
                    } else {
                        if (Integer.valueOf(time) > 120) {// 原来是>120
                            time = "90";
                            state = 1;
                        }
                    }
                }
            } else if (Integer.valueOf(2).equals(status)) {// 中场休息
                state = 2;
                time = "中";
            } else if (Integer.valueOf(4).equals(status)) {// 完场
                state = 1;
                time = "90";
            } else if (Integer.valueOf(5).equals(status)) {
                state = 3;
                time = "取消";
            } else if (Integer.valueOf(6).equals(status)) {
                state = 3;
                time = "改期";
            } else if (Integer.valueOf(7).equals(status)) {
                state = 3;
                time = "腰斩";
            } else if (Integer.valueOf(8).equals(status)) {
                state = 3;
                time = "中断";
            } else if (Integer.valueOf(9).equals(status)) {// 待定，到开场时间未开场
                state = 3;
                time = "待定";
            }
        }
        if (StringUtils.isBlank(time)) { // 因为目前只抓取到0,1,2,3,4,9四种状态，可能存在其他状态，如果如此使用旧方法
            if ("完".equals(stateStr)) {
                state = 1;
                time = "90";
            } else if ("未".equals(stateStr)) {
                state = 3;
                time = "0";
            } else {
                state = 2;
                if (!statusMap.isEmpty()) {
                    Integer status = statusMap.get(fid);
                    if (Integer.valueOf(2).equals(status)) {
                        time = "中";
                    }
                } else {
                    if ("中".equals(stateStr)) {
                        time = "中";
                    }
                }
                if (StringUtils.isBlank(time)) {
                    if (!open.isEmpty()) {
                        Date d = open.get(fid);
                        long realStart = (d == null ? 0L : d.getTime());
                        long ingTime = System.currentTimeMillis() - realStart;// 当前时间-开赛时间(每半场更新一次开赛时间，完赛后此时间是结束时间)
                        time = ingTime / (1000 * 60) + "";
                        // 上半场已结束加45分钟
                        if (!":".equals(halfscore)) {
                            time = (ingTime / (1000 * 60) + 45) + "";
                        }
                        if (!statusMap.isEmpty()) {
                            time = "90";
                            state = 1;
                        } else {
                            if (Integer.valueOf(time) > 120) {// 原来是>120
                                time = "90";
                                state = 1;
                            }
                        }
                    }
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("state", state);
        map.put("time", time);
        return map;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date d = format2.parse("2019-03-31 23:48:32");
        Date d = format2.parse("2019-04-01 00:47:56");
        long realStart = (d == null ? 0L : d.getTime());
        long ingTime = System.currentTimeMillis() - realStart;//当前时间-开赛时间
        String time = ingTime / (1000 * 60) + "";
        //下半场
        if (!":".equals("1:0")) {
            time = (ingTime / (1000 * 60) + 45) + "";
        }
        if (Integer.valueOf(time) > 120) {
            time = "90";
        }
        System.out.println("time=" + time);

        int bet = new Random().nextInt(3) % (3 - 1 + 1) + 1;
        System.out.println("bet=" + bet);

        Integer platformEndTime = 0;
        Date endtime = format2.parse("2019-03-20 07:55:00");
        Date strdate = format2.parse("2019-03-20 23:55:00");
        if (endtime == null) {
            endtime = new Date(strdate.getTime() - Integer.valueOf(platformEndTime) * 1000);
        } else {
            endtime = endtime.after(strdate) ? new Date(strdate.getTime() - Integer.valueOf(platformEndTime) * 1000) : endtime;
        }
        System.out.println("endtime=" + endtime + " endtime.after(strdate)=" + endtime.after(strdate));


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date[] dates = new Date[3];//昨日今日明日
        //Date[] dates = new Date[2];//昨日今日
        dates[1] = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        dates[0] = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        dates[2] = calendar.getTime();
        //String[] ds = {format.format(dates[0]),format.format(dates[1]),format.format(dates[2])};
        String[] ds = {format.format(dates[0]), format.format(dates[1]), format.format(dates[2])};
        System.out.println("ds=" + ds[0]);

    }
}
