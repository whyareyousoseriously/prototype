package test;

import org.junit.Test;

import controller.UsersBean;

public class testUsersBean {
	@Test
	public void testUserBean_getPersonalScores() {
		/*
		 * ≤‚ ‘UsersBean.getPersonalScores
		 * */
		UsersBean u = new UsersBean();
		u.setStudentID("123456");
		System.out.println(u.getPersonalScores());
	}
}
