/**
 * 下午8:27:08
 * power
 */
package test.utils.db;

import java.util.List;

import org.junit.Test;

import entity.Item;
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
}
