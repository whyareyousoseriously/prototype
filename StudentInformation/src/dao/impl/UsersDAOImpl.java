package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UsersDAO;
import db.MyHibernateSessionFactory;
import model.Users;

public class UsersDAOImpl implements UsersDAO{

	@Override
	public int usersLogin(Users users) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where studentID=:studentID and password=:password";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", users.getStudentID());
			query.setParameter("password", users.getPassword());
			List<Users> list = query.list();
			tx.commit();//提交事务
			if(list.size()>0) {
				return 1;
			}else {
				return 404;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return 404;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

}
