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
 * @version  2017-03-01 12:52:01
 * @see org.springrain.lottery.entity.BetVip
 */
@Table(name="bet_vip")
public class BetVip  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "VIP等级设置";
	public static final String ALIAS_ID = "表ID";
	public static final String ALIAS_LEVEL = "等级";
	public static final String ALIAS_BOTTOM = "经验范围下限";
	public static final String ALIAS_TOP = "经验范围上限";
	public static final String ALIAS_ALLOWANCE = "救济";
	public static final String ALIAS_SIGNIN = "签到奖励";
    */
	//date formats
	
	//columns START
	/**
	 * 表ID
	 */
	private java.lang.Integer id;
	/**
	 * 等级
	 */
	private java.lang.Integer level;
	/**
	 * 经验范围下限
	 */
	private java.lang.Integer bottom;
	/**
	 * 经验范围上限
	 */
	private java.lang.Integer top;
	/**
	 * 救济
	 */
	private java.lang.Integer allowance;
	/**
	 * 签到奖励
	 */
	private java.lang.Integer signin;
	//columns END 数据库字段结束
	
	//concstructor

	public BetVip(){
	}

	public BetVip(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetVip_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	
     @WhereSQL(sql="level=:BetVip_level")
	public java.lang.Integer getLevel() {
		return this.level;
	}
	public void setBottom(java.lang.Integer value) {
		this.bottom = value;
	}
	
     @WhereSQL(sql="bottom=:BetVip_bottom")
	public java.lang.Integer getBottom() {
		return this.bottom;
	}
	public void setTop(java.lang.Integer value) {
		this.top = value;
	}
	
     @WhereSQL(sql="top=:BetVip_top")
	public java.lang.Integer getTop() {
		return this.top;
	}
	public void setAllowance(java.lang.Integer value) {
		this.allowance = value;
	}
	
     @WhereSQL(sql="allowance=:BetVip_allowance")
	public java.lang.Integer getAllowance() {
		return this.allowance;
	}
	public void setSignin(java.lang.Integer value) {
		this.signin = value;
	}
	
     @WhereSQL(sql="signin=:BetVip_signin")
	public java.lang.Integer getSignin() {
		return this.signin;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("表ID[").append(getId()).append("],")
			.append("等级[").append(getLevel()).append("],")
			.append("经验范围下限[").append(getBottom()).append("],")
			.append("经验范围上限[").append(getTop()).append("],")
			.append("救济[").append(getAllowance()).append("],")
			.append("签到奖励[").append(getSignin()).append("],")
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
		if(obj instanceof BetVip == false) return false;
		if(this == obj) return true;
		BetVip other = (BetVip)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
