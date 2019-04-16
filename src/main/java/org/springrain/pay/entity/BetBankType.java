package org.springrain.pay.entity;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

@Table(name="bet_banktype")
public class BetBankType  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 银行名称
	 */
	private java.lang.String bankname;
	/**
	 * 银行编码
	 */
	private java.lang.String bankcode;
	/**
	 * 银行logo
	 */
	private java.lang.String logo;
	
    @WhereSQL(sql="id=:BetBankType_id")
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
    @WhereSQL(sql="bankname=:BetBankType_bankname")
	public java.lang.String getBankname() {
		return bankname;
	}
	public void setBankname(java.lang.String bankname) {
		this.bankname = bankname;
	}
	
	@WhereSQL(sql="logo=:BetBankType_logo")
	public java.lang.String getLogo() {
		return this.logo;
	}
	public void setLogo(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
		 value=value.trim();
		}
	    this.logo = value;
	}
    @WhereSQL(sql="bankname=:BetBankType_bankname")
	public java.lang.String getBankcode() {
		return bankcode;
	}
	public void setBankcode(java.lang.String bankcode) {
		this.bankcode = bankcode;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BetBankType(){
	}

	public BetBankType(
		java.lang.Integer id
	){
		this.id = id;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("银行名称[").append(getBankname()).append("],")
			.append("银行编码[").append(getBankcode()).append("],")
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
		if(obj instanceof BetBankType == false) return false;
		if(this == obj) return true;
		BetBankType other = (BetBankType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

}
