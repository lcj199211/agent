package org.springrain.lottery.entity;

import java.util.Date;
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
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-12-12 09:36:43
 * @see org.springrain.lottery.entity.BjdcOrder
 */
@Table(name = "bjdc_order")
public class BjdcOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// alias
	/*
	 * public static final String TABLE_ALIAS = "北京单场订单"; public static final
	 * String ALIAS_ID = "订单表id"; public static final String ALIAS_SCHEMEID =
	 * "方案id"; public static final String ALIAS_ORDERID = "订单号"; public static
	 * final String ALIAS_MEMBERID2 = "会员id"; public static final String
	 * ALIAS_BETTINGMONEY = "投注金额"; public static final String ALIAS_BETTINGWIN
	 * = "投注赢"; public static final String ALIAS_BETMULRIPLE = "倍数"; public
	 * static final String ALIAS_STATE = "状态1:正常 3:删除"; public static final
	 * String ALIAS_RESULT = "结果0:未结算 1:已中奖 2:已撤销 3:未中奖4非正常"; public static
	 * final String ALIAS_SETTLETIME = "结算时间"; public static final String
	 * ALIAS_CONTENTS = "比赛场数"; public static final String ALIAS_CHANNELID =
	 * "出票渠道id"; public static final String ALIAS_ISSUESTATE =
	 * "出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）"; public static final String
	 * ALIAS_SYSID = "票号"; public static final String ALIAS_PRINTTIME = "出票时间";
	 * public static final String ALIAS_FAILREASON = "失败原因"; public static final
	 * String ALIAS_DISTRIBUTETIME = "分配时间"; public static final String
	 * ALIAS_BALANCEACCOUNT = "对账状态（0：未对账；1：对账正常；2对账异常）"; public static final
	 * String ALIAS_MSG = "单票对账异常描述"; public static final String
	 * ALIAS_POSTTAXPRIZE = "税后金额"; public static final String ALIAS_AGENTID =
	 * "代理商id"; public static final String ALIAS_AGENTPARENTID = "父级代理id";
	 * public static final String ALIAS_AGENTPARENTIDS = "分级代理id ,隔开";
	 */
	// date formats
	// public static final String FORMAT_SETTLETIME = DateUtils.DATETIME_FORMAT;
	// public static final String FORMAT_PRINTTIME = DateUtils.DATETIME_FORMAT;
	// public static final String FORMAT_DISTRIBUTETIME =
	// DateUtils.DATETIME_FORMAT;

	// columns START
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
	 * 结果0:未结算 1:已中奖 2:已撤销 3:未中奖4非正常
	 */
	private java.lang.Integer result;
	/**
	 * 结算时间
	 */
	private java.util.Date settletime;
	/**
	 * 比赛场数
	 */
	private java.lang.Integer contents;
	/**
	 * 出票渠道id
	 */
	private java.lang.Integer channelid;
	/**
	 * 出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）
	 */
	private java.lang.Integer issuestate;
	/**
	 * 票号
	 */
	private java.lang.String sysid;
	/**
	 * 出票时间
	 */
	private java.util.Date printtime;
	/**
	 * 失败原因
	 */
	private java.lang.String failreason;
	/**
	 * 分配时间
	 */
	private java.util.Date distributetime;
	/**
	 * 对账状态（0：未对账；1：对账正常；2对账异常）
	 */
	private java.lang.Integer balanceaccount;
	/**
	 * 单票对账异常描述
	 */
	private java.lang.String msg;
	/**
	 * 税后金额
	 */
	private java.math.BigDecimal posttaxprize;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 父级代理id
	 */
	private java.lang.String agentparentid;
	/**
	 * 分级代理id ,隔开
	 */
	private java.lang.String agentparentids;
	// columns END 数据库字段结束
	private java.lang.Integer playmethodid;
	private java.lang.String playmethod;
	private java.lang.String issue;
	private java.lang.Integer bettingretrytime;
	private List<BjdcOrderContent> ordercontent;
	private List<BjdcOrderContent> content;
	private Date bettingtime;
	private List<BjdcSchemeMatch> schemecontent;
	private String membernickname;
	private String hometeam;
	private String guestteam;
	private  Integer systemissue;
	private Integer issuebetmulriple; 
	// concstructor

	public BjdcOrder() {
	}

	public BjdcOrder(java.lang.Integer id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	@Id
	@WhereSQL(sql = "id=:BjdcOrder_id")
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setSchemeid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.schemeid = value;
	}
	
	@Transient
	public List<BjdcOrderContent> getContent() {
		return content;
	}

	public void setContent(List<BjdcOrderContent> content) {
		this.content = content;
	}
	@WhereSQL(sql = "schemeid=:BjdcOrder_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}

	public void setOrderid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.orderid = value;
	}
	@Transient
	public String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(String membernickname) {
		this.membernickname = membernickname;
	}
	@WhereSQL(sql = "orderid=:BjdcOrder_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}

	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}

	@WhereSQL(sql = "memberid2=:BjdcOrder_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}

	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}

	@WhereSQL(sql = "bettingmoney=:BjdcOrder_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}

	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}

	@WhereSQL(sql = "bettingwin=:BjdcOrder_bettingwin")
	public java.math.BigDecimal getBettingwin() {
		return this.bettingwin;
	}

	public void setBetmulriple(java.lang.Integer value) {
		this.betmulriple = value;
	}

	@WhereSQL(sql = "betmulriple=:BjdcOrder_betmulriple")
	public java.lang.Integer getBetmulriple() {
		return this.betmulriple;
	}

	public void setState(java.lang.Integer value) {
		this.state = value;
	}

	@WhereSQL(sql = "state=:BjdcOrder_state")
	public java.lang.Integer getState() {
		return this.state;
	}

	public void setResult(java.lang.Integer value) {
		this.result = value;
	}

	@WhereSQL(sql = "result=:BjdcOrder_result")
	public java.lang.Integer getResult() {
		return this.result;
	}

	/*
	 * public String getsettletimeString() { return
	 * DateUtils.convertDate2String(FORMAT_SETTLETIME, getsettletime()); }
	 * public void setsettletimeString(String value) throws ParseException{
	 * setsettletime(DateUtils.convertString2Date(FORMAT_SETTLETIME,value)); }
	 */

	public void setSettletime(java.util.Date value) {
		this.settletime = value;
	}

	@WhereSQL(sql = "settletime=:BjdcOrder_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}

	public void setContents(java.lang.Integer value) {
		this.contents = value;
	}

	@WhereSQL(sql = "contents=:BjdcOrder_contents")
	public java.lang.Integer getContents() {
		return this.contents;
	}

	public void setChannelid(java.lang.Integer value) {
		this.channelid = value;
	}

	@WhereSQL(sql = "channelid=:BjdcOrder_channelid")
	public java.lang.Integer getChannelid() {
		return this.channelid;
	}

	public void setIssuestate(java.lang.Integer value) {
		this.issuestate = value;
	}

	@WhereSQL(sql = "issuestate=:BjdcOrder_issuestate")
	public java.lang.Integer getIssuestate() {
		return this.issuestate;
	}

	public void setSysid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.sysid = value;
	}

	@WhereSQL(sql = "sysid=:BjdcOrder_sysid")
	public java.lang.String getSysid() {
		return this.sysid;
	}

	/*
	 * public String getprinttimeString() { return
	 * DateUtils.convertDate2String(FORMAT_PRINTTIME, getprinttime()); } public
	 * void setprinttimeString(String value) throws ParseException{
	 * setprinttime(DateUtils.convertString2Date(FORMAT_PRINTTIME,value)); }
	 */

	public void setPrinttime(java.util.Date value) {
		this.printtime = value;
	}

	@WhereSQL(sql = "printtime=:BjdcOrder_printtime")
	public java.util.Date getPrinttime() {
		return this.printtime;
	}

	public void setFailreason(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.failreason = value;
	}

	@WhereSQL(sql = "failreason=:BjdcOrder_failreason")
	public java.lang.String getFailreason() {
		return this.failreason;
	}

	/*
	 * public String getdistributetimeString() { return
	 * DateUtils.convertDate2String(FORMAT_DISTRIBUTETIME, getdistributetime());
	 * } public void setdistributetimeString(String value) throws
	 * ParseException{
	 * setdistributetime(DateUtils.convertString2Date(FORMAT_DISTRIBUTETIME
	 * ,value)); }
	 */

	public void setDistributetime(java.util.Date value) {
		this.distributetime = value;
	}

	@WhereSQL(sql = "distributetime=:BjdcOrder_distributetime")
	public java.util.Date getDistributetime() {
		return this.distributetime;
	}

	public void setBalanceaccount(java.lang.Integer value) {
		this.balanceaccount = value;
	}

	@WhereSQL(sql = "balanceaccount=:BjdcOrder_balanceaccount")
	public java.lang.Integer getBalanceaccount() {
		return this.balanceaccount;
	}

	public void setMsg(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.msg = value;
	}

	@WhereSQL(sql = "msg=:BjdcOrder_msg")
	public java.lang.String getMsg() {
		return this.msg;
	}

	public void setPosttaxprize(java.math.BigDecimal value) {
		this.posttaxprize = value;
	}

	@WhereSQL(sql = "posttaxprize=:BjdcOrder_posttaxprize")
	public java.math.BigDecimal getPosttaxprize() {
		return this.posttaxprize;
	}

	public void setAgentid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.agentid = value;
	}

	@WhereSQL(sql = "agentid=:BjdcOrder_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}

	public void setAgentparentid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.agentparentid = value;
	}

	@WhereSQL(sql = "agentparentid=:BjdcOrder_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}

	public void setAgentparentids(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.agentparentids = value;
	}

	@WhereSQL(sql = "agentparentids=:BjdcOrder_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	@Transient
	public List<BjdcSchemeMatch> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<BjdcSchemeMatch> schemecontent) {
		this.schemecontent = schemecontent;
	}
	public String toString() {
		return new StringBuffer().append("订单表id[").append(getId()).append("],")
				.append("方案id[").append(getSchemeid()).append("],")
				.append("订单号[").append(getOrderid()).append("],")
				.append("会员id[").append(getMemberid2()).append("],")
				.append("投注金额[").append(getBettingmoney()).append("],")
				.append("投注赢[").append(getBettingwin()).append("],")
				.append("倍数[").append(getBetmulriple()).append("],")
				.append("状态1:正常 3:删除[").append(getState()).append("],")
				.append("结果0:未结算 1:已中奖 2:已撤销 3:未中奖4非正常[").append(getResult())
				.append("],").append("结算时间[").append(getSettletime())
				.append("],").append("比赛场数[").append(getContents())
				.append("],").append("出票渠道id[").append(getChannelid())
				.append("],").append("出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）[")
				.append(getIssuestate()).append("],").append("票号[")
				.append(getSysid()).append("],").append("出票时间[")
				.append(getPrinttime()).append("],").append("失败原因[")
				.append(getFailreason()).append("],").append("分配时间[")
				.append(getDistributetime()).append("],")
				.append("对账状态（0：未对账；1：对账正常；2对账异常）[")
				.append(getBalanceaccount()).append("],").append("单票对账异常描述[")
				.append(getMsg()).append("],").append("税后金额[")
				.append(getPosttaxprize()).append("],").append("代理商id[")
				.append(getAgentid()).append("],").append("父级代理id[")
				.append(getAgentparentid()).append("],").append("分级代理id ,隔开[")
				.append(getAgentparentids()).append("],").toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof BjdcOrder == false)
			return false;
		if (this == obj)
			return true;
		BjdcOrder other = (BjdcOrder) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Transient
	public java.lang.Integer getPlaymethodid() {
		return playmethodid;
	}

	public void setPlaymethodid(java.lang.Integer playmethodid) {
		this.playmethodid = playmethodid;
	}

	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}

	@Transient
	public java.lang.String getIssue() {
		return issue;
	}

	public void setIssue(java.lang.String issue) {
		this.issue = issue;
	}

	@Transient
	public java.lang.Integer getBettingretrytime() {
		return bettingretrytime;
	}

	public void setBettingretrytime(java.lang.Integer bettingretrytime) {
		this.bettingretrytime = bettingretrytime;
	}

	@Transient
	public List<BjdcOrderContent> getOrdercontent() {
		return ordercontent;
	}

	public void setOrdercontent(List<BjdcOrderContent> ordercontent) {
		this.ordercontent = ordercontent;
	}

	@Transient
	public Date getBettingtime() {
		return bettingtime;
	}

	public void setBettingtime(Date bettingtime) {
		this.bettingtime = bettingtime;
	}
	@Transient
	public String getHometeam() {
		return hometeam;
	}

	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}
	@Transient
	public String getGuestteam() {
		return guestteam;
	}

	public void setGuestteam(String guestteam) {
		this.guestteam = guestteam;
	}

	public Integer getSystemissue() {
		return systemissue;
	}

	public void setSystemissue(Integer systemissue) {
		this.systemissue = systemissue;
	}
	@WhereSQL(sql = "issuebetmulriple=:BjdcOrder_issuebetmulriple")
	public Integer getIssuebetmulriple() {
		return issuebetmulriple;
	}

	public void setIssuebetmulriple(Integer issuebetmulriple) {
		this.issuebetmulriple = issuebetmulriple;
	}


	

	
}
