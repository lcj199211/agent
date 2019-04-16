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
 * @version  2017-08-30 10:10:03
 * @see org.springrain.lottery.entity.SoccerLeagueOnewin
 */
@Table(name="soccer_league_onewin")
public class SoccerLeagueOnewin  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueOnewin";
	public static final String ALIAS_ID = "一场制胜表id";
	public static final String ALIAS_MID = "mid(来自500)";
	public static final String ALIAS_ZID = "zid(来自500)";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_ARRANGEID2 = "赛事id2(来自500)";
	public static final String ALIAS_LETPOINTS = "让球数";
	public static final String ALIAS_WIN = "胜赔率";
	public static final String ALIAS_FLAT = "平赔率";
	public static final String ALIAS_LOSE = "负赔率";
	public static final String ALIAS_DATE = "更新时间";
	public static final String ALIAS_STATE = "状态1:正常3:删除";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 一场制胜表id
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
	 * 让球数
	 */
	private java.lang.String letpoints;
	/**
	 * 胜赔率
	 */
	private java.lang.Double win;
	/**
	 * 平赔率
	 */
	private java.lang.Double flat;
	/**
	 * 负赔率
	 */
	private java.lang.Double lose;
	/**
	 * 更新时间
	 */
	private java.util.Date date;
	/**
	 * 状态1:正常3:删除
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLeagueOnewin(){
	}

	public SoccerLeagueOnewin(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 一场制胜表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 一场制胜表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueOnewin_id")
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
     @WhereSQL(sql="mid=:SoccerLeagueOnewin_mid")
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
     @WhereSQL(sql="zid=:SoccerLeagueOnewin_zid")
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
     @WhereSQL(sql="playmethodid=:SoccerLeagueOnewin_playmethodid")
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
     @WhereSQL(sql="arrangeid2=:SoccerLeagueOnewin_arrangeid2")
	public java.lang.String getArrangeid2() {
		return this.arrangeid2;
	}
		/**
		 * 让球数
		 */
	public void setLetpoints(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.letpoints = value;
	}
	
	
	
	/**
	 * 让球数
	 */
     @WhereSQL(sql="letpoints=:SoccerLeagueOnewin_letpoints")
	public java.lang.String getLetpoints() {
		return this.letpoints;
	}
		/**
		 * 胜赔率
		 */
	public void setWin(java.lang.Double value) {
		this.win = value;
	}
	
	
	
	/**
	 * 胜赔率
	 */
     @WhereSQL(sql="win=:SoccerLeagueOnewin_win")
	public java.lang.Double getWin() {
		return this.win;
	}
		/**
		 * 平赔率
		 */
	public void setFlat(java.lang.Double value) {
		this.flat = value;
	}
	
	
	
	/**
	 * 平赔率
	 */
     @WhereSQL(sql="flat=:SoccerLeagueOnewin_flat")
	public java.lang.Double getFlat() {
		return this.flat;
	}
		/**
		 * 负赔率
		 */
	public void setLose(java.lang.Double value) {
		this.lose = value;
	}
	
	
	
	/**
	 * 负赔率
	 */
     @WhereSQL(sql="lose=:SoccerLeagueOnewin_lose")
	public java.lang.Double getLose() {
		return this.lose;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
		/**
		 * 更新时间
		 */
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	
	
	/**
	 * 更新时间
	 */
     @WhereSQL(sql="date=:SoccerLeagueOnewin_date")
	public java.util.Date getDate() {
		return this.date;
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
     @WhereSQL(sql="state=:SoccerLeagueOnewin_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("一场制胜表id[").append(getId()).append("],")
			.append("mid(来自500)[").append(getMid()).append("],")
			.append("zid(来自500)[").append(getZid()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("赛事id2(来自500)[").append(getArrangeid2()).append("],")
			.append("让球数[").append(getLetpoints()).append("],")
			.append("胜赔率[").append(getWin()).append("],")
			.append("平赔率[").append(getFlat()).append("],")
			.append("负赔率[").append(getLose()).append("],")
			.append("更新时间[").append(getDate()).append("],")
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
		if(obj instanceof SoccerLeagueOnewin == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueOnewin other = (SoccerLeagueOnewin)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
