package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UserDAO;
import db.MyHibernateSessionFactory;
import entity.User;
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
		Transaction tx = null;
		
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
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

}
