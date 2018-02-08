package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UserDAO;
import db.MyHibernateSessionFactory;
import entity.User;
import utils.MailUtil;
/*
 * 用户逻辑层接口实现类
 * @author cz
 * @date 2018-1-23
 * */
public class UserDAOImpl implements UserDAO{

	public User userLogin(User user) {
		// TODO Auto-generated method stub
		/*
		 * 用户登录方法
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from User where username =:username and password =:password";
			Query query = session.createQuery(hql);
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			List<User> list = query.list();
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				User u = (User)(list.get(0));
				return u;
			}else {
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

	public boolean userRegister(User user) {
		// TODO Auto-generated method stub
		/*
		 * 用户注册方法
		 * */
		//将数据存入数据库
		Transaction tx = null;
	
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			//发送激活邮件
			MailUtil.sendMail(user.getEmail(),user.getMailCode());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see dao.UserDAO#findByMailCode(java.lang.String)
	 */
	@Override
	//根据邮箱激活码查询用户
	public User findByMailCode(String mailCode) {
		// TODO Auto-generated method stub
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
				System.out.println("已找到待激活用户");
				User u = (User)(list.get(0));
				return u;
			}else {
				System.out.println("未找到待激活用户，激活失败");
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
	 * @see dao.UserDAO#update(entity.User)
	 * @author cz
	 * @time 2018年2月2日下午4:07:05
	 */
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			System.out.println("开始执行激活操作,将激活后的数据写入数据库");
			System.out.println(user.toString());
			session.update(user);
			tx.commit();
			System.out.println("激活完成！");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(tx!=null)
				tx = null;
		}
	}

	

}
