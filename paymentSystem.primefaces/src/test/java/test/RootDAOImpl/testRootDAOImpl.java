/**
 * 下午5:19:09
 * power
 */
package test.RootDAOImpl;

import org.junit.Test;

import dao.RootDAO;
import dao.impl.RootDAOImpl;
import entity.Root;

/**
 * 测试RootDAOImpl
 * @author cz
 * 2018年3月12日下午5:19:09
 */
public class testRootDAOImpl {
	@Test
	public void testAddRoot() {
		Root root = new Root();
		root.setUsername("张三");
		root.setPassword("cz123456");
		RootDAO rdao = new RootDAOImpl();
		rdao.rootRegister(root);
	}
}
