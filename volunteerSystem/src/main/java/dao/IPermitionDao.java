/**
 * 下午5:30:52
 * power
 */
package dao;

import pojo.Permition;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:30:52
 */
public interface IPermitionDao {
	
	/**
	 * 更新或保存
	 * @param permition
	 * @return
	 * @author cz
	 * @time 2018年4月13日下午3:40:57
	 */
	Permition saveOrUpdatePermition(Permition permition);

}
