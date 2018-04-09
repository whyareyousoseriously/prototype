/**
 * 下午6:20:26
 * power
 */
package controller;


import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import dao.ExcelFileDAO;
import dao.impl.ExcelFileDAOImpl;
import db.MyHibernateSessionFactory;
import entity.ExcelFile;
import utils.CurrentRoot;

/**
 * 
 * @author cz 2018年4月8日下午6:20:26
 */

@ManagedBean
public class FileUploadBean {

	public void handleFileUpload(FileUploadEvent event) {
		ExcelFileDAO efdao = new ExcelFileDAOImpl();
		ExcelFile efile = new ExcelFile();
		efile.setFileName(event.getFile().getFileName());
		efile.setRoot(CurrentRoot.getCurrentRoot());
		efile.setCreateTime(new Date());
		//TODO 二进制转换为blob
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		efile.setFileDetails(Hibernate.getLobCreator(session).createBlob(event.getFile().getContents()));
		session.getTransaction().commit();
		efdao.addFile(efile);
		
		FacesMessage message = new FacesMessage(event.getFile().getFileName() + "上传成功");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
