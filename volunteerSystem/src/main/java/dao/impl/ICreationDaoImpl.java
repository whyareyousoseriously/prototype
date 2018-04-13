/**
 * 下午5:18:25
 * power
 */
package dao.impl;

import dao.ICreationDao;
import pojo.Creation;
import utils.db.DBOperation;

/**
 * 未用到
 * @author cz
 * 2018年4月12日下午5:18:25
 */
public class ICreationDaoImpl implements ICreationDao {

	/* (non-Javadoc)
	 * @see dao.ICreationDao#saveCreation(pojo.Creation)
	 * @author cz
	 * @time 2018年4月13日下午2:21:41
	 */
	@Override
	public Creation saveCreation(Creation creation) {
		// 添加一条记录
		return (Creation) DBOperation.saveOrUpdateData("Creation", creation);
	}

}
