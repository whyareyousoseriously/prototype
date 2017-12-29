package test;



import org.junit.Test;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;

public class testUsersDAOImpl {
	/*
	 * 用户接口实现类的测试类
	 * @author cz
	 * 2017-12-26
	 * */
	@Test
	public void testUsersDAOImpl_doLogin() {
		/*
		 * 测试controller.UsersBean.doLogin
		 * */
		Users u = new Users();
		u.setStudentID("320140938321");
		u.setPassword("1231231");
		UsersDAO udao = new UsersDAOImpl();
		Users u_feedback = udao.usersLogin(u);
		if(u_feedback!=null) {
			System.out.println("登陆成功");
		}else {
			System.out.println("登陆失败");
		}
	}
	
}
