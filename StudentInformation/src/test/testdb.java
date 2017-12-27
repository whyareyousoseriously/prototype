package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;
import utils.DateUtils;

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
	@Test
	public void testUsersDAOImpl() {
		Users users = new Users();
		users.setName("����");
		users.setStudentID("320140938321");
		users.setPassword("1231231");
		users.setGender("��");
		Date date = new Date();
		users.setBrithday(date);
		
		UsersDAO udao = new UsersDAOImpl();
		if(udao.usersRegister(users)==1) {
			System.out.println("��Ϣ¼�����ݿ�ɹ�");
		}else {
			System.out.println("��Ϣ¼��ʧ��");
		}
	}
}
