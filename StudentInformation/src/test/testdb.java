package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/*
 *test database
 *测试数据库的连接，查看是否可以成功通过hibernate4建表
 *@author cz
 *2017-12-22
 * */
public class testdb {
	@Test
	public void testSchemaExport() {
		//创建配置对象 create a configuration object;
		Configuration config = new Configuration().configure();
		//创建服务注册对象 create a service registry object;
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建session对象
		Session session = sessionFactory.getCurrentSession();
		//创建SchemaExport对象
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true);
	}
	
}
