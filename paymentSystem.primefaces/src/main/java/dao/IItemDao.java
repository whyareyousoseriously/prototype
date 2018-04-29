/**
 * 下午11:06:42
 * power
 */
package dao;

import java.util.List;

import pojo.Item;

/**
 * 
 * @author cz
 * 2018年4月28日下午11:06:42
 */
public interface IItemDao {

	/**
	 * 保存一条支付条目
	 * @param item
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午11:08:07
	 */
	Item saveOrUpdate(Item item);

	/**
	 * 获取满足条件的集合
	 * @param managerId
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午11:28:53
	 */
	List<Item> listItemByManagerId(String managerId);

	/**
	 * 根据逐渐查询
	 * @param itemId
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午12:34:07
	 */
	Item selectItemByItemId(String itemId);

	/**
	 * 按主键删除
	 * @param itemId
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午1:20:50
	 */
	Item deleteItemByItemId(String itemId);

	/**
	 * @param code
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午6:59:32
	 */
	List<Item> listItemByStatus(int code);

}
