package org.springrain.lottery.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-17 17:05:08
 * @see org.springrain.lottery.entity.SoccerLeagueTeam
 */
@Table(name="soccer_league_team")
public class SoccerLeagueTeam  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "SoccerLeagueTeam";
	public static final String ALIAS_ID = "球队表id";
	public static final String ALIAS_ID2 = "球队id2(来自500)";
	public static final String ALIAS_NAME = "球队名";
	public static final String ALIAS_ESTABLISHTIME = "成立时间";
	public static final String ALIAS_AREA = "国家地区";
	public static final String ALIAS_CITY = "所在城市";
	public static final String ALIAS_DIAMOND = "球场";
	public static final String ALIAS_PRICE = "球队身价";
	public static final String ALIAS_STATE = "1正常,3删除";
    */
	//date formats
	
	//columns START
	/**
	 * 球队表id
	 */
	private java.lang.Integer id;
	/**
	 * 球队id2(来自500)
	 */
	private java.lang.String id2;
	/**
	 * 球队名
	 */
	private java.lang.String name;
	/**
	 * 球队全称
	 */
	private java.lang.String longname;
	
	/**
	 * 成立时间
	 */
	private java.lang.String establishtime;
	/**
	 * 国家地区
	 */
	private java.lang.String area;
	/**
	 * 所在城市
	 */
	private java.lang.String city;
	/**
	 * 球场
	 */
	private java.lang.String diamond;
	/**
	 * 球队身价
	 */
	private java.lang.String price;
	/**
	 * 1正常,3删除
	 */
	private java.lang.Integer state;
	/**
	 * 近十场胜
	 */
	private java.lang.String win;
	/**
	 * 近十场平
	 */
	private java.lang.String flat;
	/**
	 * 近十场负
	 */
	private java.lang.String lose;
	/**
	 * 进球数
	 */
	private java.lang.String goal;
	/**
	 * 失球数
	 */
	private java.lang.String fault;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 排名
	 */
	private String ranknum;
	private String othername;
	//columns END 数据库字段结束
	
	//concstructor

	public SoccerLeagueTeam(){
	}

	public SoccerLeagueTeam(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
		/**
		 * 球队表id
		 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	/**
	 * 球队表id
	 */
	@Id
     @WhereSQL(sql="id=:SoccerLeagueTeam_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/**
		 * 球队id2(来自500)
		 */
	public void setId2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id2 = value;
	}
	
	
	
	/**
	 * 球队id2(来自500)
	 */
     @WhereSQL(sql="id2=:SoccerLeagueTeam_id2")
	public java.lang.String getId2() {
		return this.id2;
	}
		/**
		 * 球队名
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 球队名
	 */
     @WhereSQL(sql="name=:SoccerLeagueTeam_name")
	public java.lang.String getName() {
		return this.name;
	}
     
    @WhereSQL(sql="longname=:SoccerLeagueTeam_longname")
    public java.lang.String getLongname() {
 		return longname;
 	}

 	public void setLongname(java.lang.String longname) {
 		this.longname = longname;
 	} 
     /**
		 * 成立时间
		 */
	public void setEstablishtime(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.establishtime = value;
	}
	
	
	
	/**
	 * 成立时间
	 */
     @WhereSQL(sql="establishtime=:SoccerLeagueTeam_establishtime")
	public java.lang.String getEstablishtime() {
		return this.establishtime;
	}
		/**
		 * 国家地区
		 */
	public void setArea(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.area = value;
	}
	
	
	
	/**
	 * 国家地区
	 */
     @WhereSQL(sql="area=:SoccerLeagueTeam_area")
	public java.lang.String getArea() {
		return this.area;
	}
		/**
		 * 所在城市
		 */
	public void setCity(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.city = value;
	}
	
	
	
	/**
	 * 所在城市
	 */
     @WhereSQL(sql="city=:SoccerLeagueTeam_city")
	public java.lang.String getCity() {
		return this.city;
	}
		/**
		 * 球场
		 */
	public void setDiamond(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.diamond = value;
	}
	
	
	
	/**
	 * 球场
	 */
     @WhereSQL(sql="diamond=:SoccerLeagueTeam_diamond")
	public java.lang.String getDiamond() {
		return this.diamond;
	}
		/**
		 * 球队身价
		 */
	public void setPrice(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.price = value;
	}
	
	
	
	/**
	 * 球队身价
	 */
     @WhereSQL(sql="price=:SoccerLeagueTeam_price")
	public java.lang.String getPrice() {
		return this.price;
	}
		/**
		 * 1正常,3删除
		 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}
	
	/**
	 * 1正常,3删除
	 */
     @WhereSQL(sql="state=:SoccerLeagueTeam_state")
	public java.lang.Integer getState() {
		return this.state;
	}
     
     @WhereSQL(sql="win=:SoccerLeagueTeam_win")
	public java.lang.String getWin() {
		return win;
	}

	public void setWin(java.lang.String win) {
		this.win = win;
	}

	@WhereSQL(sql="flat=:SoccerLeagueTeam_flat")
	public java.lang.String getFlat() {
		return flat;
	}

	public void setFlat(java.lang.String flat) {
		this.flat = flat;
	}

	@WhereSQL(sql="lose=:SoccerLeagueTeam_lose")
	public java.lang.String getLose() {
		return lose;
	}

	public void setLose(java.lang.String lose) {
		this.lose = lose;
	}
	
	@WhereSQL(sql="goal=:SoccerLeagueTeam_goal")
	public java.lang.String getGoal() {
		return goal;
	}

	public void setGoal(java.lang.String goal) {
		this.goal = goal;
	}
	
	@WhereSQL(sql="fault=:SoccerLeagueTeam_fault")
	public java.lang.String getFault() {
		return fault;
	}

	public void setFault(java.lang.String fault) {
		this.fault = fault;
	}
	@WhereSQL(sql="img=:SoccerLeagueTeam_img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@WhereSQL(sql="ranknum=:SoccerLeagueTeam_ranknum")
	public String getRanknum() {
		return ranknum;
	}

	public void setRanknum(String ranknum) {
		this.ranknum = ranknum;
	}
     @WhereSQL(sql="othername=:SoccerLeagueTeam_othername")
	public java.lang.String getOthername() {
		return this.othername;
	}
	public void setOthername(java.lang.String value) {
		this.othername = value;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("球队表id[").append(getId()).append("],")
			.append("球队id2(来自500)[").append(getId2()).append("],")
			.append("球队名[").append(getName()).append("],")
			.append("成立时间[").append(getEstablishtime()).append("],")
			.append("国家地区[").append(getArea()).append("],")
			.append("所在城市[").append(getCity()).append("],")
			.append("球场[").append(getDiamond()).append("],")
			.append("球队身价[").append(getPrice()).append("],")
			.append("1正常,3删除[").append(getState()).append("],")
			.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((diamond == null) ? 0 : diamond.hashCode());
		result = prime * result
				+ ((establishtime == null) ? 0 : establishtime.hashCode());
		result = prime * result + ((flat == null) ? 0 : flat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id2 == null) ? 0 : id2.hashCode());
		result = prime * result
				+ ((longname == null) ? 0 : longname.hashCode());
		result = prime * result + ((lose == null) ? 0 : lose.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((win == null) ? 0 : win.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoccerLeagueTeam other = (SoccerLeagueTeam) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (diamond == null) {
			if (other.diamond != null)
				return false;
		} else if (!diamond.equals(other.diamond))
			return false;
		if (establishtime == null) {
			if (other.establishtime != null)
				return false;
		} else if (!establishtime.equals(other.establishtime))
			return false;
		if (flat == null) {
			if (other.flat != null)
				return false;
		} else if (!flat.equals(other.flat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id2 == null) {
			if (other.id2 != null)
				return false;
		} else if (!id2.equals(other.id2))
			return false;
		if (longname == null) {
			if (other.longname != null)
				return false;
		} else if (!longname.equals(other.longname))
			return false;
		if (lose == null) {
			if (other.lose != null)
				return false;
		} else if (!lose.equals(other.lose))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (win == null) {
			if (other.win != null)
				return false;
		} else if (!win.equals(other.win))
			return false;
		return true;
	}

	

	
	
}

	
