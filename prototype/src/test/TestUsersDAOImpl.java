package test;

import org.junit.Assert;
import org.junit.Test;

import entity.Users;
import service.UsersDAO;
import service.impl.UsersDAOImpl;

public class TestUsersDAOImpl {
	@Test
	public void testUsersLogin() {
		Users u = new Users(1,"zhangsan","123456",0);
		System.out.println(u.getPassword()+u.getUsername());
		UsersDAO udao = new UsersDAOImpl();
		Assert.assertEquals(true,udao.usersLogin(u));
	}
}
