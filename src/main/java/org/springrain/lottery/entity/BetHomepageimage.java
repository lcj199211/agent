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
 * @version  2017-08-28 15:56:47
 * @see org.springrain.lottery.entity.BetHomepageimage
 */
@Table(name="bet_homepageimage")
public class BetHomepageimage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "首页图片";
	public static final String ALIAS_ID = "首页图片";
	public static final String ALIAS_URL = "图片地址";
	public static final String ALIAS_ORDER1 = "排序";
	public static final String ALIAS_COMMENT = "备注";
    */
	//date formats
	
	//columns START
	/**
	 * 首页图片
	 */
	private java.lang.Integer id;
	/**
	 * 图片地址
	 */
	private java.lang.String url;
	/**
	 * 排序
	 */
	private java.lang.Integer order1;
	/**
	 * 备注
	 */
	private java.lang.String comment;
	
	private java.lang.Integer state;
	private java.lang.Integer type;
	
	private java.lang.String company;
	private String title;
	//columns END 数据库字段结束
	
	@WhereSQL(sql="company=:BetHomepageimage_company")
	public java.lang.String getCompany() {
		return company;
	}

	public void setCompany(java.lang.String company) {
		this.company = company;
	}
	
	
	@WhereSQL(sql="type=:BetHomepageimage_type")
	public java.lang.Integer getType() {
		return type;
	}


	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	//concstructor
	@WhereSQL(sql="state=:BetHomepageimage_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public BetHomepageimage(){
	}

	public BetHomepageimage(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetHomepageimage_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.url = value;
	}
	
     @WhereSQL(sql="url=:BetHomepageimage_url")
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setOrder1(java.lang.Integer value) {
		this.order1 = value;
	}
	
     @WhereSQL(sql="order1=:BetHomepageimage_order1")
	public java.lang.Integer getOrder1() {
		return this.order1;
	}
	public void setComment(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.comment = value;
	}
	
     @WhereSQL(sql="comment=:BetHomepageimage_comment")
	public java.lang.String getComment() {
		return this.comment;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("首页图片[").append(getId()).append("],")
			.append("图片地址[").append(getUrl()).append("],")
			.append("排序[").append(getOrder1()).append("],")
			.append("备注[").append(getComment()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetHomepageimage == false) return false;
		if(this == obj) return true;
		BetHomepageimage other = (BetHomepageimage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

	
