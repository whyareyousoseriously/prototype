/**
 * 下午5:47:23
 * power
 */
package service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import common.Const;
import common.ServerResponse;
import dao.IOrderDao;
import dao.IOrderItemDao;
import dao.IPayInfoDetailsDao;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import dao.impl.PayInfoDetailsImpl;
import pojo.PayInfoDetails;
import pojo.UserOrder;
import pojo.UserOrderItem;
import service.IOrderService;
import utils.BigDecimalUtil;
import utils.DateTimeUtil;
import utils.FTPUtil;
import utils.PropertiesUtil;

/**
 * 
 * @author cz 2018年4月30日下午5:47:23
 */
public class OrderServiceImpl implements IOrderService {
	private IOrderDao iOrderDao = new OrderDaoImpl();
	private IOrderItemDao iOrderItemDao = new OrderItemDaoImpl();
	private IPayInfoDetailsDao iPayInfoDetailsDao = new PayInfoDetailsImpl();

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	/**
	 * 订单号的生成规则
	 * 
	 * @return
	 * @author cz
	 * @time 2018年4月30日下午9:40:03
	 */
	private long generateOrderNo() {
		long currentTime = System.currentTimeMillis();
		return currentTime + currentTime % 10;
	}

	// 简单打印应答
	private void dumpResponse(AlipayResponse response) {
		if (response != null) {
			logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
			if (StringUtils.isNotEmpty(response.getSubCode())) {
				logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
			}
			logger.info("body:" + response.getBody());
		}
	}

