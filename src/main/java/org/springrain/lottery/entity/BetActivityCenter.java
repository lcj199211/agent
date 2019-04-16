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
 * @version  2017-05-08 09:55:56
 * @see org.springrain.lottery.entity.BetActivityCenter
 */
@Table(name="bet_activity_center")
public class BetActivityCenter  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetActivityCenter";
	public static final String ALIAS_ID = "活动中心表id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_ACTIVITYBEGINTIME = "活动开始时间";
	public static final String ALIAS_ACTIVITYENDTIME = "活动结束时间";
	public static final String ALIAS_IMG = "图片";
	public static final String ALIAS_STATE = "状态0:已结束 1:进行中";
    */
	//date formats
	//public static final String FORMAT_ACTIVITYBEGINTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ACTIVITYENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 活动中心表id
	 */
	private java.lang.Integer id;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 活动开始时间
	 */
	private java.util.Date activitybegintime;
	/**
	 * 活动结束时间
	 */
	private java.util.Date activityendtime;
	/**
	 * 图片
	 */
	private java.lang.String img;
	/**
	 * 状态0:已结束 1:进行中
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
	 * 代理商id 从根节点开始 逗号隔开
	 */
	private java.lang.String agentparentids;
	private java.lang.Integer type;
	
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="type=:BetActivityCenter_type")
	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	public BetActivityCenter(){
	}

	public BetActivityCenter(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetActivityCenter_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:BetActivityCenter_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:BetActivityCenter_content")
	public java.lang.String getContent() {
		return this.content;
	}
		/*
	public String getactivitybegintimeString() {
		return DateUtils.convertDate2String(FORMAT_ACTIVITYBEGINTIME, getactivitybegintime());
	}
	public void setactivitybegintimeString(String value) throws ParseException{
		setactivitybegintime(DateUtils.convertString2Date(FORMAT_ACTIVITYBEGINTIME,value));
	}*/
	
	public void setActivitybegintime(java.util.Date value) {
		this.activitybegintime = value;
	}
	
     @WhereSQL(sql="activitybegintime=:BetActivityCenter_activitybegintime")
	public java.util.Date getActivitybegintime() {
		return this.activitybegintime;
	}
		/*
	public String getactivityendtimeString() {
		return DateUtils.convertDate2String(FORMAT_ACTIVITYENDTIME, getactivityendtime());
	}
	public void setactivityendtimeString(String value) throws ParseException{
		setactivityendtime(DateUtils.convertString2Date(FORMAT_ACTIVITYENDTIME,value));
	}*/
	
	public void setActivityendtime(java.util.Date value) {
		this.activityendtime = value;
	}
	
     @WhereSQL(sql="activityendtime=:BetActivityCenter_activityendtime")
	public java.util.Date getActivityendtime() {
		return this.activityendtime;
	}
	public void setImg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.img = value;
	}
	
     @WhereSQL(sql="img=:BetActivityCenter_img")
	public java.lang.String getImg() {
		return this.img;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetActivityCenter_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     @WhereSQL(sql="agentid=:BetActivityCenter_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetActivityCenter_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetActivityCenter_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("活动中心表id[").append(getId()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("活动开始时间[").append(getActivitybegintime()).append("],")
			.append("活动结束时间[").append(getActivityendtime()).append("],")
			.append("图片[").append(getImg()).append("],")
			.append("状态0:已结束 1:进行中[").append(getState()).append("],")
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
		if(obj instanceof BetActivityCenter == false) return false;
		if(this == obj) return true;
		BetActivityCenter other = (BetActivityCenter)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
