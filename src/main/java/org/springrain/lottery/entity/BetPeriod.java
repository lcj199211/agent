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
 * @version  2017-03-09 16:17:46
 * @see org.springrain.lottery.entity.BetPeriod
 */
@Table(name="bet_period")
public class BetPeriod  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "开奖";
	public static final String ALIAS_ID = "开奖期数ID";
	public static final String ALIAS_GAMECLASSID = "游戏类别";
	public static final String ALIAS_QIBIE = "期数";
	public static final String ALIAS_HS = "开奖结果";
	public static final String ALIAS_TIME = "开奖时间";
	public static final String ALIAS_BETTINGAMOUNT = "投注金额";
	public static final String ALIAS_NUMOFPEOPLE = "人数";
	public static final String ALIAS_RESULT = "结果";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 开奖期数ID
	 */
	private java.lang.Integer id;
	/**
	 * 游戏类别
	 */
	private java.lang.Integer gameclassid;
	/**
	 * 期数
	 */
	private java.lang.String qibie;
	/**
	 * 开奖结果
	 */
	private java.lang.String hs;
	/**
	 * 开奖时间
	 */
	private java.util.Date time;
	/**
	 * 投注金额
	 */
	private java.lang.Double bettingamount;
	/**
	 * 人数
	 */
	private java.lang.Integer numofpeople;
	/**
	 * 结果
	 */
	private java.lang.Double result;
	//columns END 数据库字段结束
	private java.lang.Integer state;
	private java.lang.String record;
	@WhereSQL(sql="record=:BetPeriod_record")
	public java.lang.String getRecord() {
		return record;
	}

	public void setRecord(java.lang.String record) {
		this.record = record;
	}

	//concstructor
	@WhereSQL(sql="state=:BetPeriod_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public BetPeriod(){
	}

	public BetPeriod(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetPeriod_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGameclassid(java.lang.Integer value) {
		this.gameclassid = value;
	}
	
     @WhereSQL(sql="gameclassid=:BetPeriod_gameclassid")
	public java.lang.Integer getGameclassid() {
		return this.gameclassid;
	}
	public void setQibie(java.lang.String value) {
		this.qibie = value;
	}
	
     @WhereSQL(sql="qibie=:BetPeriod_qibie")
	public java.lang.String getQibie() {
		return this.qibie;
	}
	public void setHs(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hs = value;
	}
	
     @WhereSQL(sql="hs=:BetPeriod_hs")
	public java.lang.String getHs() {
		return this.hs;
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
	
     @WhereSQL(sql="time=:BetPeriod_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setBettingamount(java.lang.Double value) {
		this.bettingamount = value;
	}
	
     @WhereSQL(sql="bettingamount=:BetPeriod_bettingamount")
	public java.lang.Double getBettingamount() {
		return this.bettingamount;
	}
	public void setNumofpeople(java.lang.Integer value) {
		this.numofpeople = value;
	}
	
     @WhereSQL(sql="numofpeople=:BetPeriod_numofpeople")
	public java.lang.Integer getNumofpeople() {
		return this.numofpeople;
	}
	public void setResult(java.lang.Double value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BetPeriod_result")
	public java.lang.Double getResult() {
		return this.result;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("开奖期数ID[").append(getId()).append("],")
			.append("游戏类别[").append(getGameclassid()).append("],")
			.append("期数[").append(getQibie()).append("],")
			.append("开奖结果[").append(getHs()).append("],")
			.append("开奖时间[").append(getTime()).append("],")
			.append("投注金额[").append(getBettingamount()).append("],")
			.append("人数[").append(getNumofpeople()).append("],")
			.append("结果[").append(getResult()).append("],")
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
		if(obj instanceof BetPeriod == false) return false;
		if(this == obj) return true;
		BetPeriod other = (BetPeriod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
