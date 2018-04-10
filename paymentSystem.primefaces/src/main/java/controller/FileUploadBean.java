/**
 * 下午6:20:26
 * power
 */
package controller;


import java.util.Date;
import java.util.Set;

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
 * 文件上传的bean
 * @author cz 2018年4月8日下午6:20:26
 */

@ManagedBean
public class FileUploadBean {
	
	private Set<ExcelFile> excelFiles;
	
	

	public Set<ExcelFile> getExcelFiles() {
		//TODO 获取当前用户的ExcelFiles
		ExcelFileDAO efdao = new ExcelFileDAOImpl();
		excelFiles = efdao.ListExcelFilesByCurrentRootID(CurrentRoot.getCurrentRoot().getId());
		System.out.println("当前root用户为"+CurrentRoot.getCurrentRoot().toString());
		return excelFiles;
	}



	public void setExcelFiles(Set<ExcelFile> excelFiles) {
		this.excelFiles = excelFiles;
	}



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
