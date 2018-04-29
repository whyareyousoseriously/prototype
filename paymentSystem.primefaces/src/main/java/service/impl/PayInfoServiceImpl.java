/**
 * 下午5:03:26
 * power
 */
package service.impl;

import java.util.List;

import common.ServerResponse;
import dao.IPayInfoDao;
import dao.impl.PayInfoDaoImpl;
import pojo.PayInfo;
import service.IPayInfoService;

/**
 * 
 * @author cz
 * 2018年4月29日下午5:03:26
 */
public class PayInfoServiceImpl implements IPayInfoService {
	private IPayInfoDao iPayInfoDao;
	

	public PayInfoServiceImpl() {
		this.iPayInfoDao = new PayInfoDaoImpl();
	}


	/* (non-Javadoc)
	 * @see service.IPayInfoService#saveOnePayInfo(pojo.PayInfo)
	 * @author cz
	 * @time 2018年4月29日下午5:06:32
	 */
	@Override
	public ServerResponse<PayInfo> saveOnePayInfo(PayInfo payInfo) {
		PayInfo payInfo_feedback = iPayInfoDao.saveOnePayInfo(payInfo);
		if(payInfo_feedback==null) {
			//没有保存成功
			return ServerResponse.createByError();
		}else {
			//保存成功
			return ServerResponse.createBySuccess(payInfo_feedback);
		}
	}


	/* (non-Javadoc)
	 * @see service.IPayInfoService#listPayInfoByUserId(java.lang.String)
	 * @author cz
	 * @time 2018年4月29日下午7:21:09
	 */
	@Override
	public ServerResponse<List<PayInfo>> listPayInfoByUserId(String userId) {
		List<PayInfo> payInfoList = iPayInfoDao.listPayInfoByUserId(userId);
		if(payInfoList.isEmpty()) {
			//没有找到
			return ServerResponse.createByError();
		}else {
			return ServerResponse.createBySuccess(payInfoList);
		}
	}


	/* (non-Javadoc)
	 * @see service.IPayInfoService#listPayInfoByUserIdAndStatus(java.lang.String, int)
	 * @author cz
	 * @time 2018年4月29日下午9:01:59
	 */
	@Override
	public ServerResponse<List<PayInfo>> listPayInfoByUserIdAndStatus(String userId, int code) {
		List<PayInfo> payInfoList = iPayInfoDao.listpayInfoByUserIdAndStatus(userId,code);
		if(payInfoList.isEmpty()) {
			//没有找到
			return ServerResponse.createByError();
		}else {
			return ServerResponse.createBySuccess(payInfoList);
		}
	}

}
