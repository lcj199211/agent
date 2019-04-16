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
 * @version  2017-10-09 17:19:50
 * @see org.springrain.lottery.entity.BetPlayingmethod
 */
@Table(name="bet_playingmethod")
public class BetPlayingmethod  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "玩法说明";
	public static final String ALIAS_ID = "玩法表id";
	public static final String ALIAS_GAMENAME = "游戏名";
	public static final String ALIAS_PLAYINGMETHOD = "玩法";
    */
	//date formats
	
	//columns START
	/**
	 * 玩法表id
	 */
	private java.lang.Integer id;
	/**
	 * 游戏名
	 */
	private java.lang.String gamename;
	/**
	 * 玩法
	 */
	private java.lang.String playingmethod;
	private java.lang.Integer state;
	//columns END 数据库字段结束
	
	//concstructor
	@WhereSQL(sql="state=:BetPlayingmethod_state")
	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public BetPlayingmethod(){
	}

	public BetPlayingmethod(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 玩法表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 玩法表id
	 */
	@Id
     @WhereSQL(sql="id=:BetPlayingmethod_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 游戏名
		 */
	public void setGamename(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gamename = value;
	}
	
	
	
	/**
	 * 游戏名
	 */
     @WhereSQL(sql="gamename=:BetPlayingmethod_gamename")
	public java.lang.String getGamename() {
		return this.gamename;
	}
		/**
		 * 玩法
		 */
	public void setPlayingmethod(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.playingmethod = value;
	}
	
	
	
	/**
	 * 玩法
	 */
     @WhereSQL(sql="playingmethod=:BetPlayingmethod_playingmethod")
	public java.lang.String getPlayingmethod() {
		return this.playingmethod;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("玩法表id[").append(getId()).append("],")
			.append("游戏名[").append(getGamename()).append("],")
			.append("玩法[").append(getPlayingmethod()).append("],")
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
		if(obj instanceof BetPlayingmethod == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		BetPlayingmethod other = (BetPlayingmethod)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
