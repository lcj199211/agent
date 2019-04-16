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
 * @version  2017-12-25 15:08:35
 * @see org.springrain.lottery.entity.BetAgentwithdraw
 */
@Table(name="bet_agentwithdraw")
public class BetAgentwithdraw  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetAgentwithdraw";
	public static final String ALIAS_ID = "代理提现订单号";
	public static final String ALIAS_AGENTID = "代理ID";
	public static final String ALIAS_APPLICATIONTIME = "申请时间";
	public static final String ALIAS_AUDITTIME = "审核时间";
	public static final String ALIAS_BWCS = "提现前分";
	public static final String ALIAS_AWCS = "提现后分";
	public static final String ALIAS_MONEY = "提现金额";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_STATE = "状态： 0:已申请 1:取消 2:已处理";
	public static final String ALIAS_OPERATOR = "operator";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
	public static final String ALIAS_REMARK = "备注";
    */
	//date formats
	//public static final String FORMAT_APPLICATIONTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_AUDITTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 代理提现订单号
	 */
	private java.lang.String id;
	/**
	 * 代理ID
	 */
	private java.lang.String agentid;
	/**
	 * 申请时间
	 */
	private java.util.Date applicationtime;
	/**
	 * 审核时间
	 */
	private java.util.Date audittime;
	/**
	 * 提现前分
	 */
	private java.math.BigDecimal bwcs;
	/**
	 * 提现后分
	 */
	private java.math.BigDecimal awcs;
	/**
	 * 提现金额
	 */
	private java.math.BigDecimal money;
	/**
	 * 真实姓名
	 */
	private java.lang.String realname;
	/**
	 * 状态： 0:已申请 1:取消 2:已处理
	 */
	private java.lang.Integer state;
	/**
	 * operator
	 */
	private java.lang.String operator;
	/**
	 * 代理商父级id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商id 从根节点开始
	 */
	private java.lang.String agentparentids;
	
	/**
	   银行
	*/
	private java.lang.String bank;
	/**
	   提现方式
	*/
	private java.lang.Integer paytype;

	
	/**
	 * 备注
	 */
	private java.lang.String remark;
	private java.lang.String agentnickname;
	private java.lang.String agentaccount;
	
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="agentnickname=:BetAgentwithdraw_agentnickname")
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}
	@WhereSQL(sql="agentaccount=:BetAgentwithdraw_agentaccount")
	public java.lang.String getAgentaccount() {
		return agentaccount;
	}

	public void setAgentaccount(java.lang.String agentaccount) {
		this.agentaccount = agentaccount;
	}

	public BetAgentwithdraw(){
	}

	public BetAgentwithdraw(
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
     @WhereSQL(sql="id=:BetAgentwithdraw_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentwithdraw_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
		/*
	public String getapplicationtimeString() {
		return DateUtils.convertDate2String(FORMAT_APPLICATIONTIME, getapplicationtime());
	}
	public void setapplicationtimeString(String value) throws ParseException{
		setapplicationtime(DateUtils.convertString2Date(FORMAT_APPLICATIONTIME,value));
	}*/
	
	public void setApplicationtime(java.util.Date value) {
		this.applicationtime = value;
	}
	
     @WhereSQL(sql="applicationtime=:BetAgentwithdraw_applicationtime")
	public java.util.Date getApplicationtime() {
		return this.applicationtime;
	}
		/*
	public String getaudittimeString() {
		return DateUtils.convertDate2String(FORMAT_AUDITTIME, getaudittime());
	}
	public void setaudittimeString(String value) throws ParseException{
		setaudittime(DateUtils.convertString2Date(FORMAT_AUDITTIME,value));
	}*/
	
	public void setAudittime(java.util.Date value) {
		this.audittime = value;
	}
	
     @WhereSQL(sql="audittime=:BetAgentwithdraw_audittime")
	public java.util.Date getAudittime() {
		return this.audittime;
	}
	public void setBwcs(java.math.BigDecimal value) {
		this.bwcs = value;
	}
	
     @WhereSQL(sql="bwcs=:BetAgentwithdraw_bwcs")
	public java.math.BigDecimal getBwcs() {
		return this.bwcs;
	}
	public void setAwcs(java.math.BigDecimal value) {
		this.awcs = value;
	}
	
     @WhereSQL(sql="awcs=:BetAgentwithdraw_awcs")
	public java.math.BigDecimal getAwcs() {
		return this.awcs;
	}
	public void setMoney(java.math.BigDecimal value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetAgentwithdraw_money")
	public java.math.BigDecimal getMoney() {
		return this.money;
	}
	public void setRealname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.realname = value;
	}
	
     @WhereSQL(sql="realname=:BetAgentwithdraw_realname")
	public java.lang.String getRealname() {
		return this.realname;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetAgentwithdraw_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setOperator(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operator = value;
	}
	
     @WhereSQL(sql="operator=:BetAgentwithdraw_operator")
	public java.lang.String getOperator() {
		return this.operator;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetAgentwithdraw_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetAgentwithdraw_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetAgentwithdraw_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("代理提现订单号[").append(getId()).append("],")
			.append("代理ID[").append(getAgentid()).append("],")
			.append("申请时间[").append(getApplicationtime()).append("],")
			.append("审核时间[").append(getAudittime()).append("],")
			.append("提现前分[").append(getBwcs()).append("],")
			.append("提现后分[").append(getAwcs()).append("],")
			.append("提现金额[").append(getMoney()).append("],")
			.append("真实姓名[").append(getRealname()).append("],")
			.append("状态： 0:已申请 1:取消 2:已处理[").append(getState()).append("],")
			.append("operator[").append(getOperator()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentwithdraw == false) return false;
		if(this == obj) return true;
		BetAgentwithdraw other = (BetAgentwithdraw)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
    @WhereSQL(sql="bank=:BetAgentwithdraw_bank")
	public java.lang.String getBank() {
    	
		return bank;
	}

	public void setBank(java.lang.String value) {
		if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bank = value;
	}
    @WhereSQL(sql="paytype=:BetAgentwithdraw_paytype")
	public java.lang.Integer getPaytype() {
    	if(paytype==null){
    		return 200;
    	}else{
    		return paytype;
    	}
	}

	public void setPaytype(java.lang.Integer paytype) {
		this.paytype = paytype;
	}
}

	
