/**
 * 下午2:31:53
 * power
 */
package controller.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.Const;
import common.ServerResponse;
import controller.user.UserController;
import pojo.Manager;
import service.IManagerService;
import service.impl.ManagerServiceImpl;

/**
 * 
 * @author cz 2018年4月28日下午2:31:53
 */
@ManagedBean(name="managerController")
@SessionScoped
public class ManagerController {
	private String managerName;// 对应页面表格中managerName;
	private String managerPassword;// 对应页面表格中password;
	private String email;// 对应页面表格中的email;
	private String type;// 对应页面表格中的类型;目前没有用到

	private Manager manager;
	// 以下两个变量用在密码修改页面
	private String managerPasswordNew;// 修改密码用的
	private String restPasswordCheckCode;// 重置密码所需的验证码

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	private IManagerService iManagerService = new ManagerServiceImpl();

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
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

	
	public String getManagerPasswordNew() {
		return managerPasswordNew;
	}

	public void setManagerPasswordNew(String managerPasswordNew) {
		this.managerPasswordNew = managerPasswordNew;
	}

	public String getRestPasswordCheckCode() {
		return restPasswordCheckCode;
	}

	public void setRestPasswordCheckCode(String restPasswordCheckCode) {
		this.restPasswordCheckCode = restPasswordCheckCode;
	}

	private void showActive() {

		HttpSession session = this.getCurrentSession();
		Manager manager = (Manager) session.getAttribute(Const.CURRENT_MANAGER);
		if (manager.getActive() == Const.UN_ACTIVE)
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
		logger.info("请求登陆的账号:" + managerName);
		logger.info("请求登陆的密码" + managerPassword);
		// 进行数据封装
		manager = new Manager();
		manager.setManagerName(managerName);
		manager.setManagerPassword(managerPassword);

		// 接收逻辑层返回值
		ServerResponse<Manager> response = iManagerService.login(manager.getManagerName(),
				manager.getManagerPassword());

		if (response.isSuccess()) {

			logger.info("账号:" + this.manager.getManagerName() + "登陆成功");
			HttpSession session = this.getCurrentSession();
			session.setAttribute(Const.CURRENT_MANAGER, response.getData());
			logger.info("当前manager写入session成功");
			this.showActive();// 将账号状态加入FacesMessage
			return "/manager/r_home?faces-redirect=true";
		} else {

			logger.info("账号:" + this.manager.getManagerName() + "登陆失败");
			return "/WEB-INF/rootPage/r_login_error";
		}

	}

	public String doRegister() {

		logger.info("请求注册的账号:" + managerName);
		logger.info("请求注册的密码" + managerPassword);

		// 进行数据封装
		manager = new Manager();
		manager.setManagerName(managerName);
		manager.setManagerPassword(managerPassword);
		manager.setEmail(email);

		// 调用业务层处理数据
		ServerResponse<String> response = iManagerService.register(manager);

		if (response.isSuccess()) {

			return "/WEB-INF/rootPage/r_registration_success";
		}

		else {

			return "/WEB-INF/rootPage/r_registration_error";
		}

	}
	/**
	 * 用户登出
	 * 
	 * @author cz
	 * @time 2018年4月26日上午11:26:30
	 */
	public String logout() {
		// 获取当前session
		HttpSession session = this.getCurrentSession();
		// 移除当前session中的当前用户
		session.removeAttribute(Const.CURRENT_MANAGER);
		ServerResponse.createBySuccessMessage("用户登出成功");
		//返回初始页面
		return "/index?faces-redirect=true";
	}
	
	/**
	 * 
	 * @author cz
	 * @return 
	 * @time 2018年4月26日下午4:49:58
	 */
	public String sendCheckCodeEmail() {
		//发送验证码邮件
		ServerResponse<String> response = iManagerService.sendCheckCodeEmail(managerName,email);
		if(response.isSuccess()) {
			//发送修改密码验证码成功
			return "/manager/resetPassword?faces-redirect=true";
		}else {
			return "/manager/sendCheckCodeEmail?faces-redirect=true";
		}
	}
	/**
	 * 重置密码通过邮箱
	 * @author cz
	 * @time 2018年4月26日下午12:20:08
	 */
	public String forgetResetPassword() {
		ServerResponse<String> response = iManagerService.forgetResetPassword(managerName,managerPasswordNew,restPasswordCheckCode);
		if(response.isSuccess()) {
			//提示信息，已经在forgetRestPassword方法中加了
			return "/login?faces-redirect=true";
		}else {
			//什么也不做，停留在原界面
			return "/manager/resetPassword?faces-redirect=true";
		}
	}
	
	public String resetPasswordDirect() {
		Manager manager = (Manager) this.getCurrentSession().getAttribute(Const.CURRENT_MANAGER);
		if(manager==null) {
			//当前session中用户为空，所以未登录，跳转到登录页面
			ServerResponse.createByErrorMessage("用户未登录，无法进行直接修改密码操作,跳转登录界面");
			return "/login?faces-redirect=true";
		}
		ServerResponse<String> response = iManagerService.resetPasswordDirect(manager.getManagerName(),managerPasswordNew);
		if(response.isSuccess()) {
			return "/manager/r_home?faces-redirect=true";
		}else {
			return "/manager/resetPasswordDirect?faces-redirect=true";
		}
	}

}
