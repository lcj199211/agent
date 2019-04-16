package org.springrain.lottery.entity;

import java.util.Date;

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
 * @version  2017-12-05 11:06:35
 * @see org.springrain.lottery.entity.BjdcResult
 */
@Table(name="bjdc_result")
public class BjdcResult  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BjdcResult";
	public static final String ALIAS_ID = "北单比赛结果表id";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_NUM = "编号";
	public static final String ALIAS_PERIODNUM = "期号";
	public static final String ALIAS_MATCHNAME = "赛事名";
	public static final String ALIAS_HOMETEAM = "主队";
	public static final String ALIAS_GUESTTEAM = "客队";
	public static final String ALIAS_LETPOINTS = "让球数";
	public static final String ALIAS_ALLSCORE = "全场比分";
	public static final String ALIAS_HALFSCORE = "半场比分";
	public static final String ALIAS_STATE = "状态 1:完赛 3:未开赛";
    */
	//date formats
	
	//columns START
	/**
	 * 北单比赛结果表id
	 */
	private java.lang.Integer id;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * 编号
	 */
	private java.lang.String num;
	/**
	 * 期号
	 */
	private java.lang.String periodnum;
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 主队
	 */
	private java.lang.String hometeam;
	/**
	 * 客队
	 */
	private java.lang.String guestteam;
	/**
	 * 让球数
	 */
	private java.lang.String letpoints;
	/**
	 * 全场比分
	 */
	private java.lang.String allscore;
	/**
	 * 半场比分
	 */
	private java.lang.String halfscore;
	/**
	 * 状态 1:完赛 3:未开赛
	 */
	private java.lang.Integer state;
	private Integer issettle;
	private Integer istrue;
	
	//columns END 数据库字段结束
	
	private Date matchtime;
	private String matchid2;
	private String left_team_id2;
	private String right_team_id2;
	
	//concstructor

	public BjdcResult(){
	}

	public BjdcResult(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcResult_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcResult_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
     @WhereSQL(sql="num=:BjdcResult_num")
	public java.lang.String getNum() {
		return this.num;
	}
	public void setPeriodnum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.periodnum = value;
	}
	
     @WhereSQL(sql="periodnum=:BjdcResult_periodnum")
	public java.lang.String getPeriodnum() {
		return this.periodnum;
	}
	public void setMatchname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchname = value;
	}
	
     @WhereSQL(sql="matchname=:BjdcResult_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
	public void setHometeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeam = value;
	}
	
     @WhereSQL(sql="hometeam=:BjdcResult_hometeam")
	public java.lang.String getHometeam() {
		return this.hometeam;
	}
	public void setGuestteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.guestteam = value;
	}
	
     @WhereSQL(sql="guestteam=:BjdcResult_guestteam")
	public java.lang.String getGuestteam() {
		return this.guestteam;
	}
	public void setLetpoints(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.letpoints = value;
	}
	
     @WhereSQL(sql="letpoints=:BjdcResult_letpoints")
	public java.lang.String getLetpoints() {
		return this.letpoints;
	}
	public void setAllscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allscore = value;
	}
	
     @WhereSQL(sql="allscore=:BjdcResult_allscore")
	public java.lang.String getAllscore() {
		return this.allscore;
	}
	public void setHalfscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.halfscore = value;
	}
	
     @WhereSQL(sql="halfscore=:BjdcResult_halfscore")
	public java.lang.String getHalfscore() {
		return this.halfscore;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BjdcResult_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     @WhereSQL(sql="issettle=:BjdcResult_issettle")
     public Integer getIssettle() {
 		return issettle;
 	}

 	public void setIssettle(Integer issettle) {
 		this.issettle = issettle;
 	}
 	@Transient
 	public Date getMatchtime() {
 		return matchtime;
 	}

 	public void setMatchtime(Date matchtime) {
 		this.matchtime = matchtime;
 	}
 	@Transient
 	public String getMatchid2() {
		return matchid2;
	}

	public void setMatchid2(String matchid2) {
		this.matchid2 = matchid2;
	}
	@Transient
	public String getLeft_team_id2() {
		return left_team_id2;
	}

	public void setLeft_team_id2(String left_team_id2) {
		this.left_team_id2 = left_team_id2;
	}
	@Transient
	public String getRight_team_id2() {
		return right_team_id2;
	}

	public void setRight_team_id2(String right_team_id2) {
		this.right_team_id2 = right_team_id2;
	}
	@WhereSQL(sql="istrue=:BjdcResult_istrue")
	public Integer getIstrue() {
		return istrue;
	}

	public void setIstrue(Integer istrue) {
		this.istrue = istrue;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("北单比赛结果表id[").append(getId()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("编号[").append(getNum()).append("],")
			.append("期号[").append(getPeriodnum()).append("],")
			.append("赛事名[").append(getMatchname()).append("],")
			.append("主队[").append(getHometeam()).append("],")
			.append("客队[").append(getGuestteam()).append("],")
			.append("让球数[").append(getLetpoints()).append("],")
			.append("全场比分[").append(getAllscore()).append("],")
			.append("半场比分[").append(getHalfscore()).append("],")
			.append("状态 1:完赛 3:未开赛[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcResult == false) return false;
		if(this == obj) return true;
		BjdcResult other = (BjdcResult)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	

	
	

	

	
}

	
