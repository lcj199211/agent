
/**
 * 
 */
package org.springrain.frame.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springrain.frame.annotation.LuceneField;
import org.springrain.frame.annotation.LuceneSearch;
import org.springrain.frame.annotation.NotLog;
import org.springrain.frame.annotation.PKSequence;
import org.springrain.frame.annotation.TableSuffix;
import org.springrain.frame.annotation.WhereSQL;


/**
* 处理类的工具类. 例如反射
*
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.util.ClassUtils
*/

public class ClassUtils {
	
	
	//缓存 entity的字段信息
	public static Map<String,EntityInfo> staticEntitymap=new  ConcurrentHashMap<String,EntityInfo>();
	//缓存 所有的WhereSql注解
	public static Map<String, List<WhereSQLInfo>> staticWhereSQLmap=new  ConcurrentHashMap<String, List<WhereSQLInfo>>();
	//缓存 所有的字段
	public static Map<String, Set<String>> allFieldmap=new  ConcurrentHashMap<String, Set<String>>();
	//缓存 所有的数据库字段
	public static Map<String, List<String>> allDBFieldmap=new  ConcurrentHashMap<String, List<String>>();
	
	
	//缓存 实体类是否进行LuceneSearch
    public static Map<String,Boolean> luceneSearchmap=new  ConcurrentHashMap<String, Boolean>();
	
