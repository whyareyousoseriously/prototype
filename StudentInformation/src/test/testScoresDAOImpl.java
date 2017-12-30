package test;

import java.util.List;

import org.junit.Test;

import dao.ScoresDAO;
import dao.impl.ScoresDAOImpl;
import model.Scores;

public class testScoresDAOImpl {
	/*
	 * 测试ScoresDAOImpl的接口
	 * @author cz
	 * 2017-12-30
	 * */
	@Test
	public void testScoresDAOImpl_getAllScores() {
		/*
		 * 测试dao.impl.ScoresDAOImpl.getAllScores;
		 * 2017-12-30
		 * */
		ScoresDAO sdao = new ScoresDAOImpl();
		sdao.getAllScores();
	}
	@Test
	public void testScoresDAOImpl_addScores() {
		/*
		 * 测试dao.impl.ScoresDAOImpl.addScores
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setC("100");
		ScoresDAO sdao = new ScoresDAOImpl();
		if("add-success".equals(sdao.addScores(scores))) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
		
	}
	@Test
	public void testScoresDAOImpl_deleteScores() {
		/*
		 * 测试dao.impl.ScoresDAOImpl.deleteScores;
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setName("张山");
		scores.setStudentID("320140938321");
		scores.setJava("100");
		ScoresDAO sdao = new ScoresDAOImpl();
		String sdao_feedback;
		//更新升级，后台输出信息在dao实现方法内部，这里不在提示
		sdao_feedback=sdao.deleteScores(scores, "name");
		sdao_feedback=sdao.deleteScores(scores, "studentID");
		
	}
	@Test
	public void testScoresDAOImpl_searchByCondition() {
		/*
		 * 测试dao.impl.ScoresDAOImpl.searchByCondition
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setName("张山");
		scores.setStudentID("320140938321");
		scores.setJava("100");
		ScoresDAO sdao = new ScoresDAOImpl();
		List<Scores> sdao_feedback;
		sdao_feedback = sdao.searchByCondition(scores, "name");
		sdao_feedback = sdao.searchByCondition(scores, "studentID");
		
	}
	@Test
	public void testScoresDAOImpl_updateScores() {
		/*
		 * 测试dao.impl.ScoresDAOImpl.updateScores
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setStudentID("320140938322");
		ScoresDAO sdao = new ScoresDAOImpl();
		//更新学号320140938322的名字为翠花
		scores.setName("翠花");
		sdao.updateScores(scores, "studentID", "name");
		//更新学号320140938322的性别为男
		scores.setGender("男");
		sdao.updateScores(scores, "studentID", "gender");
		//更新学号320140938322的院系为物理院
		scores.setDepartment("物理院");
		sdao.updateScores(scores, "studentID", "department");
		//更新学号320140938322的专业为物理
		scores.setMajor("物理");
		sdao.updateScores(scores, "studentID", "major");
		//更新学号320140938322的数学为70
		scores.setMath("70");
		sdao.updateScores(scores, "studentID", "math");
		//更新学号320140938322的英语为40
		scores.setEnglish("40");
		sdao.updateScores(scores, "studentID", "english");
		//更新学号320140938322的java为40
		scores.setJava("40");
		sdao.updateScores(scores, "studentID", "java");
		//更新学号320140938322的c为34
		scores.setC("34");
		sdao.updateScores(scores, "studentID", "c");
		//更新学号320140938322的os为55
		scores.setOs("55");
		sdao.updateScores(scores, "studentID", "os");
		//更新学号320140938322的javaEE为55
		scores.setJavaEE("55");
		sdao.updateScores(scores, "studentID", "javaEE");
	}
}
