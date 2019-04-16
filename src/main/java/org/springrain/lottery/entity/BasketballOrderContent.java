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
 * @version  2017-11-14 16:09:18
 * @see org.springrain.lottery.entity.BasketballOrderContent
 */
@Table(name="basketball_order_content")
public class BasketballOrderContent  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballOrderContent";
	public static final String ALIAS_ID = "订单内容表id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_ODDS = "赔率";
	public static final String ALIAS_ODDSNAME = "赔率名";
	public static final String ALIAS_SETTLETIME = "结算时间";
	public static final String ALIAS_RESULT = "结果1:中奖 3:未中奖 0:未结算 4:非正常";
	public static final String ALIAS_RESULTNAME = "开奖投注项";
	public static final String ALIAS_SCHEMEID = "方案号";
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
	private java.lang.String mid;
	/**
	 * zid
	 */
	private java.lang.String zid;
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
	//columns END 数据库字段结束
	
	/**
	 * 主队名
	 */
	private java.lang.String hometeam;
	
	/**
	 * 客队名
	 */
	private java.lang.String awayteam;
	
	/**
	 * 投注字段中文名
	 */
	private java.lang.String oddsrealname;
	
	/**
	 * 短中文名
	 */
	private java.lang.String shortname;
	/**
	 * 玩法名
	 */
	private java.lang.String betname;
	
	
	private java.lang.String num2;
	
	
	private java.math.BigDecimal base;
	
	
	
	
	
	//concstructor

	@WhereSQL(sql="base=:BasketballOrderContent_base")
	public java.math.BigDecimal getBase() {
		return base;
	}

	public void setBase(java.math.BigDecimal base) {
		this.base = base;
	}

	@Transient
	public java.lang.String getNum2() {
		return num2;
	}

	public void setNum2(java.lang.String num2) {
		this.num2 = num2;
	}
	
	@Transient
	public java.lang.String getShortname() {
		return shortname;
	}

	public void setShortname(java.lang.String shortname) {
		this.shortname = shortname;
	}

	@Transient
	public java.lang.String getBetname() {
		return betname;
	}

	public void setBetname(java.lang.String betname) {
		this.betname = betname;
	}

	@Transient
	public java.lang.String getHometeam() {
		return hometeam;
	}
	
	public void setHometeam(java.lang.String hometeam) {
		this.hometeam = hometeam;
	}

	@Transient
	public java.lang.String getAwayteam() {
		return awayteam;
	}

	public void setAwayteam(java.lang.String awayteam) {
		this.awayteam = awayteam;
	}

	@Transient
	public java.lang.String getOddsrealname() {
		return oddsrealname;
	}

	public void setOddsrealname(java.lang.String oddsrealname) {
		this.oddsrealname = oddsrealname;
	}

	public BasketballOrderContent(){
	}

	public BasketballOrderContent(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballOrderContent_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BasketballOrderContent_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
     @WhereSQL(sql="mid=:BasketballOrderContent_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
     @WhereSQL(sql="zid=:BasketballOrderContent_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
	public void setOdds(java.lang.Double value) {
		this.odds = value;
	}
	
     @WhereSQL(sql="odds=:BasketballOrderContent_odds")
	public java.lang.Double getOdds() {
		return this.odds;
	}
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
     @WhereSQL(sql="oddsname=:BasketballOrderContent_oddsname")
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
	
     @WhereSQL(sql="settletime=:BasketballOrderContent_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}
	public void setResult(java.lang.Integer value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BasketballOrderContent_result")
	public java.lang.Integer getResult() {
		return this.result;
	}
	public void setResultname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.resultname = value;
	}
	
     @WhereSQL(sql="resultname=:BasketballOrderContent_resultname")
	public java.lang.String getResultname() {
		return this.resultname;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:BasketballOrderContent_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("订单内容表id[").append(getId()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("赔率[").append(getOdds()).append("],")
			.append("赔率名[").append(getOddsname()).append("],")
			.append("结算时间[").append(getSettletime()).append("],")
			.append("结果1:中奖 3:未中奖 0:未结算 4:非正常[").append(getResult()).append("],")
			.append("开奖投注项[").append(getResultname()).append("],")
			.append("方案号[").append(getSchemeid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballOrderContent == false) return false;
		if(this == obj) return true;
		BasketballOrderContent other = (BasketballOrderContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
