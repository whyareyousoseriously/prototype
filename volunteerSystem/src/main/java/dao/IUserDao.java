/**
 * 下午5:17:51
 * power
 */
package dao;

import pojo.User;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:17:51
 */
public interface IUserDao {

	/**
	 * 用户登陆方法
	 * @param user 用户实体
	 * @return 成功登陆的用户实体,失败返回空值
	 * @author cz
	 * @time 2018年4月12日下午5:39:57
	 */
	User login(User user);
	/**
	 * 用户注册的方法
	 * @param user 用户实体
	 * @return 成功注册返回用户实体，失败返回空值
	 * @author cz
	 * @time 2018年4月12日下午5:41:55
	 */
	User register(User user);
	
	//TODO
	User forgetPassword(User user);
	
	//TODO
	User changePassword(User user);
}
