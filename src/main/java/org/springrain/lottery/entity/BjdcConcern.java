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
 * @version  2017-12-12 09:35:21
 * @see org.springrain.lottery.entity.BjdcConcern
 */
@Table(name="bjdc_concern")
public class BjdcConcern  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "北京单场关注";
	public static final String ALIAS_ID = "比赛关注表id";
	public static final String ALIAS_MEMBERID2 = "id2";
	public static final String ALIAS_FID = "fid";
    */
	//date formats
	
	//columns START
	/**
	 * 比赛关注表id
	 */
	private java.lang.Integer id;
	/**
	 * id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * fid
	 */
	private java.lang.String fid;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcConcern(){
	}

	public BjdcConcern(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcConcern_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BjdcConcern_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcConcern_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("比赛关注表id[").append(getId()).append("],")
			.append("id2[").append(getMemberid2()).append("],")
			.append("fid[").append(getFid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcConcern == false) return false;
		if(this == obj) return true;
		BjdcConcern other = (BjdcConcern)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
