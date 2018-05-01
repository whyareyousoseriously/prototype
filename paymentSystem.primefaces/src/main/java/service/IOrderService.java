/**
 * 下午5:47:00
 * power
 */
package service;

import java.util.List;
import java.util.Map;

import common.ServerResponse;
import pojo.UserOrder;
import pojo.UserOrderItem;


/**
 * 
 * @author cz
 * 2018年4月30日下午5:47:00
 */
public interface IOrderService {
	ServerResponse<Map<String,String>> pay(Long orderNo, String userId, String path);
	ServerResponse aliCallback(Map<String,String> params);
	ServerResponse queryOrderPayStatus(String userId,Long orderNo);
	/**
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午8:57:30
	 */
	ServerResponse<List<UserOrder>> listUserOrderByUserId(String userId);
	/**
	 * @param userId
	 * @param condition
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午9:13:00
	 */
	ServerResponse<List<UserOrder>> listUserOrderByUserIdAndStatus(String userId, Integer condition);
	/**
	 * @param userOrder
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午9:52:39
	 */
	ServerResponse<UserOrder> saveOneUserOrder(UserOrder userOrder);
	/**
	 * @param userOrderItem
	 * @return
	 * @author cz
	 * @time 2018年5月1日上午11:39:45
	 */
	ServerResponse<UserOrderItem> saveOneUserOrderItem(UserOrderItem userOrderItem);
	/**
	 * 取消一个订单
	 * @param toUserOrder
	 * @return
	 * @author cz
	 * @time 2018年5月1日下午4:09:54
	 */
	ServerResponse<UserOrder> cancelOneUserOrder(UserOrder toUserOrder);
}
