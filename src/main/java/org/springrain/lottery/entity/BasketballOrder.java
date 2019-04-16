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
 * @version  2017-11-14 16:08:39
 * @see org.springrain.lottery.entity.BasketballOrder
 */
@Table(name="basketball_order")
public class BasketballOrder  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BasketballOrder";
	public static final String ALIAS_ID = "订单表id";
	public static final String ALIAS_SCHEMEID = "方案id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_MEMBERID2 = "会员id";
	public static final String ALIAS_BETTINGMONEY = "投注金额";
	public static final String ALIAS_BETTINGWIN = "投注赢";
	public static final String ALIAS_BETMULRIPLE = "倍数";
	public static final String ALIAS_STATE = "状态1:正常 3:删除";
	public static final String ALIAS_RESULT = "结果0:未结算 1:已返奖 2:已撤销 3:未中奖4非正常";
	public static final String ALIAS_SETTLETIME = "结算时间";
	public static final String ALIAS_CONTENTS = "比赛场数";
    */
	//date formats
	//public static final String FORMAT_SETTLETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 订单表id
	 */
	private java.lang.Integer id;
	/**
	 * 方案id
	 */
	private java.lang.String schemeid;
	/**
	 * 订单号
	 */
	private java.lang.String orderid;
	/**
	 * 会员id
	 */
	private java.lang.Integer memberid2;
	/**
	 * 投注金额
	 */
	private java.math.BigDecimal bettingmoney;
	/**
	 * 投注赢
	 */
	private java.math.BigDecimal bettingwin;
	/**
	 * 倍数
	 */
	private java.lang.Integer betmulriple;
	/**
	 * 状态1:正常 3:删除
	 */
	private java.lang.Integer state;
	/**
	 * 结果0:未结算 1:已返奖 2:已撤销 3:未中奖4非正常
	 */
	private java.lang.Integer result;
	/**
	 * 结算时间
	 */
	private java.util.Date settletime;
	
	private java.util.Date bettingtime;
	/**
	 * 比赛场数
	 */
	private java.lang.Integer contents;
	
	private java.lang.Integer systemissue;
	
	private java.lang.Integer issuebetmulriple;
	
	
	//columns END 数据库字段结束
	
	/**
	 * 投注内容
	 */
	private List<BasketballOrderContent> ordercontent;
	
	/**
	 * 玩法
	 */
	private java.lang.String playmethod;
	
	private java.lang.Integer channelid;
	private java.lang.Integer issuestate;
	private java.lang.Integer balanceaccount;
	private java.lang.Integer bettingretrytime;
	private java.lang.String sysid;
	private java.util.Date printtime;
	private java.util.Date distributetime;
	private java.lang.String failreason;
	private java.lang.String msg;
	private java.lang.Double posttaxprize;
	
	private List<BasketballSchemeMatch> schemecontent;
	
	private java.lang.String membernickname;
	
	@Transient
	public List<BasketballSchemeMatch> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<BasketballSchemeMatch> schemecontent) {
		this.schemecontent = schemecontent;
	}

	@Transient
	public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}
		
	
	@WhereSQL(sql="issuebetmulriple=:BasketballOrder_issuebetmulriple")
	public java.lang.Integer getIssuebetmulriple() {
		return issuebetmulriple;
	}

	public void setIssuebetmulriple(java.lang.Integer issuebetmulriple) {
		this.issuebetmulriple = issuebetmulriple;
	}

	@WhereSQL(sql="channelid=:BasketballOrder_channelid")
	public java.lang.Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(java.lang.Integer channelid) {
		this.channelid = channelid;
	}
	@WhereSQL(sql="issuestate=:BasketballOrder_issuestate")
	public java.lang.Integer getIssuestate() {
		return issuestate;
	}

	public void setIssuestate(java.lang.Integer issuestate) {
		this.issuestate = issuestate;
	}
	@WhereSQL(sql="balanceaccount=:BasketballOrder_balanceaccount")
	public java.lang.Integer getBalanceaccount() {
		return balanceaccount;
	}

	public void setBalanceaccount(java.lang.Integer balanceaccount) {
		this.balanceaccount = balanceaccount;
	}
	@WhereSQL(sql="bettingretrytime=:BasketballOrder_bettingretrytime")
	public java.lang.Integer getBettingretrytime() {
		return bettingretrytime;
	}

	public void setBettingretrytime(java.lang.Integer bettingretrytime) {
		this.bettingretrytime = bettingretrytime;
	}
	@WhereSQL(sql="sysid=:BasketballOrder_sysid")
	public java.lang.String getSysid() {
		return sysid;
	}

	public void setSysid(java.lang.String sysid) {
		this.sysid = sysid;
	}
	@WhereSQL(sql="printtime=:BasketballOrder_printtime")
	public java.util.Date getPrinttime() {
		return printtime;
	}

	public void setPrinttime(java.util.Date printtime) {
		this.printtime = printtime;
	}
	 @WhereSQL(sql="distributetime=:BasketballOrder_distributetime")
	public java.util.Date getDistributetime() {
		return distributetime;
	}

	public void setDistributetime(java.util.Date distributetime) {
		this.distributetime = distributetime;
	}
	 @WhereSQL(sql="failreason=:BasketballOrder_failreason")
	public java.lang.String getFailreason() {
		return failreason;
	}

	public void setFailreason(java.lang.String failreason) {
		this.failreason = failreason;
	}
	 @WhereSQL(sql="msg=:BasketballOrder_msg")
	public java.lang.String getMsg() {
		return msg;
	}

	public void setMsg(java.lang.String msg) {
		this.msg = msg;
	}
	 @WhereSQL(sql="posttaxprize=:BasketballOrder_posttaxprize")
	public java.lang.Double getPosttaxprize() {
		return posttaxprize;
	}

	public void setPosttaxprize(java.lang.Double posttaxprize) {
		this.posttaxprize = posttaxprize;
	}
	
	
	
	
	
	//concstructor
	
	@WhereSQL(sql="systemissue=:BasketballOrder_systemissue")
	public java.lang.Integer getSystemissue() {
		return systemissue;
	}

	public void setSystemissue(java.lang.Integer systemissue) {
		this.systemissue = systemissue;
	}

	@Transient
	public java.util.Date getBettingtime() {
		return bettingtime;
	}

	public void setBettingtime(java.util.Date bettingtime) {
		this.bettingtime = bettingtime;
	}

	@Transient
	public List<BasketballOrderContent> getOrdercontent() {
		return ordercontent;
	}

	
	public void setOrdercontent(List<BasketballOrderContent> ordercontent) {
		this.ordercontent = ordercontent;
	}

	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}

	public BasketballOrder(){
	}

	public BasketballOrder(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BasketballOrder_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:BasketballOrder_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
     @WhereSQL(sql="orderid=:BasketballOrder_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BasketballOrder_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}
	
     @WhereSQL(sql="bettingmoney=:BasketballOrder_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}
	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}
	
     @WhereSQL(sql="bettingwin=:BasketballOrder_bettingwin")
	public java.math.BigDecimal getBettingwin() {
		return this.bettingwin;
	}
	public void setBetmulriple(java.lang.Integer value) {
		this.betmulriple = value;
	}
	
     @WhereSQL(sql="betmulriple=:BasketballOrder_betmulriple")
	public java.lang.Integer getBetmulriple() {
		return this.betmulriple;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BasketballOrder_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setResult(java.lang.Integer value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:BasketballOrder_result")
	public java.lang.Integer getResult() {
		return this.result;
	}
		/*
	public String getsettletimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLETIME, getsettletime());
	}
	public void setsettletimeString(String value) throws ParseException{
		setsettletime(DateUtils.convertString2Date(FORMAT_SETTLETIME,value));
	}*/
	
	public void setSettletime(java.util.Date value) {
		this.settletime = value;
	}
	
     @WhereSQL(sql="settletime=:BasketballOrder_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}
	public void setContents(java.lang.Integer value) {
		this.contents = value;
	}
	
     @WhereSQL(sql="contents=:BasketballOrder_contents")
	public java.lang.Integer getContents() {
		return this.contents;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("订单表id[").append(getId()).append("],")
			.append("方案id[").append(getSchemeid()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("会员id[").append(getMemberid2()).append("],")
			.append("投注金额[").append(getBettingmoney()).append("],")
			.append("投注赢[").append(getBettingwin()).append("],")
			.append("倍数[").append(getBetmulriple()).append("],")
			.append("状态1:正常 3:删除[").append(getState()).append("],")
			.append("结果0:未结算 1:已返奖 2:已撤销 3:未中奖4非正常[").append(getResult()).append("],")
			.append("结算时间[").append(getSettletime()).append("],")
			.append("比赛场数[").append(getContents()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasketballOrder == false) return false;
		if(this == obj) return true;
		BasketballOrder other = (BasketballOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
