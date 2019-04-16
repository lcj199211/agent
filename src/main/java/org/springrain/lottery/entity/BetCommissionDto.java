package org.springrain.lottery.entity;

import java.util.List;

public class BetCommissionDto {
private List<CommissionDto> list;
private String sum;
public List<CommissionDto> getList() {
	return list;
}
public void setList(List<CommissionDto> list) {
	this.list = list;
}
public String getSum() {
	return sum;
}
public void setSum(String sum) {
	this.sum = sum;
}
}
