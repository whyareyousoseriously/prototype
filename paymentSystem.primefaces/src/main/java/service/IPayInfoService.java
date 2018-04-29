/**
 * 下午5:02:49
 * power
 */
package service;

import java.util.List;

import common.ServerResponse;
import pojo.PayInfo;

/**
 * 
 * @author cz
 * 2018年4月29日下午5:02:49
 */
public interface IPayInfoService {

	/**
	 * 保存一条支付信息
	 * @param payInfo
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午5:06:11
	 */
	ServerResponse<PayInfo> saveOnePayInfo(PayInfo payInfo);

	/**
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午7:20:57
	 */
	ServerResponse<List<PayInfo>> listPayInfoByUserId(String userId);

	/**
	 * @param userId
	 * @param code
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午9:01:45
	 */
	ServerResponse<List<PayInfo>> listPayInfoByUserIdAndStatus(String userId, int code);

}
