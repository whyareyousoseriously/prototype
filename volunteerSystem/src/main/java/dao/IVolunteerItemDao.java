/**
 * 下午5:18:07
 * power
 */
package dao;

import java.util.List;

import pojo.VolunteerItem;

/**
 * 志愿条目的接口
 * 志愿条目的增删改查
 * @author cz
 * 2018年4月12日下午5:18:07
 */
public interface IVolunteerItemDao {
	/**
	 * 罗列所有的志愿条目
	 * @param status 志愿条目的状态0-未批准,1-批准,2-下架 
	 * @return List 所有满足条件的志愿条目
	 * @author cz
	 * @time 2018年4月13日上午11:04:56
	 */
	List<VolunteerItem> ListIVolunteerItem(Integer status);
	
	/**
	 * 添加一条志愿条目
	 * @param volunteerItem
	 * @return VolunteerItem
	 * @author cz
	 * @time 2018年4月13日下午2:04:14
	 */
	VolunteerItem saveOrUpdateVolunteerItem(VolunteerItem volunteerItem);
	
	/**
	 * 罗列所有的符合要求的求助条目
	 * @param userId 发起者id
	 * @param status 条目状态status
	 * @return list
	 * @author cz
	 * @time 2018年4月13日下午6:46:12
	 */
	List<VolunteerItem> ListVolunteerItem(String userId,Integer status);
}
