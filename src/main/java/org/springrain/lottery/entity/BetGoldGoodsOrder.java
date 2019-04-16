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
 * @version  2019-01-25 14:49:18
 * @see org.springrain.lottery.entity.BetGoldGoodsOrder
 */
@Table(name="bet_gold_goods_order")
public class BetGoldGoodsOrder  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGoldGoodsOrder";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CREATETIME = "购买时间";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_STATE = "订单状态:1待付款 2 成功 3 取消";
	public static final String ALIAS_ADDRESS = "收件地址";
	public static final String ALIAS_TELPHONE = "手机号";
	public static final String ALIAS_ADDRESSEE = "收件人";
	public static final String ALIAS_TOTALMONEY = "订单总金额";
	public static final String ALIAS_MEMBERID = "用户id";
	public static final String ALIAS_AGENTID = "代理商ID";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
	public static final String ALIAS_TYPE = "类型：1 金额购买  2 积分兑换";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 购买时间
	 */
	private java.util.Date createtime;
	/**
	 * 订单号
	 */
	private java.lang.String orderid;
	/**
	 * 订单状态:1待付款 2 成功 3 取消
	 */
	private java.lang.Integer state;
	/**
	 * 收件地址
	 */
	private java.lang.String address;
	/**
	 * 手机号
	 */
	private java.lang.String telphone;
	/**
	 * 收件人
	 */
	private java.lang.String addressee;
	/**
	 * 订单总金额
	 */
	private java.lang.Double totalmoney;
	/**
	 * 用户id
	 */
	private java.lang.Integer memberid;
	/**
	 * 代理商ID
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父级id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商id 从根节点开始
	 */
	private java.lang.String agentparentids;
	/**
	 * 类型：1 金额购买  2 积分兑换
	 */
	private java.lang.Integer type;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGoldGoodsOrder(){
	}

	public BetGoldGoodsOrder(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGoldGoodsOrder_id")
	public java.lang.Integer getId() {
		return this.id;
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
	
     @WhereSQL(sql="createtime=:BetGoldGoodsOrder_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BetGoldGoodsOrder_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetGoldGoodsOrder_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.address = value;
	}
	
     @WhereSQL(sql="address=:BetGoldGoodsOrder_address")
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setTelphone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.telphone = value;
	}
	
     @WhereSQL(sql="telphone=:BetGoldGoodsOrder_telphone")
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	public void setAddressee(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.addressee = value;
	}
	
     @WhereSQL(sql="addressee=:BetGoldGoodsOrder_addressee")
	public java.lang.String getAddressee() {
		return this.addressee;
	}
	public void setTotalmoney(java.lang.Double value) {
		this.totalmoney = value;
	}
	
     @WhereSQL(sql="totalmoney=:BetGoldGoodsOrder_totalmoney")
	public java.lang.Double getTotalmoney() {
		return this.totalmoney;
	}
	public void setMemberid(java.lang.Integer value) {
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetGoldGoodsOrder_memberid")
	public java.lang.Integer getMemberid() {
		return this.memberid;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetGoldGoodsOrder_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetGoldGoodsOrder_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetGoldGoodsOrder_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetGoldGoodsOrder_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("购买时间[").append(getCreatetime()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("订单状态:1待付款 2 成功 3 取消[").append(getState()).append("],")
			.append("收件地址[").append(getAddress()).append("],")
			.append("手机号[").append(getTelphone()).append("],")
			.append("收件人[").append(getAddressee()).append("],")
			.append("订单总金额[").append(getTotalmoney()).append("],")
			.append("用户id[").append(getMemberid()).append("],")
			.append("代理商ID[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.append("类型：1 金额购买  2 积分兑换[").append(getType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGoldGoodsOrder == false) return false;
		if(this == obj) return true;
		BetGoldGoodsOrder other = (BetGoldGoodsOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
