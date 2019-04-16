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
 * @version  2017-05-02 01:50:02
 * @see org.springrain.lottery.entity.BetRedenvelopeRecord
 */
@Table(name="bet_redenvelope_record")
public class BetRedenvelopeRecord  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetRedenvelopeRecord";
	public static final String ALIAS_ID = "红包领取记录表ID";
	public static final String ALIAS_MEMBERID = "会员id";
	public static final String ALIAS_REDENVELOPECODE = "红包码";
	public static final String ALIAS_RECEIVESCORE = "领取分";
	public static final String ALIAS_RECEIVETIME = "领取时间";
	public static final String ALIAS_SOURCE = "来源";
	public static final String ALIAS_STATE = "状态0:已领取 1:未领取";
	public static final String ALIAS_REDENVELOPEID = "红包id";
    */
	//date formats
	//public static final String FORMAT_RECEIVETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 红包领取记录表ID
	 */
	private java.lang.Integer id;
	/**
	 * 会员id
	 */
	private java.lang.String memberid2;
	/**
	 * 红包码
	 */
	private java.lang.String redenvelopecode;
	/**
	 * 领取分
	 */
	private java.lang.Integer receivescore;
	/**
	 * 领取时间
	 */
	private java.util.Date receivetime;
	/**
	 * 来源
	 */
	private java.lang.String source;
	/**
	 * 状态0:已领取 1:未领取
	 */
	private java.lang.Integer state;
	/**
	 * 红包id
	 */
	private java.lang.Integer redenvelopeid;
	//columns END 数据库字段结束
	
	//concstructor

	public BetRedenvelopeRecord(){
	}

	public BetRedenvelopeRecord(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetRedenvelopeRecord_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetRedenvelopeRecord_memberid2")
	public java.lang.String getMemberid2() {
		return this.memberid2;
	}
	public void setRedenvelopecode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.redenvelopecode = value;
	}
	
     @WhereSQL(sql="redenvelopecode=:BetRedenvelopeRecord_redenvelopecode")
	public java.lang.String getRedenvelopecode() {
		return this.redenvelopecode;
	}
	public void setReceivescore(java.lang.Integer value) {
		this.receivescore = value;
	}
	
     @WhereSQL(sql="receivescore=:BetRedenvelopeRecord_receivescore")
	public java.lang.Integer getReceivescore() {
		return this.receivescore;
	}
		/*
	public String getreceivetimeString() {
		return DateUtils.convertDate2String(FORMAT_RECEIVETIME, getreceivetime());
	}
	public void setreceivetimeString(String value) throws ParseException{
		setreceivetime(DateUtils.convertString2Date(FORMAT_RECEIVETIME,value));
	}*/
	
	public void setReceivetime(java.util.Date value) {
		this.receivetime = value;
	}
	
     @WhereSQL(sql="receivetime=:BetRedenvelopeRecord_receivetime")
	public java.util.Date getReceivetime() {
		return this.receivetime;
	}
	public void setSource(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.source = value;
	}
	
     @WhereSQL(sql="source=:BetRedenvelopeRecord_source")
	public java.lang.String getSource() {
		return this.source;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetRedenvelopeRecord_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setRedenvelopeid(java.lang.Integer value) {
		this.redenvelopeid = value;
	}
	
     @WhereSQL(sql="redenvelopeid=:BetRedenvelopeRecord_redenvelopeid")
	public java.lang.Integer getRedenvelopeid() {
		return this.redenvelopeid;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("红包领取记录表ID[").append(getId()).append("],")
			.append("会员id[").append(getMemberid2()).append("],")
			.append("红包码[").append(getRedenvelopecode()).append("],")
			.append("领取分[").append(getReceivescore()).append("],")
			.append("领取时间[").append(getReceivetime()).append("],")
			.append("来源[").append(getSource()).append("],")
			.append("状态0:已领取 1:未领取[").append(getState()).append("],")
			.append("红包id[").append(getRedenvelopeid()).append("],")
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
		if(obj instanceof BetRedenvelopeRecord == false) return false;
		if(this == obj) return true;
		BetRedenvelopeRecord other = (BetRedenvelopeRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
