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
 * @version  2017-11-07 13:59:14
 * @see org.springrain.lottery.entity.BasketballAdjustodds
 */
@Table(name="basketball_adjustodds")
public class BasketballAdjustodds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballAdjustodds";
	public static final String ALIAS_ID = "赔率调整表";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_SFZS = "sfzs";
	public static final String ALIAS_SFZF = "sfzf";
	public static final String ALIAS_RFZS = "rfzs";
	public static final String ALIAS_RFZF = "rfzf";
	public static final String ALIAS_BIG = "big";
	public static final String ALIAS_SMALL = "small";
	public static final String ALIAS_ZSFC1T5 = "zsfc1t5";
	public static final String ALIAS_ZSFC6T10 = "zsfc6t10";
	public static final String ALIAS_ZSFC11T15 = "zsfc11t15";
	public static final String ALIAS_ZSFC16T20 = "zsfc16t20";
	public static final String ALIAS_ZSFC26 = "zsfc26";
	public static final String ALIAS_KSFC1T5 = "ksfc1t5";
	public static final String ALIAS_KSFC6T10 = "ksfc6t10";
	public static final String ALIAS_KSFC11T15 = "ksfc11t15";
	public static final String ALIAS_KSFC16T20 = "ksfc16t20";
	public static final String ALIAS_KSFC26 = "ksfc26";
    */
	//date formats
	
	//columns START
	/**
	 * 赔率调整表
	 */
	private java.lang.Integer id;
	/**
	 * mid
	 */
	private java.lang.String mid;
	/**
	 * zid
	 */
	private java.lang.String zid;
	/**
	 * sfzs
	 */
	private java.math.BigDecimal sfzs;
	/**
	 * sfzf
	 */
	private java.math.BigDecimal sfzf;
	/**
	 * rfzs
	 */
	private java.math.BigDecimal rfzs;
	/**
	 * rfzf
	 */
	private java.math.BigDecimal rfzf;
	/**
	 * big
	 */
	private java.math.BigDecimal big;
	/**
	 * small
	 */
	private java.math.BigDecimal small;
	/**
	 * zsfc1t5
	 */
	private java.math.BigDecimal zsfc1t5;
	/**
	 * zsfc6t10
	 */
	private java.math.BigDecimal zsfc6t10;
	/**
	 * zsfc11t15
	 */
	private java.math.BigDecimal zsfc11t15;
	/**
	 * zsfc16t20
	 */
	private java.math.BigDecimal zsfc16t20;
	/**
	 * zsfc21t25
	 */
	private java.math.BigDecimal zsfc21t25;
	/**
	 * zsfc26
	 */
	private java.math.BigDecimal zsfc26;
	/**
	 * ksfc1t5
	 */
	private java.math.BigDecimal ksfc1t5;
	/**
	 * ksfc6t10
	 */
	private java.math.BigDecimal ksfc6t10;
	/**
	 * ksfc11t15
	 */
	private java.math.BigDecimal ksfc11t15;
	/**
	 * ksfc16t20
	 */
	private java.math.BigDecimal ksfc16t20;
	/**
	 * ksfc21t25
	 */
	private java.math.BigDecimal ksfc21t25;
	/**
	 * ksfc26
	 */
	private java.math.BigDecimal ksfc26;
	//columns END 数据库字段结束
	
	/**
	 * 序号(来自500)
	 */
	private java.lang.String num;
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 截止投注时间
	 */
	private java.util.Date endtime;
	/**
	 * 主队名
	 */
	private java.lang.String hometeam;
	/**
	 * 客队名
	 */
	private java.lang.String awayteam;
	/**
	 * 哪天的比赛
	 */
	private java.util.Date matchdate;
	
	
	
	//concstructor
	@Transient
	public java.lang.String getNum() {
		return num;
	}

	public void setNum(java.lang.String num) {
		this.num = num;
	}
	@Transient
	public java.lang.String getMatchname() {
		return matchname;
	}

	public void setMatchname(java.lang.String matchname) {
		this.matchname = matchname;
	}
	@Transient
	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	@Transient
	public java.util.Date getEndtime() {
		return endtime;
	}

	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
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
	public java.util.Date getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(java.util.Date matchdate) {
		this.matchdate = matchdate;
	}

	public BasketballAdjustodds(){
	}

	public BasketballAdjustodds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballAdjustodds_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
     @WhereSQL(sql="mid=:BasketballAdjustodds_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
     @WhereSQL(sql="zid=:BasketballAdjustodds_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
	public void setSfzs(java.math.BigDecimal value) {
		this.sfzs = value;
	}
	
     @WhereSQL(sql="sfzs=:BasketballAdjustodds_sfzs")
	public java.math.BigDecimal getSfzs() {
		return this.sfzs;
	}
	public void setSfzf(java.math.BigDecimal value) {
		this.sfzf = value;
	}
	
     @WhereSQL(sql="sfzf=:BasketballAdjustodds_sfzf")
	public java.math.BigDecimal getSfzf() {
		return this.sfzf;
	}
	public void setRfzs(java.math.BigDecimal value) {
		this.rfzs = value;
	}
	
     @WhereSQL(sql="rfzs=:BasketballAdjustodds_rfzs")
	public java.math.BigDecimal getRfzs() {
		return this.rfzs;
	}
	public void setRfzf(java.math.BigDecimal value) {
		this.rfzf = value;
	}
	
     @WhereSQL(sql="rfzf=:BasketballAdjustodds_rfzf")
	public java.math.BigDecimal getRfzf() {
		return this.rfzf;
	}
	public void setBig(java.math.BigDecimal value) {
		this.big = value;
	}
	
     @WhereSQL(sql="big=:BasketballAdjustodds_big")
	public java.math.BigDecimal getBig() {
		return this.big;
	}
	public void setSmall(java.math.BigDecimal value) {
		this.small = value;
	}
	
     @WhereSQL(sql="small=:BasketballAdjustodds_small")
	public java.math.BigDecimal getSmall() {
		return this.small;
	}
	public void setZsfc1t5(java.math.BigDecimal value) {
		this.zsfc1t5 = value;
	}
	
     @WhereSQL(sql="zsfc1t5=:BasketballAdjustodds_zsfc1t5")
	public java.math.BigDecimal getZsfc1t5() {
		return this.zsfc1t5;
	}
	public void setZsfc6t10(java.math.BigDecimal value) {
		this.zsfc6t10 = value;
	}
	
     @WhereSQL(sql="zsfc6t10=:BasketballAdjustodds_zsfc6t10")
	public java.math.BigDecimal getZsfc6t10() {
		return this.zsfc6t10;
	}
	public void setZsfc11t15(java.math.BigDecimal value) {
		this.zsfc11t15 = value;
	}
	
     @WhereSQL(sql="zsfc11t15=:BasketballAdjustodds_zsfc11t15")
	public java.math.BigDecimal getZsfc11t15() {
		return this.zsfc11t15;
	}
	public void setZsfc16t20(java.math.BigDecimal value) {
		this.zsfc16t20 = value;
	}
	
     @WhereSQL(sql="zsfc16t20=:BasketballAdjustodds_zsfc16t20")
	public java.math.BigDecimal getZsfc16t20() {
		return this.zsfc16t20;
	}
	public void setZsfc26(java.math.BigDecimal value) {
		this.zsfc26 = value;
	}
	
     @WhereSQL(sql="zsfc26=:BasketballAdjustodds_zsfc26")
	public java.math.BigDecimal getZsfc26() {
		return this.zsfc26;
	}
	public void setKsfc1t5(java.math.BigDecimal value) {
		this.ksfc1t5 = value;
	}
	
     @WhereSQL(sql="ksfc1t5=:BasketballAdjustodds_ksfc1t5")
	public java.math.BigDecimal getKsfc1t5() {
		return this.ksfc1t5;
	}
	public void setKsfc6t10(java.math.BigDecimal value) {
		this.ksfc6t10 = value;
	}
	
     @WhereSQL(sql="ksfc6t10=:BasketballAdjustodds_ksfc6t10")
	public java.math.BigDecimal getKsfc6t10() {
		return this.ksfc6t10;
	}
	public void setKsfc11t15(java.math.BigDecimal value) {
		this.ksfc11t15 = value;
	}
	
     @WhereSQL(sql="ksfc11t15=:BasketballAdjustodds_ksfc11t15")
	public java.math.BigDecimal getKsfc11t15() {
		return this.ksfc11t15;
	}
	public void setKsfc16t20(java.math.BigDecimal value) {
		this.ksfc16t20 = value;
	}
	
     @WhereSQL(sql="ksfc16t20=:BasketballAdjustodds_ksfc16t20")
	public java.math.BigDecimal getKsfc16t20() {
		return this.ksfc16t20;
	}
	public void setKsfc26(java.math.BigDecimal value) {
		this.ksfc26 = value;
	}
	
     @WhereSQL(sql="ksfc26=:BasketballAdjustodds_ksfc26")
	public java.math.BigDecimal getKsfc26() {
		return this.ksfc26;
	}
     @WhereSQL(sql="zsfc21t25=:BasketballAdjustodds_zsfc21t25")
	public java.math.BigDecimal getZsfc21t25() {
		return zsfc21t25;
	}

	public void setZsfc21t25(java.math.BigDecimal zsfc21t25) {
		this.zsfc21t25 = zsfc21t25;
	}
	 @WhereSQL(sql="ksfc21t25=:BasketballAdjustodds_ksfc21t25")
	public java.math.BigDecimal getKsfc21t25() {
		return ksfc21t25;
	}

	public void setKsfc21t25(java.math.BigDecimal ksfc21t25) {
		this.ksfc21t25 = ksfc21t25;
	}

	public String toString() {
		return new StringBuffer()
			.append("赔率调整表[").append(getId()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("sfzs[").append(getSfzs()).append("],")
			.append("sfzf[").append(getSfzf()).append("],")
			.append("rfzs[").append(getRfzs()).append("],")
			.append("rfzf[").append(getRfzf()).append("],")
			.append("big[").append(getBig()).append("],")
			.append("small[").append(getSmall()).append("],")
			.append("zsfc1t5[").append(getZsfc1t5()).append("],")
			.append("zsfc6t10[").append(getZsfc6t10()).append("],")
			.append("zsfc11t15[").append(getZsfc11t15()).append("],")
			.append("zsfc16t20[").append(getZsfc16t20()).append("],")
			.append("zsfc26[").append(getZsfc26()).append("],")
			.append("ksfc1t5[").append(getKsfc1t5()).append("],")
			.append("ksfc6t10[").append(getKsfc6t10()).append("],")
			.append("ksfc11t15[").append(getKsfc11t15()).append("],")
			.append("ksfc16t20[").append(getKsfc16t20()).append("],")
			.append("ksfc26[").append(getKsfc26()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballAdjustodds == false) return false;
		if(this == obj) return true;
		BasketballAdjustodds other = (BasketballAdjustodds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
