/**
 * 下午5:30:20
 * power
 */
package controller.user;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import common.Const;
import common.ServerResponse;
import pojo.User;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * 用于用户登陆和注册页面的控制
 * 
 * @author cz 2018年4月25日下午5:30:20
 */
public class UserController {
	private String username;// 对应页面表格中username;
	private String password;// 对应页面表格中password;
	private String email;// 对应页面表格中的email;
	private String type;// 对应页面表格中的类型;目前没有用到
	private User user;// 用户存页面的值

	//以下两个变量用在密码修改页面
	private String passwordNew;//修改密码用的
	private String restPasswordCheckCode;//重置密码所需的验证码
	
	private User currentUser;// 当前session中的user
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// WCF Interface oriented programming
	//声明iUserService;
	private IUserService iUserService = new UserServiceImpl();

	
	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

	public String getRestPasswordCheckCode() {
		return restPasswordCheckCode;
	}

	public void setRestPasswordCheckCode(String restPasswordCheckCode) {
		this.restPasswordCheckCode = restPasswordCheckCode;
	}

	/**
	 * 查询当前用户信息
	 * @return
	 * @author cz
	 * @time 2018年4月26日下午12:18:49
	 */
	public User getCurrentUser() {
		user = (User)this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		if(user==null) {
			ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
		}
		ServerResponse.createBySuccessMessage("获取当前用户信息成功");
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public void showActive() {
		
		HttpSession session = this.getCurrentSession();
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user.getActive() == Const.UN_ACTIVE)
			ServerResponse.createByErrorMessage("你的账号未激活，请尽快激活，以免影响你的使用");
		else
			ServerResponse.createBySuccessMessage("你的账号已激活");
	}

	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		return session;
	}

	public String doLogin() {
		logger.info("请求登陆的账号:" + username);
		logger.info("请求登陆的密码" + password);
		// 进行数据封装
		user = new User();
		user.setUsername(username);
		user.setPassword(password);

		// 接收逻辑层返回值
		ServerResponse<User> response = iUserService.login(user.getUsername(), user.getPassword());

		if (response.isSuccess()) {
			
			logger.info("账号:" + this.user.getUsername() + "登陆成功");
			HttpSession session = this.getCurrentSession();
			session.setAttribute(Const.CURRENT_USER, response.getData());
			logger.info("当前user写入session成功");
			this.showActive();// 将账号状态加入FacesMessage
			return "u_home?faces-redirect=true";
		} else {
			
			logger.info("账号:" + this.user.getUsername() + "登陆失败");
			return "/WEB-INF/userPage/u_login-error?faces-redirect=true";
		}

	}

	public String doRegister() {

		logger.info("请求注册的账号:" + username);
		logger.info("请求注册的密码" + password);

		// 封装user
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		

		// 调用业务层处理数据
		ServerResponse<String> response = iUserService.register(user);

		if (response.isSuccess()) {
			
			return "/WEB-INF/userPage/u_registration_success?faces-redirect=true";
		}
			
		else {
			
			return "/WEB-INF/userPage/u_registration_error?faces-redirect=true";
		}
			
	}

	/**
	 * 用户登出
	 * 
	 * @author cz
	 * @time 2018年4月26日上午11:26:30
	 */
	public void logout() {
		// 获取当前session
		HttpSession session = this.getCurrentSession();
		// 移除当前session中的当前用户
		session.removeAttribute(Const.CURRENT_USER);
		ServerResponse.createBySuccessMessage("用户登出成功");
	}
	
	/**
	 * 
	 * @author cz
	 * @return 
	 * @time 2018年4月26日下午4:49:58
	 */
	public String sendCheckCodeEmail() {
		//发送验证码邮件
		ServerResponse<String> response = iUserService.sendCheckCodeEmail(username,email);
		if(response.isSuccess()) {
			//发送修改密码验证码成功
			return "resetPassword?faces-redirect=true";
		}else {
			return "sendCheckCodeEmail?faces-redirect=true";
		}
	}
	/**
	 * 重置密码通过邮箱
	 * @author cz
	 * @time 2018年4月26日下午12:20:08
	 */
	public String forgetRestPassword() {
		ServerResponse<String> response = iUserService.forgetRestPassword(username,passwordNew,restPasswordCheckCode);
		if(response.isSuccess()) {
			//提示信息，已经在forgetRestPassword方法中加了
			return "login?faces-redirect=true";
		}else {
			//什么也不做，停留在原界面
			return "resetPassword?faces-redirect=true";
		}
	}

}
