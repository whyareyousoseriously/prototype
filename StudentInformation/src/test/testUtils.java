package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Users;
import utils.DataSearchUtils;

public class testUtils {
	@Test
	public void testDuplicateChecking() {
		Users users = new Users();
		users.setName("����");
		users.setStudentID("320140938321");
		users.setPassword("1231231");
		
		UsersDAO udao = new UsersDAOImpl();
		
		if(udao.usersLogin(users)!=null) {
			System.out.println("��¼�ɹ�");
		}else {
			System.out.println("��¼ʧ��");
		}
		
		if(DataSearchUtils.duplicateChecking(users.getStudentID()))
			System.out.println("��ѧ���Ѵ���");
		else
			System.out.println("��ѧ��������");
	}
}
