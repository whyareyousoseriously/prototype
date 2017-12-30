package controller;

import java.util.Date;
import java.util.List;

import dao.ScoresDAO;
import dao.UsersDAO;
import dao.impl.ScoresDAOImpl;
import dao.impl.UsersDAOImpl;
import model.Scores;
import model.Users;

public class UsersBean {
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
	private List<String> hobbys;
	private Scores personalScores;
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
	
	
	public List<String> getHobbys() {
		return hobbys;
	}
	public void setHobbys(List<String> hobbys) {
		this.hobbys = hobbys;
	}
	
	public Scores getPersonalScores() {
		/*
		 * 调用ScoresDAO的方法来获得自己的成绩
		 * */
		ScoresDAO sdao = new ScoresDAOImpl();
		//通过查询Scores表中studentID来获得成绩
		List<Scores> sdao_feedback= sdao.searchByCondition("studentID", studentID);
		personalScores = sdao_feedback.get(0);
		return personalScores;
	}
	public void setPersonalScores(Scores personalScores) {
		this.personalScores = personalScores;
	}
	
	@Override
	public String toString() {
		return "UsersBean [name=" + name + ", studentID=" + studentID + ", password=" + password + ", gender=" + gender
				+ ", brithday=" + brithday + ", grade=" + grade + ", department=" + department + ", major=" + major
				+ ", email=" + email + ", address=" + address + ", hobbys=" + hobbys + ", personalScores="
				+ personalScores + "]";
	}
	public String doLogin() {
		System.out.println(studentID);
		System.out.println(password);
		
		//将页面上的学号和密码封装成Users对象
		//The studentID and password on the page are encapsulated into users objects.
		Users users= new Users();
		users.setStudentID(studentID);
		users.setPassword(password);
		
		//面向接口编程WCF Interface oriented programming
		UsersDAO usersDAO = new UsersDAOImpl();
		//将页面上的Users对象传递，然后数据库进行查询
		
		//从数据库返回的信息
		Users users_feedback = usersDAO.usersLogin(users);
		
		
		if(users_feedback!=null) {	
			System.out.println(users_feedback.toString()+"登录成功");
			
			this.setAddress(users_feedback.getAddress());
			this.setBrithday(users_feedback.getBrithday());
			this.setDepartment(users_feedback.getDepartment());
			this.setEmail(users_feedback.getEmail());
			this.setGender(users_feedback.getGender());
			this.setGrade(users_feedback.getGrade());
			this.setHobbys(users_feedback.getHobbys());
			this.setMajor(users_feedback.getMajor());
			this.setName(users_feedback.getName());
			this.setStudentID(users_feedback.getStudentID());
			
			return "home?facesRedirect=true";
		}else {
			System.out.println(users.getStudentID()+"登录失败");
			
			return "login-error?facesRedirect=true";
		}
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
		users.setHobbys(hobbys);
		
		UsersDAO udao = new UsersDAOImpl();
		if(udao.usersRegister(users)==1)
			return "registration-success?facesRedirect=true";
		else
			return "registration-error?facesRedirect=true";
	}
}
