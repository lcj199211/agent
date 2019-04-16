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
 * @version  2017-08-31 10:10:59
 * @see org.springrain.lottery.entity.BetReportformDistributor
 */
@Table(name="bet_reportform_distributor")
public class BetReportformDistributor  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "分销代理报表\r\n";
	public static final String ALIAS_ID = "分销代理表id";
	public static final String ALIAS_DISTRIBUTORID2 = "分销商id";
	public static final String ALIAS_DISTRIBUTORNICKNAME = "分销代理昵称";
	public static final String ALIAS_RECOMMENDNUM = "推广人数";
	public static final String ALIAS_SB = "投注流水";
	public static final String ALIAS_SC = "充值";
	public static final String ALIAS_SW = "游戏输赢";
	public static final String ALIAS_TIME = "时间";
	public static final String ALIAS_AGENTID = "agentid";
	public static final String ALIAS_AGENTPARENTID = "agentparentid";
	public static final String ALIAS_AGENTPARENTIDS = "agentparentids";
	public static final String ALIAS_SUBDISTRIBUTORID2 = "下级ID2";
	public static final String ALIAS_SUBDISTRIBUTORNICKNAME = "下级昵称";
    */
	//date formats
	//public static final String FORMAT_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 分销代理表id
	 */
	private java.lang.Integer id;
	/**
	 * 分销商id
	 */
	private java.lang.Integer distributorid2;
	/**
	 * 分销代理昵称
	 */
	private java.lang.String distributornickname;
	/**
	 * 推广人数
	 */
	private java.lang.Integer recommendnum;
	/**
	 * 投注流水
	 */
	private java.math.BigDecimal sb;
	/**
	 * 充值
	 */
	private java.math.BigDecimal sc;
	/**
	 * 游戏输赢
	 */
	private java.math.BigDecimal sw;
	/**
	 * 时间
	 */
	private java.util.Date time;
	/**
	 * agentid
	 */
	private java.lang.String agentid;
	/**
	 * agentparentid
	 */
	private java.lang.String agentparentid;
	/**
	 * agentparentids
	 */
	private java.lang.String agentparentids;
	/**
	 * 下级ID2
	 */
	private java.lang.Integer subdistributorid2;
	/**
	 * 下级昵称
	 */
	private java.lang.String subdistributornickname;
	//columns END 数据库字段结束
	
	//concstructor

	public BetReportformDistributor(){
	}

	public BetReportformDistributor(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetReportformDistributor_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setDistributorid2(java.lang.Integer value) {
		this.distributorid2 = value;
	}
	
     @WhereSQL(sql="distributorid2=:BetReportformDistributor_distributorid2")
	public java.lang.Integer getDistributorid2() {
		return this.distributorid2;
	}
	public void setDistributornickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.distributornickname = value;
	}
	
     @WhereSQL(sql="distributornickname=:BetReportformDistributor_distributornickname")
	public java.lang.String getDistributornickname() {
		return this.distributornickname;
	}
	public void setRecommendnum(java.lang.Integer value) {
		this.recommendnum = value;
	}
	
     @WhereSQL(sql="recommendnum=:BetReportformDistributor_recommendnum")
	public java.lang.Integer getRecommendnum() {
		return this.recommendnum;
	}
	public void setSb(java.math.BigDecimal value) {
		this.sb = value;
	}
	
     @WhereSQL(sql="sb=:BetReportformDistributor_sb")
	public java.math.BigDecimal getSb() {
		return this.sb;
	}
	public void setSc(java.math.BigDecimal value) {
		this.sc = value;
	}
	
     @WhereSQL(sql="sc=:BetReportformDistributor_sc")
	public java.math.BigDecimal getSc() {
		return this.sc;
	}
	public void setSw(java.math.BigDecimal value) {
		this.sw = value;
	}
	
     @WhereSQL(sql="sw=:BetReportformDistributor_sw")
	public java.math.BigDecimal getSw() {
		return this.sw;
	}
		/*
	public String gettimeString() {
		return DateUtils.convertDate2String(FORMAT_TIME, gettime());
	}
	public void settimeString(String value) throws ParseException{
		settime(DateUtils.convertString2Date(FORMAT_TIME,value));
	}*/
	
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	
     @WhereSQL(sql="time=:BetReportformDistributor_time")
	public java.util.Date getTime() {
		return this.time;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetReportformDistributor_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetReportformDistributor_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetReportformDistributor_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setSubdistributorid2(java.lang.Integer value) {
		this.subdistributorid2 = value;
	}
	
     @WhereSQL(sql="subdistributorid2=:BetReportformDistributor_subdistributorid2")
	public java.lang.Integer getSubdistributorid2() {
		return this.subdistributorid2;
	}
	public void setSubdistributornickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.subdistributornickname = value;
	}
	
     @WhereSQL(sql="subdistributornickname=:BetReportformDistributor_subdistributornickname")
	public java.lang.String getSubdistributornickname() {
		return this.subdistributornickname;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("分销代理表id[").append(getId()).append("],")
			.append("分销商id[").append(getDistributorid2()).append("],")
			.append("分销代理昵称[").append(getDistributornickname()).append("],")
			.append("推广人数[").append(getRecommendnum()).append("],")
			.append("投注流水[").append(getSb()).append("],")
			.append("充值[").append(getSc()).append("],")
			.append("游戏输赢[").append(getSw()).append("],")
			.append("时间[").append(getTime()).append("],")
			.append("agentid[").append(getAgentid()).append("],")
			.append("agentparentid[").append(getAgentparentid()).append("],")
			.append("agentparentids[").append(getAgentparentids()).append("],")
			.append("下级ID2[").append(getSubdistributorid2()).append("],")
			.append("下级昵称[").append(getSubdistributornickname()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetReportformDistributor == false) return false;
		if(this == obj) return true;
		BetReportformDistributor other = (BetReportformDistributor)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
