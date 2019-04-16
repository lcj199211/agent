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
 * @version  2017-03-23 16:46:05
 * @see org.springrain.lottery.entity.BetGold
 */
@Table(name="bet_gold")
public class BetGold  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "充值";
	public static final String ALIAS_ID = "订单编号";
	public static final String ALIAS_MEMBERID = "用户ID";
	public static final String ALIAS_BRS = "充值前分";
	public static final String ALIAS_ARS = "充值后分";
	public static final String ALIAS_SOURCE = "来源";
	public static final String ALIAS_ACCOUNTNAME = "账户名称";
	public static final String ALIAS_MONEY = "金额";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_SUBMITTIME = "提交时间";
	public static final String ALIAS_RECHARGETIME = "充值时间";
    */
	//date formats
	//public static final String FORMAT_SUBMITTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_RECHARGETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 订单编号
	 */
	private java.lang.String id;
	/**
	 * 用户ID
	 */
	private java.lang.String memberid;
	/**
	 * 充值前分
	 */
	private java.lang.Double brs;
	/**
	 * 充值后分
	 */
	private java.lang.Double ars;
	/**
	 * 来源
	 */
	private java.lang.String source;
	/**
	 * 账户名称
	 */
	private java.lang.String accountname;
	/**
	 * 金额
	 */
	private java.lang.Double money;
	/**
	 * 状态
	 */
	private java.lang.Integer state;
	/**
	 * 提交时间
	 */
	private java.util.Date submittime;
	/**
	 * 充值时间
	 */
	private java.util.Date rechargetime;
	//columns END 数据库字段结束
	private java.lang.String account;
	private java.lang.Integer memberid2;
	private java.lang.String operator;
	private java.lang.String remark;
	private java.lang.Integer isbt;
	private java.lang.Double realmoney;
	private java.lang.Integer type;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	private java.lang.Integer visiblestate;
	private java.lang.Integer isresubmit;
	private java.lang.String agentnickname;
	private String accountnickname;
	private String appid;//支付宝appid
	private java.lang.Integer rgold;
	
	@Transient
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}
	@WhereSQL(sql="isresubmit=:BetGold_isresubmit")
	public java.lang.Integer getIsresubmit() {
		return isresubmit;
	}

	public void setIsresubmit(java.lang.Integer isresubmit) {
		this.isresubmit = isresubmit;
	}

	@WhereSQL(sql="visiblestate=:BetGold_visiblestate")
	public java.lang.Integer getVisiblestate() {
		return visiblestate;
	}

	public void setVisiblestate(java.lang.Integer visiblestate) {
		this.visiblestate = visiblestate;
	}

	@WhereSQL(sql="agentid=:BetGold_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetGold_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetGold_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	@WhereSQL(sql="realmoney=:BetGold_realmoney")
	public java.lang.Double getRealmoney() {
		return realmoney;
	}

	public void setRealmoney(java.lang.Double realmoney) {
		this.realmoney = realmoney;
	}
	@WhereSQL(sql="type=:BetGold_type")
	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	@WhereSQL(sql="operator=:BetGold_operator")
	public java.lang.String getOperator() {
		return operator;
	}

	public void setOperator(java.lang.String operator) {
		this.operator = operator;
	}
	@WhereSQL(sql="remark=:BetGold_remark")
	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	@WhereSQL(sql="isbt=:BetGold_isbt")
	public java.lang.Integer getIsbt() {
		return isbt;
	}

	public void setIsbt(java.lang.Integer isbt) {
		this.isbt = isbt;
	}

	@Transient
	public java.lang.Integer getMemberid2() {
		return memberid2;
	}

	public void setMemberid2(java.lang.Integer memberid2) {
		this.memberid2 = memberid2;
	}

	//concstructor
	@WhereSQL(sql="account=:BetGold_account")
	public java.lang.String getAccount() {
		return account;
	}

	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	public BetGold(){
	}

	public BetGold(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGold_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setMemberid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetGold_memberid")
	public java.lang.String getMemberid() {
		return this.memberid;
	}
	public void setBrs(java.lang.Double value) {
		this.brs = value;
	}
	
     @WhereSQL(sql="brs=:BetGold_brs")
	public java.lang.Double getBrs() {
		return this.brs;
	}
	public void setArs(java.lang.Double value) {
		this.ars = value;
	}
	
     @WhereSQL(sql="ars=:BetGold_ars")
	public java.lang.Double getArs() {
		return this.ars;
	}
	public void setSource(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.source = value;
	}
	
     @WhereSQL(sql="source=:BetGold_source")
	public java.lang.String getSource() {
		return this.source;
	}
	public void setAccountname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.accountname = value;
	}
	
     @WhereSQL(sql="accountname=:BetGold_accountname")
	public java.lang.String getAccountname() {
		return this.accountname;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetGold_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetGold_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/*
	public String getsubmittimeString() {
		return DateUtils.convertDate2String(FORMAT_SUBMITTIME, getsubmittime());
	}
	public void setsubmittimeString(String value) throws ParseException{
		setsubmittime(DateUtils.convertString2Date(FORMAT_SUBMITTIME,value));
	}*/
	
	public void setSubmittime(java.util.Date value) {
		this.submittime = value;
	}
	
     @WhereSQL(sql="submittime=:BetGold_submittime")
	public java.util.Date getSubmittime() {
		return this.submittime;
	}
		/*
	public String getrechargetimeString() {
		return DateUtils.convertDate2String(FORMAT_RECHARGETIME, getrechargetime());
	}
	public void setrechargetimeString(String value) throws ParseException{
		setrechargetime(DateUtils.convertString2Date(FORMAT_RECHARGETIME,value));
	}*/
	
	public void setRechargetime(java.util.Date value) {
		this.rechargetime = value;
	}
	
     @WhereSQL(sql="rechargetime=:BetGold_rechargetime")
	public java.util.Date getRechargetime() {
		return this.rechargetime;
	}
     @WhereSQL(sql="appid=:BetGold_appid")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("订单编号[").append(getId()).append("],")
			.append("用户ID[").append(getMemberid()).append("],")
			.append("充值前分[").append(getBrs()).append("],")
			.append("充值后分[").append(getArs()).append("],")
			.append("来源[").append(getSource()).append("],")
			.append("账户名称[").append(getAccountname()).append("],")
			.append("金额[").append(getMoney()).append("],")
			.append("状态[").append(getState()).append("],")
			.append("提交时间[").append(getSubmittime()).append("],")
			.append("充值时间[").append(getRechargetime()).append("],")
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
		if(obj instanceof BetGold == false) return false;
		if(this == obj) return true;
		BetGold other = (BetGold)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@WhereSQL(sql="rgold=:BetGold_rgold")
	public java.lang.Integer getRgold() {
		return rgold;
	}

	public void setRgold(java.lang.Integer rgold) {
		this.rgold = rgold;
	}
	@WhereSQL(sql="accountnickname=:BetGold_accountnickname")
	public String getAccountnickname() {
		return accountnickname;
	}

	public void setAccountnickname(String accountnickname) {
		this.accountnickname = accountnickname;
	}
}

	
