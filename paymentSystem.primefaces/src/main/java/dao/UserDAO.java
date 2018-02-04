package dao;

import entity.User;

public interface UserDAO {
	/*
	 * @author cz
	 * @data 2018-1-23
	 * */
	/*
	 * 用户登陆
	 * @param user;
	 * @return User;
	 * */
	public User userLogin(User user);
	/*
	 * 用户注册
	 * @param user;
	 * @return boolean;
	 * */
	public boolean userRegister(User user);
	/**
	 * @param mailCode
	 * @return
	 */
	public User findByMailCode(String mailCode);
	/**
	 * @param user
	 * @author cz
	 * @time 2018年2月2日下午4:06:01
	 */
	public void update(User user);
	
}
