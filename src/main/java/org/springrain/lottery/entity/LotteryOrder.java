package org.springrain.lottery.entity;

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
 * @author Administrator
 * @version 2018-04-10 10:48:40
 * @see org.springrain.lottery.entity.LotteryOrder
 */

@Table(name="lottery_order")
public class LotteryOrder extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	//alias
	/*
	public static final String TABLE_ALIAS = "大乐透订单";
	public static final String ALIAS_ID = "订单表id";
	public static final String ALIAS_SCHEMEID ="方案id"; 
	public static final String ALIAS_ORDERID = "订单id";
	public static final String ALIAS_MEMBERID2 = "会员id";
	public static final String ALIAS_PHASENO = "期号";
	public static final String ALIAS_PLAYTYPE = "玩法";
	public static final String ALIAS_BETCODE = "注码";
	public static final String ALIAS_BETTINGMONEY = "投注金额";
	public static final String ALIAS_BETTINGWIN = "投注赢（税后）";
	public static final String ALIAS_BETMULTIPLE = "倍数";
	public static final String ALIAS_STATE = "状态1:正常 3:删除";
	public static final String ALIAS_RESULT = "结果0:未结算 1:已中奖 2:已撤销 3:未中奖 4非正常";
	public static final String ALIAS_SETTLETIME = "结算时间";
	public static final String ALIAS_ISSUESTATE = "出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）"; 	
	public static final String ALIAS_SYSID = "票号";
	public static final String ALIAS_ADDS = "追加0否1是";
	public static final String ALIAS_PRINTTIME = "出票时间";
	public static final String ALIAS_FAILREASON = "失败原因";
	public static final String ALIAS_BETTINGRETRYTIME= "出票失败次数";
	public static final String ALIAS_BALANCEACCOUNT = "对账状态（0：未对账；1：对账正常；2对账异常）";
	public static final String ALIAS_MSG = "单票对账异常描述";
	public static final String ALIAS_POSTTAXPRIZE = "税后金额";
 	public static final String ALIAS_AGENTID = "代理商id"; 
 	public static final String ALIAS_AGENTPARENTID = "父级代理id";
 	public static final String ALIAS_AGENTPARENTIDS = "分级代理id ,隔开";
 	public static final String ALIAS_SYSTEMISSUE = "1系统出票 3手动出票  null系统不出票";
 	public static final String ALIAS_ISSUEBETMULTIPLE = "出票倍数";
 	public static final String ALIAS_CHANNELID = "出票渠道id";
    */
	//date formats
	//public static final String FORMAT_SETTLETIME = DateUtils.DATETIME_FORMAT;	
	// public static final String FORMAT_PRINTTIME = DateUtils.DATETIME_FORMAT;
	
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
	 * 订单id
	 */
	private java.lang.String orderid;
	/**
	 * 会员id
	 */
	private java.lang.Integer memberid2;
	/**
	 * 期号
	 */
	private java.lang.String phaseno;
	/**
	 * 玩法
	 */
	private java.lang.String playtype;
	/**
	 * 注码
	 */
	private java.lang.String  betcode;
	/**
	 * 投注金额
	 */
	private java.math.BigDecimal bettingmoney;
	/**
	 * 投注赢（税后）
	 */
	private java.math.BigDecimal bettingwin;
	/**
	 * 倍数
	 */
	private java.lang.Integer betmultiple;
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
	 * 出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）
	 */
	private java.lang.Integer issuestate;
	/**
	 * 票号
	 */
	private java.lang.String sysid;
	/**
	 * 追加0否1是
	 */
	private java.lang.Integer adds;
	/**
	 * 出票时间
	 */
	private java.util.Date printtime;
	/**
	 * 失败原因
	 */
	private java.lang.String failreason;
	/**
	 * 出票失败次数
	 */
	private java.lang.Integer bettingretrytime;
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
	/**
	 * 1系统出票3手动出票null系统不出票
	 */
	private java.lang.Integer systemissue;
	/**
	 * 出票倍数
	 */
	private java.lang.Integer issuebetmultiple;
	/**
	 * 出票渠道id
	 */
	private java.lang.Integer channelid;
	// columns END 数据库字段结束
	private java.util.Date bettingtime;//投注时间
	private java.lang.String playmethod;//玩法名
	private java.lang.String bettingip;//投注IP
	private java.lang.String membernickname;//投注IP


	// concstructor
	public LotteryOrder() {
	}
	
	public LotteryOrder(java.lang.Integer id) {
		this.id = id;
	}
		
	//get and set
	
	/**
	 * 订单表id
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
	@WhereSQL(sql = "id=:LotteryOrder_id")
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 方案id
	 */
	public void setSchemeid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.schemeid = value;
	}

	@WhereSQL(sql = "schemeid=:LotteryOrder_schemeid")
	public java.lang.String getSchemeid() {
			return this.schemeid;
	}

	/**
	 * 订单id
	 */
	public void setOrderid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.orderid = value;
	}

	@WhereSQL(sql = "orderid=:LotteryOrder_orderid")
	public java.lang.String getOrderid() {
		return this.orderid;
	}

	/**
	 * 会员id
	 */
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}

	@WhereSQL(sql = "memberid2=:LotteryOrder_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}

	/**
	 * 期号数
	 */
	public void setPhaseno(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.phaseno = value;
	}

	@WhereSQL(sql="phaseno=:LotteryScheme_phaseno")
	public java.lang.String getPhaseno() {
		return this.phaseno;
	}
	/**
	 * 玩法
	 */
	public void setPlaytype(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.playtype = value;
	}

	@WhereSQL(sql="phaseno=:LotteryScheme_phaseno")
	public java.lang.String getPlaytype() {
		return this.playtype;
	}
	/**
	 * 注码
	 */
	public void setBetcode(java.lang.String value) {
		this.betcode = value;
	}

	@WhereSQL(sql = "betcode=:LotteryOrder_betcode")
	public java.lang.String getBetcode() {
		return this.betcode;
	}
	
	/**
	 * 投注金额
	 */
	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}

	@WhereSQL(sql = "bettingmoney=:LotteryOrder_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}

	/**
	 * 投注金额
	 */
	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}

	@WhereSQL(sql = "bettingwin=:LotteryOrder_bettingwin")
	public java.math.BigDecimal getBettingwin() {
		return this.bettingwin;
	}
	
	/**
	 * 倍数
	 */
	public void setBetmultiple(java.lang.Integer value) {
		this.betmultiple = value;
	}

	@WhereSQL(sql = "betmultiple=:LotteryOrder_betmultiple")
	public java.lang.Integer getBetmultiple() {
		return this.betmultiple;
	}

	/**
	 * 状态1:正常 3:删除
	 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}

	@WhereSQL(sql = "state=:LotteryOrder_state")
	public java.lang.Integer getState() {
		return this.state;
	}

	/**
	 * 结果0:未结算 1:已中奖 2:已撤销 3:未中奖4非正常
	 */
	public void setResult(java.lang.Integer value) {
		this.result = value;
	}

	@WhereSQL(sql = "result=:LotteryOrder_result")
	public java.lang.Integer getResult() {
		return this.result;
	}

	/**
	 * 结算时间
	 */
	public void setSettletime(java.util.Date value) {
		this.settletime = value;
	}

	@WhereSQL(sql = "settletime=:LotteryOrder_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}

	/**
	 * 出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）
	 */
	public void setIssuestate(java.lang.Integer value) {
		this.issuestate = value;
	}

	@WhereSQL(sql = "issuestate=:LotteryOrder_issuestate")
	public java.lang.Integer getIssuestate() {
		return this.issuestate;
	}

	/**
	 * 票号
	 */
	public void setSysid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.sysid = value;
	}

	@WhereSQL(sql = "sysid=:LotteryOrder_sysid")
	public java.lang.String getSysid() {
		return this.sysid;
	}
	
	/**
	 * 追加0否1是
	 */
	public void setAdds(java.lang.Integer value) {
		this.adds = value;
	}

	@WhereSQL(sql = "state=:LotteryOrder_state")
	public java.lang.Integer getAdds() {
		return this.adds;
	}
	
	/**
	 * 出票时间
	 */
	public void setPrinttime(java.util.Date value) {
		this.printtime = value;
	}

	@WhereSQL(sql = "printtime=:LotteryOrder_printtime")
	public java.util.Date getPrinttime() {
		return this.printtime;
	}
	
	/**
	 * 失败原因
	 */
	public void setFailreason(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.failreason = value;
	}

	@WhereSQL(sql = "failreason=:LotteryOrder_failreason")
	public java.lang.String getFailreason() {
		return this.failreason;
	}

	/**
	 * 出票失败次数
	 */
	public void setBettingretrytime(java.lang.Integer value) {
		this.bettingretrytime = value;
	}

	@WhereSQL(sql = "bettingretrytime=:LotteryOrder_bettingretrytime")
	public java.lang.Integer getBettingretrytime() {
		return this.bettingretrytime;
	}
	
	/**
	 * 对账状态（0：未对账；1：对账正常；2对账异常）
	 */
	public void setBalanceaccount(java.lang.Integer value) {
		this.balanceaccount = value;
	}

	@WhereSQL(sql = "balanceaccount=:LotteryOrder_balanceaccount")
	public java.lang.Integer getBalanceaccount() {
		return this.balanceaccount;
	}

	/**
	 * 单票对账异常描述
	 */
	public void setMsg(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.msg = value;
	}

	@WhereSQL(sql = "msg=:LotteryOrder_msg")
	public java.lang.String getMsg() {
		return this.msg;
	}

	/**
	 * 税后金额
	 */
	public void setPosttaxprize(java.math.BigDecimal value) {
		this.posttaxprize = value;
	}

	@WhereSQL(sql = "posttaxprize=:LotteryOrder_posttaxprize")
	public java.math.BigDecimal getPosttaxprize() {
		return this.posttaxprize;
	}

	/**
	 * 代理商id
	 */
	public void setAgentid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.agentid = value;
	}

	@WhereSQL(sql = "agentid=:LotteryOrder_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}

	/**
	 * 父级代理id
	 */
	public void setAgentparentid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.agentparentid = value;
	}

	@WhereSQL(sql = "agentparentid=:LotteryOrder_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}

	/**
	 * 分级代理id ,隔开
	 */
	public void setAgentparentids(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.agentparentids = value;
	}

	@WhereSQL(sql = "agentparentids=:LotteryOrder_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	
	/**
	 * 1系统出票3手动出票null系统不出票
	 */
	public void setSystemissue(java.lang.Integer value) {
		this.systemissue = value;
	}

	@WhereSQL(sql = "systemissue=:LotteryOrder_systemissue")
	public java.lang.Integer getSystemissue() {
		return this.systemissue;
	}
	
	/**
	 * 出票倍数
	 */
	public void setIssuebetmultiple(java.lang.Integer value) {
		this.issuebetmultiple = value;
	}

	@WhereSQL(sql = "issuebetmultiple=:LotteryOrder_issuebetmultiple")
	public java.lang.Integer getIssuebetmultiple() {
		return this.issuebetmultiple;
	}
	
	/**
	 * 出票渠道id
	 */
	public void setChannelid(java.lang.Integer value) {
		this.channelid = value;
	}

	@WhereSQL(sql = "channelid=:LotteryOrder_channelid")
	public java.lang.Integer getChannelid() {
		return this.channelid;
	}
	
	public String toString() {
		return new StringBuffer()
				.append("订单表id[").append(getId()).append("],")
				.append("方案id[").append(getSchemeid()).append("],")
				.append("订单号[").append(getOrderid()).append("],")
				.append("会员id[").append(getMemberid2()).append("],")
				.append("期号[").append(getPhaseno()).append("],")
				.append("玩法[").append(getPlaytype()).append("],")
				.append("注码[").append(getBetcode()).append("],")
				.append("投注金额[").append(getBettingmoney()).append("],")
				.append("投注赢（税后）[").append(getBettingwin()).append("],")
				.append("倍数[").append(getBetmultiple()).append("],")
				.append("状态1:正常 3:删除[").append(getState()).append("],")
				.append("结果0:未结算 1:已中奖 2:已撤销 3:未中奖4非正常[").append(getResult()).append("],")
				.append("结算时间[").append(getSettletime()).append("],")
				.append("出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）[").append(getIssuestate()).append("],")
				.append("票号[").append(getSysid()).append("],")
				.append("追加0否1是[").append(getAdds()).append("],")
				.append("出票时间[").append(getPrinttime()).append("],")
				.append("失败原因[").append(getFailreason()).append("],")
				.append("出票失败次数[").append(getBettingretrytime()).append("],")
				.append("对账状态（0：未对账；1：对账正常；2对账异常）[").append(getBalanceaccount()).append("],")
				.append("单票对账异常描述[").append(getMsg()).append("],")
				.append("税后金额[").append(getPosttaxprize()).append("],")
				.append("代理商id[").append(getAgentid()).append("],")
				.append("父级代理id[").append(getAgentparentid()).append("],")
				.append("分级代理id ,隔开[").append(getAgentparentids()).append("],")
				.append("1系统出票 3手动出票 null系统不出票[").append(getSystemissue()).append("],")
				.append("出票倍数").append(getIssuebetmultiple()).append("],")
				.append("出票渠道id").append(getChannelid()).append("],").toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof LotteryOrder == false)
			return false;
		if (this == obj)
			return true;
		LotteryOrder other = (LotteryOrder) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	public java.util.Date getBettingtime() {
		return bettingtime;
	}

	public void setBettingtime(java.util.Date bettingtime) {
		this.bettingtime = bettingtime;
	}

	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}
	
	public java.lang.String getBettingip() {
		return bettingip;
	}

	public void setBettingip(java.lang.String bettingip) {
		this.bettingip = bettingip;
	}
	
	public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}
}
