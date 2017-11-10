package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class MyHibernateSessionFactory {
	//�Ự��������
	private static SessionFactory sessionFactory;
	//���췽��˽�л�����֤����ģʽ
	private MyHibernateSessionFactory() {
		
	}
	//���еľ�̬��������ûỰ��������
	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null) {
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}else {
			return sessionFactory;
		}
	}
}