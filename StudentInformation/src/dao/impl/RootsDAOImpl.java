package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.RootsDAO;
import dao.UsersDAO;
import db.MyHibernateSessionFactory;
import model.Roots;
import model.Users;

public class RootsDAOImpl implements RootsDAO{

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		/*
		 * 获得数据库中所有用户信息的方法
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			tx.commit();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	
	}

	@Override
	public String addUser(Users user) {
		// TODO Auto-generated method stub
				/*
				 * 向数据库中添加一条数据
				 * */
				//创建一个事务对象
				Transaction tx = null;
			
				try {
					Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
					tx = session.beginTransaction();
					session.save(user);
					tx.commit();
					return "add-success";
				}catch(Exception e) {
					e.printStackTrace();
					return "add-error";
				}finally {
					if(tx!=null) {
						tx=null;
					}
				}
				
			}



	@Override
	public Users searchOne(Users user) {
		// TODO Auto-generated method stub
		/*
		 * 数据库中查找一条信息
		 * 
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Users user_feedback = (Users)session.get(Users.class,user.getUid());
			tx.commit();
			return user_feedback;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null)
				tx = null;
		}
	}

	@Override
	public List<Users> searchManyByName(Users user) {
		// TODO Auto-generated method stub
		/*
		 * 查询数据库中的信息根据名字
		 * */
		Transaction tx = null;
		
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Users where name=:name";
			Query query = session.createQuery(hql);
			query.setParameter("name", user.getName());
			List<Users> list = query.list();
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				System.out.println("查询成功");
				System.out.println(list.toString());
				return list;
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

	@Override
	public List<Users> searchManyByStudentID(Users user) {
		// TODO Auto-generated method stub
		/*
		 * 查询数据库中的信息通过学号
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Users where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", user.getStudentID());
			List<Users> list = query.list();
			System.out.println(list.toString());
			tx.commit();
			if(list.size()>0) {
				return list;
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

	@Override
	public String deleteUser(Users user, String condition) {
		// TODO Auto-generated method stub
		/*
		 * 删除数据库中数据根据condition
		 * 2017-12-28
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			if(condition.equals("studentID")) {
				String hql = "from Users where studentID=:studentID";
				Query query = session.createQuery(hql);
				query.setParameter("studentID", user.getStudentID());
				List<Users> list = query.list();
				if(list.size()>0) {
					//list不为空
					System.out.println(list.toString());
					System.out.println("执行删除操作");
					//执行删除操作
					for(Users u:list) {
						session.delete(u);
					}
					tx.commit();
					return "delete-success";
				}else{
					return "no-data";
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return "delete-exception";
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
		return "exception";
	}

	@Override
	public String updateUser(Users user,String condition) {
		// TODO Auto-generated method stub
		/*
		 * 更新数据库中的一条数据
		 * @author cz
		 * 2017-12-28
		 * */
		Transaction tx =null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql="from Users where studentID=:studentID";
			Query query = session.createQuery(hql);
			query.setParameter("studentID", condition);
			List<Users> list = query.list();
			if(list.size()>0) {
				//list不为空
				System.out.println("找到数据");
				System.out.println(list.toString());
				System.out.println("执行更新操作");
				//执行更新操作
				for(Users u:list) {
					if(u!=null) {
						//u=user;//测试是不是赋值的问题影响的merge的执行
						u.setAddress(user.getAddress());
						u.setBrithday(user.getBrithday());
						u.setDepartment(user.getDepartment());
						u.setEmail(user.getEmail());
						u.setGender(user.getGender());
						u.setGrade(user.getGrade());
						u.setHobbys(user.getHobbys());
						u.setMajor(user.getMajor());
						u.setName(user.getName());
						u.setPassword(user.getPassword());
						u.setStudentID(user.getStudentID());
						session.merge(u);
					}
				}
				tx.commit();
				return "update-success";
			}else {
				return "no-data";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "update-exception";
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public Roots RootsLogin(Roots root) {
		// TODO Auto-generated method stub
		/*
		 * root用户登录方法
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Roots where rootID=:rootID and password=:password";
			Query query = session.createQuery(hql);
			query.setParameter("rootID", root.getRootID());
			query.setParameter("password", root.getPassword());
			List<Roots> list = query.list();
			System.out.println(list.toString());
			tx.commit();//提交事务
			if(list.size()>0) {
				Roots r = (Roots)(list.get(0));
				
				return r;
			}else {
				return null;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public Roots RootsRegister(Roots root) {
		// TODO Auto-generated method stub
		/*
		 * root用户注册方法
		 * */
		//创建一个事务对象
		Transaction tx = null;
	
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(root);
			tx.commit();
			return root;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null) {
				tx=null;
			}
		}
		
	}

}
