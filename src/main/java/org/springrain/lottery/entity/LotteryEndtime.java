package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-09-12 14:50:03
 * @see org.springrain.lottery.entity.LotteryEndtime
 */
@Table(name="lottery_endtime")
public class LotteryEndtime  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "彩票提前截止时间";
	public static final String ALIAS_ID = "各彩票时间表id";
	public static final String ALIAS_LOTTERYID = "彩种id";
	public static final String ALIAS_LOTTERYTYPE = "彩种";
	public static final String ALIAS_ENDSECOND = "投注提前截止秒数";
	public static final String ALIAS_STATE = "状态1:正常3:删除";
    */
	//date formats
	
	//columns START
	/**
	 * 各彩票时间表id
	 */
	private java.lang.Integer id;
	/**
	 * 彩种id
	 */
	private java.lang.String lotteryid;
	/**
	 * 彩种
	 */
	private java.lang.String lotterytype;
	/**
	 * 投注提前截止秒数
	 */
	private java.lang.Integer endsecond;
	/**
	 * 奖金限额
	 */
	private java.lang.Double rewardlimit;
	/**
	 * 状态1:正常3:删除
	 */
	//private java.lang.Integer state;
	//columns END 数据库字段结束
	/**
	 * 500彩票 1使用0不使用
	 */
	private java.lang.Integer wbcp;
	/**
	 * 中国竞彩1使用0不使用
	 */
	private java.lang.Integer zgjc;
	/**
	 * 知了彩1使用0不使用
	 */
	private java.lang.Integer zlc;
	
	
	//concstructor
	
	
	
	@Transient
	public java.lang.Integer getZlc() {
		return zlc;
	}

	public void setZlc(java.lang.Integer zlc) {
		this.zlc = zlc;
	}

	@Transient
	public java.lang.Integer getWbcp() {
		return wbcp;
	}
	
	public void setWbcp(java.lang.Integer wbcp) {
		this.wbcp = wbcp;
	}
	@Transient
	public java.lang.Integer getZgjc() {
		return zgjc;
	}

	public void setZgjc(java.lang.Integer zgjc) {
		this.zgjc = zgjc;
	}

	public LotteryEndtime(){
	}

	public LotteryEndtime(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 各彩票时间表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 各彩票时间表id
	 */
	@Id
     @WhereSQL(sql="id=:LotteryEndtime_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 彩种id
		 */
	public void setLotteryid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lotteryid = value;
	}
	
	
	
	/**
	 * 彩种id
	 */
     @WhereSQL(sql="lotteryid=:LotteryEndtime_lotteryid")
	public java.lang.String getLotteryid() {
		return this.lotteryid;
	}
		/**
		 * 彩种
		 */
	public void setLotterytype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lotterytype = value;
	}
	
	
	
	/**
	 * 彩种
	 */
     @WhereSQL(sql="lotterytype=:LotteryEndtime_lotterytype")
	public java.lang.String getLotterytype() {
		return this.lotterytype;
	}
		/**
		 * 投注提前截止秒数
		 */
	public void setEndsecond(java.lang.Integer value) {
		this.endsecond = value;
	}
	
	
	
	/**
	 * 投注提前截止秒数
	 */
     @WhereSQL(sql="endsecond=:LotteryEndtime_endsecond")
	public java.lang.Integer getEndsecond() {
		return this.endsecond;
	}
     
     @WhereSQL(sql="rewardlimit=:LotteryEndtime_rewardlimit")
     public java.lang.Double getRewardlimit() {
 		return rewardlimit;
 	}

 	public void setRewardlimit(java.lang.Double rewardlimit) {
 		this.rewardlimit = rewardlimit;
 	}
     
     
		/**
		 * 状态1:正常3:删除
		 */
//	public void setState(java.lang.Integer value) {
//		this.state = value;
//	}
	

	/**
	 * 状态1:正常3:删除
	 */
//     @WhereSQL(sql="state=:LotteryEndtime_state")
//	public java.lang.Integer getState() {
//		return this.state;
//	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("各彩票时间表id[").append(getId()).append("],")
			.append("彩种id[").append(getLotteryid()).append("],")
			.append("彩种[").append(getLotterytype()).append("],")
			.append("投注提前截止秒数[").append(getEndsecond()).append("],")
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
		if(obj instanceof LotteryEndtime == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		LotteryEndtime other = (LotteryEndtime)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
