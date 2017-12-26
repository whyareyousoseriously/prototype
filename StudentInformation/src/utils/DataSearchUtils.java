package utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import model.Users;

public class DataSearchUtils {
	/*
	 * 从数据库中搜索数据
	 * @author cz
	 * */
	
	/*
	 * 查重数据
	 * @author cz
	 * */
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
	}
}
