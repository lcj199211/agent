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
 * @version  2017-03-20 18:12:06
 * @see org.springrain.lottery.entity.BetPt
 */
@Table(name="bet_pt")
public class BetPt  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetPt";
	public static final String ALIAS_ID = "游戏分类ID";
	public static final String ALIAS_NAME = "游戏分类";
    */
	//date formats
	
	//columns START
	/**
	 * 游戏分类ID
	 */
	private java.lang.Integer id;
	/**
	 * 游戏分类
	 */
	private java.lang.String name;
	//columns END 数据库字段结束
	
	//concstructor

	public BetPt(){
	}

	public BetPt(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetPt_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BetPt_name")
	public java.lang.String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("游戏分类ID[").append(getId()).append("],")
			.append("游戏分类[").append(getName()).append("],")
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
		if(obj instanceof BetPt == false) return false;
		if(this == obj) return true;
		BetPt other = (BetPt)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
