package org.springrain.lottery.entity;


import java.util.List;
import java.util.Map;

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
 * @version  2017-09-04 09:18:59
 * @see org.springrain.lottery.entity.SoccerLeagueOrderContent
 */
@Table(name="soccer_league_order_content")
public class SoccerLeagueOrderContent  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueOrderContent";
	public static final String ALIAS_ID = "订单内容表id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_ODDS = "赔率";
	public static final String ALIAS_ODDSNAME = "赔率名";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_LETPOINT = "是否让球1:是3:否";
	public static final String ALIAS_SETTLETIME = "settletime";
	public static final String ALIAS_RESULT = "结果";
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
	 * 玩法id
	 */
	private java.lang.Integer playmethodid;
	/**
	 * 是否让球1:是3:否
	 */
	private java.lang.Integer letpoint;
	/**
	 * settletime
	 */
	private java.util.Date settletime;
	/**
	 * 结果 1:中奖 3:未中奖 0:未结算
	 */
	private java.lang.Integer result;
	
	private java.lang.String schemeid;
	//columns END 数据库字段结束
	
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 主队名
	 */
	private java.lang.String leftteamname;
	/**
	 * 客队名
	 */
	private java.lang.String rightteamname;
	/**
	 * 字段中文名
	 */
	private java.lang.String oddsrealname;
	private java.lang.String resultname;
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
	
	private List<Map<String, Object>> resultMap;
	
	private String allScore;
	private String halfScore;
	
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
	public java.lang.String getBetname() {
		return betname;
	}

	public void setBetname(java.lang.String betname) {
		this.betname = betname;
	}

	@Transient
	public java.util.Date getEndtime() {
		return endtime;
	}

	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}

	@Transient
	public java.lang.String getNum() {
		return num;
	}

	public void setNum(java.lang.String num) {
		this.num = num;
	}

	@Transient
	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}

	@WhereSQL(sql="resultname=:SoccerLeagueOrderContent_resultname")
	public java.lang.String getResultname() {
		return resultname;
	}

	public void setResultname(java.lang.String resultname) {
		this.resultname = resultname;
	}

	@Transient
	public java.lang.String getOddsrealname() {
		return oddsrealname;
	}

	public void setOddsrealname(java.lang.String oddsrealname) {
		this.oddsrealname = oddsrealname;
	}

	@Transient
	public java.lang.String getMatchname() {
		return matchname;
	}

	public void setMatchname(java.lang.String matchname) {
		this.matchname = matchname;
	}
	
	@Transient
	public java.lang.String getLeftteamname() {
		return leftteamname;
	}

	public void setLeftteamname(java.lang.String leftteamname) {
		this.leftteamname = leftteamname;
	}
	@Transient
	public java.lang.String getRightteamname() {
		return rightteamname;
	}

	public void setRightteamname(java.lang.String rightteamname) {
		this.rightteamname = rightteamname;
	}
	
	//concstructor

	public SoccerLeagueOrderContent(){
	}

	public SoccerLeagueOrderContent(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 订单内容表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 订单内容表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueOrderContent_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 订单号
		 */
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
	
	
	/**
	 * 订单号
	 */
     @WhereSQL(sql="orderid=:SoccerLeagueOrderContent_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
		/**
		 * mid
		 */
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
	
	
	/**
	 * mid
	 */
     @WhereSQL(sql="mid=:SoccerLeagueOrderContent_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
		/**
		 * zid
		 */
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
	
	
	/**
	 * zid
	 */
     @WhereSQL(sql="zid=:SoccerLeagueOrderContent_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
		/**
		 * 赔率
		 */
	public void setOdds(java.lang.Double value) {
		this.odds = value;
	}
	
	
	
	/**
	 * 赔率
	 */
     @WhereSQL(sql="odds=:SoccerLeagueOrderContent_odds")
	public java.lang.Double getOdds() {
		return this.odds;
	}
		/**
		 * 赔率名
		 */
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
	
	
	/**
	 * 赔率名
	 */
     @WhereSQL(sql="oddsname=:SoccerLeagueOrderContent_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
		/**
		 * 玩法id
		 */
	public void setPlaymethodid(java.lang.Integer value) {
		this.playmethodid = value;
	}
	
	
	
	/**
	 * 玩法id
	 */
     @WhereSQL(sql="playmethodid=:SoccerLeagueOrderContent_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
		/**
		 * 是否让球1:是3:否
		 */
	public void setLetpoint(java.lang.Integer value) {
		this.letpoint = value;
	}
	
	
	
	/**
	 * 是否让球1:是3:否
	 */
     @WhereSQL(sql="letpoint=:SoccerLeagueOrderContent_letpoint")
	public java.lang.Integer getLetpoint() {
		return this.letpoint;
	}
		/*
	public String getsettletimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLETIME, getsettletime());
	}
	public void setsettletimeString(String value) throws ParseException{
		setsettletime(DateUtils.convertString2Date(FORMAT_SETTLETIME,value));
	}*/
	
		/**
		 * settletime
		 */
	public void setSettletime(java.util.Date value) {
		this.settletime = value;
	}
	
	
	
	/**
	 * settletime
	 */
     @WhereSQL(sql="settletime=:SoccerLeagueOrderContent_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}
		/**
		 * 结果
		 */
	public void setResult(java.lang.Integer value) {
		   
		this.result = value;
	}
	
	
	
	/**
	 * 结果
	 */
     @WhereSQL(sql="result=:SoccerLeagueOrderContent_result")
	public java.lang.Integer getResult() {
		return this.result;
	}
     
     
     @WhereSQL(sql="schemeid=:SoccerLeagueOrderContent_schemeid")
	public java.lang.String getSchemeid() {
		return schemeid;
	}

	public void setSchemeid(java.lang.String schemeid) {
		this.schemeid = schemeid;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("订单内容表id[").append(getId()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("赔率[").append(getOdds()).append("],")
			.append("赔率名[").append(getOddsname()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("是否让球1:是3:否[").append(getLetpoint()).append("],")
			.append("settletime[").append(getSettletime()).append("],")
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
		if(obj instanceof SoccerLeagueOrderContent == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueOrderContent other = (SoccerLeagueOrderContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public List<Map<String, Object>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(List<Map<String, Object>> resultMap) {
		this.resultMap = resultMap;
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
}

	
