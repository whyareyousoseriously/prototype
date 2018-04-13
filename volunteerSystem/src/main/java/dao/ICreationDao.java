/**
 * 下午5:17:05
 * power
 */
package dao;

import pojo.Creation;

/**
 * 未用到
 * @author cz
 * 2018年4月12日下午5:17:05
 */
public interface ICreationDao {

	/**
	 * 向Creation中添加一条记录
	 * @param creation
	 * @return creation成功  null失败
	 * @author cz
	 * @time 2018年4月13日下午2:20:41
	 */
	Creation saveCreation(Creation creation);
}
