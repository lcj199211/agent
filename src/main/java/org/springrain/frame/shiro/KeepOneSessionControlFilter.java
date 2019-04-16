package org.springrain.frame.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.lottery.utils.AgentToolUtil;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 保存最新的用户在线，踢出上一个用户
 * @author caomei
 *
 */

@Component("keepone")
public class KeepOneSessionControlFilter extends AccessControlFilter {
    @Resource
	private SessionManager sessionManager;
    @Resource
    private CacheManager cacheManager;


	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		String userId = SessionUser.getUserId();
		Object attribute = SessionUser.getSession().getAttribute("XSDFSADFAS");
		if(attribute!=null){
			HttpServletRequest req1=(HttpServletRequest) request;
			if(AgentToolUtil.isMobileDevice(req1.getHeader("user-agent"))){
				ReturnDatas returnobject = ReturnDatas.getErrorReturnDatas();
				returnobject.setStatusCode("110");
				returnobject.setMessage("您的帐号在其它地方登录，请核对密码是否被盗用。");
				ObjectMapper mapper=new ObjectMapper();
				byte[] json = mapper.writeValueAsBytes(returnobject);
				String string = new String(json,"utf-8");
				response.setCharacterEncoding("UTF-8");  
				response.setContentType("application/json; charset=utf-8");
				 PrintWriter out = null;  
			    try {  
			        out = response.getWriter();  
			        out.append(string);  
			        
			    } catch (IOException e) {  
			        e.printStackTrace();  
			    } finally {  
			        if (out != null) {  
			            out.close();  
			        }  
			    }  
			    return true;
			}else{
				request.getRequestDispatcher("/errorpage/keepone").forward(request, response);
				return true;
			}
		}
		
		if (StringUtils.isBlank(userId)) {// 没有登录
			return true;
		}
		
		//当前session 的Id
		Serializable sessionId =SessionUser.getSession().getId();

		
		//当前用户缓存中的sessionId
		  Cache cache = cacheManager.getCache(GlobalStatic.springrainkeeponeCacheKey);
		  String deleteSessionId = cache.get(userId,String.class);
	
		if (sessionId.toString().equalsIgnoreCase(deleteSessionId)) {
			return true;
		} else if(StringUtils.isBlank(deleteSessionId)){
			cache.put(userId, sessionId.toString());
			return true;
		}else {
			cache.put(userId, sessionId.toString());
			
			//Session deletetSession = sessionManager.getSession(new DefaultSessionKey(deleteSessionId));
			Session deletetSession=null;
			try {
			    deletetSession = sessionManager.getSession(new DefaultSessionKey(deleteSessionId));
			} catch (UnknownSessionException e) {//no session with  id [deleteSessionId]

			} catch(ExpiredSessionException e){//Session with id [deleteSessionId] has expired

			}
			
			if (deletetSession == null) {
				return true;
			}
			//根据 需要删除的 sessionId,生成subject
			Subject deleteSubject = new Subject.Builder().sessionId(deleteSessionId).buildSubject();
            //退出
			deleteSubject.getSession().setAttribute("XSDFSADFAS", 2);
//			deleteSubject.logout();
			//在此可以自定义json格式的回复
			return true;

		}

	}


}
