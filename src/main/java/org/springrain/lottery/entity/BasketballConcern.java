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
 * @version  2017-11-14 16:05:32
 * @see org.springrain.lottery.entity.BasketballConcern
 */
@Table(name="basketball_concern")
public class BasketballConcern  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballConcern";
	public static final String ALIAS_ID = "表id";
	public static final String ALIAS_MEMBERID2 = "用户id2";
	public static final String ALIAS_ZID = "zid";
    */
	//date formats
	
	//columns START
	/**
	 * 表id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * zid
	 */
	private java.lang.String zid;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballConcern(){
	}

	public BasketballConcern(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballConcern_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BasketballConcern_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
     @WhereSQL(sql="zid=:BasketballConcern_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("表id[").append(getId()).append("],")
			.append("用户id2[").append(getMemberid2()).append("],")
			.append("zid[").append(getZid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballConcern == false) return false;
		if(this == obj) return true;
		BasketballConcern other = (BasketballConcern)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
