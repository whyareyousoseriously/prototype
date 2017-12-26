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
	 * ��ʱ��ʹ��
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
		grades.put("2014��","2014��");
		grades.put("2015��","2015��");
		grades.put("2016��", "2016��");
		grades.put("2017��", "2017��");
		
		departments = new HashMap<String,String>();
		departments.put("��ϢԺ", "��ϢԺ");
		departments.put("����Ժ", "����Ժ");
		departments.put("��ѧԺ", "��ѧԺ");
		departments.put("��ѧԺ", "��ѧԺ");
		departments.put("��ѧԺ", "��ѧԺ");
		
		majors = new HashMap<String,String>();
		majors.put("�����", "�����");
		majors.put("����", "����");
		majors.put("����", "����");
		majors.put("��ѧ", "��ѧ");
		majors.put("��ѧ", "��ѧ");
		
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
