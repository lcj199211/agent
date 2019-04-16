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
 * @version  2017-12-06 09:51:48
 * @see org.springrain.lottery.entity.LotteryScheme
 */
@Table(name="lottery_scheme")
public class LotteryScheme  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "投注";
	public static final String ALIAS_ID = "投注ID";
	public static final String ALIAS_MEMBERID2 = "用户ID";
	public static final String ALIAS_SCHEMEID = "投注ID";
	public static final String ALIAS_LOTTERYTYPE = "彩种类型";
	public static final String ALIAS_PHASE = "期号";
	public static final String ALIAS_AMOUNT = "金额";
	public static final String ALIAS_AWARD = "奖金";
	public static final String ALIAS_BETTINGTIME = "投注时间";
	public static final String ALIAS_BETTINGIP = "投注IP";
	public static final String ALIAS_BETTINGTOOL = "投注工具";
	public static final String ALIAS_PRETAXAMOUNT = "税前金额";
	public static final String ALIAS_MULTIPLE = "倍数";
	public static final String ALIAS_ADDS = "追加0否1是";
	public static final String ALIAS_MSG = "描述";
	public static final String ALIAS_TERMINAL = "出票终端";
	public static final String ALIAS_HASDETAIL = "是否有详细";
	public static final String ALIAS_SITUATION = "开奖情况0:未开奖  1:已结算 2:已撤销3:非正常";
	public static final String ALIAS_SETTLEMENTTIME = "结算时间";
	public static final String ALIAS_PERIODS = "期数";
	public static final String ALIAS_PERIODSTIMES = "还需自动投注的次数";
	public static final String ALIAS_BETTINGSTATE = "多期方案下一期投注状态 (0已投注1未投注)";
	public static final String ALIAS_SCHEMEID2 = "首次购买该方案的id";
	public static final String ALIAS_BUYTYPE = "购买类型（0：自购；3：追号）";
	public static final String ALIAS_ISSUESTATE = 出票状态（0未出票；1出票中；2出票成功；3出票失败）;
	public static final String ALIAS_PLUSAWARDS = "加奖";
	public static final String ALIAS_AGENTID = "代理商ID";
	public static final String ALIAS_AGENTPARENTID = "父级代理商ID";
	public static final String ALIAS_AGENTPARENTIDS = "分级代理商ID";
    */
	//date formats
	//public static final String FORMAT_BETTINGTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 投注ID
	 */
	private java.lang.Integer id;
	/**
	 * 方案id
	 */
	private java.lang.String schemeid;
	/**
	 * 用户ID
	 */
	private java.lang.Integer memberid2;
	/**
	 * 彩种类型
	 */
	private java.lang.String lotterytype;
	/**
	 * 期号
	 */
	private java.lang.String phase;
	/**
	 * 金额
	 */
	private java.math.BigDecimal amount;
	/**
	 * 奖金
	 */
	private java.math.BigDecimal award;
	/**
	 * 投注时间
	 */
	private java.util.Date bettingtime;
	/**
	 * 倍数
	 */
	private java.lang.Integer multiple;
	/**
	 * 投注ip
	 */
	private java.lang.String bettingip;
	/**
	 * 投注工具
	 */
	private java.lang.String bettingtool;
	/**
	 * 税前金额
	 */
	private java.math.BigDecimal pretaxamount;
	/**
	 * 追加0否1是
	 */
	private java.lang.Integer adds;
	/**
	 * 描述
	 */
	private java.lang.String msg;
	/**
	 * 出票终端
	 */
	private java.lang.String terminal;
	/**
	 * 是否有详细
	 */
	private java.lang.Integer hasdetail;
	/**
	 * 开奖情况0:未开奖  1:已结算 2:已撤销3:非正常
	 */
	private java.lang.Integer situation;
	/**
	 * 结算时间
	 */
	private java.util.Date settlementtime;
	/**
	 * 期数
	 */
	private java.lang.Integer periods;
	/**
	 * 还需自动投注的次数
	 */
	private java.lang.Integer periodstimes;
	/**
	 * 多期方案下一期投注状态 (0已投注1未投注)
	 */
	private java.lang.Integer bettingstate;
	/**
	 * 首次购买该方案的id
	 */
	private java.lang.String schemeid2;
	/**
	 * 购买类型（0：自购；3：追号）
	 */
	private java.lang.Integer buytype;
	/**
	 * 出票状态（0未出票；1出票中；2出票成功；3出票失败）
	 */
	private java.lang.Integer issuestate;
	/**
	 * 加奖
	 */
	private java.math.BigDecimal plusawards;
	/**
	 * 代理商ID
	 */
	private java.lang.String agentid;
	/**
	 * 父级代理商ID
	 */
	private java.lang.String agentparentid;
	/**
	 * 分级代理商ID
	 */
	private java.lang.String agentparentids;
	//columns END 数据库字段结束
	/**
	 * 用户表的用户昵称
	 */
	private java.lang.String membernickname;
	private java.lang.Integer isinternal;//是否内部员工（0否1是）
	private List<LotteryOrder> schemecontent;//方案内容
	private java.lang.String agentnickname;//代理昵称

	//concstructor
	public LotteryScheme(){
	}

	public LotteryScheme(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:LotteryScheme_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setMemberid2(java.lang.Integer value) {
		this.memberid2 = value;
	}
	 @WhereSQL(sql="memberid2=:LotteryScheme_memberid2")
	public java.lang.Integer getMemberid2() {
		return this.memberid2;
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
	@WhereSQL(sql="schemeid=:LotteryScheme_schemeid")
	public java.lang.String getSchemeid() {
		return this.schemeid;
	}
	
	/**
	 * 开奖情况0:未开奖  1:已结算 2:已撤销3:非正常
	 */
    @WhereSQL(sql="situation=:LotteryScheme_situation")
	public java.lang.Integer getSituation() {
		return situation;
	}

    /**
	 * 开奖情况0:未开奖  1:已结算 2:已撤销3:非正常
	 */
	public void setSituation(java.lang.Integer situation) {
		this.situation = situation;
	}
	
	public void setLotterytype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lotterytype = value;
	}
	
     @WhereSQL(sql="lotterytype=:LotteryScheme_lotterytype")
	public java.lang.String getLotterytype() {
		return this.lotterytype;
	}
	public void setPhase(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phase = value;
	}
	
     @WhereSQL(sql="phase=:LotteryScheme_phase")
	public java.lang.String getPhase() {
		return this.phase;
	}
	public void setAmount(java.math.BigDecimal value) {
		this.amount = value;
	}
	
     @WhereSQL(sql="amount=:LotteryScheme_amount")
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	public void setAward(java.math.BigDecimal value) {
		this.award = value;
	}
	
     @WhereSQL(sql="award=:LotteryScheme_award")
	public java.math.BigDecimal getAward() {
		return this.award;
	}
	
	public void setBettingtime(java.util.Date value) {
		this.bettingtime = value;
	}
	
     @WhereSQL(sql="bettingtime=:LotteryScheme_bettingtime")
	public java.util.Date getBettingtime() {
		return this.bettingtime;
	}

	public void setMultiple(java.lang.Integer value) {
		this.multiple = value;
	}
	
	@WhereSQL(sql="multiple=:LotteryScheme_multiple")
	public java.lang.Integer getMultiple() {
		return this.multiple;
	}
	
	public void setBettingip(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.bettingip = value;
	}
	
	@WhereSQL(sql="bettingip=:LotteryScheme_bettingip")
	public java.lang.String getBettingip() {
		return this.bettingip;
	}

	public void setBettingtool(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.bettingtool = value;
	}

	@WhereSQL(sql="bettingtool=:LotteryScheme_bettingtool")
	public java.lang.String getBettingtool() {
		return this.bettingtool;
	}
     
    public void setPretaxamount(java.math.BigDecimal value) {
 		this.pretaxamount = value;
 	}
 	
    @WhereSQL(sql="pretaxamount=:LotteryScheme_pretaxamount")
 	public java.math.BigDecimal getPretaxamount() {
 		return this.pretaxamount;
 	}
	
	public void setAdds(java.lang.Integer value) {
		this.adds = value;
	}
	
     @WhereSQL(sql="adds=:LotteryScheme_adds")
	public java.lang.Integer getAdds() {
		return this.adds;
	}

	public void setMsg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.msg = value;
	}
	
     @WhereSQL(sql="msg=:LotteryScheme_msg")
	public java.lang.String getMsg() {
		return this.msg;
	}
	public void setTerminal(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.terminal = value;
	}
	
     @WhereSQL(sql="terminal=:LotteryScheme_terminal")
	public java.lang.String getTerminal() {
		return this.terminal;
	}
	public void setHasdetail(java.lang.Integer value) {
		this.hasdetail = value;
	}
	
     @WhereSQL(sql="hasdetail=:LotteryScheme_hasdetail")
	public java.lang.Integer getHasdetail() {
		return this.hasdetail;
	}
     
     public void setSettlementtime(java.util.Date value) {
 		this.settlementtime = value;
 	}
 	
      @WhereSQL(sql="Settlementtime=:LotteryScheme_settlementtime")
 	public java.util.Date getSettlementtime() {
 		return this.settlementtime;
 	}
      
      public void setPeriods(java.lang.Integer value) {
  		this.periods = value;
  	}
       @WhereSQL(sql="periods=:LotteryScheme_periods")
  	public java.lang.Integer getPeriods() {
  		return this.periods;
  	}
 
       public void setPeriodstimes(java.lang.Integer value) {
   		this.periodstimes = value;
   	}
   	
      		@WhereSQL(sql="periodstimes=:LotteryScheme_periodstimes")
   	public java.lang.Integer getPeriodstimes() {
   		return this.periodstimes;
   	}
   	
   	public void setBettingstate(java.lang.Integer value) {
   		this.bettingstate = value;
   	}
   		
   		@WhereSQL(sql="bettingstate=:LotteryScheme_bettingstate")
   	public java.lang.Integer getBettingstate() {
   		return this.bettingstate;
   	}
 
   	public void setSchemeid2(java.lang.String value) {
   	    if(StringUtils.isNotBlank(value)){
   	  	    value=value.trim();
   	  	}
   	  	this.schemeid2 = value;
    }
   	@WhereSQL(sql="schemeid2=:LotteryScheme_schemeid2")
   	public java.lang.String getSchemeid2() {
   	    return this.schemeid2;
   	}
   	   		
    public void setBuytype(java.lang.Integer value) {
   	   	this.buytype = value;
   	}
    
   	@WhereSQL(sql="buytype=:LotteryScheme_buytype")
   	public java.lang.Integer getBuytype() {
   	    return this.buytype;
   	}  	
 
    public void setIssuestate(java.lang.Integer value) {
   		this.issuestate = value;
   	}
   	
        @WhereSQL(sql="issuestate=:LotteryScheme_issuestate")
   	public java.lang.Integer getIssuestate() {
   		return this.issuestate;
   	}
        
    public void setPlusawards(java.math.BigDecimal value) {
     	this.plusawards = value;
    }
     	
      @WhereSQL(sql="plusawards=:LotteryScheme_plusawards")
    public java.math.BigDecimal getPlusawards() {
     	return this.plusawards;
     }
 
  	public void setAgentid(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.agentid = value;
	}
  		@WhereSQL(sql="agentid=:LotteryScheme_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}	

	public void setAgentparentid(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.agentparentid = value;
	}
		@WhereSQL(sql="agentparentid=:LotteryScheme_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	
	public void setAgentparentids(java.lang.String value) {
	    if(StringUtils.isNotBlank(value)){
	    	value=value.trim();
		}
	    this.agentparentids = value;
	}
		@WhereSQL(sql="agentparentids=:LotteryScheme_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
     /**
 	 * 用户表的用户昵称
 	 */
     @Transient
	  public java.lang.String getMembernickname() {
		return membernickname;
	}

	public void setMembernickname(java.lang.String membernickname) {
		this.membernickname = membernickname;
	}
	public java.lang.Integer getIsinternal() {
		return isinternal;
	}

	public void setIsinternal(java.lang.Integer isinternal) {
		this.isinternal = isinternal;
	}
	/**
	 * 代理昵称
	 */
	public java.lang.String getAgentnickname() {
		return agentnickname;
	}

	public void setAgentnickname(java.lang.String agentnickname) {
		this.agentnickname = agentnickname;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("投注ID[").append(getId()).append("],")
			.append("用户ID[").append(getMemberid2()).append("],")
			.append("彩种类型[").append(getLotterytype()).append("],")
			.append("期号[").append(getPhase()).append("],")
			.append("金额[").append(getAmount()).append("],")
			.append("奖金[").append(getAward()).append("],")
			.append("投注时间[").append(getBettingtime()).append("],")
			.append("倍数[").append(getMultiple()).append("],")
			.append("税前金额[").append(getPretaxamount()).append("],")
			.append("投注ip[").append(getBettingip()).append("],")
			.append("投注工具[").append(getBettingtool()).append("],")
			.append("追加0否1是[").append(getAdds()).append("],")
			.append("描述[").append(getMsg()).append("],")
			.append("出票终端[").append(getTerminal()).append("],")
			.append("是否有详细[").append(getHasdetail()).append("],")
			.append("开奖情况0:未开奖  1:已结算 2:已撤销3:非正常[").append(getSituation()).append("],")
			.append("结算时间[").append(getSettlementtime()).append("],")
			.append("期数[").append(getPeriods()).append("],")
			.append("还需自动投注的次数[").append(getPeriodstimes()).append("],")
			.append("多期方案下一期投注状态 (0已投注1未投注)[").append(getBettingstate()).append("],")
			.append("购买类型（0：自购；3：追号）[").append(getBuytype()).append("],")
			.append("出票状态（0未出票；1出票中；2出票成功；3出票失败）[").append(getIssuestate()).append("],")
			.append("加奖[").append(getPlusawards()).append("],")
			.append("代理商ID[").append(getAgentid()).append("],")
			.append("父级代理商ID[").append(getAgentparentid()).append("],")
			.append("分级代理商ID[").append(getAgentparentids()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LotteryScheme == false) return false;
		if(this == obj) return true;
		LotteryScheme other = (LotteryScheme)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@Transient
	public List<LotteryOrder> getSchemecontent() {
		return schemecontent;
	}

	public void setSchemecontent(List<LotteryOrder> schemecontent) {
		this.schemecontent = schemecontent;
	}
}

	
