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
 * @version  2018-09-06 10:24:53
 * @see org.springrain.lottery.entity.SoccerAllotTicket
 */
@Table(name="soccer_allot_ticket")
public class SoccerAllotTicket  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerAllotTicket";
	public static final String ALIAS_ID = "足球票务分配表id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_MONEY = "订单金额";
	public static final String ALIAS_STATE = "状态1.已出票 2.未处理 3.不出票";
	public static final String ALIAS_OPERATER = "操作人";
	public static final String ALIAS_COMPANY = "公司";
	public static final String ALIAS_DATETIME = "处理时间";
    */
	//date formats
	//public static final String FORMAT_DATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 足球票务分配表id
	 */
	private java.lang.Integer id;
	/**
	 * 订单号
	 */
	private java.lang.String orderid;
	/**
	 * 订单金额
	 */
	private java.math.BigDecimal money;
	/**
	 * 状态1.已出票 2.未处理 3.不出票
	 */
	private java.lang.Integer state;
	/**
	 * 操作人
	 */
	private java.lang.String operater;
	/**
	 * 公司
	 */
	private java.lang.String company;
	/**
	 * 处理时间
	 */
	private java.util.Date datetime;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerAllotTicket(){
	}

	public SoccerAllotTicket(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:SoccerAllotTicket_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:SoccerAllotTicket_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setMoney(java.math.BigDecimal value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:SoccerAllotTicket_money")
	public java.math.BigDecimal getMoney() {
		return this.money;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:SoccerAllotTicket_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setOperater(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operater = value;
	}
	
     @WhereSQL(sql="operater=:SoccerAllotTicket_operater")
	public java.lang.String getOperater() {
		return this.operater;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:SoccerAllotTicket_company")
	public java.lang.String getCompany() {
		return this.company;
	}
		/*
	public String getdatetimeString() {
		return DateUtils.convertDate2String(FORMAT_DATETIME, getdatetime());
	}
	public void setdatetimeString(String value) throws ParseException{
		setdatetime(DateUtils.convertString2Date(FORMAT_DATETIME,value));
	}*/
	
	public void setDatetime(java.util.Date value) {
		this.datetime = value;
	}
	
     @WhereSQL(sql="datetime=:SoccerAllotTicket_datetime")
	public java.util.Date getDatetime() {
		return this.datetime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("足球票务分配表id[").append(getId()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("订单金额[").append(getMoney()).append("],")
			.append("状态1.已出票 2.未处理 3.不出票[").append(getState()).append("],")
			.append("操作人[").append(getOperater()).append("],")
			.append("公司[").append(getCompany()).append("],")
			.append("处理时间[").append(getDatetime()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SoccerAllotTicket == false) return false;
		if(this == obj) return true;
		SoccerAllotTicket other = (SoccerAllotTicket)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
