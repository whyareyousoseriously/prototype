/**
 * 下午5:30:26
 * power
 */
package dao.impl;

import dao.IRootDao;
import pojo.Root;
import utils.db.DBOperation;

/**
 * 
 * @author cz
 * 2018年4月12日下午5:30:26
 */
public class IRootDaoImpl implements IRootDao {

	/* (non-Javadoc)
	 * @see dao.IRootDao#login(pojo.Root)
	 * @author cz
	 * @time 2018年4月12日下午6:19:59
	 */
	@Override
	public Root login(Root root) {
		//调用工具类
		Root rootLogin = (Root)DBOperation.login("Root", root);
		if(rootLogin!=null) {
			return rootLogin;
		}else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see dao.IRootDao#register(pojo.Root)
	 * @author cz
	 * @time 2018年4月12日下午6:19:59
	 */
	@Override
	public Root register(Root root) {
		Root rootRegister = (Root)DBOperation.addData("Root", root);
		if(rootRegister!=null) {
			return rootRegister;
		}else {
			return null;
		}
	}

}
