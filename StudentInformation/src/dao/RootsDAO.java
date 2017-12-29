package dao;

import java.util.List;

import model.Users;

public interface RootsDAO {
	/*
	 * ��������Ա��Ľӿ���
	 * @author cz
	 * 2017-12-26
	 * */
	
	//�����ǲ�ѯ���ݿ��������û��Ľӿ�
	public List<Users> getAllUsers();
	
	//���û��������һ����Ϣ�ķ���
	public String addUser(Users user);
	
	
	
	//��ѯ���ݿ��е�һ����Ϣ
	public Users searchOne(Users user);
	
	//��ѯ���ݿ��е���Ϣͨ������
	public List<Users> searchManyByName(Users user);
	
	//��ѯ���ݿ��е���Ϣͨ��ѧ��
	public List<Users> searchManyByStudentID(Users user);
	
	//�����ݿ���ɾ��һ����Ϣͨ��ָ������
	public String deleteUser(Users user,String condition);
	
	//�������ݿ��е�һ������ͨ��ָ������
	public String updateUser(Users user,String condition);
}
