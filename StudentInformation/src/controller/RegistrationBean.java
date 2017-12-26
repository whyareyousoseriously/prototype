package controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;
import utils.FormUtils;

/*
 * registerationBean
 * @author cz
 * 2017-12-22
 * */

public class RegistrationBean {
	private String name;
	private String studentID;
	private String password;
	private String gender;
	private Date brithday;
	private String grade;
	private String department;
	private String major;
	private String email;
	private String address;
	private List<String> hobby;
	private String otherHobby;
	
	public String getOtherHobby() {
		return otherHobby;
	}
	public void setOtherHobby(String otherHobby) {
		this.otherHobby = otherHobby;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	
	@Override
	public String toString() {
		return "RegistrationBean [name=" + name + ", studentID=" + studentID + ", password=" + password + ", gender="
				+ gender + ", brithday=" + brithday + ", grade=" + grade + ", department=" + department + ", major="
				+ major + ", email=" + email + ", address=" + address + ", hobby=" + hobby + ", otherHobby="
				+ otherHobby + "]";
	}
	public String doRegistration() {
		System.out.println(this.toString());
		
		//将页面上的注册信息封装成Users对象
		//The information of registration on the page are encapsulated into users objects.
		Users users= new Users();
		users.setName(name);
		users.setStudentID(studentID);
		users.setPassword(password);
		users.setGender(gender);
		users.setBrithday(brithday);
		users.setGrade(grade);
		users.setDepartment(department);
		users.setMajor(major);
		users.setEmail(email);
		users.setAddress(address);
		users.setHobby(hobby);
		
		UsersDAO udao = new UsersDAOImpl();
		if(udao.usersRegister(users)==1)
			return "registration-success";
		else
			return "registration-error";
	}
	/*
	 * 用来检测sturndenID是否合法
	 * 1.类型是否合法
	 * 2.数据库中是否已经存在
	 * */
	public void validatorStudentID() {
		
	}
}
