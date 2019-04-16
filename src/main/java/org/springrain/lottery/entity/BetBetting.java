package org.springrain.lottery.entity;

import java.util.Date;

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
 * @version  2017-03-31 10:08:58
 * @see org.springrain.lottery.entity.BetBetting
 */
@Table(name="bet_betting")
public class BetBetting  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "投注";
	public static final String ALIAS_ID = "投注ID";
	public static final String ALIAS_MEMBERID = "用户ID";
	public static final String ALIAS_GAMEPLAYID = "玩法ID";
	public static final String ALIAS_QIBIE = "期数";
	public static final String ALIAS_BETTINGNUM = "投注数量";
	public static final String ALIAS_BETTINGMONEY = "投注金额";
	public static final String ALIAS_BETTINGSCORE = "投注输赢";
	public static final String ALIAS_BETTINGTIME = "投注时间";
    */
	//date formats
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 投注ID
	 */
	private java.lang.Integer id;
	/**
	 * 用户ID
	 */
	private java.lang.String memberid;
	/**
	 * 玩法
	 */
	private java.lang.Integer gameplayid;
	/**
	 * 期数
	 */
	private java.lang.String qibie;
	/**
	 * 投注金额
	 */
	private java.lang.Double bettingmoney;
	/**
	 * 投注输赢
	 */
	private java.lang.Double bettingscore;
	/**
	 * 投注时间
	 */
	private java.util.Date bettingtime;
	private java.lang.Integer gameclassid;
	private java.lang.String gcname;
	private java.lang.String detail;
	private java.lang.Integer state;
	private java.lang.String name1;
	private java.lang.Double odds;
	private java.lang.String loginip;
	private java.lang.String logintool;
	private java.lang.String hs;
	private java.lang.Integer memberid2;
	private String agentid;
	private String agentparentid;
	private String agentparentids;
	private Date settlementtime;
	private Double ty;
	private Integer tystate;
	private Double bettingwinorlose;
	private java.lang.Double realodds;
	private java.lang.Double memberty;
	private java.lang.Integer membertystate;
	private java.lang.Double taxdeduction;
	private java.lang.String name2;
	private java.lang.Double commission;
	private java.lang.String agentnickname;
	private java.lang.String membernickname;
	
	@Transient
	public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}

	@Transient
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}

	@Transient
	public java.lang.Double getCommission() {
		return commission;
	}

	public void setCommission(java.lang.Double commission) {
		this.commission = commission;
	}

	//	private java.lang.Double mks;
