package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.UsersDAO;
import dao.impl.UsersDAOImpl;
import model.Scores;
import model.Users;
import utils.DataSearchUtils;

public class testUtils {
	@Test
	public void testDuplicateCheckingData() {
		
		/*
		 * ����util.DataSearchUtils.duplicateCheckingData;
		 * @author cz
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setStudentID("320140938322");
		String table = "Scores";
		
		if(DataSearchUtils.duplicateCheckingData(table, scores.getStudentID())) {
			System.out.println("��ѧ���Ѵ���");
		}else {
			System.out.println("��ѧ�Ų�����");
		}
	}
}
