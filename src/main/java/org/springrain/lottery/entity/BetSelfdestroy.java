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
 * @version  2017-07-13 17:17:26
 * @see org.springrain.lottery.entity.BetSelfdestroy
 */
@Table(name="bet_selfdestroy")
public class BetSelfdestroy  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetSelfdestroy";
	public static final String ALIAS_ID = "0自毁 1正常";
	public static final String ALIAS_STATE = "state";
    */
	//date formats
	
	//columns START
	/**
	 * 0自毁 1正常
	 */
	private java.lang.Integer id;
	/**
	 * state
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public BetSelfdestroy(){
	}

	public BetSelfdestroy(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetSelfdestroy_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetSelfdestroy_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("0自毁 1正常[").append(getId()).append("],")
			.append("state[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetSelfdestroy == false) return false;
		if(this == obj) return true;
		BetSelfdestroy other = (BetSelfdestroy)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
