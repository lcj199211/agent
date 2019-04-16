package org.springrain.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.service.IBetSelfdestroyService;

public class DestoryInterceptor implements HandlerInterceptor {
	@Resource
	private IBetSelfdestroyService betSelfdestroyService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		String state = betSelfdestroyService.queryForObject(new Finder("select state from bet_selfdestroy where id=1"), String.class);
//		if("1".equals(state)){
//			return true;
//		}
//		request.getRequestDispatcher("/errorpage/404").forward(request, response);
//		return false;
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
