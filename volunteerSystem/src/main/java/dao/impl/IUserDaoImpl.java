/**
 * 下午5:18:50
 * power
 */
package dao.impl;

import dao.IUserDao;
import pojo.User;
import utils.db.DBOperation;

/**
 * 用户接口实现类
 * 实现用户的注册以及登陆以及密码的修改
 * @author cz
 * 2018年4月12日下午5:18:50
 */
public class IUserDaoImpl implements IUserDao {

	/* (non-Javadoc)
	 * @see dao.IUserDao#login(pojo.User)
	 * @author cz
	 * @time 2018年4月12日下午5:43:57
	 */
	@Override
	public User login(User user) {
		//调用DBOPeration工具类
		User loginUser = (User)DBOperation.login("User", user);
		if(loginUser!=null) {
			return loginUser;
		}else {
			return null;
		}	
	}

	/* (non-Javadoc)
	 * @see dao.IUserDao#register(pojo.User)
	 * @author cz
	 * @time 2018年4月12日下午5:43:57
	 */
	@Override
	public User register(User user) {
		//调用DBOPeration工具类
		User registerUser = (User)DBOperation.addData("User", user);
		if(registerUser!=null) {
			return registerUser;
		}else {
			return null;
		}	
	}

	/* (non-Javadoc)
	 * @see dao.IUserDao#forgetPassword(pojo.User)
	 * @author cz
	 * @time 2018年4月12日下午5:43:57
	 */
	@Override
	public User forgetPassword(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.IUserDao#changePassword(pojo.User)
	 * @author cz
	 * @time 2018年4月12日下午5:43:57
	 */
	@Override
	public User changePassword(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
