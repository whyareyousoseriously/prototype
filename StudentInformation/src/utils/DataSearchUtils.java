package utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import model.Scores;
import model.Users;

public class DataSearchUtils {
	/*
	 * �����ݿ�����������
	 * @author cz
	 * */
	
	/*
	 * ��������
	 * @author cz
	 * ���䣺�÷����ѱ���̭
	public static boolean duplicateChecking(String s) {
		//��������
		Transaction tx = null;
		String hql="";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", s);
			
			List<Users> list = query.list();
			tx.commit();//�ύ����
			if(list.size()>0) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return true;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}*/
	
	public static boolean duplicateCheckingData(String table,String condition,String conditionValue) {
		/*
		 * ���ݲ���
		 * @�����������ص�һ������ condition
		 * @�����������һ��������ֵconditionValue
		 * @�����������ص����ݿ��еı�table
		 * @���أ�������ֵ����Ӧ�ظ����߲��ظ�
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from "+table+" where "+condition+"=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", conditionValue);
			List<Scores> list = query.list();
			tx.commit();
			if(list.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return true;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}
}
