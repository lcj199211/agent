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
 * @version  2017-08-23 15:18:40
 * @see org.springrain.lottery.entity.SoccerLeagueGoalodds
 */
@Table(name="soccer_league_goalodds")
public class SoccerLeagueGoalodds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueGoalodds";
	public static final String ALIAS_ID = "进球数表id";
	public static final String ALIAS_MID = "mid(来自500)";
	public static final String ALIAS_ZID = "zid(来自500)";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_ARRANGEID2 = "赛事id2";
	public static final String ALIAS_STATE = "状态1:正常3:删除";
	public static final String ALIAS_BALL0 = "进0球";
	public static final String ALIAS_BALL1 = "进1球";
	public static final String ALIAS_BALL2 = "进2球";
	public static final String ALIAS_BALL3 = "进3球";
	public static final String ALIAS_BALL4 = "进4球";
	public static final String ALIAS_BALL5 = "进5球";
	public static final String ALIAS_BALL6 = "进6球";
	public static final String ALIAS_BALL7 = "进7+球";
    */
	//date formats
	
	//columns START
	/**
	 * 进球数表id
	 */
	private java.lang.Integer id;
	/**
	 * mid(来自500)
	 */
	private java.lang.String mid;
	/**
	 * zid(来自500)
	 */
	private java.lang.String zid;
	/**
	 * 玩法id
	 */
	private java.lang.Integer playmethodid;
	/**
	 * 赛事id2
	 */
	private java.lang.String arrangeid2;
	/**
	 * 状态1:正常3:删除
	 */
	private java.lang.Integer state;
	/**
	 * 进0球
	 */
	private java.lang.Double ball0;
	/**
	 * 进1球
	 */
	private java.lang.Double ball1;
	/**
	 * 进2球
	 */
	private java.lang.Double ball2;
	/**
	 * 进3球
	 */
	private java.lang.Double ball3;
	/**
	 * 进4球
	 */
	private java.lang.Double ball4;
	/**
	 * 进5球
	 */
	private java.lang.Double ball5;
	/**
	 * 进6球
	 */
	private java.lang.Double ball6;
	/**
	 * 进7+球
	 */
	private java.lang.Double ball7;
	
	private java.util.Date datetime;
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
	 * 玩法
	 */
	private java.lang.String playmethod;
	
	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
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
	@WhereSQL(sql="datetime=:SoccerLeagueGoalodds_datetime")
	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public SoccerLeagueGoalodds(){
	}

	public SoccerLeagueGoalodds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 进球数表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 进球数表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueGoalodds_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * mid(来自500)
		 */
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
	
	
	/**
	 * mid(来自500)
	 */
     @WhereSQL(sql="mid=:SoccerLeagueGoalodds_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
		/**
		 * zid(来自500)
		 */
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
	
	
	/**
	 * zid(来自500)
	 */
     @WhereSQL(sql="zid=:SoccerLeagueGoalodds_zid")
	public java.lang.String getZid() {
		return this.zid;
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
     @WhereSQL(sql="playmethodid=:SoccerLeagueGoalodds_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
		/**
		 * 赛事id2
		 */
	public void setArrangeid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.arrangeid2 = value;
	}
	
	
	
	/**
	 * 赛事id2
	 */
     @WhereSQL(sql="arrangeid2=:SoccerLeagueGoalodds_arrangeid2")
	public java.lang.String getArrangeid2() {
		return this.arrangeid2;
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
     @WhereSQL(sql="state=:SoccerLeagueGoalodds_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/**
		 * 进0球
		 */
	public void setBall0(java.lang.Double value) {
		this.ball0 = value;
	}
	
	
	
	/**
	 * 进0球
	 */
     @WhereSQL(sql="ball0=:SoccerLeagueGoalodds_ball0")
	public java.lang.Double getBall0() {
		return this.ball0;
	}
		/**
		 * 进1球
		 */
	public void setBall1(java.lang.Double value) {
		this.ball1 = value;
	}
	
	
	
	/**
	 * 进1球
	 */
     @WhereSQL(sql="ball1=:SoccerLeagueGoalodds_ball1")
	public java.lang.Double getBall1() {
		return this.ball1;
	}
		/**
		 * 进2球
		 */
	public void setBall2(java.lang.Double value) {
		this.ball2 = value;
	}
	
	
	
	/**
	 * 进2球
	 */
     @WhereSQL(sql="ball2=:SoccerLeagueGoalodds_ball2")
	public java.lang.Double getBall2() {
		return this.ball2;
	}
		/**
		 * 进3球
		 */
	public void setBall3(java.lang.Double value) {
		this.ball3 = value;
	}
	
	
	
	/**
	 * 进3球
	 */
     @WhereSQL(sql="ball3=:SoccerLeagueGoalodds_ball3")
	public java.lang.Double getBall3() {
		return this.ball3;
	}
		/**
		 * 进4球
		 */
	public void setBall4(java.lang.Double value) {
		this.ball4 = value;
	}
	
	
	
	/**
	 * 进4球
	 */
     @WhereSQL(sql="ball4=:SoccerLeagueGoalodds_ball4")
	public java.lang.Double getBall4() {
		return this.ball4;
	}
		/**
		 * 进5球
		 */
	public void setBall5(java.lang.Double value) {
		this.ball5 = value;
	}
	
	
	
	/**
	 * 进5球
	 */
     @WhereSQL(sql="ball5=:SoccerLeagueGoalodds_ball5")
	public java.lang.Double getBall5() {
		return this.ball5;
	}
		/**
		 * 进6球
		 */
	public void setBall6(java.lang.Double value) {
		this.ball6 = value;
	}
	
	
	
	/**
	 * 进6球
	 */
     @WhereSQL(sql="ball6=:SoccerLeagueGoalodds_ball6")
	public java.lang.Double getBall6() {
		return this.ball6;
	}
		/**
		 * 进7+球
		 */
	public void setBall7(java.lang.Double value) {
		this.ball7 = value;
	}
	
	
	
	/**
	 * 进7+球
	 */
     @WhereSQL(sql="ball7=:SoccerLeagueGoalodds_ball7")
	public java.lang.Double getBall7() {
		return this.ball7;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("进球数表id[").append(getId()).append("],")
			.append("mid(来自500)[").append(getMid()).append("],")
			.append("zid(来自500)[").append(getZid()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("赛事id2[").append(getArrangeid2()).append("],")
			.append("状态1:正常3:删除[").append(getState()).append("],")
			.append("进0球[").append(getBall0()).append("],")
			.append("进1球[").append(getBall1()).append("],")
			.append("进2球[").append(getBall2()).append("],")
			.append("进3球[").append(getBall3()).append("],")
			.append("进4球[").append(getBall4()).append("],")
			.append("进5球[").append(getBall5()).append("],")
			.append("进6球[").append(getBall6()).append("],")
			.append("进7+球[").append(getBall7()).append("],")
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
		if(obj instanceof SoccerLeagueGoalodds == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueGoalodds other = (SoccerLeagueGoalodds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
