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
 * @version  2017-07-10 17:42:49
 * @see org.springrain.lottery.entity.BetCustomCenter
 */
@Table(name="bet_custom_center")
public class BetCustomCenter  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "客服中心";
	public static final String ALIAS_ID = "客服中心ID";
	public static final String ALIAS_QQ = "客服QQ";
	public static final String ALIAS_QQGROUP = "玩家QQ群";
	public static final String ALIAS_WECHAT = "微信";
	public static final String ALIAS_WECHATGROUP = "微信群";
    */
	//date formats
	
	//columns START
	/**
	 * 客服中心ID
	 */
	private java.lang.Integer id;
	/**
	 * 客服QQ
	 */
	private java.lang.String qq;
	/**
	 * 玩家QQ群
	 */
	private java.lang.String qqgroup;
	/**
	 * 微信
	 */
	private java.lang.String wechat;
	/**
	 * 微信群
	 */
	private java.lang.String wechatgroup;
	//columns END 数据库字段结束
	
	//concstructor

	public BetCustomCenter(){
	}

	public BetCustomCenter(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetCustomCenter_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setQq(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qq = value;
	}
	
     @WhereSQL(sql="qq=:BetCustomCenter_qq")
	public java.lang.String getQq() {
		return this.qq;
	}
	public void setQqgroup(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qqgroup = value;
	}
	
     @WhereSQL(sql="qqgroup=:BetCustomCenter_qqgroup")
	public java.lang.String getQqgroup() {
		return this.qqgroup;
	}
	public void setWechat(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wechat = value;
	}
	
     @WhereSQL(sql="wechat=:BetCustomCenter_wechat")
	public java.lang.String getWechat() {
		return this.wechat;
	}
	public void setWechatgroup(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wechatgroup = value;
	}
	
     @WhereSQL(sql="wechatgroup=:BetCustomCenter_wechatgroup")
	public java.lang.String getWechatgroup() {
		return this.wechatgroup;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("客服中心ID[").append(getId()).append("],")
			.append("客服QQ[").append(getQq()).append("],")
			.append("玩家QQ群[").append(getQqgroup()).append("],")
			.append("微信[").append(getWechat()).append("],")
			.append("微信群[").append(getWechatgroup()).append("],")
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
		if(obj instanceof BetCustomCenter == false) return false;
		if(this == obj) return true;
		BetCustomCenter other = (BetCustomCenter)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
