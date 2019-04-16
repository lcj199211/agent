package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springframework.data.annotation.Transient;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-10-20 20:37:36
 * @see org.springrain.lottery.entity.BetCommission
 */
@Table(name="bet_commission")
public class BetCommission  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "投注代理退佣";
	public static final String ALIAS_ID = "投注代理退佣表id";
	public static final String ALIAS_AGENTID = "代理id";
	public static final String ALIAS_MEMBERID2 = "用户id2";
	public static final String ALIAS_COMMISSION = "佣金";
	public static final String ALIAS_SETTLEMENTTIME = "结算时间";
	public static final String ALIAS_ORDERID = "投注订单id";
	public static final String ALIAS_REMARK = "备注";
    */
	//date formats
	//public static final String FORMAT_SETTLEMENTTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 投注代理退佣表id
	 */
	private java.lang.Integer id;
	/**
	 * 代理id
	 */
	private java.lang.String agentid;
	
	private java.lang.String agentparentid;
	
	private java.lang.String agentparentids;
	/**
	 * 用户id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 佣金
	 */
	private java.lang.Double commission;
	/**
	 * 结算时间
	 */
	private java.util.Date settlementtime;
	
	private java.util.Date bettingtime;
	/**
	 * 投注订单id
	 */
	private java.lang.String orderid;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	
	private java.lang.String memberagentid;
	
	
	//columns END 数据库字段结束
	
	//concstructor

	public BetCommission(){
	}

	public BetCommission(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetCommission_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetCommission_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetCommission_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setCommission(java.lang.Double value) {
		this.commission = value;
	}
	
     @WhereSQL(sql="commission=:BetCommission_commission")
	public java.lang.Double getCommission() {
		return this.commission;
	}
		/*
	public String getsettlementtimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLEMENTTIME, getsettlementtime());
	}
	public void setsettlementtimeString(String value) throws ParseException{
		setsettlementtime(DateUtils.convertString2Date(FORMAT_SETTLEMENTTIME,value));
	}*/
	
	public void setSettlementtime(java.util.Date value) {
		this.settlementtime = value;
	}
	
     @WhereSQL(sql="settlementtime=:BetCommission_settlementtime")
	public java.util.Date getSettlementtime() {
		return this.settlementtime;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BetCommission_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetCommission_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
     
     
     @WhereSQL(sql="agentparentid=:BetCommission_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}

	 @WhereSQL(sql="agentparentids=:BetCommission_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	 @WhereSQL(sql="bettingtime=:BetCommission_bettingtime")
	public java.util.Date getBettingtime() {
		return bettingtime;
	}

	public void setBettingtime(java.util.Date bettingtime) {
		this.bettingtime = bettingtime;
	}

	
	@WhereSQL(sql="memberagentid=:BetCommission_memberagentid")
	public java.lang.String getMemberagentid() {
		return memberagentid;
	}

	public void setMemberagentid(java.lang.String memberagentid) {
		this.memberagentid = memberagentid;
	}

	public String toString() {
		return new StringBuffer()
			.append("投注代理退佣表id[").append(getId()).append("],")
			.append("代理id[").append(getAgentid()).append("],")
			.append("用户id2[").append(getMemberid2()).append("],")
			.append("佣金[").append(getCommission()).append("],")
			.append("结算时间[").append(getSettlementtime()).append("],")
			.append("投注订单id[").append(getOrderid()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetCommission == false) return false;
		if(this == obj) return true;
		BetCommission other = (BetCommission)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
