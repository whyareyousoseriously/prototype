package service;

import entity.Users;


//用户业务逻辑接口
public interface UsersDAO {
	/*
	 * The method of userLogin
	 * @param u{Users};
	 * @return the limits of authority.404 represent error.non-404 represent true;
	 * 
	 * 用户登陆的方法
	 * 输入：用户u;
	 * 返回：用户的权限，404代表登陆失败
	 * */
	public int usersLogin(Users u);
	/*
	 * The method of userRegister
	 * @param u{Users};
	 * @return the result
	 * 
	 * 用户注册的方法
	 * 输入：用户u
	 * 返回：结果，成功或失败的原因
	 * */
	public int usersRegister(Users u);
	
}
