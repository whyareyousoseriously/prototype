/**
 * 下午7:01:50
 * power
 */
package test.ItemDAOImpl;

import java.util.List;

import org.junit.Test;

import dao.ItemDAO;
import dao.impl.ItemDAOImpl;
import entity.Item;

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
}
