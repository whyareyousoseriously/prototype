/**
 * 下午5:30:12
 * power
 */
package dao;

import pojo.Root;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:30:12
 */
public interface IRootDao {
	
	/**
	 * 管理用户的登陆
	 * @param root
	 * @return 成功返回root,失败返回null
	 * @author cz
	 * @time 2018年4月12日下午6:18:53
	 */
	Root login(Root root);
	
	/**
	 * 管理用户的注册
	 * @param root
	 * @return 成功返回root,失败返回null
	 * @author cz
	 * @time 2018年4月12日下午6:18:56
	 */
	Root register(Root root);

}
