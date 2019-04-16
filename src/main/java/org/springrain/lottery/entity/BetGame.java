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
 * @version  2018-04-11 14:54:00
 * @see org.springrain.lottery.entity.BetGame
 */
@Table(name="bet_game")
public class BetGame  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGame";
	public static final String ALIAS_ID = "首页游戏表id";
	public static final String ALIAS_GAMENAME = "游戏名";
	public static final String ALIAS_TITLE = "游戏标题";
	public static final String ALIAS_IMG = "图标";
	public static final String ALIAS_REMARK = "说明";
	public static final String ALIAS_STATE = "1.开启 3.关闭";
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
	private String company;
	private Integer allowupdate;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGame(){
	}

	public BetGame(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGame_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGamename(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gamename = value;
	}
	
     @WhereSQL(sql="gamename=:BetGame_gamename")
	public java.lang.String getGamename() {
		return this.gamename;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:BetGame_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setImg(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.img = value;
	}
	
     @WhereSQL(sql="img=:BetGame_img")
	public java.lang.String getImg() {
		return this.img;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:BetGame_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetGame_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     @WhereSQL(sql="company=:BetGame_company")
     public String getCompany() {
 		return company;
 	}

 	public void setCompany(String company) {
 		this.company = company;
 	}
 	@WhereSQL(sql="allowupdate=:BetGame_allowupdate")
 	public Integer getAllowupdate() {
 		return allowupdate;
 	}

 	public void setAllowupdate(Integer allowupdate) {
 		this.allowupdate = allowupdate;
 	}
	public String toString() {
		return new StringBuffer()
			.append("首页游戏表id[").append(getId()).append("],")
			.append("游戏名[").append(getGamename()).append("],")
			.append("游戏标题[").append(getTitle()).append("],")
			.append("图标[").append(getImg()).append("],")
			.append("说明[").append(getRemark()).append("],")
			.append("1.开启 3.关闭[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGame == false) return false;
		if(this == obj) return true;
		BetGame other = (BetGame)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
}

	
