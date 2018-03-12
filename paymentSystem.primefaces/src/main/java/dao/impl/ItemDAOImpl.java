/**
 * 下午6:42:35
 * power
 */
package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ItemDAO;
import db.MyHibernateSessionFactory;
import entity.Item;
import utils.db.DBOperation;

/**
 * @author cz
 *
 * 2018年3月10日下午6:42:35
 */
public class ItemDAOImpl implements ItemDAO {

	/* (non-Javadoc)
	 * @see dao.ItemDAO#addItem(entity.Item)
	 * @author cz
	 * @time 2018年3月11日下午6:40:42
	 */
	@Override
	public String addItem(Item item) {
		// TODO Auto-generated method stub
		//调用工具类DBOperation
		String add_feedback = DBOperation.addData("item", item);
		if("add_success".equals(add_feedback)) {
			return "add_success";
		}else {
			return "add_failure";
		}
	}

	/* (non-Javadoc)
	 * @see dao.ItemDAO#deleteItemByCondition(java.lang.String, java.lang.String)
	 * @author cz
	 * @time 2018年3月11日下午6:40:42
	 */
	@Override
	public String deleteItemByCondition(String searchCondition, String searchValue) {
		// TODO Auto-generated method stub
		//创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "";
			
			hql = "from Item where "+searchCondition+"=:searchValue";
			Query query = session.createQuery(hql);
			query.setParameter("searchValue", searchValue);
			List<Item> list = query.list();
			
			if(list.size()>0) {
				System.out.println("根据"+searchCondition+"="+searchValue+"查询成功");
				System.out.println(list.toString());
				System.out.println("开始执行删除");
				for(Item i:list) {
					session.delete(i);
				}
				System.out.println("删除成功");
				t.commit();
				return "delete_success";
			}else {
				System.out.println("根据"+searchCondition+"="+searchValue+"查询失败");
				System.out.println("未找到无法执行删除操作");
				t.commit();
				return "delete-failure";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "delete_failure";
		}finally {
			if(t!=null)
				t = null;
		}
		
	}
	/* (non-Javadoc)
	 * @see dao.ItemDAO#updateItemByCondition(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @author cz
	 * @time 2018年3月11日下午7:50:42
	 */
	@Override
	public String updateItemByCondition(String searchCondition, String searchValue, String updateCondition,
			String updateValue) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.ItemDAO#getItemByCondition(java.lang.String, java.lang.String)
	 * @author cz
	 * @time 2018年3月11日下午7:53:44
	 */
	@Override
	public List<Item> getItemByCondition(String searchCondition, String searchValue) {
		// TODO Auto-generated method stub
		//调用工具类dbOperation
		List list = DBOperation.getDataByCondition("Item", searchCondition, searchValue);
		if(list.size()>0) {
			System.out.println("对表Item的查询操作成功"+"searchCondition="+searchCondition+"searchValue"+searchValue);
			return list;
		}else {
			System.out.println("对表Item的查询操作失败"+"searchCondition="+searchCondition+"searchValue"+searchValue);
			return null;
		}
	}
	
}
