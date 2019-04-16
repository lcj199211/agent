package org.springrain.lottery.entity;

import java.math.BigDecimal;
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
 * @version  2017-12-12 09:37:24
 * @see org.springrain.lottery.entity.BjdcScheme
 */
@Table(name="bjdc_scheme")
public class BjdcScheme  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "北京单场方案";
	public static final String ALIAS_ID = "方案表id";
	public static final String ALIAS_SCHEMEID = "方案id";
	public static final String ALIAS_PLAYTYPE = "投注玩法(逗号隔开)";
	public static final String ALIAS_PLAYMETHODID = "玩法id";
	public static final String ALIAS_BETTINGMONEY = "投注金额";
	public static final String ALIAS_BETTINGWIN = "投注输赢";
	public static final String ALIAS_BETTINGTIME = "投注时间";
	public static final String ALIAS_BETTINGIP = "投注ip";
	public static final String ALIAS_BETTINGTOOL = "投注工具";
	public static final String ALIAS_MEMBERID2 = "用户id2";
	public static final String ALIAS_BETMULRIPLE = "倍数";
	public static final String ALIAS_STATE = "状态1:正常 3:删除";
	public static final String ALIAS_MATCHES = "比赛数量";
	public static final String ALIAS_SITUATION = "开奖情况0:未开奖  1:已开奖 2:已撤销3:非正常";
	public static final String ALIAS_SETTLEMENTTIME = "结算时间";
	public static final String ALIAS_THEORETICALBONUS = "理论奖金";
	public static final String ALIAS_BUYTYPE = "购买类型 0自购 1跟买 2神单";
	public static final String ALIAS_BROKERAGE = "佣金比例";
	public static final String ALIAS_SCHEMEID2 = "合买号,跟单号";
	public static final String ALIAS_ENDTIME = "神单截止时间";
	public static final String ALIAS_BROKERAGEMONEY = "佣金";
	public static final String ALIAS_MINBETTING = "起投";
	public static final String ALIAS_BETTINGNUM = "人气";
	public static final String ALIAS_BETTINGALREADY = "已跟单金额";
	public static final String ALIAS_ISSUESTATE = "出票状态（0未出票；1出票中；2出票成功；3出票失败）";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "父级代理id";
	public static final String ALIAS_AGENTPARENTIDS = "分级代理id ,隔开";
    */
	//date formats
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SETTLEMENTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 方案表id
	 */
	private java.lang.Integer id;
	/**
	 * 方案id
	 */
	private java.lang.String schemeid;
	/**
	 * 投注玩法(逗号隔开)
	 */
	private java.lang.String playtype;
	/**
	 * 玩法id
	 */
	private java.lang.Integer playmethodid;
	/**
	 * 投注金额
	 */
	private java.math.BigDecimal bettingmoney;
	/**
	 * 投注输赢
	 */
	private java.math.BigDecimal bettingwin;
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
	 * 用户id2
	 */
	private java.lang.Integer memberid2;
	/**
	 * 倍数
	 */
	private java.lang.Integer betmulriple;
	/**
	 * 状态1:正常 3:删除
	 */
	private java.lang.Integer state;
	/**
	 * 比赛数量
	 */
	private java.lang.Integer matches;
	/**
	 * 开奖情况0:未开奖  1:已开奖 2:已撤销3:非正常
	 */
	private java.lang.Integer situation;
	/**
	 * 结算时间
	 */
	private java.util.Date settlementtime;
	/**
	 * 理论奖金
	 */
	private java.lang.String theoreticalbonus;
	/**
	 * 购买类型 0自购 1跟买 2神单
	 */
	private java.lang.Integer buytype;
	/**
	 * 佣金比例
	 */
	private java.lang.Integer brokerage;
	/**
	 * 合买号,跟单号
	 */
	private java.lang.String schemeid2;
	/**
	 * 神单截止时间
	 */
	private java.util.Date endtime;
	/**
	 * 佣金
	 */
	private java.math.BigDecimal brokeragemoney;
	/**
	 * 起投
	 */
	private java.math.BigDecimal minbetting;
	/**
	 * 人气
	 */
	private java.lang.Integer bettingnum;
	/**
	 * 已跟单金额
	 */
	private java.math.BigDecimal bettingalready;
	/**
	 * 出票状态（0未出票；1出票中；2出票成功；3出票失败）
	 */
	private java.lang.Integer issuestate;
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
	//columns END 数据库字段结束
	
	/**
	 * 玩法
	 */
	private java.lang.String playmethod;
	
	private java.lang.String membernickname;
	
	/**
	 * 理论奖金
	 */
	private java.lang.Double theoreticalbonusmax; 
	/**
	 * 最大理论奖金倍数
	 */
	private java.lang.Double maxmultiple; 
	
	
	/**
	 * 是否内部员工
	 */
	private java.lang.Integer isinternal; 
	
	private BigDecimal guarantee;
	/**
	 * 方案内容
	 */
	private List<BjdcSchemeMatch> schemecontent;
	private BigDecimal brokeragemembermoney;
	private BigDecimal brokerageagentmoney;
	private String brokerageagentid;
	private Integer pubstate;
	private String statement;
	private BigDecimal plusawards;
	private BigDecimal pretaxamount;
	
	private Integer systemissue;
	
	/**
	 * 一级代理佣金比例
	 */
	private java.math.BigDecimal brokerageagent;
	/**
	 * 大神佣金比例
	 */
	private java.math.BigDecimal brokeragemember;
	
	//concstructor
	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}

	@Transient
	public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}

	@Transient
	public List<BjdcSchemeMatch> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<BjdcSchemeMatch> schemecontent) {
		this.schemecontent = schemecontent;
	}

	public BjdcScheme(){
	}

	public BjdcScheme(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BjdcScheme_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
     @WhereSQL(sql="schemeid=:BjdcScheme_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	public void setPlaytype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.playtype = value;
	}
	
     @WhereSQL(sql="playtype=:BjdcScheme_playtype")
	public java.lang.String getPlaytype() {
		return this.playtype;
	}
	public void setPlaymethodid(java.lang.Integer value) {
		this.playmethodid = value;
	}
	
     @WhereSQL(sql="playmethodid=:BjdcScheme_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}
	
     @WhereSQL(sql="bettingmoney=:BjdcScheme_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}
	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}
	
     @WhereSQL(sql="bettingwin=:BjdcScheme_bettingwin")
	public java.math.BigDecimal getBettingwin() {
		return this.bettingwin;
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
	
     @WhereSQL(sql="bettingtime=:BjdcScheme_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
	public void setBettingip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingip = value;
	}
	
     @WhereSQL(sql="bettingip=:BjdcScheme_bettingip")
	public java.lang.String getBettingip() {
		return this.bettingip;
	}
	public void setBettingtool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingtool = value;
	}
	
     @WhereSQL(sql="bettingtool=:BjdcScheme_bettingtool")
	public java.lang.String getBettingtool() {
		return this.bettingtool;
	}
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BjdcScheme_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
	public void setBetmulriple(java.lang.Integer value) {
		this.betmulriple = value;
	}
	
     @WhereSQL(sql="betmulriple=:BjdcScheme_betmulriple")
	public java.lang.Integer getBetmulriple() {
		return this.betmulriple;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BjdcScheme_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setMatches(java.lang.Integer value) {
		this.matches = value;
	}
	
     @WhereSQL(sql="matches=:BjdcScheme_matches")
	public java.lang.Integer getMatches() {
		return this.matches;
	}
	public void setSituation(java.lang.Integer value) {
		this.situation = value;
	}
	
     @WhereSQL(sql="situation=:BjdcScheme_situation")
	public java.lang.Integer getSituation() {
		return this.situation;
	}
		/*
	public String getsettlementtimeString() {
		return DateUtils.convertDate2String(FORMAT_SETTLEMENTTIME, getsettlementtime());
	}
	public void setsettlementtimeString(String value) throws ParseException{
		setsettlementtime(DateUtils.convertString2Date(FORMAT_SETTLEMENTTIME,value));
	}*/
	
	public void setSettlementtime(java.util.Date value) {
		this.settlementtime = value;
	}
	
     @WhereSQL(sql="settlementtime=:BjdcScheme_settlementtime")
	public java.util.Date getSettlementtime() {
		return this.settlementtime;
	}
	public void setTheoreticalbonus(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.theoreticalbonus = value;
	}
	
     @WhereSQL(sql="theoreticalbonus=:BjdcScheme_theoreticalbonus")
	public java.lang.String getTheoreticalbonus() {
		return this.theoreticalbonus;
	}
	public void setBuytype(java.lang.Integer value) {
		this.buytype = value;
	}
	
     @WhereSQL(sql="buytype=:BjdcScheme_buytype")
	public java.lang.Integer getBuytype() {
		return this.buytype;
	}
	public void setBrokerage(java.lang.Integer value) {
		this.brokerage = value;
	}
	
     @WhereSQL(sql="brokerage=:BjdcScheme_brokerage")
	public java.lang.Integer getBrokerage() {
		return this.brokerage;
	}
	public void setSchemeid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid2 = value;
	}
	
     @WhereSQL(sql="schemeid2=:BjdcScheme_schemeid2")
	public java.lang.String getSchemeid2() {
		return this.schemeid2;
	}
		/*
	public String getendtimeString() {
		return DateUtils.convertDate2String(FORMAT_ENDTIME, getendtime());
	}
	public void setendtimeString(String value) throws ParseException{
		setendtime(DateUtils.convertString2Date(FORMAT_ENDTIME,value));
	}*/
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
     @WhereSQL(sql="endtime=:BjdcScheme_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setBrokeragemoney(java.math.BigDecimal value) {
		this.brokeragemoney = value;
	}
	
     @WhereSQL(sql="brokeragemoney=:BjdcScheme_brokeragemoney")
	public java.math.BigDecimal getBrokeragemoney() {
		return this.brokeragemoney;
	}
	public void setMinbetting(java.math.BigDecimal value) {
		this.minbetting = value;
	}
	
     @WhereSQL(sql="minbetting=:BjdcScheme_minbetting")
	public java.math.BigDecimal getMinbetting() {
		return this.minbetting;
	}
	public void setBettingnum(java.lang.Integer value) {
		this.bettingnum = value;
	}
	
     @WhereSQL(sql="bettingnum=:BjdcScheme_bettingnum")
	public java.lang.Integer getBettingnum() {
		return this.bettingnum;
	}
	public void setBettingalready(java.math.BigDecimal value) {
		this.bettingalready = value;
	}
	
     @WhereSQL(sql="bettingalready=:BjdcScheme_bettingalready")
	public java.math.BigDecimal getBettingalready() {
		return this.bettingalready;
	}
	public void setIssuestate(java.lang.Integer value) {
		this.issuestate = value;
	}
	
     @WhereSQL(sql="issuestate=:BjdcScheme_issuestate")
	public java.lang.Integer getIssuestate() {
		return this.issuestate;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BjdcScheme_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BjdcScheme_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BjdcScheme_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
     public BigDecimal getGuarantee() {
 		return guarantee;
 	}

 	public void setGuarantee(BigDecimal guarantee) {
 		this.guarantee = guarantee;
 	}
 	@WhereSQL(sql="brokeragemembermoney=:BjdcScheme_brokeragemembermoney")
 	public BigDecimal getBrokeragemembermoney() {
 		return brokeragemembermoney;
 	}

 	public void setBrokeragemembermoney(BigDecimal brokeragemembermoney) {
 		this.brokeragemembermoney = brokeragemembermoney;
 	}
 	@WhereSQL(sql="brokerageagentmoney=:BjdcScheme_brokerageagentmoney")
 	public BigDecimal getBrokerageagentmoney() {
 		return brokerageagentmoney;
 	}

 	public void setBrokerageagentmoney(BigDecimal brokerageagentmoney) {
 		this.brokerageagentmoney = brokerageagentmoney;
 	}
 	@WhereSQL(sql="brokerageagentid=:BjdcScheme_brokerageagentid")
 	public String getBrokerageagentid() {
 		return brokerageagentid;
 	}

 	public void setBrokerageagentid(String brokerageagentid) {
 		this.brokerageagentid = brokerageagentid;
 	}
 	@WhereSQL(sql="pubstate=:BjdcScheme_pubstate")
 	public Integer getPubstate() {
 		return pubstate;
 	}

 	public void setPubstate(Integer pubstate) {
 		this.pubstate = pubstate;
 	}
 	@WhereSQL(sql="statement=:BjdcScheme_statement")
 	public String getStatement() {
 		return statement;
 	}

 	public void setStatement(String statement) {
 		this.statement = statement;
 	}
 	@WhereSQL(sql="plusawards=:BjdcScheme_plusawards")
 	public BigDecimal getPlusawards() {
 		return plusawards;
 	}

 	public void setPlusawards(BigDecimal plusawards) {
 		this.plusawards = plusawards;
 	}
 	@WhereSQL(sql="pretaxamount=:BjdcScheme_pretaxamount")
 	public BigDecimal getPretaxamount() {
 		return pretaxamount;
 	}

 	public void setPretaxamount(BigDecimal pretaxamount) {
 		this.pretaxamount = pretaxamount;
 	}
 	@Transient
	public Integer getSystemissue() {
		return systemissue;
	}

	public void setSystemissue(Integer systemissue) {
		this.systemissue = systemissue;
	}
	public String toString() {
		return new StringBuffer()
			.append("方案表id[").append(getId()).append("],")
			.append("方案id[").append(getSchemeid()).append("],")
			.append("投注玩法(逗号隔开)[").append(getPlaytype()).append("],")
			.append("玩法id[").append(getPlaymethodid()).append("],")
			.append("投注金额[").append(getBettingmoney()).append("],")
			.append("投注输赢[").append(getBettingwin()).append("],")
			.append("投注时间[").append(getBettingtime()).append("],")
			.append("投注ip[").append(getBettingip()).append("],")
			.append("投注工具[").append(getBettingtool()).append("],")
			.append("用户id2[").append(getMemberid2()).append("],")
			.append("倍数[").append(getBetmulriple()).append("],")
			.append("状态1:正常 3:删除[").append(getState()).append("],")
			.append("比赛数量[").append(getMatches()).append("],")
			.append("开奖情况0:未开奖  1:已开奖 2:已撤销3:非正常[").append(getSituation()).append("],")
			.append("结算时间[").append(getSettlementtime()).append("],")
			.append("理论奖金[").append(getTheoreticalbonus()).append("],")
			.append("购买类型 0自购 1跟买 2神单[").append(getBuytype()).append("],")
			.append("佣金比例[").append(getBrokerage()).append("],")
			.append("合买号,跟单号[").append(getSchemeid2()).append("],")
			.append("神单截止时间[").append(getEndtime()).append("],")
			.append("佣金[").append(getBrokeragemoney()).append("],")
			.append("起投[").append(getMinbetting()).append("],")
			.append("人气[").append(getBettingnum()).append("],")
			.append("已跟单金额[").append(getBettingalready()).append("],")
			.append("出票状态（0未出票；1出票中；2出票成功；3出票失败）[").append(getIssuestate()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("父级代理id[").append(getAgentparentid()).append("],")
			.append("分级代理id ,隔开[").append(getAgentparentids()).append("],")
			.append("一级代理佣金比例[").append(getBrokerageagent()).append("],")
			.append("大神佣金比例[").append(getBrokeragemember()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BjdcScheme == false) return false;
		if(this == obj) return true;
		BjdcScheme other = (BjdcScheme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
	@WhereSQL(sql="theoreticalbonusmax=:BjdcScheme_theoreticalbonusmax")
	public java.lang.Double getTheoreticalbonusmax() {
		return theoreticalbonusmax;
	}

	public void setTheoreticalbonusmax(java.lang.Double theoreticalbonusmax) {
		this.theoreticalbonusmax = theoreticalbonusmax;
	}

	@WhereSQL(sql="maxmultiple=:BjdcScheme_maxmultiple")
	public java.lang.Double getMaxmultiple() {
		return maxmultiple;
	}

	public void setMaxmultiple(java.lang.Double maxmultiple) {
		this.maxmultiple = maxmultiple;
	}

	public java.lang.Integer getIsinternal() {
		return isinternal;
	}

	public void setIsinternal(java.lang.Integer isinternal) {
		this.isinternal = isinternal;
	}

	/**
	 * 设置一级代理佣金比例
	 * 
	 * @return
	 */
	public java.math.BigDecimal getBrokerageagent() {
		return brokerageagent;
	}

	/**
	 * 获取一级代理佣金比例
	 * 
	 * @param brokerageagent
	 */
	public void setBrokerageagent(java.math.BigDecimal brokerageagent) {
		this.brokerageagent = brokerageagent;
	}

	/**
	 * 设置大神佣金比例
	 * 
	 * @return
	 */
	public java.math.BigDecimal getBrokeragemember() {
		return brokeragemember;
	}

	/**
	 * 获取大神佣金比例
	 * 
	 * @param brokeragemember
	 */
	public void setBrokeragemember(java.math.BigDecimal brokeragemember) {
		this.brokeragemember = brokeragemember;
	}
	
	



	
}

	
