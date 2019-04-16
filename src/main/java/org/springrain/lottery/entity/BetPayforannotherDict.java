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
 * @version  2018-12-04 13:44:07
 * @see org.springrain.lottery.entity.BetPayforannotherDict
 */
@Table(name="bet_payforanother_dict")
public class BetPayforannotherDict  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetPayforannotherDict";
	public static final String ALIAS_AGENTID = "代理公司";
	public static final String ALIAS_PUBLICKEY = "公钥";
	public static final String ALIAS_PRIVATEKEY = "私钥";
	public static final String ALIAS_MD5KEY = "用于加密的key";
	public static final String ALIAS_MERID = "商户id";
	public static final String ALIAS_URL = "接口地址";
    */
	//date formats
	
	//columns START
	/**
	 * 代理公司
	 */
	private java.lang.String id;
	/**
	 * 公钥
	 */
	private java.lang.String publickey;
	/**
	 * 私钥
	 */
	private java.lang.String privatekey;
	/**
	 * 用于加密的key
	 */
	private java.lang.String md5key;
	/**
	 * 商户id
	 */
	private java.lang.String merid;
	/**
	 * 接口地址
	 */
	private java.lang.String url;
	//columns END 数据库字段结束
	
	//concstructor

	public BetPayforannotherDict(){
	}

	public BetPayforannotherDict(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="Id=:BetPayforannotherDict_Id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setPublickey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.publickey = value;
	}
	
     @WhereSQL(sql="publickey=:BetPayforannotherDict_publickey")
	public java.lang.String getPublickey() {
		return this.publickey;
	}
	public void setPrivatekey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.privatekey = value;
	}
	
     @WhereSQL(sql="privatekey=:BetPayforannotherDict_privatekey")
	public java.lang.String getPrivatekey() {
		return this.privatekey;
	}
	public void setMd5key(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.md5key = value;
	}
	
     @WhereSQL(sql="md5key=:BetPayforannotherDict_md5key")
	public java.lang.String getMd5key() {
		return this.md5key;
	}
	public void setMerid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.merid = value;
	}
	
     @WhereSQL(sql="merid=:BetPayforannotherDict_merid")
	public java.lang.String getMerid() {
		return this.merid;
	}
	public void setUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.url = value;
	}
	
     @WhereSQL(sql="url=:BetPayforannotherDict_url")
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("代理公司[").append(getId()).append("],")
			.append("公钥[").append(getPublickey()).append("],")
			.append("私钥[").append(getPrivatekey()).append("],")
			.append("用于加密的key[").append(getMd5key()).append("],")
			.append("商户id[").append(getMerid()).append("],")
			.append("接口地址[").append(getUrl()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetPayforannotherDict == false) return false;
		if(this == obj) return true;
		BetPayforannotherDict other = (BetPayforannotherDict)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
