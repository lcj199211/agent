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
 * @author springrain<Auto generate>
 * @version  2017-05-08 09:58:06
 * @see org.springrain.lottery.entity.BetSubordinaterebateDetail
 */
@Table(name="bet_subordinaterebate_detail")
public class BetSubordinaterebateDetail  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetSubordinaterebateDetail";
	public static final String ALIAS_ID = "下线返利表id";
	public static final String ALIAS_MEMBERID = "会员id";
	public static final String ALIAS_SUBORDINATEID = "会员下线id";
	public static final String ALIAS_NICKNAME = "下线昵称";
	public static final String ALIAS_SUBORDINATEBET = "下线投注流水佣金";
	public static final String ALIAS_RECOMMENDNUM = "下线推荐人数";
	public static final String ALIAS_SUBORDINATERECHARGE = "下线充值佣金";
	public static final String ALIAS_SUBORDINATELOSE = "下线输佣金";
	public static final String ALIAS_INCOME = "结算";
	public static final String ALIAS_SUBTIME = "时间";
	public static final String ALIAS_STATE = "状态0:已领取 1:未领取";
    */
	//date formats
	//public static final String FORMAT_SUBTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 下线返利表id
	 */
	private java.lang.Integer id;
	private java.lang.Integer memberid2;
	/**
	 * 下线昵称
	 */
	private java.lang.String nickname;
	/**
	 * 下线投注流水佣金
	 */
	private java.lang.Double subordinatebet;
	/**
	 * 下线推荐人数
	 */
	private java.lang.Integer recommendnum;
	/**
	 * 下线充值佣金
	 */
	private java.lang.Double subordinaterecharge;
	/**
	 * 下线输佣金
	 */
	private java.lang.Double subordinatelose;
	/**
	 * 结算
	 */
	private java.lang.Double income;
	/**
	 * 时间
	 */
	private java.util.Date subtime;
	/**
	 * 状态0:已领取 1:未领取
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	private java.lang.Double sb;
	private java.lang.Double sc;
	private java.lang.Double sl;
	private java.util.Date receivetime;
	private java.lang.String receiveip;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	private java.lang.Integer parentmemberid2;
	private java.lang.String parentnickname;
	@WhereSQL(sql="parentnickname=:BetSubordinaterebateDetail_parentnickname")
	public java.lang.String getParentnickname() {
		return parentnickname;
	}

	public void setParentnickname(java.lang.String parentnickname) {
		this.parentnickname = parentnickname;
	}

	@WhereSQL(sql="parentmemberid2=:BetSubordinaterebateDetail_parentmemberid2")
	public java.lang.Integer getParentmemberid2() {
		return parentmemberid2;
	}

	public void setParentmemberid2(java.lang.Integer parentmemberid2) {
		this.parentmemberid2 = parentmemberid2;
	}

	@WhereSQL(sql="agentid=:BetSubordinaterebateDetail_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetSubordinaterebateDetail_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetSubordinaterebateDetail_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	@WhereSQL(sql="receivetime=:BetSubordinaterebateDetail_receivetime")
	public java.util.Date getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(java.util.Date receivetime) {
		this.receivetime = receivetime;
	}
	@WhereSQL(sql="receiveip=:BetSubordinaterebateDetail_receiveip")
	public java.lang.String getReceiveip() {
		return receiveip;
	}

	public void setReceiveip(java.lang.String receiveip) {
		this.receiveip = receiveip;
	}

	//concstructor
	@WhereSQL(sql="sb=:BetSubordinaterebateDetail_sb")
	public java.lang.Double getSb() {
		return sb;
	}

	public void setSb(java.lang.Double sb) {
		this.sb = sb;
	}
	@WhereSQL(sql="sc=:BetSubordinaterebateDetail_sc")
	public java.lang.Double getSc() {
		return sc;
	}

	public void setSc(java.lang.Double sc) {
		this.sc = sc;
	}
	@WhereSQL(sql="sl=:BetSubordinaterebateDetail_sl")
	public java.lang.Double getSl() {
		return sl;
	}

	public void setSl(java.lang.Double sl) {
		this.sl = sl;
	}

	public BetSubordinaterebateDetail(){
	}

	public BetSubordinaterebateDetail(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetSubordinaterebateDetail_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetSubordinaterebateDetail_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
     
     @WhereSQL(sql="memberid2=:BetSubordinaterebateDetail_memberid2")
	public java.lang.Integer getMemberid2() {
		return memberid2;
	}

	public void setMemberid2(java.lang.Integer memberid2) {
		this.memberid2 = memberid2;
	}

	public void setSubordinatebet(java.lang.Double value) {
		this.subordinatebet = value;
	}
	
     @WhereSQL(sql="subordinatebet=:BetSubordinaterebateDetail_subordinatebet")
	public java.lang.Double getSubordinatebet() {
		return this.subordinatebet;
	}
	public void setRecommendnum(java.lang.Integer value) {
		this.recommendnum = value;
	}
	
     @WhereSQL(sql="recommendnum=:BetSubordinaterebateDetail_recommendnum")
	public java.lang.Integer getRecommendnum() {
		return this.recommendnum;
	}
	public void setSubordinaterecharge(java.lang.Double value) {
		this.subordinaterecharge = value;
	}
	
     @WhereSQL(sql="subordinaterecharge=:BetSubordinaterebateDetail_subordinaterecharge")
	public java.lang.Double getSubordinaterecharge() {
		return this.subordinaterecharge;
	}
	public void setSubordinatelose(java.lang.Double value) {
		this.subordinatelose = value;
	}
	
     @WhereSQL(sql="subordinatelose=:BetSubordinaterebateDetail_subordinatelose")
	public java.lang.Double getSubordinatelose() {
		return this.subordinatelose;
	}
	public void setIncome(java.lang.Double value) {
		this.income = value;
	}
	
     @WhereSQL(sql="income=:BetSubordinaterebateDetail_income")
	public java.lang.Double getIncome() {
		return this.income;
	}
		/*
	public String getsubtimeString() {
		return DateUtils.convertDate2String(FORMAT_SUBTIME, getsubtime());
	}
	public void setsubtimeString(String value) throws ParseException{
		setsubtime(DateUtils.convertString2Date(FORMAT_SUBTIME,value));
	}*/
	
	public void setSubtime(java.util.Date value) {
		this.subtime = value;
	}
	
     @WhereSQL(sql="subtime=:BetSubordinaterebateDetail_subtime")
	public java.util.Date getSubtime() {
		return this.subtime;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetSubordinaterebateDetail_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("下线返利表id[").append(getId()).append("],")
			.append("下线昵称[").append(getNickname()).append("],")
			.append("下线投注流水佣金[").append(getSubordinatebet()).append("],")
			.append("下线推荐人数[").append(getRecommendnum()).append("],")
			.append("下线充值佣金[").append(getSubordinaterecharge()).append("],")
			.append("下线输佣金[").append(getSubordinatelose()).append("],")
			.append("结算[").append(getIncome()).append("],")
			.append("时间[").append(getSubtime()).append("],")
			.append("状态0:已领取 1:未领取[").append(getState()).append("],")
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
		if(obj instanceof BetSubordinaterebateDetail == false) return false;
		if(this == obj) return true;
		BetSubordinaterebateDetail other = (BetSubordinaterebateDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
