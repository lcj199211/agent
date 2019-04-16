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
 * @version  2017-11-29 15:02:41
 * @see org.springrain.lottery.entity.BjdcPlaymethod
 */
@Table(name="bjdc_playmethod")
public class BjdcPlaymethod  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BjdcPlaymethod";
	public static final String ALIAS_ID = "玩法表id";
	public static final String ALIAS_NAME = "玩法名";
	public static final String ALIAS_SHORTNAME = "shortname";
	public static final String ALIAS_STATE = "状态 1:正常  3:删除 ";
	public static final String ALIAS_MINBETTING = "最小投注";
	public static final String ALIAS_MAXBETTING = "最大投注";
    */
	//date formats
	
	//columns START
	/**
	 * 玩法表id
	 */
	private java.lang.Integer id;
	/**
	 * 玩法名
	 */
	private java.lang.String name;
	/**
	 * shortname
	 */
	private java.lang.String shortname;
	/**
	 * 状态 1:正常  3:删除 
	 */
	private java.lang.Integer state;
	/**
	 * 最小投注
	 */
	private java.math.BigDecimal minbetting;
	/**
	 * 最大投注
	 */
	private java.math.BigDecimal maxbetting;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcPlaymethod(){
	}

	public BjdcPlaymethod(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcPlaymethod_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BjdcPlaymethod_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setShortname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shortname = value;
	}
	
     @WhereSQL(sql="shortname=:BjdcPlaymethod_shortname")
	public java.lang.String getShortname() {
		return this.shortname;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BjdcPlaymethod_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setMinbetting(java.math.BigDecimal value) {
		this.minbetting = value;
	}
	
     @WhereSQL(sql="minbetting=:BjdcPlaymethod_minbetting")
	public java.math.BigDecimal getMinbetting() {
		return this.minbetting;
	}
	public void setMaxbetting(java.math.BigDecimal value) {
		this.maxbetting = value;
	}
	
     @WhereSQL(sql="maxbetting=:BjdcPlaymethod_maxbetting")
	public java.math.BigDecimal getMaxbetting() {
		return this.maxbetting;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("玩法表id[").append(getId()).append("],")
			.append("玩法名[").append(getName()).append("],")
			.append("shortname[").append(getShortname()).append("],")
			.append("状态 1:正常  3:删除 [").append(getState()).append("],")
			.append("最小投注[").append(getMinbetting()).append("],")
			.append("最大投注[").append(getMaxbetting()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcPlaymethod == false) return false;
		if(this == obj) return true;
		BjdcPlaymethod other = (BjdcPlaymethod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
