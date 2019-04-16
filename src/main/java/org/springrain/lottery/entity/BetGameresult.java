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
 * @version  2017-10-18 10:58:47
 * @see org.springrain.lottery.entity.BetGameresult
 */
@Table(name="bet_gameresult")
public class BetGameresult  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "所有彩票实时开奖";
	public static final String ALIAS_ID = "开奖结果表id";
	public static final String ALIAS_GAMENAME = "游戏名字";
	public static final String ALIAS_QIBIE = "期号";
	public static final String ALIAS_TIME = "时间";
	public static final String ALIAS_RESULT = "开奖结果s";
	public static final String ALIAS_REMARK = "其他";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 开奖结果表id
	 */
	private java.lang.Integer id;
	/**
	 * 游戏名字
	 */
	private java.lang.String gamename;
	/**
	 * 期号
	 */
	private java.lang.String qibie;
	/**
	 * 时间
	 */
	private java.util.Date time;
	/**
	 * 开奖结果s
	 */
	private java.lang.String result;
	/**
	 * 其他
	 */
	private java.lang.String remark;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGameresult(){
	}

	public BetGameresult(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 开奖结果表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 开奖结果表id
	 */
	@Id
     @WhereSQL(sql="id=:BetGameresult_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 游戏名字
		 */
	public void setGamename(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gamename = value;
	}
	
	
	
	/**
	 * 游戏名字
	 */
     @WhereSQL(sql="gamename=:BetGameresult_gamename")
	public java.lang.String getGamename() {
		return this.gamename;
	}
		/**
		 * 期号
		 */
	public void setQibie(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qibie = value;
	}
	
	
	
	/**
	 * 期号
	 */
     @WhereSQL(sql="qibie=:BetGameresult_qibie")
	public java.lang.String getQibie() {
		return this.qibie;
	}
		/*
	public String gettimeString() {
		return DateUtils.convertDate2String(FORMAT_TIME, gettime());
	}
	public void settimeString(String value) throws ParseException{
		settime(DateUtils.convertString2Date(FORMAT_TIME,value));
	}*/
	
		/**
		 * 时间
		 */
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	
	
	
	/**
	 * 时间
	 */
     @WhereSQL(sql="time=:BetGameresult_time")
	public java.util.Date getTime() {
		return this.time;
	}
		/**
		 * 开奖结果s
		 */
	public void setResult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.result = value;
	}
	
	
	
	/**
	 * 开奖结果s
	 */
     @WhereSQL(sql="result=:BetGameresult_result")
	public java.lang.String getResult() {
		return this.result;
	}
		/**
		 * 其他
		 */
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
	
	
	/**
	 * 其他
	 */
     @WhereSQL(sql="remark=:BetGameresult_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("开奖结果表id[").append(getId()).append("],")
			.append("游戏名字[").append(getGamename()).append("],")
			.append("期号[").append(getQibie()).append("],")
			.append("时间[").append(getTime()).append("],")
			.append("开奖结果s[").append(getResult()).append("],")
			.append("其他[").append(getRemark()).append("],")
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
		if(obj instanceof BetGameresult == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		BetGameresult other = (BetGameresult)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
