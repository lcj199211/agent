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
 * @version  2017-06-22 16:22:30
 * @see org.springrain.lottery.entity.BetKeyvalue
 */
@Table(name="bet_keyvalue")
public class BetKeyvalue  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetKeyvalue";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "键";
	public static final String ALIAS_VALUE = "值";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 键
	 */
	private java.lang.String name;
	/**
	 * 值
	 */
	private java.lang.Double value;
	//columns END 数据库字段结束
	
	//concstructor

	public BetKeyvalue(){
	}

	public BetKeyvalue(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetKeyvalue_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BetKeyvalue_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setValue(java.lang.Double value) {
		this.value = value;
	}
	
     @WhereSQL(sql="value=:BetKeyvalue_value")
	public java.lang.Double getValue() {
		return this.value;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("键[").append(getName()).append("],")
			.append("值[").append(getValue()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetKeyvalue == false) return false;
		if(this == obj) return true;
		BetKeyvalue other = (BetKeyvalue)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
