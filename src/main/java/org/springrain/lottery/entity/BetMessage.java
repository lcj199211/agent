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
 * @version  2017-03-01 12:53:20
 * @see org.springrain.lottery.entity.BetMessage
 */
@Table(name="bet_message")
public class BetMessage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "站内消息";
	public static final String ALIAS_ID = "表ID";
	public static final String ALIAS_SENDER = "发送者ID";
	public static final String ALIAS_RECEIVER = "接收者ID";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_CONTEXT = "内容";
	public static final String ALIAS_TIME = "发送时间";
	public static final String ALIAS_STATE = "状态：1查看0未查看2默认";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 表ID
	 */
	private java.lang.String id;
	/**
	 * 发送者ID
	 */
	private java.lang.String sender;
	/**
	 * 接收者ID
	 */
	private java.lang.String receiver;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 内容
	 */
	private java.lang.String context;
	/**
	 * 发送时间
	 */
	private java.util.Date time;
	/**
	 * 状态：1查看0未查看2默认
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public BetMessage(){
	}

	public BetMessage(
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
     @WhereSQL(sql="id=:BetMessage_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setSender(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sender = value;
	}
	
     @WhereSQL(sql="sender=:BetMessage_sender")
	public java.lang.String getSender() {
		return this.sender;
	}
	public void setReceiver(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.receiver = value;
	}
	
     @WhereSQL(sql="receiver=:BetMessage_receiver")
	public java.lang.String getReceiver() {
		return this.receiver;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:BetMessage_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContext(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.context = value;
	}
	
     @WhereSQL(sql="context=:BetMessage_context")
	public java.lang.String getContext() {
		return this.context;
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
	
     @WhereSQL(sql="time=:BetMessage_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetMessage_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("表ID[").append(getId()).append("],")
			.append("发送者ID[").append(getSender()).append("],")
			.append("接收者ID[").append(getReceiver()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("内容[").append(getContext()).append("],")
			.append("发送时间[").append(getTime()).append("],")
			.append("状态：1查看0未查看2默认[").append(getState()).append("],")
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
		if(obj instanceof BetMessage == false) return false;
		if(this == obj) return true;
		BetMessage other = (BetMessage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
