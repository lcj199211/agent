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
 * @version  2017-11-07 13:46:58
 * @see org.springrain.lottery.entity.BasketballLeagueOdds
 */
@Table(name="basketball_league_odds")
public class BasketballLeagueOdds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球联赛赔率";
	public static final String ALIAS_ID = "篮球赔率表id";
	public static final String ALIAS_MID = "场次id(来自500)";
	public static final String ALIAS_ZID = "场次id(来自500)";
	public static final String ALIAS_ARRANGEID = "赛事id";
	public static final String ALIAS_ARRANGEID2 = "赛事id2";
	public static final String ALIAS_SFZS = "胜负主胜";
	public static final String ALIAS_SFZF = "胜负主负";
	public static final String ALIAS_LETPOINTS = "主队让分";
	public static final String ALIAS_RFZS = "让分主胜";
	public static final String ALIAS_RFZF = "让分主负";
	public static final String ALIAS_DXF = "大小分预设";
	public static final String ALIAS_BIG = "大";
	public static final String ALIAS_SMALL = "小";
	public static final String ALIAS_ZSFC1T5 = "主胜";
	public static final String ALIAS_ZSFC6T10 = "主胜";
	public static final String ALIAS_ZSFC11T15 = "主胜";
	public static final String ALIAS_ZSFC16T20 = "主胜";
	public static final String ALIAS_ZSFC21T25 = "主胜";
	public static final String ALIAS_ZSFC26 = "主胜";
	public static final String ALIAS_KSFC1T5 = "客胜";
	public static final String ALIAS_KSFC6T10 = "客胜";
	public static final String ALIAS_KSFC11T15 = "客胜";
	public static final String ALIAS_KSFC16T20 = "客胜";
	public static final String ALIAS_KSFC21T25 = "客胜";
	public static final String ALIAS_KSFC26 = "客胜";
	public static final String ALIAS_DATE = "时间(最新时间)";
	public static final String ALIAS_STATE = "1正常,3删除";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 篮球赔率表id
	 */
	private java.lang.Integer id;
	/**
	 * 场次id(来自500)
	 */
	private java.lang.String mid;
	/**
	 * 场次id(来自500)
	 */
	private java.lang.String zid;
	/**
	 * 赛事id
	 */
	private java.lang.Integer arrangeid;
	/**
	 * 赛事id2
	 */
	private java.lang.String arrangeid2;
	/**
	 * 胜负主胜
	 */
	private java.math.BigDecimal sfzs;
	/**
	 * 胜负主负
	 */
	private java.math.BigDecimal sfzf;
	/**
	 * 主队让分
	 */
	private java.math.BigDecimal letpoints;
	/**
	 * 让分主胜
	 */
	private java.math.BigDecimal rfzs;
	/**
	 * 让分主负
	 */
	private java.math.BigDecimal rfzf;
	/**
	 * 大小分预设
	 */
	private java.math.BigDecimal dxf;
	/**
	 * 大
	 */
	private java.math.BigDecimal big;
	/**
	 * 小
	 */
	private java.math.BigDecimal small;
	/**
	 * 主胜
	 */
	private java.math.BigDecimal zsfc1t5;
	/**
	 * 主胜
	 */
	private java.math.BigDecimal zsfc6t10;
	/**
	 * 主胜
	 */
	private java.math.BigDecimal zsfc11t15;
	/**
	 * 主胜
	 */
	private java.math.BigDecimal zsfc16t20;
	/**
	 * 主胜
	 */
	private java.math.BigDecimal zsfc21t25;
	/**
	 * 主胜
	 */
	private java.math.BigDecimal zsfc26;
	/**
	 * 客胜
	 */
	private java.math.BigDecimal ksfc1t5;
	/**
	 * 客胜
	 */
	private java.math.BigDecimal ksfc6t10;
	/**
	 * 客胜
	 */
	private java.math.BigDecimal ksfc11t15;
	/**
	 * 客胜
	 */
	private java.math.BigDecimal ksfc16t20;
	/**
	 * 客胜
	 */
	private java.math.BigDecimal ksfc21t25;
	/**
	 * 客胜
	 */
	private java.math.BigDecimal ksfc26;
	/**
	 * 时间(最新时间)
	 */
	private java.util.Date date;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballLeagueOdds(){
	}

	public BasketballLeagueOdds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballLeagueOdds_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
     @WhereSQL(sql="mid=:BasketballLeagueOdds_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
     @WhereSQL(sql="zid=:BasketballLeagueOdds_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
	public void setArrangeid(java.lang.Integer value) {
		this.arrangeid = value;
	}
	
     @WhereSQL(sql="arrangeid=:BasketballLeagueOdds_arrangeid")
	public java.lang.Integer getArrangeid() {
		return this.arrangeid;
	}
	public void setArrangeid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.arrangeid2 = value;
	}
	
     @WhereSQL(sql="arrangeid2=:BasketballLeagueOdds_arrangeid2")
	public java.lang.String getArrangeid2() {
		return this.arrangeid2;
	}
	public void setSfzs(java.math.BigDecimal value) {
		this.sfzs = value;
	}
	
     @WhereSQL(sql="sfzs=:BasketballLeagueOdds_sfzs")
	public java.math.BigDecimal getSfzs() {
		return this.sfzs;
	}
	public void setSfzf(java.math.BigDecimal value) {
		this.sfzf = value;
	}
	
     @WhereSQL(sql="sfzf=:BasketballLeagueOdds_sfzf")
	public java.math.BigDecimal getSfzf() {
		return this.sfzf;
	}
	public void setLetpoints(java.math.BigDecimal value) {
		this.letpoints = value;
	}
	
     @WhereSQL(sql="letpoints=:BasketballLeagueOdds_letpoints")
	public java.math.BigDecimal getLetpoints() {
		return this.letpoints;
	}
	public void setRfzs(java.math.BigDecimal value) {
		this.rfzs = value;
	}
	
     @WhereSQL(sql="rfzs=:BasketballLeagueOdds_rfzs")
	public java.math.BigDecimal getRfzs() {
		return this.rfzs;
	}
	public void setRfzf(java.math.BigDecimal value) {
		this.rfzf = value;
	}
	
     @WhereSQL(sql="rfzf=:BasketballLeagueOdds_rfzf")
	public java.math.BigDecimal getRfzf() {
		return this.rfzf;
	}
	public void setDxf(java.math.BigDecimal value) {
		this.dxf = value;
	}
	
     @WhereSQL(sql="dxf=:BasketballLeagueOdds_dxf")
	public java.math.BigDecimal getDxf() {
		return this.dxf;
	}
	public void setBig(java.math.BigDecimal value) {
		this.big = value;
	}
	
     @WhereSQL(sql="big=:BasketballLeagueOdds_big")
	public java.math.BigDecimal getBig() {
		return this.big;
	}
	public void setSmall(java.math.BigDecimal value) {
		this.small = value;
	}
	
     @WhereSQL(sql="small=:BasketballLeagueOdds_small")
	public java.math.BigDecimal getSmall() {
		return this.small;
	}
	public void setZsfc1t5(java.math.BigDecimal value) {
		this.zsfc1t5 = value;
	}
	
     @WhereSQL(sql="zsfc1t5=:BasketballLeagueOdds_zsfc1t5")
	public java.math.BigDecimal getZsfc1t5() {
		return this.zsfc1t5;
	}
	public void setZsfc6t10(java.math.BigDecimal value) {
		this.zsfc6t10 = value;
	}
	
     @WhereSQL(sql="zsfc6t10=:BasketballLeagueOdds_zsfc6t10")
	public java.math.BigDecimal getZsfc6t10() {
		return this.zsfc6t10;
	}
	public void setZsfc11t15(java.math.BigDecimal value) {
		this.zsfc11t15 = value;
	}
	
     @WhereSQL(sql="zsfc11t15=:BasketballLeagueOdds_zsfc11t15")
	public java.math.BigDecimal getZsfc11t15() {
		return this.zsfc11t15;
	}
	public void setZsfc16t20(java.math.BigDecimal value) {
		this.zsfc16t20 = value;
	}
	
     @WhereSQL(sql="zsfc16t20=:BasketballLeagueOdds_zsfc16t20")
	public java.math.BigDecimal getZsfc16t20() {
		return this.zsfc16t20;
	}
	public void setZsfc21t25(java.math.BigDecimal value) {
		this.zsfc21t25 = value;
	}
	
     @WhereSQL(sql="zsfc21t25=:BasketballLeagueOdds_zsfc21t25")
	public java.math.BigDecimal getZsfc21t25() {
		return this.zsfc21t25;
	}
	public void setZsfc26(java.math.BigDecimal value) {
		this.zsfc26 = value;
	}
	
     @WhereSQL(sql="zsfc26=:BasketballLeagueOdds_zsfc26")
	public java.math.BigDecimal getZsfc26() {
		return this.zsfc26;
	}
	public void setKsfc1t5(java.math.BigDecimal value) {
		this.ksfc1t5 = value;
	}
	
     @WhereSQL(sql="ksfc1t5=:BasketballLeagueOdds_ksfc1t5")
	public java.math.BigDecimal getKsfc1t5() {
		return this.ksfc1t5;
	}
	public void setKsfc6t10(java.math.BigDecimal value) {
		this.ksfc6t10 = value;
	}
	
     @WhereSQL(sql="ksfc6t10=:BasketballLeagueOdds_ksfc6t10")
	public java.math.BigDecimal getKsfc6t10() {
		return this.ksfc6t10;
	}
	public void setKsfc11t15(java.math.BigDecimal value) {
		this.ksfc11t15 = value;
	}
	
     @WhereSQL(sql="ksfc11t15=:BasketballLeagueOdds_ksfc11t15")
	public java.math.BigDecimal getKsfc11t15() {
		return this.ksfc11t15;
	}
	public void setKsfc16t20(java.math.BigDecimal value) {
		this.ksfc16t20 = value;
	}
	
     @WhereSQL(sql="ksfc16t20=:BasketballLeagueOdds_ksfc16t20")
	public java.math.BigDecimal getKsfc16t20() {
		return this.ksfc16t20;
	}
	public void setKsfc21t25(java.math.BigDecimal value) {
		this.ksfc21t25 = value;
	}
	
     @WhereSQL(sql="ksfc21t25=:BasketballLeagueOdds_ksfc21t25")
	public java.math.BigDecimal getKsfc21t25() {
		return this.ksfc21t25;
	}
	public void setKsfc26(java.math.BigDecimal value) {
		this.ksfc26 = value;
	}
	
     @WhereSQL(sql="ksfc26=:BasketballLeagueOdds_ksfc26")
	public java.math.BigDecimal getKsfc26() {
		return this.ksfc26;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:BasketballLeagueOdds_date")
	public java.util.Date getDate() {
		return this.date;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BasketballLeagueOdds_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("篮球赔率表id[").append(getId()).append("],")
			.append("场次id(来自500)[").append(getMid()).append("],")
			.append("场次id(来自500)[").append(getZid()).append("],")
			.append("赛事id[").append(getArrangeid()).append("],")
			.append("赛事id2[").append(getArrangeid2()).append("],")
			.append("胜负主胜[").append(getSfzs()).append("],")
			.append("胜负主负[").append(getSfzf()).append("],")
			.append("主队让分[").append(getLetpoints()).append("],")
			.append("让分主胜[").append(getRfzs()).append("],")
			.append("让分主负[").append(getRfzf()).append("],")
			.append("大小分预设[").append(getDxf()).append("],")
			.append("大[").append(getBig()).append("],")
			.append("小[").append(getSmall()).append("],")
			.append("主胜[").append(getZsfc1t5()).append("],")
			.append("主胜[").append(getZsfc6t10()).append("],")
			.append("主胜[").append(getZsfc11t15()).append("],")
			.append("主胜[").append(getZsfc16t20()).append("],")
			.append("主胜[").append(getZsfc21t25()).append("],")
			.append("主胜[").append(getZsfc26()).append("],")
			.append("客胜[").append(getKsfc1t5()).append("],")
			.append("客胜[").append(getKsfc6t10()).append("],")
			.append("客胜[").append(getKsfc11t15()).append("],")
			.append("客胜[").append(getKsfc16t20()).append("],")
			.append("客胜[").append(getKsfc21t25()).append("],")
			.append("客胜[").append(getKsfc26()).append("],")
			.append("时间(最新时间)[").append(getDate()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballLeagueOdds == false) return false;
		if(this == obj) return true;
		BasketballLeagueOdds other = (BasketballLeagueOdds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
