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
 * @version  2018-12-04 15:36:40
 * @see org.springrain.lottery.entity.BetPayforanother
 */
@Table(name="bet_payforanother")
public class BetPayforanother  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetPayforanother";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TXNAMT = "商户订单金额";
	public static final String ALIAS_ACCOUNTNO = "卡号";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_TRANDATE = "交易日期";
	public static final String ALIAS_TRANID = "交易流水号";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_STATE = "状态： 0:处理中 1:取消 2:成功 ";
	public static final String ALIAS_WEITHDRAWCASHID = "bet_withdrawcash表主键id";
	public static final String ALIAS_COMPANY = "company";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 商户订单金额
	 */
	private java.lang.Double txnAmt;
	/**
	 * 卡号
	 */
	private java.lang.String accountNo;
	/**
	 * 订单号
	 */
	private java.lang.String orderId;
	/**
	 * 交易日期
	 */
	private java.lang.String tranDate;
	/**
	 * 交易流水号
	 */
	private java.lang.String tranId;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 状态： 0:处理中 1:取消 2:成功 
	 */
	private java.lang.Integer state;
	/**
	 * bet_withdrawcash表主键id
	 */
	private java.lang.String weithdrawcashId;
	/**
	 * company
	 */
	private java.lang.String company;
	//columns END 数据库字段结束
	
	//concstructor

	public BetPayforanother(){
	}

	public BetPayforanother(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetPayforanother_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setTxnAmt(java.lang.Double value) {
		this.txnAmt = value;
	}
	
     @WhereSQL(sql="txnAmt=:BetPayforanother_txnAmt")
	public java.lang.Double getTxnAmt() {
		return this.txnAmt;
	}
	public void setAccountNo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.accountNo = value;
	}
	
     @WhereSQL(sql="accountNo=:BetPayforanother_accountNo")
	public java.lang.String getAccountNo() {
		return this.accountNo;
	}
	public void setOrderId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderId = value;
	}
	
     @WhereSQL(sql="orderId=:BetPayforanother_orderId")
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	public void setTranDate(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tranDate = value;
	}
	
     @WhereSQL(sql="tranDate=:BetPayforanother_tranDate")
	public java.lang.String getTranDate() {
		return this.tranDate;
	}
	public void setTranId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tranId = value;
	}
	
     @WhereSQL(sql="tranId=:BetPayforanother_tranId")
	public java.lang.String getTranId() {
		return this.tranId;
	}
		/*
	public String getcreatetimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreatetime());
	}
	public void setcreatetimeString(String value) throws ParseException{
		setcreatetime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
     @WhereSQL(sql="createtime=:BetPayforanother_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetPayforanother_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setWeithdrawcashId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.weithdrawcashId = value;
	}
	
     @WhereSQL(sql="weithdrawcashId=:BetPayforanother_weithdrawcashId")
	public java.lang.String getWeithdrawcashId() {
		return this.weithdrawcashId;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:BetPayforanother_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("商户订单金额[").append(getTxnAmt()).append("],")
			.append("卡号[").append(getAccountNo()).append("],")
			.append("订单号[").append(getOrderId()).append("],")
			.append("交易日期[").append(getTranDate()).append("],")
			.append("交易流水号[").append(getTranId()).append("],")
			.append("创建时间[").append(getCreatetime()).append("],")
			.append("状态： 0:处理中 1:取消 2:成功 [").append(getState()).append("],")
			.append("bet_withdrawcash表主键id[").append(getWeithdrawcashId()).append("],")
			.append("company[").append(getCompany()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetPayforanother == false) return false;
		if(this == obj) return true;
		BetPayforanother other = (BetPayforanother)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
