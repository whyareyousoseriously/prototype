/**
 * 下午7:01:50
 * power
 */
package test.ItemDAOImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import dao.ItemDAO;
import dao.impl.ItemDAOImpl;
import entity.Item;
import entity.Root;

/**
 * @author cz
 *
 * 2018年3月11日下午7:01:50
 */
public class testItemDAOImpl {

	/**
	 * 测试addItem方法
	 * @author cz
	 * @time 2018年3月11日下午7:02:49
	 */
	@Test
	public void testAddItem() {
		Item item = new Item();
		item.setName("网费");
		item.setValue(100);
		item.setUser(null);
		
		ItemDAO idao = new ItemDAOImpl();
		idao.addItem(item);
	}
	
	/**
	 * 测试deleteItemByCondition
	 * @author cz
	 * @time 2018年3月11日下午7:12:39
	 */
	@Test
	public void testDeleteItemByCondition() {
		String condition = "NAME";
		String conditionValue = "网费";
		ItemDAO idao = new ItemDAOImpl();
		idao.deleteItemByCondition(condition, conditionValue);
	}
	/**
	 * 测试getItemByCondition
	 * @author cz
	 * @time 2018年3月11日下午8:19:57
	 */
	@Test
	public void testGetItemByCondition() {
		String searchCondition = "NAME";
		String searchValue = "网费";
		ItemDAO idao = new ItemDAOImpl();
		List<Item> list = idao.getItemByCondition(searchCondition, searchValue);
		for(Item i : list) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testAddItem2() {
		Root root = new Root();
		root.setId("40288a87621986220162198624fb0000");
		root.setUsername("张三");
		root.setPassword("cz123456");
		Set<Root> tempRoot = new HashSet<Root>();
		tempRoot.add(root);
		Item item = new Item();
		item.setName("网费");
		item.setValue(100);
		item.setUser(null);
		item.setRoot(tempRoot);
		ItemDAO idao = new ItemDAOImpl();
		idao.addItem(item);
	}
}
