package org.springrain.lottery.entity;

import java.util.Date;

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
 * @version  2017-12-09 16:22:01
 * @see org.springrain.lottery.entity.BetReserve
 */
@Table(name="bet_reserve")
public class BetReserve  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetReserve";
	public static final String ALIAS_PHONENO = "phoneno";
	public static final String ALIAS_AGENTID = "agentid";
    */
	//date formats
	
	//columns START
	/**
	 * phoneno
	 */
	private java.lang.String phoneno;
	/**
	 * agentid
	 */
	private java.lang.String agentid;
	/**
	 * day
	 */
	private Date day;
	private Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public BetReserve(){
	}

	public BetReserve(
		java.lang.String phoneno
	){
		this.phoneno = phoneno;
	}

	//get and set
	public void setPhoneno(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phoneno = value;
	}
	
	@Id
     @WhereSQL(sql="phoneno=:BetReserve_phoneno")
	public java.lang.String getPhoneno() {
		return this.phoneno;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetReserve_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
     
    public void setDay(Date value) {
		this.day = value;
	}
	
     @WhereSQL(sql="day=:BetReserve_day")
	public Date getDay() {
		return this.day;
	}
     @WhereSQL(sql="state=:BetReserve_state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String toString() {
		return new StringBuffer()
			.append("phoneno[").append(getPhoneno()).append("],")
			.append("agentid[").append(getAgentid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPhoneno())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetReserve == false) return false;
		if(this == obj) return true;
		BetReserve other = (BetReserve)obj;
		return new EqualsBuilder()
			.append(getPhoneno(),other.getPhoneno())
			.isEquals();
	}
}

	
