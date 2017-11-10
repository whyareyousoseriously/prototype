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

	// �û���½
	public String login() {
		UsersDAO udao = new UsersDAOImpl();
		if (udao.usersLogin(user) == 404) {// ��½ʧ��
			return "login_failure";
		}
		if (udao.usersLogin(user) == 0) {// ���û�Ȩ��Ϊ0
			// ��session�б����½�ɹ����û���
			session.setAttribute("loginUserName", user.getUsername());
			// ��session�б����û�Ȩ��
			session.setAttribute("loginPower", 0);
			return "login_success";
		}
		if (udao.usersLogin(user) == 1) {
			// ���û�Ȩ��Ϊ1
			// ��session�б����½�ɹ����û���
			session.setAttribute("loginUserName", user.getUsername());
			// ��session�б����û�Ȩ��
			session.setAttribute("loginPower", 1);
			return "login_success";
		}
		if (udao.usersLogin(user) == 2) {
			// ���û�Ȩ��Ϊ2
			// ��session�б����½�ɹ����û���
			session.setAttribute("loginUserName", user.getUsername());
			// ��session�б����û�Ȩ��
			session.setAttribute("loginPower", 2);
			return "login_success";
		}
		return "login_failure";

	}

	// �û�ע��
	@SkipValidation // ����validate��֤
	public String logout() {
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}

	/*
	 * //����֤
	 * 
	 * @Override public void validate() { //�û�������Ϊ��
	 * if("".equals(user.getUsername().trim())) {
	 * this.addFieldError("usernameError","�û�������Ϊ��"); }
	 * if(user.getPassword().length()<6) { this.addFieldError("passwordError",
	 * "���볤�Ȳ�����6λ"); } }
	 */
	@Override
	public Users getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}

}
