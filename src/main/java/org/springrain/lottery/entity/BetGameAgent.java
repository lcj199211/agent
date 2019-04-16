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
 * @version  2018-05-10 11:02:59
 * @see org.springrain.lottery.entity.BetGameAgent
 */
@Table(name="bet_game_agent")
public class BetGameAgent  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGameAgent";
	public static final String ALIAS_ID = "首页游戏表id";
	public static final String ALIAS_GAMENAME = "游戏名";
	public static final String ALIAS_TITLE = "游戏标题";
	public static final String ALIAS_IMG = "图标";
	public static final String ALIAS_REMARK = "说明";
	public static final String ALIAS_STATE = "1.开启 3.关闭";
	public static final String ALIAS_AGENTID = "代理";
	public static final String ALIAS_PARENTIDS = "company";
    */
	//date formats
	
	//columns START
	/**
	 * 首页游戏表id
	 */
	private java.lang.Integer id;
	/**
	 * 游戏名
	 */
	private java.lang.String gamename;
	/**
	 * 游戏标题
	 */
	private java.lang.String title;
	/**
	 * 图标
	 */
	private java.lang.String img;
	/**
	 * 说明
	 */
	private java.lang.String remark;
	/**
	 * 1.开启 3.关闭
	 */
	private java.lang.Integer state;
	/**
	 * 代理
	 */
	private java.lang.String agentid;
	/**
	 * company
	 */
	private String company;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGameAgent(){
	}

	public BetGameAgent(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGameAgent_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGamename(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gamename = value;
	}
	
     @WhereSQL(sql="gamename=:BetGameAgent_gamename")
	public java.lang.String getGamename() {
		return this.gamename;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:BetGameAgent_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setImg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.img = value;
	}
	
     @WhereSQL(sql="img=:BetGameAgent_img")
	public java.lang.String getImg() {
		return this.img;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetGameAgent_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetGameAgent_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	
     @WhereSQL(sql="agentid=:BetGameAgent_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
     @WhereSQL(sql="company=:BetGameAgent_company")
     public String getCompany() {
 		return company;
 	}

 	public void setCompany(String company) {
 		this.company = company;
 	}
	public String toString() {
		return new StringBuffer()
			.append("首页游戏表id[").append(getId()).append("],")
			.append("游戏名[").append(getGamename()).append("],")
			.append("游戏标题[").append(getTitle()).append("],")
			.append("图标[").append(getImg()).append("],")
			.append("说明[").append(getRemark()).append("],")
			.append("1.开启 3.关闭[").append(getState()).append("],")
			.append("代理[").append(getAgentid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGameAgent == false) return false;
		if(this == obj) return true;
		BetGameAgent other = (BetGameAgent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
}

	
