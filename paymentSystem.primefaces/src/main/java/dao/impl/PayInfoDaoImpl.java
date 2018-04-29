/**
 * 下午5:09:11
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

import dao.IPayInfoDao;
import pojo.PayInfo;

import utils.db.MyHibernateSessionFactory;

/**
 * 
 * @author cz 2018年4月29日下午5:09:11
 */
public class PayInfoDaoImpl implements IPayInfoDao {

	private static final Logger logger = LoggerFactory.getLogger(PayInfoDaoImpl.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IPayInfoDao#saveOnePayInfo(pojo.PayInfo)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月29日下午5:11:07
	 */
	@Override
	public PayInfo saveOnePayInfo(PayInfo payInfo) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(payInfo);
			t.commit();
			logger.info(payInfo.toString() + "插入成功");
			return payInfo;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(payInfo.toString() + "插入失败");
			return null;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}
	/* (non-Javadoc)
	 * @see dao.IPayInfoDao#listPayInfoByUserId(java.lang.String)
	 * @author cz
	 * @time 2018年4月29日下午7:22:53
	 */
	@Override
	public List<PayInfo> listPayInfoByUserId(String userId) {
		logger.info("待检查的用户id:" + userId);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from PayInfo where userId =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", userId);
			@SuppressWarnings("unchecked")
			List<PayInfo> list = query.list();
			t.commit();
			if(list.isEmpty()) {
				//查找失败
				logger.info("没有找到该用户已经支付的项目");
				return Lists.newArrayList();
			}else {
				logger.info("已经找到该用户已经支付的项目");
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
	/* (non-Javadoc)
	 * @see dao.IPayInfoDao#listpayInfoByUserIdAndStatus(java.lang.String, int)
	 * @author cz
	 * @time 2018年4月29日下午9:03:38
	 */
	@Override
	public List<PayInfo> listpayInfoByUserIdAndStatus(String userId, int code) {
		logger.info("待检查的用户id:" + userId);
		logger.info("待检查的状态:" + code);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from PayInfo where userId =:userId and status =:code";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("code", code);
			@SuppressWarnings("unchecked")
			List<PayInfo> list = query.list();
			t.commit();
			if(list.isEmpty()) {
				//查找失败
				logger.info("没有找到该用户已经支付的项目");
				return Lists.newArrayList();
			}else {
				logger.info("已经找到该用户已经支付的项目");
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

}
