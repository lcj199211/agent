package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:52:23
 * @see org.springrain.lottery.entity.BetRankConf
 */
@Table(name="bet_rank_conf")
public class BetRankConf  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "排行榜设置";
	public static final String ALIAS_ID = "表ID";
	public static final String ALIAS_ORDERNO = "排名";
	public static final String ALIAS_AWARD = "奖励";
    */
	//date formats
	
	//columns START
	/**
	 * 表ID
	 */
	private java.lang.Integer id;
	/**
	 * 排名
	 */
	private java.lang.Integer orderno;
	/**
	 * 奖励
	 */
	private java.lang.Double award;
	//columns END 数据库字段结束
	
	//concstructor

	public BetRankConf(){
	}

	public BetRankConf(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetRankConf_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setOrderno(java.lang.Integer value) {
		this.orderno = value;
	}
	
     @WhereSQL(sql="orderno=:BetRankConf_orderno")
	public java.lang.Integer getOrderno() {
		return this.orderno;
	}
	public void setAward(java.lang.Double value) {
		this.award = value;
	}
	
     @WhereSQL(sql="award=:BetRankConf_award")
	public java.lang.Double getAward() {
		return this.award;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("表ID[").append(getId()).append("],")
			.append("排名[").append(getOrderno()).append("],")
			.append("奖励[").append(getAward()).append("],")
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
		if(obj instanceof BetRankConf == false) return false;
		if(this == obj) return true;
		BetRankConf other = (BetRankConf)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
