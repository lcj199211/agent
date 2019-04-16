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
 * @version  2017-09-18 09:08:37
 * @see org.springrain.lottery.entity.SoccerSchemeMatch
 */
@Table(name="soccer_scheme_match")
public class SoccerSchemeMatch  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerSchemeMatch";
	public static final String ALIAS_ID = "方案比赛表id";
	public static final String ALIAS_SCHEMEID = "方案id";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_DAN = "胆(0无,1有)";
	public static final String ALIAS_ODDSNAME = "赔率名";
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
	 * 赔率名
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
	private java.lang.String leftteamname;
	/**
	 * 客队名
	 */
	private java.lang.String rightteamname;
	/**
	 * 投注字段中文名
	 */
	private java.lang.String oddsrealname;
	/**
	 * 半场比分
	 */
	private java.lang.String halfscore;
	/**
	 * 全场比分
	 */
	private java.lang.String allscore;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 开赛时间
	 */
	private java.util.Date endtime;
	
	private java.lang.String num;
	
	private List<Map<String, Object>> resultMap;
	
	@Transient
	public List<Map<String, Object>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(List<Map<String, Object>> resultMap) {
		this.resultMap = resultMap;
	}

	@Transient
	public java.lang.String getNum() {
		return num;
	}

	public void setNum(java.lang.String num) {
		this.num = num;
	}

	@Transient
	public java.util.Date getEndtime() {
		return endtime;
	}

	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}

	@Transient
	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}

	@Transient
	public java.lang.String getHalfscore() {
		return halfscore;
	}

	public void setHalfscore(java.lang.String halfscore) {
		this.halfscore = halfscore;
	}
	@Transient
	public java.lang.String getAllscore() {
		return allscore;
	}

	public void setAllscore(java.lang.String allscore) {
		this.allscore = allscore;
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

	@Transient
	public java.lang.String getOddsrealname() {
		return oddsrealname;
	}

	public void setOddsrealname(java.lang.String oddsrealname) {
		this.oddsrealname = oddsrealname;
	}
	
	//concstructor
	

	public SoccerSchemeMatch(){
	}

	public SoccerSchemeMatch(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 方案比赛表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 方案比赛表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerSchemeMatch_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 方案id
		 */
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
	
	
	/**
	 * 方案id
	 */
     @WhereSQL(sql="schemeid=:SoccerSchemeMatch_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
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
     @WhereSQL(sql="mid=:SoccerSchemeMatch_mid")
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
     @WhereSQL(sql="zid=:SoccerSchemeMatch_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
		/**
		 * 胆(0无,1有)
		 */
	public void setDan(java.lang.Integer value) {
		this.dan = value;
	}
	
	
	
	/**
	 * 胆(0无,1有)
	 */
     @WhereSQL(sql="dan=:SoccerSchemeMatch_dan")
	public java.lang.Integer getDan() {
		return this.dan;
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
     @WhereSQL(sql="oddsname=:SoccerSchemeMatch_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
		/**
		 * 赛事结果
		 */
	public void setResult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.result = value;
	}
	
	
	
	/**
	 * 赛事结果
	 */
     @WhereSQL(sql="result=:SoccerSchemeMatch_result")
	public java.lang.String getResult() {
		return this.result;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("方案比赛表id[").append(getId()).append("],")
			.append("方案id[").append(getSchemeid()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("胆(0无,1有)[").append(getDan()).append("],")
			.append("赔率名[").append(getOddsname()).append("],")
			.append("赛事结果[").append(getResult()).append("],")
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
		if(obj instanceof SoccerSchemeMatch == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerSchemeMatch other = (SoccerSchemeMatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
