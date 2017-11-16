package service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import imageVerification.ImageResult;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public int usersLogin(Users u) {
		// TODO Auto-generated method stub
		//事务对象
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=:username and password=:password";
			Query query = session.createQuery(hql);
			query.setParameter("username", u.getUsername());
			query.setParameter("password", u.getPassword());
			List<Users> list = query.list();
			tx.commit();//提交事务
			if(list.size()>0) {
				
				return list.get(0).getPower();
			}else {
				return 404;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return 404;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

	@Override
	public boolean imageVerification(String locationString,ImageResult imageResult) {
		// TODO Auto-generated method stub
		String[] resultArray = locationString.split(";");
    	int[][] array = new int[resultArray.length][2];
    	for (int i = 0; i<resultArray.length;i++) {
			String[] temp = resultArray[i].split(",");
			array[i][0] = Integer.parseInt(temp[0]) + 150 - 10;
			array[i][1] = Integer.parseInt(temp[1]) + 300;
		}
    	
    	for(int i=0;i<array.length;i++){
    		int location = location(array[i][1],array[i][0]);
    		System.out.println("解析后的坐标序号：" + location);
    		if(!imageResult.getKeySet().contains(location)){
    			return false;
    		}
    	}
    	
		return true;
	}
	private int location(int x, int y) {
		if(y >=0 && y<75){
			return xLocation(x);
		}else if(y >=75 && y<=150){
			return xLocation(x)+4;
		}else{
			// 脏数据
			return -1;
		}
	}

	private int xLocation(int x) {
		if(x >=0 && x<75){
			return 0;
		}else if(x >=75 && x<150){
			return 1;
		}else if(x >=150 && x<225){
			return 2;
		}else if(x >=225 && x<=300){
			return 3;
		}else{
			// 脏数据
			return -1;
		}
	}

}
