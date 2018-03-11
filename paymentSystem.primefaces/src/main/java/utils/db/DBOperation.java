/**
 * 下午8:02:08
 * power
 */
package utils.db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

/**
 * 数据库操作类
 * 操作数据库名称:paymentSystem
 * @author cz
 *
 * 2018年3月11日下午8:02:08
 */
public class DBOperation {
	/**
	 * @param table 查询的表名
	 * @param searchCondition 查询的条件
	 * @param searchValue 查询的条件的值
	 * @return 满足条件的集合
	 * @author cz
	 * @time 2018年3月11日下午8:05:19
	 */
	public static List getDataByCondition(String table, String searchCondition, String searchValue) {
		//创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from "+table+" where "+searchCondition+"=:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", searchValue);
			List list = query.list();
			if(list.size()>0) {
				System.out.println("成功获得满足条件的集合");
				return list;
			}else {
				System.out.println("未找到满足条件的集合");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(t!=null)
				t = null;
		}	
	}
}
