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
 * @version  2017-05-08 09:57:32
 * @see org.springrain.lottery.entity.BetSubordinateRebate
 */
@Table(name="bet_subordinate_rebate")
public class BetSubordinateRebate  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetSubordinateRebate";
	public static final String ALIAS_ID = "返利表id";
	public static final String ALIAS_REBATE = "返利额度,末尾加%";
	public static final String ALIAS_BETSTREAM = "下线投注流水";
	public static final String ALIAS_BETAMOUNTS = "下线充值";
	public static final String ALIAS_LOSESCORE = "下线输";
	public static final String ALIAS_REMARK = "备注：推广t日输赢r周输赢z首充sc日充rc注册zc";
    */
	//date formats
	
	//columns START
	/**
	 * 返利表id
	 */
	private java.lang.Integer id;
	/**
	 * 返利额度,末尾加%
	 */
	private java.lang.Double rebate;
	/**
	 * 下线投注流水
	 */
	private java.lang.Double betstream;
	/**
	 * 下线充值
	 */
	private java.lang.Double betamounts;
	/**
	 * 下线输
	 */
	private java.lang.Double losescore;
	/**
	 * 备注：推广t日输赢r周输赢z首充sc日充rc注册zc
	 */
	private java.lang.String remark;
	private java.lang.Double scbettingmultiple;
	
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="scbettingmultiple=:BetSubordinateRebate_scbettingmultiple")
	public java.lang.Double getScbettingmultiple() {
		return scbettingmultiple;
	}

	public void setScbettingmultiple(java.lang.Double scbettingmultiple) {
		this.scbettingmultiple = scbettingmultiple;
	}

	public BetSubordinateRebate(){
	}

	public BetSubordinateRebate(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetSubordinateRebate_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setRebate(java.lang.Double value) {
		this.rebate = value;
	}
	
     @WhereSQL(sql="rebate=:BetSubordinateRebate_rebate")
	public java.lang.Double getRebate() {
		return this.rebate;
	}
	public void setBetstream(java.lang.Double value) {
		this.betstream = value;
	}
	
     @WhereSQL(sql="betstream=:BetSubordinateRebate_betstream")
	public java.lang.Double getBetstream() {
		return this.betstream;
	}
	public void setBetamounts(java.lang.Double value) {
		this.betamounts = value;
	}
	
     @WhereSQL(sql="betamounts=:BetSubordinateRebate_betamounts")
	public java.lang.Double getBetamounts() {
		return this.betamounts;
	}
	public void setLosescore(java.lang.Double value) {
		this.losescore = value;
	}
	
     @WhereSQL(sql="losescore=:BetSubordinateRebate_losescore")
	public java.lang.Double getLosescore() {
		return this.losescore;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetSubordinateRebate_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("返利表id[").append(getId()).append("],")
			.append("返利额度,末尾加%[").append(getRebate()).append("],")
			.append("下线投注流水[").append(getBetstream()).append("],")
			.append("下线充值[").append(getBetamounts()).append("],")
			.append("下线输[").append(getLosescore()).append("],")
			.append("备注：推广t日输赢r周输赢z首充sc日充rc注册zc[").append(getRemark()).append("],")
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
		if(obj instanceof BetSubordinateRebate == false) return false;
		if(this == obj) return true;
		BetSubordinateRebate other = (BetSubordinateRebate)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
