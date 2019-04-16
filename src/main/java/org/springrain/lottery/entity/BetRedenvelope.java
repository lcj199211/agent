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
 * @version  2017-05-02 00:22:46
 * @see org.springrain.lottery.entity.BetRedenvelope
 */
@Table(name="bet_redenvelope")
public class BetRedenvelope  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetRedenvelope";
	public static final String ALIAS_ID = "红包id";
	public static final String ALIAS_TOTALSCORE = "总金额";
	public static final String ALIAS_REDENVELOPECODE = "红包码";
	public static final String ALIAS_SENDNUM = "发包数";
	public static final String ALIAS_RECEIVENUM = "领取数";
	public static final String ALIAS_MINSCORE = "最少分";
	public static final String ALIAS_MAXSCORE = "最大分";
	public static final String ALIAS_CAPTION = "说明";
	public static final String ALIAS_CREATETIME = "生成时间";
	public static final String ALIAS_STATE = "状态0:作废 1:禁用 2:生效";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_MEMBERID = "会员id,该项为空时指定红包派遣给用户,用户id用逗号隔开";
	public static final String ALIAS_COUNTPAYSCORE = "领取分,该红包被领取的总分";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 红包id
	 */
	private java.lang.Integer id;
	/**
	 * 总金额
	 */
	private java.lang.Double totalscore;
	/**
	 * 红包码
	 */
	private java.lang.String redenvelopecode;
	/**
	 * 发包数
	 */
	private java.lang.Integer sendnum;
	/**
	 * 领取数
	 */
	private java.lang.Integer receivenum;
	/**
	 * 最少分
	 */
	private java.lang.Double minscore;
	/**
	 * 最大分
	 */
	private java.lang.Double maxscore;
	/**
	 * 说明
	 */
	private java.lang.String caption;
	/**
	 * 生成时间
	 */
	private java.util.Date createtime;
	/**
	 * 状态0:作废 1:禁用 2:生效
	 */
	private java.lang.Integer state;
	/**
	 * 类型
	 */
	private java.lang.Integer type;
	/**
	 * 会员id,该项为空时指定红包派遣给用户,用户id用逗号隔开
	 */
	private java.lang.String memberid2;
	/**
	 * 领取分,该红包被领取的总分
	 */
	private java.lang.Double countpayscore;
	private java.lang.String agentid;
	private java.lang.String agentparentid;
	private java.lang.String agentparentids;
	/**
	 * 生命周期
	 */
	private Integer lifetime;
	/**
	 * 失效备注
	 */
	private String remark;
	
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="agentid=:BetRedenvelope_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
	@WhereSQL(sql="agentparentid=:BetRedenvelope_agentparentid")
	public java.lang.String getAgentparentid() {
		return agentparentid;
	}

	public void setAgentparentid(java.lang.String agentparentid) {
		this.agentparentid = agentparentid;
	}
	@WhereSQL(sql="agentparentids=:BetRedenvelope_agentparentids")
	public java.lang.String getAgentparentids() {
		return agentparentids;
	}

	public void setAgentparentids(java.lang.String agentparentids) {
		this.agentparentids = agentparentids;
	}

	public BetRedenvelope(){
	}

	public BetRedenvelope(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetRedenvelope_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setTotalscore(java.lang.Double value) {
		this.totalscore = value;
	}
	
     @WhereSQL(sql="totalscore=:BetRedenvelope_totalscore")
	public java.lang.Double getTotalscore() {
		return this.totalscore;
	}
	public void setRedenvelopecode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.redenvelopecode = value;
	}
	
     @WhereSQL(sql="redenvelopecode=:BetRedenvelope_redenvelopecode")
	public java.lang.String getRedenvelopecode() {
		return this.redenvelopecode;
	}
	public void setSendnum(java.lang.Integer value) {
		this.sendnum = value;
	}
	
     @WhereSQL(sql="sendnum=:BetRedenvelope_sendnum")
	public java.lang.Integer getSendnum() {
		return this.sendnum;
	}
	public void setReceivenum(java.lang.Integer value) {
		this.receivenum = value;
	}
	
     @WhereSQL(sql="receivenum=:BetRedenvelope_receivenum")
	public java.lang.Integer getReceivenum() {
		return this.receivenum;
	}
	public void setMinscore(java.lang.Double value) {
		this.minscore = value;
	}
	
     @WhereSQL(sql="minscore=:BetRedenvelope_minscore")
	public java.lang.Double getMinscore() {
		return this.minscore;
	}
	public void setMaxscore(java.lang.Double value) {
		this.maxscore = value;
	}
	
     @WhereSQL(sql="maxscore=:BetRedenvelope_maxscore")
	public java.lang.Double getMaxscore() {
		return this.maxscore;
	}
	public void setCaption(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.caption = value;
	}
	
     @WhereSQL(sql="caption=:BetRedenvelope_caption")
	public java.lang.String getCaption() {
		return this.caption;
	}
		/*
	public String getcreatetimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreatetime());
	}
	public void setcreatetimeString(String value) throws ParseException{
		setcreatetime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
     @WhereSQL(sql="createtime=:BetRedenvelope_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetRedenvelope_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetRedenvelope_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setMemberid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberid2 = value;
	}
	
     @WhereSQL(sql="memberid2=:BetRedenvelope_memberid2")
	public java.lang.String getMemberid2() {
		return this.memberid2;
	}
	public void setCountpayscore(java.lang.Double value) {
		this.countpayscore = value;
	}
	
     @WhereSQL(sql="countpayscore=:BetRedenvelope_countpayscore")
	public java.lang.Double getCountpayscore() {
		return this.countpayscore;
	}
     
     @WhereSQL(sql="lifetime=:BetRedenvelope_lifetime")
 	public Integer getLifetime() {
 		return lifetime;
 	}

 	public void setLifetime(Integer lifetime) {
 		this.lifetime = lifetime;
 	}
 	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return new StringBuffer()
			.append("红包id[").append(getId()).append("],")
			.append("总金额[").append(getTotalscore()).append("],")
			.append("红包码[").append(getRedenvelopecode()).append("],")
			.append("发包数[").append(getSendnum()).append("],")
			.append("领取数[").append(getReceivenum()).append("],")
			.append("最少分[").append(getMinscore()).append("],")
			.append("最大分[").append(getMaxscore()).append("],")
			.append("说明[").append(getCaption()).append("],")
			.append("生成时间[").append(getCreatetime()).append("],")
			.append("状态0:作废 1:禁用 2:生效[").append(getState()).append("],")
			.append("类型[").append(getType()).append("],")
			.append("会员id,该项为空时指定红包派遣给用户,用户id用逗号隔开[").append(getMemberid2()).append("],")
			.append("领取分,该红包被领取的总分[").append(getCountpayscore()).append("],")
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
		if(obj instanceof BetRedenvelope == false) return false;
		if(this == obj) return true;
		BetRedenvelope other = (BetRedenvelope)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
