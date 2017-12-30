package model;

public class Scores {
	private int uid;
	private String name;
	private String studentID;
	private String gender;
	private String department;
	private String major;
	private double math;
	private double english;
	private double java;
	private double c;
	private double os;
	private double javaEE;
	
	
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
	public double getMath() {
		return math;
	}
	public void setMath(String math) {
		/*
		 * 这里修改，因为前台传来的String型数据，转换成Double,
		 * @author cz
		 * 2017-12-30
		 * */
		this.math = Double.parseDouble(math);
	}
	public void setMath(Double math) {
		this.math = math;
	}
	public double getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = Double.parseDouble(english);
	}
	public double getJava() {
		return java;
	}
	public void setJava(String java) {
		this.java = Double.parseDouble(java);
	}
	public void setJava(Double java) {
		this.java = java;
	}
	public double getC() {
		return c;
	}
	public void setC(String c) {
		this.c = Double.parseDouble(c);
	}
	public double getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = Double.parseDouble(os);
	}
	public void setOs(Double os) {
		this.os = os;
	}
	public double getJavaEE() {
		return javaEE;
	}
	public void setJavaEE(String javaEE) {
		this.javaEE = Double.parseDouble(javaEE);
	}
	public void setJavaEE(Double javaEE) {
		this.javaEE = javaEE;
	}
	@Override
	public String toString() {
		return "Scores [name=" + name + ", studentID=" + studentID + ", gender=" + gender + ", department=" + department
				+ ", major=" + major + ", math=" + math + ", english=" + english + ", java=" + java + ", c=" + c
				+ ", os=" + os + ", javaEE=" + javaEE + "]";
	}
	
}
