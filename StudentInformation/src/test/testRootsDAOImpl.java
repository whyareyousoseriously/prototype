package test;

import java.util.List;

import org.junit.Test;

import dao.RootsDAO;
import dao.impl.RootsDAOImpl;
import model.Users;

public class testRootsDAOImpl {
	/*
	 * RootsDAOImpl的测试类
	 * @author cz
	 * 2017-12-26
	 * */
	@Test
	public void testRootsDAOImpl_getAllUsers() {
		/*
		 * 测试dao.impl.RootsDAOImpl.getAllUsers()
		 * */
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> list = rdao.getAllUsers();
		if(list.size()>0) {
			System.out.println("遍历所有成功");
			for(Users u : list)
				System.out.println(u);
			
		}	
		else
			System.out.println("遍历所有失败");
	}
	@Test
	public void testRootsDAOImpl_deleteUser() {
		/*
		 * 测试dao.impl.RootsDAOImpl.getAllUsers()
		 * */
		//给定一个users
		Users user = new Users();
		user.setStudentID("000001");
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.deleteUser(user,"studentID");
		if(rdao_feedback.equals("delete-success")){
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}
	@Test
	public void testRootsDAOImpl_searchOne() {
		/*
		 * 测试dao.impl.RootsDAOImpl.searchOne
		 * */
		//给定一个users
		Users user = new Users();
		user.setUid(5);
		RootsDAO rdao = new RootsDAOImpl();
		Users rdao_feedback = rdao.searchOne(user);
		if(rdao_feedback!=null) {
			System.out.println(rdao_feedback);
			System.out.println("查找到");
		}else {
			System.out.println("未找到");
		}
			
	}
	
	@Test
	public void testRootsDAOImpl_searchManyByName() {
		/*
		 * 测试dao.impl.RootsDAOImpl.searchManyByName
		 * */
		Users user = new Users();
		user.setName("万里独行");
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> rdao_feedback = rdao.searchManyByName(user);
		if(rdao_feedback!=null) {
			System.out.println("已找到满足条件的数据");
			for(Users u:rdao_feedback) {
				System.out.println(u);
			}
		}else {
			System.out.println("未找到");
		}
	}
	
	@Test
	public void testRootsDAOImpl_searchManyByStduentID() {
		/*
		 * 测试dao.impl.RootsDAOImpl.searchManyByStudentID
		 * */
		Users user = new Users();
		user.setStudentID("000005");
		RootsDAO rdao = new RootsDAOImpl();
		List<Users> rdao_feedback = rdao.searchManyByStudentID(user);
		if(rdao_feedback!=null) {
			System.out.println("已找到满足条件的数据");
			for(Users u:rdao_feedback) {
				System.out.println(u);
			}
		}else {
			System.out.println("未找到");
		}

	}
	
	@Test
	public void testRootsDAOImpl_updateUser() {
		/*
		 * 测试dao.impl.RootsDAOImpl.updateUser
		 *@author cz
		 *2017-12-28
		 * */
		Users user = new Users();
		//修改名字为更新者
		user.setName("更新者");
		RootsDAO rdao = new RootsDAOImpl();
		String rdao_feedback = rdao.updateUser(user,"000006");
		if(rdao_feedback.equals("update-success")) {
			System.out.println("更新成功");
		}else {
			System.out.println("更新失败");
		}
	}
}
