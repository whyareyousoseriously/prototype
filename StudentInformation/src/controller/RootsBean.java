package controller;


import java.util.Collections;
import java.util.List;

import javax.faces.event.ActionEvent;

import dao.RootsDAO;
import dao.ScoresDAO;
import dao.impl.RootsDAOImpl;
import dao.impl.ScoresDAOImpl;
import model.Scores;
import model.Users;

public class RootsBean {
	/*
	 * ��������Ա���bean��
	 * @author cz
	 * 2017-12-26
	 * */
	private String rootID;
	private String password;
	
	private List<Users> allUsers;
	private List<Users> conditionUsers;
	private Users selected = new Users();
	
	private List<Scores> allScores;
	private List<Users> conditionScores;
	private Scores selectedScores = new Scores();
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
		 * �����õ������û���Ϣ�ķ���
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

	
	public List<Scores> getAllScores() {
		/*
		 * �����õ����е�scores;
		 * 
		 * */
		ScoresDAO sdao = new ScoresDAOImpl();
		allScores = sdao.getAllScores();
		
		return allScores;
	}


	public void setAllScores(List<Scores> allScores) {
		this.allScores = allScores;
	}


	public List<Users> getConditionScores() {
		return conditionScores;
	}


	public void setConditionScores(List<Users> conditionScores) {
		this.conditionScores = conditionScores;
	}


	public Scores getSelectedScores() {
		return selectedScores;
	}


	public void setSelectedScores(Scores selectedScores) {
		this.selectedScores = selectedScores;
	}

	public void addScores(ActionEvent e) {
		//��ҳ���ϵ������Ϣ
		Scores scores = new Scores();
		scores.setC(selectedScores.getC());
		scores.setDepartment(selectedScores.getDepartment());
		scores.setEnglish(selectedScores.getEnglish());
		scores.setGender(selectedScores.getGender());
		scores.setJava(selectedScores.getJava());
		scores.setJavaEE(selectedScores.getJavaEE());
		scores.setMajor(selectedScores.getMajor());
		scores.setMath(selectedScores.getMath());
		scores.setName(selectedScores.getName());
		scores.setStudentID(selectedScores.getStudentID());
		
		ScoresDAO sdao = new ScoresDAOImpl();
		String sdao_feedback = sdao.addScores(scores);
		if(sdao_feedback.equals("add-success")){
			
		}else {
			
		}
	}
	
	public void searchByCondition(ActionEvent e) {
		//���Ҹ�������
		
	}
	public void addUsers(ActionEvent e) {
		//��ҳ���ϵ���Ϣ��װ��Users����
		//The information of the page are encapsulated into users objects.
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
		//���selected;
		selected = new Users();
	}
	
	public void searchManyByName(ActionEvent e) {
		//��ҳ��������Ĳ�ѯ���������Users����
		Users users = new Users();
		users.setName(selected.getName());
		
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> rdao_feedback=rdao.searchManyByName(users);
		if(rdao_feedback!=null) {
			this.setConditionUsers(rdao_feedback);
		}else {
			
		}
		
		//���selected
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
		//���selected;
		selected = new Users();
	}
	
	public void updateByCondition(ActionEvent e) {
		/*
		 * 2017-12-28
		 * @author cz
		 * 
		 * */
		//��Dialog�еĸ�����Ϣ��װ��Users����
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
		
		//���selected
		selected = new Users();
	}
}
