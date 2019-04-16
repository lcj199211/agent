package org.springrain.lottery.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springrain.frame.dao.BaseJdbcDaoImpl;
import org.springrain.frame.dao.dialect.IDialect;
import org.springrain.frame.util.Finder;
import org.springrain.system.entity.User;
public class OptLog extends BaseJdbcDaoImpl{
	private static User user;
	/**
	 * 获取当前用户的账户名
	 * @return
	 */
	public String getOnlineLoginNames(){
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		String account = session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY").toString();
		return account;
	}
	
	/**
	 * 通过账户名查找用户对象
	 * @return
	 * @throws Exception
	 */
	public User findUserByAccount() throws Exception{
		String sql = "SELECT * FROM t_user WHERE account=:" + getOnlineLoginNames();
		Finder finder = new Finder(sql);
		user = queryForObject(finder,User.class);
		return user;
	}
	
	/**
	 * 操作日志的记录，详情需要手动设置。写在Controller层更新操作成功后，返回页面前
	 * 例子：
	 * 更新操作：String details = "";
	 * 		if(id==null||"".equals(id)){
	 * 			details = "添加了支付接口，名字为："+betPaymentInterface.getBankname();
	 * 		}else{
	 * 			details = "更新了"+betPaymentInterface.getId()+"的支付接口";
	 * 		}
	 * 删除操作：String details = "删除id为的"+id+"支付接口";
	 * 最后调用betOptLogService.save(OptLog.optLog(request,details));
	 * @param request 
	 * @param details 操作详情信息
	 * @author zmw
	 */
	
	
	//以下方法不用，继承只是为了用queryForObject
	@Override
	public NamedParameterJdbcTemplate getJdbc() {
		return null;
	}

	@Override
	public SimpleJdbcCall getJdbcCall() {
		return null;
	}

	@Override
	public IDialect getDialect() {
		return null;
	}
}
