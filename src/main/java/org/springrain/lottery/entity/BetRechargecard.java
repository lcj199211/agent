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
 * @version  2017-04-26 16:43:02
 * @see org.springrain.lottery.entity.BetRechargecard
 */
@Table(name="bet_rechargecard")
public class BetRechargecard  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "充值卡";
	public static final String ALIAS_ID = "充值卡号";
	public static final String ALIAS_PASSWORD = "充值密码";
	public static final String ALIAS_MONEY = "面值/元";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_IP = "充值地址IP";
	public static final String ALIAS_TIME = "充值时间";
	public static final String ALIAS_MEMBERID2 = "充值ID";
	public static final String ALIAS_OPERATOR = "操作";
	public static final String ALIAS_VALIDITY = "有效期/天";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 充值卡号
	 */
	private java.lang.String id;
	/**
	 * 充值密码
	 */
	private java.lang.String password;
	/**
	 * 面值/元
	 */
	private java.lang.Double money;
	/**
	 * 状态
	 */
	private java.lang.Integer state;
	/**
	 * 充值地址IP
	 */
	private java.lang.String ip;
	/**
	 * 充值时间
	 */
	private java.util.Date time;
	/**
	 * 充值ID
	 */
	private java.lang.Integer memberid2;
	/**
	 * 操作
	 */
	private java.lang.String operator;
	/**
	 * 有效期/天
	 */
	private java.lang.Integer validity;
	private java.util.Date rechargetime;
	
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="rechargetime=:BetRechargecard_rechargetime")
	public java.util.Date getRechargetime() {
		return rechargetime;
	}

	public void setRechargetime(java.util.Date rechargetime) {
		this.rechargetime = rechargetime;
	}

	public BetRechargecard(){
	}

	public BetRechargecard(
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
     @WhereSQL(sql="id=:BetRechargecard_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setPassword(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.password = value;
	}
	
     @WhereSQL(sql="password=:BetRechargecard_password")
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetRechargecard_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetRechargecard_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setIp(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}
	
     @WhereSQL(sql="ip=:BetRechargecard_ip")
	public java.lang.String getIp() {
		return this.ip;
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
	
     @WhereSQL(sql="time=:BetRechargecard_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetRechargecard_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setOperator(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operator = value;
	}
	
     @WhereSQL(sql="operator=:BetRechargecard_operator")
	public java.lang.String getOperator() {
		return this.operator;
	}
	public void setValidity(java.lang.Integer value) {
		this.validity = value;
	}
	
     @WhereSQL(sql="validity=:BetRechargecard_validity")
	public java.lang.Integer getValidity() {
		return this.validity;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("充值卡号[").append(getId()).append("],")
			.append("充值密码[").append(getPassword()).append("],")
			.append("面值/元[").append(getMoney()).append("],")
			.append("状态[").append(getState()).append("],")
			.append("充值地址IP[").append(getIp()).append("],")
			.append("充值时间[").append(getTime()).append("],")
			.append("充值ID[").append(getMemberid2()).append("],")
			.append("操作[").append(getOperator()).append("],")
			.append("有效期/天[").append(getValidity()).append("],")
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
		if(obj instanceof BetRechargecard == false) return false;
		if(this == obj) return true;
		BetRechargecard other = (BetRechargecard)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
