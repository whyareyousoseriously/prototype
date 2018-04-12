/**
 * 下午5:32:11
 * power
 */
package controller;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.IUserDao;

import dao.impl.IUserDaoImpl;

import pojo.User;

/**
 * 一般用户bean类
 * @author cz 2018年4月12日下午5:32:11
 */
@ManagedBean
public class UserBean {
	private String id;// 用户表id
	private String username;// 用户名
	private String password;// 用户密码
	private String email;
	private String phone;
	private String question;// 找回密码问题
	private String answer;// 找回密码答案
	private Date createTime;// 创建时间
	private Date updateTime;// 最后一次更新时间

	public UserBean() {
		super();
	}

	public UserBean(String id, String username, String password, String email, String phone, String question,
			String answer, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.question = question;
		this.answer = answer;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String doLogin() {
		// 正常发布，取消这两行打印，或者采用日志进行记录
		System.out.println("请求登陆的user用户账户:" + username);
		System.out.println("请求登陆的user用户密码:" + password);
		// 将页面属性封装到user中
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		// 调用dao.impl
		IUserDao idao = new IUserDaoImpl();
		User user_feedback = idao.login(user);
		if (user_feedback != null) {
			System.out.println(user.toString() + "login success!");
			this.setId(user_feedback.getId());
			this.setUsername(user_feedback.getUsername());
			this.setPassword(user_feedback.getPassword());
			this.setQuestion(user_feedback.getQuestion());
			this.setAnswer(user_feedback.getAnswer());
			this.setEmail(user_feedback.getEmail());
			this.setPhone(user_feedback.getPhone());
			this.setCreateTime(user_feedback.getCreateTime());
			this.setUpdateTime(user_feedback.getUpdateTime());

			// 将user_feedback保持在session中
			System.out.println("向session中写入d当前的user");
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext ec = context.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(true);
			session.setAttribute("CurrentUser", user_feedback);
			System.out.println("写入session完成");

			return "u_home?faceRedirect=true";

		} else {
			System.out.println(user.toString() + "login faliure!");
			return "/WEB-INF/userPage/u_login-error?facesRedirect=true";
		}
	}

	public String doRegister() {
		System.out.println("请求注册的root用户账户:" + username);
		System.out.println("请求注册的root用户密码:" + password);
		// 将页面属性封装到user中
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setQuestion(question);
		user.setAnswer(answer);
		user.setEmail(email);
		user.setPhone(phone);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		// 调用dao.impl
		IUserDao idao = new IUserDaoImpl();
		User user_feedback = idao.register(user);
		if (user_feedback != null) {
			System.out.println(user.toString() + "注册成功");
			return "/WEB-INF/userPage/u_registration_success?facesRedirect=true";
		} else {
			System.out.println(user.toString() + "注册失败");
			return "/WEB-INF/userPage/u_registration_error?facesRedirect=true";
		}
	}
}
