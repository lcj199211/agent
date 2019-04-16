package org.springrain.frame.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springrain.frame.shiro.ShiroUser;

/**
 * 当前登录用户信息,可以在bean中调用获取当前登录用户信息,例如 SessionUser.getUserId()获取当前登录人的userId
 * 
 * @copyright {@link weicms.net}
 * @author springrain<9iuorg@gmail.com>
 * @version 2013-03-19 11:08:15
 * @see org.springrain.frame.common.SessionUser
 */
public class SessionUser {

	public SessionUser() {
	}

	public static ShiroUser getShiroUser() {

		try {

			Subject user = SecurityUtils.getSubject();
			if (user == null) {
				return null;
			}
			ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
			if (shiroUser == null) {
				return null;
			}
			return shiroUser;
		} catch (Exception e) {
			return null;
		}
	}

	public static Session getSession() {
		try {
			Subject user = SecurityUtils.getSubject();
			if (user == null) {
				return null;
			}
			Session session = user.getSession();
			return session;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getUserId() {
		try {
			ShiroUser shiroUser = getShiroUser();
			if (shiroUser == null) {
				return null;
			}
			return shiroUser.getId();
		} catch (Exception e) {
			return null;
		}
	}
	public static String getAgentId() {
		try {
			ShiroUser shiroUser = getShiroUser();
			if (shiroUser == null) {
				return null;
			}
			return shiroUser.getAgentid();
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer getUserType() {
		ShiroUser shiroUser = getShiroUser();
		if (shiroUser == null) {
			return null;
		}
		return shiroUser.getUserType();
	}

	public static String getUserCode() {
		ShiroUser shiroUser = getShiroUser();
		if (shiroUser == null) {
			return null;
		}
		return shiroUser.getAccount();

	}

	public static String getUserName() {
		ShiroUser shiroUser = getShiroUser();
		if (shiroUser == null) {
			return null;
		}
		return shiroUser.getName();
	}

	public static String getEmail() {
		ShiroUser shiroUser = getShiroUser();
		if (shiroUser == null) {
			return null;
		}
		return shiroUser.getEmail();
	}

}
