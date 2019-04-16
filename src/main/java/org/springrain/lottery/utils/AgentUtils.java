package org.springrain.lottery.utils;

/**
 * 代理工具类
 * @author Administrator
 *
 */
public class AgentUtils {
	public static String TOOL;
	
	/**
	 * 获取一级代理（分公司）id，0级为总公司,',A101,aaa,bbb,'
	 * @param request
	 * @return
	 */
	public static String getSubCompanyId(String[] parentids){
		
		if (parentids == null || parentids.length <=1) {
			return null;
		}
		
		if(parentids.length>1&&!"".equals(parentids[0])){
			return parentids[1];
		}else if (parentids.length>2&&"".equals(parentids[0])){
			return parentids[2];
		}
		
		return null;
	}
	
	/**
	 * 获取一级代理（分公司）id，0级为总公司,',A101,aaa,bbb,'
	 * @param request
	 * @return
	 */
	public static String getSubCompanyId(String parentids){
		if(parentids == null || "".equals(parentids)){
			return null;
		}
		String[] pids = parentids.split(",");
		return getSubCompanyId(pids);
	}
	
	/**
	 * 获取代理等级 
	 * ',A101,aaa,bbb,' A101为0级，aaa为1级，bbb为2级，自身为三级
	 * @param request
	 * @return
	 */
	public static int getSubCompanyLevel(String[] parentids){
		if(parentids == null || parentids.length == 0){
			return -1;
		}
		if("".equals(parentids[0])){
			return parentids.length - 1;
		}else{
			return parentids.length;
		}
	}
	
	/**
	 * 获取代理等级
	 * @param request
	 * @return
	 */
	public static int getSubCompanyLevel(String parentids){
		if(parentids == null || "".equals(parentids)){
			return -1;
		}
		String[] pids = parentids.split(",");
		return getSubCompanyLevel(pids);
	}
	
}
