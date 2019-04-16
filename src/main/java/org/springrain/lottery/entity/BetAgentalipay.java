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
 * @version  2018-01-06 15:13:59
 * @see org.springrain.lottery.entity.BetAgentalipay
 */
@Table(name="bet_agentalipay")
public class BetAgentalipay  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetAgentalipay";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_APPID = "appid";
	public static final String ALIAS_SELLERID = "seller_id";
	public static final String ALIAS_ALIPAYPUBLICKEY = "alipay_public_key";
	public static final String ALIAS_ALIPAYPRIVATEKEY = "alipay_private_key";
	public static final String ALIAS_AGENTID = "代理ID";
    */
	//date formats
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.Integer id;
	/**
	 * appid
	 */
	private java.lang.String appid;
	/**
	 * seller_id
	 */
	private java.lang.String sellerid;
	/**
	 * alipay_public_key
	 */
	private java.lang.String alipaypublickey;
	/**
	 * alipay_private_key
	 */
	private java.lang.String alipayprivatekey;
	/**
	 * 代理ID
	 */
	private java.lang.String agentid;
	private java.lang.Double daymoney;
	private java.lang.Integer state;
	private java.lang.String mailbox;
	private java.lang.String rsapublickey;
	
	private Double startmoney;
	private Double endmoney;
	private Double daylimit;
	private String starttime;
	private String endtime;
	
	
	@WhereSQL(sql="rsapublickey=:BetAgentalipay_rsapublickey")
	public java.lang.String getRsapublickey() {
		return rsapublickey;
	}

	public void setRsapublickey(java.lang.String rsapublickey) {
		this.rsapublickey = rsapublickey;
	}

	@WhereSQL(sql="mailbox=:BetAgentalipay_mailbox")
	public java.lang.String getMailbox() {
		return mailbox;
	}

	public void setMailbox(java.lang.String mailbox) {
		this.mailbox = mailbox;
	}

	//columns END 数据库字段结束
	 @WhereSQL(sql="state=:BetAgentalipay_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	//concstructor
	 @WhereSQL(sql="daymoney=:BetAgentalipay_daymoney")
	public java.lang.Double getDaymoney() {
		return daymoney;
	}

	public void setDaymoney(java.lang.Double daymoney) {
		this.daymoney = daymoney;
	}

	public BetAgentalipay(){
	}

	public BetAgentalipay(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentalipay_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setAppid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.appid = value;
	}
	
     @WhereSQL(sql="appid=:BetAgentalipay_appid")
	public java.lang.String getAppid() {
		return this.appid;
	}
	public void setSellerid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sellerid = value;
	}
	
     @WhereSQL(sql="sellerid=:BetAgentalipay_sellerid")
	public java.lang.String getSellerid() {
		return this.sellerid;
	}
	public void setAlipaypublickey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.alipaypublickey = value;
	}
	
     @WhereSQL(sql="alipaypublickey=:BetAgentalipay_alipaypublickey")
	public java.lang.String getAlipaypublickey() {
		return this.alipaypublickey;
	}
	public void setAlipayprivatekey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.alipayprivatekey = value;
	}
	
     @WhereSQL(sql="alipayprivatekey=:BetAgentalipay_alipayprivatekey")
	public java.lang.String getAlipayprivatekey() {
		return this.alipayprivatekey;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentalipay_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
     @WhereSQL(sql="startmoney=:BetAgentalipay_startmoney")
     public Double getStartmoney() {
 		return startmoney;
 	}

 	public void setStartmoney(Double startmoney) {
 		this.startmoney = startmoney;
 	}
 	 @WhereSQL(sql="endmoney=:BetAgentalipay_endmoney")
 	public Double getEndmoney() {
 		return endmoney;
 	}

 	public void setEndmoney(Double endmoney) {
 		this.endmoney = endmoney;
 	}
 	@WhereSQL(sql="daylimit=:BetAgentalipay_daylimit")
 	public Double getDaylimit() {
 		return daylimit;
 	}

 	public void setDaylimit(Double daylimit) {
 		this.daylimit = daylimit;
 	}
 	@WhereSQL(sql="starttime=:BetAgentalipay_starttime")
 	public String getStarttime() {
 		return starttime;
 	}

 	public void setStarttime(String starttime) {
 		this.starttime = starttime;
 	}
 	@WhereSQL(sql="endtime=:BetAgentalipay_endtime")
 	public String getEndtime() {
 		return endtime;
 	}

 	public void setEndtime(String endtime) {
 		this.endtime = endtime;
 	}
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("appid[").append(getAppid()).append("],")
			.append("seller_id[").append(getSellerid()).append("],")
			.append("alipay_public_key[").append(getAlipaypublickey()).append("],")
			.append("alipay_private_key[").append(getAlipayprivatekey()).append("],")
			.append("代理ID[").append(getAgentid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentalipay == false) return false;
		if(this == obj) return true;
		BetAgentalipay other = (BetAgentalipay)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
}

	
