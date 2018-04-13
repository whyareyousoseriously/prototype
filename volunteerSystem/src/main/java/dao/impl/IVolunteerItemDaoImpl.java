/**
 * 下午5:34:56
 * power
 */
package dao.impl;


import java.util.List;


import dao.IVolunteerItemDao;
import pojo.VolunteerItem;
import utils.db.DBOperation;

/**
 * 志愿条目的dao.impl
 * 志愿条目的增删改查
 * @author cz
 * 2018年4月12日下午5:34:56
 */
public class IVolunteerItemDaoImpl implements IVolunteerItemDao {

	/* (non-Javadoc)
	 * @see dao.IVolunteerItemDao#ListIVolunteerItem(java.lang.Integer)
	 * @author cz
	 * @time 2018年4月13日上午11:08:23
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VolunteerItem> ListIVolunteerItem(Integer status) {
		return DBOperation.listDataByStatus("VolunteerItem", "status", status);
	}

	/* (non-Javadoc)
	 * @see dao.IVolunteerItemDao#addVolunteerItem(pojo.VolunteerItem)
	 * @author cz
	 * @time 2018年4月13日下午2:04:36
	 */
	@Override
	public VolunteerItem saveOrUpdateVolunteerItem(VolunteerItem volunteerItem) {
		//添加一条志愿条目
		return (VolunteerItem) DBOperation.saveOrUpdateData("VolunteerItem", volunteerItem);
	}

	/* (non-Javadoc)
	 * @see dao.IVolunteerItemDao#ListVolunteerItem(java.lang.Integer, java.lang.Integer)
	 * @author cz
	 * @time 2018年4月13日下午6:47:05
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VolunteerItem> ListVolunteerItem(String userId,Integer status){
		
		return DBOperation.listDataByTwoCondition("VolunteerItem", "userId", userId, "status", status);
	}

}
