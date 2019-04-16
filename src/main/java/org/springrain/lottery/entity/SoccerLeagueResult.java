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
 * @version  2017-08-24 16:23:56
 * @see org.springrain.lottery.entity.SoccerLeagueResult
 */
@Table(name="soccer_league_result")
public class SoccerLeagueResult  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueResult";
	public static final String ALIAS_ID = "比分表id";
	public static final String ALIAS_NUM = "编号";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_ARRANGEID2 = "场次id2";
	public static final String ALIAS_MATCHTIME = "比赛时间";
	public static final String ALIAS_LEFT_TEAM = "主队名";
	public static final String ALIAS_LEFT_TEAM_ID2 = "主队id2";
	public static final String ALIAS_RIGHT_TEAM = "客队名";
	public static final String ALIAS_RIGHT_TEAM_ID2 = "客队id2";
	public static final String ALIAS_HALFSCORE = "半场比分";
	public static final String ALIAS_ALLSCORE = "全场比分";
	public static final String ALIAS_STATE = "状态1:正常3:删除";
    */
	//date formats
	//public static final String FORMAT_MATCHTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 比分表id
	 */
	private java.lang.Integer id;
	/**
	 * 编号
	 */
	private java.lang.String num;
	/**
	 * mid
	 */
	private java.lang.String mid;
	/**
	 * zid
	 */
	private java.lang.String zid;
	/**
	 * 场次id2
	 */
	private java.lang.String arrangeid2;
	/**
	 * 比赛时间
	 */
	private java.util.Date matchtime;
	/**
	 * 主队名
	 */
	private java.lang.String left_team;
	/**
	 * 主队id2
	 */
	private java.lang.String left_team_id2;
	/**
	 * 客队名
	 */
	private java.lang.String right_team;
	/**
	 * 客队id2
	 */
	private java.lang.String right_team_id2;
	/**
	 * 半场比分
	 */
	private java.lang.String halfscore;
	/**
	 * 全场比分
	 */
	private java.lang.String allscore;
	/**
	 * 状态1:正常3:删除
	 */
	private java.lang.Integer state;

	/**
	 * 结算1:已结算 3:未结算
	 */
	private java.lang.Integer issettle;
	private Date creattime;
	//columns END 数据库字段结束
	
	/**
	 * 联赛名
	 */
	private java.lang.String name;
	
	/**
	 * 总进球
	 */
	private java.lang.String zjq;
	
	/**
	 * 胜平负
	 */
	private java.lang.String spf;
	
	/**
	 * 让球胜平负
	 */
	private java.lang.String rqspf;

	/**
	 * 半全场
	 */
	private java.lang.String bqc;

	/**
	 * 比分
	 */
	private java.lang.String bf;
	
	/**
	 * 2选1
	 */
	private java.lang.String choose;
	/**
	 * 截止投注时间
	 */
	private java.util.Date endtime;
	//concstructor

	
	
	@Transient
	public java.util.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	
	@Transient
	public java.lang.String getChoose() {
		return choose;
	}
	public void setChoose(java.lang.String choose) {
		this.choose = choose;
	}
	
	@Transient
	public java.lang.String getBf() {
		return bf;
	}
	public void setBf(java.lang.String bf) {
		this.bf = bf;
	}
	
	@Transient
	public java.lang.String getBqc() {
		return bqc;
	}
	public void setBqc(java.lang.String bqc) {
		this.bqc = bqc;
	}
	
	@Transient
	public java.lang.String getRqspf() {
		return rqspf;
	}
	public void setRqspf(java.lang.String rqspf) {
		this.rqspf = rqspf;
	}
	@Transient
	public java.lang.String getSpf() {
		return spf;
	}
	public void setSpf(java.lang.String spf) {
		this.spf = spf;
	}
	@Transient
	public java.lang.String getZjq() {
		return zjq;
	}

	public void setZjq(java.lang.String zjq) {
		this.zjq = zjq;
	}
	
	@Transient
	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public SoccerLeagueResult(){
	}

	public SoccerLeagueResult(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 比分表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 比分表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueResult_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 编号
		 */
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
	
	
	/**
	 * 编号
	 */
     @WhereSQL(sql="num=:SoccerLeagueResult_num")
	public java.lang.String getNum() {
		return this.num;
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
     @WhereSQL(sql="mid=:SoccerLeagueResult_mid")
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
     @WhereSQL(sql="zid=:SoccerLeagueResult_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
		/**
		 * 场次id2
		 */
	public void setArrangeid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.arrangeid2 = value;
	}
	
	
	
	/**
	 * 场次id2
	 */
     @WhereSQL(sql="arrangeid2=:SoccerLeagueResult_arrangeid2")
	public java.lang.String getArrangeid2() {
		return this.arrangeid2;
	}
		/*
	public String getmatchtimeString() {
		return DateUtils.convertDate2String(FORMAT_MATCHTIME, getmatchtime());
	}
	public void setmatchtimeString(String value) throws ParseException{
		setmatchtime(DateUtils.convertString2Date(FORMAT_MATCHTIME,value));
	}*/
	
		/**
		 * 比赛时间
		 */
	public void setMatchtime(java.util.Date value) {
		this.matchtime = value;
	}
	
	
	
	/**
	 * 比赛时间
	 */
     @WhereSQL(sql="matchtime=:SoccerLeagueResult_matchtime")
	public java.util.Date getMatchtime() {
		return this.matchtime;
	}
		/**
		 * 主队名
		 */
	public void setLeft_team(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.left_team = value;
	}
	
	
	
	/**
	 * 主队名
	 */
     @WhereSQL(sql="left_team=:SoccerLeagueResult_left_team")
	public java.lang.String getLeft_team() {
		return this.left_team;
	}
		/**
		 * 主队id2
		 */
	public void setLeft_team_id2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.left_team_id2 = value;
	}
	
	
	
	/**
	 * 主队id2
	 */
     @WhereSQL(sql="left_team_id2=:SoccerLeagueResult_left_team_id2")
	public java.lang.String getLeft_team_id2() {
		return this.left_team_id2;
	}
		/**
		 * 客队名
		 */
	public void setRight_team(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.right_team = value;
	}
	
	
	
	/**
	 * 客队名
	 */
     @WhereSQL(sql="right_team=:SoccerLeagueResult_right_team")
	public java.lang.String getRight_team() {
		return this.right_team;
	}
		/**
		 * 客队id2
		 */
	public void setRight_team_id2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.right_team_id2 = value;
	}
	
	
	
	/**
	 * 客队id2
	 */
     @WhereSQL(sql="right_team_id2=:SoccerLeagueResult_right_team_id2")
	public java.lang.String getRight_team_id2() {
		return this.right_team_id2;
	}
		/**
		 * 半场比分
		 */
	public void setHalfscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.halfscore = value;
	}
	
	
	
	/**
	 * 半场比分
	 */
     @WhereSQL(sql="halfscore=:SoccerLeagueResult_halfscore")
	public java.lang.String getHalfscore() {
		return this.halfscore;
	}
		/**
		 * 全场比分
		 */
	public void setAllscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allscore = value;
	}
	
	
	
	/**
	 * 全场比分
	 */
     @WhereSQL(sql="allscore=:SoccerLeagueResult_allscore")
	public java.lang.String getAllscore() {
		return this.allscore;
	}
		/**
		 * 状态1:正常3:删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 状态1:正常3:删除
	 */
     @WhereSQL(sql="state=:SoccerLeagueResult_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     
     @WhereSQL(sql="issettle=:SoccerLeagueResult_issettle")
	public java.lang.Integer getIssettle() {
		return issettle;
	}
	public void setIssettle(java.lang.Integer issettle) {
		this.issettle = issettle;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("比分表id[").append(getId()).append("],")
			.append("编号[").append(getNum()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("场次id2[").append(getArrangeid2()).append("],")
			.append("比赛时间[").append(getMatchtime()).append("],")
			.append("主队名[").append(getLeft_team()).append("],")
			.append("主队id2[").append(getLeft_team_id2()).append("],")
			.append("客队名[").append(getRight_team()).append("],")
			.append("客队id2[").append(getRight_team_id2()).append("],")
			.append("半场比分[").append(getHalfscore()).append("],")
			.append("全场比分[").append(getAllscore()).append("],")
			.append("状态1:正常3:删除[").append(getState()).append("],")
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
		if(obj instanceof SoccerLeagueResult == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueResult other = (SoccerLeagueResult)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
}

	
