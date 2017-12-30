package test;

import java.util.List;

import org.junit.Test;

import dao.RootsDAO;
import dao.impl.RootsDAOImpl;
import model.Users;

public class testRootsDAOImpl {
	/*
	 * RootsDAOImpl�Ĳ�����
	 * @author cz
	 * 2017-12-26
	 * */
	@Test
	public void testRootsDAOImpl_getAllUsers() {
		/*
		 * ����dao.impl.RootsDAOImpl.getAllUsers()
		 * */
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> list = rdao.getAllUsers();
		if(list.size()>0) {
			System.out.println("�������гɹ�");
			for(Users u : list)
				System.out.println(u);
			
		}	
		else
			System.out.println("��������ʧ��");
	}
	@Test
	public void testRootsDAOImpl_deleteUser() {
		/*
		 * ����dao.impl.RootsDAOImpl.getAllUsers()
		 * */
		//����һ��users
		Users user = new Users();
		user.setStudentID("000001");
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.deleteUser(user,"studentID");
		if(rdao_feedback.equals("delete-success")){
			System.out.println("ɾ���ɹ�");
		}else {
			System.out.println("ɾ��ʧ��");
		}
	}
	@Test
	public void testRootsDAOImpl_searchOne() {
		/*
		 * ����dao.impl.RootsDAOImpl.searchOne
		 * */
		//����һ��users
		Users user = new Users();
		user.setUid(5);
		RootsDAO rdao = new RootsDAOImpl();
		Users rdao_feedback = rdao.searchOne(user);
		if(rdao_feedback!=null) {
			System.out.println(rdao_feedback);
			System.out.println("���ҵ�");
		}else {
			System.out.println("δ�ҵ�");
		}
			
	}
	
	@Test
	public void testRootsDAOImpl_searchManyByName() {
		/*
		 * ����dao.impl.RootsDAOImpl.searchManyByName
		 * */
		Users user = new Users();
		user.setName("�������");
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> rdao_feedback = rdao.searchManyByName(user);
		if(rdao_feedback!=null) {
			System.out.println("���ҵ���������������");
			for(Users u:rdao_feedback) {
				System.out.println(u);
			}
		}else {
			System.out.println("δ�ҵ�");
		}
	}
	
	@Test
	public void testRootsDAOImpl_searchManyByStduentID() {
		/*
		 * ����dao.impl.RootsDAOImpl.searchManyByStudentID
		 * */
		Users user = new Users();
		user.setStudentID("000005");
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> rdao_feedback = rdao.searchManyByStudentID(user);
		if(rdao_feedback!=null) {
			System.out.println("���ҵ���������������");
			for(Users u:rdao_feedback) {
				System.out.println(u);
			}
		}else {
			System.out.println("δ�ҵ�");
		}

	}
	
	@Test
	public void testRootsDAOImpl_updateUser() {
		/*
		 * ����dao.impl.RootsDAOImpl.updateUser
		 *@author cz
		 *2017-12-28
		 * */
		Users user = new Users();
		//�޸�����Ϊ������
		user.setName("������");
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.updateUser(user,"000006");
		if(rdao_feedback.equals("update-success")) {
			System.out.println("���³ɹ�");
		}else {
			System.out.println("����ʧ��");
		}
	}
}
