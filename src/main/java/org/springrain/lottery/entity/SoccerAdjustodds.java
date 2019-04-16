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
 * @version  2017-10-10 14:03:43
 * @see org.springrain.lottery.entity.SoccerAdjustodds
 */
@Table(name="soccer_adjustodds")
public class SoccerAdjustodds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "调整足球赔率表";
	public static final String ALIAS_ID = "调整足球赔率表ID";
	public static final String ALIAS_MID = "场次mid";
	public static final String ALIAS_ZID = "场次zid";
	public static final String ALIAS_WIN = "win";
	public static final String ALIAS_FLAT = "flat";
	public static final String ALIAS_LOSE = "lose";
	public static final String ALIAS_RQWIN = "rqwin";
	public static final String ALIAS_RQFLAT = "rqflat";
	public static final String ALIAS_RQLOSE = "rqlose";
	public static final String ALIAS_BALL0 = "ball0";
	public static final String ALIAS_BALL1 = "ball1";
	public static final String ALIAS_BALL2 = "ball2";
	public static final String ALIAS_BALL3 = "ball3";
	public static final String ALIAS_BALL4 = "ball4";
	public static final String ALIAS_BALL5 = "ball5";
	public static final String ALIAS_BALL6 = "ball6";
	public static final String ALIAS_BALL7 = "ball7";
	public static final String ALIAS_SP33 = "sp33";
	public static final String ALIAS_SP31 = "sp31";
	public static final String ALIAS_SP30 = "sp30";
	public static final String ALIAS_SP13 = "sp13";
	public static final String ALIAS_SP11 = "sp11";
	public static final String ALIAS_SP10 = "sp10";
	public static final String ALIAS_SP03 = "sp03";
	public static final String ALIAS_SP01 = "sp01";
	public static final String ALIAS_SP00 = "sp00";
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
	public static final String ALIAS_LEFT_ODDS = "left_odds";
	public static final String ALIAS_RIGHT_ODDS = "right_odds";
    */
	//date formats
	
	//columns START
	/**
	 * 调整足球赔率表ID
	 */
	private java.lang.Integer id;
	/**
	 * 场次mid
	 */
	private java.lang.String mid;
	/**
	 * 场次zid
	 */
	private java.lang.String zid;
	/**
	 * win
	 */
	private java.math.BigDecimal win;
	/**
	 * flat
	 */
	private java.math.BigDecimal flat;
	/**
	 * lose
	 */
	private java.math.BigDecimal lose;
	/**
	 * rqwin
	 */
	private java.math.BigDecimal rqwin;
	/**
	 * rqflat
	 */
	private java.math.BigDecimal rqflat;
	/**
	 * rqlose
	 */
	private java.math.BigDecimal rqlose;
	/**
	 * ball0
	 */
	private java.math.BigDecimal ball0;
	/**
	 * ball1
	 */
	private java.math.BigDecimal ball1;
	/**
	 * ball2
	 */
	private java.math.BigDecimal ball2;
	/**
	 * ball3
	 */
	private java.math.BigDecimal ball3;
	/**
	 * ball4
	 */
	private java.math.BigDecimal ball4;
	/**
	 * ball5
	 */
	private java.math.BigDecimal ball5;
	/**
	 * ball6
	 */
	private java.math.BigDecimal ball6;
	/**
	 * ball7
	 */
	private java.math.BigDecimal ball7;
	/**
	 * sp33
	 */
	private java.math.BigDecimal sp33;
	/**
	 * sp31
	 */
	private java.math.BigDecimal sp31;
	/**
	 * sp30
	 */
	private java.math.BigDecimal sp30;
	/**
	 * sp13
	 */
	private java.math.BigDecimal sp13;
	/**
	 * sp11
	 */
	private java.math.BigDecimal sp11;
	/**
	 * sp10
	 */
	private java.math.BigDecimal sp10;
	/**
	 * sp03
	 */
	private java.math.BigDecimal sp03;
	/**
	 * sp01
	 */
	private java.math.BigDecimal sp01;
	/**
	 * sp00
	 */
	private java.math.BigDecimal sp00;
	/**
	 * win3A
	 */
	private java.math.BigDecimal win3A;
	/**
	 * win10
	 */
	private java.math.BigDecimal win10;
	/**
	 * win20
	 */
	private java.math.BigDecimal win20;
	/**
	 * win21
	 */
	private java.math.BigDecimal win21;
	/**
	 * win30
	 */
	private java.math.BigDecimal win30;
	/**
	 * win31
	 */
	private java.math.BigDecimal win31;
	/**
	 * win32
	 */
	private java.math.BigDecimal win32;
	/**
	 * win40
	 */
	private java.math.BigDecimal win40;
	/**
	 * win41
	 */
	private java.math.BigDecimal win41;
	/**
	 * win42
	 */
	private java.math.BigDecimal win42;
	/**
	 * win50
	 */
	private java.math.BigDecimal win50;
	/**
	 * win51
	 */
	private java.math.BigDecimal win51;
	/**
	 * win52
	 */
	private java.math.BigDecimal win52;
	/**
	 * flat1A
	 */
	private java.math.BigDecimal flat1A;
	/**
	 * flat00
	 */
	private java.math.BigDecimal flat00;
	/**
	 * flat11
	 */
	private java.math.BigDecimal flat11;
	/**
	 * flat22
	 */
	private java.math.BigDecimal flat22;
	/**
	 * flat33
	 */
	private java.math.BigDecimal flat33;
	/**
	 * lose0A
	 */
	private java.math.BigDecimal lose0A;
	/**
	 * lose01
	 */
	private java.math.BigDecimal lose01;
	/**
	 * lose02
	 */
	private java.math.BigDecimal lose02;
	/**
	 * lose12
	 */
	private java.math.BigDecimal lose12;
	/**
	 * lose03
	 */
	private java.math.BigDecimal lose03;
	/**
	 * lose13
	 */
	private java.math.BigDecimal lose13;
	/**
	 * lose23
	 */
	private java.math.BigDecimal lose23;
	/**
	 * lose04
	 */
	private java.math.BigDecimal lose04;
	/**
	 * lose14
	 */
	private java.math.BigDecimal lose14;
	/**
	 * lose24
	 */
	private java.math.BigDecimal lose24;
	/**
	 * lose05
	 */
	private java.math.BigDecimal lose05;
	/**
	 * lose15
	 */
	private java.math.BigDecimal lose15;
	/**
	 * lose25
	 */
	private java.math.BigDecimal lose25;
	/**
	 * left_odds
	 */
	private java.math.BigDecimal left_odds;
	/**
	 * right_odds
	 */
	private java.math.BigDecimal right_odds;
	//columns END 数据库字段结束
	
	/**
	 * 赛事名
	 */
	private java.lang.String matchname;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 主队名
	 */
	private java.lang.String leftteamname;
	/**
	 * 客队名
	 */
	private java.lang.String rightteamname;
	
	private java.lang.String num;
	
	/**
	 * 截止投注时间
	 */
	private java.util.Date endtime;
	
	//concstructor

	
	
	public SoccerAdjustodds(){
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

	public SoccerAdjustodds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 调整足球赔率表ID
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 调整足球赔率表ID
	 */
	@Id
     @WhereSQL(sql="id=:SoccerAdjustodds_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 场次mid
		 */
	public void setMid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mid = value;
	}
	
	
	
	/**
	 * 场次mid
	 */
     @WhereSQL(sql="mid=:SoccerAdjustodds_mid")
	public java.lang.String getMid() {
		return this.mid;
	}
		/**
		 * 场次zid
		 */
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
	
	
	/**
	 * 场次zid
	 */
     @WhereSQL(sql="zid=:SoccerAdjustodds_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
		/**
		 * win
		 */
	public void setWin(java.math.BigDecimal value) {
		this.win = value;
	}
	
	
	
	/**
	 * win
	 */
     @WhereSQL(sql="win=:SoccerAdjustodds_win")
	public java.math.BigDecimal getWin() {
		return this.win;
	}
		/**
		 * flat
		 */
	public void setFlat(java.math.BigDecimal value) {
		this.flat = value;
	}
	
	
	
	/**
	 * flat
	 */
     @WhereSQL(sql="flat=:SoccerAdjustodds_flat")
	public java.math.BigDecimal getFlat() {
		return this.flat;
	}
		/**
		 * lose
		 */
	public void setLose(java.math.BigDecimal value) {
		this.lose = value;
	}
	
	
	
	/**
	 * lose
	 */
     @WhereSQL(sql="lose=:SoccerAdjustodds_lose")
	public java.math.BigDecimal getLose() {
		return this.lose;
	}
		/**
		 * rqwin
		 */
	public void setRqwin(java.math.BigDecimal value) {
		this.rqwin = value;
	}
	
	
	
	/**
	 * rqwin
	 */
     @WhereSQL(sql="rqwin=:SoccerAdjustodds_rqwin")
	public java.math.BigDecimal getRqwin() {
		return this.rqwin;
	}
		/**
		 * rqflat
		 */
	public void setRqflat(java.math.BigDecimal value) {
		this.rqflat = value;
	}
	
	
	
	/**
	 * rqflat
	 */
     @WhereSQL(sql="rqflat=:SoccerAdjustodds_rqflat")
	public java.math.BigDecimal getRqflat() {
		return this.rqflat;
	}
		/**
		 * rqlose
		 */
	public void setRqlose(java.math.BigDecimal value) {
		this.rqlose = value;
	}
	
	
	
	/**
	 * rqlose
	 */
     @WhereSQL(sql="rqlose=:SoccerAdjustodds_rqlose")
	public java.math.BigDecimal getRqlose() {
		return this.rqlose;
	}
		/**
		 * ball0
		 */
	public void setBall0(java.math.BigDecimal value) {
		this.ball0 = value;
	}
	
	
	
	/**
	 * ball0
	 */
     @WhereSQL(sql="ball0=:SoccerAdjustodds_ball0")
	public java.math.BigDecimal getBall0() {
		return this.ball0;
	}
		/**
		 * ball1
		 */
	public void setBall1(java.math.BigDecimal value) {
		this.ball1 = value;
	}
	
	
	
	/**
	 * ball1
	 */
     @WhereSQL(sql="ball1=:SoccerAdjustodds_ball1")
	public java.math.BigDecimal getBall1() {
		return this.ball1;
	}
		/**
		 * ball2
		 */
	public void setBall2(java.math.BigDecimal value) {
		this.ball2 = value;
	}
	
	
	
	/**
	 * ball2
	 */
     @WhereSQL(sql="ball2=:SoccerAdjustodds_ball2")
	public java.math.BigDecimal getBall2() {
		return this.ball2;
	}
		/**
		 * ball3
		 */
	public void setBall3(java.math.BigDecimal value) {
		this.ball3 = value;
	}
	
	
	
	/**
	 * ball3
	 */
     @WhereSQL(sql="ball3=:SoccerAdjustodds_ball3")
	public java.math.BigDecimal getBall3() {
		return this.ball3;
	}
		/**
		 * ball4
		 */
	public void setBall4(java.math.BigDecimal value) {
		this.ball4 = value;
	}
	
	
	
	/**
	 * ball4
	 */
     @WhereSQL(sql="ball4=:SoccerAdjustodds_ball4")
	public java.math.BigDecimal getBall4() {
		return this.ball4;
	}
		/**
		 * ball5
		 */
	public void setBall5(java.math.BigDecimal value) {
		this.ball5 = value;
	}
	
	
	
	/**
	 * ball5
	 */
     @WhereSQL(sql="ball5=:SoccerAdjustodds_ball5")
	public java.math.BigDecimal getBall5() {
		return this.ball5;
	}
		/**
		 * ball6
		 */
	public void setBall6(java.math.BigDecimal value) {
		this.ball6 = value;
	}
	
	
	
	/**
	 * ball6
	 */
     @WhereSQL(sql="ball6=:SoccerAdjustodds_ball6")
	public java.math.BigDecimal getBall6() {
		return this.ball6;
	}
		/**
		 * ball7
		 */
	public void setBall7(java.math.BigDecimal value) {
		this.ball7 = value;
	}
	
	
	
	/**
	 * ball7
	 */
     @WhereSQL(sql="ball7=:SoccerAdjustodds_ball7")
	public java.math.BigDecimal getBall7() {
		return this.ball7;
	}
		/**
		 * sp33
		 */
	public void setSp33(java.math.BigDecimal value) {
		this.sp33 = value;
	}
	
	
	
	/**
	 * sp33
	 */
     @WhereSQL(sql="sp33=:SoccerAdjustodds_sp33")
	public java.math.BigDecimal getSp33() {
		return this.sp33;
	}
		/**
		 * sp31
		 */
	public void setSp31(java.math.BigDecimal value) {
		this.sp31 = value;
	}
	
	
	
	/**
	 * sp31
	 */
     @WhereSQL(sql="sp31=:SoccerAdjustodds_sp31")
	public java.math.BigDecimal getSp31() {
		return this.sp31;
	}
		/**
		 * sp30
		 */
	public void setSp30(java.math.BigDecimal value) {
		this.sp30 = value;
	}
	
	
	
	/**
	 * sp30
	 */
     @WhereSQL(sql="sp30=:SoccerAdjustodds_sp30")
	public java.math.BigDecimal getSp30() {
		return this.sp30;
	}
		/**
		 * sp13
		 */
	public void setSp13(java.math.BigDecimal value) {
		this.sp13 = value;
	}
	
	
	
	/**
	 * sp13
	 */
     @WhereSQL(sql="sp13=:SoccerAdjustodds_sp13")
	public java.math.BigDecimal getSp13() {
		return this.sp13;
	}
		/**
		 * sp11
		 */
	public void setSp11(java.math.BigDecimal value) {
		this.sp11 = value;
	}
	
	
	
	/**
	 * sp11
	 */
     @WhereSQL(sql="sp11=:SoccerAdjustodds_sp11")
	public java.math.BigDecimal getSp11() {
		return this.sp11;
	}
		/**
		 * sp10
		 */
	public void setSp10(java.math.BigDecimal value) {
		this.sp10 = value;
	}
	
	
	
	/**
	 * sp10
	 */
     @WhereSQL(sql="sp10=:SoccerAdjustodds_sp10")
	public java.math.BigDecimal getSp10() {
		return this.sp10;
	}
		/**
		 * sp03
		 */
	public void setSp03(java.math.BigDecimal value) {
		this.sp03 = value;
	}
	
	
	
	/**
	 * sp03
	 */
     @WhereSQL(sql="sp03=:SoccerAdjustodds_sp03")
	public java.math.BigDecimal getSp03() {
		return this.sp03;
	}
		/**
		 * sp01
		 */
	public void setSp01(java.math.BigDecimal value) {
		this.sp01 = value;
	}
	
	
	
	/**
	 * sp01
	 */
     @WhereSQL(sql="sp01=:SoccerAdjustodds_sp01")
	public java.math.BigDecimal getSp01() {
		return this.sp01;
	}
		/**
		 * sp00
		 */
	public void setSp00(java.math.BigDecimal value) {
		this.sp00 = value;
	}
	
	
	
	/**
	 * sp00
	 */
     @WhereSQL(sql="sp00=:SoccerAdjustodds_sp00")
	public java.math.BigDecimal getSp00() {
		return this.sp00;
	}
		/**
		 * win3A
		 */
	public void setWin3A(java.math.BigDecimal value) {
		this.win3A = value;
	}
	
	
	
	/**
	 * win3A
	 */
     @WhereSQL(sql="win3A=:SoccerAdjustodds_win3A")
	public java.math.BigDecimal getWin3A() {
		return this.win3A;
	}
		/**
		 * win10
		 */
	public void setWin10(java.math.BigDecimal value) {
		this.win10 = value;
	}
	
	
	
	/**
	 * win10
	 */
     @WhereSQL(sql="win10=:SoccerAdjustodds_win10")
	public java.math.BigDecimal getWin10() {
		return this.win10;
	}
		/**
		 * win20
		 */
	public void setWin20(java.math.BigDecimal value) {
		this.win20 = value;
	}
	
	
	
	/**
	 * win20
	 */
     @WhereSQL(sql="win20=:SoccerAdjustodds_win20")
	public java.math.BigDecimal getWin20() {
		return this.win20;
	}
		/**
		 * win21
		 */
	public void setWin21(java.math.BigDecimal value) {
		this.win21 = value;
	}
	
	
	
	/**
	 * win21
	 */
     @WhereSQL(sql="win21=:SoccerAdjustodds_win21")
	public java.math.BigDecimal getWin21() {
		return this.win21;
	}
		/**
		 * win30
		 */
	public void setWin30(java.math.BigDecimal value) {
		this.win30 = value;
	}
	
	
	
	/**
	 * win30
	 */
     @WhereSQL(sql="win30=:SoccerAdjustodds_win30")
	public java.math.BigDecimal getWin30() {
		return this.win30;
	}
		/**
		 * win31
		 */
	public void setWin31(java.math.BigDecimal value) {
		this.win31 = value;
	}
	
	
	
	/**
	 * win31
	 */
     @WhereSQL(sql="win31=:SoccerAdjustodds_win31")
	public java.math.BigDecimal getWin31() {
		return this.win31;
	}
		/**
		 * win32
		 */
	public void setWin32(java.math.BigDecimal value) {
		this.win32 = value;
	}
	
	
	
	/**
	 * win32
	 */
     @WhereSQL(sql="win32=:SoccerAdjustodds_win32")
	public java.math.BigDecimal getWin32() {
		return this.win32;
	}
		/**
		 * win40
		 */
	public void setWin40(java.math.BigDecimal value) {
		this.win40 = value;
	}
	
	
	
	/**
	 * win40
	 */
     @WhereSQL(sql="win40=:SoccerAdjustodds_win40")
	public java.math.BigDecimal getWin40() {
		return this.win40;
	}
		/**
		 * win41
		 */
	public void setWin41(java.math.BigDecimal value) {
		this.win41 = value;
	}
	
	
	
	/**
	 * win41
	 */
     @WhereSQL(sql="win41=:SoccerAdjustodds_win41")
	public java.math.BigDecimal getWin41() {
		return this.win41;
	}
		/**
		 * win42
		 */
	public void setWin42(java.math.BigDecimal value) {
		this.win42 = value;
	}
	
	
	
	/**
	 * win42
	 */
     @WhereSQL(sql="win42=:SoccerAdjustodds_win42")
	public java.math.BigDecimal getWin42() {
		return this.win42;
	}
		/**
		 * win50
		 */
	public void setWin50(java.math.BigDecimal value) {
		this.win50 = value;
	}
	
	
	
	/**
	 * win50
	 */
     @WhereSQL(sql="win50=:SoccerAdjustodds_win50")
	public java.math.BigDecimal getWin50() {
		return this.win50;
	}
		/**
		 * win51
		 */
	public void setWin51(java.math.BigDecimal value) {
		this.win51 = value;
	}
	
	
	
	/**
	 * win51
	 */
     @WhereSQL(sql="win51=:SoccerAdjustodds_win51")
	public java.math.BigDecimal getWin51() {
		return this.win51;
	}
		/**
		 * win52
		 */
	public void setWin52(java.math.BigDecimal value) {
		this.win52 = value;
	}
	
	
	
	/**
	 * win52
	 */
     @WhereSQL(sql="win52=:SoccerAdjustodds_win52")
	public java.math.BigDecimal getWin52() {
		return this.win52;
	}
		/**
		 * flat1A
		 */
	public void setFlat1A(java.math.BigDecimal value) {
		this.flat1A = value;
	}
	
	
	
	/**
	 * flat1A
	 */
     @WhereSQL(sql="flat1A=:SoccerAdjustodds_flat1A")
	public java.math.BigDecimal getFlat1A() {
		return this.flat1A;
	}
		/**
		 * flat00
		 */
	public void setFlat00(java.math.BigDecimal value) {
		this.flat00 = value;
	}
	
	
	
	/**
	 * flat00
	 */
     @WhereSQL(sql="flat00=:SoccerAdjustodds_flat00")
	public java.math.BigDecimal getFlat00() {
		return this.flat00;
	}
		/**
		 * flat11
		 */
	public void setFlat11(java.math.BigDecimal value) {
		this.flat11 = value;
	}
	
	
	
	/**
	 * flat11
	 */
     @WhereSQL(sql="flat11=:SoccerAdjustodds_flat11")
	public java.math.BigDecimal getFlat11() {
		return this.flat11;
	}
		/**
		 * flat22
		 */
	public void setFlat22(java.math.BigDecimal value) {
		this.flat22 = value;
	}
	
	
	
	/**
	 * flat22
	 */
     @WhereSQL(sql="flat22=:SoccerAdjustodds_flat22")
	public java.math.BigDecimal getFlat22() {
		return this.flat22;
	}
		/**
		 * flat33
		 */
	public void setFlat33(java.math.BigDecimal value) {
		this.flat33 = value;
	}
	
	
	
	/**
	 * flat33
	 */
     @WhereSQL(sql="flat33=:SoccerAdjustodds_flat33")
	public java.math.BigDecimal getFlat33() {
		return this.flat33;
	}
		/**
		 * lose0A
		 */
	public void setLose0A(java.math.BigDecimal value) {
		this.lose0A = value;
	}
	
	
	
	/**
	 * lose0A
	 */
     @WhereSQL(sql="lose0A=:SoccerAdjustodds_lose0A")
	public java.math.BigDecimal getLose0A() {
		return this.lose0A;
	}
		/**
		 * lose01
		 */
	public void setLose01(java.math.BigDecimal value) {
		this.lose01 = value;
	}
	
	
	
	/**
	 * lose01
	 */
     @WhereSQL(sql="lose01=:SoccerAdjustodds_lose01")
	public java.math.BigDecimal getLose01() {
		return this.lose01;
	}
		/**
		 * lose02
		 */
	public void setLose02(java.math.BigDecimal value) {
		this.lose02 = value;
	}
	
	
	
	/**
	 * lose02
	 */
     @WhereSQL(sql="lose02=:SoccerAdjustodds_lose02")
	public java.math.BigDecimal getLose02() {
		return this.lose02;
	}
		/**
		 * lose12
		 */
	public void setLose12(java.math.BigDecimal value) {
		this.lose12 = value;
	}
	
	
	
	/**
	 * lose12
	 */
     @WhereSQL(sql="lose12=:SoccerAdjustodds_lose12")
	public java.math.BigDecimal getLose12() {
		return this.lose12;
	}
		/**
		 * lose03
		 */
	public void setLose03(java.math.BigDecimal value) {
		this.lose03 = value;
	}
	
	
	
	/**
	 * lose03
	 */
     @WhereSQL(sql="lose03=:SoccerAdjustodds_lose03")
	public java.math.BigDecimal getLose03() {
		return this.lose03;
	}
		/**
		 * lose13
		 */
	public void setLose13(java.math.BigDecimal value) {
		this.lose13 = value;
	}
	
	
	
	/**
	 * lose13
	 */
     @WhereSQL(sql="lose13=:SoccerAdjustodds_lose13")
	public java.math.BigDecimal getLose13() {
		return this.lose13;
	}
		/**
		 * lose23
		 */
	public void setLose23(java.math.BigDecimal value) {
		this.lose23 = value;
	}
	
	
	
	/**
	 * lose23
	 */
     @WhereSQL(sql="lose23=:SoccerAdjustodds_lose23")
	public java.math.BigDecimal getLose23() {
		return this.lose23;
	}
		/**
		 * lose04
		 */
	public void setLose04(java.math.BigDecimal value) {
		this.lose04 = value;
	}
	
	
	
	/**
	 * lose04
	 */
     @WhereSQL(sql="lose04=:SoccerAdjustodds_lose04")
	public java.math.BigDecimal getLose04() {
		return this.lose04;
	}
		/**
		 * lose14
		 */
	public void setLose14(java.math.BigDecimal value) {
		this.lose14 = value;
	}
	
	
	
	/**
	 * lose14
	 */
     @WhereSQL(sql="lose14=:SoccerAdjustodds_lose14")
	public java.math.BigDecimal getLose14() {
		return this.lose14;
	}
		/**
		 * lose24
		 */
	public void setLose24(java.math.BigDecimal value) {
		this.lose24 = value;
	}
	
	
	
	/**
	 * lose24
	 */
     @WhereSQL(sql="lose24=:SoccerAdjustodds_lose24")
	public java.math.BigDecimal getLose24() {
		return this.lose24;
	}
		/**
		 * lose05
		 */
	public void setLose05(java.math.BigDecimal value) {
		this.lose05 = value;
	}
	
	
	
	/**
	 * lose05
	 */
     @WhereSQL(sql="lose05=:SoccerAdjustodds_lose05")
	public java.math.BigDecimal getLose05() {
		return this.lose05;
	}
		/**
		 * lose15
		 */
	public void setLose15(java.math.BigDecimal value) {
		this.lose15 = value;
	}
	
	
	
	/**
	 * lose15
	 */
     @WhereSQL(sql="lose15=:SoccerAdjustodds_lose15")
	public java.math.BigDecimal getLose15() {
		return this.lose15;
	}
		/**
		 * lose25
		 */
	public void setLose25(java.math.BigDecimal value) {
		this.lose25 = value;
	}
	
	
	
	/**
	 * lose25
	 */
     @WhereSQL(sql="lose25=:SoccerAdjustodds_lose25")
	public java.math.BigDecimal getLose25() {
		return this.lose25;
	}
		/**
		 * left_odds
		 */
	public void setLeft_odds(java.math.BigDecimal value) {
		this.left_odds = value;
	}
	
	
	
	/**
	 * left_odds
	 */
     @WhereSQL(sql="left_odds=:SoccerAdjustodds_left_odds")
	public java.math.BigDecimal getLeft_odds() {
		return this.left_odds;
	}
		/**
		 * right_odds
		 */
	public void setRight_odds(java.math.BigDecimal value) {
		this.right_odds = value;
	}
	
	
	
	/**
	 * right_odds
	 */
     @WhereSQL(sql="right_odds=:SoccerAdjustodds_right_odds")
	public java.math.BigDecimal getRight_odds() {
		return this.right_odds;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("调整足球赔率表ID[").append(getId()).append("],")
			.append("场次mid[").append(getMid()).append("],")
			.append("场次zid[").append(getZid()).append("],")
			.append("win[").append(getWin()).append("],")
			.append("flat[").append(getFlat()).append("],")
			.append("lose[").append(getLose()).append("],")
			.append("rqwin[").append(getRqwin()).append("],")
			.append("rqflat[").append(getRqflat()).append("],")
			.append("rqlose[").append(getRqlose()).append("],")
			.append("ball0[").append(getBall0()).append("],")
			.append("ball1[").append(getBall1()).append("],")
			.append("ball2[").append(getBall2()).append("],")
			.append("ball3[").append(getBall3()).append("],")
			.append("ball4[").append(getBall4()).append("],")
			.append("ball5[").append(getBall5()).append("],")
			.append("ball6[").append(getBall6()).append("],")
			.append("ball7[").append(getBall7()).append("],")
			.append("sp33[").append(getSp33()).append("],")
			.append("sp31[").append(getSp31()).append("],")
			.append("sp30[").append(getSp30()).append("],")
			.append("sp13[").append(getSp13()).append("],")
			.append("sp11[").append(getSp11()).append("],")
			.append("sp10[").append(getSp10()).append("],")
			.append("sp03[").append(getSp03()).append("],")
			.append("sp01[").append(getSp01()).append("],")
			.append("sp00[").append(getSp00()).append("],")
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
			.append("left_odds[").append(getLeft_odds()).append("],")
			.append("right_odds[").append(getRight_odds()).append("],")
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
		if(obj instanceof SoccerAdjustodds == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerAdjustodds other = (SoccerAdjustodds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
