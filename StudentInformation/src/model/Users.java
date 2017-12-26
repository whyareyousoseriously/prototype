package model;

import java.util.Date;
import java.util.List;

public class Users {
	private int uid;
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
	public Users() {}
	public Users(int uid, String name, String studentID, String password, String gender, Date brithday, String grade,
			String department, String major, String email, String address, List<String> hobby) {
		super();
		this.uid = uid;
		this.name = name;
		this.studentID = studentID;
		this.password = password;
		this.gender = gender;
		this.brithday = brithday;
		this.grade = grade;
		this.department = department;
		this.major = major;
		this.email = email;
		this.address = address;
		this.hobby = hobby;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	
	
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	
}
