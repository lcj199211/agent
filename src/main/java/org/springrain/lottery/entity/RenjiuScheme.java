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
 * @version  2018-07-30 16:29:19
 * @see org.springrain.lottery.entity.RenjiuScheme
 */
@Table(name="renjiu_scheme")
public class RenjiuScheme  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "RenjiuScheme";
	public static final String ALIAS_ID = "订单表id";
	public static final String ALIAS_SCHEMEID = "方案id";
	public static final String ALIAS_MEMBERID2 = "会员id";
	public static final String ALIAS_BETTINGMONEY = "投注金额";
	public static final String ALIAS_BETTINGWIN = "投注赢（税后金额）";
	public static final String ALIAS_BETMULRIPLE = "倍数";
	public static final String ALIAS_STATE = "状态1:正常 3:删除";
	public static final String ALIAS_RESULT = "结果0:未结算 1:已返奖 2:已撤销 3:未中奖4非正常";
	public static final String ALIAS_SETTLETIME = "结算时间";
	public static final String ALIAS_CHANNELID = "出票渠道id";
	public static final String ALIAS_ISSUESTATE = "出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）";
	public static final String ALIAS_SYSID = "票号";
	public static final String ALIAS_PRINTTIME = "出票时间";
	public static final String ALIAS_FAILREASON = "失败原因";
	public static final String ALIAS_DISTRIBUTETIME = "分配时间";
	public static final String ALIAS_BALANCEACCOUNT = "对账状态（0：未对账；1：对账正常；2对账异常）";
	public static final String ALIAS_MSG = "单票对账异常描述";
	public static final String ALIAS_POSTTAXPRIZE = "税前金额";
	public static final String ALIAS_BETTINGRETRYTIME = "出票投注重试次数";
	public static final String ALIAS_AGENTID = "agentid";
	public static final String ALIAS_AGENTPARENTID = "agentparentid";
	public static final String ALIAS_AGENTPARENTIDS = "agentparentids";
	public static final String ALIAS_SYSTEMISSUE = "1.系统出票 3.手动出票 null系统不出票";
	public static final String ALIAS_PERIODNUM = "期号";
	public static final String ALIAS_BETTINGTIME = "投注时间";
	public static final String ALIAS_BETTINGIP = "投注ip";
	public static final String ALIAS_BETTINGTOOL = "投注工具";
	public static final String ALIAS_BETTINGCODE = "投注码";
	public static final String ALIAS_TYPE = "单式(400101)复式(400102)";
	public static final String ALIAS_PLUSAWARDS = "加奖";
    */
	//date formats
	//public static final String FORMAT_SETTLETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_PRINTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_DISTRIBUTETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	
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
	 * 会员id
	 */
	private java.lang.Integer memberid2;
	/**
	 * 投注金额
	 */
	private java.math.BigDecimal bettingmoney;
	/**
	 * 投注赢（税后金额）
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
	 * 开奖情况0:未开奖  1:已结算 2:已撤销3:非正常
	 */
	private java.lang.Integer situation;
	/**
	 * 结算时间
	 */
	private java.util.Date settletime;
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
	 * 税前金额
	 */
	private java.math.BigDecimal posttaxprize;
	/**
	 * 出票投注重试次数
	 */
	private java.lang.Integer bettingretrytime;
	/**
	 * agentid
	 */
	private java.lang.String agentid;
	/**
	 * agentparentid
	 */
	private java.lang.String agentparentid;
	/**
	 * agentparentids
	 */
	private java.lang.String agentparentids;
	/**
	 * 1.系统出票 3.手动出票 null系统不出票
	 */
	private java.lang.Integer systemissue;
	/**
	 * 期号
	 */
	private java.lang.String periodnum;
	/**
	 * 投注时间
	 */
	private java.util.Date bettingtime;
	/**
	 * 投注ip
	 */
	private java.lang.String bettingip;
	/**
	 * 投注工具
	 */
	private java.lang.String bettingtool;
	/**
	 * 投注码
	 */
	private java.lang.String bettingcode;
	/**
	 * 单式(400101)复式(400102)
	 */
	private java.lang.Integer type;
	/**
	 * 加奖
	 */
	private java.math.BigDecimal plusawards;
	//columns END 数据库字段结束
	private java.lang.String membernickname;
	private java.lang.Integer isinternal;
	private java.lang.String agentnickname;
	
	//concstructor

	public RenjiuScheme(){
	}

	public RenjiuScheme(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:RenjiuScheme_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:RenjiuScheme_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:RenjiuScheme_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}
	
     @WhereSQL(sql="bettingmoney=:RenjiuScheme_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}
	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}
	
     @WhereSQL(sql="bettingwin=:RenjiuScheme_bettingwin")
	public java.math.BigDecimal getBettingwin() {
		return this.bettingwin;
	}
	public void setBetmulriple(java.lang.Integer value) {
		this.betmulriple = value;
	}
	
     @WhereSQL(sql="betmulriple=:RenjiuScheme_betmulriple")
	public java.lang.Integer getBetmulriple() {
		return this.betmulriple;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:RenjiuScheme_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setResult(java.lang.Integer value) {
		this.result = value;
	}
	
     @WhereSQL(sql="result=:RenjiuScheme_result")
	public java.lang.Integer getResult() {
		return this.result;
	}
     
     public void setSituation(java.lang.Integer value) {
 		this.situation = value;
 	}
 	
      @WhereSQL(sql="situation=:RenjiuScheme_situation")
 	public java.lang.Integer getSituation() {
 		return this.situation;
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
	
     @WhereSQL(sql="settletime=:RenjiuScheme_settletime")
	public java.util.Date getSettletime() {
		return this.settletime;
	}
	public void setChannelid(java.lang.Integer value) {
		this.channelid = value;
	}
	
     @WhereSQL(sql="channelid=:RenjiuScheme_channelid")
	public java.lang.Integer getChannelid() {
		return this.channelid;
	}
	public void setIssuestate(java.lang.Integer value) {
		this.issuestate = value;
	}
	
     @WhereSQL(sql="issuestate=:RenjiuScheme_issuestate")
	public java.lang.Integer getIssuestate() {
		return this.issuestate;
	}
	public void setSysid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sysid = value;
	}
	
     @WhereSQL(sql="sysid=:RenjiuScheme_sysid")
	public java.lang.String getSysid() {
		return this.sysid;
	}
		/*
	public String getprinttimeString() {
		return DateUtils.convertDate2String(FORMAT_PRINTTIME, getprinttime());
	}
	public void setprinttimeString(String value) throws ParseException{
		setprinttime(DateUtils.convertString2Date(FORMAT_PRINTTIME,value));
	}*/
	
	public void setPrinttime(java.util.Date value) {
		this.printtime = value;
	}
	
     @WhereSQL(sql="printtime=:RenjiuScheme_printtime")
	public java.util.Date getPrinttime() {
		return this.printtime;
	}
	public void setFailreason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.failreason = value;
	}
	
     @WhereSQL(sql="failreason=:RenjiuScheme_failreason")
	public java.lang.String getFailreason() {
		return this.failreason;
	}
		/*
	public String getdistributetimeString() {
		return DateUtils.convertDate2String(FORMAT_DISTRIBUTETIME, getdistributetime());
	}
	public void setdistributetimeString(String value) throws ParseException{
		setdistributetime(DateUtils.convertString2Date(FORMAT_DISTRIBUTETIME,value));
	}*/
	
	public void setDistributetime(java.util.Date value) {
		this.distributetime = value;
	}
	
     @WhereSQL(sql="distributetime=:RenjiuScheme_distributetime")
	public java.util.Date getDistributetime() {
		return this.distributetime;
	}
	public void setBalanceaccount(java.lang.Integer value) {
		this.balanceaccount = value;
	}
	
     @WhereSQL(sql="balanceaccount=:RenjiuScheme_balanceaccount")
	public java.lang.Integer getBalanceaccount() {
		return this.balanceaccount;
	}
	public void setMsg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.msg = value;
	}
	
     @WhereSQL(sql="msg=:RenjiuScheme_msg")
	public java.lang.String getMsg() {
		return this.msg;
	}
	public void setPosttaxprize(java.math.BigDecimal value) {
		this.posttaxprize = value;
	}
	
     @WhereSQL(sql="posttaxprize=:RenjiuScheme_posttaxprize")
	public java.math.BigDecimal getPosttaxprize() {
		return this.posttaxprize;
	}
	public void setBettingretrytime(java.lang.Integer value) {
		this.bettingretrytime = value;
	}
	
     @WhereSQL(sql="bettingretrytime=:RenjiuScheme_bettingretrytime")
	public java.lang.Integer getBettingretrytime() {
		return this.bettingretrytime;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:RenjiuScheme_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:RenjiuScheme_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:RenjiuScheme_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setSystemissue(java.lang.Integer value) {
		this.systemissue = value;
	}
	
     @WhereSQL(sql="systemissue=:RenjiuScheme_systemissue")
	public java.lang.Integer getSystemissue() {
		return this.systemissue;
	}
	public void setPeriodnum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.periodnum = value;
	}
	
     @WhereSQL(sql="periodnum=:RenjiuScheme_periodnum")
	public java.lang.String getPeriodnum() {
		return this.periodnum;
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
	
     @WhereSQL(sql="bettingtime=:RenjiuScheme_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
	public void setBettingip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingip = value;
	}
	
     @WhereSQL(sql="bettingip=:RenjiuScheme_bettingip")
	public java.lang.String getBettingip() {
		return this.bettingip;
	}
	public void setBettingtool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingtool = value;
	}
	
     @WhereSQL(sql="bettingtool=:RenjiuScheme_bettingtool")
	public java.lang.String getBettingtool() {
		return this.bettingtool;
	}
	public void setBettingcode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingcode = value;
	}
	
     @WhereSQL(sql="bettingcode=:RenjiuScheme_bettingcode")
	public java.lang.String getBettingcode() {
		return this.bettingcode;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:RenjiuScheme_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setPlusawards(java.math.BigDecimal value) {
		this.plusawards = value;
	}
	
     @WhereSQL(sql="plusawards=:RenjiuScheme_plusawards")
	public java.math.BigDecimal getPlusawards() {
		return this.plusawards;
	}
	
	public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}
	
	@Transient
	public java.lang.Integer getIsinternal() {
		return isinternal;
	}

	public void setIsinternal(java.lang.Integer isinternal) {
		this.isinternal = isinternal;
	}
	
	@Transient
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}

	public String toString() {
		return new StringBuffer()
			.append("订单表id[").append(getId()).append("],")
			.append("方案id[").append(getSchemeid()).append("],")
			.append("会员id[").append(getMemberid2()).append("],")
			.append("投注金额[").append(getBettingmoney()).append("],")
			.append("投注赢（税后金额）[").append(getBettingwin()).append("],")
			.append("倍数[").append(getBetmulriple()).append("],")
			.append("状态1:正常 3:删除[").append(getState()).append("],")
			.append("结果0:未结算 1:已返奖 2:已撤销 3:未中奖4非正常[").append(getResult()).append("],")
			.append("结算时间[").append(getSettletime()).append("],")
			.append("出票渠道id[").append(getChannelid()).append("],")
			.append("出票状态（0待分配；1待传输；2待出票；3出票成功；4出票失败）[").append(getIssuestate()).append("],")
			.append("票号[").append(getSysid()).append("],")
			.append("出票时间[").append(getPrinttime()).append("],")
			.append("失败原因[").append(getFailreason()).append("],")
			.append("分配时间[").append(getDistributetime()).append("],")
			.append("对账状态（0：未对账；1：对账正常；2对账异常）[").append(getBalanceaccount()).append("],")
			.append("单票对账异常描述[").append(getMsg()).append("],")
			.append("税前金额[").append(getPosttaxprize()).append("],")
			.append("出票投注重试次数[").append(getBettingretrytime()).append("],")
			.append("agentid[").append(getAgentid()).append("],")
			.append("agentparentid[").append(getAgentparentid()).append("],")
			.append("agentparentids[").append(getAgentparentids()).append("],")
			.append("1.系统出票 3.手动出票 null系统不出票[").append(getSystemissue()).append("],")
			.append("期号[").append(getPeriodnum()).append("],")
			.append("投注时间[").append(getBettingtime()).append("],")
			.append("投注ip[").append(getBettingip()).append("],")
			.append("投注工具[").append(getBettingtool()).append("],")
			.append("投注码[").append(getBettingcode()).append("],")
			.append("单式(400101)复式(400102)[").append(getType()).append("],")
			.append("加奖[").append(getPlusawards()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RenjiuScheme == false) return false;
		if(this == obj) return true;
		RenjiuScheme other = (RenjiuScheme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
