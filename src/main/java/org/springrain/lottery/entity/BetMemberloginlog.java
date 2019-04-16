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
 * @version  2017-03-31 13:36:49
 * @see org.springrain.lottery.entity.BetMemberloginlog
 */
@Table(name="bet_memberloginlog")
public class BetMemberloginlog  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetMemberloginlog";
	public static final String ALIAS_ID = "用户登录ID";
	public static final String ALIAS_MEMBERID = "用户id";
	public static final String ALIAS_IP = "登录IP";
	public static final String ALIAS_TIME = "登录时间";
	public static final String ALIAS_TOOL = "登录工具";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 用户登录ID
	 */
	private java.lang.Integer id;
	/**
	 * 用户id
	 */
	private java.lang.Integer memberid2;
	/**
	 * 登录IP
	 */
	private java.lang.String ip;
	/**
	 * 登录时间
	 */
	private java.util.Date time;
	/**
	 * 登录工具
	 */
	private java.lang.String tool;
	//columns END 数据库字段结束
	
	//concstructor

	public BetMemberloginlog(){
	}

	public BetMemberloginlog(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetMemberloginlog_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetMemberloginlog_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setIp(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}
	
     @WhereSQL(sql="ip=:BetMemberloginlog_ip")
	public java.lang.String getIp() {
		return this.ip;
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
	
     @WhereSQL(sql="time=:BetMemberloginlog_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setTool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tool = value;
	}
	
     @WhereSQL(sql="tool=:BetMemberloginlog_tool")
	public java.lang.String getTool() {
		return this.tool;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("用户登录ID[").append(getId()).append("],")
			.append("用户id[").append(getMemberid2()).append("],")
			.append("登录IP[").append(getIp()).append("],")
			.append("登录时间[").append(getTime()).append("],")
			.append("登录工具[").append(getTool()).append("],")
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
		if(obj instanceof BetMemberloginlog == false) return false;
		if(this == obj) return true;
		BetMemberloginlog other = (BetMemberloginlog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
