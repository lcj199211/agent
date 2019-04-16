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
 * @version  2017-07-19 17:45:45
 * @see org.springrain.lottery.entity.BetReportformsAgent
 */
@Table(name="bet_reportforms_agent")
public class BetReportformsAgent  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetReportformsAgent";
	public static final String ALIAS_ID = "代理商报表id";
	public static final String ALIAS_DATE = "日期";
	public static final String ALIAS_MEMBERNUM = "下线";
	public static final String ALIAS_RECHARGE = "充值";
	public static final String ALIAS_RECHARGEYJ = "充值佣金";
	public static final String ALIAS_WITHDRAW = "提现";
	public static final String ALIAS_BETMONEY = "投注额";
	public static final String ALIAS_BETYJ = "投注返利";
	public static final String ALIAS_GAMEWINORLOSE = "游戏输赢";
	public static final String ALIAS_WINORLOSEYJ = "输赢返利";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_PARENTID = "父级代理商id";
	public static final String ALIAS_PARENTIDS = "根节点到父级代理 逗号隔开";
    */
	//date formats
	//public static final String FORMAT_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 代理商报表id
	 */
	private java.lang.Integer id;
	/**
	 * 日期
	 */
	private java.util.Date date;
	/**
	 * 下线
	 */
	private java.lang.Integer membernum;
	/**
	 * 充值
	 */
	private java.lang.Double recharge;
	/**
	 * 充值佣金
	 */
	private java.lang.Double rechargeyj;
	/**
	 * 提现
	 */
	private java.lang.Double withdraw;
	/**
	 * 投注额
	 */
	private java.lang.Double betmoney;
	/**
	 * 投注返利
	 */
	private java.lang.Double betyj;
	/**
	 * 游戏输赢
	 */
	private java.lang.Double gamewinorlose;
	/**
	 * 输赢返利
	 */
	private java.lang.Double winorloseyj;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 父级代理商id
	 */
	private java.lang.String parentid;
	/**
	 * 根节点到父级代理 逗号隔开
	 */
	private java.lang.String parentids;
	//columns END 数据库字段结束
	
	//concstructor

	public BetReportformsAgent(){
	}

	public BetReportformsAgent(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetReportformsAgent_id")
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
	
     @WhereSQL(sql="date=:BetReportformsAgent_date")
	public java.util.Date getDate() {
		return this.date;
	}
	public void setMembernum(java.lang.Integer value) {
		this.membernum = value;
	}
	
     @WhereSQL(sql="membernum=:BetReportformsAgent_membernum")
	public java.lang.Integer getMembernum() {
		return this.membernum;
	}
	public void setRecharge(java.lang.Double value) {
		this.recharge = value;
	}
	
     @WhereSQL(sql="recharge=:BetReportformsAgent_recharge")
	public java.lang.Double getRecharge() {
		return this.recharge;
	}
	public void setRechargeyj(java.lang.Double value) {
		this.rechargeyj = value;
	}
	
     @WhereSQL(sql="rechargeyj=:BetReportformsAgent_rechargeyj")
	public java.lang.Double getRechargeyj() {
		return this.rechargeyj;
	}
	public void setWithdraw(java.lang.Double value) {
		this.withdraw = value;
	}
	
     @WhereSQL(sql="withdraw=:BetReportformsAgent_withdraw")
	public java.lang.Double getWithdraw() {
		return this.withdraw;
	}
	public void setBetmoney(java.lang.Double value) {
		this.betmoney = value;
	}
	
     @WhereSQL(sql="betmoney=:BetReportformsAgent_betmoney")
	public java.lang.Double getBetmoney() {
		return this.betmoney;
	}
	public void setBetyj(java.lang.Double value) {
		this.betyj = value;
	}
	
     @WhereSQL(sql="betyj=:BetReportformsAgent_betyj")
	public java.lang.Double getBetyj() {
		return this.betyj;
	}
	public void setGamewinorlose(java.lang.Double value) {
		this.gamewinorlose = value;
	}
	
     @WhereSQL(sql="gamewinorlose=:BetReportformsAgent_gamewinorlose")
	public java.lang.Double getGamewinorlose() {
		return this.gamewinorlose;
	}
	public void setWinorloseyj(java.lang.Double value) {
		this.winorloseyj = value;
	}
	
     @WhereSQL(sql="winorloseyj=:BetReportformsAgent_winorloseyj")
	public java.lang.Double getWinorloseyj() {
		return this.winorloseyj;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetReportformsAgent_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setParentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.parentid = value;
	}
	
     @WhereSQL(sql="parentid=:BetReportformsAgent_parentid")
	public java.lang.String getParentid() {
		return this.parentid;
	}
	public void setParentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.parentids = value;
	}
	
     @WhereSQL(sql="parentids=:BetReportformsAgent_parentids")
	public java.lang.String getParentids() {
		return this.parentids;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("代理商报表id[").append(getId()).append("],")
			.append("日期[").append(getDate()).append("],")
			.append("下线[").append(getMembernum()).append("],")
			.append("充值[").append(getRecharge()).append("],")
			.append("充值佣金[").append(getRechargeyj()).append("],")
			.append("提现[").append(getWithdraw()).append("],")
			.append("投注额[").append(getBetmoney()).append("],")
			.append("投注返利[").append(getBetyj()).append("],")
			.append("游戏输赢[").append(getGamewinorlose()).append("],")
			.append("输赢返利[").append(getWinorloseyj()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("父级代理商id[").append(getParentid()).append("],")
			.append("根节点到父级代理 逗号隔开[").append(getParentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetReportformsAgent == false) return false;
		if(this == obj) return true;
		BetReportformsAgent other = (BetReportformsAgent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
