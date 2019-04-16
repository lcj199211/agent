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
 * @version  2017-11-15 11:11:45
 * @see org.springrain.lottery.entity.BetAgentannouncement
 */
@Table(name="bet_agentannouncement")
public class BetAgentannouncement  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "代理公告";
	public static final String ALIAS_ID = "代理公告id";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
	public static final String ALIAS_TIME = "发布时间";
	public static final String ALIAS_TYPE = "类型（0一般公告；1重要公告；2个人公告）";
	public static final String ALIAS_CONTENT = "公告内容";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 代理公告id
	 */
	private java.lang.Integer id;
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
	/**
	 * 发布时间
	 */
	private java.util.Date time;
	/**
	 * 类型（0一般公告；1重要公告；2个人公告）
	 */
	private java.lang.Integer type;
	/**
	 * 公告内容
	 */
	private java.lang.String content;
	private java.lang.String agentaccount;
	//columns END 数据库字段结束
	
	//concstructor
	 @WhereSQL(sql="agentaccount=:BetAgentannouncement_agentaccount")
	public java.lang.String getAgentaccount() {
		return agentaccount;
	}

	public void setAgentaccount(java.lang.String agentaccount) {
		this.agentaccount = agentaccount;
	}

	public BetAgentannouncement(){
	}

	public BetAgentannouncement(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentannouncement_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentannouncement_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetAgentannouncement_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetAgentannouncement_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
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
	
     @WhereSQL(sql="time=:BetAgentannouncement_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetAgentannouncement_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:BetAgentannouncement_content")
	public java.lang.String getContent() {
		return this.content;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("代理公告id[").append(getId()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.append("发布时间[").append(getTime()).append("],")
			.append("类型（0一般公告；1重要公告；2个人公告）[").append(getType()).append("],")
			.append("公告内容[").append(getContent()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentannouncement == false) return false;
		if(this == obj) return true;
		BetAgentannouncement other = (BetAgentannouncement)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
