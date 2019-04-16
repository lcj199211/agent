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
 * @version  2018-02-06 13:40:17
 * @see org.springrain.lottery.entity.BetChannelcharge
 */
@Table(name="bet_channelcharge")
public class BetChannelcharge  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "代理充值表";
	public static final String ALIAS_ID = "渠道充值订单编号";
	public static final String ALIAS_AGENTID = "代理id";
	public static final String ALIAS_AGENTACCOUNT = "代理账号";
	public static final String ALIAS_AGENTNICKNAME = "代理昵称";
	public static final String ALIAS_SOURCE = "来源";
	public static final String ALIAS_BRS = "充值前分";
	public static final String ALIAS_ARS = "充值后分";
	public static final String ALIAS_MONEY = "金额";
	public static final String ALIAS_STATE = "状态0:未处理1:充值失败2:已处理";
	public static final String ALIAS_SUBMITTIME = "提交时间";
	public static final String ALIAS_RECHARGETIME = "充值时间";
	public static final String ALIAS_OPERATOR = "操作人";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
	public static final String ALIAS_CHANNELID = "channelid";
	public static final String ALIAS_CHANNELNAME = "channelname";
    */
	//date formats
	//public static final String FORMAT_SUBMITTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_RECHARGETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 渠道充值订单编号
	 */
	private java.lang.String id;
	/**
	 * 代理id
	 */
	private java.lang.String agentid;
	/**
	 * 代理账号
	 */
	private java.lang.String agentaccount;
	/**
	 * 代理昵称
	 */
	private java.lang.String agentnickname;
	/**
	 * 来源
	 */
	private java.lang.String source;
	/**
	 * 充值前分
	 */
	private java.math.BigDecimal brs;
	/**
	 * 充值后分
	 */
	private java.math.BigDecimal ars;
	/**
	 * 金额
	 */
	private java.math.BigDecimal money;
	/**
	 * 状态0:未处理1:充值失败2:已处理
	 */
	private java.lang.Integer state;
	/**
	 * 提交时间
	 */
	private java.util.Date submittime;
	/**
	 * 充值时间
	 */
	private java.util.Date rechargetime;
	/**
	 * 操作人
	 */
	private java.lang.String operator;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 代理商父级id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商id 从根节点开始
	 */
	private java.lang.String agentparentids;
	/**
	 * channelid
	 */
	private java.lang.Integer channelid;
	/**
	 * channelname
	 */
	private java.lang.String channelname;
	//columns END 数据库字段结束
	
	//concstructor

	public BetChannelcharge(){
	}

	public BetChannelcharge(
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
     @WhereSQL(sql="id=:BetChannelcharge_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetChannelcharge_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentaccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentaccount = value;
	}
	
     @WhereSQL(sql="agentaccount=:BetChannelcharge_agentaccount")
	public java.lang.String getAgentaccount() {
		return this.agentaccount;
	}
	public void setAgentnickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentnickname = value;
	}
	
     @WhereSQL(sql="agentnickname=:BetChannelcharge_agentnickname")
	public java.lang.String getAgentnickname() {
		return this.agentnickname;
	}
	public void setSource(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.source = value;
	}
	
     @WhereSQL(sql="source=:BetChannelcharge_source")
	public java.lang.String getSource() {
		return this.source;
	}
	public void setBrs(java.math.BigDecimal value) {
		this.brs = value;
	}
	
     @WhereSQL(sql="brs=:BetChannelcharge_brs")
	public java.math.BigDecimal getBrs() {
		return this.brs;
	}
	public void setArs(java.math.BigDecimal value) {
		this.ars = value;
	}
	
     @WhereSQL(sql="ars=:BetChannelcharge_ars")
	public java.math.BigDecimal getArs() {
		return this.ars;
	}
	public void setMoney(java.math.BigDecimal value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetChannelcharge_money")
	public java.math.BigDecimal getMoney() {
		return this.money;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetChannelcharge_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/*
	public String getsubmittimeString() {
		return DateUtils.convertDate2String(FORMAT_SUBMITTIME, getsubmittime());
	}
	public void setsubmittimeString(String value) throws ParseException{
		setsubmittime(DateUtils.convertString2Date(FORMAT_SUBMITTIME,value));
	}*/
	
	public void setSubmittime(java.util.Date value) {
		this.submittime = value;
	}
	
     @WhereSQL(sql="submittime=:BetChannelcharge_submittime")
	public java.util.Date getSubmittime() {
		return this.submittime;
	}
		/*
	public String getrechargetimeString() {
		return DateUtils.convertDate2String(FORMAT_RECHARGETIME, getrechargetime());
	}
	public void setrechargetimeString(String value) throws ParseException{
		setrechargetime(DateUtils.convertString2Date(FORMAT_RECHARGETIME,value));
	}*/
	
	public void setRechargetime(java.util.Date value) {
		this.rechargetime = value;
	}
	
     @WhereSQL(sql="rechargetime=:BetChannelcharge_rechargetime")
	public java.util.Date getRechargetime() {
		return this.rechargetime;
	}
	public void setOperator(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operator = value;
	}
	
     @WhereSQL(sql="operator=:BetChannelcharge_operator")
	public java.lang.String getOperator() {
		return this.operator;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetChannelcharge_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetChannelcharge_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetChannelcharge_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setChannelid(java.lang.Integer value) {
		this.channelid = value;
	}
	
     @WhereSQL(sql="channelid=:BetChannelcharge_channelid")
	public java.lang.Integer getChannelid() {
		return this.channelid;
	}
	public void setChannelname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.channelname = value;
	}
	
     @WhereSQL(sql="channelname=:BetChannelcharge_channelname")
	public java.lang.String getChannelname() {
		return this.channelname;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("渠道充值订单编号[").append(getId()).append("],")
			.append("代理id[").append(getAgentid()).append("],")
			.append("代理账号[").append(getAgentaccount()).append("],")
			.append("代理昵称[").append(getAgentnickname()).append("],")
			.append("来源[").append(getSource()).append("],")
			.append("充值前分[").append(getBrs()).append("],")
			.append("充值后分[").append(getArs()).append("],")
			.append("金额[").append(getMoney()).append("],")
			.append("状态0:未处理1:充值失败2:已处理[").append(getState()).append("],")
			.append("提交时间[").append(getSubmittime()).append("],")
			.append("充值时间[").append(getRechargetime()).append("],")
			.append("操作人[").append(getOperator()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.append("channelid[").append(getChannelid()).append("],")
			.append("channelname[").append(getChannelname()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetChannelcharge == false) return false;
		if(this == obj) return true;
		BetChannelcharge other = (BetChannelcharge)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
