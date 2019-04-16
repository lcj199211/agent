package org.springrain.lottery.entity;

import java.math.BigDecimal;

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
 * @version  2017-04-18 14:23:24
 * @see org.springrain.lottery.entity.BetScorerecord
 */
@Table(name="bet_scorerecord")
public class BetScorerecord  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "金币记录";
	public static final String ALIAS_ID = "用户金币记录ID";
	public static final String ALIAS_MEMBERID2 = "用户ID2";
	public static final String ALIAS_TIME = "时间";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_ORIGINALSCORE = "原金额";
	public static final String ALIAS_BALANCE = "余额";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_CHANGESCORE = "发生金额";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 用户金币记录ID
	 */
	private java.lang.Integer id;
	/**
	 * 用户ID2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 时间
	 */
	private java.util.Date time;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 原金额
	 */
	private java.lang.Double originalscore;
	/**
	 * 余额
	 */
	private java.lang.Double balance;
	/**
	 * 状态
	 */
	private java.lang.Integer state;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 类型
	 */
	private java.lang.Integer type;
	/**
	 * 发生金额
	 */
	private java.lang.Double changescore;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	private BigDecimal ogamescore;//原始游戏分
	private BigDecimal obankscore;//原始银行分
	private BigDecimal ofreezescore;//原始冻结分
	private BigDecimal gamescore;//游戏分
	private BigDecimal bankscore;//银行分
	private BigDecimal freezescore;//冻结分
	private String orderid;
	
	//columns END 数据库字段结束
	
	//concstructor

	public BetScorerecord(){
	}

	public BetScorerecord(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetScorerecord_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetScorerecord_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
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
	
     @WhereSQL(sql="time=:BetScorerecord_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:BetScorerecord_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setOriginalscore(java.lang.Double value) {
		this.originalscore = value;
	}
	
     @WhereSQL(sql="originalscore=:BetScorerecord_originalscore")
	public java.lang.Double getOriginalscore() {
		return this.originalscore;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:BetScorerecord_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetScorerecord_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetScorerecord_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetScorerecord_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setChangescore(java.lang.Double value) {
		this.changescore = value;
	}
	
     @WhereSQL(sql="changescore=:BetScorerecord_changescore")
	public java.lang.Double getChangescore() {
		return this.changescore;
	}
     
	public BigDecimal getOgamescore() {
		return ogamescore;
	}

	public void setOgamescore(BigDecimal ogamescore) {
		this.ogamescore = ogamescore;
	}

	public BigDecimal getObankscore() {
		return obankscore;
	}

	public void setObankscore(BigDecimal obankscore) {
		this.obankscore = obankscore;
	}

	public BigDecimal getOfreezescore() {
		return ofreezescore;
	}

	public void setOfreezescore(BigDecimal ofreezescore) {
		this.ofreezescore = ofreezescore;
	}

	public BigDecimal getGamescore() {
		return gamescore;
	}

	public void setGamescore(BigDecimal gamescore) {
		this.gamescore = gamescore;
	}

	public BigDecimal getBankscore() {
		return bankscore;
	}

	public void setBankscore(BigDecimal bankscore) {
		this.bankscore = bankscore;
	}

	public BigDecimal getFreezescore() {
		return freezescore;
	}

	public void setFreezescore(BigDecimal freezescore) {
		this.freezescore = freezescore;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("用户金币记录ID[").append(getId()).append("],")
			.append("用户ID2[").append(getMemberid2()).append("],")
			.append("时间[").append(getTime()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("原金额[").append(getOriginalscore()).append("],")
			.append("余额[").append(getBalance()).append("],")
			.append("状态[").append(getState()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("类型[").append(getType()).append("],")
			.append("发生金额[").append(getChangescore()).append("],")
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
		if(obj instanceof BetScorerecord == false) return false;
		if(this == obj) return true;
		BetScorerecord other = (BetScorerecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@WhereSQL(sql="agentid=:BetScorerecord_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetScorerecord_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetScorerecord_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}
}

	
