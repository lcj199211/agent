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
 * @version  2019-01-23 16:44:19
 * @see org.springrain.lottery.entity.BetGoldGoodsCategory
 */
@Table(name="bet_gold_goods_category")
public class BetGoldGoodsCategory  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGoldGoodsCategory";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_PARENTID = "父级ID(默认为0, 0表示第一级)";
	public static final String ALIAS_CATEGORYNAME = "分类名称";
	public static final String ALIAS_CREATEDATE = "创建时间";
    */
	//date formats
	
	//columns START
	/**
	 * 主键ID
	 */
	private java.lang.Integer id;
	/**
	 * 父级ID(默认为0, 0表示第一级)
	 */
	private java.lang.Integer parentId;
	/**
	 * 分类名称
	 */
	private java.lang.String categoryName;
	/**
	 * 创建时间
	 */
	private java.lang.String createdate;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGoldGoodsCategory(){
	}

	public BetGoldGoodsCategory(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGoldGoodsCategory_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
     @WhereSQL(sql="parentId=:BetGoldGoodsCategory_parentId")
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	public void setCategoryName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.categoryName = value;
	}
	
     @WhereSQL(sql="categoryName=:BetGoldGoodsCategory_categoryName")
	public java.lang.String getCategoryName() {
		return this.categoryName;
	}
	public void setCreatedate(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.createdate = value;
	}
	
     @WhereSQL(sql="createdate=:BetGoldGoodsCategory_createdate")
	public java.lang.String getCreatedate() {
		return this.createdate;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键ID[").append(getId()).append("],")
			.append("父级ID(默认为0, 0表示第一级)[").append(getParentId()).append("],")
			.append("分类名称[").append(getCategoryName()).append("],")
			.append("创建时间[").append(getCreatedate()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGoldGoodsCategory == false) return false;
		if(this == obj) return true;
		BetGoldGoodsCategory other = (BetGoldGoodsCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
