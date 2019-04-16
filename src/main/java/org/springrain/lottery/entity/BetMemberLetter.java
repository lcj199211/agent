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
 * @version  2018-07-10 17:29:40
 * @see org.springrain.lottery.entity.BetMemberLetter
 */
@Table(name="bet_member_letter")
public class BetMemberLetter  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetMemberLetter";
	public static final String ALIAS_ID = "站内信id";
	public static final String ALIAS_MEMBERID2 = "用户id2";
	public static final String ALIAS_TYPE = "信息类型（1系统信息）";
	public static final String ALIAS_TITLE = "信息标题";
	public static final String ALIAS_MESSAGE = "信息";
	public static final String ALIAS_ENTRYTIME = "录入时间";
	public static final String ALIAS_STATE = "信息状态（1已读取；3未读取）";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
    */
	//date formats
	//public static final String FORMAT_ENTRYTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 站内信id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id2
	 */
	private java.lang.String memberid2;
	/**
	 * 信息类型（1系统信息）
	 */
	private java.lang.Integer type;
	/**
	 * 信息标题
	 */
	private java.lang.String title;
	/**
	 * 信息
	 */
	private java.lang.String message;
	/**
	 * 录入时间
	 */
	private java.util.Date entrytime;
	/**
	 * 信息状态（1已读取；3未读取）
	 */
	private java.lang.Integer state;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父级id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商id 从根节点开始
	 */
	private java.lang.String agentparentids;
	//columns END 数据库字段结束
	
	//concstructor

	public BetMemberLetter(){
	}

	public BetMemberLetter(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetMemberLetter_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetMemberLetter_memberid2")
	public java.lang.String getMemberid2() {
		return this.memberid2;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetMemberLetter_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:BetMemberLetter_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setMessage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.message = value;
	}
	
     @WhereSQL(sql="message=:BetMemberLetter_message")
	public java.lang.String getMessage() {
		return this.message;
	}
		/*
	public String getentrytimeString() {
		return DateUtils.convertDate2String(FORMAT_ENTRYTIME, getentrytime());
	}
	public void setentrytimeString(String value) throws ParseException{
		setentrytime(DateUtils.convertString2Date(FORMAT_ENTRYTIME,value));
	}*/
	
	public void setEntrytime(java.util.Date value) {
		this.entrytime = value;
	}
	
     @WhereSQL(sql="entrytime=:BetMemberLetter_entrytime")
	public java.util.Date getEntrytime() {
		return this.entrytime;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetMemberLetter_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetMemberLetter_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetMemberLetter_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetMemberLetter_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("站内信id[").append(getId()).append("],")
			.append("用户id2[").append(getMemberid2()).append("],")
			.append("信息类型（1系统信息）[").append(getType()).append("],")
			.append("信息标题[").append(getTitle()).append("],")
			.append("信息[").append(getMessage()).append("],")
			.append("录入时间[").append(getEntrytime()).append("],")
			.append("信息状态（1已读取；3未读取）[").append(getState()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetMemberLetter == false) return false;
		if(this == obj) return true;
		BetMemberLetter other = (BetMemberLetter)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
