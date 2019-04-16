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
 * @version  2017-08-23 16:47:58
 * @see org.springrain.lottery.entity.SoccerLeague2choose1odds
 */
@Table(name="soccer_league_2choose1odds")
public class SoccerLeague2choose1odds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeague2choose1odds";
	public static final String ALIAS_ID = "二选一赔率表id";
	public static final String ALIAS_MID = "mid(来自500)";
	public static final String ALIAS_ZID = "zid(来自500)";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_ARRANGEID2 = "赛事id2";
	public static final String ALIAS_STATE = "状态1:正常3:删除";
	public static final String ALIAS_LEFT_NAME = "主队胜负情况";
	public static final String ALIAS_LEFT_ODDS = "压主队赔率";
	public static final String ALIAS_RIGHT_ODDS = "压客队赔率";
	public static final String ALIAS_RIGHT_NAME = "客队胜负情况";
    */
	//date formats
	
	//columns START
	/**
	 * 二选一赔率表id
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
	 * 主队胜负情况
	 */
	private java.lang.String left_name;
	/**
	 * 压主队赔率
	 */
	private java.lang.Double left_odds;
	/**
	 * 压客队赔率
	 */
	private java.lang.Double right_odds;
	/**
	 * 客队胜负情况
	 */
	private java.lang.String right_name;
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
	@WhereSQL(sql="datetime=:SoccerLeague2choose1odds_datetime")
	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public SoccerLeague2choose1odds(){
	}

	public SoccerLeague2choose1odds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 二选一赔率表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 二选一赔率表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeague2choose1odds_id")
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
     @WhereSQL(sql="mid=:SoccerLeague2choose1odds_mid")
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
     @WhereSQL(sql="zid=:SoccerLeague2choose1odds_zid")
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
     @WhereSQL(sql="playmethodid=:SoccerLeague2choose1odds_playmethodid")
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
     @WhereSQL(sql="arrangeid2=:SoccerLeague2choose1odds_arrangeid2")
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
     @WhereSQL(sql="state=:SoccerLeague2choose1odds_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/**
		 * 主队胜负情况
		 */
	public void setLeft_name(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.left_name = value;
	}
	
	
	
	/**
	 * 主队胜负情况
	 */
     @WhereSQL(sql="left_name=:SoccerLeague2choose1odds_left_name")
	public java.lang.String getLeft_name() {
		return this.left_name;
	}
		/**
		 * 压主队赔率
		 */
	public void setLeft_odds(java.lang.Double value) {
		this.left_odds = value;
	}
	
	
	
	/**
	 * 压主队赔率
	 */
     @WhereSQL(sql="left_odds=:SoccerLeague2choose1odds_left_odds")
	public java.lang.Double getLeft_odds() {
		return this.left_odds;
	}
		/**
		 * 压客队赔率
		 */
	public void setRight_odds(java.lang.Double value) {
		this.right_odds = value;
	}
	
	
	
	/**
	 * 压客队赔率
	 */
     @WhereSQL(sql="right_odds=:SoccerLeague2choose1odds_right_odds")
	public java.lang.Double getRight_odds() {
		return this.right_odds;
	}
		/**
		 * 客队胜负情况
		 */
	public void setRight_name(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.right_name = value;
	}
	
	
	
	/**
	 * 客队胜负情况
	 */
     @WhereSQL(sql="right_name=:SoccerLeague2choose1odds_right_name")
	public java.lang.String getRight_name() {
		return this.right_name;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("二选一赔率表id[").append(getId()).append("],")
			.append("mid(来自500)[").append(getMid()).append("],")
			.append("zid(来自500)[").append(getZid()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("赛事id2[").append(getArrangeid2()).append("],")
			.append("状态1:正常3:删除[").append(getState()).append("],")
			.append("主队胜负情况[").append(getLeft_name()).append("],")
			.append("压主队赔率[").append(getLeft_odds()).append("],")
			.append("压客队赔率[").append(getRight_odds()).append("],")
			.append("客队胜负情况[").append(getRight_name()).append("],")
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
		if(obj instanceof SoccerLeague2choose1odds == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeague2choose1odds other = (SoccerLeague2choose1odds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
