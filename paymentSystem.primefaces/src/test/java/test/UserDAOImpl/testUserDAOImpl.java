/**
 * 
 */
package test.UserDAOImpl;

import org.junit.Test;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.User;

/**
 * @author cz
 *
 * 2018年2月2日下午4:21:39
 */
public class testUserDAOImpl {
	@Test
	public void testUserDAOImplUpdate() {
		User u = new User();
		u.setUid(2);
		u.setUsername("222222");
		u.setPassword("cz123456");
		u.setEmail("cz826033956@126.com");
		u.setActive("已激活");
		u.setType("0");
		u.setMailCode("d0030fe114bd496cb72cb31d391a9866");
		UserDAO udao = new UserDAOImpl();
		udao.findByMailCode(u.getMailCode());
		udao.update(u);
	}
}
