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
 * @version  2017-05-23 15:55:19
 * @see org.springrain.lottery.entity.BetAgentAccount
 */
@Table(name="bet_agent_account")
public class BetAgentAccount  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetAgentAccount";
	public static final String ALIAS_ID = "代理商账户表id";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_PARENTID = "代理商父级id";
	public static final String ALIAS_PARENTIDS = "上级代理商ID 从根节点开始到父级用逗号隔开";
	public static final String ALIAS_ACCOUNTNAME = "支付方式";
	public static final String ALIAS_ACCOUNTNUM = "支付账号";
	public static final String ALIAS_BANKOFDEPOSIT = "开户行";
    */
	//date formats
	
	//columns START
	/**
	 * 代理商账户表id
	 */
	private java.lang.Integer id;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父级id
	 */
	private java.lang.String parentid;
	/**
	 * 上级代理商ID 从根节点开始到父级用逗号隔开
	 */
	private java.lang.String parentids;
	/**
	 * 支付方式
	 */
	private java.lang.String accountname;
	/**
	 * 支付账号
	 */
	private java.lang.String accountnum;
	/**
	 * 开户行
	 */
	private java.lang.String bankofdeposit;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAgentAccount(){
	}

	public BetAgentAccount(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentAccount_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentAccount_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setParentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.parentid = value;
	}
	
     @WhereSQL(sql="parentid=:BetAgentAccount_parentid")
	public java.lang.String getParentid() {
		return this.parentid;
	}
	public void setParentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.parentids = value;
	}
	
     @WhereSQL(sql="parentids=:BetAgentAccount_parentids")
	public java.lang.String getParentids() {
		return this.parentids;
	}
	public void setAccountname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.accountname = value;
	}
	
     @WhereSQL(sql="accountname=:BetAgentAccount_accountname")
	public java.lang.String getAccountname() {
		return this.accountname;
	}
	public void setAccountnum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.accountnum = value;
	}
	
     @WhereSQL(sql="accountnum=:BetAgentAccount_accountnum")
	public java.lang.String getAccountnum() {
		return this.accountnum;
	}
	public void setBankofdeposit(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bankofdeposit = value;
	}
	
     @WhereSQL(sql="bankofdeposit=:BetAgentAccount_bankofdeposit")
	public java.lang.String getBankofdeposit() {
		return this.bankofdeposit;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("代理商账户表id[").append(getId()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getParentid()).append("],")
			.append("上级代理商ID 从根节点开始到父级用逗号隔开[").append(getParentids()).append("],")
			.append("支付方式[").append(getAccountname()).append("],")
			.append("支付账号[").append(getAccountnum()).append("],")
			.append("开户行[").append(getBankofdeposit()).append("],")
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
		if(obj instanceof BetAgentAccount == false) return false;
		if(this == obj) return true;
		BetAgentAccount other = (BetAgentAccount)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
