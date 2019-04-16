package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-27 15:19:18
 * @see org.springrain.lottery.entity.BetGameplay
 */
@Table(name="bet_gameplay")
public class BetGameplay  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "玩法_原始（新）";
	public static final String ALIAS_ID = "玩法ID";
	public static final String ALIAS_GAMECLASSID = "游戏ID";
	public static final String ALIAS_NAME1 = "玩法一级分类";
	public static final String ALIAS_NAME2 = "玩法二级分类";
	public static final String ALIAS_ODDS = "赔率";
	public static final String ALIAS_NAME3 = "玩法三级分类";
	public static final String ALIAS_WIDTH = "宽度";
	public static final String ALIAS_HEIGHT = "高度";
	public static final String ALIAS_ORDER = "排序号";
	public static final String ALIAS_FONTSIZE = "字体大小";
    */
	//date formats
	
	//columns START
	/**
	 * 玩法ID
	 */
	private java.lang.Integer id;
	/**
	 * 游戏ID
	 */
	private java.lang.Integer gameclassid;
	/**
	 * 玩法一级分类
	 */
	private java.lang.String name1;
	/**
	 * 玩法二级分类
	 */
	private java.lang.String name2;
	/**
	 * 赔率
	 */
	private java.lang.Double odds;
	/**
	 * 玩法三级分类
	 */
	private java.lang.String name3;
	/**
	 * 宽度
	 */
	private java.lang.Integer width;
	/**
	 * 排序号
	 */
	private java.lang.Integer order1;
	/**
	 * 三级分类字体大小
	 */
	private java.lang.Integer fontsize;
	/**
	 * 二级分类字体大小
	 */
	private java.lang.Integer fontsize1;
	/**
	 * 下注个数
	 */
	private java.lang.Integer numofbets;
	private java.lang.Double maxodds;
	private java.lang.Double minbetting;
	private java.lang.Double maxbetting;
	//即时投注
	private java.lang.Double gameplaybetting;
	private java.lang.Double bettingrisk;
	
	@Transient
	public java.lang.Double getGameplaybetting() {
		return gameplaybetting;
	}

	public void setGameplaybetting(java.lang.Double gameplaybetting) {
		this.gameplaybetting = gameplaybetting;
	}
	@Transient
	public java.lang.Double getBettingrisk() {
		return bettingrisk;
	}

	public void setBettingrisk(java.lang.Double bettingrisk) {
		this.bettingrisk = bettingrisk;
	}

	//columns END 数据库字段结束
	@WhereSQL(sql="maxodds=:BetGameplay_maxodds")
	public java.lang.Double getMaxodds() {
		return maxodds;
	}

	public void setMaxodds(java.lang.Double maxodds) {
		this.maxodds = maxodds;
	}
	@WhereSQL(sql="minbetting=:BetGameplay_minbetting")
	public java.lang.Double getMinbetting() {
		return minbetting;
	}

	public void setMinbetting(java.lang.Double minbetting) {
		this.minbetting = minbetting;
	}
	@WhereSQL(sql="maxbetting=:BetGameplay_maxbetting")
	public java.lang.Double getMaxbetting() {
		return maxbetting;
	}

	public void setMaxbetting(java.lang.Double maxbetting) {
		this.maxbetting = maxbetting;
	}

	//concstructor
	 @WhereSQL(sql="fontsize1=:BetGameplay_fontsize1")
		public java.lang.Integer getFontsize1() {
			return fontsize1;
		}

		public void setFontsize1(java.lang.Integer fontsize1) {
			this.fontsize1 = fontsize1;
		}
	 @WhereSQL(sql="numofbets=:BetGameplay_numofbets")
	public java.lang.Integer getNumofbets() {
		return numofbets;
	}

	public void setNumofbets(java.lang.Integer numofbets) {
		this.numofbets = numofbets;
	}

	private java.lang.String gameclassname;
	@Transient
	public java.lang.String getGameclassname() {
		return gameclassname;
	}
	
	public void setGameclassname(java.lang.String gameclassname) {
		this.gameclassname = gameclassname;
	}

	public BetGameplay(){
	}

	public BetGameplay(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGameplay_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setGameclassid(java.lang.Integer value) {
		this.gameclassid = value;
	}
	
     @WhereSQL(sql="gameclassid=:BetGameplay_gameclassid")
	public java.lang.Integer getGameclassid() {
		return this.gameclassid;
	}
	public void setName1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name1 = value;
	}
	
     @WhereSQL(sql="name1=:BetGameplay_name1")
	public java.lang.String getName1() {
		return this.name1;
	}
	public void setName2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name2 = value;
	}
	
     @WhereSQL(sql="name2=:BetGameplay_name2")
	public java.lang.String getName2() {
		return this.name2;
	}
	public void setOdds(java.lang.Double value) {
		this.odds = value;
	}
	
     @WhereSQL(sql="odds=:BetGameplay_odds")
	public java.lang.Double getOdds() {
		return this.odds;
	}
	public void setName3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name3 = value;
	}
	
     @WhereSQL(sql="name3=:BetGameplay_name3")
	public java.lang.String getName3() {
		return this.name3;
	}
	public void setWidth(java.lang.Integer value) {
		this.width = value;
	}
	
     @WhereSQL(sql="width=:BetGameplay_width")
	public java.lang.Integer getWidth() {
		return this.width;
	}
	
	public void setOrder1(java.lang.Integer value) {
		this.order1 = value;
	}
	
     @WhereSQL(sql="order1=:BetGameplay_order1")
	public java.lang.Integer getOrder1() {
		return this.order1;
	}
	public void setFontsize(java.lang.Integer value) {
		this.fontsize = value;
	}
	
     @WhereSQL(sql="fontsize=:BetGameplay_fontsize")
	public java.lang.Integer getFontsize() {
		return this.fontsize;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("玩法ID[").append(getId()).append("],")
			.append("游戏ID[").append(getGameclassid()).append("],")
			.append("玩法一级分类[").append(getName1()).append("],")
			.append("玩法二级分类[").append(getName2()).append("],")
			.append("赔率[").append(getOdds()).append("],")
			.append("玩法三级分类[").append(getName3()).append("],")
			.append("宽度[").append(getWidth()).append("],")
			.append("排序号[").append(getOrder1()).append("],")
			.append("字体大小[").append(getFontsize()).append("],")
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
		if(obj instanceof BetGameplay == false) return false;
		if(this == obj) return true;
		BetGameplay other = (BetGameplay)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
