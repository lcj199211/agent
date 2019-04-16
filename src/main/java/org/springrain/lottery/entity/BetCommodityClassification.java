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
 * @version  2017-07-11 11:17:22
 * @see org.springrain.lottery.entity.BetCommodityClassification
 */
@Table(name="bet_commodity_classification")
public class BetCommodityClassification  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "商品分类";
	public static final String ALIAS_ID = "商品分类ID";
	public static final String ALIAS_TYPE = "商品分类";
    */
	//date formats
	
	//columns START
	/**
	 * 商品分类ID
	 */
	private java.lang.Integer id;
	/**
	 * 商品分类
	 */
	private java.lang.String type;
	//columns END 数据库字段结束
	
	//concstructor

	public BetCommodityClassification(){
	}

	public BetCommodityClassification(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetCommodityClassification_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetCommodityClassification_type")
	public java.lang.String getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("商品分类ID[").append(getId()).append("],")
			.append("商品分类[").append(getType()).append("],")
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
		if(obj instanceof BetCommodityClassification == false) return false;
		if(this == obj) return true;
		BetCommodityClassification other = (BetCommodityClassification)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
