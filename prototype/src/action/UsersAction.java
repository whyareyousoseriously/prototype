package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.UsersDAO;
import service.impl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();

	// 用户登陆
	public String login() {
		UsersDAO udao = new UsersDAOImpl();
		if (udao.usersLogin(user) == 404) {// 登陆失败
			return "login_failure";
		}
		if (udao.usersLogin(user) == 0) {// 若用户权限为0
			// 在session中保存登陆成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			// 在session中保存用户权限
			session.setAttribute("loginPower", 0);
			return "login_success";
		}
		if (udao.usersLogin(user) == 1) {
			// 若用户权限为1
			// 在session中保存登陆成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			// 在session中保存用户权限
			session.setAttribute("loginPower", 1);
			return "login_success";
		}
		if (udao.usersLogin(user) == 2) {
			// 若用户权限为2
			// 在session中保存登陆成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			// 在session中保存用户权限
			session.setAttribute("loginPower", 2);
			return "login_success";
		}
		return "login_failure";

	}

	// 用户注销
	@SkipValidation // 跳过validate验证
	public String logout() {
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}

	/*
	 * //表单验证
	 * 
	 * @Override public void validate() { //用户名不能为空
	 * if("".equals(user.getUsername().trim())) {
	 * this.addFieldError("usernameError","用户名不能为空"); }
	 * if(user.getPassword().length()<6) { this.addFieldError("passwordError",
	 * "密码长度不少于6位"); } }
	 */
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
