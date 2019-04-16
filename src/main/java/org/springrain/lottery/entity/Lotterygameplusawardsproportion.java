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
 * @see org.springrain.lottery.entity.Lotterygameplusawardsproportion
 */
@Table(name="lottery_endtime")
public class Lotterygameplusawardsproportion  extends BaseEntity {
	
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
	private java.lang.Double plusawardsproportion;
	private java.lang.String agentid;
	private Integer systemissue;
	private Integer systemsettle;
	private Integer autobigticket;
	private Double bigticketodds;
	private Double ticketbetmultiple;
	//concstructor
	
	@WhereSQL(sql="systemissue=:LotteryEndtime_systemissue")
	public Integer getSystemissue() {
		return systemissue;
	}

	public void setSystemissue(Integer systemissue) {
		this.systemissue = systemissue;
	}
	@WhereSQL(sql="systemsettle=:LotteryEndtime_systemsettle")
	public Integer getSystemsettle() {
		return systemsettle;
	}

	public void setSystemsettle(Integer systemsettle) {
		this.systemsettle = systemsettle;
	}
	@WhereSQL(sql="autobigticket=:LotteryEndtime_autobigticket")
	public Integer getAutobigticket() {
		return autobigticket;
	}

	public void setAutobigticket(Integer autobigticket) {
		this.autobigticket = autobigticket;
	}
	@WhereSQL(sql="bigticketodds=:LotteryEndtime_bigticketodds")
	public Double getBigticketodds() {
		return bigticketodds;
	}

	public void setBigticketodds(Double bigticketodds) {
		this.bigticketodds = bigticketodds;
	}
	
	@WhereSQL(sql="plusawardsproportion=:LotteryEndtime_plusawardsproportion")
	public java.lang.Double getPlusawardsproportion() {
		return plusawardsproportion;
	}

	public void setPlusawardsproportion(java.lang.Double plusawardsproportion) {
		this.plusawardsproportion = plusawardsproportion;
	}
	@WhereSQL(sql="agentid=:LotteryEndtime_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}

	public Lotterygameplusawardsproportion(){
	}

	public Lotterygameplusawardsproportion(
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
     @WhereSQL(sql="ticketbetmultiple=:LotteryEndtime_ticketbetmultiple")
     public Double getTicketbetmultiple() {
 		return ticketbetmultiple;
 	}

 	public void setTicketbetmultiple(Double ticketbetmultiple) {
 		this.ticketbetmultiple = ticketbetmultiple;
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
		if(obj instanceof Lotterygameplusawardsproportion == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		Lotterygameplusawardsproportion other = (Lotterygameplusawardsproportion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
	
}

	
