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
 * @version  2017-08-17 17:49:11
 * @see org.springrain.lottery.entity.SoccerLeagueArrange
 */
@Table(name="soccer_league_arrange")
public class SoccerLeagueArrange  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueArrange";
	public static final String ALIAS_ID = "场次表id";
	public static final String ALIAS_DATE = "录入时间";
	public static final String ALIAS_MATCHNAME = "赛事名";
	public static final String ALIAS_MATCHID = "赛事id";
	public static final String ALIAS_MATCHID2 = "赛事id2";
	public static final String ALIAS_STARTTIME = "开赛时间";
	public static final String ALIAS_ENDTIME = "截止投注时间";
	public static final String ALIAS_LEFTTEAMNAME = "主队名";
	public static final String ALIAS_LEFTTEAMID = "主队id";
	public static final String ALIAS_LEFTTEAMID2 = "主队id2";
	public static final String ALIAS_RIGHTTEAMNAME = "客队名";
	public static final String ALIAS_RIGHTTEAMID = "客队id";
	public static final String ALIAS_RIGHTTEAMID2 = "客队id2";
	public static final String ALIAS_STATE = "1正常,3删除";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 场次表id
	 */
	private java.lang.Integer id;
	/**
	 * 录入时间
	 */
	private java.util.Date date;
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
	private java.lang.String leftteamname;
	/**
	 * 主队id
	 */
	private java.lang.Integer leftteamid;
	/**
	 * 主队id2
	 */
	private java.lang.String leftteamid2;
	/**
	 * 客队名
	 */
	private java.lang.String rightteamname;
	/**
	 * 客队id
	 */
	private java.lang.Integer rightteamid;
	/**
	 * 客队id2
	 */
	private java.lang.String rightteamid2;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 是否热门1:是 3:不是
	 */
	private java.lang.Integer ishot;
	
	private java.lang.String num;
	
	private java.lang.String zid;
	
	private java.lang.String issue;
	private String liveid;
	//columns END 数据库字段结束
	
	//concstructor
	 @WhereSQL(sql="issue=:BasketballLeagueArrange_issue")
	public java.lang.String getIssue() {
		return issue;
	}

	public void setIssue(java.lang.String issue) {
		this.issue = issue;
	}
	@WhereSQL(sql="ishot=:SoccerLeagueArrange_ishot")
	public java.lang.Integer getIshot() {
		return ishot;
	}

	public void setIshot(java.lang.Integer ishot) {
		this.ishot = ishot;
	}

	@WhereSQL(sql="zid=:SoccerLeagueArrange_zid")
	public java.lang.String getZid() {
		return zid;
	}

	public void setZid(java.lang.String zid) {
		this.zid = zid;
	}

	@WhereSQL(sql="num=:SoccerLeagueArrange_num")
	public java.lang.String getNum() {
		return num;
	}

	public void setNum(java.lang.String num) {
		this.num = num;
	}
	private java.lang.String mid;
	//columns END 数据库字段结束
	
	//concstructor
	
	 @WhereSQL(sql="mid=:SoccerLeagueArrange_mid")
	public java.lang.String getMid() {
		return mid;
	}

	public void setMid(java.lang.String mid) {
		this.mid = mid;
	}

	public SoccerLeagueArrange(){
	}

	public SoccerLeagueArrange(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 场次表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 场次表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueArrange_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
		/**
		 * 录入时间
		 */
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	
	
	/**
	 * 录入时间
	 */
     @WhereSQL(sql="date=:SoccerLeagueArrange_date")
	public java.util.Date getDate() {
		return this.date;
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
     @WhereSQL(sql="matchname=:SoccerLeagueArrange_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
		/**
		 * 赛事id
		 */
	public void setMatchid(java.lang.Integer value) {
		this.matchid = value;
	}
	
	
	
	/**
	 * 赛事id
	 */
     @WhereSQL(sql="matchid=:SoccerLeagueArrange_matchid")
	public java.lang.Integer getMatchid() {
		return this.matchid;
	}
		/**
		 * 赛事id2
		 */
	public void setMatchid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchid2 = value;
	}
	
	
	
	/**
	 * 赛事id2
	 */
     @WhereSQL(sql="matchid2=:SoccerLeagueArrange_matchid2")
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
	
		/**
		 * 开赛时间
		 */
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	
	
	/**
	 * 开赛时间
	 */
     @WhereSQL(sql="starttime=:SoccerLeagueArrange_starttime")
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
	
		/**
		 * 截止投注时间
		 */
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
	
	
	/**
	 * 截止投注时间
	 */
     @WhereSQL(sql="endtime=:SoccerLeagueArrange_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
		/**
		 * 主队名
		 */
	public void setLeftteamname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftteamname = value;
	}
	
	
	
	/**
	 * 主队名
	 */
     @WhereSQL(sql="leftteamname=:SoccerLeagueArrange_leftteamname")
	public java.lang.String getLeftteamname() {
		return this.leftteamname;
	}
		/**
		 * 主队id
		 */
	public void setLeftteamid(java.lang.Integer value) {
		this.leftteamid = value;
	}
	
	
	
	/**
	 * 主队id
	 */
     @WhereSQL(sql="leftteamid=:SoccerLeagueArrange_leftteamid")
	public java.lang.Integer getLeftteamid() {
		return this.leftteamid;
	}
		/**
		 * 主队id2
		 */
	public void setLeftteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftteamid2 = value;
	}
	
	
	
	/**
	 * 主队id2
	 */
     @WhereSQL(sql="leftteamid2=:SoccerLeagueArrange_leftteamid2")
	public java.lang.String getLeftteamid2() {
		return this.leftteamid2;
	}
		/**
		 * 客队名
		 */
	public void setRightteamname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightteamname = value;
	}
	
	
	
	/**
	 * 客队名
	 */
     @WhereSQL(sql="rightteamname=:SoccerLeagueArrange_rightteamname")
	public java.lang.String getRightteamname() {
		return this.rightteamname;
	}
		/**
		 * 客队id
		 */
	public void setRightteamid(java.lang.Integer value) {
		this.rightteamid = value;
	}
	
	
	
	/**
	 * 客队id
	 */
     @WhereSQL(sql="rightteamid=:SoccerLeagueArrange_rightteamid")
	public java.lang.Integer getRightteamid() {
		return this.rightteamid;
	}
		/**
		 * 客队id2
		 */
	public void setRightteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightteamid2 = value;
	}
	
	
	
	/**
	 * 客队id2
	 */
     @WhereSQL(sql="rightteamid2=:SoccerLeagueArrange_rightteamid2")
	public java.lang.String getRightteamid2() {
		return this.rightteamid2;
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
     @WhereSQL(sql="state=:SoccerLeagueArrange_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     
     
     /**
 	 * liveid
 	 */
      @WhereSQL(sql="liveid=:SoccerLeagueArrange_liveid")
 	public java.lang.String getLiveid() {
 		return this.liveid;
 	}
 		/**
 		 * liveid
 		 */
 	public void setLiveid(java.lang.String value) {
 		this.liveid = value;
 	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("场次表id[").append(getId()).append("],")
			.append("录入时间[").append(getDate()).append("],")
			.append("赛事名[").append(getMatchname()).append("],")
			.append("赛事id[").append(getMatchid()).append("],")
			.append("赛事id2[").append(getMatchid2()).append("],")
			.append("开赛时间[").append(getStarttime()).append("],")
			.append("截止投注时间[").append(getEndtime()).append("],")
			.append("主队名[").append(getLeftteamname()).append("],")
			.append("主队id[").append(getLeftteamid()).append("],")
			.append("主队id2[").append(getLeftteamid2()).append("],")
			.append("客队名[").append(getRightteamname()).append("],")
			.append("客队id[").append(getRightteamid()).append("],")
			.append("客队id2[").append(getRightteamid2()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
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
		if(obj instanceof SoccerLeagueArrange == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueArrange other = (SoccerLeagueArrange)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
