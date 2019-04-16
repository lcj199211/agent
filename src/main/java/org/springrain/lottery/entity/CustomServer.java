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
 * @version  2018-07-02 19:40:30
 * @see org.springrain.lottery.entity.CustomServer
 */
@Table(name="custom_server")
public class CustomServer  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "CustomServer";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "客服id";
	public static final String ALIAS_ACTIVE = "0不在线1在线";
	public static final String ALIAS_CHARNUM = "聊天人数";
	public static final String ALIAS_COMPANY = "公司";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 客服id
	 */
	private java.lang.String userid;
	/**
	 * 0不在线1在线
	 */
	private java.lang.Integer active;
	/**
	 * 聊天人数
	 */
	private java.lang.Integer charnum;
	/**
	 * 公司
	 */
	private java.lang.String company;
	//columns END 数据库字段结束
	
	//concstructor

	public CustomServer(){
	}

	public CustomServer(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:CustomServer_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userid = value;
	}
	
     @WhereSQL(sql="userid=:CustomServer_userid")
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:CustomServer_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	public void setCharnum(java.lang.Integer value) {
		this.charnum = value;
	}
	
     @WhereSQL(sql="charnum=:CustomServer_charnum")
	public java.lang.Integer getCharnum() {
		return this.charnum;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:CustomServer_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("客服id[").append(getUserid()).append("],")
			.append("0不在线1在线[").append(getActive()).append("],")
			.append("聊天人数[").append(getCharnum()).append("],")
			.append("公司[").append(getCompany()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CustomServer == false) return false;
		if(this == obj) return true;
		CustomServer other = (CustomServer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
