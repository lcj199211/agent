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
 * @version  2018-02-23 11:24:47
 * @see org.springrain.lottery.entity.RenjiuMatch
 */
@Table(name="renjiu_match")
public class RenjiuMatch  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "RenjiuMatch";
	public static final String ALIAS_ID = "任9比赛表id";
	public static final String ALIAS_NUM = "编号(竞彩网)";
	public static final String ALIAS_PERIODNUM = "期号";
	public static final String ALIAS_MID = "mid(竞彩网)";
	public static final String ALIAS_LEAGUENAME = "联赛名";
	public static final String ALIAS_LEFTTEAM = "主队";
	public static final String ALIAS_RIGHTTEAM = "客队";
	public static final String ALIAS_STARTTIME = "比赛时间 ";
	public static final String ALIAS_STARTBUYTIME = "开售时间";
	public static final String ALIAS_ENDTIME = "截止时间";
	public static final String ALIAS_CREATTIME = "录入时间";
	public static final String ALIAS_WIN = "胜";
	public static final String ALIAS_FLAT = "平";
	public static final String ALIAS_LOSE = "负";
	public static final String ALIAS_FULL = "比分";
	public static final String ALIAS_RESULT = "3胜 1平 0负";
	public static final String ALIAS_STATE = "1正常 2取消 3删除";
    */
	//date formats
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTBUYTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 任9比赛表id
	 */
	private java.lang.Integer id;
	/**
	 * 编号(竞彩网)
	 */
	private java.lang.String num;
	/**
	 * 期号
	 */
	private java.lang.String periodnum;
	/**
	 * 联赛名
	 */
	private java.lang.String leaguename;
	/**
	 * 主队
	 */
	private java.lang.String leftteam;
	/**
	 * 客队
	 */
	private java.lang.String rightteam;
	/**
	 * 比赛时间 
	 */
	private java.util.Date starttime;
	/**
	 * 截止时间
	 */
	private java.util.Date endtime;
	/**
	 * 录入时间
	 */
	private java.util.Date creattime;
	/**
	 * 胜
	 */
	private java.lang.String win;
	/**
	 * 平
	 */
	private java.lang.String flat;
	/**
	 * 负
	 */
	private java.lang.String lose;
	/**
	 * 比分
	 */
	private java.lang.String full;
	/**
	 * 3胜 1平 0负
	 */
	private java.lang.Integer result;
	/**
	 * 1正常 2取消 3删除
	 */
	private java.lang.Integer state;
	private Integer istrue;
	private String rid;
	//columns END 数据库字段结束
	
	//concstructor

	public RenjiuMatch(){
	}

	public RenjiuMatch(
		java.lang.Integer id,
		java.lang.String num
	){
		this.id = id;
		this.num = num;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:RenjiuMatch_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.num = value;
	}
	
	@Id
     @WhereSQL(sql="num=:RenjiuMatch_num")
	public java.lang.String getNum() {
		return this.num;
	}
	public void setPeriodnum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.periodnum = value;
	}
	
     @WhereSQL(sql="periodnum=:RenjiuMatch_periodnum")
	public java.lang.String getPeriodnum() {
		return this.periodnum;
	}
	public void setLeaguename(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leaguename = value;
	}
	
     @WhereSQL(sql="leaguename=:RenjiuMatch_leaguename")
	public java.lang.String getLeaguename() {
		return this.leaguename;
	}
	public void setLeftteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.leftteam = value;
	}
	
     @WhereSQL(sql="leftteam=:RenjiuMatch_leftteam")
	public java.lang.String getLeftteam() {
		return this.leftteam;
	}
	public void setRightteam(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.rightteam = value;
	}
	
     @WhereSQL(sql="rightteam=:RenjiuMatch_rightteam")
	public java.lang.String getRightteam() {
		return this.rightteam;
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
	
     @WhereSQL(sql="starttime=:RenjiuMatch_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
     @WhereSQL(sql="endtime=:RenjiuMatch_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	
	public void setCreattime(java.util.Date value) {
		this.creattime = value;
	}
	
     @WhereSQL(sql="creattime=:RenjiuMatch_creattime")
	public java.util.Date getCreattime() {
		return this.creattime;
	}
	public void setWin(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.win = value;
	}
	
     @WhereSQL(sql="win=:RenjiuMatch_win")
	public java.lang.String getWin() {
		return this.win;
	}
	public void setFlat(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.flat = value;
	}
	
     @WhereSQL(sql="flat=:RenjiuMatch_flat")
	public java.lang.String getFlat() {
		return this.flat;
	}
	public void setLose(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lose = value;
	}
	
     @WhereSQL(sql="lose=:RenjiuMatch_lose")
	public java.lang.String getLose() {
		return this.lose;
	}
	public void setFull(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.full = value;
	}
	
     @WhereSQL(sql="full=:RenjiuMatch_full")
	public java.lang.String getFull() {
		return this.full;
	}
	public void setResult(java.lang.Integer value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:RenjiuMatch_result")
	public java.lang.Integer getResult() {
		return this.result;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:RenjiuMatch_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     @WhereSQL(sql="istrue=:RenjiuMatch_istrue")
     public Integer getIstrue() {
 		return istrue;
 	}

 	public void setIstrue(Integer istrue) {
 		this.istrue = istrue;
 	}
 	@WhereSQL(sql="rid=:RenjiuMatch_rid")
 	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("任9比赛表id[").append(getId()).append("],")
			.append("编号(竞彩网)[").append(getNum()).append("],")
			.append("期号[").append(getPeriodnum()).append("],")
			.append("联赛名[").append(getLeaguename()).append("],")
			.append("主队[").append(getLeftteam()).append("],")
			.append("客队[").append(getRightteam()).append("],")
			.append("比赛时间 [").append(getStarttime()).append("],")
			.append("截止时间[").append(getEndtime()).append("],")
			.append("录入时间[").append(getCreattime()).append("],")
			.append("胜[").append(getWin()).append("],")
			.append("平[").append(getFlat()).append("],")
			.append("负[").append(getLose()).append("],")
			.append("比分[").append(getFull()).append("],")
			.append("3胜 1平 0负[").append(getResult()).append("],")
			.append("1正常 2取消 3删除[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getNum())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RenjiuMatch == false) return false;
		if(this == obj) return true;
		RenjiuMatch other = (RenjiuMatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getNum(),other.getNum())
			.isEquals();
	}

	
}

	
