package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-14 10:32:58
 * @see org.springrain.lottery.entity.BasketballLive
 */
@Table(name="basketball_live")
public class BasketballLive  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "篮球实况";
	public static final String ALIAS_ID = "篮球实况表id";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_NUM = "编号";
	public static final String ALIAS_AWAYTEAM = "客队";
	public static final String ALIAS_AWAYTEAMID2 = "客队id2";
	public static final String ALIAS_HOMETEAM = "主队";
	public static final String ALIAS_HOMETEAMID2 = "主队id2";
	public static final String ALIAS_MATCHNAME = "赛事名";
	public static final String ALIAS_STARTTIME = "开始时间";
	public static final String ALIAS_TIME = "进行时间";
	public static final String ALIAS_ONESCORE = "第一节";
	public static final String ALIAS_TWOSCORE = "第二节";
	public static final String ALIAS_THREESCORE = "第三节";
	public static final String ALIAS_FOURSCORE = "第四节";
	public static final String ALIAS_ALLSCORE = "比分";
	public static final String ALIAS_ALLLANBAN = "alllanban";
	public static final String ALIAS_ALLZHUGONG = "allzhugong";
	public static final String ALIAS_ALLQIANGDUAN = "allqiangduan";
	public static final String ALIAS_ALLGAIMAO = "allgaimao";
	public static final String ALIAS_ALLSHIWU = "allshiwu";
	public static final String ALIAS_ALLFANGUI = "犯规";
	public static final String ALIAS_ALLTOULANMINGZHONGLV = "投篮命中率";
	public static final String ALIAS_ALLFAQIUMINGZHONGLV = "罚球命中率";
	public static final String ALIAS_ALLSANFENMIINGZHONGLV = "3分命中率";
    */
	//date formats
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 篮球实况表id
	 */
	private java.lang.Integer id;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * zid
	 */
	private java.lang.String zid;
	/**
	 * 编号
	 */
	private java.lang.String num;
	/**
	 * 客队
	 */
	private java.lang.String awayteam;
	/**
	 * 客队id2
	 */
	private java.lang.String awayteamid2;
	/**
	 * 主队
	 */
	private java.lang.String hometeam;
	/**
	 * 主队id2
	 */
	private java.lang.String hometeamid2;
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 开始时间
	 */
	private java.util.Date starttime;
	/**
	 * 进行时间
	 */
	private java.lang.String time;
	/**
	 * 客队4节
	 */
	private java.lang.String awayfourscore;
	/**
	 * 主队4节
	 */
	private java.lang.String homefourscore;
	
	/**
	 * 比分
	 */
	private java.lang.String allscore;
	/**
	 * alllanban
	 */
	private java.lang.String alllanban;
	/**
	 * allzhugong
	 */
	private java.lang.String allzhugong;
	/**
	 * allqiangduan
	 */
	private java.lang.String allqiangduan;
	/**
	 * allgaimao
	 */
	private java.lang.String allgaimao;
	/**
	 * allshiwu
	 */
	private java.lang.String allshiwu;
	/**
	 * 犯规
	 */
	private java.lang.String allfangui;
	/**
	 * 投篮命中率
	 */
	private java.lang.String alltoulanmingzhonglv;
	/**
	 * 罚球命中率
	 */
	private java.lang.String allfaqiumingzhonglv;
	/**
	 * 3分命中率
	 */
	private java.lang.String allsanfenmiingzhonglv;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballLive(){
	}

	public BasketballLive(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 篮球实况表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 篮球实况表id
	 */
	@Id
     @WhereSQL(sql="id=:BasketballLive_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * fid
		 */
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
	
	
	/**
	 * fid
	 */
     @WhereSQL(sql="fid=:BasketballLive_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
		/**
		 * zid
		 */
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
	
	
	/**
	 * zid
	 */
     @WhereSQL(sql="zid=:BasketballLive_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
		/**
		 * 编号
		 */
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
	
	
	/**
	 * 编号
	 */
     @WhereSQL(sql="num=:BasketballLive_num")
	public java.lang.String getNum() {
		return this.num;
	}
		/**
		 * 客队
		 */
	public void setAwayteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.awayteam = value;
	}
	
	
	
	/**
	 * 客队
	 */
     @WhereSQL(sql="awayteam=:BasketballLive_awayteam")
	public java.lang.String getAwayteam() {
		return this.awayteam;
	}
		/**
		 * 客队id2
		 */
	public void setAwayteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.awayteamid2 = value;
	}
	
	
	
	/**
	 * 客队id2
	 */
     @WhereSQL(sql="awayteamid2=:BasketballLive_awayteamid2")
	public java.lang.String getAwayteamid2() {
		return this.awayteamid2;
	}
		/**
		 * 主队
		 */
	public void setHometeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeam = value;
	}
	
	
	
	/**
	 * 主队
	 */
     @WhereSQL(sql="hometeam=:BasketballLive_hometeam")
	public java.lang.String getHometeam() {
		return this.hometeam;
	}
		/**
		 * 主队id2
		 */
	public void setHometeamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeamid2 = value;
	}
	
	
	
	/**
	 * 主队id2
	 */
     @WhereSQL(sql="hometeamid2=:BasketballLive_hometeamid2")
	public java.lang.String getHometeamid2() {
		return this.hometeamid2;
	}
		/**
		 * 赛事名
		 */
	public void setMatchname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchname = value;
	}
	
	
	
	/**
	 * 赛事名
	 */
     @WhereSQL(sql="matchname=:BasketballLive_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
		/*
	public String getstarttimeString() {
		return DateUtils.convertDate2String(FORMAT_STARTTIME, getstarttime());
	}
	public void setstarttimeString(String value) throws ParseException{
		setstarttime(DateUtils.convertString2Date(FORMAT_STARTTIME,value));
	}*/
	
		/**
		 * 开始时间
		 */
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	
	
	/**
	 * 开始时间
	 */
     @WhereSQL(sql="starttime=:BasketballLive_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
		/**
		 * 进行时间
		 */
	public void setTime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.time = value;
	}
	
	
	
	/**
	 * 进行时间
	 */
     @WhereSQL(sql="time=:BasketballLive_time")
	public java.lang.String getTime() {
		return this.time;
	}
		
     
     
     @WhereSQL(sql="awayfourscore=:BasketballLive_awayfourscore")
	public java.lang.String getAwayfourscore() {
		return awayfourscore;
	}

	public void setAwayfourscore(java.lang.String awayfourscore) {
		this.awayfourscore = awayfourscore;
	}

	@WhereSQL(sql="homefourscore=:BasketballLive_homefourscore")
	public java.lang.String getHomefourscore() {
		return homefourscore;
	}

	public void setHomefourscore(java.lang.String homefourscore) {
		this.homefourscore = homefourscore;
	}

		/**
		 * 比分
		 */
	public void setAllscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allscore = value;
	}
	
	
	
	/**
	 * 比分
	 */
     @WhereSQL(sql="allscore=:BasketballLive_allscore")
	public java.lang.String getAllscore() {
		return this.allscore;
	}
		/**
		 * alllanban
		 */
	public void setAlllanban(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.alllanban = value;
	}
	
	
	
	/**
	 * alllanban
	 */
     @WhereSQL(sql="alllanban=:BasketballLive_alllanban")
	public java.lang.String getAlllanban() {
		return this.alllanban;
	}
		/**
		 * allzhugong
		 */
	public void setAllzhugong(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allzhugong = value;
	}
	
	
	
	/**
	 * allzhugong
	 */
     @WhereSQL(sql="allzhugong=:BasketballLive_allzhugong")
	public java.lang.String getAllzhugong() {
		return this.allzhugong;
	}
		/**
		 * allqiangduan
		 */
	public void setAllqiangduan(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allqiangduan = value;
	}
	
	
	
	/**
	 * allqiangduan
	 */
     @WhereSQL(sql="allqiangduan=:BasketballLive_allqiangduan")
	public java.lang.String getAllqiangduan() {
		return this.allqiangduan;
	}
		/**
		 * allgaimao
		 */
	public void setAllgaimao(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allgaimao = value;
	}
	
	
	
	/**
	 * allgaimao
	 */
     @WhereSQL(sql="allgaimao=:BasketballLive_allgaimao")
	public java.lang.String getAllgaimao() {
		return this.allgaimao;
	}
		/**
		 * allshiwu
		 */
	public void setAllshiwu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allshiwu = value;
	}
	
	
	
	/**
	 * allshiwu
	 */
     @WhereSQL(sql="allshiwu=:BasketballLive_allshiwu")
	public java.lang.String getAllshiwu() {
		return this.allshiwu;
	}
		/**
		 * 犯规
		 */
	public void setAllfangui(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allfangui = value;
	}
	
	
	
	/**
	 * 犯规
	 */
     @WhereSQL(sql="allfangui=:BasketballLive_allfangui")
	public java.lang.String getAllfangui() {
		return this.allfangui;
	}
		/**
		 * 投篮命中率
		 */
	public void setAlltoulanmingzhonglv(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.alltoulanmingzhonglv = value;
	}
	
	
	
	/**
	 * 投篮命中率
	 */
     @WhereSQL(sql="alltoulanmingzhonglv=:BasketballLive_alltoulanmingzhonglv")
	public java.lang.String getAlltoulanmingzhonglv() {
		return this.alltoulanmingzhonglv;
	}
		/**
		 * 罚球命中率
		 */
	public void setAllfaqiumingzhonglv(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allfaqiumingzhonglv = value;
	}
	
	
	
	/**
	 * 罚球命中率
	 */
     @WhereSQL(sql="allfaqiumingzhonglv=:BasketballLive_allfaqiumingzhonglv")
	public java.lang.String getAllfaqiumingzhonglv() {
		return this.allfaqiumingzhonglv;
	}
		/**
		 * 3分命中率
		 */
	public void setAllsanfenmiingzhonglv(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allsanfenmiingzhonglv = value;
	}
	
	
	
	/**
	 * 3分命中率
	 */
     @WhereSQL(sql="allsanfenmiingzhonglv=:BasketballLive_allsanfenmiingzhonglv")
	public java.lang.String getAllsanfenmiingzhonglv() {
		return this.allsanfenmiingzhonglv;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("篮球实况表id[").append(getId()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("编号[").append(getNum()).append("],")
			.append("客队[").append(getAwayteam()).append("],")
			.append("客队id2[").append(getAwayteamid2()).append("],")
			.append("主队[").append(getHometeam()).append("],")
			.append("主队id2[").append(getHometeamid2()).append("],")
			.append("赛事名[").append(getMatchname()).append("],")
			.append("开始时间[").append(getStarttime()).append("],")
			.append("进行时间[").append(getTime()).append("],")
			.append("客队4节").append(getAwayfourscore()).append("],")
			.append("主队4节").append(getHomefourscore()).append("],")
			.append("比分[").append(getAllscore()).append("],")
			.append("alllanban[").append(getAlllanban()).append("],")
			.append("allzhugong[").append(getAllzhugong()).append("],")
			.append("allqiangduan[").append(getAllqiangduan()).append("],")
			.append("allgaimao[").append(getAllgaimao()).append("],")
			.append("allshiwu[").append(getAllshiwu()).append("],")
			.append("犯规[").append(getAllfangui()).append("],")
			.append("投篮命中率[").append(getAlltoulanmingzhonglv()).append("],")
			.append("罚球命中率[").append(getAllfaqiumingzhonglv()).append("],")
			.append("3分命中率[").append(getAllsanfenmiingzhonglv()).append("],")
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BasketballLive == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		BasketballLive other = (BasketballLive)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
