/**
 * 上午11:53:09
 * power
 */
package utils;



import org.apache.commons.lang3.StringUtils;

import common.Const;

import pojo.Item;
import pojo.ManagerDetails;
import service.IManagerService;
import service.impl.ManagerServiceImpl;
import vo.ItemVo;

/**
 * 给前后端数据转换，提供支持
 * @author cz
 * 2018年4月29日上午11:53:09
 */
public class VoUtil {
	private static IManagerService iManagerService = new ManagerServiceImpl();

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
	
	public static String writePayStatus(Integer status) {
		if(Const.PayStatus.PAID.getCode() ==status) {
			return Const.PayStatus.PAID.getValue();
		}
		if(Const.PayStatus.NO_PAY.getCode()==status) {
			return Const.PayStatus.NO_PAY.getValue();
		}
		return Const.PayStatus.UNKNOWN.getValue();
	}
	
	public static Integer readPayStatus(String status) {
		if(StringUtils.equals(Const.PayStatus.PAID.getValue(), status)) {
			return Const.PayStatus.PAID.getCode();
		}
		if(StringUtils.equals(Const.PayStatus.NO_PAY.getValue(), status)) {
			return Const.PayStatus.NO_PAY.getCode();
		}
		return Const.PayStatus.UNKNOWN.getCode();
	}

	/**
	 * @param data
	 * @return
	 * @author cz
	 * @time 2018年4月29日下午8:03:19
	 */
	public static ItemVo ItemToItemVo(Item data) {
		if(data==null) {
			return new ItemVo();
		}
		ItemVo itemVo = new ItemVo();
		itemVo.setAccountId(data.getAccountId());
		ManagerDetails managerDetails = iManagerService .selectManagerDetailsByAccountId(data.getAccountId())
				.getData();
		if (managerDetails == null) {
			// 判空，防止出错
			itemVo.setAccountType(null);
		} else {
			itemVo.setAccountType(VoUtil.readAccountType(managerDetails.getAccountType()));
		}
		itemVo.setActive(VoUtil.readAccountStatus(data.getActive()));
		itemVo.setCreateTime(DateTimeUtil.dateToStr(data.getCreateTime()));
		itemVo.setItemId(data.getItemId());
		itemVo.setItemName(data.getItemName());
		itemVo.setManagerId(data.getManagerId());
		itemVo.setPrice(data.getPrice().toString());
		itemVo.setUpdateTime(DateTimeUtil.dateToStr(data.getUpdateTime()));
		return itemVo;
	}
}
