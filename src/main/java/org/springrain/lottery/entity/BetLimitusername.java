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
 * @version  2017-04-05 13:15:35
 * @see org.springrain.lottery.entity.BetLimitusername
 */
@Table(name="bet_limitusername")
public class BetLimitusername  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "限制用户名";
	public static final String ALIAS_ID = "限制用户名ID";
	public static final String ALIAS_NICKNAME = "昵称";
    */
	//date formats
	
	//columns START
	/**
	 * 限制用户名ID
	 */
	private java.lang.Integer id;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	//columns END 数据库字段结束
	
	//concstructor

	public BetLimitusername(){
	}

	public BetLimitusername(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetLimitusername_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetLimitusername_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("限制用户名ID[").append(getId()).append("],")
			.append("昵称[").append(getNickname()).append("],")
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
		if(obj instanceof BetLimitusername == false) return false;
		if(this == obj) return true;
		BetLimitusername other = (BetLimitusername)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
