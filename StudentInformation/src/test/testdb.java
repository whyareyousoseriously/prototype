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
 *�������ݿ�����ӣ��鿴�Ƿ���Գɹ�ͨ��hibernate4����
 *@author cz
 *2017-12-22
 * */
public class testdb {
	@Test
	public void testSchemaExport() {
		//�������ö��� create a configuration object;
		Configuration config = new Configuration().configure();
		//��������ע����� create a service registry object;
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//����sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//����session����
		Session session = sessionFactory.getCurrentSession();
		//����SchemaExport����
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true);
	}
	
}
