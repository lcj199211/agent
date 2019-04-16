package org.springrain.lottery.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetOptLog;
import org.springrain.lottery.service.IBetOptLogService;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-01 12:51:24
 * @see org.springrain.lottery.service.impl.BetOptLog
 */
@Service("betOptLogService")
public class BetOptLogServiceImpl extends BaseSpringrainServiceImpl implements IBetOptLogService {
	
   
    @Override
    
	public String  save(Object entity ) throws Exception{
	      BetOptLog betOptLog=(BetOptLog) entity;
	       return super.save(betOptLog).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      BetOptLog betOptLog=(BetOptLog) entity;
		 return super.saveorupdate(betOptLog).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 BetOptLog betOptLog=(BetOptLog) entity;
	return super.update(betOptLog);
    }
    @Override
	public BetOptLog findBetOptLogById(Object id) throws Exception{
	 return super.findById(id,BetOptLog.class);
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public List<BetOptLog> findListDataByDate(Page page, Date startTime,
			Date endTime) throws Exception {
		Finder finder = new Finder("SELECT * FROM bet_opt_log where 1=1 ");
		 Calendar   calendar   =   new   GregorianCalendar(); 
	    if(endTime!=null){
	    	calendar.setTime(endTime); 
		     calendar.add(Calendar.DATE,1);
		     endTime=calendar.getTime();   
			String strStartTime=DateUtils.convertDate2String("yyyy-MM-dd",startTime);
			String strEndTime=DateUtils.convertDate2String("yyyy-MM-dd",endTime);
			finder.append(" and time>=:startTime").setParam("startTime",strStartTime);
			finder.append(" and time<:endTime").setParam("endTime",strEndTime);
	    }
		return  super.findListDataByFinder(finder,page,BetOptLog.class,null);
	}
//	@Async
	@Override
	public  void saveoptLog(String tool,String ip,String details,String agentid,String agentparentid,String agentparentids) {
		BetOptLog log = new BetOptLog();
		String userId = SessionUser.getShiroUser().getId();
	    try {
	    		log.setTool(tool);
	    		log.setIp(ip);
	    		User user1 = queryForObject(new Finder("select * from t_user where id=:id ").setParam("id", userId), User.class);
	    		BetAgent betAgent1 = queryForObject(new Finder("select * from bet_agent r where agentid=:agentid ").setParam("agentid", agentid), BetAgent.class);
	    		if(user1.getCpuandmac()!=null){
	    			log.setUserid(user1.getId2());
	    			log.setAccount(user1.getAccount());
	    			log.setName(user1.getName()); 	
	    		}else{
	    			User user2 = queryForObject(new Finder("select * from t_user where account=:account ").setParam("account", betAgent1.getAccount()), User.class);
	    			log.setUserid(user2.getId2());
	    			log.setAccount(betAgent1.getAccount());
	    			log.setName(betAgent1.getNickname()); 	
	    		}
	    		log.setTime(new Date());
	    		log.setDetails(details);
	    		log.setAgentid(agentid);
	    		log.setAgentparentid(betAgent1.getParentid());
	    		log.setAgentparentids(betAgent1.getParentids());
	    		save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
