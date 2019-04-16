package org.springrain.lottery.entity;

import java.text.ParseException;
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
 * @version  2019-01-25 14:49:03
 * @see org.springrain.lottery.entity.BetGoldGoodsCoinslogs
 */
@Table(name="bet_gold_goods_coinslogs")
public class BetGoldGoodsCoinslogs  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGoldGoodsCoinslogs";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_COINS = "积分";
	public static final String ALIAS_TYPE = "类型：1 金额购买  2 积分兑换";
	public static final String ALIAS_EXPLAINS = "说明";
	public static final String ALIAS_CREATETIME = "时间";
	public static final String ALIAS_BALANCE = "积分余额";
	public static final String ALIAS_MEMBERID = "用户id";
	public static final String ALIAS_ORDERID = "订单表id";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 积分
	 */
	private java.lang.String coins;
	/**
	 * 类型：1 金额购买  2 积分兑换
	 */
	private java.lang.Integer type;
	/**
	 * 说明
	 */
	private java.lang.String explains;
	/**
	 * 时间
	 */
	private java.util.Date createtime;
	/**
	 * 积分余额
	 */
	private java.lang.Double balance;
	/**
	 * 用户id
	 */
	private java.lang.Integer memberid;
	/**
	 * 订单表id
	 */
	private java.lang.Integer orderid;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGoldGoodsCoinslogs(){
	}

	public BetGoldGoodsCoinslogs(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGoldGoodsCoinslogs_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setCoins(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.coins = value;
	}
	
     @WhereSQL(sql="coins=:BetGoldGoodsCoinslogs_coins")
	public java.lang.String getCoins() {
		return this.coins;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetGoldGoodsCoinslogs_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setExplains(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.explains = value;
	}
	
     @WhereSQL(sql="explains=:BetGoldGoodsCoinslogs_explains")
	public java.lang.String getExplains() {
		return this.explains;
	}
		/*
	public String getcreatetimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreatetime());
	}
	public void setcreatetimeString(String value) throws ParseException{
		setcreatetime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
     @WhereSQL(sql="createtime=:BetGoldGoodsCoinslogs_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:BetGoldGoodsCoinslogs_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	public void setMemberid(java.lang.Integer value) {
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetGoldGoodsCoinslogs_memberid")
	public java.lang.Integer getMemberid() {
		return this.memberid;
	}
	public void setOrderid(java.lang.Integer value) {
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BetGoldGoodsCoinslogs_orderid")
	public java.lang.Integer getOrderid() {
		return this.orderid;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("积分[").append(getCoins()).append("],")
			.append("类型：1 金额购买  2 积分兑换[").append(getType()).append("],")
			.append("说明[").append(getExplains()).append("],")
			.append("时间[").append(getCreatetime()).append("],")
			.append("积分余额[").append(getBalance()).append("],")
			.append("用户id[").append(getMemberid()).append("],")
			.append("订单表id[").append(getOrderid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGoldGoodsCoinslogs == false) return false;
		if(this == obj) return true;
		BetGoldGoodsCoinslogs other = (BetGoldGoodsCoinslogs)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
