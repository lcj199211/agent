package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-04-12 11:30:45
 * @see org.springrain.lottery.entity.BetReportform
 */
@Table(name="bet_agentreportform")
public class BetReportform  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "报表";
	public static final String ALIAS_ID = "报表ID";
	public static final String ALIAS_DATE = "日期";
	public static final String ALIAS_REGISTERNUM = "注册";
	public static final String ALIAS_RECHARGE = "充值";
	public static final String ALIAS_WITHDRAWCASH = "提现";
	public static final String ALIAS_REBATE = "返利/周/日/输";
	public static final String ALIAS_REDPACKAGE = "红包";
	public static final String ALIAS_SUBORDINATEREBATE = "下级返利";
	public static final String ALIAS_WINORLOSS = "游戏输赢";
	public static final String ALIAS_SCORE = "用户留存";
	public static final String ALIAS_TAX = "税收";
	public static final String ALIAS_RESULT = "结果";
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
	 * 注册
	 */
	private java.lang.Integer registernum;
	/**
	 * 充值
	 */
	private java.lang.Double recharge;
	/**
	 * 提现
	 */
	private java.lang.Double withdrawcash;
	/**
	 * 商户提现
	 */
	private java.lang.Double agentproxies;
	/**
	 * 卡收
	 */
	private java.lang.Double cardrecycle;
	/**
	 * 每日返利
	 */
	private java.lang.Double daywinorfailrebate;
	/**
	 * 签到
	 */
	private java.lang.Integer signin;
	/**
	 * 救济
	 */
	private java.lang.Integer relief;
	/**
	 * 首冲
	 */
	private java.lang.Double firstrecharge;
	/**
	 * 排行
	 */
	private java.lang.Double rank;
	/**
	 * 红包
	 */
	private java.lang.Double redpackage;
	/**
	 * 下级返利
	 */
	private java.lang.Double subordinaterebate;
	/**
	 * 游戏输赢
	 */
	private java.lang.Double winorloss;
	/**
	 * 用户留存
	 */
	private java.lang.Double score;
	/**
	 * 税收
	 */
	private java.lang.Double bettingmoney;
	/**
	 * 结果
	 */
	private java.lang.Double result;
	/**
	 * 退佣
	 */
	private Double ty;
	/**
	 * 总福利
	 */
	private Double allwelfare;
	/**
	 * 注册送
	 */
	private Double registersend;
	/**
	 * 充值返利
	 */
	private Double payrebate;
	/**
	 * 今日留存
	 */
	private Double dayscore;
	/**
	 * 每日返利
	 */
	private Double todayrechargerebate;
	/**
	 * 周输赢返利
	 */
	private Double weekwinorfailrebate;
	/**
	 * 转账分
	 */
	private Double transferaccount;
	
	
	/**
	 * 代理转账分
	 */
	private Double transferaccountagent;
	/**
	 * 一级代理转账分分
	 */
	private Double transferaccountagent1;
	/**
	 * 领取佣金
	 */
	private Double brokerage;
	
	private String agentid;
	private String agentparentid;
	private String agentparentids;
	private Double untreatedbettingmoney;
	private Double tzty;
	private Double bettingtimebettingmoney;
	private Double bettingtimebettingscore;
	private Double bettingtimebettingwin;
	private Double bettingtimecommission;
	
	private Double noissuebettingtimebettingmoney;
	private Double noissuebettingtimebettingscore;
	private Double noissuebettingtimebettingwin;
	private Double noissuebettingtimecommission;
	private Double noissuebettingtimetotalbettingmoney;
	private Double noissuebettingtimeunsettlebettingmoney;
	private Double noissuebetwithdrawcash;
	private Double noissuebetgold;
	private Double noissuetransferscore;
	private Double noissuescore;
	private Double noissuegamescore;
	private Double noissuebankscore;
	private Double noissuefreezingscore;
	private Double gamescore;
	private Double bankscore;
	private Double freezingscore;
	private Double scoree;
	private Double agentwithdrawcash;
	
	private Double activitywelfare;
	
	private java.lang.Double settletimebettingscore;
	private java.lang.Double noissuesettletimebettingscore;
	private java.lang.Double settletimebrokeragemoney;
	private java.lang.Double noissuesettletimebrokeragemoney;
	
	/**
	 * 返给代理佣金
	 */
	private java.math.BigDecimal brokerageagentmoney;
	private java.math.BigDecimal noissuebrokerageagentmoney;
	
	private Double daywinloss1;
	private Double daywinloss2;
	private Double daynoissue1;
	private Double daynoissue2;
	private Double dayisissue1;
	private Double dayisissue2;
	
	
	@WhereSQL(sql="brokerageagentmoney=:BetAgentreportform_brokerageagentmoney")
	public java.math.BigDecimal getBrokerageagentmoney() {
		return brokerageagentmoney;
	}

	public void setBrokerageagentmoney(java.math.BigDecimal brokerageagentmoney) {
		this.brokerageagentmoney = brokerageagentmoney;
	}

	
	
	@WhereSQL(sql="noissuebrokerageagentmoney=:BetAgentreportform_noissuebrokerageagentmoney")
	public java.math.BigDecimal getNoissuebrokerageagentmoney() {
		return noissuebrokerageagentmoney;
	}

	public void setNoissuebrokerageagentmoney(
			java.math.BigDecimal noissuebrokerageagentmoney) {
		this.noissuebrokerageagentmoney = noissuebrokerageagentmoney;
	}

	@WhereSQL(sql="settletimebettingscore=:BetAgentreportform_settletimebettingscore")
	public java.lang.Double getSettletimebettingscore() {
		return settletimebettingscore;
	}

	public void setSettletimebettingscore(java.lang.Double settletimebettingscore) {
		this.settletimebettingscore = settletimebettingscore;
	}

	@WhereSQL(sql="noissuesettletimebettingscore=:BetAgentreportform_noissuesettletimebettingscore")
	public java.lang.Double getNoissuesettletimebettingscore() {
		return noissuesettletimebettingscore;
	}

	public void setNoissuesettletimebettingscore(
			java.lang.Double noissuesettletimebettingscore) {
		this.noissuesettletimebettingscore = noissuesettletimebettingscore;
	}

	@WhereSQL(sql="settletimebrokeragemoney=:BetAgentreportform_settletimebrokeragemoney")
	public java.lang.Double getSettletimebrokeragemoney() {
		return settletimebrokeragemoney;
	}

	public void setSettletimebrokeragemoney(
			java.lang.Double settletimebrokeragemoney) {
		this.settletimebrokeragemoney = settletimebrokeragemoney;
	}

	@WhereSQL(sql="noissuesettletimebrokeragemoney=:BetAgentreportform_noissuesettletimebrokeragemoney")
	public java.lang.Double getNoissuesettletimebrokeragemoney() {
		return noissuesettletimebrokeragemoney;
	}

	public void setNoissuesettletimebrokeragemoney(
			java.lang.Double noissuesettletimebrokeragemoney) {
		this.noissuesettletimebrokeragemoney = noissuesettletimebrokeragemoney;
	}

	@WhereSQL(sql="agentwithdrawcash=:BetAgentreportform_agentwithdrawcash")
	public Double getAgentwithdrawcash() {
		return agentwithdrawcash;
	}

	public void setAgentwithdrawcash(Double agentwithdrawcash) {
		this.agentwithdrawcash = agentwithdrawcash;
	}

	@WhereSQL(sql="agentproxies=:BetAgentreportform_agentproxies")
	public java.lang.Double getAgentproxies() {
		return agentproxies;
	}

	public void setAgentproxies(java.lang.Double agentproxies) {
		this.agentproxies = agentproxies;
	}
	
	@WhereSQL(sql="scoree=:BetAgentreportform_scoree")
	public Double getScoree() {
		return scoree;
	}

	public void setScoree(Double scoree) {
		this.scoree = scoree;
	}

	@WhereSQL(sql="noissuescore=:BetAgentreportform_noissuescore")
	public Double getNoissuescore() {
		return noissuescore;
	}

	public void setNoissuescore(Double noissuescore) {
		this.noissuescore = noissuescore;
	}
	@WhereSQL(sql="noissuegamescore=:BetAgentreportform_noissuegamescore")
	public Double getNoissuegamescore() {
		return noissuegamescore;
	}

	public void setNoissuegamescore(Double noissuegamescore) {
		this.noissuegamescore = noissuegamescore;
	}
	@WhereSQL(sql="noissuebankscore=:BetAgentreportform_noissuebankscore")
	public Double getNoissuebankscore() {
		return noissuebankscore;
	}

	public void setNoissuebankscore(Double noissuebankscore) {
		this.noissuebankscore = noissuebankscore;
	}
	@WhereSQL(sql="noissuefreezingscore=:BetAgentreportform_noissuefreezingscore")
	public Double getNoissuefreezingscore() {
		return noissuefreezingscore;
	}

	public void setNoissuefreezingscore(Double noissuefreezingscore) {
		this.noissuefreezingscore = noissuefreezingscore;
	}
	@WhereSQL(sql="gamescore=:BetAgentreportform_gamescore")
	public Double getGamescore() {
		return gamescore;
	}

	public void setGamescore(Double gamescore) {
		this.gamescore = gamescore;
	}
	@WhereSQL(sql="bankscore=:BetAgentreportform_bankscore")
	public Double getBankscore() {
		return bankscore;
	}

	public void setBankscore(Double bankscore) {
		this.bankscore = bankscore;
	}
	@WhereSQL(sql="freezingscore=:BetAgentreportform_freezingscore")
	public Double getFreezingscore() {
		return freezingscore;
	}

	public void setFreezingscore(Double freezingscore) {
		this.freezingscore = freezingscore;
	}

	@WhereSQL(sql="noissuetransferscore=:BetAgentreportform_noissuetransferscore")
	public Double getNoissuetransferscore() {
		return noissuetransferscore;
	}

	public void setNoissuetransferscore(Double noissuetransferscore) {
		this.noissuetransferscore = noissuetransferscore;
	}

	@WhereSQL(sql="noissuebetgold=:BetAgentreportform_noissuebetgold")
	public Double getNoissuebetgold() {
		return noissuebetgold;
	}

	public void setNoissuebetgold(Double noissuebetgold) {
		this.noissuebetgold = noissuebetgold;
	}

	@WhereSQL(sql="noissuebettingtimeunsettlebettingmoney=:BetAgentreportform_noissuebettingtimeunsettlebettingmoney")
	public Double getNoissuebettingtimeunsettlebettingmoney() {
		return noissuebettingtimeunsettlebettingmoney;
	}
	@WhereSQL(sql="noissuebetwithdrawcash=:BetAgentreportform_noissuebetwithdrawcash")
	public Double getNoissuebetwithdrawcash() {
		return noissuebetwithdrawcash;
	}
	
	public void setNoissuebetwithdrawcash(Double noissuebetwithdrawcash) {
		this.noissuebetwithdrawcash = noissuebetwithdrawcash;
	}

	public void setNoissuebettingtimeunsettlebettingmoney(
			Double noissuebettingtimeunsettlebettingmoney) {
		this.noissuebettingtimeunsettlebettingmoney = noissuebettingtimeunsettlebettingmoney;
	}

	@WhereSQL(sql="noissuebettingtimetotalbettingmoney=:BetAgentreportform_noissuebettingtimetotalbettingmoney")
	public Double getNoissuebettingtimetotalbettingmoney() {
		return noissuebettingtimetotalbettingmoney;
	}

	public void setNoissuebettingtimetotalbettingmoney(
			Double noissuebettingtimetotalbettingmoney) {
		this.noissuebettingtimetotalbettingmoney = noissuebettingtimetotalbettingmoney;
	}

	@WhereSQL(sql="noissuebettingtimecommission=:BetAgentreportform_noissuebettingtimecommission")
	public Double getNoissuebettingtimecommission() {
		return noissuebettingtimecommission;
	}

	public void setNoissuebettingtimecommission(Double noissuebettingtimecommission) {
		this.noissuebettingtimecommission = noissuebettingtimecommission;
	}

	@WhereSQL(sql="noissuebettingtimebettingmoney=:BetAgentreportform_noissuebettingtimebettingmoney")
	public Double getNoissuebettingtimebettingmoney() {
		return noissuebettingtimebettingmoney;
	}

	public void setNoissuebettingtimebettingmoney(
			Double noissuebettingtimebettingmoney) {
		this.noissuebettingtimebettingmoney = noissuebettingtimebettingmoney;
	}
	
	@WhereSQL(sql="noissuebettingtimebettingscore=:BetAgentreportform_noissuebettingtimebettingscore")
	public Double getNoissuebettingtimebettingscore() {
		return noissuebettingtimebettingscore;
	}

	public void setNoissuebettingtimebettingscore(
			Double noissuebettingtimebettingscore) {
		this.noissuebettingtimebettingscore = noissuebettingtimebettingscore;
	}

	@WhereSQL(sql="noissuebettingtimebettingwin=:BetAgentreportform_noissuebettingtimebettingwin")
	public Double getNoissuebettingtimebettingwin() {
		return noissuebettingtimebettingwin;
	}

	public void setNoissuebettingtimebettingwin(Double noissuebettingtimebettingwin) {
		this.noissuebettingtimebettingwin = noissuebettingtimebettingwin;
	}

	@WhereSQL(sql="bettingtimecommission=:BetAgentreportform_bettingtimecommission")
	public Double getBettingtimecommission() {
		return bettingtimecommission;
	}

	public void setBettingtimecommission(Double bettingtimecommission) {
		this.bettingtimecommission = bettingtimecommission;
	}

	@WhereSQL(sql="bettingtimebettingwin=:BetAgentreportform_bettingtimebettingwin")
	public Double getBettingtimebettingwin() {
		return bettingtimebettingwin;
	}

	public void setBettingtimebettingwin(Double bettingtimebettingwin) {
		this.bettingtimebettingwin = bettingtimebettingwin;
	}

	@WhereSQL(sql="bettingtimebettingmoney=:BetAgentreportform_bettingtimebettingmoney")
	public Double getBettingtimebettingmoney() {
		return bettingtimebettingmoney;
	}

	public void setBettingtimebettingmoney(Double bettingtimebettingmoney) {
		this.bettingtimebettingmoney = bettingtimebettingmoney;
	}
	@WhereSQL(sql="bettingtimebettingscore=:BetAgentreportform_bettingtimebettingscore")
	public Double getBettingtimebettingscore() {
		return bettingtimebettingscore;
	}

	public void setBettingtimebettingscore(Double bettingtimebettingscore) {
		this.bettingtimebettingscore = bettingtimebettingscore;
	}

	@WhereSQL(sql="tzty=:BetAgentreportform_tzty")
	public Double getTzty() {
		return tzty;
	}

	public void setTzty(Double tzty) {
		this.tzty = tzty;
	}

	//	private java.lang.Double mks;
