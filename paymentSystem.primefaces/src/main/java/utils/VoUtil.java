/**
 * 上午11:53:09
 * power
 */
package utils;

import org.apache.commons.lang3.StringUtils;

import common.Const;

/**
 * 给前后端数据转换，提供支持
 * @author cz
 * 2018年4月29日上午11:53:09
 */
public class VoUtil {
	/**
	 * 给前端显示提供支持 暂时只识别微信，支付宝 0代表微信，1代表支付宝，非0非1，默认为支付宝 识别账户类型
	 * 
	 * @param accountType
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:13:33
	 */
	public static String readAccountType(Integer accountType) {

		if (Const.PayPlatformEnum.ALIPAY.getCode() == accountType) {
			return Const.PayPlatformEnum.ALIPAY.getValue();
		}
		if (Const.PayPlatformEnum.WECHAT.getCode() == accountType) {
			return Const.PayPlatformEnum.WECHAT.getValue();
		}
		return Const.PayPlatformEnum.ALIPAY.getValue();
	}

	/**
	 * 给后端保存提供支持
	 * 
	 * @param accountType
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:45:54
	 */
	public static Integer writeAccountType(String accountType) {
		if (StringUtils.equals(accountType, Const.PayPlatformEnum.ALIPAY.getValue())) {
			// 支付宝
			return Const.PayPlatformEnum.ALIPAY.getCode();
		}
		if (StringUtils.equals(accountType, Const.PayPlatformEnum.WECHAT.getValue())) {
			// 微信
			return Const.PayPlatformEnum.WECHAT.getCode();
		}
		return Const.PayPlatformEnum.ALIPAY.getCode();
	}

	/**
	 * 给前端显示提供支持 0-未激活，1-激活
	 * 
	 * @param active
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:22:57
	 */
	public static String readAccountStatus(Integer active) {
		if (Const.AccountStatus.ACTIVE.getCode() == active) {
			return Const.AccountStatus.ACTIVE.getValue();
		} else {
			return Const.AccountStatus.UNACTIVE.getValue();
		}
	}

	/**
	 * 给后端保存提供支持
	 * 
	 * @param active
	 * @return
	 * @author cz
	 * @time 2018年4月28日下午6:49:43
	 */
	public static Integer writeAccountStatus(String active) {
		if (StringUtils.equals(Const.AccountStatus.ACTIVE.getValue(), active)) {
			return Const.AccountStatus.ACTIVE.getCode();
		} else {
			return Const.AccountStatus.UNACTIVE.getCode();
		}
	}
	
	/**
	 * 支付条目是否已激活， 给前端封装提供支持
	 * 
	 * @param active
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午12:17:50
	 */
	public static String writeItemStatus(Integer active) {
		if (Const.ItemStatus.ACTIVE.getCode() == active) {
			return Const.ItemStatus.ACTIVE.getValue();
		} else {
			return Const.ItemStatus.UNACTIVE.getValue();
		}
	}

	/**
	 * @param active
	 * @return
	 * @author cz
	 * @time 2018年4月29日上午12:26:42
	 */
	public static Integer readItemStatus(String active) {
		if (StringUtils.equals(Const.ItemStatus.ACTIVE.getValue(), active)) {
			return Const.ItemStatus.ACTIVE.getCode();
		} else {
			return Const.ItemStatus.UNACTIVE.getCode();
		}
	}
}
