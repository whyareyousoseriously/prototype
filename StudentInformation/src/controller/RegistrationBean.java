package controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

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
	public String doRegistration(ActionEvent actionEvent) {
		/*if(FormUtils.isAnyMissing(name,studentID,password,gender,grade,department,major,email,address)) {
			return "registration-error";
		}else {
			return "registration-success";
		}*/
		return "registration-success";
	}
	public String doRegistration() {
		/*if(FormUtils.isAnyMissing(name,studentID,password,gender,grade,department,major,email,address)) {
			return "registration-error";
		}else {
			return "registration-success";
		}*/
		return "registration-success";
	}
	public String make() {
		return "index";
	}
}
