package test;



import org.junit.Test;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;

public class testUsersDAOImpl {
	/*
	 * �û��ӿ�ʵ����Ĳ�����
	 * @author cz
	 * 2017-12-26
	 * */
	@Test
	public void testUsersDAOImpl_doLogin() {
		/*
		 * ����controller.UsersBean.doLogin
		 * */
		Users u = new Users();
		u.setStudentID("320140938321");
		u.setPassword("1231231");
		UsersDAO udao = new UsersDAOImpl();
		Users u_feedback = udao.usersLogin(u);
		if(u_feedback!=null) {
			System.out.println("��½�ɹ�");
		}else {
			System.out.println("��½ʧ��");
		}
	}
	
}
