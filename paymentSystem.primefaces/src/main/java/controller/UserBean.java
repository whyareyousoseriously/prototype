package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

/*
 * 加入激活状态的属性
 * @author
 * @data 2018-1-30
 * */
public class UserBean {
	private String username;
	private String password;
	private String email;
	private String type;
	private String active;
	public UserBean() {
		super();
	}
	public UserBean(String username, String password, String email, String type, String active) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
		this.active = active;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public void showActive() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, new FacesMessage("登陆中","账号状态："+this.active));
		if(active.equals("未激活"))
			context.addMessage(null, new FacesMessage("提示","您的账号状态未激活，请尽快激活，以免影响您的使用"));
	}
	
	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password + ", email=" + email + ", type=" + type
				+ ", active=" + active + "]";
	}
	
	public String doLogin() {
		System.out.println(username);
		System.out.println(password);
		
		//将页面上的属性封装在User中
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		//WCF Interface oriented programming
		UserDAO userDAO = new UserDAOImpl();
		
		
		//接收逻辑层返回值
		User user_feedback = userDAO.userLogin(user);
		
		if(user_feedback!=null) {
			System.out.println(user_feedback.toString()+"login success");
			this.setUsername(user_feedback.getUsername());
			this.setPassword(user_feedback.getPassword());
			this.setEmail(user_feedback.getEmail());
			this.setType(user_feedback.getType());
			this.setActive(user_feedback.getActive());
			this.showActive(); //将账号状态加入FacesMessage
			return "home?facesRedirect=true";
		}else {
			System.out.println(user.getUsername()+"login faliure!");
			
			return "/WEB-INF/userPage/login-error?facesRedirect=true";
		}
	}
	
	public String doRegister() {
		System.out.println(this.toString());
		
		//将页面属性封装入User对象
		User user = new User();
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		user.setEmail(this.getEmail());
		user.setType(this.getType());
		user.setActive("未激活");; //置激活状态为0，未激活。
		UserDAO udao = new UserDAOImpl();
		if(udao.userRegister(user))
			return "/WEB-INF/userPage/registration-success?facesRedirect=true";
		else
			return "/WEB-INF/userPage/registration-error?facesRedirect=true";
	}
	
}
