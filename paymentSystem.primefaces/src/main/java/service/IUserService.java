/**
 * 下午5:44:23
 * power
 */
package service;

import common.ServerResponse;
import pojo.User;
import pojo.UserDetails;

/**
 * 
 * @author cz
 * 2018年4月25日下午5:44:23
 */
public interface IUserService {
	/**
	 * @param username
	 * @param password
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午6:15:13
	 */
	ServerResponse<User> login(String username,String password);
	
	/**
	 * 注册方法
	 * @param user
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午7:55:19
	 */
	ServerResponse<String> register(User user);
	
	
	/**
	 * 对字段的查重
	 * @param str
	 * @param type
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午7:55:47
	 */
	ServerResponse<User> checkValid(String str, String type);
	
	/**
	 * 账户的激活
	 * @param mailCode
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午9:40:14
	 */
	ServerResponse<String> active(String mailCode);

	
	
	/**
	 * 向邮箱发送验证码，用于密码的修改
	 * @param username
	 * @param email
	 * @return
	 * @author cz
	 * @time 2018年4月27日下午1:30:30
	 */
	ServerResponse<String> sendCheckCodeEmail(String username, String email);

	/**
	 * 查询用户的详细信息
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午7:04:19
	 */
	UserDetails listUserDetailsByUserId(String userId);

	/**
	 * @param username
	 * @param passwordNew
	 * @param restPasswordCheckCode
	 * @return
	 * @author cz
	 * @time 2018年4月27日下午3:07:46
	 */
	ServerResponse<String> forgetRestPassword(String username, String passwordNew, String restPasswordCheckCode);
}
