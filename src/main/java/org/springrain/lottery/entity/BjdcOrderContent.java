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
 * @version  2017-12-12 09:37:03
 * @see org.springrain.lottery.entity.BjdcOrderContent
 */
@Table(name="bjdc_order_content")
public class BjdcOrderContent  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "北京单场订单详情";
	public static final String ALIAS_ID = "订单内容表id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_FID = "mid";
	public static final String ALIAS_ODDS = "赔率";
	public static final String ALIAS_ODDSNAME = "赔率名";
	public static final String ALIAS_SETTLETIME = "结算时间";
	public static final String ALIAS_RESULT = "结果1:中奖 3:未中奖 0:未结算 4:非正常";
	public static final String ALIAS_RESULTNAME = "开奖投注项";
	public static final String ALIAS_SCHEMEID = "方案号";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "父级代理id";
	public static final String ALIAS_AGENTPARENTIDS = "分级代理,隔开";
    */
	//date formats
	//public static final String FORMAT_SETTLETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 订单内容表id
	 */
	private java.lang.Integer id;
	/**
	 * 订单号
	 */
	private java.lang.String orderid;
	/**
	 * mid
	 */
	private java.lang.String fid;
	/**
	 * 赔率
	 */
	private java.lang.Double odds;
	/**
	 * 赔率名
	 */
	private java.lang.String oddsname;
	/**
	 * 结算时间
	 */
	private java.util.Date settletime;
	/**
	 * 结果1:中奖 3:未中奖 0:未结算 4:非正常
	 */
	private java.lang.Integer result;
	/**
	 * 开奖投注项
	 */
	private java.lang.String resultname;
	/**
	 * 方案号
	 */
	private java.lang.String schemeid;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 父级代理id
	 */
	private java.lang.String agentparentid;
	/**
	 * 分级代理,隔开
	 */
	private java.lang.String agentparentids;
	//columns END 数据库字段结束
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 主队名
	 */
	private java.lang.String hometeam;
	/**
	 * 客队名
	 */
	private java.lang.String guestteam;
	/**
	 * 字段中文名
	 */
	private java.lang.String oddsrealname;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	private java.lang.String num;
	private java.lang.String num1;
	private java.lang.String num2;
	
	/**
	 * 截止投注时间
	 */
	private java.util.Date endtime;
	/**
	 * 玩法名
	 */
	private java.lang.String betname;
	//concstructor
	private String allScore;
	private String halfScore;
	public BjdcOrderContent(){
	}

	public BjdcOrderContent(
		java.lang.Integer id
	){
		this.id = id;
	}
	@Transient
	public String getAllScore() {
		return allScore;
	}

	public void setAllScore(String allScore) {
		this.allScore = allScore;
	}
	@Transient
	public String getHalfScore() {
		return halfScore;
	}

	public void setHalfScore(String halfScore) {
		this.halfScore = halfScore;
	}
	
	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcOrderContent_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BjdcOrderContent_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcOrderContent_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setOdds(java.lang.Double value) {
		this.odds = value;
	}
	
     @WhereSQL(sql="odds=:BjdcOrderContent_odds")
	public java.lang.Double getOdds() {
		return this.odds;
	}
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
     @WhereSQL(sql="oddsname=:BjdcOrderContent_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
		/*
	public String getsettletimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLETIME, getsettletime());
	}
	public void setsettletimeString(String value) throws ParseException{
		setsettletime(DateUtils.convertString2Date(FORMAT_SETTLETIME,value));
	}*/
	
	public void setSettletime(java.util.Date value) {
		this.settletime = value;
	}
	
     @WhereSQL(sql="settletime=:BjdcOrderContent_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}
	public void setResult(java.lang.Integer value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BjdcOrderContent_result")
	public java.lang.Integer getResult() {
		return this.result;
	}
	public void setResultname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.resultname = value;
	}
	
     @WhereSQL(sql="resultname=:BjdcOrderContent_resultname")
	public java.lang.String getResultname() {
		return this.resultname;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:BjdcOrderContent_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BjdcOrderContent_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BjdcOrderContent_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BjdcOrderContent_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("订单内容表id[").append(getId()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("mid[").append(getFid()).append("],")
			.append("赔率[").append(getOdds()).append("],")
			.append("赔率名[").append(getOddsname()).append("],")
			.append("结算时间[").append(getSettletime()).append("],")
			.append("结果1:中奖 3:未中奖 0:未结算 4:非正常[").append(getResult()).append("],")
			.append("开奖投注项[").append(getResultname()).append("],")
			.append("方案号[").append(getSchemeid()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("父级代理id[").append(getAgentparentid()).append("],")
			.append("分级代理,隔开[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcOrderContent == false) return false;
		if(this == obj) return true;
		BjdcOrderContent other = (BjdcOrderContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public java.lang.String getMatchname() {
		return matchname;
	}

	public void setMatchname(java.lang.String matchname) {
		this.matchname = matchname;
	}
	@Transient
	public java.lang.String getHometeam() {
		return hometeam;
	}

	public void setHometeam(java.lang.String hometeam) {
		this.hometeam = hometeam;
	}
	@Transient
	public java.lang.String getGuestteam() {
		return guestteam;
	}

	public void setGuestteam(java.lang.String guestteam) {
		this.guestteam = guestteam;
	}
	@Transient
	public java.lang.String getOddsrealname() {
		return oddsrealname;
	}

	public void setOddsrealname(java.lang.String oddsrealname) {
		this.oddsrealname = oddsrealname;
	}
	@Transient
	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	@Transient
	public java.lang.String getNum() {
		return num;
	}

	public void setNum(java.lang.String num) {
		this.num = num;
	}
	@Transient
	public java.lang.String getNum1() {
		return num1;
	}

	public void setNum1(java.lang.String num1) {
		this.num1 = num1;
	}
	@Transient
	public java.lang.String getNum2() {
		return num2;
	}

	public void setNum2(java.lang.String num2) {
		this.num2 = num2;
	}
	@Transient
	public java.util.Date getEndtime() {
		return endtime;
	}

	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
@Transient
	public java.lang.String getBetname() {
		return betname;
	}

	public void setBetname(java.lang.String betname) {
		this.betname = betname;
	}

	
}

	
