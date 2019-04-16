package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 投注方式及玩法
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:35:18
 * @see org.springrain.lottery.entity.LotteryPlaymethod
 */
@Table(name="lottery_playmethod")
public class LotteryPlaymethod  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "大乐透玩法表";
	public static final String ALIAS_ID = "玩法ID";
	public static final String ALIAS_NAME = "玩法名称";
	public static final String ALIAS_SHORTNAME = "玩法短名称";
	public static final String ALIAS_STATE = "开启状态 1正常 3删除";
	public static final String ALIAS_EXPLAIN = "格式说明";
	public static final String ALIAS_NUMBERS = "号码个数";
	public static final String ALIAS_DEMO = "注码示例";
    */
	//date formats
	
	//columns START
	/**
	 * 玩法ID
	 */
	private java.lang.Integer id;
	/**
	 * 玩法名称
	 */
	private java.lang.String name;
	/**
	 * 玩法短名称
	 */
	private java.lang.String shortname;
	/**
	 * 开启状态 1正常 3删除
	 */
	private java.lang.Integer state;
	/**
	 * 格式说明
	 */
	private java.lang.String explain;
	/**
	 * 号码个数
	 */
	private java.lang.String numbers;
	/**
	 * 注码示例
	 */
	private java.lang.String demo;
	//columns END 数据库字段结束
	
	//concstructor

	public LotteryPlaymethod(){
	}

	public LotteryPlaymethod(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	
	/**
	 * 玩法ID
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:LotteryPlaymethod_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	
	/**
	 * 玩法名称
	 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:LotteryPlaymethod_name")
	public java.lang.String getName() {
		return this.name;
	}
     
     /**
 	 * 玩法短名称
 	 */
	public void setShortname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shortname = value;
	}
	
     @WhereSQL(sql="shortname=:LotteryPlaymethod_shortname")
	public java.lang.String getShortname() {
		return this.shortname;
	}
     
     /**
 	 * 开启状态 1正常 3删除
 	 */
 	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:LotteryPlaymethod_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     /**
 	 * 格式说明
 	 */
 	public void setExplain(java.lang.String value) {
 			if(StringUtils.isNotBlank(value)){
 				value=value.trim();
 			}
 			this.explain = value;
 	}

 	@WhereSQL(sql="explain=:LotteryPlaymethod_explain")
 	public java.lang.String getExplain() {
 		return this.explain;
 	}
 
 	/**
	 * 号码个数
	 */
	public void setNumbers(java.lang.String value) {
		this.numbers = value;
	}
	
     @WhereSQL(sql="numbers=:LotteryPlaymethod_numbers")
	public java.lang.String getNumbers() {
		return this.numbers;
	}
     
     /**
 	 * 注码示例
 	 */
	public void setDemo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.demo = value;
	}
	
     @WhereSQL(sql="demo=:LotteryPlaymethod_demo")
	public java.lang.String getDemo() {
		return this.demo;
	}


	
	public String toString() {
		return new StringBuffer()
			.append("玩法ID[").append(getId()).append("],")
			.append("玩法名称[").append(getName()).append("],")
			.append("玩法短名称[").append(getShortname()).append("],")
			.append("开启状态 1正常 3删除[").append(getState()).append("],")
			.append("格式说明[").append(getExplain()).append("],")
			.append("号码个数[").append(getNumbers()).append("],")
			.append("注码示例[").append(getDemo()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LotteryPlaymethod == false) return false;
		if(this == obj) return true;
		LotteryPlaymethod other = (LotteryPlaymethod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
