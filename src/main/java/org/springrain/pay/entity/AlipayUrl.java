package org.springrain.pay.entity;

import javax.persistence.Table;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-25 15:08:35
 * @see org.springrain.lottery.entity.AlipayUrl
 */
@Table(name="alipay_url")
public class AlipayUrl  extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
	private java.lang.String id;
	private java.lang.String prvKey;
	private java.lang.String desKey;
	private java.lang.String md5Key;
	private java.lang.String pubKey;
	private java.lang.String agtId;
	private java.lang.String merId;
	
	@WhereSQL(sql="merId=:AlipayUrl_merId")
	public java.lang.String getMerId() {
		return merId;
	}
	public void setMerId(java.lang.String merId) {
		this.merId = merId;
	}
	private java.lang.String url;
	
	@WhereSQL(sql="id=:AlipayUrl_id")
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	@WhereSQL(sql="prvKey=:AlipayUrl_prvKey")
	public java.lang.String getPrvKey() {
		return prvKey;
	}
	public void setPrvKey(java.lang.String prvKey) {
		this.prvKey = prvKey;
	}
	@WhereSQL(sql="desKey=:AlipayUrl_desKey")
	public java.lang.String getDesKey() {
		return desKey;
	}
	public void setDesKey(java.lang.String desKey) {
		this.desKey = desKey;
	}
	@WhereSQL(sql="pubKey=:AlipayUrl_pubKey")
	public java.lang.String getPubKey() {
		return pubKey;
	}
	public void setPubKey(java.lang.String pubKey) {
		this.pubKey = pubKey;
	}
	@WhereSQL(sql="agtId=:AlipayUrl_agtId")
	public java.lang.String getAgtId() {
		return agtId;
	}
	public void setAgtId(java.lang.String agtId) {
		this.agtId = agtId;
	}
	@WhereSQL(sql="url=:AlipayUrl_url")
	public java.lang.String getUrl() {
		return url;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	@WhereSQL(sql="md5Key=:AlipayUrl_md5Key")
	public java.lang.String getMd5Key() {
		return md5Key;
	}
	public void setMd5Key(java.lang.String md5Key) {
		this.md5Key = md5Key;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
