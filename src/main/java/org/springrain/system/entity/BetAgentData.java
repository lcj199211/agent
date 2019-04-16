package org.springrain.system.entity;

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
 * @version  2018-01-26 10:15:07
 * @see org.springrain.lottery.entity.BetAgentData
 */
@Table(name="bet_agent_data")
public class BetAgentData  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "公共字典";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_CODE = "编码";
	public static final String ALIAS_PID = "父ID";
	public static final String ALIAS_SORTNO = "排序";
	public static final String ALIAS_REMARK = "描述";
	public static final String ALIAS_ACTIVE = "是否有效(0否,1是)";
	public static final String ALIAS_TYPEKEY = "类型";
	public static final String ALIAS_VALUE = "参数值";
	public static final String ALIAS_COMPANY = "公司id，一级代理关联";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 编码
	 */
	private java.lang.String code;
	/**
	 * 父ID
	 */
	private java.lang.String pid;
	/**
	 * 排序
	 */
	private java.lang.Integer sortno;
	/**
	 * 描述
	 */
	private java.lang.String remark;
	/**
	 * 是否有效(0否,1是)
	 */
	private java.lang.Integer active;
	/**
	 * 类型
	 */
	private java.lang.String typekey;
	/**
	 * 参数值
	 */
	private java.lang.String value;
	/**
	 * 公司id，一级代理关联
	 */
	private java.lang.String company;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAgentData(){
	}

	public BetAgentData(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentData_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:BetAgentData_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:BetAgentData_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:BetAgentData_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:BetAgentData_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetAgentData_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:BetAgentData_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	public void setTypekey(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.typekey = value;
	}
	
     @WhereSQL(sql="typekey=:BetAgentData_typekey")
	public java.lang.String getTypekey() {
		return this.typekey;
	}
	public void setValue(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.value = value;
	}
	
     @WhereSQL(sql="value=:BetAgentData_value")
	public java.lang.String getValue() {
		return this.value;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:BetAgentData_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("编码[").append(getCode()).append("],")
			.append("父ID[").append(getPid()).append("],")
			.append("排序[").append(getSortno()).append("],")
			.append("描述[").append(getRemark()).append("],")
			.append("是否有效(0否,1是)[").append(getActive()).append("],")
			.append("类型[").append(getTypekey()).append("],")
			.append("参数值[").append(getValue()).append("],")
			.append("公司id，一级代理关联[").append(getCompany()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAgentData == false) return false;
		if(this == obj) return true;
		BetAgentData other = (BetAgentData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
