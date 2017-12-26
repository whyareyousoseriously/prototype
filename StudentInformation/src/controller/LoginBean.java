package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;

/*
 * Handle login related operations
 * ����͵�½�йصĲ���
 * @author cz
 * 2017-12-22
 * */

public class LoginBean {
	private String studentID;
	private String password;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String doLogin() {
		System.out.println(studentID);
		System.out.println(password);
		
		//��ҳ���ϵ�ѧ�ź������װ��Users����
		//The studentID and password on the page are encapsulated into users objects.
		Users users= new Users();
		users.setStudentID(studentID);
		users.setPassword(password);
		
		//����ӿڱ��WCF Interface oriented programming
		UsersDAO usersDAO = new UsersDAOImpl();
		//��ҳ���ϵ�Users���󴫵ݣ�Ȼ�����ݿ���в�ѯ
		
		if(usersDAO.usersLogin(users)==1) {
			System.out.println(users.getStudentID()+"��¼�ɹ�");
			return "login-success";
		}else {
			System.out.println(users.getStudentID()+"��¼ʧ��");
			
			return "login-error";
		}
	}
}
