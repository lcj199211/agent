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
 * @version  2017-11-14 10:33:12
 * @see org.springrain.lottery.entity.BasketballLiveAnalyze
 */
@Table(name="basketball_live_analyze")
public class BasketballLiveAnalyze  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "篮球实况分析";
	public static final String ALIAS_ID = "实况技术统计表id";
	public static final String ALIAS_FID = "fid";
	public static final String ALIAS_TEAMNAME = "teamname";
	public static final String ALIAS_TEAMID2 = "teamid2";
	public static final String ALIAS_PLAYER = "player";
	public static final String ALIAS_ISPUB = "是否首发1.是 3.不是";
	public static final String ALIAS_TIME = "time";
	public static final String ALIAS_SCORE = "score";
	public static final String ALIAS_LANBAN0 = "lanban0";
	public static final String ALIAS_ZHUGONG = "zhugong";
	public static final String ALIAS_TOULAN = "toulan";
	public static final String ALIAS_SANFEN = "sanfen";
	public static final String ALIAS_FAQIU = "faqiu";
	public static final String ALIAS_QIANGDUAN = "qiangduan";
	public static final String ALIAS_SHIWU = "shiwu";
	public static final String ALIAS_GAIMAO = "gaimao";
	public static final String ALIAS_FANGUI = "fangui";
	public static final String ALIAS_QIANLANBAN = "前篮板";
	public static final String ALIAS_HOULANBAN = "后篮板";
    */
	//date formats
	
	//columns START
	/**
	 * 实况技术统计表id
	 */
	private java.lang.Integer id;
	/**
	 * fid
	 */
	private java.lang.String fid;
	/**
	 * teamname
	 */
	private java.lang.String teamname;
	/**
	 * teamid2
	 */
	private java.lang.String teamid2;
	/**
	 * player
	 */
	private java.lang.String player;
	/**
	 * 是否首发1.是 3.不是
	 */
	private java.lang.Integer ispub;
	/**
	 * time
	 */
	private java.lang.String time;
	/**
	 * score
	 */
	private java.lang.String score;
	/**
	 * lanban0
	 */
	private java.lang.String lanban;
	/**
	 * zhugong
	 */
	private java.lang.String zhugong;
	/**
	 * toulan
	 */
	private java.lang.String toulan;
	/**
	 * sanfen
	 */
	private java.lang.String sanfen;
	/**
	 * faqiu
	 */
	private java.lang.String faqiu;
	/**
	 * qiangduan
	 */
	private java.lang.String qiangduan;
	/**
	 * shiwu
	 */
	private java.lang.String shiwu;
	/**
	 * gaimao
	 */
	private java.lang.String gaimao;
	/**
	 * fangui
	 */
	private java.lang.String fangui;
	/**
	 * 前篮板
	 */
	private java.lang.String qianlanban;
	/**
	 * 后篮板
	 */
	private java.lang.String houlanban;
	//columns END 数据库字段结束
	
	//concstructor

	public BasketballLiveAnalyze(){
	}

	public BasketballLiveAnalyze(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 实况技术统计表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 实况技术统计表id
	 */
	@Id
     @WhereSQL(sql="id=:BasketballLiveAnalyze_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * fid
		 */
	public void setFid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fid = value;
	}
	
	
	
	/**
	 * fid
	 */
     @WhereSQL(sql="fid=:BasketballLiveAnalyze_fid")
	public java.lang.String getFid() {
		return this.fid;
	}
		/**
		 * teamname
		 */
	public void setTeamname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.teamname = value;
	}
	
	
	
	/**
	 * teamname
	 */
     @WhereSQL(sql="teamname=:BasketballLiveAnalyze_teamname")
	public java.lang.String getTeamname() {
		return this.teamname;
	}
		/**
		 * teamid2
		 */
	public void setTeamid2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.teamid2 = value;
	}
	
	
	
	/**
	 * teamid2
	 */
     @WhereSQL(sql="teamid2=:BasketballLiveAnalyze_teamid2")
	public java.lang.String getTeamid2() {
		return this.teamid2;
	}
		/**
		 * player
		 */
	public void setPlayer(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.player = value;
	}
	
	
	
	/**
	 * player
	 */
     @WhereSQL(sql="player=:BasketballLiveAnalyze_player")
	public java.lang.String getPlayer() {
		return this.player;
	}
		/**
		 * 是否首发1.是 3.不是
		 */
	public void setIspub(java.lang.Integer value) {
		this.ispub = value;
	}
	
	
	
	/**
	 * 是否首发1.是 3.不是
	 */
     @WhereSQL(sql="ispub=:BasketballLiveAnalyze_ispub")
	public java.lang.Integer getIspub() {
		return this.ispub;
	}
		/**
		 * time
		 */
	public void setTime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.time = value;
	}
	
	
	
	/**
	 * time
	 */
     @WhereSQL(sql="time=:BasketballLiveAnalyze_time")
	public java.lang.String getTime() {
		return this.time;
	}
		/**
		 * score
		 */
	public void setScore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.score = value;
	}
	
	
	
	/**
	 * score
	 */
     @WhereSQL(sql="score=:BasketballLiveAnalyze_score")
	public java.lang.String getScore() {
		return this.score;
	}
		/**
		 * lanban0
		 */
	public void setLanban(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lanban = value;
	}
	
	
	
	/**
	 * lanban0
	 */
     @WhereSQL(sql="lanban=:BasketballLiveAnalyze_lanban")
	public java.lang.String getLanban() {
		return this.lanban;
	}
		/**
		 * zhugong
		 */
	public void setZhugong(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zhugong = value;
	}
	
	
	
	/**
	 * zhugong
	 */
     @WhereSQL(sql="zhugong=:BasketballLiveAnalyze_zhugong")
	public java.lang.String getZhugong() {
		return this.zhugong;
	}
		/**
		 * toulan
		 */
	public void setToulan(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.toulan = value;
	}
	
	
	
	/**
	 * toulan
	 */
     @WhereSQL(sql="toulan=:BasketballLiveAnalyze_toulan")
	public java.lang.String getToulan() {
		return this.toulan;
	}
		/**
		 * sanfen
		 */
	public void setSanfen(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sanfen = value;
	}
	
	
	
	/**
	 * sanfen
	 */
     @WhereSQL(sql="sanfen=:BasketballLiveAnalyze_sanfen")
	public java.lang.String getSanfen() {
		return this.sanfen;
	}
		/**
		 * faqiu
		 */
	public void setFaqiu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.faqiu = value;
	}
	
	
	
	/**
	 * faqiu
	 */
     @WhereSQL(sql="faqiu=:BasketballLiveAnalyze_faqiu")
	public java.lang.String getFaqiu() {
		return this.faqiu;
	}
		/**
		 * qiangduan
		 */
	public void setQiangduan(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qiangduan = value;
	}
	
	
	
	/**
	 * qiangduan
	 */
     @WhereSQL(sql="qiangduan=:BasketballLiveAnalyze_qiangduan")
	public java.lang.String getQiangduan() {
		return this.qiangduan;
	}
		/**
		 * shiwu
		 */
	public void setShiwu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shiwu = value;
	}
	
	
	
	/**
	 * shiwu
	 */
     @WhereSQL(sql="shiwu=:BasketballLiveAnalyze_shiwu")
	public java.lang.String getShiwu() {
		return this.shiwu;
	}
		/**
		 * gaimao
		 */
	public void setGaimao(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gaimao = value;
	}
	
	
	
	/**
	 * gaimao
	 */
     @WhereSQL(sql="gaimao=:BasketballLiveAnalyze_gaimao")
	public java.lang.String getGaimao() {
		return this.gaimao;
	}
		/**
		 * fangui
		 */
	public void setFangui(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fangui = value;
	}
	
	
	
	/**
	 * fangui
	 */
     @WhereSQL(sql="fangui=:BasketballLiveAnalyze_fangui")
	public java.lang.String getFangui() {
		return this.fangui;
	}
		/**
		 * 前篮板
		 */
	public void setQianlanban(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qianlanban = value;
	}
	
	
	
	/**
	 * 前篮板
	 */
     @WhereSQL(sql="qianlanban=:BasketballLiveAnalyze_qianlanban")
	public java.lang.String getQianlanban() {
		return this.qianlanban;
	}
		/**
		 * 后篮板
		 */
	public void setHoulanban(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.houlanban = value;
	}
	
	
	
	/**
	 * 后篮板
	 */
     @WhereSQL(sql="houlanban=:BasketballLiveAnalyze_houlanban")
	public java.lang.String getHoulanban() {
		return this.houlanban;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("实况技术统计表id[").append(getId()).append("],")
			.append("fid[").append(getFid()).append("],")
			.append("teamname[").append(getTeamname()).append("],")
			.append("teamid2[").append(getTeamid2()).append("],")
			.append("player[").append(getPlayer()).append("],")
			.append("是否首发1.是 3.不是[").append(getIspub()).append("],")
			.append("time[").append(getTime()).append("],")
			.append("score[").append(getScore()).append("],")
			.append("lanban0[").append(getLanban()).append("],")
			.append("zhugong[").append(getZhugong()).append("],")
			.append("toulan[").append(getToulan()).append("],")
			.append("sanfen[").append(getSanfen()).append("],")
			.append("faqiu[").append(getFaqiu()).append("],")
			.append("qiangduan[").append(getQiangduan()).append("],")
			.append("shiwu[").append(getShiwu()).append("],")
			.append("gaimao[").append(getGaimao()).append("],")
			.append("fangui[").append(getFangui()).append("],")
			.append("前篮板[").append(getQianlanban()).append("],")
			.append("后篮板[").append(getHoulanban()).append("],")
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
		if(obj instanceof BasketballLiveAnalyze == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		BasketballLiveAnalyze other = (BasketballLiveAnalyze)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
