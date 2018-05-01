/**
 * 下午5:51:01
 * power
 */
package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import dao.IOrderDao;

import pojo.UserOrder;
import utils.db.MyHibernateSessionFactory;

/**
 * 
 * @author cz 2018年4月30日下午5:51:01
 */
public class OrderDaoImpl implements IOrderDao {
	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IOrderDao#selectByUserIdAndOrderNo(java.lang.String, java.lang.Long)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午5:52:59
	 */
	@Override
	public UserOrder selectByUserIdAndOrderNo(String userId, Long orderNo) {
		logger.info("待查找的用户id:" + userId);
		logger.info("待查找的订单号:" + orderNo);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from UserOrder where userId =:userId and orderNo =:orderNo";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("orderNo", orderNo);
			@SuppressWarnings("unchecked")
			List<UserOrder> list = query.list();
			t.commit();
			if (list.isEmpty()) {
				logger.error("未查找到满足条件的订单");
				return null;
			} else {
				logger.error("已查找到满足条件的订单");
				return list.get(0);
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
	 * @see dao.IOrderDao#selectByOrderNo(java.lang.Long)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午7:09:32
	 */
	@Override
	public UserOrder selectByOrderNo(Long orderNo) {

		logger.info("待查找的订单号:" + orderNo);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from UserOrder where orderNo =:orderNo";
			Query query = session.createQuery(hql);

			query.setParameter("orderNo", orderNo);
			@SuppressWarnings("unchecked")
			List<UserOrder> list = query.list();
			t.commit();
			if (list.isEmpty()) {
				logger.error("未查找到满足条件的订单");
				return null;
			} else {
				logger.error("已查找到满足条件的订单");
				return list.get(0);
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
	 * @see dao.IOrderDao#updateByPrimaryKeySelective(pojo.UserOrder)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午7:13:22
	 */
	@Override
	public UserOrder updateByPrimaryKeySelective(UserOrder userOrder) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(userOrder);
			t.commit();
			logger.info(userOrder.toString() + "插入成功");
			return userOrder;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(userOrder.toString() + "插入失败");
			return null;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IOrderDao#listUserOrderByUserId(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午9:00:47
	 */
	@Override
	public List<UserOrder> listUserOrderByUserId(String userId) {
		logger.info("待查找的订单的用户id:" + userId);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from UserOrder where userId =:userId";
			Query query = session.createQuery(hql);

			query.setParameter("userId", userId);
			@SuppressWarnings("unchecked")
			List<UserOrder> list = query.list();
			t.commit();
			if (list.isEmpty()) {
				logger.error("未查找到满足条件的订单");
				return Lists.newArrayList();
			} else {
				logger.error("已查找到满足条件的订单");
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Lists.newArrayList();
		} finally {
			if (t != null)
				t = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IOrderDao#listUserOrderByUserIdAndStatus(java.lang.String,
	 * java.lang.Integer)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午9:14:14
	 */
	@Override
	public List<UserOrder> listUserOrderByUserIdAndStatus(String userId, Integer condition) {
		logger.info("待查找的订单的用户id:" + userId);
		logger.info("待查找的用户订单状态" + condition);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from UserOrder where userId =:userId and status =:status";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("status", condition);
			@SuppressWarnings("unchecked")
			List<UserOrder> list = query.list();
			t.commit();
			if (list.isEmpty()) {
				logger.error("未查找到满足条件的订单");
				return Lists.newArrayList();
			} else {
				logger.error("已查找到满足条件的订单");
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Lists.newArrayList();
		} finally {
			if (t != null)
				t = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IOrderDao#saveOneUserOrder(pojo.UserOrder)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午9:55:27
	 */
	@Override
	public UserOrder saveOneUserOrder(UserOrder userOrder) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(userOrder);
			t.commit();
			logger.info(userOrder.toString() + "保存成功");
			return userOrder;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(userOrder.toString() + "保存失败");
			return null;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see dao.IOrderDao#selectById(java.lang.String)
	 * @author cz
	 * @time 2018年5月1日下午4:48:51
	 */
	@Override
	public UserOrder selectById(String id) {
		logger.info("待查找的订单的id:" + id);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from UserOrder where id =:id";
			Query query = session.createQuery(hql);

			query.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<UserOrder> list = query.list();
			t.commit();
			if (list.isEmpty()) {
				logger.error("未查找到满足条件的订单");
				return null;
			} else {
				logger.error("已查找到满足条件的订单");
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (t != null)
				t = null;
		}
	}

}
