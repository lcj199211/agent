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
 * @version  2018-03-09 11:07:04
 * @see org.springrain.lottery.entity.RenjiuResult
 */
@Table(name="renjiu_result")
public class RenjiuResult  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "RenjiuResult";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PERIODNUM = "期号";
	public static final String ALIAS_RESULT = "结果";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 期号
	 */
	private java.lang.String periodnum;
	/**
	 * 结果
	 */
	private java.lang.String result;
	
	private Double salesmoney;
	private Integer winnum;
	private Double bonus;
	private Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public RenjiuResult(){
	}

	public RenjiuResult(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:RenjiuResult_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setPeriodnum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.periodnum = value;
	}
	
     @WhereSQL(sql="periodnum=:RenjiuResult_periodnum")
	public java.lang.String getPeriodnum() {
		return this.periodnum;
	}
	public void setResult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.result = value;
	}
	
     @WhereSQL(sql="result=:RenjiuResult_result")
	public java.lang.String getResult() {
		return this.result;
	}
     @WhereSQL(sql="salesmoney=:RenjiuResult_salesmoney")
     public Double getSalesmoney() {
 		return salesmoney;
 	}

 	public void setSalesmoney(Double salesmoney) {
 		this.salesmoney = salesmoney;
 	}
 	 @WhereSQL(sql="winnum=:RenjiuResult_winnum")
 	public Integer getWinnum() {
 		return winnum;
 	}

 	public void setWinnum(Integer winnum) {
 		this.winnum = winnum;
 	}
 	@WhereSQL(sql="bonus=:RenjiuResult_bonus")
 	public Double getBonus() {
 		return bonus;
 	}

 	public void setBonus(Double bonus) {
 		this.bonus = bonus;
 	}
 	@WhereSQL(sql="state=:RenjiuResult_state")
 	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("期号[").append(getPeriodnum()).append("],")
			.append("结果[").append(getResult()).append("]")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RenjiuResult == false) return false;
		if(this == obj) return true;
		RenjiuResult other = (RenjiuResult)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	

	
}

	
