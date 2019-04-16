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
 * @version  2017-09-14 16:25:05
 * @see org.springrain.lottery.entity.SoccerBettingScheme
 */
@Table(name="soccer_betting_scheme")
public class SoccerBettingScheme  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "投注方案表";
	public static final String ALIAS_ID = "表id";
	public static final String ALIAS_SCHEMEID = "投注方案id";
	public static final String ALIAS_INITIATORID2 = "发起人ID2";
	public static final String ALIAS_MEMBERID2 = "会员id2";
	public static final String ALIAS_TOTALLYBETTINGMONEY = "投注金额";
	public static final String ALIAS_TOTALLYBETTINGSCORE = "投注赢取";
	public static final String ALIAS_STATE = "结算状态0:未开奖 1:已开奖 2:撤销";
	public static final String ALIAS_BETTINGTIME = "投注时间";
	public static final String ALIAS_SETTLEMENTTIME = "结算时间";
	public static final String ALIAS_TYPE = "是否合买（0：不是，1是）";
	public static final String ALIAS_LAUNCHTIME = "发起时间";
    */
	//date formats
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SETTLEMENTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_LAUNCHTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 表id
	 */
	private java.lang.Integer id;
	/**
	 * 投注方案id
	 */
	private java.lang.String schemeid;
	/**
	 * 发起人ID2
	 */
	private java.lang.Integer initiatorid2;
	/**
	 * 会员id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 投注金额
	 */
	private java.lang.Double totallybettingmoney;
	/**
	 * 投注赢取
	 */
	private java.lang.Double totallybettingscore;
	/**
	 * 结算状态0:未开奖 1:已开奖 2:撤销
	 */
	private java.lang.Integer state;
	/**
	 * 投注时间
	 */
	private java.util.Date bettingtime;
	/**
	 * 结算时间
	 */
	private java.util.Date settlementtime;
	/**
	 * 是否合买（0：不是，1是）
	 */
	private java.lang.Integer type;
	/**
	 * 发起时间
	 */
	private java.util.Date launchtime;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerBettingScheme(){
	}

	public SoccerBettingScheme(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerBettingScheme_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 投注方案id
		 */
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
	
	
	/**
	 * 投注方案id
	 */
     @WhereSQL(sql="schemeid=:SoccerBettingScheme_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
		/**
		 * 发起人ID2
		 */
	public void setInitiatorid2(java.lang.Integer value) {
		this.initiatorid2 = value;
	}
	
	
	
	/**
	 * 发起人ID2
	 */
     @WhereSQL(sql="initiatorid2=:SoccerBettingScheme_initiatorid2")
	public java.lang.Integer getInitiatorid2() {
		return this.initiatorid2;
	}
		/**
		 * 会员id2
		 */
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
	
	
	/**
	 * 会员id2
	 */
     @WhereSQL(sql="memberid2=:SoccerBettingScheme_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
		/**
		 * 投注金额
		 */
	public void setTotallybettingmoney(java.lang.Double value) {
		this.totallybettingmoney = value;
	}
	
	
	
	/**
	 * 投注金额
	 */
     @WhereSQL(sql="totallybettingmoney=:SoccerBettingScheme_totallybettingmoney")
	public java.lang.Double getTotallybettingmoney() {
		return this.totallybettingmoney;
	}
		/**
		 * 投注赢取
		 */
	public void setTotallybettingscore(java.lang.Double value) {
		this.totallybettingscore = value;
	}
	
	
	
	/**
	 * 投注赢取
	 */
     @WhereSQL(sql="totallybettingscore=:SoccerBettingScheme_totallybettingscore")
	public java.lang.Double getTotallybettingscore() {
		return this.totallybettingscore;
	}
		/**
		 * 结算状态0:未开奖 1:已开奖 2:撤销
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 结算状态0:未开奖 1:已开奖 2:撤销
	 */
     @WhereSQL(sql="state=:SoccerBettingScheme_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/*
	public String getbettingtimeString() {
		return DateUtils.convertDate2String(FORMAT_BETTINGTIME, getbettingtime());
	}
	public void setbettingtimeString(String value) throws ParseException{
		setbettingtime(DateUtils.convertString2Date(FORMAT_BETTINGTIME,value));
	}*/
	
		/**
		 * 投注时间
		 */
	public void setBettingtime(java.util.Date value) {
		this.bettingtime = value;
	}
	
	
	
	/**
	 * 投注时间
	 */
     @WhereSQL(sql="bettingtime=:SoccerBettingScheme_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
		/*
	public String getsettlementtimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLEMENTTIME, getsettlementtime());
	}
	public void setsettlementtimeString(String value) throws ParseException{
		setsettlementtime(DateUtils.convertString2Date(FORMAT_SETTLEMENTTIME,value));
	}*/
	
		/**
		 * 结算时间
		 */
	public void setSettlementtime(java.util.Date value) {
		this.settlementtime = value;
	}
	
	
	
	/**
	 * 结算时间
	 */
     @WhereSQL(sql="settlementtime=:SoccerBettingScheme_settlementtime")
	public java.util.Date getSettlementtime() {
		return this.settlementtime;
	}
		/**
		 * 是否合买（0：不是，1是）
		 */
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 是否合买（0：不是，1是）
	 */
     @WhereSQL(sql="type=:SoccerBettingScheme_type")
	public java.lang.Integer getType() {
		return this.type;
	}
		/*
	public String getlaunchtimeString() {
		return DateUtils.convertDate2String(FORMAT_LAUNCHTIME, getlaunchtime());
	}
	public void setlaunchtimeString(String value) throws ParseException{
		setlaunchtime(DateUtils.convertString2Date(FORMAT_LAUNCHTIME,value));
	}*/
	
		/**
		 * 发起时间
		 */
	public void setLaunchtime(java.util.Date value) {
		this.launchtime = value;
	}
	
	
	
	/**
	 * 发起时间
	 */
     @WhereSQL(sql="launchtime=:SoccerBettingScheme_launchtime")
	public java.util.Date getLaunchtime() {
		return this.launchtime;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("表id[").append(getId()).append("],")
			.append("投注方案id[").append(getSchemeid()).append("],")
			.append("发起人ID2[").append(getInitiatorid2()).append("],")
			.append("会员id2[").append(getMemberid2()).append("],")
			.append("投注金额[").append(getTotallybettingmoney()).append("],")
			.append("投注赢取[").append(getTotallybettingscore()).append("],")
			.append("结算状态0:未开奖 1:已开奖 2:撤销[").append(getState()).append("],")
			.append("投注时间[").append(getBettingtime()).append("],")
			.append("结算时间[").append(getSettlementtime()).append("],")
			.append("是否合买（0：不是，1是）[").append(getType()).append("],")
			.append("发起时间[").append(getLaunchtime()).append("],")
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
		if(obj instanceof SoccerBettingScheme == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerBettingScheme other = (SoccerBettingScheme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
