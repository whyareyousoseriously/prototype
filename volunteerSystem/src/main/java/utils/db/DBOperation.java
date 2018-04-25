/**
 * 下午8:02:08
 * power
 */
package utils.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Creation;
import pojo.Donation;
import pojo.Permition;
import pojo.Root;
import pojo.User;
import pojo.VolunteerItem;
import utils.db.MyHibernateSessionFactory;

/**
 * 数据库操作类 操作数据库名称:volunteerSystem
 * 
 * @author cz
 *
 *         2018年3月11日下午8:02:08
 */
public class DBOperation {
	/**
	 * 登陆操作类
	 * 
	 * @param table
	 *            登陆的表
	 * @param o
	 *            登陆的Object
	 * @return 成功返回object,不成功返回null
	 * @author cz
	 * @time 2018年4月12日下午6:09:31
	 */
	public static Object login(String table, Object o) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			if ("User".equals(table)) {
				// 对User表进行登陆操作
				User user = (User) o;
				hql = "from User where username =:username and password =:password";
				Query query = session.createQuery(hql);
				query.setParameter("username", user.getUsername());
				query.setParameter("password", user.getPassword());
				List<Object> list = query.list();
				t.commit();
				if (list.size() > 0) {
					System.out.println(list.toString() + "登陆成功");
					return list.get(0);
				} else {
					System.out.println(list.toString() + "登陆失败");
					return null;
				}
			}
			if ("Root".equals(table)) {
				// 对Root表进行登陆操作
				Root root = (Root) o;
				hql = "from Root where rootname =:rootname and password =:password";
				Query query = session.createQuery(hql);
				query.setParameter("rootname", root.getRootname());
				query.setParameter("password", root.getPassword());
				List<Object> list = query.list();
				t.commit();
				if (list.size() > 0) {
					System.out.println(list.toString() + "登陆成功");
					return list.get(0);
				} else {
					System.out.println(list.toString() + "登陆失败");
					return null;
				}
			} else {
				t.commit();
				System.out.println("未找到登陆所需表");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (t != null)
				t = null;
		}

	}

	public static List listAllData(String table) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from " + table;
			Query query = session.createQuery(hql);
			List list = query.list();
			if (list.size() > 0) {
				System.out.println("成功获得满足条件的集合");
				t.commit();
				return list;
			} else {
				System.out.println("未找到满足条件的集合");
				t.commit();
				return new ArrayList();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		} finally {
			if (t != null)
				t = null;
		}

	}

	public static List listDataByTwoCondition(String table, String firstCondition, String firstConditionValue,
			String secondCondition, int secondConditionValue) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from " + table + " where " + firstCondition + "=:firstConditionValue and " + secondCondition
					+ "=:secondConditionValue";
			Query query = session.createQuery(hql);
			query.setParameter("firstConditionValue", firstConditionValue);
			query.setParameter("secondConditionValue", secondConditionValue);
			List list = query.list();
			if (list.size() > 0) {
				System.out.println("成功获得满足条件的集合");
				t.commit();
				return list;
			} else {
				System.out.println("未找到满足条件的集合");
				t.commit();
				return new ArrayList();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		} finally {
			if (t != null)
				t = null;
		}

	}
	public static List listDataByTwoCondition(String table, String firstCondition, String firstConditionValue,
			String secondCondition, String secondConditionValue) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from " + table + " where " + firstCondition + "=:firstConditionValue and " + secondCondition
					+ "=:secondConditionValue";
			Query query = session.createQuery(hql);
			query.setParameter("firstConditionValue", firstConditionValue);
			query.setParameter("secondConditionValue", secondConditionValue);
			List list = query.list();
			if (list.size() > 0) {
				System.out.println("成功获得满足条件的集合");
				t.commit();
				return list;
			} else {
				System.out.println("未找到满足条件的集合");
				t.commit();
				return new ArrayList();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		} finally {
			if (t != null)
				t = null;
		}

	}

	/**
	 * 对指定表进行查询操作
	 * 
	 * @param table
	 *            查询的表名
	 * @param searchCondition
	 *            查询的条件
	 * @param searchValue
	 *            查询的条件的值
	 * @return 满足条件的集合
	 * @author cz
	 * @time 2018年3月11日下午8:05:19
	 */
	public static List getDataByCondition(String table, String searchCondition, String searchValue) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from " + table + " where " + searchCondition + "=:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", searchValue);
			List list = query.list();
			if (list.size() > 0) {
				System.out.println("成功获得满足条件的集合");
				t.commit();
				return list;
			} else {
				System.out.println("未找到满足条件的集合");
				t.commit();
				return new ArrayList();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		} finally {
			if (t != null)
				t = null;
		}
	}

	/**
	 * 罗列所有满足条件的集合
	 * 
	 * @param table
	 * @param searchCondition
	 * @param status
	 * @return list集合
	 * @author cz
	 * @time 2018年4月13日上午11:13:44
	 */
	public static List listDataByStatus(String table, String searchCondition, Integer status) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			hql = "from " + table + " where " + searchCondition + "=:status";
			Query query = session.createQuery(hql);
			query.setParameter("status", status);
			List list = query.list();
			if (list.size() > 0) {
				System.out.println("成功获得满足条件的集合");
				t.commit();
				return list;
			} else {
				System.out.println("未找到满足条件的集合");
				t.commit();
				return new ArrayList();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		} finally {
			if (t != null)
				t = null;
		}
	}

	/**
	 * 对制定表进行插入操作，当然也可用用于注册的时候对表的插入
	 * 
	 * @param table
	 * @param data
	 * @return 成功插入返回插入的对象data,失败返回null
	 * @author cz
	 * @time 2018年4月12日下午6:14:41
	 */
	public static Object saveOrUpdateData(String table, Object data) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(data);
			t.commit();
			System.out.println(data.toString() + "插入成功");
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(data.toString() + "插入失败");
			return new Object();
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

	/**
	 * 对制定表根据指定条件进行删除操作
	 * 
	 * @param table
	 * @param searchCondition
	 * @param searchValue
	 * @return
	 * @author cz
	 * @time 2018年3月12日上午10:31:26
	 */
	public static String deleteDataByCondition(String table, String searchCondition, String searchValue) {

		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "from" + " " + table + " " + "where" + " " + searchCondition + "=:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", searchValue);
			System.out.println("开始对表" + table + "进行删除操作");
			System.out.println("删除条件:" + searchCondition + " " + "删除条件的值" + searchValue);
			if ("VolunteerItem".equals(table)) {
				List<VolunteerItem> list = query.list();
				if (list.size() > 0) {
					for (VolunteerItem i : list) {
						session.delete(i);
					}
					System.out.println("成功删除");
					t.commit();
					return "delete_success";
				} else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}

			}
			if ("Creation".equals(table)) {
				List<Creation> list = query.list();
				if (list.size() > 0) {
					for (Creation i : list) {
						session.delete(i);
						t.commit();
					}
					System.out.println("成功删除");
					return "delete_success";
				} else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}

			}
			if ("Donation".equals(table)) {
				List<Donation> list = query.list();
				if (list.size() > 0) {
					for (Donation i : list) {
						session.delete(i);
					}
					t.commit();
					System.out.println("成功删除");
					return "delete_success";
				} else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
			}
			if ("Permition".equals(table)) {
				List<Permition> list = query.list();
				if (list.size() > 0) {
					for (Permition i : list) {
						session.delete(i);
					}
					t.commit();
					System.out.println("成功删除");
					return "delete_success";
				} else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
			}
			if ("Root".equals(table)) {
				List<Root> list = query.list();
				if (list.size() > 0) {
					for (Root i : list) {
						session.delete(i);
					}
					t.commit();
					System.out.println("成功删除");
					return "delete_success";
				} else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
			}
			if ("User".equals(table)) {
				List<User> list = query.list();
				if (list.size() > 0) {
					for (User i : list) {
						session.delete(i);
					}
					t.commit();
					System.out.println("成功删除");
					return "delete_success";
				} else {
					System.out.println("未找到满足条件的值");
					return "delete_failure";
				}
			} else {
				System.out.println("未在数据库中找到表" + table + " 删除失败");
				return "delete_failure";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "delete_failure";
		} finally {
			if (t != null)
				t = null;
		}
	}

}
