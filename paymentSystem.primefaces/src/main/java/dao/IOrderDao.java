/**
 * 下午5:50:40
 * power
 */
package dao;

import java.util.List;

import pojo.UserOrder;

/**
 * 
 * @author cz
 * 2018年4月30日下午5:50:40
 */
public interface IOrderDao {
	
	UserOrder selectByUserIdAndOrderNo(String userId,Long orderNo);

	/**
	 * @param orderNo
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午7:09:19
	 */
	UserOrder selectByOrderNo(Long orderNo);

	/**
	 * @param userOrder
	 * @author cz
	 * @time 2018年4月30日下午7:12:50
	 */
	UserOrder updateByPrimaryKeySelective(UserOrder userOrder);

	/**
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午9:00:39
	 */
	List<UserOrder> listUserOrderByUserId(String userId);

	/**
	 * @param userId
	 * @param condition
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午9:14:02
	 */
	List<UserOrder> listUserOrderByUserIdAndStatus(String userId, Integer condition);

	/**
	 * @param userOrder
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午9:54:47
	 */
	UserOrder saveOneUserOrder(UserOrder userOrder);

	/**
	 * @param id
	 * @return
	 * @author cz
	 * @time 2018年5月1日下午4:48:39
	 */
	UserOrder selectById(String id);

}
