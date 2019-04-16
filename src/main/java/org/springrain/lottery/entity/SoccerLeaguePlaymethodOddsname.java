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
 * @version  2017-09-01 16:22:09
 * @see org.springrain.lottery.entity.SoccerLeaguePlaymethodOddsname
 */
@Table(name="soccer_league_playmethod_oddsname")
public class SoccerLeaguePlaymethodOddsname  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeaguePlaymethodOddsname";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ODDSNAME = "字段名";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
    */
	//date formats
	
	//columns START
	/**
	 * id
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
	 * 表名
	 */
	private java.lang.String tablename;
	/**
	 * 中文名
	 */
	private java.lang.String shortname;
	/**
	 * 中文名
	 */
	private java.lang.String betname;
	/**
	 * 玩法id
	 */
	private java.lang.Integer playmethodid;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLeaguePlaymethodOddsname(){
	}

	public SoccerLeaguePlaymethodOddsname(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeaguePlaymethodOddsname_id")
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
     @WhereSQL(sql="oddsname=:SoccerLeaguePlaymethodOddsname_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
     
     
     @WhereSQL(sql="oddsrealname=:SoccerLeaguePlaymethodOddsname_oddsrealname")
     public java.lang.String getOddsrealname() {
		return oddsrealname;
	}

	public void setOddsrealname(java.lang.String oddsrealname) {
		this.oddsrealname = oddsrealname;
	}

	@WhereSQL(sql="tablename=:SoccerLeaguePlaymethodOddsname_tablename")
	public java.lang.String getTablename() {
		return tablename;
	}

	public void setTablename(java.lang.String tablename) {
		this.tablename = tablename;
	}

	@WhereSQL(sql="shortname=:SoccerLeaguePlaymethodOddsname_shortname")
	public java.lang.String getShortname() {
		return shortname;
	}

	public void setShortname(java.lang.String shortname) {
		this.shortname = shortname;
	}

		/**
		 * 玩法id
		 */
	public void setPlaymethodid(java.lang.Integer value) {
		this.playmethodid = value;
	}
	
	
	
	/**
	 * 玩法id
	 */
     @WhereSQL(sql="playmethodid=:SoccerLeaguePlaymethodOddsname_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
     
     
     @WhereSQL(sql="betname=:SoccerLeaguePlaymethodOddsname_betname")
	public java.lang.String getBetname() {
		return betname;
	}

	public void setBetname(java.lang.String betname) {
		this.betname = betname;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("字段名[").append(getOddsname()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
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
		if(obj instanceof SoccerLeaguePlaymethodOddsname == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeaguePlaymethodOddsname other = (SoccerLeaguePlaymethodOddsname)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
