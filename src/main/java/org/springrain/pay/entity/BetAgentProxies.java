package org.springrain.pay.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
import org.springrain.lottery.entity.BetWithdrawcash;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-25 15:08:35
 * @see org.springrain.lottery.entity.BetAgentProxies
 */
@Table(name="bet_agentproxies")
public class BetAgentProxies  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 提现表ID
	 */
	private java.lang.String id;
	/**
	 * 提现人ID
	 */
	private java.lang.String memberid;
	/**
	 * 开户市
	 */
	private java.lang.String orderid;
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
	 * 银行编号
	 */
	private java.lang.String bankcode;
	/**
	 * 账户名
	 */
	private java.lang.String accountname;
	/**
	 * 账户卡号
	 */
	private java.lang.String accountno;
	/**
	 * 身份证号
	 */
	private java.lang.String certnum;
	/**
	 * 提现金额
	 */
	private java.lang.Double money;
	/**
	 * 银行名称
	 */
	private java.lang.String bankname;
	/**
	 * 状态
	 */
	private java.lang.Integer state;
	/**
	 * 联行号
	 */
	private java.lang.String cnaps;
	/**
	 * 提现失败原因
	 */
	private java.lang.String failreason;
	
	private java.lang.String mobile;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	/**
	 * 开户行
	 */
	private java.lang.String bankbranch;
	/**
	 * 开户省
	 */
	private java.lang.String bankprov;
	/**
	 * 开户市
	 */
	private java.lang.String bankcity;
	/**
	 * 账户类型 账户类型  0 对公 1对私
	 */
	private java.lang.Integer accounttype;

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


	@WhereSQL(sql="mobile=:BetWithdrawcash_mobile")
	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}


	@WhereSQL(sql="orderid=:BetWithdrawcash_orderid")
	public java.lang.String getOrderid() {
		return orderid;
	}

	public void setOrderid(java.lang.String orderid) {
		this.orderid = orderid;
	}

	@WhereSQL(sql="bankcode=:BetWithdrawcash_bankcode")
	public java.lang.String getBankcode() {
		return bankcode;
	}

	public void setBankcode(java.lang.String bankcode) {
		this.bankcode = bankcode;
	}

	@WhereSQL(sql="accountname=:BetWithdrawcash_accountname")
	public java.lang.String getAccountname() {
		return accountname;
	}

	public void setAccountname(java.lang.String accountname) {
		this.accountname = accountname;
	}

	@WhereSQL(sql="bankname=:BetWithdrawcash_bankname")
	public java.lang.String getBankname() {
		return bankname;
	}

	public void setBankname(java.lang.String bankname) {
		this.bankname = bankname;
	}

	@WhereSQL(sql="cnaps=:BetWithdrawcash_cnaps")
	public java.lang.String getCnaps() {
		return cnaps;
	}

	public void setCnaps(java.lang.String cnaps) {
		this.cnaps = cnaps;
	}

	@WhereSQL(sql="bankbranch=:BetWithdrawcash_bankbranch")
	public java.lang.String getBankbranch() {
		return bankbranch;
	}

	public void setBankbranch(java.lang.String bankbranch) {
		this.bankbranch = bankbranch;
	}

	@WhereSQL(sql="bankprov=:BetWithdrawcash_bankprov")
	public java.lang.String getBankprov() {
		return bankprov;
	}

	public void setBankprov(java.lang.String bankprov) {
		this.bankprov = bankprov;
	}

	@WhereSQL(sql="bankcity=:BetWithdrawcash_bankcity")
	public java.lang.String getBankcity() {
		return bankcity;
	}

	public void setBankcity(java.lang.String bankcity) {
		this.bankcity = bankcity;
	}

	@WhereSQL(sql="accounttype=:BetWithdrawcash_accounttype")
	public java.lang.Integer getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(java.lang.Integer accounttype) {
		this.accounttype = accounttype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BetAgentProxies(){
	}

	public BetAgentProxies(
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

	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetWithdrawcash_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetWithdrawcash_state")
	public java.lang.Integer getState() {
		return this.state;
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
  
	@Override
	public String toString() {
		return new StringBuffer()
			.append("提现表ID[").append(getId()).append("],")
			.append("提现人ID[").append(getMemberid()).append("],")
			.append("商户订单号[").append(getOrderid()).append("],")
			.append("提现时间[").append(getApplicationtime()).append("],")
			.append("审核时间[").append(getAudittime()).append("],")
			.append("提现前分[").append(getBwcs()).append("],")
			.append("冻结分[").append(getFreezingscore()).append("],")
			.append("提现后分[").append(getAwcs()).append("],")
			.append("提现金额[").append(getMoney()).append("],")
			.append("身份证号[").append(getCertnum()).append("],")
			.append("账户名[").append(getAccountname()).append("],")
			.append("账户卡号[").append(getAccountno()).append("],")
			.append("银行编号[").append(getBankcode()).append("],")
			.append("银行名称[").append(getBankname()).append("],")
			.append("状态[").append(getState()).append("],")
			.append("联行号[").append(getCnaps()).append("],")
			.append("开户行[").append(getBankbranch()).append("],")
			.append("开户省[").append(getBankprov()).append("],")
			.append("开户市[").append(getBankcity()).append("],")
			.append("失败原因[").append(getFailreason()).append("],")
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
		if(obj instanceof BetAgentProxies == false) return false;
		if(this == obj) return true;
		BetAgentProxies other = (BetAgentProxies)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

    @WhereSQL(sql="accountno=:BetWithdrawcash_accountno")
	public java.lang.String getAccountno() {
		return accountno;
	}

	public void setAccountno(java.lang.String accountno) {
		this.accountno = accountno;
	}

    @WhereSQL(sql="certnum=:BetWithdrawcash_certnum")
	public java.lang.String getCertnum() {
		return certnum;
	}

	public void setCertnum(java.lang.String certnum) {
		this.certnum = certnum;
	}
}

	
