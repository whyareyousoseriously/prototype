/**
 * 下午5:54:53
 * power
 */
package dao;

import pojo.User;
import pojo.UserDetails;

/**
 * 
 * @author cz
 * 2018年4月25日下午5:54:53
 */
public interface IUserDao {
	/**
	 * 检查用户名是否重复
	 * @param username
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午6:04:47
	 */
	User checkUsername(String username);
	
	/**
	 * 登陆方法
	 * @param username
	 * @param md5Password
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午6:12:50
	 */
	User selectLogin(String username,String md5Password);
	
	/**
	 * 检查Email是否重复
	 * @param email
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午7:40:51
	 */
	User checkEmail(String email);
	
	/**
	 * 向数据库插入或更新一条数据，注册方法
	 * @param user
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午7:49:07
	 */
	Integer saveOrUpdate(User user);
	
	/**
	 * 根据mailCode找到知道的需要激活的用户
	 * @param mailCode
	 * @return
	 * @author cz
	 * @time 2018年4月25日下午9:28:53
	 */
	User findByMailCode(String mailCode);

	/**
	 * 发送邮件验证码的时候，需要检验输入的用户名和邮箱
	 * @param username
	 * @param email
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午4:59:29
	 */
	User checkUsernameAndEmail(String username, String email);

	/**
	 * 获取用户的详细信息
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午7:09:05
	 */
	UserDetails listUserDetailsByUserId(String userId);

	/**
	 * 保存用户详细信息
	 * @param userDetails
	 * @return
	 * @author cz
	 * @time 2018年4月27日下午5:13:27
	 */
	UserDetails saveOrUpdate(UserDetails userDetails);
}
