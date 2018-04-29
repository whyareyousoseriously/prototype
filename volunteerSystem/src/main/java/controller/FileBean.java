/**
 * 上午9:56:44
 * power
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;

import dao.IFileDao;
import dao.impl.IFileDaoImpl;
import pojo.FileStorage;
import pojo.User;
import pojo.VolunteerItem;
import utils.db.MyHibernateSessionFactory;

/**
 * 
 * @author cz 2018年4月23日上午9:56:44
 */
public class FileBean {
	private Set<FileStorage> files;

	public Set<FileStorage> listFiles() {
		// TODO 获取当前用户的Files
		IFileDao fdao = new IFileDaoImpl();
		User user = getCurrentUser();
		files = fdao.ListFilesByCurrentRootID(user.getId());
		return files;
	}

	public void setFiles(Set<FileStorage> files) {
		this.files = files;
	}

	public void handleFileUpload(FileUploadEvent event) {
		IFileDao fdao = new IFileDaoImpl();
		FileStorage file = new FileStorage();

		User user = getCurrentUser();
		VolunteerItem volunteerItem = getCurrentVolunteerItem();
		// 检查乱码问题，打印上传文件名字，看是否是乱码
		System.out.println(event.getFile().getFileName());
		file.setFileName(event.getFile().getFileName());
		file.setUserId(user.getId());
		file.setCreateTime(new Date());
		file.setUpdateTime(new Date());
		file.setVolunteerItemId(volunteerItem.getId());
		// TODO 二进制转换为blob
		Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		file.setFileDetails(Hibernate.getLobCreator(session).createBlob(event.getFile().getContents()));
		session.getTransaction().commit();
		fdao.saveFile(file);

		// TODO 上传到本地路径
		// 为其创建路径
		String path = "E:\\" + user.getId() + "\\";
		System.out.println(path);

		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.setWritable(true);
			fileDir.mkdirs();
		}
		File targetFile = new File(path, event.getFile().getFileName());

		try {
			OutputStream outputStream = new FileOutputStream(targetFile);
			outputStream.write(event.getFile().getContents());
			System.out.println("图片名称:" + event.getFile().getFileName() + "图片内容：" + event.getFile().getContentType());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		FacesMessage message = new FacesMessage(event.getFile().getFileName() + "上传成功");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private User getCurrentUser() {
		// 取出当前session中保存的当前用户
		System.out.println("读取当前session中当前的user");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		User user = (User) session.getAttribute("CurrentUser");
		System.out.println("读取session完成");
		return user;
	}

	private VolunteerItem getCurrentVolunteerItem() {
		// 取出当前session中保存的当前用户
		System.out.println("读取当前session中当前的volunteer");
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		VolunteerItem volunteerItem = (VolunteerItem) session.getAttribute("CurrentVolunteerItem");
		System.out.println("读取session完成");
		return volunteerItem;
	}

}
