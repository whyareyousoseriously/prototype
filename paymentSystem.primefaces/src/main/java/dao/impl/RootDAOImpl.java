/**
 * 
 */
package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.RootDAO;
import db.MyHibernateSessionFactory;
import entity.Item;
import entity.Root;
import utils.CurrentRoot;
import utils.MailUtil;

/**
 * @author cz
 * root用户的DAO实现层
 * 2018年3月9日下午9:07:09
 */
public class RootDAOImpl implements RootDAO {
	public Root rootLogin(Root root) {
		// TODO Auto-generated method stub
		/*
		 * root用户登录方法
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Root where username =:username and password =:password";
			Query query = session.createQuery(hql);
			query.setParameter("username", root.getUsername());
			query.setParameter("password", root.getPassword());
			List<Root> list = query.list();
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				Root u = (Root)(list.get(0));
				return u;
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

	public boolean rootRegister(Root root) {
		// TODO Auto-generated method stub
		/*
		 * root用户注册方法
		 * */
		//将数据存入数据库
		Transaction tx = null;
	
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(root);
			tx.commit();
			//发送激活邮件
			MailUtil.sendMail(root.getEmail(),root.getMailCode());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see dao.RootDAO#findByMailCode(java.lang.String)
	 */
	
	//根据邮箱激活码查询用户
	public Root findByMailCode(String mailCode) {
		// TODO Auto-generated method stub
		Transaction tx =null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Root Where mailCode =:mailCode";
			Query query = session.createQuery(hql);
			query.setParameter("mailCode", mailCode);
			List<Root> list = query.list();
			
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				System.out.println("已找到待激活用户");
				Root u = (Root)(list.get(0));
				return u;
			}else {
				System.out.println("未找到待激活用户，激活失败");
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

	/* (non-Javadoc)
	 * @see dao.RootDAO#update(entity.Root)
	 * @author cz
	 * @time 2018年3月9日下午9:07:09
	 */
	public void update(Root root) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			System.out.println("开始执行激活操作,将激活后的数据写入数据库");
			System.out.println(root.toString());
			session.update(root);
			tx.commit();
			System.out.println("激活完成！");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(tx!=null)
				tx = null;
		}
	}
	/* (non-Javadoc)
	 * @see dao.RootDAO#getOwnItems()
	 * @author cz
	 * @time 2018年3月13日下午7:19:00
	 */
	@Override
	public Root getOwnRoot(String id) {
		// TODO Auto-generated method stub
		Transaction t = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			t = session.beginTransaction();
			String hql = "from Root where id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List list = query.list();
			if(list.size()>0) {
				System.out.println("成功获取");
				System.out.println(list.toString());
				Root root = (Root) list.get(0);
				return root;
			}else {
				System.out.println("获取失败");
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
