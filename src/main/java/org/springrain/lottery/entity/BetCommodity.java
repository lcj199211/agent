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
 * @version  2017-07-11 11:16:54
 * @see org.springrain.lottery.entity.BetCommodity
 */
@Table(name="bet_commodity")
public class BetCommodity  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "商品列表";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_EXCHANGESCORE = "兑换分";
	public static final String ALIAS_STATE = "状态（0：下架，1：发布中）";
	public static final String ALIAS_EXCHANGENUM = "兑换/笔";
	public static final String ALIAS_PURCHASEPRICE = "进价";
	public static final String ALIAS_TYPE = "商品分类";
    */
	//date formats
	
	//columns START
	/**
	 * 编号
	 */
	private java.lang.Integer id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 兑换分
	 */
	private java.lang.Double exchangescore;
	/**
	 * 状态（0：下架，1：发布中）
	 */
	private java.lang.Integer state;
	/**
	 * 兑换/笔
	 */
	private java.lang.Integer exchangenum;
	/**
	 * 进价
	 */
	private java.lang.Double purchaseprice;
	/**
	 * 商品分类
	 */
	private java.lang.Integer type;
	//columns END 数据库字段结束
	
	//concstructor

	public BetCommodity(){
	}

	public BetCommodity(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetCommodity_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BetCommodity_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setExchangescore(java.lang.Double value) {
		this.exchangescore = value;
	}
	
     @WhereSQL(sql="exchangescore=:BetCommodity_exchangescore")
	public java.lang.Double getExchangescore() {
		return this.exchangescore;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetCommodity_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setExchangenum(java.lang.Integer value) {
		this.exchangenum = value;
	}
	
     @WhereSQL(sql="exchangenum=:BetCommodity_exchangenum")
	public java.lang.Integer getExchangenum() {
		return this.exchangenum;
	}
	public void setPurchaseprice(java.lang.Double value) {
		this.purchaseprice = value;
	}
	
     @WhereSQL(sql="purchaseprice=:BetCommodity_purchaseprice")
	public java.lang.Double getPurchaseprice() {
		return this.purchaseprice;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetCommodity_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("兑换分[").append(getExchangescore()).append("],")
			.append("状态（0：下架，1：发布中）[").append(getState()).append("],")
			.append("兑换/笔[").append(getExchangenum()).append("],")
			.append("进价[").append(getPurchaseprice()).append("],")
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
		if(obj instanceof BetCommodity == false) return false;
		if(this == obj) return true;
		BetCommodity other = (BetCommodity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
