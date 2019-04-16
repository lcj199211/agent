package org.springrain.lottery.entity;

public class CommissionDto {
	/**
	 * 佣金
	 */
	private java.lang.Double commission;
	/**
	 * 结算时间
	 */
	private java.util.Date settlementtime;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 用户id2
	 */
	private java.lang.Integer memberid2;
	public java.lang.Double getCommission() {
		return commission;
	}
	public void setCommission(java.lang.Double commission) {
		this.commission = commission;
	}
	public java.util.Date getSettlementtime() {
		return settlementtime;
	}
	public void setSettlementtime(java.util.Date settlementtime) {
		this.settlementtime = settlementtime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public java.lang.Integer getMemberid2() {
		return memberid2;
	}
	public void setMemberid2(java.lang.Integer memberid2) {
		this.memberid2 = memberid2;
	}
}
