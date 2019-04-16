package org.springrain.lottery.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * TODO 在此加入类描述
 * @copyright
 * @author springrain<Auto generate>
 * @version  2019-04-01 09:41:49
 * @see BetAgentgoldDaylyRank
 */
@Table(name="bet_agentgold_daylyrank")
public class BetAgentgoldDaylyRank extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 代理充值订单编号
	 */
	private String id;
	/**
	 * 代理商id
	 */
	private String agentid;
	/**
	 * 代理账号
	 */
	private String account;
	/**
	 * 代理昵称
	 */
	private String nickname;

	/**
	 * 代理商父级id
	 */
	private String parentid;
	/**
	 * 代理商id 从根节点开始
	 */
	private String parentids;

	/**
	 * 下属
	 */
	private int subordinate;
	/**
	 * 所属会员
	 */
	private int membernum;
	/**
	 * 是否有效(0否,1是)
	 */
	private int active;
	/**
	 * 充值总额
	 */
	private Double totalmoney;

	/**
	 * 创建时间
	 */
	private java.util.Date creatTime;

	//columns END 数据库字段结束



	private Double mon;//临时字段  总金额

	@Transient
	public Double getMon() {
		return mon;
	}

	public void setMon(Double mon) {
		this.mon = mon;
	}


	public BetAgentgoldDaylyRank(){

	}

	public BetAgentgoldDaylyRank(
		String id
	){
		this.id = id;
	}

	//get and set
	public void setId(String value) {this.id = value;}
	@Id
     @WhereSQL(sql="id=:id")
	public String getId() {
		return this.id;
	}


	public void setAgentid(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.agentid = value;
	}
	@WhereSQL(sql="agentid=:agentid")
	public String getAgentid() {
		return agentid;
	}

	@WhereSQL(sql="account=:account")
	public String getAccount() {
		return account;
	}
	public void setAccount(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.account = value;
	}

	@WhereSQL(sql="nickname=:nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.nickname = value;
	}

	@WhereSQL(sql="parentid=:parentid")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.parentid = value;
	}

	@WhereSQL(sql="parentids=:parentids")
	public String getParentids() {
		return parentids;
	}

	public void setParentids(String value) {
		if(StringUtils.isNotBlank(value)){
			value=value.trim();
		}
		this.parentids = value;
	}

	@WhereSQL(sql="subordinate=:subordinate")
	public int getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(int subordinate) {

		this.subordinate = subordinate;
	}

	@WhereSQL(sql="membernum=:membernum")
	public int getMembernum() {
		return membernum;
	}

	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}

	@WhereSQL(sql="active=:active")
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@WhereSQL(sql="totalmoney=:totalmoney")
	public Double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}

	@WhereSQL(sql="creatTime=:creatTime")
	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentgoldDaylyRank == false) return false;
		if(this == obj) return true;
		BetAgentgoldDaylyRank other = (BetAgentgoldDaylyRank)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
