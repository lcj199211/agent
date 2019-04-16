package org.springrain.lottery.utils;

import java.util.Calendar;
import java.util.Date;

public class WeekOfDate {
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(new Date(date.getTime()-11*60*60*1000-30*60*1000));      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
}
