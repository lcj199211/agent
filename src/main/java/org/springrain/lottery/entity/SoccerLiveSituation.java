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
 * @version  2017-10-19 09:38:37
 * @see org.springrain.lottery.entity.SoccerLiveSituation
 */
@Table(name="soccer_live_situation")
public class SoccerLiveSituation  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球实况详情";
	public static final String ALIAS_ID = "直播事件表id";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_TIME = "第几分钟";
	public static final String ALIAS_LEFTINCIDENT = "主队事件";
	public static final String ALIAS_RIGHTINCIDENT = "客队事件";
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
	private java.lang.Integer time;
	/**
	 * 主队事件
	 */
	private java.lang.String incident;
	/**
	 * 客队事件
	 */
	private java.lang.String team;
	private java.lang.String name1;
	private java.lang.String name2;
	private java.lang.Integer type;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLiveSituation(){
	}

	public SoccerLiveSituation(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 直播事件表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 直播事件表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLiveSituation_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * fid
		 */
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
	
	
	/**
	 * fid
	 */
     @WhereSQL(sql="fid=:SoccerLiveSituation_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
		/**
		 * 第几分钟
		 */
	public void setTime(java.lang.Integer value) {
		this.time = value;
	}
	
	
	
	/**
	 * 第几分钟
	 */
     @WhereSQL(sql="time=:SoccerLiveSituation_time")
	public java.lang.Integer getTime() {
		return this.time;
	}
	/**
	 * 事件
	 */
	public void setIncident(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.incident = value;
	}
	
	/**
	 * 事件
	 */
     @WhereSQL(sql="incident=:SoccerLiveSituation_incident")
	public java.lang.String getIncident() {
		return this.incident;
	}
	/**
	 * 球队
	 */
	public void setTeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.team = value;
	}
	
	/**
	 * 球队
	 */
     @WhereSQL(sql="team=:SoccerLiveSituation_team")
	public java.lang.String getTeam() {
		return this.team;
	}
	public java.lang.String getName1() {
		return name1;
	}

	public void setName1(java.lang.String name1) {
		this.name1 = name1;
	}

	public java.lang.String getName2() {
		return name2;
	}

	public void setName2(java.lang.String name2) {
		this.name2 = name2;
	}

	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("直播事件表id[").append(getId()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("第几分钟[").append(getTime()).append("],")
			.append("事件[").append(getIncident()).append("],")
			.append("队伍[").append(getTeam()).append("],")
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getFid()).append(getTime()).append(getTeam())
			.append(getType()).append(getName1())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SoccerLiveSituation == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLiveSituation other = (SoccerLiveSituation)obj;
		boolean eq = this.fid.equals(other.getFid())&&this.team.equals(other.getTeam())
				&&this.time==other.getTime()&&this.type==other.getType()
				&&this.name1.equals(other.getName1());
		return eq;
	}
}

	
