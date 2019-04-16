package org.springrain.lottery.entity;

import java.text.ParseException;
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
 * @version  2017-06-12 11:04:57
 * @see org.springrain.lottery.entity.BetRegisterReward
 */
@Table(name="bet_register_reward")
public class BetRegisterReward  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetRegisterReward";
	public static final String ALIAS_ID = "注册送id";
	public static final String ALIAS_MEMBERID2 = "用户id2";
	public static final String ALIAS_REGISTERREWARD = "注册送分";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_RECEIVETIME = "领取时间";
	public static final String ALIAS_RECEIVEIP = "领取ip";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
    */
	//date formats
	//public static final String FORMAT_RECEIVETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 注册送id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 注册送分
	 */
	private java.lang.Double reward;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 领取时间
	 */
	private java.util.Date receivetime;
	/**
	 * 领取ip
	 */
	private java.lang.String receiveip;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商id 从根节点开始
	 */
	private java.lang.String agentparentids;
	//columns END 数据库字段结束
	
	//concstructor

	public BetRegisterReward(){
	}

	public BetRegisterReward(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetRegisterReward_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetRegisterReward_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setReward(java.lang.Double value) {
		this.reward = value;
	}
	
     @WhereSQL(sql="reward=:BetRegisterReward_reward")
	public java.lang.Double getReward() {
		return this.reward;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetRegisterReward_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
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
	
     @WhereSQL(sql="receivetime=:BetRegisterReward_receivetime")
	public java.util.Date getReceivetime() {
		return this.receivetime;
	}
	public void setReceiveip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.receiveip = value;
	}
	
     @WhereSQL(sql="receiveip=:BetRegisterReward_receiveip")
	public java.lang.String getReceiveip() {
		return this.receiveip;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetRegisterReward_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetRegisterReward_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetRegisterReward_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("注册送id[").append(getId()).append("],")
			.append("用户id2[").append(getMemberid2()).append("],")
			.append("注册送分[").append(getReward()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("领取时间[").append(getReceivetime()).append("],")
			.append("领取ip[").append(getReceiveip()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetRegisterReward == false) return false;
		if(this == obj) return true;
		BetRegisterReward other = (BetRegisterReward)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
