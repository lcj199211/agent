package org.springrain.lottery.entity;

import java.io.Serializable;

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
 * @version  2017-08-23 09:03:03
 * @see org.springrain.lottery.entity.SoccerLeagueScoreodds
 */
@Table(name="soccer_league_scoreodds")
public class SoccerLeagueScoreodds  extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueScoreodds";
	public static final String ALIAS_ID = "比分赔率表 id";
	public static final String ALIAS_MID = "mid(来自500)";
	public static final String ALIAS_ZID = "zid(来自500)";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_ARRANGEID2 = "赛事id2(来自500)";
	public static final String ALIAS_STATE = "状态1:正常 3:删除";
	public static final String ALIAS_WIN3A = "win3A";
	public static final String ALIAS_WIN10 = "win10";
	public static final String ALIAS_WIN20 = "win20";
	public static final String ALIAS_WIN21 = "win21";
	public static final String ALIAS_WIN30 = "win30";
	public static final String ALIAS_WIN31 = "win31";
	public static final String ALIAS_WIN32 = "win32";
	public static final String ALIAS_WIN40 = "win40";
	public static final String ALIAS_WIN41 = "win41";
	public static final String ALIAS_WIN42 = "win42";
	public static final String ALIAS_WIN50 = "win50";
	public static final String ALIAS_WIN51 = "win51";
	public static final String ALIAS_WIN52 = "win52";
	public static final String ALIAS_FLAT1A = "flat1A";
	public static final String ALIAS_FLAT00 = "flat00";
	public static final String ALIAS_FLAT11 = "flat11";
	public static final String ALIAS_FLAT22 = "flat22";
	public static final String ALIAS_FLAT33 = "flat33";
	public static final String ALIAS_LOSE0A = "lose0A";
	public static final String ALIAS_LOSE01 = "lose01";
	public static final String ALIAS_LOSE02 = "lose02";
	public static final String ALIAS_LOSE12 = "lose12";
	public static final String ALIAS_LOSE03 = "lose03";
	public static final String ALIAS_LOSE13 = "lose13";
	public static final String ALIAS_LOSE23 = "lose23";
	public static final String ALIAS_LOSE04 = "lose04";
	public static final String ALIAS_LOSE14 = "lose14";
	public static final String ALIAS_LOSE24 = "lose24";
	public static final String ALIAS_LOSE05 = "lose05";
	public static final String ALIAS_LOSE15 = "lose15";
	public static final String ALIAS_LOSE25 = "lose25";
    */
	//date formats
	
	//columns START
	/**
	 * 比分赔率表 id
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
	 * 状态1:正常 3:删除
	 */
	private java.lang.Integer state;
	/**
	 * win3A
	 */
	private java.lang.Double win3A;
	/**
	 * win10
	 */
	private java.lang.Double win10;
	/**
	 * win20
	 */
	private java.lang.Double win20;
	/**
	 * win21
	 */
	private java.lang.Double win21;
	/**
	 * win30
	 */
	private java.lang.Double win30;
	/**
	 * win31
	 */
	private java.lang.Double win31;
	/**
	 * win32
	 */
	private java.lang.Double win32;
	/**
	 * win40
	 */
	private java.lang.Double win40;
	/**
	 * win41
	 */
	private java.lang.Double win41;
	/**
	 * win42
	 */
	private java.lang.Double win42;
	/**
	 * win50
	 */
	private java.lang.Double win50;
	/**
	 * win51
	 */
	private java.lang.Double win51;
	/**
	 * win52
	 */
	private java.lang.Double win52;
	/**
	 * flat1A
	 */
	private java.lang.Double flat1A;
	/**
	 * flat00
	 */
	private java.lang.Double flat00;
	/**
	 * flat11
	 */
	private java.lang.Double flat11;
	/**
	 * flat22
	 */
	private java.lang.Double flat22;
	/**
	 * flat33
	 */
	private java.lang.Double flat33;
	/**
	 * lose0A
	 */
	private java.lang.Double lose0A;
	/**
	 * lose01
	 */
	private java.lang.Double lose01;
	/**
	 * lose02
	 */
	private java.lang.Double lose02;
	/**
	 * lose12
	 */
	private java.lang.Double lose12;
	/**
	 * lose03
	 */
	private java.lang.Double lose03;
	/**
	 * lose13
	 */
	private java.lang.Double lose13;
	/**
	 * lose23
	 */
	private java.lang.Double lose23;
	/**
	 * lose04
	 */
	private java.lang.Double lose04;
	/**
	 * lose14
	 */
	private java.lang.Double lose14;
	/**
	 * lose24
	 */
	private java.lang.Double lose24;
	/**
	 * lose05
	 */
	private java.lang.Double lose05;
	/**
	 * lose15
	 */
	private java.lang.Double lose15;
	/**
	 * lose25
	 */
	private java.lang.Double lose25;
	
	private java.util.Date datetime;
	//columns END 数据库字段结束
	
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
	 * 开赛时间
	 */
	private java.util.Date starttime;
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
	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
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
	@WhereSQL(sql="datetime=:SoccerLeagueScoreodds_datetime")
	public java.util.Date getDatetime() {
		return datetime;
	}

	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	public SoccerLeagueScoreodds(){
	}

	public SoccerLeagueScoreodds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 比分赔率表 id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 比分赔率表 id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueScoreodds_id")
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
     @WhereSQL(sql="mid=:SoccerLeagueScoreodds_mid")
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
     @WhereSQL(sql="zid=:SoccerLeagueScoreodds_zid")
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
     @WhereSQL(sql="playmethodid=:SoccerLeagueScoreodds_playmethodid")
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
     @WhereSQL(sql="arrangeid2=:SoccerLeagueScoreodds_arrangeid2")
	public java.lang.String getArrangeid2() {
		return this.arrangeid2;
	}
		/**
		 * 状态1:正常 3:删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 状态1:正常 3:删除
	 */
     @WhereSQL(sql="state=:SoccerLeagueScoreodds_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/**
		 * win3A
		 */
	public void setWin3A(java.lang.Double value) {
		this.win3A = value;
	}
	
	
	
	/**
	 * win3A
	 */
     @WhereSQL(sql="win3A=:SoccerLeagueScoreodds_win3A")
	public java.lang.Double getWin3A() {
		return this.win3A;
	}
		/**
		 * win10
		 */
	public void setWin10(java.lang.Double value) {
		this.win10 = value;
	}
	
	
	
	/**
	 * win10
	 */
     @WhereSQL(sql="win10=:SoccerLeagueScoreodds_win10")
	public java.lang.Double getWin10() {
		return this.win10;
	}
		/**
		 * win20
		 */
	public void setWin20(java.lang.Double value) {
		this.win20 = value;
	}
	
	
	
	/**
	 * win20
	 */
     @WhereSQL(sql="win20=:SoccerLeagueScoreodds_win20")
	public java.lang.Double getWin20() {
		return this.win20;
	}
		/**
		 * win21
		 */
	public void setWin21(java.lang.Double value) {
		this.win21 = value;
	}
	
	
	
	/**
	 * win21
	 */
     @WhereSQL(sql="win21=:SoccerLeagueScoreodds_win21")
	public java.lang.Double getWin21() {
		return this.win21;
	}
		/**
		 * win30
		 */
	public void setWin30(java.lang.Double value) {
		this.win30 = value;
	}
	
	
	
	/**
	 * win30
	 */
     @WhereSQL(sql="win30=:SoccerLeagueScoreodds_win30")
	public java.lang.Double getWin30() {
		return this.win30;
	}
		/**
		 * win31
		 */
	public void setWin31(java.lang.Double value) {
		this.win31 = value;
	}
	
	
	
	/**
	 * win31
	 */
     @WhereSQL(sql="win31=:SoccerLeagueScoreodds_win31")
	public java.lang.Double getWin31() {
		return this.win31;
	}
		/**
		 * win32
		 */
	public void setWin32(java.lang.Double value) {
		this.win32 = value;
	}
	
	
	
	/**
	 * win32
	 */
     @WhereSQL(sql="win32=:SoccerLeagueScoreodds_win32")
	public java.lang.Double getWin32() {
		return this.win32;
	}
		/**
		 * win40
		 */
	public void setWin40(java.lang.Double value) {
		this.win40 = value;
	}
	
	
	
	/**
	 * win40
	 */
     @WhereSQL(sql="win40=:SoccerLeagueScoreodds_win40")
	public java.lang.Double getWin40() {
		return this.win40;
	}
		/**
		 * win41
		 */
	public void setWin41(java.lang.Double value) {
		this.win41 = value;
	}
	
	
	
	/**
	 * win41
	 */
     @WhereSQL(sql="win41=:SoccerLeagueScoreodds_win41")
	public java.lang.Double getWin41() {
		return this.win41;
	}
		/**
		 * win42
		 */
	public void setWin42(java.lang.Double value) {
		this.win42 = value;
	}
	
	
	
	/**
	 * win42
	 */
     @WhereSQL(sql="win42=:SoccerLeagueScoreodds_win42")
	public java.lang.Double getWin42() {
		return this.win42;
	}
		/**
		 * win50
		 */
	public void setWin50(java.lang.Double value) {
		this.win50 = value;
	}
	
	
	
	/**
	 * win50
	 */
     @WhereSQL(sql="win50=:SoccerLeagueScoreodds_win50")
	public java.lang.Double getWin50() {
		return this.win50;
	}
		/**
		 * win51
		 */
	public void setWin51(java.lang.Double value) {
		this.win51 = value;
	}
	
	
	
	/**
	 * win51
	 */
     @WhereSQL(sql="win51=:SoccerLeagueScoreodds_win51")
	public java.lang.Double getWin51() {
		return this.win51;
	}
		/**
		 * win52
		 */
	public void setWin52(java.lang.Double value) {
		this.win52 = value;
	}
	
	
	
	/**
	 * win52
	 */
     @WhereSQL(sql="win52=:SoccerLeagueScoreodds_win52")
	public java.lang.Double getWin52() {
		return this.win52;
	}
		/**
		 * flat1A
		 */
	public void setFlat1A(java.lang.Double value) {
		this.flat1A = value;
	}
	
	
	
	/**
	 * flat1A
	 */
     @WhereSQL(sql="flat1A=:SoccerLeagueScoreodds_flat1A")
	public java.lang.Double getFlat1A() {
		return this.flat1A;
	}
		/**
		 * flat00
		 */
	public void setFlat00(java.lang.Double value) {
		this.flat00 = value;
	}
	
	
	
	/**
	 * flat00
	 */
     @WhereSQL(sql="flat00=:SoccerLeagueScoreodds_flat00")
	public java.lang.Double getFlat00() {
		return this.flat00;
	}
		/**
		 * flat11
		 */
	public void setFlat11(java.lang.Double value) {
		this.flat11 = value;
	}
	
	
	
	/**
	 * flat11
	 */
     @WhereSQL(sql="flat11=:SoccerLeagueScoreodds_flat11")
	public java.lang.Double getFlat11() {
		return this.flat11;
	}
		/**
		 * flat22
		 */
	public void setFlat22(java.lang.Double value) {
		this.flat22 = value;
	}
	
	
	
	/**
	 * flat22
	 */
     @WhereSQL(sql="flat22=:SoccerLeagueScoreodds_flat22")
	public java.lang.Double getFlat22() {
		return this.flat22;
	}
		/**
		 * flat33
		 */
	public void setFlat33(java.lang.Double value) {
		this.flat33 = value;
	}
	
	
	
	/**
	 * flat33
	 */
     @WhereSQL(sql="flat33=:SoccerLeagueScoreodds_flat33")
	public java.lang.Double getFlat33() {
		return this.flat33;
	}
		/**
		 * lose0A
		 */
	public void setLose0A(java.lang.Double value) {
		this.lose0A = value;
	}
	
	
	
	/**
	 * lose0A
	 */
     @WhereSQL(sql="lose0A=:SoccerLeagueScoreodds_lose0A")
	public java.lang.Double getLose0A() {
		return this.lose0A;
	}
		/**
		 * lose01
		 */
	public void setLose01(java.lang.Double value) {
		this.lose01 = value;
	}
	
	
	
	/**
	 * lose01
	 */
     @WhereSQL(sql="lose01=:SoccerLeagueScoreodds_lose01")
	public java.lang.Double getLose01() {
		return this.lose01;
	}
		/**
		 * lose02
		 */
	public void setLose02(java.lang.Double value) {
		this.lose02 = value;
	}
	
	
	
	/**
	 * lose02
	 */
     @WhereSQL(sql="lose02=:SoccerLeagueScoreodds_lose02")
	public java.lang.Double getLose02() {
		return this.lose02;
	}
		/**
		 * lose12
		 */
	public void setLose12(java.lang.Double value) {
		this.lose12 = value;
	}
	
	
	
	/**
	 * lose12
	 */
     @WhereSQL(sql="lose12=:SoccerLeagueScoreodds_lose12")
	public java.lang.Double getLose12() {
		return this.lose12;
	}
		/**
		 * lose03
		 */
	public void setLose03(java.lang.Double value) {
		this.lose03 = value;
	}
	
	
	
	/**
	 * lose03
	 */
     @WhereSQL(sql="lose03=:SoccerLeagueScoreodds_lose03")
	public java.lang.Double getLose03() {
		return this.lose03;
	}
		/**
		 * lose13
		 */
	public void setLose13(java.lang.Double value) {
		this.lose13 = value;
	}
	
	
	
	/**
	 * lose13
	 */
     @WhereSQL(sql="lose13=:SoccerLeagueScoreodds_lose13")
	public java.lang.Double getLose13() {
		return this.lose13;
	}
		/**
		 * lose23
		 */
	public void setLose23(java.lang.Double value) {
		this.lose23 = value;
	}
	
	
	
	/**
	 * lose23
	 */
     @WhereSQL(sql="lose23=:SoccerLeagueScoreodds_lose23")
	public java.lang.Double getLose23() {
		return this.lose23;
	}
		/**
		 * lose04
		 */
	public void setLose04(java.lang.Double value) {
		this.lose04 = value;
	}
	
	
	
	/**
	 * lose04
	 */
     @WhereSQL(sql="lose04=:SoccerLeagueScoreodds_lose04")
	public java.lang.Double getLose04() {
		return this.lose04;
	}
		/**
		 * lose14
		 */
	public void setLose14(java.lang.Double value) {
		this.lose14 = value;
	}
	
	
	
	/**
	 * lose14
	 */
     @WhereSQL(sql="lose14=:SoccerLeagueScoreodds_lose14")
	public java.lang.Double getLose14() {
		return this.lose14;
	}
		/**
		 * lose24
		 */
	public void setLose24(java.lang.Double value) {
		this.lose24 = value;
	}
	
	
	
	/**
	 * lose24
	 */
     @WhereSQL(sql="lose24=:SoccerLeagueScoreodds_lose24")
	public java.lang.Double getLose24() {
		return this.lose24;
	}
		/**
		 * lose05
		 */
	public void setLose05(java.lang.Double value) {
		this.lose05 = value;
	}
	
	
	
	/**
	 * lose05
	 */
     @WhereSQL(sql="lose05=:SoccerLeagueScoreodds_lose05")
	public java.lang.Double getLose05() {
		return this.lose05;
	}
		/**
		 * lose15
		 */
	public void setLose15(java.lang.Double value) {
		this.lose15 = value;
	}
	
	
	
	/**
	 * lose15
	 */
     @WhereSQL(sql="lose15=:SoccerLeagueScoreodds_lose15")
	public java.lang.Double getLose15() {
		return this.lose15;
	}
		/**
		 * lose25
		 */
	public void setLose25(java.lang.Double value) {
		this.lose25 = value;
	}
	
	
	
	/**
	 * lose25
	 */
     @WhereSQL(sql="lose25=:SoccerLeagueScoreodds_lose25")
	public java.lang.Double getLose25() {
		return this.lose25;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("比分赔率表 id[").append(getId()).append("],")
			.append("mid(来自500)[").append(getMid()).append("],")
			.append("zid(来自500)[").append(getZid()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("赛事id2(来自500)[").append(getArrangeid2()).append("],")
			.append("状态1:正常 3:删除[").append(getState()).append("],")
			.append("win3A[").append(getWin3A()).append("],")
			.append("win10[").append(getWin10()).append("],")
			.append("win20[").append(getWin20()).append("],")
			.append("win21[").append(getWin21()).append("],")
			.append("win30[").append(getWin30()).append("],")
			.append("win31[").append(getWin31()).append("],")
			.append("win32[").append(getWin32()).append("],")
			.append("win40[").append(getWin40()).append("],")
			.append("win41[").append(getWin41()).append("],")
			.append("win42[").append(getWin42()).append("],")
			.append("win50[").append(getWin50()).append("],")
			.append("win51[").append(getWin51()).append("],")
			.append("win52[").append(getWin52()).append("],")
			.append("flat1A[").append(getFlat1A()).append("],")
			.append("flat00[").append(getFlat00()).append("],")
			.append("flat11[").append(getFlat11()).append("],")
			.append("flat22[").append(getFlat22()).append("],")
			.append("flat33[").append(getFlat33()).append("],")
			.append("lose0A[").append(getLose0A()).append("],")
			.append("lose01[").append(getLose01()).append("],")
			.append("lose02[").append(getLose02()).append("],")
			.append("lose12[").append(getLose12()).append("],")
			.append("lose03[").append(getLose03()).append("],")
			.append("lose13[").append(getLose13()).append("],")
			.append("lose23[").append(getLose23()).append("],")
			.append("lose04[").append(getLose04()).append("],")
			.append("lose14[").append(getLose14()).append("],")
			.append("lose24[").append(getLose24()).append("],")
			.append("lose05[").append(getLose05()).append("],")
			.append("lose15[").append(getLose15()).append("],")
			.append("lose25[").append(getLose25()).append("],")
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
		if(obj instanceof SoccerLeagueScoreodds == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueScoreodds other = (SoccerLeagueScoreodds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
