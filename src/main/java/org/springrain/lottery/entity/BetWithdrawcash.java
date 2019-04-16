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
 * @version  2017-03-27 09:58:21
 * @see org.springrain.lottery.entity.BetWithdrawcash
 */
@Table(name="bet_withdrawcash")
public class BetWithdrawcash  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "提现";
	public static final String ALIAS_ID = "提现表ID";
	public static final String ALIAS_MEMBERID = "会员ID";
	public static final String ALIAS_APPLICATIONTIME = "申请时间";
	public static final String ALIAS_AUDITTIME = "审核时间";
	public static final String ALIAS_BWCS = "提现前分";
	public static final String ALIAS_FREEZINGSCORE = "冻结分";
	public static final String ALIAS_AWCS = "提现后分";
	public static final String ALIAS_PAYMENTMETHOD = "收款方式";
	public static final String ALIAS_ACCOUNT = "账号";
	public static final String ALIAS_MONEY = "体现金额";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_SOURCE = "来源";
    */
	//date formats
	//public static final String FORMAT_APPLICATIONTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_AUDITTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 提现表ID
	 */
	private java.lang.String id;
	/**
	 * 会员ID
	 */
	private java.lang.String memberid;
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
	private java.lang.Double bwcs;
	/**
	 * 冻结分
	 */
	private java.lang.Double freezingscore;
	/**
	 * 提现后分
	 */
	private java.lang.Double awcs;
	/**
	 * 收款方式
	 */
	private java.lang.String paymentmethod;
	/**
	 * 账号
	 */
	private java.lang.String account;
	/**
	 * 体现金额
	 */
	private java.lang.Double money;
	/**
	 * 真实姓名
	 */
	private java.lang.String realname;
	/**
	 * 状态
	 */
	private java.lang.Integer state;
	/**
	 * 来源
	 */
	private java.lang.String source;
	/**
	 * 取消提现的原因
	 */
	private java.lang.String failreason;
	/**
	 * 提现申请序号
	 */
	private java.lang.String num;
	
	private java.lang.Integer memberid2; 
	private java.lang.String qq;
	private java.lang.String mobile;
	private java.lang.String operator;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	private java.lang.String bankofdeposit;
	@WhereSQL(sql="bankofdeposit=:BetWithdrawcash_bankofdeposit")
	public java.lang.String getBankofdeposit() {
		return bankofdeposit;
	}

	public void setBankofdeposit(java.lang.String bankofdeposit) {
		this.bankofdeposit = bankofdeposit;
	}

	@WhereSQL(sql="agentid=:BetWithdrawcash_agentid")
	 public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetWithdrawcash_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetWithdrawcash_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	@WhereSQL(sql="operator=:BetWithdrawcash_operator")
	public java.lang.String getOperator() {
		return operator;
	}

	public void setOperator(java.lang.String operator) {
		this.operator = operator;
	}

	//columns END 数据库字段结束
	@Transient
	public java.lang.String getQq() {
		return qq;
	}

	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}
	@Transient
	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	//concstructor
	@Transient
	public java.lang.Integer getMemberid2() {
		return memberid2;
	}

	public void setMemberid2(java.lang.Integer memberid2) {
		this.memberid2 = memberid2;
	}

	public BetWithdrawcash(){
	}

	public BetWithdrawcash(
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
     @WhereSQL(sql="id=:BetWithdrawcash_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setMemberid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetWithdrawcash_memberid")
	public java.lang.String getMemberid() {
		return this.memberid;
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
	
     @WhereSQL(sql="applicationtime=:BetWithdrawcash_applicationtime")
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
	
     @WhereSQL(sql="audittime=:BetWithdrawcash_audittime")
	public java.util.Date getAudittime() {
		return this.audittime;
	}
	public void setBwcs(java.lang.Double value) {
		this.bwcs = value;
	}
	
     @WhereSQL(sql="bwcs=:BetWithdrawcash_bwcs")
	public java.lang.Double getBwcs() {
		return this.bwcs;
	}
	public void setFreezingscore(java.lang.Double value) {
		this.freezingscore = value;
	}
	
     @WhereSQL(sql="freezingscore=:BetWithdrawcash_freezingscore")
	public java.lang.Double getFreezingscore() {
		return this.freezingscore;
	}
	public void setAwcs(java.lang.Double value) {
		this.awcs = value;
	}
	
     @WhereSQL(sql="awcs=:BetWithdrawcash_awcs")
	public java.lang.Double getAwcs() {
		return this.awcs;
	}
	public void setPaymentmethod(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.paymentmethod = value;
	}
	
     @WhereSQL(sql="paymentmethod=:BetWithdrawcash_paymentmethod")
	public java.lang.String getPaymentmethod() {
		return this.paymentmethod;
	}
	public void setAccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.account = value;
	}
	
     @WhereSQL(sql="account=:BetWithdrawcash_account")
	public java.lang.String getAccount() {
		return this.account;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetWithdrawcash_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setRealname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.realname = value;
	}
	
     @WhereSQL(sql="realname=:BetWithdrawcash_realname")
	public java.lang.String getRealname() {
		return this.realname;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetWithdrawcash_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setSource(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.source = value;
	}
	
     @WhereSQL(sql="source=:BetWithdrawcash_source")
	public java.lang.String getSource() {
		return this.source;
	}
	
     public void setFailreason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.failreason = value;
	}
	
     @WhereSQL(sql="failreason=:BetWithdrawcash_failreason")
	public java.lang.String getFailreason() {
		return this.failreason;
	}
     
    public void setNum(java.lang.String value) {
    	if(StringUtils.isNotBlank(value)){
			 value=value.trim();
    	}
		this.num = value;
	}
	
    @WhereSQL(sql="num=:BetWithdrawcash_num")
	public java.lang.String getNum() {
		return this.num;
	}
  
	@Override
	public String toString() {
		return new StringBuffer()
			.append("提现表ID[").append(getId()).append("],")
			.append("提现申请序号[").append(getNum()).append("],")
			.append("会员ID[").append(getMemberid()).append("],")
			.append("申请时间[").append(getApplicationtime()).append("],")
			.append("审核时间[").append(getAudittime()).append("],")
			.append("提现前分[").append(getBwcs()).append("],")
			.append("冻结分[").append(getFreezingscore()).append("],")
			.append("提现后分[").append(getAwcs()).append("],")
			.append("收款方式[").append(getPaymentmethod()).append("],")
			.append("账号[").append(getAccount()).append("],")
			.append("体现金额[").append(getMoney()).append("],")
			.append("真实姓名[").append(getRealname()).append("],")
			.append("状态[").append(getState()).append("],")
			.append("来源[").append(getSource()).append("],")
			.append("取消提现的原因[").append(getFailreason()).append("],")
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
		if(obj instanceof BetWithdrawcash == false) return false;
		if(this == obj) return true;
		BetWithdrawcash other = (BetWithdrawcash)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
