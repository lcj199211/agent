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
 * @version  2017-11-07 13:43:08
 * @see org.springrain.lottery.entity.BasketballLeagueArrange
 */
@Table(name="basketball_league_arrange")
public class BasketballLeagueArrange  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球联赛场次";
	public static final String ALIAS_ID = "场次表id";
	public static final String ALIAS_NUM = "序号(来自500)";
	public static final String ALIAS_DATE = "录入时间";
	public static final String ALIAS_MID = "场次mid(来自500)";
	public static final String ALIAS_ZID = "场次mid(来自500)";
	public static final String ALIAS_MATCHNAME = "赛事名";
	public static final String ALIAS_MATCHID = "赛事id";
	public static final String ALIAS_MATCHID2 = "赛事id2";
	public static final String ALIAS_STARTTIME = "开赛时间";
	public static final String ALIAS_ENDTIME = "截止投注时间";
	public static final String ALIAS_HOMETEAM = "主队名";
	public static final String ALIAS_HOMETEAMID = "主队id";
	public static final String ALIAS_HOMETEAMID2 = "主队id2";
	public static final String ALIAS_AWAYTEAM = "客队名";
	public static final String ALIAS_AWAYTEAMID = "客队id";
	public static final String ALIAS_AWAYTEAMID2 = "客队id2";
	public static final String ALIAS_STATE = "1正常,3删除";
	public static final String ALIAS_ISHOT = "是否热门1:是 3:不是";
	public static final String ALIAS_MATCHDATE = "哪天的比赛";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_MATCHDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 场次表id
	 */
	private java.lang.Integer id;
	/**
	 * 序号(来自500)
	 */
	private java.lang.String num;
	/**
	 * 录入时间
	 */
	private java.util.Date date;
	/**
	 * 场次mid(来自500)
	 */
	private java.lang.String mid;
	/**
	 * 场次mid(来自500)
	 */
	private java.lang.String zid;
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 赛事id
	 */
	private java.lang.Integer matchid;
	/**
	 * 赛事id2
	 */
	private java.lang.String matchid2;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 截止投注时间
	 */
	private java.util.Date endtime;
	/**
	 * 主队名
	 */
	private java.lang.String hometeam;
	/**
	 * 主队id
	 */
	private java.lang.Integer hometeamid;
	/**
	 * 主队id2
	 */
	private java.lang.String hometeamid2;
	/**
	 * 客队名
	 */
	private java.lang.String awayteam;
	/**
	 * 客队id
	 */
	private java.lang.Integer awayteamid;
	/**
	 * 客队id2
	 */
	private java.lang.String awayteamid2;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 是否热门1:是 3:不是
	 */
	private java.lang.Integer ishot;
	/**
	 * 哪天的比赛
	 */
	private java.util.Date matchdate;
	/**
	 * 期号
	 */
	private String issue;
	private Integer danguan;
	private Integer rfsfdanguan;
	private Integer dxfdanguan;
	
	public BasketballLeagueArrange(){
	}

	public BasketballLeagueArrange(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballLeagueArrange_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
     @WhereSQL(sql="num=:BasketballLeagueArrange_num")
	public java.lang.String getNum() {
		return this.num;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:BasketballLeagueArrange_date")
	public java.util.Date getDate() {
		return this.date;
	}
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
     @WhereSQL(sql="mid=:BasketballLeagueArrange_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
     @WhereSQL(sql="zid=:BasketballLeagueArrange_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
	public void setMatchname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchname = value;
	}
	
     @WhereSQL(sql="matchname=:BasketballLeagueArrange_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
	public void setMatchid(java.lang.Integer value) {
		this.matchid = value;
	}
	
     @WhereSQL(sql="matchid=:BasketballLeagueArrange_matchid")
	public java.lang.Integer getMatchid() {
		return this.matchid;
	}
	public void setMatchid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchid2 = value;
	}
	
     @WhereSQL(sql="matchid2=:BasketballLeagueArrange_matchid2")
	public java.lang.String getMatchid2() {
		return this.matchid2;
	}
		/*
	public String getstarttimeString() {
		return DateUtils.convertDate2String(FORMAT_STARTTIME, getstarttime());
	}
	public void setstarttimeString(String value) throws ParseException{
		setstarttime(DateUtils.convertString2Date(FORMAT_STARTTIME,value));
	}*/
	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
     @WhereSQL(sql="starttime=:BasketballLeagueArrange_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
		/*
	public String getendtimeString() {
		return DateUtils.convertDate2String(FORMAT_ENDTIME, getendtime());
	}
	public void setendtimeString(String value) throws ParseException{
		setendtime(DateUtils.convertString2Date(FORMAT_ENDTIME,value));
	}*/
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
     @WhereSQL(sql="endtime=:BasketballLeagueArrange_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setHometeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeam = value;
	}
	
     @WhereSQL(sql="hometeam=:BasketballLeagueArrange_hometeam")
	public java.lang.String getHometeam() {
		return this.hometeam;
	}
	public void setHometeamid(java.lang.Integer value) {
		this.hometeamid = value;
	}
	
     @WhereSQL(sql="hometeamid=:BasketballLeagueArrange_hometeamid")
	public java.lang.Integer getHometeamid() {
		return this.hometeamid;
	}
	public void setHometeamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeamid2 = value;
	}
	
     @WhereSQL(sql="hometeamid2=:BasketballLeagueArrange_hometeamid2")
	public java.lang.String getHometeamid2() {
		return this.hometeamid2;
	}
	public void setAwayteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.awayteam = value;
	}
	
     @WhereSQL(sql="awayteam=:BasketballLeagueArrange_awayteam")
	public java.lang.String getAwayteam() {
		return this.awayteam;
	}
	public void setAwayteamid(java.lang.Integer value) {
		this.awayteamid = value;
	}
	
     @WhereSQL(sql="awayteamid=:BasketballLeagueArrange_awayteamid")
	public java.lang.Integer getAwayteamid() {
		return this.awayteamid;
	}
	public void setAwayteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.awayteamid2 = value;
	}
	
     @WhereSQL(sql="awayteamid2=:BasketballLeagueArrange_awayteamid2")
	public java.lang.String getAwayteamid2() {
		return this.awayteamid2;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BasketballLeagueArrange_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setIshot(java.lang.Integer value) {
		this.ishot = value;
	}
	
     @WhereSQL(sql="ishot=:BasketballLeagueArrange_ishot")
	public java.lang.Integer getIshot() {
		return this.ishot;
	}
		/*
	public String getmatchdateString() {
		return DateUtils.convertDate2String(FORMAT_MATCHDATE, getmatchdate());
	}
	public void setmatchdateString(String value) throws ParseException{
		setmatchdate(DateUtils.convertString2Date(FORMAT_MATCHDATE,value));
	}*/
	
	public void setMatchdate(java.util.Date value) {
		this.matchdate = value;
	}
	
     @WhereSQL(sql="matchdate=:BasketballLeagueArrange_matchdate")
	public java.util.Date getMatchdate() {
		return this.matchdate;
	}
     
     @WhereSQL(sql="issue=:BasketballLeagueArrange_issue")
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	 @WhereSQL(sql="danguan=:BasketballLeagueArrange_danguan")
	public Integer getDanguan() {
		return danguan;
	}

	public void setDanguan(Integer danguan) {
		this.danguan = danguan;
	}
	@WhereSQL(sql="rfsfdanguan=:BasketballLeagueArrange_rfsfdanguan")
	public Integer getRfsfdanguan() {
		return rfsfdanguan;
	}

	public void setRfsfdanguan(Integer rfsfdanguan) {
		this.rfsfdanguan = rfsfdanguan;
	}
	@WhereSQL(sql="dxfdanguan=:BasketballLeagueArrange_dxfdanguan")
	public Integer getDxfdanguan() {
		return dxfdanguan;
	}

	public void setDxfdanguan(Integer dxfdanguan) {
		this.dxfdanguan = dxfdanguan;
	}

	public String toString() {
		return new StringBuffer()
			.append("场次表id[").append(getId()).append("],")
			.append("序号(来自500)[").append(getNum()).append("],")
			.append("录入时间[").append(getDate()).append("],")
			.append("场次mid(来自500)[").append(getMid()).append("],")
			.append("场次mid(来自500)[").append(getZid()).append("],")
			.append("赛事名[").append(getMatchname()).append("],")
			.append("赛事id[").append(getMatchid()).append("],")
			.append("赛事id2[").append(getMatchid2()).append("],")
			.append("开赛时间[").append(getStarttime()).append("],")
			.append("截止投注时间[").append(getEndtime()).append("],")
			.append("主队名[").append(getHometeam()).append("],")
			.append("主队id[").append(getHometeamid()).append("],")
			.append("主队id2[").append(getHometeamid2()).append("],")
			.append("客队名[").append(getAwayteam()).append("],")
			.append("客队id[").append(getAwayteamid()).append("],")
			.append("客队id2[").append(getAwayteamid2()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.append("是否热门1:是 3:不是[").append(getIshot()).append("],")
			.append("哪天的比赛[").append(getMatchdate()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballLeagueArrange == false) return false;
		if(this == obj) return true;
		BasketballLeagueArrange other = (BasketballLeagueArrange)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
	
}

	
