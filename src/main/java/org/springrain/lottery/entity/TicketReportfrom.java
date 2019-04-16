package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-05-07 09:40:04
 * @see org.springrain.lottery.entity.TicketReportfrom
 */
@Table(name="ticket_reportfrom")
public class TicketReportfrom  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "TicketReportfrom";
	public static final String ALIAS_ID = "出票报表id";
	public static final String ALIAS_DATE = "日期";
	public static final String ALIAS_SOCCERSUCCESS = "足球成功数";
	public static final String ALIAS_SOCCERFAIL = "足球失败数";
	public static final String ALIAS_LQSUCCESS = "篮球成功";
	public static final String ALIAS_LQFAIL = "篮球失败";
	public static final String ALIAS_BJDCSUCCESS = "北单成功";
	public static final String ALIAS_BJDCFAIL = "北单失败";
	public static final String ALIAS_LOTTERYSUCCESS = "大乐透成功";
	public static final String ALIAS_LOTTERYFAIL = "大乐透失败";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 出票报表id
	 */
	private java.lang.Integer id;
	/**
	 * 日期
	 */
	private java.util.Date date;
	/**
	 * 足球成功数
	 */
	private java.math.BigDecimal soccersuccess;
	/**
	 * 足球失败数
	 */
	private java.math.BigDecimal soccerfail;
	/**
	 * 篮球成功
	 */
	private java.math.BigDecimal lqsuccess;
	/**
	 * 篮球失败
	 */
	private java.math.BigDecimal lqfail;
	/**
	 * 北单成功
	 */
	private java.math.BigDecimal bjdcsuccess;
	/**
	 * 北单失败
	 */
	private java.math.BigDecimal bjdcfail;
	/**
	 * 大乐透成功
	 */
	private java.math.BigDecimal lotterysuccess;
	/**
	 * 大乐透失败
	 */
	private java.math.BigDecimal lotteryfail;
	
	private String agentid;
	//columns END 数据库字段结束
	
	//concstructor

	public TicketReportfrom(){
	}

	public TicketReportfrom(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:TicketReportfrom_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:TicketReportfrom_date")
	public java.util.Date getDate() {
		return this.date;
	}
	public void setSoccersuccess(java.math.BigDecimal value) {
		this.soccersuccess = value;
	}
	
     @WhereSQL(sql="soccersuccess=:TicketReportfrom_soccersuccess")
	public java.math.BigDecimal getSoccersuccess() {
		return this.soccersuccess;
	}
	public void setSoccerfail(java.math.BigDecimal value) {
		this.soccerfail = value;
	}
	
     @WhereSQL(sql="soccerfail=:TicketReportfrom_soccerfail")
	public java.math.BigDecimal getSoccerfail() {
		return this.soccerfail;
	}
	public void setLqsuccess(java.math.BigDecimal value) {
		this.lqsuccess = value;
	}
	
     @WhereSQL(sql="lqsuccess=:TicketReportfrom_lqsuccess")
	public java.math.BigDecimal getLqsuccess() {
		return this.lqsuccess;
	}
	public void setLqfail(java.math.BigDecimal value) {
		this.lqfail = value;
	}
	
     @WhereSQL(sql="lqfail=:TicketReportfrom_lqfail")
	public java.math.BigDecimal getLqfail() {
		return this.lqfail;
	}
	public void setBjdcsuccess(java.math.BigDecimal value) {
		this.bjdcsuccess = value;
	}
	
     @WhereSQL(sql="bjdcsuccess=:TicketReportfrom_bjdcsuccess")
	public java.math.BigDecimal getBjdcsuccess() {
		return this.bjdcsuccess;
	}
	public void setBjdcfail(java.math.BigDecimal value) {
		this.bjdcfail = value;
	}
	
     @WhereSQL(sql="bjdcfail=:TicketReportfrom_bjdcfail")
	public java.math.BigDecimal getBjdcfail() {
		return this.bjdcfail;
	}
     
    public void setLotterysuccess(java.math.BigDecimal value) {
 		this.lotterysuccess = value;
 	}
 	
      @WhereSQL(sql="lotterysuccess=:TicketReportfrom_lotterysuccess")
 	public java.math.BigDecimal getLotterysuccess() {
 		return this.lotterysuccess;
 	}
 	public void setLotteryfail(java.math.BigDecimal value) {
 		this.lotteryfail = value;
 	}
 	
      @WhereSQL(sql="lotteryfail=:TicketReportfrom_lotteryfail")
 	public java.math.BigDecimal getLotteryfail() {
 		return this.lotteryfail;
 	}
     
     @WhereSQL(sql="agentid=:TicketReportfrom_agentid")
 	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String toString() {
		return new StringBuffer()
			.append("出票报表id[").append(getId()).append("],")
			.append("日期[").append(getDate()).append("],")
			.append("足球成功数[").append(getSoccersuccess()).append("],")
			.append("足球失败数[").append(getSoccerfail()).append("],")
			.append("篮球成功[").append(getLqsuccess()).append("],")
			.append("篮球失败[").append(getLqfail()).append("],")
			.append("北单成功[").append(getBjdcsuccess()).append("],")
			.append("北单失败[").append(getBjdcfail()).append("],")
			.append("大乐透成功[").append(getLotterysuccess()).append("],")
			.append("大乐透失败[").append(getLotteryfail()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TicketReportfrom == false) return false;
		if(this == obj) return true;
		TicketReportfrom other = (TicketReportfrom)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}


}

	
