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
 * @version  2017-11-29 15:01:22
 * @see org.springrain.lottery.entity.BjdcArrange
 */
@Table(name="bjdc_arrange")
public class BjdcArrange  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BjdcArrange";
	public static final String ALIAS_ID = "北京单场赛事安排表id";
	public static final String ALIAS_NUM = "编号";
	public static final String ALIAS_DATE = "录入时间";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_MATCHNAME = "赛事名";
	public static final String ALIAS_MATCHID2 = "赛事id2";
	public static final String ALIAS_STARTTIME = "开赛时间";
	public static final String ALIAS_ENDTIME = "截止时间";
	public static final String ALIAS_HOMETEAM = "主队";
	public static final String ALIAS_HOMETEAMID2 = "主队id";
	public static final String ALIAS_GUESTTEAM = "客队";
	public static final String ALIAS_GUESTTEAMID2 = "客队id";
	public static final String ALIAS_STATE = "状态 1正常 2截止 3删除";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 北京单场赛事安排表id
	 */
	private java.lang.Integer id;
	/**
	 * 期号
	 */
	private String periodnum;
	/**
	 * 编号
	 */
	private java.lang.String num;
	/**
	 * 录入时间
	 */
	private java.util.Date date;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 赛事id2
	 */
	private java.lang.String matchid2;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 截止时间
	 */
	private java.util.Date endtime;
	/**
	 * 主队
	 */
	private java.lang.String hometeam;
	/**
	 * 主队id
	 */
	private java.lang.String hometeamid2;
	/**
	 * 客队
	 */
	private java.lang.String guestteam;
	/**
	 * 客队id
	 */
	private java.lang.String guestteamid2;
	/**
	 * 状态 1正常 2截止 3删除
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcArrange(){
	}

	public BjdcArrange(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcArrange_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
     @WhereSQL(sql="num=:BjdcArrange_num")
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
	
     @WhereSQL(sql="date=:BjdcArrange_date")
	public java.util.Date getDate() {
		return this.date;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcArrange_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setMatchname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchname = value;
	}
	
     @WhereSQL(sql="matchname=:BjdcArrange_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
	public void setMatchid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchid2 = value;
	}
	
     @WhereSQL(sql="matchid2=:BjdcArrange_matchid2")
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
	
     @WhereSQL(sql="starttime=:BjdcArrange_starttime")
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
	
     @WhereSQL(sql="endtime=:BjdcArrange_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setHometeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeam = value;
	}
	
     @WhereSQL(sql="hometeam=:BjdcArrange_hometeam")
	public java.lang.String getHometeam() {
		return this.hometeam;
	}
	public void setHometeamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeamid2 = value;
	}
	
     @WhereSQL(sql="hometeamid2=:BjdcArrange_hometeamid2")
	public java.lang.String getHometeamid2() {
		return this.hometeamid2;
	}
	public void setGuestteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.guestteam = value;
	}
	
     @WhereSQL(sql="guestteam=:BjdcArrange_guestteam")
	public java.lang.String getGuestteam() {
		return this.guestteam;
	}
	public void setGuestteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.guestteamid2 = value;
	}
	
     @WhereSQL(sql="guestteamid2=:BjdcArrange_guestteamid2")
	public java.lang.String getGuestteamid2() {
		return this.guestteamid2;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BjdcArrange_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("北京单场赛事安排表id[").append(getId()).append("],")
			.append("编号[").append(getNum()).append("],")
			.append("录入时间[").append(getDate()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("赛事名[").append(getMatchname()).append("],")
			.append("赛事id2[").append(getMatchid2()).append("],")
			.append("开赛时间[").append(getStarttime()).append("],")
			.append("截止时间[").append(getEndtime()).append("],")
			.append("主队[").append(getHometeam()).append("],")
			.append("主队id[").append(getHometeamid2()).append("],")
			.append("客队[").append(getGuestteam()).append("],")
			.append("客队id[").append(getGuestteamid2()).append("],")
			.append("状态 1正常 2截止 3删除[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcArrange == false) return false;
		if(this == obj) return true;
		BjdcArrange other = (BjdcArrange)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getPeriodnum() {
		return periodnum;
	}

	public void setPeriodnum(String periodnum) {
		this.periodnum = periodnum;
	}
}

	
