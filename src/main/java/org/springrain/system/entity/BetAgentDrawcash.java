package org.springrain.system.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

/**
 * 提现方式
 */
@Table(name="bet_agentdrawcash")
public class BetAgentDrawcash extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
	
	private java.lang.Integer id;
	//提现名称
	private java.lang.String name;
	//提现类型
	private java.lang.String type;
	//启用状态
	private java.lang.Integer active;
	//备注
	private java.lang.String remark;
	
	public BetAgentDrawcash(){
	}

	public BetAgentDrawcash(
		java.lang.Integer id
	){
		this.id = id;
	}
	
    @WhereSQL(sql="name=:BetAgentDrawcash_name")
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
    @WhereSQL(sql="type=:BetAgentDrawcash_type")
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
    @WhereSQL(sql="active=:BetAgentDrawcash_active")
	public java.lang.Integer getActive() {
		return active;
	}
	public void setActive(java.lang.Integer active) {
		this.active = active;
	}
	
    @WhereSQL(sql="remark=:BetAgentDrawcash_remark")
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
    @WhereSQL(sql="company=:BetAgentDrawcash_company")
	public java.lang.String getCompany() {
		return company;
	}
	public void setCompany(java.lang.String company) {
		this.company = company;
	}
	//公司
	private java.lang.String company;
	
	@Id
    @WhereSQL(sql="id=:BetAgentDrawcash_id")
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("提现名称[").append(getName()).append("],")
			.append("提现类型[").append(getType()).append("],")
			.append("启用状态0：未启用 1：启用[").append(getActive()).append("],")
			.append("备注[").append(getRemark()).append("],")
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
		if(obj instanceof BetAgentDrawcash == false) return false;
		if(this == obj) return true;
		BetAgentDrawcash other = (BetAgentDrawcash)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

}
