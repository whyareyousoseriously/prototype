package test;

import org.junit.Test;

import entity.Users;
import service.impl.UsersDAOImpl;

public class TestUsersDAOImpl {
	@Test
	public void TestUsersRegister() {
		Users u = new Users(1,"lisi","12345678","cz123456@123.com",1);
		UsersDAOImpl udao = new UsersDAOImpl();
		udao.usersRegister(u);
	}
}
