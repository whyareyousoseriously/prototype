package utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.db.MyHibernateSessionFactory;

/*
 * 数据查询
 * @author cz
 * @date 2018-1-23
 * */
public class DataSearchUtil {
	public static boolean duplicateCheckingData(String table,String condition,String conditionValue) {
		/*
		 * 数据查重
		 * @参数：待查重的一个条件 condition
		 * @参数：待查的一个条件的值conditionValue
		 * @参数：待查重的数据库中的表table
		 * @返回：布尔型值，反应重复或者不重复
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from "+table+" where "+condition+"=:value";
			Query query = session.createQuery(hql);
			query.setParameter("value", conditionValue);
			List list = query.list();
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
