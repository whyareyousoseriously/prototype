/**
 * 用来通过页面来管理paymentSystem数据库的表的重新构建。
 * 还可以进一步改进
 */
package controller;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author cz
 *
 * 2018年2月2日下午4:42:10
 */

public class OriginBean {
	private String originID;
	private String dbName;
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	/**
	 * @return the originID
	 */
	public String getOriginID() {
		return originID;
	}
	/**
	 * @param originID the originID to set
	 */
	public void setOriginID(String originID) {
		this.originID = originID;
	}
	
	
	@Override
	public String toString() {
		return "OriginBean [originID=" + originID + ", dbName=" + dbName + "]";
	}
	@SuppressWarnings("unused")
	public  String createTable() {
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
		System.out.println("重新建表");
		return "create-success";
	}
	
}
