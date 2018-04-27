package utils.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class MyHibernateSessionFactory {
	//会话工厂属性
	private static SessionFactory sessionFactory;
	//构造方法私有化保证单例模式
	private MyHibernateSessionFactory() {
		
	}
	//公有的静态方法获得会话工厂属性
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
