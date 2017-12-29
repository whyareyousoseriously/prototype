package dao;

import java.util.List;

import model.Users;

public interface RootsDAO {
	/*
	 * 超级管理员类的接口类
	 * @author cz
	 * 2017-12-26
	 * */
	
	//首先是查询数据库中所有用户的接口
	public List<Users> getAllUsers();
	
	//向用户表中添加一条信息的方法
	public String addUser(Users user);
	
	
	
	//查询数据库中的一条信息
	public Users searchOne(Users user);
	
	//查询数据库中的信息通过姓名
	public List<Users> searchManyByName(Users user);
	
	//查询数据库中的信息通过学号
	public List<Users> searchManyByStudentID(Users user);
	
	//在数据库中删除一条信息通过指定条件
	public String deleteUser(Users user,String condition);
	
	//更新数据库中的一条数据通过指定条件
	public String updateUser(Users user,String condition);
}
