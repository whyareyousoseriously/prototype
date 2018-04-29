/**
 * 下午11:07:25
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

import dao.IItemDao;
import pojo.Item;

import utils.db.MyHibernateSessionFactory;

/**
 * 
 * @author cz 2018年4月28日下午11:07:25
 */
public class ItemDaoImpl implements IItemDao {

	private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IItemDao#saveOrUpdate(pojo.Item)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午11:08:25
	 */
	@Override
	public Item saveOrUpdate(Item item) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(item);
			t.commit();
			logger.info(item.toString() + "插入成功");
			return item;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(item.toString() + "插入失败");
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
	 * @see dao.IItemDao#listItemByManagerId(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午11:29:14
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> listItemByManagerId(String managerId) {
		logger.info("待查找的管理员id:" + managerId);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Item where managerId =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", managerId);
			List<Item> list = query.list();
			t.commit();
			if(list.isEmpty()) {
				logger.error("根据管理员id"+managerId+"查找失败");
				return Lists.newArrayList();
			}else {
				logger.info("根据管理员id"+managerId+"查找成功");
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

	/* (non-Javadoc)
	 * @see dao.IItemDao#selectItemByItemId(java.lang.String)
	 * @author cz
	 * @time 2018年4月29日上午12:34:40
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Item selectItemByItemId(String itemId) {
		logger.info("待查找的条目id:" + itemId);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Item where itemId =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", itemId);
			List<Item> list = query.list();
			t.commit();
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (t != null)
				t = null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IItemDao#deleteItemByItemId(java.lang.String)
	 * @author cz
	 * @time 2018年4月29日上午1:27:00
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Item deleteItemByItemId(String itemId) {
		logger.info("待查找的条目id:" + itemId);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Item where itemId =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", itemId);
			List<Item> list = query.list();
			//再删除
			session.delete(list.get(0));
			t.commit();
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (t != null)
				t = null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IItemDao#listItemByStatus(int)
	 * @author cz
	 * @time 2018年4月29日下午6:59:38
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> listItemByStatus(int code) {
		logger.info("待查找的条目状态:" + code);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Item where active =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", code);
			List<Item> list = query.list();
			t.commit();
			if(list.isEmpty()) {
				//查找失败
				logger.error("没有已经激活的可支付条目");
				return Lists.newArrayList();
			}else {
				logger.info("已找到激活的可支付条目");
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

	

}
