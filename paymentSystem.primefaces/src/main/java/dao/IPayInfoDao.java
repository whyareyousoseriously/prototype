/**
 * 下午5:08:47
 * power
 */
package dao;

import java.util.List;

import pojo.PayInfo;

/**
 * 
 * @author cz
 * 2018年4月29日下午5:08:47
 */
public interface IPayInfoDao {

	/**
	 * 保存一条支付信息
	 * @param payInfo
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午5:10:53
	 */
	PayInfo saveOnePayInfo(PayInfo payInfo);

	/**
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午7:22:44
	 */
	List<PayInfo> listPayInfoByUserId(String userId);

	/**
	 * @param userId
	 * @param code
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午9:03:21
	 */
	List<PayInfo> listpayInfoByUserIdAndStatus(String userId, int code);

}
