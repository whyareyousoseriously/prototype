/**
 * 下午5:56:16
 * power
 */
package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IUserDao;
import pojo.User;
import pojo.UserDetails;
import utils.db.MyHibernateSessionFactory;

/**
 * 结合hibernate与数据库进行交互 交互对象User表
 * 
 * @author cz 2018年4月25日下午5:56:16
 */
public class UserDaoImpl implements IUserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@SuppressWarnings("unchecked")
	public User checkUsername(String username) {
		logger.info("待检查的用户名:"+username);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from User where username =:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", username);

			List<User> list = query.list();
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

	@SuppressWarnings("unchecked")
	public User selectLogin(String username, String md5Password) {
		logger.info("登录请求用户名:"+username);
		logger.info("登陆请求的密码:"+md5Password);
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from User where username =:username and password=:md5Password";
			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("md5Password", md5Password);
			List<User> list = query.list();
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
	 * @see dao.IUserDao#checkEmail(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月25日下午7:41:24
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User checkEmail(String email) {
		logger.info("待检查的邮箱:"+email);
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from User where email =:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);

			List<User> list = query.list();
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
	 * @see dao.IUserDao#saveOrUpdate(pojo.User)
	 * @author cz
	 * @time 2018年4月25日下午9:26:55
	 */
	@Override
	public Integer saveOrUpdate(User user) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(user);
			t.commit();
			logger.info(user.toString() + "插入成功");
			return 1;//插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(user.toString() + "插入失败");
			return 0;//插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see dao.IUserDao#findByMailCode(java.lang.String)
	 * @author cz
	 * @time 2018年4月25日下午9:27:34
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findByMailCode(String mailCode) {
		logger.info("待查找的邮箱验证码:"+mailCode);
		Transaction tx =null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from User Where mailCode =:mailCode";
			Query query = session.createQuery(hql);
			query.setParameter("mailCode", mailCode);
			List<User> list = query.list();
			
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				logger.info("已找到待激活用户");
				User u = (User)(list.get(0));
				return u;
			}else {
				logger.info("未找到待激活用户，激活失败");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null)
				tx = null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IUserDao#checkUsernameAndEmail(java.lang.String, java.lang.String)
	 * @author cz
	 * @time 2018年4月26日下午5:00:01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User checkUsernameAndEmail(String username, String email) {
		logger.info("邮箱："+email+"身份："+username);
		Transaction tx =null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from User Where username =:username and email =:email";
			Query query = session.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("email", email);
			List<User> list = query.list();
			tx.commit();
			logger.info("邮箱及身份查询,已查到");
			return list.get(0);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("邮箱及身份查询,未查到");
			return null;
		}finally {
			if(tx!=null)
				tx = null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IUserDao#listUserDetailsByUserId(java.lang.String)
	 * @author cz
	 * @time 2018年4月26日下午7:09:27
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails listUserDetailsByUserId(String userId) {
		logger.info("待查找的userId:"+userId);
		Transaction tx =null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from UserDetails Where userId =:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			List<UserDetails> list = query.list();
			tx.commit();
			if(list.size()>0) {
				logger.info("已找到用户的详细信息");
				return (UserDetails)list.get(0);
			}else {
				logger.info("未找到用户的详细信息");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null)
				tx = null;
		}
		
	}

}
