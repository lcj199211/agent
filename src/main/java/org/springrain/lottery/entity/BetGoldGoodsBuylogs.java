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
 * @version  2019-01-22 17:38:07
 * @see org.springrain.lottery.entity.BetGoldGoodsBuylogs
 */
@Table(name="bet_gold_goods_buylogs")
public class BetGoldGoodsBuylogs  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGoldGoodsBuylogs";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_GOODSID = "商品表id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_GOODSNUM = "购买数量";
	public static final String ALIAS_TOTALMONEY = "订单金额";
	public static final String ALIAS_GOODSNAME = "订单名称";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 商品表id
	 */
	private java.lang.Integer goodsid;
	/**
	 * 订单号
	 */
	private java.lang.String orderid;
	/**
	 * 购买数量
	 */
	private java.lang.Integer goodsnum;
	/**
	 * 订单金额
	 */
	private java.lang.Double totalmoney;
	/**
	 * 订单名称
	 */
	private java.lang.String goodsname;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGoldGoodsBuylogs(){
	}

	public BetGoldGoodsBuylogs(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGoldGoodsBuylogs_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGoodsid(java.lang.Integer value) {
		this.goodsid = value;
	}
	
     @WhereSQL(sql="goodsid=:BetGoldGoodsBuylogs_goodsid")
	public java.lang.Integer getGoodsid() {
		return this.goodsid;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BetGoldGoodsBuylogs_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setGoodsnum(java.lang.Integer value) {
		this.goodsnum = value;
	}
	
     @WhereSQL(sql="goodsnum=:BetGoldGoodsBuylogs_goodsnum")
	public java.lang.Integer getGoodsnum() {
		return this.goodsnum;
	}
	public void setTotalmoney(java.lang.Double value) {
		this.totalmoney = value;
	}
	
     @WhereSQL(sql="totalmoney=:BetGoldGoodsBuylogs_totalmoney")
	public java.lang.Double getTotalmoney() {
		return this.totalmoney;
	}
	public void setGoodsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.goodsname = value;
	}
	
     @WhereSQL(sql="goodsname=:BetGoldGoodsBuylogs_goodsname")
	public java.lang.String getGoodsname() {
		return this.goodsname;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("商品表id[").append(getGoodsid()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("购买数量[").append(getGoodsnum()).append("],")
			.append("订单金额[").append(getTotalmoney()).append("],")
			.append("订单名称[").append(getGoodsname()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGoldGoodsBuylogs == false) return false;
		if(this == obj) return true;
		BetGoldGoodsBuylogs other = (BetGoldGoodsBuylogs)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
