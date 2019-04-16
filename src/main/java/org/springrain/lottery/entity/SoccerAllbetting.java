package org.springrain.lottery.entity;

import java.util.List;

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
 * @version  2017-09-14 16:24:25
 * @see org.springrain.lottery.entity.SoccerAllbetting
 */
@Table(name="soccer_allbetting")
public class SoccerAllbetting  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "投注汇总";
	public static final String ALIAS_ID = "订单号";
	public static final String ALIAS_MEMBERID2 = "用户ID";
	public static final String ALIAS_BETTINGMONEY = "投注金额";
	public static final String ALIAS_BETTINGSCORE = "投注输赢";
	public static final String ALIAS_BETTINGTIME = "投注时间";
	public static final String ALIAS_STATE = "结算状态0:未开奖 1:已开奖 2:撤销";
	public static final String ALIAS_SETTLEMENTTIME = "结算时间";
	public static final String ALIAS_LOGINIP = "登录IP";
	public static final String ALIAS_LOGINTOOL = "登录工具";
	public static final String ALIAS_CONTENT = "投注内容";
	public static final String ALIAS_GCNAME = "游戏名称";
	public static final String ALIAS_TYPE = "游戏类型（0：彩票，1：足球）";
    */
	//date formats
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SETTLEMENTTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 订单号
	 */
	private java.lang.String id;
	/**
	 * 用户ID
	 */
	private java.lang.Integer memberid2;
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
	/**
	 * 结算状态0:未开奖 1:已开奖 2:撤销
	 */
	private java.lang.Integer state;
	/**
	 * 结算时间
	 */
	private java.util.Date settlementtime;
	/**
	 * 登录IP
	 */
	private java.lang.String loginip;
	/**
	 * 登录工具
	 */
	private java.lang.String logintool;
	/**
	 * 投注内容
	 */
	private java.lang.String content;
	/**
	 * 游戏名称
	 */
	private java.lang.String gcname;
	/**
	 * 游戏类型（0：彩票，1：足球）
	 */
	private java.lang.Integer type;
	/**
	 * 购买类型 0自购 1跟买 2神单
	 */
	private java.lang.Integer buytype;
	private java.lang.Integer valid;
	
	//columns END 数据库字段结束
	
	/**
	 * 方案内容
	 */
	private List<SoccerSchemeMatch> schemecontent;
	private List<LotteryOrder> lotteryschemecontent;
	
	private java.lang.String membernickname;
	private java.lang.Double commission;
	
	private java.lang.String SoccerTheoreticalbonusmax;
	private java.lang.String BjdcTheoreticalbonusmax;
	private java.lang.String BasketTheoreticalbonusmax;
	
	private java.lang.String SoccerMaxmultiple;
	private java.lang.String BjdcMaxmultiple;
	private java.lang.String BasketMaxmultiple;
	
	private java.lang.String SoccerIssuestate;
	private java.lang.String BjdcIssuestate;
	private java.lang.String BasketIssuestate;
	private java.lang.String issuestate;
	
	@Transient
	public java.lang.String getSoccerMaxmultiple() {
		return SoccerMaxmultiple;
	}

	public void setSoccerMaxmultiple(java.lang.String soccerMaxmultiple) {
		SoccerMaxmultiple = soccerMaxmultiple;
	}
	@Transient
	public java.lang.String getBjdcMaxmultiple() {
		return BjdcMaxmultiple;
	}

	public void setBjdcMaxmultiple(java.lang.String bjdcMaxmultiple) {
		BjdcMaxmultiple = bjdcMaxmultiple;
	}
    
	@Transient
	public java.lang.String getBasketMaxmultiple() {
		return BasketMaxmultiple;
	}

	public void setBasketMaxmultiple(java.lang.String basketMaxmultiple) {
		BasketMaxmultiple = basketMaxmultiple;
	}

	@Transient
	public java.lang.String getSoccerIssuestate() {
		return SoccerIssuestate;
	}

	public void setSoccerIssuestate(java.lang.String soccerIssuestate) {
		SoccerIssuestate = soccerIssuestate;
	}

	@Transient
	public java.lang.String getBjdcIssuestate() {
		return BjdcIssuestate;
	}

	public void setBjdcIssuestate(java.lang.String bjdcIssuestate) {
		BjdcIssuestate = bjdcIssuestate;
	}

	@Transient
	public java.lang.String getBasketIssuestate() {
		return BasketIssuestate;
	}

	public void setBasketIssuestate(java.lang.String basketIssuestate) {
		BasketIssuestate = basketIssuestate;
	}

	/**
	 * 方案内容
	 */
	private List<BasketballSchemeMatch> basketballschemecontent;
	
	
	private List<BjdcSchemeMatch> bjdcschemecontent;
	private java.lang.String agentnickname;
	
	
	@WhereSQL(sql="agentnickname=:SoccerAllbetting_agentnickname")
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}

	@Transient
	public List<BasketballSchemeMatch> getBasketballschemecontent() {
		return basketballschemecontent;
	}

	public void setBasketballschemecontent(
			List<BasketballSchemeMatch> basketballschemecontent) {
		this.basketballschemecontent = basketballschemecontent;
	}
	@Transient
	public List<BjdcSchemeMatch> getBjdcschemecontent() {
		return bjdcschemecontent;
	}

	public void setBjdcschemecontent(List<BjdcSchemeMatch> bjdcschemecontent) {
		this.bjdcschemecontent = bjdcschemecontent;
	}
	@Transient
	public List<LotteryOrder> getLotteryschemecontent() {
		return lotteryschemecontent;
	}

	public void setLotteryschemecontent(List<LotteryOrder> lotteryschemecontent) {
		this.lotteryschemecontent = lotteryschemecontent;
	}
	@Transient
	public java.lang.Double getCommission() {
		return commission;
	}

	public void setCommission(java.lang.Double commission) {
		this.commission = commission;
	}

	@Transient
	public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}
	
	@Transient
	public List<SoccerSchemeMatch> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<SoccerSchemeMatch> schemecontent) {
		this.schemecontent = schemecontent;
	}
	
	//concstructor

	public SoccerAllbetting(){
	}

	public SoccerAllbetting(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 订单号
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * 订单号
	 */
	@Id
     @WhereSQL(sql="id=:SoccerAllbetting_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 用户ID
		 */
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
	
	
	/**
	 * 用户ID
	 */
     @WhereSQL(sql="memberid2=:SoccerAllbetting_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
		/**
		 * 投注金额
		 */
	public void setBettingmoney(java.lang.Double value) {
		this.bettingmoney = value;
	}
	
	
	
	/**
	 * 投注金额
	 */
     @WhereSQL(sql="bettingmoney=:SoccerAllbetting_bettingmoney")
	public java.lang.Double getBettingmoney() {
		return this.bettingmoney;
	}
		/**
		 * 投注输赢
		 */
	public void setBettingscore(java.lang.Double value) {
		this.bettingscore = value;
	}
	
	
	
	/**
	 * 投注输赢
	 */
     @WhereSQL(sql="bettingscore=:SoccerAllbetting_bettingscore")
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
	
		/**
		 * 投注时间
		 */
	public void setBettingtime(java.util.Date value) {
		this.bettingtime = value;
	}
	
	
	
	/**
	 * 投注时间
	 */
     @WhereSQL(sql="bettingtime=:SoccerAllbetting_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
		/**
		 * 结算状态0:未开奖 1:已开奖 2:撤销
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	
	
	/**
	 * 结算状态0:未开奖 1:已开奖 2:撤销
	 */
     @WhereSQL(sql="state=:SoccerAllbetting_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/*
	public String getsettlementtimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLEMENTTIME, getsettlementtime());
	}
	public void setsettlementtimeString(String value) throws ParseException{
		setsettlementtime(DateUtils.convertString2Date(FORMAT_SETTLEMENTTIME,value));
	}*/
	
		/**
		 * 结算时间
		 */
	public void setSettlementtime(java.util.Date value) {
		this.settlementtime = value;
	}
	
	
	
	/**
	 * 结算时间
	 */
     @WhereSQL(sql="settlementtime=:SoccerAllbetting_settlementtime")
	public java.util.Date getSettlementtime() {
		return this.settlementtime;
	}
		/**
		 * 登录IP
		 */
	public void setLoginip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.loginip = value;
	}
	
	
	
	/**
	 * 登录IP
	 */
     @WhereSQL(sql="loginip=:SoccerAllbetting_loginip")
	public java.lang.String getLoginip() {
		return this.loginip;
	}
		/**
		 * 登录工具
		 */
	public void setLogintool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.logintool = value;
	}
	
	
	
	/**
	 * 登录工具
	 */
     @WhereSQL(sql="logintool=:SoccerAllbetting_logintool")
	public java.lang.String getLogintool() {
		return this.logintool;
	}
		/**
		 * 投注内容
		 */
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
	
	
	/**
	 * 投注内容
	 */
     @WhereSQL(sql="content=:SoccerAllbetting_content")
	public java.lang.String getContent() {
		return this.content;
	}
		/**
		 * 游戏名称
		 */
	public void setGcname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gcname = value;
	}
	
	
	
	/**
	 * 游戏名称
	 */
     @WhereSQL(sql="gcname=:SoccerAllbetting_gcname")
	public java.lang.String getGcname() {
		return this.gcname;
	}
		/**
		 * 游戏类型（0：彩票，1：足球）
		 */
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 游戏类型（0：彩票，1：足球）
	 */
     @WhereSQL(sql="type=:SoccerAllbetting_type")
	public java.lang.Integer getType() {
		return this.type;
	}
     
    
     
     @WhereSQL(sql="buytype=:SoccerAllbetting_buytype")
	public java.lang.Integer getBuytype() {
		return buytype;
	}

	public void setBuytype(java.lang.Integer buytype) {
		this.buytype = buytype;
	}
	
	@WhereSQL(sql="valid=:SoccerAllbetting_valid")
	public java.lang.Integer getValid() {
		return valid;
	}

	public void setValid(java.lang.Integer valid) {
		this.valid = valid;
	}
	

	@Override
	public String toString() {
		return new StringBuilder()
			.append("订单号[").append(getId()).append("],")
			.append("用户ID[").append(getMemberid2()).append("],")
			.append("投注金额[").append(getBettingmoney()).append("],")
			.append("投注输赢[").append(getBettingscore()).append("],")
			.append("投注时间[").append(getBettingtime()).append("],")
			.append("结算状态0:未开奖 1:已开奖 2:撤销[").append(getState()).append("],")
			.append("结算时间[").append(getSettlementtime()).append("],")
			.append("登录IP[").append(getLoginip()).append("],")
			.append("登录工具[").append(getLogintool()).append("],")
			.append("投注内容[").append(getContent()).append("],")
			.append("游戏名称[").append(getGcname()).append("],")
			.append("游戏类型（0：彩票，1：足球）[").append(getType()).append("],")
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
		if(obj instanceof SoccerAllbetting == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerAllbetting other = (SoccerAllbetting)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
	@Transient
	public java.lang.String getBjdcTheoreticalbonusmax() {
		return BjdcTheoreticalbonusmax;
	}

	public void setBjdcTheoreticalbonusmax(java.lang.String bjdcTheoreticalbonusmax) {
		BjdcTheoreticalbonusmax = bjdcTheoreticalbonusmax;
	}

	
	@Transient
	public java.lang.String getBasketTheoreticalbonusmax() {
		return BasketTheoreticalbonusmax;
	}

	public void setBasketTheoreticalbonusmax(java.lang.String basketTheoreticalbonusmax) {
		BasketTheoreticalbonusmax = basketTheoreticalbonusmax;
	}
    
	@Transient
	public java.lang.String getSoccerTheoreticalbonusmax() {
		return SoccerTheoreticalbonusmax;
	}

	public void setSoccerTheoreticalbonusmax(java.lang.String soccerTheoreticalbonusmax) {
		SoccerTheoreticalbonusmax = soccerTheoreticalbonusmax;
	}

	public java.lang.String getIssuestate() {
		return issuestate;
	}

	public void setIssuestate(java.lang.String issuestate) {
		this.issuestate = issuestate;
	}
	
}

	
