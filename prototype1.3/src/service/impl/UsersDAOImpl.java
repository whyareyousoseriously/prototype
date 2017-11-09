package service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public boolean usersLogin(Users u) {
		// TODO Auto-generated method stub
		//事务对象
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=:username and password=:password";
			Query query = session.createQuery(hql);
			query.setParameter("username", u.getUsername());
			query.setParameter("password", u.getPassword());
			List list = query.list();
			tx.commit();//提交事务
			if(list.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

}
