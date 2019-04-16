package org.springrain.lottery.entity;

import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

/**
 * 收款码
 * 
 * @author AA
 * @version 2019-03-19 11:28:46
 * @see org.springrain.news.entity.BetAgentPaymentCode
 */
@Table(name = "bet_agent_payment_code")
public class BetAgentPaymentCode extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "BetAgentPaymentCode"; public
	 * static final String ALIAS_ID = "id"; public static final String
	 * ALIAS_AMOUNT = "收款金额"; public static final String ALIAS_URL = "二维码";
	 * public static final String ALIAS_COMPANY = "代理"; public static final
	 * String ALIAS_PAYTYPE = "支付类型"; public static final String ALIAS_STATE =
	 * "1正常 3禁用"; public static final String ALIAS_CREATE_TIME = "创建时间"; public
	 * static final String ALIAS_MODIFY_TIME = "修改时间"; public static final
	 * String ALIAS_CUSTOMERID = "收款账户";
	 */
	// date formats
	// public static final String FORMAT_CREATE_TIME =
	// DateUtils.DATETIME_FORMAT;
	// public static final String FORMAT_MODIFY_TIME =
	// DateUtils.DATETIME_FORMAT;

	// columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 收款金额
	 */
	private java.math.BigDecimal amount;
	/**
	 * 二维码
	 */
	private java.lang.String url;
	/**
	 * 代理
	 */
	private java.lang.String company;
	/**
	 * 支付类型
	 */
	private java.lang.Integer paytype;
	/**
	 * 1正常 3禁用
	 */
	private java.lang.Integer state;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 修改时间
	 */
	private java.util.Date modifytime;
	/**
	 * 收款账户
	 */
	private java.lang.String customerid;
	/**
	 * 范围，50,100,200,500，值为50，则amount收款金额应该在49.1到50.9内
	 */
	private java.lang.Integer scope;
	// columns END 数据库字段结束

	// concstructor

	public BetAgentPaymentCode() {
	}

	public BetAgentPaymentCode(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:BetAgentPaymentCode_id")
	public java.lang.String getId() {
		return this.id;
	}

	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}

	@WhereSQL(sql = "amount=:BetAgentPaymentCode_amount")
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}

	public void setUrl(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.url = value;
	}

	@WhereSQL(sql = "url=:BetAgentPaymentCode_url")
	public java.lang.String getUrl() {
		return this.url;
	}

	public void setCompany(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.company = value;
	}

	@WhereSQL(sql = "company=:BetAgentPaymentCode_company")
	public java.lang.String getCompany() {
		return this.company;
	}

	public void setPaytype(java.lang.Integer value) {
		this.paytype = value;
	}

	@WhereSQL(sql = "paytype=:BetAgentPaymentCode_paytype")
	public java.lang.Integer getPaytype() {
		return this.paytype;
	}

	public void setState(java.lang.Integer value) {
		this.state = value;
	}

	@WhereSQL(sql = "state=:BetAgentPaymentCode_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	/*
	 * public String getcreate_timeString() { return
	 * DateUtils.convertDate2String(FORMAT_CREATE_TIME, getcreate_time()); }
	 * public void setcreate_timeString(String value) throws ParseException{
	 * setcreate_time(DateUtils.convertString2Date(FORMAT_CREATE_TIME,value)); }
	 */

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	@WhereSQL(sql = "create_time=:BetAgentPaymentCode_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	/*
	 * public String getmodify_timeString() { return
	 * DateUtils.convertDate2String(FORMAT_MODIFY_TIME, getmodify_time()); }
	 * public void setmodify_timeString(String value) throws ParseException{
	 * setmodify_time(DateUtils.convertString2Date(FORMAT_MODIFY_TIME,value)); }
	 */

	public void setModifytime(java.util.Date value) {
		this.modifytime = value;
	}

	@WhereSQL(sql = "modify_time=:BetAgentPaymentCode_modifytime")
	public java.util.Date getModifytime() {
		return this.modifytime;
	}

	public void setCustomerid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.customerid = value;
	}

	@WhereSQL(sql = "customerid=:BetAgentPaymentCode_customerid")
	public java.lang.String getCustomerid() {
		return this.customerid;
	}

	public void setScope(java.lang.Integer value) {
		this.scope = value;
	}

	@WhereSQL(sql = "scope=:BetAgentPaymentCode_scope")
	public java.lang.Integer getScope() {
		return this.scope;
	}

	public String toString() {
		return new StringBuffer().append("id[").append(getId()).append("],").append("收款金额[").append(getAmount())
				.append("],").append("二维码[").append(getUrl()).append("],").append("代理[").append(getCompany())
				.append("],").append("支付类型[").append(getPaytype()).append("],").append("1正常 3禁用[").append(getState())
				.append("],").append("创建时间[").append(getCreatetime()).append("],").append("修改时间[")
				.append(getModifytime()).append("],").append("收款账户[").append(getCustomerid()).append("],")
				.append("范围，50,100,200,500，值为50，则amount收款金额应该在49.1到50.9内[").append(getScope()).append("],").toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof BetAgentPaymentCode == false)
			return false;
		if (this == obj)
			return true;
		BetAgentPaymentCode other = (BetAgentPaymentCode) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
