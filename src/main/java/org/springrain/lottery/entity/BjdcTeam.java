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
 * @version  2017-11-29 15:03:22
 * @see org.springrain.lottery.entity.BjdcTeam
 */
@Table(name="bjdc_team")
public class BjdcTeam  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BjdcTeam";
	public static final String ALIAS_ID = "北单球队id";
	public static final String ALIAS_ID2 = "球队id2";
	public static final String ALIAS_TEAMNAME = "球队名";
	public static final String ALIAS_LONGNAME = "完整名字";
	public static final String ALIAS_ESTABLISH = "成立时间";
	public static final String ALIAS_AREA = "地区";
	public static final String ALIAS_CITY = "所在城市";
	public static final String ALIAS_GYM = "球场";
	public static final String ALIAS_PRICE = "身价";
	public static final String ALIAS_IMG = "队标";
    */
	//date formats
	
	//columns START
	/**
	 * 北单球队id
	 */
	private java.lang.Integer id;
	/**
	 * 球队id2
	 */
	private java.lang.String id2;
	/**
	 * 球队名
	 */
	private java.lang.String teamname;
	/**
	 * 完整名字
	 */
	private java.lang.String longname;
	/**
	 * 成立时间
	 */
	private java.lang.String establish;
	/**
	 * 地区
	 */
	private java.lang.String area;
	/**
	 * 所在城市
	 */
	private java.lang.String city;
	/**
	 * 球场
	 */
	private java.lang.String gym;
	/**
	 * 身价
	 */
	private java.lang.String price;
	/**
	 * 队标
	 */
	private java.lang.String img;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcTeam(){
	}

	public BjdcTeam(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcTeam_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setId2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id2 = value;
	}
	
     @WhereSQL(sql="id2=:BjdcTeam_id2")
	public java.lang.String getId2() {
		return this.id2;
	}
	public void setTeamname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.teamname = value;
	}
	
     @WhereSQL(sql="teamname=:BjdcTeam_teamname")
	public java.lang.String getTeamname() {
		return this.teamname;
	}
	public void setLongname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.longname = value;
	}
	
     @WhereSQL(sql="longname=:BjdcTeam_longname")
	public java.lang.String getLongname() {
		return this.longname;
	}
	public void setEstablish(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.establish = value;
	}
	
     @WhereSQL(sql="establish=:BjdcTeam_establish")
	public java.lang.String getEstablish() {
		return this.establish;
	}
	public void setArea(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.area = value;
	}
	
     @WhereSQL(sql="area=:BjdcTeam_area")
	public java.lang.String getArea() {
		return this.area;
	}
	public void setCity(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.city = value;
	}
	
     @WhereSQL(sql="city=:BjdcTeam_city")
	public java.lang.String getCity() {
		return this.city;
	}
	public void setGym(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gym = value;
	}
	
     @WhereSQL(sql="gym=:BjdcTeam_gym")
	public java.lang.String getGym() {
		return this.gym;
	}
	public void setPrice(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.price = value;
	}
	
     @WhereSQL(sql="price=:BjdcTeam_price")
	public java.lang.String getPrice() {
		return this.price;
	}
	public void setImg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.img = value;
	}
	
     @WhereSQL(sql="img=:BjdcTeam_img")
	public java.lang.String getImg() {
		return this.img;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("北单球队id[").append(getId()).append("],")
			.append("球队id2[").append(getId2()).append("],")
			.append("球队名[").append(getTeamname()).append("],")
			.append("完整名字[").append(getLongname()).append("],")
			.append("成立时间[").append(getEstablish()).append("],")
			.append("地区[").append(getArea()).append("],")
			.append("所在城市[").append(getCity()).append("],")
			.append("球场[").append(getGym()).append("],")
			.append("身价[").append(getPrice()).append("],")
			.append("队标[").append(getImg()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcTeam == false) return false;
		if(this == obj) return true;
		BjdcTeam other = (BjdcTeam)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
