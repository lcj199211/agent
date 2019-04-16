package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-04-12 10:30:52
 * @see org.springrain.lottery.entity.BetCentralbank
 */
@Table(name = "bet_centralbank")
public class BetCentralbank extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "中央银行"; public static final
	 * String ALIAS_MEMBERID2 = "会员ID"; public static final String ALIAS_SYSTEM
	 * = "系统"; public static final String ALIAS_ADMINTRANSFER = "管理员转账"; public
	 * static final String ALIAS_ONLINERECHARGE = "在线充值"; public static final
	 * String ALIAS_CARDRECHARGE = "点卡充值"; public static final String
	 * ALIAS_FROZENSCORE = "冻结分数"; public static final String ALIAS_MEMBERSCORE
	 * = "用户总分"; public static final String ALIAS_CENTRALBANK = "中央银行"; public
	 * static final String ALIAS_RELIEFCOLLECTION = "救济领取"; public static final
	 * String ALIAS_SIGNINCOLLECTION = "签到领取"; public static final String
	 * ALIAS_SUBORDINATEREWARD = "下线奖励"; public static final String
	 * ALIAS_WEEKREBATE = "周返利"; public static final String
	 * ALIAS_DAYWINORLOSSREBATE = "日输赢返利"; public static final String
	 * ALIAS_THEFIRSTCHARGEREBATE = "首充返利"; public static final String
	 * ALIAS_REDPACKETBONUS = "红包奖励";
	 */
	// date formats

	// columns START
	/**
	 * 会员ID
	 */
	private java.lang.Integer id;
	/**
	 * 管理员转账
	 */
	private java.lang.Double admintransfer;
	/**
	 * 在线充值
	 */
	private java.lang.Double onlinerecharge;
	/**
	 * 点卡充值
	 */
	private java.lang.Double cardrecharge;
	/**
	 * 冻结分数
	 */
	private java.lang.Double frozenscore;
	/**
	 * 用户总分
	 */
	private java.lang.Double memberscore;
	/**
	 * 中央银行
	 */
	private java.lang.Double centralbank;
	/**
	 * 救济领取
	 */
	private java.lang.Double reliefcollection;
	/**
	 * 签到领取
	 */
	private java.lang.Double signincollection;
	/**
	 * 下线奖励
	 */
	private java.lang.Double subordinatereward;
	/**
	 * 周返利
	 */
	private java.lang.Double weekrebate;
	/**
	 * 日输赢返利
	 */
	private java.lang.Double daywinorlossrebate;
	/**
	 * 首充返利
	 */
	private java.lang.Double thefirstchargerebate;
	/**
	 * 红包奖励
	 */
	private java.lang.Double redpacketbonus;
	private java.lang.Double cardrecovery;
	private java.lang.Double withdrawcash;
	private java.lang.Double recharge;
	private java.lang.Double winorfail;
	private java.lang.Double internaltest;
	private java.lang.Double rankmemberreward;
	private java.lang.Double registerreward;
	// columns END 数据库字段结束
	@WhereSQL(sql = "rankmemberreward=:BetCentralbank_rankmemberreward")
	public java.lang.Double getRankmemberreward() {
		return rankmemberreward;
	}
	public void setRankmemberreward(java.lang.Double rankmemberreward) {
		this.rankmemberreward = rankmemberreward;
	}
	@WhereSQL(sql = "registerreward=:BetCentralbank_registerreward")
	public java.lang.Double getRegisterreward() {
		return registerreward;
	}
	public void setRegisterreward(java.lang.Double registerreward) {
		this.registerreward = registerreward;
	}
	// concstructor
	@WhereSQL(sql = "cardrecovery=:BetCentralbank_cardrecovery")
	public java.lang.Double getCardrecovery() {
		return cardrecovery;
	}
	@WhereSQL(sql = "withdrawcash=:BetCentralbank_withdrawcash")
	public java.lang.Double getWithdrawcash() {
		return withdrawcash;
	}

	public void setWithdrawcash(java.lang.Double withdrawcash) {
		this.withdrawcash = withdrawcash;
	}
	@WhereSQL(sql = "recharge=:BetCentralbank_recharge")
	public java.lang.Double getRecharge() {
		return recharge;
	}

	public void setRecharge(java.lang.Double recharge) {
		this.recharge = recharge;
	}
	@WhereSQL(sql = "winorfail=:BetCentralbank_winorfail")
	public java.lang.Double getWinorfail() {
		return winorfail;
	}

	public void setWinorfail(java.lang.Double winorfail) {
		this.winorfail = winorfail;
	}
	@WhereSQL(sql = "internaltest=:BetCentralbank_internaltest")
	public java.lang.Double getInternaltest() {
		return internaltest;
	}

	public void setInternaltest(java.lang.Double internaltest) {
		this.internaltest = internaltest;
	}

	public void setCardrecovery(java.lang.Double cardrecovery) {
		this.cardrecovery = cardrecovery;
	}

	public BetCentralbank() {
	}

	public BetCentralbank(java.lang.Integer id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:BetCentralbank_id")
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setAdmintransfer(java.lang.Double value) {
		this.admintransfer = value;
	}

	@WhereSQL(sql = "admintransfer=:BetCentralbank_admintransfer")
	public java.lang.Double getAdmintransfer() {
		return this.admintransfer;
	}

	public void setOnlinerecharge(java.lang.Double value) {
		this.onlinerecharge = value;
	}

	@WhereSQL(sql = "onlinerecharge=:BetCentralbank_onlinerecharge")
	public java.lang.Double getOnlinerecharge() {
		return this.onlinerecharge;
	}

	public void setCardrecharge(java.lang.Double value) {
		this.cardrecharge = value;
	}

	@WhereSQL(sql = "cardrecharge=:BetCentralbank_cardrecharge")
	public java.lang.Double getCardrecharge() {
		return this.cardrecharge;
	}

	public void setFrozenscore(java.lang.Double value) {
		this.frozenscore = value;
	}

	@WhereSQL(sql = "frozenscore=:BetCentralbank_frozenscore")
	public java.lang.Double getFrozenscore() {
		return this.frozenscore;
	}

	public void setMemberscore(java.lang.Double value) {
		this.memberscore = value;
	}

	@WhereSQL(sql = "memberscore=:BetCentralbank_memberscore")
	public java.lang.Double getMemberscore() {
		return this.memberscore;
	}

	public void setCentralbank(java.lang.Double value) {
		this.centralbank = value;
	}

	@WhereSQL(sql = "centralbank=:BetCentralbank_centralbank")
	public java.lang.Double getCentralbank() {
		return this.centralbank;
	}

	public void setReliefcollection(java.lang.Double value) {
		this.reliefcollection = value;
	}

	@WhereSQL(sql = "reliefcollection=:BetCentralbank_reliefcollection")
	public java.lang.Double getReliefcollection() {
		return this.reliefcollection;
	}

	public void setSignincollection(java.lang.Double value) {
		this.signincollection = value;
	}

	@WhereSQL(sql = "signincollection=:BetCentralbank_signincollection")
	public java.lang.Double getSignincollection() {
		return this.signincollection;
	}

	public void setSubordinatereward(java.lang.Double value) {
		this.subordinatereward = value;
	}

	@WhereSQL(sql = "subordinatereward=:BetCentralbank_subordinatereward")
	public java.lang.Double getSubordinatereward() {
		return this.subordinatereward;
	}

	public void setWeekrebate(java.lang.Double value) {
		this.weekrebate = value;
	}

	@WhereSQL(sql = "weekrebate=:BetCentralbank_weekrebate")
	public java.lang.Double getWeekrebate() {
		return this.weekrebate;
	}

	public void setDaywinorlossrebate(java.lang.Double value) {
		this.daywinorlossrebate = value;
	}

	@WhereSQL(sql = "daywinorlossrebate=:BetCentralbank_daywinorlossrebate")
	public java.lang.Double getDaywinorlossrebate() {
		return this.daywinorlossrebate;
	}

	public void setThefirstchargerebate(java.lang.Double value) {
		this.thefirstchargerebate = value;
	}

	@WhereSQL(sql = "thefirstchargerebate=:BetCentralbank_thefirstchargerebate")
	public java.lang.Double getThefirstchargerebate() {
		return this.thefirstchargerebate;
	}

	public void setRedpacketbonus(java.lang.Double value) {
		this.redpacketbonus = value;
	}

	@WhereSQL(sql = "redpacketbonus=:BetCentralbank_redpacketbonus")
	public java.lang.Double getRedpacketbonus() {
		return this.redpacketbonus;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("会员ID[").append(getId()).append("],")
				.append("管理员转账[").append(getAdmintransfer()).append("],")
				.append("在线充值[").append(getOnlinerecharge()).append("],")
				.append("点卡充值[").append(getCardrecharge()).append("],")
				.append("冻结分数[").append(getFrozenscore()).append("],")
				.append("用户总分[").append(getMemberscore()).append("],")
				.append("中央银行[").append(getCentralbank()).append("],")
				.append("救济领取[").append(getReliefcollection()).append("],")
				.append("签到领取[").append(getSignincollection()).append("],")
				.append("下线奖励[").append(getSubordinatereward()).append("],")
				.append("周返利[").append(getWeekrebate()).append("],")
				.append("日输赢返利[").append(getDaywinorlossrebate()).append("],")
				.append("首充返利[").append(getThefirstchargerebate()).append("],")
				.append("红包奖励[").append(getRedpacketbonus()).append("],")
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BetCentralbank == false)
			return false;
		if (this == obj)
			return true;
		BetCentralbank other = (BetCentralbank) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
