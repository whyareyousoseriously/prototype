/**
 * 下午7:24:52
 * power
 */
package dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IPayInfoDetailsDao;
import pojo.PayInfoDetails;
import utils.db.MyHibernateSessionFactory;

/**
 * 
 * @author cz 2018年4月30日下午7:24:52
 */
public class PayInfoDetailsImpl implements IPayInfoDetailsDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PayInfoDetailsImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.IPayInfoDetailsDao#insert(pojo.PayInfoDetails)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午7:26:08
	 */
	@Override
	public PayInfoDetails insert(PayInfoDetails payInfoDetails) {
		// 创建一个事务
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			session.saveOrUpdate(payInfoDetails);
			t.commit();
			logger.info(payInfoDetails.toString() + "插入成功");
			return payInfoDetails;// 插入成功
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(payInfoDetails.toString() + "插入失败");
			return null;// 插入失败
		} finally {
			if (t != null) {
				t = null;
			}
		}
	}

}
