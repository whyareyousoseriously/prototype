package controller;

import javax.faces.bean.ManagedBean;

/*
 * Handle login related operations
 * 处理和登陆有关的操作
 * @author cz
 * 2017-12-22
 * */
@ManagedBean
public class loginBean {
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
	public String dologin() {
		if(true) {
			return "login-success";
		}else {
			return "login-error";
		}
	}
}
