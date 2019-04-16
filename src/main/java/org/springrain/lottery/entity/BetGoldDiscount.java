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
 * @version  2018-10-25 16:54:29
 * @see org.springrain.lottery.entity.BetGoldDiscount
 */
@Table(name="bet_gold_discount")
public class BetGoldDiscount  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "充值优惠表";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_MIN_RECHARGE = "充值最小";
	public static final String ALIAS_MAX_RECHARGE = "充值最大";
	public static final String ALIAS_DISCOUNT_PERCENT = "优惠百分比";
	public static final String ALIAS_COMPANY = "代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；";
    */
	//date formats
	
	//columns START
	/**
	 * 主键ID
	 */
	private java.lang.Integer id;
	/**
	 * 充值最小
	 */
	private java.lang.Double min_recharge;
	/**
	 * 充值最大
	 */
	private java.lang.Double max_recharge;
	/**
	 * 优惠百分比
	 */
	private java.lang.Double discount_percent;
	/**
	 * 代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；
	 */
	private java.lang.String company;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGoldDiscount(){
	}

	public BetGoldDiscount(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGoldDiscount_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMin_recharge(java.lang.Double value) {
		this.min_recharge = value;
	}
	
     @WhereSQL(sql="min_recharge=:BetGoldDiscount_min_recharge")
	public java.lang.Double getMin_recharge() {
		return this.min_recharge;
	}
	public void setMax_recharge(java.lang.Double value) {
		this.max_recharge = value;
	}
	
     @WhereSQL(sql="max_recharge=:BetGoldDiscount_max_recharge")
	public java.lang.Double getMax_recharge() {
		return this.max_recharge;
	}
	public void setDiscount_percent(java.lang.Double value) {
		this.discount_percent = value;
	}
	
     @WhereSQL(sql="discount_percent=:BetGoldDiscount_discount_percent")
	public java.lang.Double getDiscount_percent() {
		return this.discount_percent;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:BetGoldDiscount_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键ID[").append(getId()).append("],")
			.append("充值最小[").append(getMin_recharge()).append("],")
			.append("充值最大[").append(getMax_recharge()).append("],")
			.append("优惠百分比[").append(getDiscount_percent()).append("],")
			.append("代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；[").append(getCompany()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGoldDiscount == false) return false;
		if(this == obj) return true;
		BetGoldDiscount other = (BetGoldDiscount)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
