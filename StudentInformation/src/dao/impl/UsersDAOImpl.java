package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UsersDAO;
import db.MyHibernateSessionFactory;
import model.Users;


public class UsersDAOImpl implements UsersDAO{	
	
	/*
	 * �û���ؽӿ�ʵ����
	 * @author cz
	 * 2017-12-26
	 * */
	@Override
	public Users usersLogin(Users users) {
		// TODO Auto-generated method stub
		
		/*
		 * �û���¼����
		 * */
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
			System.out.println(list.toString());
			tx.commit();//�ύ����
			if(list.size()>0) {
				Users u = (Users)(list.get(0));
				
				return u;
			}else {
				return null;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public int usersRegister(Users users) {
		// TODO Auto-generated method stub
		/*
		 * �û�ע�᷽��
		 * */
		//����һ���������
		Transaction tx = null;
	
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(users);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			return 404;
		}finally {
			if(tx!=null) {
				tx=null;
			}
		}
		
		return 1;
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		/*
		 * ������ݿ��������û���Ϣ�ķ���
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			tx.commit();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

}
