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
 * @version  2017-05-24 10:03:23
 * @see org.springrain.lottery.entity.BetAgentGamemanage
 */
@Table(name="bet_agent_gamemanage")
public class BetAgentGamemanage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetAgentGamemanage";
	public static final String ALIAS_ID = "代理商游戏管理表id";
	public static final String ALIAS_GAMECLASSID = "游戏id";
	public static final String ALIAS_AGENTID = "代理商id";
	public static final String ALIAS_AGENTPARENTID = "代理商父id";
	public static final String ALIAS_AGENTPARENTIDS = "代理商所有上级id，根节点开始到父节点";
	public static final String ALIAS_STATE = "游戏管理(0:不可玩 1:可玩)";
    */
	//date formats
	
	//columns START
	/**
	 * 代理商游戏管理表id
	 */
	private java.lang.Integer id;
	/**
	 * 游戏id
	 */
	private java.lang.Integer gameclassid;
	/**
	 * 游戏名
	 */
	private java.lang.String gcname;
	/**
	 * 代理商id
	 */
	private java.lang.String agentid;
	/**
	 * 代理商父id
	 */
	private java.lang.String agentparentid;
	/**
	 * 代理商所有上级id，根节点开始到父节点
	 */
	private java.lang.String agentparentids;
	/**
	 * 游戏管理(0:不可玩 1:可玩)
	 */
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor

	public BetAgentGamemanage(){
	}

	public BetAgentGamemanage(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgentGamemanage_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGameclassid(java.lang.Integer value) {
		this.gameclassid = value;
	}
	
     @WhereSQL(sql="gameclassid=:BetAgentGamemanage_gameclassid")
	public java.lang.Integer getGameclassid() {
		return this.gameclassid;
	}
	public void setAgentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentid = value;
	}
	 @WhereSQL(sql="gcname=:BetAgentGamemanage_gcname")
     public java.lang.String getGcname() {
		return gcname;
	}

	public void setGcname(java.lang.String gcname) {
		this.gcname = gcname;
	}

	@WhereSQL(sql="agentid=:BetAgentGamemanage_agentid")
	public java.lang.String getAgentid() {
		return this.agentid;
	}
	public void setAgentparentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentid = value;
	}
	
     @WhereSQL(sql="agentparentid=:BetAgentGamemanage_agentparentid")
	public java.lang.String getAgentparentid() {
		return this.agentparentid;
	}
	public void setAgentparentids(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.agentparentids = value;
	}
	
     @WhereSQL(sql="agentparentids=:BetAgentGamemanage_agentparentids")
	public java.lang.String getAgentparentids() {
		return this.agentparentids;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetAgentGamemanage_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("代理商游戏管理表id[").append(getId()).append("],")
			.append("游戏id[").append(getGameclassid()).append("],")
			.append("代理商id[").append(getAgentid()).append("],")
			.append("代理商父id[").append(getAgentparentid()).append("],")
			.append("代理商所有上级id，根节点开始到父节点[").append(getAgentparentids()).append("],")
			.append("游戏管理(0:不可玩 1:可玩)[").append(getState()).append("],")
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
		if(obj instanceof BetAgentGamemanage == false) return false;
		if(this == obj) return true;
		BetAgentGamemanage other = (BetAgentGamemanage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
