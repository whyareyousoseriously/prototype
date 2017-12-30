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
	 * ��������
	 * ���������ӵĶ���scores;
	 * ���أ�����ִ�еĽ��
	 * */
	public String addScores(Scores scores);
	
	
	
	/*
	 * ������ѯ,
	 * ��������ѯ����Ĵ������scores��scores������ ��һ��ȫ���� 
	 * ��������ѯ������conditon����scores��ѡȡ�Ǹ�������Ϊ��ѯ������
	 * ���أ�һ��������������������List
	 * ���䣺���������Ϊ�˲�ѯ�Ķ�������
	 * */
	public List<Scores> searchByCondition(Scores scores,String condition);
	
	
	/*
	 * ��������
	 * ���������¶������������Scores
	 * ������������һ������¼which
	 * ����������һ����¼��ʲô�������趨��all���������������ԣ�
	 * ���أ�������ִ�еĽ��
	 * */
	public String updateScores(Scores scores,String which,String what);
	
	
	
	/*
	 * ����ɾ��
	 * ������ɾ���Ķ���Ĵ������scores��scores�����Բ�һ��ȫ����
	 * ������ɾ��������condition(��scores��ѡȡ��һ��������Ϊɾ��������
	 * ���أ�����ִ�еĽ��
	 * */
	public String deleteScores(Scores scores,String condition);
}