package org.springrain.lottery.entity;

public class SubordinateDto {
	/**
	 * 用户id
	 */
	private java.lang.String id2;
	/**
	 * agentid
	 */
	private java.lang.String agentid;
	/**
	 * 会员账户
	 */
	private java.lang.String account;
	/**
	 * 昵称
	 */
	private java.lang.String nickname;
	/**
	 * 投注退佣比例
	 */
	private Double bettingrebate;
	/**
	 * 跟单退佣比例
	 */
	private Double bettingrebate2;
	
	/**
	 * 代理投注退佣比例
	 */
	private Double agentbettingrebate;
	/**
	 * 代理跟单退佣比例
	 */
	private Double agentbettingrebate2;
	public java.lang.String getAccount() {
		return account;
	}
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	public java.lang.String getNickname() {
		return nickname;
	}
	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}
	public Double getBettingrebate() {
		return bettingrebate;
	}
	public void setBettingrebate(Double bettingrebate) {
		this.bettingrebate = bettingrebate;
	}
	public Double getBettingrebate2() {
		return bettingrebate2;
	}
	public void setBettingrebate2(Double bettingrebate2) {
		this.bettingrebate2 = bettingrebate2;
	}
	public java.lang.String getId2() {
		return id2;
	}
	public void setId2(java.lang.String id2) {
		this.id2 = id2;
	}
	public Double getAgentbettingrebate() {
		return agentbettingrebate;
	}
	public void setAgentbettingrebate(Double agentbettingrebate) {
		this.agentbettingrebate = agentbettingrebate;
	}
	public Double getAgentbettingrebate2() {
		return agentbettingrebate2;
	}
	public void setAgentbettingrebate2(Double agentbettingrebate2) {
		this.agentbettingrebate2 = agentbettingrebate2;
	}
	public java.lang.String getAgentid() {
		return agentid;
	}
	public void setAgentid(java.lang.String agentid) {
		this.agentid = agentid;
	}
}
