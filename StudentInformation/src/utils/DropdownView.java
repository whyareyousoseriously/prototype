package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class DropdownView implements Serializable{

	/**
	 * 2017-12-25
	 * @author cz
	 * temporarily out of use
	 * 暂时不使用
	 */
	private static final long serialVersionUID = -4666819935664882088L;
	
	private String grade;
	private String department;
	private String major;
	private Map<String,String> grades;
	private Map<String,String> departments;
	private Map<String,String> majors;
	private String test;
	private List<String> tests;
	@PostConstruct
	public void init() {
		grades = new HashMap<String,String>();
		grades.put("2014级","2014级");
		grades.put("2015级","2015级");
		grades.put("2016级", "2016级");
		grades.put("2017级", "2017级");
		
		departments = new HashMap<String,String>();
		departments.put("信息院", "信息院");
		departments.put("物理院", "物理院");
		departments.put("文学院", "文学院");
		departments.put("化学院", "化学院");
		departments.put("数学院", "数学院");
		
		majors = new HashMap<String,String>();
		majors.put("计算机", "计算机");
		majors.put("物理", "物理");
		majors.put("中文", "中文");
		majors.put("化学", "化学");
		majors.put("数学", "数学");
		
		tests = new ArrayList<String>();
		tests.add("test1");
		tests.add("test2");
		tests.add("test3");
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<String> getTest1() {
		return tests;
	}

	public void setTest1(List<String> tests) {
		this.tests = tests;
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
	public Map<String, String> getGrades() {
		return grades;
	}
	public void setGrades(Map<String, String> grades) {
		this.grades = grades;
	}
	public Map<String, String> getDepartments() {
		return departments;
	}
	public void setDepartments(Map<String, String> departments) {
		this.departments = departments;
	}
	public Map<String, String> getMajors() {
		return majors;
	}
	public void setMajors(Map<String, String> majors) {
		this.majors = majors;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