//	private java.lang.Double gks;
//	
//	@WhereSQL(sql="mks=:BetBetting_mks")
//	public java.lang.Double getMks() {
//		return mks;
//	}
//
//	public void setMks(java.lang.Double mks) {
//		this.mks = mks;
//	}
//	@WhereSQL(sql="gks=:BetBetting_gks")
//	public java.lang.Double getGks() {
//		return gks;
//	}
//
//	public void setGks(java.lang.Double gks) {
//		this.gks = gks;
//	}
	@Transient
	public java.lang.String getName2() {
		return name2;
	}

	public void setName2(java.lang.String name2) {
		this.name2 = name2;
	}
	@Transient
	public java.lang.Double getTaxdeduction() {
		return taxdeduction;
	}

	public void setTaxdeduction(java.lang.Double taxdeduction) {
		this.taxdeduction = taxdeduction;
	}
	@WhereSQL(sql="memberty=:BetBetting_memberty")
	public java.lang.Double getMemberty() {
		return memberty;
	}

	public void setMemberty(java.lang.Double memberty) {
		this.memberty = memberty;
	}
	@WhereSQL(sql="membertystate=:BetBetting_membertystate")
	public java.lang.Integer getMembertystate() {
		return membertystate;
	}

	public void setMembertystate(java.lang.Integer membertystate) {
		this.membertystate = membertystate;
	}
	@WhereSQL(sql="realodds=:BetBetting_realodds")
	public java.lang.Double getRealodds() {
		return realodds;
	}

	public void setRealodds(java.lang.Double realodds) {
		this.realodds = realodds;
	}
	@Transient
	public java.lang.String getHs() {
		return hs;
	}

	public void setHs(java.lang.String hs) {
		this.hs = hs;
	}
	@Transient
	public Double getBettingwinorlose() {
		return bettingwinorlose;
	}

	public void setBettingwinorlose(Double bettingwinorlose) {
		this.bettingwinorlose = bettingwinorlose;
	}
	@WhereSQL(sql="loginip=:BetBetting_loginip")
	public java.lang.String getLoginip() {
		return loginip;
	}

	public void setLoginip(java.lang.String loginip) {
		this.loginip = loginip;
	}
	@WhereSQL(sql="logintool=:BetBetting_logintool")
	public java.lang.String getLogintool() {
		return logintool;
	}

	public void setLogintool(java.lang.String logintool) {
		this.logintool = logintool;
	}

	@WhereSQL(sql="odds=:BetBetting_odds")
	public java.lang.Double getOdds() {
		return odds;
	}

	public void setOdds(java.lang.Double odds) {
		this.odds = odds;
	}

	//columns END 数据库字段结束
	@WhereSQL(sql="name1=:BetBetting_name1")
	 public java.lang.String getName1() {
		return name1;
	}

	public void setName1(java.lang.String name1) {
		this.name1 = name1;
	}

	@WhereSQL(sql="state=:BetBetting_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	//concstructor
	 @WhereSQL(sql="gameclassid=:BetBetting_gameclassid")
	public java.lang.Integer getGameclassid() {
		return gameclassid;
	}

	public void setGameclassid(java.lang.Integer gameclassid) {
		this.gameclassid = gameclassid;
	}
	 @WhereSQL(sql="gcname=:BetBetting_gcname")
	public java.lang.String getGcname() {
		return gcname;
	}

	public void setGcname(java.lang.String gcname) {
		this.gcname = gcname;
	}

	public BetBetting(){
	}

	public BetBetting(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetBetting_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMemberid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid = value;
	}
	
     @WhereSQL(sql="memberid=:BetBetting_memberid")
	public java.lang.String getMemberid() {
		return this.memberid;
	}
     @WhereSQL(sql="gameplayid=:BetBetting_gameplayid")
	public java.lang.Integer getGameplayid() {
		return gameplayid;
	}

	public void setGameplayid(java.lang.Integer gameplayid) {
		this.gameplayid = gameplayid;
	}
	 @WhereSQL(sql="detail=:BetBetting_detail")
	public java.lang.String getDetail() {
		return detail;
	}

	public void setDetail(java.lang.String detail) {
		this.detail = detail;
	}

	public void setQibie(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qibie = value;
	}
	
     @WhereSQL(sql="qibie=:BetBetting_qibie")
	public java.lang.String getQibie() {
		return this.qibie;
	}
	public void setBettingmoney(java.lang.Double value) {
		this.bettingmoney = value;
	}
	
     @WhereSQL(sql="bettingmoney=:BetBetting_bettingmoney")
	public java.lang.Double getBettingmoney() {
		return this.bettingmoney;
	}
	public void setBettingscore(java.lang.Double value) {
		this.bettingscore = value;
	}
	
     @WhereSQL(sql="bettingscore=:BetBetting_bettingscore")
	public java.lang.Double getBettingscore() {
		return this.bettingscore;
	}
		/*
	public String getbettingtimeString() {
		return DateUtils.convertDate2String(FORMAT_BETTINGTIME, getbettingtime());
	}
	public void setbettingtimeString(String value) throws ParseException{
		setbettingtime(DateUtils.convertString2Date(FORMAT_BETTINGTIME,value));
	}*/
	
	public void setBettingtime(java.util.Date value) {
		this.bettingtime = value;
	}
	
     @WhereSQL(sql="bettingtime=:BetBetting_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
	@Transient
	public java.lang.Integer getMemberid2() {
		return memberid2;
	}

	public void setMemberid2(java.lang.Integer memberid2) {
		this.memberid2 = memberid2;
	}
	 @WhereSQL(sql="agentid=:BetBetting_agentid")
	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	 @WhereSQL(sql="agentparentid=:BetBetting_agentparentid")
	public String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(String agentparentid) {
		this.agentparentid = agentparentid;
	}
	 @WhereSQL(sql="agentparentids=:BetBetting_agentparentids")
	public String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(String agentparentids) {
		this.agentparentids = agentparentids;
	}
	 @WhereSQL(sql="ty=:BetBetting_ty")
	public Double getTy() {
		return ty;
	}

	public void setTy(Double ty) {
		this.ty = ty;
	}
	 @WhereSQL(sql="tystate=:BetBetting_tystate")
	public Integer getTystate() {
		return tystate;
	}

	public void setTystate(Integer tystate) {
		this.tystate = tystate;
	}
	 @WhereSQL(sql="settlementtime=:BetBetting_settlementtime")
	public Date getSettlementtime() {
		return settlementtime;
	}

	public void setSettlementtime(Date settlementtime) {
		this.settlementtime = settlementtime;
	}
	@Override
	public String toString() {
		return new StringBuffer()
			.append("投注ID[").append(getId()).append("],")
			.append("用户ID[").append(getMemberid()).append("],")
			.append("期数[").append(getQibie()).append("],")
			.append("投注金额[").append(getBettingmoney()).append("],")
			.append("投注输赢[").append(getBettingscore()).append("],")
			.append("投注时间[").append(getBettingtime()).append("],")
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
		if(obj instanceof BetBetting == false) return false;
		if(this == obj) return true;
		BetBetting other = (BetBetting)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	

	

	

}

	
