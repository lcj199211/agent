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
 * @version  2017-03-01 12:51:48
 * @see org.springrain.lottery.entity.BetPaymentInterface
 */
@Table(name="bet_payment_interface")
public class BetPaymentInterface  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "支付接口";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_BANKNAME = "姓名";
	public static final String ALIAS_BANKCARD = "账号";
	public static final String ALIAS_BANKTYPE = "通道";
	public static final String ALIAS_STATE = "状态：1上线0下线";
	public static final String ALIAS_REMARKS = "备注";
    */
	//date formats
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.String id;
	/**
	 * 姓名
	 */
	private java.lang.String bankname;
	/**
	 * 账号
	 */
	private java.lang.String bankcard;
	/**
	 * 通道
	 */
	private java.lang.String banktype;
	/**
	 * 状态：1上线0下线
	 */
	private java.lang.Integer state;
	/**
	 * 备注
	 */
	private java.lang.String remarks;
	/**
	 * 秘钥
	 */
	private java.lang.String mkey;
	//columns END 数据库字段结束
	private java.lang.String twobarcode;
	private java.lang.Integer order1;
	private java.lang.String type;
	private java.lang.Integer limit1;
	private java.lang.String bankofdeposit;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	
	@WhereSQL(sql="agentid=:BetPaymentInterface_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetPaymentInterface_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetPaymentInterface_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	@WhereSQL(sql="bankofdeposit=:BetPaymentInterface_bankofdeposit")
	public java.lang.String getBankofdeposit() {
		return bankofdeposit;
	}

	public void setBankofdeposit(java.lang.String bankofdeposit) {
		this.bankofdeposit = bankofdeposit;
	}
	@WhereSQL(sql="limit1=:BetPaymentInterface_limit1")
	public java.lang.Integer getLimit1() {
		return limit1;
	}

	public void setLimit1(java.lang.Integer limit1) {
		this.limit1 = limit1;
	}

	@WhereSQL(sql="type=:BetPaymentInterface_type")
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	@WhereSQL(sql="order1=:BetPaymentInterface_order1")
	public java.lang.Integer getOrder1() {
		return order1;
	}

	public void setOrder1(java.lang.Integer order1) {
		this.order1 = order1;
	}

	//concstructor
	@WhereSQL(sql="twobarcode=:BetPaymentInterface_twobarcode")
	public java.lang.String getTwobarcode() {
		return twobarcode;
	}

	public void setTwobarcode(java.lang.String twobarcode) {
		this.twobarcode = twobarcode;
	}

	public BetPaymentInterface(){
	}

	public BetPaymentInterface(
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
     @WhereSQL(sql="id=:BetPaymentInterface_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setBankname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bankname = value;
	}
	
     @WhereSQL(sql="bankname=:BetPaymentInterface_bankname")
	public java.lang.String getBankname() {
		return this.bankname;
	}
	public void setBankcard(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bankcard = value;
	}
	
     @WhereSQL(sql="bankcard=:BetPaymentInterface_bankcard")
	public java.lang.String getBankcard() {
		return this.bankcard;
	}
	public void setBanktype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.banktype = value;
	}
	
     @WhereSQL(sql="banktype=:BetPaymentInterface_banktype")
	public java.lang.String getBanktype() {
		return this.banktype;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetPaymentInterface_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setRemarks(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remarks = value;
	}
	
     @WhereSQL(sql="remarks=:BetPaymentInterface_remarks")
	public java.lang.String getRemarks() {
		return this.remarks;
	}
     public void setMkey(java.lang.String mkey) {
		this.mkey = mkey;
	}
	
     @WhereSQL(sql="mkey=:BetPaymentInterface_mkey")
	public java.lang.String getMkey() {
		return this.mkey;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("姓名[").append(getBankname()).append("],")
			.append("账号[").append(getBankcard()).append("],")
			.append("通道[").append(getBanktype()).append("],")
			.append("状态：1上线0下线[").append(getState()).append("],")
			.append("备注[").append(getRemarks()).append("],")
			.append("秘钥[").append(getMkey()).append("]")
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
		if(obj instanceof BetPaymentInterface == false) return false;
		if(this == obj) return true;
		BetPaymentInterface other = (BetPaymentInterface)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
