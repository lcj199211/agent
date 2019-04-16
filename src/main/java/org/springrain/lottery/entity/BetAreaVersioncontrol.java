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
 * @version  2019-04-03 10:16:14
 * @see org.springrain.news.entity.BetAreaVersioncontrol
 */
@Table(name="bet_area_versioncontrol")
public class BetAreaVersioncontrol  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "地域栏目版本";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_VERSIONCONTROL = "栏目版本资讯版A版，彩票版B版";
	public static final String ALIAS_AREAIDS = "行政地区ids，11";
	public static final String ALIAS_AREANAMES = "行政地区名称，北京";
	public static final String ALIAS_CREATEDATE = "创建时间";
	public static final String ALIAS_MODIFYDATE = "修改时间";
	public static final String ALIAS_COMPANY = "代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；";
	public static final String ALIAS_STATUS = "状态：1、开启；2、关闭；";
	public static final String ALIAS_DIRECTION = "方向：1、禁止；2、只能";
    */
	//date formats
	//public static final String FORMAT_CREATEDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_MODIFYDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 栏目版本资讯版A版，彩票版B版
	 */
	private java.lang.String versioncontrol;
	/**
	 * 行政地区ids，11
	 */
	private java.lang.String areaids;
	/**
	 * 行政地区名称，北京
	 */
	private java.lang.String areanames;
	/**
	 * 创建时间
	 */
	private java.util.Date createdate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifydate;
	/**
	 * 代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；
	 */
	private java.lang.String company;
	/**
	 * 状态：1、开启；2、关闭；
	 */
	private java.lang.Integer status;
	/**
	 * 方向：1、禁止；2、只能
	 */
	private java.lang.Integer direction;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAreaVersioncontrol(){
	}

	public BetAreaVersioncontrol(
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
     @WhereSQL(sql="id=:BetAreaVersioncontrol_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setVersioncontrol(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.versioncontrol = value;
	}
	
     @WhereSQL(sql="versioncontrol=:BetAreaVersioncontrol_versioncontrol")
	public java.lang.String getVersioncontrol() {
		return this.versioncontrol;
	}
	public void setAreaids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.areaids = value;
	}
	
     @WhereSQL(sql="areaids=:BetAreaVersioncontrol_areaids")
	public java.lang.String getAreaids() {
		return this.areaids;
	}
	public void setAreanames(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.areanames = value;
	}
	
     @WhereSQL(sql="areanames=:BetAreaVersioncontrol_areanames")
	public java.lang.String getAreanames() {
		return this.areanames;
	}
		/*
	public String getcreatedateString() {
		return DateUtils.convertDate2String(FORMAT_CREATEDATE, getcreatedate());
	}
	public void setcreatedateString(String value) throws ParseException{
		setcreatedate(DateUtils.convertString2Date(FORMAT_CREATEDATE,value));
	}*/
	
	public void setCreatedate(java.util.Date value) {
		this.createdate = value;
	}
	
     @WhereSQL(sql="createdate=:BetAreaVersioncontrol_createdate")
	public java.util.Date getCreatedate() {
		return this.createdate;
	}
		/*
	public String getmodifydateString() {
		return DateUtils.convertDate2String(FORMAT_MODIFYDATE, getmodifydate());
	}
	public void setmodifydateString(String value) throws ParseException{
		setmodifydate(DateUtils.convertString2Date(FORMAT_MODIFYDATE,value));
	}*/
	
	public void setModifydate(java.util.Date value) {
		this.modifydate = value;
	}
	
     @WhereSQL(sql="modifydate=:BetAreaVersioncontrol_modifydate")
	public java.util.Date getModifydate() {
		return this.modifydate;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:BetAreaVersioncontrol_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:BetAreaVersioncontrol_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setDirection(java.lang.Integer value) {
		this.direction = value;
	}
	
     @WhereSQL(sql="direction=:BetAreaVersioncontrol_direction")
	public java.lang.Integer getDirection() {
		return this.direction;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("栏目版本资讯版A版，彩票版B版[").append(getVersioncontrol()).append("],")
			.append("行政地区ids，11[").append(getAreaids()).append("],")
			.append("行政地区名称，北京[").append(getAreanames()).append("],")
			.append("创建时间[").append(getCreatedate()).append("],")
			.append("修改时间[").append(getModifydate()).append("],")
			.append("代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；[").append(getCompany()).append("],")
			.append("状态：1、开启；2、关闭；[").append(getStatus()).append("],")
			.append("方向：1、禁止；2、只能[").append(getDirection()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetAreaVersioncontrol == false) return false;
		if(this == obj) return true;
		BetAreaVersioncontrol other = (BetAreaVersioncontrol)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
