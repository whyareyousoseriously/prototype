package dao;

import java.util.List;

import model.Scores;

public interface ScoresDAO {
	/*
	 * @author cz
	 * 2017-12-29
	 * */
	
	/*
	 * ��ѯ���з���
	 * ��������
	 * ���أ����ݿ�����Ӵscores�ļ���
	 * */
	public List<Scores> getAllScores();
	
	
	/*
	 * �������
	 * ��������ӵĶ���scores;
	 * ���أ�����ִ�еĽ��
	 * */
	public String addScores(Scores scores);
	
	
	
	/*
	 * ����������ѯ,
	 * ��������ѯ���������֮һcondition 
	 * ��������ѯ������������ֵconditonValue
	 * ���أ�һ��������������������List
	 * ���䣺���������Ϊ�˲�ѯ�Ķ�������
	 * */
	public List<Scores> searchByCondition(String condition,String conditionValue);
	
	/*
	 * ��������ɾ��
	 * ������ɾ��������������conditon
	 * ������ɾ�������������Ե�ֵconditionValue
	 * ���أ�����ִ�еĽ��
	 * @author cz
	 * ��������:2017-12-30
	 * */
	public String deleteScoresByCondition(String condition,String conditionValue);
	
	/*
	 * ��������
	 * ������ɾ���Ķ���scores
	 * */
	public String updateScores(Scores scores);
	
	/*
	 * ������������
	 * ���������¶������������Scores
	 * ������������һ������¼which
	 * ����������һ����¼��ʲô�������趨��all��������������ԣ�
	 * ���أ�������ִ�еĽ��
	 * */
	public String updateScoresByCondition(Scores scores,String which,String what);
	
}
