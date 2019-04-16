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
 * @version  2017-11-28 13:48:31
 * @see org.springrain.lottery.entity.BetLotterychannelaccess
 */
@Table(name="bet_lotterychannelaccess")
public class BetLotterychannelaccess  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "出票渠道";
	public static final String ALIAS_ID = "出票渠道id";
	public static final String ALIAS_NAME = "渠道名称";
	public static final String ALIAS_STATE = "状态（1：启用；0停用）";
	public static final String ALIAS_REMARK = "评论";
    */
	//date formats
	
	//columns START
	/**
	 * 出票渠道id
	 */
	private java.lang.Integer id;
	/**
	 * 渠道名称
	 */
	private java.lang.String name;
	/**
	 * 状态（1：启用；0停用）
	 */
	private java.lang.Integer state;
	/**
	 * 评论
	 */
	private java.lang.String remark;
	private java.lang.String url;
	private java.lang.String merchant;
	private java.lang.String password;
	private java.lang.String publickey;
	private java.lang.String privatekey;
	private java.lang.Double money;
	private java.lang.Double rebate;
	/**
	 * 公司id
	 */
	private String company;
	/**
	 * 归属1系统2分公司代理指定
	 */
	private Integer belonging;
	
	//columns END 数据库字段结束
	@WhereSQL(sql="privatekey=:BetLotterychannelaccess_privatekey")
	public java.lang.String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(java.lang.String privatekey) {
		this.privatekey = privatekey;
	}

	//concstructor
	  @WhereSQL(sql="url=:BetLotterychannelaccess_url")
	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	  @WhereSQL(sql="merchant=:BetLotterychannelaccess_merchant")
	public java.lang.String getMerchant() {
		return merchant;
	}

	public void setMerchant(java.lang.String merchant) {
		this.merchant = merchant;
	}
	@WhereSQL(sql="password=:BetLotterychannelaccess_password")
	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	@WhereSQL(sql="publickey=:BetLotterychannelaccess_publickey")
	public java.lang.String getPublickey() {
		return publickey;
	}

	public void setPublickey(java.lang.String publickey) {
		this.publickey = publickey;
	}
	
	@WhereSQL(sql="money=:BetLotterychannelaccess_money")
	public java.lang.Double getMoney() {
		return money;
	}

	public void setMoney(java.lang.Double money) {
		this.money = money;
	}
	@WhereSQL(sql="rebate=:BetLotterychannelaccess_rebate")
	public java.lang.Double getRebate() {
		return rebate;
	}

	public void setRebate(java.lang.Double rebate) {
		this.rebate = rebate;
	}

	public BetLotterychannelaccess(){
	}

	public BetLotterychannelaccess(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetLotterychannelaccess_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BetLotterychannelaccess_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetLotterychannelaccess_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetLotterychannelaccess_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
     
     @WhereSQL(sql="company=:BetLotterychannelaccess_company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getBelonging() {
		return belonging;
	}

	public void setBelonging(Integer belonging) {
		this.belonging = belonging;
	}

	public String toString() {
		return new StringBuffer()
			.append("出票渠道id[").append(getId()).append("],")
			.append("渠道名称[").append(getName()).append("],")
			.append("状态（1：启用；0停用）[").append(getState()).append("],")
			.append("评论[").append(getRemark()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetLotterychannelaccess == false) return false;
		if(this == obj) return true;
		BetLotterychannelaccess other = (BetLotterychannelaccess)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
