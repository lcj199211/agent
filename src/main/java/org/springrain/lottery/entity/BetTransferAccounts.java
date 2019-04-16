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
 * @version  2017-05-02 10:06:57
 * @see org.springrain.lottery.entity.BetTransferAccounts
 */
@Table(name="bet_transfer_accounts")
public class BetTransferAccounts  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "转账";
	public static final String ALIAS_ID = "转账ID";
	public static final String ALIAS_TIME = "时间";
	public static final String ALIAS_TRANSFERACCOUNTSSCORE = "转账分";
	public static final String ALIAS_TRANSFERMAN = "转账人";
	public static final String ALIAS_TRANSFERMANID2 = "转账人ID2";
	public static final String ALIAS_MEMBERID2 = "接收ID";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 转账ID
	 */
	private java.lang.Integer id;
	/**
	 * 时间
	 */
	private java.util.Date time;
	/**
	 * 转账分
	 */
	private java.lang.Double transferaccountsscore;
	/**
	 * 转账人
	 */
	private java.lang.String transferman;
	/**
	 * 转账人ID2
	 */
	private java.lang.String transfermanid;
	/**
	 * 接收ID
	 */
	private java.lang.Integer memberid2;
	private java.lang.String remark;
	private java.lang.String ip;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	private Integer type;
	//columns END 数据库字段结束
	@WhereSQL(sql="ip=:BetTransferAccounts_ip")
	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	//concstructor
    @WhereSQL(sql="remark=:BetTransferAccounts_remark")
	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public BetTransferAccounts(){
	}

	public BetTransferAccounts(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetTransferAccounts_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/*
	public String gettimeString() {
		return DateUtils.convertDate2String(FORMAT_TIME, gettime());
	}
	public void settimeString(String value) throws ParseException{
		settime(DateUtils.convertString2Date(FORMAT_TIME,value));
	}*/
	
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	
     @WhereSQL(sql="time=:BetTransferAccounts_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setTransferaccountsscore(java.lang.Double value) {
		this.transferaccountsscore = value;
	}
	
     @WhereSQL(sql="transferaccountsscore=:BetTransferAccounts_transferaccountsscore")
	public java.lang.Double getTransferaccountsscore() {
		return this.transferaccountsscore;
	}
	public void setTransferman(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.transferman = value;
	}
	
     @WhereSQL(sql="transferman=:BetTransferAccounts_transferman")
	public java.lang.String getTransferman() {
		return this.transferman;
	}
	public void setTransfermanid(java.lang.String value) {
		this.transfermanid = value;
	}
	
     @WhereSQL(sql="transfermanid=:BetTransferAccounts_transfermanid")
	public java.lang.String getTransfermanid() {
		return this.transfermanid;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetTransferAccounts_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("转账ID[").append(getId()).append("],")
			.append("时间[").append(getTime()).append("],")
			.append("转账分[").append(getTransferaccountsscore()).append("],")
			.append("转账人[").append(getTransferman()).append("],")
			.append("转账人ID2[").append(getTransfermanid()).append("],")
			.append("接收ID[").append(getMemberid2()).append("],")
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
		if(obj instanceof BetTransferAccounts == false) return false;
		if(this == obj) return true;
		BetTransferAccounts other = (BetTransferAccounts)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@WhereSQL(sql="agentid=:BetTransferAccounts_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetTransferAccounts_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetTransferAccounts_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}
	@WhereSQL(sql="type=:BetTransferAccounts_type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}

	
