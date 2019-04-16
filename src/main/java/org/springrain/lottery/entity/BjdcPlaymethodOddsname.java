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
 * @version  2017-11-29 15:03:03
 * @see org.springrain.lottery.entity.BjdcPlaymethodOddsname
 */
@Table(name="bjdc_playmethod_oddsname")
public class BjdcPlaymethodOddsname  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BjdcPlaymethodOddsname";
	public static final String ALIAS_ID = "北单工具表id";
	public static final String ALIAS_PLAYID = "playid";
	public static final String ALIAS_ODDSNAME = "oddsname";
	public static final String ALIAS_ODDSREALNAME = "oddsrealname";
	public static final String ALIAS_SHORTNAME = "shortname";
	public static final String ALIAS_BETNAME = "betname";
    */
	//date formats
	
	//columns START
	/**
	 * 北单工具表id
	 */
	private java.lang.Integer id;
	/**
	 * playid
	 */
	private java.lang.Integer playid;
	/**
	 * oddsname
	 */
	private java.lang.String oddsname;
	/**
	 * oddsrealname
	 */
	private java.lang.String oddsrealname;
	/**
	 * shortname
	 */
	private java.lang.String shortname;
	/**
	 * betname
	 */
	private java.lang.String betname;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcPlaymethodOddsname(){
	}

	public BjdcPlaymethodOddsname(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcPlaymethodOddsname_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setPlayid(java.lang.Integer value) {
		this.playid = value;
	}
	
     @WhereSQL(sql="playid=:BjdcPlaymethodOddsname_playid")
	public java.lang.Integer getPlayid() {
		return this.playid;
	}
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
     @WhereSQL(sql="oddsname=:BjdcPlaymethodOddsname_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
	public void setOddsrealname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsrealname = value;
	}
	
     @WhereSQL(sql="oddsrealname=:BjdcPlaymethodOddsname_oddsrealname")
	public java.lang.String getOddsrealname() {
		return this.oddsrealname;
	}
	public void setShortname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shortname = value;
	}
	
     @WhereSQL(sql="shortname=:BjdcPlaymethodOddsname_shortname")
	public java.lang.String getShortname() {
		return this.shortname;
	}
	public void setBetname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.betname = value;
	}
	
     @WhereSQL(sql="betname=:BjdcPlaymethodOddsname_betname")
	public java.lang.String getBetname() {
		return this.betname;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("北单工具表id[").append(getId()).append("],")
			.append("playid[").append(getPlayid()).append("],")
			.append("oddsname[").append(getOddsname()).append("],")
			.append("oddsrealname[").append(getOddsrealname()).append("],")
			.append("shortname[").append(getShortname()).append("],")
			.append("betname[").append(getBetname()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcPlaymethodOddsname == false) return false;
		if(this == obj) return true;
		BjdcPlaymethodOddsname other = (BjdcPlaymethodOddsname)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