//	private java.lang.Double gks;
//	
//	//columns END 数据库字段结束
//	
//	//concstructor
//	@WhereSQL(sql="mks=:BetAgentreportform_mks")
//	public java.lang.Double getMks() {
//		return mks;
//	}
//
//	public void setMks(java.lang.Double mks) {
//		this.mks = mks;
//	}
//	@WhereSQL(sql="gks=:BetAgentreportform_gks")
//	public java.lang.Double getGks() {
//		return gks;
//	}
//
//	public void setGks(java.lang.Double gks) {
//		this.gks = gks;
//	}
	 @WhereSQL(sql="untreatedbettingmoney=:BetAgentreportform_untreatedbettingmoney")
	public Double getUntreatedbettingmoney() {
		return untreatedbettingmoney;
	}

	public void setUntreatedbettingmoney(Double untreatedbettingmoney) {
		this.untreatedbettingmoney = untreatedbettingmoney;
	}

	public BetReportform(){
	}

	public BetReportform(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentreportform_id")
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
	
     @WhereSQL(sql="date=:BetAgentreportform_date")
	public java.util.Date getDate() {
		return this.date;
	}
     @WhereSQL(sql="transferaccount=:BetAgentreportform_transferaccount")
     public Double getTransferaccount() {
 		return transferaccount;
 	}

 	public void setTransferaccount(Double transferaccount) {
 		this.transferaccount = transferaccount;
 	}
     
     @WhereSQL(sql="registersend=:BetAgentreportform_registersend")
 	public Double getRegistersend() {
		return registersend;
	}

	public void setRegistersend(Double registersend) {
		this.registersend = registersend;
	}
	 @WhereSQL(sql="todayrechargerebate=:BetAgentreportform_todayrechargerebate")
	public Double getTodayrechargerebate() {
		return todayrechargerebate;
	}

	public void setTodayrechargerebate(Double todayrechargerebate) {
		this.todayrechargerebate = todayrechargerebate;
	}
     @WhereSQL(sql="allwelfare=:BetAgentreportform_allwelfare")
     public Double getAllwelfare() {
 		return allwelfare;
 	}

 	public void setAllwelfare(Double allwelfare) {
 		this.allwelfare = allwelfare;
 	}
	public void setRegisternum(java.lang.Integer value) {
		this.registernum = value;
	}
	
     @WhereSQL(sql="registernum=:BetAgentreportform_registernum")
	public java.lang.Integer getRegisternum() {
		return this.registernum;
	}
	public void setRecharge(java.lang.Double value) {
		this.recharge = value;
	}
	
     @WhereSQL(sql="recharge=:BetAgentreportform_recharge")
	public java.lang.Double getRecharge() {
		return this.recharge;
	}
	public void setWithdrawcash(java.lang.Double value) {
		this.withdrawcash = value;
	}
	
     @WhereSQL(sql="withdrawcash=:BetAgentreportform_withdrawcash")
	public java.lang.Double getWithdrawcash() {
		return this.withdrawcash;
	}
     
     @WhereSQL(sql="cardrecycle=:BetAgentreportform_cardrecycle")
	public java.lang.Double getCardrecycle() {
		return cardrecycle;
	}

	public void setCardrecycle(java.lang.Double cardrecycle) {
		this.cardrecycle = cardrecycle;
	}

	public void setDaywinorfailrebate(java.lang.Double value) {
		this.daywinorfailrebate = value;
	}
	
     @WhereSQL(sql="daywinorfailrebate=:BetAgentreportform_daywinorfailrebate")
	public java.lang.Double getDaywinorfailrebate() {
		return this.daywinorfailrebate;
	}
     @WhereSQL(sql="signin=:BetAgentreportform_signin")
	public java.lang.Integer getSignin() {
		return signin;
	}

	public void setSignin(java.lang.Integer signin) {
		this.signin = signin;
	}

	public void setRelief(java.lang.Integer relief) {
		this.relief = relief;
	}

	@WhereSQL(sql="relief=:BetAgentreportform_relief")
	public java.lang.Integer getRelief() {
		return relief;
	}
	
	@WhereSQL(sql="firstrecharge=:BetAgentreportform_firstrecharge")
	public java.lang.Double getFirstrecharge() {
		return firstrecharge;
	}

	public void setFirstrecharge(java.lang.Double firstrecharge) {
		this.firstrecharge = firstrecharge;
	}

	public void setRank(java.lang.Double rank) {
		this.rank = rank;
	}
	
	@WhereSQL(sql="rank=:BetAgentreportform_rank")
	public java.lang.Double getRank() {
		return rank;
	}

	public void setRedpackage(java.lang.Double value) {
		this.redpackage = value;
	}
	
	@WhereSQL(sql="redpackage=:BetAgentreportform_redpackage")
	public java.lang.Double getRedpackage() {
		return this.redpackage;
	}
	@WhereSQL(sql="weekwinorfailrebate=:BetAgentreportform_weekwinorfailrebate")
	public Double getWeekwinorfailrebate() {
		return weekwinorfailrebate;
	}

	public void setWeekwinorfailrebate(Double weekwinorfailrebate) {
		this.weekwinorfailrebate = weekwinorfailrebate;
	}
	public void setSubordinaterebate(java.lang.Double value) {
		this.subordinaterebate = value;
	}
	
     @WhereSQL(sql="subordinaterebate=:BetAgentreportform_subordinaterebate")
	public java.lang.Double getSubordinaterebate() {
		return this.subordinaterebate;
	}
	public void setWinorloss(java.lang.Double value) {
		this.winorloss = value;
	}
	
     @WhereSQL(sql="winorloss=:BetAgentreportform_winorloss")
	public java.lang.Double getWinorloss() {
		return this.winorloss;
	}
	public void setScore(java.lang.Double value) {
		this.score = value;
	}
	
     @WhereSQL(sql="score=:BetAgentreportform_score")
	public java.lang.Double getScore() {
		return this.score;
	}
	public void setBettingmoney(java.lang.Double value) {
		this.bettingmoney = value;
	}
	
     @WhereSQL(sql="bettingmoney=:BetAgentreportform_bettingmoney")
	public java.lang.Double getBettingmoney() {
		return this.bettingmoney;
	}
	public void setResult(java.lang.Double value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BetAgentreportform_result")
	public java.lang.Double getResult() {
		return this.result;
	}
     @WhereSQL(sql="ty=:BetAgentreportform_ty")
     public Double getTy() {
 		return ty;
 	}

 	public void setTy(Double ty) {
 		this.ty = ty;
 	}
 	@WhereSQL(sql="payrebate=:BetAgentreportform_payrebate")
 	public Double getPayrebate() {
		return payrebate;
	}

	public void setPayrebate(Double payrebate) {
		this.payrebate = payrebate;
	}

     @WhereSQL(sql="agentid=:BetAgentreportform_agentid")
     public String getAgentid() {
 		return agentid;
 	}

 	public void setAgentid(String agentid) {
 		this.agentid = agentid;
 	}
 	 @WhereSQL(sql="agentparentid=:BetAgentreportform_agentparentid")
 	public String getAgentparentid() {
 		return agentparentid;
 	}

 	public void setAgentparentid(String agentparentid) {
 		this.agentparentid = agentparentid;
 	}
 	 @WhereSQL(sql="agentparentids=:BetAgentreportform_agentparentids")
 	public String getAgentparentids() {
 		return agentparentids;
 	}

 	public void setAgentparentids(String agentparentids) {
 		this.agentparentids = agentparentids;
 	}
 	 @WhereSQL(sql="dayscore=:BetAgentreportform_dayscore")
 	public Double getDayscore() {
		return dayscore;
	}

	public void setDayscore(Double dayscore) {
		this.dayscore = dayscore;
	}
	@Override
	public String toString() {
		return new StringBuffer()
			.append("报表ID[").append(getId()).append("],")
			.append("日期[").append(getDate()).append("],")
			.append("注册[").append(getRegisternum()).append("],")
			.append("充值[").append(getRecharge()).append("],")
			.append("提现[").append(getWithdrawcash()).append("],")
			.append("每日返利[").append(getDaywinorfailrebate()).append("],")
			.append("红包[").append(getRedpackage()).append("],")
			.append("下级返利[").append(getSubordinaterebate()).append("],")
			.append("游戏输赢[").append(getWinorloss()).append("],")
			.append("用户留存[").append(getScore()).append("],")
			.append("投注额[").append(getBettingmoney()).append("],")
			.append("结果[").append(getResult()).append("]")
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
		if(obj instanceof BetReportform == false) return false;
		if(this == obj) return true;
		BetReportform other = (BetReportform)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@WhereSQL(sql="activitywelfare=:BetAgentreportform_activitywelfare")
	public Double getActivitywelfare() {
		return activitywelfare;
	}

	public void setActivitywelfare(Double activitywelfare) {
		this.activitywelfare = activitywelfare;
	}
	@WhereSQL(sql="daywinloss1=:BetAgentreportform_daywinloss1")
	public Double getDaywinloss1() {
		return daywinloss1;
	}

	public void setDaywinloss1(Double daywinloss1) {
		this.daywinloss1 = daywinloss1;
	}
	@WhereSQL(sql="daywinloss2=:BetAgentreportform_daywinloss2")
	public Double getDaywinloss2() {
		return daywinloss2;
	}

	public void setDaywinloss2(Double daywinloss2) {
		this.daywinloss2 = daywinloss2;
	}
	@WhereSQL(sql="daynoissue1=:BetAgentreportform_daynoissue1")
	public Double getDaynoissue1() {
		return daynoissue1;
	}

	public void setDaynoissue1(Double daynoissue1) {
		this.daynoissue1 = daynoissue1;
	}
	@WhereSQL(sql="daynoissue2=:BetAgentreportform_daynoissue2")
	public Double getDaynoissue2() {
		return daynoissue2;
	}

	public void setDaynoissue2(Double daynoissue2) {
		this.daynoissue2 = daynoissue2;
	}
	@WhereSQL(sql="dayisissue1=:BetAgentreportform_dayisissue1")
	public Double getDayisissue1() {
		return dayisissue1;
	}

	public void setDayisissue1(Double dayisissue1) {
		this.dayisissue1 = dayisissue1;
	}
	@WhereSQL(sql="dayisissue2=:BetAgentreportform_dayisissue2")
	public Double getDayisissue2() {
		return dayisissue2;
	}

	public void setDayisissue2(Double dayisissue2) {
		this.dayisissue2 = dayisissue2;
	}

	
	/**
	 * 代理转账分
	 */
	@WhereSQL(sql="transferaccountagent=:BetAgentreportform_transferaccountagent")
    public Double getTransferaccountagent() {
		return transferaccountagent;
	}

	public void setTransferaccountagent(Double transferaccountagent) {
		this.transferaccountagent = transferaccountagent;
	}
	/**
	 * 一级代理转账分
	 */
	@WhereSQL(sql="transferaccountagent1=:BetAgentreportform_transferaccountagent1")
    public Double getTransferaccountagent1() {
		return transferaccountagent1;
	}

	public void setTransferaccountagent1(Double transferaccountagent1) {
		this.transferaccountagent1 = transferaccountagent1;
	}

	/**
	 * 领取佣金
	 */
	@WhereSQL(sql="brokerage=:BetAgentreportform_brokerage")
    public Double getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}
}

	
