package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;

/*
 * Handle login related operations
 * 处理和登陆有关的操作
 * @author cz
 * 2017-12-22
 * */

public class LoginBean {
	private String studentID;
	private String password;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String doLogin() {
		System.out.println(studentID);
		System.out.println(password);
		
		//将页面上的学号和密码封装成Users对象
		//The studentID and password on the page are encapsulated into users objects.
		Users users= new Users();
		users.setStudentID(studentID);
		users.setPassword(password);
		
		//面向接口编程WCF Interface oriented programming
		UsersDAO usersDAO = new UsersDAOImpl();
		//将页面上的Users对象传递，然后数据库进行查询
		
		if(usersDAO.usersLogin(users)==1) {
			System.out.println(users.getStudentID()+"登录成功");
			return "login-success";
		}else {
			System.out.println(users.getStudentID()+"登录失败");
			
			return "login-error";
		}
	}
}
