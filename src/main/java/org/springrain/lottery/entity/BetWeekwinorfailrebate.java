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
 * @version  2017-05-10 09:50:39
 * @see org.springrain.lottery.entity.BetWeekwinorfailrebate
 */
@Table(name="bet_weekwinorfailrebate")
public class BetWeekwinorfailrebate  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetWeekwinorfailrebate";
	public static final String ALIAS_ID = "周输返利ID";
	public static final String ALIAS_MEMBERID2 = "用户ID";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_WEEKSCORE = "输赢";
	public static final String ALIAS_WEEKBETTING = "流水";
	public static final String ALIAS_REBATE = "返利";
	public static final String ALIAS_RECEIVETIME = "领取时间";
	public static final String ALIAS_RECEIVEIP = "领取IP";
	public static final String ALIAS_GAMESCORE = "游戏积分";
	public static final String ALIAS_BANKSCORE = "银行积分";
	public static final String ALIAS_STATE = "状态：1未领";
	public static final String ALIAS_DATE = "日期：当周周一";
    */
	//date formats
	//public static final String FORMAT_RECEIVETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 周输返利ID
	 */
	private java.lang.Integer id;
	/**
	 * 用户ID
	 */
	private java.lang.Integer memberid2;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 输赢
	 */
	private java.lang.Double weekscore;
	/**
	 * 流水
	 */
	private java.lang.Double weekbetting;
	/**
	 * 返利
	 */
	private java.lang.Double rebate;
	/**
	 * 领取时间
	 */
	private java.util.Date receivetime;
	/**
	 * 领取IP
	 */
	private java.lang.String receiveip;
	/**
	 * 游戏积分
	 */
	private java.lang.Double gamescore;
	/**
	 * 银行积分
	 */
	private java.lang.Double bankscore;
	/**
	 * 状态：1未领
	 */
	private java.lang.Integer state;
	/**
	 * 日期：当周周一
	 */
	private java.util.Date date;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	
	//columns END 数据库字段结束
	
	//concstructor
	 @WhereSQL(sql="agentid=:BetWeekwinorfailrebate_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	 @WhereSQL(sql="agentparentid=:BetWeekwinorfailrebate_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	 @WhereSQL(sql="agentparentids=:BetWeekwinorfailrebate_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	public BetWeekwinorfailrebate(){
	}

	public BetWeekwinorfailrebate(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetWeekwinorfailrebate_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetWeekwinorfailrebate_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetWeekwinorfailrebate_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setWeekscore(java.lang.Double value) {
		this.weekscore = value;
	}
	
     @WhereSQL(sql="weekscore=:BetWeekwinorfailrebate_weekscore")
	public java.lang.Double getWeekscore() {
		return this.weekscore;
	}
	public void setWeekbetting(java.lang.Double value) {
		this.weekbetting = value;
	}
	
     @WhereSQL(sql="weekbetting=:BetWeekwinorfailrebate_weekbetting")
	public java.lang.Double getWeekbetting() {
		return this.weekbetting;
	}
	public void setRebate(java.lang.Double value) {
		this.rebate = value;
	}
	
     @WhereSQL(sql="rebate=:BetWeekwinorfailrebate_rebate")
	public java.lang.Double getRebate() {
		return this.rebate;
	}
		/*
	public String getreceivetimeString() {
		return DateUtils.convertDate2String(FORMAT_RECEIVETIME, getreceivetime());
	}
	public void setreceivetimeString(String value) throws ParseException{
		setreceivetime(DateUtils.convertString2Date(FORMAT_RECEIVETIME,value));
	}*/
	
	public void setReceivetime(java.util.Date value) {
		this.receivetime = value;
	}
	
     @WhereSQL(sql="receivetime=:BetWeekwinorfailrebate_receivetime")
	public java.util.Date getReceivetime() {
		return this.receivetime;
	}
	public void setReceiveip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.receiveip = value;
	}
	
     @WhereSQL(sql="receiveip=:BetWeekwinorfailrebate_receiveip")
	public java.lang.String getReceiveip() {
		return this.receiveip;
	}
	public void setGamescore(java.lang.Double value) {
		this.gamescore = value;
	}
	
     @WhereSQL(sql="gamescore=:BetWeekwinorfailrebate_gamescore")
	public java.lang.Double getGamescore() {
		return this.gamescore;
	}
	public void setBankscore(java.lang.Double value) {
		this.bankscore = value;
	}
	
     @WhereSQL(sql="bankscore=:BetWeekwinorfailrebate_bankscore")
	public java.lang.Double getBankscore() {
		return this.bankscore;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetWeekwinorfailrebate_state")
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
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:BetWeekwinorfailrebate_date")
	public java.util.Date getDate() {
		return this.date;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("周输返利ID[").append(getId()).append("],")
			.append("用户ID[").append(getMemberid2()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("输赢[").append(getWeekscore()).append("],")
			.append("流水[").append(getWeekbetting()).append("],")
			.append("返利[").append(getRebate()).append("],")
			.append("领取时间[").append(getReceivetime()).append("],")
			.append("领取IP[").append(getReceiveip()).append("],")
			.append("游戏积分[").append(getGamescore()).append("],")
			.append("银行积分[").append(getBankscore()).append("],")
			.append("状态：1未领[").append(getState()).append("],")
			.append("日期：当周周一[").append(getDate()).append("],")
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
		if(obj instanceof BetWeekwinorfailrebate == false) return false;
		if(this == obj) return true;
		BetWeekwinorfailrebate other = (BetWeekwinorfailrebate)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
