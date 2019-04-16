package org.springrain.lottery.entity;

public class AssistGameManage {
private Integer gameclassid;
private String gcname;
public Integer getGameclassid() {
	return gameclassid;
}
public void setGameclassid(Integer gameclassid) {
	this.gameclassid = gameclassid;
}
public String getGcname() {
	return gcname;
}
public void setGcname(String gcname) {
	this.gcname = gcname;
}
@Override
public String toString() {
	return "AssistGameManage [gameclassid=" + gameclassid + ", gcname="
			+ gcname + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((gameclassid == null) ? 0 : gameclassid.hashCode());
	result = prime * result + ((gcname == null) ? 0 : gcname.hashCode());
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
	AssistGameManage other = (AssistGameManage) obj;
	if (gameclassid == null) {
		if (other.gameclassid != null)
			return false;
	} else if (!gameclassid.equals(other.gameclassid))
		return false;
	if (gcname == null) {
		if (other.gcname != null)
			return false;
	} else if (!gcname.equals(other.gcname))
		return false;
	return true;
}

}
