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
		
		ScoresDAO sdao = new ScoresDAOImpl();
		String sdao_feedback;
		//������������̨�����Ϣ��daoʵ�ַ����ڲ������ﲻ����ʾ
		/*
		 * ��������:�޸Ļ����ӿڣ�ʹ�ӿڹ��ܸ����Ի�
		 * @author cz
		 * @time 2017-12-30
		 * */
		sdao_feedback=sdao.deleteScoresByCondition("name","��ʵС�ɾ�");
		sdao_feedback=sdao.deleteScoresByCondition("studentID", "000001");
		
	}
	@Test
	public void testScoresDAOImpl_searchByCondition() {
		/*
		 * ����dao.impl.ScoresDAOImpl.searchByCondition
		 * 2017-12-30
		 * */
		ScoresDAO sdao = new ScoresDAOImpl();
		List<Scores> sdao_feedback;
		sdao_feedback = sdao.searchByCondition("name", "��ʵС�ɾ�");
		sdao_feedback = sdao.searchByCondition("studentID", "000001");
		
	}
	@Test
	public void testScoresDAOImpl_updateScoresByCondition() {
		/*
		 * ����dao.impl.ScoresDAOImpl.updateScores
		 * 2017-12-30
		 * */
		Scores scores = new Scores();
		scores.setStudentID("000001");
		ScoresDAO sdao = new ScoresDAOImpl();
		//����ѧ��320140938322������Ϊ�仨
		scores.setName("�仨");
		sdao.updateScoresByCondition(scores, "studentID", "name");
		//����ѧ��320140938322���Ա�Ϊ��
		scores.setGender("��");
		sdao.updateScoresByCondition(scores, "studentID", "gender");
		//����ѧ��320140938322��ԺϵΪ����Ժ
		scores.setDepartment("����Ժ");
		sdao.updateScoresByCondition(scores, "studentID", "department");
		//����ѧ��320140938322��רҵΪ����
		scores.setMajor("����");
		sdao.updateScoresByCondition(scores, "studentID", "major");
		//����ѧ��320140938322����ѧΪ70
		scores.setMath("70");
		sdao.updateScoresByCondition(scores, "studentID", "math");
		//����ѧ��320140938322��Ӣ��Ϊ40
		scores.setEnglish("40");
		sdao.updateScoresByCondition(scores, "studentID", "english");
		//����ѧ��320140938322��javaΪ40
		scores.setJava("40");
		sdao.updateScoresByCondition(scores, "studentID", "java");
		//����ѧ��320140938322��cΪ34
		scores.setC("34");
		sdao.updateScoresByCondition(scores, "studentID", "c");
		//����ѧ��320140938322��osΪ55
		scores.setOs("55");
		sdao.updateScoresByCondition(scores, "studentID", "os");
		//����ѧ��320140938322��javaEEΪ55
		scores.setJavaEE("55");
		sdao.updateScoresByCondition(scores, "studentID", "javaEE");
	}
	@Test
	public void testScoresDAOImpl_updateScores() {
		Scores scores = new Scores();
		scores.setStudentID("000001");
		ScoresDAO sdao = new ScoresDAOImpl();scores.setName("�仨");scores.setGender("��");scores.setDepartment("����Ժ");
		scores.setMajor("����");scores.setMath("70");scores.setEnglish("40");scores.setJava("40");scores.setC("34");
		scores.setOs("55");scores.setJavaEE("55");
		sdao.updateScores(scores);
	}
}
