package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 17:47:41
 * @see org.springrain.lottery.entity.SoccerLeagueOdds
 */
@Table(name="soccer_league_odds")
public class SoccerLeagueOdds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueOdds";
	public static final String ALIAS_ID = "赔率表id";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_ARRANGEID = "场次id";
	public static final String ALIAS_ARRANGEID2 = "场次id2";
	public static final String ALIAS_TYPE = "不让球0,让球1";
	public static final String ALIAS_LETPOINTS = "让球";
	public static final String ALIAS_WIN = "胜赔率";
	public static final String ALIAS_FLAT = "平赔率";
	public static final String ALIAS_LOSE = "输赔率";
	public static final String ALIAS_DATE = "时间(最新时间)";
	public static final String ALIAS_STATE = "1正常,3删除";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 赔率表id
	 */
	private java.lang.Integer id;
	/**
	 * 玩法id
	 */
	private java.lang.Integer playmethodid;
	/**
	 * 场次id
	 */
	private java.lang.Integer arrangeid;
	/**
	 * 场次id2
	 */
	private java.lang.String arrangeid2;
	/**
	 * 不让球0,让球1
	 */
	private java.lang.Integer type;
	/**
	 * 让球
	 */
	private java.lang.String letpoints;
	/**
	 * 胜赔率
	 */
	private java.lang.Double win;
	/**
	 * 平赔率
	 */
	private java.lang.Double flat;
	/**
	 * 输赔率
	 */
	private java.lang.Double lose;
	/**
	 * 时间(最新时间)
	 */
	private java.util.Date date;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	
	private java.lang.String mid;
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 主队名
	 */
	private java.lang.String leftteamname;
	/**
	 * 客队名
	 */
	private java.lang.String rightteamname;
	
	private java.lang.String zid;
	
	/**
	 * 玩法
	 */
	private java.lang.String playmethod;
	private Integer danguan;
	
	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}
	
	@WhereSQL(sql="zid=:SoccerLeagueOdds_zid")
	public java.lang.String getZid() {
		return zid;
	}

	public void setZid(java.lang.String zid) {
		this.zid = zid;
	}
	
	@Transient
	public java.lang.String getMatchname() {
		return matchname;
	}

	public void setMatchname(java.lang.String matchname) {
		this.matchname = matchname;
	}
	
	@Transient
	public java.lang.String getLeftteamname() {
		return leftteamname;
	}

	public void setLeftteamname(java.lang.String leftteamname) {
		this.leftteamname = leftteamname;
	}
	@Transient
	public java.lang.String getRightteamname() {
		return rightteamname;
	}

	public void setRightteamname(java.lang.String rightteamname) {
		this.rightteamname = rightteamname;
	}

	@WhereSQL(sql="mid=:SoccerLeagueOdds_mid")
	public java.lang.String getMid() {
		return mid;
	}

	public void setMid(java.lang.String mid) {
		this.mid = mid;
	}
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLeagueOdds(){
	}

	public SoccerLeagueOdds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 赔率表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 赔率表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueOdds_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 玩法id
		 */
	public void setPlaymethodid(java.lang.Integer value) {
		this.playmethodid = value;
	}
	
	
	
	/**
	 * 玩法id
	 */
     @WhereSQL(sql="playmethodid=:SoccerLeagueOdds_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
		/**
		 * 场次id
		 */
	public void setArrangeid(java.lang.Integer value) {
		this.arrangeid = value;
	}
	
	
	
	/**
	 * 场次id
	 */
     @WhereSQL(sql="arrangeid=:SoccerLeagueOdds_arrangeid")
	public java.lang.Integer getArrangeid() {
		return this.arrangeid;
	}
		/**
		 * 场次id2
		 */
	public void setArrangeid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.arrangeid2 = value;
	}
	
	
	
	/**
	 * 场次id2
	 */
     @WhereSQL(sql="arrangeid2=:SoccerLeagueOdds_arrangeid2")
	public java.lang.String getArrangeid2() {
		return this.arrangeid2;
	}
		/**
		 * 不让球0,让球1
		 */
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 不让球0,让球1
	 */
     @WhereSQL(sql="type=:SoccerLeagueOdds_type")
	public java.lang.Integer getType() {
		return this.type;
	}
		/**
		 * 让球
		 */
	public void setLetpoints(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.letpoints = value;
	}
	
	
	
	/**
	 * 让球
	 */
     @WhereSQL(sql="letpoints=:SoccerLeagueOdds_letpoints")
	public java.lang.String getLetpoints() {
		return this.letpoints;
	}
		/**
		 * 胜赔率
		 */
	public void setWin(java.lang.Double value) {
		this.win = value;
	}
	
	
	
	/**
	 * 胜赔率
	 */
     @WhereSQL(sql="win=:SoccerLeagueOdds_win")
	public java.lang.Double getWin() {
		return this.win;
	}
		/**
		 * 平赔率
		 */
	public void setFlat(java.lang.Double value) {
		this.flat = value;
	}
	
	
	
	/**
	 * 平赔率
	 */
     @WhereSQL(sql="flat=:SoccerLeagueOdds_flat")
	public java.lang.Double getFlat() {
		return this.flat;
	}
		/**
		 * 输赔率
		 */
	public void setLose(java.lang.Double value) {
		this.lose = value;
	}
	
	
	
	/**
	 * 输赔率
	 */
     @WhereSQL(sql="lose=:SoccerLeagueOdds_lose")
	public java.lang.Double getLose() {
		return this.lose;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
		/**
		 * 时间(最新时间)
		 */
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	
	
	/**
	 * 时间(最新时间)
	 */
     @WhereSQL(sql="date=:SoccerLeagueOdds_date")
	public java.util.Date getDate() {
		return this.date;
	}
		/**
		 * 1正常,3删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 1正常,3删除
	 */
     @WhereSQL(sql="state=:SoccerLeagueOdds_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     public Integer getDanguan() {
 		return danguan;
 	}
     @WhereSQL(sql="danguan=:SoccerLeagueOdds_danguan")
 	public void setDanguan(Integer danguan) {
 		this.danguan = danguan;
 	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("赔率表id[").append(getId()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("场次id[").append(getArrangeid()).append("],")
			.append("场次id2[").append(getArrangeid2()).append("],")
			.append("不让球0,让球1[").append(getType()).append("],")
			.append("让球[").append(getLetpoints()).append("],")
			.append("胜赔率[").append(getWin()).append("],")
			.append("平赔率[").append(getFlat()).append("],")
			.append("输赔率[").append(getLose()).append("],")
			.append("时间(最新时间)[").append(getDate()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.toString();
	}

	
	
}

	
