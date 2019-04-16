package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:52:41
 * @see org.springrain.lottery.entity.BetRankMember
 */
@Table(name="bet_rank_member")
public class BetRankMember  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "会员排行榜";
	public static final String ALIAS_ID = "表ID";
	public static final String ALIAS_ORDERNO = "排名";
	public static final String ALIAS_MEMBERID = "会员ID";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_SCORE = "排行分";
	public static final String ALIAS_AWARD = "奖励分";
	public static final String ALIAS_STATE = "状态：1领取0未领";
	public static final String ALIAS_DATE = "排行榜日期";
    */
	//date formats
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
	 * 排行分
	 */
	private java.lang.Double dayscore;
	/**
	 * 奖励分
	 */
	private java.lang.Double award;
	/**
	 * 状态：1领取0未领
	 */
	private java.lang.Integer state;
	/**
	 * 排行榜日期
	 */
	private java.sql.Date date;
	private java.lang.String realname;
	private java.lang.Double bettingmoney;
	private java.lang.String loginip;
	private java.lang.String operation;
	private java.lang.Double bankmoney;
	private java.lang.Double gamemoney;
	private java.lang.Double freezingscore;
	private java.lang.Double score;
	private java.lang.Integer id2;
	@Transient
	public java.lang.Integer getId2() {
		return id2;
	}
	public void setId2(java.lang.Integer id2) {
		this.id2 = id2;
	}
	
	@WhereSQL(sql="score=:BetRankMember_score")
	public java.lang.Double getScore() {
		return score;
	}
	public void setScore(java.lang.Double score) {
		this.score = score;
	}
	//columns END 数据库字段结束
	@WhereSQL(sql="bankmoney=:BetRankMember_bankmoney")
	public java.lang.Double getBankmoney() {
		return bankmoney;
	}
	public void setBankmoney(java.lang.Double bankmoney) {
		this.bankmoney = bankmoney;
	}
	@WhereSQL(sql="gamemoney=:BetRankMember_gamemoney")
	public java.lang.Double getGamemoney() {
		return gamemoney;
	}
	public void setGamemoney(java.lang.Double gamemoney) {
		this.gamemoney = gamemoney;
	}
	//concstructor
	@WhereSQL(sql="realname=:BetRankMember_realname")
	public java.lang.String getRealname() {
		return realname;
	}
	@WhereSQL(sql="bettingmoney=:BetRankMember_bettingmoney")
	public java.lang.Double getBettingmoney() {
		return bettingmoney;
	}

	public void setBettingmoney(java.lang.Double bettingmoney) {
		this.bettingmoney = bettingmoney;
	}
	@WhereSQL(sql="loginip=:BetRankMember_loginip")
	public java.lang.String getLoginip() {
		return loginip;
	}

	public void setLoginip(java.lang.String loginip) {
		this.loginip = loginip;
	}
	@WhereSQL(sql="operation=:BetRankMember_operation")
	public java.lang.String getOperation() {
		return operation;
	}

	public void setOperation(java.lang.String operation) {
		this.operation = operation;
	}

	public void setRealname(java.lang.String realname) {
		this.realname = realname;
	}
	@WhereSQL(sql="freezingscore=:BetRankMember_freezingscore")
	public java.lang.Double getFreezingscore() {
		return freezingscore;
	}
	public void setFreezingscore(java.lang.Double freezingscore) {
		this.freezingscore = freezingscore;
	}
	public BetRankMember(){
	}
	
	public BetRankMember(java.lang.String id
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
     @WhereSQL(sql="id=:BetRankMember_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setOrderno(java.lang.Integer value) {
		this.orderno = value;
	}
	
     @WhereSQL(sql="orderno=:BetRankMember_orderno")
	public java.lang.Integer getOrderno() {
		return this.orderno;
	}
	public void setMemberid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetRankMember_memberid")
	public java.lang.String getMemberid() {
		return this.memberid;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetRankMember_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setDayscore(java.lang.Double value) {
		this.dayscore = value;
	}
	
     @WhereSQL(sql="dayscore=:BetRankMember_dayscore")
	public java.lang.Double getDayscore() {
		return this.dayscore;
	}
	public void setAward(java.lang.Double value) {
		this.award = value;
	}
	
     @WhereSQL(sql="award=:BetRankMember_award")
	public java.lang.Double getAward() {
		return this.award;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetRankMember_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
	public void setDate(java.sql.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:BetRankMember_date")
	public java.sql.Date getDate() {
		return this.date;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("表ID[").append(getId()).append("],")
			.append("排名[").append(getOrderno()).append("],")
			.append("会员ID[").append(getMemberid()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("排行分[").append(getDayscore()).append("],")
			.append("奖励分[").append(getAward()).append("],")
			.append("状态：1领取0未领[").append(getState()).append("],")
			.append("排行榜日期[").append(getDate()).append("],")
			.toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BetRankMember == false) return false;
		if(this == obj) return true;
		BetRankMember other = (BetRankMember)obj;
		return new EqualsBuilder()
			.isEquals();
	}
	
}

	
