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
 * @version  2017-09-04 09:18:40
 * @see org.springrain.lottery.entity.SoccerLeagueOrder
 */
@Table(name="soccer_league_order")
public class SoccerLeagueOrder  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueOrder";
	public static final String ALIAS_ID = "订单表id";
	public static final String ALIAS_ORDERID = "订单号";
	public static final String ALIAS_BETTIME = "提交时间";
	public static final String ALIAS_MEMBERID2 = "会员id";
	public static final String ALIAS_BETPRICE = "投注金额";
	public static final String ALIAS_BETWIN = "投注赢";
	public static final String ALIAS_SETTLETIME = "结算时间";
	public static final String ALIAS_STATE = "状态1:正常 3:删除";
    */
	//date formats
	//public static final String FORMAT_BETTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SETTLETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 订单表id
	 */
	private java.lang.Integer id;
	/**
	 * 订单号
	 */
	private java.lang.String orderid;
	/**
	 * 提交时间
	 */
	private java.util.Date bettingtime;
	/**
	 * 会员id
	 */
	private java.lang.Integer memberid2;
	/**
	 * 倍数
	 */
	private java.lang.Integer betmulriple;
	/**
	 * 投注金额
	 */
	private java.lang.Double bettingmoney;
	/**
	 * 投注赢
	 */
	private java.lang.Double bettingwin;
	/**
	 * 结算时间
	 */
	private java.util.Date settletime;
	
	
	/**
	 * 状态1:正常 3:删除
	 */
	private java.lang.Integer state;
	
	/**
	 * 结果0:未结算 1:已返奖 3:未中奖
	 */
	private java.lang.Integer result;

	/**
	 * 比赛场数
	 */
	private java.lang.Integer contents;
	
	/**
	 * 方案id
	 */
	private java.lang.String schemeid;
	
	private java.lang.Integer playmethodid;
	
	//columns END 数据库字段结束
	
	/**
	 * 投注内容
	 */
	private List<SoccerLeagueOrderContent> ordercontent;
	
	/**
	 * 玩法
	 */
	private java.lang.String playmethod;
	
	private java.lang.Integer channelid;
	private java.lang.Integer issuestate;
	private java.lang.String sysid;
	private java.util.Date printtime;
	private java.lang.String failreason;
	private java.util.Date distributetime;
	private java.lang.String msg;
	private java.lang.Integer balanceaccount;
	private java.lang.String issue;
	private java.lang.Double posttaxprize;
	private java.lang.Integer bettingretrytime;
	private List<SoccerSchemeMatch> schemecontent;
	private List<SoccerLeagueOrderContent> content;
	private String membernickname;
	private String leftteamname;
	private String rightteamname;
	private  Integer systemissue;
	private Integer issuebetmulriple;
	
	@Transient
	public List<SoccerLeagueOrderContent> getContent() {
		return content;
	}

	public void setContent(List<SoccerLeagueOrderContent> content) {
		this.content = content;
	}
	@Transient
	public String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(String membernickname) {
		this.membernickname = membernickname;
	}
	@WhereSQL(sql="bettingretrytime=:SoccerLeagueOrder_bettingretrytime")
	public java.lang.Integer getBettingretrytime() {
		return bettingretrytime;
	}

	public void setBettingretrytime(java.lang.Integer bettingretrytime) {
		this.bettingretrytime = bettingretrytime;
	}

	@WhereSQL(sql="posttaxprize=:SoccerLeagueOrder_posttaxprize")
	public java.lang.Double getPosttaxprize() {
		return posttaxprize;
	}

	public void setPosttaxprize(java.lang.Double posttaxprize) {
		this.posttaxprize = posttaxprize;
	}

	@Transient
	public java.lang.String getIssue() {
		return issue;
	}

	public void setIssue(java.lang.String issue) {
		this.issue = issue;
	}

	@WhereSQL(sql="balanceaccount=:SoccerLeagueOrder_balanceaccount")
	public java.lang.Integer getBalanceaccount() {
		return balanceaccount;
	}

	public void setBalanceaccount(java.lang.Integer balanceaccount) {
		this.balanceaccount = balanceaccount;
	}

	@WhereSQL(sql="msg=:SoccerLeagueOrder_msg")
	public java.lang.String getMsg() {
		return msg;
	}

	public void setMsg(java.lang.String msg) {
		this.msg = msg;
	}

	@WhereSQL(sql="distributetime=:SoccerLeagueOrder_distributetime")
	public java.util.Date getDistributetime() {
		return distributetime;
	}

	public void setDistributetime(java.util.Date distributetime) {
		this.distributetime = distributetime;
	}

	@WhereSQL(sql="sysid=:SoccerLeagueOrder_sysid")
	public java.lang.String getSysid() {
		return sysid;
	}

	public void setSysid(java.lang.String sysid) {
		this.sysid = sysid;
	}
	
	@WhereSQL(sql="printtime=:SoccerLeagueOrder_printtime")
	public java.util.Date getPrinttime() {
		return printtime;
	}

	public void setPrinttime(java.util.Date printtime) {
		this.printtime = printtime;
	}
	@WhereSQL(sql="failreason=:SoccerLeagueOrder_failreason")
	public java.lang.String getFailreason() {
		return failreason;
	}

	public void setFailreason(java.lang.String failreason) {
		this.failreason = failreason;
	}

	//concstructor
	 @WhereSQL(sql="channelid=:SoccerLeagueOrder_channelid")
	public java.lang.Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(java.lang.Integer channelid) {
		this.channelid = channelid;
	}
	 @WhereSQL(sql="issuestate=:SoccerLeagueOrder_issuestate")
	public java.lang.Integer getIssuestate() {
		return issuestate;
	}

	public void setIssuestate(java.lang.Integer issuestate) {
		this.issuestate = issuestate;
	}

	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}
	
	
	/**
	 * 提交时间
	 */
	@Transient
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
	
	

	/**
	 * 提交时间
	 */
	public void setBettingtime(java.util.Date value) {
		this.bettingtime = value;
	}
	
		/**
		 * 会员id
		 */
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
	
	public SoccerLeagueOrder(){
	}
	
	@Transient
	public java.lang.Integer getPlaymethodid() {
		return playmethodid;
	}

	public void setPlaymethodid(java.lang.Integer playmethodid) {
		this.playmethodid = playmethodid;
	}
	
	
	@Transient
	public List<SoccerLeagueOrderContent> getOrdercontent() {
		return ordercontent;
	}

	public void setOrdercontent(List<SoccerLeagueOrderContent> ordercontent) {
		this.ordercontent = ordercontent;
	}

	public SoccerLeagueOrder(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 订单表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 订单表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueOrder_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 订单号
		 */
	public void setOrderid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.orderid = value;
	}
	
	
	
	/**
	 * 订单号
	 */
     @WhereSQL(sql="orderid=:SoccerLeagueOrder_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}
		/*
	public String getbettimeString() {
		return DateUtils.convertDate2String(FORMAT_BETTIME, getbettime());
	}
	public void setbettimeString(String value) throws ParseException{
		setbettime(DateUtils.convertString2Date(FORMAT_BETTIME,value));
	}*/
	
		
	
     @WhereSQL(sql="issuebetmulriple=:SoccerLeagueOrder_issuebetmulriple")
     public Integer getIssuebetmulriple() {
 		return issuebetmulriple;
 	}

 	public void setIssuebetmulriple(Integer issuebetmulriple) {
 		this.issuebetmulriple = issuebetmulriple;
 	}
	
	
	
	
	/**
	 * 会员id
	 */
     @WhereSQL(sql="memberid2=:SoccerLeagueOrder_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}

     
     @WhereSQL(sql="betmulriple=:SoccerLeagueOrder_betmulriple")
     public java.lang.Integer getBetmulriple() {
		return betmulriple;
	}

	public void setBetmulriple(java.lang.Integer betmulriple) {
		this.betmulriple = betmulriple;
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
     @WhereSQL(sql="bettingmoney=:SoccerLeagueOrder_bettingmoney")
	public java.lang.Double getBettingmoney() {
		return this.bettingmoney;
	}
		/**
		 * 投注赢
		 */
	public void setBettingwin(java.lang.Double value) {
		this.bettingwin = value;
	}
	
	/**
	 * 投注赢
	 */
     @WhereSQL(sql="bettingwin=:SoccerLeagueOrder_bettingwin")
	public java.lang.Double getBettingwin() {
		return this.bettingwin;
	}
		/*
	public String getsettletimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLETIME, getsettletime());
	}
	public void setsettletimeString(String value) throws ParseException{
		setsettletime(DateUtils.convertString2Date(FORMAT_SETTLETIME,value));
	}*/
	
		/**
		 * 结算时间
		 */
	public void setSettletime(java.util.Date value) {
		this.settletime = value;
	}
	
	/**
	 * 结算时间
	 */
     @WhereSQL(sql="settletime=:SoccerLeagueOrder_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}

	/**
		 * 状态1:正常 3:删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	/**
	 * 状态1:正常 3:删除
	 */
     @WhereSQL(sql="state=:SoccerLeagueOrder_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     
     @WhereSQL(sql="result=:SoccerLeagueOrder_result")
	public java.lang.Integer getResult() {
		return result;
	}

	public void setResult(java.lang.Integer result) {
		this.result = result;
	}
	
	 @WhereSQL(sql="contents=:SoccerLeagueOrder_contents")
	public java.lang.Integer getContents() {
		return contents;
	}

	public void setContents(java.lang.Integer contents) {
		this.contents = contents;
	}
	
	@WhereSQL(sql="schemeid=:SoccerLeagueOrder_schemeid")
	public java.lang.String getSchemeid() {
		return schemeid;
	}

	public void setSchemeid(java.lang.String schemeid) {
		this.schemeid = schemeid;
	}
	
	@Transient
	public List<SoccerSchemeMatch> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<SoccerSchemeMatch> schemecontent) {
		this.schemecontent = schemecontent;
	
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("订单表id[").append(getId()).append("],")
			.append("订单号[").append(getOrderid()).append("],")
			.append("提交时间[").append(getBettingtime()).append("],")
			.append("会员id[").append(getMemberid2()).append("],")
			.append("投注金额[").append(getBettingmoney()).append("],")
			.append("投注赢[").append(getBettingwin()).append("],")
			.append("结算时间[").append(getSettletime()).append("],")
			.append("状态1:正常 3:删除[").append(getState()).append("],")
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
		if(obj instanceof SoccerLeagueOrder == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerLeagueOrder other = (SoccerLeagueOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public String getLeftteamname() {
		return leftteamname;
	}

	public void setLeftteam(String leftteamname) {
		this.leftteamname = leftteamname;
	}
	@Transient
	public String getRightteamname() {
		return rightteamname;
	}

	public void setRightteam(String rightteamname) {
		this.rightteamname = rightteamname;
	}

	public Integer getSystemissue() {
		return systemissue;
	}

	public void setSystemissue(Integer systemissue) {
		this.systemissue = systemissue;
	}
	
	


	
}

	