	public ServerResponse<Map<String, String>> pay(Long orderNo, String userId, String path) {

		Map<String, String> resultMap = Maps.newHashMap();
		UserOrder userOrder = iOrderDao.selectByUserIdAndOrderNo(userId, orderNo);
		if (userOrder == null) {
			return ServerResponse.createByError();
		} else {
			if (userOrder.getStatus() == Const.OrderStatusEnum.NO_PAY.getCode()) {
				resultMap.put("orderNo", String.valueOf(userOrder.getOrderNo()));
				// 生成支付宝订单的各种参数
				// (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
				// 需保证商户系统端不能重复，建议通过数据库sequence生成，
				String outTradeNo = userOrder.getOrderNo().toString();

				// (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
				String subject = new StringBuilder().append("paymentSystem.primefaces扫码支付，订单号:").append(outTradeNo)
						.toString();

				// (必填) 订单总金额，单位为元，不能超过1亿元
				// 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
				String totalAmount = userOrder.getPayment().toString();

				// (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
				// 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
				String undiscountableAmount = "0";

				// 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
				// 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
				String sellerId = "";

				// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
				String body = new StringBuilder().append("订单").append(outTradeNo).append("需支付").append(totalAmount)
						.append("元").toString();

				// 商户操作员编号，添加此参数可以为商户操作员做销售统计
				String operatorId = "test_operator_id";

				// (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
				String storeId = "test_store_id";

				// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
				ExtendParams extendParams = new ExtendParams();
				extendParams.setSysServiceProviderId("2088100200300400500");

				// 支付超时，定义为120分钟
				String timeoutExpress = "120m";

				// 商品明细列表，需填写购买商品详细信息，
				List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
				List<UserOrderItem> orderItemList = iOrderItemDao.listByOrderNOAndUserId(userId, orderNo);
				for (UserOrderItem userOrderItem : orderItemList) {
					GoodsDetail goods1 = GoodsDetail.newInstance(userOrderItem.getItemId(), userOrderItem.getItemName(),
							BigDecimalUtil.mul(userOrderItem.getCurrentUnitPrice().doubleValue(),
									new Double(100).doubleValue()).longValue(),
							userOrderItem.getQuantity());
					goodsDetailList.add(goods1);
				}
				/*
				 * // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
				 * GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000,
				 * 1); // 创建好一个商品后添加至商品明细列表 goodsDetailList.add(goods1);
				 * 
				 * // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件 GoodsDetail goods2 =
				 * GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
				 * goodsDetailList.add(goods2);
				 */

				// 创建扫码支付请求builder，设置请求参数
				AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
						.setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
						.setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
						.setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
						.setTimeoutExpress(timeoutExpress)
						.setNotifyUrl(PropertiesUtil.getProperty("alipay.callback.url"))// 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
						.setGoodsDetailList(goodsDetailList);

				/**
				 * 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
				 * Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
				 */
				Configs.init("zfbinfo.properties");

				/**
				 * 使用Configs提供的默认参数 AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
				 */
				AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
				AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
				switch (result.getTradeStatus()) {
				case SUCCESS:
					logger.info("支付宝预下单成功: )");

					AlipayTradePrecreateResponse response = result.getResponse();
					dumpResponse(response);

					// 创建目录
					File folder = new File(path);
					if (!folder.exists()) {
						folder.setWritable(true);
						folder.mkdirs();
					}

					// 需要修改为运行机器上的路径
					// 细节细节细节
					String qrPath = String.format(path + "/qr-%s.png", response.getOutTradeNo());
					String qrFileName = String.format("qr-%s.png", response.getOutTradeNo());
					ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);

					File targetFile = new File(path, qrFileName);
					try {
						FTPUtil.uploadFile(Lists.newArrayList(targetFile));
					} catch (IOException e) {
						logger.error("上传二维码失败", e);
					}
					logger.info("qrPath:" + qrPath);
					String qrUrl = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFile.getName();
					resultMap.put("qrUrl", qrUrl);

					// ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
					return ServerResponse.createBySuccess(resultMap);

				case FAILED:
					logger.error("支付宝预下单失败!!!");
					return ServerResponse.createByErrorMessage("支付宝预下单失败!!!");

				case UNKNOWN:
					logger.error("系统异常，预下单状态未知!!!");
					return ServerResponse.createByErrorMessage("系统异常，预下单状态未知!!!");

				default:
					logger.error("不支持的交易状态，交易返回异常!!!");
					return ServerResponse.createByErrorMessage("不支持的交易状态，交易返回异常!!!");
				}
			}else {
				return ServerResponse.createByErrorMessage("该订单不是处于未支付状态，无法支付");
			}

		}

	}

	@SuppressWarnings("rawtypes")
	public ServerResponse aliCallback(Map<String, String> params) {
		Long orderNo = Long.parseLong(params.get("out_trade_no"));
		String tradeNo = params.get("trade_no");
		String tradeStatus = params.get("trade_status");
		UserOrder userOrder = iOrderDao.selectByOrderNo(orderNo);
		if (userOrder == null) {
			return ServerResponse.createByErrorMessage("非paymentSystem的订单，回调忽略");
		}
		if (userOrder.getStatus() >= Const.OrderStatusEnum.PAID.getCode()) {
			return ServerResponse.createBySuccess("支付宝重复调用");
		}
		if (Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)) {
			userOrder.setPaymentTime(DateTimeUtil.strToDate(params.get("gmt_payment")));
			userOrder.setStatus(Const.OrderStatusEnum.PAID.getCode());
			iOrderDao.updateByPrimaryKeySelective(userOrder);
		}

		PayInfoDetails payInfoDetails = new PayInfoDetails();
		payInfoDetails.setUserId(userOrder.getUserId());
		payInfoDetails.setOrderNo(userOrder.getOrderNo());
		payInfoDetails.setPayPlatform(Const.PayPlatformEnum.ALIPAY.getCode());
		payInfoDetails.setPlatformNumber(tradeNo);
		payInfoDetails.setPlatformStatus(tradeStatus);

		iPayInfoDetailsDao.insert(payInfoDetails);
		return ServerResponse.createBySuccess();

	}

	@SuppressWarnings("rawtypes")
	public ServerResponse queryOrderPayStatus(String userId, Long orderNo) {
		UserOrder userOrder = iOrderDao.selectByUserIdAndOrderNo(userId, orderNo);
		if (userOrder == null) {
			return ServerResponse.createByErrorMessage("用户没有该订单");
		}
		if (userOrder.getStatus() >= Const.OrderStatusEnum.PAID.getCode()) {
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOrderService#listUserOrderByUserId(java.lang.String)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午8:58:44
	 */
	@Override
	public ServerResponse<List<UserOrder>> listUserOrderByUserId(String userId) {
		List<UserOrder> userOrderList = iOrderDao.listUserOrderByUserId(userId);
		if (userOrderList.isEmpty()) {
			ServerResponse.createByError();
		}
		return ServerResponse.createBySuccess(userOrderList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOrderService#listUserOrderByUserIdAndStatus(java.lang.String,
	 * java.lang.Integer)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午9:13:26
	 */
	@Override
	public ServerResponse<List<UserOrder>> listUserOrderByUserIdAndStatus(String userId, Integer condition) {
		List<UserOrder> userOrderList = iOrderDao.listUserOrderByUserIdAndStatus(userId, condition);
		if (userOrderList.isEmpty()) {
			ServerResponse.createByError();
		}
		return ServerResponse.createBySuccess(userOrderList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOrderService#saveOneUserOrder(pojo.UserOrder)
	 * 
	 * @author cz
	 * 
	 * @time 2018年4月30日下午9:52:55
	 */
	@Override
	public ServerResponse<UserOrder> saveOneUserOrder(UserOrder userOrder) {
		// 设置订单号
		userOrder.setOrderNo(this.generateOrderNo());
		UserOrder userOrder_feedback = iOrderDao.saveOneUserOrder(userOrder);
		if (userOrder_feedback == null) {
			return ServerResponse.createByError();
		} else {
			return ServerResponse.createBySuccess(userOrder_feedback);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOrderService#saveOneUserOrderItem(pojo.UserOrderItem)
	 * 
	 * @author cz
	 * 
	 * @time 2018年5月1日上午11:39:59
	 */
	@Override
	public ServerResponse<UserOrderItem> saveOneUserOrderItem(UserOrderItem userOrderItem) {
		UserOrderItem userOrderItem_feedback = iOrderItemDao.saveOneUserOrderItem(userOrderItem);
		if (userOrderItem_feedback == null) {
			return ServerResponse.createByError();
		} else {
			return ServerResponse.createBySuccess(userOrderItem_feedback);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.IOrderService#cancelOneUserOrder(pojo.UserOrder)
	 * 
	 * @author cz
	 * 
	 * @time 2018年5月1日下午4:10:10
	 */
	@Override
	public ServerResponse<UserOrder> cancelOneUserOrder(UserOrder toUserOrder) {
		UserOrder check = iOrderDao.selectById(toUserOrder.getId());
		if (check != null) {
			if (Const.OrderStatusEnum.NO_PAY.getCode() == check.getStatus()) {
				// 如果找到了订单，并且订单处于未支付的状态才能取消订单
				check.setStatus(Const.OrderStatusEnum.CANCELED.getCode());
				check.setUpdateTime(new Date());
				UserOrder userOrder_feedback = iOrderDao.saveOneUserOrder(check);
				if (userOrder_feedback != null) {
					return ServerResponse.createBySuccess();
				} else {
					return ServerResponse.createByErrorMessage("订单取消失败");
				}
			} else {
				return ServerResponse.createByErrorMessage("只有未付款的订单才能取消");
			}
		} else {
			// 未找到订单
			return ServerResponse.createByErrorMessage("未找到订单");
		}
	}
}
