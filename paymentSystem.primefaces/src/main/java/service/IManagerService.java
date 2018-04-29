/**
 * 下午2:53:30
 * power
 */
package service;

import java.util.List;

import common.ServerResponse;
import pojo.Manager;
import pojo.ManagerDetails;


/**
 * 
 * @author cz
 * 2018年4月28日下午2:53:30
 */
public interface IManagerService {

	/**
	 * @param managerName
	 * @param managerPassword
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午2:54:23
	 */
	ServerResponse<Manager> login(String managerName, String managerPassword);

	/**
	 * @param manager
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午2:54:32
	 */
	ServerResponse<String> register(Manager manager);

	/**
	 * @param managerName
	 * @param email
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午2:54:37
	 */
	ServerResponse<String> sendCheckCodeEmail(String managerName, String email);

	/**
	 * @param managerName
	 * @param managerPasswordNew
	 * @param restPasswordCheckCode
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午2:54:43
	 */
	ServerResponse<String> forgetResetPassword(String managerName, String managerPasswordNew,
			String restPasswordCheckCode);

	
	/**
	 * @param managerName
	 * @param managerPasswordNew
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午2:55:23
	 */
	ServerResponse<String> resetPasswordDirect(String managerName, String managerPasswordNew);

	/**
	 * @param managerId
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午5:58:24
	 */
	ServerResponse<List<ManagerDetails>> listManagerDetailsByManagerId(String managerId);

	/**
	 * @param managerDetailsVo
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:39:56
	 */
	ServerResponse<ManagerDetails> saveManagerDetials(ManagerDetails managerDetails);

	/**
	 * 根据managerId和code查找集合
	 * @param managerId
	 * @param code
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午11:56:23
	 */
	ServerResponse<List<ManagerDetails>> listManagerDetailsByManagerIdAndActive(String managerId, int code);

	/**
	 * 根据accountId查找
	 * @param accountId
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午1:32:06
	 */
	ServerResponse<ManagerDetails> selectManagerDetailsByAccountId(String accountId);

}
