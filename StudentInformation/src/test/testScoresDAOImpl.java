package test;

import java.util.List;

import org.junit.Test;

import dao.ScoresDAO;
import dao.impl.ScoresDAOImpl;
import model.Scores;

public class testScoresDAOImpl {
	/*
	 * ����ScoresDAOImpl�Ľӿ�
	 * @author cz
	 * 2017-12-30
	 * */
	@Test
	public void testScoresDAOImpl_getAllScores() {
		/*
		 * ����dao.impl.ScoresDAOImpl.getAllScores;
		 * 2017-12-30
		 * */
		ScoresDAO sdao = new ScoresDAOImpl();
		sdao.getAllScores();
	}
	@Test
	public void testScoresDAOImpl_addScores() {
		/*
		 * ����dao.impl.ScoresDAOImpl.addScores
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setC("100");
		ScoresDAO sdao = new ScoresDAOImpl();
		if("add-success".equals(sdao.addScores(scores))) {
			System.out.println("��ӳɹ�");
		}else {
			System.out.println("���ʧ��");
		}
		
	}
	@Test
	public void testScoresDAOImpl_deleteScores() {
		/*
		 * ����dao.impl.ScoresDAOImpl.deleteScores;
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setName("��ɽ");
		scores.setStudentID("320140938321");
		scores.setJava("100");
		ScoresDAO sdao = new ScoresDAOImpl();
		String sdao_feedback;
		//������������̨�����Ϣ��daoʵ�ַ����ڲ������ﲻ����ʾ
		sdao_feedback=sdao.deleteScores(scores, "name");
		sdao_feedback=sdao.deleteScores(scores, "studentID");
		
	}
	@Test
	public void testScoresDAOImpl_searchByCondition() {
		/*
		 * ����dao.impl.ScoresDAOImpl.searchByCondition
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setName("��ɽ");
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
		 * ����dao.impl.ScoresDAOImpl.updateScores
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setStudentID("320140938322");
		ScoresDAO sdao = new ScoresDAOImpl();
		//����ѧ��320140938322������Ϊ�仨
		scores.setName("�仨");
		sdao.updateScores(scores, "studentID", "name");
		//����ѧ��320140938322���Ա�Ϊ��
		scores.setGender("��");
		sdao.updateScores(scores, "studentID", "gender");
		//����ѧ��320140938322��ԺϵΪ����Ժ
		scores.setDepartment("����Ժ");
		sdao.updateScores(scores, "studentID", "department");
		//����ѧ��320140938322��רҵΪ����
		scores.setMajor("����");
		sdao.updateScores(scores, "studentID", "major");
		//����ѧ��320140938322����ѧΪ70
		scores.setMath("70");
		sdao.updateScores(scores, "studentID", "math");
		//����ѧ��320140938322��Ӣ��Ϊ40
		scores.setEnglish("40");
		sdao.updateScores(scores, "studentID", "english");
		//����ѧ��320140938322��javaΪ40
		scores.setJava("40");
		sdao.updateScores(scores, "studentID", "java");
		//����ѧ��320140938322��cΪ34
		scores.setC("34");
		sdao.updateScores(scores, "studentID", "c");
		//����ѧ��320140938322��osΪ55
		scores.setOs("55");
		sdao.updateScores(scores, "studentID", "os");
		//����ѧ��320140938322��javaEEΪ55
		scores.setJavaEE("55");
		sdao.updateScores(scores, "studentID", "javaEE");
	}
}
