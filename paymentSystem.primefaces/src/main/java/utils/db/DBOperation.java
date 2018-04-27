/**
 * 下午8:02:08
 * power
 */
package utils.db;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Item;
import entity.Root;
import entity.User;

/**
 * 数据库操作类
 * 操作数据库名称:paymentSystem
 * @author cz
 *
 * 2018年3月11日下午8:02:08
 */
public class DBOperation {
	/**
	 * 对指定表进行查询操作
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
				t.commit();
				return list;
			}else {
				System.out.println("未找到满足条件的集合");
				t.commit();
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
	/**
	 * 对指定表进行插入操作
	 * @param table 表名
	 * @param data 增加的数据
	 * @return
	 * @author cz
	 * @time 2018年3月12日上午9:17:42
	 */
	public static String addData(String table, Object data) {
		//创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.save(data);
			t.commit();
			return "add_success";
		}catch(Exception e) {
			e.printStackTrace();
			return "add_failure";
		}finally {
			if(t!=null) {
				t = null;
			}
		}
	}
	/**
	 * 对制定表根据指定条件进行删除操作
	 * 目前支持对表 User Root Item 三表进行操作
	 * @param table
	 * @param searchCondition
	 * @param searchValue
	 * @return
	 * @author cz
	 * @time 2018年3月12日上午10:31:26
	 */
	public static String deleteDataByCondition(String table, String searchCondition, String searchValue) {
		
		//创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "from"+" "+table+" "+"where"+" "+searchCondition+"=:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", searchValue);
			System.out.println("开始对表"+table+"进行删除操作");
			System.out.println("删除条件:"+searchCondition+" "+"删除条件的值"+searchValue);
			if("Item".equals(table)) {
				List<Item> list = query.list();
				if(list.size()>0) {
					for(Item i: list) {
						session.delete(i);	
					}
					System.out.println("成功删除");
					t.commit();
					return "delete_success";
				}else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
				
			}
			if("Root".equals(table)) {
				List<Root> list = query.list();
				if(list.size()>0) {
					for(Root i: list) {
						session.delete(i);	
						t.commit();
					}
					System.out.println("成功删除");
					return "delete_success";
				}else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
				
			}
			if("User".equals(table)) {
				List<User> list = query.list();
				if(list.size()>0) {
					for(User i: list) {
						session.delete(i);	
					}
					t.commit();
					System.out.println("成功删除");
					return "delete_success";
				}else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
			}else {
				System.out.println("未在数据库中找到表"+table+" 删除失败");
				return "delete_failure";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return "delete_failure";
		}finally {
			if(t!=null)
				t = null;
		}
	}
	
}
