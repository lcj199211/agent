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
 * @version  2017-09-18 09:08:21
 * @see org.springrain.lottery.entity.SoccerScheme
 */
@Table(name="soccer_scheme")
public class SoccerScheme  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerScheme";
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
    */
	//date formats
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	
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
	 * 比赛数量
	 */
	private java.lang.Integer situation;
	
	/**
	 * 购买类型 0自购 1跟买 2神单
	 */
	private java.lang.Integer buytype;
	/**
	 * 佣金比例
	 */
	private java.lang.Integer brokerage;
	/**
	 * 方案号,跟单号
	 */
	private java.lang.String schemeid2;
	
	private java.util.Date endtime;
	
	private java.lang.Double theoreticalbonusmax; 
	
	private java.lang.Double maxmultiple; 
	
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
	 * 保证赔率
	 */
	private java.math.BigDecimal guarantee;
	
	/**
	 * 返给代理佣金
	 */
	private java.math.BigDecimal brokerageagentmoney;
	
	/**
	 * 返给大神佣金
	 */
	private java.math.BigDecimal brokeragemembermoney;
	
	/**
	 * 返佣金代理id
	 */
	private java.lang.String brokerageagentid;
	
	/**
	 * 加奖
	 */
	private java.math.BigDecimal plusawards;
	
	private java.lang.Integer pubstate;
	//columns END 数据库字段结束
	private String statement;
	/**
	 * 玩法
	 */
	private java.lang.String playmethod;
	
	/**
	 * 方案内容
	 */
	private List<SoccerSchemeMatch> schemecontent;
	private java.util.Date settlementtime;
	private java.lang.String membernickname;
	private java.lang.Integer isinternal;
	private java.lang.String theoreticalbonus;
	private java.lang.String agentaccount;
	private java.lang.String agentnickname;
	private java.lang.Double commission;
	private java.lang.Integer issuestate;
	
	private Integer systemissue;
	
	/**
	 * 一级代理佣金比例
	 */
	private java.math.BigDecimal brokerageagent;
	/**
	 * 大神佣金比例
	 */
	private java.math.BigDecimal brokeragemember;
	
	  @WhereSQL(sql="issuestate=:SoccerScheme_issuestate")
	public java.lang.Integer getIssuestate() {
		return issuestate;
	}

	public void setIssuestate(java.lang.Integer issuestate) {
		this.issuestate = issuestate;
	}

	@Transient
	public java.lang.Double getCommission() {
		return commission;
	}

	public void setCommission(java.lang.Double commission) {
		this.commission = commission;
	}

	@Transient
	public java.lang.String getAgentaccount() {
		return agentaccount;
	}

	public void setAgentaccount(java.lang.String agentaccount) {
		this.agentaccount = agentaccount;
	}
	@Transient
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}

	@WhereSQL(sql="theoreticalbonus=:SoccerScheme_theoreticalbonus")
	public java.lang.String getTheoreticalbonus() {
		return theoreticalbonus;
	}

	public void setTheoreticalbonus(java.lang.String theoreticalbonus) {
		this.theoreticalbonus = theoreticalbonus;
	}
	@Transient
	  public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}

	@WhereSQL(sql="settlementtime=:SoccerScheme_settlementtimes")
	public java.util.Date getSettlementtime() {
		return settlementtime;
	}

	public void setSettlementtime(java.util.Date settlementtime) {
		this.settlementtime = settlementtime;
	}

	@Transient
	public List<SoccerSchemeMatch> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<SoccerSchemeMatch> schemecontent) {
		this.schemecontent = schemecontent;
	}

	@Transient
	public java.lang.String getPlaymethod() {
		return playmethod;
	}

	public void setPlaymethod(java.lang.String playmethod) {
		this.playmethod = playmethod;
	}
	
	//concstructor

	public SoccerScheme(){
	}

	public SoccerScheme(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 方案表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 方案表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerScheme_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 方案id
		 */
	public void setSchemeid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schemeid = value;
	}
	
	
	
	/**
	 * 方案id
	 */
     @WhereSQL(sql="schemeid=:SoccerScheme_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
		/**
		 * 投注玩法(逗号隔开)
		 */
	public void setPlaytype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.playtype = value;
	}
	
	
	
	/**
	 * 投注玩法(逗号隔开)
	 */
     @WhereSQL(sql="playtype=:SoccerScheme_playtype")
	public java.lang.String getPlaytype() {
		return this.playtype;
	}
		/**
		 * 玩法id
		 */
	public void setPlaymethodid(java.lang.Integer value) {
		this.playmethodid = value;
	}
	
	
	
	/**
	 * 玩法id
	 */
     @WhereSQL(sql="playmethodid=:SoccerScheme_playmethodid")
	public java.lang.Integer getPlaymethodid() {
		return this.playmethodid;
	}
		/**
		 * 投注金额
		 */
	public void setBettingmoney(java.math.BigDecimal value) {
		this.bettingmoney = value;
	}
	
	
	
	/**
	 * 投注金额
	 */
     @WhereSQL(sql="bettingmoney=:SoccerScheme_bettingmoney")
	public java.math.BigDecimal getBettingmoney() {
		return this.bettingmoney;
	}
		/**
		 * 投注输赢
		 */
	public void setBettingwin(java.math.BigDecimal value) {
		this.bettingwin = value;
	}
	
	
	
	/**
	 * 投注输赢
	 */
     @WhereSQL(sql="bettingwin=:SoccerScheme_bettingwin")
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
	
		/**
		 * 投注时间
		 */
	public void setBettingtime(java.util.Date value) {
		this.bettingtime = value;
	}
	
	
	
	/**
	 * 投注时间
	 */
     @WhereSQL(sql="bettingtime=:SoccerScheme_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}
		/**
		 * 投注ip
		 */
	public void setBettingip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingip = value;
	}
	
	
	
	/**
	 * 投注ip
	 */
     @WhereSQL(sql="bettingip=:SoccerScheme_bettingip")
	public java.lang.String getBettingip() {
		return this.bettingip;
	}
		/**
		 * 投注工具
		 */
	public void setBettingtool(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bettingtool = value;
	}
	
	
	
	/**
	 * 投注工具
	 */
     @WhereSQL(sql="bettingtool=:SoccerScheme_bettingtool")
	public java.lang.String getBettingtool() {
		return this.bettingtool;
	}
		/**
		 * 用户id2
		 */
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	
	
	
	/**
	 * 用户id2
	 */
     @WhereSQL(sql="memberid2=:SoccerScheme_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
	}
		/**
		 * 倍数
		 */
	public void setBetmulriple(java.lang.Integer value) {
		this.betmulriple = value;
	}
	
	
	
	/**
	 * 倍数
	 */
     @WhereSQL(sql="betmulriple=:SoccerScheme_betmulriple")
	public java.lang.Integer getBetmulriple() {
		return this.betmulriple;
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
     @WhereSQL(sql="state=:SoccerScheme_state")
	public java.lang.Integer getState() {
		return this.state;
	}
		/**
		 * 比赛数量
		 */
	public void setMatches(java.lang.Integer value) {
		this.matches = value;
	}
	
	
	
	/**
	 * 比赛数量
	 */
     @WhereSQL(sql="matches=:SoccerScheme_matches")
	public java.lang.Integer getMatches() {
		return this.matches;
	}
     
     
     @WhereSQL(sql="situation=:SoccerScheme_situation")
	public java.lang.Integer getSituation() {
		return situation;
	}

	public void setSituation(java.lang.Integer situation) {
		this.situation = situation;
	}

	 @WhereSQL(sql="buytype=:SoccerScheme_buytype")
		public java.lang.Integer getBuytype() {
			return buytype;
		}

		public void setBuytype(java.lang.Integer buytype) {
			this.buytype = buytype;
		}
		
		@WhereSQL(sql="brokerage=:SoccerScheme_brokerage")
		public java.lang.Integer getBrokerage() {
			return brokerage;
		}

		public void setBrokerage(java.lang.Integer brokerage) {
			this.brokerage = brokerage;
		}
		
		@WhereSQL(sql="schemeid2=:SoccerScheme_schemeid2")
		public java.lang.String getSchemeid2() {
			return schemeid2;
		}

		public void setSchemeid2(java.lang.String schemeid2) {
			this.schemeid2 = schemeid2;
		}

		
		
		@WhereSQL(sql="endtime=:SoccerScheme_endtime")
		public java.util.Date getEndtime() {
			return endtime;
		}

		public void setEndtime(java.util.Date endtime) {
			this.endtime = endtime;
		}

		@WhereSQL(sql="brokeragemoney=:SoccerScheme_brokeragemoney")
		public java.math.BigDecimal getBrokeragemoney() {
			return brokeragemoney;
		}

		public void setBrokeragemoney(java.math.BigDecimal brokeragemoney) {
			this.brokeragemoney = brokeragemoney;
		}

		
		@WhereSQL(sql="minbetting=:SoccerScheme_minbetting")
		public java.math.BigDecimal getMinbetting() {
			return minbetting;
		}

		public void setMinbetting(java.math.BigDecimal minbetting) {
			this.minbetting = minbetting;
		}
		
		@WhereSQL(sql="bettingnum=:SoccerScheme_bettingnum")
		public java.lang.Integer getBettingnum() {
			return bettingnum;
		}

		public void setBettingnum(java.lang.Integer bettingnum) {
			this.bettingnum = bettingnum;
		}
	
		@WhereSQL(sql="bettingalready=:SoccerScheme_bettingalready")
		public java.math.BigDecimal getBettingalready() {
			return bettingalready;
		}

		public void setBettingalready(java.math.BigDecimal bettingalready) {
			this.bettingalready = bettingalready;
		}
		
		
		@WhereSQL(sql="guarantee=:SoccerScheme_guarantee")
		public java.math.BigDecimal getGuarantee() {
			return guarantee;
		}

		public void setGuarantee(java.math.BigDecimal guarantee) {
			this.guarantee = guarantee;
		}

		
		@WhereSQL(sql="brokerageagentmoney=:SoccerScheme_brokerageagentmoney")
		public java.math.BigDecimal getBrokerageagentmoney() {
			return brokerageagentmoney;
		}

		public void setBrokerageagentmoney(java.math.BigDecimal brokerageagentmoney) {
			this.brokerageagentmoney = brokerageagentmoney;
		}

		@WhereSQL(sql="brokeragemembermoney=:SoccerScheme_brokeragemembermoney")
		public java.math.BigDecimal getBrokeragemembermoney() {
			return brokeragemembermoney;
		}

		public void setBrokeragemembermoney(java.math.BigDecimal brokeragemembermoney) {
			this.brokeragemembermoney = brokeragemembermoney;
		}

		@WhereSQL(sql="brokerageagentid=:SoccerScheme_brokerageagentid")
		public java.lang.String getBrokerageagentid() {
			return brokerageagentid;
		}

		public void setBrokerageagentid(java.lang.String brokerageagentid) {
			this.brokerageagentid = brokerageagentid;
		}
		
		
		@WhereSQL(sql="plusawards=:SoccerScheme_plusawards")
		public java.math.BigDecimal getPlusawards() {
			return plusawards;
		}

		public void setPlusawards(java.math.BigDecimal plusawards) {
			this.plusawards = plusawards;
		}
		
		@WhereSQL(sql="pubstate=:SoccerScheme_pubstate")
		public java.lang.Integer getPubstate() {
			return pubstate;
		}

		public void setPubstate(java.lang.Integer pubstate) {
			this.pubstate = pubstate;
		}
		@Transient
		public Integer getSystemissue() {
			return systemissue;
		}

		public void setSystemissue(Integer systemissue) {
			this.systemissue = systemissue;
		}
	@Override
	public String toString() {
		return new StringBuilder()
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
			.append("一级代理佣金比例[").append(getBrokerageagent()).append("],")
			.append("大神佣金比例[").append(getBrokeragemember()).append("],")
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
		if(obj instanceof SoccerScheme == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		SoccerScheme other = (SoccerScheme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@WhereSQL(sql="theoreticalbonusmax=:SoccerScheme_theoreticalbonusmax")
	public java.lang.Double getTheoreticalbonusmax() {
		return theoreticalbonusmax;
	}

	public void setTheoreticalbonusmax(java.lang.Double theoreticalbonusmax) {
		this.theoreticalbonusmax = theoreticalbonusmax;
	}

	@WhereSQL(sql="maxmultiple=:SoccerScheme_maxmultiple")
	public java.lang.Double getMaxmultiple() {
		return maxmultiple;
	}

	public void setMaxmultiple(java.lang.Double maxmultiple) {
		this.maxmultiple = maxmultiple;
	}

	@Transient
	public java.lang.Integer getIsinternal() {
		return isinternal;
	}

	public void setIsinternal(java.lang.Integer isinternal) {
		this.isinternal = isinternal;
	}
	@WhereSQL(sql="statement=:SoccerScheme_statement")
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
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

	
