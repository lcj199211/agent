package org.springrain.lottery.utils;

import javax.servlet.http.HttpServletRequest;

public class AgentToolUtil {
	public static String TOOL;
	
	public static String getAgentTool(HttpServletRequest request){
		String header = request.getHeader("User-Agent");
	//	System.out.println("*************"+header+"*************");
		if(header.contains("Mobile")){
			if(header.contains("Android")){
				TOOL = "安卓手机";
			}else if(header.contains("iPhone")){
				TOOL = "苹果手机";
			}else{
				TOOL = "其他手机";
			}
		}else{
			TOOL = "PC";
		}
		return TOOL;
		
	}
	
	public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
		//System.out.println("requestHeader="+requestHeader);
        String[] deviceArray = new String[]{"android","iphone","ipad","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
}
}
