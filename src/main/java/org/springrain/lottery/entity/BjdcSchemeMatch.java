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
 * @version  2017-12-12 09:37:41
 * @see org.springrain.lottery.entity.BjdcSchemeMatch
 */
@Table(name="bjdc_scheme_match")
public class BjdcSchemeMatch  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "北京单场方案比赛";
	public static final String ALIAS_ID = "方案比赛表id";
	public static final String ALIAS_SCHEMEID = "方案id";
	public static final String ALIAS_FID = "zid";
	public static final String ALIAS_DAN = "胆(0无,1有)";
	public static final String ALIAS_ODDSNAME = "赔率名(逗号隔开)";
	public static final String ALIAS_RESULT = "赛事结果";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "父级代理id";
	public static final String ALIAS_AGENTPARENTIDS = "分级代理id ,隔开";
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
	 * zid
	 */
	private java.lang.String fid;
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
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 父级代理id
	 */
	private java.lang.String agentparentid;
	/**
	 * 分级代理id ,隔开
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
	
	private java.lang.String periodnum;
	
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
	public java.lang.String getPeriodnum() {
		return periodnum;
	}

	public void setPeriodnum(java.lang.String periodnum) {
		this.periodnum = periodnum;
	}

	@Transient
	public List<Map<String, Object>> getResultMap() {
		return resultMap;
	}

	public void setResultMap(List<Map<String, Object>> resultMap) {
		this.resultMap = resultMap;
	}

	public BjdcSchemeMatch(){
	}

	public BjdcSchemeMatch(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcSchemeMatch_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:BjdcSchemeMatch_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcSchemeMatch_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setDan(java.lang.Integer value) {
		this.dan = value;
	}
	
     @WhereSQL(sql="dan=:BjdcSchemeMatch_dan")
	public java.lang.Integer getDan() {
		return this.dan;
	}
	public void setOddsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.oddsname = value;
	}
	
     @WhereSQL(sql="oddsname=:BjdcSchemeMatch_oddsname")
	public java.lang.String getOddsname() {
		return this.oddsname;
	}
	public void setResult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BjdcSchemeMatch_result")
	public java.lang.String getResult() {
		return this.result;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BjdcSchemeMatch_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BjdcSchemeMatch_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BjdcSchemeMatch_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("方案比赛表id[").append(getId()).append("],")
			.append("方案id[").append(getSchemeid()).append("],")
			.append("zid[").append(getFid()).append("],")
			.append("胆(0无,1有)[").append(getDan()).append("],")
			.append("赔率名(逗号隔开)[").append(getOddsname()).append("],")
			.append("赛事结果[").append(getResult()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("父级代理id[").append(getAgentparentid()).append("],")
			.append("分级代理id ,隔开[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcSchemeMatch == false) return false;
		if(this == obj) return true;
		BjdcSchemeMatch other = (BjdcSchemeMatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
