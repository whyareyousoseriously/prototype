/**
 * 上午11:53:09
 * power
 */
package utils;


import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import common.Const;

import pojo.Item;
import pojo.ManagerDetails;
import pojo.PayInfoDetails;
import pojo.UserOrder;
import pojo.UserOrderItem;
import service.IManagerService;
import service.impl.ManagerServiceImpl;
import vo.ItemVo;
import vo.PayInfoDetailsVo;
import vo.UserOrderItemVo;
import vo.UserOrderVo;

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
	
	public static String writePayPlatform(Integer payPlatform) {
		if(Const.PayPlatformEnum.ALIPAY.getCode()==payPlatform) {
			return Const.PayPlatformEnum.ALIPAY.getValue();
		}
		if(Const.PayPlatformEnum.WECHAT.getCode()==payPlatform) {
			return Const.PayPlatformEnum.WECHAT.getValue();
		}
		return Const.PayPlatformEnum.UNKNOWN.getValue();
	}
	public static Integer readPayPlatform(String payPlatform) {
		if(StringUtils.equals(Const.PayPlatformEnum.ALIPAY.getValue(), payPlatform)) {
			return Const.PayPlatformEnum.ALIPAY.getCode();
		}
		if(StringUtils.equals(Const.PayPlatformEnum.WECHAT.getValue(), payPlatform)) {
			return Const.PayPlatformEnum.WECHAT.getCode();
		}
		return Const.PayPlatformEnum.UNKNOWN.getCode();
	}
	public static String writePaymentType(Integer paymentType) {
		if(Const.PaymentTypeEnum.ONLINE_PAY.getCode()==paymentType) {
			return Const.PaymentTypeEnum.ONLINE_PAY.getValue();
		}
		return Const.PaymentTypeEnum.UNKNOWN.getValue();
	}
	public static Integer readPaymentType(String paymentType) {
		if(StringUtils.equals(Const.PaymentTypeEnum.ONLINE_PAY.getValue(), paymentType)) {
			return Const.PaymentTypeEnum.ONLINE_PAY.getCode();
		}
		return Const.PaymentTypeEnum.UNKNOWN.getCode();
	}
	public static String writeOrderStatus(Integer status) {
		if(Const.OrderStatusEnum.NO_PAY.getCode()==status) {
			return Const.OrderStatusEnum.NO_PAY.getValue();
		}
		if(Const.OrderStatusEnum.ORDER_CLOSE.getCode()==status) {
			return Const.OrderStatusEnum.ORDER_CLOSE.getValue();
		}
		if(Const.OrderStatusEnum.ORDER_SUCCESS.getCode()==status) {
			return Const.OrderStatusEnum.ORDER_SUCCESS.getValue();
		}
		if(Const.OrderStatusEnum.PAID.getCode()==status) {
			return Const.OrderStatusEnum.PAID.getValue();
		}
		if(Const.OrderStatusEnum.SHIPPED.getCode()==status) {
			return Const.OrderStatusEnum.SHIPPED.getValue();
		}
		if(Const.OrderStatusEnum.CANCELED.getCode()==status) {
			return Const.OrderStatusEnum.CANCELED.getValue();
		}
		return Const.OrderStatusEnum.UNKNOWN.getValue();
	}
	public static Integer readOrderStatus(String status) {
		if(StringUtils.equals(Const.OrderStatusEnum.NO_PAY.getValue(), status)) {
			return Const.OrderStatusEnum.NO_PAY.getCode();
		}
		if(StringUtils.equals(Const.OrderStatusEnum.ORDER_CLOSE.getValue(), status)) {
			return Const.OrderStatusEnum.ORDER_CLOSE.getCode();
		}
		if(StringUtils.equals(Const.OrderStatusEnum.ORDER_SUCCESS.getValue(),status)) {
			return Const.OrderStatusEnum.ORDER_SUCCESS.getCode();
		}
		if(StringUtils.equals(Const.OrderStatusEnum.PAID.getValue(), status)) {
			return Const.OrderStatusEnum.PAID.getCode();
		}
		if(StringUtils.equals(Const.OrderStatusEnum.SHIPPED.getValue(), status)) {
			return Const.OrderStatusEnum.SHIPPED.getCode();
		}
		if(StringUtils.equals(Const.OrderStatusEnum.CANCELED.getValue(), status)) {
			return Const.OrderStatusEnum.CANCELED.getCode();
		}
		return Const.OrderStatusEnum.UNKNOWN.getCode();
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
	
	public static PayInfoDetailsVo PayInfoDetailsToVo(PayInfoDetails payInfoDetails) {
		if(payInfoDetails==null) {
			return new PayInfoDetailsVo();
		}
		PayInfoDetailsVo payInfoDetailsVo = new PayInfoDetailsVo();
		payInfoDetailsVo.setCreateTime(DateTimeUtil.dateToStr(payInfoDetails.getCreateTime()));
		payInfoDetailsVo.setId(payInfoDetails.getId());
		payInfoDetailsVo.setOrderNo(payInfoDetails.getOrderNo().toString());
		payInfoDetailsVo.setPayPlatform(VoUtil.writePayPlatform(payInfoDetails.getPayPlatform()));
		payInfoDetailsVo.setPlatformNumber(payInfoDetails.getPlatformNumber());
		payInfoDetailsVo.setPlatformStatus(payInfoDetails.getPlatformStatus());
		payInfoDetailsVo.setUpdateTime(DateTimeUtil.dateToStr(payInfoDetails.getUpdateTime()));
		return payInfoDetailsVo;
	}
	
	public static UserOrderItemVo UserOrderItemToVo(UserOrderItem userOrderItem) {
		if(userOrderItem==null) {
			return new UserOrderItemVo();
		}
		UserOrderItemVo userOrderItemVo = new UserOrderItemVo();
		userOrderItemVo.setCreateTime(DateTimeUtil.dateToStr(userOrderItem.getCreateTime()));
		userOrderItemVo.setCurrentUnitPrice(userOrderItem.getCurrentUnitPrice().toString());
		userOrderItemVo.setId(userOrderItem.getId());
		userOrderItemVo.setItemId(userOrderItem.getItemId());
		userOrderItemVo.setItemImage(userOrderItem.getItemImage());
		userOrderItemVo.setItemName(userOrderItem.getItemName());
		userOrderItemVo.setOrderNo(userOrderItem.getOrderNo().toString());
		userOrderItemVo.setQuantity(userOrderItem.getQuantity().toString());
		userOrderItemVo.setTotalPrice(userOrderItem.getTotalPrice().toString());
		userOrderItemVo.setUpdateTime(DateTimeUtil.dateToStr(userOrderItem.getUpdateTime()));
		userOrderItemVo.setUserId(userOrderItem.getUserId());
		return userOrderItemVo;
	}
	
	public static UserOrderVo UserOrderToVo(UserOrder userOrder) {
		if(userOrder==null) {
			return new UserOrderVo();
		}
		UserOrderVo userOrderVo = new UserOrderVo();
		userOrderVo.setCloseTime(DateTimeUtil.dateToStr(userOrder.getCloseTime()));
		userOrderVo.setCreateTime(DateTimeUtil.dateToStr(userOrder.getCreateTime()));
		userOrderVo.setEndTime(DateTimeUtil.dateToStr(userOrder.getEndTime()));
		userOrderVo.setId(userOrder.getId());
		userOrderVo.setOrderNo(userOrder.getOrderNo().toString());
		userOrderVo.setPayment(userOrder.getPayment().toString());
		userOrderVo.setPaymentTime(DateTimeUtil.dateToStr(userOrder.getPaymentTime()));
		userOrderVo.setPaymentType(VoUtil.writePaymentType(userOrder.getPaymentType()));
		userOrderVo.setPostage(userOrder.getPostage().toString());
		userOrderVo.setSendTime(DateTimeUtil.dateToStr(userOrder.getSendTime()));
		userOrderVo.setShippingId(userOrder.getShippingId().toString());
		userOrderVo.setStatus(VoUtil.writeOrderStatus(userOrder.getStatus()));
		userOrderVo.setUpdateTime(DateTimeUtil.dateToStr(userOrder.getUpdateTime()));
		userOrderVo.setUserId(userOrder.getUserId());
		return userOrderVo;
	}
	
	public static UserOrder ToUserOrder(UserOrderVo userOrderVo) {
		if(userOrderVo==null) {
			return new UserOrder();
		}
		UserOrder userOrder = new UserOrder();
		userOrder.setCloseTime(DateTimeUtil.strToDate(userOrderVo.getCloseTime()));
		userOrder.setCreateTime(DateTimeUtil.strToDate(userOrderVo.getCreateTime()));
		userOrder.setEndTime(DateTimeUtil.strToDate(userOrderVo.getEndTime()));
		userOrder.setId(userOrderVo.getId());
		userOrder.setOrderNo(Long.getLong(userOrderVo.getOrderNo()));
		userOrder.setPayment(new BigDecimal(userOrderVo.getPayment()));
		userOrder.setPaymentTime(DateTimeUtil.strToDate(userOrderVo.getPaymentTime()));
		userOrder.setPaymentType(VoUtil.readPaymentType(userOrderVo.getPaymentType()));
		userOrder.setPostage(Integer.valueOf(userOrderVo.getPostage()));
		userOrder.setSendTime(DateTimeUtil.strToDate(userOrderVo.getSendTime()));
		userOrder.setShippingId(Integer.valueOf(userOrderVo.getShippingId()));
		userOrder.setStatus(VoUtil.readOrderStatus(userOrderVo.getStatus()));
		userOrder.setUpdateTime(DateTimeUtil.strToDate(userOrderVo.getUpdateTime()));
		userOrder.setUserId(userOrderVo.getUserId());
		return userOrder;
		
	}
}
