package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.RootsDAO;
import db.MyHibernateSessionFactory;
import model.Users;

public class RootsDAOImpl implements RootsDAO{

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

	@Override
	public String addUser(Users user) {
		// TODO Auto-generated method stub
				/*
				 * �����ݿ������һ������
				 * */
				//����һ���������
				Transaction tx = null;
			
				try {
					Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
					tx = session.beginTransaction();
					session.save(user);
					tx.commit();
					return "add-success";
				}catch(Exception e) {
					e.printStackTrace();
					return "add-error";
				}finally {
					if(tx!=null) {
						tx=null;
					}
				}
				
			}



	@Override
	public Users searchOne(Users user) {
		// TODO Auto-generated method stub
		/*
		 * ���ݿ��в���һ����Ϣ
		 * 
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Users user_feedback = (Users)session.get(Users.class,user.getUid());
			tx.commit();
			return user_feedback;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null)
				tx = null;
		}
	}

	@Override
	public List<Users> searchManyByName(Users user) {
		// TODO Auto-generated method stub
		/*
		 * ��ѯ���ݿ��е���Ϣ��������
		 * */
		Transaction tx = null;
		
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Users where name=:name";
			Query query = session.createQuery(hql);
			query.setParameter("name", user.getName());
			List<Users> list = query.list();
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				System.out.println("��ѯ�ɹ�");
				System.out.println(list.toString());
				return list;
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

	@Override
	public List<Users> searchManyByStudentID(Users user) {
		// TODO Auto-generated method stub
		/*
		 * ��ѯ���ݿ��е���Ϣͨ��ѧ��
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Users where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", user.getStudentID());
			List<Users> list = query.list();
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				return list;
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

	@Override
	public String deleteUser(Users user, String condition) {
		// TODO Auto-generated method stub
		/*
		 * ɾ�����ݿ������ݸ���condition
		 * 2017-12-28
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			if(condition.equals("studentID")) {
				String hql = "from Users where studentID=:studentID";
				Query query = session.createQuery(hql);
				query.setParameter("studentID", user.getStudentID());
				List<Users> list = query.list();
				if(list.size()>0) {
					//list��Ϊ��
					System.out.println(list.toString());
					System.out.println("ִ��ɾ������");
					//ִ��ɾ������
					for(Users u:list) {
						session.delete(u);
					}
					tx.commit();
					return "delete-success";
				}else{
					return "no-data";
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return "delete-exception";
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
		return "exception";
	}

	@Override
	public String updateUser(Users user,String condition) {
		// TODO Auto-generated method stub
		/*
		 * �������ݿ��е�һ������
		 * @author cz
		 * 2017-12-28
		 * */
		Transaction tx =null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql="from Users where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", condition);
			List<Users> list = query.list();
			if(list.size()>0) {
				//list��Ϊ��
				System.out.println("�ҵ�����");
				System.out.println(list.toString());
				System.out.println("ִ�и��²���");
				//ִ�и��²���
				for(Users u:list) {
					if(u!=null) {
						u=user;
						session.merge(u);
					}
				}
				tx.commit();
				return "update-success";
			}else {
				return "no-data";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "update-exception";
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}
}
