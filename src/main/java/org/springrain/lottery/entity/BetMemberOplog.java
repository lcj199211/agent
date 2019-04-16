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
 * @version  2017-04-17 23:56:12
 * @see org.springrain.lottery.entity.BetMemberOplog
 */
@Table(name="bet_member_oplog")
public class BetMemberOplog  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户操作日志";
	public static final String ALIAS_ID = "用户操作日志ID";
	public static final String ALIAS_MEMBERID2 = "用户ID2";
	public static final String ALIAS_OPERATIONDETAILS = "操作详情";
	public static final String ALIAS_CURRENTLOCATION = "当前所在位置";
	public static final String ALIAS_LOGINIP = "登录地址";
	public static final String ALIAS_LOGINTOOL = "登陆";
	public static final String ALIAS_TIME = "时间";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 用户操作日志ID
	 */
	private java.lang.Integer id;
	/**
	 * 用户ID2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 操作详情
	 */
	private java.lang.String operationdetails;
	/**
	 * 当前所在位置
	 */
	private java.lang.String currentlocation;
	/**
	 * 登录地址
	 */
	private java.lang.String loginip;
	/**
	 * 登陆
	 */
	private java.lang.String logintool;
	/**
	 * 时间
	 */
	private java.util.Date time;
	//columns END 数据库字段结束
	
	//concstructor

	public BetMemberOplog(){
	}

	public BetMemberOplog(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetMemberOplog_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetMemberOplog_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setOperationdetails(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operationdetails = value;
	}
	
     @WhereSQL(sql="operationdetails=:BetMemberOplog_operationdetails")
	public java.lang.String getOperationdetails() {
		return this.operationdetails;
	}
	public void setCurrentlocation(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.currentlocation = value;
	}
	
     @WhereSQL(sql="currentlocation=:BetMemberOplog_currentlocation")
	public java.lang.String getCurrentlocation() {
		return this.currentlocation;
	}
	public void setLoginip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.loginip = value;
	}
	
     @WhereSQL(sql="loginip=:BetMemberOplog_loginip")
	public java.lang.String getLoginip() {
		return this.loginip;
	}
	public void setLogintool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.logintool = value;
	}
	
     @WhereSQL(sql="logintool=:BetMemberOplog_logintool")
	public java.lang.String getLogintool() {
		return this.logintool;
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
	
     @WhereSQL(sql="time=:BetMemberOplog_time")
	public java.util.Date getTime() {
		return this.time;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("用户操作日志ID[").append(getId()).append("],")
			.append("用户ID2[").append(getMemberid2()).append("],")
			.append("操作详情[").append(getOperationdetails()).append("],")
			.append("当前所在位置[").append(getCurrentlocation()).append("],")
			.append("登录地址[").append(getLoginip()).append("],")
			.append("登陆[").append(getLogintool()).append("],")
			.append("时间[").append(getTime()).append("],")
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
		if(obj instanceof BetMemberOplog == false) return false;
		if(this == obj) return true;
		BetMemberOplog other = (BetMemberOplog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
