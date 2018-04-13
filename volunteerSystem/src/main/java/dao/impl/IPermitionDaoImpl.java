/**
 * 下午5:31:08
 * power
 */
package dao.impl;

import dao.IPermitionDao;
import pojo.Permition;
import utils.db.DBOperation;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:31:08
 */
public class IPermitionDaoImpl implements IPermitionDao {

	/* (non-Javadoc)
	 * @see dao.IPermitionDao#saveOrUpdatePermition(pojo.Permition)
	 * @author cz
	 * @time 2018年4月13日下午3:41:10
	 */
	@Override
	public Permition saveOrUpdatePermition(Permition permition) {
		// 更新或者添加Permition
		return (Permition) DBOperation.saveOrUpdateData("Permition", permition);
	}

}
