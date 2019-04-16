package org.springrain.lottery.entity;

import java.text.ParseException;
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
 * @version  2017-12-12 16:08:20
 * @see org.springrain.lottery.entity.BjdcLive
 */
@Table(name="bjdc_live")
public class BjdcLive  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球实况";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PERIODNUM = "periodnum";
	public static final String ALIAS_NUM = "num";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_MATCHNAME = "matchname";
	public static final String ALIAS_MATCHID2 = "matchid2";
	public static final String ALIAS_STATE = "状态1:已完成 2:进行中 3:未开始";
	public static final String ALIAS_TIME = "比赛进行的时间";
	public static final String ALIAS_STARTTIME = "starttime";
	public static final String ALIAS_HOMETEAM = "hometeam";
	public static final String ALIAS_HOMETEAMID2 = "hometeamid2";
	public static final String ALIAS_GUESTTEAM = "guestteam";
	public static final String ALIAS_GUESTTEAMID2 = "guestteamid2";
	public static final String ALIAS_LEFTPUB1 = "leftpub1";
	public static final String ALIAS_LEFTPUB2 = "leftpub2";
	public static final String ALIAS_LEFTPUB3 = "leftpub3";
	public static final String ALIAS_LEFTPUB4 = "leftpub4";
	public static final String ALIAS_LEFTPUB5 = "leftpub5";
	public static final String ALIAS_LEFTPUB6 = "leftpub6";
	public static final String ALIAS_LEFTPUB7 = "leftpub7";
	public static final String ALIAS_LEFTPUB8 = "leftpub8";
	public static final String ALIAS_LEFTPUB9 = "leftpub9";
	public static final String ALIAS_LEFTPUB10 = "leftpub10";
	public static final String ALIAS_LEFTPUB11 = "leftpub11";
	public static final String ALIAS_LEFTRES1 = "leftres1";
	public static final String ALIAS_LEFTRES2 = "leftres2";
	public static final String ALIAS_LEFTRES3 = "leftres3";
	public static final String ALIAS_LEFTRES4 = "leftres4";
	public static final String ALIAS_LEFTRES5 = "leftres5";
	public static final String ALIAS_LEFTRES6 = "leftres6";
	public static final String ALIAS_LEFTRES7 = "leftres7";
	public static final String ALIAS_RIGHTPUB1 = "rightpub1";
	public static final String ALIAS_RIGHTPUB2 = "rightpub2";
	public static final String ALIAS_RIGHTPUB3 = "rightpub3";
	public static final String ALIAS_RIGHTPUB4 = "rightpub4";
	public static final String ALIAS_RIGHTPUB5 = "rightpub5";
	public static final String ALIAS_RIGHTPUB6 = "rightpub6";
	public static final String ALIAS_RIGHTPUB7 = "rightpub7";
	public static final String ALIAS_RIGHTPUB8 = "rightpub8";
	public static final String ALIAS_RIGHTPUB9 = "rightpub9";
	public static final String ALIAS_RIGHTPUB10 = "rightpub10";
	public static final String ALIAS_RIGHTPUB11 = "rightpub11";
	public static final String ALIAS_RIGHTRES1 = "rightres1";
	public static final String ALIAS_RIGHTRES2 = "rightres2";
	public static final String ALIAS_RIGHTRES3 = "rightres3";
	public static final String ALIAS_RIGHTRES4 = "rightres4";
	public static final String ALIAS_RIGHTRES5 = "rightres5";
	public static final String ALIAS_RIGHTRES6 = "rightres6";
	public static final String ALIAS_RIGHTRES7 = "rightres7";
	public static final String ALIAS_SHEMENG = "shemeng";
	public static final String ALIAS_SHEZHENG = "shezheng";
	public static final String ALIAS_FANGUI = "fangui";
	public static final String ALIAS_JIAOQIU = "jiaoqiu";
	public static final String ALIAS_YUEWEI = "yuewei";
	public static final String ALIAS_RED = "red";
	public static final String ALIAS_YELLOW = "yellow";
	public static final String ALIAS_JINGGONG = "jinggong";
	public static final String ALIAS_WEIXIEJINGGONG = "weixiejinggong";
	public static final String ALIAS_RENYIQIU = "renyiqiu";
	public static final String ALIAS_JIUQIU = "jiuqiu";
	public static final String ALIAS_KONGQIULV = "kongqiulv";
	public static final String ALIAS_REALSCORE = "即时比分";
	public static final String ALIAS_HALFSCORE = "0:0";
	public static final String ALIAS_LEFTRES8 = "leftres8";
	public static final String ALIAS_LEFTRES9 = "leftres9";
	public static final String ALIAS_LEFTRES10 = "leftres10";
	public static final String ALIAS_LEFTRES11 = "leftres11";
	public static final String ALIAS_LEFTRES12 = "leftres12";
	public static final String ALIAS_RIGHTRES8 = "rightres8";
	public static final String ALIAS_RIGHTRES9 = "rightres9";
	public static final String ALIAS_RIGHTRES10 = "rightres10";
	public static final String ALIAS_RIGHTRES11 = "rightres11";
	public static final String ALIAS_RIGHTRES12 = "rightres12";
    */
	//date formats
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * periodnum
	 */
	private java.lang.String periodnum;
	/**
	 * num
	 */
	private java.lang.String num;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * matchname
	 */
	private java.lang.String matchname;
	/**
	 * matchid2
	 */
	private java.lang.String matchid2;
	/**
	 * 状态1:已完成 2:进行中 3:未开始
	 */
	private java.lang.Integer state;
	/**
	 * 比赛进行的时间
	 */
	private java.lang.String time;
	/**
	 * starttime
	 */
	private java.util.Date starttime;
	/**
	 * hometeam
	 */
	private java.lang.String hometeam;
	/**
	 * hometeamid2
	 */
	private java.lang.String hometeamid2;
	/**
	 * guestteam
	 */
	private java.lang.String guestteam;
	/**
	 * guestteamid2
	 */
	private java.lang.String guestteamid2;
	/**
	 * leftpub1
	 */
	private java.lang.String leftpub1;
	/**
	 * leftpub2
	 */
	private java.lang.String leftpub2;
	/**
	 * leftpub3
	 */
	private java.lang.String leftpub3;
	/**
	 * leftpub4
	 */
	private java.lang.String leftpub4;
	/**
	 * leftpub5
	 */
	private java.lang.String leftpub5;
	/**
	 * leftpub6
	 */
	private java.lang.String leftpub6;
	/**
	 * leftpub7
	 */
	private java.lang.String leftpub7;
	/**
	 * leftpub8
	 */
	private java.lang.String leftpub8;
	/**
	 * leftpub9
	 */
	private java.lang.String leftpub9;
	/**
	 * leftpub10
	 */
	private java.lang.String leftpub10;
	/**
	 * leftpub11
	 */
	private java.lang.String leftpub11;
	/**
	 * leftres1
	 */
	private java.lang.String leftres1;
	/**
	 * leftres2
	 */
	private java.lang.String leftres2;
	/**
	 * leftres3
	 */
	private java.lang.String leftres3;
	/**
	 * leftres4
	 */
	private java.lang.String leftres4;
	/**
	 * leftres5
	 */
	private java.lang.String leftres5;
	/**
	 * leftres6
	 */
	private java.lang.String leftres6;
	/**
	 * leftres7
	 */
	private java.lang.String leftres7;
	/**
	 * rightpub1
	 */
	private java.lang.String rightpub1;
	/**
	 * rightpub2
	 */
	private java.lang.String rightpub2;
	/**
	 * rightpub3
	 */
	private java.lang.String rightpub3;
	/**
	 * rightpub4
	 */
	private java.lang.String rightpub4;
	/**
	 * rightpub5
	 */
	private java.lang.String rightpub5;
	/**
	 * rightpub6
	 */
	private java.lang.String rightpub6;
	/**
	 * rightpub7
	 */
	private java.lang.String rightpub7;
	/**
	 * rightpub8
	 */
	private java.lang.String rightpub8;
	/**
	 * rightpub9
	 */
	private java.lang.String rightpub9;
	/**
	 * rightpub10
	 */
	private java.lang.String rightpub10;
	/**
	 * rightpub11
	 */
	private java.lang.String rightpub11;
	/**
	 * rightres1
	 */
	private java.lang.String rightres1;
	/**
	 * rightres2
	 */
	private java.lang.String rightres2;
	/**
	 * rightres3
	 */
	private java.lang.String rightres3;
	/**
	 * rightres4
	 */
	private java.lang.String rightres4;
	/**
	 * rightres5
	 */
	private java.lang.String rightres5;
	/**
	 * rightres6
	 */
	private java.lang.String rightres6;
	/**
	 * rightres7
	 */
	private java.lang.String rightres7;
	/**
	 * shemeng
	 */
	private java.lang.String shemeng;
	/**
	 * shezheng
	 */
	private java.lang.String shezheng;
	/**
	 * fangui
	 */
	private java.lang.String fangui;
	/**
	 * jiaoqiu
	 */
	private java.lang.String jiaoqiu;
	/**
	 * yuewei
	 */
	private java.lang.String yuewei;
	/**
	 * red
	 */
	private java.lang.String red;
	/**
	 * yellow
	 */
	private java.lang.String yellow;
	/**
	 * jinggong
	 */
	private java.lang.String jinggong;
	/**
	 * weixiejinggong
	 */
	private java.lang.String weixiejinggong;
	/**
	 * renyiqiu
	 */
	private java.lang.String renyiqiu;
	/**
	 * jiuqiu
	 */
	private java.lang.String jiuqiu;
	/**
	 * kongqiulv
	 */
	private java.lang.String kongqiulv;
	/**
	 * 即时比分
	 */
	private java.lang.String realscore;
	/**
	 * 0:0
	 */
	private java.lang.String halfscore;
	/**
	 * leftres8
	 */
	private java.lang.String leftres8;
	/**
	 * leftres9
	 */
	private java.lang.String leftres9;
	/**
	 * leftres10
	 */
	private java.lang.String leftres10;
	/**
	 * leftres11
	 */
	private java.lang.String leftres11;
	/**
	 * leftres12
	 */
	private java.lang.String leftres12;
	/**
	 * rightres8
	 */
	private java.lang.String rightres8;
	/**
	 * rightres9
	 */
	private java.lang.String rightres9;
	/**
	 * rightres10
	 */
	private java.lang.String rightres10;
	/**
	 * rightres11
	 */
	private java.lang.String rightres11;
	/**
	 * rightres12
	 */
	private java.lang.String rightres12;
	//columns END 数据库字段结束
	
	//concstructor

	public BjdcLive(){
	}

	public BjdcLive(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcLive_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setPeriodnum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.periodnum = value;
	}
	
     @WhereSQL(sql="periodnum=:BjdcLive_periodnum")
	public java.lang.String getPeriodnum() {
		return this.periodnum;
	}
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
     @WhereSQL(sql="num=:BjdcLive_num")
	public java.lang.String getNum() {
		return this.num;
	}
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
     @WhereSQL(sql="fid=:BjdcLive_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
	public void setMatchname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchname = value;
	}
	
     @WhereSQL(sql="matchname=:BjdcLive_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
	public void setMatchid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchid2 = value;
	}
	
     @WhereSQL(sql="matchid2=:BjdcLive_matchid2")
	public java.lang.String getMatchid2() {
		return this.matchid2;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BjdcLive_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setTime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.time = value;
	}
	
     @WhereSQL(sql="time=:BjdcLive_time")
	public java.lang.String getTime() {
		return this.time;
	}
		/*
	public String getstarttimeString() {
		return DateUtils.convertDate2String(FORMAT_STARTTIME, getstarttime());
	}
	public void setstarttimeString(String value) throws ParseException{
		setstarttime(DateUtils.convertString2Date(FORMAT_STARTTIME,value));
	}*/
	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
     @WhereSQL(sql="starttime=:BjdcLive_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	public void setHometeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeam = value;
	}
	
     @WhereSQL(sql="hometeam=:BjdcLive_hometeam")
	public java.lang.String getHometeam() {
		return this.hometeam;
	}
	public void setHometeamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeamid2 = value;
	}
	
     @WhereSQL(sql="hometeamid2=:BjdcLive_hometeamid2")
	public java.lang.String getHometeamid2() {
		return this.hometeamid2;
	}
	public void setGuestteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.guestteam = value;
	}
	
     @WhereSQL(sql="guestteam=:BjdcLive_guestteam")
	public java.lang.String getGuestteam() {
		return this.guestteam;
	}
	public void setGuestteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.guestteamid2 = value;
	}
	
     @WhereSQL(sql="guestteamid2=:BjdcLive_guestteamid2")
	public java.lang.String getGuestteamid2() {
		return this.guestteamid2;
	}
	public void setLeftpub1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub1 = value;
	}
	
     @WhereSQL(sql="leftpub1=:BjdcLive_leftpub1")
	public java.lang.String getLeftpub1() {
		return this.leftpub1;
	}
	public void setLeftpub2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub2 = value;
	}
	
     @WhereSQL(sql="leftpub2=:BjdcLive_leftpub2")
	public java.lang.String getLeftpub2() {
		return this.leftpub2;
	}
	public void setLeftpub3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub3 = value;
	}
	
     @WhereSQL(sql="leftpub3=:BjdcLive_leftpub3")
	public java.lang.String getLeftpub3() {
		return this.leftpub3;
	}
	public void setLeftpub4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub4 = value;
	}
	
     @WhereSQL(sql="leftpub4=:BjdcLive_leftpub4")
	public java.lang.String getLeftpub4() {
		return this.leftpub4;
	}
	public void setLeftpub5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub5 = value;
	}
	
     @WhereSQL(sql="leftpub5=:BjdcLive_leftpub5")
	public java.lang.String getLeftpub5() {
		return this.leftpub5;
	}
	public void setLeftpub6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub6 = value;
	}
	
     @WhereSQL(sql="leftpub6=:BjdcLive_leftpub6")
	public java.lang.String getLeftpub6() {
		return this.leftpub6;
	}
	public void setLeftpub7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub7 = value;
	}
	
     @WhereSQL(sql="leftpub7=:BjdcLive_leftpub7")
	public java.lang.String getLeftpub7() {
		return this.leftpub7;
	}
	public void setLeftpub8(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub8 = value;
	}
	
     @WhereSQL(sql="leftpub8=:BjdcLive_leftpub8")
	public java.lang.String getLeftpub8() {
		return this.leftpub8;
	}
	public void setLeftpub9(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub9 = value;
	}
	
     @WhereSQL(sql="leftpub9=:BjdcLive_leftpub9")
	public java.lang.String getLeftpub9() {
		return this.leftpub9;
	}
	public void setLeftpub10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub10 = value;
	}
	
     @WhereSQL(sql="leftpub10=:BjdcLive_leftpub10")
	public java.lang.String getLeftpub10() {
		return this.leftpub10;
	}
	public void setLeftpub11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub11 = value;
	}
	
     @WhereSQL(sql="leftpub11=:BjdcLive_leftpub11")
	public java.lang.String getLeftpub11() {
		return this.leftpub11;
	}
	public void setLeftres1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres1 = value;
	}
	
     @WhereSQL(sql="leftres1=:BjdcLive_leftres1")
	public java.lang.String getLeftres1() {
		return this.leftres1;
	}
	public void setLeftres2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres2 = value;
	}
	
     @WhereSQL(sql="leftres2=:BjdcLive_leftres2")
	public java.lang.String getLeftres2() {
		return this.leftres2;
	}
	public void setLeftres3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres3 = value;
	}
	
     @WhereSQL(sql="leftres3=:BjdcLive_leftres3")
	public java.lang.String getLeftres3() {
		return this.leftres3;
	}
	public void setLeftres4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres4 = value;
	}
	
     @WhereSQL(sql="leftres4=:BjdcLive_leftres4")
	public java.lang.String getLeftres4() {
		return this.leftres4;
	}
	public void setLeftres5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres5 = value;
	}
	
     @WhereSQL(sql="leftres5=:BjdcLive_leftres5")
	public java.lang.String getLeftres5() {
		return this.leftres5;
	}
	public void setLeftres6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres6 = value;
	}
	
     @WhereSQL(sql="leftres6=:BjdcLive_leftres6")
	public java.lang.String getLeftres6() {
		return this.leftres6;
	}
	public void setLeftres7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres7 = value;
	}
	
     @WhereSQL(sql="leftres7=:BjdcLive_leftres7")
	public java.lang.String getLeftres7() {
		return this.leftres7;
	}
	public void setRightpub1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub1 = value;
	}
	
     @WhereSQL(sql="rightpub1=:BjdcLive_rightpub1")
	public java.lang.String getRightpub1() {
		return this.rightpub1;
	}
	public void setRightpub2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub2 = value;
	}
	
     @WhereSQL(sql="rightpub2=:BjdcLive_rightpub2")
	public java.lang.String getRightpub2() {
		return this.rightpub2;
	}
	public void setRightpub3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub3 = value;
	}
	
     @WhereSQL(sql="rightpub3=:BjdcLive_rightpub3")
	public java.lang.String getRightpub3() {
		return this.rightpub3;
	}
	public void setRightpub4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub4 = value;
	}
	
     @WhereSQL(sql="rightpub4=:BjdcLive_rightpub4")
	public java.lang.String getRightpub4() {
		return this.rightpub4;
	}
	public void setRightpub5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub5 = value;
	}
	
     @WhereSQL(sql="rightpub5=:BjdcLive_rightpub5")
	public java.lang.String getRightpub5() {
		return this.rightpub5;
	}
	public void setRightpub6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub6 = value;
	}
	
     @WhereSQL(sql="rightpub6=:BjdcLive_rightpub6")
	public java.lang.String getRightpub6() {
		return this.rightpub6;
	}
	public void setRightpub7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub7 = value;
	}
	
     @WhereSQL(sql="rightpub7=:BjdcLive_rightpub7")
	public java.lang.String getRightpub7() {
		return this.rightpub7;
	}
	public void setRightpub8(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub8 = value;
	}
	
     @WhereSQL(sql="rightpub8=:BjdcLive_rightpub8")
	public java.lang.String getRightpub8() {
		return this.rightpub8;
	}
	public void setRightpub9(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub9 = value;
	}
	
     @WhereSQL(sql="rightpub9=:BjdcLive_rightpub9")
	public java.lang.String getRightpub9() {
		return this.rightpub9;
	}
	public void setRightpub10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub10 = value;
	}
	
     @WhereSQL(sql="rightpub10=:BjdcLive_rightpub10")
	public java.lang.String getRightpub10() {
		return this.rightpub10;
	}
	public void setRightpub11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub11 = value;
	}
	
     @WhereSQL(sql="rightpub11=:BjdcLive_rightpub11")
	public java.lang.String getRightpub11() {
		return this.rightpub11;
	}
	public void setRightres1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres1 = value;
	}
	
     @WhereSQL(sql="rightres1=:BjdcLive_rightres1")
	public java.lang.String getRightres1() {
		return this.rightres1;
	}
	public void setRightres2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres2 = value;
	}
	
     @WhereSQL(sql="rightres2=:BjdcLive_rightres2")
	public java.lang.String getRightres2() {
		return this.rightres2;
	}
	public void setRightres3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres3 = value;
	}
	
     @WhereSQL(sql="rightres3=:BjdcLive_rightres3")
	public java.lang.String getRightres3() {
		return this.rightres3;
	}
	public void setRightres4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres4 = value;
	}
	
     @WhereSQL(sql="rightres4=:BjdcLive_rightres4")
	public java.lang.String getRightres4() {
		return this.rightres4;
	}
	public void setRightres5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres5 = value;
	}
	
     @WhereSQL(sql="rightres5=:BjdcLive_rightres5")
	public java.lang.String getRightres5() {
		return this.rightres5;
	}
	public void setRightres6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres6 = value;
	}
	
     @WhereSQL(sql="rightres6=:BjdcLive_rightres6")
	public java.lang.String getRightres6() {
		return this.rightres6;
	}
	public void setRightres7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres7 = value;
	}
	
     @WhereSQL(sql="rightres7=:BjdcLive_rightres7")
	public java.lang.String getRightres7() {
		return this.rightres7;
	}
	public void setShemeng(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shemeng = value;
	}
	
     @WhereSQL(sql="shemeng=:BjdcLive_shemeng")
	public java.lang.String getShemeng() {
		return this.shemeng;
	}
	public void setShezheng(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shezheng = value;
	}
	
     @WhereSQL(sql="shezheng=:BjdcLive_shezheng")
	public java.lang.String getShezheng() {
		return this.shezheng;
	}
	public void setFangui(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fangui = value;
	}
	
     @WhereSQL(sql="fangui=:BjdcLive_fangui")
	public java.lang.String getFangui() {
		return this.fangui;
	}
	public void setJiaoqiu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jiaoqiu = value;
	}
	
     @WhereSQL(sql="jiaoqiu=:BjdcLive_jiaoqiu")
	public java.lang.String getJiaoqiu() {
		return this.jiaoqiu;
	}
	public void setYuewei(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.yuewei = value;
	}
	
     @WhereSQL(sql="yuewei=:BjdcLive_yuewei")
	public java.lang.String getYuewei() {
		return this.yuewei;
	}
	public void setRed(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.red = value;
	}
	
     @WhereSQL(sql="red=:BjdcLive_red")
	public java.lang.String getRed() {
		return this.red;
	}
	public void setYellow(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.yellow = value;
	}
	
     @WhereSQL(sql="yellow=:BjdcLive_yellow")
	public java.lang.String getYellow() {
		return this.yellow;
	}
	public void setJinggong(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jinggong = value;
	}
	
     @WhereSQL(sql="jinggong=:BjdcLive_jinggong")
	public java.lang.String getJinggong() {
		return this.jinggong;
	}
	public void setWeixiejinggong(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.weixiejinggong = value;
	}
	
     @WhereSQL(sql="weixiejinggong=:BjdcLive_weixiejinggong")
	public java.lang.String getWeixiejinggong() {
		return this.weixiejinggong;
	}
	public void setRenyiqiu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.renyiqiu = value;
	}
	
     @WhereSQL(sql="renyiqiu=:BjdcLive_renyiqiu")
	public java.lang.String getRenyiqiu() {
		return this.renyiqiu;
	}
	public void setJiuqiu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jiuqiu = value;
	}
	
     @WhereSQL(sql="jiuqiu=:BjdcLive_jiuqiu")
	public java.lang.String getJiuqiu() {
		return this.jiuqiu;
	}
	public void setKongqiulv(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.kongqiulv = value;
	}
	
     @WhereSQL(sql="kongqiulv=:BjdcLive_kongqiulv")
	public java.lang.String getKongqiulv() {
		return this.kongqiulv;
	}
	public void setRealscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.realscore = value;
	}
	
     @WhereSQL(sql="realscore=:BjdcLive_realscore")
	public java.lang.String getRealscore() {
		return this.realscore;
	}
	public void setHalfscore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.halfscore = value;
	}
	
     @WhereSQL(sql="halfscore=:BjdcLive_halfscore")
	public java.lang.String getHalfscore() {
		return this.halfscore;
	}
	public void setLeftres8(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres8 = value;
	}
	
     @WhereSQL(sql="leftres8=:BjdcLive_leftres8")
	public java.lang.String getLeftres8() {
		return this.leftres8;
	}
	public void setLeftres9(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres9 = value;
	}
	
     @WhereSQL(sql="leftres9=:BjdcLive_leftres9")
	public java.lang.String getLeftres9() {
		return this.leftres9;
	}
	public void setLeftres10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres10 = value;
	}
	
     @WhereSQL(sql="leftres10=:BjdcLive_leftres10")
	public java.lang.String getLeftres10() {
		return this.leftres10;
	}
	public void setLeftres11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres11 = value;
	}
	
     @WhereSQL(sql="leftres11=:BjdcLive_leftres11")
	public java.lang.String getLeftres11() {
		return this.leftres11;
	}
	public void setLeftres12(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres12 = value;
	}
	
     @WhereSQL(sql="leftres12=:BjdcLive_leftres12")
	public java.lang.String getLeftres12() {
		return this.leftres12;
	}
	public void setRightres8(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres8 = value;
	}
	
     @WhereSQL(sql="rightres8=:BjdcLive_rightres8")
	public java.lang.String getRightres8() {
		return this.rightres8;
	}
	public void setRightres9(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres9 = value;
	}
	
     @WhereSQL(sql="rightres9=:BjdcLive_rightres9")
	public java.lang.String getRightres9() {
		return this.rightres9;
	}
	public void setRightres10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres10 = value;
	}
	
     @WhereSQL(sql="rightres10=:BjdcLive_rightres10")
	public java.lang.String getRightres10() {
		return this.rightres10;
	}
	public void setRightres11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres11 = value;
	}
	
     @WhereSQL(sql="rightres11=:BjdcLive_rightres11")
	public java.lang.String getRightres11() {
		return this.rightres11;
	}
	public void setRightres12(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres12 = value;
	}
	
     @WhereSQL(sql="rightres12=:BjdcLive_rightres12")
	public java.lang.String getRightres12() {
		return this.rightres12;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("periodnum[").append(getPeriodnum()).append("],")
			.append("num[").append(getNum()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("matchname[").append(getMatchname()).append("],")
			.append("matchid2[").append(getMatchid2()).append("],")
			.append("状态1:已完成 2:进行中 3:未开始[").append(getState()).append("],")
			.append("比赛进行的时间[").append(getTime()).append("],")
			.append("starttime[").append(getStarttime()).append("],")
			.append("hometeam[").append(getHometeam()).append("],")
			.append("hometeamid2[").append(getHometeamid2()).append("],")
			.append("guestteam[").append(getGuestteam()).append("],")
			.append("guestteamid2[").append(getGuestteamid2()).append("],")
			.append("leftpub1[").append(getLeftpub1()).append("],")
			.append("leftpub2[").append(getLeftpub2()).append("],")
			.append("leftpub3[").append(getLeftpub3()).append("],")
			.append("leftpub4[").append(getLeftpub4()).append("],")
			.append("leftpub5[").append(getLeftpub5()).append("],")
			.append("leftpub6[").append(getLeftpub6()).append("],")
			.append("leftpub7[").append(getLeftpub7()).append("],")
			.append("leftpub8[").append(getLeftpub8()).append("],")
			.append("leftpub9[").append(getLeftpub9()).append("],")
			.append("leftpub10[").append(getLeftpub10()).append("],")
			.append("leftpub11[").append(getLeftpub11()).append("],")
			.append("leftres1[").append(getLeftres1()).append("],")
			.append("leftres2[").append(getLeftres2()).append("],")
			.append("leftres3[").append(getLeftres3()).append("],")
			.append("leftres4[").append(getLeftres4()).append("],")
			.append("leftres5[").append(getLeftres5()).append("],")
			.append("leftres6[").append(getLeftres6()).append("],")
			.append("leftres7[").append(getLeftres7()).append("],")
			.append("rightpub1[").append(getRightpub1()).append("],")
			.append("rightpub2[").append(getRightpub2()).append("],")
			.append("rightpub3[").append(getRightpub3()).append("],")
			.append("rightpub4[").append(getRightpub4()).append("],")
			.append("rightpub5[").append(getRightpub5()).append("],")
			.append("rightpub6[").append(getRightpub6()).append("],")
			.append("rightpub7[").append(getRightpub7()).append("],")
			.append("rightpub8[").append(getRightpub8()).append("],")
			.append("rightpub9[").append(getRightpub9()).append("],")
			.append("rightpub10[").append(getRightpub10()).append("],")
			.append("rightpub11[").append(getRightpub11()).append("],")
			.append("rightres1[").append(getRightres1()).append("],")
			.append("rightres2[").append(getRightres2()).append("],")
			.append("rightres3[").append(getRightres3()).append("],")
			.append("rightres4[").append(getRightres4()).append("],")
			.append("rightres5[").append(getRightres5()).append("],")
			.append("rightres6[").append(getRightres6()).append("],")
			.append("rightres7[").append(getRightres7()).append("],")
			.append("shemeng[").append(getShemeng()).append("],")
			.append("shezheng[").append(getShezheng()).append("],")
			.append("fangui[").append(getFangui()).append("],")
			.append("jiaoqiu[").append(getJiaoqiu()).append("],")
			.append("yuewei[").append(getYuewei()).append("],")
			.append("red[").append(getRed()).append("],")
			.append("yellow[").append(getYellow()).append("],")
			.append("jinggong[").append(getJinggong()).append("],")
			.append("weixiejinggong[").append(getWeixiejinggong()).append("],")
			.append("renyiqiu[").append(getRenyiqiu()).append("],")
			.append("jiuqiu[").append(getJiuqiu()).append("],")
			.append("kongqiulv[").append(getKongqiulv()).append("],")
			.append("即时比分[").append(getRealscore()).append("],")
			.append("0:0[").append(getHalfscore()).append("],")
			.append("leftres8[").append(getLeftres8()).append("],")
			.append("leftres9[").append(getLeftres9()).append("],")
			.append("leftres10[").append(getLeftres10()).append("],")
			.append("leftres11[").append(getLeftres11()).append("],")
			.append("leftres12[").append(getLeftres12()).append("],")
			.append("rightres8[").append(getRightres8()).append("],")
			.append("rightres9[").append(getRightres9()).append("],")
			.append("rightres10[").append(getRightres10()).append("],")
			.append("rightres11[").append(getRightres11()).append("],")
			.append("rightres12[").append(getRightres12()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcLive == false) return false;
		if(this == obj) return true;
		BjdcLive other = (BjdcLive)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
