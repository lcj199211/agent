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
 * @version  2017-08-17 17:59:17
 * @see org.springrain.lottery.entity.SoccerLeaguePlaymethod
 */
@Table(name="soccer_league_playmethod")
public class SoccerLeaguePlaymethod  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeaguePlaymethod";
	public static final String ALIAS_ID = "玩法表id";
	public static final String ALIAS_NAME = "玩法名";
	public static final String ALIAS_PLAYCLASS = "类型";
	public static final String ALIAS_SHORTNAME = "短名字";
	public static final String ALIAS_STATE = "1正常,3删除";
    */
	//date formats
	
	//columns START
	/**
	 * 玩法表id
	 */
	private java.lang.Integer id;
	/**
	 * 玩法名
	 */
	private java.lang.String name;
	/**
	 * 类型
	 */
	private java.lang.String playclass;
	/**
	 * 短名字
	 */
	private java.lang.String shortname;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 是否单关1:是3:不是
	 */
	private java.lang.Integer type;
	private java.lang.Double minbetting;
	private java.lang.Double maxbetting;
	
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="minbetting=:SoccerLeaguePlaymethod_minbetting")
	public java.lang.Double getMinbetting() {
		return minbetting;
	}

	public void setMinbetting(java.lang.Double minbetting) {
		this.minbetting = minbetting;
	}
	@WhereSQL(sql="maxbetting=:SoccerLeaguePlaymethod_maxbetting")
	public java.lang.Double getMaxbetting() {
		return maxbetting;
	}

	public void setMaxbetting(java.lang.Double maxbetting) {
		this.maxbetting = maxbetting;
	}

	public SoccerLeaguePlaymethod(){
	}

	public SoccerLeaguePlaymethod(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 玩法表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 玩法表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeaguePlaymethod_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 玩法名
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 玩法名
	 */
     @WhereSQL(sql="name=:SoccerLeaguePlaymethod_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * 类型
		 */
	public void setPlayclass(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.playclass = value;
	}
	
	
	
	/**
	 * 类型
	 */
     @WhereSQL(sql="playclass=:SoccerLeaguePlaymethod_playclass")
	public java.lang.String getPlayclass() {
		return this.playclass;
	}
		/**
		 * 短名字
		 */
	public void setShortname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shortname = value;
	}
	
	
	
	/**
	 * 短名字
	 */
     @WhereSQL(sql="shortname=:SoccerLeaguePlaymethod_shortname")
	public java.lang.String getShortname() {
		return this.shortname;
	}
		/**
		 * 1正常,3删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 1正常,3删除
	 */
     @WhereSQL(sql="state=:SoccerLeaguePlaymethod_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     @WhereSQL(sql="type=:SoccerLeaguePlaymethod_type")
	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("玩法表id[").append(getId()).append("],")
			.append("玩法名[").append(getName()).append("],")
			.append("类型[").append(getPlayclass()).append("],")
			.append("短名字[").append(getShortname()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
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
		if(obj instanceof SoccerLeaguePlaymethod == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeaguePlaymethod other = (SoccerLeaguePlaymethod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
