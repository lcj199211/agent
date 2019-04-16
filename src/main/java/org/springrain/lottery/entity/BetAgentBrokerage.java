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
 * @version  2017-12-14 09:50:03
 * @see org.springrain.lottery.entity.BetAgentBrokerage
 */
@Table(name="bet_agent_brokerage")
public class BetAgentBrokerage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "一级代理佣金比例";
	public static final String ALIAS_ID = "佣金比例表id";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "父级代理";
	public static final String ALIAS_AGENTPARENTIDS = "分级代理id,隔开";
	public static final String ALIAS_BROKERAGEAGENT = "一级代理佣金比例";
	public static final String ALIAS_BROKERAGEMEMBER = "大神佣金比例";
    */
	//date formats
	
	//columns START
	/**
	 * 佣金比例表id
	 */
	private java.lang.Integer id;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 父级代理
	 */
	private java.lang.String agentparentid;
	/**
	 * 分级代理id,隔开
	 */
	private java.lang.String agentparentids;
	/**
	 * 一级代理佣金比例
	 */
	private java.math.BigDecimal brokerageagent;
	/**
	 * 大神佣金比例
	 */
	private java.math.BigDecimal brokeragemember;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAgentBrokerage(){
	}

	public BetAgentBrokerage(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentBrokerage_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentBrokerage_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetAgentBrokerage_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetAgentBrokerage_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setBrokerageagent(java.math.BigDecimal value) {
		this.brokerageagent = value;
	}
	
     @WhereSQL(sql="brokerageagent=:BetAgentBrokerage_brokerageagent")
	public java.math.BigDecimal getBrokerageagent() {
		return this.brokerageagent;
	}
	public void setBrokeragemember(java.math.BigDecimal value) {
		this.brokeragemember = value;
	}
	
     @WhereSQL(sql="brokeragemember=:BetAgentBrokerage_brokeragemember")
	public java.math.BigDecimal getBrokeragemember() {
		return this.brokeragemember;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("佣金比例表id[").append(getId()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("父级代理[").append(getAgentparentid()).append("],")
			.append("分级代理id,隔开[").append(getAgentparentids()).append("],")
			.append("一级代理佣金比例[").append(getBrokerageagent()).append("],")
			.append("大神佣金比例[").append(getBrokeragemember()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentBrokerage == false) return false;
		if(this == obj) return true;
		BetAgentBrokerage other = (BetAgentBrokerage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
