package org.springrain.frame.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.lottery.utils.AgentToolUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 权限访问的过滤器
 * @author caomei
 *
 */


@Component("frameperms")
public class FramePermissionsAuthorizationFilter extends
		PermissionsAuthorizationFilter {
	public Logger logger = LoggerFactory.getLogger(getClass());
	//@Resource
	//private IMenuService menuService;
	//@Resource
	//private CacheManager shiroCacheManager;
	

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		/*
		Session session = user.getSession(false);
		Cache<Object, Object> cache = shiroCacheManager.getCache(GlobalStatic.authenticationCacheName);
		String cachedSessionId = cache.get(GlobalStatic.authenticationCacheName+"-"+shiroUser.getAccount()).toString();
		String sessionId=(String) session.getId();
		if(!sessionId.equals(cachedSessionId)){
			user.logout();
		}
		*/
		
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		if(uri.endsWith("/pre")){// 去掉pre
			uri=uri.substring(0,uri.length()-4);
		}
		if(uri.endsWith("/json")){// 去掉json
			uri=uri.substring(0,uri.length()-5);
		}
		if(uri.endsWith("/more")){// 去掉more
			uri=uri.substring(0,uri.length()-5);
		}
		int i=uri.indexOf(contextPath);
		if(i>-1){
			uri=uri.substring(i+contextPath.length());
		}
		if(StringUtils.isBlank(uri)){
			uri="/";
		}
		
		 boolean permitted = false;
		 if("/".equals(uri)){
			 permitted=true;
		 }else{
		     permitted= subject.isPermitted(uri);
		 }
	
		HttpServletRequest req1=(HttpServletRequest) request;
		if(AgentToolUtil.isMobileDevice(req1.getHeader("user-agent"))){
			if(permitted==false){
				ReturnDatas returnobject = ReturnDatas.getErrorReturnDatas();
				returnobject.setStatusCode("119");
				returnobject.setMessage("您访问的网址没有权限。");
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
			    return permitted;
			}else{
				return permitted;
			}
		}else{
			return permitted;
		}
	}
}
