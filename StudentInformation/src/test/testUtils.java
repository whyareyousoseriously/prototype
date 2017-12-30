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
		 * 测试util.DataSearchUtils.duplicateCheckingData;
		 * @author cz
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setStudentID("320140938322");
		String table = "Scores";
		
		if(DataSearchUtils.duplicateCheckingData(table, scores.getStudentID())) {
			System.out.println("该学号已存在");
		}else {
			System.out.println("该学号不存在");
		}
	}
}
