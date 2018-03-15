/**
 * 下午8:27:08
 * power
 */
package test.utils.db;

import java.util.List;

import org.junit.Test;

import entity.Item;
import entity.User;
import utils.db.DBOperation;

/**
 * 测试工具类DbOperation
 * @author cz
 *
 * 2018年3月11日下午8:27:08
 */
public class testDBOperation {
	@Test
	public void testGetDataByCondition() {
		String table = "Item";
		String searchCondition = "NAME";
		String searchValue = "网费";
		List<Item> list = DBOperation.getDataByCondition(table, searchCondition, searchValue);
		for(Item i:list) {
			System.out.println(i);
		}
	}
	/**
	 * 对插入表Item进行测试
	 * @author cz
	 * @time 2018年3月12日上午9:26:00
	 */
	@Test
	public void testAddData() {
		String table = "Item";
		Item item = new Item();
		item.setName("书本费");
		item.setValue(100);
		item.setUser(null);
		item.setStatuts("未缴费");
		String add_feedback = DBOperation.addData(table, item);
	}
	/**
	 * 对插入表User进行测试
	 * @author cz
	 * @time 2018年3月12日上午9:47:35
	 */
	@Test
	public void testAddData2() {
		String table = "User";
		User user = new User();
		user.setUsername("cz123456");
		user.setPassword("cz123456");
		String add_feedback = DBOperation.addData(table, user);
	}
	
	@Test
	public void testDeleteByCondition() {
		String table = "User";
		String searchCondition = "username";
		String searchValue = "cz123456";
		String delete_feedback = DBOperation.deleteDataByCondition(table, searchCondition, searchValue);
	}
}
