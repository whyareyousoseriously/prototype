package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;


/*
 *test database
 * 数据库表的初始化
 *@author cz
 *2018-1-23
 * */
public class testDB {
	@Test
	public void testSchemaExport() {
		//create a configuration object;
		Configuration config = new Configuration().configure();
		//create a service registry object;
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//session
		Session session = sessionFactory.getCurrentSession();
		//SchemaExport
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true);
	}
}
