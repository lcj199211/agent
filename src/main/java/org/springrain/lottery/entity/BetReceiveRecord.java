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
 * @version  2017-03-01 12:53:06
 * @see org.springrain.lottery.entity.BetReceiveRecord
 */
@Table(name="bet_receive_record")
public class BetReceiveRecord  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "领取记录";
	public static final String ALIAS_ID = "表ID";
	public static final String ALIAS_ORDERNO = "排名";
	public static final String ALIAS_MEMBERID = "会员ID";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_TIME = "领取时间";
	public static final String ALIAS_AWARD = "奖励分";
	public static final String ALIAS_SCORE = "排行分";
	public static final String ALIAS_IP = "领取IP";
	public static final String ALIAS_DATE = "当期排行榜";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 表ID
	 */
	private java.lang.String id;
	/**
	 * 排名
	 */
	private java.lang.Integer orderno;
	/**
	 * 会员ID
	 */
	private java.lang.String memberid;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 领取时间
	 */
	private java.util.Date time;
	/**
	 * 奖励分
	 */
	private java.lang.Double award;
	/**
	 * 排行分
	 */
	private java.lang.Double score;
	/**
	 * 领取IP
	 */
	private java.lang.String ip;
	/**
	 * 当期排行榜
	 */
	private java.util.Date date;
	//columns END 数据库字段结束
	
	//concstructor

	public BetReceiveRecord(){
	}

	public BetReceiveRecord(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetReceiveRecord_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setOrderno(java.lang.Integer value) {
		this.orderno = value;
	}
	
     @WhereSQL(sql="orderno=:BetReceiveRecord_orderno")
	public java.lang.Integer getOrderno() {
		return this.orderno;
	}
	public void setMemberid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetReceiveRecord_memberid")
	public java.lang.String getMemberid() {
		return this.memberid;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetReceiveRecord_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
		/*
	public String gettimeString() {
		return DateUtils.convertDate2String(FORMAT_TIME, gettime());
	}
	public void settimeString(String value) throws ParseException{
		settime(DateUtils.convertString2Date(FORMAT_TIME,value));
	}*/
	
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	
     @WhereSQL(sql="time=:BetReceiveRecord_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setAward(java.lang.Double value) {
		this.award = value;
	}
	
     @WhereSQL(sql="award=:BetReceiveRecord_award")
	public java.lang.Double getAward() {
		return this.award;
	}
	public void setScore(java.lang.Double value) {
		this.score = value;
	}
	
     @WhereSQL(sql="score=:BetReceiveRecord_score")
	public java.lang.Double getScore() {
		return this.score;
	}
	public void setIp(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}
	
     @WhereSQL(sql="ip=:BetReceiveRecord_ip")
	public java.lang.String getIp() {
		return this.ip;
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
	
     @WhereSQL(sql="date=:BetReceiveRecord_date")
	public java.util.Date getDate() {
		return this.date;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("表ID[").append(getId()).append("],")
			.append("排名[").append(getOrderno()).append("],")
			.append("会员ID[").append(getMemberid()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("领取时间[").append(getTime()).append("],")
			.append("奖励分[").append(getAward()).append("],")
			.append("排行分[").append(getScore()).append("],")
			.append("领取IP[").append(getIp()).append("],")
			.append("当期排行榜[").append(getDate()).append("],")
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
		if(obj instanceof BetReceiveRecord == false) return false;
		if(this == obj) return true;
		BetReceiveRecord other = (BetReceiveRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
