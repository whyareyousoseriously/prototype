/**
 * 下午6:19:22
 * power
 */
package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IOrderItemDao;

import pojo.UserOrderItem;
import utils.db.MyHibernateSessionFactory;

/**
 * 
 * @author cz 2018年4月30日下午6:19:22
 */
public class OrderItemDaoImpl implements IOrderItemDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderItemDaoImpl.class);

	public List<UserOrderItem> listByOrderNOAndUserId(String userId, Long orderNo) {
		logger.info("待查找的用户id:" + userId);
		logger.info("待查找的订单号:" + orderNo);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from UserOrderItem where userId =:userId and orderNo =:orderNo";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("orderNo", orderNo);
			@SuppressWarnings("unchecked")
			List<UserOrderItem> list = query.list();
			t.commit();
			if (list.isEmpty()) {
				logger.error("未查找到满足条件的订单子");
				return null;
			} else {
				logger.error("已查找到满足条件的订单子");
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (t != null)
				t = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IOrderItemDao#saveOneUserOrderItem(pojo.UserOrderItem)
	 * 
	 * @author cz
	 * 
	 * @time 2018年5月1日上午11:42:26
	 */
	@Override
	public UserOrderItem saveOneUserOrderItem(UserOrderItem userOrderItem) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(userOrderItem);
			t.commit();
			logger.info(userOrderItem.toString() + "插入成功");
			return userOrderItem;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(userOrderItem.toString() + "插入失败");
			return null;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

}
