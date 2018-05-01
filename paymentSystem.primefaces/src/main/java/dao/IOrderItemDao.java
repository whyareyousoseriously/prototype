/**
 * 下午6:19:08
 * power
 */
package dao;

import java.util.List;

import pojo.UserOrderItem;

/**
 * 
 * @author cz
 * 2018年4月30日下午6:19:08
 */
public interface IOrderItemDao {
	/**
	 * 查询表UserOrderItem,
	 * @param userId
	 * @param orderNo
	 * @return list<UserOrderItem>
	 * @author cz
	 * @time 2018年4月30日下午6:24:11
	 */
	List<UserOrderItem> listByOrderNOAndUserId(String userId, Long orderNo);

	/**
	 * @param userOrderItem
	 * @return
	 * @author cz
	 * @time 2018年5月1日上午11:42:13
	 */
	UserOrderItem saveOneUserOrderItem(UserOrderItem userOrderItem);
}
