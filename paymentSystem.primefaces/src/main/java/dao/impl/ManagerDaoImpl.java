/**
 * 下午3:14:46
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

import dao.IManagerDao;
import pojo.Manager;
import pojo.ManagerDetails;

import utils.db.MyHibernateSessionFactory;

/**
 * 结合hibernate与数据库进行交互 交互对象Manager表
 * 
 * @author cz 2018年4月28日下午3:14:46
 */
public class ManagerDaoImpl implements IManagerDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IManagerDao#checkManagerName(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午3:23:29
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Manager checkManagerName(String managerName) {
		logger.info("待检查的用户名:" + managerName);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Manager where managerName =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", managerName);
			List<Manager> list = query.list();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IManagerDao#selectLogin(java.lang.String, java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午3:23:32
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Manager selectLogin(String managerName, String md5Password) {
		logger.info("登录请求用户名:" + managerName);
		logger.info("登陆请求的密码:" + md5Password);
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Manager where managerName =:managerName and managerPassword=:md5Password";
			Query query = session.createQuery(hql);
			query.setParameter("managerName", managerName);
			query.setParameter("md5Password", md5Password);
			List<Manager> list = query.list();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IManagerDao#saveOrUpdate(pojo.Manager)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午3:23:35
	 */
	@Override
	public Integer saveOrUpdate(Manager manager) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			
			session.saveOrUpdate(manager);
			t.commit();
			logger.info(manager.toString() + "插入成功");
			return 1;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(manager.toString() + "插入失败");
			return 0;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IManagerDao#checkEmail(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午3:23:38
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Manager checkEmail(String email) {
		logger.info("待检查的邮箱:" + email);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from Manager where email =:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);

			List<Manager> list = query.list();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IManagerDao#checkManagerNameAndEmail(java.lang.String,
	 * java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午3:23:41
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Manager checkManagerNameAndEmail(String managerName, String email) {
		logger.info("邮箱：" + email + "身份：" + managerName);
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Manager Where managerName =:managerName and email =:email";
			Query query = session.createQuery(hql);
			query.setParameter("managerName", managerName);
			query.setParameter("email", email);
			List<Manager> list = query.list();
			tx.commit();
			logger.info("邮箱及身份查询,已查到");
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("邮箱及身份查询,未查到");
			return null;
		} finally {
			if (tx != null)
				tx = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IManagerDao#listManagerDetailsByManagerId(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午6:06:04
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerDetails> listManagerDetailsByManagerId(String managerId) {
		logger.info("待查找的管理员id:" + managerId);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from ManagerDetails where managerId =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", managerId);
			List<ManagerDetails> list = query.list();
			t.commit();
			return list;
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
	 * @see dao.IManagerDao#saveOrUpdateManagerDetails(pojo.ManagerDetails)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月28日下午6:56:32
	 */
	@Override
	public ManagerDetails saveOrUpdateManagerDetails(ManagerDetails managerDetails) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			
			session.saveOrUpdate(managerDetails);
			t.commit();
			logger.info(managerDetails.toString() + "插入成功");
			return managerDetails;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(managerDetails.toString() + "插入失败");
			return null;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see dao.IManagerDao#checkAccountId(java.lang.String)
	 * @author cz
	 * @time 2018年4月28日下午8:09:00
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ManagerDetails checkAccountId(String str) {
		logger.info("待检查的支付账户:" + str);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from ManagerDetails where email =:str";
			Query query = session.createQuery(hql);
			query.setParameter("str", str);
			List<ManagerDetails> list = query.list();
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
	 * @see dao.IManagerDao#checkExistByAccountIdAndManagerId(java.lang.String, java.lang.String)
	 * @author cz
	 * @time 2018年4月28日下午8:34:36
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ManagerDetails checkExistByAccountIdAndManagerId(String accountId, String managerId) {
		logger.info("账户Id：" + accountId + "管理者：" + managerId);
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from ManagerDetails Where managerId =:managerId and accountId =:accountId";
			Query query = session.createQuery(hql);
			query.setParameter("managerId", managerId);
			query.setParameter("accountId", accountId);
			List<ManagerDetails> list = query.list();
			tx.commit();
			logger.info("邮箱及身份查询,已查到");
			return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("邮箱及身份查询,未查到");
			return null;
		} finally {
			if (tx != null)
				tx = null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IManagerDao#listManagerDetailsByManagerIdAndActive(java.lang.String, int)
	 * @author cz
	 * @time 2018年4月29日上午11:59:17
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ManagerDetails> listManagerDetailsByManagerIdAndActive(String managerId, int code) {
		logger.info("管理Id：" + managerId + "支付账号激活状态：" + code);
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from ManagerDetails Where managerId =:managerId and active =:active";
			Query query = session.createQuery(hql);
			query.setParameter("managerId", managerId);
			query.setParameter("active", code);
			List<ManagerDetails> list = query.list();
			tx.commit();
			if(list.isEmpty()) {
				logger.error("没有找到该管理用户的已经激活的支付账号,未查到");
				return Lists.newArrayList();
			}else {
				logger.info("该管理用户已经激活的支付账号信息已查到");
				return list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("没有找到该管理用户的已经激活的支付账号,未查到");
			return Lists.newArrayList();
		} finally {
			if (tx != null)
				tx = null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IManagerDao#selectManagerDetailsByAccountId(java.lang.String)
	 * @author cz
	 * @time 2018年4月29日下午1:35:02
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ManagerDetails selectManagerDetailsByAccountId(String accountId) {
		logger.info("账户Id：" + accountId);
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from ManagerDetails Where accountId =:accountId";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", accountId);
			List<ManagerDetails> list = query.list();
			tx.commit();
			if(list.isEmpty()) {
				logger.info("根据accountId没有找到该管理用户,未查到");
				return null;
			}else {
				return list.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("根据accountId没有找到该管理用户,未查到");
			return null;
		} finally {
			if (tx != null)
				tx = null;
		}
	}

}
