package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 中国竞彩网上的开奖结果
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2018-02-24 10:48:11
 * @see org.springrain.lottery.entity.LotteryPhaseDlt
 */
@Table(name="lottery_phase_dlt")
public class LotteryPhaseDlt  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "大乐透开奖结果";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_LOTTERYTYPE = "彩票类型";
	public static final String ALIAS_PHASENO = "期号";
	public static final String ALIAS_WINCODE = "开奖号码";
	public static final String ALIAS_WIN1 = "一等奖基本奖金";
	public static final String ALIAS_WIN2 = "二等奖基本奖金";
	public static final String ALIAS_WIN3 = "三等奖基本奖金";
	public static final String ALIAS_WIN4 = "四等奖基本奖金";
	public static final String ALIAS_WIN5 = "五等奖基本奖金";
	public static final String ALIAS_WIN6 = "六等奖基本奖金";
	public static final String ALIAS_ZWIN1 = "一等奖追加奖金";
	public static final String ALIAS_ZWIN2 = "二等奖追加奖金";
	public static final String ALIAS_ZWIN3 = "三等奖追加奖金";
	public static final String ALIAS_ZWIN4 = "四等奖追加奖金";
	public static final String ALIAS_ZWIN5 = "五等奖追加奖金";
	public static final String ALIAS_ZWIN6 = "六等奖追加奖金";
	public static final String ALIAS_WIN1P = "一等奖基本派奖奖金";
	public static final String ALIAS_WIN2P = "二等奖基本派奖奖金";
	public static final String ALIAS_WIN3P = "三等奖基本派奖奖金";
	public static final String ALIAS_WIN4P = "四等奖基本派奖奖金";
	public static final String ALIAS_WIN5P = "五等奖基本派奖奖金";
	public static final String ALIAS_WIN6P = "六等奖基本派奖奖金";
	public static final String ALIAS_ZWIN1P = "一等奖追加派奖奖金";
	public static final String ALIAS_ZWIN2P = "二等奖追加派奖奖金";
	public static final String ALIAS_ZWIN3P = "三等奖追加派奖奖金";
	public static final String ALIAS_ZWIN4P = "四等奖追加派奖奖金";
	public static final String ALIAS_ZWIN5P = "五等奖追加派奖奖金";
	public static final String ALIAS_ZWIN6P = "六等奖追加派奖奖金";
	public static final String ALIAS_WIN1COUNT = "一等奖基本投注注数";
	public static final String ALIAS_WIN2COUNT = "二等奖基本投注注数";
	public static final String ALIAS_WIN3COUNT = "三等奖基本投注注数";
	public static final String ALIAS_WIN4COUNT = "四等奖基本投注注数";
	public static final String ALIAS_WIN5COUNT = "五等奖基本投注注数";
	public static final String ALIAS_WIN6COUNT = "六等奖基本投注注数";
	public static final String ALIAS_ZWIN1COUNT = "一等奖追加投注注数";
	public static final String ALIAS_ZWIN2COUNT = "二等奖追加投注注数";
	public static final String ALIAS_ZWIN3COUNT = "三等奖追加投注注数";
	public static final String ALIAS_ZWIN4COUNT = "四等奖追加投注注数";
	public static final String ALIAS_ZWIN5COUNT = "五等奖追加投注注数";
	public static final String ALIAS_ZWIN6COUNT = "六等奖追加投注注数";
	public static final String ALIAS_WIN1PCOUNT = "一等奖基本派奖投注注数";
	public static final String ALIAS_WIN2PCOUNT = "二等奖基本派奖投注注数";
	public static final String ALIAS_WIN3PCOUNT = "三等奖基本派奖投注注数";
	public static final String ALIAS_WIN4PCOUNT = "四等奖基本派奖投注注数";
	public static final String ALIAS_WIN5PCOUNT = "五等奖基本派奖投注注数";
	public static final String ALIAS_WIN6PCOUNT = "六等奖基本派奖投注注数";
	public static final String ALIAS_ZWIN1PCOUNT = "一等奖追加派奖投注注数";
	public static final String ALIAS_ZWIN2PCOUNT = "二等奖追加派奖投注注数";
	public static final String ALIAS_ZWIN3PCOUNT = "三等奖追加派奖投注注数";
	public static final String ALIAS_ZWIN4PCOUNT = "四等奖追加派奖投注注数";
	public static final String ALIAS_ZWIN5PCOUNT = "五等奖追加派奖投注注数";
	public static final String ALIAS_ZWIN6PCOUNT = "六等奖追加派奖投注注数";
	
	public static final String ALIAS_POOLAMOUNT = "奖池";
	public static final String ALIAS_ADDPOOLAMOUNT = "加奖奖池";
	public static final String ALIAS_SALEAMOUNT = "销售额";
	public static final String ALIAS_ENDTIME = "官方兑奖截止时间";
	public static final String ALIAS_STARTTIME = "官方开奖时间";
	public static final String ALIAS_LOCALENDTIME = "平台截止时间";
	public static final String ALIAS_ISTRUE = "是否修正（0否1是）";
    */
	//date formats
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_STARTTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_LOCALENDTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 彩票类型
	 */
	private java.lang.String lotterytype;
	/**
	 * 期号
	 */
	private java.lang.String phaseno;
	/**
	 * 开奖号码
	 */
	private java.lang.String wincode;
	/**
	 * 一等奖基本奖金
	 */
	private java.math.BigDecimal win1;
	/**
	 * 二等奖基本奖金
	 */
	private java.math.BigDecimal win2;
	/**
	 * 三等奖基本奖金
	 */
	private java.math.BigDecimal win3;
	/**
	 * 四等奖基本奖金
	 */
	private java.math.BigDecimal win4;
	/**
	 * 五等奖基本奖金
	 */
	private java.math.BigDecimal win5;
	/**
	 * 六等奖基本奖金
	 */
	private java.math.BigDecimal win6;
	/**
	 * 一等奖追加奖金
	 */
	private java.math.BigDecimal zwin1;
	/**
	 * 二等奖追加奖金
	 */
	private java.math.BigDecimal zwin2;
	/**
	 * 三等奖追加奖金
	 */
	private java.math.BigDecimal zwin3;
	/**
	 * 四等奖追加奖金
	 */
	private java.math.BigDecimal zwin4;
	/**
	 * 五等奖追加奖金
	 */
	private java.math.BigDecimal zwin5;
	/**
	 * 六等奖追加奖金
	 */
	private java.math.BigDecimal zwin6;
	/**
	 * 一等奖基本派奖奖金
	 */
	private java.math.BigDecimal win1p;
	/**
	 * 二等奖基本派奖奖金
	 */
	private java.math.BigDecimal win2p;
	/**
	 * 三等奖基本派奖奖金
	 */
	private java.math.BigDecimal win3p;
	/**
	 * 四等奖基本派奖奖金
	 */
	private java.math.BigDecimal win4p;
	/**
	 * 五等奖基本派奖奖金
	 */
	private java.math.BigDecimal win5p;
	/**
	 * 六等奖基本派奖奖金
	 */
	private java.math.BigDecimal win6p;
	/**
	 * 一等奖追加派奖奖金
	 */
	private java.math.BigDecimal zwin1p;
	/**
	 * 二等奖追加派奖奖金
	 */
	private java.math.BigDecimal zwin2p;
	/**
	 * 三等奖追加派奖奖金
	 */
	private java.math.BigDecimal zwin3p;
	/**
	 * 四等奖追加派奖奖金
	 */
	private java.math.BigDecimal zwin4p;
	/**
	 * 五等奖追加派奖奖金
	 */
	private java.math.BigDecimal zwin5p;
	/**
	 * 六等奖追加派奖奖金
	 */
	private java.math.BigDecimal zwin6p;
	/**
	 * 一等奖基本投注注数
	 */
	private java.math.BigDecimal win1count;
	/**
	 * 二等奖基本投注注数
	 */
	private java.math.BigDecimal win2count;
	/**
	 * 三等奖基本投注注数
	 */
	private java.math.BigDecimal win3count;
	/**
	 * 四等奖基本投注注数
	 */
	private java.math.BigDecimal win4count;
	/**
	 * 五等奖基本投注注数
	 */
	private java.math.BigDecimal win5count;
	/**
	 * 六等奖基本投注注数
	 */
	private java.math.BigDecimal win6count;
	/**
	 * 一等奖追加投注注数
	 */
	private java.math.BigDecimal zwin1count;
	/**
	 * 二等奖追加投注注数
	 */
	private java.math.BigDecimal zwin2count;
	/**
	 * 三等奖追加投注注数
	 */
	private java.math.BigDecimal zwin3count;
	/**
	 * 四等奖追加投注注数
	 */
	private java.math.BigDecimal zwin4count;
	/**
	 * 五等奖追加投注注数
	 */
	private java.math.BigDecimal zwin5count;
	/**
	 * 六等奖追加投注注数
	 */
	private java.math.BigDecimal zwin6count;
	/**
	 * 一等奖基本派奖投注注数
	 */
	private java.math.BigDecimal win1pcount;
	/**
	 * 二等奖基本派奖投注注数
	 */
	private java.math.BigDecimal win2pcount;
	/**
	 * 三等奖基本派奖投注注数
	 */
	private java.math.BigDecimal win3pcount;
	/**
	 * 四等奖基本派奖投注注数
	 */
	private java.math.BigDecimal win4pcount;
	/**
	 * 五等奖基本派奖投注注数
	 */
	private java.math.BigDecimal win5pcount;
	/**
	 * 六等奖基本派奖投注注数
	 */
	private java.math.BigDecimal win6pcount;
	/**
	 * 一等奖追加派奖投注注数
	 */
	private java.math.BigDecimal zwin1pcount;
	/**
	 * 二等奖追加派奖投注注数
	 */
	private java.math.BigDecimal zwin2pcount;
	/**
	 * 三等奖追加派奖投注注数
	 */
	private java.math.BigDecimal zwin3pcount;
	/**
	 * 四等奖追加派奖投注注数
	 */
	private java.math.BigDecimal zwin4pcount;
	/**
	 * 五等奖追加派奖投注注数
	 */
	private java.math.BigDecimal zwin5pcount;
	/**
	 * 六等奖追加派奖投注注数
	 */
	private java.math.BigDecimal zwin6pcount;
	/**
	 * 奖池
	 */
	private java.math.BigDecimal poolamount;
	/**
	 * 加奖奖池
	 */
	private java.math.BigDecimal addpoolamount;
	/**
	 * 销售额
	 */
	private java.math.BigDecimal saleamount;
	/**
	 * 官方兑奖截止时间
	 */
	private java.util.Date endtime;
	/**
	 * 官方开奖时间
	 */
	private java.util.Date starttime;
	/**
	 * 平台截止时间
	 */
	private java.util.Date localendtime;
	/**
	 * 是否修正（0否1是）
	 */
	private java.lang.Integer istrue;
	//columns END 数据库字段结束

	
	//concstructor

	public LotteryPhaseDlt(){
	}

	public LotteryPhaseDlt(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:LotteryPhaseDlt_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setLotterytype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.lotterytype = value;
	}
	
     @WhereSQL(sql="lotterytype=:LotteryPhaseDlt_lotterytype")
	public java.lang.String getLotterytype() {
		return this.lotterytype;
	}
	public void setPhaseno(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phaseno = value;
	}
	
     @WhereSQL(sql="phaseno=:LotteryPhaseDlt_phaseno")
	public java.lang.String getPhaseno() {
		return this.phaseno;
	}
	public void setWincode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wincode = value;
	}
	
     @WhereSQL(sql="wincode=:LotteryPhaseDlt_wincode")
	public java.lang.String getWincode() {
		return this.wincode;
	}
	public void setWin1(java.math.BigDecimal value) {
		this.win1 = value;
	}
	
     @WhereSQL(sql="win1=:LotteryPhaseDlt_win1")
	public java.math.BigDecimal getWin1() {
		return this.win1;
	}
	public void setWin2(java.math.BigDecimal value) {
		this.win2 = value;
	}
	
     @WhereSQL(sql="win2=:LotteryPhaseDlt_win2")
	public java.math.BigDecimal getWin2() {
		return this.win2;
	}
	public void setWin3(java.math.BigDecimal value) {
		this.win3 = value;
	}
	
     @WhereSQL(sql="win3=:LotteryPhaseDlt_win3")
	public java.math.BigDecimal getWin3() {
		return this.win3;
	}
     public void setWin4(java.math.BigDecimal value) {
 		this.win4 = value;
 	}
 	
      @WhereSQL(sql="win4=:LotteryPhaseDlt_win4")
 	public java.math.BigDecimal getWin4() {
 		return this.win4;
 	}
      public void setWin5(java.math.BigDecimal value) {
  		this.win5 = value;
  	}
  	
       @WhereSQL(sql="win5=:LotteryPhaseDlt_win5")
  	public java.math.BigDecimal getWin5() {
  		return this.win5;
  	}
       public void setWin6(java.math.BigDecimal value) {
   		this.win6 = value;
   	}
   	
        @WhereSQL(sql="win6=:LotteryPhaseDlt_win6")
   	public java.math.BigDecimal getWin6() {
   		return this.win6;
   	}
	public void setZwin1(java.math.BigDecimal value) {
		this.zwin1 = value;
	}
	
     @WhereSQL(sql="zwin1=:LotteryPhaseDlt_zwin1")
	public java.math.BigDecimal getZwin1() {
		return this.zwin1;
	}
	public void setZwin2(java.math.BigDecimal value) {
		this.zwin2 = value;
	}
	
     @WhereSQL(sql="zwin2=:LotteryPhaseDlt_zwin2")
	public java.math.BigDecimal getZwin2() {
		return this.zwin2;
	}
	public void setZwin3(java.math.BigDecimal value) {
		this.zwin3 = value;
	}
	
     @WhereSQL(sql="zwin3=:LotteryPhaseDlt_zwin3")
	public java.math.BigDecimal getZwin3() {
		return this.zwin3;
	}
     public void setZwin4(java.math.BigDecimal value) {
 		this.zwin4 = value;
 	}
 	
      @WhereSQL(sql="zwin4=:LotteryPhaseDlt_zwin4")
 	public java.math.BigDecimal getZwin4() {
 		return this.zwin4;
 	}
 	public void setZwin5(java.math.BigDecimal value) {
 		this.zwin5 = value;
 	}
 	
      @WhereSQL(sql="zwin5=:LotteryPhaseDlt_zwin5")
 	public java.math.BigDecimal getZwin5() {
 		return this.zwin5;
 	}
 	public void setZwin6(java.math.BigDecimal value) {
 		this.zwin6 = value;
 	}
 	
      @WhereSQL(sql="zwin6=:LotteryPhaseDlt_zwin6")
 	public java.math.BigDecimal getZwin6() {
 		return this.zwin6;
 	}
      public void setWin1p(java.math.BigDecimal value) {
  		this.win1p = value;
  	}
  	
       @WhereSQL(sql="win1p=:LotteryPhaseDlt_win1p")
  	public java.math.BigDecimal getWin1p() {
  		return this.win1p;
  	}
  	public void setWin2p(java.math.BigDecimal value) {
  		this.win2p = value;
  	}
  	
       @WhereSQL(sql="win2p=:LotteryPhaseDlt_win2p")
  	public java.math.BigDecimal getWin2p() {
  		return this.win2p;
  	}
  	public void setWin3p(java.math.BigDecimal value) {
  		this.win3p = value;
  	}
  	
       @WhereSQL(sql="win3p=:LotteryPhaseDlt_win3p")
  	public java.math.BigDecimal getWin3p() {
  		return this.win3p;
  	}
       public void setWin4p(java.math.BigDecimal value) {
   		this.win4p = value;
   	}
   	
        @WhereSQL(sql="win4p=:LotteryPhaseDlt_win4p")
   	public java.math.BigDecimal getWin4p() {
   		return this.win4p;
   	}
    public void setWin5p(java.math.BigDecimal value) {
    	this.win5p = value;
    }
    	
        @WhereSQL(sql="win5p=:LotteryPhaseDlt_win5p")
    public java.math.BigDecimal getWin5p() {
    	return this.win5p;
    }
    public void setWin6p(java.math.BigDecimal value) {
        this.win6p = value;
    }
     	
       @WhereSQL(sql="win6p=:LotteryPhaseDlt_win6p")
    public java.math.BigDecimal getWin6p() {
     	return this.win6p;
    }
  	public void setZwin1p(java.math.BigDecimal value) {
  		this.zwin1p = value;
  	}
  	
       @WhereSQL(sql="zwin1p=:LotteryPhaseDlt_zwin1p")
  	public java.math.BigDecimal getZwin1p() {
  		return this.zwin1p;
  	}
  	public void setZwin2p(java.math.BigDecimal value) {
  		this.zwin2p = value;
  	}
  	
       @WhereSQL(sql="zwin2p=:LotteryPhaseDlt_zwin2p")
  	public java.math.BigDecimal getZwin2p() {
  		return this.zwin2p;
  	}
  	public void setZwin3p(java.math.BigDecimal value) {
  		this.zwin3p = value;
  	}
  	
       @WhereSQL(sql="zwin3p=:LotteryPhaseDlt_zwin3p")
  	public java.math.BigDecimal getZwin3p() {
  		return this.zwin3p;
  	}
       public void setZwin4p(java.math.BigDecimal value) {
   		this.zwin4p = value;
   	}
   	
        @WhereSQL(sql="zwin4p=:LotteryPhaseDlt_zwin4p")
   	public java.math.BigDecimal getZwin4p() {
   		return this.zwin4p;
   	}
   	public void setZwin5p(java.math.BigDecimal value) {
   		this.zwin5p = value;
   	}
   	
        @WhereSQL(sql="zwin5p=:LotteryPhaseDlt_zwin5p")
   	public java.math.BigDecimal getZwin5p() {
   		return this.zwin5p;
   	}
   	public void setZwin6p(java.math.BigDecimal value) {
   		this.zwin6p = value;
   	}
   	
        @WhereSQL(sql="zwin6p=:LotteryPhaseDlt_zwin6p")
   	public java.math.BigDecimal getZwin6p() {
   		return this.zwin6p;
   	}
    public void setWin1count(java.math.BigDecimal value) {
    	this.win1count = value;
    }
    	
        @WhereSQL(sql="win1count=:LotteryPhaseDlt_win1count")
    public java.math.BigDecimal getWin1count() {
    	return this.win1count;
    }
    public void setWin2count(java.math.BigDecimal value) {
    	this.win2count = value;
    }
    	
        @WhereSQL(sql="win2count=:LotteryPhaseDlt_win2count")
    public java.math.BigDecimal getWin2count() {
    	return this.win2count;
    }
    public void setWin3count(java.math.BigDecimal value) {
    	this.win3count = value;
    }
    	
        @WhereSQL(sql="win3count=:LotteryPhaseDlt_win3count")
    public java.math.BigDecimal getWin3count() {
    	return this.win3count;
    }
    public void setWin4count(java.math.BigDecimal value) {
     	this.win4count = value;
     }
     	
        @WhereSQL(sql="win4count=:LotteryPhaseDlt_win4count")
    public java.math.BigDecimal getWin4count() {
     	return this.win4count;
     }
    public void setWin5count(java.math.BigDecimal value) {
      	this.win5count = value;
    }
      	
        @WhereSQL(sql="win5count=:LotteryPhaseDlt_win5count")
    public java.math.BigDecimal getWin5count() {
      	return this.win5count;
    }
    public void setWin6count(java.math.BigDecimal value) {
       	this.win6count = value;
    }
       	
        @WhereSQL(sql="win6count=:LotteryPhaseDlt_win6count")
    public java.math.BigDecimal getWin6count() {
       	return this.win6count;
    }
    public void setZwin1count(java.math.BigDecimal value) {
    	this.zwin1count = value;
    }
    	
        @WhereSQL(sql="zwin1count=:LotteryPhaseDlt_zwin1count")
    public java.math.BigDecimal getZwin1count() {
    	return this.zwin1count;
    }
    public void setZwin2count(java.math.BigDecimal value) {
    	this.zwin2count = value;
    }
    	
        @WhereSQL(sql="zwin2count=:LotteryPhaseDlt_zwin2count")
    public java.math.BigDecimal getZwin2count() {
    	return this.zwin2count;
    }
    public void setZwin3count(java.math.BigDecimal value) {
    	this.zwin3count = value;
    }
    	
    	@WhereSQL(sql="zwin3count=:LotteryPhaseDlt_zwin3count")
    public java.math.BigDecimal getZwin3count() {
    	return this.zwin3count;
    }
    public void setZwin4count(java.math.BigDecimal value) {
     	this.zwin4count = value;
    }
     	
        @WhereSQL(sql="zwin4count=:LotteryPhaseDlt_zwin4count")
    public java.math.BigDecimal getZwin4count() {
     	return this.zwin4count;
    }
    public void setZwin5count(java.math.BigDecimal value) {
     	this.zwin5count = value;
    }
     	
        @WhereSQL(sql="zwin5count=:LotteryPhaseDlt_zwin5count")
    public java.math.BigDecimal getZwin5count() {
     	return this.zwin5count;
    }
    public void setZwin6count(java.math.BigDecimal value) {
     	this.zwin6count = value;
     }
     	
        @WhereSQL(sql="zwin6count=:LotteryPhaseDlt_zwin6count")
    public java.math.BigDecimal getZwin6count() {
     	return this.zwin6count;
    }
    public void setWin1pcount(java.math.BigDecimal value) {
      	this.win1pcount = value;
    }
      	
        @WhereSQL(sql="win1pcount=:LotteryPhaseDlt_win1pcount")
    public java.math.BigDecimal getWin1pcount() {
      	return this.win1pcount;
    }
    public void setWin2pcount(java.math.BigDecimal value) {
      	this.win2pcount = value;
    }
      	
       	@WhereSQL(sql="win2pcount=:LotteryPhaseDlt_win2pcount")
    public java.math.BigDecimal getWin2pcount() {
      	return this.win2pcount;
    }
    public void setWin3pcount(java.math.BigDecimal value) {
      	this.win3pcount = value;
    }
      	
        @WhereSQL(sql="win3pcount=:LotteryPhaseDlt_win3pcount")
    public java.math.BigDecimal getWin3pcount() {
      	return this.win3pcount;
    }
    public void setWin4pcount(java.math.BigDecimal value) {
       	this.win4pcount = value;
    }
       	
        @WhereSQL(sql="win4pcount=:LotteryPhaseDlt_win4pcount")
    public java.math.BigDecimal getWin4pcount() {
       	return this.win4pcount;
    }
    public void setWin5pcount(java.math.BigDecimal value) {
        this.win5pcount = value;
    }
        	
        @WhereSQL(sql="win5pcount=:LotteryPhaseDlt_win5pcount")
    public java.math.BigDecimal getWin5pcount() {
        return this.win5pcount;
    }
    public void setWin6pcount(java.math.BigDecimal value) {
        this.win6pcount = value;
    }
         	
        @WhereSQL(sql="win6pcount=:LotteryPhaseDlt_win6pcount")
    public java.math.BigDecimal getWin6pcount() {
        return this.win6pcount;
    }
    public void setZwin1pcount(java.math.BigDecimal value) {
      	this.zwin1pcount = value;
    }
      	
        @WhereSQL(sql="zwin1pcount=:LotteryPhaseDlt_zwin1pcount")
    public java.math.BigDecimal getZwin1pcount() {
      	return this.zwin1pcount;
    }
    public void setZwin2pcount(java.math.BigDecimal value) {
      	this.zwin2pcount = value;
    }
      	
        @WhereSQL(sql="zwin2pcount=:LotteryPhaseDlt_zwin2pcount")
    public java.math.BigDecimal getZwin2pcount() {
      	return this.zwin2pcount;
    }
    public void setZwin3pcount(java.math.BigDecimal value) {
      	this.zwin3pcount = value;
    }
      	
        @WhereSQL(sql="zwin3pcount=:LotteryPhaseDlt_zwin3pcount")
    public java.math.BigDecimal getZwin3pcount() {
      	return this.zwin3pcount;
    }
    public void setZwin4pcount(java.math.BigDecimal value) {
       	this.zwin4pcount = value;
    }
       	
        @WhereSQL(sql="zwin4pcount=:LotteryPhaseDlt_zwin4pcount")
    public java.math.BigDecimal getZwin4pcount() {
       	return this.zwin4pcount;
    }
    public void setZwin5pcount(java.math.BigDecimal value) {
       	this.zwin5pcount = value;
    }
       	
        @WhereSQL(sql="zwin5pcount=:LotteryPhaseDlt_zwin5pcount")
    public java.math.BigDecimal getZwin5pcount() {
       	return this.zwin5pcount;
    }
    public void setZwin6pcount(java.math.BigDecimal value) {
       	this.zwin6pcount = value;
    }
       	
        @WhereSQL(sql="zwin6pcount=:LotteryPhaseDlt_zwin6pcount")
    public java.math.BigDecimal getZwin6pcount() {
       	return this.zwin6pcount;
    }        
	public void setPoolamount(java.math.BigDecimal value) {
		this.poolamount = value;
	}
	
     @WhereSQL(sql="poolamount=:LotteryPhaseDlt_poolamount")
	public java.math.BigDecimal getPoolamount() {
		return this.poolamount;
	}
	public void setAddpoolamount(java.math.BigDecimal value) {
		this.addpoolamount = value;
	}
	
     @WhereSQL(sql="addpoolamount=:LotteryPhaseDlt_addpoolamount")
	public java.math.BigDecimal getAddpoolamount() {
		return this.addpoolamount;
	}
	public void setSaleamount(java.math.BigDecimal value) {
		this.saleamount = value;
	}
	
     @WhereSQL(sql="saleamount=:LotteryPhaseDlt_saleamount")
	public java.math.BigDecimal getSaleamount() {
		return this.saleamount;
	}
		/*
	public String getendtimeString() {
		return DateUtils.convertDate2String(FORMAT_ENDTIME, getendtime());
	}
	public void setendtimeString(String value) throws ParseException{
		setendtime(DateUtils.convertString2Date(FORMAT_ENDTIME,value));
	}*/
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
     @WhereSQL(sql="endtime=:LotteryPhaseDlt_endtime")
	public java.util.Date getEndtime() {
		return this.endtime;
	}
		/*
	public String getstarttimeString() {
		return DateUtils.convertDate2String(FORMAT_STARTTIME, getstarttime());
	}
	public void setstarttimeString(String value) throws ParseException{
		setstarttime(DateUtils.convertString2Date(FORMAT_STARTTIME,value));
	}*/
	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
     @WhereSQL(sql="starttime=:LotteryPhaseDlt_starttime")
	public java.util.Date getStarttime() {
		return this.starttime;
	}
		/*
	public String getlocalendtimeString() {
		return DateUtils.convertDate2String(FORMAT_LOCALENDTIME, getlocalendtime());
	}
	public void setlocalendtimeString(String value) throws ParseException{
		setlocalendtime(DateUtils.convertString2Date(FORMAT_LOCALENDTIME,value));
	}*/
	
	public void setLocalendtime(java.util.Date value) {
		this.localendtime = value;
	}
	
     @WhereSQL(sql="localendtime=:LotteryPhaseDlt_localendtime")
	public java.util.Date getLocalendtime() {
		return this.localendtime;
	}
	public void setIstrue(java.lang.Integer value) {
		this.istrue = value;
	}
	
     @WhereSQL(sql="istrue=:LotteryPhaseDlt_istrue")
	public java.lang.Integer getIstrue() {
		return this.istrue;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("彩票类型[").append(getLotterytype()).append("],")
			.append("期号[").append(getPhaseno()).append("],")
			.append("开奖号码[").append(getWincode()).append("],")
			.append("一等奖基本奖金[").append(getWin1()).append("],")
			.append("二等奖基本奖金[").append(getWin2()).append("],")
			.append("三等奖基本奖金[").append(getWin3()).append("],")
			.append("四等奖基本奖金[").append(getWin4()).append("],")
			.append("五等奖基本奖金[").append(getWin5()).append("],")
			.append("六等奖基本奖金[").append(getWin6()).append("],")			
			.append("一等奖追加奖金[").append(getZwin1()).append("],")
			.append("二等奖追加奖金[").append(getZwin2()).append("],")
			.append("三等奖追加奖金[").append(getZwin3()).append("],")
			.append("四等奖追加奖金[").append(getZwin4()).append("],")
			.append("五等奖追加奖金[").append(getZwin5()).append("],")
			.append("六等奖追加奖金[").append(getZwin6()).append("],")
			.append("一等奖基本派奖奖金[").append(getWin1p()).append("],")
			.append("二等奖基本派奖奖金[").append(getWin2p()).append("],")
			.append("三等奖基本派奖奖金[").append(getWin3p()).append("],")
			.append("四等奖基本派奖奖金[").append(getWin4p()).append("],")
			.append("五等奖基本派奖奖金[").append(getWin5p()).append("],")
			.append("六等奖基本派奖奖金[").append(getWin6p()).append("],")			
			.append("一等奖追加派奖奖金[").append(getZwin1p()).append("],")
			.append("二等奖追加派奖奖金[").append(getZwin2p()).append("],")
			.append("三等奖追加派奖奖金[").append(getZwin3p()).append("],")
			.append("四等奖追加派奖奖金[").append(getZwin4p()).append("],")
			.append("五等奖追加派奖奖金[").append(getZwin5p()).append("],")
			.append("六等奖追加派奖奖金[").append(getZwin6p()).append("],")
			.append("一等奖基本投注注数[").append(getWin1count()).append("],")
			.append("二等奖基本投注注数[").append(getWin2count()).append("],")
			.append("三等奖基本投注注数[").append(getWin3count()).append("],")
			.append("四等奖基本投注注数[").append(getWin4count()).append("],")
			.append("五等奖基本投注注数[").append(getWin5count()).append("],")
			.append("六等奖基本投注注数[").append(getWin6count()).append("],")			
			.append("一等奖追加投注注数[").append(getZwin1count()).append("],")
			.append("二等奖追加投注注数[").append(getZwin2count()).append("],")
			.append("三等奖追加投注注数[").append(getZwin3count()).append("],")
			.append("四等奖追加投注注数[").append(getZwin4count()).append("],")
			.append("五等奖追加投注注数[").append(getZwin5count()).append("],")
			.append("六等奖追加投注注数[").append(getZwin6count()).append("],")
			.append("一等奖基本派奖投注注数[").append(getWin1pcount()).append("],")
			.append("二等奖基本派奖投注注数[").append(getWin2pcount()).append("],")
			.append("三等奖基本派奖投注注数[").append(getWin3pcount()).append("],")
			.append("四等奖基本派奖投注注数[").append(getWin4pcount()).append("],")
			.append("五等奖基本派奖投注注数[").append(getWin5pcount()).append("],")
			.append("六等奖基本派奖投注注数[").append(getWin6pcount()).append("],")			
			.append("一等奖追加派奖投注注数[").append(getZwin1pcount()).append("],")
			.append("二等奖追加派奖投注注数[").append(getZwin2pcount()).append("],")
			.append("三等奖追加派奖投注注数[").append(getZwin3pcount()).append("],")
			.append("四等奖追加派奖投注注数[").append(getZwin4pcount()).append("],")
			.append("五等奖追加派奖投注注数[").append(getZwin5pcount()).append("],")
			.append("六等奖追加派奖投注注数[").append(getZwin6pcount()).append("],")
			.append("奖池[").append(getPoolamount()).append("],")
			.append("加奖奖池[").append(getAddpoolamount()).append("],")
			.append("销售额[").append(getSaleamount()).append("],")
			.append("官方兑奖截止时间[").append(getEndtime()).append("],")
			.append("官方开奖时间[").append(getStarttime()).append("],")
			.append("平台截止时间[").append(getLocalendtime()).append("],")
			.append("是否修正（0否1是）[").append(getIstrue()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LotteryPhaseDlt == false) return false;
		if(this == obj) return true;
		LotteryPhaseDlt other = (LotteryPhaseDlt)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
