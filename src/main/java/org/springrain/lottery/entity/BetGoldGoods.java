package org.springrain.lottery.entity;

import java.text.ParseException;
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
 * @version  2019-01-25 14:48:35
 * @see org.springrain.lottery.entity.BetGoldGoods
 */
@Table(name="bet_gold_goods")
public class BetGoldGoods  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "BetGoldGoods";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MINRECHARGE = "最小值";
	public static final String ALIAS_MAXRECHARGE = "最大值";
	public static final String ALIAS_IMAGE = "图片路径";
	public static final String ALIAS_COMPANY = "代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；";
	public static final String ALIAS_GOODSNAME = "商品名称";
	public static final String ALIAS_MONEY = "金额";
	public static final String ALIAS_CONTENT = "商品详细";
	public static final String ALIAS_CATEGORYID = "关联分类表id";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_TYPE = "类型：1 金额购买  2 积分兑换";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 最小值
	 */
	private java.lang.Double minrecharge;
	/**
	 * 最大值
	 */
	private java.lang.Double maxrecharge;
	/**
	 * 图片路径
	 */
	private java.lang.String image;
	/**
	 * 代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；
	 */
	private java.lang.String company;
	/**
	 * 商品名称
	 */
	private java.lang.String goodsname;
	/**
	 * 金额
	 */
	private java.lang.Double money;
	/**
	 * 商品详细
	 */
	private java.lang.String content;
	/**
	 * 关联分类表id
	 */
	private java.lang.Integer categoryid;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 类型：1 金额购买  2 积分兑换
	 */
	private java.lang.Integer type;
	//columns END 数据库字段结束
	
	//concstructor

	public BetGoldGoods(){
	}

	public BetGoldGoods(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetGoldGoods_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMinrecharge(java.lang.Double value) {
		this.minrecharge = value;
	}
	
     @WhereSQL(sql="minrecharge=:BetGoldGoods_minrecharge")
	public java.lang.Double getMinrecharge() {
		return this.minrecharge;
	}
	public void setMaxrecharge(java.lang.Double value) {
		this.maxrecharge = value;
	}
	
     @WhereSQL(sql="maxrecharge=:BetGoldGoods_maxrecharge")
	public java.lang.Double getMaxrecharge() {
		return this.maxrecharge;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:BetGoldGoods_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
     @WhereSQL(sql="company=:BetGoldGoods_company")
	public java.lang.String getCompany() {
		return this.company;
	}
	public void setGoodsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.goodsname = value;
	}
	
     @WhereSQL(sql="goodsname=:BetGoldGoods_goodsname")
	public java.lang.String getGoodsname() {
		return this.goodsname;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:BetGoldGoods_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:BetGoldGoods_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setCategoryid(java.lang.Integer value) {
		this.categoryid = value;
	}
	
     @WhereSQL(sql="categoryid=:BetGoldGoods_categoryid")
	public java.lang.Integer getCategoryid() {
		return this.categoryid;
	}
		/*
	public String getcreatetimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreatetime());
	}
	public void setcreatetimeString(String value) throws ParseException{
		setcreatetime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
     @WhereSQL(sql="createtime=:BetGoldGoods_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:BetGoldGoods_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("最小值[").append(getMinrecharge()).append("],")
			.append("最大值[").append(getMaxrecharge()).append("],")
			.append("图片路径[").append(getImage()).append("],")
			.append("代理公司名称：zhouyunfei 樂彩；klc 麒麟；fhjc 鳳凰競彩；fhwc 鳳凰微彩； paike99 微投彩；bifa888 必中彩；yc 易彩；ttcai 天天彩；[").append(getCompany()).append("],")
			.append("商品名称[").append(getGoodsname()).append("],")
			.append("金额[").append(getMoney()).append("],")
			.append("商品详细[").append(getContent()).append("],")
			.append("关联分类表id[").append(getCategoryid()).append("],")
			.append("创建时间[").append(getCreatetime()).append("],")
			.append("类型：1 金额购买  2 积分兑换[").append(getType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BetGoldGoods == false) return false;
		if(this == obj) return true;
		BetGoldGoods other = (BetGoldGoods)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
