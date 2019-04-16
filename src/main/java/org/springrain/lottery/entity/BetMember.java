package org.springrain.lottery.entity;

import java.math.BigDecimal;

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
 * @version  2017-03-03 16:41:36
 * @see org.springrain.lottery.entity.BetMember
 */
@Table(name="bet_member")
public class BetMember  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetMember";
	public static final String ALIAS_ID = "会员ID";
	public static final String ALIAS_ACCOUNT = "会员账户";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_NICKNAME = "昵称";
	public static final String ALIAS_IDNO = "身份证号";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_EMAIL = "邮箱";
	public static final String ALIAS_QQ = "QQ";
	public static final String ALIAS_SCORE = "总分数";
	public static final String ALIAS_COINS = "金币";
	public static final String ALIAS_LEVEL = "等级";
	public static final String ALIAS_EXP = "经验";
    */
	//date formats
	
	//columns START
	/**
	 * 会员ID
	 */
	private java.lang.String id;
	/**
	 * 会员账户
	 */
	private java.lang.String account;
	/**
	 * 密码
	 */
	private java.lang.String password;
	/**
	 * 真实姓名
	 */
	private java.lang.String realname;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 身份证号
	 */
	private java.lang.String idno;
	/**
	 * 手机号
	 */
	private java.lang.String mobile;
	/**
	 * 邮箱
	 */
	private java.lang.String email;
	/**
	 * QQ
	 */
	private java.lang.String qq;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * 总分数
	 */
	private java.lang.Double score;
	/**
	 * 乐豆
	 */
	private java.lang.Double coins;
	/**
	 * 等级
	 */
	private java.lang.Integer level;
	/**
	 * 经验
	 */
	private java.lang.Integer exp;
	//columns END 数据库字段结束
	private java.lang.Double dayscore;
	//concstructor
	private java.lang.Double award;
	private java.lang.Integer subordinate;
	private java.lang.Integer  logintimes;
	private java.lang.Double  winorfail;
	private java.lang.Integer parentid;
	private java.lang.String agentid;
	private java.lang.Integer  status;
	private Integer fansnum;
	private Integer concernnum;
	private BigDecimal bettingwin;
	private Integer weekschemenum;
	private Integer schemewinnum;
	private BigDecimal schemewin;
	private Integer lianhong;
	/**
	 * 登录地址
	 */
	private java.lang.String loginip;
	/**
	 * 登录时间
	 */
	private java.util.Date logintime;
	/**
	 * 来源
	 */
	private java.lang.String logintool;
	

	/**
	 * 注册IP
	 */
	private java.lang.String signip;
	/**
	 * 注册时间
	 */
	private java.util.Date signdate;
	private java.lang.Double gamescore;
	private java.lang.Double bankscore;
	private java.lang.Integer todayexp;
	private java.lang.Integer id2;
	private java.lang.String signature;
	private java.lang.String password2;
	private Double currentBettingMoney;
	private String operate;
	private String avatar;
	private Double todayRecharge;
	private Double todaywithdrawcash;
	private Double totalBettingMoney;
	private Double totalRecharge;
	private Integer reliefpaymentstate;
	private java.lang.String gameauthorization;
	private java.lang.String agentauthorization;
	private java.lang.String financeauthorization;
	private java.lang.Double freezingscore;
	private java.lang.Integer isinternal;
	private java.util.Date internaladdtime;
	private java.lang.String internalremark;
	private java.lang.String internaloperator;
	private java.lang.String ticket;
	private java.lang.Double tx;
	private java.util.Date freezeaddtime;
	private java.lang.String freezeremark;
	private java.lang.String freezeoperator;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	private java.lang.Double sbproportion;
	private java.lang.Double scproportion;
	private java.lang.Double slproportion;
	private java.lang.Double ty;
	private java.lang.Integer checkin;
	private java.lang.Double score1;
	private java.lang.Double freezingscore1;
	private java.lang.Double gamescore1;
	private java.lang.Double bankscore1;
	private java.lang.Integer todayexp1;
	private java.lang.Double dayscore1;
	private java.lang.Integer exp1;
	private java.lang.Double winorfail1;
	private java.lang.Integer version;
	private	java.lang.Integer isparent;
	private java.lang.String parentids;
	private java.lang.String agentnickname;
	private java.lang.Integer pid;
	private java.lang.Integer subdistributor;
	private java.lang.Integer isissue;
	private java.lang.Integer isavatarupdate;
	/**
	 * 栏目版本
	 */
	private String versioncontrol;
	
	
	@WhereSQL(sql="isissue=:BetMember_isissue")
	public java.lang.Integer getIsissue() {
		return isissue;
	}

	public void setIsissue(java.lang.Integer isissue) {
		this.isissue = isissue;
	}
	
	@WhereSQL(sql="isavatarupdate=:BetMember_isavatarupdate")
	public java.lang.Integer getIsavatarupdate() {
		return isavatarupdate;
	}

	public void setIsavatarupdate(java.lang.Integer isavatarupdate) {
		this.isavatarupdate = isavatarupdate;
	}

	@Transient
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}
	
	@WhereSQL(sql="pid=:BetMember_pid")
	public java.lang.Integer getPid() {
		return pid;
	}

	public void setPid(java.lang.Integer pid) {
		this.pid = pid;
	}
	@WhereSQL(sql="subdistributor=:BetMember_subdistributor")
	public java.lang.Integer getSubdistributor() {
		return subdistributor;
	}

	public void setSubdistributor(java.lang.Integer subdistributor) {
		this.subdistributor = subdistributor;
	}

	@WhereSQL(sql="isparent=:BetMember_isparent")
	public java.lang.Integer getIsparent() {
		return isparent;
	}

	public void setIsparent(java.lang.Integer isparent) {
		this.isparent = isparent;
	}
	@WhereSQL(sql="parentids=:BetMember_parentids")
	public java.lang.String getParentids() {
		return parentids;
	}

	public void setParentids(java.lang.String parentids) {
		this.parentids = parentids;
	}

	@WhereSQL(sql="version=:BetMember_version")
	public java.lang.Integer getVersion() {
		return version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	@WhereSQL(sql="score1=:BetMember_score1")
	public java.lang.Double getScore1() {
		return score1;
	}

	public void setScore1(java.lang.Double score1) {
		this.score1 = score1;
	}
	@WhereSQL(sql="freezingscore1=:BetMember_freezingscore1")
	public java.lang.Double getFreezingscore1() {
		return freezingscore1;
	}

	public void setFreezingscore1(java.lang.Double freezingscore1) {
		this.freezingscore1 = freezingscore1;
	}
	@WhereSQL(sql="gamescore1=:BetMember_gamescore1")
	public java.lang.Double getGamescore1() {
		return gamescore1;
	}

	public void setGamescore1(java.lang.Double gamescore1) {
		this.gamescore1 = gamescore1;
	}
	@WhereSQL(sql="bankscore1=:BetMember_bankscore1")
	public java.lang.Double getBankscore1() {
		return bankscore1;
	}

	public void setBankscore1(java.lang.Double bankscore1) {
		this.bankscore1 = bankscore1;
	}
	@WhereSQL(sql="todayexp1=:BetMember_todayexp1")
	public java.lang.Integer getTodayexp1() {
		return todayexp1;
	}

	public void setTodayexp1(java.lang.Integer todayexp1) {
		this.todayexp1 = todayexp1;
	}
	@WhereSQL(sql="dayscore1=:BetMember_dayscore1")
	public java.lang.Double getDayscore1() {
		return dayscore1;
	}

	public void setDayscore1(java.lang.Double dayscore1) {
		this.dayscore1 = dayscore1;
	}
	@WhereSQL(sql="exp1=:BetMember_exp1")
	public java.lang.Integer getExp1() {
		return exp1;
	}

	public void setExp1(java.lang.Integer exp1) {
		this.exp1 = exp1;
	}
	@WhereSQL(sql="winorfail1=:BetMember_winorfail1")
	public java.lang.Double getWinorfail1() {
		return winorfail1;
	}

	public void setWinorfail1(java.lang.Double winorfail1) {
		this.winorfail1 = winorfail1;
	}

	@WhereSQL(sql="checkin=:BetMember_checkin")
	public java.lang.Integer getCheckin() {
		return checkin;
	}

	public void setCheckin(java.lang.Integer checkin) {
		this.checkin = checkin;
	}

	@WhereSQL(sql="ty=:BetMember_ty")
	public java.lang.Double getTy() {
		return ty;
	}

	public void setTy(java.lang.Double ty) {
		this.ty = ty;
	}

	@Transient
	public java.lang.Double getSbproportion() {
		return sbproportion;
	}

	public void setSbproportion(java.lang.Double sbproportion) {
		this.sbproportion = sbproportion;
	}
	@Transient
	public java.lang.Double getScproportion() {
		return scproportion;
	}

	public void setScproportion(java.lang.Double scproportion) {
		this.scproportion = scproportion;
	}
	@Transient
	public java.lang.Double getSlproportion() {
		return slproportion;
	}

	public void setSlproportion(java.lang.Double slproportion) {
		this.slproportion = slproportion;
	}

	@WhereSQL(sql="agentparentid=:BetMember_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetMember_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	@WhereSQL(sql="freezeaddtime=:BetMember_freezeaddtime")
	public java.util.Date getFreezeaddtime() {
		return freezeaddtime;
	}

	public void setFreezeaddtime(java.util.Date freezeaddtime) {
		this.freezeaddtime = freezeaddtime;
	}
	@WhereSQL(sql="freezeremark=:BetMember_freezeremark")
	public java.lang.String getFreezeremark() {
		return freezeremark;
	}

	public void setFreezeremark(java.lang.String freezeremark) {
		this.freezeremark = freezeremark;
	}
	@WhereSQL(sql="freezeoperator=:BetMember_freezeoperator")
	public java.lang.String getFreezeoperator() {
		return freezeoperator;
	}

	public void setFreezeoperator(java.lang.String freezeoperator) {
		this.freezeoperator = freezeoperator;
	}

	@WhereSQL(sql="tx=:BetMember_tx")
	public java.lang.Double getTx() {
		return tx;
	}

	public void setTx(java.lang.Double tx) {
		this.tx = tx;
	}

	@WhereSQL(sql="ticket=:BetMember_ticket")
	public java.lang.String getTicket() {
		return ticket;
	}

	public void setTicket(java.lang.String ticket) {
		this.ticket = ticket;
	}

	@WhereSQL(sql="internaladdtime=:BetMember_internaladdtime")
	public java.util.Date getInternaladdtime() {
		return internaladdtime;
	}

	public void setInternaladdtime(java.util.Date internaladdtime) {
		this.internaladdtime = internaladdtime;
	}
	@WhereSQL(sql="internalremark=:BetMember_internalremark")
	public java.lang.String getInternalremark() {
		return internalremark;
	}

	public void setInternalremark(java.lang.String internalremark) {
		this.internalremark = internalremark;
	}
	@WhereSQL(sql="internaloperator=:BetMember_internaloperator")
	public java.lang.String getInternaloperator() {
		return internaloperator;
	}

	public void setInternaloperator(java.lang.String internaloperator) {
		this.internaloperator = internaloperator;
	}

	@WhereSQL(sql="isinternal=:BetMember_isinternal")
	public java.lang.Integer getIsinternal() {
		return isinternal;
	}

	public void setIsinternal(java.lang.Integer isinternal) {
		this.isinternal = isinternal;
	}

	@WhereSQL(sql="freezingscore=:BetMember_freezingscore")
	public java.lang.Double getFreezingscore() {
		return freezingscore;
	}

	public void setFreezingscore(java.lang.Double freezingscore) {
		this.freezingscore = freezingscore;
	}

	@WhereSQL(sql="gameauthorization=:BetMember_gameauthorization")
	public java.lang.String getGameauthorization() {
		return gameauthorization;
	}

	public void setGameauthorization(java.lang.String gameauthorization) {
		this.gameauthorization = gameauthorization;
	}
	@WhereSQL(sql="agentauthorization=:BetMember_agentauthorization")
	public java.lang.String getAgentauthorization() {
		return agentauthorization;
	}

	public void setAgentauthorization(java.lang.String agentauthorization) {
		this.agentauthorization = agentauthorization;
	}
	@WhereSQL(sql="financeauthorization=:BetMember_financeauthorization")
	public java.lang.String getFinanceauthorization() {
		return financeauthorization;
	}

	public void setFinanceauthorization(java.lang.String financeauthorization) {
		this.financeauthorization = financeauthorization;
	}

	@Transient
	public Integer getReliefpaymentstate() {
		return reliefpaymentstate;
	}

	public void setReliefpaymentstate(Integer reliefpaymentstate) {
		this.reliefpaymentstate = reliefpaymentstate;
	}

	@Transient
	public Double getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(Double totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	@Transient
	public Double getTotalBettingMoney() {
		return totalBettingMoney;
	}

	public void setTotalBettingMoney(Double totalBettingMoney) {
		this.totalBettingMoney = totalBettingMoney;
	}

	@Transient
	public Double getTodayRecharge() {
		return todayRecharge;
	}

	public void setTodayRecharge(Double todayRecharge) {
		this.todayRecharge = todayRecharge;
	}
	@Transient
	public Double getTodaywithdrawcash() {
		return todaywithdrawcash;
	}

	public void setTodaywithdrawcash(Double todaywithdrawcash) {
		this.todaywithdrawcash = todaywithdrawcash;
	}

	@WhereSQL(sql="avatar=:BetMember_avatar")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Transient
	public Double getCurrentBettingMoney() {
		return currentBettingMoney;
	}

	public void setCurrentBettingMoney(Double currentBettingMoney) {
		this.currentBettingMoney = currentBettingMoney;
	}
	@Transient
	public String getOperate() {
		return operate;
	}
	
	public void setOperate(String operate) {
		this.operate = operate;
	}

	@WhereSQL(sql="password2=:BetMember_password2")
	public java.lang.String getPassword2() {
		return password2;
	}

	public void setPassword2(java.lang.String password2) {
		this.password2 = password2;
	}

	@WhereSQL(sql="signature=:BetMember_signature")
	public java.lang.String getSignature() {
		return signature;
	}

	public void setSignature(java.lang.String signature) {
		this.signature = signature;
	}
	
	//get and set
		public void setId(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.id = value;
		}
		
		@Id
	     @WhereSQL(sql="id=:BetMember_id")
		public java.lang.String getId() {
			return this.id;
		}
		public void setAccount(java.lang.String value) {
			    if(StringUtils.isNotBlank(value)){
				 value=value.trim();
				}
			this.account = value;
		}
	@WhereSQL(sql="id2 = :BetMember_id2")//id2 like :%BetMember_id2%
	public java.lang.Integer getId2() {
		return id2;
	}

	public void setId2(java.lang.Integer id2) {
		this.id2 = id2;
	}

	@WhereSQL(sql="todayexp=:BetMember_todayexp")
	 public java.lang.Integer getTodayexp() {
		return todayexp;
	}

	public void setTodayexp(java.lang.Integer todayexp) {
		this.todayexp = todayexp;
	}

	@WhereSQL(sql="gamescore=:BetMember_gamescore")
	public java.lang.Double getGamescore() {
		return gamescore;
	}

	public void setGamescore(java.lang.Double gamescore) {
		this.gamescore = gamescore;
	}
	 @WhereSQL(sql="bankscore=:BetMember_bankscore")
	public java.lang.Double getBankscore() {
		return bankscore;
	}

	public void setBankscore(java.lang.Double bankscore) {
		this.bankscore = bankscore;
	}

	public void setLoginip(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
		 value=value.trim();
		}
	this.loginip = value;
}

 @WhereSQL(sql="loginip=:BetMember_loginip")
public java.lang.String getLoginip() {
	return this.loginip;
}
public void setLogintime(java.util.Date value) {
	   
	this.logintime = value;
}

 @WhereSQL(sql="logintime=:BetMember_logintime")
public java.util.Date getLogintime() {
	return this.logintime;
}
public void setLogintool(java.lang.String value) {
	   
	this.logintool = value;
}

 @WhereSQL(sql="logintool=:BetMember_logintool")
public java.lang.String getLogintool() {
	return this.logintool;
}
	public void setSignip(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
		 value=value.trim();
		}
	this.signip = value;
	}	

	@WhereSQL(sql="signip like :%BetMember_signip%")
	public java.lang.String getSignip() {
		return this.signip;
	}
	public void setSigndate(java.util.Date value) {
		this.signdate = value;
	}
	
  @WhereSQL(sql="signdate like :%BetMember_signdate%")
	public java.util.Date getSigndate() {
		return this.signdate;
	}
	@WhereSQL(sql="status=:BetMember_status")
	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	@WhereSQL(sql="parentid=:BetMember_parentid")
	public java.lang.Integer getParentid() {
		return parentid;
	}

	public void setParentid(java.lang.Integer parentid) {
		this.parentid = parentid;
	}
	@WhereSQL(sql="agentid like :%BetMember_agentid%")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}

	@WhereSQL(sql="subordinate=:BetMember_subordinate")
	public java.lang.Integer getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(java.lang.Integer subordinate) {
		this.subordinate = subordinate;
	}
	@WhereSQL(sql="logintimes=:BetMember_logintimes")
	public java.lang.Integer getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(java.lang.Integer logintimes) {
		this.logintimes = logintimes;
	}
	@WhereSQL(sql="winorfail=:BetMember_winorfail")
	public java.lang.Double getWinorfail() {
		return winorfail;
	}

	public void setWinorfail(java.lang.Double winorfail) {
		this.winorfail = winorfail;
	}
	@Transient
	public java.lang.Double getAward() {
		return award;
	}

	public void setAward(java.lang.Double award) {
		this.award = award;
	}

	@WhereSQL(sql="dayscore=:BetMember_dayscore")
	public java.lang.Double getDayscore() {
		return dayscore;
	}

	public void setDayscore(java.lang.Double dayscore) {
		this.dayscore = dayscore;
	}

	public BetMember(){
	}

	public BetMember(
		java.lang.String id
	){
		this.id = id;
	}

	
     @WhereSQL(sql="account like :%BetMember_account%")
	public java.lang.String getAccount() {
		return this.account;
	}
	public void setPassword(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.password = value;
	}
	
     @WhereSQL(sql="password=:BetMember_password")
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setRealname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.realname = value;
	}
	
     @WhereSQL(sql="realname like :%BetMember_realname%")
	public java.lang.String getRealname() {
		return this.realname;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
	@WhereSQL(sql="nickname like :%BetMember_nickname%")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setIdno(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.idno = value;
	}
	
     @WhereSQL(sql="idno=:BetMember_idno")
	public java.lang.String getIdno() {
		return this.idno;
	}
	public void setMobile(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mobile = value;
	}
	
     @WhereSQL(sql="mobile like :%BetMember_mobile%")
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setEmail(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.email = value;
	}
	
     @WhereSQL(sql="email=:BetMember_email")
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setQq(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qq = value;
	}
	
     @WhereSQL(sql="qq=:BetMember_qq")
	public java.lang.String getQq() {
		return this.qq;
	}
	public void setScore(java.lang.Double value) {
		this.score = value;
	}
	
     @WhereSQL(sql="score=:BetMember_score")
	public java.lang.Double getScore() {
		return this.score;
	}
	public void setCoins(java.lang.Double value) {
		this.coins = value;
	}
	
     @WhereSQL(sql="coins=:BetMember_coins")
	public java.lang.Double getCoins() {
		return this.coins;
	}
	public void setLevel(java.lang.Integer value) {
		this.level = value;
	}
	@Transient
	public java.lang.Integer getLevel() {
		return this.level;
	}
	public void setExp(java.lang.Integer value) {
		this.exp = value;
	}
	
     @WhereSQL(sql="exp=:BetMember_exp")
	public java.lang.Integer getExp() {
		return this.exp;
	}
     
    public String getWechat() {
 		return wechat;
 	}

 	public void setWechat(String wechat) {
 		this.wechat = wechat;
 	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("会员ID[").append(getId()).append("],")
			.append("会员账户[").append(getAccount()).append("],")
			.append("密码[").append(getPassword()).append("],")
			.append("真实姓名[").append(getRealname()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("身份证号[").append(getIdno()).append("],")
			.append("手机号[").append(getMobile()).append("],")
			.append("邮箱[").append(getEmail()).append("],")
			.append("QQ[").append(getQq()).append("],")
			.append("总分数[").append(getScore()).append("],")
			.append("金币[").append(getCoins()).append("],")
			.append("经验[").append(getExp()).append("],")
			.append("每日输赢[").append(getDayscore()).append("],")
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
		if(obj instanceof BetMember == false) return false;
		if(this == obj) return true;
		BetMember other = (BetMember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@WhereSQL(sql="lianhong=:BetMember_lianhong")
	public Integer getLianhong() {
		return lianhong;
	}

	public void setLianhong(Integer lianhong) {
		this.lianhong = lianhong;
	}

	public Integer getFansnum() {
		return fansnum;
	}

	public void setFansnum(Integer fansnum) {
		this.fansnum = fansnum;
	}

	public Integer getConcernnum() {
		return concernnum;
	}

	public void setConcernnum(Integer concernnum) {
		this.concernnum = concernnum;
	}

	public BigDecimal getBettingwin() {
		return bettingwin;
	}

	public void setBettingwin(BigDecimal bettingwin) {
		this.bettingwin = bettingwin;
	}

	public Integer getWeekschemenum() {
		return weekschemenum;
	}

	public void setWeekschemenum(Integer weekschemenum) {
		this.weekschemenum = weekschemenum;
	}

	public Integer getSchemewinnum() {
		return schemewinnum;
	}

	public void setSchemewinnum(Integer schemewinnum) {
		this.schemewinnum = schemewinnum;
	}

	public BigDecimal getSchemewin() {
		return schemewin;
	}

	public void setSchemewin(BigDecimal schemewin) {
		this.schemewin = schemewin;
	}

	public String getVersioncontrol() {
		return versioncontrol;
	}

	public void setVersioncontrol(String versioncontrol) {
		this.versioncontrol = versioncontrol;
	}

}

	
