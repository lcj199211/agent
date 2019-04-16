package org.springrain.lottery.entity;

import java.util.ArrayList;

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
 * @version  2017-05-11 15:44:16
 * @see org.springrain.lottery.entity.BetAgent
 */
@Table(name="bet_agent")
public class BetAgent  extends BaseEntity implements Comparable<BetAgent> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 代理ID
	 */
	private java.lang.String id;
	/**
	 * 代理账号
	 */
	private java.lang.String account;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 所属
	 */
	private java.lang.String parentid;
	/**
	 * 登录密码
	 */
	private java.lang.String password;
	/**
	 * 银行密码
	 */
	private java.lang.String bankpassword;
	/**
	 * 联系电话
	 */
	private java.lang.String mobile;
	/**
	 * qq
	 */
	private java.lang.String qq;
	/**
	 * 微信Id
	 */
	private java.lang.String weixin;
	/**
	 * 是否有效(0否,1是)
	 */
	private java.lang.Integer active;
	/**
	 * 金币
	 */
	private java.lang.Double score;
	/**
	 * 转账金额
	 */
	private java.lang.Double transferaccountsscore;
	/**
	 * 退佣
	 */
	private java.lang.Double ty;
	/**
	 * 投注额
	 */
	private java.lang.Double sx;
	/**
	 * 投注已到账退佣
	 */
	private Double ty2;
	/**
	 * 投注未到账退佣
	 */
	private Double ty3;
	/**
	 * 下属
	 */
	private java.lang.Integer subordinate;
	/**
	 * 登录
	 */
	private java.lang.Integer login;
	/**
	 * 充值返利
	 */
	private java.lang.Double rechargerebate;
	/**
	 * 输赢返利
	 */
	private java.lang.Double winorlossrebate;
	/**
	 * 投注流水返利（下单）
	 */
	private java.lang.Double bettingrebate;
	/**
	 * 投注流水返利（跟单）
	 */
	private java.lang.Double bettingrebate2;
	/**
	 * 公司吃成
	 */
	private java.lang.Double companyproportion;
	/**
	 * 注册时间
	 */
	private java.util.Date registrationtime;
	/**
	 * 代理商id 逗号隔开从根节点开始
	 */
	private java.lang.String parentids;
	/**
	 * 代理商ID
	 */
	private java.lang.String agentid;
	private String name;
	/**
	 * 是否允许代理
	 */
	private java.lang.Integer allowagent;
	/**
	 * 是否允许转账
	 */
	private java.lang.Integer transferaccount;
	/**
	 * 是否允许充值
	 */
	private java.lang.Integer recharge;
	/**
	 * 是否允许兑换
	 */
	private java.lang.Integer exchange;
	/**
	 * 下属会员
	 */
	private Integer membernum;
	//columns END 数据库字段结束
	/**
	 * 投注输赢
	 */
	private java.lang.Double bettingwin;
	/**
	 * 是否允许提现（0是1否）
	 */
	private java.lang.Integer iswithdraw;
	/**
	 * 是否机器人（0否1是）
	 */
	private java.lang.Integer isrobot;
	
	
	
	private ArrayList children;
	private java.lang.Double bettingty;
	private java.lang.Double bettingty2;
	private java.lang.String mercnum;
	private java.lang.String privatekey;
	private String company;
	/**
	 * 出票模式（仅一级代理设置），0手动，1系统，2店内
	 */
	private Integer issuemodel;
	/**
	 * 系统出票余额
	 */
	private Double issuebalance;
	
	private Double subordinaterebate;
	
	private Double ownsubordinaterebate;
	
	
	@WhereSQL(sql="ownsubordinaterebate=:BetAgent_ownsubordinaterebate")
    public Double getOwnsubordinaterebate() {
		return ownsubordinaterebate;
	}

	public void setOwnsubordinaterebate(Double ownsubordinaterebate) {
		this.ownsubordinaterebate = ownsubordinaterebate;
	}

	@WhereSQL(sql="subordinaterebate=:BetAgent_subordinaterebate")
	public Double getSubordinaterebate() {
		return subordinaterebate;
	}

	public void setSubordinaterebate(Double subordinaterebate) {
		this.subordinaterebate = subordinaterebate;
	}

	@WhereSQL(sql="company=:BetBetting_company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@WhereSQL(sql="mercnum=:BetBetting_mercnum")
	public java.lang.String getMercnum() {
		return mercnum;
	}

	public void setMercnum(java.lang.String mercnum) {
		this.mercnum = mercnum;
	}
	@WhereSQL(sql="privatekey=:BetBetting_privatekey")
	public java.lang.String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(java.lang.String privatekey) {
		this.privatekey = privatekey;
	}

	@WhereSQL(sql="bettingty=:BetBetting_bettingty")
	public java.lang.Double getBettingty() {
		return bettingty;
	}

	public void setBettingty(java.lang.Double bettingty) {
		this.bettingty = bettingty;
	}
	//concstructor
	
	@Transient
	public java.lang.Double getBettingwin() {
		return bettingwin;
	}

	public void setBettingwin(java.lang.Double bettingwin) {
		this.bettingwin = bettingwin;
	}
	
	@Transient
	public ArrayList getChildren() {
		return children;
	}

	

	public void setChildren(ArrayList children) {
		this.children = children;
	}

	public BetAgent(){
	}

	public BetAgent(
		java.lang.String id
	){
		this.id = id;
	}
	@Transient
	public Double getTy3() {
		return ty3;
	}

	public void setTy3(Double ty3) {
		this.ty3 = ty3;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:BetAgent_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setAccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.account = value;
	}
	@WhereSQL(sql="membernum=:BetAgent_membernum")
	public Integer getMembernum() {
		return membernum;
	}

	public void setMembernum(Integer membernum) {
		this.membernum = membernum;
	}

	
     @WhereSQL(sql="account=:BetAgent_account")
	public java.lang.String getAccount() {
		return this.account;
	}
	public void setNickname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.nickname = value;
	}
	
     @WhereSQL(sql="nickname=:BetAgent_nickname")
	public java.lang.String getNickname() {
		return this.nickname;
	}
	public void setParentid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.parentid = value;
	}
	
     @WhereSQL(sql="parentid=:BetAgent_parentid")
	public java.lang.String getParentid() {
		return this.parentid;
	}
	public void setPassword(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.password = value;
	}
	
     @WhereSQL(sql="password=:BetAgent_password")
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setBankpassword(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bankpassword = value;
	}
	
     @WhereSQL(sql="bankpassword=:BetAgent_bankpassword")
	public java.lang.String getBankpassword() {
		return this.bankpassword;
	}
	public void setMobile(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mobile = value;
	}
	
     @WhereSQL(sql="mobile=:BetAgent_mobile")
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setQq(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qq = value;
	}
	
     @WhereSQL(sql="qq=:BetAgent_qq")
	public java.lang.String getQq() {
		return this.qq;
	}
	public void setWeixin(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.weixin = value;
	}
	
     @WhereSQL(sql="weixin=:BetAgent_weixin")
	public java.lang.String getWeixin() {
		return this.weixin;
	}
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
     @WhereSQL(sql="active=:BetAgent_active")
	public java.lang.Integer getActive() {
		return this.active;
	}
	public void setScore(java.lang.Double value) {
		this.score = value;
	}
	
     @WhereSQL(sql="score=:BetAgent_score")
	public java.lang.Double getScore() {
		return this.score;
	}
	public void setTransferaccountsscore(java.lang.Double value) {
		this.transferaccountsscore = value;
	}
	
     @WhereSQL(sql="transferaccountsscore=:BetAgent_transferaccountsscore")
	public java.lang.Double getTransferaccountsscore() {
		return this.transferaccountsscore;
	}
	public void setTy(java.lang.Double value) {
		this.ty = value;
	}
	
     @WhereSQL(sql="ty=:BetAgent_ty")
	public java.lang.Double getTy() {
		return this.ty;
	}
	public void setSx(java.lang.Double value) {
		this.sx = value;
	}
	@WhereSQL(sql="ty2=:BetAgent_ty2")
	public Double getTy2() {
		return ty2;
	}

	public void setTy2(Double ty2) {
		this.ty2 = ty2;
	}
     @WhereSQL(sql="sx=:BetAgent_sx")
	public java.lang.Double getSx() {
		return this.sx;
	}
	public void setSubordinate(java.lang.Integer value) {
		this.subordinate = value;
	}
	
     @WhereSQL(sql="subordinate=:BetAgent_subordinate")
	public java.lang.Integer getSubordinate() {
		return this.subordinate;
	}
	public void setLogin(java.lang.Integer value) {
		this.login = value;
	}
	
     @WhereSQL(sql="login=:BetAgent_login")
	public java.lang.Integer getLogin() {
		return this.login;
	}
	public void setRechargerebate(java.lang.Double value) {
		this.rechargerebate = value;
	}
	
     @WhereSQL(sql="rechargerebate=:BetAgent_rechargerebate")
	public java.lang.Double getRechargerebate() {
		return this.rechargerebate;
	}
	public void setWinorlossrebate(java.lang.Double value) {
		this.winorlossrebate = value;
	}
	
     @WhereSQL(sql="winorlossrebate=:BetAgent_winorlossrebate")
	public java.lang.Double getWinorlossrebate() {
		return this.winorlossrebate;
	}
	public void setBettingrebate(java.lang.Double value) {
		this.bettingrebate = value;
	}
	
     @WhereSQL(sql="bettingrebate=:BetAgent_bettingrebate")
	public java.lang.Double getBettingrebate() {
		return this.bettingrebate;
	}
     //跟单投注退佣
     public void setBettingrebate2(java.lang.Double value) {
 		this.bettingrebate2 = value;
 	}
 	
      @WhereSQL(sql="bettingrebate2=:BetAgent_bettingrebate2")
 	public java.lang.Double getBettingrebate2() {
 		return this.bettingrebate2;
 	}
      //
	public void setCompanyproportion(java.lang.Double value) {
		this.companyproportion = value;
	}
	
     @WhereSQL(sql="companyproportion=:BetAgent_companyproportion")
	public java.lang.Double getCompanyproportion() {
		return this.companyproportion;
	}
		/*
	public String getregistrationtimeString() {
		return DateUtils.convertDate2String(FORMAT_REGISTRATIONTIME, getregistrationtime());
	}
	public void setregistrationtimeString(String value) throws ParseException{
		setregistrationtime(DateUtils.convertString2Date(FORMAT_REGISTRATIONTIME,value));
	}*/
	
	public void setRegistrationtime(java.util.Date value) {
		this.registrationtime = value;
	}
	
     @WhereSQL(sql="registrationtime=:BetAgent_registrationtime")
	public java.util.Date getRegistrationtime() {
		return this.registrationtime;
	}
     
     @WhereSQL(sql="parentids=:BetAgent_parentids")
	public java.lang.String getParentids() {
		return parentids;
	}

	public void setParentids(java.lang.String parentids) {
		this.parentids = parentids;
	}
	 @WhereSQL(sql="agentid=:BetAgent_agentid")
	public java.lang.String getAgentid() {
		return agentid;
	}

	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
		this.name = agentid;
	}
	 @WhereSQL(sql="allowagent=:BetAgent_allowagent")
	public java.lang.Integer getAllowagent() {
		return allowagent;
	}

	public void setAllowagent(java.lang.Integer allowagent) {
		this.allowagent = allowagent;
	}
	 @WhereSQL(sql="transferaccount=:BetAgent_transferaccount")
	public java.lang.Integer getTransferaccount() {
		return transferaccount;
	}

	public void setTransferaccount(java.lang.Integer transferaccount) {
		this.transferaccount = transferaccount;
	}
	 @WhereSQL(sql="recharge=:BetAgent_recharge")
	public java.lang.Integer getRecharge() {
		return recharge;
	}

	public void setRecharge(java.lang.Integer recharge) {
		this.recharge = recharge;
	}
	 @WhereSQL(sql="exchange=:BetAgent_exchange")
	public java.lang.Integer getExchange() {
		return exchange;
	}

	public void setExchange(java.lang.Integer exchange) {
		this.exchange = exchange;
	}

	public Integer getIssuemodel() {
		return issuemodel;
	}

	public void setIssuemodel(Integer issuemodel) {
		this.issuemodel = issuemodel;
	}

	public Double getIssuebalance() {
		return issuebalance;
	}

	public void setIssuebalance(Double issuebalance) {
		this.issuebalance = issuebalance;
	}

	@WhereSQL(sql="iswithdraw=:BetAgent_iswithdraw")
	public java.lang.Integer getIswithdraw() {
		return iswithdraw;
	}

	public void setIswithdraw(java.lang.Integer iswithdraw) {
		this.iswithdraw = iswithdraw;
	}
     
	@Override
	public String toString() {
		return new StringBuffer()
			.append("代理ID[").append(getId()).append("],")
			.append("代理账号[").append(getAccount()).append("],")
			.append("昵称[").append(getNickname()).append("],")
			.append("所属[").append(getParentid()).append("],")
			.append("登录密码[").append(getPassword()).append("],")
			.append("银行密码[").append(getBankpassword()).append("],")
			.append("联系电话[").append(getMobile()).append("],")
			.append("qq[").append(getQq()).append("],")
			.append("微信Id[").append(getWeixin()).append("],")
			.append("是否有效(0否,1是)[").append(getActive()).append("],")
			.append("金币[").append(getScore()).append("],")
			.append("转账金额[").append(getTransferaccountsscore()).append("],")
			.append("退佣[").append(getTy()).append("],")
			.append("授信[").append(getSx()).append("],")
			.append("下属[").append(getSubordinate()).append("],")
			.append("登录[").append(getLogin()).append("],")
			.append("充值返利[").append(getRechargerebate()).append("],")
			.append("输赢返利[").append(getWinorlossrebate()).append("],")
			.append("投注流水返利[").append(getBettingrebate()).append("],")
			.append("公司吃成[").append(getCompanyproportion()).append("],")
			.append("注册时间[").append(getRegistrationtime()).append("],")
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
		if(obj instanceof BetAgent == false) return false;
		if(this == obj) return true;
		BetAgent other = (BetAgent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@Override
	public int compareTo(BetAgent o) {
		// TODO Auto-generated method stub
		if(this.parentid!=null){
			if(o!=null){
				this.parentid.compareTo(o.getParentid());
			}
		}
		return 0;
	}
@Transient
	public String getName() {
		return agentid;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	@WhereSQL(sql="bettingty2=:BetBetting_bettingty2")
	public java.lang.Double getBettingty2() {
		return bettingty2;
	}

	public void setBettingty2(java.lang.Double bettingty2) {
		this.bettingty2 = bettingty2;
	}
	
	
	@WhereSQL(sql="isrobot=:BetAgent_isrobot")
	public java.lang.Integer getIsrobot() {
		if(this.isrobot==null){
			return 0;
		}else{
			return isrobot;
		}
	}

	public void setIsrobot(java.lang.Integer isrobot) {
		this.isrobot = isrobot;
	}

	


	
	
//	public void setChildren(ArrayList<BetAgent> children) {
//		this.children = new ArrayList<BetAgent>();
//	}

	
	
	
	
}

	
