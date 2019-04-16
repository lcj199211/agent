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
 * @version  2017-03-01 12:51:24
 * @see org.springrain.lottery.entity.BetOptLog
 */
@Table(name="bet_opt_log")
public class BetOptLog  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "操作日志";
	public static final String ALIAS_ID = "表ID";
	public static final String ALIAS_USER_ID = "管理ID";
	public static final String ALIAS_NAME = "昵称";
	public static final String ALIAS_DETAILS = "操作详情";
	public static final String ALIAS_TIME = "时间";
	public static final String ALIAS_IP = "登录地址";
	public static final String ALIAS_TOOL = "工具";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 表ID
	 */
	private java.lang.String id;
	/**
	 * 管理ID
	 */
	private java.lang.Integer userid;
	/**
	 * 管理员账户
	 */
	private java.lang.String account;
	/**
	 * 昵称
	 */
	private java.lang.String name;
	/**
	 * 操作详情
	 */
	private java.lang.String details;
	/**
	 * 时间
	 */
	private java.util.Date time;
	/**
	 * 登录地址
	 */
	private java.lang.String ip;
	/**
	 * 工具
	 */
	private java.lang.String tool;
	private String agentid;
	private String agentparentid;
	private String agentparentids;
	//columns END 数据库字段结束
	
	//concstructor

	public BetOptLog(){
	}

	public BetOptLog(
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
     @WhereSQL(sql="id=:BetOptLog_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}
	
     @WhereSQL(sql="userid=:BetOptLog_userid")
	public java.lang.Integer getUserid() {
		return this.userid;
	}
     public void setAccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.account = value;
	}
	
     @WhereSQL(sql="account=:BetOptLog_account")
	public java.lang.String getAccount() {
		return this.account;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BetOptLog_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setDetails(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.details = value;
	}
	
     @WhereSQL(sql="details=:BetOptLog_details")
	public java.lang.String getDetails() {
		return this.details;
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
	
     @WhereSQL(sql="time=:BetOptLog_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setIp(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}
	
     @WhereSQL(sql="ip=:BetOptLog_ip")
	public java.lang.String getIp() {
		return this.ip;
	}
	public void setTool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tool = value;
	}
	
     @WhereSQL(sql="tool=:BetOptLog_tool")
	public java.lang.String getTool() {
		return this.tool;
	}
     
     @WhereSQL(sql="agentid=:BetOptLog_agentid")
     public String getAgentid() {
 		return agentid;
 	}

 	public void setAgentid(String agentid) {
 		this.agentid = agentid;
 	}
 	
 	@WhereSQL(sql="agentparentid=:BetOptLog_agentparentid")
 	public String getAgentparentid() {
 		return agentparentid;
 	}

 	public void setAgentparentid(String agentparentid) {
 		this.agentparentid = agentparentid;
 	}
 	
 	@WhereSQL(sql="agentparentids=:BetOptLog_agentparentids")
 	public String getAgentparentids() {
 		return agentparentids;
 	}

 	public void setAgentparentids(String agentparentids) {
 		this.agentparentids = agentparentids;
 	}
	@Override
	public String toString() {
		return new StringBuffer()
			.append("表ID[").append(getId()).append("],")
			.append("管理ID[").append(getUserid()).append("],")
			.append("昵称[").append(getName()).append("],")
			.append("操作详情[").append(getDetails()).append("],")
			.append("时间[").append(getTime()).append("],")
			.append("登录地址[").append(getIp()).append("],")
			.append("工具[").append(getTool()).append("],")
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
		if(obj instanceof BetOptLog == false) return false;
		if(this == obj) return true;
		BetOptLog other = (BetOptLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
}

	
