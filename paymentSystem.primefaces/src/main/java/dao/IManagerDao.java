/**
 * 下午3:14:26
 * power
 */
package dao;

import java.util.List;


import pojo.Manager;
import pojo.ManagerDetails;


/**
 * 
 * @author cz
 * 2018年4月28日下午3:14:26
 */
public interface IManagerDao {

	/**
	 * @param managerName
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午3:15:36
	 */
	Manager checkManagerName(String managerName);

	/**
	 * @param managerName
	 * @param md5Password
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午3:15:42
	 */
	Manager selectLogin(String managerName, String md5Password);

	/**
	 * @param manager
	 * @author cz
	 * @time 2018年4月28日下午3:15:46
	 */
	Integer saveOrUpdate(Manager manager);

	/**
	 * @param str
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午3:16:41
	 */
	Manager checkEmail(String email);

	/**
	 * @param managerName
	 * @param email
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午3:16:49
	 */
	Manager checkManagerNameAndEmail(String managerName, String email);

	/**
	 * @param managerId
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:05:46
	 */
	List<ManagerDetails> listManagerDetailsByManagerId(String managerId);

	/**
	 * @param managerDetails
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:56:17
	 */
	ManagerDetails saveOrUpdateManagerDetails(ManagerDetails managerDetails);

	/**
	 * @param str
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午8:08:17
	 */
	ManagerDetails checkAccountId(String str);

	/**
	 * 用于判断是执行的更新操作还是创建操作
	 * @param accountId
	 * @param managerId
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午8:33:16
	 */
	ManagerDetails checkExistByAccountIdAndManagerId(String accountId, String managerId);

}
