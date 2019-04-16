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
 * @version  2018-01-24 10:41:36
 * @see org.springrain.lottery.entity.BetAgentreportformJc
 */
@Table(name="bet_agentreportform_jc")
public class BetAgentreportformJc  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "报表";
	public static final String ALIAS_ID = "报表ID";
	public static final String ALIAS_DATE = "日期";
	public static final String ALIAS_BETTINGMONEY = "投注额";
	public static final String ALIAS_UNTREATEDBETTINGMONEY = "未结算";
	public static final String ALIAS_TREATEDBETTINGMONEY = "已结算";
	public static final String ALIAS_BETTINGSCORE = "派彩";
	public static final String ALIAS_BETTINGWIN = "输赢";
	public static final String ALIAS_BETTINGTIMECOMMISSION = "投注佣金";
	public static final String ALIAS_BETTINGTIMECOMMISSION1 = "直属投注佣金";
	public static final String ALIAS_TRANSFERACCOUNTSSCORE = "转账";
	public static final String ALIAS_ISAGENT = "是否是代理,1:代理，0:会员";
	public static final String ALIAS_AGENTACCOUNT = "代理账号";
	public static final String ALIAS_AGENTNICKNAME = "代理昵称";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父级id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商ID 从根节点开始";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 报表ID
	 */
	private java.lang.Integer id;
	/**
	 * 日期
	 */
	private java.util.Date date;
	/**
	 * 投注额
	 */
	private java.math.BigDecimal bettingmoney;
	/**
	 * 未结算
	 */
	private java.math.BigDecimal untreatedbettingmoney;
	/**
	 * 已结算
	 */
	private java.math.BigDecimal treatedbettingmoney;
	/**
	 * 派彩
	 */
	private java.math.BigDecimal bettingscore;
	/**
	 * 输赢
	 */
	private java.math.BigDecimal bettingwin;
	/**
	 * 投注佣金
	 */
	private java.math.BigDecimal bettingtimecommission;
	/**
	 * 直属投注佣金
	 */
	private java.math.BigDecimal bettingtimecommission1;
	/**
	 * 转账
	 */
	private java.math.BigDecimal transferaccountsscore;
	/**
	 * 是否是代理,1:代理，0:会员
	 */
	private java.lang.Integer isagent;
	/**
	 * 代理账号
	 */
	private java.lang.String agentaccount;
	/**
	 * 代理昵称
	 */
	private java.lang.String agentnickname;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父级id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商ID 从根节点开始
	 */
	private java.lang.String agentparentids;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAgentreportformJc(){
	}

	public BetAgentreportformJc(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentreportformJc_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/*
	public String getdateString() {
		return DateUtils.convertDate2String(FORMAT_DATE, getdate());
	}
	public void setdateString(String value) throws ParseException{
		setdate(DateUtils.convertString2Date(FORMAT_DATE,value));
	}*/
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
     @WhereSQL(sql="date=:BetAgentreportformJc_date")
	public java.util.Date getDate() {
		return this.date;
	}
	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}
	
     @WhereSQL(sql="bettingmoney=:BetAgentreportformJc_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}
	public void setUntreatedbettingmoney(java.math.BigDecimal value) {
		this.untreatedbettingmoney = value;
	}
	
     @WhereSQL(sql="untreatedbettingmoney=:BetAgentreportformJc_untreatedbettingmoney")
	public java.math.BigDecimal getUntreatedbettingmoney() {
		return this.untreatedbettingmoney;
	}
	public void setTreatedbettingmoney(java.math.BigDecimal value) {
		this.treatedbettingmoney = value;
	}
	
     @WhereSQL(sql="treatedbettingmoney=:BetAgentreportformJc_treatedbettingmoney")
	public java.math.BigDecimal getTreatedbettingmoney() {
		return this.treatedbettingmoney;
	}
	public void setBettingscore(java.math.BigDecimal value) {
		this.bettingscore = value;
	}
	
     @WhereSQL(sql="bettingscore=:BetAgentreportformJc_bettingscore")
	public java.math.BigDecimal getBettingscore() {
		return this.bettingscore;
	}
	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}
	
     @WhereSQL(sql="bettingwin=:BetAgentreportformJc_bettingwin")
	public java.math.BigDecimal getBettingwin() {
		return this.bettingwin;
	}
	public void setBettingtimecommission(java.math.BigDecimal value) {
		this.bettingtimecommission = value;
	}
	
     @WhereSQL(sql="bettingtimecommission=:BetAgentreportformJc_bettingtimecommission")
	public java.math.BigDecimal getBettingtimecommission() {
		return this.bettingtimecommission;
	}
	public void setBettingtimecommission1(java.math.BigDecimal value) {
		this.bettingtimecommission1 = value;
	}
	
     @WhereSQL(sql="bettingtimecommission1=:BetAgentreportformJc_bettingtimecommission1")
	public java.math.BigDecimal getBettingtimecommission1() {
		return this.bettingtimecommission1;
	}
	public void setTransferaccountsscore(java.math.BigDecimal value) {
		this.transferaccountsscore = value;
	}
	
     @WhereSQL(sql="transferaccountsscore=:BetAgentreportformJc_transferaccountsscore")
	public java.math.BigDecimal getTransferaccountsscore() {
		return this.transferaccountsscore;
	}
	public void setIsagent(java.lang.Integer value) {
		this.isagent = value;
	}
	
     @WhereSQL(sql="isagent=:BetAgentreportformJc_isagent")
	public java.lang.Integer getIsagent() {
		return this.isagent;
	}
	public void setAgentaccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentaccount = value;
	}
	
     @WhereSQL(sql="agentaccount=:BetAgentreportformJc_agentaccount")
	public java.lang.String getAgentaccount() {
		return this.agentaccount;
	}
	public void setAgentnickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentnickname = value;
	}
	
     @WhereSQL(sql="agentnickname=:BetAgentreportformJc_agentnickname")
	public java.lang.String getAgentnickname() {
		return this.agentnickname;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetAgentreportformJc_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetAgentreportformJc_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetAgentreportformJc_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("报表ID[").append(getId()).append("],")
			.append("日期[").append(getDate()).append("],")
			.append("投注额[").append(getBettingmoney()).append("],")
			.append("未结算[").append(getUntreatedbettingmoney()).append("],")
			.append("已结算[").append(getTreatedbettingmoney()).append("],")
			.append("派彩[").append(getBettingscore()).append("],")
			.append("输赢[").append(getBettingwin()).append("],")
			.append("投注佣金[").append(getBettingtimecommission()).append("],")
			.append("直属投注佣金[").append(getBettingtimecommission1()).append("],")
			.append("转账[").append(getTransferaccountsscore()).append("],")
			.append("是否是代理,1:代理，0:会员[").append(getIsagent()).append("],")
			.append("代理账号[").append(getAgentaccount()).append("],")
			.append("代理昵称[").append(getAgentnickname()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父级id[").append(getAgentparentid()).append("],")
			.append("代理商ID 从根节点开始[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentreportformJc == false) return false;
		if(this == obj) return true;
		BetAgentreportformJc other = (BetAgentreportformJc)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