	//缓存 所有的参与Lucene的字段
	public static Map<String,List<String>> allLucenemap=new  ConcurrentHashMap<String, List<String>>();
	

	
	/**
	 * 添加一个EntityInfo 信息,用于缓存.
	 * @param info
	 * @return
	 */
	public static  Map<String,EntityInfo> addEntityInfo(EntityInfo info){
		if(info==null||info.getClassName()==null){
			 return null;
		}
		staticEntitymap.put(info.getClassName(), info);
		return staticEntitymap;
	}
/**
 * 根据ClassName获取 EntityInfo
 * @param className
 * @return
 * @throws Exception
 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static EntityInfo getEntityInfoByClass(Class clazz) throws Exception{
		if(clazz==null)
			return null;
		String className=clazz.getName();
		if(className==null)
			return null;
		boolean iskey=staticEntitymap.containsKey(className);
		if(iskey){
			return staticEntitymap.get(className);
		}

		
		 if((clazz.isAnnotationPresent(Table.class)==false)){
			 return null;
		 }
		
		String tableName = ClassUtils.getTableNameByClass(clazz);
		if(tableName==null)
			return null;
		EntityInfo info=new EntityInfo();
		info.setTableName(tableName);
		info.setClassName(clazz.getName());
		List<String> fields = ClassUtils.getAllDBFields(clazz);
    	if(fields==null)
		return null;
    	 for(String fdName:fields){
 			boolean ispk= isAnnotation(clazz,fdName,Id.class);
 			if(ispk==true){
 				info.setPkName(fdName);
 			  boolean isSequence=	 isAnnotation(clazz,fdName,PKSequence.class);
 			 Class returnType = getReturnType(fdName, clazz);
 			info.setPkReturnType(returnType);
 			  if(isSequence){
 					PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
 					Method getMethod = pd.getReadMethod();// 获得get方法
 					PKSequence sequenceAnnotation = getMethod.getAnnotation(PKSequence.class);
 					info.setPksequence(sequenceAnnotation.name());
 			  }
 				break;
 			}
 		 }
    	
    	
   	 if(clazz.isAnnotationPresent(TableSuffix.class)){
   		info.setSharding(true);
	 }
 	 if(clazz.isAnnotationPresent(NotLog.class)){
    		info.setNotLog(true);
 	 }
   	     	staticEntitymap.put(className,info);
		return staticEntitymap.get(className);
	}
	/**
	 * 根据对象获取Entity信息,主要是为了获取分表的信息
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static EntityInfo getEntityInfoByEntity(Object o) throws Exception{
		if(o==null)
			return null;
		Class clazz=o.getClass();
		EntityInfo info=getEntityInfoByClass(clazz);
		if(info==null){
			return null;
		}
		String tableExt=getTableExt(o);
		info.setTableSuffix(tableExt);
		return info;
	}

	
	
	/**
	 * 根据ClassName获取wheresql 注解的类信息
	 * @param className
	 * @return
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static List<WhereSQLInfo> getWhereSQLInfo(Class clazz) throws  Exception{
		if(clazz==null)
			return null;
		String className=clazz.getName();
		if(StringUtils.isBlank(className)){
			return null;
		}
		
		boolean iskey=staticWhereSQLmap.containsKey(className);
		if(iskey){
			return staticWhereSQLmap.get(className);
		}
		Set<String> names = getAllFieldNames(clazz);
		if(CollectionUtils.isEmpty(names))
			return null;
		
		 List<WhereSQLInfo>  wheresql=new ArrayList<WhereSQLInfo> ();
		for(String name:names){
			boolean isWhereSQL= isAnnotation(clazz,name,WhereSQL.class);
			if(isWhereSQL==false){
				continue;
			}
			
			PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			
			WhereSQL ws= getMethod.getAnnotation(WhereSQL.class);
			WhereSQLInfo info=new WhereSQLInfo();
			info.setName(name);
			info.setWheresql(ws.sql());
			wheresql.add(info);
		}
		
		return wheresql;
		
	}
	
	
	
	

	/**
	 * 获取一个类的所有属性名称,包括继承的父类
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Set<String> getAllFieldNames(Class clazz) throws Exception{
		if(clazz==null){
			return null;
		}
		String className=clazz.getName();
		boolean iskey=allFieldmap.containsKey(className);
		if(iskey){
		 return  allFieldmap.get(className);
		}
		Set<String>	allSet=new HashSet<String>();
		allSet=	recursionFiled(clazz,allSet);
		allFieldmap.put(className, allSet);
		return allSet;
	}
	
	
	
	
	/**
	 * 获取所有数据库的类字段对应的属性
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getAllDBFields(Class clazz) throws Exception{
		
		if(clazz==null){
			return null;
		}
		String className=clazz.getName();
		boolean iskey=allDBFieldmap.containsKey(className);
		if(iskey){
			return allDBFieldmap.get(className);
		}
		
		Set<String> allNames = getAllFieldNames(clazz);
     if(CollectionUtils.isEmpty(allNames))
    	 return null;
    
     List<String>   dbList=new ArrayList<String>();
	 for(String fdName:allNames){
		boolean isDB= isAnnotation(clazz,fdName,Transient.class);
		if(isDB==false){
			dbList.add(fdName);
		}
	 }
	 allDBFieldmap.put(className, dbList);
		return dbList;
	}
	
	
	/**
	 * 获取所有标注的lucene标注的字段,LuceneSearch标注为实体类使用了Lucene,LuceneField为字段进行了索引
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getLuceneFields(Class clazz) throws Exception{
		
		if(clazz==null){
			return null;
		}
		//检测
		
		//取消检测,增加手动模式
		
		// if(!clazz.isAnnotationPresent(LuceneSearch.class)){
		//   		return null;
		//}
		String className=clazz.getName();
		boolean iskey=allLucenemap.containsKey(className);
		if(iskey){
			return allLucenemap.get(className);
		}
		
		Set<String> allNames = getAllFieldNames(clazz);
     if(CollectionUtils.isEmpty(allNames))
    	 return null;
    
     List<String>   luceneList=new ArrayList<String>();
	 for(String fdName:allNames){
		boolean isLuceneField= isAnnotation(clazz,fdName,LuceneField.class);
		if(isLuceneField){
			luceneList.add(fdName);
		}
	 }
	     allLucenemap.put(className, luceneList);
		return luceneList;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * clazz 属性 fd 的 getReadMethod() 是否包含 注解 annotationName
	 * @param clazz
	 * @param fdName
	 * @param annotationClass
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isAnnotation(Class clazz,String fdName,Class annotationClass) throws Exception{
		
		if(clazz==null||fdName==null||annotationClass==null)
			return false;
		PropertyDescriptor pd = new PropertyDescriptor(fdName, clazz);
		Method getMethod = pd.getReadMethod();// 获得get方法
		return getMethod.isAnnotationPresent(annotationClass);
		
	}
	
	/**
	 * 获取 Class 的@Table注解 name 属性,没有属性则返回 类名
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static String  getTableName(Object object) throws Exception{
		
		if(object==null)
			return null;
	   String tableName=null;
	   
		if(object instanceof Class){
		EntityInfo entityInfo = getEntityInfoByClass((Class)object);
		tableName=entityInfo.getTableName();
		}else{
			EntityInfo entityInfoByEntity = ClassUtils
					.getEntityInfoByEntity(object);
			 tableName = entityInfoByEntity.getTableName();
			String tableExt = entityInfoByEntity.getTableSuffix();
			if (StringUtils.isNotBlank(tableExt)) {
				tableName = tableName + tableExt;
			}
		}
		

		if(tableName==null){
			return object.getClass().getSimpleName();
		}
		
			return tableName;
		
	}
	
	
	/**
	 * 获取 Class 的@Table注解 name 属性,没有属性则返回 类名
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String  getTableNameByClass(Class clazz){
		
		if(clazz==null)
			return null;
		
		 if((clazz.isAnnotationPresent(Table.class)==false))
			 return clazz.getSimpleName();
		 
		Table table= (Table) clazz.getAnnotation(Table.class);
		
		String tableName=table.name();
		if(tableName==null)
			return clazz.getSimpleName();
			return tableName;
		
	}
	
	
	
	/**
	 * 获取数据库分表的后缀
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getTableExt(Object o) throws Exception{
		Class clazz=o.getClass();
		if(clazz.isAnnotationPresent(TableSuffix.class)==false)
			return "";
		
		TableSuffix group =	(TableSuffix)clazz.getAnnotation(TableSuffix.class);
		String p=group.name();
		String  tableExt= (String) getPropertieValue(p, o);
		return tableExt;
		
	}
	
	
	
	/**
	 * 递归查询父类的所有属性,set 去掉重复的属性
	 * @param clazz
	 * @param fdNameSet
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static  Set<String> recursionFiled(Class clazz,Set<String> fdNameSet) throws Exception {
		Field[] fds = clazz.getDeclaredFields();
		for (int i = 0; i < fds.length; i++) {
			Field fd = fds[i];
			fdNameSet.add(fd.getName());
		}
		Class superClass = clazz.getSuperclass();
		if (superClass != Object.class) {
			recursionFiled(superClass,fdNameSet);
		}
		return fdNameSet;
	}
	
	
	/**
	 * 获得主键的值
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object getPKValue(Object o) throws Exception{
		Class clazz=o.getClass();
	     String id=getEntityInfoByClass(clazz).getPkName();
		return getPropertieValue(id,o) ;
			
	}
	
	/**
	 * 获取一个实体类的属性值
	 * @param p
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Object getPropertieValue(String p,Object o) throws Exception{
		Object _obj=null;
		for(Class<?> clazz = o.getClass(); clazz != Object.class;  clazz = clazz.getSuperclass()) {
			try{
			 PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				if(getMethod!=null){
					_obj= getMethod.invoke(o);
					break;
				}
			}catch(Exception e){
				return null;
			}
			
		}
		
		return _obj;
		
	}
	/**
	 * 设置实体类的属性值
	 * @param p
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Object setPropertieValue(String p,Object o,Object value) throws Exception{
		Object _obj=null;
		for(Class<?> clazz = o.getClass(); clazz != Object.class;  clazz = clazz.getSuperclass()) {
			 PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
				Method setMethod = pd.getWriteMethod();// 获得set方法
				if(setMethod!=null){
					setMethod.invoke(o, value);  
					break;
				}
			
		}
		
		return _obj;
		
	}
	
	/**
	 * 获取字段的返回类型
	 * @param p
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Class getReturnType(String p,Class _clazz) throws Exception{
		
		Class  returnType=null;
		for(Class<?> clazz = _clazz; clazz != Object.class;  clazz = clazz.getSuperclass()) {
			 PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
				Method getMethod = pd.getReadMethod();// 获得get方法
				if(getMethod!=null){
					returnType= getMethod.getReturnType();
					break;
				}
			
		}
		
		return returnType;
	}
	
	/**
	 * 是否是java的基本类型
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static  boolean isBaseType(Class clazz){
		if(clazz==null){
			return false;
		}
		String className=clazz.getName().toLowerCase();
		if(className.startsWith("java.")){
			return true;
		}else{
			return false;
		}
	}
	
	
	//实体类是否进行Lucene检索
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isLuceneSearch(Class clazz) throws Exception{
		if(clazz==null)
			return false;
		String className=clazz.getName();
		if(className==null){
			return false;
		}
			
		boolean iskey=luceneSearchmap.containsKey(className);
		if(iskey){
			return luceneSearchmap.get(className);
		}

		boolean isLuceneSearch=clazz.isAnnotationPresent(LuceneSearch.class);
		luceneSearchmap.put(className,isLuceneSearch );
		return isLuceneSearch;
		
	}
	
	
	
	
}
