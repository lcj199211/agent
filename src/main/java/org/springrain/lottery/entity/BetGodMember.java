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
 * @version  2018-11-28 14:46:43
 * @see org.springrain.lottery.entity.BetGodMember
 */
@Table(name="bet_god_member")
public class BetGodMember  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "后台添加爆红大神表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MEMBERID2 = "对应用户表id2字段";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_AGENTID = "代理商ID";
	public static final String ALIAS_AGENTPARENTID = "agentparentid";
	public static final String ALIAS_AGENTPARENTIDS = "agentparentids";
	public static final String ALIAS_AVATAR = "头像";
	public static final String ALIAS_NICKNAME = "昵称";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 对应用户表id2字段
	 */
	private java.lang.Integer memberid2;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 代理商ID
	 */
	private java.lang.String agentid;
	/**
	 * agentparentid
	 */
	private java.lang.String agentparentid;
	/**
	 * agentparentids
	 */
	private java.lang.String agentparentids;
	/**
	 * 头像
	 */
	private java.lang.String avatar;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGodMember(){
	}

	public BetGodMember(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGodMember_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetGodMember_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
     @WhereSQL(sql="createTime=:BetGodMember_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetGodMember_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetGodMember_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetGodMember_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setAvatar(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.avatar = value;
	}
	
     @WhereSQL(sql="avatar=:BetGodMember_avatar")
	public java.lang.String getAvatar() {
		return this.avatar;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetGodMember_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("对应用户表id2字段[").append(getMemberid2()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("代理商ID[").append(getAgentid()).append("],")
			.append("agentparentid[").append(getAgentparentid()).append("],")
			.append("agentparentids[").append(getAgentparentids()).append("],")
			.append("头像[").append(getAvatar()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGodMember == false) return false;
		if(this == obj) return true;
		BetGodMember other = (BetGodMember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
