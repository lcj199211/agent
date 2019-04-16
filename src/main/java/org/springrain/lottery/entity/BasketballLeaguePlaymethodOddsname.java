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
 * @version  2017-11-09 09:55:38
 * @see org.springrain.lottery.entity.BasketballLeaguePlaymethodOddsname
 */
@Table(name="basketball_league_playmethod_oddsname")
public class BasketballLeaguePlaymethodOddsname  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballLeaguePlaymethodOddsname";
	public static final String ALIAS_ID = "篮球工具表id";
	public static final String ALIAS_ODDSNAME = "字段名";
	public static final String ALIAS_ODDSREALNAME = "字段中文名";
	public static final String ALIAS_SHORTNAME = "短中文名";
	public static final String ALIAS_BETNAME = "玩法名";
	public static final String ALIAS_PY = "拼音";
    */
	//date formats
	
	//columns START
	/**
	 * 篮球工具表id
	 */
	private java.lang.Integer id;
	/**
	 * 字段名
	 */
	private java.lang.String oddsname;
	/**
	 * 字段中文名
	 */
	private java.lang.String oddsrealname;
	/**
	 * 短中文名
	 */
	private java.lang.String shortname;
	/**
	 * 玩法名
	 */
	private java.lang.String betname;
	/**
	 * 拼音
	 */
	private java.lang.String py;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballLeaguePlaymethodOddsname(){
	}

	public BasketballLeaguePlaymethodOddsname(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 篮球工具表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 篮球工具表id
	 */
	@Id
     @WhereSQL(sql="id=:BasketballLeaguePlaymethodOddsname_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 字段名
		 */
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
	
	
	/**
	 * 字段名
	 */
     @WhereSQL(sql="oddsname=:BasketballLeaguePlaymethodOddsname_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
		/**
		 * 字段中文名
		 */
	public void setOddsrealname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsrealname = value;
	}
	
	
	
	/**
	 * 字段中文名
	 */
     @WhereSQL(sql="oddsrealname=:BasketballLeaguePlaymethodOddsname_oddsrealname")
	public java.lang.String getOddsrealname() {
		return this.oddsrealname;
	}
		/**
		 * 短中文名
		 */
	public void setShortname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shortname = value;
	}
	
	
	
	/**
	 * 短中文名
	 */
     @WhereSQL(sql="shortname=:BasketballLeaguePlaymethodOddsname_shortname")
	public java.lang.String getShortname() {
		return this.shortname;
	}
		/**
		 * 玩法名
		 */
	public void setBetname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.betname = value;
	}
	
	
	
	/**
	 * 玩法名
	 */
     @WhereSQL(sql="betname=:BasketballLeaguePlaymethodOddsname_betname")
	public java.lang.String getBetname() {
		return this.betname;
	}
		/**
		 * 拼音
		 */
	public void setPy(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.py = value;
	}
	
	
	
	/**
	 * 拼音
	 */
     @WhereSQL(sql="py=:BasketballLeaguePlaymethodOddsname_py")
	public java.lang.String getPy() {
		return this.py;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("篮球工具表id[").append(getId()).append("],")
			.append("字段名[").append(getOddsname()).append("],")
			.append("字段中文名[").append(getOddsrealname()).append("],")
			.append("短中文名[").append(getShortname()).append("],")
			.append("玩法名[").append(getBetname()).append("],")
			.append("拼音[").append(getPy()).append("],")
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
		if(obj instanceof BasketballLeaguePlaymethodOddsname == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		BasketballLeaguePlaymethodOddsname other = (BasketballLeaguePlaymethodOddsname)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
