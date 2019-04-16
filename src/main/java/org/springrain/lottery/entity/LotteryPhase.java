package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 彩票机上的新期和往期开奖结果
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-12-06 09:34:26
 * @see org.springrain.lottery.entity.LotteryPhase
 */
@Table(name="lottery_phase")
public class LotteryPhase  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "大乐透期次结果";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_LOTTERYTYPE = "彩票类型";
	public static final String ALIAS_PHASENO = "期号";
	public static final String ALIAS_WINCODE = "开奖号码";
	public static final String ALIAS_WINDETAIL = "中奖明细";
	public static final String ALIAS_POOLAMOUNT = "奖池";
	public static final String ALIAS_ADDPOOLAMOUNT = "加奖奖池";
	public static final String ALIAS_SALEAMOUNT = "销售额";
	public static final String ALIAS_ENDTIME = "官方开售时间";
	public static final String ALIAS_STARTTIME = "官方截止时间";
	public static final String ALIAS_LOCALENDTIME = "平台截止时间";
	public static final String ALIAS_ISTRUE = "是否修正（0否1是）";
    */
	//date formats
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_LOCALENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 彩票类型
	 */
	private java.lang.String lotterytype;
	/**
	 * 期号
	 */
	private java.lang.String phaseno;
	/**
	 * 开奖号码
	 */
	private java.lang.String wincode;
	/**
	 * 中奖明细
	 */
	private java.lang.String windetail;
	/**
	 * 奖池
	 */
	private java.math.BigDecimal poolamount;
	/**
	 * 加奖奖池
	 */
	private java.math.BigDecimal addpoolamount;
	/**
	 * 销售额
	 */
	private java.math.BigDecimal saleamount;
	/**
	 * 官方开售时间
	 */
	private java.util.Date endtime;
	/**
	 * 官方截止时间
	 */
	private java.util.Date starttime;
	/**
	 * 平台截止时间
	 */
	private java.util.Date localendtime;
	/**
	 * 是否修正（0否1是）
	 */
	private java.lang.Integer istrue;
	//columns END 数据库字段结束
	
	//concstructor

	public LotteryPhase(){
	}

	public LotteryPhase(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:LotteryPhase_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setLotterytype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lotterytype = value;
	}
	
     @WhereSQL(sql="lotterytype=:LotteryPhase_lotterytype")
	public java.lang.String getLotterytype() {
		return this.lotterytype;
	}
	public void setPhaseno(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phaseno = value;
	}
	
     @WhereSQL(sql="phaseno=:LotteryPhase_phaseno")
	public java.lang.String getPhaseno() {
		return this.phaseno;
	}
	public void setWincode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wincode = value;
	}
	
     @WhereSQL(sql="wincode=:LotteryPhase_wincode")
	public java.lang.String getWincode() {
		return this.wincode;
	}
	public void setWindetail(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.windetail = value;
	}
	
     @WhereSQL(sql="windetail=:LotteryPhase_windetail")
	public java.lang.String getWindetail() {
		return this.windetail;
	}
	public void setPoolamount(java.math.BigDecimal value) {
		this.poolamount = value;
	}
	
     @WhereSQL(sql="poolamount=:LotteryPhase_poolamount")
	public java.math.BigDecimal getPoolamount() {
		return this.poolamount;
	}
	public void setAddpoolamount(java.math.BigDecimal value) {
		this.addpoolamount = value;
	}
	
     @WhereSQL(sql="addpoolamount=:LotteryPhase_addpoolamount")
	public java.math.BigDecimal getAddpoolamount() {
		return this.addpoolamount;
	}
	public void setSaleamount(java.math.BigDecimal value) {
		this.saleamount = value;
	}
	
     @WhereSQL(sql="saleamount=:LotteryPhase_saleamount")
	public java.math.BigDecimal getSaleamount() {
		return this.saleamount;
	}
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
     @WhereSQL(sql="endtime=:LotteryPhase_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
     @WhereSQL(sql="starttime=:LotteryPhase_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
	public void setLocalendtime(java.util.Date value) {
		this.localendtime = value;
	}
	
     @WhereSQL(sql="localendtime=:LotteryPhase_localendtime")
	public java.util.Date getLocalendtime() {
		return this.localendtime;
	}
	public void setIstrue(java.lang.Integer value) {
		this.istrue = value;
	}
	
     @WhereSQL(sql="istrue=:LotteryPhase_istrue")
	public java.lang.Integer getIstrue() {
		return this.istrue;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("彩票类型[").append(getLotterytype()).append("],")
			.append("期号[").append(getPhaseno()).append("],")
			.append("开奖号码[").append(getWincode()).append("],")
			.append("中奖明细[").append(getWindetail()).append("],")
			.append("奖池[").append(getPoolamount()).append("],")
			.append("加奖奖池[").append(getAddpoolamount()).append("],")
			.append("销售额[").append(getSaleamount()).append("],")
			.append("官方开售时间[").append(getEndtime()).append("],")
			.append("官方截止时间[").append(getStarttime()).append("],")
			.append("平台截止时间[").append(getLocalendtime()).append("],")
			.append("是否修正（0否1是）[").append(getIstrue()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LotteryPhase == false) return false;
		if(this == obj) return true;
		LotteryPhase other = (LotteryPhase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
