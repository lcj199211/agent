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
 * @version  2017-03-14 15:56:46
 * @see org.springrain.lottery.entity.BetCollectionAddress
 */
@Table(name="bet_collection_address")
public class BetCollectionAddress  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "采集地址";
	public static final String ALIAS_ID = "游戏ID";
	public static final String ALIAS_GCNAME = "游戏名称";
	public static final String ALIAS_ADDRESS = "数据采集地址";
    */
	//date formats
	
	//columns START
	/**
	 * 游戏ID
	 */
	private java.lang.Integer id;
	/**
	 * 游戏名称
	 */
	private java.lang.String gcname;
	/**
	 * 数据采集地址
	 */
	private java.lang.String address;
	//columns END 数据库字段结束
	
	//concstructor

	public BetCollectionAddress(){
	}

	public BetCollectionAddress(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetCollectionAddress_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGcname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gcname = value;
	}
	
     @WhereSQL(sql="gcname=:BetCollectionAddress_gcname")
	public java.lang.String getGcname() {
		return this.gcname;
	}
	public void setAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.address = value;
	}
	
     @WhereSQL(sql="address=:BetCollectionAddress_address")
	public java.lang.String getAddress() {
		return this.address;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("游戏ID[").append(getId()).append("],")
			.append("游戏名称[").append(getGcname()).append("],")
			.append("数据采集地址[").append(getAddress()).append("],")
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
		if(obj instanceof BetCollectionAddress == false) return false;
		if(this == obj) return true;
		BetCollectionAddress other = (BetCollectionAddress)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
