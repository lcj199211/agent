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
 * @version  2017-11-07 13:47:30
 * @see org.springrain.lottery.entity.BasketballLeaguePlaymethod
 */
@Table(name="basketball_league_playmethod")
public class BasketballLeaguePlaymethod  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballLeaguePlaymethod";
	public static final String ALIAS_ID = "篮球玩法表id";
	public static final String ALIAS_NAME = "玩法名";
	public static final String ALIAS_SHORTNAME = "短名字";
	public static final String ALIAS_STATE = "1正常,3删除";
	public static final String ALIAS_MINBETTING = "限额最小";
	public static final String ALIAS_MAXBETTING = "限额最大";
    */
	//date formats
	
	//columns START
	/**
	 * 篮球玩法表id
	 */
	private java.lang.Integer id;
	/**
	 * 玩法名
	 */
	private java.lang.String name;
	/**
	 * 短名字
	 */
	private java.lang.String shortname;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 限额最小
	 */
	private java.math.BigDecimal minbetting;
	/**
	 * 限额最大
	 */
	private java.math.BigDecimal maxbetting;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballLeaguePlaymethod(){
	}

	public BasketballLeaguePlaymethod(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballLeaguePlaymethod_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BasketballLeaguePlaymethod_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setShortname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shortname = value;
	}
	
     @WhereSQL(sql="shortname=:BasketballLeaguePlaymethod_shortname")
	public java.lang.String getShortname() {
		return this.shortname;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BasketballLeaguePlaymethod_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setMinbetting(java.math.BigDecimal value) {
		this.minbetting = value;
	}
	
     @WhereSQL(sql="minbetting=:BasketballLeaguePlaymethod_minbetting")
	public java.math.BigDecimal getMinbetting() {
		return this.minbetting;
	}
	public void setMaxbetting(java.math.BigDecimal value) {
		this.maxbetting = value;
	}
	
     @WhereSQL(sql="maxbetting=:BasketballLeaguePlaymethod_maxbetting")
	public java.math.BigDecimal getMaxbetting() {
		return this.maxbetting;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("篮球玩法表id[").append(getId()).append("],")
			.append("玩法名[").append(getName()).append("],")
			.append("短名字[").append(getShortname()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.append("限额最小[").append(getMinbetting()).append("],")
			.append("限额最大[").append(getMaxbetting()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballLeaguePlaymethod == false) return false;
		if(this == obj) return true;
		BasketballLeaguePlaymethod other = (BasketballLeaguePlaymethod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
