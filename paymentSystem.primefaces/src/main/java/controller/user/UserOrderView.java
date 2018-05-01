/**
 * 下午7:44:52
 * power
 */
package controller.user;

import java.io.IOException;


import java.util.List;
import java.util.Map;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.common.collect.Lists;


import common.Const;
import common.ResponseCode;
import common.ServerResponse;
import pojo.User;
import pojo.UserOrder;
import service.IOrderService;
import service.impl.OrderServiceImpl;

import utils.VoUtil;
import vo.PayInfoDetailsVo;
import vo.QRCodeVo;
import vo.UserOrderItemVo;
import vo.UserOrderVo;

/**
 * 
 * @author cz 2018年4月30日下午7:44:52
 */
@ManagedBean(name = "userOrderView")
@SessionScoped
public class UserOrderView {
	private static final Logger logger = LoggerFactory.getLogger(UserOrderView.class);
	private IOrderService iOrderService;

	private UserOrderVo userOrderVo;
	private UserOrderItemVo userOrderItemVo;
	private PayInfoDetailsVo payInfoDetailsVo;
	private List<UserOrderVo> userOrderVoList;
	
	private QRCodeVo qRCodeVo;

	private String status;// 0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭

	public UserOrderView() {
		this.qRCodeVo = new QRCodeVo();
		this.userOrderVo = new UserOrderVo();
		this.userOrderItemVo = new UserOrderItemVo();
		this.payInfoDetailsVo = new PayInfoDetailsVo();
		this.userOrderVoList = Lists.newArrayList();
		this.iOrderService = new OrderServiceImpl();
	}

	public UserOrderVo getUserOrderVo() {
		return userOrderVo;
	}

	public UserOrderItemVo getUserOrderItemVo() {
		return userOrderItemVo;
	}

	public PayInfoDetailsVo getPayInfoDetailsVo() {
		return payInfoDetailsVo;
	}

	public List<UserOrderVo> getUserOrderVoList() {
		return userOrderVoList;
	}

	public void setUserOrderVo(UserOrderVo userOrderVo) {
		this.userOrderVo = userOrderVo;
	}

	public void setUserOrderItemVo(UserOrderItemVo userOrderItemVo) {
		this.userOrderItemVo = userOrderItemVo;
	}

	public void setPayInfoDetailsVo(PayInfoDetailsVo payInfoDetailsVo) {
		this.payInfoDetailsVo = payInfoDetailsVo;
	}

	public void setUserOrderVoList(List<UserOrderVo> userOrderVoList) {
		this.userOrderVoList = userOrderVoList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	

	public QRCodeVo getqRCodeVo() {
		return qRCodeVo;
	}

	public void setqRCodeVo(QRCodeVo qRCodeVo) {
		this.qRCodeVo = qRCodeVo;
	}

	private HttpSession getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(true);
		return session;
	}

	public void listUserOrderVo() {
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		if (user == null) {
			ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
					ResponseCode.NEED_LOGIN.getDesc());
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 通过权限判断,调用iOrderService获取详细信息
		ServerResponse<List<UserOrder>> response = iOrderService.listUserOrderByUserId(user.getUserId());
		if (response.isSuccess()) {
			this.userOrderVoList = Lists.newArrayList();
			logger.info("获取该用户所有订单信息成功");
			ServerResponse.createBySuccessMessage("获取该用户所有订单信息成功");
			List<UserOrder> userOrderList = response.getData();
			for (UserOrder userOrder : userOrderList) {
				this.userOrderVoList.add(VoUtil.UserOrderToVo(userOrder));
			}
		} else {
			logger.error("获取该用户所有订单信息失败");
			ServerResponse.createByErrorMessage("获取该用户所有订单信息失败");
			this.userOrderVoList = Lists.newArrayList();
		}
	}

	public void listUserOrderVoByStatus() {
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		if (user == null) {
			ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
					ResponseCode.NEED_LOGIN.getDesc());
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 通过权限判断,调用iOrderService获取详细信息
		ServerResponse<List<UserOrder>> response = iOrderService.listUserOrderByUserIdAndStatus(user.getUserId(),
				VoUtil.readOrderStatus(status));
		if (response.isSuccess()) {
			this.userOrderVoList = Lists.newArrayList();
			logger.info("获取该用户" + status + "订单信息成功");
			ServerResponse.createBySuccessMessage("获取该用户" + status + "订单信息成功");
			List<UserOrder> userOrderList = response.getData();
			for (UserOrder userOrder : userOrderList) {
				this.userOrderVoList.add(VoUtil.UserOrderToVo(userOrder));
			}
		} else {
			logger.error("获取该用户" + status + "订单信息失败");
			ServerResponse.createByErrorMessage("获取该用户" + status + "订单信息失败");
			this.userOrderVoList = Lists.newArrayList();
		}
	}

	public void cancelOneOrder() {
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		if (user == null) {
			ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
					ResponseCode.NEED_LOGIN.getDesc());
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 通过权限判断,
		// 通过userOrderVo生成userOrder,并更新数据
		ServerResponse<UserOrder> response = iOrderService.cancelOneUserOrder(VoUtil.ToUserOrder(userOrderVo));
		if(response.isSuccess()) {
			logger.info("订单取消成功");
			ServerResponse.createBySuccessMessage("订单取消成功");
		}else {
			logger.error("订单取消失败");
			ServerResponse.createByErrorMessage("订单取消失败");
		}
		this.listUserOrderVoByStatus();
		
	}

	public void alipay() {
		// 获得当前session中的当前用户
		User user = (User) this.getCurrentSession().getAttribute(Const.CURRENT_USER);
		// 进行判断
		if (user == null) {
			ServerResponse.createByErrorMessage("用户未登录，无法获取详细信息");
			// 返回登陆界面
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
								+ "login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String path = getCurrentSession().getServletContext().getRealPath("upload");
			Long orderNo = Long.valueOf(userOrderVo.getOrderNo());
			ServerResponse<Map<String,String>> response = iOrderService.pay(orderNo, user.getUserId(), path);
			
			if (response.isSuccess()) {
				// 进行页面跳转
				qRCodeVo.setOrderNo(response.getData().get("orderNo"));
				qRCodeVo.setQrCode(response.getData().get("qrUrl"));
				ServerResponse.createBySuccessMessage("进行页面跳转");
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("qrCode.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			this.listUserOrderVoByStatus();
		}
	}

}
