package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ScoresDAO;
import db.MyHibernateSessionFactory;
import model.Scores;
import model.Users;

public class ScoresDAOImpl implements ScoresDAO {
	/*
	 * ScoresDAO的实现类
	 * @author cz
	 * 2017-12-28
	 * */
	@Override
	public String addScores(Scores scores) {
		// TODO Auto-generated method stub
		/*
		 * 分数添加
		 * 参数：添加的对象scores;
		 * 返回：函数执行的结果
		 * */
		//创建一个事务对象
		Transaction tx = null;
	
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(scores);
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
	public List<Scores> searchByCondition(String condition, String conditionValue) {
		// TODO Auto-generated method stub

		/*
		 * 分数查询,
		 * 参数：查询对象的属性之一condition 
		 * 参数：查询的条件的属性值conditonValue
		 * 返回：一个存有所有满足条件的List
		 * 补充：这样设计是为了查询的多样化。
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql ="";
			
			hql ="from Scores where "+condition+"=:conditionValue";
			Query query =session.createQuery(hql);
			query.setParameter("conditionValue", conditionValue);
			List<Scores> list = query.list();
			tx.commit();
			if(list.size()>0) {
				System.out.println("根据"+condition+"="+conditionValue+"查询成功");
				System.out.println(list.toString());
				return list;
			}else {
				System.out.println("根据"+condition+"="+conditionValue+"查询失败");
				return null;
			}
			/*if("name".equals(condition)) {
				hql = "from Scores where name=:name";
				Query query = session.createQuery(hql);
				query.setParameter("name", scores.getName());
				List<Scores> list = query.list();
				tx.commit();
				if(list.size()>0) {
					System.out.println("scores 查询成功");
					System.out.println(list.toString());
					
					return list;
				}else {
					System.out.println("scores 查询失败");
					
					return null;
				}
			}
			if("studentID".equals(condition)) {
				hql = "from Scores where studentID=:studentID";
				Query query = session.createQuery(hql);
				query.setParameter("studentID", scores.getStudentID());
				List<Scores> list = query.list();
				tx.commit();
				if(list.size()>0) {
					System.out.println("scores 查询成功");
					System.out.println(list.toString());
					
					return list;
				}else {
					System.out.println("scores 查询失败");
					
					return null;
				}
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	
	}

	@Override
	public String updateScoresByCondition(Scores scores, String which, String what) {
		// TODO Auto-generated method stub
		/*
		 * 分数更新
		 * 参数：更新对象的所有属性Scores
		 * 参数：更新那一个条记录查找的条件which
		 * 参数：更新一条记录的什么，初步设定，all代表更新所有属性，
		 * 返回：函数的执行的结果
		 * 补充：关于更新那一条记录的选择条件which目前只提供根据学号进行更新的操作，其他的都大同小异，节省时间就先不实现
		 * 补充：关于更新一条的什么
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "";
			if("all".equals(what)) {
				//更新一条记录的所有属性
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新所有属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//设定所有值
							s.setC(scores.getC());
							s.setDepartment(scores.getDepartment());
							s.setEnglish(scores.getEnglish());
							s.setGender(scores.getGender());
							s.setJava(scores.getJava());
							s.setJavaEE(scores.getJavaEE());
							s.setMajor(scores.getMajor());
							s.setMath(scores.getMath());
							s.setName(scores.getName());
							s.setOs(scores.getOs());
							s.setStudentID(scores.getStudentID());
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}
				}
			}
			if("c".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性c
							s.setC(scores.getC());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("department".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性department
							s.setDepartment(scores.getDepartment());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("english".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性english;
							s.setEnglish(scores.getEnglish());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("gender".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性gender
							s.setGender(scores.getGender());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("java".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性java
							s.setJava(scores.getJava());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("javaEE".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性javaEE
							s.setJavaEE(scores.getJavaEE());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("major".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性major
							s.setMajor(scores.getMajor());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("math".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性math
							s.setMath(scores.getMath());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("name".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性name
							s.setName(scores.getName());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("os".equals(what)) {
				//只更新属性c
				if("studentID".equals(which)) {
					//根据scores中的studentID属性更新数据。
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//找到符合条件的记录簇
						System.out.println("根据"+which+"找到符合条件的所有记录"+"然后更新"+what+"属性");
						System.out.println(list.toString());
						System.out.println("更新开始");
						for(Scores s:list) {
							//只设定属性os
							s.setOs(scores.getOs());
							
							session.merge(s);
						}
						System.out.println("更新结束");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("未找到符合条件的记录");
						tx.commit();
						return "update-error";
					}	
				}
			}
			tx.commit();
			//暂不提供studentID的修改服务
		}catch(Exception e) {
			e.printStackTrace();
			return "update-error";
		}
		return "update-error";
	}

	@Override
	public String deleteScoresByCondition(String condition, String conditionValue) {
		// TODO Auto-generated method stub
		/*
		 * 分数删除
		 * 参数：删除的条件的属性conditon
		 * 参数：删除的条件的属性的值conditionValue
		 * 返回：函数执行的结果
		 * @author cz
		 * 更新日期:2017-12-30
		 * 更新内容：修改基础接口，使接口功能更加人性化
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql ="";
			
			hql ="from Scores where "+condition+"=:conditionValue";
			Query query =session.createQuery(hql);
			query.setParameter("conditionValue", conditionValue);
			List<Scores> list = query.list();
			
			if(list.size()>0) {
				System.out.println("根据"+condition+"="+conditionValue+"查询成功");
				System.out.println(list.toString());
				System.out.println("开始执行删除");
				for(Scores s:list) {
					session.delete(s);
				}
				System.out.println("删除成功");
				tx.commit();
				return "delete-success";
			}else {
				System.out.println("根据"+condition+"="+conditionValue+"查询失败");
				System.out.println("未找到无法执行删除操作");
				tx.commit();
				return "delete-error";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "delete-error";
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
		/*try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "";
			if("name".equals(condition)) {
				hql = "from Scores where name=:name";
				Query query = session.createQuery(hql);
				query.setParameter("name", scores.getName());
				List<Scores> list = query.list();
				
				if(list.size()>0) {
					System.out.println("根据"+condition+"成功找到符合删除条件的");
					System.out.println(list.toString());
					System.out.println("执行删除");
					for(Scores s:list) {
						session.delete(s);
					}
					System.out.println("删除成功");
					tx.commit();
					return "delete-success";
				}else {
					System.out.println("未能找到符合条件"+condition+"的记录");
					System.out.println("删除失败");
					tx.commit();
					return "delte-error";
				}
			}
			if("studentID".equals(condition)) {
				hql = "from Scores where studentID=:studentID";
				Query query = session.createQuery(hql);
				query.setParameter("studentID", scores.getStudentID());
				List<Scores> list = query.list();
				
				if(list.size()>0) {
					System.out.println("根据"+condition+"成功找到符合删除条件的记录");
					System.out.println(list.toString());
					System.out.println("执行删除");
					for(Scores s:list) {
						session.delete(s);
					}
					tx.commit();
					System.out.println("删除成功");
					return "delete-success";
				}else {
					System.out.println("未找到符合条件"+condition+"的记录");
					System.out.println("删除失败");
					tx.commit();
					return "delte-error";
				}
			}
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			return "delete-error";
		}finally {
			if(tx!=null) {
				tx=null;
			}
		}
		
		return "delete-error";*/
	}

	@Override
	public List<Scores> getAllScores() {
		// TODO Auto-generated method stub
		/*
		 * 查询所有分数
		 * 参数：无
		 * 返回：数据库中所哟scores的集合
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Scores";
			Query query = session.createQuery(hql);
			List<Scores> list = query.list();
			System.out.println("找到所有数据");
			System.out.println(list.toString());
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
	public String updateScores(Scores scores) {
		// TODO Auto-generated method stub
		Transaction tx =null;
		try{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql="from Scores where studentID=:studentID";
			Query query = session.createQuery(hql);
			System.out.println(scores.getStudentID());
			query.setParameter("studentID", scores.getStudentID());
			List<Scores> list = query.list();
			if(list.size()>0) {
				//list不为空
				System.out.println("找到数据");
				System.out.println(list.toString());
				System.out.println("执行更新操作");
				//执行更新操作
				for(Scores s:list) {
					if(s!=null) {
						//u=user;//测试是不是赋值的问题影响的merge的执行
						
						s.setDepartment(scores.getDepartment());
						s.setGender(scores.getGender());
						s.setMajor(scores.getMajor());
						s.setName(scores.getName());
						s.setStudentID(scores.getStudentID());
						s.setC(scores.getC());
						s.setJava(scores.getJava());
						s.setJavaEE(scores.getJavaEE());
						s.setMath(scores.getMath());
						s.setOs(scores.getOs());
						s.setEnglish(scores.getEnglish());
						session.merge(s);
					}
				}
				tx.commit();
				return "update-success";
			}else {
				System.out.println("未找到数据");
				tx.commit();
				return "no-data";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "update-error";
		}finally {
			if(tx!=null) {
				tx = null;
			}
		}
	}

}
