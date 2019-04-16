package org.springrain.system.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springrain.frame.dao.BaseSlaveJdbcDaoImpl;
import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.dao.IBaseSlaveJdbcDao;
import org.springrain.frame.dao.ReflectionUtil;
import org.springrain.frame.dao.dialect.IDialect;
import org.springrain.frame.entity.AuditLog;

/**
 *   springrain项目的基础Dao,代理demo数据库
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.system.dao.BaseSlaveDaoImpl
 */
@Repository("baseSlaveDao")
public class BaseSlaveDaoImpl extends BaseSlaveJdbcDaoImpl implements IBaseSlaveJdbcDao{
	private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * demo  数据库的jdbc,对应 spring配置的 jdbc bean
	 */
	@Resource
	NamedParameterJdbcTemplate jdbcSlave;
	/**
	 * demo  数据库的jdbcCall,对应 spring配置的 jdbcCall bean
	 */
	@Resource
	public SimpleJdbcCall jdbcCallSlave;
    /**
     * mysqlDialect 是mysql的方言,springBean的name,可以参考 IDialect的实现
     */
	@Resource
	public IDialect mysqlDialect;
	@Override
	public IDialect getDialect() {
		System.out.println("BaseSlaveDaoImpl.getDialect()mysqlDialect="+mysqlDialect);
		return mysqlDialect;
	}

	public BaseSlaveDaoImpl() {
	}


	/**
	 * 实现父类方法,demo  数据库的jdbc,对应 spring配置的 jdbc bean
	 */
	@Override
	public SimpleJdbcCall getJdbcCall() {
		//System.out.println("BaseSlaveDaoImpl.getJdbcCall()jdbcCallSlave="+jdbcCallSlave);
		return this.jdbcCallSlave;
	}
	/**
	 * 实现父类方法,demo  数据库的jdbcCall,对应 spring配置的 jdbcCall bean
	 */
	@Override
	public NamedParameterJdbcTemplate getJdbc() {
		com.alibaba.druid.pool.DruidDataSource ds=(com.alibaba.druid.pool.DruidDataSource)jdbcCallSlave.getJdbcTemplate().getDataSource();
			
		//System.out.println("BaseSlaveDaoImpl.getJdbc()jdbcCallSlave="+ds.getUrl());
		//logger.debug("BaseSlaveDaoImpl.getJdbc()jdbcCallSlave="+ds.getUrl());
			
		
		return jdbcSlave;
	}
	
//	@SuppressWarnings("rawtypes")
//	public static void getProperty(Object entityName)  {
//		try {
//			Class c = entityName.getClass();
//			Field field[] = c.getDeclaredFields();
//			for (Field f : field) {
//				Object v = invokeMethod(entityName, f.getName(), null);
//				if (v != null) {
//					System.out.println(f.getName() + "\t" + v + "\t"
//							+ v.toString().length());
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	* 获得对象属性的值
//	*/
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
//	Class ownerClass = owner.getClass();
//	methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
//	Method method = null;
//	try {
//	method = ownerClass.getMethod("get" + methodName);
//	} catch (SecurityException e) {
//	} catch (NoSuchMethodException e) {
//	return " can't find 'get" + methodName + "' method";
//	}
//	return method.invoke(owner);
//	}
	/*
	 * 读写分离时,处理同步延迟问题
	@Override
	public NamedParameterJdbcTemplate getJdbc() {
		try{
            TransactionInterceptor.currentTransactionStatus();
		}catch(NoTransactionException e){
			return this.jdbc;
		}
		return getWriteJdbc();
	}
	*/


/**
 * 实现父类方法,返回记录日志的Entity接口实现,reutrn null 则代表不记录日志
 */
	@Override
	public AuditLog getAuditLog() {
		return null;
//		return new AuditLog();
	}
	
	/**
	 * 是否打印sql语句,默认false
	 * 
	 * @return
	 */
	@Override
	public boolean showsql() {
		return true;
	}



}
