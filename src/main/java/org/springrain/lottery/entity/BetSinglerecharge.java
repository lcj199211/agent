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
 * @version  2017-06-12 15:22:38
 * @see org.springrain.lottery.entity.BetSinglerecharge
 */
@Table(name="bet_singlerecharge")
public class BetSinglerecharge  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "单笔充值返";
	public static final String ALIAS_ID = "单笔充值返id";
	public static final String ALIAS_MEMBERID2 = "用户id2";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_RECHARGE = "充值";
	public static final String ALIAS_REBATE = "单笔充值返";
	public static final String ALIAS_RECEIVETIME = "领取时间";
	public static final String ALIAS_RECEIVEIP = "领取ip";
	public static final String ALIAS_AGENTID = "agentid";
	public static final String ALIAS_AGENTPARENTID = "agentparentid";
	public static final String ALIAS_AGENTPARENTIDS = "agentparentids";
    */
	//date formats
	
	//columns START
	/**
	 * 单笔充值返id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 充值
	 */
	private java.lang.Double recharge;
	/**
	 * 单笔充值返
	 */
	private java.lang.Double rebate;
	/**
	 * 领取时间
	 */
	private java.lang.String receivetime;
	/**
	 * 领取ip
	 */
	private java.lang.String receiveip;
	/**
	 * agentid
	 */
	private java.lang.String agentid;
	/**
	 * agentparentid
	 */
	private java.lang.String agentparentid;
	/**
	 * agentparentids
	 */
	private java.lang.String agentparentids;
	private java.util.Date time;
	private java.lang.Integer state;
	private java.lang.Integer msg;
	//columns END 数据库字段结束
	
	//concstructor

	public BetSinglerecharge(){
	}

	public BetSinglerecharge(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetSinglerecharge_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetSinglerecharge_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetSinglerecharge_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setRecharge(java.lang.Double value) {
		this.recharge = value;
	}
	
     @WhereSQL(sql="recharge=:BetSinglerecharge_recharge")
	public java.lang.Double getRecharge() {
		return this.recharge;
	}
	public void setRebate(java.lang.Double value) {
		this.rebate = value;
	}
	
     @WhereSQL(sql="rebate=:BetSinglerecharge_rebate")
	public java.lang.Double getRebate() {
		return this.rebate;
	}
	public void setReceivetime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.receivetime = value;
	}
	
     @WhereSQL(sql="receivetime=:BetSinglerecharge_receivetime")
	public java.lang.String getReceivetime() {
		return this.receivetime;
	}
	public void setReceiveip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.receiveip = value;
	}
	
     @WhereSQL(sql="receiveip=:BetSinglerecharge_receiveip")
	public java.lang.String getReceiveip() {
		return this.receiveip;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetSinglerecharge_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetSinglerecharge_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetSinglerecharge_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("单笔充值返id[").append(getId()).append("],")
			.append("用户id2[").append(getMemberid2()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("充值[").append(getRecharge()).append("],")
			.append("单笔充值返[").append(getRebate()).append("],")
			.append("领取时间[").append(getReceivetime()).append("],")
			.append("领取ip[").append(getReceiveip()).append("],")
			.append("agentid[").append(getAgentid()).append("],")
			.append("agentparentid[").append(getAgentparentid()).append("],")
			.append("agentparentids[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetSinglerecharge == false) return false;
		if(this == obj) return true;
		BetSinglerecharge other = (BetSinglerecharge)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@WhereSQL(sql="state=:BetSinglerecharge_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	@WhereSQL(sql="time=:BetSinglerecharge_time")
	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	@WhereSQL(sql="msg=:BetSinglerecharge_msg")
	public java.lang.Integer getMsg() {
		return msg;
	}

	public void setMsg(java.lang.Integer msg) {
		this.msg = msg;
	}
}

	
