package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * 版本控制栏目
 * @copyright {@link weicms.net}
 * @author qiang
 * @version  2019-03-13 18:25:56
 * @see org.springrain.news.entity.BetChannelVersionControl
 */
@Table(name="bet_channel_version_control")
public class BetChannelVersionControl  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "版本控制栏目表";
	public static final String ALIAS_ID = "主键ID";
	public static final String ALIAS_VERSION_STATUS = "版本状态：1、A版（资讯版）；2、B版（彩票版）；3、公共版；";
	public static final String ALIAS_CHANNEL_NAME = "栏目名称";
	public static final String ALIAS_CHANNEL_FLAG = "栏目标识";
	public static final String ALIAS_SELECT_ICON = "栏目选中图标";
	public static final String ALIAS_UNSELECTED_ICON = "栏目未选中图标";
	public static final String ALIAS_STATUS = "状态：1、开启；2、关闭；";
	public static final String ALIAS_COMPANY = "代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_SORT_NUM = "排序";
    */
	//date formats
	
	//columns START
	/**
	 * 主键ID
	 */
	private java.lang.Integer id;
	/**
	 * 版本状态：1、A版（资讯版）；2、B版（彩票版）；3、公共版；
	 */
	private java.lang.Integer version_status;
	/**
	 * 栏目名称
	 */
	private java.lang.String channel_name;
	/**
	 * 栏目标识
	 */
	private java.lang.String channel_flag;
	/**
	 * 栏目选中图标
	 */
	private java.lang.String select_icon;
	/**
	 * 栏目未选中图标
	 */
	private java.lang.String unselected_icon;
	/**
	 * 状态：1、开启；2、关闭；
	 */
	private java.lang.Integer status;
	/**
	 * 代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；
	 */
	private java.lang.String company;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 排序
	 */
	private java.lang.Integer sort_num;
	//columns END 数据库字段结束
	
	//concstructor

	public BetChannelVersionControl(){
	}

	public BetChannelVersionControl(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetChannelVersionControl_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setVersion_status(java.lang.Integer value) {
		this.version_status = value;
	}
	
     @WhereSQL(sql="version_status=:BetChannelVersionControl_version_status")
	public java.lang.Integer getVersion_status() {
		return this.version_status;
	}
	public void setChannel_name(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.channel_name = value;
	}
	
     @WhereSQL(sql="channel_name=:BetChannelVersionControl_channel_name")
	public java.lang.String getChannel_name() {
		return this.channel_name;
	}
	public void setChannel_flag(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.channel_flag = value;
	}
	
     @WhereSQL(sql="channel_flag=:BetChannelVersionControl_channel_flag")
	public java.lang.String getChannel_flag() {
		return this.channel_flag;
	}
	public void setSelect_icon(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.select_icon = value;
	}
	
     @WhereSQL(sql="select_icon=:BetChannelVersionControl_select_icon")
	public java.lang.String getSelect_icon() {
		return this.select_icon;
	}
	public void setUnselected_icon(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.unselected_icon = value;
	}
	
     @WhereSQL(sql="unselected_icon=:BetChannelVersionControl_unselected_icon")
	public java.lang.String getUnselected_icon() {
		return this.unselected_icon;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:BetChannelVersionControl_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:BetChannelVersionControl_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetChannelVersionControl_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setSort_num(java.lang.Integer value) {
		this.sort_num = value;
	}
	
     @WhereSQL(sql="sort_num=:BetChannelVersionControl_sort_num")
	public java.lang.Integer getSort_num() {
		return this.sort_num;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键ID[").append(getId()).append("],")
			.append("版本状态：1、A版（资讯版）；2、B版（彩票版）；3、公共版；[").append(getVersion_status()).append("],")
			.append("栏目名称[").append(getChannel_name()).append("],")
			.append("栏目标识[").append(getChannel_flag()).append("],")
			.append("栏目选中图标[").append(getSelect_icon()).append("],")
			.append("栏目未选中图标[").append(getUnselected_icon()).append("],")
			.append("状态：1、开启；2、关闭；[").append(getStatus()).append("],")
			.append("代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；[").append(getCompany()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("排序[").append(getSort_num()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetChannelVersionControl == false) return false;
		if(this == obj) return true;
		BetChannelVersionControl other = (BetChannelVersionControl)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
