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
 * @version  2017-04-13 12:47:57
 * @see org.springrain.lottery.entity.BetMemberbankcard
 */
@Table(name="bet_memberbankcard")
public class BetMemberbankcard  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetMemberbankcard";
	public static final String ALIAS_ID = "用户银行卡ID";
	public static final String ALIAS_MEMBERID2 = "用户ID2";
	public static final String ALIAS_ISDEFAULT = "是否默认";
	public static final String ALIAS_BANKTYPE = "类别";
	public static final String ALIAS_ACCOUNT = "账户";
	public static final String ALIAS_BINDTIME = "绑定时间";
    */
	//date formats
	//public static final String FORMAT_BINDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 用户银行卡ID
	 */
	private java.lang.Integer id;
	/**
	 * 用户ID2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 是否默认
	 */
	private java.lang.Integer isdefault;
	/**
	 * 类别
	 */
	private java.lang.String banktype;
	/**
	 * 账户
	 */
	private java.lang.String account;
	/**
	 * 绑定时间
	 */
	private java.util.Date bindtime;
	private java.lang.Integer banktype1;
	private java.lang.Double availablecredit;
	private java.lang.String bankofdeposit;
	private java.lang.Integer type;//1银行卡2支付宝
	@WhereSQL(sql="bankofdeposit=:BetMemberbankcard_bankofdeposit")
	public java.lang.String getBankofdeposit() {
		return bankofdeposit;
	}

	public void setBankofdeposit(java.lang.String bankofdeposit) {
		this.bankofdeposit = bankofdeposit;
	}
	
	@WhereSQL(sql="type=:BetMemberbankcard_type")
	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	//columns END 数据库字段结束
	@WhereSQL(sql="availablecredit=:BetMemberbankcard_availablecredit")
	public java.lang.Double getAvailablecredit() {
		return availablecredit;
	}

	public void setAvailablecredit(java.lang.Double availablecredit) {
		this.availablecredit = availablecredit;
	}

	//concstructor
	@WhereSQL(sql="banktype1=:BetMemberbankcard_banktype1")
	public java.lang.Integer getBanktype1() {
		return banktype1;
	}

	public void setBanktype1(java.lang.Integer banktype1) {
		this.banktype1 = banktype1;
	}

	public BetMemberbankcard(){
	}

	public BetMemberbankcard(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetMemberbankcard_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetMemberbankcard_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setIsdefault(java.lang.Integer value) {
		this.isdefault = value;
	}
	
     @WhereSQL(sql="isdefault=:BetMemberbankcard_isdefault")
	public java.lang.Integer getIsdefault() {
		return this.isdefault;
	}
	public void setBanktype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.banktype = value;
	}
	
     @WhereSQL(sql="banktype=:BetMemberbankcard_banktype")
	public java.lang.String getBanktype() {
		return this.banktype;
	}
	public void setAccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.account = value;
	}
	
     @WhereSQL(sql="account=:BetMemberbankcard_account")
	public java.lang.String getAccount() {
		return this.account;
	}
		/*
	public String getbindtimeString() {
		return DateUtils.convertDate2String(FORMAT_BINDTIME, getbindtime());
	}
	public void setbindtimeString(String value) throws ParseException{
		setbindtime(DateUtils.convertString2Date(FORMAT_BINDTIME,value));
	}*/
	
	public void setBindtime(java.util.Date value) {
		this.bindtime = value;
	}
	
     @WhereSQL(sql="bindtime=:BetMemberbankcard_bindtime")
	public java.util.Date getBindtime() {
		return this.bindtime;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("用户银行卡ID[").append(getId()).append("],")
			.append("用户ID2[").append(getMemberid2()).append("],")
			.append("是否默认[").append(getIsdefault()).append("],")
			.append("类别[").append(getBanktype()).append("],")
			.append("账户[").append(getAccount()).append("],")
			.append("绑定时间[").append(getBindtime()).append("],")
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
		if(obj instanceof BetMemberbankcard == false) return false;
		if(this == obj) return true;
		BetMemberbankcard other = (BetMemberbankcard)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
