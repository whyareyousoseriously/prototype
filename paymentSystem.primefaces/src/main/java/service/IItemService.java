/**
 * 下午10:59:07
 * power
 */
package service;

import java.util.List;

import common.ServerResponse;
import pojo.Item;

/**
 * 
 * @author cz
 * 2018年4月28日下午10:59:07
 */
public interface IItemService {

	/**
	 * 保存一条支付条目
	 * @param item
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午11:00:10
	 */
	ServerResponse<Item> saveItem(Item item);

	/**
	 * 获得managerId的支付条目
	 * @param managerId
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午11:26:57
	 */
	ServerResponse<List<Item>> listItemByManagerId(String managerId);

	/**
	 * 更新一条支付条目
	 * @param item
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午12:09:37
	 */
	ServerResponse<Item> updateItem(Item item);


	/**
	 * 删除一条支付条目
	 * @param item
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午1:25:29
	 */
	ServerResponse<Item> deleteItemByItemId(Item item);

	/**
	 * 上线支付条目，和跟新区分
	 * @param item
	 * @author cz
	 * @time 2018年4月29日下午12:24:30
	 */
	ServerResponse<Item> onLineItem(Item item);

	/**
	 * 下线支付条目
	 * @param item
	 * @author cz
	 * @time 2018年4月29日下午3:39:17
	 */
	ServerResponse<Item> outLineItem(Item item);

}
