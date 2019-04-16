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
 * @version  2017-11-10 09:23:04
 * @see org.springrain.lottery.entity.BasketballLeagueResult
 */
@Table(name="basketball_league_result")
public class BasketballLeagueResult  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "篮球比分结果";
	public static final String ALIAS_ID = "比分表id";
	public static final String ALIAS_ZID = "zid";
	public static final String ALIAS_NUM = "编号";
	public static final String ALIAS_MATCHNAME = "赛事";
	public static final String ALIAS_STARTTIME = "开赛时间";
	public static final String ALIAS_AWAYTEAM = "客队";
	public static final String ALIAS_HOMETEAM = "主队";
	public static final String ALIAS_SCORE = "得分";
	public static final String ALIAS_SF = "胜负";
	public static final String ALIAS_LETPOINTS = "让分";
	public static final String ALIAS_RFSF = "让分胜负";
	public static final String ALIAS_SFC = "胜分差";
	public static final String ALIAS_DXF = "大小的预设";
	public static final String ALIAS_DXFRESULT = "大小结果";
	public static final String ALIAS_CREATEDATE = "抓取时间";
	public static final String ALIAS_STATE = "状态1.正常 3.删除";
	public static final String ALIAS_ISSETTLE = "1已结算 3未结算";
    */
	//date formats
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 比分表id
	 */
	private java.lang.Integer id;
	/**
	 * zid
	 */
	private java.lang.String zid;
	/**
	 * 编号
	 */
	private java.lang.String num;
	/**
	 * 赛事
	 */
	private java.lang.String matchname;
	/**
	 * 开赛时间
	 */
	private java.util.Date starttime;
	/**
	 * 客队
	 */
	private java.lang.String awayteam;
	private java.lang.String awayteamid2;
	/**
	 * 主队
	 */
	private java.lang.String hometeam;
	private java.lang.String hometeamid2;
	/**
	 * 得分
	 */
	private java.lang.String score;
	/**
	 * 胜负
	 */
	private java.lang.String sf;
	/**
	 * 让分
	 */
	private java.lang.String letpoints;
	/**
	 * 让分胜负
	 */
	private java.lang.String rfsf;
	/**
	 * 胜分差
	 */
	private java.lang.String sfc;
	/**
	 * 大小的预设
	 */
	private java.lang.String dxf;
	/**
	 * 大小结果
	 */
	private java.lang.String dxfresult;
	/**
	 * 抓取时间
	 */
	private java.util.Date createdate;
	/**
	 * 状态1.已确认 3.未确认
	 */
	private java.lang.Integer state;
	/**
	 * 1已结算 3未结算
	 */
	private java.lang.Integer issettle;
	//columns END 数据库字段结束
	
	/**
	 * 哪天的比赛
	 */
	private java.util.Date matchdate;
	
	
	
	//concstructor

	@Transient
	public java.util.Date getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(java.util.Date matchdate) {
		this.matchdate = matchdate;
	}

	public BasketballLeagueResult(){
	}

	public BasketballLeagueResult(
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
     @WhereSQL(sql="id=:BasketballLeagueResult_id")
	public java.lang.Integer getId() {
		return this.id;
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
     @WhereSQL(sql="zid=:BasketballLeagueResult_zid")
	public java.lang.String getZid() {
		return this.zid;
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
     @WhereSQL(sql="num=:BasketballLeagueResult_num")
	public java.lang.String getNum() {
		return this.num;
	}
		/**
		 * 赛事
		 */
	public void setMatchname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.matchname = value;
	}
	
	
	
	/**
	 * 赛事
	 */
     @WhereSQL(sql="matchname=:BasketballLeagueResult_matchname")
	public java.lang.String getMatchname() {
		return this.matchname;
	}
		/*
	public String getstarttimeString() {
		return DateUtils.convertDate2String(FORMAT_STARTTIME, getstarttime());
	}
	public void setstarttimeString(String value) throws ParseException{
		setstarttime(DateUtils.convertString2Date(FORMAT_STARTTIME,value));
	}*/
	
		/**
		 * 开赛时间
		 */
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	
	
	/**
	 * 开赛时间
	 */
     @WhereSQL(sql="starttime=:BasketballLeagueResult_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
		/**
		 * 客队
		 */
	public void setAwayteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.awayteam = value;
	}
	
	
	
	/**
	 * 客队
	 */
     @WhereSQL(sql="awayteam=:BasketballLeagueResult_awayteam")
	public java.lang.String getAwayteam() {
		return this.awayteam;
	}
		/**
		 * 主队
		 */
	public void setHometeam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hometeam = value;
	}
	
	
	
	/**
	 * 主队
	 */
     @WhereSQL(sql="hometeam=:BasketballLeagueResult_hometeam")
	public java.lang.String getHometeam() {
		return this.hometeam;
	}
		/**
		 * 得分
		 */
	public void setScore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.score = value;
	}
	
	
	
	/**
	 * 得分
	 */
     @WhereSQL(sql="score=:BasketballLeagueResult_score")
	public java.lang.String getScore() {
		return this.score;
	}
		/**
		 * 胜负
		 */
	public void setSf(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sf = value;
	}
	
	
	
	/**
	 * 胜负
	 */
     @WhereSQL(sql="sf=:BasketballLeagueResult_sf")
	public java.lang.String getSf() {
		return this.sf;
	}
		/**
		 * 让分
		 */
	public void setLetpoints(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.letpoints = value;
	}
	
	
	
	/**
	 * 让分
	 */
     @WhereSQL(sql="letpoints=:BasketballLeagueResult_letpoints")
	public java.lang.String getLetpoints() {
		return this.letpoints;
	}
		/**
		 * 让分胜负
		 */
	public void setRfsf(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rfsf = value;
	}
	
	
	
	/**
	 * 让分胜负
	 */
     @WhereSQL(sql="rfsf=:BasketballLeagueResult_rfsf")
	public java.lang.String getRfsf() {
		return this.rfsf;
	}
		/**
		 * 胜分差
		 */
	public void setSfc(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sfc = value;
	}
	
	
	
	/**
	 * 胜分差
	 */
     @WhereSQL(sql="sfc=:BasketballLeagueResult_sfc")
	public java.lang.String getSfc() {
		return this.sfc;
	}
		/**
		 * 大小的预设
		 */
	public void setDxf(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.dxf = value;
	}
	
	
	
	/**
	 * 大小的预设
	 */
     @WhereSQL(sql="dxf=:BasketballLeagueResult_dxf")
	public java.lang.String getDxf() {
		return this.dxf;
	}
		/**
		 * 大小结果
		 */
	public void setDxfresult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.dxfresult = value;
	}
	
	
	
	/**
	 * 大小结果
	 */
     @WhereSQL(sql="dxfresult=:BasketballLeagueResult_dxfresult")
	public java.lang.String getDxfresult() {
		return this.dxfresult;
	}
		/*
	public String getcreatedateString() {
		return DateUtils.convertDate2String(FORMAT_CREATEDATE, getcreatedate());
	}
	public void setcreatedateString(String value) throws ParseException{
		setcreatedate(DateUtils.convertString2Date(FORMAT_CREATEDATE,value));
	}*/
	
		/**
		 * 抓取时间
		 */
	public void setCreatedate(java.util.Date value) {
		this.createdate = value;
	}
	
	
	
	/**
	 * 抓取时间
	 */
     @WhereSQL(sql="createdate=:BasketballLeagueResult_createdate")
	public java.util.Date getCreatedate() {
		return this.createdate;
	}
		/**
		 * 状态1.正常 3.删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 状态1.正常 3.删除
	 */
     @WhereSQL(sql="state=:BasketballLeagueResult_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/**
		 * 1已结算 3未结算
		 */
	public void setIssettle(java.lang.Integer value) {
		this.issettle = value;
	}
	
	
	
	/**
	 * 1已结算 3未结算
	 */
     @WhereSQL(sql="issettle=:BasketballLeagueResult_issettle")
	public java.lang.Integer getIssettle() {
		return this.issettle;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("比分表id[").append(getId()).append("],")
			.append("zid[").append(getZid()).append("],")
			.append("编号[").append(getNum()).append("],")
			.append("赛事[").append(getMatchname()).append("],")
			.append("开赛时间[").append(getStarttime()).append("],")
			.append("客队[").append(getAwayteam()).append("],")
			.append("主队[").append(getHometeam()).append("],")
			.append("得分[").append(getScore()).append("],")
			.append("胜负[").append(getSf()).append("],")
			.append("让分[").append(getLetpoints()).append("],")
			.append("让分胜负[").append(getRfsf()).append("],")
			.append("胜分差[").append(getSfc()).append("],")
			.append("大小的预设[").append(getDxf()).append("],")
			.append("大小结果[").append(getDxfresult()).append("],")
			.append("抓取时间[").append(getCreatedate()).append("],")
			.append("状态1.正常 3.删除[").append(getState()).append("],")
			.append("1已结算 3未结算[").append(getIssettle()).append("],")
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
		if(obj instanceof BasketballLeagueResult == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		BasketballLeagueResult other = (BasketballLeagueResult)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.String getAwayteamid2() {
		return awayteamid2;
	}

	public void setAwayteamid2(java.lang.String awayteamid2) {
		this.awayteamid2 = awayteamid2;
	}

	public java.lang.String getHometeamid2() {
		return hometeamid2;
	}

	public void setHometeamid2(java.lang.String hometeamid2) {
		this.hometeamid2 = hometeamid2;
	}
}

	
