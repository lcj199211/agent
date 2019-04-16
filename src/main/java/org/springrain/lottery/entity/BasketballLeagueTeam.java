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
 * @version  2017-11-10 16:55:00
 * @see org.springrain.lottery.entity.BasketballLeagueTeam
 */
@Table(name="basketball_league_team")
public class BasketballLeagueTeam  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球联赛队伍";
	public static final String ALIAS_ID = "球队表id";
	public static final String ALIAS_ID2 = "球队id2(来自500)";
	public static final String ALIAS_NAME = "球队名";
	public static final String ALIAS_LONGNAME = "球队全称";
	public static final String ALIAS_ENGLISHNAME = "英文名";
	public static final String ALIAS_ESTABLISH = "成立时间";
	public static final String ALIAS_AREA = "国家地区";
	public static final String ALIAS_CITY = "所在城市";
	public static final String ALIAS_GYM = "体育馆";
	public static final String ALIAS_STATE = "1正常,3删除";
	public static final String ALIAS_IMG = "图片";
	public static final String ALIAS_NUM = "得冠总数";
    */
	//date formats
	
	//columns START
	/**
	 * 球队表id
	 */
	private java.lang.Integer id;
	/**
	 * 球队id2(来自500)
	 */
	private java.lang.String id2;
	/**
	 * 球队名
	 */
	private java.lang.String name;
	/**
	 * 球队全称
	 */
	private java.lang.String longname;
	/**
	 * 英文名
	 */
	private java.lang.String englishname;
	/**
	 * 成立时间
	 */
	private java.lang.String establish;
	/**
	 * 国家地区
	 */
	private java.lang.String area;
	/**
	 * 所在城市
	 */
	private java.lang.String city;
	/**
	 * 体育馆
	 */
	private java.lang.String gym;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 图片
	 */
	private java.lang.String img;
	/**
	 * 得冠总数
	 */
	private java.lang.Integer num;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballLeagueTeam(){
	}

	public BasketballLeagueTeam(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballLeagueTeam_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setId2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id2 = value;
	}
	
     @WhereSQL(sql="id2=:BasketballLeagueTeam_id2")
	public java.lang.String getId2() {
		return this.id2;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name like :%BasketballLeagueTeam_name%")
	public java.lang.String getName() {
		return this.name;
	}
	public void setLongname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.longname = value;
	}
	
     @WhereSQL(sql="longname=:BasketballLeagueTeam_longname")
	public java.lang.String getLongname() {
		return this.longname;
	}
	public void setEnglishname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.englishname = value;
	}
	
     @WhereSQL(sql="englishname=:BasketballLeagueTeam_englishname")
	public java.lang.String getEnglishname() {
		return this.englishname;
	}
	public void setEstablish(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.establish = value;
	}
	
     @WhereSQL(sql="establish=:BasketballLeagueTeam_establish")
	public java.lang.String getEstablish() {
		return this.establish;
	}
	public void setArea(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.area = value;
	}
	
     @WhereSQL(sql="area=:BasketballLeagueTeam_area")
	public java.lang.String getArea() {
		return this.area;
	}
	public void setCity(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.city = value;
	}
	
     @WhereSQL(sql="city=:BasketballLeagueTeam_city")
	public java.lang.String getCity() {
		return this.city;
	}
	public void setGym(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gym = value;
	}
	
     @WhereSQL(sql="gym=:BasketballLeagueTeam_gym")
	public java.lang.String getGym() {
		return this.gym;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BasketballLeagueTeam_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setImg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.img = value;
	}
	
     @WhereSQL(sql="img=:BasketballLeagueTeam_img")
	public java.lang.String getImg() {
		return this.img;
	}
	public void setNum(java.lang.Integer value) {
		this.num = value;
	}
	
     @WhereSQL(sql="num=:BasketballLeagueTeam_num")
	public java.lang.Integer getNum() {
		return this.num;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("球队表id[").append(getId()).append("],")
			.append("球队id2(来自500)[").append(getId2()).append("],")
			.append("球队名[").append(getName()).append("],")
			.append("球队全称[").append(getLongname()).append("],")
			.append("英文名[").append(getEnglishname()).append("],")
			.append("成立时间[").append(getEstablish()).append("],")
			.append("国家地区[").append(getArea()).append("],")
			.append("所在城市[").append(getCity()).append("],")
			.append("体育馆[").append(getGym()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.append("图片[").append(getImg()).append("],")
			.append("得冠总数[").append(getNum()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballLeagueTeam == false) return false;
		if(this == obj) return true;
		BasketballLeagueTeam other = (BasketballLeagueTeam)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
