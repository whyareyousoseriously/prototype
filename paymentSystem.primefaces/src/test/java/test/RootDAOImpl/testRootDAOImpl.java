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
	/**
	 * 测试对表Item和表root_item的联合查询
	 * @author cz
	 * @time 2018年3月13日下午7:37:52
	 */
	@Test
	public void testGettOwnItems() {
		RootDAO rdao = new RootDAOImpl();
		Root root = rdao.getOwnRoot("40288a876219902001621990b70c0000");
		System.out.println(root.getItem().toString());
	}
}
