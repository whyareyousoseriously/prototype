package controller;


import java.util.Collections;
import java.util.List;

import javax.faces.event.ActionEvent;

import dao.RootsDAO;
import dao.impl.RootsDAOImpl;
import model.Users;

public class RootsBean {
	/*
	 * 超级管理员类的bean类
	 * @author cz
	 * 2017-12-26
	 * */
	private String rootID;
	private String password;
	
	private List<Users> allUsers;
	private List<Users> conditionUsers;
	private Users selected = new Users();
	
	public String getRootID() {
		return rootID;
	}


	public void setRootID(String rootID) {
		this.rootID = rootID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Users> getAllUsers() {
		/*
		 * 遍历得到所有用户信息的方法
		 * 
		 * */
		RootsDAO rdao = new RootsDAOImpl();
		allUsers = rdao.getAllUsers();
		
		/*Collections.sort(allUsers,new Comparator<Users>()
				{
					@Override
					public int compare(Users u1,Users u2) {
						return u1.getStudentID().compareTo(u2.getStudentID());
					}
				});*/
		return allUsers;
	}


	public void setUsers(List<Users> allUsers) {
		this.allUsers = allUsers;
	}
	

	public Users getSelected() {
		return selected;
	}


	public void setSelected(Users selected) {
		this.selected = selected;
	}


	public void setAllUsers(List<Users> allUsers) {
		this.allUsers = allUsers;
	}
	

	public List<Users> getConditionUsers() {
		return conditionUsers;
	}


	public void setConditionUsers(List<Users> conditionUsers) {
		this.conditionUsers = conditionUsers;
	}


	public void addUsers(ActionEvent e) {
		//将页面上的注册信息封装成Users对象
		//The information of registration on the page are encapsulated into users objects.
		Users users= new Users();
		users.setName(selected.getName());
		users.setStudentID(selected.getStudentID());
		users.setPassword(selected.getPassword());
		users.setGender(selected.getGender());
		users.setBrithday(selected.getBrithday());
		users.setGrade(selected.getGrade());
		users.setDepartment(selected.getDepartment());
		users.setMajor(selected.getMajor());
		users.setEmail(selected.getEmail());
		users.setAddress(selected.getAddress());
		users.setHobbys(selected.getHobbys());
		
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.addUser(users);
		if(rdao_feedback.equals("add-success")) {
			
		}else {
	
		}
		//清空selected;
		selected = new Users();
	}
	
	public void searchManyByName(ActionEvent e) {
		//将页面上输入的查询条件添加如Users对象
		Users users = new Users();
		users.setName(selected.getName());
		
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> rdao_feedback=rdao.searchManyByName(users);
		if(rdao_feedback!=null) {
			this.setConditionUsers(rdao_feedback);
		}else {
			
		}
		
		//清空selected
		selected = new Users();
	}
	
	public void deleteByCondition(ActionEvent e) {
		/*
		 * 2017-12-28
		 * */
		Users users = new Users();
		users.setStudentID(selected.getStudentID());
		
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.deleteUser(users, "studentID");
		if(rdao_feedback.equals("delete-success")) {
			
		}else {
			
		}
		//清空selected;
		selected = new Users();
	}
	
	public void updateByCondition(ActionEvent e) {
		/*
		 * 2017-12-28
		 * @author cz
		 * 
		 * */
		//将Dialog中的更新信息封装成Users对象
		//The information of dialog on the page are encapsulated into users objects.
		Users users= new Users();
		users.setName(selected.getName());
		users.setStudentID(selected.getStudentID());
		users.setPassword(selected.getPassword());
		users.setGender(selected.getGender());
		users.setBrithday(selected.getBrithday());
		users.setGrade(selected.getGrade());
		users.setDepartment(selected.getDepartment());
		users.setMajor(selected.getMajor());
		users.setEmail(selected.getEmail());
		users.setAddress(selected.getAddress());
		users.setHobbys(selected.getHobbys());
				
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.updateUser(users,users.getStudentID());
		if(rdao_feedback.equals("update-success")) {
			
		}else {
			
		}
		
		//清空selected
		selected = new Users();
	}
}
