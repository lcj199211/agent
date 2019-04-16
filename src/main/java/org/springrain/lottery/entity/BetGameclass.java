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
 * @version  2017-03-09 11:41:19
 * @see org.springrain.lottery.entity.BetGameclass
 */
@Table(name="bet_gameclass")
public class BetGameclass  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "游戏";
	public static final String ALIAS_GAMECLASSID = "游戏ID";
	public static final String ALIAS_GCNAME = "游戏中文名";
	public static final String ALIAS_GENAME = "游戏英文名";
	public static final String ALIAS_GSNAME = "简称";
	public static final String ALIAS_GRANK = "排序状态";
	public static final String ALIAS_GLOCK = "状态";
	public static final String ALIAS_GKIND = "1一般,2时时,3快开";
	public static final String ALIAS_PT = "性质分类";
	public static final String ALIAS_NLEN = "号码数量";
	public static final String ALIAS_NMIN = "最小号码";
	public static final String ALIAS_NMAX = "最大号码";
	public static final String ALIAS_GAMERULES = "游戏规则";
	public static final String ALIAS_GAMETXT = "gametxt";
	public static final String ALIAS_GSTOCKID = "股份类型";
	public static final String ALIAS_GAMETYPE = "gametype";
	public static final String ALIAS_SUBMENU = "投注子菜单";
	public static final String ALIAS_MAXLOST = "maxlost";
	public static final String ALIAS_RATELOST = "ratelost";
	public static final String ALIAS_RATEWIN = "ratewin";
	public static final String ALIAS_MCLASS = "mclass";
	public static final String ALIAS_MLOCK = "mlock";
	public static final String ALIAS_MSH = "开始时";
	public static final String ALIAS_MSM = "开始分";
	public static final String ALIAS_MSS = "开始秒";
	public static final String ALIAS_MEH = "结束时";
	public static final String ALIAS_MEM = "结束分";
	public static final String ALIAS_MES = "结束秒";
	public static final String ALIAS_MSTEP = "每期时间";
	public static final String ALIAS_MNUMS = "每天期数";
	public static final String ALIAS_MOPEN = "开奖/秒";
	public static final String ALIAS_MSTOP = "截止/秒";
	public static final String ALIAS_MFINE = "开奖时间微调";
	public static final String ALIAS_MSNUM = "首期编码";
	public static final String ALIAS_MSTIME = "首期时间";
	public static final String ALIAS_MSURL = "网址";
	public static final String ALIAS_BETTINGCEILING = "投注上限";
	public static final String ALIAS_BETTINGLIMIT = "投注下限";
	public static final String ALIAS_TY = "退佣";
	public static final String ALIAS_STATE = "状态";
	public static final String ALIAS_CURRENTTICKETS = "本期开奖";
    */
	//date formats
	
	//columns START
	/**
	 * 游戏ID
	 */
	private java.lang.Integer gameclassid;
	/**
	 * 游戏中文名
	 */
	private java.lang.String gcname;
	/**
	 * 游戏英文名
	 */
	private java.lang.String gename;
	/**
	 * 简称
	 */
	private java.lang.String gsname;
	/**
	 * 排序状态
	 */
	private java.lang.Integer grank;
	/**
	 * 状态
	 */
	private java.lang.Integer glock;
	/**
	 * 1一般,2时时,3快开
	 */
	private java.lang.Integer gkind;
	/**
	 * 性质分类
	 */
	private java.lang.Integer pt;
	/**
	 * 号码数量
	 */
	private java.lang.Integer nlen;
	/**
	 * 最小号码
	 */
	private java.lang.Integer nmin;
	/**
	 * 最大号码
	 */
	private java.lang.Integer nmax;
	/**
	 * 游戏规则
	 */
	private java.lang.String gamerules;
	/**
	 * gametxt
	 */
	private java.lang.String gametxt;
	/**
	 * 股份类型
	 */
	private java.lang.Integer gstockid;
	/**
	 * gametype
	 */
	private java.lang.Integer gametype;
	/**
	 * 投注子菜单
	 */
	private java.lang.String submenu;
	/**
	 * maxlost
	 */
	private java.lang.Integer maxlost;
	/**
	 * ratelost
	 */
	private java.lang.Integer ratelost;
	/**
	 * ratewin
	 */
	private java.lang.Integer ratewin;
	/**
	 * mclass
	 */
	private java.lang.Integer mclass;
	/**
	 * mlock
	 */
	private java.lang.Integer mlock;
	/**
	 * 开始时
	 */
	private java.lang.Integer msh;
	/**
	 * 开始分
	 */
	private java.lang.Integer msm;
	/**
	 * 开始秒
	 */
	private java.lang.Integer mss;
	/**
	 * 结束时
	 */
	private java.lang.Integer meh;
	/**
	 * 结束分
	 */
	private java.lang.Integer mem;
	/**
	 * 结束秒
	 */
	private java.lang.Integer mes;
	/**
	 * 每期时间
	 */
	private java.lang.Integer mstep;
	/**
	 * 每天期数
	 */
	private java.lang.Integer mnums;
	/**
	 * 开奖/秒
	 */
	private java.lang.Integer mopen;
	/**
	 * 截止/秒
	 */
	private java.lang.Integer mstop;
	/**
	 * 开奖时间微调
	 */
	private java.lang.Integer mfine;
	/**
	 * 首期编码
	 */
	private java.lang.Integer msnum;
	/**
	 * 首期时间
	 */
	private java.lang.String mstime;
	/**
	 * 网址
	 */
	private java.lang.String msurl;
	/**
	 * 投注上限
	 */
	private java.lang.Double bettingceiling;
	/**
	 * 投注下限
	 */
	private java.lang.Double bettinglimit;
	/**
	 * 退佣
	 */
	private java.lang.Double ty;
	/**
	 * 状态
	 */
	private java.lang.Integer state;
	
	/**
	 * 本期开奖
	 */
	private java.lang.String currenttickets;
	private java.lang.String specialrules;
	private java.lang.String imgable;
	private java.lang.String imgdisabled;
	private java.lang.Integer tymode;
	private java.lang.Double totalty;
	private java.lang.String mobilegamerules;
	private java.lang.Double membertotalty;
	private java.lang.Integer membertymode;
	private java.lang.Double memberty;
	
	

	@WhereSQL(sql="memberty=:BetGameclass_memberty")
	public java.lang.Double getMemberty() {
		return memberty;
	}

	public void setMemberty(java.lang.Double memberty) {
		this.memberty = memberty;
	}

	@WhereSQL(sql="membertymode=:BetGameclass_membertymode")
	public java.lang.Integer getMembertymode() {
		return membertymode;
	}

	public void setMembertymode(java.lang.Integer membertymode) {
		this.membertymode = membertymode;
	}

	@Transient
	public java.lang.Double getMembertotalty() {
		return membertotalty;
	}

	public void setMembertotalty(java.lang.Double membertotalty) {
		this.membertotalty = membertotalty;
	}

	@WhereSQL(sql="mobilegamerules=:BetGameclass_mobilegamerules")
	public java.lang.String getMobilegamerules() {
		return mobilegamerules;
	}

	public void setMobilegamerules(java.lang.String mobilegamerules) {
		this.mobilegamerules = mobilegamerules;
	}

	@Transient
	public java.lang.Double getTotalty() {
		return totalty;
	}

	public void setTotalty(java.lang.Double totalty) {
		this.totalty = totalty;
	}

	@WhereSQL(sql="tymode=:BetGameclass_tymode")
	public java.lang.Integer getTymode() {
		return tymode;
	}

	public void setTymode(java.lang.Integer tymode) {
		this.tymode = tymode;
	}

	@WhereSQL(sql="imgable=:BetGameclass_imgable")
	public java.lang.String getImgable() {
		return imgable;
	}

	public void setImgable(java.lang.String imgable) {
		this.imgable = imgable;
	}
	@WhereSQL(sql="imgdisabled=:BetGameclass_imgdisabled")
	public java.lang.String getImgdisabled() {
		return imgdisabled;
	}

	public void setImgdisabled(java.lang.String imgdisabled) {
		this.imgdisabled = imgdisabled;
	}

	@WhereSQL(sql="specialrules=:BetGameclass_specialrules")
	public java.lang.String getSpecialrules() {
		return specialrules;
	}

	public void setSpecialrules(java.lang.String specialrules) {
		this.specialrules = specialrules;
	}

	//columns END 数据库字段结束
	private java.lang.String ptname;
	@Transient
	public java.lang.String getPtname() {
		return ptname;
	}

	public void setPtname(java.lang.String ptname) {
		this.ptname = ptname;
	}
	//concstructor

	

	public BetGameclass(){
	}

	public BetGameclass(
		java.lang.Integer gameclassid
	){
		this.gameclassid = gameclassid;
	}

	//get and set
	public void setGameclassid(java.lang.Integer value) {
		this.gameclassid = value;
	}
	
	@Id
     @WhereSQL(sql="gameclassid=:BetGameclass_gameclassid")
	public java.lang.Integer getGameclassid() {
		return this.gameclassid;
	}
	public void setGcname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gcname = value;
	}
	
     @WhereSQL(sql="gcname=:BetGameclass_gcname")
	public java.lang.String getGcname() {
		return this.gcname;
	}
	public void setGename(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gename = value;
	}
	
     @WhereSQL(sql="gename=:BetGameclass_gename")
	public java.lang.String getGename() {
		return this.gename;
	}
	public void setGsname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gsname = value;
	}
	
     @WhereSQL(sql="gsname=:BetGameclass_gsname")
	public java.lang.String getGsname() {
		return this.gsname;
	}
	public void setGrank(java.lang.Integer value) {
		this.grank = value;
	}
	
     @WhereSQL(sql="grank=:BetGameclass_grank")
	public java.lang.Integer getGrank() {
		return this.grank;
	}
	public void setGlock(java.lang.Integer value) {
		this.glock = value;
	}
	
     @WhereSQL(sql="glock=:BetGameclass_glock")
	public java.lang.Integer getGlock() {
		return this.glock;
	}
	public void setGkind(java.lang.Integer value) {
		this.gkind = value;
	}
	
     @WhereSQL(sql="gkind=:BetGameclass_gkind")
	public java.lang.Integer getGkind() {
		return this.gkind;
	}
	public void setPt(java.lang.Integer value) {
		this.pt = value;
	}
	
     @WhereSQL(sql="pt=:BetGameclass_pt")
	public java.lang.Integer getPt() {
		return this.pt;
	}
	public void setNlen(java.lang.Integer value) {
		this.nlen = value;
	}
	
     @WhereSQL(sql="nlen=:BetGameclass_nlen")
	public java.lang.Integer getNlen() {
		return this.nlen;
	}
	public void setNmin(java.lang.Integer value) {
		this.nmin = value;
	}
	
     @WhereSQL(sql="nmin=:BetGameclass_nmin")
	public java.lang.Integer getNmin() {
		return this.nmin;
	}
	public void setNmax(java.lang.Integer value) {
		this.nmax = value;
	}
	
     @WhereSQL(sql="nmax=:BetGameclass_nmax")
	public java.lang.Integer getNmax() {
		return this.nmax;
	}
	public void setGamerules(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gamerules = value;
	}
	
     @WhereSQL(sql="gamerules=:BetGameclass_gamerules")
	public java.lang.String getGamerules() {
		return this.gamerules;
	}
	public void setGametxt(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gametxt = value;
	}
	
     @WhereSQL(sql="gametxt=:BetGameclass_gametxt")
	public java.lang.String getGametxt() {
		return this.gametxt;
	}
	public void setGstockid(java.lang.Integer value) {
		this.gstockid = value;
	}
	
     @WhereSQL(sql="gstockid=:BetGameclass_gstockid")
	public java.lang.Integer getGstockid() {
		return this.gstockid;
	}
	public void setGametype(java.lang.Integer value) {
		this.gametype = value;
	}
	
     @WhereSQL(sql="gametype=:BetGameclass_gametype")
	public java.lang.Integer getGametype() {
		return this.gametype;
	}
	public void setSubmenu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.submenu = value;
	}
	
     @WhereSQL(sql="submenu=:BetGameclass_submenu")
	public java.lang.String getSubmenu() {
		return this.submenu;
	}
	public void setMaxlost(java.lang.Integer value) {
		this.maxlost = value;
	}
	
     @WhereSQL(sql="maxlost=:BetGameclass_maxlost")
	public java.lang.Integer getMaxlost() {
		return this.maxlost;
	}
	public void setRatelost(java.lang.Integer value) {
		this.ratelost = value;
	}
	
     @WhereSQL(sql="ratelost=:BetGameclass_ratelost")
	public java.lang.Integer getRatelost() {
		return this.ratelost;
	}
	public void setRatewin(java.lang.Integer value) {
		this.ratewin = value;
	}
	
     @WhereSQL(sql="ratewin=:BetGameclass_ratewin")
	public java.lang.Integer getRatewin() {
		return this.ratewin;
	}
	public void setMclass(java.lang.Integer value) {
		this.mclass = value;
	}
	
     @WhereSQL(sql="mclass=:BetGameclass_mclass")
	public java.lang.Integer getMclass() {
		return this.mclass;
	}
	public void setMlock(java.lang.Integer value) {
		this.mlock = value;
	}
	
     @WhereSQL(sql="mlock=:BetGameclass_mlock")
	public java.lang.Integer getMlock() {
		return this.mlock;
	}
	public void setMsh(java.lang.Integer value) {
		this.msh = value;
	}
	
     @WhereSQL(sql="msh=:BetGameclass_msh")
	public java.lang.Integer getMsh() {
		return this.msh;
	}
	public void setMsm(java.lang.Integer value) {
		this.msm = value;
	}
	
     @WhereSQL(sql="msm=:BetGameclass_msm")
	public java.lang.Integer getMsm() {
		return this.msm;
	}
	public void setMss(java.lang.Integer value) {
		this.mss = value;
	}
	
     @WhereSQL(sql="mss=:BetGameclass_mss")
	public java.lang.Integer getMss() {
		return this.mss;
	}
	public void setMeh(java.lang.Integer value) {
		this.meh = value;
	}
	
     @WhereSQL(sql="meh=:BetGameclass_meh")
	public java.lang.Integer getMeh() {
		return this.meh;
	}
	public void setMem(java.lang.Integer value) {
		this.mem = value;
	}
	
     @WhereSQL(sql="mem=:BetGameclass_mem")
	public java.lang.Integer getMem() {
		return this.mem;
	}
	public void setMes(java.lang.Integer value) {
		this.mes = value;
	}
	
     @WhereSQL(sql="mes=:BetGameclass_mes")
	public java.lang.Integer getMes() {
		return this.mes;
	}
	public void setMstep(java.lang.Integer value) {
		this.mstep = value;
	}
	
     @WhereSQL(sql="mstep=:BetGameclass_mstep")
	public java.lang.Integer getMstep() {
		return this.mstep;
	}
	public void setMnums(java.lang.Integer value) {
		this.mnums = value;
	}
	
     @WhereSQL(sql="mnums=:BetGameclass_mnums")
	public java.lang.Integer getMnums() {
		return this.mnums;
	}
	public void setMopen(java.lang.Integer value) {
		this.mopen = value;
	}
	
     @WhereSQL(sql="mopen=:BetGameclass_mopen")
	public java.lang.Integer getMopen() {
		return this.mopen;
	}
	public void setMstop(java.lang.Integer value) {
		this.mstop = value;
	}
	
     @WhereSQL(sql="mstop=:BetGameclass_mstop")
	public java.lang.Integer getMstop() {
		return this.mstop;
	}
	public void setMfine(java.lang.Integer value) {
		this.mfine = value;
	}
	
     @WhereSQL(sql="mfine=:BetGameclass_mfine")
	public java.lang.Integer getMfine() {
		return this.mfine;
	}
	public void setMsnum(java.lang.Integer value) {
		this.msnum = value;
	}
	
     @WhereSQL(sql="msnum=:BetGameclass_msnum")
	public java.lang.Integer getMsnum() {
		return this.msnum;
	}
	public void setMstime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mstime = value;
	}
	
     @WhereSQL(sql="mstime=:BetGameclass_mstime")
	public java.lang.String getMstime() {
		return this.mstime;
	}
	public void setMsurl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.msurl = value;
	}
	
     @WhereSQL(sql="msurl=:BetGameclass_msurl")
	public java.lang.String getMsurl() {
		return this.msurl;
	}
	public void setBettingceiling(java.lang.Double value) {
		this.bettingceiling = value;
	}
	
     @WhereSQL(sql="bettingceiling=:BetGameclass_bettingceiling")
	public java.lang.Double getBettingceiling() {
		return this.bettingceiling;
	}
	public void setBettinglimit(java.lang.Double value) {
		this.bettinglimit = value;
	}
	
     @WhereSQL(sql="bettinglimit=:BetGameclass_bettinglimit")
	public java.lang.Double getBettinglimit() {
		return this.bettinglimit;
	}
	public void setTy(java.lang.Double value) {
		this.ty = value;
	}
	
     @WhereSQL(sql="ty=:BetGameclass_ty")
	public java.lang.Double getTy() {
		return this.ty;
	}
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
     @WhereSQL(sql="state=:BetGameclass_state")
	public java.lang.Integer getState() {
		return this.state;
	}
	public void setCurrenttickets(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.currenttickets = value;
	}
	
     @WhereSQL(sql="currenttickets=:BetGameclass_currenttickets")
	public java.lang.String getCurrenttickets() {
		return this.currenttickets;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
			.append("游戏ID[").append(getGameclassid()).append("],")
			.append("游戏中文名[").append(getGcname()).append("],")
			.append("游戏英文名[").append(getGename()).append("],")
			.append("简称[").append(getGsname()).append("],")
			.append("排序状态[").append(getGrank()).append("],")
			.append("状态[").append(getGlock()).append("],")
			.append("1一般,2时时,3快开[").append(getGkind()).append("],")
			.append("性质分类[").append(getPt()).append("],")
			.append("号码数量[").append(getNlen()).append("],")
			.append("最小号码[").append(getNmin()).append("],")
			.append("最大号码[").append(getNmax()).append("],")
			.append("游戏规则[").append(getGamerules()).append("],")
			.append("gametxt[").append(getGametxt()).append("],")
			.append("股份类型[").append(getGstockid()).append("],")
			.append("gametype[").append(getGametype()).append("],")
			.append("投注子菜单[").append(getSubmenu()).append("],")
			.append("maxlost[").append(getMaxlost()).append("],")
			.append("ratelost[").append(getRatelost()).append("],")
			.append("ratewin[").append(getRatewin()).append("],")
			.append("mclass[").append(getMclass()).append("],")
			.append("mlock[").append(getMlock()).append("],")
			.append("开始时[").append(getMsh()).append("],")
			.append("开始分[").append(getMsm()).append("],")
			.append("开始秒[").append(getMss()).append("],")
			.append("结束时[").append(getMeh()).append("],")
			.append("结束分[").append(getMem()).append("],")
			.append("结束秒[").append(getMes()).append("],")
			.append("每期时间[").append(getMstep()).append("],")
			.append("每天期数[").append(getMnums()).append("],")
			.append("开奖/秒[").append(getMopen()).append("],")
			.append("截止/秒[").append(getMstop()).append("],")
			.append("开奖时间微调[").append(getMfine()).append("],")
			.append("首期编码[").append(getMsnum()).append("],")
			.append("首期时间[").append(getMstime()).append("],")
			.append("网址[").append(getMsurl()).append("],")
			.append("投注上限[").append(getBettingceiling()).append("],")
			.append("投注下限[").append(getBettinglimit()).append("],")
			.append("退佣[").append(getTy()).append("],")
			.append("状态[").append(getState()).append("],")
			.append("本期开奖[").append(getCurrenttickets()).append("],")
			.toString();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGameclassid())
			.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BetGameclass == false) return false;
		if(this == obj) return true;
		BetGameclass other = (BetGameclass)obj;
		return new EqualsBuilder()
			.append(getGameclassid(),other.getGameclassid())
			.isEquals();
	}
}

	
