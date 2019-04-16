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
 * @version  2017-10-19 09:38:14
 * @see org.springrain.lottery.entity.SoccerLive
 */
@Table(name="soccer_live")
public class SoccerLive  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "足球实况";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MID = "mid";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_STARTTIME = "starttime";
	public static final String ALIAS_LEFTTEAM = "leftteam";
	public static final String ALIAS_LEFTTEAMID2 = "leftteamid2";
	public static final String ALIAS_RIGHTTEAM = "rightteam";
	public static final String ALIAS_RIGHTTEAMID2 = "rightteamid2";
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
	public static final String ALIAS_TIME = "比赛进行的时间";
	public static final String ALIAS_FID = "fid";
    */
	//date formats
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * mid
	 */
	private java.lang.String mid;
	/**
	 * zid
	 */
	private java.lang.String zid;
	/**
	 * starttime
	 */
	private java.util.Date starttime;
	/**
	 * leftteam
	 */
	private java.lang.String leftteam;
	/**
	 * leftteamid2
	 */
	private java.lang.String leftteamid2;
	/**
	 * rightteam
	 */
	private java.lang.String rightteam;
	/**
	 * rightteamid2
	 */
	private java.lang.String rightteamid2;
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
	private String leftres8;
	private String leftres9;
	private String leftres10;
	private String leftres11;
	private String leftres12;
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
	private String rightres8;
	private String rightres9;
	private String rightres10;
	private String rightres11;
	private String rightres12;
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
	 * 比赛进行的时间
	 */
	private java.lang.String time;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * 状态1:已完成 2:进行中 3:未开始
	 */
	private java.lang.Integer state;
	/**
	 * 联赛名
	 */
	private java.lang.String leaguename;
	/**
	 * 比分
	 */
	private java.lang.String realscore;
	/**
	 * 比分
	 */
	private java.lang.String halfscore;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLive(){
	}

	public SoccerLive(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLive_id")
	public java.lang.Integer getId() {
		return this.id;
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
     @WhereSQL(sql="mid=:SoccerLive_mid")
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
     @WhereSQL(sql="zid=:SoccerLive_zid")
	public java.lang.String getZid() {
		return this.zid;
	}
		/*
	public String getstarttimeString() {
		return DateUtils.convertDate2String(FORMAT_STARTTIME, getstarttime());
	}
	public void setstarttimeString(String value) throws ParseException{
		setstarttime(DateUtils.convertString2Date(FORMAT_STARTTIME,value));
	}*/
	
		/**
		 * starttime
		 */
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	
	
	/**
	 * starttime
	 */
     @WhereSQL(sql="starttime=:SoccerLive_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
		/**
		 * leftteam
		 */
	public void setLeftteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftteam = value;
	}
	
	
	
	/**
	 * leftteam
	 */
     @WhereSQL(sql="leftteam=:SoccerLive_leftteam")
	public java.lang.String getLeftteam() {
		return this.leftteam;
	}
		/**
		 * leftteamid2
		 */
	public void setLeftteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftteamid2 = value;
	}
	
	
	
	/**
	 * leftteamid2
	 */
     @WhereSQL(sql="leftteamid2=:SoccerLive_leftteamid2")
	public java.lang.String getLeftteamid2() {
		return this.leftteamid2;
	}
		/**
		 * rightteam
		 */
	public void setRightteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightteam = value;
	}
	
	
	
	/**
	 * rightteam
	 */
     @WhereSQL(sql="rightteam=:SoccerLive_rightteam")
	public java.lang.String getRightteam() {
		return this.rightteam;
	}
		/**
		 * rightteamid2
		 */
	public void setRightteamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightteamid2 = value;
	}
	
	
	
	/**
	 * rightteamid2
	 */
     @WhereSQL(sql="rightteamid2=:SoccerLive_rightteamid2")
	public java.lang.String getRightteamid2() {
		return this.rightteamid2;
	}
		/**
		 * leftpub1
		 */
	public void setLeftpub1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub1 = value;
	}
	
	
	
	/**
	 * leftpub1
	 */
     @WhereSQL(sql="leftpub1=:SoccerLive_leftpub1")
	public java.lang.String getLeftpub1() {
		return this.leftpub1;
	}
		/**
		 * leftpub2
		 */
	public void setLeftpub2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub2 = value;
	}
	
	
	
	/**
	 * leftpub2
	 */
     @WhereSQL(sql="leftpub2=:SoccerLive_leftpub2")
	public java.lang.String getLeftpub2() {
		return this.leftpub2;
	}
		/**
		 * leftpub3
		 */
	public void setLeftpub3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub3 = value;
	}
	
	
	
	/**
	 * leftpub3
	 */
     @WhereSQL(sql="leftpub3=:SoccerLive_leftpub3")
	public java.lang.String getLeftpub3() {
		return this.leftpub3;
	}
		/**
		 * leftpub4
		 */
	public void setLeftpub4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub4 = value;
	}
	
	
	
	/**
	 * leftpub4
	 */
     @WhereSQL(sql="leftpub4=:SoccerLive_leftpub4")
	public java.lang.String getLeftpub4() {
		return this.leftpub4;
	}
		/**
		 * leftpub5
		 */
	public void setLeftpub5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub5 = value;
	}
	
	
	
	/**
	 * leftpub5
	 */
     @WhereSQL(sql="leftpub5=:SoccerLive_leftpub5")
	public java.lang.String getLeftpub5() {
		return this.leftpub5;
	}
		/**
		 * leftpub6
		 */
	public void setLeftpub6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub6 = value;
	}
	
	
	
	/**
	 * leftpub6
	 */
     @WhereSQL(sql="leftpub6=:SoccerLive_leftpub6")
	public java.lang.String getLeftpub6() {
		return this.leftpub6;
	}
		/**
		 * leftpub7
		 */
	public void setLeftpub7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub7 = value;
	}
	
	
	
	/**
	 * leftpub7
	 */
     @WhereSQL(sql="leftpub7=:SoccerLive_leftpub7")
	public java.lang.String getLeftpub7() {
		return this.leftpub7;
	}
		/**
		 * leftpub8
		 */
	public void setLeftpub8(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub8 = value;
	}
	
	
	
	/**
	 * leftpub8
	 */
     @WhereSQL(sql="leftpub8=:SoccerLive_leftpub8")
	public java.lang.String getLeftpub8() {
		return this.leftpub8;
	}
		/**
		 * leftpub9
		 */
	public void setLeftpub9(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub9 = value;
	}
	
	
	
	/**
	 * leftpub9
	 */
     @WhereSQL(sql="leftpub9=:SoccerLive_leftpub9")
	public java.lang.String getLeftpub9() {
		return this.leftpub9;
	}
		/**
		 * leftpub10
		 */
	public void setLeftpub10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub10 = value;
	}
	
	
	
	/**
	 * leftpub10
	 */
     @WhereSQL(sql="leftpub10=:SoccerLive_leftpub10")
	public java.lang.String getLeftpub10() {
		return this.leftpub10;
	}
		/**
		 * leftpub11
		 */
	public void setLeftpub11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftpub11 = value;
	}
	
	
	
	/**
	 * leftpub11
	 */
     @WhereSQL(sql="leftpub11=:SoccerLive_leftpub11")
	public java.lang.String getLeftpub11() {
		return this.leftpub11;
	}
		/**
		 * leftres1
		 */
	public void setLeftres1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres1 = value;
	}
	
	
	
	/**
	 * leftres1
	 */
     @WhereSQL(sql="leftres1=:SoccerLive_leftres1")
	public java.lang.String getLeftres1() {
		return this.leftres1;
	}
		/**
		 * leftres2
		 */
	public void setLeftres2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres2 = value;
	}
	
	
	
	/**
	 * leftres2
	 */
     @WhereSQL(sql="leftres2=:SoccerLive_leftres2")
	public java.lang.String getLeftres2() {
		return this.leftres2;
	}
		/**
		 * leftres3
		 */
	public void setLeftres3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres3 = value;
	}
	
	
	
	/**
	 * leftres3
	 */
     @WhereSQL(sql="leftres3=:SoccerLive_leftres3")
	public java.lang.String getLeftres3() {
		return this.leftres3;
	}
		/**
		 * leftres4
		 */
	public void setLeftres4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres4 = value;
	}
	
	
	
	/**
	 * leftres4
	 */
     @WhereSQL(sql="leftres4=:SoccerLive_leftres4")
	public java.lang.String getLeftres4() {
		return this.leftres4;
	}
		/**
		 * leftres5
		 */
	public void setLeftres5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres5 = value;
	}
	
	
	
	/**
	 * leftres5
	 */
     @WhereSQL(sql="leftres5=:SoccerLive_leftres5")
	public java.lang.String getLeftres5() {
		return this.leftres5;
	}
		/**
		 * leftres6
		 */
	public void setLeftres6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres6 = value;
	}
	
	
	
	/**
	 * leftres6
	 */
     @WhereSQL(sql="leftres6=:SoccerLive_leftres6")
	public java.lang.String getLeftres6() {
		return this.leftres6;
	}
		/**
		 * leftres7
		 */
	public void setLeftres7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftres7 = value;
	}
	
	
	
	/**
	 * leftres7
	 */
     @WhereSQL(sql="leftres7=:SoccerLive_leftres7")
	public java.lang.String getLeftres7() {
		return this.leftres7;
	}
		/**
		 * rightpub1
		 */
	public void setRightpub1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub1 = value;
	}
	
	
	
	/**
	 * rightpub1
	 */
     @WhereSQL(sql="rightpub1=:SoccerLive_rightpub1")
	public java.lang.String getRightpub1() {
		return this.rightpub1;
	}
		/**
		 * rightpub2
		 */
	public void setRightpub2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub2 = value;
	}
	
	
	
	/**
	 * rightpub2
	 */
     @WhereSQL(sql="rightpub2=:SoccerLive_rightpub2")
	public java.lang.String getRightpub2() {
		return this.rightpub2;
	}
		/**
		 * rightpub3
		 */
	public void setRightpub3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub3 = value;
	}
	
	
	
	/**
	 * rightpub3
	 */
     @WhereSQL(sql="rightpub3=:SoccerLive_rightpub3")
	public java.lang.String getRightpub3() {
		return this.rightpub3;
	}
		/**
		 * rightpub4
		 */
	public void setRightpub4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub4 = value;
	}
	
	
	
	/**
	 * rightpub4
	 */
     @WhereSQL(sql="rightpub4=:SoccerLive_rightpub4")
	public java.lang.String getRightpub4() {
		return this.rightpub4;
	}
		/**
		 * rightpub5
		 */
	public void setRightpub5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub5 = value;
	}
	
	
	
	/**
	 * rightpub5
	 */
     @WhereSQL(sql="rightpub5=:SoccerLive_rightpub5")
	public java.lang.String getRightpub5() {
		return this.rightpub5;
	}
		/**
		 * rightpub6
		 */
	public void setRightpub6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub6 = value;
	}
	
	
	
	/**
	 * rightpub6
	 */
     @WhereSQL(sql="rightpub6=:SoccerLive_rightpub6")
	public java.lang.String getRightpub6() {
		return this.rightpub6;
	}
		/**
		 * rightpub7
		 */
	public void setRightpub7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub7 = value;
	}
	
	
	
	/**
	 * rightpub7
	 */
     @WhereSQL(sql="rightpub7=:SoccerLive_rightpub7")
	public java.lang.String getRightpub7() {
		return this.rightpub7;
	}
		/**
		 * rightpub8
		 */
	public void setRightpub8(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub8 = value;
	}
	
	
	
	/**
	 * rightpub8
	 */
     @WhereSQL(sql="rightpub8=:SoccerLive_rightpub8")
	public java.lang.String getRightpub8() {
		return this.rightpub8;
	}
		/**
		 * rightpub9
		 */
	public void setRightpub9(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub9 = value;
	}
	
	
	
	/**
	 * rightpub9
	 */
     @WhereSQL(sql="rightpub9=:SoccerLive_rightpub9")
	public java.lang.String getRightpub9() {
		return this.rightpub9;
	}
		/**
		 * rightpub10
		 */
	public void setRightpub10(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub10 = value;
	}
	
	
	
	/**
	 * rightpub10
	 */
     @WhereSQL(sql="rightpub10=:SoccerLive_rightpub10")
	public java.lang.String getRightpub10() {
		return this.rightpub10;
	}
		/**
		 * rightpub11
		 */
	public void setRightpub11(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightpub11 = value;
	}
	
	
	
	/**
	 * rightpub11
	 */
     @WhereSQL(sql="rightpub11=:SoccerLive_rightpub11")
	public java.lang.String getRightpub11() {
		return this.rightpub11;
	}
		/**
		 * rightres1
		 */
	public void setRightres1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres1 = value;
	}
	
	
	
	/**
	 * rightres1
	 */
     @WhereSQL(sql="rightres1=:SoccerLive_rightres1")
	public java.lang.String getRightres1() {
		return this.rightres1;
	}
		/**
		 * rightres2
		 */
	public void setRightres2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres2 = value;
	}
	
	
	
	/**
	 * rightres2
	 */
     @WhereSQL(sql="rightres2=:SoccerLive_rightres2")
	public java.lang.String getRightres2() {
		return this.rightres2;
	}
		/**
		 * rightres3
		 */
	public void setRightres3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres3 = value;
	}
	
	
	
	/**
	 * rightres3
	 */
     @WhereSQL(sql="rightres3=:SoccerLive_rightres3")
	public java.lang.String getRightres3() {
		return this.rightres3;
	}
		/**
		 * rightres4
		 */
	public void setRightres4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres4 = value;
	}
	
	
	
	/**
	 * rightres4
	 */
     @WhereSQL(sql="rightres4=:SoccerLive_rightres4")
	public java.lang.String getRightres4() {
		return this.rightres4;
	}
		/**
		 * rightres5
		 */
	public void setRightres5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres5 = value;
	}
	
	
	
	/**
	 * rightres5
	 */
     @WhereSQL(sql="rightres5=:SoccerLive_rightres5")
	public java.lang.String getRightres5() {
		return this.rightres5;
	}
		/**
		 * rightres6
		 */
	public void setRightres6(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres6 = value;
	}
	
	
	
	/**
	 * rightres6
	 */
     @WhereSQL(sql="rightres6=:SoccerLive_rightres6")
	public java.lang.String getRightres6() {
		return this.rightres6;
	}
		/**
		 * rightres7
		 */
	public void setRightres7(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightres7 = value;
	}
	
	
	
	/**
	 * rightres7
	 */
     @WhereSQL(sql="rightres7=:SoccerLive_rightres7")
	public java.lang.String getRightres7() {
		return this.rightres7;
	}
		/**
		 * shemeng
		 */
	public void setShemeng(java.lang.String value) {
		this.shemeng = value;
	}
	
	
	
	/**
	 * shemeng
	 */
     @WhereSQL(sql="shemeng=:SoccerLive_shemeng")
	public java.lang.String getShemeng() {
		return this.shemeng;
	}
		/**
		 * shezheng
		 */
	public void setShezheng(java.lang.String value) {
		this.shezheng = value;
	}
	
	
	
	/**
	 * shezheng
	 */
     @WhereSQL(sql="shezheng=:SoccerLive_shezheng")
	public java.lang.String getShezheng() {
		return this.shezheng;
	}
		/**
		 * fangui
		 */
	public void setFangui(java.lang.String value) {
		this.fangui = value;
	}
	
	
	
	/**
	 * fangui
	 */
     @WhereSQL(sql="fangui=:SoccerLive_fangui")
	public java.lang.String getFangui() {
		return this.fangui;
	}
		/**
		 * jiaoqiu
		 */
	public void setJiaoqiu(java.lang.String value) {
		this.jiaoqiu = value;
	}
	
	
	
	/**
	 * jiaoqiu
	 */
     @WhereSQL(sql="jiaoqiu=:SoccerLive_jiaoqiu")
	public java.lang.String getJiaoqiu() {
		return this.jiaoqiu;
	}
		/**
		 * yuewei
		 */
	public void setYuewei(java.lang.String value) {
		this.yuewei = value;
	}
	
	
	
	/**
	 * yuewei
	 */
     @WhereSQL(sql="yuewei=:SoccerLive_yuewei")
	public java.lang.String getYuewei() {
		return this.yuewei;
	}
		/**
		 * red
		 */
	public void setRed(java.lang.String value) {
		this.red = value;
	}
	
	
	
	/**
	 * red
	 */
     @WhereSQL(sql="red=:SoccerLive_red")
	public java.lang.String getRed() {
		return this.red;
	}
		/**
		 * yellow
		 */
	public void setYellow(java.lang.String value) {
		this.yellow = value;
	}
	
	
	
	/**
	 * yellow
	 */
     @WhereSQL(sql="yellow=:SoccerLive_yellow")
	public java.lang.String getYellow() {
		return this.yellow;
	}
		/**
		 * jinggong
		 */
	public void setJinggong(java.lang.String value) {
		this.jinggong = value;
	}
	
	
	
	/**
	 * jinggong
	 */
     @WhereSQL(sql="jinggong=:SoccerLive_jinggong")
	public java.lang.String getJinggong() {
		return this.jinggong;
	}
		/**
		 * weixiejinggong
		 */
	public void setWeixiejinggong(java.lang.String value) {
		this.weixiejinggong = value;
	}
	
	
	
	/**
	 * weixiejinggong
	 */
     @WhereSQL(sql="weixiejinggong=:SoccerLive_weixiejinggong")
	public java.lang.String getWeixiejinggong() {
		return this.weixiejinggong;
	}
		/**
		 * renyiqiu
		 */
	public void setRenyiqiu(java.lang.String value) {
		this.renyiqiu = value;
	}
	
	
	
	/**
	 * renyiqiu
	 */
     @WhereSQL(sql="renyiqiu=:SoccerLive_renyiqiu")
	public java.lang.String getRenyiqiu() {
		return this.renyiqiu;
	}
		/**
		 * jiuqiu
		 */
	public void setJiuqiu(java.lang.String value) {
		this.jiuqiu = value;
	}
	
	
	
	/**
	 * jiuqiu
	 */
     @WhereSQL(sql="jiuqiu=:SoccerLive_jiuqiu")
	public java.lang.String getJiuqiu() {
		return this.jiuqiu;
	}
		/**
		 * kongqiulv
		 */
	public void setKongqiulv(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.kongqiulv = value;
	}
	
	
	
	/**
	 * kongqiulv
	 */
     @WhereSQL(sql="kongqiulv=:SoccerLive_kongqiulv")
	public java.lang.String getKongqiulv() {
		return this.kongqiulv;
	}
		/**
		 * 比赛进行的时间
		 */
	public void setTime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.time = value;
	}
	
	
	
	/**
	 * 比赛进行的时间
	 */
     @WhereSQL(sql="time=:SoccerLive_time")
	public java.lang.String getTime() {
		return this.time;
	}
		/**
		 * fid
		 */
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
	
	
	/**
	 * fid
	 */
     @WhereSQL(sql="fid=:SoccerLive_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
     
     
     @WhereSQL(sql="state=:SoccerLive_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}
	
	 @WhereSQL(sql="leaguename=:SoccerLive_leaguename")
	public java.lang.String getLeaguename() {
		return leaguename;
	}

	public void setLeaguename(java.lang.String leaguename) {
		this.leaguename = leaguename;
	}

	public java.lang.String getRealscore() {
		return realscore;
	}

	public void setRealscore(java.lang.String realscore) {
		this.realscore = realscore;
	}
	
	public java.lang.String getHalfscore() {
		return halfscore;
	}

	public void setHalfscore(java.lang.String halfscore) {
		this.halfscore = halfscore;
	}

	
	public String getLeftres8() {
		return leftres8;
	}

	public void setLeftres8(String leftres8) {
		this.leftres8 = leftres8;
	}

	public String getLeftres9() {
		return leftres9;
	}

	public void setLeftres9(String leftres9) {
		this.leftres9 = leftres9;
	}

	public String getLeftres10() {
		return leftres10;
	}

	public void setLeftres10(String leftres10) {
		this.leftres10 = leftres10;
	}

	public String getLeftres11() {
		return leftres11;
	}

	public void setLeftres11(String leftres11) {
		this.leftres11 = leftres11;
	}

	public String getLeftres12() {
		return leftres12;
	}

	public void setLeftres12(String leftres12) {
		this.leftres12 = leftres12;
	}

	public String getRightres8() {
		return rightres8;
	}

	public void setRightres8(String rightres8) {
		this.rightres8 = rightres8;
	}

	public String getRightres9() {
		return rightres9;
	}

	public void setRightres9(String rightres9) {
		this.rightres9 = rightres9;
	}

	public String getRightres10() {
		return rightres10;
	}

	public void setRightres10(String rightres10) {
		this.rightres10 = rightres10;
	}

	public String getRightres11() {
		return rightres11;
	}

	public void setRightres11(String rightres11) {
		this.rightres11 = rightres11;
	}

	public String getRightres12() {
		return rightres12;
	}

	public void setRightres12(String rightres12) {
		this.rightres12 = rightres12;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("mid[").append(getMid()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("starttime[").append(getStarttime()).append("],")
			.append("leftteam[").append(getLeftteam()).append("],")
			.append("rightteam[").append(getRightteam()).append("],")
			.append("state[").append(getState()).append("],")
			.append("比赛进行的时间[").append(getTime()).append("],")
			.append("fid[").append(getFid()).append("],")
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
		if(obj instanceof SoccerLive == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLive other = (SoccerLive)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
