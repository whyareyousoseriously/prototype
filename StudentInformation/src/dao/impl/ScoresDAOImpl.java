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
	 * ScoresDAO��ʵ����
	 * @author cz
	 * 2017-12-28
	 * */
	@Override
	public String addScores(Scores scores) {
		// TODO Auto-generated method stub
		/*
		 * �������
		 * ��������ӵĶ���scores;
		 * ���أ�����ִ�еĽ��
		 * */
		//����һ���������
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
		 * ������ѯ,
		 * ��������ѯ���������֮һcondition 
		 * ��������ѯ������������ֵconditonValue
		 * ���أ�һ��������������������List
		 * ���䣺���������Ϊ�˲�ѯ�Ķ�������
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
				System.out.println("����"+condition+"="+conditionValue+"��ѯ�ɹ�");
				System.out.println(list.toString());
				return list;
			}else {
				System.out.println("����"+condition+"="+conditionValue+"��ѯʧ��");
				return null;
			}
			/*if("name".equals(condition)) {
				hql = "from Scores where name=:name";
				Query query = session.createQuery(hql);
				query.setParameter("name", scores.getName());
				List<Scores> list = query.list();
				tx.commit();
				if(list.size()>0) {
					System.out.println("scores ��ѯ�ɹ�");
					System.out.println(list.toString());
					
					return list;
				}else {
					System.out.println("scores ��ѯʧ��");
					
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
					System.out.println("scores ��ѯ�ɹ�");
					System.out.println(list.toString());
					
					return list;
				}else {
					System.out.println("scores ��ѯʧ��");
					
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
		 * ��������
		 * ���������¶������������Scores
		 * ������������һ������¼���ҵ�����which
		 * ����������һ����¼��ʲô�������趨��all��������������ԣ�
		 * ���أ�������ִ�еĽ��
		 * ���䣺���ڸ�����һ����¼��ѡ������whichĿǰֻ�ṩ����ѧ�Ž��и��µĲ����������Ķ���ͬС�죬��ʡʱ����Ȳ�ʵ��
		 * ���䣺���ڸ���һ����ʲô
		 * */
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			String hql = "";
			if("all".equals(what)) {
				//����һ����¼����������
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�������������");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//�趨����ֵ
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
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}
				}
			}
			if("c".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����c
							s.setC(scores.getC());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("department".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����department
							s.setDepartment(scores.getDepartment());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("english".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����english;
							s.setEnglish(scores.getEnglish());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("gender".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����gender
							s.setGender(scores.getGender());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("java".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����java
							s.setJava(scores.getJava());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("javaEE".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����javaEE
							s.setJavaEE(scores.getJavaEE());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("major".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����major
							s.setMajor(scores.getMajor());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("math".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����math
							s.setMath(scores.getMath());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("name".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����name
							s.setName(scores.getName());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			if("os".equals(what)) {
				//ֻ��������c
				if("studentID".equals(which)) {
					//����scores�е�studentID���Ը������ݡ�
					hql = "from Scores where studentID=:studentID";
					Query query = session.createQuery(hql);
					query.setParameter("studentID", scores.getStudentID());
					List<Scores> list = query.list();
					if(list.size()>0) {
						//�ҵ����������ļ�¼��
						System.out.println("����"+which+"�ҵ��������������м�¼"+"Ȼ�����"+what+"����");
						System.out.println(list.toString());
						System.out.println("���¿�ʼ");
						for(Scores s:list) {
							//ֻ�趨����os
							s.setOs(scores.getOs());
							
							session.merge(s);
						}
						System.out.println("���½���");
						tx.commit();
						return "update-success";
					}else {
						System.out.println("δ�ҵ����������ļ�¼");
						tx.commit();
						return "update-error";
					}	
				}
			}
			tx.commit();
			//�ݲ��ṩstudentID���޸ķ���
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
		 * ����ɾ��
		 * ������ɾ��������������conditon
		 * ������ɾ�������������Ե�ֵconditionValue
		 * ���أ�����ִ�еĽ��
		 * @author cz
		 * ��������:2017-12-30
		 * �������ݣ��޸Ļ����ӿڣ�ʹ�ӿڹ��ܸ������Ի�
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
				System.out.println("����"+condition+"="+conditionValue+"��ѯ�ɹ�");
				System.out.println(list.toString());
				System.out.println("��ʼִ��ɾ��");
				for(Scores s:list) {
					session.delete(s);
				}
				System.out.println("ɾ���ɹ�");
				tx.commit();
				return "delete-success";
			}else {
				System.out.println("����"+condition+"="+conditionValue+"��ѯʧ��");
				System.out.println("δ�ҵ��޷�ִ��ɾ������");
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
					System.out.println("����"+condition+"�ɹ��ҵ�����ɾ��������");
					System.out.println(list.toString());
					System.out.println("ִ��ɾ��");
					for(Scores s:list) {
						session.delete(s);
					}
					System.out.println("ɾ���ɹ�");
					tx.commit();
					return "delete-success";
				}else {
					System.out.println("δ���ҵ���������"+condition+"�ļ�¼");
					System.out.println("ɾ��ʧ��");
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
					System.out.println("����"+condition+"�ɹ��ҵ�����ɾ�������ļ�¼");
					System.out.println(list.toString());
					System.out.println("ִ��ɾ��");
					for(Scores s:list) {
						session.delete(s);
					}
					tx.commit();
					System.out.println("ɾ���ɹ�");
					return "delete-success";
				}else {
					System.out.println("δ�ҵ���������"+condition+"�ļ�¼");
					System.out.println("ɾ��ʧ��");
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
		 * ��ѯ���з���
		 * ��������
		 * ���أ����ݿ�����Ӵscores�ļ���
		 * */
		Transaction tx = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Scores";
			Query query = session.createQuery(hql);
			List<Scores> list = query.list();
			System.out.println("�ҵ���������");
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
				//list��Ϊ��
				System.out.println("�ҵ�����");
				System.out.println(list.toString());
				System.out.println("ִ�и��²���");
				//ִ�и��²���
				for(Scores s:list) {
					if(s!=null) {
						//u=user;//�����ǲ��Ǹ�ֵ������Ӱ���merge��ִ��
						
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
				System.out.println("δ�ҵ�����");
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
