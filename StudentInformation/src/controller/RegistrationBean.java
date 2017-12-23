package controller;

import javax.faces.bean.ManagedBean;

import utils.FormUtils;

/*
 * registerationBean
 * @author cz
 * 2017-12-22
 * */
@ManagedBean
public class RegistrationBean {
	private String name;
	private String studentID;
	private String password;
	private String gender;
	private String brithday;
	private String grade;
	private String department;
	private String major;
	private String email;
	private String address;
	private String hobby;
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
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
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
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String doRegistration() {
		if(FormUtils.isAnyMissing(name,studentID,password,gender,brithday,grade,department,major,email,address,hobby)) {
			return "registration-error";
		}else {
			return "registration-success";
		}
	}
}
