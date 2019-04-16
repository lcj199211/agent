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
 * @version  2017-11-14 16:06:43
 * @see org.springrain.lottery.entity.BasketballSchemeMatch
 */
@Table(name="basketball_scheme_match")
public class BasketballSchemeMatch  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballSchemeMatch";
	public static final String ALIAS_ID = "方案比赛表id";
	public static final String ALIAS_SCHEMEID = "方案id";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_DAN = "胆(0无,1有)";
	public static final String ALIAS_ODDSNAME = "赔率名(逗号隔开)";
	public static final String ALIAS_RESULT = "赛事结果";
    */
	//date formats
	
	//columns START
	/**
	 * 方案比赛表id
	 */
	private java.lang.Integer id;
	/**
	 * 方案id
	 */
	private java.lang.String schemeid;
	/**
	 * mid
	 */
	private java.lang.String mid;
	/**
	 * zid
	 */
	private java.lang.String zid;
	/**
	 * 胆(0无,1有)
	 */
	private java.lang.Integer dan;
	/**
	 * 赔率名(逗号隔开)
	 */
	private java.lang.String oddsname;
	/**
	 * 赛事结果
	 */
	private java.lang.String result;
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
	private java.lang.String awayteam;
	/**
	 * 投注字段中文名
	 */
	private java.lang.String oddsrealname;
	
	/**
	 * 全场比分
	 */
	private java.lang.String score;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 截止时间
	 */
	private java.util.Date endtime;
	
	private java.lang.String num;
	
	/**
	 * 哪天的比赛
	 */
	private java.util.Date matchdate;
	
	private List<Map<String, Object>> resultMap;
	
	
	
	
	//concstructor
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

	@Transient
	public java.lang.String getScore() {
		return score;
	}

	public void setScore(java.lang.String score) {
		this.score = score;
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
	public java.lang.String getNum() {
		return num;
	}

	public void setNum(java.lang.String num) {
		this.num = num;
	}

	@Transient
	public java.util.Date getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(java.util.Date matchdate) {
		this.matchdate = matchdate;
	}

	@Transient
	public List<Map<String, Object>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(List<Map<String, Object>> resultMap) {
		this.resultMap = resultMap;
	}

	public BasketballSchemeMatch(){
	}

	public BasketballSchemeMatch(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballSchemeMatch_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:BasketballSchemeMatch_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
     @WhereSQL(sql="mid=:BasketballSchemeMatch_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
     @WhereSQL(sql="zid=:BasketballSchemeMatch_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
	public void setDan(java.lang.Integer value) {
		this.dan = value;
	}
	
     @WhereSQL(sql="dan=:BasketballSchemeMatch_dan")
	public java.lang.Integer getDan() {
		return this.dan;
	}
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
     @WhereSQL(sql="oddsname=:BasketballSchemeMatch_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
	public void setResult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BasketballSchemeMatch_result")
	public java.lang.String getResult() {
		return this.result;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("方案比赛表id[").append(getId()).append("],")
			.append("方案id[").append(getSchemeid()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("胆(0无,1有)[").append(getDan()).append("],")
			.append("赔率名(逗号隔开)[").append(getOddsname()).append("],")
			.append("赛事结果[").append(getResult()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballSchemeMatch == false) return false;
		if(this == obj) return true;
		BasketballSchemeMatch other = (BasketballSchemeMatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
