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
 * @version  2017-05-11 10:31:17
 * @see org.springrain.lottery.entity.BetReliefRecord
 */
@Table(name="bet_relief_record")
public class BetReliefRecord  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetReliefRecord";
	public static final String ALIAS_ID = "救济领取表id";
	public static final String ALIAS_MEMBERID = "用户ID";
	public static final String ALIAS_MEMBERID2 = "用户ID2";
	public static final String ALIAS_RELIEFSCORE = "领取的救济金";
	public static final String ALIAS_DATE = "领取时间";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 救济领取表id
	 */
	private java.lang.Integer id;
	/**
	 * 用户ID
	 */
	private java.lang.String memberid;
	/**
	 * 用户ID2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 领取的救济金
	 */
	private java.lang.Integer reliefscore;
	/**
	 * 领取时间
	 */
	private java.util.Date date;
	//columns END 数据库字段结束
	
	//concstructor

	public BetReliefRecord(){
	}

	public BetReliefRecord(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetReliefRecord_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetReliefRecord_memberid")
	public java.lang.String getMemberid() {
		return this.memberid;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetReliefRecord_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setReliefscore(java.lang.Integer value) {
		this.reliefscore = value;
	}
	
     @WhereSQL(sql="reliefscore=:BetReliefRecord_reliefscore")
	public java.lang.Integer getReliefscore() {
		return this.reliefscore;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:BetReliefRecord_date")
	public java.util.Date getDate() {
		return this.date;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("救济领取表id[").append(getId()).append("],")
			.append("用户ID[").append(getMemberid()).append("],")
			.append("用户ID2[").append(getMemberid2()).append("],")
			.append("领取的救济金[").append(getReliefscore()).append("],")
			.append("领取时间[").append(getDate()).append("],")
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
		if(obj instanceof BetReliefRecord == false) return false;
		if(this == obj) return true;
		BetReliefRecord other = (BetReliefRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
