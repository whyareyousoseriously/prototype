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
	 * 从数据库中搜索数据
	 * @author cz
	 * */
	
	/*
	 * 查重数据
	 * @author cz
	 * 补充：该方法已被淘汰
	public static boolean duplicateChecking(String s) {
		//创建事务
		Transaction tx = null;
		String hql="";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", s);
			
			List<Users> list = query.list();
			tx.commit();//提交事务
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
	
	public static boolean duplicateCheckingData(String table,String condition) {
		/*
		 * 数据查重
		 * @参数：待查重的一个条件 condition
		 * @参数：待查重的数据库中的表table
		 * @返回：布尔型值，反应重复或者不重复
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from "+table+" where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", condition);
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
