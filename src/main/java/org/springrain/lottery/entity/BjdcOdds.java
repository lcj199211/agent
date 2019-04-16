package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-11-29 15:02:15
 * @see org.springrain.lottery.entity.BjdcOdds
 */
@Table(name="bjdc_odds")
public class BjdcOdds  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BjdcOdds";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_LETPOINTS = "让球胜负平(让球)";
	public static final String ALIAS_RQWIN = "让球胜";
	public static final String ALIAS_RQFLAT = "让球平";
	public static final String ALIAS_RQLOSE = "让球负";
	public static final String ALIAS_SFLETBALL = "胜负(让球)";
	public static final String ALIAS_SFWIN = "胜";
	public static final String ALIAS_SFLOSE = "负";
	public static final String ALIAS_BALL0 = "进0球";
	public static final String ALIAS_BALL1 = "进1球";
	public static final String ALIAS_BALL2 = "进2球";
	public static final String ALIAS_BAL3 = "进3球";
	public static final String ALIAS_BALL4 = "进4球";
	public static final String ALIAS_BALL5 = "进5球";
	public static final String ALIAS_BALL6 = "进6球";
	public static final String ALIAS_BALL7 = "进7+球";
	public static final String ALIAS_TOPSINGLE = "上单";
	public static final String ALIAS_TOPDOUBLE = "上双";
	public static final String ALIAS_DOWNSINGLE = "下单";
	public static final String ALIAS_DOWNDOUBLE = "下双";
	public static final String ALIAS_WIN3A = "胜其他";
	public static final String ALIAS_WIN10 = "1:0";
	public static final String ALIAS_WIN20 = "2:0";
	public static final String ALIAS_WIN21 = "2:1";
	public static final String ALIAS_WIN30 = "3:0";
	public static final String ALIAS_WIN31 = "3:1";
	public static final String ALIAS_WIN32 = "3:2";
	public static final String ALIAS_WIN40 = "4:0";
	public static final String ALIAS_WIN41 = "4:1";
	public static final String ALIAS_WIN42 = "4:2";
	public static final String ALIAS_FLAT1A = "平其他";
	public static final String ALIAS_FLAT00 = "0:0";
	public static final String ALIAS_FLAT11 = "1:1";
	public static final String ALIAS_FLAT22 = "2:2";
	public static final String ALIAS_FLAT33 = "3:3";
	public static final String ALIAS_LOSE1A = "负其他";
	public static final String ALIAS_LOSE01 = "0:1";
	public static final String ALIAS_LOSE02 = "0:2";
	public static final String ALIAS_LOSE12 = "1:2";
	public static final String ALIAS_LOSE03 = "0:3";
	public static final String ALIAS_LOSE13 = "1:3";
	public static final String ALIAS_LOSE23 = "2:3";
	public static final String ALIAS_LOSE04 = "0:4";
	public static final String ALIAS_LOSE14 = "1:4";
	public static final String ALIAS_LOSE24 = "2:4";
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
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * 让球胜负平(让球)
	 */
	private java.lang.String letpoints;
	/**
	 * 让球胜
	 */
	private java.lang.String rqwin;
	/**
	 * 让球平
	 */
	private java.lang.String rqflat;
	/**
	 * 让球负
	 */
	private java.lang.String rqlose;
	/**
	 * 进0球
	 */
	private java.lang.String ball0;
	/**
	 * 进1球
	 */
	private java.lang.String ball1;
	/**
	 * 进2球
	 */
	private java.lang.String ball2;
	/**
	 * 进3球
	 */
	private java.lang.String ball3;
	/**
	 * 进4球
	 */
	private java.lang.String ball4;
	/**
	 * 进5球
	 */
	private java.lang.String ball5;
	/**
	 * 进6球
	 */
	private java.lang.String ball6;
	/**
	 * 进7+球
	 */
	private java.lang.String ball7;
	/**
	 * 上单
	 */
	private java.lang.String topsingle;
	/**
	 * 上双
	 */
	private java.lang.String topdouble;
	/**
	 * 下单
	 */
	private java.lang.String downsingle;
	/**
	 * 下双
	 */
	private java.lang.String downdouble;
	/**
	 * 胜其他
	 */
	private java.lang.String win3A;
	/**
	 * 1:0
	 */
	private java.lang.String win10;
	/**
	 * 2:0
	 */
	private java.lang.String win20;
	/**
	 * 2:1
	 */
	private java.lang.String win21;
	/**
	 * 3:0
	 */
	private java.lang.String win30;
	/**
	 * 3:1
	 */
	private java.lang.String win31;
	/**
	 * 3:2
	 */
	private java.lang.String win32;
	/**
	 * 4:0
	 */
	private java.lang.String win40;
	/**
	 * 4:1
	 */
	private java.lang.String win41;
	/**
	 * 4:2
	 */
	private java.lang.String win42;
	/**
	 * 平其他
	 */
	private java.lang.String flat1A;
	/**
	 * 0:0
	 */
	private java.lang.String flat00;
	/**
	 * 1:1
	 */
	private java.lang.String flat11;
	/**
	 * 2:2
	 */
	private java.lang.String flat22;
	/**
	 * 3:3
	 */
	private java.lang.String flat33;
	/**
	 * 负其他
	 */
	private java.lang.String lose0A;
	/**
	 * 0:1
	 */
	private java.lang.String lose01;
	/**
	 * 0:2
	 */
	private java.lang.String lose02;
	/**
	 * 1:2
	 */
	private java.lang.String lose12;
	/**
	 * 0:3
	 */
	private java.lang.String lose03;
	/**
	 * 1:3
	 */
	private java.lang.String lose13;
	/**
	 * 2:3
	 */
	private java.lang.String lose23;
	/**
	 * 0:4
	 */
	private java.lang.String lose04;
	/**
	 * 1:4
	 */
	private java.lang.String lose14;
	/**
	 * 2:4
	 */
	private java.lang.String lose24;
	/**
	 * 胜-胜
	 */
	private java.lang.String sp33;
	/**
	 * 胜-平
	 */
	private java.lang.String sp31;
	/**
	 * 胜-负
	 */
	private java.lang.String sp30;
	/**
	 * 平-胜
	 */
	private java.lang.String sp13;
	/**
	 * 平-平
	 */
	private java.lang.String sp11;
	/**
	 * 平-负
	 */
	private java.lang.String sp10;
	/**
	 * 负-胜
	 */
	private java.lang.String sp03;
	/**
	 * 负-平
	 */
	private java.lang.String sp01;
	/**
	 * 负-负
	 */
	private java.lang.String sp00;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcOdds(){
	}

	public BjdcOdds(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcOdds_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcOdds_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setLetpoints(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.letpoints = value;
	}
	
     @WhereSQL(sql="letpoints=:BjdcOdds_letpoints")
	public java.lang.String getLetpoints() {
		return this.letpoints;
	}
	public void setRqwin(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rqwin = value;
	}
	
     @WhereSQL(sql="rqwin=:BjdcOdds_rqwin")
	public java.lang.String getRqwin() {
		return this.rqwin;
	}
	public void setRqflat(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rqflat = value;
	}
	
     @WhereSQL(sql="rqflat=:BjdcOdds_rqflat")
	public java.lang.String getRqflat() {
		return this.rqflat;
	}
	public void setRqlose(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rqlose = value;
	}
	
     @WhereSQL(sql="rqlose=:BjdcOdds_rqlose")
	public java.lang.String getRqlose() {
		return this.rqlose;
	}

	public void setBall0(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball0 = value;
	}
	
     @WhereSQL(sql="ball0=:BjdcOdds_ball0")
	public java.lang.String getBall0() {
		return this.ball0;
	}
	public void setBall1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball1 = value;
	}
	
     @WhereSQL(sql="ball1=:BjdcOdds_ball1")
	public java.lang.String getBall1() {
		return this.ball1;
	}
	public void setBall2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball2 = value;
	}
	
     @WhereSQL(sql="ball2=:BjdcOdds_ball2")
	public java.lang.String getBall2() {
		return this.ball2;
	}
	public void setBall3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball3 = value;
	}
	
     @WhereSQL(sql="bal3=:BjdcOdds_ball3")
	public java.lang.String getBall3() {
		return this.ball3;
	}
	public void setBall4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball4 = value;
	}
	
     @WhereSQL(sql="ball4=:BjdcOdds_ball4")
	public java.lang.String getBall4() {
		return this.ball4;
	}
	public void setBall5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball5 = value;
	}
	
     @WhereSQL(sql="ball5=:BjdcOdds_ball5")
	public java.lang.String getBall5() {
		return this.ball5;
	}
	public void setBall6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball6 = value;
	}
	
     @WhereSQL(sql="ball6=:BjdcOdds_ball6")
	public java.lang.String getBall6() {
		return this.ball6;
	}
	public void setBall7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ball7 = value;
	}
	
     @WhereSQL(sql="ball7=:BjdcOdds_ball7")
	public java.lang.String getBall7() {
		return this.ball7;
	}
	public void setTopsingle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.topsingle = value;
	}
	
     @WhereSQL(sql="topsingle=:BjdcOdds_topsingle")
	public java.lang.String getTopsingle() {
		return this.topsingle;
	}
	public void setTopdouble(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.topdouble = value;
	}
	
     @WhereSQL(sql="topdouble=:BjdcOdds_topdouble")
	public java.lang.String getTopdouble() {
		return this.topdouble;
	}
	public void setDownsingle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.downsingle = value;
	}
	
     @WhereSQL(sql="downsingle=:BjdcOdds_downsingle")
	public java.lang.String getDownsingle() {
		return this.downsingle;
	}
	public void setDowndouble(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.downdouble = value;
	}
	
     @WhereSQL(sql="downdouble=:BjdcOdds_downdouble")
	public java.lang.String getDowndouble() {
		return this.downdouble;
	}
	public void setWin3A(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win3A = value;
	}
	
     @WhereSQL(sql="win3A=:BjdcOdds_win3A")
	public java.lang.String getWin3A() {
		return this.win3A;
	}
	public void setWin10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win10 = value;
	}
	
     @WhereSQL(sql="win10=:BjdcOdds_win10")
	public java.lang.String getWin10() {
		return this.win10;
	}
	public void setWin20(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win20 = value;
	}
	
     @WhereSQL(sql="win20=:BjdcOdds_win20")
	public java.lang.String getWin20() {
		return this.win20;
	}
	public void setWin21(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win21 = value;
	}
	
     @WhereSQL(sql="win21=:BjdcOdds_win21")
	public java.lang.String getWin21() {
		return this.win21;
	}
	public void setWin30(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win30 = value;
	}
	
     @WhereSQL(sql="win30=:BjdcOdds_win30")
	public java.lang.String getWin30() {
		return this.win30;
	}
	public void setWin31(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win31 = value;
	}
	
     @WhereSQL(sql="win31=:BjdcOdds_win31")
	public java.lang.String getWin31() {
		return this.win31;
	}
	public void setWin32(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win32 = value;
	}
	
     @WhereSQL(sql="win32=:BjdcOdds_win32")
	public java.lang.String getWin32() {
		return this.win32;
	}
	public void setWin40(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win40 = value;
	}
	
     @WhereSQL(sql="win40=:BjdcOdds_win40")
	public java.lang.String getWin40() {
		return this.win40;
	}
	public void setWin41(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win41 = value;
	}
	
     @WhereSQL(sql="win41=:BjdcOdds_win41")
	public java.lang.String getWin41() {
		return this.win41;
	}
	public void setWin42(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win42 = value;
	}
	
     @WhereSQL(sql="win42=:BjdcOdds_win42")
	public java.lang.String getWin42() {
		return this.win42;
	}
	public void setFlat1A(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.flat1A = value;
	}
	
     @WhereSQL(sql="flat1A=:BjdcOdds_flat1A")
	public java.lang.String getFlat1A() {
		return this.flat1A;
	}
	public void setFlat00(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.flat00 = value;
	}
	
     @WhereSQL(sql="flat00=:BjdcOdds_flat00")
	public java.lang.String getFlat00() {
		return this.flat00;
	}
	public void setFlat11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.flat11 = value;
	}
	
     @WhereSQL(sql="flat11=:BjdcOdds_flat11")
	public java.lang.String getFlat11() {
		return this.flat11;
	}
	public void setFlat22(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.flat22 = value;
	}
	
     @WhereSQL(sql="flat22=:BjdcOdds_flat22")
	public java.lang.String getFlat22() {
		return this.flat22;
	}
	public void setFlat33(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.flat33 = value;
	}
	
     @WhereSQL(sql="flat33=:BjdcOdds_flat33")
	public java.lang.String getFlat33() {
		return this.flat33;
	}
	public void setLose0A(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose0A = value;
	}
	
     @WhereSQL(sql="lose1A=:BjdcOdds_lose0A")
	public java.lang.String getLose0A() {
		return this.lose0A;
	}
	public void setLose01(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose01 = value;
	}
	
     @WhereSQL(sql="lose01=:BjdcOdds_lose01")
	public java.lang.String getLose01() {
		return this.lose01;
	}
	public void setLose02(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose02 = value;
	}
	
     @WhereSQL(sql="lose02=:BjdcOdds_lose02")
	public java.lang.String getLose02() {
		return this.lose02;
	}
	public void setLose12(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose12 = value;
	}
	
     @WhereSQL(sql="lose12=:BjdcOdds_lose12")
	public java.lang.String getLose12() {
		return this.lose12;
	}
	public void setLose03(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose03 = value;
	}
	
     @WhereSQL(sql="lose03=:BjdcOdds_lose03")
	public java.lang.String getLose03() {
		return this.lose03;
	}
	public void setLose13(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose13 = value;
	}
	
     @WhereSQL(sql="lose13=:BjdcOdds_lose13")
	public java.lang.String getLose13() {
		return this.lose13;
	}
	public void setLose23(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose23 = value;
	}
	
     @WhereSQL(sql="lose23=:BjdcOdds_lose23")
	public java.lang.String getLose23() {
		return this.lose23;
	}
	public void setLose04(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose04 = value;
	}
	
     @WhereSQL(sql="lose04=:BjdcOdds_lose04")
	public java.lang.String getLose04() {
		return this.lose04;
	}
	public void setLose14(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose14 = value;
	}
	
     @WhereSQL(sql="lose14=:BjdcOdds_lose14")
	public java.lang.String getLose14() {
		return this.lose14;
	}
	public void setLose24(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose24 = value;
	}
	
     @WhereSQL(sql="lose24=:BjdcOdds_lose24")
	public java.lang.String getLose24() {
		return this.lose24;
	}
	public void setSp33(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp33 = value;
	}
	
     @WhereSQL(sql="sp33=:BjdcOdds_sp33")
	public java.lang.String getSp33() {
		return this.sp33;
	}
	public void setSp31(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp31 = value;
	}
	
     @WhereSQL(sql="sp31=:BjdcOdds_sp31")
	public java.lang.String getSp31() {
		return this.sp31;
	}
	public void setSp30(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp30 = value;
	}
	
     @WhereSQL(sql="sp30=:BjdcOdds_sp30")
	public java.lang.String getSp30() {
		return this.sp30;
	}
	public void setSp13(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp13 = value;
	}
	
     @WhereSQL(sql="sp13=:BjdcOdds_sp13")
	public java.lang.String getSp13() {
		return this.sp13;
	}
	public void setSp11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp11 = value;
	}
	
     @WhereSQL(sql="sp11=:BjdcOdds_sp11")
	public java.lang.String getSp11() {
		return this.sp11;
	}
	public void setSp10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp10 = value;
	}
	
     @WhereSQL(sql="sp10=:BjdcOdds_sp10")
	public java.lang.String getSp10() {
		return this.sp10;
	}
	public void setSp03(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp03 = value;
	}
	
     @WhereSQL(sql="sp03=:BjdcOdds_sp03")
	public java.lang.String getSp03() {
		return this.sp03;
	}
	public void setSp01(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp01 = value;
	}
	
     @WhereSQL(sql="sp01=:BjdcOdds_sp01")
	public java.lang.String getSp01() {
		return this.sp01;
	}
	public void setSp00(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sp00 = value;
	}
	
     @WhereSQL(sql="sp00=:BjdcOdds_sp00")
	public java.lang.String getSp00() {
		return this.sp00;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("让球胜负平(让球)[").append(getLetpoints()).append("],")
			.append("让球胜[").append(getRqwin()).append("],")
			.append("让球平[").append(getRqflat()).append("],")
			.append("让球负[").append(getRqlose()).append("],")
			.append("进0球[").append(getBall0()).append("],")
			.append("进1球[").append(getBall1()).append("],")
			.append("进2球[").append(getBall2()).append("],")
			.append("进3球[").append(getBall3()).append("],")
			.append("进4球[").append(getBall4()).append("],")
			.append("进5球[").append(getBall5()).append("],")
			.append("进6球[").append(getBall6()).append("],")
			.append("进7+球[").append(getBall7()).append("],")
			.append("上单[").append(getTopsingle()).append("],")
			.append("上双[").append(getTopdouble()).append("],")
			.append("下单[").append(getDownsingle()).append("],")
			.append("下双[").append(getDowndouble()).append("],")
			.append("胜其他[").append(getWin3A()).append("],")
			.append("1:0[").append(getWin10()).append("],")
			.append("2:0[").append(getWin20()).append("],")
			.append("2:1[").append(getWin21()).append("],")
			.append("3:0[").append(getWin30()).append("],")
			.append("3:1[").append(getWin31()).append("],")
			.append("3:2[").append(getWin32()).append("],")
			.append("4:0[").append(getWin40()).append("],")
			.append("4:1[").append(getWin41()).append("],")
			.append("4:2[").append(getWin42()).append("],")
			.append("平其他[").append(getFlat1A()).append("],")
			.append("0:0[").append(getFlat00()).append("],")
			.append("1:1[").append(getFlat11()).append("],")
			.append("2:2[").append(getFlat22()).append("],")
			.append("3:3[").append(getFlat33()).append("],")
			.append("负其他[").append(getLose0A()).append("],")
			.append("0:1[").append(getLose01()).append("],")
			.append("0:2[").append(getLose02()).append("],")
			.append("1:2[").append(getLose12()).append("],")
			.append("0:3[").append(getLose03()).append("],")
			.append("1:3[").append(getLose13()).append("],")
			.append("2:3[").append(getLose23()).append("],")
			.append("0:4[").append(getLose04()).append("],")
			.append("1:4[").append(getLose14()).append("],")
			.append("2:4[").append(getLose24()).append("],")
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
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcOdds == false) return false;
		if(this == obj) return true;
		BjdcOdds other = (BjdcOdds)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
