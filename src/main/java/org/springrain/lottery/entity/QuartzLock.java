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
 * @version  2017-06-05 12:49:18
 * @see org.springrain.lottery.entity.QuartzLock
 */
@Table(name="quartz_lock")
public class QuartzLock  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "QuartzLock";
	public static final String ALIAS_JOBNAME = "jobname";
	public static final String ALIAS_STATE = "0：空闲；1使用中";
    */
	//date formats
	
	//columns START
	/**
	 * jobname
	 */
	private java.lang.String jobname;
	/**
	 * 0：空闲；1使用中
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public QuartzLock(){
	}

	public QuartzLock(
		java.lang.String jobname
	){
		this.jobname = jobname;
	}

	//get and set
	public void setJobname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jobname = value;
	}
	
	@Id
     @WhereSQL(sql="jobname=:QuartzLock_jobname")
	public java.lang.String getJobname() {
		return this.jobname;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:QuartzLock_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("jobname[").append(getJobname()).append("],")
			.append("0：空闲；1使用中[").append(getState()).append("],")
			.toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getJobname())
			.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof QuartzLock == false) return false;
		if(this == obj) return true;
		QuartzLock other = (QuartzLock)obj;
		return new EqualsBuilder()
			.append(getJobname(),other.getJobname())
			.isEquals();
	}
}

	
