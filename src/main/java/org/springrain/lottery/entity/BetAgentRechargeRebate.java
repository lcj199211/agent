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
 * @version  2017-06-23 15:57:45
 * @see org.springrain.lottery.entity.BetAgentRechargeRebate
 */
@Table(name="bet_agent_recharge_rebate")
public class BetAgentRechargeRebate  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "代理充值返利";
	public static final String ALIAS_ID = "代理充值返利表ID";
	public static final String ALIAS_AGENTID = "代理商ID";
	public static final String ALIAS_LOWERLIMIT = "充值金额下限";
	public static final String ALIAS_UPPERLIMIT = "充值金额上限";
	public static final String ALIAS_REBATE = "rebate";
    */
	//date formats
	
	//columns START
	/**
	 * 代理充值返利表ID
	 */
	private java.lang.Integer id;
	/**
	 * 代理商ID
	 */
	private java.lang.String agentid;
	/**
	 * 充值金额下限
	 */
	private java.lang.Double lowerlimit;
	/**
	 * 充值金额上限
	 */
	private java.lang.Double upperlimit;
	/**
	 * rebate
	 */
	private java.lang.Double rebate;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAgentRechargeRebate(){
	}

	public BetAgentRechargeRebate(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentRechargeRebate_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentRechargeRebate_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setLowerlimit(java.lang.Double value) {
		this.lowerlimit = value;
	}
	
     @WhereSQL(sql="lowerlimit=:BetAgentRechargeRebate_lowerlimit")
	public java.lang.Double getLowerlimit() {
		return this.lowerlimit;
	}
	public void setUpperlimit(java.lang.Double value) {
		this.upperlimit = value;
	}
	
     @WhereSQL(sql="upperlimit=:BetAgentRechargeRebate_upperlimit")
	public java.lang.Double getUpperlimit() {
		return this.upperlimit;
	}
	public void setRebate(java.lang.Double value) {
		this.rebate = value;
	}
	
     @WhereSQL(sql="rebate=:BetAgentRechargeRebate_rebate")
	public java.lang.Double getRebate() {
		return this.rebate;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("代理充值返利表ID[").append(getId()).append("],")
			.append("代理商ID[").append(getAgentid()).append("],")
			.append("充值金额下限[").append(getLowerlimit()).append("],")
			.append("充值金额上限[").append(getUpperlimit()).append("],")
			.append("rebate[").append(getRebate()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentRechargeRebate == false) return false;
		if(this == obj) return true;
		BetAgentRechargeRebate other = (BetAgentRechargeRebate)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
