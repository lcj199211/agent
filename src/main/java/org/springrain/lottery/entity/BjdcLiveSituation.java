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
 * @version  2017-12-12 16:08:47
 * @see org.springrain.lottery.entity.BjdcLiveSituation
 */
@Table(name="bjdc_live_situation")
public class BjdcLiveSituation  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球实况详情";
	public static final String ALIAS_ID = "直播事件表id";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_TIME = "第几分钟";
	public static final String ALIAS_INCIDENT = "事件";
	public static final String ALIAS_TEAM = "主队客队（home/guest）";
	public static final String ALIAS_NAME1 = "name1";
	public static final String ALIAS_NAME2 = "name2";
	public static final String ALIAS_TYPE = "事件类型1.进球 2.乌龙 3.点球 4.黄牌 5.红牌 6.两黄变红 7.入球无效 8.换人";
    */
	//date formats
	
	//columns START
	/**
	 * 直播事件表id
	 */
	private java.lang.Integer id;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * 第几分钟
	 */
	private java.lang.String time;
	/**
	 * 事件
	 */
	private java.lang.String incident;
	/**
	 * 主队客队（home/guest）
	 */
	private java.lang.String team;
	/**
	 * name1
	 */
	private java.lang.String name1;
	/**
	 * name2
	 */
	private java.lang.String name2;
	/**
	 * 事件类型1.进球 2.乌龙 3.点球 4.黄牌 5.红牌 6.两黄变红 7.入球无效 8.换人
	 */
	private java.lang.Integer type;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcLiveSituation(){
	}

	public BjdcLiveSituation(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcLiveSituation_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcLiveSituation_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setTime(java.lang.String value) {
		this.time = value;
	}
	
     @WhereSQL(sql="time=:BjdcLiveSituation_time")
	public java.lang.String getTime() {
		return this.time;
	}
	public void setIncident(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.incident = value;
	}
	
     @WhereSQL(sql="incident=:BjdcLiveSituation_incident")
	public java.lang.String getIncident() {
		return this.incident;
	}
	public void setTeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.team = value;
	}
	
     @WhereSQL(sql="team=:BjdcLiveSituation_team")
	public java.lang.String getTeam() {
		return this.team;
	}
	public void setName1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name1 = value;
	}
	
     @WhereSQL(sql="name1=:BjdcLiveSituation_name1")
	public java.lang.String getName1() {
		return this.name1;
	}
	public void setName2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name2 = value;
	}
	
     @WhereSQL(sql="name2=:BjdcLiveSituation_name2")
	public java.lang.String getName2() {
		return this.name2;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BjdcLiveSituation_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("直播事件表id[").append(getId()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("第几分钟[").append(getTime()).append("],")
			.append("事件[").append(getIncident()).append("],")
			.append("主队客队（home/guest）[").append(getTeam()).append("],")
			.append("name1[").append(getName1()).append("],")
			.append("name2[").append(getName2()).append("],")
			.append("事件类型1.进球 2.乌龙 3.点球 4.黄牌 5.红牌 6.两黄变红 7.入球无效 8.换人[").append(getType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcLiveSituation == false) return false;
		if(this == obj) return true;
		BjdcLiveSituation other = (BjdcLiveSituation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
