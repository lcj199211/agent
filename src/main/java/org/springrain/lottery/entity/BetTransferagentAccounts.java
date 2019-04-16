package org.springrain.lottery.entity;

import java.text.ParseException;
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
 * @version  2017-06-08 09:55:02
 * @see org.springrain.lottery.entity.BetTransferagentAccounts
 */
@Table(name="bet_transferagent_accounts")
public class BetTransferagentAccounts  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetTransferagentAccounts";
	public static final String ALIAS_ID = "转账表id(转给代理商)";
	public static final String ALIAS_TIME = "时间";
	public static final String ALIAS_TRANSFERACCOUNTSSCORE = "转账金额";
	public static final String ALIAS_TRANSFERMAN = "转账人";
	public static final String ALIAS_TRANSFERMANAGENTID = "被转代理商id";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_IP = "转账ip";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商从根节点到父节点的id逗号隔开";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 转账表id(转给代理商)
	 */
	private java.lang.Integer id;
	/**
	 * 时间
	 */
	private java.util.Date time;
	/**
	 * 转账金额
	 */
	private java.lang.Double transferaccountsscore;
	/**
	 * 转账人
	 */
	private java.lang.String transferman;
	/**
	 * 被转代理商id
	 */
	private java.lang.String transfermanagentid;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 转账ip
	 */
	private java.lang.String ip;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父级id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商从根节点到父节点的id逗号隔开
	 */
	private java.lang.String agentparentids;
	private Integer type;
	//columns END 数据库字段结束
	
	//concstructor

	public BetTransferagentAccounts(){
	}

	public BetTransferagentAccounts(
		java.lang.Integer id,
		java.lang.String transferman
	){
		this.id = id;
		this.transferman = transferman;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetTransferagentAccounts_id")
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
	
     @WhereSQL(sql="time=:BetTransferagentAccounts_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setTransferaccountsscore(java.lang.Double value) {
		this.transferaccountsscore = value;
	}
	
     @WhereSQL(sql="transferaccountsscore=:BetTransferagentAccounts_transferaccountsscore")
	public java.lang.Double getTransferaccountsscore() {
		return this.transferaccountsscore;
	}
	public void setTransferman(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.transferman = value;
	}
	
	@Id
     @WhereSQL(sql="transferman=:BetTransferagentAccounts_transferman")
	public java.lang.String getTransferman() {
		return this.transferman;
	}
	public void setTransfermanagentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.transfermanagentid = value;
	}
	
     @WhereSQL(sql="transfermanagentid=:BetTransferagentAccounts_transfermanagentid")
	public java.lang.String getTransfermanagentid() {
		return this.transfermanagentid;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetTransferagentAccounts_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setIp(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}
	
     @WhereSQL(sql="ip=:BetTransferagentAccounts_ip")
	public java.lang.String getIp() {
		return this.ip;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetTransferagentAccounts_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetTransferagentAccounts_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetTransferagentAccounts_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
     @WhereSQL(sql="type=:BetTransferagentAccounts_type")
     public Integer getType() {
 		return type;
 	}

 	public void setType(Integer type) {
 		this.type = type;
 	}
	public String toString() {
		return new StringBuffer()
			.append("转账表id(转给代理商)[").append(getId()).append("],")
			.append("时间[").append(getTime()).append("],")
			.append("转账金额[").append(getTransferaccountsscore()).append("],")
			.append("转账人[").append(getTransferman()).append("],")
			.append("被转代理商id[").append(getTransfermanagentid()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("转账ip[").append(getIp()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商从根节点到父节点的id逗号隔开[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getTransferman())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetTransferagentAccounts == false) return false;
		if(this == obj) return true;
		BetTransferagentAccounts other = (BetTransferagentAccounts)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getTransferman(),other.getTransferman())
			.isEquals();
	}

	
}

	
