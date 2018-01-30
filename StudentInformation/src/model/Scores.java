package model;

public class Scores {
	private int uid;
	private String name;
	private String studentID;
	private String gender;
	private String department;
	private String major;
	private String math;
	private String english;
	private String java;
	private String c;
	private String os;
	private String javaEE;
	
	
	
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



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
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



	public String getMath() {
		return math;
	}



	public void setMath(String math) {
		this.math = math;
	}



	public String getEnglish() {
		return english;
	}



	public void setEnglish(String english) {
		this.english = english;
	}



	public String getJava() {
		return java;
	}



	public void setJava(String java) {
		this.java = java;
	}



	public String getC() {
		return c;
	}



	public void setC(String c) {
		this.c = c;
	}



	public String getOs() {
		return os;
	}



	public void setOs(String os) {
		this.os = os;
	}



	public String getJavaEE() {
		return javaEE;
	}



	public void setJavaEE(String javaEE) {
		this.javaEE = javaEE;
	}



	@Override
	public String toString() {
		return "Scores [name=" + name + ", studentID=" + studentID + ", gender=" + gender + ", department=" + department
				+ ", major=" + major + ", math=" + math + ", english=" + english + ", java=" + java + ", c=" + c
				+ ", os=" + os + ", javaEE=" + javaEE + "]";
	}
	
}
