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
 * @version  2017-08-23 14:01:58
 * @see org.springrain.lottery.entity.SoccerLeagueHalfallodds
 */
@Table(name="soccer_league_halfallodds")
public class SoccerLeagueHalfallodds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueHalfallodds";
	public static final String ALIAS_ID = "半全场赔率表id";
	public static final String ALIAS_MID = "mid(来自500)";
	public static final String ALIAS_ZID = "zid(来自500)";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_ARRANGEID2 = "赛事id2(来自500)";
	public static final String ALIAS_STATE = "状态1:正常3:删除";
	public static final String ALIAS_SP33 = "胜-胜";
	public static final String ALIAS_SP31 = "胜-平";
	public static final String ALIAS_SP30 = "胜-负";
	public static final String ALIAS_SP13 = "平-胜";
	public static final String ALIAS_SP11 = "平-平";
	public static final String ALIAS_SP10 = "平-负";
	public static final String ALIAS_SP03 = "负-胜";
	public static final String ALIAS_SP01 = "负-平";
	public static final String ALIAS_SP00 = "负-负";
    */
	//date formats
	
	//columns START
	/**
	 * 半全场赔率表id
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
	 * 赛事id2(来自500)
	 */
	private java.lang.String arrangeid2;
	/**
	 * 状态1:正常3:删除
	 */
	private java.lang.Integer state;
	/**
	 * 胜-胜
	 */
	private java.lang.Double sp33;
	/**
	 * 胜-平
	 */
	private java.lang.Double sp31;
	/**
	 * 胜-负
	 */
	private java.lang.Double sp30;
	/**
	 * 平-胜
	 */
	private java.lang.Double sp13;
	/**
	 * 平-平
	 */
	private java.lang.Double sp11;
	/**
	 * 平-负
	 */
	private java.lang.Double sp10;
	/**
	 * 负-胜
	 */
	private java.lang.Double sp03;
	/**
	 * 负-平
	 */
	private java.lang.Double sp01;
	/**
	 * 负-负
	 */
	private java.lang.Double sp00;
	
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
	@WhereSQL(sql="datetime=:SoccerLeagueHalfallodds_datetime")
	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public SoccerLeagueHalfallodds(){
	}

	public SoccerLeagueHalfallodds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 半全场赔率表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 半全场赔率表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueHalfallodds_id")
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
     @WhereSQL(sql="mid=:SoccerLeagueHalfallodds_mid")
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
     @WhereSQL(sql="zid=:SoccerLeagueHalfallodds_zid")
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
     @WhereSQL(sql="playmethodid=:SoccerLeagueHalfallodds_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
		/**
		 * 赛事id2(来自500)
		 */
	public void setArrangeid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.arrangeid2 = value;
	}
	
	
	
	/**
	 * 赛事id2(来自500)
	 */
     @WhereSQL(sql="arrangeid2=:SoccerLeagueHalfallodds_arrangeid2")
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
     @WhereSQL(sql="state=:SoccerLeagueHalfallodds_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/**
		 * 胜-胜
		 */
	public void setSp33(java.lang.Double value) {
		this.sp33 = value;
	}
	
	
	
	/**
	 * 胜-胜
	 */
     @WhereSQL(sql="sp33=:SoccerLeagueHalfallodds_sp33")
	public java.lang.Double getSp33() {
		return this.sp33;
	}
		/**
		 * 胜-平
		 */
	public void setSp31(java.lang.Double value) {
		this.sp31 = value;
	}
	
	
	
	/**
	 * 胜-平
	 */
     @WhereSQL(sql="sp31=:SoccerLeagueHalfallodds_sp31")
	public java.lang.Double getSp31() {
		return this.sp31;
	}
		/**
		 * 胜-负
		 */
	public void setSp30(java.lang.Double value) {
		this.sp30 = value;
	}
	
	
	
	/**
	 * 胜-负
	 */
     @WhereSQL(sql="sp30=:SoccerLeagueHalfallodds_sp30")
	public java.lang.Double getSp30() {
		return this.sp30;
	}
		/**
		 * 平-胜
		 */
	public void setSp13(java.lang.Double value) {
		this.sp13 = value;
	}
	
	
	
	/**
	 * 平-胜
	 */
     @WhereSQL(sql="sp13=:SoccerLeagueHalfallodds_sp13")
	public java.lang.Double getSp13() {
		return this.sp13;
	}
		/**
		 * 平-平
		 */
	public void setSp11(java.lang.Double value) {
		this.sp11 = value;
	}
	
	
	
	/**
	 * 平-平
	 */
     @WhereSQL(sql="sp11=:SoccerLeagueHalfallodds_sp11")
	public java.lang.Double getSp11() {
		return this.sp11;
	}
		/**
		 * 平-负
		 */
	public void setSp10(java.lang.Double value) {
		this.sp10 = value;
	}
	
	
	
	/**
	 * 平-负
	 */
     @WhereSQL(sql="sp10=:SoccerLeagueHalfallodds_sp10")
	public java.lang.Double getSp10() {
		return this.sp10;
	}
		/**
		 * 负-胜
		 */
	public void setSp03(java.lang.Double value) {
		this.sp03 = value;
	}
	
	
	
	/**
	 * 负-胜
	 */
     @WhereSQL(sql="sp03=:SoccerLeagueHalfallodds_sp03")
	public java.lang.Double getSp03() {
		return this.sp03;
	}
		/**
		 * 负-平
		 */
	public void setSp01(java.lang.Double value) {
		this.sp01 = value;
	}
	
	
	
	/**
	 * 负-平
	 */
     @WhereSQL(sql="sp01=:SoccerLeagueHalfallodds_sp01")
	public java.lang.Double getSp01() {
		return this.sp01;
	}
		/**
		 * 负-负
		 */
	public void setSp00(java.lang.Double value) {
		this.sp00 = value;
	}
	
	
	
	/**
	 * 负-负
	 */
     @WhereSQL(sql="sp00=:SoccerLeagueHalfallodds_sp00")
	public java.lang.Double getSp00() {
		return this.sp00;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("半全场赔率表id[").append(getId()).append("],")
			.append("mid(来自500)[").append(getMid()).append("],")
			.append("zid(来自500)[").append(getZid()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("赛事id2(来自500)[").append(getArrangeid2()).append("],")
			.append("状态1:正常3:删除[").append(getState()).append("],")
			.append("胜-胜[").append(getSp33()).append("],")
			.append("胜-平[").append(getSp31()).append("],")
			.append("胜-负[").append(getSp30()).append("],")
			.append("平-胜[").append(getSp13()).append("],")
			.append("平-平[").append(getSp11()).append("],")
			.append("平-负[").append(getSp10()).append("],")
			.append("负-胜[").append(getSp03()).append("],")
			.append("负-平[").append(getSp01()).append("],")
			.append("负-负[").append(getSp00()).append("],")
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
		if(obj instanceof SoccerLeagueHalfallodds == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueHalfallodds other = (SoccerLeagueHalfallodds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
