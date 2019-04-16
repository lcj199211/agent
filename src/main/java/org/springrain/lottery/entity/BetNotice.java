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
 * @version  2018-11-08 11:26:09
 * @see org.springrain.lottery.entity.BetNotice
 */
@Table(name="bet_notice")
public class BetNotice  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "公告 新闻";
	public static final String ALIAS_ID = "公告表ID";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_STATE = "状态：1发布中0下架";
	public static final String ALIAS_MARK = "标记：news,help,page,notice,importNotice";
	public static final String ALIAS_TIME = "发布时间";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商id 从根节点开始";
	public static final String ALIAS_ICON = "icon";
	public static final String ALIAS_IMAGES = "images";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 公告表ID
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
	 * 状态：1发布中0下架
	 */
	private java.lang.Integer state;
	/**
	 * 标记：news,help,page,notice,importNotice
	 */
	private java.lang.String mark;
	/**
	 * 发布时间
	 */
	private java.util.Date time;
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
	 * icon
	 */
	private java.lang.String icon;
	/**
	 * images
	 */
	private java.lang.String images;
	//columns END 数据库字段结束
	
	//concstructor

	public BetNotice(){
	}

	public BetNotice(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetNotice_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:BetNotice_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:BetNotice_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetNotice_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setMark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mark = value;
	}
	
     @WhereSQL(sql="mark=:BetNotice_mark")
	public java.lang.String getMark() {
		return this.mark;
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
	
     @WhereSQL(sql="time=:BetNotice_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetNotice_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetNotice_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetNotice_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setIcon(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.icon = value;
	}
	
     @WhereSQL(sql="icon=:BetNotice_icon")
	public java.lang.String getIcon() {
		return this.icon;
	}
	public void setImages(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.images = value;
	}
	
     @WhereSQL(sql="images=:BetNotice_images")
	public java.lang.String getImages() {
		return this.images;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("公告表ID[").append(getId()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("状态：1发布中0下架[").append(getState()).append("],")
			.append("标记：news,help,page,notice,importNotice[").append(getMark()).append("],")
			.append("发布时间[").append(getTime()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商id 从根节点开始[").append(getAgentparentids()).append("],")
			.append("icon[").append(getIcon()).append("],")
			.append("images[").append(getImages()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetNotice == false) return false;
		if(this == obj) return true;
		BetNotice other = (BetNotice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
