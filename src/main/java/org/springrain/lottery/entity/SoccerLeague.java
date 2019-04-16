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
 * @version  2017-08-17 16:51:27
 * @see org.springrain.lottery.entity.SoccerLeague
 */
@Table(name="soccer_league")
public class SoccerLeague  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeague";
	public static final String ALIAS_ID = "联赛表id";
	public static final String ALIAS_ID2 = "联赛id(来自500)";
	public static final String ALIAS_NAME = "联赛名";
	public static final String ALIAS_STATE = "1正常,3删除";
    */
	//date formats
	
	//columns START
	/**
	 * 联赛表id
	 */
	private java.lang.Integer id;
	/**
	 * 联赛id(来自500)
	 */
	private java.lang.String id2;
	/**
	 * 联赛名
	 */
	private java.lang.String name;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 是否热门1:是 3:不是
	 */
	private java.lang.Integer ishot;
	/**
	 * 是否五大联赛1:是 3:不是
	 */
	private java.lang.Integer iswdls;
	/**
	 * 1五大联赛,2热门赛事
	 
	private java.lang.String type;
	*/
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLeague(){
	}

	public SoccerLeague(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:SoccerLeague_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setId2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id2 = value;
	}
	
     @WhereSQL(sql="id2=:SoccerLeague_id2")
	public java.lang.String getId2() {
		return this.id2;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:SoccerLeague_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:SoccerLeague_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	
     
     @WhereSQL(sql="ishot=:SoccerLeague_ishot")
     public java.lang.Integer getIshot() {
		return ishot;
	}

	public void setIshot(java.lang.Integer ishot) {
		this.ishot = ishot;
	}
	@WhereSQL(sql="iswdls=:SoccerLeague_iswdls")
	public java.lang.Integer getIswdls() {
		return iswdls;
	}

	public void setIswdls(java.lang.Integer iswdls) {
		this.iswdls = iswdls;
	}

	/*
     @WhereSQL(sql="type=:SoccerLeague_type")
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}
*/
	public String toString() {
		return new StringBuffer()
			.append("联赛表id[").append(getId()).append("],")
			.append("联赛id(来自500)[").append(getId2()).append("],")
			.append("联赛名[").append(getName()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SoccerLeague == false) return false;
		if(this == obj) return true;
		SoccerLeague other = (SoccerLeague)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
